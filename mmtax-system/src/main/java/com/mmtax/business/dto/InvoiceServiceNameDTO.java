package com.mmtax.business.dto;

import com.mmtax.business.yunzbdto.YunZBInvoiceContentResultDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class InvoiceServiceNameDTO {

    @ApiModelProperty("服务名称列表")
    private List<YunZBInvoiceContentResultDTO> yunZBInvoiceContentResultDTOS;
    @ApiModelProperty("默认发票内容")
    private String defaultInvoiceContent;


    public List<YunZBInvoiceContentResultDTO> getYunZBInvoiceContentResultDTOS() {
        return yunZBInvoiceContentResultDTOS;
    }

    public void setYunZBInvoiceContentResultDTOS(List<YunZBInvoiceContentResultDTO> yunZBInvoiceContentResultDTOS) {
        this.yunZBInvoiceContentResultDTOS = yunZBInvoiceContentResultDTOS;
    }

    public String getDefaultInvoiceContent() {
        return defaultInvoiceContent;
    }

    public void setDefaultInvoiceContent(String defaultInvoiceContent) {
        this.defaultInvoiceContent = defaultInvoiceContent;
    }

    @Override
    public String toString() {
        return "InvoiceServiceNameDTO{" +
                "yunZBInvoiceContentResultDTOS=" + yunZBInvoiceContentResultDTOS +
                ", defaultInvoiceContent='" + defaultInvoiceContent + '\'' +
                '}';
    }
}
