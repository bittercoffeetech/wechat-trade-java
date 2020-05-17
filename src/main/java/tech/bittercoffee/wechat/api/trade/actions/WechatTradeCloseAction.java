package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeCloseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeCloseResponseModel;

/**
 * 关闭订单
 * 
 * @author Bob
 *
 */
public class WechatTradeCloseAction implements WechatTradeAction<TradeCloseModel, TradeCloseResponseModel> {

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/closeorder";
	}

	@Override
	public boolean hasHierarchy() {
		return false;
	}
}
