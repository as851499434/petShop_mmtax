package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/19 13:44
 */
@Data
@ApiModel("审核查询条件")
public class CheckAndChangeRateDTO {
    @ApiModelProperty("商户名称")
    private String merchantName;

    @ApiModelProperty("商户编号")
    private String merchantCode;

    @ApiModelProperty("直属代理商名称")
    private String agentName;

}
