package com.mmtax.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 发票申请详情表 tbl_invoice_apply_detail
 *
 * @author meimiao
 * @date 2019-09-18
 */
@Table(name = "tbl_invoice_apply_detail")
public class InvoiceApplyDetail {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 申请记录id
     */
    private Integer invoiceApplyId;
    /**
     * 充值记录id
     */
    private Integer rechargeRecord;
    /**
     * 发票图片
     */
    private String invoiceImg;
    /**
     * 发票号码
     */
    private String invoiceNo;
    /**
     * 发票代码
     */
    private String invoiceCode;
    /**
     * 开票月份
     */
    private String invoiceMonth;
    /**
     * 开票日期
     */
    private String invoiceTime;
    /**
     * 删除状态 0-未删除1-已删除
     */
    private Integer delStatus;
    /**
     *
     */
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

    public void setInvoiceApplyId(Integer invoiceApplyId) {
        this.invoiceApplyId = invoiceApplyId;
    }

    public Integer getInvoiceApplyId() {
        return invoiceApplyId;
    }

    public void setRechargeRecord(Integer rechargeRecord) {
        this.rechargeRecord = rechargeRecord;
    }

    public Integer getRechargeRecord() {
        return rechargeRecord;
    }

    public void setInvoiceImg(String invoiceImg) {
        this.invoiceImg = invoiceImg;
    }

    public String getInvoiceImg() {
        return invoiceImg;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceMonth(String invoiceMonth) {
        this.invoiceMonth = invoiceMonth;
    }

    public String getInvoiceMonth() {
        return invoiceMonth;
    }

    public void setInvoiceTime(String invoiceTime) {
        this.invoiceTime = invoiceTime;
    }

    public String getInvoiceTime() {
        return invoiceTime;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public Integer getDelStatus() {
        return delStatus;
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


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("invoiceApplyId", getInvoiceApplyId())
                .append("rechargeRecord", getRechargeRecord())
                .append("invoiceImg", getInvoiceImg())
                .append("invoiceNo", getInvoiceNo())
                .append("invoiceCode", getInvoiceCode())
                .append("invoiceMonth", getInvoiceMonth())
                .append("invoiceTime", getInvoiceTime())
                .append("delStatus", getDelStatus())
                .append("providerId", getProviderId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
