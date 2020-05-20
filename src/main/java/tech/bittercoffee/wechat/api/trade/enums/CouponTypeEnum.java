package tech.bittercoffee.wechat.api.trade.enums;

/**
 * 代金券类型
 * 
 * @author BitterCoffee
 *
 */
public enum CouponTypeEnum {
	/**
	 * 充值代金券
	 */
	CASH("CASH"), 
	/**
	 * 非充值优惠券
	 */
	NO_CASH("NO_CASH");
	
	private String value;

	private CouponTypeEnum(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
