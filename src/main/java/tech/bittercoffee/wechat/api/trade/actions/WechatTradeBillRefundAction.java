package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.models.TradeBillRefundModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillRefundResponseModel;

/**
 * 下载退款交易账单
 * 
 * @author Bob
 *
 */
public class WechatTradeBillRefundAction implements WechatTradeAction<TradeBillRefundModel, TradeBillRefundResponseModel> {

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
	public Class<TradeBillRefundModel> getRequestType() {
		return TradeBillRefundModel.class;
	}

	@Override
	public Class<TradeBillRefundResponseModel> getResponseType() {
		return TradeBillRefundResponseModel.class;
	}

}
