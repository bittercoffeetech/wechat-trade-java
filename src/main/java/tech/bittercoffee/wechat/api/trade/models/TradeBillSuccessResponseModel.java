package tech.bittercoffee.wechat.api.trade.models;

public class TradeBillSuccessResponseModel extends TradeCsvResponseModel<TradeBillSummaryInfo, TradeBillSuccessInfo> {

	private static final long serialVersionUID = -1636304450877901306L;

	@Override
	public Class<TradeBillSummaryInfo> getSummaryType() {
		return TradeBillSummaryInfo.class;
	}

	@Override
	public Class<TradeBillSuccessInfo> getRecordType() {
		return TradeBillSuccessInfo.class;
	}


}
