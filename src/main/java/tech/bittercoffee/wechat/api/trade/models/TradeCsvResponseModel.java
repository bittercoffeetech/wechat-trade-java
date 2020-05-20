package tech.bittercoffee.wechat.api.trade.models;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.common.collect.Lists;

/**
 * 对账单返回的封装类型
 * 
 * @author BitterCoffee
 *
 * @param <M> 概要对象类型
 * @param <D> 明细对象类型
 */
@JsonRootName("sheet")
public abstract class TradeCsvResponseModel<M, D> implements Serializable {

	private static final long serialVersionUID = -2944743648771800968L;

	@JsonProperty("summary")
	private M summary;

	@JsonProperty("records")
	private List<D> records = Lists.newArrayList();

	public void setSummary(M summary) {
		this.summary = summary;
	}

	public M getSummary() {
		return summary;
	}

	public List<D> getRecords() {
		return records;
	}
	
	/**
	 * 概要数据类型
	 * 
	 * @return
	 */
	public abstract Class<M> getSummaryType();
	
	/**
	 * 明细数据类型
	 * 
	 * @return
	 */
	public abstract Class<D> getRecordType();
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
