package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeQueryRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeQueryResponse;

/**
 * 订单查询
 * 
 * @author BitterCoffee
 *
 */
public final class WechatTradeQueryAction implements WechatTradeAction<TradeQueryRequest, TradeQueryResponse> {

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/orderquery";
	}

	@Override
	public Class<TradeQueryRequest> getRequestType() {
		return TradeQueryRequest.class;
	}

	@Override
	public Class<TradeQueryResponse> getResponseType() {
		return TradeQueryResponse.class;
	}

}
