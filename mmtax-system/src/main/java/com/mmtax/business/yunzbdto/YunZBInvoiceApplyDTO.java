package com.mmtax.business.yunzbdto;

import com.mmtax.common.utils.yunzbutil.BaseRequest;

import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/12 9:08
 */
public class YunZBInvoiceApplyDTO extends BaseRequest {
    /**
     * 申请说明
     */
    private String applyRemark;
    /**
     * 账单流水号，不参与签名
     */
    private List<String> billIds;
    /**
     * 渠道号
     */
    private String channelNo;
    /**
     * 发票内容代码
     */
    private String contentCode;
    /**
     * 发票金额版面 1: 千元版 2：万元版 3: 十万元版 4：百万元版
     */
    private String invoiceAmtType;
    /**
     * 发票说明
     */
    private String invoiceRemark;
    /**
     * 子商户号
     */
    private String subMchId;
    /**
     * 含税总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
     */
    private String totalAmt;

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }

    public List<String> getBillIds() {
        return billIds;
    }

    public void setBillIds(List<String> billIds) {
        this.billIds = billIds;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getContentCode() {
        return contentCode;
    }

    public void setContentCode(String contentCode) {
        this.contentCode = contentCode;
    }

    public String getInvoiceAmtType() {
        return invoiceAmtType;
    }

    public void setInvoiceAmtType(String invoiceAmtType) {
        this.invoiceAmtType = invoiceAmtType;
    }

    public String getInvoiceRemark() {
        return invoiceRemark;
    }

    public void setInvoiceRemark(String invoiceRemark) {
        this.invoiceRemark = invoiceRemark;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    public String getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
    }

    @Override
    public String toString() {
        return "YunZBInvoiceApplyDTO{" +
                "applyRemark='" + applyRemark + '\'' +
                ", billIds=" + billIds +
                ", channelNo='" + channelNo + '\'' +
                ", contentCode='" + contentCode + '\'' +
                ", invoiceAmtType='" + invoiceAmtType + '\'' +
                ", invoiceRemark='" + invoiceRemark + '\'' +
                ", subMchId='" + subMchId + '\'' +
                ", totalAmt='" + totalAmt + '\'' +
                '}';
    }
}
