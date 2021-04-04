package com.mmtax.business.mapper;

import com.mmtax.business.domain.MerchantAccountDetail;
import com.mmtax.business.dto.*;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商户账户余额变动明细 数据层
 *
 * @author meimiao
 * @date 2019-07-09
 */
@Repository
public interface MerchantAccountDetailMapper extends MyMapper<MerchantAccountDetail> {

    /**
     * 查询某个商户当月代付总金额
     *
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param merchantId 商户id
     * @param type 交易类型
     * @return
     */
    BigDecimal getMonthSumPaymentAmount(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                        @Param("merchantId") Integer merchantId, @Param("type") Integer type);




    /**
     * 查询某个商户当月代付总条数
     *
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param merchantId 商户id
     * @param type 交易类型
     * @return
     */
    int getMonthCountPaymentRecord(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                   @Param("merchantId") Integer merchantId, @Param("type") Integer type);


    /**
     * 获渠道代付总额
     * @param startDate
     * @param endDate
     * @param merchantId
     * @param type
     * @return
     */
    List<MerchantChannelPayment> getChannelPayment(@Param("startDate") String startDate,
                                                   @Param("endDate") String endDate,
                                                   @Param("merchantId") Integer merchantId,
                                                   @Param("type") Integer type);


    /**
     * 查询年度交易走势
     *
     * @param startDate  开始时间
     * @param endDate    结束时间
     * @param merchantId 商户id
     * @param type 交易类型
     * @return
     */
    List<PayTrendDTO> getPayTrend(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                  @Param("merchantId") Integer merchantId, @Param("type") Integer type);

    /**
     * 获取账户流水详情
     *
     * @param managerAccountRecordDTO 查询条件
     * @return
     */
    List<ManagerCapitalFlowDTO> getListAccountAllRecord(ManagerAccountRecordDTO managerAccountRecordDTO);

    /**
     * 获取账户入账详情
     *
     * @param managerAccountingDTO 查询条件
     * @return
     */
    List<ManagerCapitalFlowDTO> getListAccountingRecord(ManagerAccountingDTO managerAccountingDTO);

    /**
     * 获取资金流水
     * @param merchantId
     * @param pageSize
     * @param startIndex
     * @param startDate
     * @param endDate
     * @param orderSerialNum
     * @param type
     * @return
     */
    List<MerchantCapitalFlowDTO> getCapitalFlow(@Param("merchantId") Integer merchantId,
                                                @Param("pageSize") Integer pageSize,
                                                @Param("startIndex") Integer startIndex,
                                                @Param("startDate") String startDate,
                                                @Param("endDate") String endDate,
                                                @Param("orderSerialNum") String orderSerialNum,
                                                @Param("type") Integer type);

    /**
     * 计数
     * @param merchantId
     * @param startDate
     * @param endDate
     * @param orderSerialNum
     * @param type
     * @return
     */
    int countCapitalFlow(@Param("merchantId") Integer merchantId, @Param("startDate") String startDate,
                         @Param("endDate") String endDate, @Param("orderSerialNum") String orderSerialNum,
                         @Param("type") Integer type);


    /**
     * 获取返点流水
     * 充值类型尚不明确
     * @param  managerReturnPointQueryDTO
     * @return 返点资金流水
     */
    List<MerchantReturnPointListDTO> getBackFlow(ManagerReturnPointQueryDTO managerReturnPointQueryDTO);

    /**
     * 商户后台获取返点流水
     *
     * @param merchantId
     * @param pageSize
     * @param startIndex
     * @param startDate
     * @param endDate
     * @param status
     * @param type       交易类型
     * @return
     */
    List<MerchantReturnPointListDTO> getMerchantBackFlow(@Param("merchantId") Integer merchantId,
                                                         @Param("pageSize") Integer pageSize,
                                                         @Param("startIndex") Integer startIndex,
                                                         @Param("startDate") String startDate,
                                                         @Param("endDate") String endDate,
                                                         @Param("status") Integer status,
                                                         @Param("type") Integer type);

    /**
     * 计数
     *
     * @param merchantId
     * @param startDate
     * @param endDate
     * @param status
     * @param type
     * @return
     */
    int countBackFlow(@Param("merchantId") Integer merchantId, @Param("startDate") String startDate,
                      @Param("endDate") String endDate, @Param("status") Integer status, @Param("type") Integer type);

    /**
     * 获得商户payment_type为空的资金流水
     * @return
     */
    List<MerchantAccountDetail> getIsNullMerchantAccountDetail();
}