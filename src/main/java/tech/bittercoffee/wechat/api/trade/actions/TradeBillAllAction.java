package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.models.TradeBillAllRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeBillAllResponse;

/**
 * 下载所有交易账单
 * 
 * @author BitterCoffee
 *
 */
public class WechatTradeBillAllAction extends WechatTradeBillAction<TradeBillAllRequest, TradeBillAllResponse> {

	@Override
	public Class<TradeBillAllRequest> getRequestType() {
		return TradeBillAllRequest.class;
	}

	@Override
	public Class<TradeBillAllResponse> getResponseType() {
		return TradeBillAllResponse.class;
	}

}
