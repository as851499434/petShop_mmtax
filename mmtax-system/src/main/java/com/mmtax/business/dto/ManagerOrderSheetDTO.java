package com.mmtax.business.dto;

import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/16 14:01
 */
public class ManagerOrderSheetDTO  extends BaseEntity {
    @ApiModelProperty(value = "调单id", required = true)
    private Integer id;

    /**
     * 风险点
     */
    @ApiModelProperty(value = "风险点", required = true)
    private String riskPoint;

    /**
     * 调单说明
     */
    @ApiModelProperty(value = "调单说明", required = true)
    private String orderNote;

    /**
     * 调单数量
     */
    @ApiModelProperty(value = "调单数量", required = true)
    private Integer orderNum;
    /**
     * 调单流水号
     */
    @ApiModelProperty(value = "调单流水号", required = true)
    private String orderNo;

    /**
     * 审核结论
     */
    @ApiModelProperty(value = "审核结论", required = true)
    private String auditResult;

    /**
     * 审核说明
     */
    @ApiModelProperty(value = "审核说明", required = true)
    private String auditNote;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    private String createTime;
    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称", required = true)
    private String merchantName;
    /**
     * 收款人名字
     */
    @ApiModelProperty(value = "收款人名字", required = true)
    private String name;
    @ApiModelProperty(value = "所属销售", required = true)
    private String amount;
    @ApiModelProperty(value = "所属销售", required = true)
    private String saleName;
    @ApiModelProperty(hidden = true)
    private String saleId;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
