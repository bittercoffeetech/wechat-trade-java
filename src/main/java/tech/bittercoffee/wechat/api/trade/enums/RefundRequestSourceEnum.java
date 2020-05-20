package tech.bittercoffee.wechat.api.trade.enums;

/**
 * 退款发起来源
 * 
 * @author BitterCoffee
 *
 */
public enum RefundRequestSourceEnum {
	/**
	 * API接口
	 */
	API("API"), 
	/**
	 * 商户平台
	 */
	VENDOR_PLATFORM("VENDOR_PLATFORM");
	
	private String value;

	private RefundRequestSourceEnum(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
