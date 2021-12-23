package tech.bittercoffee.wechat.api.trade.models.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import tech.bittercoffee.wechat.api.trade.models.StandardLocalDateTimeDeserializer;

/**
 * 资金账单记录
 * 
 * @author BitterCoffee
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = { "account_time", "transaction_id", "trade_no", "biz_name", "biz_type", "trade_type", "fee",
		"balance", "proposer", "note", "voucher_no" })
public class TradeFundflowInfo implements Serializable {

	private static final long serialVersionUID = 729077094636175091L;

	/**
	 * 记账时间
	 */
	@JsonProperty("account_time")
	@JsonDeserialize(using = StandardLocalDateTimeDeserializer.class)
	private LocalDateTime accountTime;

	/**
	 * 微信支付业务单号
	 */
	@JsonProperty("transaction_id")
	private String transactionId;

	/**
	 * 资金流水单号
	 */
	@JsonProperty("trade_no")
	private String tradeNo;

	/**
	 * 业务名称
	 */
	@JsonProperty("biz_name")
	private String bizName;

	/**
	 * 业务类型
	 */
	@JsonProperty("biz_type")
	private String bizType;

	/**
	 * 收支类型
	 */
	@JsonProperty("trade_type")
	private String tradeType;

	/**
	 * 收支金额（元）
	 */
	@JsonProperty("fee")
	private double fee;

	/**
	 * 账户结余（元）
	 */
	@JsonProperty("balance")
	private double balance;

	/**
	 * 资金变更提交申请人
	 */
	@JsonProperty("proposer")
	private String proposer;

	/**
	 * 备注
	 */
	@JsonProperty("note")
	private String note;

	/**
	 * 业务凭证号
	 */
	@JsonProperty("voucher_no")
	private String voucherNo;

	public LocalDateTime getAccountTime() {
		return accountTime;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public String getBizName() {
		return bizName;
	}

	public String getBizType() {
		return bizType;
	}

	public String getTradeType() {
		return tradeType;
	}

	public double getFee() {
		return fee;
	}

	public double getBalance() {
		return balance;
	}

	public String getProposer() {
		return proposer;
	}

	public String getNote() {
		return note;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
