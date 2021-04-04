package com.mmtax.business.mapper;

import com.mmtax.business.domain.FactorOrder;
import com.mmtax.business.dto.MerchantFactorListDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 要素认证订单 数据层
 *
 * @author meimiao
 * @date 2019-08-12
 */
public interface FactorOrderMapper extends MyMapper<FactorOrder> {

    /**
     * 获取要素认证订单
     *
     * @param merchantId 商户id
     * @param pageSize   每页大小
     * @param startIndex 起始位置
     * @param startDate  起始时间
     * @param endDate    结束时间
     * @param orderNo    订单编号
     * @return
     */
    List<MerchantFactorListDTO> getListFactorOrder(@Param("merchantId") Integer merchantId,
                                                   @Param("pageSize") Integer pageSize,
                                                   @Param("startIndex") Integer startIndex,
                                                   @Param("startDate") String startDate,
                                                   @Param("endDate") String endDate, @Param("orderNo") String orderNo);

    /**
     * 获取要素认证订单总记录数
     *
     * @param merchantId 商户id
     * @param startDate  起始时间
     * @param endDate    结束时间
     * @param orderNo    订单编号
     * @return
     */
    int getCountFactorOrder(@Param("merchantId") Integer merchantId, @Param("startDate") String startDate,
                            @Param("endDate") String endDate, @Param("orderNo") String orderNo);

}