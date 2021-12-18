package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatClientConfig;
import tech.bittercoffee.wechat.api.trade.models.response.HierarchyConfig;
import tech.bittercoffee.wechat.api.trade.models.response.TradeCouponInfo;
import tech.bittercoffee.wechat.api.trade.models.response.TradeCreateNotify;

/**
 * 支付结果通知
 * 
 * @author BitterCoffee
 *
 */
public class TradeCreateNotification extends AbstractTradeNotification<TradeCreateNotify> {

	public TradeCreateNotification(WechatClientConfig config) {
		super(config);
	}

	@Override
	public Class<TradeCreateNotify> getResponseType() {
		return TradeCreateNotify.class;
	}

	@Override
	public HierarchyConfig[] hierarchy() {
		return new HierarchyConfig[] { new HierarchyConfig("coupon", TradeCouponInfo.class, "coupon_count") };
	}

}
