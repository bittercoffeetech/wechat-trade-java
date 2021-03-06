package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeResponse;
import tech.bittercoffee.wechat.api.trade.models.TradeCreateNotifyModel;

/**
 * 支付结果通知
 * 
 * @author BitterCoffee
 *
 */
public class WechatTradeCreateNotify implements WechatTradeResponse<TradeCreateNotifyModel> {

	@Override
	public Class<TradeCreateNotifyModel> getResponseType() {
		return TradeCreateNotifyModel.class;
	}
	
}
