package tech.bittercoffee.wechat.api.trade.actions;

import com.google.common.collect.ImmutableList;

import tech.bittercoffee.wechat.api.trade.WechatClientConfig;
import tech.bittercoffee.wechat.api.trade.models.request.TradeRefundQueryRequest;
import tech.bittercoffee.wechat.api.trade.models.response.HierarchyConfig;
import tech.bittercoffee.wechat.api.trade.models.response.TradeRefundCouponInfo;
import tech.bittercoffee.wechat.api.trade.models.response.TradeRefundInfo;
import tech.bittercoffee.wechat.api.trade.models.response.TradeRefundQueryResponse;

/**
 * 退款查询
 * 
 * @author BitterCoffee
 *
 */
public class TradeRefundQueryAction extends AbstractTradeAction<TradeRefundQueryRequest, TradeRefundQueryResponse> {

	public TradeRefundQueryAction(WechatClientConfig config) {
		super(config);
	}

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/pay/refundquery";
	}

	@Override
	public Class<TradeRefundQueryRequest> getRequestType() {
		return TradeRefundQueryRequest.class;
	}

	@Override
	public Class<TradeRefundQueryResponse> getResponseType() {
		return TradeRefundQueryResponse.class;
	}

	@Override
	public HierarchyConfig[] hierarchy() {
		return new HierarchyConfig[] { new HierarchyConfig("refund", TradeRefundInfo.class, "refund_count",
				ImmutableList.of(new HierarchyConfig("coupon", TradeRefundCouponInfo.class, "coupon_refund_count"))) };
	}

}
