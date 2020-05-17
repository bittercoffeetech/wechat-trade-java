package test.tech.bittercoffee.wechat.api.trade;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tech.bittercoffee.wechat.api.trade.WechatApiException;
import tech.bittercoffee.wechat.api.trade.WechatTradeClient;
import tech.bittercoffee.wechat.api.trade.enums.TarTypeEnum;
import tech.bittercoffee.wechat.api.trade.models.TradeBillAllInfo;
import tech.bittercoffee.wechat.api.trade.models.TradeBillAllModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillSummaryInfo;
import tech.bittercoffee.wechat.api.trade.models.TradeCsvResponseModel;

class BillTest {
	
	private WechatTradeClient client = new WechatTradeClient("wx8949c222019862f5", "1234539902", "a10add3849ba56abbe56e056f20f883f", null);

	@Test
	void testDownloadBillInvalidDate() {
		try {
			TradeBillAllModel model = new TradeBillAllModel();
			model.setBillDate(LocalDate.of(2021, 5, 15));
			client.newBillAllAction().withModel(model).execute();
		} catch (WechatApiException e) {
			Assertions.assertEquals("FAIL", e.getCode());
		}
	}
	
	@Test
	void testDownloadBillZip() throws WechatApiException {
		TradeBillAllModel model = new TradeBillAllModel();
		model.setBillDate(LocalDate.of(2020, 5, 15));
		model.setTarType(TarTypeEnum.GZIP);
		TradeCsvResponseModel<TradeBillSummaryInfo, TradeBillAllInfo> result = client.newBillAllAction().withModel(model).execute();
		
		Assertions.assertNotNull(result.getRecords());
		Assertions.assertTrue(!result.getRecords().isEmpty(), "Has records.");
	}
	
	@Test
	void testDownloadBillNoZip() throws WechatApiException {
		TradeBillAllModel model = new TradeBillAllModel();
		model.setBillDate(LocalDate.of(2020, 5, 15));
		TradeCsvResponseModel<TradeBillSummaryInfo, TradeBillAllInfo> result = client.newBillAllAction().withModel(model).execute();
		
		Assertions.assertNotNull(result.getRecords());
		Assertions.assertTrue(!result.getRecords().isEmpty(), "Has records.");
	}
	
}