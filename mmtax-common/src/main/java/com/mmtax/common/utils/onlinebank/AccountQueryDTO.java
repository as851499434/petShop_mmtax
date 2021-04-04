package com.mmtax.common.utils.onlinebank;

import lombok.Data;

@Data
public class AccountQueryDTO extends OnlineBankCommonDTO{
    private String uid;
    private String startTime;
    private String endTime;
    private String accountType;
    private String currentPage;
}
