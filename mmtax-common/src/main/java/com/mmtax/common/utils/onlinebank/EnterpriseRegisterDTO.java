package com.mmtax.common.utils.onlinebank;

import lombok.Data;

@Data
public class EnterpriseRegisterDTO extends OnlineBankCommonDTO {
    private String uid;
    private String enterpriseName;
    private String unifiedSocialCreditCode;
}
