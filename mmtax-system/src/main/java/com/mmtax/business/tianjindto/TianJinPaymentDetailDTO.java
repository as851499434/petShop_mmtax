package com.mmtax.business.tianjindto;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/3/19 3:29 下午
 */
public class TianJinPaymentDetailDTO {
    /**
     * 身份证号
     */
    private String social_no;
    /**
     * 姓名
     */
    private String name;
    /**
     * 默认值为"身份证"
     */
    private String identity_type;
    /**
     * 账户号
     */
    private String card_no;
    /**
     * 银行简称
     */
    private String bank_code;
    /**
     * 开
     * 户行,（非必填，若支付宝
     * 账户则必填：“支付宝”）
     */
    private String bank_branch_name;
    /**
     * 手机号
     */
    private String mobile_no;
    /**
     * 打款金额
     */
    private String amount;

    public String getSocial_no() {
        return social_no;
    }

    public void setSocial_no(String social_no) {
        this.social_no = social_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity_type() {
        return identity_type;
    }

    public void setIdentity_type(String identity_type) {
        this.identity_type = identity_type;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getBank_branch_name() {
        return bank_branch_name;
    }

    public void setBank_branch_name(String bank_branch_name) {
        this.bank_branch_name = bank_branch_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TianJinPaymentDetailDTO{" +
                "social_no='" + social_no + '\'' +
                ", name='" + name + '\'' +
                ", identity_type='" + identity_type + '\'' +
                ", card_no='" + card_no + '\'' +
                ", bank_code='" + bank_code + '\'' +
                ", bank_branch_name='" + bank_branch_name + '\'' +
                ", mobile_no='" + mobile_no + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
