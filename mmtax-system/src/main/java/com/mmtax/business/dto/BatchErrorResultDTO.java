package com.mmtax.business.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author：YH
 * @Date：2020/11/27 9:25
 */
@Data
public class BatchErrorResultDTO implements Serializable {
    private static final long serialVersionUID = 20171515101102103L;
    @ApiModelProperty(value = "商户订单号")
    private String merchantSerialNum;
    @ApiModelProperty(value = "收款账号(个人银行卡号)")
    private String bankCard;
    @ApiModelProperty(value = "收款户名(真实姓名)")
    private String name;
    @ApiModelProperty(value = "身份证号")
    private String idCardNo;
    @ApiModelProperty(value = "银行卡预留手机号")
    private String mobile;
    @ApiModelProperty(value = "打款金额")
    private BigDecimal amount;
    @ApiModelProperty(value = "校验结果")
    private String checkResult;
    @ApiModelProperty(value = "打款备注")
    private String remark;
    @ApiModelProperty(value = "失败原因")
    private String comment;

}
