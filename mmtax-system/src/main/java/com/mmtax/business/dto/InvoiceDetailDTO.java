package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 发票详情DTO
 * @author yuanligang
 * @date 2019/7/15
 */
public class InvoiceDetailDTO {

    /**
     * 发票ID
     */
    @ApiModelProperty(value = "发票ID")
    private Integer id;

    /**
     * 商户ID
     */
    @ApiModelProperty(value = "商户ID")
    private  Integer merchantId;

    /**
     *发票申请编号
     */
    @ApiModelProperty(value = "发票申请编号")
    private String  invoiceSerialNum;

    /**
     * 含税金额
     */
    @ApiModelProperty(value = "含税金额")
    private BigDecimal invoiceAmount;

    /**
     * 商户别名
     */
    @ApiModelProperty(value = "商户别名")
    private  String companyName;

    /**
     * 纳税人类型
     */
    @ApiModelProperty(value = "纳税人类型")
    private  Integer taxpayerType;

    /**
     * 收件地址
     */
    @ApiModelProperty(value = "收件地址")
    private String address;
    /**
     * 收件人姓名
     */
    @ApiModelProperty(value = "收件人姓名")
    private  String addresseeName;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String mobile;

    /**
     * 发票申请状态
     */
    @ApiModelProperty(value = "发票状态APPLY-申请中AGREE-已同意POSTED-已寄出REFUSE-已拒绝")
    private String invoiceStatus;

    /**
     * 开票说明
     */
    @ApiModelProperty(value = "开票说明")
    private String instruction;


    /**
     * 开票月份
     */
    @ApiModelProperty(value = "开票月份")
    private String invoiceMonth;
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

    @ApiModelProperty("开票日期")
    private String invoiceTime;

    @ApiModelProperty("金额及劳务服务税名称")
    private List<InvoiceAmountServiceDetailDTO> serviceAmounts;


    public String getInvoiceMonth() {
        return invoiceMonth;
    }

    public void setInvoiceMonth(String invoiceMonth) {
        this.invoiceMonth = invoiceMonth;
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

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getTaxpayerType() {
        return taxpayerType;
    }

    public void setTaxpayerType(Integer taxpayerType) {
        this.taxpayerType = taxpayerType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddresseeName() {
        return addresseeName;
    }

    public void setAddresseeName(String addresseeName) {
        this.addresseeName = addresseeName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getInvoiceImg() {
        return invoiceImg;
    }

    public void setInvoiceImg(String invoiceImg) {
        this.invoiceImg = invoiceImg;
    }

    public String getInvoiceTime() {
        return invoiceTime;
    }

    public void setInvoiceTime(String invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    public List<InvoiceAmountServiceDetailDTO> getServiceAmounts() {
        return serviceAmounts;
    }

    public void setServiceAmounts(List<InvoiceAmountServiceDetailDTO> serviceAmounts) {
        this.serviceAmounts = serviceAmounts;
    }

    @Override
    public String toString() {
        return "InvoiceDetailDTO{" +
                "id=" + id +
                ", merchantId=" + merchantId +
                ", invoiceSerialNum='" + invoiceSerialNum + '\'' +
                ", invoiceAmount=" + invoiceAmount +
                ", companyName='" + companyName + '\'' +
                ", taxpayerType=" + taxpayerType +
                ", address='" + address + '\'' +
                ", addresseeName='" + addresseeName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", invoiceStatus='" + invoiceStatus + '\'' +
                ", instruction='" + instruction + '\'' +
                ", invoiceMonth='" + invoiceMonth + '\'' +
                ", invoiceNum='" + invoiceNum + '\'' +
                ", invoiceCode='" + invoiceCode + '\'' +
                ", invoiceType=" + invoiceType +
                ", invoiceImg='" + invoiceImg + '\'' +
                ", invoiceTime='" + invoiceTime + '\'' +
                ", serviceAmounts=" + serviceAmounts +
                '}';
    }
}
