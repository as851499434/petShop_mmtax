package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 商户转账员工记录表 tbl_transfer_order
 * 
 * @author meimiao
 * @date 2020-07-09
 */
@Table(name = "tbl_transfer_order")
public class TransferOrder
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
				/** 打款金额 */
	private BigDecimal amount;
				/** 税率 */
	private BigDecimal taxRate;
				/** 手续费 */
	private BigDecimal charge;
				/** 转账总金额 */
	private BigDecimal actualAmount;
				/** 转账流水号(可查网商) */
	private String transferSerialNum;
				/** 关联订单号 */
	private String orderSerialNum;
				/** 批次号 */
	private String batchNo;
				/** 员工姓名 */
	private String customerName;
				/** -1-初始化0-进行中1-已转账2-转账失败 */
	private Integer status;
				/** 批量打款记录id */
	private Integer batchPaymentRecordId;
				/** BANK-银行ALIPAY-支付宝WECHAT-微信 */
	private String paymentChannel;
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

	public void setTransferSerialNum(String transferSerialNum) 
	{
		this.transferSerialNum = transferSerialNum;
	}

	public String getTransferSerialNum()
	{
		return transferSerialNum;
	}

	public void setOrderSerialNum(String orderSerialNum) 
	{
		this.orderSerialNum = orderSerialNum;
	}

	public String getOrderSerialNum()
	{
		return orderSerialNum;
	}

	public void setBatchNo(String batchNo) 
	{
		this.batchNo = batchNo;
	}

	public String getBatchNo()
	{
		return batchNo;
	}

	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}

	public String getCustomerName()
	{
		return customerName;
	}

	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setBatchPaymentRecordId(Integer batchPaymentRecordId) 
	{
		this.batchPaymentRecordId = batchPaymentRecordId;
	}

	public Integer getBatchPaymentRecordId()
	{
		return batchPaymentRecordId;
	}

	public void setPaymentChannel(String paymentChannel) 
	{
		this.paymentChannel = paymentChannel;
	}

	public String getPaymentChannel()
	{
		return paymentChannel;
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
            .append("amount", getAmount())
            .append("taxRate", getTaxRate())
            .append("charge", getCharge())
            .append("actualAmount", getActualAmount())
            .append("transferSerialNum", getTransferSerialNum())
            .append("orderSerialNum", getOrderSerialNum())
            .append("batchNo", getBatchNo())
            .append("customerName", getCustomerName())
            .append("status", getStatus())
            .append("batchPaymentRecordId", getBatchPaymentRecordId())
            .append("paymentChannel", getPaymentChannel())
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
