package tech.bittercoffee.wechat.api.trade.models;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 交易对账单汇总信息
 * 
 * @author BitterCoffee
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = { "total_trades", "settlement_total_fee", "total_refunded_fee", "total_coupon_fee",
		"total_service_fee", "total_fee", "total_refund_fee" })
public class TradeBillSummaryInfo implements Serializable {

	private static final long serialVersionUID = 7534222140623095477L;

	/**
	 * 总交易单数
	 */
	@JsonProperty("total_trades")
	private long totalTrades;

	/**
	 * 应结订单总金额
	 */
	@JsonProperty("settlement_total_fee")
	private double settlementTotalFee;

	/**
	 * 退款总金额
	 */
	@JsonProperty("total_refunded_fee")
	private double totalRefundedFee;

	/**
	 * 充值券退款总金额
	 */
	@JsonProperty("total_coupon_fee")
	private double totalCouponFee;

	/**
	 * 手续费总金额
	 */
	@JsonProperty("total_service_fee")
	private double totalServiceFee;

	/**
	 * 订单总金额
	 */
	@JsonProperty("total_fee")
	private double totalFee;

	/**
	 * 申请退款总金额
	 */
	@JsonProperty("total_refund_fee")
	private double totalRefundFee;

	public long getTotalTrades() {
		return totalTrades;
	}

	public double getSettlementTotalFee() {
		return settlementTotalFee;
	}

	public double getTotalRefundedFee() {
		return totalRefundedFee;
	}

	public double getTotalCouponFee() {
		return totalCouponFee;
	}

	public double getTotalServiceFee() {
		return totalServiceFee;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public double getTotalRefundFee() {
		return totalRefundFee;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
