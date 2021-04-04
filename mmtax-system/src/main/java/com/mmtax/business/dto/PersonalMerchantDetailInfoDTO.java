package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * create by
 *
 * @author zouyuanpeng
 * @date 2020/11/27 14:03
 */
@Data
public class PersonalMerchantDetailInfoDTO {
    /**
     * 申请Id
     */
    @ApiModelProperty("申请Id")
    private Integer applyId;

    /**
     * 关联税务Id
     */
    @ApiModelProperty("关联税务Id")
    private Integer taxTypeId;

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
     * 银行卡号
     */
    @ApiModelProperty("银行卡号")
    private String bankNo;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobileNumber;

    /**
     * 身份证人像面存储地址
     */
    @ApiModelProperty("身份证人像面存储地址")
    private String idCardPictureFront;
    /**
     * 身份证国徽面存储地址
     */
    @ApiModelProperty("身份证国徽面存储地址")
    private String idCardPictureBehind;
    /**
     * 手持身份证存储地址
     */
    @ApiModelProperty("手持身份证存储地址")
    private String idCardPictureFrontWithPeople;

    /**
     * 营业执照名称
     */
    @ApiModelProperty("营业执照名称")
    private String businessLicenseName;
    /**
     * 经营场所
     */
    @ApiModelProperty("经营场所")
    private String premises;

    /**
     * 税源地Id
     */
    @ApiModelProperty("税源地Id")
    private Integer taxSounrceCompanyId;
    /**
     * 税源地公司名称
     */
    @ApiModelProperty("税源地公司名称")
    private String taxSounrceCompanyName;
    /**
     * 行业类型
     */
    @ApiModelProperty("行业类型")
    private String businessType;
    /**
     * 税率类型
     */
    @ApiModelProperty("税率类型")
    private String taxType;
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
     * 个体工商户税率
     */
    @ApiModelProperty("个体工商户税率")
    private String taxPerson;
}
