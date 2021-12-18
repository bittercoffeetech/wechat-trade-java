package tech.bittercoffee.wechat.api.trade.actions;

import tech.bittercoffee.wechat.api.trade.enums.SignTypeEnum;
import tech.bittercoffee.wechat.api.trade.models.response.HierarchyConfig;

/**
 * 接口返回内容定义
 * 
 * @author BitterCoffee
 *
 * @param <S> 返回对象类型
 */
public interface TradeResponse<S> {

	/**
	 * 获取返回对象的类型信息
	 * 
	 * @return 返回对象类型
	 */
	Class<? extends S> getResponseType();

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
	default HierarchyConfig[] hierarchy() {
		return new HierarchyConfig[] {};
	}

	/**
	 * @return 签名算法
	 */
	default SignTypeEnum responseSignType() {
		return SignTypeEnum.MD5;
	}

	default boolean isStreaming() {
		return false;
	}

}
