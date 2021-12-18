package tech.bittercoffee.wechat.api.trade.models;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 交易对账单退款记录
 * 
 * @author BitterCoffee
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = { "trade_time", "app_id", "mch_id", "sub_mch_id", "device_info", "transaction_id", "trade_no",
		"open_id", "trade_type", "trade_status", "bank_type", "fee_type", "settlement_total_fee", "coupon_fee",
		"refund_time", "refund_success_time", "refund_id", "refund_no", "refunded_fee", "refund_coupon_fee",
		"refund_channel", "refund_status", "body", "attach", "service_fee", "rate", "total_fee", "refund_fee",
		"rate_desc" })
public class TradeBillRefundInfo extends TradeBillAllInfo {

	private static final long serialVersionUID = 729077094636175091L;

	/**
	 * 退款申请时间
	 */
	@JsonProperty("refund_time")
	@JsonDeserialize(using = StandardLocalDateTimeDeserializer.class)
	private LocalDateTime refundTime;

	/**
	 * 退款成功时间
	 */
	@JsonProperty("refund_success_time")
	@JsonDeserialize(using = StandardLocalDateTimeDeserializer.class)
	private LocalDateTime refundSuccessTime;

	public LocalDateTime getRefundTime() {
		return refundTime;
	}

	public LocalDateTime getRefundSuccessTime() {
		return refundSuccessTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
