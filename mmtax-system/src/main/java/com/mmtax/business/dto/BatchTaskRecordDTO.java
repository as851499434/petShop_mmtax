package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BatchTaskRecordDTO {
    @ApiModelProperty(value = "商户id")
    private Integer merchantId;

    @ApiModelProperty(value = "任务信息id")
    private Integer taskInfoId;

    @Excel(name = "批次号")
    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @Excel(name = "任务名称")
    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @Excel(name = "任务简介")
    @ApiModelProperty(value = "任务简介")
    private String taskInfo;

    @Excel(name = "任务数量")
    @ApiModelProperty(value = "任务数量")
    private String taskTotalNum;

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

}
