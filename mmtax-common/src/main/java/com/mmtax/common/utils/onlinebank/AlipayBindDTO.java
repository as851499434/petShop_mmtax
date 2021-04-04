package com.mmtax.common.utils.onlinebank;

import lombok.Data;

@Data
public class AlipayBindDTO extends OnlineBankCommonDTO {
    private String uid;
    private String accountNo;
    private String accountName;
    private String certificateNo;
    private String mobileNo;
    private String isVerify;
}
