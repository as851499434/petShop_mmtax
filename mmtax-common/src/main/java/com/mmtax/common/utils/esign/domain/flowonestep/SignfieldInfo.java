package com.mmtax.common.utils.esign.domain.flowonestep;

public class SignfieldInfo {
	
	//是否自动执行
	private Boolean autoExecute;
	
	//机构签约类别
	private String actorIndentityType;
	
	//文件fileId
	private String fileId;

	//印章类型
	private String sealType;
	
	//添加签署日期
	private SignDateBean signDateBean;
	
	//签署类型
	private Integer signType;
	
	//签署区位置信息
	private PosBeanInfo posBean;
	
	//签署区的宽度
	private Integer width;

	public Boolean getAutoExecute() {
		return autoExecute;
	}

	public void setAutoExecute(Boolean autoExecute) {
		this.autoExecute = autoExecute;
	}

	public String getActorIndentityType() {
		return actorIndentityType;
	}

	public void setActorIndentityType(String actorIndentityType) {
		this.actorIndentityType = actorIndentityType;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getSealType() {
		return sealType;
	}

	public void setSealType(String sealType) {
		this.sealType = sealType;
	}

	public SignDateBean getSignDateBean() {
		return signDateBean;
	}

	public void setSignDateBean(SignDateBean signDateBean) {
		this.signDateBean = signDateBean;
	}

	public Integer getSignType() {
		return signType;
	}

	public void setSignType(Integer signType) {
		this.signType = signType;
	}

	public PosBeanInfo getPosBean() {
		return posBean;
	}

	public void setPosBean(PosBeanInfo posBean) {
		this.posBean = posBean;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public SignfieldInfo(Boolean autoExecute, String actorIndentityType, String fileId, String sealType,
			SignDateBean signDateBean, Integer signType, PosBeanInfo posBean, Integer width) {
		this.autoExecute = autoExecute;
		this.actorIndentityType = actorIndentityType;
		this.fileId = fileId;
		this.sealType = sealType;
		this.signDateBean = signDateBean;
		this.signType = signType;
		this.posBean = posBean;
		this.width = width;
	}

	public SignfieldInfo() {
	}
	
	
}
