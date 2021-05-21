package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeCloseRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeCloseResponse;

/**
 * 关闭订单
 * 
 * @author BitterCoffee
 *
 */
public class WechatTradeCloseAction implements WechatTradeAction<TradeCloseRequest, TradeCloseResponse> {

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/closeorder";
	}

	@Override
	public boolean hasHierarchy() {
		return false;
	}

	@Override
	public Class<TradeCloseRequest> getRequestType() {
		return TradeCloseRequest.class;
	}

	@Override
	public Class<TradeCloseResponse> getResponseType() {
		return TradeCloseResponse.class;
	}
	
}
