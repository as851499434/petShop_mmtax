package com.mmtax.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 员工表 tbl_customer_info
 *
 * @author meimiao
 * @date 2020-07-13
 */
@Table(name = "tbl_customer_info")
public class CustomerInfo
{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 员工编号 */
	private String customerNo;
	/** 昵称 */
	private String memberName;
	/** 真名 */
	private String realName;
	/** 证件类型,取数据字典 */
	private String certificateType;
	/** 证件号码 */
	private String certificateNo;
	/** 手机号 */
	private String mobile;
	/** 邮箱 */
	private String email;
	/** 提现密码 */
	private String withdrawPass;
	/** 盐 */
	private String salt;
	/** 所属商户id */
	private Integer merchantId;
	/** 是否认证 0否 1是（已认证通过） */
	private Integer verifyStatus;
	/** 是否有效 0否 1是（成功开户即为有效） */
	private Integer effective;
	/**当月使用费率 0普通 1大额 */
	private Integer monthUseRate;
	/** 任务名称 */
	private String taskName;
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

	/** 0为不预警 1为预警 */
	private Integer customerWarn;

	public String getWithdrawPass() {
		return withdrawPass;
	}

	public void setWithdrawPass(String withdrawPass) {
		this.withdrawPass = withdrawPass;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getMonthUseRate() {
		return monthUseRate;
	}

	public void setMonthUseRate(Integer monthUseRate) {
		this.monthUseRate = monthUseRate;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setCustomerNo(String customerNo)
	{
		this.customerNo = customerNo;
	}

	public String getCustomerNo()
	{
		return customerNo;
	}

	public void setMemberName(String memberName)
	{
		this.memberName = memberName;
	}

	public String getMemberName()
	{
		return memberName;
	}

	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	public String getRealName()
	{
		return realName;
	}

	public void setCertificateType(String certificateType)
	{
		this.certificateType = certificateType;
	}

	public String getCertificateType()
	{
		return certificateType;
	}

	public void setCertificateNo(String certificateNo)
	{
		this.certificateNo = certificateNo;
	}

	public String getCertificateNo()
	{
		return certificateNo;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getEmail()
	{
		return email;
	}

	public void setMerchantId(Integer merchantId)
	{
		this.merchantId = merchantId;
	}

	public Integer getMerchantId()
	{
		return merchantId;
	}

	public void setVerifyStatus(Integer verifyStatus)
	{
		this.verifyStatus = verifyStatus;
	}

	public Integer getVerifyStatus()
	{
		return verifyStatus;
	}

	public void setEffective(Integer effective)
	{
		this.effective = effective;
	}

	public Integer getEffective()
	{
		return effective;
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

	public void setCustomerWarn(Integer customerWarn)
	{
		this.customerWarn = customerWarn;
	}

	public Integer getCustomerWarn()
	{
		return customerWarn;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("customerNo", getCustomerNo())
				.append("memberName", getMemberName())
				.append("realName", getRealName())
				.append("certificateType", getCertificateType())
				.append("certificateNo", getCertificateNo())
				.append("mobile", getMobile())
				.append("email", getEmail())
				.append("merchantId", getMerchantId())
				.append("verifyStatus", getVerifyStatus())
				.append("effective", getEffective())
				.append("customerWarn", getCustomerWarn())
				.append("providerId", getProviderId())
				.append("reservedFieldOne", getReservedFieldOne())
				.append("reservedFieldTwo", getReservedFieldTwo())
				.append("reservedFieldThree", getReservedFieldThree())
				.append("createTime", getCreateTime())
				.append("updateTime", getUpdateTime())
				.toString();
	}
}
