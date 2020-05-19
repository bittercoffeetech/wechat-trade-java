package tech.bittercoffee.wechat.api.trade;

import static java.util.Objects.nonNull;
import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Hex.encodeHexString;
import static org.apache.commons.codec.digest.DigestUtils.md5;
import static org.apache.commons.codec.digest.DigestUtils.sha256;
import static org.apache.commons.collections.MapUtils.getIntValue;
import static org.apache.commons.collections.MapUtils.getString;
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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;

import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.ArrayUtils;
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
import com.google.common.collect.ImmutableMap;
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
import tech.bittercoffee.wechat.api.trade.models.TradeBillAllModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillAllResponseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillRefundModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillRefundResponseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillSuccessModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillSuccessResponseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeCloseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeCloseResponseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeCreateModel;
import tech.bittercoffee.wechat.api.trade.models.TradeCreateNotifyModel;
import tech.bittercoffee.wechat.api.trade.models.TradeCreateResponseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeCsvResponseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeFundflowModel;
import tech.bittercoffee.wechat.api.trade.models.TradeFundflowResponseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeQueryModel;
import tech.bittercoffee.wechat.api.trade.models.TradeQueryResponseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundModel;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundNotifyModel;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundQueryModel;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundQueryResponseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundResponseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeResultModel;
import tech.bittercoffee.wechat.api.trade.models.TradeReturnModel;
import tech.bittercoffee.wechat.api.trade.models.TradeSignatureModel;

/**
 * 微信支付客户端的默认实现
 * 
 * @author Bob
 *
 */
public final class WechatTradeClient {

	@SuppressWarnings("rawtypes")
	private static final Map<Class<? extends WechatTradeAction>, WechatTradeAction> ACTIONS = 
		ImmutableMap.<Class<? extends WechatTradeAction>, WechatTradeAction>builder()
			.put(WechatTradeCreateAction.class, new WechatTradeCreateAction())
			.put(WechatTradeQueryAction.class, new WechatTradeQueryAction())
			.put(WechatTradeCloseAction.class, new WechatTradeCloseAction())
			.put(WechatTradeRefundAction.class, new WechatTradeRefundAction())
			.put(WechatTradeRefundQueryAction.class, new WechatTradeRefundQueryAction())
			.put(WechatTradeBillAllAction.class, new WechatTradeBillAllAction())
			.put(WechatTradeBillSuccessAction.class, new WechatTradeBillSuccessAction())
			.put(WechatTradeBillRefundAction.class, new WechatTradeBillRefundAction())
			.put(WechatTradeFundflowAction.class, new WechatTradeFundflowAction())
			.build();
	@SuppressWarnings("rawtypes")
	private static final Map<Class<? extends WechatTradeResponse>, WechatTradeResponse> NOTIFIES = 
		ImmutableMap.<Class<? extends WechatTradeResponse>, WechatTradeResponse>builder()
			.put(WechatTradeCreateNotify.class, new WechatTradeCreateNotify())
			.put(WechatTradeRefundNotify.class, new WechatTradeRefundNotify())
			.build();
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

	public WechatTradeClient(String appId, String mchId, String mchKey, InputStream apiCert) {
		this.appId = appId;
		this.mchId = mchId;
		this.mchKey = mchKey;
		this.apiCert = apiCert;
	}
	
	public class Executor<R extends TradeSignatureModel, S> {
		WechatTradeAction<R, S> action;
		R model;

		@SuppressWarnings("unchecked")
		private <T extends WechatTradeAction<R, S>> Executor(final Class<T> r) {
			this.action = ACTIONS.get(r);
		}

		public Executor<R, S> withModel(final R model) {
			this.model = model;
			return this;
		}

		public S execute() throws WechatApiException {
			try(CloseableHttpClient httpClient = createHttpClient()) {
				HttpPost httpPost = new HttpPost(action.url());
				httpPost.setEntity(new StringEntity(toXmlRequest(), ContentType.APPLICATION_XML));

				try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
					if (response.getCode() == 200) {
						HttpEntity entity = response.getEntity();
						
						if(action.isStreaming()) {
							boolean isGzip = "application/x-gzip".equals(entity.getContentType());
						
							if(!isGzip && !entity.isChunked()) {
								return fromXmlResponse((WechatTradeResponse<S>) action, 	entity.getContent());
							} else if (!isGzip && entity.isChunked()) {
								return fromCsvResponse(action, entity.getContent());
							} else {
								return fromCsvResponse(action, new GZIPInputStream(entity.getContent()));
							} 
						} else {
							return fromXmlResponse((WechatTradeResponse<S>) action, 
									entity.getContent());
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

		private CloseableHttpClient createHttpClient() throws IOException, GeneralSecurityException {
			CloseableHttpClient httpClient;
			if (action.certificated()) {
				final KeyStore keyStore = KeyStore.getInstance("PKCS12");
				keyStore.load(apiCert, mchId.toCharArray());
				final SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, mchId.toCharArray()).build();
				final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
			            .setSslContext(sslContext)
			            .setTlsVersions(TLS.V_1_2)
			            .build();
				final HttpClientConnectionManager conectionManager = PoolingHttpClientConnectionManagerBuilder.create()
			            .setSSLSocketFactory(sslSocketFactory)
			            .build();
				
				httpClient = HttpClients.custom().setConnectionManager(conectionManager).build();
			} else {
				httpClient = HttpClients.createDefault();
			}
			return httpClient;
		}
		
		private String toXmlRequest() throws IOException {
			TradeValues tv = xmlMapper.convertValue(this.model, TradeValues.class);
			tv.put("appid", appId);
			tv.put("mch_id", mchId);
			tv.put("sign_type", action.responseSignType());
			tv.put("sign", tv.getSign.apply(action.responseSignType(), mchKey));
			R signed = xmlMapper.convertValue(tv, action.getRequestType());
			
			return xmlMapper.writer().withRootName("xml").writeValueAsString(signed);
		}

		@SuppressWarnings("unchecked")
		private S fromCsvResponse(final WechatTradeResponse<S> response, final InputStream input) 
				throws IOException  {
			
			Predicate<String> isChineseWord = word -> Pattern.compile("[\u4e00-\u9fa5]").matcher(word).find();
			TradeCsvResponseModel<?, ?> result;
			try {
				result = (TradeCsvResponseModel<?, ?>)ConstructorUtils.invokeConstructor(response.getResponseType(), ArrayUtils.EMPTY_OBJECT_ARRAY);
			} catch (ReflectiveOperationException roe) {
				return null;
			}
		
			try (LineIterator reader = new LineIterator(new InputStreamReader(input, StandardCharsets.UTF_8))) {
				AtomicBoolean isSummary = new AtomicBoolean(false);
				
				reader.next(); // Skip First Title
				reader.forEachRemaining(lineText -> {
					lineText = removeAll(lineText, "(`|\\r|\\n)");
					boolean isTitle = isChineseWord.test(lineText.substring(0, 1));
		
					if (!isSummary.get()) { isSummary.set(isTitle); }
					if (!isTitle) {
						try {
							if (isSummary.get()) {
								result.setSummary(csvMapper.readerFor(result.getSummaryType())
										.with(csvMapper.schemaFor(result.getSummaryType()))
										.readValue(lineText));
							} else {
								result.getRecords().add(csvMapper.readerFor(result.getRecordType())
										.with(csvMapper.schemaFor(result.getRecordType()))
										.readValue(lineText));
							}
						} catch (JsonProcessingException e) {
							// Ignore
						}
					}
				});
			}
			
			return (S) result;
		}
	}
	
	public class Notifier<S> {
		WechatTradeResponse<?> ntf;
		
		public <T extends WechatTradeResponse<S>> Notifier(Class<T> r) {
			this.ntf = NOTIFIES.get(r);
		}
		
		@SuppressWarnings("unchecked")
		public S parse(InputStream xml) throws WechatApiException, IOException {
			return (S) fromXmlResponse((WechatTradeResponse<?>) ntf, xml);
		}
	}
	
	public Notifier<TradeCreateNotifyModel> newCreateNotifier() {
		return newNotifier(WechatTradeCreateNotify.class);
	}
	
	public Notifier<TradeRefundNotifyModel> newRefundNotifier() {
		return newNotifier(WechatTradeRefundNotify.class);
	}

	public Executor<TradeCreateModel, TradeCreateResponseModel> newCreateAction() {
		return newRequest(WechatTradeCreateAction.class);
	}

	public Executor<TradeQueryModel, TradeQueryResponseModel> newQueryAction() {
		return newRequest(WechatTradeQueryAction.class);
	}

	public Executor<TradeCloseModel, TradeCloseResponseModel> newCloseAction() {
		return newRequest(WechatTradeCloseAction.class);
	}

	public Executor<TradeRefundModel, TradeRefundResponseModel> newRefundAction() {
		return newRequest(WechatTradeRefundAction.class);
	}

	public Executor<TradeRefundQueryModel, TradeRefundQueryResponseModel> newRefundQueryAction() {
		return newRequest(WechatTradeRefundQueryAction.class);
	}

	public Executor<TradeBillAllModel, TradeBillAllResponseModel> newBillAllAction() {
		return newRequest(WechatTradeBillAllAction.class);
	}

	public Executor<TradeBillSuccessModel, TradeBillSuccessResponseModel> newBillSuccessAction() {
		return newRequest(WechatTradeBillSuccessAction.class);
	}

	public Executor<TradeBillRefundModel, TradeBillRefundResponseModel> newBillRefundAction() {
		return newRequest(WechatTradeBillRefundAction.class);
	}

	public Executor<TradeFundflowModel, TradeFundflowResponseModel> newFundflowAction() {
		return newRequest(WechatTradeFundflowAction.class);
	}

	private <R extends TradeSignatureModel, S> Executor<R, S> newRequest(final Class<? extends WechatTradeAction<R, S>> req) {
		return new Executor<>(req);
	}

	private <S> Notifier<S> newNotifier(final Class<? extends WechatTradeResponse<S>> resp) {
		return new Notifier<>(resp);
	}

	@SuppressWarnings("unchecked")
	private <S> S fromXmlResponse(final WechatTradeResponse<S> response, final InputStream input) throws WechatApiException, IOException {
		TradeValues tvs = xmlMapper.readValue(new InputStreamReader(input, StandardCharsets.UTF_8), TradeValues.class);
		
		TradeReturnModel returnModel = xmlMapper.convertValue(tvs, TradeReturnModel.class);
		if (!returnModel.isSuccess()) {
			throw new WechatApiException(returnModel.getCode(), returnModel.getMessage());
		}
		
		TradeResultModel resultModel = xmlMapper.convertValue(tvs, TradeResultModel.class);
		if (response.hasResult() && !resultModel.isSuccess()) {
			throw new WechatApiException(resultModel.getCode(), resultModel.getMessage());
		}

		if (response.hasSigned() && !tvs.hasValidSign.test(response.responseSignType(), mchKey)) {
			throw new WechatApiException(ErrorCodeEnum.SIGNERROR);
		}

		if (isNotEmpty(response.encrypted())) {
			String dec = tvs.decrypt.apply(response.encrypted(), mchKey);
			tvs.putAll(xmlMapper.readValue(dec, Map.class));
			tvs.remove(response.encrypted());
		}
		
		if (response.hasHierarchy()) {
			TradeValues ntvs = tvs.hierarchy.apply(response.getResponseType());
			tvs.clear();
			tvs.putAll(ntvs);
		}

		return xmlMapper.convertValue(tvs, response.getResponseType());
	}

	private static final class TradeValues extends TreeMap<String, Object> {
		private static final long serialVersionUID = -2271075041300944374L;

		public TradeValues() {
			super();
		}
		
		BiFunction<SignTypeEnum, String, String> getSign = (signType, key) -> {
			String toSign = entrySet().stream()
					.filter(p -> !"sign".equals(p.getKey()))
					.filter(p -> nonNull(p.getValue()) && isNotEmpty(p.getValue().toString()))
					.map(p -> p.getKey() + "=" + p.getValue())
					.reduce((p1, p2) -> p1 + "&" + p2)
					.orElse(EMPTY) + "&key=" + key;
		
			if(SignTypeEnum.HMAC_SHA256.equals(signType)) {
				return encodeHexString(sha256(toSign.getBytes()), false);
			} else {
				return encodeHexString(md5(toSign.getBytes()), false);
			}
		};
		
		BiPredicate<SignTypeEnum, String> hasValidSign = (signType,  key) -> 
			getSign.apply(signType, key).equals(getString(this, "sign"));
				
		BinaryOperator<String> decrypt = (propName, key) -> {
			String enc = getString(this, propName);
			
			if (isNotEmpty(enc)) {
				try {
					Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
					cipher.init(Cipher.DECRYPT_MODE,
							new SecretKeySpec(encodeHexString(md5(key.getBytes()), true).getBytes(), "AES"));

					return new String(cipher.doFinal(decodeBase64(enc)));
				} catch (Exception e) {
					// do nothing
				}
			}
			
			return EMPTY;
		};			
		
		Function<Class<?>, TradeValues> hierarchy = klass -> {
			TradeValues ntv = new TradeValues();
			readValues(klass, ntv);
			return ntv;
		};
		
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

		@Override
		public boolean equals(Object o) {
			return super.equals(o);
		}

		@Override
		public int hashCode() {
			return super.hashCode();
		}

	}
	
}
