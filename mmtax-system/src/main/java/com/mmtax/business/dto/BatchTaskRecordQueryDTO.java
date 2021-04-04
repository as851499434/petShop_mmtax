package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BatchTaskRecordQueryDTO {
    @ApiModelProperty("商户id")
    private String merchantId;
    @ApiModelProperty("任务编号")
    private String taskSerialNum;
    @ApiModelProperty("完成人")
    private String name;
    @ApiModelProperty("批次号")
    private String batchNo;
    @ApiModelProperty("任务名称")
    private String taskName;
    @ApiModelProperty("任务简介")
    private String taskInfo;
    @ApiModelProperty("任务单状态")
    private String taskStatus;
    @ApiModelProperty("派单时间(起)")
    private String taskBeginStartTime;
    @ApiModelProperty("派单时间(止)")
    private String taskBeginEndTime;
    @ApiModelProperty("完成时间(起)")
    private String taskCompleteStartTime;
    @ApiModelProperty("完成时间(止)")
    private String taskCompleteEndTime;
    @ApiModelProperty("要求完成时间(起)")
    private String taskRequireCompleteStartTime;
    @ApiModelProperty("要求完成时间(止)")
    private String taskRequireCompleteEndTime;
    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "页码")
    private Integer currentPage;

    private Integer startIndex;

}
