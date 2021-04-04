package com.mmtax.business.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class StatistFlowRespDTO {
    private Integer taxSourceId;
    private String taxSourceName;
    private BigDecimal incomeAmount;
    private BigDecimal WithdrawAmount;
    private List<IncomeWithdrawDTO> balanceFlow;
    private List<BankcardAlipayInfoDTO> bankIncomeList;
    private List<BankcardAlipayInfoDTO> alipayIncomeList;
}
