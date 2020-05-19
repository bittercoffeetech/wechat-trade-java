package tech.bittercoffee.wechat.api.trade.models;

public class TradeFundflowResponseModel extends TradeCsvResponseModel<TradeFundflowSummaryInfo, TradeFundflowInfo> {

	private static final long serialVersionUID = -6255447743096773519L;

	@Override
	public Class<TradeFundflowSummaryInfo> getSummaryType() {
		return TradeFundflowSummaryInfo.class;
	}

	@Override
	public Class<TradeFundflowInfo> getRecordType() {
		return TradeFundflowInfo.class;
	}

}
