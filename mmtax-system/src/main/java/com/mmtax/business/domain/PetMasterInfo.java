package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 宠物主人表 tbl_pet_master_info
 * 
 * @author meimiao
 * @date 2021-04-10
 */
@Table(name = "tbl_pet_master_info")
public class PetMasterInfo
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 姓名 */
	private String name;
	/** 年龄 */
	private Integer age;
	/** 性别 0 男 1 女 2 未知 */
	private String sex;
	/** 手机号 */
	private String phonenumber;
	/** 邮箱地址 */
	private String email;
	/** 居住地址 */
	private String adress;
	/** 居住地址 */
	private Integer delStatus;
	/**  */
	private Integer providerId;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;

	public Integer getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}

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

	public void setAge(Integer age) 
	{
		this.age = age;
	}

	public Integer getAge()
	{
		return age;
	}

	public void setSex(String sex) 
	{
		this.sex = sex;
	}

	public String getSex()
	{
		return sex;
	}

	public void setPhonenumber(String phonenumber) 
	{
		this.phonenumber = phonenumber;
	}

	public String getPhonenumber()
	{
		return phonenumber;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getEmail()
	{
		return email;
	}

	public void setAdress(String adress) 
	{
		this.adress = adress;
	}

	public String getAdress()
	{
		return adress;
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
            .append("age", getAge())
            .append("sex", getSex())
            .append("phonenumber", getPhonenumber())
            .append("email", getEmail())
			.append("delStatus", getDelStatus())
            .append("adress", getAdress())
            .append("providerId", getProviderId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
