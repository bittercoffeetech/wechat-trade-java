package tech.bittercoffee.wechat.api.trade.models;

public class TradeBillRefundResponseModel extends TradeCsvResponseModel<TradeBillSummaryInfo, TradeBillRefundInfo> {

	private static final long serialVersionUID = 257730504925921681L;

	@Override
	public Class<TradeBillSummaryInfo> getSummaryType() {
		return TradeBillSummaryInfo.class;
	}

	@Override
	public Class<TradeBillRefundInfo> getRecordType() {
		return TradeBillRefundInfo.class;
	}


}
