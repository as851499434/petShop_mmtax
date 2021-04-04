package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 签约模板控件表 tbl_template_component
 * 
 * @author meimiao
 * @date 2020-07-30
 */
@Table(name = "tbl_template_component")
public class TemplateComponent
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 控件名称 */
	private String componentName;
				/** 控件id(e签宝) */
	private String componentId;
				/** 所属模板表id */
	private Integer esignTemplateId;
				/** 模板id(e签宝) */
	private String templateId;
				/**  */
	private Integer providerId;
				/**  */
	private String reservedFieldOne;
				/**  */
	private String reservedFieldTwo;
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

	public void setComponentName(String componentName) 
	{
		this.componentName = componentName;
	}

	public String getComponentName()
	{
		return componentName;
	}

	public void setComponentId(String componentId) 
	{
		this.componentId = componentId;
	}

	public String getComponentId()
	{
		return componentId;
	}

	public void setEsignTemplateId(Integer esignTemplateId) 
	{
		this.esignTemplateId = esignTemplateId;
	}

	public Integer getEsignTemplateId()
	{
		return esignTemplateId;
	}

	public void setTemplateId(String templateId) 
	{
		this.templateId = templateId;
	}

	public String getTemplateId()
	{
		return templateId;
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
            .append("componentName", getComponentName())
            .append("componentId", getComponentId())
            .append("esignTemplateId", getEsignTemplateId())
            .append("templateId", getTemplateId())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
