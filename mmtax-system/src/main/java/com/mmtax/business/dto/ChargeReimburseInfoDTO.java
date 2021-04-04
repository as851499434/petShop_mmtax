package com.mmtax.business.dto;

import lombok.Data;

/**
 * @Author：YH
 * @Date：2020/10/30 13:54
 */
@Data
public class ChargeReimburseInfoDTO {

    private String merchantName;
    private String userName;
    private String payeeName;
    private String amount;
    private String actualAmount;
    private String charge;
    private String reimburseCharge;
    private String status;
    private String orderSerialNum;
    private String merchantSerialNum;
    private String createTime;

}
