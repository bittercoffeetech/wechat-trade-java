package tech.bittercoffee.wechat.api.trade.models.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import tech.bittercoffee.wechat.api.trade.enums.FeeTypeEnum;
import tech.bittercoffee.wechat.api.trade.enums.TradeStatusEnum;
import tech.bittercoffee.wechat.api.trade.enums.TradeTypeEnum;

/**
 * 交易对账单成功的记录
 * 
 * @author BitterCoffee
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = { "trade_time", "app_id", "mch_id", "sub_mch_id", "device_info", "transaction_id",
		"trade_no", "open_id", "trade_type", "trade_status", "bank_type", "fee_type", "settlement_total_fee",
		"coupon_fee", "body", "attach", "service_fee", "rate", "total_fee", "rate_desc" })
public class TradeBillSuccessInfo implements Serializable {
	private static final long serialVersionUID = 729077094636175091L;

	/**
	 * 交易时间
	 */
	@JsonProperty("trade_time")
	@JsonDeserialize(using = StandardLocalDateTimeDeserializer.class)
	private LocalDateTime tradeTime;

	/**
	 * 公众账号ID
	 */
	@JsonProperty("app_id")
	private String appId;

	/**
	 * 商户号
	 */
	@JsonProperty("mch_id")
	private String mchId;

	/**
	 * 特约商户号
	 */
	@JsonProperty("sub_mch_id")
	private String subMchId;

	/**
	 * 设备号
	 */
	@JsonProperty("device_info")
	private String deviceInfo;

	/**
	 * 微信订单号
	 */
	@JsonProperty("transaction_id")
	private String transactionId;

	/**
	 * 商户订单号
	 */
	@JsonProperty("trade_no")
	private String tradeNo;

	/**
	 * 用户标识
	 */
	@JsonProperty("open_id")
	private String openId;

	/**
	 * 交易类型
	 */
	@JsonProperty("trade_type")
	private TradeTypeEnum tradeType;

	/**
	 * 交易状态
	 */
	@JsonProperty("trade_status")
	private TradeStatusEnum tradeStatus;

	/**
	 * 付款银行
	 */
	@JsonProperty("bank_type")
	private String bankType;

	/**
	 * 货币种类
	 */
	@JsonProperty("fee_type")
	private FeeTypeEnum feeType;

	/**
	 * 应结订单金额
	 */
	@JsonProperty("settlement_total_fee")
	private double settlementTotalFee;

	/**
	 * 代金券金额
	 */
	@JsonProperty("coupon_fee")
	private double couponFee;

	/**
	 * 商品名称
	 */
	@JsonProperty("body")
	private String body;

	/**
	 * 商户数据包
	 */
	@JsonProperty("attach")
	private String attach;

	/**
	 * 手续费
	 */
	@JsonProperty("service_fee")
	private double serviceFee;

	/**
	 * 费率
	 */
	@JsonProperty("rate")
	@JsonDeserialize(using = RateDeserializer.class)
	private double rate;

	/**
	 * 订单金额
	 */
	@JsonProperty("total_fee")
	private double totalFee;

	/**
	 * 费率备注
	 */
	@JsonProperty("rate_desc")
	private String rateDesc;

	public LocalDateTime getTradeTime() {
		return tradeTime;
	}

	public String getAppId() {
		return appId;
	}

	public String getMchId() {
		return mchId;
	}

	public String getSubMchId() {
		return subMchId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public String getOpenId() {
		return openId;
	}

	public TradeTypeEnum getTradeType() {
		return tradeType;
	}

	public TradeStatusEnum getTradeStatus() {
		return tradeStatus;
	}

	public String getBankType() {
		return bankType;
	}

	public FeeTypeEnum getFeeType() {
		return feeType;
	}

	public double getSettlementTotalFee() {
		return settlementTotalFee;
	}

	public double getCouponFee() {
		return couponFee;
	}

	public String getBody() {
		return body;
	}

	public String getAttach() {
		return attach;
	}

	public double getServiceFee() {
		return serviceFee;
	}

	public double getRate() {
		return rate;
	}

	public double getTotalFee() {
		return totalFee;
	}

	public String getRateDesc() {
		return rateDesc;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
