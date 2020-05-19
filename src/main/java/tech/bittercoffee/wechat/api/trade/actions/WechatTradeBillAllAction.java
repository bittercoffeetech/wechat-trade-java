package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeBillAllModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillAllResponseModel;

/**
 * 下载所有交易账单
 * 
 * @author Bob
 *
 */
public class WechatTradeBillAllAction implements WechatTradeAction<TradeBillAllModel, TradeBillAllResponseModel> {
	
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
	public Class<TradeBillAllModel> getRequestType() {
		return TradeBillAllModel.class;
	}

	@Override
	public Class<TradeBillAllResponseModel> getResponseType() {
		return TradeBillAllResponseModel.class;
	}

}
