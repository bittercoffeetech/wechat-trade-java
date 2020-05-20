package tech.bittercoffee.wechat.api.trade.models;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import tech.bittercoffee.wechat.api.trade.enums.RefundAccountEnum;
import tech.bittercoffee.wechat.api.trade.enums.RefundRequestSourceEnum;
import tech.bittercoffee.wechat.api.trade.enums.RefundStatusEnum;

/**
 * 退款结果通知
 * 
 * @author BitterCoffee
 *
 */
@JsonRootName("trade_refund_notify")
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public final class TradeRefundNotifyModel extends TradeAppModel {

	private static final long serialVersionUID = -8310580381720323679L;

	/**
	 * 商户订单号 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
	 */
	@ApiField(name = "out_trade_no")
	@JsonProperty("out_trade_no")
	private String tradeNo;

	/**
	 * 微信订单号 微信的订单号，优先使用
	 */
	@ApiField(name = "transaction_id")
	@JsonProperty("transaction_id")
	private String transactionId;

	/**
	 * 标价金额 订单总金额，单位为分
	 */
	@ApiField(name = "total_fee")
	@JsonProperty("total_fee")
	private long totalFee;

	/**
	 * 应结订单金额 当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
	 */
	@ApiField(name = "settlement_total_fee")
	@JsonProperty("settlement_total_fee")
	private long settlementTotalFee;

	/**
	 * 商户退款单号 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
	 */
	@ApiField(name = "out_refund_no")
	@JsonProperty("out_refund_no")
	private String refundNo;

	/**
	 * 微信退款单号 微信生成的退款单号，在申请退款接口有返回
	 */
	@ApiField(name = "refund_id")
	@JsonProperty("refund_id")
	private String refundId;

	/**
	 * 退款金额 退款总金额，订单总金额，单位为分，只能为整数
	 */
	@ApiField(name = "refund_fee")
	@JsonProperty("refund_fee")
	private long refundFee;

	/**
	 * 应结退款金额 去掉非充值代金券退款金额后的退款金额，退款金额=申请退款金额-非充值代金券退款金额，退款金额&lt;=申请退款金额
	 */
	@ApiField(name = "settlement_refund_fee")
	@JsonProperty("settlement_refund_fee")
	private long settlementRefundFee;

	/**
	 * 退款状态
	 */
	@ApiField(name = "refund_status")
	@JsonProperty("refund_status")
	private RefundStatusEnum refundStatus;

	/**
	 * 退款资金来源 仅针对老资金流商户使用
	 * 
	 * @see RefundAccountEnum
	 */
	@ApiField(name = "refund_account")
	@JsonProperty("refund_account")
	private RefundAccountEnum refundAccount;

	/**
	 * 退款入账账户
	 */
	@ApiField(name = "refund_recv_accout")
	@JsonProperty("refund_recv_accout")
	private String refundRecvAccout;

	/**
	 * 退款发起来源
	 */
	@ApiField(name = "refund_request_source")
	@JsonProperty("refund_request_source")
	private RefundRequestSourceEnum refundRequestSource;

	/**
	 * 退款成功时间 资金退款至用户帐号的时间，格式2017-12-15 09:46:01
	 */
	@ApiField(name = "success_time")
	@JsonProperty("success_time")
	@JsonDeserialize(using = StandardLocalDateTimeDeserializer.class)
	private LocalDateTime successTime;

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

	public String getRefundNo() {
		return refundNo;
	}

	public String getRefundId() {
		return refundId;
	}

	public long getRefundFee() {
		return refundFee;
	}

	public long getSettlementRefundFee() {
		return settlementRefundFee;
	}

	public RefundStatusEnum getRefundStatus() {
		return refundStatus;
	}

	public RefundAccountEnum getRefundAccount() {
		return refundAccount;
	}

	public String getRefundRecvAccout() {
		return refundRecvAccout;
	}

	public RefundRequestSourceEnum getRefundRequestSource() {
		return refundRequestSource;
	}

	public LocalDateTime getSuccessTime() {
		return successTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
