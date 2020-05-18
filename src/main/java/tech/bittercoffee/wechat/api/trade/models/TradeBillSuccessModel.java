package tech.bittercoffee.wechat.api.trade.models;

import java.time.LocalDate;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import tech.bittercoffee.wechat.api.trade.enums.BillTypeEnum;
import tech.bittercoffee.wechat.api.trade.enums.TarTypeEnum;

/**
 * 下载成功交易账单
 * 
 * @author Bob
 *
 */
@JsonRootName("bill")
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public final class TradeBillSuccessModel extends TradeCsvlModel {

	private static final long serialVersionUID = -198191473007581123L;
	
	public static TradeBillSuccessModel at(LocalDate billDate) {
		TradeBillSuccessModel model = new TradeBillSuccessModel();
		model.billDate = billDate;
		
		return model;
	}
	
	public TradeBillSuccessModel withZip() {
		this.tarType = TarTypeEnum.GZIP;
		return this;
	}

	/**
	 * 账单类型
	 */
	@JsonProperty("bill_type")
	@JacksonXmlCData
	private BillTypeEnum billType = BillTypeEnum.SUCCESS;

	public BillTypeEnum getBillType() {
		return billType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
