package tech.bittercoffee.wechat.api.trade.actions;

import java.io.InputStream;

import tech.bittercoffee.wechat.api.trade.WechatApiException;
import tech.bittercoffee.wechat.api.trade.WechatClientConfig;

/**
 * 微信接口封装
 * 
 * @author BitterCoffee
 *
 * @param <S> 返回对象类型
 */
public abstract class AbstractTradeNotification<S> extends AbstractTradeResponsive<S> {

	protected AbstractTradeNotification(WechatClientConfig config) {
		super(config);
	}

	public S execute(InputStream input) throws WechatApiException {
		return forXml.apply(input);
	}

}
