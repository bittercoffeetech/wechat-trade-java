package tech.bittercoffee.wechat.api.trade.models;

import java.time.LocalDate;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import tech.bittercoffee.wechat.api.trade.enums.BillTypeEnum;
import tech.bittercoffee.wechat.api.trade.enums.TarTypeEnum;

/**
 * 下载所有交易账单请求
 * 
 * @author BitterCoffee
 *
 */
@JsonRootName("bill")
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public final class TradeBillAllModel extends TradeCsvlModel {

	private static final long serialVersionUID = -198191473007581123L;
	
	public static TradeBillAllModel of(LocalDate billDate, boolean zip) {
		return new TradeBillAllModel(billDate, zip ? TarTypeEnum.GZIP : null);
	}
	
	@JsonCreator
	public TradeBillAllModel(@JsonProperty("bill_date") LocalDate billDate, @JsonProperty("tar_type") TarTypeEnum tarType) {
		super(billDate, tarType);
	}
	
	/**
	 * 账单类型
	 */
	@JsonProperty("bill_type")
	@JacksonXmlCData
	private BillTypeEnum billType = BillTypeEnum.ALL;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
