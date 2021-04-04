package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ManagerReturnListTrxOrderDTO {
    @Excel(name = "收款户名")
    @ApiModelProperty(value = "收款户名")
    private String payeeName;

    @Excel(name = "收款账号")
    @ApiModelProperty(value = "收款账号")
    private String payeeBankNo;

    @Excel(name = "所属商户")
    @ApiModelProperty(value = "所属商户")
    private String merchantName;

    @Excel(name = "商户打款请求金额")
    @ApiModelProperty(value = "商户打款请求金额")
    private String amount;


    @Excel(name = "退还服务费")
    @ApiModelProperty(value = "退还服务费")
    private String returnCharge;

    @Excel(name = "退回状态")
    @ApiModelProperty(value = "退回状态")
    private String status;

    @Excel(name = "退还流水号")
    @ApiModelProperty(value = "退还流水号")
    private String returnSerialNum;
    @Excel(name = "订单流水号")
    @ApiModelProperty(value = "订单流水号")
    private String orderSerialNum;


    @Excel(name = "创建时间")
    @ApiModelProperty(value = "创建时间")
    private String createTime;


}
