package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeCsvAction;
import tech.bittercoffee.wechat.api.trade.models.TradeBillRefundInfo;
import tech.bittercoffee.wechat.api.trade.models.TradeBillRefundModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillSummaryInfo;

/**
 * 下载退款交易账单
 * 
 * @author Bob
 *
 */
public class WechatTradeBillRefundAction implements WechatTradeCsvAction<TradeBillRefundModel, TradeBillSummaryInfo, TradeBillRefundInfo> {

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
