package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 任务记录表 tbl_merchant_task_info
 * 
 * @author meimiao
 * @date 2020-10-13
 */
@ApiModel("任务信息")
@Table(name = "tbl_merchant_task_info")
public class MerchantTaskInfo
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty("任务Id")
	private Integer id;
	/** 商户id */
	@ApiModelProperty("商户id")
	private Integer merchantId;
	/** 任务初始化id */
	@ApiModelProperty("任务初始化id")
	private Integer taskId;
	/** 任务名 */
	@ApiModelProperty("任务名")
	private String taskName;
	/** 任务简介 */
	@ApiModelProperty("任务简介")
	private String taskInfo;
	/** 删除标识0-未删除1-已删除 */
	@ApiModelProperty("删除标识0-未删除1-已删除")
	private Integer delStatus;
	/**  */
	private Integer providerId;
	/** 预留字段1 */
	@ApiModelProperty("预留字段1")
	private String reservedFieldOne;
	/** 预留字段2 */
	@ApiModelProperty("预留字段2")
	private String reservedFieldTwo;
	/** 预留字段3 */
	@ApiModelProperty("预留字段3")
	private String reservedFieldThree;
	/**  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("创建时间")
	private Date createTime;
	/**  */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("修改时间")
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

	public void setTaskId(Integer taskId) 
	{
		this.taskId = taskId;
	}

	public Integer getTaskId()
	{
		return taskId;
	}

	public void setTaskName(String taskName) 
	{
		this.taskName = taskName;
	}

	public String getTaskName()
	{
		return taskName;
	}

	public void setTaskInfo(String taskInfo) 
	{
		this.taskInfo = taskInfo;
	}

	public String getTaskInfo()
	{
		return taskInfo;
	}

	public void setDelStatus(Integer delStatus) 
	{
		this.delStatus = delStatus;
	}

	public Integer getDelStatus()
	{
		return delStatus;
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
            .append("merchantId", getMerchantId())
            .append("taskId", getTaskId())
            .append("taskName", getTaskName())
            .append("taskInfo", getTaskInfo())
            .append("delStatus", getDelStatus())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
