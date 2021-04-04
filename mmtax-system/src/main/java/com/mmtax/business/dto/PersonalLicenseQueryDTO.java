package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/12/1 11:18
 */
@Data
public class PersonalLicenseQueryDTO {
    @ApiModelProperty(value = "营业执照名称")
    private String businessLicenseName;

    @ApiModelProperty(value = "申请人姓名")
    private String applyName;

    @ApiModelProperty(value = "手机号")
    private String mobileNumber;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;
}
