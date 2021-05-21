package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundQueryRequest;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundQueryResponse;

/**
 * 退款查询
 * 
 * @author BitterCoffee
 *
 */
public class WechatTradeRefundQueryAction implements WechatTradeAction<TradeRefundQueryRequest, TradeRefundQueryResponse> {

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/refundquery";
	}

	@Override
	public Class<TradeRefundQueryRequest> getRequestType() {
		return TradeRefundQueryRequest.class;
	}

	@Override
	public Class<TradeRefundQueryResponse> getResponseType() {
		return TradeRefundQueryResponse.class;
	}

}
