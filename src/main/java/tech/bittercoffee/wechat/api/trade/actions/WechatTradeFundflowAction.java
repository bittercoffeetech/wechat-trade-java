package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.WechatTradeCsvAction;
import tech.bittercoffee.wechat.api.trade.enums.SignTypeEnum;
import tech.bittercoffee.wechat.api.trade.models.TradeFundflowInfo;
import tech.bittercoffee.wechat.api.trade.models.TradeFundflowModel;
import tech.bittercoffee.wechat.api.trade.models.TradeFundflowSummaryInfo;

/**
 * 下载资金账单
 * 
 * @author Bob
 *
 */
public class WechatTradeFundflowAction implements WechatTradeCsvAction<TradeFundflowModel, TradeFundflowSummaryInfo, TradeFundflowInfo> {

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

}
