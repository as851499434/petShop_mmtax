package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 后台商户展示DTO
 *
 * @author yuanligang
 * @date 2019/7/19
 */
public class ManagerMerchantInfoDTO {

    /**
     * 主键
     */
    @Excel(name = "商户ID")
    @ApiModelProperty(value = "商户id")
    private Integer id;
    /**
     * 商户编码
     */
    @Excel(name = "商户商编")
    @ApiModelProperty(hidden = true)
    private String merchantCode;
    /**
     * 商户名称
     */
    @Excel(name = "商户名称")
    @ApiModelProperty(value = "商户名称", required = true)
    private String merchantName;
    /**
     * 联系人
     */
    @Excel(name = "联系人")
    @ApiModelProperty(value = "联系人", required = true)
    private String contacts;
    /**
     * 联系人手机号
     */
    @Excel(name = "联系人手机号")
    @ApiModelProperty(value = "联系人手机号", required = true)
    private String contactsMobile;

    @Excel(name = "所属销售")
    @ApiModelProperty(value = "所属销售", required = true)
    private String saleName;
    /**
     * 所属销售id
     */
    @ApiModelProperty(hidden = true)
    private String saleId;

    @Excel(name = "账户状态" , readConverterExp = "ACTIVE=激活,LOCKED=锁定,CANCEL=注销")
    @ApiModelProperty(value = "账户状态")
    private String status;
    /**
     * 邮件
     */
    @Excel(name = "邮箱")
    @ApiModelProperty(value = "邮件", required = true)
    private String email;
    /**
     * 税源地商户
     */
    @Excel(name = "税源地商户")
    private String taxSounrceCompanyName;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间" , dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(hidden = true)
    private Date createTime;

    @ApiModelProperty("代理商")
    private String agentName;
    @ApiModelProperty("代理商编号")
    private String agentNumber;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public void setAgentNumber(String agentNumber) {
        this.agentNumber = agentNumber;
    }


    public String getTaxSounrceCompanyName() {
        return taxSounrceCompanyName;
    }

    public void setTaxSounrceCompanyName(String taxSounrceCompanyName) {
        this.taxSounrceCompanyName = taxSounrceCompanyName;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    @Override
    public String toString() {
        return "ManagerMerchantInfoDTO{" +
                "id=" + id +
                ", merchantCode='" + merchantCode + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", contacts='" + contacts + '\'' +
                ", contactsMobile='" + contactsMobile + '\'' +
                ", saleName='" + saleName + '\'' +
                ", saleId='" + saleId + '\'' +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
