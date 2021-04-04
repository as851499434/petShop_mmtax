package com.mmtax.common.enums;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/19 15:35
 */
public enum TrxOrderCommentEnum {
    //
    OUT_SINGLE_QUOTA("超出单笔限额"),
    OUT_SINGLE_MONTH_QUOTA("超出单人单月限额"),
    ORDER_SHEEL("属于调单的订单"),
    NO_SEND_CUSTOMER("该灵工无已完成任务，不予打款");

    public String description;

    TrxOrderCommentEnum(String description) {
        this.description = description;
    }


}
