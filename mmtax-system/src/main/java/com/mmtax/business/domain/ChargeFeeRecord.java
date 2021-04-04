package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 服务费收取记录表 tbl_charge_fee_record
 * 
 * @author meimiao
 * @date 2020-04-27
 */
@Table(name = "tbl_charge_fee_record")
public class ChargeFeeRecord
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 服务费支付方商户id */
	private Integer payMerchantId;
				/** 收取服务费的流水号 */
	private String chargeSerialNum;
				/** 服务费 */
	private BigDecimal chargeFee;
				/** 状态 0-失败 1-成功 2-挂起 */
	private Integer status;
				/** 服务费费率 */
	private BigDecimal singleServiceRate;
				/** 关联代付的订单号 */
	private String orderSerialNum;
				/** 代付金额 */
	private BigDecimal amount;
				/** 服务费收取方商户id */
	private Integer chargeMerchantId;
				/** 服务费收取方渠道 ONLINE */
	private String chargeChannel;
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
	
	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setPayMerchantId(Integer payMerchantId) 
	{
		this.payMerchantId = payMerchantId;
	}

	public Integer getPayMerchantId()
	{
		return payMerchantId;
	}

	public void setChargeSerialNum(String chargeSerialNum) 
	{
		this.chargeSerialNum = chargeSerialNum;
	}

	public String getChargeSerialNum()
	{
		return chargeSerialNum;
	}

	public void setChargeFee(BigDecimal chargeFee) 
	{
		this.chargeFee = chargeFee;
	}

	public BigDecimal getChargeFee()
	{
		return chargeFee;
	}

	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setSingleServiceRate(BigDecimal singleServiceRate) 
	{
		this.singleServiceRate = singleServiceRate;
	}

	public BigDecimal getSingleServiceRate()
	{
		return singleServiceRate;
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

	public void setChargeMerchantId(Integer chargeMerchantId) 
	{
		this.chargeMerchantId = chargeMerchantId;
	}

	public Integer getChargeMerchantId()
	{
		return chargeMerchantId;
	}

	public void setChargeChannel(String chargeChannel) 
	{
		this.chargeChannel = chargeChannel;
	}

	public String getChargeChannel()
	{
		return chargeChannel;
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
            .append("payMerchantId", getPayMerchantId())
            .append("chargeSerialNum", getChargeSerialNum())
            .append("chargeFee", getChargeFee())
            .append("status", getStatus())
            .append("singleServiceRate", getSingleServiceRate())
            .append("orderSerialNum", getOrderSerialNum())
            .append("amount", getAmount())
            .append("chargeMerchantId", getChargeMerchantId())
            .append("chargeChannel", getChargeChannel())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
