package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 任务接单记录表 tbl_customer_task
 * 
 * @author meimiao
 * @date 2020-10-15
 */
@Table(name = "tbl_customer_task")
@ApiModel("任务接单记录")
public class CustomerTask
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty("主键id")
	private Integer id;
					/** 详细任务id */
	@ApiModelProperty("详细任务id")
	private Integer taskDetailId;
				/** 接任务人id */
	@ApiModelProperty("接任务人id")
	private Integer customerId;
				/** 接任务人的姓名 */
	@ApiModelProperty("接任务人的姓名")
	private String name;
				/** 接任务人的联系电话 */
	@ApiModelProperty("接任务人的联系电话")
	private String mobileNo;
				/** 接任务人的身份证号 */
	@ApiModelProperty("接任务人的身份证号")
	private String certificateNo;
				/** 备注 */
	@ApiModelProperty("备注")
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
	
	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setTaskDetailId(Integer taskDetailId) 
	{
		this.taskDetailId = taskDetailId;
	}

	public Integer getTaskDetailId()
	{
		return taskDetailId;
	}

	public void setCustomerId(Integer customerId)
	{
		this.customerId = customerId;
	}

	public Integer getCustomerId()
	{
		return customerId;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setMobileNo(String mobileNo) 
	{
		this.mobileNo = mobileNo;
	}

	public String getMobileNo()
	{
		return mobileNo;
	}

	public void setCertificateNo(String certificateNo) 
	{
		this.certificateNo = certificateNo;
	}

	public String getCertificateNo()
	{
		return certificateNo;
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
            .append("taskDetailId", getTaskDetailId())
            .append("customerId", getCustomerId())
            .append("name", getName())
            .append("mobileNo", getMobileNo())
            .append("certificateNo", getCertificateNo())
            .append("remark", getRemark())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
