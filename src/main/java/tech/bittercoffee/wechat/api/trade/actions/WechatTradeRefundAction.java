package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundModel;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundResponseModel;

/**
 * 退款申请
 * 
 * @author BitterCoffee
 *
 */
public class WechatTradeRefundAction implements WechatTradeAction<TradeRefundModel, TradeRefundResponseModel> {

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/secapi/pay/refund";
	}

	@Override
	public boolean certificated() {
		return true;
	}

	@Override
	public Class<TradeRefundModel> getRequestType() {
		return TradeRefundModel.class;
	}

	@Override
	public Class<TradeRefundResponseModel> getResponseType() {
		return TradeRefundResponseModel.class;
	}

}
