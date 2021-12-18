package tech.bittercoffee.wechat.api.trade.actions;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.SSLContext;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.ssl.TLS;
import org.apache.hc.core5.ssl.SSLContexts;

import com.fasterxml.jackson.core.JsonProcessingException;

import tech.bittercoffee.wechat.api.trade.UidGenerator;
import tech.bittercoffee.wechat.api.trade.WechatApiException;
import tech.bittercoffee.wechat.api.trade.WechatClientConfig;
import tech.bittercoffee.wechat.api.trade.enums.ErrorCodeEnum;

/**
 * 微信接口封装
 * 
 * @author BitterCoffee
 *
 * @param <R> 请求对象类型
 * @param <S> 返回对象类型
 */
public abstract class AbstractTradeAction<R, S> extends AbstractTradeResponsive<S> implements TradeRequest<R> {

	public AbstractTradeAction(WechatClientConfig config) {
		super(config);
	}

	public S execute(R model) throws WechatApiException {
		@SuppressWarnings("unchecked")
		TreeMap<String, Object> requestValues = xmlMapper.convertValue(model, TreeMap.class);

		Supplier<String> toXml = () -> {
			requestValues.put("appid", config.getAppId());
			requestValues.put("mch_id", config.getMchId());
			requestValues.put("sign_type", requestSignType());
			requestValues.put("nonce_str", UidGenerator.next());
			requestValues.put("sign", signOf.apply(requestValues, requestSignType()));

			try {
				return xmlMapper.writer().withRootName("xml").writeValueAsString(requestValues);
			} catch (JsonProcessingException e) {
				return "<xml/>";
			}
		};

		Supplier<CloseableHttpClient> createHttpClient = () -> {
			CloseableHttpClient httpClient = null;
			if (certificated()) {
				try {
					final KeyStore keyStore = KeyStore.getInstance("PKCS12");
					keyStore.load(config.getApiCert(), config.getMchId().toCharArray());
					final SSLContext sslContext = SSLContexts.custom()
							.loadKeyMaterial(keyStore, config.getMchId().toCharArray()).build();
					final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
							.setSslContext(sslContext).setTlsVersions(TLS.V_1_2).build();
					final HttpClientConnectionManager conectionManager = PoolingHttpClientConnectionManagerBuilder
							.create().setSSLSocketFactory(sslSocketFactory).build();

					httpClient = HttpClients.custom().setConnectionManager(conectionManager).build();
				} catch (IOException | GeneralSecurityException e) {
					httpClient = HttpClients.createDefault();
				}
			} else {
				httpClient = HttpClients.createDefault();
			}
			return httpClient;
		};

		try (CloseableHttpClient httpClient = createHttpClient.get()) {
			HttpPost httpPost = new HttpPost(url());
			httpPost.setEntity(new StringEntity(toXml.get()));
			httpPost.setHeader("Content-Type", ContentType.APPLICATION_XML);

			try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
				if (response.getCode() == 200) {
					HttpEntity entity = response.getEntity();

					if (isStreaming()) {
						boolean isGzip = "application/x-gzip".equals(entity.getContentType());

						if (!isGzip && !entity.isChunked()) {
							return forXml.apply(entity.getContent());
						} else if (!isGzip && entity.isChunked()) {
							return forCsv.apply(entity.getContent());
						} else {
							return forCsv.apply(new GZIPInputStream(entity.getContent()));
						}
					} else {
						return forXml.apply(entity.getContent());
					}
				} else {
					throw new WechatApiException(String.valueOf(response.getCode()), response.getReasonPhrase());
				}
			}
		} catch (WechatApiException wae) {
			throw wae;
		} catch (Exception e) {
			throw new WechatApiException(ErrorCodeEnum.of(ErrorCodeEnum.INVALID_REQUEST), e);
		}
	}

}
