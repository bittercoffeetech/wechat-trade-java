package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatClientConfig;
import tech.bittercoffee.wechat.api.trade.models.request.TradeCreateRequest;
import tech.bittercoffee.wechat.api.trade.models.response.TradeCreateResponse;

/**
 * 统一下单
 * 
 * @author BitterCoffee
 *
 */
public class TradeCreateAction extends AbstractTradeAction<TradeCreateRequest, TradeCreateResponse> {

	public TradeCreateAction(WechatClientConfig config) {
		super(config);
	}

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/unifiedorder";
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
