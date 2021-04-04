package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 发票申请记录查询DTO
 *
 * @author yuanligang
 * @date 2019/7/17
 */
public class InvoiceQueryDTO {
    /**
     * 发票ID
     */
    @ApiModelProperty(value = "发票ID")
    private Integer id;
    /**
     * 发票申请编号
     */
    @Excel(name = "发票申请编号")
    @ApiModelProperty(value = "发票申请编号")
    private String invoiceSerialNum;
    /**
     * 0-增值税专用发票1-普通发票
     */
    @Excel(name = "0-增值税专用发票1-普通发票")
    @ApiModelProperty(value = "0-增值税专用发票1-普通发票")
    private Integer invoiceType;
    /**
     * 开票金额
     */
    @Excel(name = "开票金额")
    @ApiModelProperty(value = "开票金额")
    private BigDecimal invoiceAmount;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间")
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    /**
     * 开票状态
     */
    @Excel(name = "开票状态 1-申请开票 2-已完成 3-已驳回（申请开票驳回） 4-退票申请中 5-退票已完成 6-退票已驳回 7-等待开票 8-已作废")
    @ApiModelProperty
            (value = "开票状态 1-申请开票 2-已完成 3-已驳回（申请开票驳回） 4-退票申请中 5-退票已完成 6-退票已驳回 7-等待开票 8-已作废")
    private String invoiceStatus;

    /**
     * 公司名称
     */
    @Excel(name = "商户名称")
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    /**
     * 作废标识
     */
    @ApiModelProperty("作废标识 0-不显示 1和2均显示已作废")
    private Integer toVoid;
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

    @ApiModelProperty(value = "所属销售" , required = true)
    private String saleName;
    @ApiModelProperty(hidden = true)
    private String saleId;

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceSerialNum() {
        return invoiceSerialNum;
    }

    public void setInvoiceSerialNum(String invoiceSerialNum) {
        this.invoiceSerialNum = invoiceSerialNum;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }


    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getToVoid() {
        return toVoid;
    }

    public void setToVoid(Integer toVoid) {
        this.toVoid = toVoid;
    }

    @Override
    public String toString() {
        return "InvoiceQueryDTO{" +
                "id=" + id +
                ", invoiceSerialNum='" + invoiceSerialNum + '\'' +
                ", invoiceType=" + invoiceType +
                ", invoiceAmount=" + invoiceAmount +
                ", createTime='" + createTime + '\'' +
                ", invoiceStatus='" + invoiceStatus + '\'' +
                ", companyName='" + merchantName + '\'' +
                ", toVoid=" + toVoid +
                ", invoiceSerialName='" + invoiceSerialName + '\'' +
                ", taxRate=" + taxRate +
                ", taxAmount=" + taxAmount +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
