package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 发票信息管理DTO
 * @author zouyuanpeng
 * @date 2020/9/24 18:23
 */
@ApiModel("发票信息管理DTO")
public class InvoiceInfoDetailDTO {
    /**
     * 发票申请编号
     */
    @ApiModelProperty("发票申请编号")
    @Excel(name = "发票申请编号")
    private String invoiceSerialNum;
    /**
     * 发票编号
     */
    @ApiModelProperty("发票编号")
    @Excel(name = "发票编号")
    private String invoiceNo;
    /**
     * 发票抬头
     */
    @ApiModelProperty("发票抬头")
    @Excel(name = "发票抬头")
    private String invoiceTitle;
    /**
     * 快递单号
     */
    @ApiModelProperty("快递单号")
    @Excel(name = "快递单号")
    private String expressNo;
    /**
     * 快递公司
     */
    @ApiModelProperty("快递公司")
    @Excel(name = "快递公司")
    private String expressCompanyName;
    /**
     * 收件人姓名
     */
    @ApiModelProperty("收件人姓名")
    private String addresseeName;
    /**
     * 收件人电话
     */
    @ApiModelProperty("收件人电话")
    private String addressMobile;
    /**
     * 收件地址
     */
    @ApiModelProperty("收件地址")
    private String address;
    /**
     * 收件信息
     */
    @ApiModelProperty("收件信息")
    private String addressInfo;
    /**
     * 开票类型
     */
    @ApiModelProperty("开票类型")
    @Excel(name = "开票类型",readConverterExp = "0=增值税专用发票,1=增值税普通发票")
    private Integer invoiceType;
    /**
     * 开票类型字符串
     */
    @ApiModelProperty("开票类型字符串")
    private String invoiceTypeStr;
    /**
     * 开票金额（元）
     */
    @ApiModelProperty("开票金额（元）")
    @Excel(name = "开票金额（元）")
    private BigDecimal invoiceAmount;
    /**
     * 应税劳务服务名称
     */
    @ApiModelProperty("应税劳务服务名称")
    @Excel(name = "应税劳务服务名称")
    private String content;
    /**
     * 发票状态
     */
    @ApiModelProperty("发票状态")
    @Excel(name = "发票状态",readConverterExp = "1=已申请,2=已完成,3=已驳回,4=退票处理中," +
            "5=退票已完成,6=退票已驳回,7=开票中,8=已作废")
    private Integer invoiceStatus;
    /**
     * 发票状态字符串
     */
    @ApiModelProperty("发票状态字符串")

    private String invoiceStatusStr;
    /**
     * 开票日期
     */
    @ApiModelProperty("开票日期")
    @Excel(name = "开票日期",dateFormat = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    /**
     * 开票日期字符串
     */
    @ApiModelProperty("开票日期字符串")
    private String createTimeStr;

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String getInvoiceSerialNum() {
        return invoiceSerialNum;
    }

    public void setInvoiceSerialNum(String invoiceSerialNum) {
        this.invoiceSerialNum = invoiceSerialNum;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressCompanyName() {
        return expressCompanyName;
    }

    public void setExpressCompanyName(String expressCompanyName) {
        this.expressCompanyName = expressCompanyName;
    }

    public String getAddresseeName() {
        return addresseeName;
    }

    public void setAddresseeName(String addresseeName) {
        this.addresseeName = addresseeName;
    }

    public String getAddressMobile() {
        return addressMobile;
    }

    public void setAddressMobile(String addressMobile) {
        this.addressMobile = addressMobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTypeStr() {
        return invoiceTypeStr;
    }

    public void setInvoiceTypeStr(String invoiceTypeStr) {
        this.invoiceTypeStr = invoiceTypeStr;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceStatusStr() {
        return invoiceStatusStr;
    }

    public void setInvoiceStatusStr(String invoiceStatusStr) {
        this.invoiceStatusStr = invoiceStatusStr;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}
