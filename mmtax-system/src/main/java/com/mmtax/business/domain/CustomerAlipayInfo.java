package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 员工绑定支付宝表 tbl_customer_alipay_info
 * 
 * @author meimiao
 * @date 2020-07-09
 */
@Table(name = "tbl_customer_alipay_info")
public class CustomerAlipayInfo
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
				/** 支付宝账号 */
	private String accountNo;
				/** 支付宝对应的真实姓名 */
	private String accountName;
				/** 身份证 */
	private String certificateNo;
				/** 预留手机号 */
	private String mobileNo;
				/** 是否认证 0否 1是 */
	private Integer verifyStatus;
				/** 是否绑定网商 0否 1是 */
	private Integer bindStatus;
				/** 网商绑定id */
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

	public void setAccountNo(String accountNo) 
	{
		this.accountNo = accountNo;
	}

	public String getAccountNo()
	{
		return accountNo;
	}

	public void setAccountName(String accountName) 
	{
		this.accountName = accountName;
	}

	public String getAccountName()
	{
		return accountName;
	}

	public void setCertificateNo(String certificateNo) 
	{
		this.certificateNo = certificateNo;
	}

	public String getCertificateNo()
	{
		return certificateNo;
	}

	public void setMobileNo(String mobileNo) 
	{
		this.mobileNo = mobileNo;
	}

	public String getMobileNo()
	{
		return mobileNo;
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
            .append("accountNo", getAccountNo())
            .append("accountName", getAccountName())
            .append("certificateNo", getCertificateNo())
            .append("mobileNo", getMobileNo())
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
