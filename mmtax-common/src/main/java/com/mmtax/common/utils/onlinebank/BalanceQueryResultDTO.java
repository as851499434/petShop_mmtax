package com.mmtax.common.utils.onlinebank;

import com.mmtax.common.utils.onlinebank.OnlineCommonResultDTO;
import lombok.Data;

@Data
public class BalanceQueryResultDTO extends OnlineCommonResultDTO {
    private String card_no;
    private String account_name;
    private String available_balance;
    private String actual_balance;
    private String freeze_amount;
}
