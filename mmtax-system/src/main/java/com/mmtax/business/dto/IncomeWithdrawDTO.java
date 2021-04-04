package com.mmtax.business.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncomeWithdrawDTO {
    private String merchantname;
    private String type;
    //收入或提现的时间
    private String incomeWithdrawTime;
    //收入金额
    private BigDecimal incomeAmount;
    //提现金额
    private BigDecimal withdrawAmount;
    //灵工余额
    private BigDecimal balance;
    private Integer status;
}
