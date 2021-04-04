package com.mmtax.business.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaxWithdrawAccountDTO {
    private Integer id;
    /** 对应merchantId */
    private Integer chargeMerchantId;
    /** 公司账户名称 */
    private String companyName;
    /** 税源地公司id */
    private Integer taxSourceCompanyId;
    /** 税源地名称 */
    private String taxSourceName;
    /** 税号 */
    private String taxIdNumber;
    /** 地址 */
    private String companyAddress;
    /** 电话 */
    private String companyTel;
    /** 对公银行名称 */
    private String bankPublic;
    /** 对公开户行 */
    private String bankNamePublic;
    /** 对公银行户名 */
    private String bankAccountPublic;
    /** 对公银行账号 */
    private String bankCardPublic;
    /** 对私银行名称 */
    private String bankPrivate;
    /** 对私开户行 */
    private String bankNamePrivate;
    /** 对私银行户名 */
    private String bankAccountPrivate;
    /** 对私银行账号 */
    private String bankCardPrivate;
    /**对私银行卡户主手机号*/
    private String mobilePrivate;
    /** 对私身份证号 */
    private String idCardPrivate;
    /** 是否允许提现 0否 1是 */
    private Integer allowWithdraw;
    /** 对公或对私 0对公 1对私 */
    private Integer publicOrPrivate;
    /** 预留字段一 */
    private String reservedFieldOne;
    /**  */
    private String createTime;
    private BigDecimal money;
    private String startDate;
    private String endDate;
}
