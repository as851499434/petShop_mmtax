package com.mmtax.business.yunzbdto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/22 10:15
 */
public class YunZbNewInvoiceApplyDTO {

    /**
     * 发票金额版面 1: 千元版 2：万元版 3: 十万元版 4：百万元版
     */
    @ApiModelProperty("发票金额版面 1: 千元版 2：万元版 3: 十万元版 4：百万元版")
    private String invoiceAmtType;
    @ApiModelProperty("账单流水号")
    private List<String> billsId;
    @ApiModelProperty("含税总金额")
    private String totalAmount;
    @ApiModelProperty("发票内容代码")
    private String contentCode;
    @ApiModelProperty("商户id")
    private Integer merchantId;
    @ApiModelProperty("申请说明")
    private String applyRemark;
    @ApiModelProperty("发票说明")
    private String invoiceRemark;

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }

    public String getInvoiceRemark() {
        return invoiceRemark;
    }

    public void setInvoiceRemark(String invoiceRemark) {
        this.invoiceRemark = invoiceRemark;
    }

    public String getInvoiceAmtType() {
        return invoiceAmtType;
    }

    public void setInvoiceAmtType(String invoiceAmtType) {
        this.invoiceAmtType = invoiceAmtType;
    }

    public List<String> getBillsId() {
        return billsId;
    }

    public String getContentCode() {
        return contentCode;
    }

    public void setContentCode(String contentCode) {
        this.contentCode = contentCode;
    }

    public void setBillsId(List<String> billsId) {
        this.billsId = billsId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }
}
