package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatClientConfig;
import tech.bittercoffee.wechat.api.trade.models.request.TradeBillRefundRequest;
import tech.bittercoffee.wechat.api.trade.models.response.TradeBillRefundResponse;

/**
 * 下载退款交易账单
 * 
 * @author BitterCoffee
 *
 */
public class TradeBillRefundAction extends TradeBillAction<TradeBillRefundRequest, TradeBillRefundResponse> {

	public TradeBillRefundAction(WechatClientConfig config) {
		super(config);
	}

	@Override
	public Class<TradeBillRefundRequest> getRequestType() {
		return TradeBillRefundRequest.class;
	}

	@Override
	public Class<TradeBillRefundResponse> getResponseType() {
		return TradeBillRefundResponse.class;
	}

}
