package tech.bittercoffee.wechat.api.trade.models;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import tech.bittercoffee.wechat.api.trade.enums.CouponTypeEnum;

/**
 * 代金券
 * 
 * @author Bob
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class TradeCouponInfo implements Serializable {

	private static final long serialVersionUID = -8351852302306303653L;

	/**
	 * 代金券ID
	 */
	@ApiField(name = "coupon_id")
	@JsonProperty("coupon_id")
	private String id;

	/**
	 * 单个代金券支付金额
	 */
	@ApiField(name = "coupon_fee")
	@JsonProperty("coupon_fee")
	private long fee;

	/**
	 * 代金券类型 开通免充值券功能，并且订单使用了优惠券后有返回（取值：CASH、NO_CASH）。
	 * 
	 * @see CouponTypeEnum
	 */
	@ApiField(name = "coupon_type")
	@JsonProperty("coupon_type")
	private CouponTypeEnum type;

	public TradeCouponInfo(String id, CouponTypeEnum type, long fee) {
		super();
		this.id = id;
		this.type = type;
		this.fee = fee;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
