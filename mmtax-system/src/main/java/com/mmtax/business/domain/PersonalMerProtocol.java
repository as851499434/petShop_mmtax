package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 个体工商户协议表 tbl_personal_mer_protocol
 * 
 * @author meimiao
 * @date 2020-11-27
 */
@Table(name = "tbl_personal_mer_protocol")
public class PersonalMerProtocol
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 签约员工id */
	private Integer cusEsignId;
				/** 姓名 */
	private String name;
				/** 签约文档id */
	private Integer conInfoId;
				/** 签约文档名称 */
	private String fileName;
				/** 签约时间 */
	private String signTime;
				/** 签约状态 0初始化 2:签署完成 3:失败 4:拒签 */
	private Integer signStatus;
				/** 失败原因 */
	private String comment;
				/** 签约到期时间 */
	private Date espireTime;
				/** 过期状态0-未过期 1-过期 */
	private Integer expireStatus;
				/** 对应流程表id */
	private Integer esignFlowId;
				/** 所属税源地id */
	private Integer taxSourceId;
				/** 签约流水号 */
	private String signSerialNum;
				/** 对应个体工商户的申请编号 */
	private String applyNumber;
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

	public void setCusEsignId(Integer cusEsignId) 
	{
		this.cusEsignId = cusEsignId;
	}

	public Integer getCusEsignId()
	{
		return cusEsignId;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setConInfoId(Integer conInfoId) 
	{
		this.conInfoId = conInfoId;
	}

	public Integer getConInfoId()
	{
		return conInfoId;
	}

	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setSignTime(String signTime) 
	{
		this.signTime = signTime;
	}

	public String getSignTime()
	{
		return signTime;
	}

	public void setSignStatus(Integer signStatus) 
	{
		this.signStatus = signStatus;
	}

	public Integer getSignStatus()
	{
		return signStatus;
	}

	public void setComment(String comment) 
	{
		this.comment = comment;
	}

	public String getComment()
	{
		return comment;
	}

	public void setEspireTime(Date espireTime) 
	{
		this.espireTime = espireTime;
	}

	public Date getEspireTime()
	{
		return espireTime;
	}

	public void setExpireStatus(Integer expireStatus) 
	{
		this.expireStatus = expireStatus;
	}

	public Integer getExpireStatus()
	{
		return expireStatus;
	}

	public void setEsignFlowId(Integer esignFlowId) 
	{
		this.esignFlowId = esignFlowId;
	}

	public Integer getEsignFlowId()
	{
		return esignFlowId;
	}

	public void setTaxSourceId(Integer taxSourceId) 
	{
		this.taxSourceId = taxSourceId;
	}

	public Integer getTaxSourceId()
	{
		return taxSourceId;
	}

	public void setSignSerialNum(String signSerialNum) 
	{
		this.signSerialNum = signSerialNum;
	}

	public String getSignSerialNum()
	{
		return signSerialNum;
	}

	public void setApplyNumber(String applyNumber) 
	{
		this.applyNumber = applyNumber;
	}

	public String getApplyNumber()
	{
		return applyNumber;
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
            .append("cusEsignId", getCusEsignId())
            .append("name", getName())
            .append("conInfoId", getConInfoId())
            .append("fileName", getFileName())
            .append("signTime", getSignTime())
            .append("signStatus", getSignStatus())
            .append("comment", getComment())
            .append("espireTime", getEspireTime())
            .append("expireStatus", getExpireStatus())
            .append("esignFlowId", getEsignFlowId())
            .append("taxSourceId", getTaxSourceId())
            .append("signSerialNum", getSignSerialNum())
            .append("applyNumber", getApplyNumber())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
