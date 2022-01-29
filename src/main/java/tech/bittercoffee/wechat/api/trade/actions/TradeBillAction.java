package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatClientConfig;

/**
 * 下载交易账单
 * 
 * @author BitterCoffee
 *
 */
public abstract class TradeBillAction<R, S> extends AbstractTradeAction<R, S> {

	protected TradeBillAction(WechatClientConfig config) {
		super(config);
	}

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/downloadbill";
	}

	@Override
	public boolean hasSigned() {
		return false;
	}

}
