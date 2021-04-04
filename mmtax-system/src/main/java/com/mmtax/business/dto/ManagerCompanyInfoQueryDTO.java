package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

    @Data
    public class ManagerCompanyInfoQueryDTO {
        @ApiModelProperty("批次号")
        private String batchNo;

        @ApiModelProperty("发票申请编号")
        private String invoiceSerialNum;

        @ApiModelProperty("发票编号")
        private String invoiceNo;

        @ApiModelProperty("发票抬头")
        private String invoiceTitle;

        @ApiModelProperty("应税劳务服务名称")
        private String content;

        @ApiModelProperty(value = "交易类型")
        private Integer type;

        @ApiModelProperty(value = "商户名称")
        private String merchantName;

        @ApiModelProperty(value = "任务名称")
        private String taskName;

        @ApiModelProperty(value = "付款状态")
        private String status;

        @ApiModelProperty(value = "交易类型：0-账户收款 1-收取佣金")
        private String paymentType;

        @ApiModelProperty(value = "员工姓名")
        private String name;

        @ApiModelProperty(value = "手机号")
        private String mobile;

        @ApiModelProperty(value = "所属商户名称")
        private String companyName;

        @ApiModelProperty(value = "开始时间")
        private String startDate;

        @ApiModelProperty(value = "结束时间")
        private String endDate;

        @ApiModelProperty(value = "起始金额")
        private String startMoney;

        @ApiModelProperty(value = "结束金额")
        private String endMoney;

        @ApiModelProperty(value = "签约过期状态")
        private Integer expireStatus;

        @ApiModelProperty(value = "公司名称")
        private String taxSounrceCompanyName;

        @ApiModelProperty(value = "商户id")
        private Integer id;

        @ApiModelProperty(value = "税源地id")
        private Integer taxSourceId;

        @ApiModelProperty(value = "当前登录用户id")
        private Long userId;


    }
