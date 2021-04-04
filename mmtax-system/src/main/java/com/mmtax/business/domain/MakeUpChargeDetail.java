package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 补交服务费详细记录表 tbl_make_up_charge_detail
 * 
 * @author meimiao
 * @date 2020-08-19
*/
@Table(name = "tbl_make_up_charge_detail")
public class MakeUpChargeDetail
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 补交服务费总记录表id */
	private Integer makeUpChargeId;
				/** 订单流水号 */
	private String orderSerialNum;
				/** 补交服务费时订单的状态 */
	private Integer orderStatus;
				/** 原税率 */
	private BigDecimal taxRate;
				/** 原手续费 */
	private BigDecimal charge;
				/** 现税率 */
	private BigDecimal taxRateNow;
				/** 现手续费 */
	private BigDecimal chargeNow;
				/** 补交手续费 */
	private BigDecimal chargeMakeUp;
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

	public void setMakeUpChargeId(Integer makeUpChargeId) 
	{
		this.makeUpChargeId = makeUpChargeId;
	}

	public Integer getMakeUpChargeId()
	{
		return makeUpChargeId;
	}

	public void setOrderSerialNum(String orderSerialNum) 
	{
		this.orderSerialNum = orderSerialNum;
	}

	public String getOrderSerialNum()
	{
		return orderSerialNum;
	}

	public void setOrderStatus(Integer orderStatus) 
	{
		this.orderStatus = orderStatus;
	}

	public Integer getOrderStatus()
	{
		return orderStatus;
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

	public void setTaxRateNow(BigDecimal taxRateNow) 
	{
		this.taxRateNow = taxRateNow;
	}

	public BigDecimal getTaxRateNow()
	{
		return taxRateNow;
	}

	public void setChargeNow(BigDecimal chargeNow) 
	{
		this.chargeNow = chargeNow;
	}

	public BigDecimal getChargeNow()
	{
		return chargeNow;
	}

	public void setChargeMakeUp(BigDecimal chargeMakeUp) 
	{
		this.chargeMakeUp = chargeMakeUp;
	}

	public BigDecimal getChargeMakeUp()
	{
		return chargeMakeUp;
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
            .append("makeUpChargeId", getMakeUpChargeId())
            .append("orderSerialNum", getOrderSerialNum())
            .append("orderStatus", getOrderStatus())
            .append("taxRate", getTaxRate())
            .append("charge", getCharge())
            .append("taxRateNow", getTaxRateNow())
            .append("chargeNow", getChargeNow())
            .append("chargeMakeUp", getChargeMakeUp())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
