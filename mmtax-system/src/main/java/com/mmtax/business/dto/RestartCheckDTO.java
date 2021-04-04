package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 后台重开发票审核DTO
 *
 * @author yuanligang
 * @date 2019/8/8
 */
public class RestartCheckDTO {

    @ApiModelProperty("发票审核详情DTO")
    private InvoiceCheckDetailDTO invoiceDetailDTO;

    @ApiModelProperty("认证状态")
    private Integer status;

    @ApiModelProperty("红冲信息表下载URL")
    private String redInvoiceInfo;

    @ApiModelProperty("盖章版退票说明下载URL")
    private String sealExplain;



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRedInvoiceInfo() {
        return redInvoiceInfo;
    }

    public void setRedInvoiceInfo(String redInvoiceInfo) {
        this.redInvoiceInfo = redInvoiceInfo;
    }

    public String getSealExplain() {
        return sealExplain;
    }

    public void setSealExplain(String sealExplain) {
        this.sealExplain = sealExplain;
    }

    public InvoiceCheckDetailDTO getInvoiceDetailDTO() {
        return invoiceDetailDTO;
    }

    public void setInvoiceDetailDTO(InvoiceCheckDetailDTO invoiceDetailDTO) {
        this.invoiceDetailDTO = invoiceDetailDTO;
    }

    @Override
    public String toString() {
        return "RestartCheckDTO{" +
                "invoiceDetailDTO=" + invoiceDetailDTO +
                ", status=" + status +
                ", redInvoiceInfo='" + redInvoiceInfo + '\'' +
                ", sealExplain='" + sealExplain + '\'' +
                '}';
    }
}
