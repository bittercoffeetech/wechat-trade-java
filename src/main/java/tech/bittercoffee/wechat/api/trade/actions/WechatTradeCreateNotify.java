package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeResponse;
import tech.bittercoffee.wechat.api.trade.models.TradeCreateNotify;

/**
 * 支付结果通知
 * 
 * @author BitterCoffee
 *
 */
public class WechatTradeCreateNotify implements WechatTradeResponse<TradeCreateNotify> {

	@Override
	public Class<TradeCreateNotify> getResponseType() {
		return TradeCreateNotify.class;
	}
	
}
