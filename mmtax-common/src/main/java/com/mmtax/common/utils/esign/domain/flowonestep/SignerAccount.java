package com.mmtax.common.utils.esign.domain.flowonestep;

/**
 * @description 签署方账号信息
 * @author 宫清
 * @date 2019年11月19日 下午2:40:46
 * @since JDK1.7
 */
public class SignerAccount {
	
	//签署操作人个人账号标识，即操作本次签署的个人
	private String signerAccountId;
	
	//签约主体账号标识，即本次签署对应任务的归属方
	private String authorizedAccountId;

	public String getSignerAccountId() {
		return signerAccountId;
	}

	public void setSignerAccountId(String signerAccountId) {
		this.signerAccountId = signerAccountId;
	}

	public String getAuthorizedAccountId() {
		return authorizedAccountId;
	}

	public void setAuthorizedAccountId(String authorizedAccountId) {
		this.authorizedAccountId = authorizedAccountId;
	}

	public SignerAccount(String signerAccountId, String authorizedAccountId) {
		this.signerAccountId = signerAccountId;
		this.authorizedAccountId = authorizedAccountId;
	}

	public SignerAccount() {
	}
	
	
}
