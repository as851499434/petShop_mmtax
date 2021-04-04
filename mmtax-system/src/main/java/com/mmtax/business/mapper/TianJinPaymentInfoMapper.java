package com.mmtax.business.mapper;

import com.mmtax.business.domain.TianJinPaymentInfo;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 天津渠道商户 数据层
 * 
 * @author meimiao
 * @date 2020-03-20
 */
public interface TianJinPaymentInfoMapper extends MyMapper<TianJinPaymentInfo>
{
    /**
     * 根据商户id查询天津渠道信息
     * @param merchantId 商户id
     * @return
     */
    TianJinPaymentInfo getTianJinPaymentInfoByMerchantId(@Param("merchantId") Integer merchantId);
}