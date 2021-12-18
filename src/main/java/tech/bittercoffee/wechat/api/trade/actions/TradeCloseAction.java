package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatClientConfig;
import tech.bittercoffee.wechat.api.trade.models.request.TradeCloseRequest;
import tech.bittercoffee.wechat.api.trade.models.response.TradeCloseResponse;

/**
 * 关闭订单
 * 
 * @author BitterCoffee
 *
 */
public class TradeCloseAction extends AbstractTradeAction<TradeCloseRequest, TradeCloseResponse> {

	public TradeCloseAction(WechatClientConfig config) {
		super(config);
	}

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/closeorder";
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
