package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 员工关联商户表 tbl_cus_link_mer_info
 * 
 * @author meimiao
 * @date 2020-10-10
 */
@Table(name = "tbl_cus_link_mer_info")
public class CusLinkMerInfo
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
				/** 员工真名 */
	private String realName;
	/** 员工签约id */
	private Integer cusEsignId;
	/** 证件号码 */
	private String certificateNo;
				/** 商户id */
	private Integer merchantId;
				/** 商户名称 */
	private String merchantName;
				/** 所属税源地id */
	private Integer taxSourceId;
				/** 当月使用费率 0普通 1大额 */
	private Integer monthUseRate;
				/**  */
	private Integer providerId;
				/**  */
	private String reservedFieldOne;
				/**  */
	private String reservedFieldTwo;
				/**  */
	private String reservedFieldThree;
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

	public void setRealName(String realName) 
	{
		this.realName = realName;
	}

	public String getRealName()
	{
		return realName;
	}

	public void setMerchantId(Integer merchantId) 
	{
		this.merchantId = merchantId;
	}

	public Integer getMerchantId()
	{
		return merchantId;
	}

	public void setMerchantName(String merchantName) 
	{
		this.merchantName = merchantName;
	}

	public String getMerchantName()
	{
		return merchantName;
	}

	public void setTaxSourceId(Integer taxSourceId) 
	{
		this.taxSourceId = taxSourceId;
	}

	public Integer getTaxSourceId()
	{
		return taxSourceId;
	}

	public void setMonthUseRate(Integer monthUseRate) 
	{
		this.monthUseRate = monthUseRate;
	}

	public Integer getMonthUseRate()
	{
		return monthUseRate;
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

	public void setReservedFieldThree(String reservedFieldThree) 
	{
		this.reservedFieldThree = reservedFieldThree;
	}

	public String getReservedFieldThree()
	{
		return reservedFieldThree;
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

	public Integer getCusEsignId() {
		return cusEsignId;
	}

	public void setCusEsignId(Integer cusEsignId) {
		this.cusEsignId = cusEsignId;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	@Override
	public String toString() {
		return "CusLinkMerInfo{" +
				"id=" + id +
				", customerId=" + customerId +
				", realName='" + realName + '\'' +
				", cusEsignId=" + cusEsignId +
				", certificateNo='" + certificateNo + '\'' +
				", merchantId=" + merchantId +
				", merchantName='" + merchantName + '\'' +
				", taxSourceId=" + taxSourceId +
				", monthUseRate=" + monthUseRate +
				", providerId=" + providerId +
				", reservedFieldOne='" + reservedFieldOne + '\'' +
				", reservedFieldTwo='" + reservedFieldTwo + '\'' +
				", reservedFieldThree='" + reservedFieldThree + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
