package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeSignatureModel;

/**
 * 下载所有交易账单
 * 
 * @author Bob
 *
 */
public abstract class WechatTradeBillAction<R extends TradeSignatureModel, S> implements WechatTradeAction<R, S> {
	
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
	public boolean isStreaming() {
		return true;
	}

}
