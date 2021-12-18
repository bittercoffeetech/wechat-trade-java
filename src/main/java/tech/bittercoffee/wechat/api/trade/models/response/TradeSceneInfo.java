package tech.bittercoffee.wechat.api.trade.models;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * 统一下单场景信息
 * 
 * @author BitterCoffee
 *
 */
@JsonRootName("store_info")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class TradeSceneInfo implements Serializable {

	private static final long serialVersionUID = -8630289323986936098L;
	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
	}

	public static class SceneInfoSerializer extends StdSerializer<TradeSceneInfo> {
		private static final long serialVersionUID = -7127214276144608360L;

		protected SceneInfoSerializer() {
			super(TradeSceneInfo.class);
		}

		@Override
		public void serialize(TradeSceneInfo value, JsonGenerator gen, SerializerProvider provider) throws IOException {
			gen.writeString(mapper.writeValueAsString(value));
		}

	}

	public static class SceneInfoDeserializer extends StdDeserializer<TradeSceneInfo> {
		private static final long serialVersionUID = -1758706480964497937L;

		protected SceneInfoDeserializer() {
			super(TradeSceneInfo.class);
		}

		@Override
		public TradeSceneInfo deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
			return mapper.readValue(p.getValueAsString(), TradeSceneInfo.class);
		}

	}

	/**
	 * 门店id 门店编号，由商户自定义
	 */
	private String id;

	/**
	 * 门店名称 门店名称 ，由商户自定义
	 */
	private String name;

	/**
	 * 门店行政区划码 门店所在地行政区划码
	 */
	@JsonProperty("area_code")
	private String areaCode;

	/**
	 * 门店详细地址 门店详细地址 ，由商户自定义
	 */
	private String address;

	@JsonCreator
	public TradeSceneInfo(@JsonProperty("id") String id, @JsonProperty("name") String name,
			@JsonProperty("area_code") String areaCode, @JsonProperty("address") String address) {
		super();
		this.id = id;
		this.name = name;
		this.areaCode = areaCode;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
