package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundResponse;

/**
 * 退款申请
 * 
 * @author BitterCoffee
 *
 */
public class WechatTradeRefundAction implements WechatTradeAction<TradeRefundRequest, TradeRefundResponse> {

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/secapi/pay/refund";
	}

	@Override
	public boolean certificated() {
		return true;
	}

	@Override
	public Class<TradeRefundRequest> getRequestType() {
		return TradeRefundRequest.class;
	}

	@Override
	public Class<TradeRefundResponse> getResponseType() {
		return TradeRefundResponse.class;
	}

}
