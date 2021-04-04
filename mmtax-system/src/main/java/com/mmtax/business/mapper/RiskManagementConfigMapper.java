package com.mmtax.business.mapper;

import com.mmtax.business.domain.RiskManagementConfig;
import com.mmtax.common.utils.MyMapper;

/**
 * 风险配置 数据层
 * 
 * @author meimiao
 * @date 2019-07-09
 */
public interface RiskManagementConfigMapper extends MyMapper<RiskManagementConfig>
{
    /**
     * 根据商户ID获取风险配置信息
     *
     * @param merchantId 商户id
     * @return
     */
    RiskManagementConfig getConfigByMerchantId(Integer merchantId);
}