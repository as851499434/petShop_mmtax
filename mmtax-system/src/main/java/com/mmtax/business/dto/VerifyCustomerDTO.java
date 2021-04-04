package com.mmtax.business.dto;

import lombok.Data;

@Data
public class VerifyCustomerDTO {
    private String openId;
    private String wechatNumber;
    private String wechatName;
    private String realName;
    private String idcardNo;
    private String mobile;
    private String verfyCode;
    private String pictureCode;
    private String token;
    private Integer customerId;
}
