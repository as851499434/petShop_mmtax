package com.mmtax.business.mapper;

import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.*;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * 交易订单 数据层
 *
 * @author meimiao
 * @date 2019-07-09
 */
@Repository
public interface TrxOrderMapper extends MyMapper<TrxOrder> {

    //统计batchPaymentId 终态的个数
    Integer countTrxOrderFinish(@Param("batchPaymentRecordId")Integer batchPaymentRecordId);

    BigDecimal sumActualAmountByBankIdCusId(@Param("customerId") Integer customerId,@Param("bankId") Integer bankId);

    List<IncomeWithdrawDTO> listPageByCustomerIdCreateTimeStatus(@Param("customerId") Integer customerId,
                                                                 @Param("status") Integer status,
                                                                 @Param("startDate") String startDate,
                                                                 @Param("endDate") String endDate,
                                                                 @Param("index") Integer index,
                                                                 @Param("pageSize") Integer pageSize);

    int countByCustomerIdCreateTimeStatus(@Param("customerId") Integer customerId,
                                          @Param("status") Integer status,
                                          @Param("startDate") String startDate,
                                          @Param("endDate") String endDate);

   DownloadCredentialDTO selectCreateTimeAndPidByorderSerialNum(@Param("orderSerialNum")String orderSerialNum);

    List<TrxOrder> selectByStatusInOneMonth(@Param("merchantId") Integer merchantId
            , @Param("customerId") Integer customerId,@Param("idCardNo") String idCardNo
            ,@Param("startTime") String startTime,@Param("endTime") String endTime
            ,@Param("statusList") Integer[] statusList,@Param("taxSourceId") Integer taxSourceId);

    List<TrxOrder> selectByOrderStatusAsyncStatus(@Param("orderStatus") Integer orderStatus
            ,@Param("asyncStatus") Integer asyncStatus);
    /**
     * 查询订单为init和process
     */
    List<TrxOrder> selectByStatusList(@Param("createTime") String createTime);

    /**
     * 查询订单状态为成功或者失败的订单
     */
    List<TrxOrder> selectStatusSuccessOrFail();

    /**
     * 根据时间查询进行中的和成功的大额订单
     * @return
     */
    List<TrxOrder> selectByMonthAndBigRate(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    int countSuccess(@Param("merchatId")Integer merchatId,
                     @Param("batchNo")String batchNo );


    /**
     * 查看服务费退还记录
     * @return
     */
    List<ManagerReturnListTrxOrderDTO> returnListTrxOrder(@Param("startDate") String startDate,
                                                          @Param("endDate") String endDate,
                                                          @Param("payeeName") String payeeName);
    /**
     * 查看服务费补交记录
     * @return
     */
    List<ManagerMakeUpListTrxOrderDTO> makeUpListTrxOrder(@Param("startDate") String startDate,
                                                          @Param("endDate") String endDate,
                                                          @Param("payeeName") String payeeName);


    /**
     * 交易订单列表
     *
     * @param merchantId        商户ID
     * @param pageSize          每页大小
     * @param startIndex        检索位置
     * @param startDate         起始日期
     * @param endDate           技术日期
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
    List<ListTrxOrdersDTO> listTrxOrders(@Param("merchantId") int merchantId, @Param("pageSize") Integer pageSize,
                                 @Param("startIndex") Integer startIndex, @Param("startDate") String startDate,
                                 @Param("endDate") String endDate, @Param("merchantName") String merchantName,
                                 @Param("orderSerialNum") String orderSerialNum,
                                 @Param("merchantSerialNum") String merchantSerialNum,
                                 @Param("payeeBankCard") String payeeBankCard, @Param("payeeName") String payeeName,
                                 @Param("payeeIdCardNo") String payeeIdCardNo, @Param("orderStatus") Integer orderStatus,
                                 @Param("paymentChannel") String paymentChannel);

    /**
     * 交易订单列表
     *
     * @param merchantId        商户ID
     * @param startDate         起始日期
     * @param endDate           技术日期
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
    int countTrxOrders(@Param("merchantId") int merchantId, @Param("startDate") String startDate,
                       @Param("endDate") String endDate, @Param("merchantName") String merchantName,
                       @Param("orderSerialNum") String orderSerialNum,
                       @Param("merchantSerialNum") String merchantSerialNum,
                       @Param("payeeBankCard") String payeeBankCard,
                       @Param("payeeName") String payeeName, @Param("payeeIdCardNo") String payeeIdCardNo,
                       @Param("orderStatus") Integer orderStatus, @Param("paymentChannel") String paymentChannel);


    /**
     * 交易订单列表--商户后台
     *
     * @param sysTrxRecordDTO
     * @return
     */
    List<SysTrxOrderDTO> listSysTrxOrders(SysTrxRecordDTO sysTrxRecordDTO);


    /**
     * 获取代付列表
     *
     * @param managerTrxOrderDTO 查询参数
     * @return
     */
    List<ManagerTrxOrderDTO> getListTrxOrder(ManagerTrxOrderDTO managerTrxOrderDTO);

    List<ManagerTrxOrderDTO> getListTrxOrderOfSource(ManagerTrxOrderDTO managerTrxOrderDTO);

    /**
     * 根据批量代付记录id获取代付列表
     *
     * @param id         批量代付记录id
     * @param startIndex 查询起始位置
     * @param pageSize   每页大小
     * @return
     */
    List<MerchantBatchPaymentDetailDTO> getListDetail(@Param("id") Integer id,
                                                      @Param("startIndex") Integer startIndex,
                                                      @Param("pageSize") Integer pageSize);

    /**
     * 根据批量代付记录id获取代付列表数量
     *
     * @param id 批量代付id
     * @return
     */
    int getCountDetail(@Param("id") Integer id);

    /**
     * 根据批量代付id获取各个状态的金额
     *
     * @param id          批量代付记录id
     * @param orderStatus 代付订单状态
     * @return
     */
    BigDecimal getSumAmountByRecordId(@Param("id") Integer id, @Param("orderStatus") Integer orderStatus);

    /**
     * 根据批量代付id获取各个状态的数量
     *
     * @param id          批量代付记录id
     * @param orderStatus 代付订单状态
     * @return
     */
    int getCountByRecordId(@Param("id") Integer id, @Param("orderStatus") Integer orderStatus);

    /**
     * 获取批量代付时所花的手续费
     *
     * @param id 批量代付记录id
     * @return
     */
    BigDecimal getSumCharge(@Param("id") Integer id);

    /**
     * 获取交易记录明细
     *
     * @param id 交易记录ID
     * @return
     */
    TrxOrderDTO getTrxDetail(@Param("id") Integer id);

    /**
     * 电子凭单
     *
     * @param id 交易订单号
     * @return
     */
    ElectronicVoucherDTO getVoucher(@Param("id") Integer id);

    /**
     * 获取订单总数量
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    int orderNum(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 获取各个代付渠道代付金额
     *
     * @return
     */
    ManagerIndexPaymentDTO getChannelPaymentBySaler(SysUserDTO sysUser);

    /**
     * 获取累计打款金额
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    BigDecimal getSumAmount(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 获取今年每个月代付金额
     *
     * @return
     */
    List<ManagerIndexYearDataDTO> getMonthAmount();

    /**
     * 获取商户当年代付金额
     *
     * @param startDate  起始时间
     * @param endDate    结束时间
     * @param merchantId 商户id
     * @return
     */
    BigDecimal getSumAmountByMerchantIdCustomerId(@Param("startDate") String startDate, @Param("endDate") String endDate
            , @Param("merchantId") Integer merchantId, @Param("customerId") Integer customerId);

    /**
     * 获取商户当年代付金额扣除的手续费
     *
     * @param startDate  起始时间
     * @param endDate    结束时间
     * @param merchantId 商户id
     * @return
     */
    BigDecimal getSumTaxAmountByMerchantId(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                           @Param("merchantId") Integer merchantId);

    /**
     * 获取商户端年度走势图
     *
     * @param merchantId 商户id
     * @param startDate  起始时间
     * @param endDate    结束时间
     * @return
     */
    List<PayTrendDTO> getMerchantMonthPaymentDetail(@Param("startDate") String startDate,
                                                    @Param("endDate") String endDate,
                                                    @Param("merchantId") Integer merchantId);

    /**
     * 获取商户端每日走势图
     *
     * @param merchantId 商户id
     * @param startDate  起始时间
     * @param endDate    结束时间
     * @return
     */
    List<DayPayTrendDTO> getMerchantDayPaymentDetail(@Param("startDate") String startDate,
                                                     @Param("endDate") String endDate,
                                                     @Param("merchantId") Integer merchantId);

    /**
     * 商户交易笔数汇总
     * @param startDate 起始时间
     * @param endDate 结束时间
     * @param merchantName 商户名称
     * @param orderSerialNum 订单编号
     * @param merchantSerialNum 商户订单号
     * @param payeeBankCard 收款人银行卡号
     * @param payeeName 收款人名字
     * @param payeeIdCardNo 收款人身份证号
     * @param orderStatus 订单状态
     * @param merchantId 商户id
     * @return
     */
    MerchantStatisticalDataDTO getMerchantStatisticalOrderCount(@Param("startDate") String startDate,
                                                                @Param("endDate") String endDate,
                                                                @Param("merchantName") String merchantName,
                                                                @Param("orderSerialNum") String orderSerialNum,
                                                                @Param("merchantSerialNum") String merchantSerialNum,
                                                                @Param("payeeBankCard") String payeeBankCard,
                                                                @Param("payeeName") String payeeName,
                                                                @Param("payeeIdCardNo") String payeeIdCardNo,
                                                                @Param("orderStatus") Integer orderStatus,
                                                                @Param("merchantId") Integer merchantId);

    /**
     * 商户交易金额汇总
     *
     * @param startDate 起始时间
     * @param endDate 结束时间
     * @param merchantName 商户名称
     * @param orderSerialNum 订单编号
     * @param merchantSerialNum 商户订单号
     * @param payeeBankCard 收款人银行卡号
     * @param payeeName 收款人名字
     * @param payeeIdCardNo 收款人身份证号
     * @param orderStatus 订单状态
     * @param merchantId 商户id
     * @return
     */
    MerchantStatisticalDataDTO getMerchantStatisticalOrderAmount(@Param("startDate") String startDate,
                                                                 @Param("endDate") String endDate,
                                                                 @Param("merchantName") String merchantName,
                                                                 @Param("orderSerialNum") String orderSerialNum,
                                                                 @Param("merchantSerialNum") String merchantSerialNum,
                                                                 @Param("payeeBankCard") String payeeBankCard,
                                                                 @Param("payeeName") String payeeName,
                                                                 @Param("payeeIdCardNo") String payeeIdCardNo,
                                                                 @Param("orderStatus") Integer orderStatus,
                                                                 @Param("merchantId") Integer merchantId);

    /**
     * 商户交易成功手续费金额汇总
     *
     * @param startDate 起始时间
     * @param endDate 结束时间
     * @param merchantName 商户名称
     * @param orderSerialNum 订单编号
     * @param merchantSerialNum 商户订单号
     * @param payeeBankCard 收款人银行卡号
     * @param payeeName 收款人名字
     * @param payeeIdCardNo 收款人身份证号
     * @param orderStatus 订单状态
     * @param merchantId 商户id
     * @return
     */
    BigDecimal getMerchantStatisticalOrderCharge(@Param("startDate") String startDate,
                                                 @Param("endDate") String endDate,
                                                 @Param("merchantName") String merchantName,
                                                 @Param("orderSerialNum") String orderSerialNum,
                                                 @Param("merchantSerialNum") String merchantSerialNum,
                                                 @Param("payeeBankCard") String payeeBankCard,
                                                 @Param("payeeName") String payeeName,
                                                 @Param("payeeIdCardNo") String payeeIdCardNo,
                                                 @Param("orderStatus") Integer orderStatus,
                                                 @Param("merchantId") Integer merchantId);

    /**
     * 获取销售总金额数据汇总
     * @param  sysUser
     * @return
     */
    BigDecimal getSumAmountBySale(SysUserDTO sysUser);

    /**
     * 获取销售手续费数据汇总
     *
     * @param  sysUser
     * @return
     */
    BigDecimal getSumChargeBySale(SysUserDTO sysUser);

    /**
     * 获取销售数据订单数汇总
     *
      @param  sysUser
     * @return
     */
    Integer getCountOrderBySale(SysUserDTO sysUser);

    /**
     * 获取同一收款人同一税源地期间打款金额求和
     * @param merchantId 收款人身份证号
     * @param payeeIdCardNo 收款人身份证号
     * @param endDate   起始时间
     * @param startDate 结束时间
     * @return
     */
    BigDecimal sumAmountByPayeeIdCardNo(@Param("merchantId") String merchantId,
                                        @Param("payeeIdCardNo") String payeeIdCardNo,
                                        @Param("startDate") String startDate,
                                        @Param("endDate") String endDate);
 /**
  * 获取同一收款人同一税源地期间打款金额求和
  * @param merchantId 商户 id
  * @param customerId 员工 id
  * @param endDate   起始时间
  * @param startDate 结束时间
  * @return
  */
 BigDecimal sumAmountByCustomerId(@Param("merchantId") Integer merchantId,
                                     @Param("customerId") Integer customerId,
                                     @Param("startDate") String startDate,
                                     @Param("endDate") String endDate);

    /**
     * 当月交易走势图
     * @param  sysUser
     * @return
     */
    List<MangerMerchantStatisticsDTO> getMerchantMonthPaidStatistics(SysUserDTO sysUser);

    /**
     * 当年交易走势图
     * @param  sysUser
     * @return
     */
    List<MangerMerchantStatisticsDTO> getMerchantYearPaidStatistics(SysUserDTO sysUser);

    /**
     * 获取商户汇总数据统计
     *
     * @param dto 参数
     * @return
     */
    List<ManagerSaleStatisticDTO> getSaleStatistic(ManagerSaleStatisticDTO dto);

    /**
     * 根据商户订单号查询订单数量
     *
     * @param merchantTrxOrderNo 商户订单号
     * @return
     */
    Integer getCountByMerchantSerialNum(@Param("merchantTrxOrderNo") String merchantTrxOrderNo,
                                        @Param("merchantId") Integer merchantId);

    List<TrxOrder> selectInOrderSerialNums(@Param("orderSerialNums") List<String> orderSerialNums);

    TrxOrder selectByOrderSerialNum(@Param("orderSerialNum") String orderSerialNum);

    TrxOrder selectByMerchantSerialNum(@Param("merchantSerialNum") String merchantSerialNum);

    List<TrxOrder> selectByCustomerIdAndStatusAndUseBigRate(@Param("customerId") Integer customerId
            ,@Param("merchantId") Integer merchantId,@Param("status") Integer status
            ,@Param("useBigRate") Integer useBigRate);
    //推广列表查询
    List<CustomerPromotionDTO> getPromotionList(QueryCustomerPromotionDTO queryCustomerPromotionDTO);

    //查询推广列表
    List<CustomerPromotionDTO> listPromotions(QueryCustomerPromotionDTO queryCustomerPromotionDTO);

    int countPromotion(QueryCustomerPromotionDTO queryCustomerPromotionDTO);


    BigDecimal getAllamoutByBatchNoAndCertificateNo(@Param("batchNo")String batchNo,
                                                    @Param("certificateNo")String certificateNo);

    /**
     * 获取生成协议信息
     * @param idNumber
     * @param merchantId
     * @return
     */
    ManagerAgreementInfoDTO getManagerAgreementInfoDTO(@Param("idNumber") String idNumber,
                                                       @Param("merchantId") Integer merchantId);

}