package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 账户设置邮箱修改
 *
 * @author yuanligang
 * @date 2019/7/26
 */
public class UpdateAccountEmailDTO {
    @ApiModelProperty(value = "ID", required = true)
    private Integer id;
    /**
     * 预警及提醒类邮件发送邮箱地址
     */
    @ApiModelProperty(value = "预警及提醒类邮件发送邮箱地址", required = true)
    private List<String> balanceRemindEmail;
    /**
     * 账单类邮件发送范围
     */
    @ApiModelProperty(value = "账单类邮件发送范围", required = true)
    private List<String> rechargeRemindEmail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getBalanceRemindEmail() {
        return balanceRemindEmail;
    }

    public void setBalanceRemindEmail(List<String> balanceRemindEmail) {
        this.balanceRemindEmail = balanceRemindEmail;
    }

    public List<String> getRechargeRemindEmail() {
        return rechargeRemindEmail;
    }

    public void setRechargeRemindEmail(List<String> rechargeRemindEmail) {
        this.rechargeRemindEmail = rechargeRemindEmail;
    }

    @Override
    public String toString() {
        return "UpdateAccountEmailDTO{" +
                "id=" + id +
                ", balanceRemindEmail=" + balanceRemindEmail +
                ", rechargeRemindEmail=" + rechargeRemindEmail +
                '}';
    }
}
