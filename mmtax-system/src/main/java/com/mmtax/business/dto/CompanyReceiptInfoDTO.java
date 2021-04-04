package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CompanyReceiptInfoDTO {
    @ApiModelProperty(value = "明细表id")
    private Integer id;

    @ApiModelProperty(value = "商户id")
    private Integer merchantId;

    @Excel(name = "流水号")
    @ApiModelProperty(value = "流水号")
    private String orderSerialNum;

    @Excel(name = "账户名称")
    @ApiModelProperty(value = "账户名称")
    private String merchantName;

    @Excel(name = "交易类型",readConverterExp = "0=发放佣金,1=账户付款" )
    @ApiModelProperty(value = "交易类型")
    private Integer type;

    @ApiModelProperty(value = "个人交易类型")
    private Integer paymentType;

    @ApiModelProperty(value = "交易金额")
    private BigDecimal paymentAmount;

    @Excel(name = "增加(元)")
    @ApiModelProperty(value = "增加(元)")
    private BigDecimal increaseAmount;

    @Excel(name = "减少(元)")
    @ApiModelProperty(value = "减少(元)")
    private BigDecimal decreaseAmount;

    @Excel(name = "账户余额")
    @ApiModelProperty(value = "交易之后可用余额")
    private BigDecimal paymentUsableAmountAfter;

    @Excel(name = "交易时间")
    @ApiModelProperty(value = "交易时间")
    private String createTime;

}
