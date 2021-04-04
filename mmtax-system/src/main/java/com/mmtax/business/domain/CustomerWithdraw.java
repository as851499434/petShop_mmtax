package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 员工提现记录表 tbl_customer_withdraw
 * 
 * @author meimiao
 * @date 2020-09-11
 */
@Table(name = "tbl_customer_withdraw")
public class CustomerWithdraw
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 灵工id */
	private Integer customerId;
	/** 商户id */
	private Integer merchantId;
	/** 提现金额 */
	private BigDecimal amount;
	/** 提现账号 */
	private String withdrawAccount;
	/** 对应网商bankId */
	private String bankId;
	/** 提现渠道 BANK-银行 ALIPAY-支付宝 */
	private String withdrawChannel;
	/** 灵工流水号 */
	private String customerSerialNum;
	/** 提现流水号 */
	private String withdrawSerialNum;
	/** 提现备注 */
	private String comment;
	/** -1-初始化 0-进行中 1-提现成功 2-提现失败 */
	private Integer withdrawStatus;
	/**  */
	private Integer providerId;
	/** 后续异步动作是否处理 0否 1是 */
	private Integer asyncStatus;
	/** 预留字段1 */
	private String reservedFieldOne;
	/** 预留字段2 */
	private String reservedFieldTwo;
	/** 预留字段3 */
	private String reservedFieldThree;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

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

	public void setMerchantId(Integer merchantId) 
	{
		this.merchantId = merchantId;
	}

	public Integer getMerchantId()
	{
		return merchantId;
	}

	public void setAmount(BigDecimal amount) 
	{
		this.amount = amount;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setWithdrawAccount(String withdrawAccount) 
	{
		this.withdrawAccount = withdrawAccount;
	}

	public String getWithdrawAccount()
	{
		return withdrawAccount;
	}

	public void setWithdrawChannel(String withdrawChannel) 
	{
		this.withdrawChannel = withdrawChannel;
	}

	public String getWithdrawChannel()
	{
		return withdrawChannel;
	}

	public void setCustomerSerialNum(String customerSerialNum) 
	{
		this.customerSerialNum = customerSerialNum;
	}

	public String getCustomerSerialNum()
	{
		return customerSerialNum;
	}

	public void setWithdrawSerialNum(String withdrawSerialNum) 
	{
		this.withdrawSerialNum = withdrawSerialNum;
	}

	public String getWithdrawSerialNum()
	{
		return withdrawSerialNum;
	}

	public void setComment(String comment) 
	{
		this.comment = comment;
	}

	public String getComment()
	{
		return comment;
	}

	public void setWithdrawStatus(Integer withdrawStatus) 
	{
		this.withdrawStatus = withdrawStatus;
	}

	public Integer getWithdrawStatus()
	{
		return withdrawStatus;
	}

	public void setProviderId(Integer providerId) 
	{
		this.providerId = providerId;
	}

	public Integer getProviderId()
	{
		return providerId;
	}

	public void setAsyncStatus(Integer asyncStatus) 
	{
		this.asyncStatus = asyncStatus;
	}

	public Integer getAsyncStatus()
	{
		return asyncStatus;
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
            .append("customerId", getCustomerId())
            .append("merchantId", getMerchantId())
            .append("amount", getAmount())
            .append("withdrawAccount", getWithdrawAccount())
            .append("withdrawChannel", getWithdrawChannel())
            .append("customerSerialNum", getCustomerSerialNum())
            .append("withdrawSerialNum", getWithdrawSerialNum())
            .append("comment", getComment())
            .append("withdrawStatus", getWithdrawStatus())
            .append("providerId", getProviderId())
            .append("asyncStatus", getAsyncStatus())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
