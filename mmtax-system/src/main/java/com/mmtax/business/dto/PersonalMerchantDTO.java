package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/27 10:31
 */
@Data
public class PersonalMerchantDTO {
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
     * 申请人姓名
     */
    @ApiModelProperty("申请人姓名")
    private String applyName;
    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String idCardNumber;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobileNumber;
    /**
     * 税源地Id
     */
    @ApiModelProperty("税源地Id")
    private String taxSounrceCompanyId;
    /**
     * 税源地公司名称
     */
    @ApiModelProperty("税源地公司名称")
    private String taxSounrceCompanyName;
    /**
     * 科目内容（用于发票内容）
     */
    @ApiModelProperty("科目内容（用于发票内容）")
    private String invoiceContent;
    /**
     * 经营范围
     */
    @ApiModelProperty("经营范围")
    private String businessScope;
    /**
     * 行业类型
     */
    @ApiModelProperty("行业类型")
    private String businessType;
    /**
     * 订单状态 -1 - 待处理 0-办理中 1-已办理 2-驳回申请 3-申请失败 4-办理完成
     */
    @ApiModelProperty("订单状态 -1 - 待处理 0-办理中 1-已办理 2-驳回申请 3-申请失败 4-办理完成")
    private Integer orderStatus;
    /**
     * 提交申请时间
     */
    @ApiModelProperty("提交申请时间")
    private String applyTime;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private String updateTime;
}
