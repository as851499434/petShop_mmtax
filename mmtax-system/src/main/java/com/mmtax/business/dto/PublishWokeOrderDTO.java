package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author：YH
 * @Date：2020/12/10 14:03
 */
@Data
public class PublishWokeOrderDTO {

    @ApiModelProperty("入网申请编号")
    private String applyNumber;
    @ApiModelProperty("名字")
    private String name;
    @ApiModelProperty("身份证号")
    private String certificateNo;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("内容")
    private String content;
}
