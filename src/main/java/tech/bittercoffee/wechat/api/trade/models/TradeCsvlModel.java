package tech.bittercoffee.wechat.api.trade.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

import tech.bittercoffee.wechat.api.trade.enums.TarTypeEnum;

/**
 * 下载对账单
 * 
 * @author Bob
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TradeCsvlModel extends TradeSignatureModel {

	private static final long serialVersionUID = -198191473007581123L;

	/**
	 * 下载对账单的日期，格式：20140603
	 */
	@JsonProperty("bill_date")
	@JacksonXmlCData
	@JsonSerialize(using = CompactLocalDateSerializer.class)
	@JsonDeserialize(using = CompactLocalDateDeserializer.class)
	protected LocalDate billDate;

	/**
	 * 压缩账单
	 */
	@JsonProperty("tar_type")
	@JacksonXmlCData
	protected TarTypeEnum tarType;
	
	@SuppressWarnings("unchecked")
	public <T extends TradeCsvlModel> T at(LocalDate billDate) {
		this.billDate = billDate;
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends TradeCsvlModel> T withZip() {
		this.tarType = TarTypeEnum.GZIP;
		return (T) this;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public TarTypeEnum getTarType() {
		return tarType;
	}
}
