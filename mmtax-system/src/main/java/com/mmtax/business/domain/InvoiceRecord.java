package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 发票记录表 tbl_invoice_record
 *
 * @author meimiao
 * @date 2020-08-27
 */
@Table(name = "tbl_invoice_record")
public class InvoiceRecord
{
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 商户id */
	private Integer merchantId;
	/** 税源地id,对应表tbl_tax_sounrce_company.id */
	private Integer taxSourceId;
	/** 代征主体（税源地名称） */
	private String behalfMainBody;
	/** 发票金额 */
	@ApiModelProperty("发票总额")
	private BigDecimal invoiceAmount;
	/** 税率 */
	@ApiModelProperty("税率")
	private BigDecimal taxRate;
	/** 税额 */
	@ApiModelProperty("税额")
	private BigDecimal taxAmount;
	/** 单价*/
	private BigDecimal unitPrice;
	/** 规格型号 */
	private String specification;
	/** 发票性质 1-纸质发票 2-电子发票 */
	private Integer invoiceNature;
	/** 1-申请开票 2-已完成 3-已驳回（申请开票驳回） 4-退票申请中 5-退票已完成 6-退票已驳回 7-等待开票 8-已作废 */
	@ApiModelProperty("发票状态")
	private Integer invoiceStatus;
	/** 发票类型 0-增值税专用发票1-普通发票 */
	private Integer invoiceType;
	/** 发票申请编号（全局唯一，本地生成） */
	@ApiModelProperty("发票申请编号")
	private String invoiceSerialNum;
	/** 发票编号（对应发票上的编号） */
	private String invoiceNo;
	/** 发票抬头 */
	@ApiModelProperty("发票抬头")
	private String invoiceTitle;
	/** 纳税人识别号 */
	private String taxpayerIdentificationNumber;
	/** 单位注册地址 */
	private String companyAddress;
	/** 单位手机号 */
	private String invoiceMobile;
	/** 开户银行名称 */
	private String bankName;
	/** 开户账号 */
	private String bankAccountNo;
	/** 发票科目id，对应tbl_invoice_subject.id */
	private Integer invoiceSubjectId;
	/** 发票内容，对应tbl_invoice_subject.content */
	@ApiModelProperty("服务名称")
	private String invoiceContent;
	/** 收件地址id，对应tbl_address.id */
	private Integer addressId;
	/** 收件人姓名 */
	private String addresseeName;
	/** 收件人电话 */
	private String addressMobile;
	/** 收件地址 */
	private String address;
	/** 快递公司名称（税源地寄出） */
	private String expressCompanyName;
	/** 快递单号（税源地寄出） */
	private String expressNo;
	/** 快递公司名称（商户退票寄出） */
	private String expressCompanyNameReturn;
	/** 快递单号（商户退票寄出） */
	private String expressNoReturn;
	/** 退票原因 */
	private String returnReason;
	/** 驳回原因 */
	private String rejectedReason;
	/** 审核账号id，对应表sys_user.user_id */
	private Integer auditUserId;
	/** 审核人姓名，对应sys_user.user_name */
	private String auditUserName;
	/** 备注 */
	private String remark;
	/** 退票备注 */
	private String returnRemark;
	/** 删除标识0-未删除1-已删除 */
	private Integer delStatus;
	/**  */
	private Integer providerId;
	/** 预留字段1 */
	private String reservedFieldOne;
	/** 预留字段2 */
	private String reservedFieldTwo;
	/** 预留字段3 */
	private String reservedFieldThree;
	/**  */
	@ApiModelProperty("申请时间")
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

	public void setMerchantId(Integer merchantId)
	{
		this.merchantId = merchantId;
	}

	public Integer getMerchantId()
	{
		return merchantId;
	}

	public void setTaxSourceId(Integer taxSourceId)
	{
		this.taxSourceId = taxSourceId;
	}

	public Integer getTaxSourceId()
	{
		return taxSourceId;
	}

	public void setBehalfMainBody(String behalfMainBody)
	{
		this.behalfMainBody = behalfMainBody;
	}

	public String getBehalfMainBody()
	{
		return behalfMainBody;
	}

	public void setInvoiceAmount(BigDecimal invoiceAmount)
	{
		this.invoiceAmount = invoiceAmount;
	}

	public BigDecimal getInvoiceAmount()
	{
		return invoiceAmount;
	}

	public void setTaxRate(BigDecimal taxRate)
	{
		this.taxRate = taxRate;
	}

	public BigDecimal getTaxRate()
	{
		return taxRate;
	}

	public void setTaxAmount(BigDecimal taxAmount)
	{
		this.taxAmount = taxAmount;
	}

	public BigDecimal getTaxAmount()
	{
		return taxAmount;
	}

	public void setUnitPrice(BigDecimal unitPrice)
	{
		this.unitPrice = unitPrice;
	}

	public BigDecimal getUnitPrice()
	{
		return unitPrice;
	}

	public void setSpecification(String specification)
	{
		this.specification = specification;
	}

	public String getSpecification()
	{
		return specification;
	}

	public void setInvoiceNature(Integer invoiceNature)
	{
		this.invoiceNature = invoiceNature;
	}

	public Integer getInvoiceNature()
	{
		return invoiceNature;
	}

	public void setInvoiceStatus(Integer invoiceStatus)
	{
		this.invoiceStatus = invoiceStatus;
	}

	public Integer getInvoiceStatus()
	{
		return invoiceStatus;
	}

	public void setInvoiceType(Integer invoiceType)
	{
		this.invoiceType = invoiceType;
	}

	public Integer getInvoiceType()
	{
		return invoiceType;
	}

	public String getInvoiceSerialNum() {
		return invoiceSerialNum;
	}

	public void setInvoiceSerialNum(String invoiceSerialNum) {
		this.invoiceSerialNum = invoiceSerialNum;
	}

	public void setInvoiceNo(String invoiceNo)
	{
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceNo()
	{
		return invoiceNo;
	}

	public void setInvoiceTitle(String invoiceTitle)
	{
		this.invoiceTitle = invoiceTitle;
	}

	public String getInvoiceTitle()
	{
		return invoiceTitle;
	}

	public void setTaxpayerIdentificationNumber(String taxpayerIdentificationNumber)
	{
		this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
	}

	public String getTaxpayerIdentificationNumber()
	{
		return taxpayerIdentificationNumber;
	}

	public void setCompanyAddress(String companyAddress)
	{
		this.companyAddress = companyAddress;
	}

	public String getCompanyAddress()
	{
		return companyAddress;
	}

	public void setInvoiceMobile(String invoiceMobile)
	{
		this.invoiceMobile = invoiceMobile;
	}

	public String getInvoiceMobile()
	{
		return invoiceMobile;
	}

	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}

	public String getBankName()
	{
		return bankName;
	}

	public void setBankAccountNo(String bankAccountNo)
	{
		this.bankAccountNo = bankAccountNo;
	}

	public String getBankAccountNo()
	{
		return bankAccountNo;
	}

	public void setInvoiceSubjectId(Integer invoiceSubjectId)
	{
		this.invoiceSubjectId = invoiceSubjectId;
	}

	public Integer getInvoiceSubjectId()
	{
		return invoiceSubjectId;
	}

	public void setInvoiceContent(String invoiceContent)
	{
		this.invoiceContent = invoiceContent;
	}

	public String getInvoiceContent()
	{
		return invoiceContent;
	}

	public void setAddressId(Integer addressId)
	{
		this.addressId = addressId;
	}

	public Integer getAddressId()
	{
		return addressId;
	}

	public void setAddresseeName(String addresseeName)
	{
		this.addresseeName = addresseeName;
	}

	public String getAddresseeName()
	{
		return addresseeName;
	}

	public void setAddressMobile(String addressMobile)
	{
		this.addressMobile = addressMobile;
	}

	public String getAddressMobile()
	{
		return addressMobile;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getAddress()
	{
		return address;
	}

	public void setExpressCompanyName(String expressCompanyName)
	{
		this.expressCompanyName = expressCompanyName;
	}

	public String getExpressCompanyName()
	{
		return expressCompanyName;
	}

	public void setExpressNo(String expressNo)
	{
		this.expressNo = expressNo;
	}

	public String getExpressNo()
	{
		return expressNo;
	}

	public void setExpressCompanyNameReturn(String expressCompanyNameReturn)
	{
		this.expressCompanyNameReturn = expressCompanyNameReturn;
	}

	public String getExpressCompanyNameReturn()
	{
		return expressCompanyNameReturn;
	}

	public void setExpressNoReturn(String expressNoReturn)
	{
		this.expressNoReturn = expressNoReturn;
	}

	public String getExpressNoReturn()
	{
		return expressNoReturn;
	}

	public void setReturnReason(String returnReason)
	{
		this.returnReason = returnReason;
	}

	public String getReturnReason()
	{
		return returnReason;
	}

	public void setRejectedReason(String rejectedReason)
	{
		this.rejectedReason = rejectedReason;
	}

	public String getRejectedReason()
	{
		return rejectedReason;
	}

	public void setAuditUserId(Integer auditUserId)
	{
		this.auditUserId = auditUserId;
	}

	public Integer getAuditUserId()
	{
		return auditUserId;
	}

	public void setAuditUserName(String auditUserName)
	{
		this.auditUserName = auditUserName;
	}

	public String getAuditUserName()
	{
		return auditUserName;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setReturnRemark(String returnRemark)
	{
		this.returnRemark = returnRemark;
	}

	public String getReturnRemark()
	{
		return returnRemark;
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
		return "InvoiceRecord{" +
				"id=" + id +
				", merchantId=" + merchantId +
				", taxSourceId=" + taxSourceId +
				", behalfMainBody='" + behalfMainBody + '\'' +
				", invoiceAmount=" + invoiceAmount +
				", taxRate=" + taxRate +
				", taxAmount=" + taxAmount +
				", unitPrice=" + unitPrice +
				", specification='" + specification + '\'' +
				", invoiceNature=" + invoiceNature +
				", invoiceStatus=" + invoiceStatus +
				", invoiceType=" + invoiceType +
				", invoiceSerialNum='" + invoiceSerialNum + '\'' +
				", invoiceNo='" + invoiceNo + '\'' +
				", invoiceTitle='" + invoiceTitle + '\'' +
				", taxpayerIdentificationNumber='" + taxpayerIdentificationNumber + '\'' +
				", companyAddress='" + companyAddress + '\'' +
				", invoiceMobile='" + invoiceMobile + '\'' +
				", bankName='" + bankName + '\'' +
				", bankAccountNo='" + bankAccountNo + '\'' +
				", invoiceSubjectId=" + invoiceSubjectId +
				", invoiceContent='" + invoiceContent + '\'' +
				", addressId=" + addressId +
				", addresseeName='" + addresseeName + '\'' +
				", addressMobile='" + addressMobile + '\'' +
				", address='" + address + '\'' +
				", expressCompanyName='" + expressCompanyName + '\'' +
				", expressNo='" + expressNo + '\'' +
				", expressCompanyNameReturn='" + expressCompanyNameReturn + '\'' +
				", expressNoReturn='" + expressNoReturn + '\'' +
				", returnReason='" + returnReason + '\'' +
				", rejectedReason='" + rejectedReason + '\'' +
				", auditUserId=" + auditUserId +
				", auditUserName='" + auditUserName + '\'' +
				", remark='" + remark + '\'' +
				", returnRemark='" + returnRemark + '\'' +
				", delStatus=" + delStatus +
				", providerId=" + providerId +
				", reservedFieldOne='" + reservedFieldOne + '\'' +
				", reservedFieldTwo='" + reservedFieldTwo + '\'' +
				", reservedFieldThree='" + reservedFieldThree + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
