package com.mmtax.business.domain;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;

/**
 * 签约流程签署区记录表 tbl_esign_fields
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Table(name = "tbl_esign_fields")
public class EsignFields
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
				/** 签署员工表id */
	private Integer cusEsignId;
				/** 签署人accountId */
	private String authorizedAccountId;
	/**签署操作人个人账号标识(手动签署)*/
	private String signerAccountId;
				/** 签署顺序,顺序越小越先处理 */
	private Integer orderNum;
				/** 页码信息 */
	private Integer posPage;
				/** x坐标 */
	private Float posX;
				/** y坐标 */
	private Float posY;
				/** 签署区宽 */
	private Float width;
				/** 第三方业务流水号id */
	private String thirdOrderNo;
				/**  */
				@Transient
	private Integer providerId;
				/**  */
	private String reservedFieldOne;
				/**  */
	private String reservedFieldTwo;
				/**  */
	private Date createTime;
				/**  */
	private Date updateTime;

	public String getSignerAccountId() {
		return signerAccountId;
	}

	public void setSignerAccountId(String signerAccountId) {
		this.signerAccountId = signerAccountId;
	}

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

	public void setCusEsignId(Integer cusEsignId) 
	{
		this.cusEsignId = cusEsignId;
	}

	public Integer getCusEsignId()
	{
		return cusEsignId;
	}

	public void setAuthorizedAccountId(String authorizedAccountId) 
	{
		this.authorizedAccountId = authorizedAccountId;
	}

	public String getAuthorizedAccountId()
	{
		return authorizedAccountId;
	}

	public void setOrderNum(Integer orderNum)
	{
		this.orderNum = orderNum;
	}

	public Integer getOrderNum()
	{
		return orderNum;
	}

	public void setPosPage(Integer posPage) 
	{
		this.posPage = posPage;
	}

	public Integer getPosPage()
	{
		return posPage;
	}

	public void setPosX(Float posX) 
	{
		this.posX = posX;
	}

	public Float getPosX()
	{
		return posX;
	}

	public void setPosY(Float posY) 
	{
		this.posY = posY;
	}

	public Float getPosY()
	{
		return posY;
	}

	public void setWidth(Float width) 
	{
		this.width = width;
	}

	public Float getWidth()
	{
		return width;
	}

	public void setThirdOrderNo(String thirdOrderNo) 
	{
		this.thirdOrderNo = thirdOrderNo;
	}

	public String getThirdOrderNo()
	{
		return thirdOrderNo;
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
            .append("cusEsignId", getCusEsignId())
            .append("authorizedAccountId", getAuthorizedAccountId())
			.append("signerAccountId", getSignerAccountId())
            .append("order", getOrderNum())
            .append("posPage", getPosPage())
            .append("posX", getPosX())
            .append("posY", getPosY())
            .append("width", getWidth())
            .append("thirdOrderNo", getThirdOrderNo())
            .append("providerId", getProviderId())
            .append("reservedFieldOne", getReservedFieldOne())
            .append("reservedFieldTwo", getReservedFieldTwo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
