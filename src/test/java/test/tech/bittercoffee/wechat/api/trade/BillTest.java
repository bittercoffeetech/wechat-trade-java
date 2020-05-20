package test.tech.bittercoffee.wechat.api.trade;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tech.bittercoffee.wechat.api.trade.WechatApiException;
import tech.bittercoffee.wechat.api.trade.WechatTradeClient;
import tech.bittercoffee.wechat.api.trade.models.TradeBillAllModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillAllResponseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillRefundModel;
import tech.bittercoffee.wechat.api.trade.models.TradeBillRefundResponseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeFundflowModel;
import tech.bittercoffee.wechat.api.trade.models.TradeFundflowResponseModel;

class BillTest {
	
	private WechatTradeClient client = new WechatTradeClient("wx8949c222019862f5", "1234539902", "a10add3849ba56abbe56e056f20f883f", 
			BillTest.class.getResourceAsStream("/data/apiclient_cert_f8692628e082.p12"));
	
	@Test
	void testDownloadBillInvalidDate() {
		try {
			client.newBillAllAction().withModel( TradeBillAllModel.of(LocalDate.of(2021, 5, 15), false) ).execute();
		} catch (WechatApiException e) {
			Assertions.assertEquals("FAIL", e.getCode());
		}
	}
	
	@Test
	void testDownloadBillZip() throws WechatApiException {
		TradeBillRefundResponseModel result = client.newBillRefundAction()
				.withModel( TradeBillRefundModel.of(LocalDate.of(2020, 5, 15), true) )
				.execute();
		
		Assertions.assertNotNull(result.getRecords());
		Assertions.assertTrue(!result.getRecords().isEmpty(), "Has records.");
	}
	
	@Test
	void testDownloadBillNoZip() throws WechatApiException {
		TradeBillAllResponseModel result = client.newBillAllAction()
				.withModel( TradeBillAllModel.of(LocalDate.of(2020, 5, 15), false) )
				.execute();
		
		Assertions.assertNotNull(result.getRecords());
		Assertions.assertTrue(!result.getRecords().isEmpty(), "Has records.");
	}
	
	@Test
	void testDownloadFundflowNoZip() throws WechatApiException {
		TradeFundflowResponseModel result = client.newFundflowAction()
				.withModel( TradeFundflowModel.of(LocalDate.of(2020, 5, 15), true).basic() )
				.execute();
		
		System.out.println(result.getSummary());
		System.out.println(result.getRecords());
		
		Assertions.assertNotNull(result.getRecords());
		Assertions.assertTrue(!result.getRecords().isEmpty(), "Has records.");
	}
	
}
