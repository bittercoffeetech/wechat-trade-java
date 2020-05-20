package tech.bittercoffee.wechat.api.trade.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * 应用程序标识
 * 
 * @author BitterCoffee
 *
 */
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

}
