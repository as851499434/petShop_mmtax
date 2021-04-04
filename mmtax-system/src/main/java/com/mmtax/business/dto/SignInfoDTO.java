package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Objects;

@Data
public class SignInfoDTO {
    @ApiModelProperty(value = "收款账号(个人银行卡号)或支付宝号")
    private String bankNo;
    @ApiModelProperty(value = "签约姓名(真实姓名,必填)")
    private String name;
    @ApiModelProperty(value = "身份证号(必填)")
    private String idCardNo;
    @ApiModelProperty(value = "手机号（必填）")
    private String mobile;
    @ApiModelProperty(value = "失败原因")
    private String comment;
    @ApiModelProperty(value = "签约备注(20个字符以内,非必填)")
    private String remark;
    @ApiModelProperty(value = "签约文件模板id")
    private Integer esignTemplateId;
    @ApiModelProperty(value = "付款渠道")
    private String paymentChannel;
    @ApiModelProperty(value = "api签约流水号")
    private String signSerialNum;
    @ApiModelProperty(value = "签约岗位id")
    private Integer postId;
    @ApiModelProperty(value = "签约岗位")
    private String post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SignInfoDTO)) return false;
        SignInfoDTO that = (SignInfoDTO) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(idCardNo, that.idCardNo) &&
                Objects.equals(mobile, that.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankNo, name, idCardNo, mobile);
    }
}
