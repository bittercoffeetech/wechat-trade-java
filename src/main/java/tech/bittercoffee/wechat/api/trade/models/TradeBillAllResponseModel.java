package tech.bittercoffee.wechat.api.trade.models;

public class TradeBillAllResponseModel extends TradeCsvResponseModel<TradeBillSummaryInfo, TradeBillAllInfo> {

	private static final long serialVersionUID = -6565571196512780017L;

	@Override
	public Class<TradeBillSummaryInfo> getSummaryType() {
		return TradeBillSummaryInfo.class;
	}

	@Override
	public Class<TradeBillAllInfo> getRecordType() {
		return TradeBillAllInfo.class;
	}

}
