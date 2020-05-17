package tech.bittercoffee.wechat.api.trade;

import tech.bittercoffee.wechat.api.trade.models.TradeCsvResponseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeSignatureModel;

/**
 * 可返回CSV记录的接口封装
 * 
 * @author Bob
 *
 * @param <R> 请求模型类型
 * @param <M> 汇总对象类型
 * @param <D> 明细对象类型
 */
public interface WechatTradeCsvAction<R extends TradeSignatureModel, M, D>
		extends WechatTradeAction<R, TradeCsvResponseModel<M, D>>, WechatTradeCsvResponse<M, D> {

}
