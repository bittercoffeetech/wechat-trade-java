package tech.bittercoffee.wechat.api.trade;

import java.io.InputStream;

public final class WechatClientConfig {
	private String appId;
	private String mchId;
	private String mchKey;
	private InputStream apiCert;

	/**
	 * @param appId   应用ID
	 * @param mchId   商户ID
	 * @param mchKey  商户秘钥
	 * @param apiCert 客户端证书
	 * 
	 *                创建微信支付客户端对象
	 */
	public WechatClientConfig(String appId, String mchId, String mchKey, InputStream apiCert) {
		this.appId = appId;
		this.mchId = mchId;
		this.mchKey = mchKey;
		this.apiCert = apiCert;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchKey() {
		return mchKey;
	}

	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}

	public InputStream getApiCert() {
		return apiCert;
	}

	public void setApiCert(InputStream apiCert) {
		this.apiCert = apiCert;
	}

}
