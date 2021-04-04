package com.mmtax.business.service;

import com.mmtax.business.domain.ContractInfo;
import com.mmtax.business.domain.CustomerEsignInfo;
import com.mmtax.business.domain.EsignFields;
import com.mmtax.business.domain.EsignFlow;
import com.mmtax.common.utils.esign.domain.request.CommonRequest;

import java.util.List;

/**
 * 签约流程签署区记录 服务层
 * 
 * @author meimiao
 * @date 2020-07-29
 */
public interface IEsignFieldsService
{
    /**
     * 创建签署区
     * @param esignFlow
     * @param contractInfo
     * @param customerEsignInfo
     * @param sealType “0”-手绘签名 “1”-模板印章签名
     */
    void createFields(EsignFlow esignFlow, ContractInfo contractInfo, CustomerEsignInfo customerEsignInfo,String sealType);

    void createFieldsAuto(EsignFlow esignFlow, ContractInfo contractInfo,boolean isCus);
}
