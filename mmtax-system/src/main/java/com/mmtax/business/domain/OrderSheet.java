package com.mmtax.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 调单订单表 tbl_order_sheet
 *
 * @author meimiao
 * @date 2019-08-14
 */
@Table(name = "tbl_order_sheet")
public class OrderSheet {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 调单流水号
     */
    private String orderNo;
    /**
     * 风险点
     */
    private String riskPoint;
    /**
     * 调单说明
     */
    private String orderNote;
    /**
     * 调单流水号
     */
    private Integer orderNum;
    /**
     * 订单id
     */
    private Integer trxOrderId;
    /**
     * 审核结论
     */
    private String auditResult;
    /**
     * 审核说明
     */
    private String auditNote;
    /**
     * 反馈备注
     */
    private String feedBackComment;
    /**
     * 反馈时间
     */
    private Date feedBackTime;
    /**
     * 附件名称
     */
    private String fileName;
    /**
     * 0-未处理1-已处理2-已拒绝
     */
    private Integer status;
    /**
     *
     */
    private Integer merchantId;
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

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setRiskPoint(String riskPoint) {
        this.riskPoint = riskPoint;
    }

    public String getRiskPoint() {
        return riskPoint;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setTrxOrderId(Integer trxOrderId) {
        this.trxOrderId = trxOrderId;
    }

    public Integer getTrxOrderId() {
        return trxOrderId;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditNote(String auditNote) {
        this.auditNote = auditNote;
    }

    public String getAuditNote() {
        return auditNote;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getMerchantId() {
        return merchantId;
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

    public String getFeedBackComment() {
        return feedBackComment;
    }

    public void setFeedBackComment(String feedBackComment) {
        this.feedBackComment = feedBackComment;
    }

    public Date getFeedBackTime() {
        return feedBackTime;
    }

    public void setFeedBackTime(Date feedBackTime) {
        this.feedBackTime = feedBackTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderNo", getOrderNo())
                .append("riskPoint", getRiskPoint())
                .append("orderNote", getOrderNote())
                .append("orderNum", getOrderNum())
                .append("trxOrderId", getTrxOrderId())
                .append("auditResult", getAuditResult())
                .append("auditNote", getAuditNote())
                .append("fileName", getFileName())
                .append("status", getStatus())
                .append("feedBackComment", getFeedBackComment())
                .append("feedBackTime", getFeedBackTime())
                .append("merchantId", getMerchantId())
                .append("providerId", getProviderId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
