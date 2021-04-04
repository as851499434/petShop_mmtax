package com.mmtax.business.mapper;

import com.mmtax.business.domain.InvoiceApplyDetail;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 发票申请详情 数据层
 *
 * @author meimiao
 * @date 2019-07-24
 */
public interface InvoiceApplyDetailMapper extends MyMapper<InvoiceApplyDetail> {
    /**
     * 移除所有发票充值关联数据
     *
     * @param rechargeId
     * @return
     */
    int removeInvoiceRecharge(@Param("rechargeId") Integer rechargeId);

    /**
     * 根据发票id获取发票图片
     *
     * @param invoiceId 发票id
     * @return
     */
    List<String> getListInvoiceImg(@Param("invoiceId") Integer invoiceId);
}