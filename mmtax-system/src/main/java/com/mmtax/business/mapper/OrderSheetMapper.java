package com.mmtax.business.mapper;

import com.mmtax.business.domain.OrderSheet;
import com.mmtax.business.dto.ManagerOrderSheetDTO;
import com.mmtax.business.dto.MerchantOrderSheetDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 调单订单 数据层
 *
 * @author meimiao
 * @date 2019-08-14
 */
public interface OrderSheetMapper extends MyMapper<OrderSheet> {

    /**
     * 获取商户端调单记录列表
     *
     * @param merchantId     商户id
     * @param startIndex     查询起始位置
     * @param pageSize       每页大小
     * @param startDate      起始时间
     * @param endDate        结束时间
     * @param orderSerialNum 订单流水号
     * @param name           收款户名
     * @param orderNo       调单流水号
     * @param status         状态
     * @param auditResult    审核结论
     * @return result
     */
    List<MerchantOrderSheetDTO> getListMerchantOrderSheet(@Param("merchantId") Integer merchantId,
                                                          @Param("startIndex") Integer startIndex,
                                                          @Param("pageSize") Integer pageSize,
                                                          @Param("startDate") String startDate,
                                                          @Param("endDate") String endDate,
                                                          @Param("orderSerialNum") String orderSerialNum,
                                                          @Param("name") String name, @Param("orderNo") String orderNo,
                                                          @Param("status") Integer status,
                                                          @Param("auditResult") String auditResult);

    /**
     * 获取商户端调单记录列表数量
     *
     * @param merchantId     商户id
     * @param startDate      起始时间
     * @param endDate        结束时间
     * @param orderSerialNum 订单流水号
     * @param name           收款户名
     * @param orderNo       调单流水号
     * @param status         状态
     * @param auditResult    审核结论
     * @return result
     */
    Integer getCountMerchantOrderSheet(@Param("merchantId") Integer merchantId, @Param("startDate") String startDate,
                                       @Param("endDate") String endDate, @Param("orderSerialNum") String orderSerialNum,
                                       @Param("name") String name, @Param("orderNo") String orderNo,
                                       @Param("status") Integer status, @Param("auditResult") String auditResult);

    /**
     * 获取后台调单列表
     *
     * @param dto 参数
     * @return
     */
    List<ManagerOrderSheetDTO> getListOrderSheet(ManagerOrderSheetDTO dto);
}