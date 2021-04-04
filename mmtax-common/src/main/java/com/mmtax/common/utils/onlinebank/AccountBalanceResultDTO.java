package com.mmtax.common.utils.onlinebank;

import com.mmtax.common.utils.onlinebank.OnlineCommonResultDTO;
import lombok.Data;

import java.util.List;

@Data
public class AccountBalanceResultDTO extends OnlineCommonResultDTO {
    private String transit_amount;
    private List<AccountInfo> account_list;

    @Data
    public static class AccountInfo{
        private String account_id;
        private String account_type;
        private String balance;
        private String available_balance;
        private String sub_account_no;
    }
}
