package com.mmtax.common.utils.onlinebank;

import lombok.Data;

@Data
public class BankQueryDTO extends OnlineBankCommonDTO {
    private String parentBranchNo;
    private String areaCode;
    private String keyWords;
}
