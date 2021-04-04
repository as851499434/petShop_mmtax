package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SignCheckPassDTO {
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "商户id")
    private Integer merchantId;
    @ApiModelProperty(value = "签署文件模板id")
    private Integer templateId;
    @ApiModelProperty(value = "后续支付的渠道")
    private String paymentChannel;
    @ApiModelProperty(value = "签约岗位id")
    private Integer postId;
    @ApiModelProperty(value = "签约岗位")
    private String post;
}
