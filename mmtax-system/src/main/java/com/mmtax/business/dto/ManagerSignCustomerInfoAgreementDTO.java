package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 员工签约协议列表信息
 * @author Ljd
 * @date 2020/7/10
 */
@ApiModel(value = "员工签约协议列表信息")
public class ManagerSignCustomerInfoAgreementDTO {

    @Excel(name = "签约甲方ID")
    @ApiModelProperty(value = "签约甲方ID")
    private Integer taxSourceId;

    @ApiModelProperty(value = "流程id")
    private Integer esignFlowId;

    @Excel(name = "签约甲方")
    @ApiModelProperty(value = "签约甲方")
    private String taxSourceName;

    @Excel(name = "签约员工ID")
    @ApiModelProperty(value = "签约员工ID")
    private Integer id;

    @Excel(name = "签约员工")
    @ApiModelProperty(value = "签约员工")
    private String name;

    @ApiModelProperty(value = "所属商户id")
    private Integer merchantId;

    @ApiModelProperty(value = "所属商户名称")
    private String merchantName;

    @Excel(name = "证件号码")
    @ApiModelProperty(value = "证件号码")
    private String idNumber;

    @Excel(name = "手机号")
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @Excel(name = "签约时间")
    @ApiModelProperty(value = "签约时间")
    private String signTime;

    @Excel(name = "协议状态")
    @ApiModelProperty(value = "协议状态")
    private String expireStatus;

    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Excel(name = "签约岗位")
    @ApiModelProperty(value = "签约岗位")
    private String post;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getEsignFlowId() {
        return esignFlowId;
    }

    public void setEsignFlowId(Integer esignFlowId) {
        this.esignFlowId = esignFlowId;
    }

    public Integer getTaxSourceId() {
        return taxSourceId;
    }

    public void setTaxSourceId(Integer taxSourceId) {
        this.taxSourceId = taxSourceId;
    }

    public String getTaxSourceName() {
        return taxSourceName;
    }

    public void setTaxSourceName(String taxSourceName) {
        this.taxSourceName = taxSourceName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getExpireStatus() {
        return expireStatus;
    }

    public void setExpireStatus(String expireStatus) {
        this.expireStatus = expireStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
