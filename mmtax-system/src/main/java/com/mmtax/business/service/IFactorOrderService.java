package com.mmtax.business.service;

import com.mmtax.common.page.Page;

/**
 * 要素认证订单 服务层
 *
 * @author meimiao
 * @date 2019-08-12
 */
public interface IFactorOrderService {

    /**
     * 获取要素认证订单
     *
     * @param merchantId  商户id
     * @param pageSize    每页大小
     * @param currentPage 页码
     * @param startDate   起始时间
     * @param endDate     结束时间
     * @param orderNo     订单编号
     * @return
     */
    Page getPageFactorOrder(Integer merchantId, Integer pageSize, Integer currentPage, String startDate,
                            String endDate, String orderNo);

}
