package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 发票全部信息 包含商户科目信息
 * @author Ljd
 * @date 2020/9/2
 */
public class UpdateInvoiceAllInfoDTO extends UpdateBaseInvoiceInfoDTO{

    @ApiModelProperty(value = "发票科目id集合")
    private List<Integer> invoiceSubjectIds;

    public List<Integer> getInvoiceSubjectIds() {
        return invoiceSubjectIds;
    }

    public void setInvoiceSubjectIds(List<Integer> invoiceSubjectIds) {
        this.invoiceSubjectIds = invoiceSubjectIds;
    }
}
