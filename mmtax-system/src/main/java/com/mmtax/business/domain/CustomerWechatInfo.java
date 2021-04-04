package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 员工与员工微信关联表 tbl_customer_wechat_info
 * 
 * @author meimiao
 * @date 2020-09-11
 */
@Table(name = "tbl_customer_wechat_info")
public class CustomerWechatInfo
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 灵工id */
	private Integer customerId;
	/** 微信号 */
	private String wechatNumber;
	/** 微信名称 */
	private String wechatName;
	/** 登录手机号(实名认证) */
	private String loginMobile;
	/** 微信对应的openId */
	private String openId;
	/** 是否退出 0-未退出 1-已退出 */
	private Integer hasQuit;
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

	public Integer getHasQuit() {
		return hasQuit;
	}

	public void setHasQuit(Integer hasQuit) {
		this.hasQuit = hasQuit;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getLoginMobile() {
		return loginMobile;
	}

	public void setLoginMobile(String loginMobile) {
		this.loginMobile = loginMobile;
	}

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

	public void setWechatNumber(String wechatNumber) 
	{
		this.wechatNumber = wechatNumber;
	}

	public String getWechatNumber()
	{
		return wechatNumber;
	}

	public void setWechatName(String wechatName) 
	{
		this.wechatName = wechatName;
	}

	public String getWechatName()
	{
		return wechatName;
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
            .append("wechatNumber", getWechatNumber())
            .append("wechatName", getWechatName())
			.append("loginMobile", getLoginMobile())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("reservedFieldThree", getReservedFieldThree())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
