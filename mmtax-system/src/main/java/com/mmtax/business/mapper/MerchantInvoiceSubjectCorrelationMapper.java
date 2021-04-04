package com.mmtax.business.mapper;

import com.mmtax.business.domain.MerchantInvoiceSubjectCorrelation;
import com.mmtax.business.yunzbdto.YunZBInvoiceContentResultDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 商户和发票科目关联 数据层
 * 
 * @author meimiao
 * @date 2020-08-24
 */
public interface MerchantInvoiceSubjectCorrelationMapper extends MyMapper<MerchantInvoiceSubjectCorrelation>
{
    List<YunZBInvoiceContentResultDTO> getInvoiceServiceName(@Param("merchantId")Integer merchantId);

    /**
     * 将该商户所有的发票 是否默认改为 否 0
     * @param merchantId
     * @param newIsDefault
     * @return
     */
    int updateInvoiceIsDefaultAll(@Param("merchantId")Integer merchantId,
                                  @Param("newIsDefault")Integer newIsDefault,
                                  @Param("updateTime") Date updateTime);


    /**
     *  将商户 某个发票内容  是否默认 从一种状态改到另一种状态
     * @param merchantId
     * @param invoiceSubjectId
     * @param newIsDefault
     * @return
     */
    int updateInvoiceIsDefault(@Param("merchantId")Integer merchantId,
                               @Param("invoiceSubjectId")Integer invoiceSubjectId,
                               @Param("newIsDefault")Integer newIsDefault,
                               @Param("updateTime") Date updateTime);


}