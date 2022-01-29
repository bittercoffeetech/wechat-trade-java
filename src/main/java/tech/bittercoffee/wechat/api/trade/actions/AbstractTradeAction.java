package tech.bittercoffee.wechat.api.trade.actions;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.function.FailableSupplier;
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

import tech.bittercoffee.wechat.api.trade.UidGenerator;
import tech.bittercoffee.wechat.api.trade.WechatApiException;
import tech.bittercoffee.wechat.api.trade.WechatClientConfig;

/**
 * 微信接口封装
 * 
 * @author BitterCoffee
 *
 * @param <R> 请求对象类型
 * @param <S> 返回对象类型
 */
public abstract class AbstractTradeAction<R, S> extends AbstractTradeResponsive<S> implements TradeRequest<R> {

	protected AbstractTradeAction(WechatClientConfig config) {
		super(config);
	}
	
	private String toRequestXml(Map<String, Object> values) throws IOException {
		values.put("appid", config.getAppId());
		values.put("mch_id", config.getMchId());
		values.put("sign_type", requestSignType());
		values.put("nonce_str", UidGenerator.next());
		values.put("sign", signOf.apply(values, requestSignType()));

		return xmlMapper.writer().withRootName("xml").writeValueAsString(values);
	}
	
	private S parseResponse(boolean isGZip, boolean isChunked, InputStream content) throws IOException {
		if(isChunked) {
			return forCsv.apply(isGZip ? new GZIPInputStream(content) : content);
		} else {
			return forXml.apply(content);
		}
	}
	
	public S execute(R model) throws WechatApiException {
		@SuppressWarnings("unchecked")
		TreeMap<String, Object> requestValues = xmlMapper.convertValue(model, TreeMap.class);
		
		FailableSupplier<CloseableHttpClient, Exception> createHttpClient = () -> {
			if (!certificated()) {
				return HttpClients.createDefault();
			}
			final KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(config.getApiCert(), config.getMchId().toCharArray());
			final SSLContext sslContext = SSLContexts.custom()
					.loadKeyMaterial(keyStore, config.getMchId().toCharArray()).build();
			final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
					.setSslContext(sslContext).setTlsVersions(TLS.V_1_2).build();
			final HttpClientConnectionManager conectionManager = PoolingHttpClientConnectionManagerBuilder.create()
					.setSSLSocketFactory(sslSocketFactory).build();
			
			return HttpClients.custom().setConnectionManager(conectionManager).build();			
		};

		try (CloseableHttpClient httpClient = createHttpClient.get()) {
			HttpPost httpPost = new HttpPost(url());
			httpPost.setEntity(new StringEntity(toRequestXml(requestValues)));
			httpPost.setHeader("Content-Type", ContentType.APPLICATION_XML);

			try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
				if (response.getCode() != 200) {
					throw new WechatApiException(String.valueOf(response.getCode()), response.getReasonPhrase());
				}
				HttpEntity entity = response.getEntity();						
				boolean isGzip = "application/x-gzip".equals(entity.getContentType());
				boolean isChunked = entity.isChunked();
				InputStream content = entity.getContent();

				return parseResponse(isGzip, isChunked, content);
			}
		} catch (Exception e) {
			throw new WechatApiException(e);
		}
	}

}
