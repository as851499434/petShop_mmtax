/**
 * Copyright 2019 bejson.com
 */
package com.mmtax.business.yunzbdto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Auto-generated: 2019-11-13 9:29:33
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class YunZBInvoiceContentsDTO {
    @ApiModelProperty("金额")
    private String amount;
    @ApiModelProperty("开票内容代码")
    private String content_code;
    @ApiModelProperty("开票内容名称")
    private String content_name;
    @ApiModelProperty("数量")
    private String quantity;
    @ApiModelProperty("税额")
    private String tax_amt;
    @ApiModelProperty("税率")
    private String tax_rate;
    @ApiModelProperty("含税税额")
    private String total_amt;
    @ApiModelProperty("单位")
    private String unit;
    @ApiModelProperty("单价")
    private String unit_price;
    @ApiModelProperty("规格型号")
    private String unit_type;

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setContent_code(String content_code) {
        this.content_code = content_code;
    }

    public String getContent_code() {
        return content_code;
    }

    public void setContent_name(String content_name) {
        this.content_name = content_name;
    }

    public String getContent_name() {
        return content_name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setTax_amt(String tax_amt) {
        this.tax_amt = tax_amt;
    }

    public String getTax_amt() {
        return tax_amt;
    }

    public void setTax_rate(String tax_rate) {
        this.tax_rate = tax_rate;
    }

    public String getTax_rate() {
        return tax_rate;
    }

    public void setTotal_amt(String total_amt) {
        this.total_amt = total_amt;
    }

    public String getTotal_amt() {
        return total_amt;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_type(String unit_type) {
        this.unit_type = unit_type;
    }

    public String getUnit_type() {
        return unit_type;
    }

}