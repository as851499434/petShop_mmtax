package com.mmtax.common.utils.onlinebank;

import com.mmtax.common.utils.onlinebank.OnlineBankCommonDTO;
import lombok.Data;

@Data
public class AccountBalanceDTO extends OnlineBankCommonDTO {
    private String uid;
    //账户类型，若为空，则默认查询会员所有账户
    private String accountType;
}
