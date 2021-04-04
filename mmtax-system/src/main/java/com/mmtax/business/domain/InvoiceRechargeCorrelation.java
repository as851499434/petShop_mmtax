package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 发票记录和充值记录关联表 tbl_invoice_recharge_correlation
 * 
 * @author meimiao
 * @date 2020-08-24
 */
@Table(name = "tbl_invoice_recharge_correlation")
public class InvoiceRechargeCorrelation
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 商户id */
	private Integer merchantId;
	/** 发票记录id，对应表tbl_invoice_record.id */
	private Integer invoiceRecordId;
	/** 充值记录id，对应tbl_recharge_record.id */
	private Integer rechargeRecordId;
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

	public InvoiceRechargeCorrelation() {
	}

	public InvoiceRechargeCorrelation(Integer merchantId, Integer invoiceRecordId, Integer rechargeRecordId, Integer delStatus, Integer providerId, Date createTime) {
		this.merchantId = merchantId;
		this.invoiceRecordId = invoiceRecordId;
		this.rechargeRecordId = rechargeRecordId;
		this.delStatus = delStatus;
		this.providerId = providerId;
		this.createTime = createTime;
	}

	public InvoiceRechargeCorrelation(Integer merchantId, Integer invoiceRecordId, Integer rechargeRecordId, Integer delStatus,  Date createTime) {
		this.merchantId = merchantId;
		this.invoiceRecordId = invoiceRecordId;
		this.rechargeRecordId = rechargeRecordId;
		this.delStatus = delStatus;
		this.createTime = createTime;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setMerchantId(Integer merchantId) 
	{
		this.merchantId = merchantId;
	}

	public Integer getMerchantId()
	{
		return merchantId;
	}

	public void setInvoiceRecordId(Integer invoiceRecordId) 
	{
		this.invoiceRecordId = invoiceRecordId;
	}

	public Integer getInvoiceRecordId()
	{
		return invoiceRecordId;
	}

	public void setRechargeRecordId(Integer rechargeRecordId) 
	{
		this.rechargeRecordId = rechargeRecordId;
	}

	public Integer getRechargeRecordId()
	{
		return rechargeRecordId;
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
            .append("merchantId", getMerchantId())
            .append("invoiceRecordId", getInvoiceRecordId())
            .append("rechargeRecordId", getRechargeRecordId())
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
