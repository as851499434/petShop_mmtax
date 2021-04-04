package com.mmtax.common.utils.esign.domain.signflow;

import lombok.Data;

/**
 * @description 签署流程创建 实体
 * @author 宫清
 * @date 2019年7月15日 下午2:50:02
 * @since JDK1.7
 */
@Data
public class SignFlowStart {

	// 是否自动归档，默认false
	private Boolean autoArchive;

	// 文件主题
	private String businessScene;

	// 任务配置信息
	private ConfigInfo configInfo;

	// 文件有效截止日期,毫秒，默认不失效
	private Long contractValidity;

	// 文件到期前，提前多少小时回调提醒续签，小时（时间区间：1小时——15天），默认不提醒
	private Integer contractRemind;

	// 签署有效截止日期,毫秒，默认不失效
	private Long signValidity;

	// 发起人账户id，即发起本次签署的操作人个人账号id；如不传，默认由对接平台发起
	private String initiatorAccountId;

	//发起方主体id，如存在个人代机构发起签约，则需传入机构id；如不传，则默认是对接平台
	private String initiatorAuthorizedAccountId;

	public SignFlowStart(Boolean autoArchive, String businessScene, Long contractValidity, Integer contractRemind,
			Long signValidity, String initiatorAccountId, String initiatorAuthorizedAccountId, ConfigInfo configInfo) {
		this.autoArchive = autoArchive;
		this.businessScene = businessScene;
		this.configInfo = configInfo;
		this.contractValidity = contractValidity;
		this.contractRemind = contractRemind;
		this.signValidity = signValidity;
		this.initiatorAccountId = initiatorAccountId;
		this.initiatorAuthorizedAccountId = initiatorAuthorizedAccountId;
	}

	
	public SignFlowStart() {
	}
}
