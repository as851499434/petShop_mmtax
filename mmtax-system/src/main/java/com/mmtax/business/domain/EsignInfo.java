package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 易签宝税源地关联表 tbl_esign_info
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Table(name = "tbl_esign_info")
public class EsignInfo
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 税源地id */
	private Integer taxSourceId;
				/** 关联易签宝appId */
	private String appId;
				/** 关联易签宝appSecret */
	private String appSecret;
				/**  */
	private Integer providerId;
				/**  */
	private String reservedFieldOne;
				/**  */
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

	public void setTaxSourceId(Integer taxSourceId) 
	{
		this.taxSourceId = taxSourceId;
	}

	public Integer getTaxSourceId()
	{
		return taxSourceId;
	}

	public void setAppId(String appId) 
	{
		this.appId = appId;
	}

	public String getAppId()
	{
		return appId;
	}

	public void setAppSecret(String appSecret) 
	{
		this.appSecret = appSecret;
	}

	public String getAppSecret()
	{
		return appSecret;
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
            .append("taxSourceId", getTaxSourceId())
            .append("appId", getAppId())
            .append("appSecret", getAppSecret())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
