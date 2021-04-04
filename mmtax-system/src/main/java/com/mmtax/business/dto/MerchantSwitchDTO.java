package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MerchantSwitchDTO {
    @ApiModelProperty("推广开关")
    private Integer promotionSwitch;
    @ApiModelProperty("签约开关")
    private Integer signSwitch;
    @ApiModelProperty("派单商户开关")
    private Integer sendOrderSwitch;
    @ApiModelProperty("派单模式")
    private Integer sendOrderMode;
    @ApiModelProperty("打款方式 0-手动 1-自动")
    private Integer moneyModel;
}
