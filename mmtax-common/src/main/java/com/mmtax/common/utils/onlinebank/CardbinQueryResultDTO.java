package com.mmtax.common.utils.onlinebank;

import lombok.Data;

@Data
public class CardbinQueryResultDTO extends OnlineCommonResultDTO{
    private CardBinInfo card_bin_info;

    @Data
    public static class CardBinInfo{
        private String card_bin;
        private String card_type;
        private String branch_no;
        private String bank_name;
    }
}

