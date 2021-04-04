package com.mmtax.business.mapper;

import com.mmtax.business.domain.BatchPaymentRecord;
import com.mmtax.business.dto.*;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 批量打款记录 数据层
 * 
 * @author meimiao
 * @date 2019-07-09
 */
@Repository
public interface BatchPaymentRecordMapper extends MyMapper<BatchPaymentRecord>
{

    /**
     * 获取商户批量代付记录
     *
     * @param startDate      查询起始时间
     * @param endDate        查询结束时间
     * @param status         状态
     * @param batchNo        批次号
     * @param paymentChannel 代付通道
     * @param merchantId     商户id
     * @param startIndex     起始位置
     * @param pageSize       每页大小
     * @return
     */
    List<MerchantBatchPaymentRecordDTO> getListMerchantPaymentRecord(@Param("startDate") String startDate,
                                                                     @Param("endDate") String endDate,
                                                                     @Param("status") Integer status,
                                                                     @Param("batchNo") String batchNo,
                                                                     @Param("paymentChannel") String paymentChannel,
                                                                     @Param("merchantId") Integer merchantId,
                                                                     @Param("startIndex") Integer startIndex,
                                                                     @Param("pageSize") Integer pageSize);


    /**
     * 余额日账单--批量代付记录
     *
     * @param startDate      查询起始时间
     * @param endDate        查询结束时间
     * @param merchantId     商户id
     * @param pageSize       每页大小
     * @param startIndex     起始位置
     * @param free           服务费是否为0
     * @return
     */
    List<MerchantBalanceDTO> getListBalancePaymentRecord(@Param("merchantId") Integer merchantId,
                                                         @Param("pageSize") Integer pageSize,
                                                         @Param("startIndex") Integer startIndex,
                                                         @Param("startDate") String startDate,
                                                         @Param("endDate") String endDate,
                                                         @Param("free") Integer free);

    /**
     * 计数
     * @param merchantId 商户ID
     * @param startDate 起始日期
     * @param endDate 结束日期
     * @param free 服务费是否为0
     * @return
     */
    int getCountBalancePaymentRecord(@Param("merchantId") Integer merchantId,@Param("startDate") String startDate,
                                     @Param("endDate") String endDate, @Param("free") Integer free);


    /**
     * 余额账单详情
     * @param batchPaymentRecordId 余额账单
     * @param pageSize 每页大小
     * @param startIndex 当前页码
     * @return
     */
    List<MerchantBalanceDetailDTO> getListBalancePaymentRecordDetail(@Param("batchPaymentRecordId")
                                                                            Integer batchPaymentRecordId,
                                                                    @Param("pageSize") Integer pageSize,
                                                                    @Param("startIndex") Integer startIndex);

    /**
     * 计数
     * @param batchPaymentRecordId 记录ID
     * @return
     */
    int getCountBalancePaymentRecordDetail(@Param("batchPaymentRecordId") Integer batchPaymentRecordId);


    /**
     * 获取金额合计
     *
     * @param recordId
     * @return
     */
    RecordNumberDTO getTotalRecord(@Param("recordId") Integer recordId);









    /**
     * 获取商户批量代付记录数量
     *
     * @param startDate      查询起始时间
     * @param endDate        查询结束时间
     * @param status         状态
     * @param batchNo        批次号
     * @param paymentChannel 代付通道
     * @param merchantId     商户id
     * @return
     */
    int getCountMerchantPaymentRecord(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                      @Param("status") Integer status, @Param("batchNo") String batchNo,
                                      @Param("paymentChannel") String paymentChannel,
                                      @Param("merchantId") Integer merchantId);

    /**
     * 获取批量代付列表
     *
     * @param dto
     * @return
     */
    List<ManagerBatchPaymentRecordDTO> getListManagerPaymentRecord(ManagerBatchPaymentRecordDTO dto);


    /**
     * 获取批量代付详情
     *
     * @param recordId 记录id
     * @return
     */
    List<MerchantBatchPaymentDetailDTO> getBatchPaymentDetailDTO(@Param("recordId") Integer recordId);

}