package tech.bittercoffee.wechat.api.trade.enums;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * 错误代码
 * 
 * @author BitterCoffee
 *
 */
public enum ErrorCodeEnum {

	/**
	 * appid和mch_id不匹配
	 */
	APPID_MCHID_NOT_MATCH("APPID_MCHID_NOT_MATCH"),

	/**
	 * APPID不存在
	 */
	APPID_NOT_EXIST("APPID_NOT_EXIST"),

	/**
	 * 退款业务流程错误，需要商户触发重试来解决
	 */
	BIZERR_NEED_RETRY("BIZERR_NEED_RETRY"),

	/**
	 * 证书校验错误
	 */
	CERT_ERROR("CERT_ERROR"),

	/**
	 * 业务错误
	 */
	ERROR("ERROR"),

	/**
	 * 频率限制
	 */
	FREQUENCY_LIMITED("FREQUENCY_LIMITED"),

	/**
	 * 无效请求过多
	 */
	INVALID_REQ_TOO_MUCH("INVALID_REQ_TOO_MUCH"),

	/**
	 * 参数错误
	 */
	INVALID_REQUEST("INVALID_REQUEST"),

	/**
	 * 无效transaction_id
	 */
	INVALID_TRANSACTIONID("INVALID_TRANSACTIONID"),

	/**
	 * 缺少参数
	 */
	LACK_PARAMS("LACK_PARAMS"),

	/**
	 * MCHID不存在
	 */
	MCHID_NOT_EXIST("MCHID_NOT_EXIST"),

	/**
	 * 商户无此接口权限.异常IP请求不予受理
	 */
	NOAUTH("NOAUTH"),

	/**
	 * 编码格式错误
	 */
	NOT_UTF8("NOT_UTF8"),

	/**
	 * 余额不足
	 */
	NOTENOUGH("NOTENOUGH"),

	/**
	 * 订单处理中，暂时无法退款，请稍后再试
	 */
	ORDER_NOT_READY("ORDER_NOT_READY"),

	/**
	 * 订单已关闭
	 */
	ORDERCLOSED("ORDERCLOSED"),

	/**
	 * 此交易订单号不存在
	 */
	ORDERNOTEXIST("ORDERNOTEXIST"),

	/**
	 * 商户订单已支付
	 */
	ORDERPAID("ORDERPAID"),

	/**
	 * 商户订单号重复
	 */
	OUT_TRADE_NO_USED("OUT_TRADE_NO_USED"),

	/**
	 * 参数错误
	 */
	PARAM_ERROR("PARAM_ERROR"),

	/**
	 * post数据为空
	 */
	POST_DATA_EMPTY("POST_DATA_EMPTY"),

	/**
	 * 订单金额或退款金额与之前请求不一致，请核实后再试
	 */
	REFUND_FEE_MISMATCH("REFUND_FEE_MISMATCH"),

	/**
	 * 退款订单查询失败
	 */
	REFUNDNOTEXIST("REFUNDNOTEXIST"),

	/**
	 * 请使用post方法
	 */
	REQUIRE_POST_METHOD("REQUIRE_POST_METHOD"),

	/**
	 * 签名错误
	 */
	SIGNERROR("SIGNERROR"),

	/**
	 * 系统错误
	 */
	SYSTEMERROR("SYSTEMERROR"),

	/**
	 * 订单已经超过退款期限
	 */
	TRADE_OVERDUE("TRADE_OVERDUE"),

	/**
	 * 退款请求失败
	 */
	USER_ACCOUNT_ABNORMAL("USER_ACCOUNT_ABNORMAL"),

	/**
	 * XML格式错误
	 */
	XML_FORMAT_ERROR("XML_FORMAT_ERROR"),

	/**
	 * 账单不存在
	 */
	NO_BILL_EXIST("NO_BILL_EXIST"),

	/**
	 * 账单创建中
	 */
	BILL_CREATING("BILL_CREATING"),

	/**
	 * 其它未知异常
	 */
	OTHERS("OTHERS");

	private String value;

	private ErrorCodeEnum(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	private static final Map<ErrorCodeEnum, String> MESSAGES = ImmutableMap.<ErrorCodeEnum, String>builder()
			.put(ErrorCodeEnum.SYSTEMERROR, "系统错误")
			.put(ErrorCodeEnum.XML_FORMAT_ERROR, "XML格式错误")
			.put(ErrorCodeEnum.APPID_NOT_EXIST, "APPID不存在")
			.put(ErrorCodeEnum.MCHID_NOT_EXIST, "MCHID不存在")
			.put(ErrorCodeEnum.APPID_MCHID_NOT_MATCH, "appid和mch_id不匹配")
			.put(ErrorCodeEnum.SIGNERROR, "签名错误")
			.put(ErrorCodeEnum.REQUIRE_POST_METHOD, "请使用post方法")
			.put(ErrorCodeEnum.NOAUTH, "商户无此接口权限")
			.put(ErrorCodeEnum.INVALID_REQUEST, "参数错误")
			.put(ErrorCodeEnum.LACK_PARAMS, "缺少参数")
			.put(ErrorCodeEnum.POST_DATA_EMPTY, "post数据为空")
			.put(ErrorCodeEnum.NOT_UTF8, "编码格式错误")
			.put(ErrorCodeEnum.NOTENOUGH, "余额不足")
			.put(ErrorCodeEnum.ORDERPAID, "商户订单已支付")
			.put(ErrorCodeEnum.ORDERCLOSED, "订单已关闭")
			.put(ErrorCodeEnum.OUT_TRADE_NO_USED, "商户订单号重复")
			.put(ErrorCodeEnum.PARAM_ERROR, "参数错误")
			.put(ErrorCodeEnum.INVALID_REQ_TOO_MUCH, "无效请求过多")
			.put(ErrorCodeEnum.FREQUENCY_LIMITED, "频率限制")
			.put(ErrorCodeEnum.CERT_ERROR, "证书校验错误")
			.put(ErrorCodeEnum.INVALID_TRANSACTIONID, "无效transaction_id")
			.put(ErrorCodeEnum.BIZERR_NEED_RETRY, "退款业务流程错误，需要商户触发重试来解决")
			.put(ErrorCodeEnum.TRADE_OVERDUE, "订单已经超过退款期限")
			.put(ErrorCodeEnum.ERROR, "业务错误")
			.put(ErrorCodeEnum.USER_ACCOUNT_ABNORMAL, "退款请求失败")
			.put(ErrorCodeEnum.REFUND_FEE_MISMATCH, "订单金额或退款金额与之前请求不一致，请核实后再试")
			.put(ErrorCodeEnum.ORDER_NOT_READY, "订单处理中，暂时无法退款，请稍后再试")
			.put(ErrorCodeEnum.REFUNDNOTEXIST, "退款订单查询失败")
			.build();

	public static String of(ErrorCodeEnum code) {
		return MESSAGES.get(code);
	}

}
