package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author：YH
 * @Date：2020/10/16 9:46
 */
@Data
public class CustomerTaskDTO {

    @ApiModelProperty("身份证号")
    private String certificateNo ;
    @ApiModelProperty("任务名称")
    private String taskName;

}
