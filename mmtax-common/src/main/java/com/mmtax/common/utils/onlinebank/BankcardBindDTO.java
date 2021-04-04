package com.mmtax.common.utils.onlinebank;

import lombok.Data;

@Data
public class BankcardBindDTO extends OnlineBankCommonDTO {
    private String uid;
    private String bankName;
    private String bankAccountNo;
    private String accountName;
    private String cardType;
    private String cardAttribute;
    private String verifyType;
    private String certificateType;
    private String certificateNo;
    private String reservedMobile;
}
