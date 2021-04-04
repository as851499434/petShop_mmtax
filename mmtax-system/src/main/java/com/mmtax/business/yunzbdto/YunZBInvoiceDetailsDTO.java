/**
 * Copyright 2019 bejson.com
 */
package com.mmtax.business.yunzbdto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Auto-generated: 2019-11-13 9:29:33
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class YunZBInvoiceDetailsDTO {
    @ApiModelProperty("开票内容")
    private List<YunZBInvoiceContentsDTO> contents;
    @ApiModelProperty("票面价税合计金额")
    private String invoice_amt;
    @ApiModelProperty("发票金额版面")
    private String invoice_amt_type;
    @ApiModelProperty("备注")
    private String remark;

    public void setContents(List<YunZBInvoiceContentsDTO> contents) {
        this.contents = contents;
    }

    public List<YunZBInvoiceContentsDTO> getContents() {
        return contents;
    }

    public void setInvoice_amt(String invoice_amt) {
        this.invoice_amt = invoice_amt;
    }

    public String getInvoice_amt() {
        return invoice_amt;
    }

    public void setInvoice_amt_type(String invoice_amt_type) {
        this.invoice_amt_type = invoice_amt_type;
    }

    public String getInvoice_amt_type() {
        return invoice_amt_type;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

}