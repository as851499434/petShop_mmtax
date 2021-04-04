package com.mmtax.business.mapper;

import com.mmtax.business.domain.InvoiceInfo;
import com.mmtax.business.dto.CheckingInvoiceDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 发票 数据层
 * 
 * @author meimiao
 * @date 2019-07-09
 */
public interface InvoiceInfoMapper extends MyMapper<InvoiceInfo> {

    /**
     * 通过商户id查询 发票默认内容
     * @return
     */
    String queryDefaultInvoiceContentBymerchantId(@Param("merchantId") Integer merchantId);

    /**
     *  更新发票默认内容 根据 商户 id
     * @param merchantId 商户 id
     * @param invoiceDefaultContent  发票默认内容
     * @return
     */
    int updateInvoiceDefaultContentBymerchantId(@Param("merchantId") Integer merchantId,
                                                @Param("invoiceDefaultContent") String invoiceDefaultContent);

    /**
     * 核对发票：提供部分信息
     * @param merchantId 商户ID
     * @return
     */
    CheckingInvoiceDTO checkingInvoice(@Param("merchantId") Integer merchantId);
    /**
     * 根据商户ID获取发票信息对应ID
     * @param  merchantId 商户ID
     * @return
     */
    int getInvoiceIdByMerchantId(int merchantId);

    /**
     * 获取开票信息
     * @param merchantId 商户ID
     * @return
     */
    InvoiceInfo getInvoiceInfoByMerchantId(@Param("merchantId") int merchantId);

}