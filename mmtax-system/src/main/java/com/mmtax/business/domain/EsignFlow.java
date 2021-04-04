package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 签约流程表 tbl_esign_flow
 *
 * @author meimiao
 * @date 2020-08-03
 */
@Table(name = "tbl_esign_flow")
public class EsignFlow
{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 签署员工表id */
	private Integer cusEsignId;
	/** 是否自动归档 0否 1是 */
	private Integer autoArchive;
	/** 文件主题 */
	private String businessScene;
	/** 回调通知地址 */
	private String noticeDeveloperUrl;
	/** 签署完成重定向地址,默认签署完成停在当前页面 */
	private String redirectUrl;
	/** 通知方式 0不需要 1短信 2邮件 */
	private Integer noticeType;
	/** 文件有效截止日期,毫秒，默认不失效；若时间到了设置的时间,则触发流程文件过期通知 */
	private Long contractValidity;
	/** 文件到期前，提前多少小时提醒续签,小时(时间区间：1小时——15天),默认不提醒 */
	private Integer contractRemind;
	/** 签署有效截止日期,毫秒,默认不失效 */
	private Integer signValidity;
	/** 流程id(e签宝) */
	private String flowId;
	/** 流程开始时间 */
	private String flowStartTime;
	/** 流程结束时间 */
	private String flowEndTime;
	/** 流程状态 0初始化 1开启中 2-已完成 3-已撤销 5-已过期 7-已拒签 */
	private Integer status;
	/** 流程说明 */
	private String flowDesc;
	/** 所属税源地id */
	private Integer taxSourceId;
	/**  */
	private Integer providerId;
	/**  */
	private String reservedFieldOne;
	/**  */
	private String reservedFieldTwo;
	/**  */
	private String reservedFieldThree;
	/**  */
	private String reservedFieldFour;
	/**  */
	private String reservedFieldFive;
	/** 记录有效性 0有效 1无效 */
	private Integer delStatus;
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

	public void setCusEsignId(Integer cusEsignId)
	{
		this.cusEsignId = cusEsignId;
	}

	public Integer getCusEsignId()
	{
		return cusEsignId;
	}

	public void setAutoArchive(Integer autoArchive)
	{
		this.autoArchive = autoArchive;
	}

	public Integer getAutoArchive()
	{
		return autoArchive;
	}

	public void setBusinessScene(String businessScene)
	{
		this.businessScene = businessScene;
	}

	public String getBusinessScene()
	{
		return businessScene;
	}

	public void setNoticeDeveloperUrl(String noticeDeveloperUrl)
	{
		this.noticeDeveloperUrl = noticeDeveloperUrl;
	}

	public String getNoticeDeveloperUrl()
	{
		return noticeDeveloperUrl;
	}

	public void setRedirectUrl(String redirectUrl)
	{
		this.redirectUrl = redirectUrl;
	}

	public String getRedirectUrl()
	{
		return redirectUrl;
	}

	public void setNoticeType(Integer noticeType)
	{
		this.noticeType = noticeType;
	}

	public Integer getNoticeType()
	{
		return noticeType;
	}

	public void setContractValidity(Long contractValidity)
	{
		this.contractValidity = contractValidity;
	}

	public Long getContractValidity()
	{
		return contractValidity;
	}

	public void setContractRemind(Integer contractRemind)
	{
		this.contractRemind = contractRemind;
	}

	public Integer getContractRemind()
	{
		return contractRemind;
	}

	public void setSignValidity(Integer signValidity)
	{
		this.signValidity = signValidity;
	}

	public Integer getSignValidity()
	{
		return signValidity;
	}

	public void setFlowId(String flowId)
	{
		this.flowId = flowId;
	}

	public String getFlowId()
	{
		return flowId;
	}

	public void setFlowStartTime(String flowStartTime)
	{
		this.flowStartTime = flowStartTime;
	}

	public String getFlowStartTime()
	{
		return flowStartTime;
	}

	public void setFlowEndTime(String flowEndTime)
	{
		this.flowEndTime = flowEndTime;
	}

	public String getFlowEndTime()
	{
		return flowEndTime;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setFlowDesc(String flowDesc)
	{
		this.flowDesc = flowDesc;
	}

	public String getFlowDesc()
	{
		return flowDesc;
	}

	public void setTaxSourceId(Integer taxSourceId)
	{
		this.taxSourceId = taxSourceId;
	}

	public Integer getTaxSourceId()
	{
		return taxSourceId;
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

	public void setReservedFieldFour(String reservedFieldFour)
	{
		this.reservedFieldFour = reservedFieldFour;
	}

	public String getReservedFieldFour()
	{
		return reservedFieldFour;
	}

	public void setReservedFieldFive(String reservedFieldFive)
	{
		this.reservedFieldFive = reservedFieldFive;
	}

	public String getReservedFieldFive()
	{
		return reservedFieldFive;
	}

	public void setDelStatus(Integer delStatus)
	{
		this.delStatus = delStatus;
	}

	public Integer getDelStatus()
	{
		return delStatus;
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
				.append("cusEsignId", getCusEsignId())
				.append("autoArchive", getAutoArchive())
				.append("businessScene", getBusinessScene())
				.append("noticeDeveloperUrl", getNoticeDeveloperUrl())
				.append("redirectUrl", getRedirectUrl())
				.append("noticeType", getNoticeType())
				.append("contractValidity", getContractValidity())
				.append("contractRemind", getContractRemind())
				.append("signValidity", getSignValidity())
				.append("flowId", getFlowId())
				.append("flowStartTime", getFlowStartTime())
				.append("flowEndTime", getFlowEndTime())
				.append("status", getStatus())
				.append("flowDesc", getFlowDesc())
				.append("taxSourceId", getTaxSourceId())
				.append("providerId", getProviderId())
				.append("reservedFieldOne", getReservedFieldOne())
				.append("reservedFieldTwo", getReservedFieldTwo())
				.append("reservedFieldThree", getReservedFieldThree())
				.append("reservedFieldFour", getReservedFieldFour())
				.append("reservedFieldFive", getReservedFieldFive())
				.append("delStatus", getDelStatus())
				.append("createTime", getCreateTime())
				.append("updateTime", getUpdateTime())
				.toString();
	}
}
