package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 订单审核DTO
 * @author yuanligang
 * @date 2019/7/18
 */
public class CheckInvoiceDTO {
    /**
     * 订单审核状态 POSTED-已寄出REFUSE-已拒绝
     */
    @ApiModelProperty(value = "订单审核状态 POSTED-已寄出REFUSE-已拒绝", required = true)
    private String invoiceStatus;
    /**
     * 开票ID
     */
    @ApiModelProperty(value = "开票ID", required = true)
    private Integer id;

    @ApiModelProperty("备注")
    private String remarks;



    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "CheckInvoiceDTO{" +
                "invoiceStatus='" + invoiceStatus + '\'' +
                ", id=" + id +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
