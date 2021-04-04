package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

public class InvoiceSubjectWithTaxCompanyCorrelationDTO {
    @ApiModelProperty(value = "科目内容")
    private String content;

    @ApiModelProperty(value = "科目id")
    private Integer invoiceSubjectId;

    @ApiModelProperty(value = "是否默认")
    private Integer isDefault;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getInvoiceSubjectId() {
        return invoiceSubjectId;
    }

    public void setInvoiceSubjectId(Integer invoiceSubjectId) {
        this.invoiceSubjectId = invoiceSubjectId;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InvoiceSubjectWithTaxCompanyCorrelationDTO that = (InvoiceSubjectWithTaxCompanyCorrelationDTO) o;
        return Objects.equals(content, that.content) &&
                Objects.equals(invoiceSubjectId, that.invoiceSubjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, invoiceSubjectId);
    }

    @Override
    public String toString() {
        return "InvoiceSubjectWithTaxCompanyCorrelationDTO{" +
                "content='" + content + '\'' +
                ", invoiceSubjectId=" + invoiceSubjectId +
                ", isDefault=" + isDefault +
                '}';
    }
}
