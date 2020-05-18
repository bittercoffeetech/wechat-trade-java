package tech.bittercoffee.wechat.api.trade.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 交易关闭请求
 * 
 * @author Bob
 *
 */
@JsonRootName("trade_close")
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public final class TradeCloseModel extends TradeSignatureModel {

	private static final long serialVersionUID = -198191473007581123L;
	
	public static TradeCloseModel withTradeNo(String tradeNo) {
		TradeCloseModel model = new TradeCloseModel();
		model.tradeNo = tradeNo;
		
		return model;
	}
	
	public static TradeCloseModel withTransactionId(String transactionId) {
		TradeCloseModel model = new TradeCloseModel();
		model.transactionId = transactionId;
		
		return model;
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
	
	public String getTradeNo() {
		return tradeNo;
	}

	public String getTransactionId() {
		return transactionId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
