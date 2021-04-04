package com.mmtax.business.dto;

import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/22 20:19
 */
public class ManagerSettleMentInfoDTO extends BaseEntity {
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("商户名称")
    private String merchantName;
    /**
     * 开户名称
     */
    @ApiModelProperty("开户名称")
    private String accountName;
    /**
     * 对公账户
     */
    @ApiModelProperty("对公账户")
    private String accountNo;
    /**
     * 开户银行名称
     */
    @ApiModelProperty("开户银行")
    private String bankName;
    /**
     * 银行代码
     */
    @ApiModelProperty("银行代码")
    private String bankCode;
    /**
     * 提现银行卡开户行行号
     */
    @ApiModelProperty("提现银行卡开户行行号")
    private String settBankcardNo;
    @ApiModelProperty("销售名字")
    private String saleName;
    /**
     * 所属销售id
     */
    @ApiModelProperty(hidden = true)
    private String saleId;

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getSettBankcardNo() {
        return settBankcardNo;
    }

    public void setSettBankcardNo(String settBankcardNo) {
        this.settBankcardNo = settBankcardNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
