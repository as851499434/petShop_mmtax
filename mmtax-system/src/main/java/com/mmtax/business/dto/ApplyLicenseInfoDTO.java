package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/30 10:14
 */
@Data
public class ApplyLicenseInfoDTO {
    /**
     * 申请Id
     */
    @ApiModelProperty("申请Id")
    private Integer applyId;
    /**
     * 申请编号
     */
    @Excel(name = "申请编号")
    @ApiModelProperty("申请编号")
    private String applyNumber;
    /**
     * 申请人姓名
     */
    @Excel(name = "申请人姓名")
    @ApiModelProperty("申请人姓名")
    private String applyName;
    /**
     * 身份证号
     */
    @Excel(name = "身份证号")
    @ApiModelProperty("身份证号")
    private String idCardNumber;
    /**
     * 手机号
     */
    @Excel(name = "手机号")
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
    @Excel(name = "税源地公司名称")
    @ApiModelProperty("税源地公司名称")
    private String taxSounrceCompanyName;
    /**
     * 科目内容（用于发票内容）
     */
    @Excel(name = "科目内容（用于发票内容）")
    @ApiModelProperty("科目内容（用于发票内容）")
    private String invoiceContent;
    /**
     * 经营范围
     */
    @Excel(name = "经营范围")
    @ApiModelProperty("经营范围")
    private String businessScope;
    /**
     * 个税政策
     */
    @Excel(name = "个税政策")
    @ApiModelProperty("个税政策")
    private String taxPerson;

    /**
     * 提交申请时间
     */
    @Excel(name = "提交申请时间")
    @ApiModelProperty("提交申请时间")
    private String applyTime;
    /**
     * 更新时间
     */
    @Excel(name = "更新时间")
    @ApiModelProperty("更新时间")
    private String updateTime;
}
