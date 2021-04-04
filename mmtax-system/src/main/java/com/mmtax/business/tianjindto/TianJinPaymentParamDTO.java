package com.mmtax.business.tianjindto;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/3/26 1:43 下午
 */
public class TianJinPaymentParamDTO {

    /**
     * 供应商id
     */
    private String serverId;
    /**
     * 打款账号id
     */
    private String accountId;

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "TianJinPaymentParamDTO{" +
                "serverId='" + serverId + '\'' +
                ", accountId='" + accountId + '\'' +
                '}';
    }
}
