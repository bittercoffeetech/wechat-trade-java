package tech.bittercoffee.wechat.api.trade.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.function.FailableFunction;
import org.apache.commons.lang3.function.TriFunction;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import tech.bittercoffee.wechat.api.trade.WechatApiException;
import tech.bittercoffee.wechat.api.trade.WechatClientConfig;
import tech.bittercoffee.wechat.api.trade.enums.ErrorCodeEnum;
import tech.bittercoffee.wechat.api.trade.enums.SignTypeEnum;
import tech.bittercoffee.wechat.api.trade.models.response.HierarchyConfig;
import tech.bittercoffee.wechat.api.trade.models.response.TradeResult;
import tech.bittercoffee.wechat.api.trade.models.response.TradeReturn;
import tech.bittercoffee.wechat.api.trade.models.response.TradeSheetResponse;

/**
 * 微信接口封装
 * 
 * @author BitterCoffee
 *
 * @param <S> 返回对象类型
 */
public abstract class AbstractTradeResponsive<S> implements TradeResponse<S> {

	protected static final XmlMapper xmlMapper = new XmlMapper();
	protected static final CsvMapper csvMapper = new CsvMapper();
	static {
		xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
		xmlMapper.setSerializationInclusion(Include.NON_NULL);
		csvMapper.enable(CsvParser.Feature.TRIM_SPACES);
		csvMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
	}
	protected WechatClientConfig config;

	public AbstractTradeResponsive(WechatClientConfig config) {
		this.config = config;
	}

	protected BiFunction<TreeMap<String, Object>, SignTypeEnum, String> signOf = (values, signType) -> {
		String toSign = values.entrySet().stream().filter(p -> !"sign".equals(p.getKey()))
				.filter(p -> Objects.nonNull(p.getValue()) && StringUtils.isNotEmpty(p.getValue().toString()))
				.map(p -> p.getKey() + "=" + p.getValue()).reduce((p1, p2) -> p1 + "&" + p2).orElse(StringUtils.EMPTY)
				+ "&key=" + config.getMchKey();

		if (SignTypeEnum.HMAC_SHA256.equals(signType)) {
			return Hex.encodeHexString(
					new HmacUtils(HmacAlgorithms.HMAC_SHA_256, config.getMchKey().getBytes()).hmac(toSign.getBytes()),
					false);
		} else {
			return Hex.encodeHexString(DigestUtils.md5(toSign.getBytes()), false);
		}
	};

	@SuppressWarnings("unchecked")
	BiConsumer<Map<String, Object>, String> decrypt = (source, enc) -> {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(
					Hex.encodeHexString(DigestUtils.md5(config.getMchKey().getBytes()), true).getBytes(), "AES"));

			String decrypted = new String(cipher.doFinal(Base64.decodeBase64(enc)));
			source.putAll(xmlMapper.readValue(decrypted, Map.class));
		} catch (Exception e) {
		}
	};

	Consumer<Map<String, Object>> hierarchy = (source) -> {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		TriFunction<TriFunction, HierarchyConfig, String, Map<String, List<Map<String, Object>>>> fetch = (func, hcfg,
				suffix) -> {
			List<Map<String, Object>> objects = Lists.newArrayList();

			int count = MapUtils.getIntValue(source, hcfg.getCountPropertyName() + suffix);
			for (int i = 0; i < count; i++) {
				List<String> tags = Lists.transform(
						FieldUtils.getFieldsListWithAnnotation(hcfg.getPropertyType(), JsonProperty.class),
						field -> field.getAnnotation(JsonProperty.class).value());
				Map<String, Object> object = Maps.newHashMap();

				for (int j = 0; j < tags.size(); j++) {
					object.put(tags.get(j), source.get(tags.get(j) + suffix + "_" + i));
				}

				if (Objects.nonNull(hcfg.getChilds())) {
					for (int c = 0; c < hcfg.getChilds().size(); c++) {
						object.putAll((Map<String, List<Map<String, Object>>>) func.apply(func, hcfg.getChilds().get(c),
								suffix + "_" + i));
					}

				}
				objects.add(object);
			}
			Map<String, List<Map<String, Object>>> result = Maps.newHashMap();
			result.put(hcfg.getPropertyName(), objects);

			return result;
		};

		for (int h = 0; h < this.hierarchy().length; h++) {
			source.putAll(fetch.apply(fetch, this.hierarchy()[h], StringUtils.EMPTY));
		}

		source.entrySet().removeIf(entry -> entry.getKey().matches("^.*((_)[0-9]+)+"));
	};

	protected FailableFunction<InputStream, S, WechatApiException> forXml = (input) -> {
		try {
			@SuppressWarnings("unchecked")
			TreeMap<String, Object> responseValues = xmlMapper
					.readValue(new InputStreamReader(input, StandardCharsets.UTF_8), TreeMap.class);

			TradeReturn returnModel = xmlMapper.convertValue(responseValues, TradeReturn.class);
			if (!returnModel.isSuccess()) {
				throw new WechatApiException(returnModel.getCode(), returnModel.getMessage());
			}
			TradeResult resultModel = xmlMapper.convertValue(responseValues, TradeResult.class);
			if (hasResult() && !resultModel.isSuccess()) {
				throw new WechatApiException(resultModel.getCode(), resultModel.getMessage());
			}
			if (hasSigned() && signOf.apply(responseValues, responseSignType())
					.equals(MapUtils.getString(responseValues, "sign"))) {
				throw new WechatApiException(ErrorCodeEnum.SIGNERROR);
			}
			if (StringUtils.isNotEmpty(encrypted())) {
				decrypt.accept(responseValues, encrypted());
			}
			if (ArrayUtils.isNotEmpty(hierarchy())) {
				hierarchy.accept(responseValues);
			}

			return xmlMapper.convertValue(responseValues, getResponseType());
		} catch (IOException e) {
			throw new WechatApiException(ErrorCodeEnum.ERROR.value(), e);
		}
	};

	@SuppressWarnings("unchecked")
	protected FailableFunction<InputStream, S, WechatApiException> forCsv = (input) -> {
		Predicate<String> isChineseWord = word -> Pattern.compile("[\u4e00-\u9fa5]").matcher(word).find();
		TradeSheetResponse<?, ?> result;
		try {
			result = (TradeSheetResponse<?, ?>) ConstructorUtils.invokeConstructor(getResponseType(),
					ArrayUtils.EMPTY_OBJECT_ARRAY);
		} catch (ReflectiveOperationException roe) {
			return null;
		}

		try (LineIterator reader = new LineIterator(new InputStreamReader(input, StandardCharsets.UTF_8))) {
			AtomicBoolean isSummary = new AtomicBoolean(false);

			reader.next(); // Skip First Title
			reader.forEachRemaining(lineText -> {
				lineText = RegExUtils.removeAll(lineText, "(`|\\r|\\n)");
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
		} catch (IOException e) {
			throw new WechatApiException(ErrorCodeEnum.ERROR.value(), e);
		}

		return (S) result;
	};

}
