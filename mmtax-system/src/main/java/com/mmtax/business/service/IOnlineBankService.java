package com.mmtax.business.service;

import com.mmtax.business.domain.*;
import com.mmtax.business.dto.BatchWithDrawToCardDTO;
import com.mmtax.common.utils.onlinebank.*;

import java.math.BigDecimal;
import java.util.Map;

public interface IOnlineBankService {

    /**
     * (网商)添加商户
     * @param merchantId
     * @param merchantInfo
     * @return
     */
    OnlinePaymentInfo registerAccount(Integer merchantId, MerchantInfo merchantInfo,Integer taxSourceId);

    /**
     * 修改网商信息
     * @param merchantId
     * @param merchantInfo
     * @param taxSourceId
     * @return
     */
    OnlineCommonResultDTO modifyAccount(Integer merchantId, MerchantInfo merchantInfo,Integer taxSourceId);

    /**
     * 添加员工(网商)
     * @param taxSourceId
     * @param customerInfo
     * @return
     */
    OnlineCustomerInfo personalRegister(Integer taxSourceId,CustomerInfo customerInfo);

    /**
     * 卡bin查询
     * @param bankAccountNo
     * @return
     */
    CardbinQueryResultDTO.CardBinInfo cardbinQuery(String bankAccountNo,Integer customerId);

    /**
     * 绑定银行卡
     * @param customerBankcardInfo
     * @return
     */
    String bankcardBind(CustomerBankcardInfo customerBankcardInfo);

    /**
     * 绑定支付宝
     * @param customerAlipayInfo
     * @return
     */
    String alipayBind(CustomerAlipayInfo customerAlipayInfo);

    /**
     * 解绑支付宝或银行卡
     * @param bankInfoId
     * @param alipayInfoId
     * @return
     */
    boolean bankcardOrAlipayUnbind(Integer bankInfoId, Integer alipayInfoId);

    /**
     * 商户向员工转账
     * @param transferOrder
     * @return
     */
    OnlineCommonResultDTO transferAmountFromMerToCus(TransferOrder transferOrder,BigDecimal overCharge);


    /**
     * 员工向商户转账
     * @param transferOrder
     * @return
     */
    OnlineCommonResultDTO transferAmountFromCusToMer(TransferOrder transferOrder);

    /**
     * 退款
     * @return
     */
    OnlineCommonResultDTO refundTransferOrder(TradeRefundOrder tradeRefundOrder);

    /**
     * 单笔提现
     * @param trxOrder
     * @return
     */
    OnlineCommonResultDTO tradePayToCard(TrxOrder trxOrder);

    /**
     * 单笔提现(临工用)
     * @param customerWithdraw
     * @return
     */
    OnlineCommonResultDTO customerWithdraw(CustomerWithdraw customerWithdraw);

    /**
     * 单笔提现到卡
     * @param trxOrder
     * @param onlineCustomerInfo
     * @param taxWithdrawAccount
     * @return
     */
    OnlineCommonResultDTO payToTheCard(TrxOrder trxOrder,OnlineCustomerInfo onlineCustomerInfo,TaxWithdrawAccount taxWithdrawAccount);

    /**
     * 批量打款(网商)
     * @return
     */
    OnlineCommonResultDTO batchwithdrawtocard(Integer taxSourceId,String batchPaymentSerialNum,String merchantCode
            , BatchWithDrawToCardDTO batchWithDrawToCard);

    /**
     * 单笔提现到卡 税源地专用
     * @param trxOrder
     * @return
     */
    OnlineCommonResultDTO payToTheCardForTaxSource(TrxOrder trxOrder, Integer taxSourceId, String uid);

    /**
     * 单笔提现到卡 商户专用
     * @param trxOrder
     * @return
     */
    OnlineCommonResultDTO payToTheCardForMerchant(TrxOrder trxOrder, Integer taxSourceId, String uid,
                                                  String bankLineNo, String bankBranch);
    /**
     * 添加单笔提现到卡资金流水 商户专用
     * @param uid
     * @param amount
     * @param trxOrder
     */
    void payToTheCardForMerchantAddInfo(String uid, String amount, TrxOrder trxOrder, String taxSourceId);
    /**
     * 异步通知处理
     */
    String receiveTranctionNotify(Map<String, String> paramCharsetConvert,Map<String,String> map);

    /**
     * 处理代付异步通知
     * @param paramsMap
     */
    void dealWithdrawNotify(Map<String,String> paramsMap);

    /**
     * 處理退款异步通知
     * @param paramsMap
     */
    void dealRefundNotify(Map<String,String> paramsMap);

    /**
     * 处理来账异步通知
     * @param paramsMap
     * @param onlinePaymentInfo
     */
    void dealRemitNotify(Map<String,String> paramsMap,OnlinePaymentInfo onlinePaymentInfo);

    /**
     * 处理手续费异步通知
     * @param paramsMap
     */
    void dealTransferNotify(Map<String,String> paramsMap);

    /**
     * 查询子账户余额
     * @param merchantId
     * @return
     */
    AccountBalanceResultDTO accountBalance(Integer merchantId,Integer customerId,Integer taxSourceId,String accountType);

    /**
     * 平台基本户转移金额至子账户
     */
    OnlineCommonResultDTO transferAmountFromPbcsicToMer(Integer taxSourceId, Integer merId, BigDecimal money);

    /**
     * 商户补交服务费
     * @param merId
     * @param money
     * @return
     */
    OnlineCommonResultDTO transferAmountFromMerToPincome(Integer merId, BigDecimal money, String tradeNo);

    /**
     * 退还商户服务费
     * @param merId
     * @param money
     * @return
     */
    OnlineCommonResultDTO transferAmountFromPincomeToMer(Integer merId, BigDecimal money, String tradeNo);

    /**
     * 查询网商订单状态
     * @param orderSerialNum
     * @return
     */
    InfoQueryResultDTO infoQuery(Integer merchantId,String orderSerialNum);

    /**
     * 查询子账户余额变动明细
     * @param merchantId
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    AccountQueryResultDTO accountQuery(Integer merchantId, String startTime, String endTime, String page);

    /**
     * 子账户余额核对网商
     * @param merchantId 商户id
     * @return 结果
     */
    AccountCheckOnlineDTO accountCheckOnline(Integer merchantId);

    String bankQueryOnline(String parentBranchNO, String areaCode, String keyWords, String sourceCompanyId);

    /**
     * 订单成功时的处理
     * @param resultTrxOrder
     */
    void orderSuccessDeal(TrxOrder resultTrxOrder);

    /**
     * 订单失败时的处理
     */
    void orderFailDeal(TrxOrder resultTrxOrder);

    /**
     * 我方发送异步通知处理
     * @param trxOrder 打款订单信息
     */
    void callBackNotifyOfOnline(TrxOrder trxOrder);
    /**
     * 手动改变退款失败订单
     * @param orderSerialNum 订单流水号
     */
    void changeOrderInfo(String orderSerialNum);

    /**
     * 获得商户账户可用余额
     * @param merchantId
     * @return
     */
    BigDecimal getUsableAmount(Integer merchantId);
}
