package test.tech.bittercoffee.wechat.api.trade;

import java.time.LocalDateTime;

import org.junit.Test;

import tech.bittercoffee.wechat.api.trade.WechatApiException;
import tech.bittercoffee.wechat.api.trade.WechatTradeClient;
import tech.bittercoffee.wechat.api.trade.enums.FeeTypeEnum;
import tech.bittercoffee.wechat.api.trade.models.TradeCreateModel;
import tech.bittercoffee.wechat.api.trade.models.TradeSceneInfo;

public class CreateTest {
		
	@Test
	public void test() throws WechatApiException {
		WechatTradeClient client = new WechatTradeClient("wx8949c222019862f5","1234539902","a10add3849ba56abbe56e056f20f883f", null);
		
	    TradeCreateModel m = new TradeCreateModel();
	    m.setBody("押金支付");
	    m.setSpbillCreateIp("39.100.125.239");
	    m.setReceipt(true);
	    m.setOpenId("oenOB4h6FmZXYSlSkCJBle5nJEoo");
	    m.setFeeType(FeeTypeEnum.CNY);
	    m.setNotifyUrl("https://bike.yxbhlt.cn:9999/api/v1/wechat/pay/notify");
	    m.setTotalFee(30000);
	    m.setTimeStart(LocalDateTime.now());
	    m.setTimeExpire(LocalDateTime.now());
	    m.setSceneInfo(new TradeSceneInfo("111","ZH","quan1","tj"));
	    m.setTotalFee(22230000);
	    
	    client.newCreateAction().withModel(m).execute();
		
	}

}
