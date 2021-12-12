package tech.bittercoffee.wechat.api.trade;

import static java.util.Objects.nonNull;
import static org.apache.commons.beanutils.ConstructorUtils.invokeConstructor;
import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Hex.encodeHexString;
import static org.apache.commons.codec.digest.DigestUtils.md5;
import static org.apache.commons.collections.MapUtils.getIntValue;
import static org.apache.commons.collections.MapUtils.getString;
import static org.apache.commons.lang3.ArrayUtils.EMPTY_OBJECT_ARRAY;
import static org.apache.commons.lang3.ArrayUtils.add;
import static org.apache.commons.lang3.ClassUtils.isAssignable;
import static org.apache.commons.lang3.RegExUtils.removeAll;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.reflect.FieldUtils.getFieldsListWithAnnotation;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.io.LineIterator;
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

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

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
import tech.bittercoffee.wechat.api.trade.enums.SignTypeEnum;
import tech.bittercoffee.wechat.api.trade.models.ApiField;
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
import tech.bittercoffee.wechat.api.trade.models.TradeSheetResponse;
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
	private static final XmlMapper xmlMapper = new XmlMapper();
	private static final CsvMapper csvMapper = new CsvMapper();
	static {
		xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
		xmlMapper.setSerializationInclusion(Include.NON_NULL);
		csvMapper.enable(CsvParser.Feature.TRIM_SPACES);
		csvMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	}
	
	private String appId;
	private String mchId;
	private String mchKey;
	private InputStream apiCert;	
	
	private final class TradeValues extends TreeMap<String, Object> {
		private static final long serialVersionUID = -2271075041300944374L;
		
		public String toXml(Class<?> klass, SignTypeEnum signType) {
			this.put("appid", appId);
			this.put("mch_id", mchId);
			this.put("sign_type", signType);
			this.put("sign", getSign(signType));
			try {
				return xmlMapper.writer().withRootName("xml").writeValueAsString(xmlMapper.convertValue(this, klass));
			} catch (JsonProcessingException e) {
				return "<xml/>";
			}
		}
		
		public boolean hasValidSign(SignTypeEnum signType) {
			return getSign(signType).equals(getString(this, "sign"));
		}
		
		@SuppressWarnings("unchecked")
		public void decrypt(String propName) {
			String enc = getString(this, propName);

			if (isNotEmpty(enc)) {
				try {
					Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
					cipher.init(Cipher.DECRYPT_MODE,
							new SecretKeySpec(encodeHexString(md5(mchKey.getBytes()), true).getBytes(), "AES"));

					String decrypted = new String(cipher.doFinal(decodeBase64(enc)));
					putAll(xmlMapper.readValue(decrypted, Map.class));
					remove(propName);
				} catch (Exception e) {
					// do nothing
				}
			}
		}
			

		public void hierarchy(Class<?> klass) {
			TradeValues ntv = new TradeValues();
			readValues(klass, ntv);
			clear();
			putAll(ntv);
		};

		private String getSign(SignTypeEnum signType) {
			String toSign = entrySet().stream().filter(p -> !"sign".equals(p.getKey()))
					.filter(p -> nonNull(p.getValue()) && isNotEmpty(p.getValue().toString()))
					.map(p -> p.getKey() + "=" + p.getValue()).reduce((p1, p2) -> p1 + "&" + p2).orElse(EMPTY) + "&key="
					+ mchKey;
		
			if (SignTypeEnum.HMAC_SHA256.equals(signType)) {
				return encodeHexString(
						new HmacUtils(HmacAlgorithms.HMAC_SHA_256, mchKey.getBytes()).hmac(toSign.getBytes()), false);
			} else {
				return encodeHexString(md5(toSign.getBytes()), false);
			}
		}

		/**
		 * 递归的方式处理嵌套的标签
		 * 
		 * @param klass  被转换的类型
		 * @param values 当前层级的数据内容
		 * @param levels 当前层级数组
		 */
		private void readValues(final Class<?> klass, TradeValues values, int... levels) {
			String suffix = levels.length == 0 ? EMPTY : "_" + Joiner.on("_").join(Ints.asList(levels));

			getFieldsListWithAnnotation(klass, ApiField.class).forEach(field -> {
				ApiField af = field.getAnnotation(ApiField.class);
				String key = af.name() + suffix;

				if (isAssignable(Void.class, af.subType())) {
					if (containsKey(key)) {
						values.put(af.name(), get(key));
					}
				} else {
					int count = getIntValue(this, af.countName() + suffix, 0);
					if (count > 0) {
						List<TradeValues> subObjectList = Lists.newArrayList();

						for (int i = 0; i < count; i++) {
							TradeValues subValues = new TradeValues();
							readValues(af.subType(), subValues, add(levels, i));
							subObjectList.add(subValues);
						}
						values.put(af.subName(), subObjectList);
					}
				}
			});
		}
	}

	private <S> S parseXmlResponse(final WechatTradeResponse<S> response, final InputStream input)
			throws WechatApiException, IOException {
		TradeValues tvs = xmlMapper.readValue(new InputStreamReader(input, StandardCharsets.UTF_8), TradeValues.class);
	
		TradeReturn returnModel = xmlMapper.convertValue(tvs, TradeReturn.class);
		if (!returnModel.isSuccess()) {
			throw new WechatApiException(returnModel.getCode(), returnModel.getMessage());
		}
	
		TradeResult resultModel = xmlMapper.convertValue(tvs, TradeResult.class);
		if (response.hasResult() && !resultModel.isSuccess()) {
			throw new WechatApiException(resultModel.getCode(), resultModel.getMessage());
		}
	
		if (response.hasSigned() && !tvs.hasValidSign(response.responseSignType())) {
			throw new WechatApiException(ErrorCodeEnum.SIGNERROR);
		}
	
		if (isNotEmpty(response.encrypted())) {
			tvs.decrypt(response.encrypted());
		}
	
		if (response.hasHierarchy()) {
			tvs.hierarchy(response.getResponseType());
		}
	
		return xmlMapper.convertValue(tvs, response.getResponseType());
	}

	@SuppressWarnings("unchecked")
	private <S> S parseCsvResponse(final WechatTradeResponse<S> response, final InputStream input) throws IOException {
		Predicate<String> isChineseWord = word -> Pattern.compile("[\u4e00-\u9fa5]").matcher(word).find();
		TradeSheetResponse<?, ?> result;
		try {
			result = (TradeSheetResponse<?, ?>) invokeConstructor(response.getResponseType(),
					EMPTY_OBJECT_ARRAY);
		} catch (ReflectiveOperationException roe) {
			return null;
		}
	
		try (LineIterator reader = new LineIterator(new InputStreamReader(input, StandardCharsets.UTF_8))) {
			AtomicBoolean isSummary = new AtomicBoolean(false);
	
			reader.next(); // Skip First Title
			reader.forEachRemaining(lineText -> {
				lineText = removeAll(lineText, "(`|\\r|\\n)");
				boolean isTitle = isChineseWord.test(lineText.substring(0, 1));
	
				if (!isSummary.get()) {
					isSummary.set(isTitle);
				}
				if (!isTitle) {
					try {
						if (isSummary.get()) {
							result.setSummary(csvMapper.readerFor(result.getSummaryType())
									.with(csvMapper.schemaFor(result.getSummaryType())).readValue(lineText));
						} else {
							result.getRecords().add(csvMapper.readerFor(result.getRecordType())
									.with(csvMapper.schemaFor(result.getRecordType())).readValue(lineText));
						}
					} catch (JsonProcessingException e) {
						// Ignore
					}
				}
			});
		}
	
		return (S) result;
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
		
		try (CloseableHttpClient httpClient = createHttpClient.get()) {
			TradeValues xml = xmlMapper.convertValue(model, TradeValues.class);
			
			HttpPost httpPost = new HttpPost(action.url());
			httpPost.setEntity(new StringEntity(xml.toXml(action.getRequestType(), action.requestSignType()), ContentType.APPLICATION_XML));

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
