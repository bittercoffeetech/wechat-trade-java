package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeAction;
import tech.bittercoffee.wechat.api.trade.enums.SignTypeEnum;
import tech.bittercoffee.wechat.api.trade.models.TradeFundflowModel;
import tech.bittercoffee.wechat.api.trade.models.TradeFundflowResponseModel;

/**
 * 下载资金账单
 * 
 * @author Bob
 *
 */
public class WechatTradeFundflowAction implements WechatTradeAction<TradeFundflowModel, TradeFundflowResponseModel> {

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/downloadfundflow";
	}

	@Override
	public SignTypeEnum responseSignType() {
		return SignTypeEnum.HMAC_SHA256;
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

	@Override
	public Class<TradeFundflowModel> getRequestType() {
		return TradeFundflowModel.class;
	}

	@Override
	public Class<TradeFundflowResponseModel> getResponseType() {
		return TradeFundflowResponseModel.class;
	}

}
