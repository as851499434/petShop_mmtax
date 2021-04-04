package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author：YH
 * @Date：2020/10/16 11:13
 */
@Data
public class CustomerTaskDetailDTO {

    @ApiModelProperty("任务状态 0 待完成,1已完成")
    private Integer taskStatus;
    @ApiModelProperty("派单时间")
    private String taskBeginTime;
    private String taskRequireCompleteTime;
    @ApiModelProperty("剩余时间")
    private String remainTime;
    @ApiModelProperty("完成时间")
    private String taskCompleteTime;



    @ApiModelProperty("任务号码")
    private String taskSerialNum;
    @ApiModelProperty("任务名称")
    private String taskName;
    @ApiModelProperty("任务简介")
    private String task_info;
    @ApiModelProperty("任务数量")
    private Integer taskNum;
    @ApiModelProperty("佣金")
    private BigDecimal allMount;
    @ApiModelProperty("单价")
    private BigDecimal amount;

    @ApiModelProperty("任务方")
    private String merchantName;
    @ApiModelProperty("承接方")
    private String taxSounrceCompanyName;

    private Integer merchantId;
    private Integer taskInfoId;



}
