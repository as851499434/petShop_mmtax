package com.mmtax.common.enums;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/29 15:59
 */
public enum MerchantAccountDetailTypeEnum {
    /**
     * 代付
     */
    PAID(0),
    /**
     * 充值
     */
    RECHARGE(1),
    /**
     * 收手续费
     */
    HANDLEFEE(2),
    /**
     * 退款
     */
    REFUND(3),
    /**
     *转出
     */
    TRANSFER(4);


    public Integer code;

    MerchantAccountDetailTypeEnum(Integer code) {
        this.code = code;
    }
}
