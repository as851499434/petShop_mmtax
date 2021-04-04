package com.mmtax.common.enums;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/10 11:30
 */
public enum TrxOrderStatusEnum {
    /**
     * 初始化
     */
    INIT(-1),
    /**
     * 进行中
     */
    UNPAID(0),
    /**
     * 打款成功
     */
    PAID(1),
    /**
     * 转账成功，打款失败未重新打款
     */
    PAID_PENDING(2),
    /**
     * 支付调单
     */
    ORDER_SHEET(3),
    /**
     * 转账成功，打款失败已重新打款
     */
    TRANSFERS_TRXF(4),
    /**
     * 失败结束(终态)
     */
    ORDER_FAIL(9);

    public Integer code;
    TrxOrderStatusEnum(Integer code){
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
