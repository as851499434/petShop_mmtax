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
 * @date 2020/11/18 11:15
 */
@Data
@ToString
@ApiModel("费率通过审核信息")
public class ChangeRateSuccessDTO {
    @ApiModelProperty("商户Id")
    private Integer merchantId;

    @ApiModelProperty("商户编号")
    private String merchantCode;

    @ApiModelProperty("原普通费率")
    private BigDecimal oldOrdinaryServiceRate;

    @ApiModelProperty("原大额费率")
    private BigDecimal oldBigServiceRate;

    @ApiModelProperty("新普通费率")
    private BigDecimal newOrdinaryServiceRate;

    @ApiModelProperty("新大额费率")
    private BigDecimal newBigServiceRate;
}
