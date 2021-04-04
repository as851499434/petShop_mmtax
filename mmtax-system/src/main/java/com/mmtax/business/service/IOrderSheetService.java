package com.mmtax.business.service;

import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.MerchantSaveOrderSheetFeedBackDTO;
import com.mmtax.common.page.Page;

/**
 * 调单订单 服务层
 *
 * @author meimiao
 * @date 2019-08-14
 */
public interface IOrderSheetService {

    /**
     * 获取商户端调单记录列表
     *
     * @param merchantId     商户id
     * @param currentPage    页码
     * @param pageSize       每页大小
     * @param startDate      起始时间
     * @param endDate        结束时间
     * @param orderSerialNum 订单流水号
     * @param name           收款户名
     * @param orderNum       调单流水号
     * @param status         状态
     * @param auditResult    审核结论
     * @return
     */
    Page getPageMerchantOrderSheet(Integer merchantId, Integer currentPage, Integer pageSize, String startDate,
                                   String endDate, String orderSerialNum, String name, String orderNum, Integer status,
                                   String auditResult);

    /**
     * 获取调单详情
     *
     * @param trxOrderId 记录id
     * @return
     */
    TrxOrder getDetail(Integer trxOrderId);

    /**
     * 保存反馈信息
     *
     * @param dto 参数
     */
    void saveFeedBackInfo(MerchantSaveOrderSheetFeedBackDTO dto);

}
