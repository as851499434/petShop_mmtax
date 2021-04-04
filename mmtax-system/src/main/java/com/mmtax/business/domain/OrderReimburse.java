package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 订单服务费补偿表 tbl_order_reimburse
 * 
 * @author meimiao
 * @date 2020-10-14
 */
@Table(name = "tbl_order_reimburse")
public class OrderReimburse
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 补偿金额 */
	private BigDecimal amount;
				/** 关联订单号 */
	private String orderSerialNum;
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

	public void setAmount(BigDecimal amount) 
	{
		this.amount = amount;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setOrderSerialNum(String orderSerialNum) 
	{
		this.orderSerialNum = orderSerialNum;
	}

	public String getOrderSerialNum()
	{
		return orderSerialNum;
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
            .append("amount", getAmount())
            .append("orderSerialNum", getOrderSerialNum())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
