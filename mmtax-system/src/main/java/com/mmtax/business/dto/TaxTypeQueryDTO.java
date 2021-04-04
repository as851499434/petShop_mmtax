package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/12/1 14:53
 */
@Data
public class TaxTypeQueryDTO {

    @ApiModelProperty(value = "税源地Id")
    private Integer taxSounrceCompanyId;

    @ApiModelProperty(value = "税源地")
    private String taxSounrceCompanyName;

    @ApiModelProperty(value = "行业类型")
    private String businessType;

    @ApiModelProperty(value = "科目内容（用于发票内容）")
    private String invoiceContent;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;
}
