package tech.bittercoffee.wechat.api.trade.models.response;

/**
 * 下载所有交易账单返回
 * 
 * @author BitterCoffee
 *
 */
public class TradeBillAllResponse extends TradeSheetResponse<TradeBillSummaryInfo, TradeBillAllInfo> {

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
