package tech.bittercoffee.wechat.api.trade.models;

import java.io.Serializable;

import org.apache.commons.text.RandomStringGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * 应用程序标识
 * 
 * @author BitterCoffee
 *
 */
public abstract class TradeAppInfo implements Serializable {

	private static final long serialVersionUID = -1334294208291195993L;
	protected static final RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder()
			.withinRange('0', '9')
			.build();

	@ApiField(name = "appid")
	@JsonProperty("appid")
	@JacksonXmlCData
	private String appId;

	@ApiField(name = "mch_id")
	@JsonProperty("mch_id")
	@JacksonXmlCData
	private String mchId;

}