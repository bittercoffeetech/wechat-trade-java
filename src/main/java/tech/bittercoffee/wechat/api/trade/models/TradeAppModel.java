package tech.bittercoffee.wechat.api.trade.models;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * 应用程序标识
 * 
 * @author Bob
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TradeAppModel implements Serializable {

	private static final long serialVersionUID = -1334294208291195993L;

	@ApiField(name = "appid")
	@JsonProperty("appid")
	@JacksonXmlCData
	private String appId;

	@ApiField(name = "mch_id")
	@JsonProperty("mch_id")
	@JacksonXmlCData
	private String mchId;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
