package com.mmtax.business.tianjindto;


/**
 * @Author: WangZhaoXu
 * @Date: 2020/3/19 2:59 下午
 */
public class SalaryOrderDTO {
    /**
     *供应商 UUID
     */
    private String server_user_uuid;
    /**
     *账户 UUID
     */
    private String customer_account_uuid;
    /**
     *预计下发日期
     */
    private String to_launch_date;
    /**
     *下发个人明细
     */
    private Object task_list;
    /**
     *回调地址
     */
    private Object call_back;
    /**
     *
     */
    private String trade_mark;
    /**
     *是否同步确认订单金请求 1 是 0 否,不传默认
     * 为 0.额
     */
    private String is_to_confirm_order;

    public String getServer_user_uuid() {
        return server_user_uuid;
    }

    public void setServer_user_uuid(String server_user_uuid) {
        this.server_user_uuid = server_user_uuid;
    }

    public String getCustomer_account_uuid() {
        return customer_account_uuid;
    }

    public void setCustomer_account_uuid(String customer_account_uuid) {
        this.customer_account_uuid = customer_account_uuid;
    }

    public String getTo_launch_date() {
        return to_launch_date;
    }

    public void setTo_launch_date(String to_launch_date) {
        this.to_launch_date = to_launch_date;
    }

    public Object getTask_list() {
        return task_list;
    }

    public void setTask_list(Object task_list) {
        this.task_list = task_list;
    }

    public Object getCall_back() {
        return call_back;
    }

    public void setCall_back(Object call_back) {
        this.call_back = call_back;
    }

    public String getTrade_mark() {
        return trade_mark;
    }

    public void setTrade_mark(String trade_mark) {
        this.trade_mark = trade_mark;
    }

    public String getIs_to_confirm_order() {
        return is_to_confirm_order;
    }

    public void setIs_to_confirm_order(String is_to_confirm_order) {
        this.is_to_confirm_order = is_to_confirm_order;
    }

    @Override
    public String toString() {
        return "SalaryOrderDTO{" +
                "server_user_uuid='" + server_user_uuid + '\'' +
                ", customer_account_uuid='" + customer_account_uuid + '\'' +
                ", to_launch_date='" + to_launch_date + '\'' +
                ", task_list=" + task_list +
                ", call_back=" + call_back +
                ", trade_mark='" + trade_mark + '\'' +
                ", is_to_confirm_order='" + is_to_confirm_order + '\'' +
                '}';
    }
}
