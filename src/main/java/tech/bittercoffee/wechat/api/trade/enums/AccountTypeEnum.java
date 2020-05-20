package tech.bittercoffee.wechat.api.trade.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 资金账户类型
 * 
 * @author BitterCoffee
 *
 */  
public enum AccountTypeEnum {
	
	/**
	 * 基本账户
	 */
	BASIC("Basic"),
	
	/**
	 * 运营账户
	 */
	OPERATION("Operation"),
	
	/**
	 * 手续费账户
	 */
	FEES("Fees");
	
	private String value;

	private AccountTypeEnum(String value) {
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
