package com.mmtax.business.service;

import com.mmtax.business.domain.ContractInfo;
import com.mmtax.business.domain.EsignFlow;
import com.mmtax.common.utils.esign.domain.request.CommonRequest;

/**
 * 签约流程文档关联 服务层
 * 
 * @author meimiao
 * @date 2020-07-29
 */
public interface IEsignFlowDocService
{
    CommonRequest addFlowFile(EsignFlow esignFlow, ContractInfo contractInfo);

}
