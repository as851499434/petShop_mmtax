package com.mmtax.business.mapper;

import com.mmtax.business.domain.MerchantAccountConfig;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 账户配置 数据层
 *
 * @author meimiao
 * @date 2019-07-16
 */
public interface MerchantAccountConfigMapper extends MyMapper<MerchantAccountConfig> {

    /**
     * 获取商户个人账户配置信息
     * @param merchantId 商户ID
     * @return
     */
    MerchantAccountConfig getMerchantConfigByMerchantId(@Param("merchantId") int merchantId);

}