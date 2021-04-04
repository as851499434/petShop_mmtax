package com.mmtax.common.utils.esign.domain.flowonestep;

/**
 * @description 任务配置信息
 * @author 宫清
 * @date 2019年11月19日 下午2:32:59
 * @since JDK1.7
 */
public class FlowConfigInfo {
	
	//通知开发者地址
	private String noticeDeveloperUrl;
	
	//通知方式，可选择多种通知方式
	private String noticeType;
	
	//签署完成重定向地址
	private String redirectUrl;
	
	//签署平台
	private String signPlatform;

	public String getNoticeDeveloperUrl() {
		return noticeDeveloperUrl;
	}

	public void setNoticeDeveloperUrl(String noticeDeveloperUrl) {
		this.noticeDeveloperUrl = noticeDeveloperUrl;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getSignPlatform() {
		return signPlatform;
	}

	public void setSignPlatform(String signPlatform) {
		this.signPlatform = signPlatform;
	}

	public FlowConfigInfo(String noticeDeveloperUrl, String noticeType, String redirectUrl, String signPlatform) {
		this.noticeDeveloperUrl = noticeDeveloperUrl;
		this.noticeType = noticeType;
		this.redirectUrl = redirectUrl;
		this.signPlatform = signPlatform;
	}

	public FlowConfigInfo() {
	}
	
	
}
