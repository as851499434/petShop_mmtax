package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 税源地和发票科目关联表 tbl_tax_source_invoice_subject_correlation
 * 
 * @author meimiao
 * @date 2020-08-24
 */
@Table(name = "tbl_tax_source_invoice_subject_correlation")
public class TaxSourceInvoiceSubjectCorrelation
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 税源地id，对应tbl_tax_sounrce_company.id */
	private Integer taxSounrceCompanyId;
	/** 发票科目id，对应tbl_invoice_subject.id */
	private Integer invoiceSubjectId;
	/** 删除标识0-未删除1-已删除 */
	private Integer delStatus;
	/**  */
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

	public void setTaxSounrceCompanyId(Integer taxSounrceCompanyId) 
	{
		this.taxSounrceCompanyId = taxSounrceCompanyId;
	}

	public Integer getTaxSounrceCompanyId()
	{
		return taxSounrceCompanyId;
	}

	public void setInvoiceSubjectId(Integer invoiceSubjectId) 
	{
		this.invoiceSubjectId = invoiceSubjectId;
	}

	public Integer getInvoiceSubjectId()
	{
		return invoiceSubjectId;
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
            .append("taxSounrceCompanyId", getTaxSounrceCompanyId())
            .append("invoiceSubjectId", getInvoiceSubjectId())
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
