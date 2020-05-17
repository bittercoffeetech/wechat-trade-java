package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeCsvAction;
import tech.bittercoffee.wechat.api.trade.models.TradeBillSuccessInfo;
import tech.bittercoffee.wechat.api.trade.models.TradeBillSuccessModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillSummaryInfo;

/**
 * 下载成功交易账单
 * 
 * @author Bob
 *
 */
public class WechatTradeBillSuccessAction implements WechatTradeCsvAction<TradeBillSuccessModel, TradeBillSummaryInfo, TradeBillSuccessInfo> {

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
