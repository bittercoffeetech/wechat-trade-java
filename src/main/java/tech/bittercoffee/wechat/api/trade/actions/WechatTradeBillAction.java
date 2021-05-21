package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeSignatureInfo;

/**
 * 下载交易账单
 * 
 * @author BitterCoffee
 *
 */
public abstract class WechatTradeBillAction<R extends TradeSignatureInfo, S> implements WechatTradeAction<R, S> {
	
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
