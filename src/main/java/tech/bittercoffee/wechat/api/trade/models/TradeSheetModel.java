package tech.bittercoffee.wechat.api.trade.models;

/**
 * 下载交易账单
 * 
 * @author Bob
 *
 */
public final class TradeSheetModel {
	
	private TradeSheetModel() {
		
	}

	public static TradeBillAllModel billAll() {
		return new TradeBillAllModel();
	}
	
	public static TradeBillRefundModel billRefund() {
		return new TradeBillRefundModel();
	}
	
	public static TradeBillSuccessModel billSuccess() {
		return new TradeBillSuccessModel();
	}
	
	public static TradeFundflowModel fundflow() {
		return new TradeFundflowModel();
	}
}
