package com.mmtax.business.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mmtax.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/13 16:00
 */
@ApiModel(description = "后台完税证明列表")
public class ManagerTaxPaymentCertificateDTO extends BaseEntity {

    @ApiModelProperty(value = "记录id")
    private Integer id;
    @ApiModelProperty(value = "商户名称")
    private String merchantName;
    @ApiModelProperty(value = "商户编码")
    private String merchantCode;
    @ApiModelProperty(value = "联系人")
    private String contacts;
    @ApiModelProperty(value = "联系人手机号")
    private String contactsMobile;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "下载文件名称")
    private String fileName;
    @ApiModelProperty(value = "文件名称")
    private String name;
    @ApiModelProperty(value = "0-未下载1-已下载2-已删除")
    private String status;
    @JsonIgnore
    private String startDate;
    @JsonIgnore
    private String endDate;
    @ApiModelProperty("销售名字")
    private String saleName;
    /**
     * 所属销售id
     */
    @ApiModelProperty(hidden = true)
    private String saleId;

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ManagerTaxPaymentCertificateDTO{" +
                "id=" + id +
                ", merchantName='" + merchantName + '\'' +
                ", merchantCode='" + merchantCode + '\'' +
                ", contacts='" + contacts + '\'' +
                ", contactsMobile='" + contactsMobile + '\'' +
                ", createTime='" + createTime + '\'' +
                ", fileName='" + fileName + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
