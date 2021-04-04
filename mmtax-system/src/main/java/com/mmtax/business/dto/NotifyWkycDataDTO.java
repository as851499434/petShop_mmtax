package com.mmtax.business.dto;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.*;
import com.mmtax.common.enums.PaymentChannelEnum;
import com.mmtax.common.enums.PaymentEnum;
import com.mmtax.common.enums.TrxOrderStatusEnum;
import com.mmtax.common.enums.UseBigRateEnum;
import lombok.Data;

/** 采用构造器 */
@Data
public class NotifyWkycDataDTO {
    /**
     * 交易编码 全局唯一
     */
    private String trxExternalNo;
    /**
     * 原交易编码 退款时对应的原交易编码 trxExternalNo
     */
    private String originalTrxExternalNo;
    /**
     * 商户编码 全局唯一
     */
    private String merchantCode;
    /**
     * 商户名称
     */
    private String merchantName;
    /**
     * 代理编码
     */
    private String agentNo;
    /**
     * 交易金额
     */
    private String tranAmount;
    /**
     * 服务费率
     */
    private String feeRate;
    /**
     * 是否大额 Y-是 N-否
     */
    private String bigFee;
    /**
     * 是否退款 Y-是 N-否
     */
    private String refund;
    /**
     * 服务费金额
     */
    private String fee;
    /**
     * 交易时间 yyyy-mm-dd HH:mm:ss
     */
    private String tranTime;
    /**
     * 是否成功 fail- 失败 success-成功
     */
    private String status;
    /** 收款方法 B-银行卡 A-支付宝 */
    private String paymentChannel;

    public static class Builder{
        private String trxExternalNo;
        private String refund;
        private String originalTrxExternalNo = "";
        private String merchantCode = "";
        private String merchantName = "";
        private String agentNo = "";
        private String tranAmount = "";
        private String feeRate = "";
        private String bigFee = "";
        private String fee = "";
        private String tranTime = "";
        private String status = "";
        private String paymentChannel = "";

        public Builder(TrxOrder trxOrder, MerchantInfo merchantInfo){
            this.trxExternalNo = trxOrder.getOrderSerialNum();
            this.refund = "N";
            this.merchantCode = merchantInfo.getMerchantCode();
            this.merchantName = merchantInfo.getMerchantName();
            this.agentNo = merchantInfo.getAgentNumber();
            this.tranAmount = trxOrder.getAmount().toString();
            boolean useBigRate = UseBigRateEnum.BIGRATE.getStatus().equals(trxOrder.getUseBigRate());
            this.feeRate = useBigRate ?trxOrder.getTrxRateBig().toString():trxOrder.getTaxRate().toString();
            this.bigFee = useBigRate?"Y":"N";
            this.fee = useBigRate?trxOrder.getChargeBig().toString():trxOrder.getCharge().toString();
            this.tranTime = DateUtil.formatDateTime(trxOrder.getCreateTime());
            this.status = TrxOrderStatusEnum.PAID.getCode().equals(trxOrder.getOrderStatus())?"success":"fail";
            this.paymentChannel = PaymentEnum.BANK.name().equals(trxOrder.getPaymentChannel())?"B":"A";
        }

        public Builder(TradeRefundOrder refundOrder, String originalTrxExternalNo){
            this.trxExternalNo = refundOrder.getRefundSerialNum();
            this.refund = "Y";
            this.originalTrxExternalNo = originalTrxExternalNo;
            this.tranTime = DateUtil.formatDateTime(refundOrder.getCreateTime());
        }

        public NotifyWkycDataDTO build(){
            return new NotifyWkycDataDTO(this);
        }
    }

    public NotifyWkycDataDTO() {

    }

    private NotifyWkycDataDTO(Builder builder) {
        trxExternalNo = builder.trxExternalNo;
        refund = builder.refund;
        originalTrxExternalNo = builder.originalTrxExternalNo;
        merchantCode = builder.merchantCode;
        merchantName = builder.merchantName;
        agentNo = builder.agentNo;
        tranAmount = builder.tranAmount;
        feeRate = builder.feeRate;
        bigFee = builder.bigFee;
        fee = builder.fee;
        tranTime = builder.tranTime;
        status = builder.status;
        paymentChannel = builder.paymentChannel;
    }
}
