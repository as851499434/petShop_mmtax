package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 宠物服务记录表 tbl_pet_service_record
 * 
 * @author meimiao
 * @date 2021-04-28
 */
@Table(name = "tbl_pet_service_record")
public class PetServiceRecord
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**  */
	private Integer petInfoId;
	/** 服务 */
	private String service;
	/** 费用 */
	private BigDecimal price;
	/** 状态 */
	private Integer status;
	/** 备注 */
	private String remake;
	/** 删除状态 */
	private Integer delStatus;
	/**  */
	private Integer providerId;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setPetInfoId(Integer petInfoId) 
	{
		this.petInfoId = petInfoId;
	}

	public Integer getPetInfoId()
	{
		return petInfoId;
	}

	public void setService(String service) 
	{
		this.service = service;
	}

	public String getService()
	{
		return service;
	}

	public void setPrice(BigDecimal price) 
	{
		this.price = price;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setRemake(String remake) 
	{
		this.remake = remake;
	}

	public String getRemake()
	{
		return remake;
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
            .append("petInfoId", getPetInfoId())
            .append("service", getService())
            .append("price", getPrice())
            .append("status", getStatus())
            .append("remake", getRemake())
            .append("delStatus", getDelStatus())
            .append("providerId", getProviderId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
