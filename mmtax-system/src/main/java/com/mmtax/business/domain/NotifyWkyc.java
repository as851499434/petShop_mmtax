package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 悟空云创需求代付发送表 tbl_notify_wkyc
 * 
 * @author meimiao
 * @date 2020-11-17
 */
@Table(name = "tbl_notify_wkyc")
public class NotifyWkyc
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
				/** 通知状态 0-待发送 1成功 2失败 */
	private Integer notifyStatus;
				/** 发送方式 1post-json 2get */
	private Integer sendMethod;
	/** 订单类型 1-普通 2-灵工提现 */
	private Integer orderType;
				/** 代付订单号 */
	private String serialNum;
				/** 失败次数 */
	private Integer failNum;
				/** 上次发送的时间 */
	private Date lastSendTime;
				/**  */
	private Integer providerId;
				/** 预留字段1 */
	private String reservedFieldOne;
				/** 预留字段2 */
	private String reservedFieldTwo;
				/**  */
	private Date createTime;
				/**  */
	private Date updateTime;

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
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

	public void setNotifyStatus(Integer notifyStatus) 
	{
		this.notifyStatus = notifyStatus;
	}

	public Integer getNotifyStatus()
	{
		return notifyStatus;
	}

	public void setSendMethod(Integer sendMethod) 
	{
		this.sendMethod = sendMethod;
	}

	public Integer getSendMethod()
	{
		return sendMethod;
	}

	public void setSerialNum(String serialNum) 
	{
		this.serialNum = serialNum;
	}

	public String getSerialNum()
	{
		return serialNum;
	}

	public void setFailNum(Integer failNum) 
	{
		this.failNum = failNum;
	}

	public Integer getFailNum()
	{
		return failNum;
	}

	public void setLastSendTime(Date lastSendTime) 
	{
		this.lastSendTime = lastSendTime;
	}

	public Date getLastSendTime()
	{
		return lastSendTime;
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
            .append("merchantId", getMerchantId())
            .append("notifyStatus", getNotifyStatus())
            .append("sendMethod", getSendMethod())
            .append("serialNum", getSerialNum())
            .append("failNum", getFailNum())
            .append("lastSendTime", getLastSendTime())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
