package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 任务批次记录表 tbl_batch_task_record
 * 
 * @author meimiao
 * @date 2020-10-15
 */
@Table(name = "tbl_batch_task_record")
public class BatchTaskRecord
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 批次号 */
	private String batchNo;
				/** 任务总数 */
	private Integer taskTotalNum;
				/** 该批次任务总金额 */
	private BigDecimal taskAmount;
				/** 任务状态 0-待完成 1-已完成 */
	private Integer taskStatus;
				/** 任务信息id */
	private Integer taskInfoId;
				/** 任务名称 */
	private String taskName;
				/** 任务发布时间 */
	private String taskBeginTime;
				/** 任务完成时间 */
	private String taskCompleteTime;
				/** 规定完成时间 */
	private String taskRequireCompleteTime;
				/** 商户id */
	private Integer merchantId;
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

	public void setBatchNo(String batchNo) 
	{
		this.batchNo = batchNo;
	}

	public String getBatchNo()
	{
		return batchNo;
	}

	public void setTaskTotalNum(Integer taskTotalNum) 
	{
		this.taskTotalNum = taskTotalNum;
	}

	public Integer getTaskTotalNum()
	{
		return taskTotalNum;
	}

	public void setTaskAmount(BigDecimal taskAmount) 
	{
		this.taskAmount = taskAmount;
	}

	public BigDecimal getTaskAmount()
	{
		return taskAmount;
	}

	public void setTaskStatus(Integer taskStatus) 
	{
		this.taskStatus = taskStatus;
	}

	public Integer getTaskStatus()
	{
		return taskStatus;
	}

	public void setTaskInfoId(Integer taskInfoId) 
	{
		this.taskInfoId = taskInfoId;
	}

	public Integer getTaskInfoId()
	{
		return taskInfoId;
	}

	public void setTaskName(String taskName) 
	{
		this.taskName = taskName;
	}

	public String getTaskName()
	{
		return taskName;
	}

	public void setTaskBeginTime(String taskBeginTime) 
	{
		this.taskBeginTime = taskBeginTime;
	}

	public String getTaskBeginTime()
	{
		return taskBeginTime;
	}

	public void setTaskCompleteTime(String taskCompleteTime) 
	{
		this.taskCompleteTime = taskCompleteTime;
	}

	public String getTaskCompleteTime()
	{
		return taskCompleteTime;
	}

	public void setTaskRequireCompleteTime(String taskRequireCompleteTime) 
	{
		this.taskRequireCompleteTime = taskRequireCompleteTime;
	}

	public String getTaskRequireCompleteTime()
	{
		return taskRequireCompleteTime;
	}

	public void setMerchantId(Integer merchantId) 
	{
		this.merchantId = merchantId;
	}

	public Integer getMerchantId()
	{
		return merchantId;
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
            .append("batchNo", getBatchNo())
            .append("taskTotalNum", getTaskTotalNum())
            .append("taskAmount", getTaskAmount())
            .append("taskStatus", getTaskStatus())
            .append("taskInfoId", getTaskInfoId())
            .append("taskName", getTaskName())
            .append("taskBeginTime", getTaskBeginTime())
            .append("taskCompleteTime", getTaskCompleteTime())
            .append("taskRequireCompleteTime", getTaskRequireCompleteTime())
            .append("merchantId", getMerchantId())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
