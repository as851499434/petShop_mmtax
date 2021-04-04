package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 任务详细记录表 tbl_batch_task_detail
 * 
 * @author meimiao
 * @date 2020-10-15
 */
@Table(name = "tbl_batch_task_detail")
@ApiModel("任务详细记录")
public class BatchTaskDetail
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty("")
	private Integer id;
					/** 批次id */
	@ApiModelProperty("批次id")
	private Integer batchTaskRecordId;
				/** 批次号 */
	@ApiModelProperty("批次号")
	private String batchNo;
				/** 任务编号 */
	@ApiModelProperty("任务编号")
	private String taskSerialNum;
				/** 任务信息id */
	@ApiModelProperty("任务信息id")
	private Integer taskInfoId;
				/** 任务名称 */
	@ApiModelProperty("任务名称")
	private String taskName;
				/** 任务数量 */
	@ApiModelProperty("任务数量")
	private Integer taskNum;
				/** 任务单价 */
	@ApiModelProperty("任务单价")
	private BigDecimal amount;
				/** 任务总价 */
	@ApiModelProperty("任务总价")
	private BigDecimal allAmount;
				/** 任务状态 0-待完成 1-已完成 */
	@ApiModelProperty("任务状态 0-待完成 1-已完成")
	private Integer taskStatus;
				/** 任务完成时间 */
	@ApiModelProperty("任务完成时间")
	private String taskCompleteTime;
				/** 规定完成时间 */
	@ApiModelProperty("规定完成时间")
	private String taskRequireCompleteTime;
				/** 商户id */
	@ApiModelProperty("商户id")
	private Integer merchantId;
				/** 税源地(承接方) */
	@ApiModelProperty("税源地(承接方)")
	private Integer taxSourceId;
	/** 是否接单 */
	@ApiModelProperty("是否接单")
	private Integer takeTaskStatus;
				/** 额外备注 */
	@ApiModelProperty("额外备注")
	private String remark;
				/**  */
	private Integer providerId;
				/**  */
	private String reservedFieldOne;
				/**  */
	private String reservedFieldTwo;
				/**  */
	private String reservedFieldThree;
				/**  */
	@ApiModelProperty("创建时间")
	private Date createTime;
				/**  */
	@ApiModelProperty("修改时间")
	private Date updateTime;

	public Integer getTakeTaskStatus() {
		return takeTaskStatus;
	}

	public void setTakeTaskStatus(Integer takeTaskStatus) {
		this.takeTaskStatus = takeTaskStatus;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setBatchTaskRecordId(Integer batchTaskRecordId) 
	{
		this.batchTaskRecordId = batchTaskRecordId;
	}

	public Integer getBatchTaskRecordId()
	{
		return batchTaskRecordId;
	}

	public void setBatchNo(String batchNo) 
	{
		this.batchNo = batchNo;
	}

	public String getBatchNo()
	{
		return batchNo;
	}

	public void setTaskSerialNum(String taskSerialNum) 
	{
		this.taskSerialNum = taskSerialNum;
	}

	public String getTaskSerialNum()
	{
		return taskSerialNum;
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

	public void setTaskNum(Integer taskNum) 
	{
		this.taskNum = taskNum;
	}

	public Integer getTaskNum()
	{
		return taskNum;
	}

	public void setAmount(BigDecimal amount) 
	{
		this.amount = amount;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAllAmount(BigDecimal allAmount) 
	{
		this.allAmount = allAmount;
	}

	public BigDecimal getAllAmount()
	{
		return allAmount;
	}

	public void setTaskStatus(Integer taskStatus) 
	{
		this.taskStatus = taskStatus;
	}

	public Integer getTaskStatus()
	{
		return taskStatus;
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

	public void setTaxSourceId(Integer taxSourceId)
	{
		this.taxSourceId = taxSourceId;
	}

	public Integer getTaxSourceId()
	{
		return taxSourceId;
	}

	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark()
	{
		return remark;
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
            .append("batchTaskRecordId", getBatchTaskRecordId())
            .append("batchNo", getBatchNo())
            .append("taskSerialNum", getTaskSerialNum())
            .append("taskInfoId", getTaskInfoId())
            .append("taskName", getTaskName())
            .append("taskNum", getTaskNum())
            .append("amount", getAmount())
            .append("allAmount", getAllAmount())
            .append("taskStatus", getTaskStatus())
            .append("taskCompleteTime", getTaskCompleteTime())
            .append("taskRequireCompleteTime", getTaskRequireCompleteTime())
            .append("merchantId", getMerchantId())
            .append("taxSourceId", getTaxSourceId())
            .append("remark", getRemark())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
			.append("takeTaskStatus", getTakeTaskStatus())
            .toString();
    }
}
