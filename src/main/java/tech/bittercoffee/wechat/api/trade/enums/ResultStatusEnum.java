package tech.bittercoffee.wechat.api.trade.enums;

/**
 * 返回状态码/业务结果
 * 
 * @author BitterCoffee
 *
 */
public enum ResultStatusEnum {
	/**
	 * 成功
	 */
	SUCCESS("SUCCESS"),
	/**
	 * 失败
	 */
	FAIL("FAIL"),
	/**
	 * 未知
	 */
	UNKNOWN("UNKNOWN");

	private String value;

	private ResultStatusEnum(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

}
