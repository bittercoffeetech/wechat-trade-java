package tech.bittercoffee.wechat.api.trade.models.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import tech.bittercoffee.wechat.api.trade.enums.RefundChannelEnum;
import tech.bittercoffee.wechat.api.trade.enums.RefundStatusEnum;

/**
 * 交易对账单所有记录
 * 
 * @author BitterCoffee
 *
 */
@JsonRootName("bill")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = { "trade_time", "app_id", "mch_id", "sub_mch_id", "device_info", "transaction_id",
		"trade_no", "open_id", "trade_type", "trade_status", "bank_type", "fee_type", "settlement_total_fee",
		"coupon_fee", "refund_id", "refund_no", "refunded_fee", "refund_coupon_fee", "refund_channel", "refund_status",
		"body", "attach", "service_fee", "rate", "total_fee", "refund_fee", "rate_desc" })
public class TradeBillAllInfo extends TradeBillSuccessInfo {

	private static final long serialVersionUID = 729077094636175091L;

	/**
	 * 微信退款单号
	 */
	@JsonProperty("refund_id")
	private String refundId;

	/**
	 * 商户退款单号
	 */
	@JsonProperty("refund_no")
	private String refundNo;

	/**
	 * 退款金额
	 */
	@JsonProperty("refunded_fee")
	private double refundedFee;

	/**
	 * 充值券退款金额
	 */
	@JsonProperty("refund_coupon_fee")
	private double refundCouponFee;

	/**
	 * 退款类型
	 */
	@JsonProperty("refund_channel")
	private RefundChannelEnum refundChannel;

	/**
	 * 退款状态
	 */
	@JsonProperty("refund_status")
	private RefundStatusEnum refundStatus;

	/**
	 * 申请退款金额
	 */
	@JsonProperty("refund_fee")
	private double refundFee;

	public String getRefundId() {
		return refundId;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public double getRefundedFee() {
		return refundedFee;
	}

	public double getRefundCouponFee() {
		return refundCouponFee;
	}

	public RefundChannelEnum getRefundChannel() {
		return refundChannel;
	}

	public RefundStatusEnum getRefundStatus() {
		return refundStatus;
	}

	public double getRefundFee() {
		return refundFee;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
