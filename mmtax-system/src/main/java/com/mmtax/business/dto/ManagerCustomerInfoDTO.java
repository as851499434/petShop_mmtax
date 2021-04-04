package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 员工列表信息
 * @author Ljd
 * @date 2020/7/10
 */
@ApiModel(value = "员工列表信息")
public class ManagerCustomerInfoDTO {

    @Excel(name = "用户ID")
    @ApiModelProperty(value = "用户id")
    private Integer id;

    @Excel(name = "员工编号")
    @ApiModelProperty(value = "员工编号")
    private String customerNo;

    @ApiModelProperty(value = "昵称")
    private String memberName;

    @Excel(name = "员工姓名")
    @ApiModelProperty(value = "员工姓名")
    private String realName;

    @ApiModelProperty(value = "证件类型,取数据字典")
    private String certificateType;

    @Excel(name = "证件号码")
    @ApiModelProperty(value = "证件号码")
    private String certificateNo;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "所属商户id")
    private Integer merchantId;

    @ApiModelProperty(value = "预警字段")
    private Integer customerWarn;


    @Excel(name = "创建时间", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @Excel(name = "所属商户名称")
    @ApiModelProperty(value = "所属商户名称")
    private String merchantName;

    @Excel(name = "银行卡绑定状态", readConverterExp = "Y=绑定,N=未绑定")
    @ApiModelProperty(value = "银行卡绑定状态 Y-绑定，N-未绑定")
    private String bindStatusBank;

    @Excel(name = "支付宝绑定状态", readConverterExp = "Y=绑定,N=未绑定")
    @ApiModelProperty(value = "支付宝绑定状态 Y-绑定，N-未绑定")
    private String bindStatusAlipay;

    @Excel(name = "微信号")
    @ApiModelProperty(value = "微信号")
    private String wechatNumber;


    @Excel(name = "微信昵称")
    @ApiModelProperty(value = "微信昵称")
    private String wechatName;

    @ApiModelProperty(value = "所属税源地")
    private String taxSourceompanyName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getBindStatusBank() {
        return bindStatusBank;
    }

    public void setBindStatusBank(String bindStatusBank) {
        this.bindStatusBank = bindStatusBank;
    }

    public String getBindStatusAlipay() {
        return bindStatusAlipay;
    }

    public void setBindStatusAlipay(String bindStatusAlipay) {
        this.bindStatusAlipay = bindStatusAlipay;
    }

    public String getWechatNumber() {
        return wechatNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public String getTaxSourceompanyName() {
        return taxSourceompanyName;
    }

    public void setTaxSourceompanyName(String taxSourceompanyName) {
        this.taxSourceompanyName = taxSourceompanyName;
    }

    @Override
    public String toString() {
        return "ManagerCustomerInfoDTO{" +
                "id=" + id +
                ", customerNo='" + customerNo + '\'' +
                ", memberName='" + memberName + '\'' +
                ", realName='" + realName + '\'' +
                ", certificateType='" + certificateType + '\'' +
                ", certificateNo='" + certificateNo + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", merchantId=" + merchantId +
                ", taxSourceompanyName=" + taxSourceompanyName +
                ", createTime=" + createTime +
                ", merchantName='" + merchantName + '\'' +
                ", bindStatusBank='" + bindStatusBank + '\'' +
                ", bindStatusAlipay='" + bindStatusAlipay + '\'' +
                ", wechatNumber='" + wechatNumber + '\'' +
                ", wechatName='" + wechatName + '\'' +
                '}';
    }

    public Integer getCustomerWarn() {
        return customerWarn;
    }

    public void setCustomerWarn(Integer customerWarn) {
        this.customerWarn = customerWarn;
    }
}
