package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
public class ManagerContractInfoDTO {
    @ApiModelProperty(value = "商户id")
    private String id;

    @Excel(name = "姓名")
    @ApiModelProperty(value = "姓名   ")
    private String name;

    @Excel(name = "手机号")
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @Excel(name = "身份证号")
    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @ApiModelProperty(value = "证件号")
    private String payeeIdCardNo;

    @Excel(name = "商户名称")
    @ApiModelProperty(value = "商户名称")
    private String merchantName;

    @Excel(name = "签约状态",readConverterExp = "0=已签,1=已过期 ")
    @ApiModelProperty(value = "签约状态")
    private Integer expireStatus;

    @Excel(name = "合同生效时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "合同生效时间")
    private Date createTime;


}

