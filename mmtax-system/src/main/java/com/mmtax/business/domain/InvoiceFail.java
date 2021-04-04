package com.mmtax.business.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * 发票重开表 tbl_invoice_fail
 *
 * @author meimiao
 * @date 2019-08-05
 */
@Table(name = "tbl_invoice_fail")
public class InvoiceFail {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 作废发票ID
     */
    private Integer invoiceId;
    /**
     * 发票认证状态 0-已认证 1-未认证
     */
    private Integer status;
    /**
     * 红冲发票信息表文件存储 已认证
     */
    private String redInvoiceInfo;
    /**
     * 红冲发票
     */
    private String redInvoice;
    /**
     * 盖章版退票说明
     */
    private String sealExplain;

    /**
     * 是否处理 0-未处理 1-已处理
     */
    private Integer check;
    /**
     * 删除状态 0-未删除1-已删除
     */
    private Integer delStatus;

    /**  */
    @Transient
    private Integer providerId;
    /**  */
    private Date createTime;
    /**  */
    private Date updateTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setRedInvoice(String redInvoice) {
        this.redInvoice = redInvoice;
    }

    public String getRedInvoice() {
        return redInvoice;
    }

    public void setSealExplain(String sealExplain) {
        this.sealExplain = sealExplain;
    }

    public String getSealExplain() {
        return sealExplain;
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

    public String getRedInvoiceInfo() {
        return redInvoiceInfo;
    }

    public void setRedInvoiceInfo(String redInvoiceInfo) {
        this.redInvoiceInfo = redInvoiceInfo;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    @Override
    public String toString() {
        return "InvoiceFail{" +
                "id=" + id +
                ", invoiceId=" + invoiceId +
                ", status=" + status +
                ", redInvoiceInfo='" + redInvoiceInfo + '\'' +
                ", redInvoice='" + redInvoice + '\'' +
                ", sealExplain='" + sealExplain + '\'' +
                ", check=" + check +
                ", delStatus=" + delStatus +
                ", providerId=" + providerId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
