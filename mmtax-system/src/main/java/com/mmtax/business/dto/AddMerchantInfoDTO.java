package com.mmtax.business.dto;

import com.mmtax.business.domain.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 商户添加包装类
 *
 * @author yuanligang
 * @date 2019/7/9
 */
@ApiModel(value = "添加商户")
public class AddMerchantInfoDTO {
    /**
     * 商户信息
     */
    @ApiModelProperty(value = "商户信息")
    private MerchantInfo merchantInfo;
    /**
     * 发票信息
     */
    @ApiModelProperty(value = "发票信息")
    private InvoiceInfo invoiceInfo;
    /**
     * 发票科目信息
     */
    @ApiModelProperty(value = "发票科目信息")
    private MerchantInvoiceSubjectCorrelation merchantInvoiceSubjectCorrelation;
    /**
     * 发票科目集合
     */
    @ApiModelProperty(value = "发票科目id集合")
    private List<Integer> invoiceSubjectIdList;
    /**
     * 邮寄地址
     */
    @ApiModelProperty(value = "邮寄地址")
    private Address address;
    /**
     * 合作信息
     */
    @ApiModelProperty(value = "合作信息")
    private Cooperation cooperation;

    /**
     * 派单信息
     */
    @ApiModelProperty(value = "派单信息")
    private List<MerchantTaskInfo> merchantTaskInfoList;
    /**
     * 风控配置信息
     */
    @ApiModelProperty(value = "风控配置信息")
    private RiskManagementConfig riskManagementConfig;
    /**
     * 账户配置
     */
    @ApiModelProperty(value = "账户设置")
    private MerchantAccountConfig merchantAccountConfig;
    /**
     * 签约岗位信息集合
     */
    @ApiModelProperty(value = "签约岗位集合")
    private List<TaskInfo> taskInfos;


    @ApiModelProperty(value = "结算信息")
    private SettlementInfo settlementInfo;

    @ApiModelProperty(value = "客户支持")
    private CustomerSupport customerSupport;
    @ApiModelProperty(value = "所属税源地公司id")
    private Integer taxSourceId;
    @ApiModelProperty(value = "天津渠道所需信息")
    private TianJinPaymentInfo tianJinPaymentInfo;

    public MerchantInvoiceSubjectCorrelation getMerchantInvoiceSubjectCorrelation() {
        return merchantInvoiceSubjectCorrelation;
    }

    public void setMerchantInvoiceSubjectCorrelation(MerchantInvoiceSubjectCorrelation merchantInvoiceSubjectCorrelation) {
        this.merchantInvoiceSubjectCorrelation = merchantInvoiceSubjectCorrelation;
    }

    public List<Integer> getInvoiceSubjectIdList() {
        return invoiceSubjectIdList;
    }

    public void setInvoiceSubjectIdList(List<Integer> invoiceSubjectIdList) {
        this.invoiceSubjectIdList = invoiceSubjectIdList;
    }

    public TianJinPaymentInfo getTianJinPaymentInfo() {
        return tianJinPaymentInfo;
    }

    public void setTianJinPaymentInfo(TianJinPaymentInfo tianJinPaymentInfo) {
        this.tianJinPaymentInfo = tianJinPaymentInfo;
    }

    public List<TaskInfo> getTaskInfos() {
        return taskInfos;
    }

    public void setTaskInfos(List<TaskInfo> taskInfos) {
        this.taskInfos = taskInfos;
    }

    public Integer getTaxSourceId() {
        return taxSourceId;
    }

    public void setTaxSourceId(Integer taxSourceId) {
        this.taxSourceId = taxSourceId;
    }

    public MerchantInfo getMerchantInfo() {
        return merchantInfo;
    }

    public void setMerchantInfo(MerchantInfo merchantInfo) {
        this.merchantInfo = merchantInfo;
    }

    public InvoiceInfo getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Cooperation getCooperation() {
        return cooperation;
    }

    public void setCooperation(Cooperation cooperation) {
        this.cooperation = cooperation;
    }

    public List<MerchantTaskInfo> getMerchantTaskInfoList() {
        return merchantTaskInfoList;
    }

    public void setMerchantTaskInfoList(List<MerchantTaskInfo> merchantTaskInfoList) {
        this.merchantTaskInfoList = merchantTaskInfoList;
    }

    public RiskManagementConfig getRiskManagementConfig() {
        return riskManagementConfig;
    }

    public void setRiskManagementConfig(RiskManagementConfig riskManagementConfig) {
        this.riskManagementConfig = riskManagementConfig;
    }

    public MerchantAccountConfig getMerchantAccountConfig() {
        return merchantAccountConfig;
    }

    public void setMerchantAccountConfig(MerchantAccountConfig merchantAccountConfig) {
        this.merchantAccountConfig = merchantAccountConfig;
    }

    public SettlementInfo getSettlementInfo() {
        return settlementInfo;
    }

    public void setSettlementInfo(SettlementInfo settlementInfo) {
        this.settlementInfo = settlementInfo;
    }

    public CustomerSupport getCustomerSupport() {
        return customerSupport;
    }

    public void setCustomerSupport(CustomerSupport customerSupport) {
        this.customerSupport = customerSupport;
    }

    @Override
    public String toString() {
        return "AddMerchantInfoDTO{" +
                "merchantInfo=" + merchantInfo +
                ", invoiceInfo=" + invoiceInfo +
                ", address=" + address +
                ", cooperation=" + cooperation +
                ", riskManagementConfig=" + riskManagementConfig +
                ", merchantAccountConfig=" + merchantAccountConfig +
                ", settlementInfo=" + settlementInfo +
                ", customerSupport=" + customerSupport +
                ", taxSourceId=" + taxSourceId +
                ", tianJinPaymentInfo=" + tianJinPaymentInfo +
                '}';
    }
}
