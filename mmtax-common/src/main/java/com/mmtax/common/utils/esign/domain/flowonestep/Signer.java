package com.mmtax.common.utils.esign.domain.flowonestep;

import java.util.List;

/**
 * @description 签署方信息
 * @author 宫清
 * @date 2019年11月19日 下午2:36:30
 * @since JDK1.7
 */
public class Signer {
	
	//是否平台自动签署
	private Boolean platformSign;
	
	//签署方签署顺序
	private Integer signOrder;
	
	//签署方账号信息
	private SignerAccount signerAccount;
	
	//签署文件信息
	private List<SignfieldInfo> signfields;
	
	private String thirdOrderNo;

	public Boolean getPlatformSign() {
		return platformSign;
	}

	public void setPlatformSign(Boolean platformSign) {
		this.platformSign = platformSign;
	}

	public Integer getSignOrder() {
		return signOrder;
	}

	public void setSignOrder(Integer signOrder) {
		this.signOrder = signOrder;
	}

	public SignerAccount getSignerAccount() {
		return signerAccount;
	}

	public void setSignerAccount(SignerAccount signerAccount) {
		this.signerAccount = signerAccount;
	}

	public List<SignfieldInfo> getSignfields() {
		return signfields;
	}

	public void setSignfields(List<SignfieldInfo> signfields) {
		this.signfields = signfields;
	}

	public String getThirdOrderNo() {
		return thirdOrderNo;
	}

	public void setThirdOrderNo(String thirdOrderNo) {
		this.thirdOrderNo = thirdOrderNo;
	}

	public Signer(Boolean platformSign, Integer signOrder, SignerAccount signerAccount, List<SignfieldInfo> signfields,
			String thirdOrderNo) {
		this.platformSign = platformSign;
		this.signOrder = signOrder;
		this.signerAccount = signerAccount;
		this.signfields = signfields;
		this.thirdOrderNo = thirdOrderNo;
	}

	public Signer() {
	}
	
	
}
