package tech.bittercoffee.wechat.api.trade.enums;

/**
 * 签名类型
 * 
 * @author Bob
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

	public String value() {
		return this.value;
	}
}
