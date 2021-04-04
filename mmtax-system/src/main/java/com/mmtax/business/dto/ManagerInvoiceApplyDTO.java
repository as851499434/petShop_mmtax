package com.mmtax.business.dto;

import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * 后台查询发票
 *
 * @author yuanligang
 * @date 2019/7/19
 */
public class ManagerInvoiceApplyDTO extends BaseEntity {
    /**
     * 发票申请记录查询
     */
    @ApiModelProperty(value = "申请编号" )
    private String invoiceSerialNum;
    /**
     * 起始日期
     */
    @ApiModelProperty(value = "起始日期" )
    private String startDate;
    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束日期" )
    private String endDate;
    /**
     * 起始日期
     */
    @ApiModelProperty(value = "起始金额" )
    private String startAmount;
    /**
     * 结束日期
     */
    @ApiModelProperty(value = "结束金额" )
    private String endAmount;

    /**
     * 发票代码
     */
    @ApiModelProperty(value = "发票代码" )
    private String invoiceCode;

    /**
     * 发票有效状态
     */
    @ApiModelProperty(value = "发票有效状态" )
    private Integer status;
    /**
     * 发票重开状态
     */
    @ApiModelProperty(value = "发票重开状态" )
    private Integer toVoid;

    @ApiModelProperty(value = "所属销售" )
    private String saleName;

    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    
    @ApiModelProperty(value = "发票编号" )
    private String invoiceNo;
    @ApiModelProperty(value = "发票科目" )
    private String invoiceContent;

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    @ApiModelProperty(hidden = true)
    private String saleId;


    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getStartAmount() {
        return startAmount;
    }

    public void setStartAmount(String startAmount) {
        this.startAmount = startAmount;
    }

    public String getEndAmount() {
        return endAmount;
    }

    public void setEndAmount(String endAmount) {
        this.endAmount = endAmount;
    }

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

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceSerialNum() {
        return invoiceSerialNum;
    }

    public void setInvoiceSerialNum(String invoiceSerialNum) {
        this.invoiceSerialNum = invoiceSerialNum;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getToVoid() {
        return toVoid;
    }

    public void setToVoid(Integer toVoid) {
        this.toVoid = toVoid;
    }

    @Override
    public String toString() {
        return "ManagerInvoiceApplyDTO{" +
                "invoiceSerialNum='" + invoiceSerialNum + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", invoiceCode='" + invoiceCode + '\'' +
                ", status=" + status +
                ", toVoid=" + toVoid +
                '}';
    }
}
