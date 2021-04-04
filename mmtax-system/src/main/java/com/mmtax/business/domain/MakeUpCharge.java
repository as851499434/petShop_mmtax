package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 补交服务费总记录表 tbl_make_up_charge
 * 
 * @author meimiao
 * @date 2020-08-19
 */
@Table(name = "tbl_make_up_charge")
public class MakeUpCharge
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 商户名称 */
	private String merchantName;
				/** 员工名称 */
	private String customerName;
	/** 对应记录创建时的关联订单 */
	private String orderSerialNum;
	/** 当月打款金额 */
	private BigDecimal monthPayAmount;
				/** 补交金额 */
	private BigDecimal amount;
	/** 0-内扣 1-外扣 */
	private Integer inOutDeduction;
				/** 补交流水号 */
	private String makeUpSerialNum;
				/** 批次号 */
	private String batchNo;
				/** -1-初始化 0-进行中 1-已补交 2-补交失败 9-失效 */
	private Integer status;
				/** 批量打款记录id */
	private Integer batchRecordId;
				/** 商户id */
	private Integer merchantId;
				/** 员工id */
	private Integer customerId;
				/** 备注 */
	private String comment;
				/** 后续异步动作是否处理 0否 1是 */
	private Integer asyncStatus;
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

	public Integer getInOutDeduction() {
		return inOutDeduction;
	}

	public void setInOutDeduction(Integer inOutDeduction) {
		this.inOutDeduction = inOutDeduction;
	}

	public String getOrderSerialNum() {
		return orderSerialNum;
	}

	public void setOrderSerialNum(String orderSerialNum) {
		this.orderSerialNum = orderSerialNum;
	}

	public BigDecimal getMonthPayAmount() {
		return monthPayAmount;
	}

	public void setMonthPayAmount(BigDecimal monthPayAmount) {
		this.monthPayAmount = monthPayAmount;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setMerchantName(String merchantName) 
	{
		this.merchantName = merchantName;
	}

	public String getMerchantName()
	{
		return merchantName;
	}

	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}

	public String getCustomerName()
	{
		return customerName;
	}

	public void setAmount(BigDecimal amount) 
	{
		this.amount = amount;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setMakeUpSerialNum(String makeUpSerialNum) 
	{
		this.makeUpSerialNum = makeUpSerialNum;
	}

	public String getMakeUpSerialNum()
	{
		return makeUpSerialNum;
	}

	public void setBatchNo(String batchNo) 
	{
		this.batchNo = batchNo;
	}

	public String getBatchNo()
	{
		return batchNo;
	}

	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setBatchRecordId(Integer batchRecordId) 
	{
		this.batchRecordId = batchRecordId;
	}

	public Integer getBatchRecordId()
	{
		return batchRecordId;
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

	public void setComment(String comment) 
	{
		this.comment = comment;
	}

	public String getComment()
	{
		return comment;
	}

	public void setAsyncStatus(Integer asyncStatus) 
	{
		this.asyncStatus = asyncStatus;
	}

	public Integer getAsyncStatus()
	{
		return asyncStatus;
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
            .append("merchantName", getMerchantName())
            .append("customerName", getCustomerName())
            .append("amount", getAmount())
            .append("makeUpSerialNum", getMakeUpSerialNum())
            .append("batchNo", getBatchNo())
            .append("status", getStatus())
            .append("batchRecordId", getBatchRecordId())
            .append("merchantId", getMerchantId())
            .append("customerId", getCustomerId())
            .append("comment", getComment())
            .append("asyncStatus", getAsyncStatus())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
