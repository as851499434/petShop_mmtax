package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 商户对应的员工推广表 tbl_customer_promotion
 * 
 * @author meimiao
 * @date 2020-09-07
 */
@Table(name = "tbl_customer_promotion")
public class CustomerPromotion
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 员工id 对应tbl_customer_info中的id */
	private Integer customerId;
	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 区 */
	private String area;
	/** 街道 */
	private String street;
	/** 社区 */
	private String community;
	/** 单价 */
	private BigDecimal unitPrice;
	/** 数量 */
	private Integer quantity;
	/** 推广开始时间 */
	private String promotionStartTime;
	/** 推广结束时间 */
	private String promotionEndTime;
	/** 商户id */
	private Integer merchantId;
	/** 交易订单id */
	private Integer trxOrderId;
	/**  */
	private Integer providerId;
	/** 预留字段1 */
	private String reservedFieldOne;
	/** 预留字段2 */
	private String reservedFieldTwo;
	/** 预留字段3 */
	private String reservedFieldThree;
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

	public void setCustomerId(Integer customerId) 
	{
		this.customerId = customerId;
	}

	public Integer getCustomerId()
	{
		return customerId;
	}

	public void setProvince(String province) 
	{
		this.province = province;
	}

	public String getProvince()
	{
		return province;
	}

	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getCity()
	{
		return city;
	}

	public void setArea(String area) 
	{
		this.area = area;
	}

	public String getArea()
	{
		return area;
	}

	public void setStreet(String street) 
	{
		this.street = street;
	}

	public String getStreet()
	{
		return street;
	}

	public void setCommunity(String community) 
	{
		this.community = community;
	}

	public String getCommunity()
	{
		return community;
	}

	public void setUnitPrice(BigDecimal unitPrice) 
	{
		this.unitPrice = unitPrice;
	}

	public BigDecimal getUnitPrice()
	{
		return unitPrice;
	}

	public void setQuantity(Integer quantity) 
	{
		this.quantity = quantity;
	}

	public Integer getQuantity()
	{
		return quantity;
	}

	public void setPromotionStartTime(String promotionStartTime) 
	{
		this.promotionStartTime = promotionStartTime;
	}

	public String getPromotionStartTime()
	{
		return promotionStartTime;
	}

	public void setPromotionEndTime(String promotionEndTime) 
	{
		this.promotionEndTime = promotionEndTime;
	}

	public String getPromotionEndTime()
	{
		return promotionEndTime;
	}

	public void setMerchantId(Integer merchantId) 
	{
		this.merchantId = merchantId;
	}

	public Integer getMerchantId()
	{
		return merchantId;
	}

	public void setTrxOrderId(Integer trxOrderId) 
	{
		this.trxOrderId = trxOrderId;
	}

	public Integer getTrxOrderId()
	{
		return trxOrderId;
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
            .append("customerId", getCustomerId())
            .append("province", getProvince())
            .append("city", getCity())
            .append("area", getArea())
            .append("street", getStreet())
            .append("community", getCommunity())
            .append("unitPrice", getUnitPrice())
            .append("quantity", getQuantity())
            .append("promotionStartTime", getPromotionStartTime())
            .append("promotionEndTime", getPromotionEndTime())
            .append("merchantId", getMerchantId())
            .append("trxOrderId", getTrxOrderId())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
