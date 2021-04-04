package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 员工绑定银行卡表 tbl_customer_bankcard_info
 * 
 * @author meimiao
 * @date 2020-07-09
 */
@Table(name = "tbl_customer_bankcard_info")
public class CustomerBankcardInfo
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 员工id */
	private Integer customerId;
				/** 员工编号 */
	private String customerNo;
				/** 银行全称 */
	private String bankName;
				/** 卡类型 DC-借记卡 CC-信用卡 */
	private String cardType;
				/** 银行账号/卡号 */
	private String bankAccountNo;
				/** 银行开户名 */
	private String accountName;
				/** 证件类型,取数据字典 */
	private String certificateType;
				/** 证件号码 */
	private String certificateNo;
				/** 银行预留手机号 */
	private String reservedMobile;
				/** 认证类型 3三要素 4四要素 */
	private Integer verifyType;
				/** 是否认证 0否 1是 */
	private Integer verifyStatus;
				/** 是否绑定网商 0否 1是 */
	private Integer bindStatus;
				/** 网商绑卡id */
	private String bankId;
				/**  */
	private Integer providerId;
				/**  */
	private String reservedFieldOne;
				/**  */
	private String reservedFieldTwo;
				/**  */
	private Date createTime;
				/**  */
	private Date updateTime;
	
	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setCustomerId(Integer customerId) 
	{
		this.customerId = customerId;
	}

	public Integer getCustomerId()
	{
		return customerId;
	}

	public void setCustomerNo(String customerNo) 
	{
		this.customerNo = customerNo;
	}

	public String getCustomerNo()
	{
		return customerNo;
	}

	public void setBankName(String bankName) 
	{
		this.bankName = bankName;
	}

	public String getBankName()
	{
		return bankName;
	}

	public void setCardType(String cardType) 
	{
		this.cardType = cardType;
	}

	public String getCardType()
	{
		return cardType;
	}

	public void setBankAccountNo(String bankAccountNo) 
	{
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankAccountNo()
	{
		return bankAccountNo;
	}

	public void setAccountName(String accountName) 
	{
		this.accountName = accountName;
	}

	public String getAccountName()
	{
		return accountName;
	}

	public void setCertificateType(String certificateType) 
	{
		this.certificateType = certificateType;
	}

	public String getCertificateType()
	{
		return certificateType;
	}

	public void setCertificateNo(String certificateNo) 
	{
		this.certificateNo = certificateNo;
	}

	public String getCertificateNo()
	{
		return certificateNo;
	}

	public void setReservedMobile(String reservedMobile) 
	{
		this.reservedMobile = reservedMobile;
	}

	public String getReservedMobile()
	{
		return reservedMobile;
	}

	public void setVerifyType(Integer verifyType) 
	{
		this.verifyType = verifyType;
	}

	public Integer getVerifyType()
	{
		return verifyType;
	}

	public void setVerifyStatus(Integer verifyStatus) 
	{
		this.verifyStatus = verifyStatus;
	}

	public Integer getVerifyStatus()
	{
		return verifyStatus;
	}

	public void setBindStatus(Integer bindStatus) 
	{
		this.bindStatus = bindStatus;
	}

	public Integer getBindStatus()
	{
		return bindStatus;
	}

	public void setBankId(String bankId) 
	{
		this.bankId = bankId;
	}

	public String getBankId()
	{
		return bankId;
	}

	public void setProviderId(Integer providerId) 
	{
		this.providerId = providerId;
	}

	public Integer getProviderId()
	{
		return providerId;
	}

	public void setReservedFieldOne(String reservedFieldOne) 
	{
		this.reservedFieldOne = reservedFieldOne;
	}

	public String getReservedFieldOne()
	{
		return reservedFieldOne;
	}

	public void setReservedFieldTwo(String reservedFieldTwo) 
	{
		this.reservedFieldTwo = reservedFieldTwo;
	}

	public String getReservedFieldTwo()
	{
		return reservedFieldTwo;
	}

	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime()
	{
		return createTime;
	}

	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime()
	{
		return updateTime;
	}


	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerId", getCustomerId())
            .append("customerNo", getCustomerNo())
            .append("bankName", getBankName())
            .append("cardType", getCardType())
            .append("bankAccountNo", getBankAccountNo())
            .append("accountName", getAccountName())
            .append("certificateType", getCertificateType())
            .append("certificateNo", getCertificateNo())
            .append("reservedMobile", getReservedMobile())
            .append("verifyType", getVerifyType())
            .append("verifyStatus", getVerifyStatus())
            .append("bindStatus", getBindStatus())
            .append("bankId", getBankId())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
