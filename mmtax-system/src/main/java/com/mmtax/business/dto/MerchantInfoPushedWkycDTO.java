package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/18 9:21
 */
@Data
@ToString
@ApiModel("悟空云创需要的数据")
public class MerchantInfoPushedWkycDTO {

    @ApiModelProperty("商户编码")
    private String merchantCode;

    @ApiModelProperty("商户名称")
    private String merchantName;

    @ApiModelProperty("代理商编码")
    private String agentNumber;

    @ApiModelProperty("普通费率")
    private BigDecimal ordinaryRate;

    @ApiModelProperty("大额费率")
    private BigDecimal bigRate;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String mobileNo;

    @ApiModelProperty("加密后的sign")
    private String sign;
}
