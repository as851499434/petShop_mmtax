package com.mmtax.business.dto;

import lombok.Data;

@Data
public class CustomerInfoDTO {
    private Integer id;
    /** 员工编号 */
    private String customerNo;
    /** 真名 */
    private String realName;
    /** 证件类型,取数据字典 */
    private String certificateType;
    /** 证件号码 */
    private String certificateNo;
    /** 手机号 */
    private String mobile;
    /** 所属商户id */
    private Integer merchantId;
    /** 所属商户名称 */
    private String merchantName;
    /** 员工头像(base64码) */
    private String customerAvatar;
    /** 所属税源地id */
    private Integer taxSourceId;
    /** 所属税源地名称 */
    private String taxSourceName;
}
