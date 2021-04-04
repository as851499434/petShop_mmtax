package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 员工银行卡列表信息
 * @author Ljd
 * @date 2020/7/10
 */
@ApiModel(value = "员工银行卡列表信息")
public class ManagerCustomerBankCardDTO {

    @Excel(name = "数据ID")
    @ApiModelProperty(value = "数据id")
    private Integer id;

    @Excel(name = "员工编号")
    @ApiModelProperty(value = "员工编号")
    private String customerNo;

    @Excel(name = "员工姓名")
    @ApiModelProperty(value = "员工姓名")
    private String realName;

    @ApiModelProperty(value = "所属商户id")
    private Integer merchantId;

    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Excel(name = "所属商户名称")
    @ApiModelProperty(value = "所属商户名称")
    private String merchantName;

    @ApiModelProperty(value = "员工id")
    private Integer customerId;

    @Excel(name = "银行卡号")
    @ApiModelProperty(value = "银行卡号")
    private String bankAccountNo;

    @Excel(name = "预留手机号")
    @ApiModelProperty(value = "预留手机号")
    private String reservedMobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getReservedMobile() {
        return reservedMobile;
    }

    public void setReservedMobile(String reservedMobile) {
        this.reservedMobile = reservedMobile;
    }
}
