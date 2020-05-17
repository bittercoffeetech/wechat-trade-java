package tech.bittercoffee.wechat.api.trade.models;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import tech.bittercoffee.wechat.api.trade.enums.TradeTypeEnum;

/**
 * 统一下单返回
 * 
 * @author Bob
 *
 */
@JsonRootName("trade_create_response")
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public final class TradeCreateResponseModel extends TradeAppModel {

	private static final long serialVersionUID = 8268705503120335822L;

	/**
	 * 二维码链接
	 */
	@ApiField(name = "code_url")
	@JsonProperty("code_url")
	private String codeUrl;

	/**
	 * 交易类型
	 * 
	 * @see TradeTypeEnum
	 */
	@ApiField(name = "trade_type")
	@JsonProperty("trade_type")
	private TradeTypeEnum tradeType;

	/**
	 * 预支付交易会话标识
	 */
	@ApiField(name = "prepay_id")
	@JsonProperty("prepay_id")
	private String prepayId;

	/**
	 * 设备号 自定义参数，可以为请求支付的终端设备号等
	 */
	@ApiField(name = "device_info")
	@JsonProperty("device_info")
	private String deviceInfo;

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
