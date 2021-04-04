package com.mmtax.business.dto;

import com.mmtax.business.domain.RechargeRecord;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 后台发票审核 包含资金流水
 *
 * @author yuanligang
 * @date 2019/8/8
 */
public class ManagerInvoiceCheckDTO {
    @ApiModelProperty("发票详情")
    private InvoiceCheckDetailDTO invoiceCheckDetailDTO;

    @ApiModelProperty("充值记录列表")
    private List<RechargeRecord> rechargeList;

    @ApiModelProperty("纳税人识别号")
    private String taxpayerIdentificationNumber;

    public InvoiceCheckDetailDTO getInvoiceCheckDetailDTO() {
        return invoiceCheckDetailDTO;
    }

    public void setInvoiceCheckDetailDTO(InvoiceCheckDetailDTO invoiceCheckDetailDTO) {
        this.invoiceCheckDetailDTO = invoiceCheckDetailDTO;
    }

    public List<RechargeRecord> getRechargeList() {
        return rechargeList;
    }

    public void setRechargeList(List<RechargeRecord> rechargeList) {
        this.rechargeList = rechargeList;
    }

    public String getTaxpayerIdentificationNumber() {
        return taxpayerIdentificationNumber;
    }

    public void setTaxpayerIdentificationNumber(String taxpayerIdentificationNumber) {
        this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
    }

    @Override
    public String toString() {
        return "ManagerInvoiceCheckDTO{" +
                "invoiceCheckDetailDTO=" + invoiceCheckDetailDTO +
                ", rechargeList=" + rechargeList +
                ", taxpayerIdentificationNumber='" + taxpayerIdentificationNumber + '\'' +
                '}';
    }
}
