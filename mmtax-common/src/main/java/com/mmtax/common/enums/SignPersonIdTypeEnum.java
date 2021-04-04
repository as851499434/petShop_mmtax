package com.mmtax.common.enums;

public enum SignPersonIdTypeEnum {
    //
    CHIDCARD("CRED_PSN_CH_IDCARD","大陆身份证"),
    //
    CHTWCARD("CRED_PSN_CH_TWCARD","台湾来往大陆通行证"),
    //
    CHMACAO("CRED_PSN_CH_MACAO","澳门来往大陆通行证"),
    //
    CHHONGKONG("CRED_PSN_CH_HONGKONG","香港来往大陆通行证"),
    //
    FOREIGN("CRED_PSN_FOREIGN","外籍证件"),
    //
    PASSPORT("CRED_PSN_PASSPORT","护照"),
    //
    CHSOLDIERIDCARD("CRED_PSN_CH_SOLDIER_IDCARD","军官证"),
    //
    CHSSCARD("CRED_PSN_CH_SSCARD","社会保障卡"),
    //
    CHARMEDPOLICEIDCARD("CRED_PSN_CH_ARMED_POLICE_IDCARD","武装警察身份证件"),
    //
    CHRESIDENCEBOOKLET("CRED_PSN_CH_RESIDENCE_BOOKLET","户口簿"),
    //
    CHTEMPORARYIDCARD("CRED_PSN_CH_TEMPORARY_IDCARD","临时居民身份证"),
    //
    CHGREENCARD("CRED_PSN_CH_GREEN_CARD","外国人永久居留证"),
    //
    SHAREHOLDERCODE("CRED_PSN_SHAREHOLDER_CODE","股东代码证"),
    //
    POLICEIDCARD("CRED_PSN_POLICE_ID_CARD","警官证"),
    //
    UNKNOWN("CRED_PSN_UNKNOWN","未知类型");

    private String code;
    private String description;

    SignPersonIdTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
