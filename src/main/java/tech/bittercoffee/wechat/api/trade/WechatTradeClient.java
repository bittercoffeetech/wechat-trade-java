package tech.bittercoffee.wechat.api.trade;

import java.io.InputStream;
import java.time.LocalDate;

import tech.bittercoffee.wechat.api.trade.actions.TradeBillAllAction;
import tech.bittercoffee.wechat.api.trade.actions.TradeBillRefundAction;
import tech.bittercoffee.wechat.api.trade.actions.TradeBillSuccessAction;
import tech.bittercoffee.wechat.api.trade.actions.TradeCloseAction;
import tech.bittercoffee.wechat.api.trade.actions.TradeCreateAction;
import tech.bittercoffee.wechat.api.trade.actions.TradeCreateNotification;
import tech.bittercoffee.wechat.api.trade.actions.TradeFundflowAction;
import tech.bittercoffee.wechat.api.trade.actions.TradeQueryAction;
import tech.bittercoffee.wechat.api.trade.actions.TradeRefundAction;
import tech.bittercoffee.wechat.api.trade.actions.TradeRefundNotification;
import tech.bittercoffee.wechat.api.trade.actions.TradeRefundQueryAction;
import tech.bittercoffee.wechat.api.trade.models.request.TradeBillAllRequest;
import tech.bittercoffee.wechat.api.trade.models.request.TradeBillRefundRequest;
import tech.bittercoffee.wechat.api.trade.models.request.TradeBillSuccessRequest;
import tech.bittercoffee.wechat.api.trade.models.request.TradeCloseRequest;
import tech.bittercoffee.wechat.api.trade.models.request.TradeCreateRequest;
import tech.bittercoffee.wechat.api.trade.models.request.TradeFundflowRequest;
import tech.bittercoffee.wechat.api.trade.models.request.TradeQueryRequest;
import tech.bittercoffee.wechat.api.trade.models.request.TradeRefundQueryRequest;
import tech.bittercoffee.wechat.api.trade.models.request.TradeRefundRequest;
import tech.bittercoffee.wechat.api.trade.models.response.TradeBillAllResponse;
import tech.bittercoffee.wechat.api.trade.models.response.TradeBillRefundResponse;
import tech.bittercoffee.wechat.api.trade.models.response.TradeBillSuccessResponse;
import tech.bittercoffee.wechat.api.trade.models.response.TradeCloseResponse;
import tech.bittercoffee.wechat.api.trade.models.response.TradeCreateNotify;
import tech.bittercoffee.wechat.api.trade.models.response.TradeCreateResponse;
import tech.bittercoffee.wechat.api.trade.models.response.TradeFundflowResponse;
import tech.bittercoffee.wechat.api.trade.models.response.TradeQueryResponse;
import tech.bittercoffee.wechat.api.trade.models.response.TradeRefundNotify;
import tech.bittercoffee.wechat.api.trade.models.response.TradeRefundQueryResponse;
import tech.bittercoffee.wechat.api.trade.models.response.TradeRefundResponse;

/**
 * 微信支付客户端的默认实现
 * 
 * @author BitterCoffee
 *
 */
public final class WechatTradeClient {
	private WechatClientConfig config;

	/**
	 * @param appId   应用ID
	 * @param mchId   商户ID
	 * @param mchKey  商户秘钥
	 * @param apiCert 客户端证书
	 * 
	 * 创建微信支付客户端对象
	 */
	public WechatTradeClient(String appId, String mchId, String mchKey, InputStream apiCert) {
		this.config = new WechatClientConfig(appId, mchId, mchKey, apiCert);
	}

	public TradeCreateNotify onCreateNotifier(InputStream xml) throws WechatApiException {
		return new TradeCreateNotification(config).execute(xml);
	}

	public TradeRefundNotify onRefundNotifier(InputStream xml) throws WechatApiException {
		return new TradeRefundNotification(config).execute(xml);
	}

	public TradeCreateResponse createTrade(final TradeCreateRequest model) throws WechatApiException {
		return new TradeCreateAction(config).execute(model);
	}

	public TradeQueryResponse queryTrade(TradeQueryRequest model) throws WechatApiException {
		return new TradeQueryAction(config).execute(model);
	}

	public TradeQueryResponse queryTrade(String outTradeNo) throws WechatApiException {
		return new TradeQueryAction(config).execute(TradeQueryRequest.withTradeNo(outTradeNo));
	}

	public TradeCloseResponse closeTrade(TradeCloseRequest model) throws WechatApiException {
		return new TradeCloseAction(config).execute(model);
	}

	public TradeCloseResponse closeTrade(String outTradeNo) throws WechatApiException {
		return new TradeCloseAction(config).execute(TradeCloseRequest.withTradeNo(outTradeNo));
	}

	public TradeRefundResponse createRefund(TradeRefundRequest model) throws WechatApiException {
		return new TradeRefundAction(config).execute(model);
	}

	public TradeRefundResponse createRefund(String tradeNo, long totalFee, long refundFeel) throws WechatApiException {
		return new TradeRefundAction(config).execute(TradeRefundRequest.withTradeNo(tradeNo, totalFee, refundFeel));
	}

	public TradeRefundQueryResponse queryRefund(TradeRefundQueryRequest model) throws WechatApiException {
		return new TradeRefundQueryAction(config).execute(model);
	}

	public TradeRefundQueryResponse queryRefund(String outTradeNo) throws WechatApiException {
		return new TradeRefundQueryAction(config).execute(TradeRefundQueryRequest.withTradeNo(outTradeNo));
	}

	public TradeRefundQueryResponse queryRefund(String outTradeNo, String refundNo) throws WechatApiException {
		return new TradeRefundQueryAction(config)
				.execute(TradeRefundQueryRequest.withTradeNo(outTradeNo).refundNo(refundNo));
	}

	public TradeBillAllResponse downloadBillAll(TradeBillAllRequest model) throws WechatApiException {
		return new TradeBillAllAction(config).execute(model);
	}

	public TradeBillAllResponse downloadBillAll(LocalDate billDate, boolean zip) throws WechatApiException {
		return new TradeBillAllAction(config).execute(TradeBillAllRequest.of(billDate, zip));
	}

	public TradeBillSuccessResponse downloadBillSuccess(TradeBillSuccessRequest model) throws WechatApiException {
		return new TradeBillSuccessAction(config).execute(model);
	}

	public TradeBillSuccessResponse downloadBillSuccess(LocalDate billDate, boolean zip) throws WechatApiException {
		return new TradeBillSuccessAction(config).execute(TradeBillSuccessRequest.of(billDate, zip));
	}

	public TradeBillRefundResponse downloadBillRefund(TradeBillRefundRequest model) throws WechatApiException {
		return new TradeBillRefundAction(config).execute(model);
	}

	public TradeBillRefundResponse downloadBillRefund(LocalDate billDate, boolean zip) throws WechatApiException {
		return new TradeBillRefundAction(config).execute(TradeBillRefundRequest.of(billDate, zip));
	}

	public TradeFundflowResponse downloadFundflow(TradeFundflowRequest model) throws WechatApiException {
		return new TradeFundflowAction(config).execute(model);
	}

	public TradeFundflowResponse downloadFundflow(LocalDate billDate, boolean zip) throws WechatApiException {
		return new TradeFundflowAction(config).execute(TradeFundflowRequest.of(billDate, zip));
	}
}
