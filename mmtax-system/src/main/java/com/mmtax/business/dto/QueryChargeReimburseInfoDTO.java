package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author：YH
 * @Date：2020/10/30 14:01
 */
@Data
public class QueryChargeReimburseInfoDTO {
    @ApiModelProperty("商户名称")
    private String merchantName;
    @ApiModelProperty("收款户名")
    private String payeeName;
    @ApiModelProperty("订单流水号")
    private String orderSerialNum;
    @ApiModelProperty("创建时间-开始")
    private String startDate;
    @ApiModelProperty("创建时间-结束")
    private String endDate;
}
