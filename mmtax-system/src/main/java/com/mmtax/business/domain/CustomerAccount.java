package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import tk.mybatis.mapper.annotation.Version;

import java.util.Date;
import java.math.BigDecimal;

/**
 * 员工账户表 tbl_customer_account
 * 
 * @author meimiao
 * @date 2020-07-09
 */
@Table(name = "tbl_customer_account")
public class CustomerAccount
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
				/** 总余额 */
	private BigDecimal amount;
				/** 冻结金额 */
	private BigDecimal frozenAmount;
				/** 可用余额 */
	private BigDecimal usableAmount;
				/** 状态 ACTIVE-激活状态，正常使用FROZED-冻结（不可提现）CANCEL-注销 */
	private String status;
	/** 版本号 */
	@Version
	private Integer version;
	/**  */
	@Transient
	private Integer providerId;
				/** 预留字段1 */
	private String reservedFieldOne;
				/** 预留字段2 */
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

	public void setAmount(BigDecimal amount) 
	{
		this.amount = amount;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setFrozenAmount(BigDecimal frozenAmount) 
	{
		this.frozenAmount = frozenAmount;
	}

	public BigDecimal getFrozenAmount()
	{
		return frozenAmount;
	}

	public void setUsableAmount(BigDecimal usableAmount) 
	{
		this.usableAmount = usableAmount;
	}

	public BigDecimal getUsableAmount()
	{
		return usableAmount;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus()
	{
		return status;
	}

	public void setVersion(Integer version) 
	{
		this.version = version;
	}

	public Integer getVersion()
	{
		return version;
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
            .append("amount", getAmount())
            .append("frozenAmount", getFrozenAmount())
            .append("usableAmount", getUsableAmount())
            .append("status", getStatus())
            .append("version", getVersion())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
