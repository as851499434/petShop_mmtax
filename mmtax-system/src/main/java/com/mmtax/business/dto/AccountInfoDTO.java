package com.mmtax.business.dto;

import com.mmtax.common.page.Page;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountInfoDTO {
    //所有余额
    private BigDecimal allAmount;
    //可提现余额
    private BigDecimal usableAmount;
    //冻结金额
    private BigDecimal frozenAmount;
    private BigDecimal incomeAmount;
    private BigDecimal withdrawAmount;
    private Page page;
}
