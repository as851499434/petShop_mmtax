package com.mmtax.business.dto;

import lombok.Data;

@Data
public class BusinessInfoDTO {
    private Integer wechatInfoId;
    /**
     * 营业执照名称
     */
    private String businessLicenseName;
    /**
     * 经营场所
     */
    private String premises;
}
