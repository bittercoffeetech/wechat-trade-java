package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatClientConfig;
import tech.bittercoffee.wechat.api.trade.models.request.TradeRefundRequest;
import tech.bittercoffee.wechat.api.trade.models.response.HierarchyConfig;
import tech.bittercoffee.wechat.api.trade.models.response.TradeRefundCouponInfo;
import tech.bittercoffee.wechat.api.trade.models.response.TradeRefundResponse;

/**
 * 退款申请
 * 
 * @author BitterCoffee
 *
 */
public class TradeRefundAction extends AbstractTradeAction<TradeRefundRequest, TradeRefundResponse> {

	public TradeRefundAction(WechatClientConfig config) {
		super(config);
	}

	@Override
	public String url() {
		return "https://api.mch.weixin.qq.com/secapi/pay/refund";
	}

	@Override
	public boolean certificated() {
		return true;
	}

	@Override
	public Class<TradeRefundRequest> getRequestType() {
		return TradeRefundRequest.class;
	}

	@Override
	public Class<TradeRefundResponse> getResponseType() {
		return TradeRefundResponse.class;
	}

	@Override
	public HierarchyConfig[] hierarchy() {
		return new HierarchyConfig[] {
				new HierarchyConfig("coupon", TradeRefundCouponInfo.class, "coupon_refund_count") };
	}
}
