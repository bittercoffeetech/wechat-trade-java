package tech.bittercoffee.wechat.api.trade.models.response;

import static org.apache.commons.lang3.EnumUtils.getEnum;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.defaultString;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

import tech.bittercoffee.wechat.api.trade.enums.ErrorCodeEnum;
import tech.bittercoffee.wechat.api.trade.enums.ResultStatusEnum;

/**
 * 业务结果返回值
 * 
 * @author BitterCoffee
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class TradeResult implements Serializable {

	private static final long serialVersionUID = 8509282269114024065L;

	@JsonCreator
	public TradeResult(@JsonProperty("result_code") String resultCode, @JsonProperty("err_code") String errorCode,
			@JsonProperty("err_code_des") String errorMessage) {
		super();
		this.resultCode = defaultIfNull(getEnum(ResultStatusEnum.class, resultCode), ResultStatusEnum.UNKNOWN);
		this.errorCode = errorCode;
		this.errorMessage = defaultString(errorMessage, ErrorCodeEnum.of(ErrorCodeEnum.INVALID_REQUEST));
	}

	/**
	 * 返回状态码 SUCCESS/FAIL
	 */
	@JsonProperty("result_code")
	@JacksonXmlCData
	private ResultStatusEnum resultCode;

	/**
	 * 错误代码
	 */
	@JsonProperty("err_code")
	@JacksonXmlCData
	private String errorCode;

	/**
	 * 错误代码描述
	 */
	@JsonProperty("err_code_des")
	@JacksonXmlCData
	private String errorMessage;

	public String getCode() {
		return errorCode;
	}

	public String getMessage() {
		return errorMessage;
	}

	public boolean isSuccess() {
		return ResultStatusEnum.SUCCESS.equals(resultCode);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
