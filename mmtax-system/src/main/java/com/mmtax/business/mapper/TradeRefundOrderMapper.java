package com.mmtax.business.mapper;

import com.mmtax.business.domain.TradeRefundOrder;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 退款记录 数据层
 * 
 * @author meimiao
 * @date 2020-09-18
 */
@Repository
public interface TradeRefundOrderMapper extends MyMapper<TradeRefundOrder>
{
    TradeRefundOrder selectByRefundSerialNum(@Param("refundSerialNum") String refundSerialNum);

    List<TradeRefundOrder> selectByAysncStatus(@Param("asyncStatus") Integer asyncStatus);
}