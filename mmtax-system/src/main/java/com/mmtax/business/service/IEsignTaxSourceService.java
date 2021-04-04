package com.mmtax.business.service;

import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.ContractInfo;
import com.mmtax.business.domain.EsignFlow;
import com.mmtax.business.dto.TaxSourceAccountDTO;

/**
 * 机构(e签宝) 服务层
 * 
 * @author meimiao
 * @date 2020-08-05
 */
public interface IEsignTaxSourceService
{
    void createTaxSourceAccount(TaxSourceAccountDTO dto);

    void deleteTaxSourceAccount(TaxSourceAccountDTO dto);

    JSONObject queryEsignAccountInfo(String accountId, String orgId);

    void createFieldsOfSource(EsignFlow esignFlow, ContractInfo contractInfo);

    void updateTaxSourceAccount(TaxSourceAccountDTO dto);
}
