package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 员工账户余额变动明细表 tbl_customer_account_detail
 * 
 * @author meimiao
 * @date 2020-07-09
 */
@Table(name = "tbl_customer_account_detail")
public class CustomerAccountDetail
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 交易之前总余额 */
	private BigDecimal paymentAmountBefore;
				/** 交易之后总余额 */
	private BigDecimal paymentAmountAfter;
				/** 交易之前冻结金额 */
	private BigDecimal paymentFrozenAmountBefore;
				/** 交易之后冻结金额 */
	private BigDecimal paymentFrozenAmountAfter;
				/** 交易之前可用余额 */
	private BigDecimal paymentUsableAmountBefore;
				/** 交易之后可用余额 */
	private BigDecimal paymentUsableAmountAfter;
				/** 变动状态 0-失败 1-成功 */
	private Integer status;
				/** 变动类型 0-出账冻结 1-入账冻结 2出账解冻 3入账解冻 */
	private Integer type;
				/** 变动金额 */
	private BigDecimal paymentAmount;
				/** 订单表流水号 */
	private String orderSerialNum;
				/** 员工id */
	private Integer customerId;
				/** 交易类型：0-账户收款 1-收取佣金 2-账户收款失败 3-收取佣金成功 */
	private String paymentType;
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

	public void setPaymentAmountBefore(BigDecimal paymentAmountBefore) 
	{
		this.paymentAmountBefore = paymentAmountBefore;
	}

	public BigDecimal getPaymentAmountBefore()
	{
		return paymentAmountBefore;
	}

	public void setPaymentAmountAfter(BigDecimal paymentAmountAfter) 
	{
		this.paymentAmountAfter = paymentAmountAfter;
	}

	public BigDecimal getPaymentAmountAfter()
	{
		return paymentAmountAfter;
	}

	public void setPaymentFrozenAmountBefore(BigDecimal paymentFrozenAmountBefore) 
	{
		this.paymentFrozenAmountBefore = paymentFrozenAmountBefore;
	}

	public BigDecimal getPaymentFrozenAmountBefore()
	{
		return paymentFrozenAmountBefore;
	}

	public void setPaymentFrozenAmountAfter(BigDecimal paymentFrozenAmountAfter) 
	{
		this.paymentFrozenAmountAfter = paymentFrozenAmountAfter;
	}

	public BigDecimal getPaymentFrozenAmountAfter()
	{
		return paymentFrozenAmountAfter;
	}

	public void setPaymentUsableAmountBefore(BigDecimal paymentUsableAmountBefore) 
	{
		this.paymentUsableAmountBefore = paymentUsableAmountBefore;
	}

	public BigDecimal getPaymentUsableAmountBefore()
	{
		return paymentUsableAmountBefore;
	}

	public void setPaymentUsableAmountAfter(BigDecimal paymentUsableAmountAfter) 
	{
		this.paymentUsableAmountAfter = paymentUsableAmountAfter;
	}

	public BigDecimal getPaymentUsableAmountAfter()
	{
		return paymentUsableAmountAfter;
	}

	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType()
	{
		return type;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) 
	{
		this.paymentAmount = paymentAmount;
	}

	public BigDecimal getPaymentAmount()
	{
		return paymentAmount;
	}

	public void setOrderSerialNum(String orderSerialNum) 
	{
		this.orderSerialNum = orderSerialNum;
	}

	public String getOrderSerialNum()
	{
		return orderSerialNum;
	}

	public void setCustomerId(Integer customerId) 
	{
		this.customerId = customerId;
	}

	public Integer getCustomerId()
	{
		return customerId;
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("paymentAmountBefore", getPaymentAmountBefore())
            .append("paymentAmountAfter", getPaymentAmountAfter())
            .append("paymentFrozenAmountBefore", getPaymentFrozenAmountBefore())
            .append("paymentFrozenAmountAfter", getPaymentFrozenAmountAfter())
            .append("paymentUsableAmountBefore", getPaymentUsableAmountBefore())
            .append("paymentUsableAmountAfter", getPaymentUsableAmountAfter())
            .append("status", getStatus())
            .append("type", getType())
            .append("paymentAmount", getPaymentAmount())
            .append("orderSerialNum", getOrderSerialNum())
            .append("customerId", getCustomerId())
            .append("providerId", getProviderId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
			.append("paymentType", getPaymentType())
            .toString();
    }
}
