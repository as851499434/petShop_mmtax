package com.mmtax.common.utils.onlinebank;

import com.mmtax.common.utils.onlinebank.OnlineBankCommonDTO;
import lombok.Data;

@Data
public class WithdrawToCardDTO extends OnlineBankCommonDTO {
    /**
     * 交易号
     */
    private String outerTradeNo;
    /**
     * 合作方业务平台用户ID
     */
    private String uid;
    /**
     * 外部机构订单号,若出款渠道是网商银行，则此处填写与outer_trade_no保持一致
     */
    private String outerInstOrderNo;
    /**
     * 平台专属出款渠道编码(联调环境默认填写MYBANK00097)
     */
    private String whiteChannelCode;
    /**
     * 账户类型，暂只支持BASIC
     */
    private String accountType;
    /**
     * 银行卡号
     */
    private String bankAccountNo;
    /**
     * 银行卡户名
     */
    private String accountName;
    /**
     * 卡类型:DC借记 CC贷记（信用卡）
     */
    private String cardType;
    /**
     * 卡属性:C对私 B对公
     */
    private String cardAttribute;
    /**
     * 金额
     */
    private String amount;
    /**
     * 银行名称,提现到银行卡不可空
     */
    private String bankName;
    /**
     * 银行code,提现到银行卡时可空
     */
    private String bankCode;
    /**
     * 银行分支行号 对公不可空，对私可空
     */
    private String bankLineNo;
    /**
     * 支行名称 对公不可空，对私可空
     */
    private String bankBranch;
    /**
     * 手续费信息
     */
    private String feeInfo;
}
