package com.mmtax.business.mapper;

import com.mmtax.business.domain.InvoiceApplyAmount;
import com.mmtax.business.dto.InvoiceAmountServiceDetailDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 发票申请金额 数据层
 *
 * @author meimiao
 * @date 2019-08-09
 */
public interface InvoiceApplyAmountMapper extends MyMapper<InvoiceApplyAmount> {
    /**
     * 根据发票id获取金额详情
     *
     * @param invoiceId
     * @return
     */
    List<InvoiceAmountServiceDetailDTO> getInvoiceApplyAmountByInvoiceId(@Param("invoiceId") Integer invoiceId);

    /**
     * 删除所有该条申请下的服务税费等
     *
     * @param invoiceApplyId
     * @return
     */
    int removeInvoiceAmountInfo(@Param("invoiceApplyId") Integer invoiceApplyId);

}