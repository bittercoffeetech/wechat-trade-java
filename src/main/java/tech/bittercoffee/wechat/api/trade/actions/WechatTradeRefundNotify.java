package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeResponse;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundNotify;

/**
 * 退款结果通知
 * 
 * @author BitterCoffee
 *
 */
public class WechatTradeRefundNotify implements WechatTradeResponse<TradeRefundNotify> {

	@Override
	public String encrypted() {
		return "req_info";
	}
	
	@Override
	public boolean hasResult() {
		return false;
	}

	@Override
	public boolean hasSigned() {
		return false;
	}

	@Override
	public boolean hasHierarchy() {
		return false;
	}

	@Override
	public Class<TradeRefundNotify> getResponseType() {
		return TradeRefundNotify.class;
	}
}
