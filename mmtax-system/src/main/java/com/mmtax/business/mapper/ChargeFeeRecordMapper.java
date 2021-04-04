package com.mmtax.business.mapper;

import com.mmtax.business.domain.ChargeFeeRecord;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 服务费收取记录 数据层
 * 
 * @author meimiao
 * @date 2020-04-27
 */
@Repository
public interface ChargeFeeRecordMapper extends MyMapper<ChargeFeeRecord>
{

    ChargeFeeRecord selectByChargeSerialNum(@Param("chargeSerialNum") String chargeSerialNum);

    List<ChargeFeeRecord> selectByOrderSerialNum(@Param("orderSerialNum") String orderSerialNum);

    List<ChargeFeeRecord> selectByOrderSerialNumStatus(@Param("orderSerialNum") String orderSerialNum,
                                                       @Param("status") String status);

    List<ChargeFeeRecord> selectBystatusAndNeedBuDan(@Param("status") String status, @Param("orderSerialNum")
            String orderSerialNum);

    List<ChargeFeeRecord> selectInitOrProcess(@Param("createTime") String createTime);
}