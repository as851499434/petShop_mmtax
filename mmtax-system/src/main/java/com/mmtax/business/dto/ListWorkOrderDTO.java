package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author：YH
 * @Date：2020/12/10 17:01
 */
@Data
public class ListWorkOrderDTO {

    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("手机号")
    private String mobile;
    @ApiModelProperty("工单状态")
    private Integer status;
    @ApiModelProperty("发布时间 查询开始")
    private String startPublishTime;
    @ApiModelProperty("发布时间 查询结束")
    private String endtPublishTime;
    @ApiModelProperty("反馈时间 查询开始")
    private String startFeedBackTime;
    @ApiModelProperty("反馈时间 查询结束")
    private String endFeedBackTime;
}
