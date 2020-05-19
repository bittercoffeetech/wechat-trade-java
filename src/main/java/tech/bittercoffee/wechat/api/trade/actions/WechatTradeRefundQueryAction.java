package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundQueryModel;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundQueryResponseModel;

/**
 * 退款查询
 * 
 * @author Bob
 *
 */
public class WechatTradeRefundQueryAction implements WechatTradeAction<TradeRefundQueryModel, TradeRefundQueryResponseModel> {

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/refundquery";
	}

	@Override
	public Class<TradeRefundQueryModel> getRequestType() {
		return TradeRefundQueryModel.class;
	}

	@Override
	public Class<TradeRefundQueryResponseModel> getResponseType() {
		return TradeRefundQueryResponseModel.class;
	}

}
