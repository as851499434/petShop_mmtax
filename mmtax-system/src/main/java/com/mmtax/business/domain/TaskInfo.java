package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 任务初始化记录表 tbl_task_info
 * 
 * @author meimiao
 * @date 2020-10-13
 */
@Table(name = "tbl_task_info")
@ApiModel("任务初始化记录")
public class TaskInfo
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty("任务Id")
	private Integer id;
	/** 任务名 */
	@ApiModelProperty("任务名")
	private String taskName;
	/** 任务简介 */
	@ApiModelProperty("任务简介")
	private String taskInfo;
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
	@ApiModelProperty("创建时间")
	private Date createTime;
	/**  */
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
            .append("taskName", getTaskName())
            .append("taskInfo", getTaskInfo())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
