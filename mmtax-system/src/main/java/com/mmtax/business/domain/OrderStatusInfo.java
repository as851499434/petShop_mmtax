package com.mmtax.business.domain;

import javax.persistence.*;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 订单状态表 tbl_order_status_info
 *
 * @author meimiao
 * @date 2020-11-26
 */
@Table(name = "tbl_order_status_info")
public class OrderStatusInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 申请表关联Id
     */
    private Integer applyId;
    /**
     * 提交申请时间
     */
    private String applyTime;
    /**
     * 审核通过时间
     */
    private String handleTime;
    /**
     * 驳回时间
     */
    private String rejectTime;
    /**
     * 执照办理完成时间
     */
    private String completeTime;
    /**
     * 订单状态 -1 - 待处理 0-办理中 1-已办理 2-驳回申请 3-申请失败 4-办理完成
     */
    private Integer orderStatus;
    /**
     * 驳回理由
     */
    private String remark;
    /**
     *
     */
    private Integer providerId;
    /** 预留字段一 */
    private String reservedFieldOne;
    /** 预留字段二 */
    private String reservedFieldTwo;
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

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setRejectTime(String rejectTime) {
        this.rejectTime = rejectTime;
    }

    public String getRejectTime() {
        return rejectTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public String getReservedFieldOne() {
        return reservedFieldOne;
    }

    public void setReservedFieldOne(String reservedFieldOne) {
        this.reservedFieldOne = reservedFieldOne;
    }

    public String getReservedFieldTwo() {
        return reservedFieldTwo;
    }

    public void setReservedFieldTwo(String reservedFieldTwo) {
        this.reservedFieldTwo = reservedFieldTwo;
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
                .append("applyId", getApplyId())
                .append("applyTime", getApplyTime())
                .append("handleTime", getHandleTime())
                .append("rejectTime", getRejectTime())
                .append("completeTime", getCompleteTime())
                .append("orderStatus", getOrderStatus())
                .append("remark", getRemark())
                .append("providerId", getProviderId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
