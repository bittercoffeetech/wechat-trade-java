package tech.bittercoffee.wechat.api.trade.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 签名类型
 * 
 * @author BitterCoffee
 *
 */
public enum SignTypeEnum {
	/**
	 * SHA256
	 */
	HMAC_SHA256("HMAC-SHA256"), 
	
	/**
	 * 默认MD5
	 */
	MD5("MD5");
	
	private String value;

	private SignTypeEnum(String value) {
		this.value = value;
	}

	@JsonValue
	public String value() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.value;
	}
	
}
