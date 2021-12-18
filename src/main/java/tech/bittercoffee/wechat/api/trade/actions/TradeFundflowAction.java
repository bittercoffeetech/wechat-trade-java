package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatClientConfig;
import tech.bittercoffee.wechat.api.trade.enums.SignTypeEnum;
import tech.bittercoffee.wechat.api.trade.models.request.TradeFundflowRequest;
import tech.bittercoffee.wechat.api.trade.models.response.TradeFundflowResponse;

/**
 * 下载资金账单
 * 
 * @author BitterCoffee
 *
 */
public class TradeFundflowAction extends AbstractTradeAction<TradeFundflowRequest, TradeFundflowResponse> {

	public TradeFundflowAction(WechatClientConfig config) {
		super(config);
	}

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/downloadfundflow";
	}

	@Override
	public boolean certificated() {
		return true;
	}

	@Override
	public SignTypeEnum requestSignType() {
		return SignTypeEnum.HMAC_SHA256;
	}

	@Override
	public boolean hasSigned() {
		return false;
	}

	@Override
	public boolean isStreaming() {
		return true;
	}

	@Override
	public Class<TradeFundflowRequest> getRequestType() {
		return TradeFundflowRequest.class;
	}

	@Override
	public Class<TradeFundflowResponse> getResponseType() {
		return TradeFundflowResponse.class;
	}

}
