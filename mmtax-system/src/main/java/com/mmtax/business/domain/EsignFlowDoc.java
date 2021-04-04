package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 签约流程文档关联表 tbl_esign_flow_doc
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Table(name = "tbl_esign_flow_doc")
public class EsignFlowDoc
{
	private static final long serialVersionUID = 1L;
	
		/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
					/** 签约流程表id */
	private Integer esignFlowId;
				/** 流程id(e签宝) */
	private String flowId;
				/** 签约文件id */
	private Integer contractInfoId;
				/** 文件id(e签宝) */
	private String fileId;
				/** 是否加密 0-不加密 1-加密 */
	private Integer encryption;
				/** 文件名称(带扩展名) */
	private String fileName;
				/** 文档密码 */
	private String filePassword;
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

	public void setEsignFlowId(Integer esignFlowId) 
	{
		this.esignFlowId = esignFlowId;
	}

	public Integer getEsignFlowId()
	{
		return esignFlowId;
	}

	public void setFlowId(String flowId) 
	{
		this.flowId = flowId;
	}

	public String getFlowId()
	{
		return flowId;
	}

	public void setContractInfoId(Integer contractInfoId) 
	{
		this.contractInfoId = contractInfoId;
	}

	public Integer getContractInfoId()
	{
		return contractInfoId;
	}

	public void setFileId(String fileId) 
	{
		this.fileId = fileId;
	}

	public String getFileId()
	{
		return fileId;
	}

	public void setEncryption(Integer encryption) 
	{
		this.encryption = encryption;
	}

	public Integer getEncryption()
	{
		return encryption;
	}

	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFilePassword(String filePassword) 
	{
		this.filePassword = filePassword;
	}

	public String getFilePassword()
	{
		return filePassword;
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
            .append("esignFlowId", getEsignFlowId())
            .append("flowId", getFlowId())
            .append("contractInfoId", getContractInfoId())
            .append("fileId", getFileId())
            .append("encryption", getEncryption())
            .append("fileName", getFileName())
            .append("filePassword", getFilePassword())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
