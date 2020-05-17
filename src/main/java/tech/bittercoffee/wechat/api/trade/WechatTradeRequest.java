package tech.bittercoffee.wechat.api.trade;

import static org.apache.commons.lang3.reflect.TypeUtils.getTypeArguments;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map.Entry;
import java.util.Optional;

import tech.bittercoffee.wechat.api.trade.enums.SignTypeEnum;
import tech.bittercoffee.wechat.api.trade.models.TradeSignatureModel;

/**
 * 接口请求内容定义
 * 
 * @author Bob
 *
 * @param <R> 请求对象类型
 */
public interface WechatTradeRequest<R extends TradeSignatureModel> {
	
	/**
	 * @return 获取请求对象类型
	 */
	@SuppressWarnings("unchecked")
	default Class<R> getRequestType() {
		Optional<Entry<TypeVariable<?>, Type>> found =  getTypeArguments(getClass(), WechatTradeRequest.class).entrySet()
				.stream()
				.filter(e -> "R".equals(e.getKey().getName()))
				.findFirst();
		return found.isPresent() ? (Class<R>) found.get().getValue() : null;
	}
	
	/**
	 * @return 返回接口地址
	 */
	String url();
	
	/**
	 * @return 是否需要安全证书
	 */
	default boolean certificated() {
		return false;
	}
	
	/**
	 * @return 签名算法
	 */
	default SignTypeEnum requestSignType() {
		return SignTypeEnum.MD5;
	}
	
}
