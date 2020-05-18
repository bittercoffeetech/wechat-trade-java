package tech.bittercoffee.wechat.api.trade.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 查询退款请求
 * 
 * @author Bob
 *
 */
@JsonRootName("trade_refund_query")
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public final class TradeRefundQueryModel extends TradeSignatureModel {

	private static final long serialVersionUID = 8909748011353635694L;
	
	public static TradeRefundQueryModel withTradeNo(String tradeNo) {
		TradeRefundQueryModel model = new TradeRefundQueryModel();
		model.tradeNo = tradeNo;
		
		return model;
	}
	
	public static TradeRefundQueryModel withTransactionId(String transactionId) {
		TradeRefundQueryModel model = new TradeRefundQueryModel();
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

	/**
	 * 商户退款单号 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
	 */
	@JsonProperty("out_refund_no")
	@JacksonXmlCData
	private String refundNo;

	/**
	 * 微信退款单号 微信生成的退款单号，在申请退款接口有返回
	 */
	@JsonProperty("refund_id")
	@JacksonXmlCData
	private String refundId;

	/**
	 * 偏移量 偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
	 */
	@JsonProperty("offset")
	@JacksonXmlCData
	private Integer offset;

	public TradeRefundQueryModel refundNo(String refundNo) {
		this.refundNo = refundNo;
		return this;
	}

	public TradeRefundQueryModel refundId(String refundId) {
		this.refundId = refundId;
		return this;
	}

	public TradeRefundQueryModel offset(Integer offset) {
		this.offset = offset;
		return this;
	}
	
	public String getRefundNo() {
		return refundNo;
	}

	public String getRefundId() {
		return refundId;
	}
	
	public Integer getOffset() {
		return offset;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
