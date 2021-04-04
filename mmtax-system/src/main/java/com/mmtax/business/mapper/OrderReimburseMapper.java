package com.mmtax.business.mapper;

import com.mmtax.business.domain.OrderReimburse;
import com.mmtax.business.dto.ChargeReimburseInfoDTO;
import com.mmtax.business.dto.QueryChargeReimburseInfoDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单服务费补偿 数据层
 * 
 * @author meimiao
 * @date 2020-10-14
 */
@Repository
public interface OrderReimburseMapper extends MyMapper<OrderReimburse>
{
    OrderReimburse selectByOrderSerilNum(@Param("orderSerilNum") String orderSerilNum);

    List<ChargeReimburseInfoDTO> listChargeReimburseInfoDTOs(QueryChargeReimburseInfoDTO chargeReimburseInfoDTO);
}