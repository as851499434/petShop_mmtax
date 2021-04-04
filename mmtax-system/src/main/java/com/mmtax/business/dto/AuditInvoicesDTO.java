package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 发票审核DTO
 *
 * @author yuanligang
 * @date 2019/8/8
 */
public class AuditInvoicesDTO {
    @ApiModelProperty("发票ID")
    private Integer invoiceId;
    @ApiModelProperty("审核状态 0-驳回 1-审核通过")
    private Integer status;
    @ApiModelProperty("发票名称")
    private String fileName;
    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "AuditInvoicesDTO{" +
                "invoiceId=" + invoiceId +
                ", status=" + status +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
