package tech.bittercoffee.wechat.api.trade.models.response;

/**
 * 下载成功交易账单返回
 * 
 * @author BitterCoffee
 *
 */
public class TradeBillSuccessResponse extends TradeSheetResponse<TradeBillSummaryInfo, TradeBillSuccessInfo> {

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
