package com.mmtax.business.mapper;

import com.mmtax.business.domain.PaymentMerchantInfo;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 代付平台商户 数据层
 *
 * @author meimiao
 * @date 2019-07-10
 */
public interface PaymentMerchantInfoMapper extends MyMapper<PaymentMerchantInfo> {
    /**
     * 根据商户id以及通道名称查询平台商户信息
     *
     * @param merchantId 商户id
     * @param name       代付通道名称
     * @return
     */
    PaymentMerchantInfo selectByMerchantIdAndChannel(@Param("merchantId") Integer merchantId,
                                                     @Param("name") String name);
}