package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工账户列表信息
 * @author Ljd
 * @date 2020/7/10
 */
@ApiModel(value = "员工账户列表信息")
public class ManagerCustomerAccountDTO {

    @Excel(name = "账户ID")
    @ApiModelProperty(value = "账户id")
    private Integer id;

    @Excel(name = "员工编号")
    @ApiModelProperty(value = "员工编号")
    private String customerNo;

    @Excel(name = "员工姓名")
    @ApiModelProperty(value = "员工姓名")
    private String realName;

    @ApiModelProperty(value = "所属商户id")
    private Integer merchantId;

    @Excel(name = "创建时间" , dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Excel(name = "所属商户名称")
    @ApiModelProperty(value = "所属商户名称")
    private String merchantName;

    @ApiModelProperty(value = "员工id")
    private Integer customerId;

    @Excel(name = "总余额")
    @ApiModelProperty(value = "总余额")
    private BigDecimal amount;

    @Excel(name = "冻结金额")
    @ApiModelProperty(value = "冻结金额")
    private BigDecimal frozenAmount;

    @Excel(name = "可用余额")
    @ApiModelProperty(value = "可用余额")
    private BigDecimal usableAmount;

    @Excel(name = "账户状态" , readConverterExp = "ACTIVE=激活,FROZED=冻结,CANCEL=注销")
    @ApiModelProperty(value = "状态 ACTIVE-激活状态，正常使用FROZED-冻结（不可提现）CANCEL-注销")
    private String status;

    @ApiModelProperty(value = "所属税源地")
    private String taxSourceompanyName;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public BigDecimal getUsableAmount() {
        return usableAmount;
    }

    public void setUsableAmount(BigDecimal usableAmount) {
        this.usableAmount = usableAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaxSourceompanyName() {
        return taxSourceompanyName;
    }

    public void setTaxSourceompanyName(String taxSourceompanyName) {
        this.taxSourceompanyName = taxSourceompanyName;
    }
}
