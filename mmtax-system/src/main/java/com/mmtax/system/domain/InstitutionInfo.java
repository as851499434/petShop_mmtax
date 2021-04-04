package com.mmtax.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 机构表 institution_info
 * 
 * @author meimiao
 * @date 2019-05-30
 */
@Table(name = "institution_info")
public class InstitutionInfo
{
	private static final long serialVersionUID = 1L;
	

	/**  */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	/** 机构编码 */
	private Integer providerId;


	/** 机构名称 */
	private String instituteName;


	/** 创建时间 */
	private Date createTime;


	/** 修改时间 */
	private Date updateTime;


	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}

	public void setProviderId(Integer providerId) 
	{
		this.providerId = providerId;
	}

	public Integer getProviderId() 
	{
		return providerId;
	}

	public void setInstituteName(String instituteName) 
	{
		this.instituteName = instituteName;
	}

	public String getInstituteName() 
	{
		return instituteName;
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
            .append("providerId", getProviderId())
            .append("instituteName", getInstituteName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
