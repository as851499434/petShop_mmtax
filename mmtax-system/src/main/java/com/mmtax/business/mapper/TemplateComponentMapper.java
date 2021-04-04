package com.mmtax.business.mapper;

import com.mmtax.business.domain.TemplateComponent;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 签约模板控件 数据层
 * 
 * @author meimiao
 * @date 2020-07-30
 */
@Repository
public interface TemplateComponentMapper extends MyMapper<TemplateComponent>
{
    List<TemplateComponent> selectByEsignTemId(@Param("esignTemId") Integer esignTemId);
}