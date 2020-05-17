package tech.bittercoffee.wechat.api.trade;

import static org.apache.commons.lang3.reflect.TypeUtils.getTypeArguments;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.lang3.reflect.TypeUtils;

import tech.bittercoffee.wechat.api.trade.enums.SignTypeEnum;

/**
 * 接口返回内容定义
 * 
 * @author Bob
 *
 * @param <S> 返回对象类型
 */
public interface WechatTradeResponse<S> {

	/**
	 * 获取返回对象的类型信息
	 * 
	 * @return 返回对象类型
	 */
	@SuppressWarnings("unchecked")
	default Class<S> getResponseType() {
		Optional<Entry<TypeVariable<?>, Type>> found = getTypeArguments(getClass(), WechatTradeResponse.class).entrySet()
				.stream().filter(e -> "S".equals(e.getKey().getName())).findFirst();

		if(found.isPresent()) {
			Type notSure = found.get().getValue();
			return (notSure instanceof ParameterizedType)
					? (Class<S>) TypeUtils.getRawType(notSure, WechatTradeResponse.class)
					: (Class<S>) notSure;
		} else {
			return null;
		}
	}

	/**
	 * @return 返回值中是否包括签名字段
	 */
	default boolean hasSigned() {
		return true;
	}

	/**
	 * @return 返回值中是否业务结果信息
	 */
	default boolean hasResult() {
		return true;
	}

	/**
	 * @return 加密字段名
	 */
	default String encrypted() {
		return null;
	}

	/**
	 * @return 是否包含嵌套对象
	 */
	default boolean hasHierarchy() {
		return true;
	}
	
	/**
	 * @return 签名算法
	 */
	default SignTypeEnum responseSignType() {
		return SignTypeEnum.MD5;
	}
	
}
