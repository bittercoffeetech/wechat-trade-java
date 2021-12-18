package tech.bittercoffee.wechat.api.trade.models;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 资金账单汇总信息
 * 
 * @author BitterCoffee
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(value = { "total_flows", "total_incomes", "total_income_fee", "total_expenses", "total_expenses_fee" })
public class TradeFundflowSummaryInfo implements Serializable {

	private static final long serialVersionUID = 7534222140623095477L;

	/**
	 * 资金流水总笔数
	 */
	@JsonProperty("total_flows")
	private long totalFlows;

	/**
	 * 收入笔数
	 */
	@JsonProperty("total_incomes")
	private long totalIncomes;

	/**
	 * 收入金额
	 */
	@JsonProperty("total_income_fee")
	private double totalIncomeFee;

	/**
	 * 支出笔数
	 */
	@JsonProperty("total_expenses")
	private double totalExpenses;

	/**
	 * 支出金额
	 */
	@JsonProperty("total_expenses_fee")
	private double totalExpensesFee;

	public long getTotalFlows() {
		return totalFlows;
	}

	public long getTotalIncomes() {
		return totalIncomes;
	}

	public double getTotalIncomeFee() {
		return totalIncomeFee;
	}

	public double getTotalExpenses() {
		return totalExpenses;
	}

	public double getTotalExpensesFee() {
		return totalExpensesFee;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}