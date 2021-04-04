package com.mmtax.business.service;

import com.mmtax.business.domain.ContractInfo;
import com.mmtax.business.domain.CustomerEsignInfo;

import java.util.List;

/**
 * 签约文件 服务层
 * 
 * @author meimiao
 * @date 2020-07-29
 */
public interface IContractInfoService
{
    ContractInfo createContractInfo(Integer esignTemplateId, CustomerEsignInfo customerEsignInfo
            ,Integer merchantId,Integer customerId);
}
