package com.mmtax.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/13 11:41
 */
@ApiModel(description = "商户端完税证明")
public class MerchantTaxPaymentCertificateDTO {

    @ApiModelProperty(value = "记录id")
    private int id;
    @ApiModelProperty(value = "文件名称")
    private String name;
    @ApiModelProperty(value = "下载文件名称")
    private String fileName;
    @ApiModelProperty(value = "下载时间")
    private String downloadTime;
    @ApiModelProperty(value = "状态0-未下载1-已下载2-已删除")
    private Integer status;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "所属期")
    private String createTime;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(String downloadTime) {
        this.downloadTime = downloadTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MerchantTaxPaymentCertificateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", downloadTime='" + downloadTime + '\'' +
                ", status=" + status +
                ", merchantName='" + merchantName + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
