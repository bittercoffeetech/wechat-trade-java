package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeQueryModel;
import tech.bittercoffee.wechat.api.trade.models.TradeQueryResponseModel;

/**
 * 订单查询
 * 
 * @author Bob
 *
 */
public final class WechatTradeQueryAction implements WechatTradeAction<TradeQueryModel, TradeQueryResponseModel> {

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/orderquery";
	}

}
