package com.mmtax.business.domain;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 开票申请表 tbl_invoice_apply
 *
 * @author meimiao
 * @date 2019-09-18
 */
@Table(name = "tbl_invoice_apply")
public class InvoiceApply {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("发票申请ID")
    private Integer id;
    /**
     * 开票金额
     */
    @ApiModelProperty("开票金额---价税合计")
    private BigDecimal invoiceAmount;
    /**
     * 发票信息
     */
    @ApiModelProperty("发票信息--ID")
    private Integer invoiceInfoId;
    /**
     * 发票状态APPLY-申请中AGREE-已同意POSTED-已寄出REFUSE-已拒绝
     */
    @ApiModelProperty("发票状态APPLY-申请中AGREE-已同意POSTED-已寄出REFUSE-已拒绝")
    private String invoiceStatus;
    /**
     * 商户id
     */
    @ApiModelProperty("商户id")
    private Integer merchantId;
    /**
     * 0-增值税专用发票1-普通发票
     */
    @ApiModelProperty("0-增值税专用发票1-普通发票")
    private Integer invoiceType;
    /**
     * 操作人
     */
    @ApiModelProperty("操作人")
    private String operator;
    /**
     * 作废标识 0-未作废 1-已作废 2-作废中 3-待重开 4-已重开
     */
    @ApiModelProperty("作废标识 0-未作废 1-已作废 2-作废中 3-待重开 4-已重开")
    private Integer toVoid;
    /**
     * 开户行
     */
    @ApiModelProperty("开户行")
    private String bankName;
    /**
     * 发票申请编号
     */
    @ApiModelProperty("发票申请编号")
    private String invoiceSerialNum;
    /**
     * 开票说明
     */
    @ApiModelProperty("开票说明")
    private String instruction;
    /**
     * 发票备注
     */
    @ApiModelProperty("发票备注")
    private String remarks;
    /**
     * 被重开发票ID
     */
    @ApiModelProperty("被重开发票ID")
    private Integer generateId;
    /**
     * 开票地址
     */
    @ApiModelProperty("开票地址")
    private Integer addressId;
    /**
     *删除标识0-未删除1-已删除
     */
    private Integer delStatus;
    @Transient
    private Integer providerId;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceInfoId(Integer invoiceInfoId) {
        this.invoiceInfoId = invoiceInfoId;
    }

    public Integer getInvoiceInfoId() {
        return invoiceInfoId;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setToVoid(Integer toVoid) {
        this.toVoid = toVoid;
    }

    public Integer getToVoid() {
        return toVoid;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setInvoiceSerialNum(String invoiceSerialNum) {
        this.invoiceSerialNum = invoiceSerialNum;
    }

    public String getInvoiceSerialNum() {
        return invoiceSerialNum;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setGenerateId(Integer generateId) {
        this.generateId = generateId;
    }

    public Integer getGenerateId() {
        return generateId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("invoiceAmount", getInvoiceAmount())
                .append("invoiceInfoId", getInvoiceInfoId())
                .append("invoiceStatus", getInvoiceStatus())
                .append("merchantId", getMerchantId())
                .append("invoiceType", getInvoiceType())
                .append("operator", getOperator())
                .append("toVoid", getToVoid())
                .append("bankName", getBankName())
                .append("invoiceSerialNum", getInvoiceSerialNum())
                .append("instruction", getInstruction())
                .append("remarks", getRemarks())
                .append("generateId", getGenerateId())
                .append("addressId", getAddressId())
                .append("delStatus", getDelStatus())
                .append("providerId", getProviderId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
