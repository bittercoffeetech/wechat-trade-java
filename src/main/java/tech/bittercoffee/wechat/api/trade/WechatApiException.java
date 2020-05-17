package tech.bittercoffee.wechat.api.trade;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import tech.bittercoffee.wechat.api.trade.enums.ErrorCodeEnum;

/**
 * 微信调用失败或返回错误时发生的异常
 * 
 * @author Bob
 *
 */
public final class WechatApiException extends Exception {

	private static final long serialVersionUID = -4996759575262635197L;
	private final String code;
	
	/**
	 * 
	 * @param code API返回的错误代码
	 * @param message API返回的错误信息
	 */
	public WechatApiException(String code, String message) {
		super(message);
		this.code = code;	
	}
	
	public WechatApiException(String message, Throwable cause) {
        super(message, cause);
        code = null;
    }
	
	public WechatApiException(ErrorCodeEnum errorCode) {
		super(ErrorCodeEnum.of(errorCode));
		this.code = errorCode.value();		
	}
	
	public String getCode() {
		return code;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
