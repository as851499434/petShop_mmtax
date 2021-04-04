package com.mmtax.business.dto;

import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QuaryTransferOrderinfoDTO extends BaseEntity {
    @ApiModelProperty(value = "订单流水号")
    private String orderSerialNum;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "员工名称")
    private String customerName;
    @ApiModelProperty(value = "打款状态")
    private Integer status;
    @ApiModelProperty(value = "打款渠道")
    private String paymentChannel;
    @ApiModelProperty(value = "开始时间")
    private String startDate;
    @ApiModelProperty(value = "结束时间")
    private String endDate;
}
