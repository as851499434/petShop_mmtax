package com.mmtax.business.dto;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/4/7 3:28 下午
 */
public class NotifyQueryOrderInfoDTO extends CallBackDTO {
    /**
     * 查询的订单编号
     */
    private String queryOrderNo;

    public String getQueryOrderNo() {
        return queryOrderNo;
    }

    public void setQueryOrderNo(String queryOrderNo) {
        this.queryOrderNo = queryOrderNo;
    }

    @Override
    public String toString() {
        return "NotifyQueryOrderInfoDTO{" +
                "queryOrderNo='" + queryOrderNo + '\'' +
                '}';
    }
}
