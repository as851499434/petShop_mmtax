package com.mmtax.business.mapper;

import com.mmtax.business.domain.EsignTaxSource;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 机构(e签宝) 数据层
 * 
 * @author meimiao
 * @date 2020-08-05
 */
@Repository
public interface EsignTaxSourceMapper extends MyMapper<EsignTaxSource>
{
    EsignTaxSource selectByTaxSourceId(@Param("taxSourceId") Integer taxSourceId);

    EsignTaxSource selectByOrgId(@Param("orgId") String orgId);
}