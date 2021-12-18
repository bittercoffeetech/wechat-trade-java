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
 * @author BitterCoffee
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TradeSheetRequest extends TradeSignatureInfo {

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

	public TradeSheetRequest(LocalDate billDate, TarTypeEnum tarType) {
		this.billDate = billDate;
		this.tarType = tarType;
	}
	
	public LocalDate getBillDate() {
		return billDate;
	}

	public TarTypeEnum getTarType() {
		return tarType;
	}
}
