package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.ContractInfo;
import com.mmtax.business.domain.EsignFlow;
import com.mmtax.business.domain.EsignFlowDoc;
import com.mmtax.business.mapper.EsignFlowDocMapper;
import com.mmtax.business.service.IEsignFlowDocService;
import com.mmtax.business.service.IEsignFlowService;
import com.mmtax.common.utils.esign.comm.CacheKeyConstant;
import com.mmtax.common.utils.esign.domain.request.CommonRequest;
import com.mmtax.common.utils.esign.helper.SignHelper;
import com.mmtax.common.utils.esign.helper.TokenHelper;
import com.mmtax.common.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 签约流程文档关联 服务层实现
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Service
public class EsignFlowDocServiceImpl implements IEsignFlowDocService
{
    @Autowired
    private EsignFlowDocMapper esignFlowDocMapper;
    @Autowired
    private IEsignFlowService esignFlowService;

    @Override
    public CommonRequest addFlowFile(EsignFlow esignFlow, ContractInfo contractInfo) {
        EsignFlowDoc esignFlowDoc = new EsignFlowDoc();
        esignFlowDoc.setEsignFlowId(esignFlow.getId());
        esignFlowDoc.setFlowId(esignFlow.getFlowId());
        esignFlowDoc.setContractInfoId(contractInfo.getId());
        esignFlowDoc.setFileId(contractInfo.getFileId());
        esignFlowDoc.setEncryption(0);
        esignFlowDoc.setCreateTime(DateUtil.date());
        esignFlowDoc.setUpdateTime(DateUtil.date());
        esignFlowDocMapper.insertSelective(esignFlowDoc);
        esignFlowService.setTokenToRedis();
        return SignHelper.addFlowDoc(esignFlow.getFlowId(),contractInfo.getFileId());
    }
}
