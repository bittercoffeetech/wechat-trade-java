package tech.bittercoffee.wechat.api.trade;

import java.io.IOException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import tech.bittercoffee.wechat.api.trade.enums.ErrorCodeEnum;

/**
 * 微信调用失败或返回错误时发生的异常
 * 
 * @author BitterCoffee
 *
 */
public final class WechatApiException extends IOException {

	private static final long serialVersionUID = -4996759575262635197L;
	private final ErrorCodeEnum errorType;
	private final String errorCode;
	private final String errorMessage;

	/**
	 * 
	 * @param code    API返回的错误代码
	 * @param message API返回的错误信息
	 */
	public WechatApiException(String code, String message) {
		super(message);
		this.errorType = ErrorCodeEnum.SYSTEMERROR;
		this.errorCode = code;
		this.errorMessage = message;
	}
	
	public WechatApiException(ErrorCodeEnum type) {
		this.errorType = type;
		this.errorCode = type.value();
		this.errorMessage = ErrorCodeEnum.of(type);
	}

	public WechatApiException(Throwable cause) {
		super(ErrorCodeEnum.of(ErrorCodeEnum.SYSTEMERROR), cause);
		this.errorType = ErrorCodeEnum.SYSTEMERROR;
		if(cause instanceof WechatApiException) {
			WechatApiException wae = (WechatApiException) cause;
			this.errorCode = wae.getErrorCode();
			this.errorMessage = wae.getErrorMessage();
		} else {
			this.errorCode = ErrorCodeEnum.of(ErrorCodeEnum.SYSTEMERROR);
			this.errorMessage = cause.getMessage();
		}
	}
	
	public ErrorCodeEnum getErrorType() {
		return errorType;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}	
}
