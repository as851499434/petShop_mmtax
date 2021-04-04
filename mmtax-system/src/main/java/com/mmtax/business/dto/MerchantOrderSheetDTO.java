package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/14 13:35
 */
@ApiModel(description = "商户端调单列表字段")
public class MerchantOrderSheetDTO {

    @ApiModelProperty(value = "调单id")
    private Integer id;
    /**
     * 调单流水号
     */
    @ApiModelProperty(value = "调单流水号")
    private String orderNo;
    /**
     * 风险点
     */
    @ApiModelProperty(value = "风险点")
    private String riskPoint;
    /**
     * 调单说明
     */
    @ApiModelProperty(value = "调单说明")
    private String orderNote;
    /**
     * 调单数量
     */
    @ApiModelProperty(value = "调单数量")
    private Integer orderNum;
    /**
     * 订单流水号
     */
    @ApiModelProperty(value = "订单流水号")
    private String orderSerialNum;
    /**
     * 收款户名
     */
    @ApiModelProperty(value = "收款户名")
    private String name;
    /**
     * 审核结论
     */
    @ApiModelProperty(value = "审核结论")
    private String auditResult;
    /**
     * 审核说明
     */
    @ApiModelProperty(value = "审核说明")
    private String auditNote;
    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称")
    private String fileName;
    /**
     * 0-未处理1-已处理2-已拒绝
     */
    @ApiModelProperty(value = "状态0-未处理1-已处理2-已拒绝")
    private Integer status;
    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    private Integer trxOrderId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private String createDate;

    /**
     * 反馈备注
     */
    @ApiModelProperty(value = "反馈备注")
    private String feedBackComment;
    /**
     * 反馈时间
     */
    @ApiModelProperty(value = "反馈时间")
    private Date feedBackTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRiskPoint() {
        return riskPoint;
    }

    public void setRiskPoint(String riskPoint) {
        this.riskPoint = riskPoint;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderSerialNum() {
        return orderSerialNum;
    }

    public void setOrderSerialNum(String orderSerialNum) {
        this.orderSerialNum = orderSerialNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String getAuditNote() {
        return auditNote;
    }

    public void setAuditNote(String auditNote) {
        this.auditNote = auditNote;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTrxOrderId() {
        return trxOrderId;
    }

    public void setTrxOrderId(Integer trxOrderId) {
        this.trxOrderId = trxOrderId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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
        return "MerchantOrderSheetDTO{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", riskPoint='" + riskPoint + '\'' +
                ", orderNote='" + orderNote + '\'' +
                ", orderNum=" + orderNum +
                ", orderSerialNum='" + orderSerialNum + '\'' +
                ", name='" + name + '\'' +
                ", auditResult='" + auditResult + '\'' +
                ", auditNote='" + auditNote + '\'' +
                ", fileName='" + fileName + '\'' +
                ", status=" + status +
                ", trxOrderId=" + trxOrderId +
                ", createDate='" + createDate + '\'' +
                ", feedBackComment='" + feedBackComment + '\'' +
                ", feedBackTime=" + feedBackTime +
                '}';
    }
}
