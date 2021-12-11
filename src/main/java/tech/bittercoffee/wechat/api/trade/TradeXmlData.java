package tech.bittercoffee.wechat.api.trade;

import static java.util.Objects.nonNull;
import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Hex.encodeHexString;
import static org.apache.commons.codec.digest.DigestUtils.md5;
import static org.apache.commons.collections.MapUtils.getIntValue;
import static org.apache.commons.collections.MapUtils.getString;
import static org.apache.commons.lang3.ArrayUtils.add;
import static org.apache.commons.lang3.ClassUtils.isAssignable;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.reflect.FieldUtils.getFieldsListWithAnnotation;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import tech.bittercoffee.wechat.api.trade.enums.SignTypeEnum;
import tech.bittercoffee.wechat.api.trade.models.ApiField;

public final class TradeXmlData extends TreeMap<String, Object> {
	private static final long serialVersionUID = -2271075041300944374L;
	private static final XmlMapper xmlMapper = new XmlMapper();
	static {
		xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
		xmlMapper.setSerializationInclusion(Include.NON_NULL);
	}
	
	private TradeXmlData() {
		super();
	}
	
	public static TradeXmlData fromXml(final InputStream input) {
		try {
			return xmlMapper.readValue(new InputStreamReader(input, StandardCharsets.UTF_8), TradeXmlData.class);
		} catch (IOException e) {
			return new TradeXmlData();
		}
	}
	
	public static TradeXmlData fromObject(final Object model) {
		return xmlMapper.convertValue(model, TradeXmlData.class);
	}
	
	public <T> T toObject(Class<T> klass) {
		return xmlMapper.convertValue(this, klass);
	}
	
	public String toXml(Class<?> klass, SignTypeEnum signType, String key) {
		this.put("sign", getSign(signType, key));
		try {
			return xmlMapper.writer().withRootName("xml").writeValueAsString(toObject(klass));
		} catch (JsonProcessingException e) {
			return "<xml/>";
		}
	}

	public boolean hasValidSign(SignTypeEnum signType, String key) {
		return getSign(signType, key).equals(getString(this, "sign"));
	}
	
	@SuppressWarnings("unchecked")
	public void decrypt(String propName, String key) {
		String enc = getString(this, propName);

		if (isNotEmpty(enc)) {
			try {
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.DECRYPT_MODE,
						new SecretKeySpec(encodeHexString(md5(key.getBytes()), true).getBytes(), "AES"));

				String decrypted = new String(cipher.doFinal(decodeBase64(enc)));
				putAll(xmlMapper.readValue(decrypted, Map.class));
				remove(propName);
			} catch (Exception e) {
				// do nothing
			}
		}
	}
		

	public void hierarchy(Class<?> klass) {
		TradeXmlData ntv = new TradeXmlData();
		readValues(klass, ntv);
		clear();
		putAll(ntv);
	};

	private String getSign(SignTypeEnum signType, String key) {
		String toSign = entrySet().stream().filter(p -> !"sign".equals(p.getKey()))
				.filter(p -> nonNull(p.getValue()) && isNotEmpty(p.getValue().toString()))
				.map(p -> p.getKey() + "=" + p.getValue()).reduce((p1, p2) -> p1 + "&" + p2).orElse(EMPTY) + "&key="
				+ key;
	
		if (SignTypeEnum.HMAC_SHA256.equals(signType)) {
			return encodeHexString(
					new HmacUtils(HmacAlgorithms.HMAC_SHA_256, key.getBytes()).hmac(toSign.getBytes()), false);
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
	private void readValues(final Class<?> klass, TradeXmlData values, int... levels) {
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
					List<TradeXmlData> subObjectList = Lists.newArrayList();

					for (int i = 0; i < count; i++) {
						TradeXmlData subValues = new TradeXmlData();
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