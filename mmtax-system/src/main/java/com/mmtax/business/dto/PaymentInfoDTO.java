package com.mmtax.business.dto;

import com.mmtax.business.domain.MakeUpCharge;
import com.mmtax.business.domain.MakeUpChargeDetail;
import com.mmtax.business.domain.PayRequestData;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.common.annotation.Excel;
import com.mmtax.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/9 19:43
 * 该类的任何字段添加都需同步到produce项目的PaymentDTO类
 */
@ApiModel(value = "代付信息")
public class PaymentInfoDTO extends CheckPasswordDTO {

    /** 对应请求数据的id */
    private Integer payRequestDataId;
    @Excel(name = "商户订单号(非必填)")
    @ApiModelProperty(value = "商户订单号(非必填)")
    private String merchantTrxOrderNo;
    @Excel(name = "收款账号(个人银行卡号)")
    @ApiModelProperty(value = "收款账号(个人银行卡号)")
    private String bankNo;
    @Excel(name = "收款户名(真实姓名,必填)")
    @ApiModelProperty(value = "收款户名(真实姓名,必填)")
    private String name;
    @Excel(name = "身份证号(必填)")
    @ApiModelProperty(value = "身份证号(必填)")
    private String idCardNo;
    @Excel(name = "打款金额/元(四舍五入至分,必填)")
    @ApiModelProperty(value = "打款金额/元(四舍五入至分,必填)")
    private String settleAmount;
    @Excel(name = "银行卡预留手机号（必填）")
    @ApiModelProperty(value = "银行卡预留手机号（必填）")
    private String mobile;
    @ApiModelProperty(value = "手续费")
    private String tax;
    @Excel(name = "打款备注(20个字符以内,非必填)")
    @ApiModelProperty(value = "打款备注(20个字符以内,非必填)")
    private String remark;
    @Excel(name = "失败原因")
    @ApiModelProperty(value = "失败原因")
    private String comment;
    @ApiModelProperty(value = "打款补交总额记录")
    private MakeUpCharge makeUpCharge;
    @ApiModelProperty(value = "打款补交详细记录")
    private String makeUpChargeDetails;
    @ApiModelProperty(value = "是否大额")
    private Boolean useBigRate;
    @ApiModelProperty(value = "补偿金额")
    private String overChargeAmount;

    @ApiModelProperty(value = "推广->省")
    private String province;
    @ApiModelProperty(value = "推广->市")
    private String city;
    @ApiModelProperty(value = "推广->区")
    private String area;
    @ApiModelProperty(value = "推广->街道")
    private String street;
    @ApiModelProperty(value = "推广->社区")
    private String community;
    @ApiModelProperty(value = "推广->单价")
    private BigDecimal unitPrice;
    @ApiModelProperty(value = "推广->数量")
    private Integer quantity;
    @ApiModelProperty(value = "推广->推广开始时间")
    private String promotionStartTime;
    @ApiModelProperty(value = "推广->推广结束时间")
    private String promotionEndTime;
    @ApiModelProperty(value = "派单批次号")
    private String taskRecordBatchNo;

    /**
     * 备注信息
     */
    private String memo;

    public static class Builder {
        private Integer payRequestDataId;
        private String merchantTrxOrderNo;
        private String bankNo;
        private String name;
        private String idCardNo;
        private String settleAmount;
        private String mobile;
        private String paymentChannel;
        private String tax = null;
        private String remark = null;
        private String comment = null;
        private MakeUpCharge makeUpCharge = null;
        private String makeUpChargeDetails = null;
        private Boolean useBigRate = false;
        private Integer merchantId;
        private String taskRecordBatchNo;

        public Builder(PayRequestData payRequestData){
            payRequestDataId = payRequestData.getId();
            merchantTrxOrderNo = payRequestData.getMerchantSerialNum();
            bankNo = payRequestData.getBankCard();
            name = payRequestData.getName();
            idCardNo = payRequestData.getIdCardNo();
            settleAmount = payRequestData.getAmount().toString();
            mobile = payRequestData.getMobile();
            if(null != payRequestData.getCharge()) {
                tax = payRequestData.getCharge().toString();
            }
            if(!StringUtils.isEmpty(payRequestData.getRemark())){
                remark = payRequestData.getRemark();
            }
            merchantId = payRequestData.getMerchantId();
            paymentChannel = payRequestData.getPaymentChannel();
            taskRecordBatchNo = payRequestData.getTaskRecordBatchNo();
        }

        public Builder comment(String val){
            comment = val;
            return this;
        }

        public Builder makeUpCharge(MakeUpCharge val){
            makeUpCharge = val;
            return this;
        }

        public Builder makeUpChargeDetails(String val){
            makeUpChargeDetails = val;
            return this;
        }

        public Builder useBigRate(Boolean val){
            useBigRate = val;
            return this;
        }

        public PaymentInfoDTO build(){
            return new PaymentInfoDTO(this);
        }
    }

    public PaymentInfoDTO() {
    }

    private PaymentInfoDTO(Builder builder){
        payRequestDataId = builder.payRequestDataId;
        merchantTrxOrderNo = builder.merchantTrxOrderNo;
        bankNo = builder.bankNo;
        name = builder.name;
        idCardNo = builder.idCardNo;
        settleAmount = builder.settleAmount;
        mobile = builder.mobile;
        tax = builder.tax;
        remark = builder.remark;
        comment = builder.comment;
        makeUpCharge = builder.makeUpCharge;
        makeUpChargeDetails = builder.makeUpChargeDetails;
        useBigRate = builder.useBigRate;
        setMerchantId(builder.merchantId);
        setPaymentChannel(builder.paymentChannel);
        taskRecordBatchNo = builder.taskRecordBatchNo;
    }

    public String getOverChargeAmount() {
        return overChargeAmount;
    }

    public void setOverChargeAmount(String overChargeAmount) {
        this.overChargeAmount = overChargeAmount;
    }

    public Integer getPayRequestDataId() {
        return payRequestDataId;
    }

    public void setPayRequestDataId(Integer payRequestDataId) {
        this.payRequestDataId = payRequestDataId;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public Boolean getUseBigRate() {
        return useBigRate;
    }

    public void setUseBigRate(Boolean useBigRate) {
        this.useBigRate = useBigRate;
    }

    public MakeUpCharge getMakeUpCharge() {
        return makeUpCharge;
    }

    public void setMakeUpCharge(MakeUpCharge makeUpCharge) {
        this.makeUpCharge = makeUpCharge;
    }

    public String getMakeUpChargeDetails() {
        return makeUpChargeDetails;
    }

    public void setMakeUpChargeDetails(String makeUpChargeDetails) {
        this.makeUpChargeDetails = makeUpChargeDetails;
    }

    public void setMakeUpChargeDetails(String[] makeUpChargeDetails) {
        this.makeUpChargeDetails = Arrays.toString(makeUpChargeDetails);
    }

    public String getMerchantTrxOrderNo() {
        return merchantTrxOrderNo;
    }

    public void setMerchantTrxOrderNo(String merchantTrxOrderNo) {
        this.merchantTrxOrderNo = merchantTrxOrderNo;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(String settleAmount) {
        this.settleAmount = settleAmount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public String getPromotionStartTime() {
        return promotionStartTime;
    }

    public void setPromotionStartTime(String promotionStartTime) {
        this.promotionStartTime = promotionStartTime;
    }

    public String getPromotionEndTime() {
        return promotionEndTime;
    }

    public void setPromotionEndTime(String promotionEndTime) {
        this.promotionEndTime = promotionEndTime;
    }

    public String getTaskRecordBatchNo() {
        return taskRecordBatchNo;
    }

    public void setTaskRecordBatchNo(String taskRecordBatchNo) {
        this.taskRecordBatchNo = taskRecordBatchNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "PaymentInfoDTO{" +
                "merchantTrxOrderNo='" + merchantTrxOrderNo + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", name='" + name + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                ", settleAmount='" + settleAmount + '\'' +
                ", mobile='" + mobile + '\'' +
                ", tax='" + tax + '\'' +
                ", comment='" + comment + '\'' +
                ", makeUpCharge=" + makeUpCharge +
                ", makeUpChargeDetails='" + makeUpChargeDetails + '\'' +
                ", useBigRate=" + useBigRate +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", street='" + street + '\'' +
                ", community='" + community + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", quantity='" + quantity + '\'' +
                ", promotionStartTime='" + promotionStartTime + '\'' +
                ", promotionEndTime='" + promotionEndTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(null == obj){
            return false;
        }
        PaymentInfoDTO dto = (PaymentInfoDTO) obj;
        if(payRequestDataId.equals(dto.getPayRequestDataId())){
            return true;
        }
        return false;
    }
}
