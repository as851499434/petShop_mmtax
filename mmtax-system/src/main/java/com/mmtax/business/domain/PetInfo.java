package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 宠物表 tbl_pet_info
 * 
 * @author meimiao
 * @date 2021-04-10
 */
@Table(name = "tbl_pet_info")
public class PetInfo
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 主人id */
	private Integer masterId;
	/** 宠物名字 */
	private String petName;
	/** 宠物种类 */
	private String petType;
	/** 宠物性别 */
	private Integer petSex;
	/** 宠物年龄 */
	private Integer petAge;
	/** 宠物信息类型 0 店养宠物 1 医疗宠物 2 销售宠物 3寄养宠物 */
	private Integer petInfoType;
	/** 照片 */
	private String photo;
	/** 备注 */
	private String remake;
	/** 删除状态 0 未删除 1 已删除 */
	private String delStatus;
	/**  */
	private Integer providerId;
	/** 创建时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;

	public Integer getMasterId() {
		return masterId;
	}

	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}

	public void setPetName(String petName) 
	{
		this.petName = petName;
	}

	public String getPetName()
	{
		return petName;
	}

	public void setPetType(String petType) 
	{
		this.petType = petType;
	}

	public String getPetType()
	{
		return petType;
	}

	public void setPetSex(Integer petSex) 
	{
		this.petSex = petSex;
	}

	public Integer getPetSex()
	{
		return petSex;
	}

	public void setPetAge(Integer petAge) 
	{
		this.petAge = petAge;
	}

	public Integer getPetAge()
	{
		return petAge;
	}

	public void setPetInfoType(Integer petInfoType) 
	{
		this.petInfoType = petInfoType;
	}

	public Integer getPetInfoType()
	{
		return petInfoType;
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

	public void setDelStatus(String delStatus) 
	{
		this.delStatus = delStatus;
	}

	public String getDelStatus()
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
			.append("masterId", getMasterId())
            .append("petName", getPetName())
            .append("petType", getPetType())
            .append("petSex", getPetSex())
            .append("petAge", getPetAge())
            .append("petInfoType", getPetInfoType())
            .append("photo", getPhoto())
            .append("remake", getRemake())
            .append("delStatus", getDelStatus())
            .append("providerId", getProviderId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
