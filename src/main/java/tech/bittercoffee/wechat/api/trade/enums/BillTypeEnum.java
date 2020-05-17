package tech.bittercoffee.wechat.api.trade.enums;

/**
 * 账单类型
 * 
 * @author Bob
 *
 */
public enum BillTypeEnum {
	
	/**
	 * （默认值），返回当日所有订单信息（不含充值退款订单）
	 */
	ALL("ALL"),
	
	/**
	 * 返回当日成功支付的订单（不含充值退款订单）
	 */
	SUCCESS("SUCCESS"),
	
	/**
	 * 返回当日退款订单（不含充值退款订单）
	 */
	REFUND("REFUND"),
	
	/**
	 * 返回当日充值退款订单
	 */
	RECHARGE_REFUND("RECHARGE_REFUND");
	
	private String value;

	private BillTypeEnum(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
