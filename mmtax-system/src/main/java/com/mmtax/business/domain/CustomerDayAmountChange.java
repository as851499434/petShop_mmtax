package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 灵工每日金额变化表 tbl_customer_day_amount_change
 * 
 * @author meimiao
 * @date 2020-09-21
 */
@Table(name = "tbl_customer_day_amount_change")
public class CustomerDayAmountChange
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
	/** 灵工id */
	private Integer customerId;
	/** 当日 */
	private String period;
	/** 当日起始金额 */
	private BigDecimal startAmount;
	/** 当日结束金额 */
	private BigDecimal endAmoutnt;
	/** 当日相差金额,结束金额-起始金额 */
	private BigDecimal diffAmount;
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

	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
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

	public void setCustomerId(Integer customerId) 
	{
		this.customerId = customerId;
	}

	public Integer getCustomerId()
	{
		return customerId;
	}

	public void setPeriod(String period) 
	{
		this.period = period;
	}

	public String getPeriod()
	{
		return period;
	}

	public void setStartAmount(BigDecimal startAmount) 
	{
		this.startAmount = startAmount;
	}

	public BigDecimal getStartAmount()
	{
		return startAmount;
	}

	public void setEndAmoutnt(BigDecimal endAmoutnt) 
	{
		this.endAmoutnt = endAmoutnt;
	}

	public BigDecimal getEndAmoutnt()
	{
		return endAmoutnt;
	}

	public void setDiffAmount(BigDecimal diffAmount) 
	{
		this.diffAmount = diffAmount;
	}

	public BigDecimal getDiffAmount()
	{
		return diffAmount;
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
            .append("customerId", getCustomerId())
            .append("period", getPeriod())
            .append("startAmount", getStartAmount())
            .append("endAmoutnt", getEndAmoutnt())
            .append("diffAmount", getDiffAmount())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
