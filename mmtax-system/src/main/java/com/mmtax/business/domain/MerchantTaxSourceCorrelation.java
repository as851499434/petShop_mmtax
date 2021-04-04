package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 商户税源地关联表 tbl_merchant_tax_source_correlation
 * 
 * @author meimiao
 * @date 2020-05-20
 */
@Table(name = "tbl_merchant_tax_source_correlation")
public class MerchantTaxSourceCorrelation
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 商户id */
	private Integer merchantId;
				/** 税源地id */
	private Integer taxSounrceId;
				/** 是否删除 1是 0否 */
	private Integer isDelete;
				/**  */
	private Integer providerId;
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

	public void setMerchantId(Integer merchantId) 
	{
		this.merchantId = merchantId;
	}

	public Integer getMerchantId()
	{
		return merchantId;
	}

	public void setTaxSounrceId(Integer taxSounrceId) 
	{
		this.taxSounrceId = taxSounrceId;
	}

	public Integer getTaxSounrceId()
	{
		return taxSounrceId;
	}

	public void setIsDelete(Integer isDelete) 
	{
		this.isDelete = isDelete;
	}

	public Integer getIsDelete()
	{
		return isDelete;
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
            .append("merchantId", getMerchantId())
            .append("taxSounrceId", getTaxSounrceId())
            .append("isDelete", getIsDelete())
            .append("providerId", getProviderId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
