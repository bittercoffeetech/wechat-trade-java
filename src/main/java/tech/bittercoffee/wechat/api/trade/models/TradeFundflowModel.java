package tech.bittercoffee.wechat.api.trade.models;

import java.time.LocalDate;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import tech.bittercoffee.wechat.api.trade.enums.AccountTypeEnum;
import tech.bittercoffee.wechat.api.trade.enums.TarTypeEnum;

/**
 * 下载资金账单
 * 
 * @author Bob
 *
 */
@JsonRootName("fundflow")
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public final class TradeFundflowModel extends TradeCsvlModel {

	private static final long serialVersionUID = -198191473007581123L;
	
	public static TradeFundflowModel at(LocalDate billDate) {
		TradeFundflowModel model = new TradeFundflowModel();
		model.billDate = billDate;
		
		return model;
	}
	
	public TradeFundflowModel withZip() {
		this.tarType = TarTypeEnum.GZIP;
		return this;
	}

	/**
	 * 资金账户类型
	 */
	@JsonProperty("account_type")
	@JacksonXmlCData
	private AccountTypeEnum accountType;
	
	public TradeFundflowModel accountType(AccountTypeEnum accountType) {
		this.accountType = accountType;
		
		return this;
	}

	public AccountTypeEnum getAccountType() {
		return accountType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
