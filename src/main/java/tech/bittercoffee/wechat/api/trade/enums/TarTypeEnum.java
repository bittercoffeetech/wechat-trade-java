package tech.bittercoffee.wechat.api.trade.enums;

/**
 * 压缩类型
 * 
 * @author BitterCoffee
 *
 */  
public enum TarTypeEnum {

	GZIP("GZIP");
	
	private String value;

	private TarTypeEnum(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
