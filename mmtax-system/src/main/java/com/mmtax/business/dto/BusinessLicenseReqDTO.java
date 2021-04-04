package com.mmtax.business.dto;

import lombok.Data;

@Data
public class BusinessLicenseReqDTO {
    private Integer wechatInfoId;
    private String businessLicenseName;
    /** 执照注册时间 */
    private String businessLicenseRegisterTime;
    /** 执照存贮地址 */
    private String businessLicense;
}
