package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 批量打款详细订单记录表 tbl_batch_payment_detail
 * 
 * @author meimiao
 * @date 2020-09-08
 */
@Table(name = "tbl_batch_payment_detail")
public class BatchPaymentDetail
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 批量打款记录id */
	private Integer batchPaymentRecordId;
				/** 批次号 */
	private String batchNo;
				/** 订单流水号 */
	private String orderSerialNum;
				/** 打款时请求打款金额 */
	private BigDecimal amount;
				/** 打款时税率 */
	private BigDecimal taxRate;
				/** 打款时手续费 */
	private BigDecimal charge;
				/** 打款时实发金额 */
	private BigDecimal actualAmount;
				/**  */
	private Integer providerId;
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

	public void setBatchPaymentRecordId(Integer batchPaymentRecordId) 
	{
		this.batchPaymentRecordId = batchPaymentRecordId;
	}

	public Integer getBatchPaymentRecordId()
	{
		return batchPaymentRecordId;
	}

	public void setBatchNo(String batchNo) 
	{
		this.batchNo = batchNo;
	}

	public String getBatchNo()
	{
		return batchNo;
	}

	public void setOrderSerialNum(String orderSerialNum) 
	{
		this.orderSerialNum = orderSerialNum;
	}

	public String getOrderSerialNum()
	{
		return orderSerialNum;
	}

	public void setAmount(BigDecimal amount) 
	{
		this.amount = amount;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setTaxRate(BigDecimal taxRate) 
	{
		this.taxRate = taxRate;
	}

	public BigDecimal getTaxRate()
	{
		return taxRate;
	}

	public void setCharge(BigDecimal charge) 
	{
		this.charge = charge;
	}

	public BigDecimal getCharge()
	{
		return charge;
	}

	public void setActualAmount(BigDecimal actualAmount) 
	{
		this.actualAmount = actualAmount;
	}

	public BigDecimal getActualAmount()
	{
		return actualAmount;
	}

	public void setProviderId(Integer providerId) 
	{
		this.providerId = providerId;
	}

	public Integer getProviderId()
	{
		return providerId;
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
            .append("batchPaymentRecordId", getBatchPaymentRecordId())
            .append("batchNo", getBatchNo())
            .append("orderSerialNum", getOrderSerialNum())
            .append("amount", getAmount())
            .append("taxRate", getTaxRate())
            .append("charge", getCharge())
            .append("actualAmount", getActualAmount())
            .append("providerId", getProviderId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
