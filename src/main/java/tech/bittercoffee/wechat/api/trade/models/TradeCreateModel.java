package tech.bittercoffee.wechat.api.trade.models;

import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import tech.bittercoffee.wechat.api.trade.enums.FeeTypeEnum;
import tech.bittercoffee.wechat.api.trade.enums.TradeTypeEnum;

/**
 * 统一下单请求
 * 
 * @author Bob
 *
 */
@JsonRootName("trade_create")
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "xml")
public final class TradeCreateModel extends TradeSignatureModel {

	private static final long serialVersionUID = 6260044783248850548L;

	public TradeCreateModel() {
		this.tradeNo = randomStringGenerator.generate(32);
	}
	
	public static TradeCreateModel newOrder(TradeTypeEnum tradeType, long totalFee, String body) {
		TradeCreateModel model = new TradeCreateModel();
		model.tradeType = tradeType;
		model.totalFee = totalFee;
		model.body = body;
		
		return model;
	}

	/**
	 * 商户订单号 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。
	 */
	@JsonProperty("out_trade_no")
	@JacksonXmlCData
	private String tradeNo;

	/**
	 * 符合ISO 4217标准的三位字母代码，默认人民币：CNY
	 */
	@JsonProperty("fee_type")
	@JacksonXmlCData
	private FeeTypeEnum feeType = FeeTypeEnum.CNY;

	/**
	 * 标价金额 订单总金额，单位为分
	 */
	@JsonProperty("total_fee")
	@JacksonXmlCData
	private long totalFee;

	/**
	 * 设备号 自定义参数，可以为请求支付的终端设备号等
	 */
	@JsonProperty("device_info")
	@JacksonXmlCData
	private String deviceInfo;

	/**
	 * 商品描述 商品简单描述
	 */
	@JsonProperty("body")
	@JacksonXmlCData
	private String body;

	/**
	 * 附加数据 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
	 */
	@JsonProperty("attach")
	@JacksonXmlCData
	private String attach;

	/**
	 * 终端IP 支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
	 */
	@JsonProperty("spbill_create_ip")
	@JacksonXmlCData
	private String spbillCreateIp;

	/**
	 * 交易起始时间 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010
	 */
	@JsonProperty("time_start")
	@JacksonXmlCData
	@JsonSerialize(using = CompactLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CompactLocalDateTimeDeserializer.class)
	private LocalDateTime timeStart;

	/**
	 * 交易结束时间
	 * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。
	 */
	@JsonProperty("time_expire")
	@JacksonXmlCData
	@JsonSerialize(using = CompactLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CompactLocalDateTimeDeserializer.class)
	private LocalDateTime timeExpire;

	/**
	 * 订单优惠标记 订单优惠标记，使用代金券或立减优惠功能时需要的参数
	 */
	@JsonProperty("goods_tag")
	@JacksonXmlCData
	private String goodsTag;

	/**
	 * 通知地址 异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
	 */
	@JsonProperty("notify_url")
	@JacksonXmlCData
	private String notifyUrl;

	/**
	 * 交易类型
	 * 
	 * @see TradeTypeEnum
	 */
	@JsonProperty("trade_type")
	@JacksonXmlCData
	private TradeTypeEnum tradeType = TradeTypeEnum.JSAPI;

	/**
	 * 商品ID tradeType=TradeTypeEnum.NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
	 */
	@JsonProperty("product_id")
	@JacksonXmlCData
	private String productId;

	/**
	 * 指定支付方式 上传此参数no_credit--可限制用户不能使用信用卡支付
	 */
	@JsonProperty("limit_pay")
	@JacksonXmlCData
	private String limitPay;

	/**
	 * 用户标识 tradeType=TradeTypeEnum.JSAPI，此参数必传，用户在商户appid下的唯一标识。
	 */
	@JsonProperty("openid")
	@JacksonXmlCData
	private String openId;

	/**
	 * 电子发票入口开放标识 Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
	 */
	@JsonProperty("receipt")
	@JacksonXmlCData
	@JsonSerialize(using = BooleanSerializer.class)
	@JsonDeserialize(using = BooleanDeserializer.class)
	private Boolean receipt;

	/**
	 * 场景信息 该字段常用于线下活动时的场景信息上报，支持上报实际门店信息，商户也可以按需求自己上报相关信息。该字段为JSON对象数据，对象格式为:
	 * <p>
	 * {"store_info":{"id": "门店ID","name": "名称","area_code": "编码","address": "地址" }}
	 * </p>
	 * 
	 * @see TradeSceneInfo
	 */
	@JsonProperty("scene_info")
	@JacksonXmlCData
	@JsonSerialize(using = TradeSceneInfo.SceneInfoSerializer.class)
	@JsonDeserialize(using = TradeSceneInfo.SceneInfoDeserializer.class)
	private TradeSceneInfo sceneInfo;

	/**
	 * 商品详情 商品详细描述，对于使用单品优惠的商户
	 * 
	 * @see TradeGoodsDetailInfo
	 */
	@JsonProperty("detail")
	@JacksonXmlCData
	@JsonSerialize(using = TradeGoodsDetailInfo.GoodsDetailInfoSerializer.class)
	@JsonDeserialize(using = TradeGoodsDetailInfo.GoodsDetailInfoDeserializer.class)
	private TradeGoodsDetailInfo detail;

	public TradeCreateModel feeType(FeeTypeEnum feeType) {
		this.feeType = feeType;
		return this;
	}

	public TradeCreateModel totalFee(long totalFee) {
		this.totalFee = totalFee;
		return this;
	}

	public TradeCreateModel deviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
		return this;
	}

	public TradeCreateModel body(String body) {
		this.body = body;
		return this;
	}

	public TradeCreateModel attach(String attach) {
		this.attach = attach;
		return this;
	}

	public TradeCreateModel spbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
		return this;
	}

	public TradeCreateModel timeStart(LocalDateTime timeStart) {
		this.timeStart = timeStart;
		return this;
	}

	public TradeCreateModel timeExpire(LocalDateTime timeExpire) {
		this.timeExpire = timeExpire;
		return this;
	}

	public TradeCreateModel goodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
		return this;
	}

	public TradeCreateModel notifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
		return this;
	}

	public TradeCreateModel tradeType(TradeTypeEnum tradeType) {
		this.tradeType = tradeType;
		return this;
	}

	public TradeCreateModel productId(String productId) {
		this.productId = productId;
		return this;
	}

	public TradeCreateModel limitPay(String limitPay) {
		this.limitPay = limitPay;
		return this;
	}

	public TradeCreateModel openId(String openId) {
		this.openId = openId;
		return this;
	}

	public TradeCreateModel receipt(Boolean receipt) {
		this.receipt = receipt;
		return this;
	}

	public TradeCreateModel sceneInfo(TradeSceneInfo sceneInfo) {
		this.sceneInfo = sceneInfo;
		return this;
	}

	public TradeCreateModel detail(TradeGoodsDetailInfo detail) {
		this.detail = detail;
		return this;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public FeeTypeEnum getFeeType() {
		return feeType;
	}

	public long getTotalFee() {
		return totalFee;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public String getBody() {
		return body;
	}

	public String getAttach() {
		return attach;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public LocalDateTime getTimeStart() {
		return timeStart;
	}

	public LocalDateTime getTimeExpire() {
		return timeExpire;
	}

	public String getGoodsTag() {
		return goodsTag;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public TradeTypeEnum getTradeType() {
		return tradeType;
	}

	public String getProductId() {
		return productId;
	}

	public String getLimitPay() {
		return limitPay;
	}

	public String getOpenId() {
		return openId;
	}

	public Boolean getReceipt() {
		return receipt;
	}

	public TradeSceneInfo getSceneInfo() {
		return sceneInfo;
	}

	public TradeGoodsDetailInfo getDetail() {
		return detail;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
