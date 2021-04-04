package com.mmtax.common.utils.onlinebank;

import lombok.Data;

@Data
public class EnterPriseRegisterResultDTO extends OnlineCommonResultDTO {
    private String member_id;
    private String sub_account_no;
}
