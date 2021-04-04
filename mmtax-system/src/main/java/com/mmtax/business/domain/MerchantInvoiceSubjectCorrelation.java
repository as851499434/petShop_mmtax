package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 商户和发票科目关联表 tbl_merchant_invoice_subject_correlation
 * 
 * @author meimiao
 * @date 2020-08-24
 */
@Table(name = "tbl_merchant_invoice_subject_correlation")
public class MerchantInvoiceSubjectCorrelation
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 商户id，对应tbl_tax_merchant_info.id */
	private Integer merchantInfoId;
	/** 发票科目id，对应tbl_invoice_subject.id */
	private Integer invoiceSubjectId;
	/** 是否默认 0-否 1-是 */
	private Integer isDefault;
	/** 删除标识0-未删除1-已删除 */
	private Integer delStatus;
	/**  */
	@Transient
	private Integer providerId;
	/** 预留字段1 */
	private String reservedFieldOne;
	/** 预留字段2 */
	private String reservedFieldTwo;
	/** 预留字段3 */
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

	public void setMerchantInfoId(Integer merchantInfoId) 
	{
		this.merchantInfoId = merchantInfoId;
	}

	public Integer getMerchantInfoId()
	{
		return merchantInfoId;
	}

	public void setInvoiceSubjectId(Integer invoiceSubjectId) 
	{
		this.invoiceSubjectId = invoiceSubjectId;
	}

	public Integer getInvoiceSubjectId()
	{
		return invoiceSubjectId;
	}

	public void setIsDefault(Integer isDefault) 
	{
		this.isDefault = isDefault;
	}

	public Integer getIsDefault()
	{
		return isDefault;
	}

	public void setDelStatus(Integer delStatus) 
	{
		this.delStatus = delStatus;
	}

	public Integer getDelStatus()
	{
		return delStatus;
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


	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merchantInfoId", getMerchantInfoId())
            .append("invoiceSubjectId", getInvoiceSubjectId())
            .append("isDefault", getIsDefault())
            .append("delStatus", getDelStatus())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
