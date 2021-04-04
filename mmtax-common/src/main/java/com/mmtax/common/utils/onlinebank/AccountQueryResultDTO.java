package com.mmtax.common.utils.onlinebank;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class AccountQueryResultDTO extends OnlineCommonResultDTO {
    private JSONObject page_info;
    private JSONArray account_trades;
}
