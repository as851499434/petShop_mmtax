package com.mmtax.common.utils.onlinebank;

import com.mmtax.common.utils.onlinebank.OnlineBankCommonDTO;
import lombok.Data;

@Data
public class RefundticketDTO extends OnlineBankCommonDTO {
    //交易号
    private String requestNo;
    //原提现交易合作方对接出款渠道使用的提现订单号(outer_inst_order_no)
    private String origOuterInstOrderNo;
}
