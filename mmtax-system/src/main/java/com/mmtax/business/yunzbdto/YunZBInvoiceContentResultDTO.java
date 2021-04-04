package com.mmtax.business.yunzbdto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/12 14:47
 */
public class YunZBInvoiceContentResultDTO {

    /**
     * 发票内容代码
     */
    @ApiModelProperty("发票内容代码")
    private String content_code;
    /**
     * 货物或应税劳务、服务名称
     */
    @ApiModelProperty("货物或应税劳务、服务名称")
    private String content_name;

    @ApiModelProperty("是否默认发票内容 0-否 1-是")
    private Integer isDefault;

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getContent_code() {
        return content_code;
    }

    public void setContent_code(String content_code) {
        this.content_code = content_code;
    }

    public String getContent_name() {
        return content_name;
    }

    public void setContent_name(String content_name) {
        this.content_name = content_name;
    }

    @Override
    public String toString() {
        return "YunZBInvoiceContentResultDTO{" +
                "content_code='" + content_code + '\'' +
                ", content_name='" + content_name + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}
