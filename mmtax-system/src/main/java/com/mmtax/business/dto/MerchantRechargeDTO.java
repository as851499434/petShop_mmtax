package com.mmtax.business.dto;

import com.mmtax.business.domain.RechargeRecord;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 开票申请充值流水
 *
 * @author yuanligang
 * @date 2019/8/12
 */
public class MerchantRechargeDTO {

    @ApiModelProperty("申请开票总额")
    private BigDecimal totalAmount;

    @ApiModelProperty("充值流水明细")
    private List<RechargeRecord> records;

}
