package com.mmtax.common.utils.onlinebank;

import lombok.Data;

/**
 * @Author：YH
 * @Date：2020/11/16 12:12
 */
@Data
public class BatchPaymentDTO extends OnlineBankCommonDTO {

    private String outerTradeNo;
    private String filePath;
    private String fileName;
    private String uid;
    private String whiteChannelCode;
    private String totalCount;
    private String totalAmount;
    private String notifyUrl;
    //网商预留字段，忽略
    private String productICode;
    //网商预留字段，忽略
    private String operatord;


}
