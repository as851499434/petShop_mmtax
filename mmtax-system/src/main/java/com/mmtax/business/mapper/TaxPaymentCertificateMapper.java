package com.mmtax.business.mapper;

import com.mmtax.business.domain.TaxPaymentCertificate;
import com.mmtax.business.dto.ManagerTaxPaymentCertificateDTO;
import com.mmtax.business.dto.MerchantTaxPaymentCertificateDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 完税证明 数据层
 *
 * @author meimiao
 * @date 2019-08-13
 */
public interface TaxPaymentCertificateMapper extends MyMapper<TaxPaymentCertificate> {

    /**
     * 商户端获取完税证明列表
     *
     * @param merchantId 商户id
     * @param startIndex 起始位置
     * @param pageSize   每页大小
     * @param startDate  起始时间
     * @param endDate    结束时间
     * @return
     */
    List<MerchantTaxPaymentCertificateDTO> getListMerchantTaxPaymentCertificate(@Param("merchantId") Integer merchantId,
                                                                                @Param("startIndex") Integer startIndex,
                                                                                @Param("pageSize") Integer pageSize,
                                                                                @Param("startDate") String startDate,
                                                                                @Param("endDate") String endDate);

    /**
     * 商户端获取完税证明总条数
     *
     * @param merchantId 商户id
     * @param startDate  起始时间
     * @param endDate    结束时间
     * @return
     */
    int getCountMerchantTaxPaymentCertificate(@Param("merchantId") Integer merchantId,
                                              @Param("startDate") String startDate,
                                              @Param("endDate") String endDate);

    /**
     * 获取系统后台完税证明列表
     *
     * @param dto 查询参数
     * @return result
     */
    List<ManagerTaxPaymentCertificateDTO> getListManagerTaxPaymentCertificate(ManagerTaxPaymentCertificateDTO dto);

    /**
     * 判断完税证明是否存在
     *
     * @param merchantId 商户id
     * @param startDate  起始时间
     * @param endDate    结束时间
     * @return result
     */
    int checkRecordByMonth(@Param("merchantId") Integer merchantId, @Param("startDate") String startDate,
                           @Param("endDate") String endDate);

}