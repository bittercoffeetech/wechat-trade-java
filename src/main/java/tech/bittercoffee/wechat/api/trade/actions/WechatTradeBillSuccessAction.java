package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.models.TradeBillSuccessModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillSuccessResponseModel;

/**
 * 下载成功交易账单
 * 
 * @author Bob
 *
 */
public class WechatTradeBillSuccessAction extends WechatTradeBillAction<TradeBillSuccessModel, TradeBillSuccessResponseModel> {

	@Override
	public Class<TradeBillSuccessModel> getRequestType() {
		return TradeBillSuccessModel.class;
	}

	@Override
	public Class<TradeBillSuccessResponseModel> getResponseType() {
		return TradeBillSuccessResponseModel.class;
	}

}
