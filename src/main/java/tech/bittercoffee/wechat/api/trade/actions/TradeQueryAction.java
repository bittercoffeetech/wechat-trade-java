package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatClientConfig;
import tech.bittercoffee.wechat.api.trade.models.request.TradeQueryRequest;
import tech.bittercoffee.wechat.api.trade.models.response.HierarchyConfig;
import tech.bittercoffee.wechat.api.trade.models.response.TradeCouponInfo;
import tech.bittercoffee.wechat.api.trade.models.response.TradeQueryResponse;

/**
 * 订单查询
 * 
 * @author BitterCoffee
 *
 */
public final class TradeQueryAction extends AbstractTradeAction<TradeQueryRequest, TradeQueryResponse> {

	public TradeQueryAction(WechatClientConfig config) {
		super(config);
	}

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/orderquery";
	}

	@Override
	public Class<TradeQueryRequest> getRequestType() {
		return TradeQueryRequest.class;
	}

	@Override
	public Class<TradeQueryResponse> getResponseType() {
		return TradeQueryResponse.class;
	}

	@Override
	public HierarchyConfig[] hierarchy() {
		return new HierarchyConfig[] { new HierarchyConfig("coupon", TradeCouponInfo.class, "coupon_count") };
	}

}
