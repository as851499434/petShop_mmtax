package com.mmtax.business.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 商户表 tbl_merchant_info
 *
 * @author meimiao
 * @date 2019-11-08
 */
@ApiModel(value = "商户信息")
@Table(name = "tbl_merchant_info")
public class MerchantInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "记录ID")
    private Integer id;
    /**
     * 商户编码
     */
    @ApiModelProperty(hidden = true)
    private String merchantCode;
    /**
     * 商户名称
     */
    @ApiModelProperty(value = "商户名称", required = true)
    private String merchantName;
    /**
     * 商户经营地址
     */
    @ApiModelProperty(value = "商户经营地址", required = true)
    private String merchantAddress;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    /**
     * 盐
     */
    @ApiModelProperty(hidden = true)
    private String salt;
    /**
     * 用户状态，激活ACTIVE锁定LOCKED注销CANCEL
     */
    @ApiModelProperty(value = "用户状态，激活ACTIVE锁定LOCKED注销CANCEL")
    private String status;
    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String contacts;
    /**
     * 联系人手机号
     */
    @ApiModelProperty(value = "联系人手机号")
    private String contactsMobile;
    /**
     * 法人身份证正面
     */
    @ApiModelProperty(value = "法人身份证正面")
    private String contactsIdCardFront;
    /**
     * 法人身份证背面
     */
    @ApiModelProperty(value = "法人身份证背面")
    private String contactsIdCardBack;
    /**
     * 联系人地址
     */
    @ApiModelProperty(value = "联系人地址")
    private String contactsAddress;
    /**
     * 接收报税文件邮箱
     */
    @ApiModelProperty(value = "接收报税文件邮箱")
    private String invoiceEmail;
    /**
     * 法人名字
     */
    @ApiModelProperty(value = "法人名字")
    private String legalPersonName;
    /**
     * 法人手机号
     */
    @ApiModelProperty(value = "法人手机号")
    private String legalPersonMobile;
    /**
     * 法人身份证号
     */
    @ApiModelProperty(value = "法人身份证号")
    private String legalPersonIdCardNo;
    /**
     * 法人身份证号有效期
     */
    @ApiModelProperty(value = "法人身份证号有效期")
    private String legalPersonIdCardExp;
    /**
     * 所属行业
     */
    @ApiModelProperty(value = "所属行业")
    private String industry;
    /**
     * 邮件
     */
    @ApiModelProperty(value = "邮件")
    private String email;
    /**
     * 营业执照编码
     */
    @ApiModelProperty(value = "营业执照编码")
    private String businessLicenseCode;
    /**
     * 营业执照图片
     */
    @ApiModelProperty(value = "营业执照图片")
    private String businessLicenseImg;
    /**
     * 营业执照有效期
     */
    @ApiModelProperty(value = "营业执照有效期, 格式YYYYMMDD 长期：99991231")
    private String businessLicenseExp;
    /**
     * 1-一般纳税人2-小规模纳税人
     */
    @ApiModelProperty(value = "1-一般纳税人2-小规模纳税人")
    private Integer taxpayerType;
    /**
     * 岗位
     */
    @ApiModelProperty(value = "岗位")
    private String job;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 归属商户名称
     */
    @ApiModelProperty(value = "归属商户名称")
    private String belongCompanyName;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;
    /**
     * 合同图片
     */
    @ApiModelProperty(value = "合同图片")
    private String contractImgUrl;
    /**
     * 合同名称
     */
    @ApiModelProperty(value = "合同名称")
    private String contractName;
    /**
     * 合同编号
     */
    @ApiModelProperty(value = "合同编号")
    private String contractNo;
    /**
     * 经营场所产权证明
     */
    @ApiModelProperty(value = "经营场所产权证明")
    private String businessPlacesProof;
    /**
     * 个体工商户设立登记申请书
     */
    @ApiModelProperty(value = "个体工商户设立登记申请书")
    private String applyRegistBusiness;
    /**
     * 经营者签署委托代理书
     */
    @ApiModelProperty(value = "经营者签署委托代理书")
    private String managerOperatorLetter;
    /**
     * 个体工商户名称预先核准通知书
     */
    @ApiModelProperty(value = "个体工商户名称预先核准通知书")
    private String individualBusinessNotice;
    /**
     * 0-个体工商户1-正式商户
     */
    @ApiModelProperty(value = "0-个体工商户1-正式商户")
    private Integer type;
    /**
     * 微信授权登录openid
     */
    @ApiModelProperty(value = "微信授权登录openid")
    private String wxOpenId;
    @ApiModelProperty(value = "代征主体")
    private String subject;
    /**
     * 0-提交资料1-平台审核2-工商注册3-注册完成
     */
    @ApiModelProperty(value = "0-提交资料1-平台审核2-工商注册3-注册完成")
    private Integer auditStatus;
    /**
     * 邀请人id
     */
    @ApiModelProperty(value = "邀请人id")
    private Integer inviter;

    /**
     * 所属销售
     */
    @ApiModelProperty(value = "所属销售")
    private Integer belongSaler;
    /**
     * 代理商编号
     */
    @ApiModelProperty(value = "代理商编号")
    private String agentNumber;
    /**
     * 代理商
     */
    @ApiModelProperty(value = "代理商")
    private String agentName;
    /**
     *
     */
    @Transient
    private Integer providerId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
    }

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsIdCardFront(String contactsIdCardFront) {
        this.contactsIdCardFront = contactsIdCardFront;
    }

    public String getContactsIdCardFront() {
        return contactsIdCardFront;
    }

    public void setContactsIdCardBack(String contactsIdCardBack) {
        this.contactsIdCardBack = contactsIdCardBack;
    }

    public String getContactsIdCardBack() {
        return contactsIdCardBack;
    }

    public void setContactsAddress(String contactsAddress) {
        this.contactsAddress = contactsAddress;
    }

    public String getContactsAddress() {
        return contactsAddress;
    }

    public void setInvoiceEmail(String invoiceEmail) {
        this.invoiceEmail = invoiceEmail;
    }

    public String getInvoiceEmail() {
        return invoiceEmail;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonMobile(String legalPersonMobile) {
        this.legalPersonMobile = legalPersonMobile;
    }

    public String getLegalPersonMobile() {
        return legalPersonMobile;
    }

    public void setLegalPersonIdCardNo(String legalPersonIdCardNo) {
        this.legalPersonIdCardNo = legalPersonIdCardNo;
    }

    public String getLegalPersonIdCardNo() {
        return legalPersonIdCardNo;
    }

    public void setLegalPersonIdCardExp(String legalPersonIdCardExp) {
        this.legalPersonIdCardExp = legalPersonIdCardExp;
    }

    public String getLegalPersonIdCardExp() {
        return legalPersonIdCardExp;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getIndustry() {
        return industry;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setBusinessLicenseCode(String businessLicenseCode) {
        this.businessLicenseCode = businessLicenseCode;
    }

    public String getBusinessLicenseCode() {
        return businessLicenseCode;
    }

    public void setBusinessLicenseImg(String businessLicenseImg) {
        this.businessLicenseImg = businessLicenseImg;
    }

    public String getBusinessLicenseImg() {
        return businessLicenseImg;
    }

    public void setBusinessLicenseExp(String businessLicenseExp) {
        this.businessLicenseExp = businessLicenseExp;
    }

    public String getBusinessLicenseExp() {
        return businessLicenseExp;
    }

    public void setTaxpayerType(Integer taxpayerType) {
        this.taxpayerType = taxpayerType;
    }

    public Integer getTaxpayerType() {
        return taxpayerType;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setBelongCompanyName(String belongCompanyName) {
        this.belongCompanyName = belongCompanyName;
    }

    public String getBelongCompanyName() {
        return belongCompanyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setContractImgUrl(String contractImgUrl) {
        this.contractImgUrl = contractImgUrl;
    }

    public String getContractImgUrl() {
        return contractImgUrl;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setBusinessPlacesProof(String businessPlacesProof) {
        this.businessPlacesProof = businessPlacesProof;
    }

    public String getBusinessPlacesProof() {
        return businessPlacesProof;
    }

    public void setApplyRegistBusiness(String applyRegistBusiness) {
        this.applyRegistBusiness = applyRegistBusiness;
    }

    public String getApplyRegistBusiness() {
        return applyRegistBusiness;
    }

    public void setManagerOperatorLetter(String managerOperatorLetter) {
        this.managerOperatorLetter = managerOperatorLetter;
    }

    public String getManagerOperatorLetter() {
        return managerOperatorLetter;
    }

    public void setIndividualBusinessNotice(String individualBusinessNotice) {
        this.individualBusinessNotice = individualBusinessNotice;
    }

    public String getIndividualBusinessNotice() {
        return individualBusinessNotice;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setInviter(Integer inviter) {
        this.inviter = inviter;
    }

    public Integer getInviter() {
        return inviter;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getBelongSaler() {
        return belongSaler;
    }

    public void setBelongSaler(Integer belongSaler) {
        this.belongSaler = belongSaler;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("merchantCode", getMerchantCode())
                .append("merchantName", getMerchantName())
                .append("merchantAddress", getMerchantAddress())
                .append("password", getPassword())
                .append("salt", getSalt())
                .append("status", getStatus())
                .append("contacts", getContacts())
                .append("contactsMobile", getContactsMobile())
                .append("contactsIdCardFront", getContactsIdCardFront())
                .append("contactsIdCardBack", getContactsIdCardBack())
                .append("contactsAddress", getContactsAddress())
                .append("invoiceEmail", getInvoiceEmail())
                .append("legalPersonName", getLegalPersonName())
                .append("legalPersonMobile", getLegalPersonMobile())
                .append("legalPersonIdCardNo", getLegalPersonIdCardNo())
                .append("legalPersonIdCardExp", getLegalPersonIdCardExp())
                .append("industry", getIndustry())
                .append("email", getEmail())
                .append("businessLicenseCode", getBusinessLicenseCode())
                .append("businessLicenseImg", getBusinessLicenseImg())
                .append("businessLicenseExp", getBusinessLicenseExp())
                .append("taxpayerType", getTaxpayerType())
                .append("job", getJob())
                .append("subject", getSubject())
                .append("remark", getRemark())
                .append("belongCompanyName", getBelongCompanyName())
                .append("companyName", getCompanyName())
                .append("contractImgUrl", getContractImgUrl())
                .append("contractName", getContractName())
                .append("contractNo", getContractNo())
                .append("businessPlacesProof", getBusinessPlacesProof())
                .append("applyRegistBusiness", getApplyRegistBusiness())
                .append("managerOperatorLetter", getManagerOperatorLetter())
                .append("individualBusinessNotice", getIndividualBusinessNotice())
                .append("type", getType())
                .append("wxOpenId", getWxOpenId())
                .append("auditStatus", getAuditStatus())
                .append("inviter", getInviter())
                .append("belongSaler", getBelongSaler())
                .append("providerId", getProviderId())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
