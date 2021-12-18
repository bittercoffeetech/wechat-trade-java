package tech.bittercoffee.wechat.api.trade.models.response;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * 产品详情对象
 * 
 * @author BitterCoffee
 *
 */
@JsonRootName("goods_detail")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class TradeGoodsDetailInfo implements Serializable {

	private static final long serialVersionUID = -8630289323986936098L;
	private static ObjectMapper mapper = new ObjectMapper();

	public static class GoodsDetailInfoSerializer extends StdSerializer<TradeGoodsDetailInfo> {
		private static final long serialVersionUID = -7127214276144608360L;

		protected GoodsDetailInfoSerializer() {
			super(TradeGoodsDetailInfo.class);
		}

		@Override
		public void serialize(TradeGoodsDetailInfo value, JsonGenerator gen, SerializerProvider provider)
				throws IOException {
			gen.writeString(mapper.writeValueAsString(value));
		}

	}

	public static class GoodsDetailInfoDeserializer extends StdDeserializer<TradeGoodsDetailInfo> {
		private static final long serialVersionUID = -1758706480964497937L;

		protected GoodsDetailInfoDeserializer() {
			super(TradeGoodsDetailInfo.class);
		}

		@Override
		public TradeGoodsDetailInfo deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
			return mapper.readValue(p.getValueAsString(), TradeGoodsDetailInfo.class);
		}

	}

	/**
	 * 单品优惠活动goods_detail字段
	 * 
	 * @author BitterCoffee
	 *
	 */
	public static class GoodInfo implements Serializable {

		private static final long serialVersionUID = -8630289323986936098L;

		/**
		 * 商品编码 由半角的大小写字母、数字、中划线、下划线中的一种或几种组成
		 */
		@JsonProperty("goods_id")
		private String goodsId;

		/**
		 * 微信侧商品编码 微信支付定义的统一商品编号（没有可不传）
		 */
		@JsonProperty("wxpay_goods_id")
		private String payGoodsId;

		/**
		 * 商品名称 商品的实际名称
		 */
		@JsonProperty("goods_name")
		private String goodsName;

		/**
		 * 商品数量 用户购买的数量
		 */
		@JsonProperty("quantity")
		private long quantity;

		/**
		 * 商品单价
		 * 单位为：分。如果商户有优惠，需传输商户优惠后的单价(例如：用户对一笔100元的订单使用了商场发的纸质优惠券100-50，则活动商品的单价应为原单价-50)
		 */
		@JsonProperty("price")
		private long price;

		@JsonCreator
		public GoodInfo(@JsonProperty("goods_id") String goodsId, @JsonProperty("wxpay_goods_id") String payGoodsId,
				@JsonProperty("goods_name") String goodsName, @JsonProperty("quantity") long quantity,
				@JsonProperty("price") long price) {
			super();
			this.goodsId = goodsId;
			this.payGoodsId = payGoodsId;
			this.goodsName = goodsName;
			this.quantity = quantity;
			this.price = price;
		}

		public String getGoodsId() {
			return goodsId;
		}

		public void setGoodsId(String goodsId) {
			this.goodsId = goodsId;
		}

		public String getPayGoodsId() {
			return payGoodsId;
		}

		public void setPayGoodsId(String payGoodsId) {
			this.payGoodsId = payGoodsId;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public long getQuantity() {
			return quantity;
		}

		public void setQuantity(long quantity) {
			this.quantity = quantity;
		}

		public long getPrice() {
			return price;
		}

		public void setPrice(long price) {
			this.price = price;
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

	/**
	 * 订单原价
	 * 
	 * <ul>
	 * <li>1.商户侧一张小票订单可能被分多次支付，订单原价用于记录整张小票的交易金额。</li>
	 * <li>2.当订单原价与支付金额不相等，则不享受优惠。</li>
	 * <li>3.该字段主要用于防止同一张小票分多次支付，以享受多次优惠的情况，正常支付订单不必上传此参数。</li>
	 * </ul>
	 */
	@JsonProperty("cost_price")
	private long costPrice;

	/**
	 * 商品小票ID
	 */
	@JsonProperty("receipt_id")
	private String receiptId;

	/**
	 * 单品列表 单品信息，使用Json数组格式提交
	 * 
	 * @see GoodInfo
	 */
	@JsonProperty("goods_detail")
	private List<GoodInfo> goods;

	@JsonCreator
	public TradeGoodsDetailInfo(@JsonProperty("cost_price") long costPrice,
			@JsonProperty("receipt_id") String receiptId, @JsonProperty("goods_detail") List<GoodInfo> goods) {
		super();
		this.costPrice = costPrice;
		this.receiptId = receiptId;
		this.goods = goods;
	}

	public long getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(long costPrice) {
		this.costPrice = costPrice;
	}

	public String getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	public List<GoodInfo> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodInfo> goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
