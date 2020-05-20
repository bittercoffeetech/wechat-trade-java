package tech.bittercoffee.wechat.api.trade.models;

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
 * 退款查询返回
 * 
 * @author BitterCoffee
 *
 */
@JsonRootName("trade_refund_query_response")
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public final class TradeRefundQueryResponseModel extends TradeAppModel {

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
	 * 符合ISO 4217标准的三位字母代码，默认人民币：CNY
	 */
	@ApiField(name = "fee_type")
	@JsonProperty("fee_type")
	private FeeTypeEnum feeType;

	/**
	 * 现金支付金额 现金支付金额订单现金支付金额
	 */
	@ApiField(name = "cash_fee")
	@JsonProperty("cash_fee")
	private Long cashFee;

	/**
	 * 现金支付币种 货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
	 */
	@ApiField(name = "cash_fee_type")
	@JsonProperty("cash_fee_type")
	private FeeTypeEnum cashFeeType;

	/**
	 * 退款笔数
	 */
	@ApiField(name = "refund_count")
	@JsonProperty("refund_count")
	private long refundCount;

	/**
	 * 退款笔数
	 */
	@ApiField(name = "refund_fee")
	@JsonProperty("refund_fee")
	private long refundFee;

	/**
	 * 退款详情
	 */
	@ApiField(name = "refund", subType = TradeRefundInfo.class, subName = "refund", countName = "refund_count")
	@JsonProperty("refund")
	@JacksonXmlElementWrapper(localName = "refund", useWrapping = false)
	private List<TradeRefundInfo> refunds;

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

	public long getRefundCount() {
		return refundCount;
	}

	public List<TradeRefundInfo> getRefunds() {
		return refunds;
	}

	public long getRefundFee() {
		return refundFee;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}