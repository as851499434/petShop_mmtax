package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 机构(e签宝)表 tbl_esign_tax_source
 * 
 * @author meimiao
 * @date 2020-08-05
 */
@Table(name = "tbl_esign_tax_source")
public class EsignTaxSource
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 机构编号 */
	private String thirdPartyUserId;
				/** 机构名 */
	private String name;
				/** 证件类型,取数据字典 */
	private String idType;
				/** 证件号码 */
	private String idNumber;
				/** 个人账号表id */
	private Integer cusEsignId;
				/** 对应个人账号id(e签宝) */
	private String accountId;
				/** 所属税源地id */
	private Integer taxSourceId;
				/** 0未开户 1已开户 2已注销 */
	private Integer status;
	/** 静默签署状态 0-否 1-是 */
	private Integer autoSignStatus;
				/** 机构账号id(e签宝) */
	private String orgId;
				/** 印章颜色 RED-红色 BLUE-蓝色 BLACK-黑色 */
	private String color;
				/** 印章模板类型 见数据字典 */
	private String type;
				/** 印章状态 0未创建 1已创建 2已删除 */
	private Integer sealStatus;
				/** 印章fileKey */
	private String fileKey;
				/** 印章id */
	private String sealId;
	/** 备注 */
	private String comment;
				/**  */
	private Integer providerId;
				/** 备注 */
	private String reservedFieldOne;
				/**  */
	private String reservedFieldTwo;
				/**  */
	private String reservedFieldThree;
				/**  */
	private Date createTime;
				/**  */
	private Date updateTime;

	public Integer getAutoSignStatus() {
		return autoSignStatus;
	}

	public void setAutoSignStatus(Integer autoSignStatus) {
		this.autoSignStatus = autoSignStatus;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setThirdPartyUserId(String thirdPartyUserId) 
	{
		this.thirdPartyUserId = thirdPartyUserId;
	}

	public String getThirdPartyUserId()
	{
		return thirdPartyUserId;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setIdType(String idType) 
	{
		this.idType = idType;
	}

	public String getIdType()
	{
		return idType;
	}

	public void setIdNumber(String idNumber) 
	{
		this.idNumber = idNumber;
	}

	public String getIdNumber()
	{
		return idNumber;
	}

	public void setCusEsignId(Integer cusEsignId) 
	{
		this.cusEsignId = cusEsignId;
	}

	public Integer getCusEsignId()
	{
		return cusEsignId;
	}

	public void setAccountId(String accountId) 
	{
		this.accountId = accountId;
	}

	public String getAccountId()
	{
		return accountId;
	}

	public void setTaxSourceId(Integer taxSourceId) 
	{
		this.taxSourceId = taxSourceId;
	}

	public Integer getTaxSourceId()
	{
		return taxSourceId;
	}

	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setOrgId(String orgId) 
	{
		this.orgId = orgId;
	}

	public String getOrgId()
	{
		return orgId;
	}

	public void setColor(String color) 
	{
		this.color = color;
	}

	public String getColor()
	{
		return color;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType()
	{
		return type;
	}

	public void setSealStatus(Integer sealStatus) 
	{
		this.sealStatus = sealStatus;
	}

	public Integer getSealStatus()
	{
		return sealStatus;
	}

	public void setFileKey(String fileKey) 
	{
		this.fileKey = fileKey;
	}

	public String getFileKey()
	{
		return fileKey;
	}

	public void setSealId(String sealId) 
	{
		this.sealId = sealId;
	}

	public String getSealId()
	{
		return sealId;
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
            .append("thirdPartyUserId", getThirdPartyUserId())
            .append("name", getName())
            .append("idType", getIdType())
            .append("idNumber", getIdNumber())
            .append("cusEsignId", getCusEsignId())
            .append("accountId", getAccountId())
            .append("taxSourceId", getTaxSourceId())
            .append("status", getStatus())
            .append("orgId", getOrgId())
            .append("color", getColor())
            .append("type", getType())
            .append("sealStatus", getSealStatus())
            .append("fileKey", getFileKey())
            .append("sealId", getSealId())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
