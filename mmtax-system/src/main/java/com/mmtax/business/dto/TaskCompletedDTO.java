package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/10/15 14:08
 */
@ApiModel("任务完成记录")
public class TaskCompletedDTO {

    @Excel(name = "任务编号")
    @ApiModelProperty("任务编号")
    private String taskSerialNum;

    @Excel(name = "任务名称")
    @ApiModelProperty("任务名称")
    private String taskName;

    @Excel(name = "任务数量")
    @ApiModelProperty("任务数量")
    private Integer taskNum;

    @Excel(name = "完成人")
    @ApiModelProperty("完成人")
    private String name;

    @Excel(name = "联系电话")
    @ApiModelProperty("联系电话")
    private String mobileNo;

    @Excel(name = "承接方")
    @ApiModelProperty("承接方")
    private String taxSourceName;

    @Excel(name = "派单方")
    @ApiModelProperty("派单方")
    private String merchantName;

    @Excel(name = "派单时间")
    @ApiModelProperty("派单时间")
    private Date createTime;

    @Excel(name = "任务完成时间")
    @ApiModelProperty("任务完成时间")
    private String taskCompleteTime;

    public String getTaskSerialNum() {
        return taskSerialNum;
    }

    public void setTaskSerialNum(String taskSerialNum) {
        this.taskSerialNum = taskSerialNum;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getTaxSourceName() {
        return taxSourceName;
    }

    public void setTaxSourceName(String taxSourceName) {
        this.taxSourceName = taxSourceName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTaskCompleteTime() {
        return taskCompleteTime;
    }

    public void setTaskCompleteTime(String taskCompleteTime) {
        this.taskCompleteTime = taskCompleteTime;
    }

}
