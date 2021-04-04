package com.mmtax.business.service;

import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.*;
import com.mmtax.common.page.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/12 16:58
 */
public interface ITrxOrderService {

    /**
     * 查看服务费退还记录
     * @return
     */
    List<ManagerReturnListTrxOrderDTO> returnListTrxOrder(String startDate,
                                                                        String endDate,
                                                                        String payeeName);
    /**
     * 查看服务费补交记录
     * @return
     */
    List<ManagerMakeUpListTrxOrderDTO> makeUpListTrxOrder(String startDate,
                                                                        String endDate,
                                                                        String payeeName);

    /**
     * 获取代付列表
     *
     * @param managerTrxOrderDTO 查询参数
     * @return
     */
    List<ManagerTrxOrderDTO> getListTrxOrder(ManagerTrxOrderDTO managerTrxOrderDTO);

    /**
     * 月度代付数据汇总
     *
     * @param flag 月度和累计的标识
     * @return
     */
    ManagerIndexPaymentDTO getMonthPayment(SysUserDTO sysUser);

    /**
     * 年度数据按月份展示
     *
     * @return
     */
    List<ManagerIndexYearDataDTO> getMonthAmount();

    /**
     * 获取后台调单列表
     *
     * @param dto 参数
     * @return
     */
    List<ManagerOrderSheetDTO> getListOrderSheet(ManagerOrderSheetDTO dto);

    /**
     * 获取交易数据汇总
     *
     * @param sysUser 登录用户
     * @return
     */
    ManagerSaleStatisticsDTO getSaleStatistics(SysUserDTO sysUser);

    /**
     * 获取累计打款详情
     *
     * @param sysUser 登录用户
     * @return
     */
    ManagerIndexPaymentDTO cumulativePaymentBySale(SysUserDTO sysUser);

    /**
     * 当月交易走势图
     *
     * @param sysUser    登录用户
     * @return
     */
    List<MangerMerchantStatisticsDTO> getMerchantMonthPaidStatistics(SysUserDTO sysUser);

    /**
     * 当年交易走势图
     *
     * @param sysUser    登录用户
     * @return
     */
    List<MangerMerchantStatisticsDTO> getMerchantYearPaidStatistics(SysUserDTO sysUser);

    /**
     * 获取商户汇总数据统计
     *
     * @param dto     参数
     * @return
     */
    List<ManagerSaleStatisticDTO> getSaleStatistic(ManagerSaleStatisticDTO dto);

    /**
     * 组合打款、变动账户和添加记录
     * @param trxOrder
     * @param transferAmount
     */
    void composeTrxOrderPay(TrxOrder trxOrder, BigDecimal transferAmount);

    void changeTrxOrderSuccess(TrxOrder trxOrder);

    void changeTrxOrderFail(TrxOrder trxOrder,String errorMsg);

    /**
     * 推广列表查询
     * @param queryCustomerPromotionDTO
     * @return
     */
    List<CustomerPromotionDTO> getPromotionList(QueryCustomerPromotionDTO queryCustomerPromotionDTO);

    Page listPromotions(QueryCustomerPromotionDTO queryCustomerPromotionDTO);

    List<ChargeReimburseInfoDTO> getRechargeReimburseList(QueryChargeReimburseInfoDTO queryCustomerPromotionDTO);
}
