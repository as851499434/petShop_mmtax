package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 发票重开DTO
 *
 * @author yuanligang
 * @date 2019/8/5
 */
public class InvoiceRestartDetailDTO {
    /**
     * 发票ID
     */
    @ApiModelProperty(value = "发票ID")
    private Integer id;

    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private Integer merchantId;

    /**
     * 发票申请编号
     */
    @ApiModelProperty(value = "发票申请编号")
    private String invoiceSerialNum;

    /**
     * 含税金额
     */
    @ApiModelProperty(value = "含税金额")
    private BigDecimal invoiceAmount;


    /**
     * 发票申请状态
     */
    @ApiModelProperty(value = "发票申请状态")
    private String invoiceStatus;

    /**
     * 开票说明
     */
    @ApiModelProperty(value = "开票说明")
    private String instruction;

    /**
     * 开票备注
     */
    @ApiModelProperty(value = "开票备注")
    private String remarks;

    /**
     *
     */
    @ApiModelProperty("服务税金额集--条目详细")
    List<InvoiceAmountServiceDetailDTO> serviceAmounts;

    /**
     * 发票号码
     */
    @ApiModelProperty(value = "发票号码")
    private String invoiceNum;

    /**
     * 发票代码
     */
    @ApiModelProperty(value = "发票代码")
    private String invoiceCode;

    /**
     * 0-增值税专用发票1-普通发票
     */
    @ApiModelProperty(value = "0-增值税专用发票1-普通发票")
    private Integer invoiceType;

    @ApiModelProperty(value = "发票图片地址")
    private String invoiceImg;

    /**
     * 应税劳务服务名称
     */
    @ApiModelProperty(value = "劳务应税名称", required = true)
    private List<String> invoiceServiceNameList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
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

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceImg() {
        return invoiceImg;
    }

    public void setInvoiceImg(String invoiceImg) {
        this.invoiceImg = invoiceImg;
    }

    public List<String> getInvoiceServiceNameList() {
        return invoiceServiceNameList;
    }

    public void setInvoiceServiceNameList(List<String> invoiceServiceNameList) {
        this.invoiceServiceNameList = invoiceServiceNameList;
    }

    public List<InvoiceAmountServiceDetailDTO> getServiceAmounts() {
        return serviceAmounts;
    }

    public void setServiceAmounts(List<InvoiceAmountServiceDetailDTO> serviceAmounts) {
        this.serviceAmounts = serviceAmounts;
    }

    @Override
    public String toString() {
        return "InvoiceRestartDetailDTO{" +
                "id=" + id +
                ", merchantId=" + merchantId +
                ", invoiceSerialNum='" + invoiceSerialNum + '\'' +
                ", invoiceAmount=" + invoiceAmount +
                ", invoiceStatus='" + invoiceStatus + '\'' +
                ", instruction='" + instruction + '\'' +
                ", remarks='" + remarks + '\'' +
                ", serviceAmounts=" + serviceAmounts +
                ", invoiceNum='" + invoiceNum + '\'' +
                ", invoiceCode='" + invoiceCode + '\'' +
                ", invoiceType=" + invoiceType +
                ", invoiceImg='" + invoiceImg + '\'' +
                ", invoiceServiceNameList=" + invoiceServiceNameList +
                '}';
    }
}
