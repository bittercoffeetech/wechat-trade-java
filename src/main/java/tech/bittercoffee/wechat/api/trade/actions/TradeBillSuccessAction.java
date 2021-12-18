package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.models.TradeBillSuccessRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeBillSuccessResponse;

/**
 * 下载成功交易账单
 * 
 * @author BitterCoffee
 *
 */
public class WechatTradeBillSuccessAction extends WechatTradeBillAction<TradeBillSuccessRequest, TradeBillSuccessResponse> {

	@Override
	public Class<TradeBillSuccessRequest> getRequestType() {
		return TradeBillSuccessRequest.class;
	}

	@Override
	public Class<TradeBillSuccessResponse> getResponseType() {
		return TradeBillSuccessResponse.class;
	}

}
