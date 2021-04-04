package com.mmtax.business.mapper;

import com.mmtax.business.domain.InvoiceFail;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 发票重开 数据层
 *
 * @author meimiao
 * @date 2019-08-05
 */
public interface InvoiceFailMapper extends MyMapper<InvoiceFail> {
    /**
     * 依据发票ID获取发票重开表记录信息
     *
     * @param invoiceId
     * @return
     */
    InvoiceFail getRestartInfoByInvoiceId(@Param("invoiceId") Integer invoiceId);

    /**
     * 发票审核更新
     *
     * @param invoiceId
     * @param status    0-驳回 1-审核通过
     * @return
     */
    int updateRestartInfoByInvoiceId(@Param("invoiceId") Integer invoiceId,
                                     @Param("status") Integer status);

    /**
     * 移除重开发票信息
     *
     * @param invoiceId
     * @return
     */
    int removeInvoiceFailInfoByInvoiceId(Integer invoiceId);

}