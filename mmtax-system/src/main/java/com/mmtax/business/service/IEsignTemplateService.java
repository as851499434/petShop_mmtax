package com.mmtax.business.service;

import com.mmtax.business.domain.EsignTemplate;
import java.util.List;

/**
 * 签约模板文件 服务层
 * 
 * @author meimiao
 * @date 2020-07-30
 */
public interface IEsignTemplateService
{
   List<EsignTemplate> getSignTemplates(Integer taxSourceId);
}
