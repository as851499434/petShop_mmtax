package com.mmtax.business.service;

import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.*;
import com.mmtax.common.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商户交易管理接口
 *
 * @author yuanligang
 * @date 2019/7/12
 */
public interface IMerchantTransactionService {


    /**
     * 退还订单列表
     *
     * @param merchantId        商户ID
     * @param pageSize          每页大小
     * @param startIndex        检索位置
     * @param startDate         起始日期
     * @param endDate           技术日期
     * @param payeeName         收款户名
     * @param orderSerialNum    订单流水号
     * @param returnSerialum    退还流水号
     * @param payeeBankCard     收款账号

     * @return
     */
    Page listReturnOrders(@Param("merchantId") int merchantId,
                          @Param("pageSize") int pageSize,
                          @Param("startIndex") int startIndex,
                          @Param("startDate") String startDate,
                          @Param("endDate") String endDate,
                          @Param("payeeName") String payeeName,
                          @Param("orderSerialNum") String orderSerialNum,
                          @Param("returnSerialum") String returnSerialum,
                          @Param("payeeBankCard") String payeeBankCard);


    /**
     *
     * @param merchantId
     * @param startDate
     * @param endDate
     * @param payeeName
     * @param orderSerialNum
     * @param returnSerialum
     * @param payeeBankCard
     * @return
     */
    List<ReturnMakeUpChargeOrderDTO> exportReturnOrder(int merchantId,  String startDate, String endDate,
                                                              String payeeName,String orderSerialNum, String returnSerialum,  String payeeBankCard);


    /**
     * 补交订单列表
     *
     * @param merchantId        商户ID
     * @param pageSize          每页大小
     * @param startIndex        检索位置
     * @param startDate         起始日期
     * @param endDate           技术日期
     * @param payeeName         收款户名
     * @param orderSerialNum    订单流水号
     * @param makeUpSerialNum    退还流水号
     * @param payeeBankCard     收款账号

     * @return
     */
    Page listMakeUpOrders(@Param("merchantId") int merchantId,
                          @Param("pageSize") int pageSize,
                          @Param("startIndex") int startIndex,
                          @Param("startDate") String startDate,
                          @Param("endDate") String endDate,
                          @Param("payeeName") String payeeName,
                          @Param("orderSerialNum") String orderSerialNum,
                          @Param("makeUpSerialNum") String makeUpSerialNum,
                          @Param("payeeBankCard") String payeeBankCard);


    /**
     *
     * @param merchantId
     * @param startDate
     * @param endDate
     * @param payeeName
     * @param orderSerialNum
     * @param makeUpSerialNum
     * @param payeeBankCard
     * @return
     */
    List<ReturnMakeUpChargeOrderDTO> exportMakeUpOrder(int merchantId, String startDate, String endDate,
                                                              String payeeName,String orderSerialNum, String makeUpSerialNum,  String payeeBankCard);


    /**
     * 获取交易订单列表
     *
     * @param merchantId        商户ID
     * @param pageSize          每页大小
     * @param currentPage       当前页码
     * @param startDate         起始时间
     * @param endDate           结束时间
     * @param merchantName      商户名称
     * @param orderSerialNum    订单流水号
     * @param merchantSerialNum 商户流水号
     * @param payeeBankCard     收款账号
     * @param payeeName         收款户名
     * @param payeeIdCardNo     身份证号
     * @param orderStatus       订单状态
     * @param paymentChannel    打款渠道
     * @return
     */
    Page listOrders(int merchantId, int pageSize, int currentPage, String startDate, String endDate, String merchantName,
                    String orderSerialNum, String merchantSerialNum, String payeeBankCard, String payeeName,
                    String payeeIdCardNo, Integer orderStatus, String paymentChannel);


    /**
     * @param merchantId
     * @param startDate
     * @param endDate
     * @param merchantName
     * @param orderSerialNum
     * @param merchantSerialNum
     * @param payeeBankCard
     * @param payeeName
     * @param payeeIdCardNo
     * @param orderStatus
     * @param paymentChannel
     * @return
     */
    List<ListTrxOrdersDTO> exportOrder(int merchantId, String startDate, String endDate, String merchantName,
                               String orderSerialNum, String merchantSerialNum, String payeeBankCard, String payeeName,
                               String payeeIdCardNo, Integer orderStatus, String paymentChannel);


    /**
     * 获取挂起订单、调单管理订单列表
     *
     * @param merchantId        商户ID
     * @param pageSize          每页大小
     * @param currentPage       当前页码
     * @param startDate         起始时间
     * @param endDate           结束时间
     * @param merchantName      商户名称
     * @param orderSerialNum    订单流水号
     * @param merchantSerialNum 商户流水号
     * @param payeeBankCard     收款账号
     * @param payeeName         收款户名
     * @param payeeIdCardNo     身份证号
     * @param orderStatus       订单状态
     * @param paymentChannel    打款渠道
     * @return
     */
    Page listHangsOrders(int merchantId, int pageSize, int currentPage, String startDate, String endDate,
                         String merchantName, String orderSerialNum, String merchantSerialNum, String payeeBankCard,
                         String payeeName, String payeeIdCardNo, Integer orderStatus, String paymentChannel);

    /**
     * 获取流水详情
     *
     * @param id 订单ID
     * @return
     */
    TrxOrderDTO getOrderDetail(int id);

    /**
     * 电子凭单
     * 交易订单ID
     *
     * @param id
     * @return
     */
    ElectronicVoucherDTO getVoucher(int id);

    /**
     * 获取资金流水
     *
     * @param merchantId
     * @param pageSize
     * @param currentPage
     * @param startDate
     * @param endDate
     * @param orderSerialNum
     * @param type
     * @return
     */
    Page getCapitalFlow(Integer merchantId, Integer pageSize, Integer currentPage,
                        String startDate, String endDate, String orderSerialNum, Integer type);

    /**
     * 资金流水
     *
     * @param merchantId     商户id
     * @param startDate      开始日期
     * @param endDate        结束日期
     * @param orderSerialNum 订单流水号
     * @param type           类型
     * @return
     */
    List<MerchantCapitalFlowDTO> exportCapitalFlow(Integer merchantId, String startDate, String endDate,
                                                   String orderSerialNum, Integer type);

    /**
     * 获取商户端商户交易统计数据
     *
     * @param startDate 起始时间
     * @param endDate 结束时间
     * @param merchantName 商户名称
     * @param orderSerialNum 订单编号
     * @param merchantSerialNum 商户订单号
     * @param payeeBankCard 收款人银行卡号
     * @param payeeName 收款人名字
     * @param payeeIdCardNo 收款人身份证号
     * @param merchantId 商户id
     * @return
     */
    MerchantStatisticalDataDTO getMerchantStatisticalData(String startDate, String endDate, String merchantName,
                                                          String orderSerialNum, String merchantSerialNum,
                                                          String payeeBankCard, String payeeName, String payeeIdCardNo,
                                                          Integer orderStatus, Integer merchantId);
}
