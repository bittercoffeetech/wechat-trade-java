package tech.bittercoffee.wechat.api.trade;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.time.LocalDate;
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

import tech.bittercoffee.wechat.api.trade.actions.WechatTradeBillAllAction;
import tech.bittercoffee.wechat.api.trade.actions.WechatTradeBillRefundAction;
import tech.bittercoffee.wechat.api.trade.actions.WechatTradeBillSuccessAction;
import tech.bittercoffee.wechat.api.trade.actions.WechatTradeCloseAction;
import tech.bittercoffee.wechat.api.trade.actions.WechatTradeCreateAction;
import tech.bittercoffee.wechat.api.trade.actions.WechatTradeCreateNotify;
import tech.bittercoffee.wechat.api.trade.actions.WechatTradeFundflowAction;
import tech.bittercoffee.wechat.api.trade.actions.WechatTradeQueryAction;
import tech.bittercoffee.wechat.api.trade.actions.WechatTradeRefundAction;
import tech.bittercoffee.wechat.api.trade.actions.WechatTradeRefundNotify;
import tech.bittercoffee.wechat.api.trade.actions.WechatTradeRefundQueryAction;
import tech.bittercoffee.wechat.api.trade.enums.ErrorCodeEnum;
import tech.bittercoffee.wechat.api.trade.models.TradeBillAllRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeBillAllResponse;
import tech.bittercoffee.wechat.api.trade.models.TradeBillRefundRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeBillRefundResponse;
import tech.bittercoffee.wechat.api.trade.models.TradeBillSuccessRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeBillSuccessResponse;
import tech.bittercoffee.wechat.api.trade.models.TradeCloseRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeCloseResponse;
import tech.bittercoffee.wechat.api.trade.models.TradeCreateNotify;
import tech.bittercoffee.wechat.api.trade.models.TradeCreateRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeCreateResponse;
import tech.bittercoffee.wechat.api.trade.models.TradeFundflowRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeFundflowResponse;
import tech.bittercoffee.wechat.api.trade.models.TradeQueryRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeQueryResponse;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundNotify;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundQueryRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundQueryResponse;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundResponse;
import tech.bittercoffee.wechat.api.trade.models.TradeResult;
import tech.bittercoffee.wechat.api.trade.models.TradeReturn;
import tech.bittercoffee.wechat.api.trade.models.TradeSignatureInfo;

/**
 * 微信支付客户端的默认实现
 * 
 * @author BitterCoffee
 *
 */
public final class WechatTradeClient {

	private static final WechatTradeCreateAction WECHAT_TRADE_CREATE_ACTION = new WechatTradeCreateAction();
	private static final WechatTradeQueryAction WECHAT_TRADE_QUERY_ACTION = new WechatTradeQueryAction();
	private static final WechatTradeCloseAction WECHAT_TRADE_CLOSE_ACTION = new WechatTradeCloseAction();
	private static final WechatTradeRefundAction WECHAT_TRADE_REFUND_ACTION = new WechatTradeRefundAction();
	private static final WechatTradeRefundQueryAction WECHAT_TRADE_REFUND_QUERY_ACTION = new WechatTradeRefundQueryAction();
	private static final WechatTradeBillAllAction WECHAT_TRADE_BILL_ALL_ACTION = new WechatTradeBillAllAction();
	private static final WechatTradeBillSuccessAction WECHAT_TRADE_BILL_SUCCESS_ACTION = new WechatTradeBillSuccessAction();
	private static final WechatTradeBillRefundAction WECHAT_TRADE_BILL_REFUND_ACTION = new WechatTradeBillRefundAction();
	private static final WechatTradeFundflowAction WECHAT_TRADE_FUNDFLOW_ACTION = new WechatTradeFundflowAction();
	private static final WechatTradeCreateNotify WECHAT_TRADE_CREATE_NOTIFY = new WechatTradeCreateNotify();
	private static final WechatTradeRefundNotify WECHAT_TRADE_REFUND_NOTIFY = new WechatTradeRefundNotify();

	private String appId;
	private String mchId;
	private String mchKey;
	private InputStream apiCert;

	/**
	 * @param appId   应用ID
	 * @param mchId   商户ID
	 * @param mchKey  商户秘钥
	 * @param apiCert 客户端证书
	 * 
	 *                创建微信支付客户端对象
	 */
	public WechatTradeClient(String appId, String mchId, String mchKey, InputStream apiCert) {
		this.appId = appId;
		this.mchId = mchId;
		this.mchKey = mchKey;
		this.apiCert = apiCert;
	}

	private <S> S parseXmlResponse(final WechatTradeResponse<S> response, final InputStream input)
			throws WechatApiException, IOException {
		TradeXmlData txd = TradeXmlData.fromXml(input);
	
		TradeReturn returnModel = txd.toObject(TradeReturn.class);
		if (!returnModel.isSuccess()) {
			throw new WechatApiException(returnModel.getCode(), returnModel.getMessage());
		}
	
		TradeResult resultModel = txd.toObject(TradeResult.class);
		if (response.hasResult() && !resultModel.isSuccess()) {
			throw new WechatApiException(resultModel.getCode(), resultModel.getMessage());
		}
	
		if (response.hasSigned() && !txd.hasValidSign(response.responseSignType(), mchKey)) {
			throw new WechatApiException(ErrorCodeEnum.SIGNERROR);
		}
	
		if (isNotEmpty(response.encrypted())) {
			txd.decrypt(response.encrypted(), mchKey);
		}
	
		if (response.hasHierarchy()) {
			txd.hierarchy(response.getResponseType());
		}
	
		return txd.toObject(response.getResponseType());
	}

	private <S> S parseCsvResponse(final WechatTradeResponse<S> response, final InputStream input) throws IOException {
		return TradeCsvData.fromStream(response.getResponseType(), input);
	}

	private <R extends TradeSignatureInfo, S> S execute(WechatTradeAction<R, S> action, R model) throws WechatApiException {
		
		Supplier<CloseableHttpClient> createHttpClient = () -> {
			CloseableHttpClient httpClient = null;
			if (action.certificated()) {
				try {
					final KeyStore keyStore = KeyStore.getInstance("PKCS12");
					keyStore.load(apiCert, mchId.toCharArray());
					final SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, mchId.toCharArray())
							.build();
					final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
							.setSslContext(sslContext).setTlsVersions(TLS.V_1_2).build();
					final HttpClientConnectionManager conectionManager = PoolingHttpClientConnectionManagerBuilder.create()
							.setSSLSocketFactory(sslSocketFactory).build();

					httpClient = HttpClients.custom().setConnectionManager(conectionManager).build();
				} catch (IOException | GeneralSecurityException e) {
					httpClient = HttpClients.createDefault();
				}
			} else {
				httpClient = HttpClients.createDefault();
			}
			return httpClient;
		};
		
		Supplier<String> toRequestBody = () -> {
			TradeXmlData tv = TradeXmlData.fromObject(model);
			tv.put("appid", appId);
			tv.put("mch_id", mchId);
			tv.put("sign_type", action.requestSignType());
			return tv.toXml(action.getRequestType(), action.requestSignType(), mchKey);
		};
		
		try (CloseableHttpClient httpClient = createHttpClient.get()) {
			HttpPost httpPost = new HttpPost(action.url());
			httpPost.setEntity(new StringEntity(toRequestBody.get(), ContentType.APPLICATION_XML));

			try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
				if (response.getCode() == 200) {
					HttpEntity entity = response.getEntity();

					if (action.isStreaming()) {
						boolean isGzip = "application/x-gzip".equals(entity.getContentType());

						if (!isGzip && !entity.isChunked()) {
							return parseXmlResponse(action, entity.getContent());
						} else if (!isGzip && entity.isChunked()) {
							return parseCsvResponse(action, entity.getContent());
						} else {
							return parseCsvResponse(action, new GZIPInputStream(entity.getContent()));
						}
					} else {
						return parseXmlResponse((WechatTradeResponse<S>) action, entity.getContent());
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

	public TradeCreateNotify onCreateNotifier(InputStream xml) throws WechatApiException, IOException {
		return parseXmlResponse(WECHAT_TRADE_CREATE_NOTIFY, xml);
	}

	public TradeRefundNotify onRefundNotifier(InputStream xml) throws WechatApiException, IOException {
		return parseXmlResponse(WECHAT_TRADE_REFUND_NOTIFY, xml);
	}

	public TradeCreateResponse createTrade(final TradeCreateRequest model) throws WechatApiException {
		return execute(WECHAT_TRADE_CREATE_ACTION, model);
	}

	public TradeQueryResponse queryTrade(TradeQueryRequest model) throws WechatApiException {
		return execute(WECHAT_TRADE_QUERY_ACTION, model);
	}
	
	public TradeQueryResponse queryTrade(String outTradeNo) throws WechatApiException {
		return execute(WECHAT_TRADE_QUERY_ACTION, TradeQueryRequest.withTradeNo(outTradeNo));
	}

	public TradeCloseResponse closeTrade(TradeCloseRequest model) throws WechatApiException {
		return execute(WECHAT_TRADE_CLOSE_ACTION, model);
	}
	
	public TradeCloseResponse closeTrade(String outTradeNo) throws WechatApiException {
		return execute(WECHAT_TRADE_CLOSE_ACTION, TradeCloseRequest.withTradeNo(outTradeNo));
	}

	public TradeRefundResponse createRefund(TradeRefundRequest model) throws WechatApiException {
		return execute(WECHAT_TRADE_REFUND_ACTION, model);
	}
	
	public TradeRefundResponse createRefund(String tradeNo, long totalFee, long refundFeel) throws WechatApiException {
		return execute(WECHAT_TRADE_REFUND_ACTION, TradeRefundRequest.withTradeNo(tradeNo, totalFee, refundFeel));
	}

	public TradeRefundQueryResponse queryRefund(TradeRefundQueryRequest model) throws WechatApiException {
		return execute(WECHAT_TRADE_REFUND_QUERY_ACTION, model);
	}
	
	public TradeRefundQueryResponse queryRefund(String outTradeNo) throws WechatApiException {
		return execute(WECHAT_TRADE_REFUND_QUERY_ACTION, TradeRefundQueryRequest.withTradeNo(outTradeNo));
	}
	
	public TradeRefundQueryResponse queryRefund(String outTradeNo, String refundNo) throws WechatApiException {
		return execute(WECHAT_TRADE_REFUND_QUERY_ACTION, TradeRefundQueryRequest.withTradeNo(outTradeNo).refundNo(refundNo));
	}

	public TradeBillAllResponse downloadBillAll(TradeBillAllRequest model) throws WechatApiException {
		return execute(WECHAT_TRADE_BILL_ALL_ACTION, model);
	}
	
	public TradeBillAllResponse downloadBillAll(LocalDate billDate, boolean zip) throws WechatApiException {
		return execute(WECHAT_TRADE_BILL_ALL_ACTION, TradeBillAllRequest.of(billDate, zip));
	}

	public TradeBillSuccessResponse downloadBillSuccess(TradeBillSuccessRequest model) throws WechatApiException {
		return execute(WECHAT_TRADE_BILL_SUCCESS_ACTION, model);
	}
	
	public TradeBillSuccessResponse downloadBillSuccess(LocalDate billDate, boolean zip) throws WechatApiException {
		return execute(WECHAT_TRADE_BILL_SUCCESS_ACTION, TradeBillSuccessRequest.of(billDate, zip));
	}

	public TradeBillRefundResponse downloadBillRefund(TradeBillRefundRequest model) throws WechatApiException {
		return execute(WECHAT_TRADE_BILL_REFUND_ACTION, model);
	}
	
	public TradeBillRefundResponse downloadBillRefund(LocalDate billDate, boolean zip) throws WechatApiException {
		return execute(WECHAT_TRADE_BILL_REFUND_ACTION, TradeBillRefundRequest.of(billDate, zip));
	}

	public TradeFundflowResponse downloadFundflow(TradeFundflowRequest model) throws WechatApiException {
		return execute(WECHAT_TRADE_FUNDFLOW_ACTION, model);
	}
	
	public TradeFundflowResponse downloadFundflow(LocalDate billDate, boolean zip) throws WechatApiException {
		return execute(WECHAT_TRADE_FUNDFLOW_ACTION, TradeFundflowRequest.of(billDate, zip));
	}
}
