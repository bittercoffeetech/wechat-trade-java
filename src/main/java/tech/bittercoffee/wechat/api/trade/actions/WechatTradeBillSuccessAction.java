package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeBillSuccessModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillSuccessResponseModel;

/**
 * 下载成功交易账单
 * 
 * @author Bob
 *
 */
public class WechatTradeBillSuccessAction implements WechatTradeAction<TradeBillSuccessModel, TradeBillSuccessResponseModel> {

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/downloadbill";
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
	public Class<TradeBillSuccessModel> getRequestType() {
		return TradeBillSuccessModel.class;
	}

	@Override
	public Class<TradeBillSuccessResponseModel> getResponseType() {
		return TradeBillSuccessResponseModel.class;
	}

}
