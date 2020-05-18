package test.tech.bittercoffee.wechat.api.trade;

import java.time.LocalDateTime;

import org.junit.Test;

import tech.bittercoffee.wechat.api.trade.WechatApiException;
import tech.bittercoffee.wechat.api.trade.WechatTradeClient;
import tech.bittercoffee.wechat.api.trade.enums.FeeTypeEnum;
import tech.bittercoffee.wechat.api.trade.enums.TradeTypeEnum;
import tech.bittercoffee.wechat.api.trade.models.TradeCreateModel;
import tech.bittercoffee.wechat.api.trade.models.TradeSceneInfo;

public class CreateTest {
		
	@Test
	public void test() throws WechatApiException {
		WechatTradeClient client = new WechatTradeClient("wx8949c222019862f5","1234539902","a10add3849ba56abbe56e056f20f883f", null);
	    
	    client.newCreateAction().withModel(TradeCreateModel.newOrder(TradeTypeEnum.JSAPI, 100, "押金支付")
	    		.spbillCreateIp("39.100.125.239")
		    .receipt(true)
		    .openId("oenOB4h6FmZXYSlSkCJBle5nJEoo")
		    .feeType(FeeTypeEnum.CNY)
		    .notifyUrl("https://bike.yxbhlt.cn:9999/api/v1/wechat/pay/notify")
		    .timeStart(LocalDateTime.now())
		    .timeExpire(LocalDateTime.now())
		    .sceneInfo(new TradeSceneInfo("111","ZH","quan1","tj"))
	    		).execute();
		
	}

}
