package com.mmtax.business.mapper;

import com.mmtax.business.domain.EsignInfo;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 易签宝税源地关联 数据层
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Repository
public interface EsignInfoMapper extends MyMapper<EsignInfo>
{
    EsignInfo selectByAppId(@Param("appId") String appId);
}