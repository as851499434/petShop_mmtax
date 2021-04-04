package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/27 17:33
 */
@Data
public class CheckPersonalMerchantInfo {
    /**
     * 申请Id
     */
    @ApiModelProperty("申请Id")
    private Integer applyId;
    /**
     * 申请编号
     */
    @ApiModelProperty("申请编号")
    private String applyNumber;
    /**
     * 订单状态 -1 - 待处理 0-办理中 1-已办理 2-驳回申请 3-申请失败 4-办理完成
     */
    @ApiModelProperty("订单状态 -1 - 待处理 0-办理中 1-已办理 2-驳回申请 3-申请失败 4-办理完成")
    private Integer orderStatus;
    /**
     * 驳回理由
     */
    @ApiModelProperty("驳回理由")
    private String remark;
}
