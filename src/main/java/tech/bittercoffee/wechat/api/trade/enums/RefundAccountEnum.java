package tech.bittercoffee.wechat.api.trade.enums;

/**
 * 退款账号
 * 
 * @author BitterCoffee
 *
 */
public enum RefundAccountEnum {
	/**
	 * 未结算资金退款（默认使用未结算资金退款）
	 */
	REFUND_SOURCE_RECHARGE_FUNDS("REFUND_SOURCE_RECHARGE_FUNDS"), 
	/**
	 * 可用余额退款
	 */
	REFUND_SOURCE_UNSETTLED_FUNDS("REFUND_SOURCE_UNSETTLED_FUNDS");
	
	private String value;

	private RefundAccountEnum(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
