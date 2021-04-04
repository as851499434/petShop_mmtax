package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author：YH
 * @Date：2020/12/11 15:03
 */
@Data
public class WorkOrderFeedBackDTO {

    @ApiModelProperty("工单编号")
    private String workOrderSerialNumber;
    @ApiModelProperty("文本")
    private String txt;
    @ApiModelProperty("pdf地址")
    private String pdfAddress;
    @ApiModelProperty("图片地址")
    private String imgAddress;
    @ApiModelProperty("视频地址")
    private String videoAddress;
}
