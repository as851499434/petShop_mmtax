package com.mmtax.common.utils.esign.domain.flowonestep;

/**
 * @description 流程基本信息
 * @author 宫清
 * @date 2019年11月19日 下午2:28:45
 * @since JDK1.7
 */
public class FlowInfo {

	//是否自动归档
	private Boolean autoArchive;
	
	//是否自动开启
	private Boolean autoInitiate;
	
	//业务场景
	private String businessScene;
	
	//文件到期前，提前多少小时回调提醒续签，小时（时间区间：1小时——15天
	private Integer contractRemind;
	
	//文件有效截止日期,毫秒
	private Long contractValidity;
	
	//任务配置信息
	private FlowConfigInfo flowConfigInfo;
	
	//流程备注
	private String remark;
	
	//签署有效截止时间，毫秒
	private Long signValidity;

	public Boolean getAutoArchive() {
		return autoArchive;
	}

	public void setAutoArchive(Boolean autoArchive) {
		this.autoArchive = autoArchive;
	}

	public Boolean getAutoInitiate() {
		return autoInitiate;
	}

	public void setAutoInitiate(Boolean autoInitiate) {
		this.autoInitiate = autoInitiate;
	}

	public String getBusinessScene() {
		return businessScene;
	}

	public void setBusinessScene(String businessScene) {
		this.businessScene = businessScene;
	}

	public Integer getContractRemind() {
		return contractRemind;
	}

	public void setContractRemind(Integer contractRemind) {
		this.contractRemind = contractRemind;
	}

	public Long getContractValidity() {
		return contractValidity;
	}

	public void setContractValidity(Long contractValidity) {
		this.contractValidity = contractValidity;
	}

	public FlowConfigInfo getFlowConfigInfo() {
		return flowConfigInfo;
	}

	public void setFlowConfigInfo(FlowConfigInfo flowConfigInfo) {
		this.flowConfigInfo = flowConfigInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getSignValidity() {
		return signValidity;
	}

	public void setSignValidity(Long signValidity) {
		this.signValidity = signValidity;
	}

	public FlowInfo(Boolean autoArchive, Boolean autoInitiate, String businessScene, Integer contractRemind,
			Long contractValidity, FlowConfigInfo flowConfigInfo, String remark, Long signValidity) {
		this.autoArchive = autoArchive;
		this.autoInitiate = autoInitiate;
		this.businessScene = businessScene;
		this.contractRemind = contractRemind;
		this.contractValidity = contractValidity;
		this.flowConfigInfo = flowConfigInfo;
		this.remark = remark;
		this.signValidity = signValidity;
	}

	
	public FlowInfo(String businessScene,FlowConfigInfo flowConfigInfo) {
		this.businessScene = businessScene;
		this.flowConfigInfo = flowConfigInfo;
	}

	public FlowInfo() {
	}
	
	
	
}
