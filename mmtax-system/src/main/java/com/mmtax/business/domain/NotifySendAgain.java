package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 异步通知重发送记录表 tbl_notify_send_again
 * 
 * @author meimiao
 * @date 2020-06-01
 */
@Table(name = "tbl_notify_send_again")
public class NotifySendAgain
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
				/** 通知类型 1代付 2充值 */
	private Integer notifyType;
				/** 通知状态 1成功 2失败 */
	private Integer notifyStatus;
				/** 发送方式 1post-json 2get */
	private Integer sendMethod;
				/** 通知内容 */
	private String notifyContent;
				/** 失败次数 */
	private Integer failNum;
				/** 上次发送的时间 */
	private Date lastSendTime;
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

	public void setMerchantId(Integer merchantId) 
	{
		this.merchantId = merchantId;
	}

	public Integer getMerchantId()
	{
		return merchantId;
	}

	public void setNotifyType(Integer notifyType) 
	{
		this.notifyType = notifyType;
	}

	public Integer getNotifyType()
	{
		return notifyType;
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

	public void setNotifyContent(String notifyContent) 
	{
		this.notifyContent = notifyContent;
	}

	public String getNotifyContent()
	{
		return notifyContent;
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
            .append("notifyType", getNotifyType())
            .append("notifyStatus", getNotifyStatus())
            .append("sendMethod", getSendMethod())
            .append("notifyContent", getNotifyContent())
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
