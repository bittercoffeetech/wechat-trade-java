package tech.bittercoffee.wechat.api.trade;

import static org.apache.commons.lang3.reflect.TypeUtils.getTypeArguments;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map.Entry;

import tech.bittercoffee.wechat.api.trade.models.TradeCsvResponseModel;

import java.util.Optional;

/**
 * CSV接口返回对象
 * 
 * @author Bob
 *
 * @param <M> 汇总对象类型
 * @param <D> 明细对象类型
 */
public interface WechatTradeCsvResponse<M, D> extends WechatTradeResponse<TradeCsvResponseModel<M, D>> {

	@SuppressWarnings("unchecked")
	default Class<M> getSummaryType() {
		Optional<Entry<TypeVariable<?>, Type>> found =  getTypeArguments(getClass(), WechatTradeResponse.class).entrySet()
				.stream()
				.filter(e -> "M".equals(e.getKey().getName()))
				.findFirst();
			return found.isPresent() ? (Class<M>) found.get().getValue() : null;
	}

	@SuppressWarnings("unchecked")
	default Class<D> getRecordType() {
		Optional<Entry<TypeVariable<?>, Type>> found =  getTypeArguments(getClass(), WechatTradeResponse.class).entrySet()
			.stream()
			.filter(e -> "D".equals(e.getKey().getName()))
			.findFirst();
		return found.isPresent() ? (Class<D>) found.get().getValue() : null;
	}
	
}
