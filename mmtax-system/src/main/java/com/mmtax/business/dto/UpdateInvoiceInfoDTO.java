package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 发票默认内容修改DTO
 * @author yuanligang
 * @date 2019/7/14
 */
public class UpdateInvoiceInfoDTO {
    /**
     * 发票ID
     */
    @ApiModelProperty(value = "发票ID", required = true)
    private Integer id;

    /**
     * 发票默认内容
     */
    @ApiModelProperty(value = "发票默认内容", required = true)
    private String defaultContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDefaultContent() {
        return defaultContent;
    }

    public void setDefaultContent(String defaultContent) {
        this.defaultContent = defaultContent;
    }

    @Override
    public String toString() {
        return "UpdateInvoiceInfoDTO{" +
                "id=" + id +
                ", defaultContent='" + defaultContent + '\'' +
                '}';
    }
}
