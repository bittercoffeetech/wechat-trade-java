package tech.bittercoffee.wechat.api.trade.models.request;

import java.time.LocalDate;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import tech.bittercoffee.wechat.api.trade.enums.AccountTypeEnum;
import tech.bittercoffee.wechat.api.trade.enums.TarTypeEnum;

/**
 * 下载资金账单请求
 * 
 * @author BitterCoffee
 *
 */
@JsonRootName("fundflow")
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public final class TradeFundflowRequest extends TradeSheetRequest {

	private static final long serialVersionUID = -198191473007581123L;

	public static TradeFundflowRequest of(LocalDate billDate, boolean zip) {
		return new TradeFundflowRequest(billDate, zip ? TarTypeEnum.GZIP : null);
	}

	@JsonCreator
	public TradeFundflowRequest(@JsonProperty("bill_date") LocalDate billDate,
			@JsonProperty("tar_type") TarTypeEnum tarType) {
		super(billDate, tarType);
	}

	/**
	 * 资金账户类型
	 */
	@JsonProperty("account_type")
	@JacksonXmlCData
	private AccountTypeEnum accountType;

	public TradeFundflowRequest basic() {
		this.accountType = AccountTypeEnum.BASIC;
		return this;
	}

	public TradeFundflowRequest fees() {
		this.accountType = AccountTypeEnum.FEES;
		return this;
	}

	public TradeFundflowRequest operation() {
		this.accountType = AccountTypeEnum.OPERATION;
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
