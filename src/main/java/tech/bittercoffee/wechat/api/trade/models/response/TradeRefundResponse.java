package tech.bittercoffee.wechat.api.trade.models.response;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import tech.bittercoffee.wechat.api.trade.enums.FeeTypeEnum;

/**
 * 退款请求返回
 * 
 * @author BitterCoffee
 *
 */
@JsonRootName("trade_refund_response")
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public final class TradeRefundResponse implements Serializable {

	private static final long serialVersionUID = -8310580381720323679L;

	/**
	 * 商户订单号 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
	 */
	@JsonProperty("out_trade_no")
	private String tradeNo;

	/**
	 * 微信订单号 微信的订单号，优先使用
	 */
	@JsonProperty("transaction_id")
	private String transactionId;

	/**
	 * 标价金额 订单总金额，单位为分
	 */
	@JsonProperty("total_fee")
	private long totalFee;

	/**
	 * 应结订单金额 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
	 */
	@JsonProperty("settlement_total_fee")
	private long settlementTotalFee;

	/**
	 * 符合ISO 4217标准的三位字母代码，默认人民币：CNY
	 */
	@JsonProperty("fee_type")
	private FeeTypeEnum feeType;

	/**
	 * 现金支付金额 现金支付金额订单现金支付金额
	 */
	@JsonProperty("cash_fee")
	private Long cashFee;

	/**
	 * 现金支付币种 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
	 */
	@JsonProperty("cash_fee_type")
	private FeeTypeEnum cashFeeType;

	/**
	 * 商户退款单号 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
	 */
	@JsonProperty("out_refund_no")
	private String refundNo;

	/**
	 * 微信退款单号 微信生成的退款单号，在申请退款接口有返回
	 */
	@JsonProperty("refund_id")
	private String refundId;

	/**
	 * 退款金额 退款总金额，订单总金额，单位为分，只能为整数
	 */
	@JsonProperty("refund_fee")
	private long refundFee;

	/**
	 * 现金退款金额 现金退款金额，单位为分，只能为整数
	 */
	@JsonProperty("cash_refund_fee")
	private Long cashRefundFee;

	/**
	 * 应结退款金额 去掉非充值代金券退款金额后的退款金额，退款金额=申请退款金额-非充值代金券退款金额，退款金额&lt;=申请退款金额
	 */
	@JsonProperty("settlement_refund_fee")
	private long settlementRefundFee;

	/**
	 * 代金券使用数量
	 */
	@JsonProperty("coupon_refund_count")
	private int couponCount;

	/**
	 * 代金券金额 “代金券”金额&lt;=订单金额，订单金额-“代金券”金额=现金支付金额
	 */
	@JsonProperty("coupon_refund_fee")
	private long couponFee;

	/**
	 * 代金券
	 * 
	 * @see TradeRefundCouponInfo
	 */
	@JsonProperty("coupon")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<TradeRefundCouponInfo> coupons;

	public String getTradeNo() {
		return tradeNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public long getSettlementTotalFee() {
		return settlementTotalFee;
	}

	public FeeTypeEnum getFeeType() {
		return feeType;
	}

	public Long getCashFee() {
		return cashFee;
	}

	public FeeTypeEnum getCashFeeType() {
		return cashFeeType;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public String getRefundId() {
		return refundId;
	}

	public long getRefundFee() {
		return refundFee;
	}

	public Long getCashRefundFee() {
		return cashRefundFee;
	}

	public long getSettlementRefundFee() {
		return settlementRefundFee;
	}

	public int getCouponCount() {
		return couponCount;
	}

	public long getCouponFee() {
		return couponFee;
	}

	public List<TradeRefundCouponInfo> getCoupons() {
		return coupons;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
