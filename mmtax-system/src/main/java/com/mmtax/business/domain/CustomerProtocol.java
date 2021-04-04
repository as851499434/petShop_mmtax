package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 员工协议表 tbl_customer_protocol
 *
 * @author meimiao
 * @date 2020-08-03
 */
@Table(name = "tbl_customer_protocol")
public class CustomerProtocol
{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 所属批次号 */
	private String batchNo;
	/** 签约员工id */
	private Integer cusEsignId;
	/** 员工编号 */
	private String thirdPartyUserId;
	/** 姓名 */
	private String name;
	/** 签约文档id */
	private Integer conInfoId;
	/** 签约文档名称 */
	private String fileName;
	/** 上次发送签约链接时间 */
	private Date sendSignUrlTime;
	/** 上次发送签约链接状态 0未发送 1已发送 */
	private Integer sendSignUrlStatus;
	/** 签约时间 */
	private Date signTime;
	/** 签约状态 0初始化 2:签署完成 3:失败 4:拒签 */
	private Integer signStatus;
	/**  报错原因*/
	private String comment;
	/** 签约备注*/
	private String remark;
	/** 签约到期时间 */
	private Date espireTime;
	/** 过期状态0-未过期 1-过期 */
	private Integer expireStatus;
	/** 对应流程表id */
	private Integer esignFlowId;
	/** 对应商户id */
	private Integer merchantId;
	/** 所属税源地id */
	private Integer taxSourceId;
	/** 个人账号id */
	private String accountId;
	/**  */
	private Integer providerId;
	/** api商户签约流水号 */
	private String merchantSerialNum;
	/** 签约流水号 */
	private String signSerialNum;
	/** 签约岗位id */
	private Integer postId;
	/** 签约岗位 */
	private String post;
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

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMerchantSerialNum() {
		return merchantSerialNum;
	}

	public void setMerchantSerialNum(String merchantSerialNum) {
		this.merchantSerialNum = merchantSerialNum;
	}

	public String getSignSerialNum() {
		return signSerialNum;
	}

	public void setSignSerialNum(String signSerialNum) {
		this.signSerialNum = signSerialNum;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getEsignFlowId() {
		return esignFlowId;
	}

	public void setEsignFlowId(Integer esignFlowId) {
		this.esignFlowId = esignFlowId;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setBatchNo(String batchNo)
	{
		this.batchNo = batchNo;
	}

	public String getBatchNo()
	{
		return batchNo;
	}

	public void setCusEsignId(Integer cusEsignId)
	{
		this.cusEsignId = cusEsignId;
	}

	public Integer getCusEsignId()
	{
		return cusEsignId;
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

	public void setSendSignUrlTime(Date sendSignUrlTime)
	{
		this.sendSignUrlTime = sendSignUrlTime;
	}

	public Date getSendSignUrlTime()
	{
		return sendSignUrlTime;
	}

	public void setSendSignUrlStatus(Integer sendSignUrlStatus)
	{
		this.sendSignUrlStatus = sendSignUrlStatus;
	}

	public Integer getSendSignUrlStatus()
	{
		return sendSignUrlStatus;
	}

	public void setSignTime(Date signTime)
	{
		this.signTime = signTime;
	}

	public Date getSignTime()
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

	public void setTaxSourceId(Integer taxSourceId)
	{
		this.taxSourceId = taxSourceId;
	}

	public Integer getTaxSourceId()
	{
		return taxSourceId;
	}

	public void setAccountId(String accountId)
	{
		this.accountId = accountId;
	}

	public String getAccountId()
	{
		return accountId;
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
				.append("batchNo", getBatchNo())
				.append("cusEsignId", getCusEsignId())
				.append("thirdPartyUserId", getThirdPartyUserId())
				.append("name", getName())
				.append("conInfoId", getConInfoId())
				.append("fileName", getFileName())
				.append("sendSignUrlTime", getSendSignUrlTime())
				.append("sendSignUrlStatus", getSendSignUrlStatus())
				.append("signTime", getSignTime())
				.append("signStatus", getSignStatus())
				.append("comment", getComment())
				.append("espireTime", getEspireTime())
				.append("expireStatus", getExpireStatus())
				.append("taxSourceId", getTaxSourceId())
				.append("accountId", getAccountId())
				.append("providerId", getProviderId())
				.append("reservedFieldOne", getReservedFieldOne())
				.append("reservedFieldTwo", getReservedFieldTwo())
				.append("reservedFieldThree", getReservedFieldThree())
				.append("createTime", getCreateTime())
				.append("updateTime", getUpdateTime())
				.append("remark", getRemark())
				.append("postId", getPostId())
				.append("post", getPost())
				.toString();
	}
}
