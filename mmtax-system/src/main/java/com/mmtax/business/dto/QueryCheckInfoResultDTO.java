package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author：YH
 * @Date：2020/11/5 14:35
 */
@Data
public class QueryCheckInfoResultDTO {

    @ApiModelProperty("通过验证订单数 ")
    private long successCount;
    @ApiModelProperty("通过验证订单总金额")
    private BigDecimal successAmounts;
    @ApiModelProperty("通过验证订单服务费")
    private BigDecimal successCharges;

    @ApiModelProperty("失败订单数")
    private long failCount;
    @ApiModelProperty("失败订单总金额")
    private BigDecimal failAmounts;

    @ApiModelProperty("待处理订单数 ")
    private long processCount;
    @ApiModelProperty("待处理订单总金额")
    private BigDecimal processAmounts;

}
