package com.mmtax.business.service.impl;

import com.mmtax.business.domain.EsignTemplate;
import com.mmtax.business.mapper.EsignTemplateMapper;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IEsignTemplateService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 签约模板文件 服务层实现
 * 
 * @author meimiao
 * @date 2020-07-30
 */
@Service
public class EsignTemplateServiceImpl implements IEsignTemplateService
{
    @Autowired
    private EsignTemplateMapper esignTemplateMapper;

    @Override
    public List<EsignTemplate> getSignTemplates(Integer taxSourceId) {
        List<EsignTemplate> esignTemplates = esignTemplateMapper.selectByTaxSourceId(taxSourceId);
        return esignTemplates.stream().filter(e -> e.getId() < 6).collect(Collectors.toList());
    }
}
