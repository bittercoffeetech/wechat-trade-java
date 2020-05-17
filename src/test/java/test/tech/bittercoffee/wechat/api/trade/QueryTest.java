package test.tech.bittercoffee.wechat.api.trade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tech.bittercoffee.wechat.api.trade.WechatApiException;
import tech.bittercoffee.wechat.api.trade.WechatTradeClient;
import tech.bittercoffee.wechat.api.trade.models.TradeQueryModel;
import tech.bittercoffee.wechat.api.trade.models.TradeQueryResponseModel;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundQueryModel;
import tech.bittercoffee.wechat.api.trade.models.TradeRefundQueryResponseModel;

class QueryTest {
	
	private WechatTradeClient client = new WechatTradeClient("wx8949c222019862f5", "1234539902", "a10add3849ba56abbe56e056f20f883f", null);

	@Test
	void testQuery() throws WechatApiException {
		TradeQueryModel model = new TradeQueryModel();
		model.setTradeNo("90013580520959892632499715588959");
		TradeQueryResponseModel result = client.newQueryAction().withModel(model).execute();
		
		Assertions.assertEquals("90013580520959892632499715588959", result.getTradeNo());
	}
	
	@Test
	void testRefundQuery() throws WechatApiException {
		TradeRefundQueryModel model = new TradeRefundQueryModel();
		model.setRefundNo("60001048329707564425329177928751");
		TradeRefundQueryResponseModel result = client.newRefundQueryAction().withModel(model).execute();
		
		Assertions.assertEquals(1, result.getRefunds().size());
	}
	
	
}
