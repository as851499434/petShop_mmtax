package com.mmtax.business.dto;

import lombok.Data;

@Data
public class RegisterInfoDTO {
    private Integer wechatInfoId;
    /** 身份证人像面存储地址 */
    private String idCardPictureFront;
    /** 身份证国徽面存储地址 */
    private String idCardPictureBehind;
    /** 手持身份证存储地址 */
    private String idCardPictureFrontWithPeople;
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
}
