package com.mmtax.common.utils.esign.domain.signarea;

/**
 * @description 签署区列表数据
 * @author 宫清
 * @date 2019年7月18日 下午3:45:02
 * @since JDK1.7
 */
public class Signfield {

	// 文件id
	private String fileId;

	// 签署顺序，默认1，且不小于1，顺序越小越先处理
	private Integer order;

	// 签署区位置信息（signType为1时，页码和XY坐标不能为空，signType为2时，页码和Y坐标不能为空）
	private PosBean posBean;

	// 印章id
	private String sealId;

	// 签署类型，1-单页签署，2-骑缝签署，默认1
	private Integer signType;

	// 第三方业务流水号，保证相同签署人，相同签约主体，相同签署顺序的任务，对应的第三方业务流水号id唯一，默认空
	private String thirdOrderNo;

	// 签约主体账号标识， 将使用该主体账号对应的数字证书完成本次签署，如：当存在签署操作人代某机构签署时，需要传入该机构的账号id
	private String authorizedAccountId;

	// 签署操作人个人账号标识，即操作本次签署的个人，如需e签宝通知用户签署，则系统向该账号下绑定的手机、邮箱发送签署链接
	private String signerAccountId;

	// 签约主体类别，0-个人，2-机构；默认0
	private String actorIndentityType;

	// 是否指定位置，如指定位置则posBean不可为空；一旦设置为TRUE，表示用户签署时不允许更新位置
	private String assignedPosbean;

	// 印章类型，支持多种类型时逗号分割，0-手绘印章，1-模版印章，为空不限制
	private String sealType;

	//是否需要添加签署日期，0-禁止 1-必须，默认0
	private Integer signDateBeanType;

	// 平台自动盖章签署区添加时创建对象
	public Signfield(String fileId, Integer order, PosBean posBean, String sealId, Integer signType,
			String thirdOrderNo) {
		this.fileId = fileId;
		this.order = order;
		this.posBean = posBean;
		this.sealId = sealId;
		this.signType = signType;
		this.thirdOrderNo = thirdOrderNo;
	}

	// 添加签署方自动盖章签署区时创建对象
	public Signfield(String fileId, String authorizedAccountId, Integer order, PosBean posBean, String sealId,
			Integer signType, String thirdOrderNo) {
		this.fileId = fileId;
		this.order = order;
		this.posBean = posBean;
		this.sealId = sealId;
		this.signType = signType;
		this.thirdOrderNo = thirdOrderNo;
		this.authorizedAccountId = authorizedAccountId;
	}

	// 添加签署方手动签署区是创建对象
	public Signfield(String fileId, String signerAccountId, String actorIndentityType, String authorizedAccountId,
			String assignedPosbean, Integer order, PosBean posBean, String sealType, Integer signType,
			String thirdOrderNo) {
		this.fileId = fileId;
		this.order = order;
		this.posBean = posBean;
		this.signType = signType;
		this.thirdOrderNo = thirdOrderNo;
		this.authorizedAccountId = authorizedAccountId;
		this.signerAccountId = signerAccountId;
		this.actorIndentityType = actorIndentityType;
		this.assignedPosbean = assignedPosbean;
		this.sealType = sealType;
	}

	public Integer getSignDateBeanType() {
		return signDateBeanType;
	}

	public void setSignDateBeanType(Integer signDateBeanType) {
		this.signDateBeanType = signDateBeanType;
	}

	public String getSignerAccountId() {
		return signerAccountId;
	}

	public void setSignerAccountId(String signerAccountId) {
		this.signerAccountId = signerAccountId;
	}

	public String getActorIndentityType() {
		return actorIndentityType;
	}

	public void setActorIndentityType(String actorIndentityType) {
		this.actorIndentityType = actorIndentityType;
	}

	public String getAssignedPosbean() {
		return assignedPosbean;
	}

	public void setAssignedPosbean(String assignedPosbean) {
		this.assignedPosbean = assignedPosbean;
	}

	public String getSealType() {
		return sealType;
	}

	public void setSealType(String sealType) {
		this.sealType = sealType;
	}

	public String getAuthorizedAccountId() {
		return authorizedAccountId;
	}

	public void setAuthorizedAccountId(String authorizedAccountId) {
		this.authorizedAccountId = authorizedAccountId;
	}

	public Signfield() {
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public PosBean getPosBean() {
		return posBean;
	}

	public void setPosBean(PosBean posBean) {
		this.posBean = posBean;
	}

	public String getSealId() {
		return sealId;
	}

	public void setSealId(String sealId) {
		this.sealId = sealId;
	}

	public Integer getSignType() {
		return signType;
	}

	public void setSignType(Integer signType) {
		this.signType = signType;
	}

	public String getThirdOrderNo() {
		return thirdOrderNo;
	}

	public void setThirdOrderNo(String thirdOrderNo) {
		this.thirdOrderNo = thirdOrderNo;
	}

}
