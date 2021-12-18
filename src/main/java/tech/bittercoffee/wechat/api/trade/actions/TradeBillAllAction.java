package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatClientConfig;
import tech.bittercoffee.wechat.api.trade.models.request.TradeBillAllRequest;
import tech.bittercoffee.wechat.api.trade.models.response.TradeBillAllResponse;

/**
 * 下载所有交易账单
 * 
 * @author BitterCoffee
 *
 */
public class TradeBillAllAction extends TradeBillAction<TradeBillAllRequest, TradeBillAllResponse> {

	public TradeBillAllAction(WechatClientConfig config) {
		super(config);
	}

	@Override
	public Class<TradeBillAllRequest> getRequestType() {
		return TradeBillAllRequest.class;
	}

	@Override
	public Class<TradeBillAllResponse> getResponseType() {
		return TradeBillAllResponse.class;
	}

}
