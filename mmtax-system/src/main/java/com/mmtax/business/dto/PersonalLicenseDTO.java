package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/12/1 11:24
 */
@Data
public class PersonalLicenseDTO {
    /**
     * 申请Id
     */
    @ApiModelProperty("申请Id")
    private Integer applyId;
    /**
     * 营业执照名称
     */
    @Excel(name = "营业执照名称")
    @ApiModelProperty("营业执照名称")
    private String businessLicenseName;
    /**
     * 申请人姓名
     */
    @Excel(name = "申请人姓名")
    @ApiModelProperty("申请人姓名")
    private String applyName;
    /**
     * 营业执照类型
     */
    @Excel(name = "营业执照类型")
    @ApiModelProperty("营业执照类型")
    private String businessLicenseType;
    /**
     * 组织形式
     */
    @Excel(name = "组织形式")
    @ApiModelProperty("组织形式")
    private String organization;
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
     * 个体工商户税率
     */
    @Excel(name = "个体工商户税率")
    @ApiModelProperty("个体工商户税率")
    private String taxPerson;
    /**
     * 经营范围
     */
    @Excel(name = "经营范围")
    @ApiModelProperty("经营范围")
    private String businessScope;
    /**
     * 提交申请时间
     */
    @Excel(name = "提交申请时间")
    @ApiModelProperty("提交申请时间")
    private String applyTime;
    /**
     * 营业执照注册时间
     */
    @Excel(name = "营业执照注册时间")
    @ApiModelProperty("营业执照注册时间")
    private String businessLicenseRegisterTime;
    /**
     * 营业执照存储地址
     */
    @ApiModelProperty("营业执照存储地址")
    private String businessLicense;
}
