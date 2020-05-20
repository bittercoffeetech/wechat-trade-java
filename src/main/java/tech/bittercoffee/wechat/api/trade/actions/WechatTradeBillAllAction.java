package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.models.TradeBillAllModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillAllResponseModel;

/**
 * 下载所有交易账单
 * 
 * @author BitterCoffee
 *
 */
public class WechatTradeBillAllAction extends WechatTradeBillAction<TradeBillAllModel, TradeBillAllResponseModel> {

	@Override
	public Class<TradeBillAllModel> getRequestType() {
		return TradeBillAllModel.class;
	}

	@Override
	public Class<TradeBillAllResponseModel> getResponseType() {
		return TradeBillAllResponseModel.class;
	}

}
