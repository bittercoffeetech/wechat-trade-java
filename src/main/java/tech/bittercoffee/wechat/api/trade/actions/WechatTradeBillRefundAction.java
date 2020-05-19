package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.models.TradeBillRefundModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillRefundResponseModel;

/**
 * 下载退款交易账单
 * 
 * @author Bob
 *
 */
public class WechatTradeBillRefundAction extends WechatTradeBillAction<TradeBillRefundModel, TradeBillRefundResponseModel> {

	@Override
	public Class<TradeBillRefundModel> getRequestType() {
		return TradeBillRefundModel.class;
	}

	@Override
	public Class<TradeBillRefundResponseModel> getResponseType() {
		return TradeBillRefundResponseModel.class;
	}

}
