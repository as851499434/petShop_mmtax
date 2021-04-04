package com.mmtax.common.utils.onlinebank;

import lombok.Data;

@Data
public class PersonalRegisterDTO extends OnlineBankCommonDTO {
    private String uid;
    private String mobile;
    private String realName;
    private String memberName;
    private String certificateType;
    private String certificateNo;
    private String isVerify;
}
