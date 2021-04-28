package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PetGoodQueryDTO {
    @ApiModelProperty(value = "商品名字")
    String name ;
    @ApiModelProperty(value = "厂家")
    String factory;
    @ApiModelProperty(value = "开始时间")
    private String startDate;
    @ApiModelProperty(value = "结束时间")
    private String endDate;

}
