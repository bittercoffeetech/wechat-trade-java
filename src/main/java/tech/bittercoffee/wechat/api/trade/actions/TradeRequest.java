package tech.bittercoffee.wechat.api.trade;

import tech.bittercoffee.wechat.api.trade.enums.SignTypeEnum;
import tech.bittercoffee.wechat.api.trade.models.TradeSignatureInfo;

/**
 * 接口请求内容定义
 * 
 * @author BitterCoffee
 *
 * @param <R> 请求对象类型
 */
public interface WechatTradeRequest<R extends TradeSignatureInfo> {
	
	/**
	 * @return 获取请求对象类型
	 */
	Class<? extends R> getRequestType();
	
	/**
	 * @return 返回接口地址
	 */
	String url();
	
	/**
	 * @return 是否需要安全证书
	 */
	default boolean certificated() {
		return false;
	}
	
	/**
	 * @return 签名算法
	 */
	default SignTypeEnum requestSignType() {
		return SignTypeEnum.MD5;
	}
	
}
