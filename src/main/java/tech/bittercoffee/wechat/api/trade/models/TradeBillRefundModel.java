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
 * 下载退款交易账单
 * 
 * @author Bob
 *
 */
@JsonRootName("bill")
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public final class TradeBillRefundModel extends TradeCsvlModel {

	private static final long serialVersionUID = -198191473007581123L;
	
	public static TradeBillRefundModel at(LocalDate billDate) {
		TradeBillRefundModel model = new TradeBillRefundModel();
		model.billDate = billDate;
		
		return model;
	}
	
	public TradeBillRefundModel withZip() {
		this.tarType = TarTypeEnum.GZIP;
		return this;
	}

	/**
	 * 账单类型
	 */
	@JsonProperty("bill_type")
	@JacksonXmlCData
	private BillTypeEnum billType = BillTypeEnum.REFUND;

	public BillTypeEnum getBillType() {
		return billType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
