package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/10/16 9:47
 */
public class TaskRecordDTO {
    @Excel(name = "批次号")
    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @Excel(name = "任务名称")
    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @Excel(name = "任务简介")
    @ApiModelProperty(value = "任务简介")
    private String taskInfo;

    @Excel(name = "商户Id")
    @ApiModelProperty("商户Id")
    private String merchantId;

    @Excel(name = "发任务方")
    @ApiModelProperty("发任务方")
    private String merchantName;

    @Excel(name = "任务总量")
    @ApiModelProperty(value = "任务总量")
    private String taskTotalNum;

    @Excel(name = "任务id")
    @ApiModelProperty(value = "任务id")
    private String taskId;

    @Excel(name = "承接方")
    @ApiModelProperty("承接方")
    private String taxSourceName;

    @Excel(name = "派单时间")
    @ApiModelProperty(value = "派单时间")
    private String taskBeginTime;

    @Excel(name = "要求完成时间")
    @ApiModelProperty(value = "要求完成时间")
    private String taskRequireCompleteTime;

    @Excel(name = "派单任务状态")
    @ApiModelProperty(value = "派单任务状态")
    private String taskStatus;

    @Excel(name = "剩余任务时间")
    @ApiModelProperty(value = "剩余任务时间")
    private String remainTime;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getTaskTotalNum() {
        return taskTotalNum;
    }

    public void setTaskTotalNum(String taskTotalNum) {
        this.taskTotalNum = taskTotalNum;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaxSourceName() {
        return taxSourceName;
    }

    public void setTaxSourceName(String taxSourceName) {
        this.taxSourceName = taxSourceName;
    }

    public String getTaskBeginTime() {
        return taskBeginTime;
    }

    public void setTaskBeginTime(String taskBeginTime) {
        this.taskBeginTime = taskBeginTime;
    }

    public String getTaskRequireCompleteTime() {
        return taskRequireCompleteTime;
    }

    public void setTaskRequireCompleteTime(String taskRequireCompleteTime) {
        this.taskRequireCompleteTime = taskRequireCompleteTime;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }
}
