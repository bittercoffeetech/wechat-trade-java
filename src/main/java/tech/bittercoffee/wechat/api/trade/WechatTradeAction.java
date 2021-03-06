package tech.bittercoffee.wechat.api.trade;

import tech.bittercoffee.wechat.api.trade.models.TradeSignatureModel;

/**
 * 微信接口封装
 * 
 * @author BitterCoffee
 *
 * @param <R> 请求对象类型
 * @param <S> 返回对象类型
 */
public interface WechatTradeAction<R extends TradeSignatureModel, S> extends WechatTradeRequest<R>, WechatTradeResponse<S> {
	
	
}
