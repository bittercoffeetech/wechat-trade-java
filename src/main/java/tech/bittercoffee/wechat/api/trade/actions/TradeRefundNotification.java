package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatClientConfig;
import tech.bittercoffee.wechat.api.trade.models.response.TradeRefundNotify;

/**
 * 退款结果通知
 * 
 * @author BitterCoffee
 *
 */
public class TradeRefundNotification extends AbstractTradeNotification<TradeRefundNotify> {

	public TradeRefundNotification(WechatClientConfig config) {
		super(config);
	}

	@Override
	public String encrypted() {
		return "req_info";
	}

	@Override
	public boolean hasResult() {
		return false;
	}

	@Override
	public boolean hasSigned() {
		return false;
	}

	@Override
	public Class<TradeRefundNotify> getResponseType() {
		return TradeRefundNotify.class;
	}
}
