package tech.bittercoffee.wechat.api.trade.models.response;

/**
 * 下载资金账单返回
 * 
 * @author BitterCoffee
 *
 */
public class TradeFundflowResponse extends TradeSheetResponse<TradeFundflowSummaryInfo, TradeFundflowInfo> {

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
