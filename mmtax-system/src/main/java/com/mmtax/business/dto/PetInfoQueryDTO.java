package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 后台员工信息查询
 * @author Ljd
 * @date 2020/7/10
 */
@Data
public class PetInfoQueryDTO {

    @ApiModelProperty(value = "名字")
    private String petName;

    @ApiModelProperty(value = "宠物种类")
    private String petType;

    @ApiModelProperty(value = "宠物信息种类")
    private Integer petInfoType;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;

}
