package com.mmtax.business.mapper;

import com.mmtax.business.domain.MakeUpCharge;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 补交服务费总记录 数据层
 * 
 * @author meimiao
 * @date 2020-08-19
 */
@Repository
public interface MakeUpChargeMapper extends MyMapper<MakeUpCharge>
{
    MakeUpCharge selectByOrderSerialSumOrMakeUpSerialNum(@Param("orderSerialSum") String orderSerialSum
            ,@Param("makeUpSerialNum") String makeUpSerialNum);

    List<MakeUpCharge> selectByStatusAndAsycSatus(@Param("status") Integer status,@Param("asycStatus") Integer asycStatus);

    List<MakeUpCharge> selectProcess(@Param("twoDayAgo") String twoDayAgo);

    List<MakeUpCharge> selectInMonthLatest(@Param("customerId") Integer customerId,@Param("merchantId") Integer merchantId
            , @Param("startTime") String startTime,@Param("endTime") String endTime);
}