package com.mmtax.business.domain;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 个体工商户申请流程日志表 tbl_per_mer_apply_schedule
 * 
 * @author meimiao
 * @date 2020-11-30
 */
@Table(name = "tbl_per_mer_apply_schedule")
public class PerMerApplySchedule
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 申请id */
	private Integer perMerId;
				/** 申请编号 */
	private String applyNumber;
				/** 时间 */
	private String submitTime;
				/** 动作(写死) */
	private String action;
				/** 第几个步骤 0-未知 1-提交资料审核 2-办理营业执照 */
	private Integer type;
				/**  */
	private Integer providerId;
	/** 预留字段一 */
	private String reservedFieldOne;
	/** 预留字段二 */
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

	public void setPerMerId(Integer perMerId) 
	{
		this.perMerId = perMerId;
	}

	public Integer getPerMerId()
	{
		return perMerId;
	}

	public void setApplyNumber(String applyNumber) 
	{
		this.applyNumber = applyNumber;
	}

	public String getApplyNumber()
	{
		return applyNumber;
	}

	public void setSubmitTime(String submitTime) 
	{
		this.submitTime = submitTime;
	}

	public String getSubmitTime()
	{
		return submitTime;
	}

	public void setAction(String action) 
	{
		this.action = action;
	}

	public String getAction()
	{
		return action;
	}

	public void setType(Integer type)
	{
		this.type = type;
	}

	public Integer getType()
	{
		return type;
	}

	public void setProviderId(Integer providerId) 
	{
		this.providerId = providerId;
	}

	public Integer getProviderId()
	{
		return providerId;
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

	public String getReservedFieldOne() {
		return reservedFieldOne;
	}

	public void setReservedFieldOne(String reservedFieldOne) {
		this.reservedFieldOne = reservedFieldOne;
	}

	public String getReservedFieldTwo() {
		return reservedFieldTwo;
	}

	public void setReservedFieldTwo(String reservedFieldTwo) {
		this.reservedFieldTwo = reservedFieldTwo;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("perMerId", getPerMerId())
            .append("applyNumber", getApplyNumber())
            .append("submitTime", getSubmitTime())
            .append("action", getAction())
            .append("type", getType())
            .append("providerId", getProviderId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
