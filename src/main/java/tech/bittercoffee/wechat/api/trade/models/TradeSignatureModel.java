package tech.bittercoffee.wechat.api.trade.models;

import org.apache.commons.text.RandomStringGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

import tech.bittercoffee.wechat.api.trade.enums.SignTypeEnum;

/**
 * 签名信息
 * 
 * @author BitterCoffee
 *
 */
public abstract class TradeSignatureModel extends TradeAppModel {

	private static final long serialVersionUID = -8209377529629240145L;
	protected static final RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder()
			.withinRange('0', '9')
			.build();

	/**
	 * 随机字符串
	 */
	@ApiField(name = "nonce_str")
	@JsonProperty("nonce_str")
	@JacksonXmlCData
	private String nonceStr;

	/**
	 * 签名算法
	 */
	@ApiField(name = "sign_type")
	@JsonProperty("sign_type")
	@JacksonXmlCData
	private SignTypeEnum signType;

	/**
	 * 签名
	 */
	@ApiField(name = "sign")
	@JsonProperty("sign")
	@JacksonXmlCData
	private String sign;

	public TradeSignatureModel() {
		this.nonceStr = randomStringGenerator.generate(32);
	}
}
