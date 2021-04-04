package com.mmtax.business.mapper;

import com.mmtax.business.domain.MakeUpChargeDetail;
import com.mmtax.business.dto.ReturnMakeUpChargeOrderDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 补交服务费详细记录 数据层
 * 
 * @author meimiao
 * @date 2020-08-21
 */
@Repository
public interface MakeUpChargeDetailMapper extends MyMapper<MakeUpChargeDetail>
{

    List<MakeUpChargeDetail> selectByMakeUpIdAndOrderStatusNotSuccess(@Param("makeUpId") Integer makeUpId);

    List<MakeUpChargeDetail> selectByMakeUpId(@Param("makeUpId") Integer makeUpId);

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
     * @param makeUpSerialNum    补交流水号
     * @param payeeBankCard     收款账号

     * @return
     */
    List<ReturnMakeUpChargeOrderDTO> listMakeUpOrders(@Param("merchantId") int merchantId,
                                                      @Param("pageSize") Integer pageSize,
                                                      @Param("startIndex") Integer startIndex,
                                                      @Param("startDate") String startDate,
                                                      @Param("endDate") String endDate,
                                                      @Param("payeeName") String payeeName,
                                                      @Param("orderSerialNum") String orderSerialNum,
                                                      @Param("makeUpSerialNum") String makeUpSerialNum,
                                                      @Param("payeeBankCard") String payeeBankCard
    );
    /**
     * 补交订单列表
     *
     * @param merchantId        商户ID
     * @param startDate         起始日期
     * @param endDate           技术日期
     * @param payeeName         收款户名
     * @param orderSerialNum    订单流水号
     * @param makeUpSerialNum    补交流水号
     * @param payeeBankCard     收款账号

     * @return
     */
    int countMakeUpOrders(@Param("merchantId") int merchantId,
                          @Param("startDate") String startDate,
                          @Param("endDate") String endDate,
                          @Param("payeeName") String payeeName,
                          @Param("orderSerialNum") String orderSerialNum,
                          @Param("makeUpSerialNum") String makeUpSerialNum,
                          @Param("payeeBankCard") String payeeBankCard);
}