package tech.bittercoffee.wechat.api.trade.models.response;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.google.common.collect.ImmutableMap;

import tech.bittercoffee.wechat.api.trade.enums.ResultStatusEnum;

/**
 * 执行返回结果
 * 
 * @author BitterCoffee
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public class TradeReturn implements Serializable {

	private static final long serialVersionUID = 8509282269114024065L;
	public static final TradeReturn OK = new TradeReturn(ResultStatusEnum.SUCCESS, "OK");

	public static final TradeReturn fail(String error) {
		return new TradeReturn(ResultStatusEnum.FAIL, error);
	}

	/**
	 * 返回状态码 SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	 */
	@JsonProperty("return_code")
	@JacksonXmlCData
	private ResultStatusEnum returnCode;

	/**
	 * 返回信息，如非空，未知错误原因,签名失败,参数格式校验错误
	 */
	@JsonProperty("return_msg")
	@JacksonXmlCData
	private String returnMessage;

	public TradeReturn(ResultStatusEnum returnCode, String returnMessage) {
		super();
		this.returnCode = returnCode;
		this.returnMessage = returnMessage;
	}

	@JsonCreator
	public TradeReturn(@JsonProperty("return_code") String returnCode, @JsonProperty("return_msg") String returnMessage,
			@JsonProperty("error_code") String errorCode) {
		super();
		this.returnCode = ObjectUtils.defaultIfNull(EnumUtils.getEnum(ResultStatusEnum.class, returnCode),
				ResultStatusEnum.UNKNOWN);

		if (ERROR_CODES.containsKey(returnMessage)) {
			this.returnMessage = ERROR_CODES.get(returnMessage);
		} else if (ERROR_CODES.containsKey(errorCode)) {
			this.returnMessage = ERROR_CODES.get(errorCode);
		} else {
			this.returnMessage = returnMessage;
		}
	}

	public boolean isSuccess() {
		return ResultStatusEnum.SUCCESS.equals(returnCode);
	}

	public String getCode() {
		return returnCode.toString();
	}

	public String getMessage() {
		return returnMessage;
	}

	private static final Map<String, String> ERROR_CODES = ImmutableMap.<String, String>builder()
			.put("sign error", "签名错误").put("nonce_str too long", "参数nonce_str错误")
			.put("invalid tar_type, Only GZIP supported", "参数tar_type错误").put("invalid bill_type", "参数bill_type错误")
			.put("invalid bill_date", "参数bill_date错误").put("require POST method", "请求方式错误")
			.put("empty post data", "请求报文错误").put("data format error", "参数格式错误").put("missing parameter", "缺少参数")
			.put("invalid appid", "appid错误").put("invalid parameter", "参数错误").put("No Bill Exist", "账单不存在")
			.put("Bill Creating", "账单未生成").put("system error", "下载失败").put("100", "下载失败").put("20003", "下载失败")
			.put("20007", "当前商户号账单API权限已经关闭").build();

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
