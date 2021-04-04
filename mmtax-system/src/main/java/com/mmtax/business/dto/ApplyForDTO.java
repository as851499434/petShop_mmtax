package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/27 10:16
 */
@Data
public class ApplyForDTO {

    @ApiModelProperty(value = "申请人姓名")
    private String applyName;

    @ApiModelProperty(value = "手机号")
    private String mobileNumber;

    @ApiModelProperty(value = "订单状态 -1 - 待处理 0-办理中 1-已办理 2-驳回申请 3-申请失败 4-办理完成")
    private Integer orderStatus;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;
}
