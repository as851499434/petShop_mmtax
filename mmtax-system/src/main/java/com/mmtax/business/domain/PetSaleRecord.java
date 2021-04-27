package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 销售宠物记录表 tbl_pet_sale_record
 * 
 * @author meimiao
 * @date 2021-04-27
 */
@Table(name = "tbl_pet_sale_record")
public class PetSaleRecord
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 宠物信息id */
	private Integer petInfoId;
	/** 售价 */
	private BigDecimal price;
	/** 成本 */
	private BigDecimal cost;
	/** 备注 */
	private String remake;
	/** 卖出标识 0 是 1 否 */
	private Integer status;
	/** 删除标识 0 未删除 1已删除 */
	private Integer delStatus;
	/**  */
	private Integer providerId;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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

	public void setPrice(BigDecimal price) 
	{
		this.price = price;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setCost(BigDecimal cost) 
	{
		this.cost = cost;
	}

	public BigDecimal getCost()
	{
		return cost;
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
            .append("price", getPrice())
            .append("cost", getCost())
            .append("remake", getRemake())
            .append("delStatus", getDelStatus())
            .append("providerId", getProviderId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
