package com.mmtax.business.service;

import com.mmtax.business.domain.MerchantAccount;
import com.mmtax.business.domain.RechargeRecord;
import com.mmtax.business.dto.*;
import com.mmtax.common.page.Page;
import com.mmtax.system.domain.SysUser;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/15 16:16
 */
public interface IMerchantAccountService {

    /**
     * 获取商户当月代付总笔数和总金额
     *
     * @param merchantId 商户id
     * @return
     */
    MerchantSumPaymentDetailDTO getMerchantSumPaymentDetail(Integer merchantId);


    /**
     * 获取商户账户余额
     *
     * @param merchantId 商户id
     * @return
     */
    BigDecimal getAccountByMerchantId(Integer merchantId);

    Page getRechargeRecord(Integer merchantId, String rechargeChannel, String rechargeType, String status,
                           Integer pageSize, Integer currentPage, String startDate, String endDate);

    /**
     * 获取商户户户转账记录
     *
     * @param merchantId  商户id
     * @param pageSize    每页大小
     * @param currentPage 页码
     * @param startDate   查询起始位置
     * @param endDate     查询结束位置
     * @param type        0-转入1-转出
     * @return
     */
    Page getPageTransferAccounts(Integer merchantId, Integer pageSize, Integer currentPage, String startDate,
                                 String endDate, Integer type);

    /**
     * 获取首页年度账单 modifyBy ylg
     * @param merchantId
     * @return
     */
    MainPagePayDTO getPayTrend(Integer merchantId);

    /**
     * 获取商户账户列表
     *
     * @param managerAccountDTO 查询参数
     * @return
     */
    List<ManagerAccountDTO> getListManagerAccount(ManagerAccountDTO managerAccountDTO);

    /**
     * 获取账户流水详情
     * @param managerAccountRecordDTO 查询条件
     * @return
     */
    List<ManagerCapitalFlowDTO> getListAccountRecord(ManagerAccountRecordDTO managerAccountRecordDTO);


    /**
     * 账户详情
     * @param managerAccountingDTO
     * @return
     */
    List<ManagerCapitalFlowDTO> getListAccountingDetails(ManagerAccountingDTO managerAccountingDTO);

    /**
     * 获取账户返点金额
     *
     * @param merchantId
     * @return
     */
    ManagerReturnPointDTO getAccountReturnPoint(Integer merchantId);


    /**
     * 获取账户返点流水
     * @param managerReturnPointQueryDTO
     * @return
     */
    List<MerchantReturnPointListDTO> getReturnPointList(ManagerReturnPointQueryDTO managerReturnPointQueryDTO);

    /**
     * 获取充值记录列表
     *
     * @param startDate  起始日期
     * @param endDate    结束日期
     * @param merchantId 商户id
     * @return
     */
    List<MerchantRechargeRecordDTO> exportMerchantRechargeRecord(String startDate, String endDate,
                                                                 Integer merchantId, String rechargeChannel,
                                                                 String rechargeType, String status);
    /**
     * 获取商户返点流水
     *
     * @param merchantId
     * @param pageSize
     * @param currentPage
     * @param startDate
     * @param endDate
     * @param status      审核状态
     * @return
     */
    Page getMerchantReturnPointList(Integer merchantId, Integer pageSize, Integer currentPage, String startDate,
                                    String endDate, Integer status);


    /**
     * 充值模拟测试用
     *
     * @param rechargeSimulationDTO
     */
    void rechargeSimulation(RechargeSimulationDTO rechargeSimulationDTO) throws Exception;

    /**
     * 获取商户端月度走势图
     *
     * @param merchantId 商户id
     * @param startDate  起始时间
     * @param endDate    结束时间
     * @return
     */
    List<PayTrendDTO> getMerchantMonthPaymentDetail(Integer merchantId, String startDate, String endDate);

    /**
     * 获取商户端每日走势图
     *
     * @param merchantId 商户id
     * @param startDate  起始时间
     * @param endDate    结束时间
     * @return
     */
    List<DayPayTrendDTO> getMerchantDayPaymentDetail(Integer merchantId, String startDate, String endDate);

    /**
     * 获取充值记录
     *
     * @param managerRechargeDTO 参数
     * @return
     */
    List<ManagerRechargeDTO> getRechargeList(ManagerRechargeDTO managerRechargeDTO);

    /**
     * 充值审核
     *
     * @param rechargeRecord 参数
     */
    void auditRecharge(RechargeRecord rechargeRecord);

    /**
     * 根据版本号更新钱包余额
     * @param merchantId
     * @param amount
     */
    void updateMerchantAccountByVersion(Integer merchantId, BigDecimal amount);
    /**
     * 根据版本号更新钱包余额
     * @param merchantId
     * @param amount
     * @param type 1冻结或解冻到可用金额 2代付成功总余额扣除冻结金额
     */
    MerchantAccount updateMerchantAccountByVersion(Integer merchantId, BigDecimal amount, Integer type);

    /**
     *
     * 后台获取合作信息列表
     * @param managerMerchantQueryDTO
     * @return
     */
    List<CooperationInfoDTO> getSysCooperationList(ManagerMerchantQueryDTO managerMerchantQueryDTO);

    /**
     * 处理资金流水旧数据
     * @return
     */
    void getIsNullMerchantAccountDetail();
}
