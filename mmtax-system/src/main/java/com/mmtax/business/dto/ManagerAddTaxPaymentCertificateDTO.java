package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/13 16:28
 */
@ApiModel(description = "添加完税证明")
public class ManagerAddTaxPaymentCertificateDTO {

    @ApiModelProperty(value = "商户id")
    private Integer merchantId;
    @ApiModelProperty(value = "文件上传之后的名称")
    private String fileName;
    @ApiModelProperty(value = "上传文件名称")
    private String name;

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ManagerADDTaxPaymentCertificateDTO{" +
                "merchantId=" + merchantId +
                ", fileName='" + fileName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
