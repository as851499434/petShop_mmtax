package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/9/18 11:07
 */
public class UpLoadInvoiceImgDTO {
    @ApiModelProperty("发票详情ID")
    private Integer invoiceDetailId;
    @ApiModelProperty("图片链接")
    private String imgUrl;

    public Integer getInvoiceDetailId() {
        return invoiceDetailId;
    }

    public void setInvoiceDetailId(Integer invoiceDetailId) {
        this.invoiceDetailId = invoiceDetailId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
