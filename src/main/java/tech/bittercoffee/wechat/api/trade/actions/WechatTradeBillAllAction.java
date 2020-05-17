package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeCsvAction;
import tech.bittercoffee.wechat.api.trade.models.TradeBillAllInfo;
import tech.bittercoffee.wechat.api.trade.models.TradeBillAllModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillSummaryInfo;

/**
 * 下载所有交易账单
 * 
 * @author Bob
 *
 */
public class WechatTradeBillAllAction implements WechatTradeCsvAction<TradeBillAllModel, TradeBillSummaryInfo, TradeBillAllInfo> {
	
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

}
