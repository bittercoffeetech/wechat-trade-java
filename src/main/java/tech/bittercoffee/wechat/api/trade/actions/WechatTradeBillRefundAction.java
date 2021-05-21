package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.models.TradeBillRefundRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeBillRefundResponse;

/**
 * 下载退款交易账单
 * 
 * @author BitterCoffee
 *
 */
public class WechatTradeBillRefundAction extends WechatTradeBillAction<TradeBillRefundRequest, TradeBillRefundResponse> {

	@Override
	public Class<TradeBillRefundRequest> getRequestType() {
		return TradeBillRefundRequest.class;
	}

	@Override
	public Class<TradeBillRefundResponse> getResponseType() {
		return TradeBillRefundResponse.class;
	}

}
