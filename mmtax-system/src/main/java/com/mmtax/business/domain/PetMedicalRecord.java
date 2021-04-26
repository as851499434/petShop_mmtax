package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 医疗宠物病历表 tbl_pet_medical_record
 * 
 * @author meimiao
 * @date 2021-04-26
 */
@Table(name = "tbl_pet_medical_record")
public class PetMedicalRecord
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 宠物信息id */
	private Integer petInfoId;
	/** 疾病 */
	private String disease;
	/** 备注 */
	private String remake;
	/** 治疗方法 */
	private String method;
	/** 删除标识0-未删除1-已删除 */
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

	public void setPetInfoId(Integer petInfoId) 
	{
		this.petInfoId = petInfoId;
	}

	public Integer getPetInfoId()
	{
		return petInfoId;
	}

	public void setDisease(String disease) 
	{
		this.disease = disease;
	}

	public String getDisease()
	{
		return disease;
	}

	public void setRemake(String remake) 
	{
		this.remake = remake;
	}

	public String getRemake()
	{
		return remake;
	}

	public void setMethod(String method) 
	{
		this.method = method;
	}

	public String getMethod()
	{
		return method;
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
            .append("petInfoId", getPetInfoId())
            .append("disease", getDisease())
            .append("remake", getRemake())
            .append("method", getMethod())
            .append("delStatus", getDelStatus())
            .append("providerId", getProviderId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
