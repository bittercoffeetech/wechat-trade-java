package tech.bittercoffee.wechat.api.trade.models.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import tech.bittercoffee.wechat.api.trade.enums.FeeTypeEnum;
import tech.bittercoffee.wechat.api.trade.enums.TradeStatusEnum;
import tech.bittercoffee.wechat.api.trade.enums.TradeTypeEnum;

/**
 * 订单查询返回
 * 
 * @author BitterCoffee
 *
 */
@JsonRootName("trade_query_response")
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public final class TradeQueryResponse implements Serializable {

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
	 * 用户标识 tradeType=TradeTypeEnum.JSAPI，此参数必传，用户在商户appid下的唯一标识。
	 */
	@JsonProperty("openid")
	private String openId;

	/**
	 * 交易类型
	 * 
	 * @see TradeTypeEnum
	 */
	@JsonProperty("trade_type")
	private TradeTypeEnum tradeType;

	/**
	 * 附加数据 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
	 */
	@JsonProperty("attach")
	private String attach;

	/**
	 * 付款银行 银行类型，采用字符串类型的银行标识
	 */
	@JsonProperty("bank_type")
	private String bankType;

	/**
	 * 是否关注公众账号 用户是否关注公众账号，Y-关注，N-未关注
	 */
	@JsonProperty("is_subscribe")
	@JsonDeserialize(using = BooleanDeserializer.class)
	private boolean isSubscribe;

	/**
	 * 支付完成时间 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。
	 */
	@JsonProperty("time_end")
	@JsonDeserialize(using = CompactLocalDateTimeDeserializer.class)
	private LocalDateTime timeEnd;

	/**
	 * 代金券使用数量
	 */
	@JsonProperty("coupon_count")
	private int couponCount;

	/**
	 * 代金券金额 “代金券”金额&lt;=订单金额，订单金额-“代金券”金额=现金支付金额
	 */
	@JsonProperty("coupon_fee")
	private long couponFee;

	/**
	 * 代金券
	 * 
	 * @see TradeCouponInfo
	 */
	@JsonProperty("coupon")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<TradeCouponInfo> coupons;

	/**
	 * 设备号 自定义参数，可以为请求支付的终端设备号等
	 */
	@JsonProperty("device_info")
	private String deviceInfo;

	/**
	 * 交易状态
	 * 
	 * @see TradeStatusEnum
	 */
	@JsonProperty("trade_state")
	private TradeStatusEnum tradeState;

	/**
	 * 交易状态描述 对当前查询订单状态的描述和下一步操作的指引
	 */
	@JsonProperty("trade_state_desc")
	private String tradeStateDesc;

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

	public String getOpenId() {
		return openId;
	}

	public TradeTypeEnum getTradeType() {
		return tradeType;
	}

	public String getAttach() {
		return attach;
	}

	public String getBankType() {
		return bankType;
	}

	public boolean isSubscribe() {
		return isSubscribe;
	}

	public LocalDateTime getTimeEnd() {
		return timeEnd;
	}

	public int getCouponCount() {
		return couponCount;
	}

	public long getCouponFee() {
		return couponFee;
	}

	public List<TradeCouponInfo> getCoupons() {
		return coupons;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public TradeStatusEnum getTradeState() {
		return tradeState;
	}

	public String getTradeStateDesc() {
		return tradeStateDesc;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
