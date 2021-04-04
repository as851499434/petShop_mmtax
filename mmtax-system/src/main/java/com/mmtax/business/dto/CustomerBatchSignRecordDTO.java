package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 员工批量签约记录
 * @author ljd
 * @date 2020-08-02
 */
@ApiModel(description = "员工批量签约记录")
public class CustomerBatchSignRecordDTO {

    @ApiModelProperty(value = "记录id,隐藏字段")
    private Integer id;
    @ApiModelProperty(value = "批量处理编号")
    private String trxExternalNo;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "批次号")
    private String batchNo;
    @ApiModelProperty(value = "总记录数")
    private Integer signCount;
    @ApiModelProperty(value = "通过录数")
    private Integer actualCount;
    @ApiModelProperty(value = "通过录数")
    private Integer failCount;
    @ApiModelProperty(value = "0-未处理1-已处理")
    private Integer status;
    @ApiModelProperty(value = "创建者")
    private String creater;
    @ApiModelProperty(value = "操作者")
    private String operator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrxExternalNo() {
        return trxExternalNo;
    }

    public void setTrxExternalNo(String trxExternalNo) {
        this.trxExternalNo = trxExternalNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getSignCount() {
        return signCount;
    }

    public void setSignCount(Integer signCount) {
        this.signCount = signCount;
    }

    public Integer getActualCount() {
        return actualCount;
    }

    public void setActualCount(Integer actualCount) {
        this.actualCount = actualCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    @Override
    public String toString() {
        return "CustomerBatchSignRecordDTO{" +
                "id=" + id +
                ", trxExternalNo='" + trxExternalNo + '\'' +
                ", createTime='" + createTime + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", signCount=" + signCount +
                ", actualCount=" + actualCount +
                ", status=" + status +
                ", creater='" + creater + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}
