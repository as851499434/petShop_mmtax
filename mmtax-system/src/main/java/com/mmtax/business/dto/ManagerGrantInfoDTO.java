package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class ManagerGrantInfoDTO {
//    @Excel(name = "身份证号")
    @ApiModelProperty(value = "身份证号")
    private String certificateNo;

    @Excel(name = "批次号")
    @ApiModelProperty(value = "批次号")
    private String batchNo;

    @Excel(name = "任务名称")
    @ApiModelProperty(value = "任务名称")
    private String taskName;

    @Excel(name = "商户名称")
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @Excel(name = "开始时间 ",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "申请时间")
    private Date taskBeginTime;

    @Excel(name = "完成时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "完成时间")
    private Date taskCompleteTime;

    @Excel(name = "订单总笔数")
    @ApiModelProperty(value = "订单总笔数")
    private Integer taskTotalNum;

    @Excel(name = "发放金额")
    @ApiModelProperty(value = "发放金额")
    private BigDecimal taskAmount;

    @Excel(name = "接单笔数")
    @ApiModelProperty(value = "接单笔数")
    private Integer successNum;

    @Excel(name = "接单金额")
    @ApiModelProperty(value = "接单金额")
    private BigDecimal successAmount;

    @Excel(name = "未接单笔数")
    @ApiModelProperty(value = "未接单笔数")
    private Integer failNum;

    @Excel(name = "未接单金额")
    @ApiModelProperty(value = "未接单金额")
    private BigDecimal failAmount;

    @Excel(name = "付款成功笔数")
    @ApiModelProperty(value = "付款成功笔数")
    private Integer paymentNum;

    @Excel(name = "付款成功金额")
    @ApiModelProperty(value = "付款成功金额")
    private BigDecimal paymentAmount;
}
