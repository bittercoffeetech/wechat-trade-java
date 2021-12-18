package tech.bittercoffee.wechat.api.trade.enums;

/**
 * 交易状态
 * 
 * @author BitterCoffee
 *
 */
public enum TradeStatusEnum {
	/**
	 * 支付成功
	 */
	SUCCESS("SUCCESS"),
	/**
	 * REFUND
	 */
	REFUND("REFUND"),
	/**
	 * 未支付
	 */
	NOTPAY("NOTPAY"),
	/**
	 * 已关闭
	 */
	CLOSED("CLOSED"),
	/**
	 * 已撤销（刷卡支付）
	 */
	REVOKED("REVOKED"),
	/**
	 * 用户支付中
	 */
	USERPAYING("USERPAYING"),
	/**
	 * 支付失败(其他原因，如银行返回失败)
	 */
	PAYERROR("PAYERROR");

	private String value;

	private TradeStatusEnum(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
