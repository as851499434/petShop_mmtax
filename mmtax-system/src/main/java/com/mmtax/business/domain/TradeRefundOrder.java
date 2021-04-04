package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 退款记录表 tbl_trade_refund_order
 * 
 * @author meimiao
 * @date 2020-09-18
 */
@Table(name = "tbl_trade_refund_order")
public class TradeRefundOrder
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 1-tbl_transfer_order表 */
	private Integer linkRecordType;
				/** 关联的原始单号 */
	private String linkSerialNum;
				/** 退款金额 */
	private BigDecimal amount;
				/** 退款手续费(若有) */
	private BigDecimal charge;
				/** 实际退款金额 */
	private BigDecimal actualAmount;
				/** 转账流水号(可查网商) */
	private String refundSerialNum;
				/** -1-初始化0-进行中1-已退款2-退款失败 */
	private Integer status;
				/** 备注 */
	private String comment;
				/** 后续异步动作是否处理 0否 1是 */
	private Integer asyncStatus;
	/** 后续异步动作是否处理 0否 1是 */
	private Integer taxSourceId;
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

	public Integer getTaxSourceId() {
		return taxSourceId;
	}

	public void setTaxSourceId(Integer taxSourceId) {
		this.taxSourceId = taxSourceId;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setLinkRecordType(Integer linkRecordType) 
	{
		this.linkRecordType = linkRecordType;
	}

	public Integer getLinkRecordType()
	{
		return linkRecordType;
	}

	public void setLinkSerialNum(String linkSerialNum) 
	{
		this.linkSerialNum = linkSerialNum;
	}

	public String getLinkSerialNum()
	{
		return linkSerialNum;
	}

	public void setAmount(BigDecimal amount) 
	{
		this.amount = amount;
	}

	public BigDecimal getAmount()
	{
		return amount;
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

	public void setRefundSerialNum(String refundSerialNum) 
	{
		this.refundSerialNum = refundSerialNum;
	}

	public String getRefundSerialNum()
	{
		return refundSerialNum;
	}

	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
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
            .append("linkRecordType", getLinkRecordType())
            .append("linkSerialNum", getLinkSerialNum())
            .append("amount", getAmount())
            .append("charge", getCharge())
            .append("actualAmount", getActualAmount())
            .append("refundSerialNum", getRefundSerialNum())
            .append("status", getStatus())
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
