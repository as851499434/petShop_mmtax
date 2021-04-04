package com.mmtax.common.utils.onlinebank;

import lombok.Data;

@Data
public class InfoQueryResultDTO extends OnlineCommonResultDTO {
    private String outer_trade_no;
    private String inner_trade_no;
    private String outer_inst_order_no;
    private String trade_amount;
    private String trade_status;
    private String trade_time;
}
