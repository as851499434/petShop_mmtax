package com.mmtax.business.mapper;

import com.mmtax.business.domain.OnlineAccountConfig;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 网商渠道税源地配置 数据层
 * 
 * @author meimiao
 * @date 2020-05-20
 */
@Repository
public interface OnlineAccountConfigMapper extends MyMapper<OnlineAccountConfig>
{
    OnlineAccountConfig selectByTaxSourceId(@Param("taxSourceId") Integer taxSourceId);
}