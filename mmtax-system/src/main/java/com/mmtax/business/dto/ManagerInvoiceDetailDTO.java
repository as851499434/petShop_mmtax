package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商户后台发票详情
 *
 * @author yuanligang
 * @date 2019/8/12
 */
public class ManagerInvoiceDetailDTO {
    /**
     * 主键
     */
    @ApiModelProperty("发票申请ID")
    private Integer id;
    /**
     * 开票金额
     */
    @ApiModelProperty("开票金额---价税合计")
    private BigDecimal invoiceAmount;

    /**
     * 应税劳务服务名
     */
    @ApiModelProperty("应税劳务服务名")
    private String invoiceSerialName;
    /**
     * 税率
     */
    @ApiModelProperty("税率")
    private BigDecimal taxRate;
    /**
     * 发票税额
     */
    @ApiModelProperty("发票税额")
    private BigDecimal taxAmount;
    /**
     * 单价
     */
    @ApiModelProperty("单价")
    private BigDecimal unitPrice;

    /**
     * 发票状态APPLY-申请中AGREE-已同意POSTED-已寄出REFUSE-已拒绝
     */
    @ApiModelProperty("发票状态APPLY-申请中AGREE-已同意POSTED-已寄出REFUSE-已拒绝")
    private String invoiceStatus;

    /**
     * 0-增值税专用发票1-普通发票
     */
    @ApiModelProperty("0-增值税专用发票1-普通发票")
    private Integer invoiceType;
    /**
     * 发票代码
     */
    @ApiModelProperty("发票代码")
    private String invoiceCode;
    /**
     * 发票号码
     */
    @ApiModelProperty("发票号码")
    private String invoiceNum;
    /**
     * 开票日期
     */
    @ApiModelProperty("开票日期")
    private String invoiceTime;
    /**
     * 开票月份
     */
    @ApiModelProperty("开票月份")
    private String invoiceMonth;


    @ApiModelProperty("作废标识 0-未作废 1-已作废")
    private String toVoid;
    /**
     * 发票图片
     */
    @ApiModelProperty("发票图片")
    private String invoiceImg;
    @ApiModelProperty("发票申请编号")
    private String invoiceSerialNum;
    /**
     * 地址ID
     */
    @ApiModelProperty("地址ID")
    private Integer addressId;
    /**
     * 开户行名称
     */
    @ApiModelProperty("开户行名称")
    private String bankName;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;
    /**
     * 开票说明
     */
    @ApiModelProperty("开票说明")
    private String instruction;
    @ApiModelProperty("发票详情id")
    private Integer detailId;

    @ApiModelProperty("应税劳务服务及金额明细列表")
    private List<InvoiceAmountServiceDetailDTO> serviceAmounts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getInvoiceTime() {
        return invoiceTime;
    }

    public void setInvoiceTime(String invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    public String getInvoiceMonth() {
        return invoiceMonth;
    }

    public void setInvoiceMonth(String invoiceMonth) {
        this.invoiceMonth = invoiceMonth;
    }

    public String getToVoid() {
        return toVoid;
    }

    public void setToVoid(String toVoid) {
        this.toVoid = toVoid;
    }

    public String getInvoiceImg() {
        return invoiceImg;
    }

    public void setInvoiceImg(String invoiceImg) {
        this.invoiceImg = invoiceImg;
    }


    public String getInvoiceSerialNum() {
        return invoiceSerialNum;
    }

    public void setInvoiceSerialNum(String invoiceSerialNum) {
        this.invoiceSerialNum = invoiceSerialNum;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }


    public List<InvoiceAmountServiceDetailDTO> getServiceAmounts() {
        return serviceAmounts;
    }

    public void setServiceAmounts(List<InvoiceAmountServiceDetailDTO> serviceAmounts) {
        this.serviceAmounts = serviceAmounts;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceSerialName() {
        return invoiceSerialName;
    }

    public void setInvoiceSerialName(String invoiceSerialName) {
        this.invoiceSerialName = invoiceSerialName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    @Override
    public String toString() {
        return "ManagerInvoiceDetailDTO{" +
                "id=" + id +
                ", invoiceAmount=" + invoiceAmount +
                ", invoiceSerialName='" + invoiceSerialName + '\'' +
                ", taxRate=" + taxRate +
                ", taxAmount=" + taxAmount +
                ", unitPrice=" + unitPrice +
                ", invoiceStatus='" + invoiceStatus + '\'' +
                ", invoiceType=" + invoiceType +
                ", invoiceCode='" + invoiceCode + '\'' +
                ", invoiceNum='" + invoiceNum + '\'' +
                ", invoiceTime='" + invoiceTime + '\'' +
                ", invoiceMonth='" + invoiceMonth + '\'' +
                ", toVoid='" + toVoid + '\'' +
                ", invoiceImg='" + invoiceImg + '\'' +
                ", invoiceSerialNum='" + invoiceSerialNum + '\'' +
                ", addressId=" + addressId +
                ", bankName='" + bankName + '\'' +
                ", remarks='" + remarks + '\'' +
                ", instruction='" + instruction + '\'' +
                ", detailId=" + detailId +
                ", serviceAmounts=" + serviceAmounts +
                '}';
    }
}
