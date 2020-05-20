package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeQueryModel;
import tech.bittercoffee.wechat.api.trade.models.TradeQueryResponseModel;

/**
 * 订单查询
 * 
 * @author BitterCoffee
 *
 */
public final class WechatTradeQueryAction implements WechatTradeAction<TradeQueryModel, TradeQueryResponseModel> {

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/orderquery";
	}

	@Override
	public Class<TradeQueryModel> getRequestType() {
		return TradeQueryModel.class;
	}

	@Override
	public Class<TradeQueryResponseModel> getResponseType() {
		return TradeQueryResponseModel.class;
	}

}
