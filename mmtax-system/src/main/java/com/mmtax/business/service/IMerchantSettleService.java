package com.mmtax.business.service;

import com.mmtax.business.dto.ManagerSettleMentInfoDTO;
import com.mmtax.business.dto.MerchantBalanceDetailDTO;
import com.mmtax.common.page.Page;

import java.util.List;

/**
 * 结算管理服务层接口
 * @author yuanligang
 * @date 2019/7/23
 */
public interface IMerchantSettleService {

    /**
     * 结算管理--余额日账单--批量打款记录
     * @param merchantId 商户ID
     * @param pageSize 每页大小
     * @param currentPage 当前页
     * @param startDate 起始日期
     * @param endDate 结束日期
     * @param free 服务费是否为0
     * @return
     */
    Page listBatchPaymentRecord(Integer merchantId, Integer pageSize, Integer currentPage,
                                String startDate, String endDate, Integer free);


    /**
     * 根据批量打款ID获取余额账单详情
     * @param batchPaymentRecordId 批量打款ID
     * @param pageSize 每页大小
     * @param currentPage 当前页面
     * @return
     */
    Page listTrxOrderByBatchId(Integer batchPaymentRecordId, Integer pageSize, Integer currentPage);

    /**
     * 导出日结账单详情
     *
     * @param batchPaymentRecordId 订单id
     * @return
     */
    List<MerchantBalanceDetailDTO> exportList(Integer batchPaymentRecordId);


    /**
     * 获取结算信息列表
     *
     * @param dto 参数
     * @return
     */
    List<ManagerSettleMentInfoDTO> getListManagersettleMentInfo(ManagerSettleMentInfoDTO dto);
}
