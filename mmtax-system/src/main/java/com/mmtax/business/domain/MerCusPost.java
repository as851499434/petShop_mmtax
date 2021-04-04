package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 商户灵工岗位表 tbl_mer_cus_post
 * 
 * @author meimiao
 * @date 2020-11-25
 */
@Table(name = "tbl_mer_cus_post")
public class MerCusPost
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 证件号码 */
	private String certificateNo;
				/** 商户id */
	private Integer merchantId;
				/** 岗位名称 */
	private String postName;
	/** 第一项中的空 */
	private String itemOne;
	/** 第二项中的空 */
	private String itemTwo;
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

	public String getItemOne() {
		return itemOne;
	}

	public void setItemOne(String itemOne) {
		this.itemOne = itemOne;
	}

	public String getItemTwo() {
		return itemTwo;
	}

	public void setItemTwo(String itemTwo) {
		this.itemTwo = itemTwo;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setCertificateNo(String certificateNo) 
	{
		this.certificateNo = certificateNo;
	}

	public String getCertificateNo()
	{
		return certificateNo;
	}

	public void setMerchantId(Integer merchantId) 
	{
		this.merchantId = merchantId;
	}

	public Integer getMerchantId()
	{
		return merchantId;
	}

	public void setPostName(String postName) 
	{
		this.postName = postName;
	}

	public String getPostName()
	{
		return postName;
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
            .append("certificateNo", getCertificateNo())
            .append("merchantId", getMerchantId())
            .append("postName", getPostName())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
