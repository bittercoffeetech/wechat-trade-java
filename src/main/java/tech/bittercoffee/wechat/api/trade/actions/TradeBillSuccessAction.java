package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatClientConfig;
import tech.bittercoffee.wechat.api.trade.models.request.TradeBillSuccessRequest;
import tech.bittercoffee.wechat.api.trade.models.response.TradeBillSuccessResponse;

/**
 * 下载成功交易账单
 * 
 * @author BitterCoffee
 *
 */
public class TradeBillSuccessAction extends TradeBillAction<TradeBillSuccessRequest, TradeBillSuccessResponse> {

	public TradeBillSuccessAction(WechatClientConfig config) {
		super(config);
	}

	@Override
	public Class<TradeBillSuccessRequest> getRequestType() {
		return TradeBillSuccessRequest.class;
	}

	@Override
	public Class<TradeBillSuccessResponse> getResponseType() {
		return TradeBillSuccessResponse.class;
	}

}
