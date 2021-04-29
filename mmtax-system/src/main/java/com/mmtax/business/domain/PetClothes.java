package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 宠物服饰表 tbl_pet_clothes
 * 
 * @author meimiao
 * @date 2021-04-29
 */
@Table(name = "tbl_pet_clothes")
public class PetClothes
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 名称 */
	private String name;
	/** 数量 */
	private Integer number;
	/** 价格 */
	private BigDecimal price;
	/** 成本 */
	private BigDecimal cost;
	/**  */
	private String photo;
	/** 备注 */
	private String remake;
	/** 厂家 */
	private String factory;
	/** 生产日期 */
	private String productionTime;
	/** 保质期/天 */
	private Integer shelfLife;
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

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setNumber(Integer number) 
	{
		this.number = number;
	}

	public Integer getNumber()
	{
		return number;
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

	public void setPhoto(String photo) 
	{
		this.photo = photo;
	}

	public String getPhoto()
	{
		return photo;
	}

	public void setRemake(String remake) 
	{
		this.remake = remake;
	}

	public String getRemake()
	{
		return remake;
	}

	public void setFactory(String factory) 
	{
		this.factory = factory;
	}

	public String getFactory()
	{
		return factory;
	}

	public void setProductionTime(String productionTime) 
	{
		this.productionTime = productionTime;
	}

	public String getProductionTime()
	{
		return productionTime;
	}

	public void setShelfLife(Integer shelfLife) 
	{
		this.shelfLife = shelfLife;
	}

	public Integer getShelfLife()
	{
		return shelfLife;
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
            .append("name", getName())
            .append("number", getNumber())
            .append("price", getPrice())
            .append("cost", getCost())
            .append("photo", getPhoto())
            .append("remake", getRemake())
            .append("factory", getFactory())
            .append("productionTime", getProductionTime())
            .append("shelfLife", getShelfLife())
            .append("delStatus", getDelStatus())
            .append("providerId", getProviderId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
