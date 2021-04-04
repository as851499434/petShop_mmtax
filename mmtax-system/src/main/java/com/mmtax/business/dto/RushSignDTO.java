package com.mmtax.business.dto;

/**
 * 催签参数封装
 * @author Ljd
 * @date 2020/8/4
 */
public class RushSignDTO {

    /**
     * 流程id
     */
    private String flowId;
    /**
     * 催签人账户id
     */
    private String accountId;
    /**
     * 通知方式，逗号分割，1-短信，2-邮件 3-支付宝 4-钉钉，默认按照走流程设置
     */
    private String noticeTypes;
    /**
     * 被催签人账号id，为空表示：催签当前轮到签署但还未签署的所有签署人
     */
    private String rushSignAccountId;

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getNoticeTypes() {
        return noticeTypes;
    }

    public void setNoticeTypes(String noticeTypes) {
        this.noticeTypes = noticeTypes;
    }

    public String getRushSignAccountId() {
        return rushSignAccountId;
    }

    public void setRushSignAccountId(String rushSignAccountId) {
        this.rushSignAccountId = rushSignAccountId;
    }
}
