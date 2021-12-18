package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeCreateRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeCreateResponse;

/**
 * 统一下单
 * 
 * @author BitterCoffee
 *
 */
public class WechatTradeCreateAction implements WechatTradeAction<TradeCreateRequest, TradeCreateResponse> {

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/unifiedorder";
	}

	@Override
	public boolean hasHierarchy() {
		return false;
	}
	
	@Override
	public Class<TradeCreateRequest> getRequestType() {
		return TradeCreateRequest.class;
	}

	@Override
	public Class<TradeCreateResponse> getResponseType() {
		return TradeCreateResponse.class;
	}
}
