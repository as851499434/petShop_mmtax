package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 工单表 tbl_work_order
 * 
 * @author meimiao
 * @date 2020-12-10
 */
@Table(name = "tbl_work_order")
public class WorkOrder
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 工单编号 */
	private String workOrderSerialNumber;
				/** 入网申请编号 */
	private String applyNumber;
				/** 名字 */
	private String name;
				/** 身份证号 */
	private String certificateNo;
				/** 标题 */
	private String title;
				/** 内容 */
	private String content;
				/** 0 已发布，1已反馈，2已处理，3关闭工单 */
	private Integer status;
				/** 文本 */
	private String txt;
				/** pdf地址 */
	private String pdfAddress;
				/** 图片地址 */
	private String imgAddress;
				/** 视频地址 */
	private String videoAddress;
				/** 发布时间 */
	private Date publishTime;
				/** 反馈时间 */
	private Date feedbackTime;
				/** 0未删除，1已删除 */
	private Integer delStatus;
				/** 税源地id */
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

	public void setWorkOrderSerialNumber(String workOrderSerialNumber) 
	{
		this.workOrderSerialNumber = workOrderSerialNumber;
	}

	public String getWorkOrderSerialNumber()
	{
		return workOrderSerialNumber;
	}

	public void setApplyNumber(String applyNumber) 
	{
		this.applyNumber = applyNumber;
	}

	public String getApplyNumber()
	{
		return applyNumber;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setCertificateNo(String certificateNo) 
	{
		this.certificateNo = certificateNo;
	}

	public String getCertificateNo()
	{
		return certificateNo;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}

	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent()
	{
		return content;
	}

	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setTxt(String txt) 
	{
		this.txt = txt;
	}

	public String getTxt()
	{
		return txt;
	}

	public void setPdfAddress(String pdfAddress) 
	{
		this.pdfAddress = pdfAddress;
	}

	public String getPdfAddress()
	{
		return pdfAddress;
	}

	public void setImgAddress(String imgAddress) 
	{
		this.imgAddress = imgAddress;
	}

	public String getImgAddress()
	{
		return imgAddress;
	}

	public void setVideoAddress(String videoAddress) 
	{
		this.videoAddress = videoAddress;
	}

	public String getVideoAddress()
	{
		return videoAddress;
	}

	public void setPublishTime(Date publishTime) 
	{
		this.publishTime = publishTime;
	}

	public Date getPublishTime()
	{
		return publishTime;
	}

	public void setFeedbackTime(Date feedbackTime) 
	{
		this.feedbackTime = feedbackTime;
	}

	public Date getFeedbackTime()
	{
		return feedbackTime;
	}

	public void setDelStatus(Integer delStatus) 
	{
		this.delStatus = delStatus;
	}

	public Integer getDelStatus()
	{
		return delStatus;
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
            .append("workOrderSerialNumber", getWorkOrderSerialNumber())
            .append("applyNumber", getApplyNumber())
            .append("name", getName())
            .append("certificateNo", getCertificateNo())
            .append("title", getTitle())
            .append("content", getContent())
            .append("status", getStatus())
            .append("txt", getTxt())
            .append("pdfAddress", getPdfAddress())
            .append("imgAddress", getImgAddress())
            .append("videoAddress", getVideoAddress())
            .append("publishTime", getPublishTime())
            .append("feedbackTime", getFeedbackTime())
            .append("delStatus", getDelStatus())
            .append("taxSourceId", getTaxSourceId())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
