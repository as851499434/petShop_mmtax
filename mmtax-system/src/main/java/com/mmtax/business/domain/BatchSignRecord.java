package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 批量签约记录表 tbl_batch_sign_record
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Table(name = "tbl_batch_sign_record")
public class BatchSignRecord
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 平台订单号 */
	private String trxExternalNo;
				/** 批次号 */
	private String batchNo;
				/** 总记录 */
	private Integer signCount;
				/** 实际通过记录 */
	private Integer actualCount;
				/** 创建人 */
	private String creater;
				/** 操作人 */
	private String operator;
				/** 0-未处理1-已处理 */
	private Integer status;
				/** 商户id */
	private Integer merchantId;
				/** 签约岗位id */
	private Integer postId;
				/** 签约岗位 */
	private String post;
				/**  */
	private Integer providerId;
				/**  */
	private Date createTime;
				/**  */
	private Date updateTime;

	public String getPost() {
		return post;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setTrxExternalNo(String trxExternalNo) 
	{
		this.trxExternalNo = trxExternalNo;
	}

	public String getTrxExternalNo()
	{
		return trxExternalNo;
	}

	public void setBatchNo(String batchNo) 
	{
		this.batchNo = batchNo;
	}

	public String getBatchNo()
	{
		return batchNo;
	}

	public void setSignCount(Integer signCount) 
	{
		this.signCount = signCount;
	}

	public Integer getSignCount()
	{
		return signCount;
	}

	public void setActualCount(Integer actualCount) 
	{
		this.actualCount = actualCount;
	}

	public Integer getActualCount()
	{
		return actualCount;
	}

	public void setCreater(String creater) 
	{
		this.creater = creater;
	}

	public String getCreater()
	{
		return creater;
	}

	public void setOperator(String operator) 
	{
		this.operator = operator;
	}

	public String getOperator()
	{
		return operator;
	}

	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setMerchantId(Integer merchantId) 
	{
		this.merchantId = merchantId;
	}

	public Integer getMerchantId()
	{
		return merchantId;
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
            .append("trxExternalNo", getTrxExternalNo())
            .append("batchNo", getBatchNo())
            .append("signCount", getSignCount())
            .append("actualCount", getActualCount())
            .append("creater", getCreater())
            .append("operator", getOperator())
            .append("status", getStatus())
            .append("merchantId", getMerchantId())
            .append("providerId", getProviderId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
			.append("post", getPost())
			.append("postId", getPostId())
            .toString();
    }
}
