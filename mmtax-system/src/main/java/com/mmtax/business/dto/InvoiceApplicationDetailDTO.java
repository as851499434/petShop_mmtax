package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 发票申请详情DTO
 */
public class InvoiceApplicationDetailDTO {

    /** 发票记录id */
    @ApiModelProperty(value = "发票记录id",hidden = true)
    private Integer id;

    /** 发票申请编号（全局唯一，本地生成） */
    @ApiModelProperty("发票申请编号")
    private String invoiceSerialNum;

    /** 发票金额 */
    @ApiModelProperty("发票金额")
    private BigDecimal invoiceAmount;

    /** 发票性质 1-纸质发票 2-电子发票 */
    @ApiModelProperty("发票性质")
    private Integer invoiceNature;

    /** 1-申请开票 2-已完成 3-已驳回（申请开票驳回） 4-退票申请中 5-退票已完成 6-退票已驳回 7-等待开票 8-已作废 */
    @ApiModelProperty("发票状态")
    private Integer invoiceStatus;

    /** 发票类型 0-增值税专用发票1-普通发票 */
    @ApiModelProperty("发票类型")
    private Integer invoiceType;

    /** 发票抬头 */
    @ApiModelProperty("发票抬头")
    private String invoiceTitle;

    /** 申请时间 */
    @ApiModelProperty("申请时间")
    private Date createTime;

    /** 发票编号（对应发票上的编号） */
    @ApiModelProperty("发票编号")
    private String invoiceNo;

    /** 收件人姓名 */
    @ApiModelProperty("收件人姓名")
    private String addresseeName;

    /** 收件地址 */
    @ApiModelProperty("收件地址")
    private String address;

    /** 快递公司名称（税源地寄出） */
    @ApiModelProperty("快递公司名称（税源地寄出）")
    private String expressCompanyName;

    /** 快递单号（税源地寄出） */
    @ApiModelProperty("快递单号（税源地寄出）")
    private String expressNo;

    /** 收件人电话 */
    @ApiModelProperty("收件人电话")
    private String addressMobile;

    /** 备注 */
    @ApiModelProperty("备注")
    private String remark;

    /** 快递公司名称（商户退票寄出） */
    @ApiModelProperty("快递公司名称（商户退票寄出）")
    private String expressCompanyNameReturn;

    /** 快递单号（商户退票寄出） */
    @ApiModelProperty("快递单号（商户退票寄出）")
    private String expressNoReturn;

    /** 充值详情*/
    @ApiModelProperty("充值详情")
    private List<RechargeDetialDTO> rechargeDetialDTOList;


    /** 退票原因 */
    @ApiModelProperty("退票原因")
    private String returnReason;

    /** 驳回原因*/
    @ApiModelProperty("驳回原因")
    private String rejectedeason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpressCompanyNameReturn() {
        return expressCompanyNameReturn;
    }

    public void setExpressCompanyNameReturn(String expressCompanyNameReturn) {
        this.expressCompanyNameReturn = expressCompanyNameReturn;
    }

    public String getExpressNoReturn() {
        return expressNoReturn;
    }

    public void setExpressNoReturn(String expressNoReturn) {
        this.expressNoReturn = expressNoReturn;
    }

    public String getInvoiceSerialNum() {
        return invoiceSerialNum;
    }

    public void setInvoiceSerialNum(String invoiceSerialNum) {
        this.invoiceSerialNum = invoiceSerialNum;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Integer getInvoiceNature() {
        return invoiceNature;
    }

    public void setInvoiceNature(Integer invoiceNature) {
        this.invoiceNature = invoiceNature;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getAddresseeName() {
        return addresseeName;
    }

    public void setAddresseeName(String addresseeName) {
        this.addresseeName = addresseeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExpressCompanyName() {
        return expressCompanyName;
    }

    public void setExpressCompanyName(String expressCompanyName) {
        this.expressCompanyName = expressCompanyName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getAddressMobile() {
        return addressMobile;
    }

    public void setAddressMobile(String addressMobile) {
        this.addressMobile = addressMobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<RechargeDetialDTO> getRechargeDetialDTOList() {
        return rechargeDetialDTOList;
    }

    public void setRechargeDetialDTOList(List<RechargeDetialDTO> rechargeDetialDTOList) {
        this.rechargeDetialDTOList = rechargeDetialDTOList;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getRejectedeason() {
        return rejectedeason;
    }

    public void setRejectedeason(String rejectedeason) {
        this.rejectedeason = rejectedeason;
    }

    @Override
    public String toString() {
        return "InvoiceApplicationDetailDTO{" +
                "id=" + id +
                ", invoiceSerialNum='" + invoiceSerialNum + '\'' +
                ", invoiceAmount=" + invoiceAmount +
                ", invoiceNature=" + invoiceNature +
                ", invoiceStatus=" + invoiceStatus +
                ", invoiceType=" + invoiceType +
                ", invoiceTitle='" + invoiceTitle + '\'' +
                ", createTime=" + createTime +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", addresseeName='" + addresseeName + '\'' +
                ", address='" + address + '\'' +
                ", expressCompanyName='" + expressCompanyName + '\'' +
                ", expressNo='" + expressNo + '\'' +
                ", addressMobile='" + addressMobile + '\'' +
                ", remark='" + remark + '\'' +
                ", expressCompanyNameReturn='" + expressCompanyNameReturn + '\'' +
                ", expressNoReturn='" + expressNoReturn + '\'' +
                ", rechargeDetialDTOList=" + rechargeDetialDTOList +
                ", returnReason='" + returnReason + '\'' +
                ", rejectedeason='" + rejectedeason + '\'' +
                '}';
    }
}
