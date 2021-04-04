package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author：YH
 * @Date：2020/10/16 9:58
 */
@Data
public class CustomerTaskResultDTO {
    @ApiModelProperty("任务id")
    private Integer customerTaskId;
    @ApiModelProperty("任务名称")
    private String taskName;
    @ApiModelProperty("派单时间")
    private String taskBeginTime;
    @ApiModelProperty("任务状态")
    private Integer taskStatus;
}
