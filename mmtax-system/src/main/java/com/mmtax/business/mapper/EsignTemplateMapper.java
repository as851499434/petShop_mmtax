package com.mmtax.business.mapper;

import com.mmtax.business.domain.EsignTemplate;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 签约模板文件 数据层
 * 
 * @author meimiao
 * @date 2020-07-30
 */
@Repository
public interface EsignTemplateMapper extends MyMapper<EsignTemplate>
{
    List<EsignTemplate> selectByTaxSourceId(@Param("taxSourceId") Integer taxSourceId);
}