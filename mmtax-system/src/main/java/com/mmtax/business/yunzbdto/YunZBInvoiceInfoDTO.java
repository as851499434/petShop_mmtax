/**
 * Copyright 2019 bejson.com
 */
package com.mmtax.business.yunzbdto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2019-11-13 9:29:33
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class YunZBInvoiceInfoDTO {
    @ApiModelProperty("申请价税合计总额")
    private String apply_amt;
    @ApiModelProperty("申请张数")
    private String apply_num;
    @ApiModelProperty("申请时间")
    private Date apply_time;
    @ApiModelProperty("申请批次号")
    private String batch_id;
    @ApiModelProperty("账单详细")
    private List<YunZBInvoiceBillsDTO> bills;
    @ApiModelProperty("开票渠道号")
    private String channel;
    @ApiModelProperty("快递信息")
    private String express_info;
    @ApiModelProperty("发票详细")
    private List<YunZBInvoiceDetailsDTO> invoice_details;
    @ApiModelProperty("发票抬头")
    private String invoice_title;
    @ApiModelProperty("开票方")
    private String issued_by;
    @JsonIgnore
    private String mch_id;
    @JsonIgnore
    private String nonce_str;
    @ApiModelProperty("驳回原因")
    private String reject_reason;
    @ApiModelProperty("开票说明")
    private String remark;
    @JsonIgnore
    private String result_code;
    @JsonIgnore
    private String result_msg;
    @JsonIgnore
    private String return_code;
    @JsonIgnore
    private String return_msg;
    @JsonIgnore
    private String sign;
    @ApiModelProperty("申请状态 可选值： 1-审核中 2-通过 3-驳回 4-已邮寄")
    private String state;
    @JsonIgnore
    private String sub_mch_id;

    public void setApply_amt(String apply_amt) {
        this.apply_amt = apply_amt;
    }

    public String getApply_amt() {
        return apply_amt;
    }

    public void setApply_num(String apply_num) {
        this.apply_num = apply_num;
    }

    public String getApply_num() {
        return apply_num;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    public Date getApply_time() {
        return apply_time;
    }

    public void setBatch_id(String batch_id) {
        this.batch_id = batch_id;
    }

    public String getBatch_id() {
        return batch_id;
    }

    public void setBills(List<YunZBInvoiceBillsDTO> bills) {
        this.bills = bills;
    }

    public List<YunZBInvoiceBillsDTO> getBills() {
        return bills;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

    public void setExpress_info(String express_info) {
        this.express_info = express_info;
    }

    public String getExpress_info() {
        return express_info;
    }

    public List<YunZBInvoiceDetailsDTO> getInvoice_details() {
        return invoice_details;
    }

    public void setInvoice_details(List<YunZBInvoiceDetailsDTO> invoice_details) {
        this.invoice_details = invoice_details;
    }

    public void setInvoice_title(String invoice_title) {
        this.invoice_title = invoice_title;
    }

    public String getInvoice_title() {
        return invoice_title;
    }

    public void setIssued_by(String issued_by) {
        this.issued_by = issued_by;
    }

    public String getIssued_by() {
        return issued_by;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason;
    }

    public String getReject_reason() {
        return reject_reason;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_msg(String result_msg) {
        this.result_msg = result_msg;
    }

    public String getResult_msg() {
        return result_msg;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setSub_mch_id(String sub_mch_id) {
        this.sub_mch_id = sub_mch_id;
    }

    public String getSub_mch_id() {
        return sub_mch_id;
    }

}