package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeCreateModel;
import tech.bittercoffee.wechat.api.trade.models.TradeCreateResponseModel;

/**
 * 统一下单
 * 
 * @author BitterCoffee
 *
 */
public class WechatTradeCreateAction implements WechatTradeAction<TradeCreateModel, TradeCreateResponseModel> {

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/unifiedorder";
	}

	@Override
	public boolean hasHierarchy() {
		return false;
	}
	
	@Override
	public Class<TradeCreateModel> getRequestType() {
		return TradeCreateModel.class;
	}

	@Override
	public Class<TradeCreateResponseModel> getResponseType() {
		return TradeCreateResponseModel.class;
	}
}
