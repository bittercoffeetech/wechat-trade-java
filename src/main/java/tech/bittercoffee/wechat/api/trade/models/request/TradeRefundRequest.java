package tech.bittercoffee.wechat.api.trade.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import tech.bittercoffee.wechat.api.trade.enums.FeeTypeEnum;
import tech.bittercoffee.wechat.api.trade.enums.RefundAccountEnum;

/**
 * 提交退款请求
 * 
 * @author BitterCoffee
 *
 */
@JsonRootName("trade_refund")
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public final class TradeRefundRequest extends TradeSignatureInfo {

	private static final long serialVersionUID = -888492915591862535L;
	
	public static TradeRefundRequest withTradeNo(String tradeNo, long totalFee, long refundFee) {
		TradeRefundRequest model = new TradeRefundRequest();
		model.tradeNo = tradeNo;
		model.totalFee = totalFee;
		model.refundFee = refundFee;
		
		return model;
	}
	
	public static TradeRefundRequest withTransactionId(String transactionId, long totalFee, long refundFee) {
		TradeRefundRequest model = new TradeRefundRequest();
		model.transactionId = transactionId;
		model.totalFee = totalFee;
		model.refundFee = refundFee;
		
		return model;
	}

	public TradeRefundRequest() {
		this.refundNo = randomStringGenerator.generate(32);
	}

	/**
	 * 商户订单号 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
	 */
	@JsonProperty("out_trade_no")
	@JacksonXmlCData
	private String tradeNo;

	/**
	 * 微信订单号 微信的订单号，优先使用
	 */
	@JsonProperty("transaction_id")
	@JacksonXmlCData
	private String transactionId;

	/**
	 * 标价金额 订单总金额，单位为分
	 */
	@JsonProperty("total_fee")
	@JacksonXmlCData
	private long totalFee;

	/**
	 * 商户退款单号 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
	 */
	@JsonProperty("out_refund_no")
	@JacksonXmlCData
	private String refundNo;

	/**
	 * 退款金额 退款总金额，订单总金额，单位为分，只能为整数
	 */
	@JsonProperty("refund_fee")
	@JacksonXmlCData
	private long refundFee;

	/**
	 * 符合ISO 4217标准的三位字母代码，默认人民币：CNY
	 */
	@JsonProperty("refund_fee_type")
	@JacksonXmlCData
	private FeeTypeEnum refundFeeType = FeeTypeEnum.CNY;

	/**
	 * 退款资金来源 仅针对老资金流商户使用
	 * 
	 * @see RefundAccountEnum
	 */
	@JsonProperty("refund_account")
	@JacksonXmlCData
	private RefundAccountEnum refundAccount;

	/**
	 * 退款原因 若商户传入，会在下发给用户的退款消息中体现退款原因 注意：若订单退款金额≤1元，且属于部分退款，则不会在退款消息中体现退款原因
	 */
	@JsonProperty("refund_desc")
	@JacksonXmlCData
	private String refundDesc;

	/**
	 * 通知地址 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
	 */
	@JsonProperty("notify_url")
	@JacksonXmlCData
	private String notifyUrl;

	public String getTradeNo() {
		return tradeNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public long getRefundFee() {
		return refundFee;
	}

	public FeeTypeEnum getRefundFeeType() {
		return refundFeeType;
	}

	public RefundAccountEnum getRefundAccount() {
		return refundAccount;
	}

	public String getRefundDesc() {
		return refundDesc;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public TradeRefundRequest feeType(FeeTypeEnum feeType) {
		this.refundFeeType = feeType;
		return this;
	}

	public TradeRefundRequest account(RefundAccountEnum account) {
		this.refundAccount = account;
		return this;
	}

	public TradeRefundRequest desc(String desc) {
		this.refundDesc = desc;
		return this;
	}

	public TradeRefundRequest notifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
