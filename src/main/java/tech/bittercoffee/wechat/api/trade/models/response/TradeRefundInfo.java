package tech.bittercoffee.wechat.api.trade.models.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import tech.bittercoffee.wechat.api.trade.enums.RefundAccountEnum;
import tech.bittercoffee.wechat.api.trade.enums.RefundChannelEnum;
import tech.bittercoffee.wechat.api.trade.enums.RefundStatusEnum;
import tech.bittercoffee.wechat.api.trade.models.StandardLocalDateTimeDeserializer;

/**
 * 退款详细信息
 * 
 * @see TradeRefundQueryResponse
 * @author BitterCoffee
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class TradeRefundInfo implements Serializable {

	private static final long serialVersionUID = -8310580381720323679L;

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
	 * 应结退款金额 去掉非充值代金券退款金额后的退款金额，退款金额=申请退款金额-非充值代金券退款金额，退款金额&lt;=申请退款金额
	 */
	@JsonProperty("settlement_refund_fee")
	private long settlementRefundFee;

	/**
	 * 退款状态
	 */
	@JsonProperty("refund_status")
	private RefundStatusEnum refundStatus;

	/**
	 * 退款资金来源 仅针对老资金流商户使用
	 * 
	 * @see RefundAccountEnum
	 */
	@JsonProperty("refund_account")
	private RefundAccountEnum refundAccount;

	/**
	 * 退款入账账户
	 */
	@JsonProperty("refund_recv_accout")
	private String refundRecvAccout;

	/**
	 * 退款渠道
	 */
	@JsonProperty("refund_channel")
	private RefundChannelEnum refundChannel;

	/**
	 * 退款成功时间 资金退款至用户帐号的时间，格式2017-12-15 09:46:01
	 */
	@JsonProperty("refund_success_time")
	@JsonDeserialize(using = StandardLocalDateTimeDeserializer.class)
	private LocalDateTime refundSuccessTime;

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

	public RefundChannelEnum getRefundChannel() {
		return refundChannel;
	}

	public LocalDateTime getRefundSuccessTime() {
		return refundSuccessTime;
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
