package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author：YH
 * @Date：2020/11/27 21:02
 */
@Data
public class SignBatchErrorResultDTO {

    @ApiModelProperty(value = "签约户名")
    private String name;
    @ApiModelProperty(value = "身份证号")
    private String idCardNo;
    @ApiModelProperty(value = "签约手机号")
    private String mobile;
    @ApiModelProperty(value = "银行卡号")
    private String bankNo;
    @ApiModelProperty(value = "签约备注")
    private String remark;
    @ApiModelProperty(value = "校验结果")
    private String checkResult;
    @ApiModelProperty(value = "失败原因")
    private String comment;
}
