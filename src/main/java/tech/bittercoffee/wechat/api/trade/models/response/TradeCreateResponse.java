package tech.bittercoffee.wechat.api.trade.models.response;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import tech.bittercoffee.wechat.api.trade.UidGenerator;
import tech.bittercoffee.wechat.api.trade.enums.TradeTypeEnum;

/**
 * 统一下单返回
 * 
 * @author BitterCoffee
 *
 */
@JsonRootName("trade_create_response")
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public final class TradeCreateResponse implements Serializable {

	private static final long serialVersionUID = 8268705503120335822L;

	public TradeCreateResponse() {
		this.tradeNo = UidGenerator.next();
	}

	/**
	 * 商户订单号 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。
	 */
	@JsonProperty("out_trade_no")
	private String tradeNo;

	/**
	 * 二维码链接
	 */
	@JsonProperty("code_url")
	private String codeUrl;

	/**
	 * 交易类型
	 * 
	 * @see TradeTypeEnum
	 */
	@JsonProperty("trade_type")
	private TradeTypeEnum tradeType;

	/**
	 * 预支付交易会话标识
	 */
	@JsonProperty("prepay_id")
	private String prepayId;

	/**
	 * 设备号 自定义参数，可以为请求支付的终端设备号等
	 */
	@JsonProperty("device_info")
	private String deviceInfo;

	public String getTradeNo() {
		return tradeNo;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public TradeTypeEnum getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeTypeEnum tradeType) {
		this.tradeType = tradeType;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
