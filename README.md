# 微信支付客户端封装

## 统一下单

```java
WechatTradeClient client = new WechatTradeClient("app id", "mch id", "mch key", "证书文件 Stream");
	    
client.newCreateAction().withModel(TradeCreateModel.newOrder(TradeTypeEnum.JSAPI, 100, "押金支付")
    .spbillCreateIp("<id address>")
    .receipt(true)
    .openId("<open id>")
    .feeType(FeeTypeEnum.CNY)
    .notifyUrl("https://<ip>:<port>/wechat/pay/notify")
    .timeStart(LocalDateTime.now())
    .timeExpire(LocalDateTime.now())
    .sceneInfo(new TradeSceneInfo("111","ZH","quan1","tj"))
  ).execute();
```

## 订单查询

```java

WechatTradeClient client = new WechatTradeClient("app id", "mch id", "mch key", "证书文件 Stream");

TradeQueryResponseModel result = client.newQueryAction()
        .withModel(TradeQueryModel.withTradeNo("90013580520959892632499715588959"))
        .execute();


```