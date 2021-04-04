package com.mmtax.common.utils.esign.domain.flowonestep;

/**
 * @description 抄送人信息
 * @author 宫清
 * @date 2019年11月19日 下午2:25:15
 * @since JDK1.7
 */
public class Copier {

	//参与人 accountId
	private String copierAccountId;
	
	//参与主体类型
	private Integer copierIdentityAccountType;
	
	//参与主体账号id
	private String copierIdentityAccountId;
	
	public String getCopierIdentityAccountId() {
		return copierIdentityAccountId;
	}

	public void setCopierIdentityAccountId(String copierIdentityAccountId) {
		this.copierIdentityAccountId = copierIdentityAccountId;
	}

	public String getCopierAccountId() {
		return copierAccountId;
	}

	public void setCopierAccountId(String copierAccountId) {
		this.copierAccountId = copierAccountId;
	}

	public Integer getCopierIdentityAccountType() {
		return copierIdentityAccountType;
	}

	public void setCopierIdentityAccountType(Integer copierIdentityAccountType) {
		this.copierIdentityAccountType = copierIdentityAccountType;
	}

	public Copier(String copierAccountId, Integer copierIdentityAccountType,String copierIdentityAccountId) {
		this.copierAccountId = copierAccountId;
		this.copierIdentityAccountType = copierIdentityAccountType;
		this.copierIdentityAccountId = copierIdentityAccountId;
	}

	public Copier() {
	}
	
	
}
