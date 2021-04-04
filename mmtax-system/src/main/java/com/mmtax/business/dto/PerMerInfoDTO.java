package com.mmtax.business.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PerMerInfoDTO {
    private Integer id;
    /**
     * 个人微信关联id
     */
    private Integer wechatInfoId;
    private Integer taxTypeId;
    private Integer orderStatus;
    /**
     * 行业类型
     */
    private String businessType;
    /**
     * 税率类型
     */
    private String taxType;
    /**
     * 科目内容（用于发票内容）
     */
    private String invoiceContent;
    /**
     * 经营范围
     */
    private String businessScope;
    /**
     * 个体工商户税率
     */
    private String taxPerson;
    /**
     * 申请编号
     */
    private String applyNumber;
    /**
     * 申请人姓名
     */
    private String applyName;
    /**
     * 身份证号
     */
    private String idCardNumber;
    /**
     * 银行卡号
     */
    private String bankNo;
    /**
     * 手机号
     */
    private String mobileNumber;
    /**
     * 税源地Id
     */
    private Integer taxSounrceCompanyId;
    /**
     * 税源地公司名称
     */
    private String taxSounrceCompanyName;
    /**
     * 身份证人像面存储地址
     */
    private String idCardPictureFront;
    /**
     * 身份证国徽面存储地址
     */
    private String idCardPictureBehind;
    /**
     * 手持身份证存储地址
     */
    private String idCardPictureFrontWithPeople;
    /**
     * 营业执照存储地址
     */
    private String businessLicense;
    /**
     * 营业执照名称
     */
    private String businessLicenseName;
    /**
     * 经营场所
     */
    private String premises;
    private String createTime;

}
