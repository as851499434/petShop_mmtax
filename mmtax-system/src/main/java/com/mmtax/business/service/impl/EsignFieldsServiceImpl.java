package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.*;
import com.mmtax.business.mapper.CustomerEsignInfoMapper;
import com.mmtax.business.mapper.EsignFieldsMapper;
import com.mmtax.business.mapper.EsignTaxSourceMapper;
import com.mmtax.business.service.IEsignFlowService;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.esign.comm.CacheKeyConstant;
import com.mmtax.common.utils.esign.domain.request.CommonRequest;
import com.mmtax.common.utils.esign.domain.signarea.PosBean;
import com.mmtax.common.utils.esign.domain.signarea.Signfield;
import com.mmtax.common.utils.esign.helper.SignHelper;
import com.mmtax.common.utils.esign.helper.TokenHelper;
import com.mmtax.common.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IEsignFieldsService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 签约流程签署区记录 服务层实现
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Service
public class EsignFieldsServiceImpl implements IEsignFieldsService
{
    @Autowired
    private EsignFieldsMapper esignFieldsMapper;
    @Autowired
    private EsignTaxSourceMapper esignTaxSourceMapper;
    @Autowired
    private IEsignFlowService esignFlowService;
    @Autowired
    private CustomerEsignInfoMapper customerEsignInfoMapper;

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void createFields(EsignFlow esignFlow, ContractInfo contractInfo, CustomerEsignInfo customerEsignInfo
            ,String sealType) {
        EsignFields esignFields = commonAction(esignFlow.getId(),esignFlow.getFlowId(),contractInfo);
        esignFields.setCusEsignId(customerEsignInfo.getId());
        esignFields.setSignerAccountId(customerEsignInfo.getAccountId());
        esignFields.setOrderNum(2);
        //根据不同的协议写死
        switch(Integer.parseInt(contractInfo.getReservedFieldTwo())){
            case 3:
            case 4:
            case 5:
                esignFields.setPosPage(3);
                esignFields.setPosX(394f);
                esignFields.setPosY(480f);
//                esignFields.setWidth(55f);
                break;
            case 6:
            case 7:
                esignFields.setPosPage(4);
                esignFields.setPosX(394f);
                esignFields.setPosY(480f);
                break;
            case 8:
                esignFields.setPosPage(11);
                esignFields.setPosX(140f);
                esignFields.setPosY(480f);
                break;
            default:
        }
        esignFieldsMapper.insertSelective(esignFields);
        PosBean posBean = new PosBean(esignFields.getPosPage().toString(),esignFields.getPosX(),esignFields.getPosY()
                ,esignFields.getWidth(),false);
        Signfield signfield = new Signfield(esignFields.getFileId(),esignFields.getSignerAccountId(),null
                ,esignFields.getAuthorizedAccountId(),null
                ,esignFields.getOrderNum(),posBean,sealType,null,esignFields.getThirdOrderNo());
        signfield.setSignDateBeanType(1);
        List<Signfield> signfields = new ArrayList<>();
        signfields.add(signfield);
        esignFlowService.setTokenToRedis();
        CommonRequest req = SignHelper.addSignerHandSignArea(esignFlow.getFlowId(),signfields);
        if(0 != req.getCode()){
            throw new BusinessException(req.getMessage());
        }
    }

    private EsignFields commonAction(Integer esignFlowId,String flowId,ContractInfo contractInfo){
        EsignFields esignFields = new EsignFields();
        esignFields.setEsignFlowId(esignFlowId);
        esignFields.setFlowId(flowId);
        esignFields.setContractInfoId(contractInfo.getId());
        esignFields.setFileId(contractInfo.getFileId());
        esignFields.setThirdOrderNo(ChanPayUtil.generateOutTradeNo());
        esignFields.setCreateTime(DateUtil.date());
        esignFields.setUpdateTime(DateUtil.date());
        return esignFields;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void createFieldsAuto(EsignFlow esignFlow, ContractInfo contractInfo,boolean isCus) {
        EsignFields esignFields = commonAction(esignFlow.getId(),esignFlow.getFlowId(),contractInfo);
        Integer cusEsignId;
        String accountId;
        if(isCus) {
            cusEsignId = esignFlow.getCusEsignId();
            CustomerEsignInfo customerEsignInfo = customerEsignInfoMapper.selectByPrimaryKey(cusEsignId);
            accountId = customerEsignInfo.getAccountId();
            esignFields.setOrderNum(2);
        }else{
            EsignTaxSource esignTaxSource = esignTaxSourceMapper.selectByTaxSourceId(esignFlow.getTaxSourceId());
            cusEsignId = esignTaxSource.getId();
            accountId = esignTaxSource.getOrgId();
            esignFields.setOrderNum(1);
        }
        esignFields.setCusEsignId(cusEsignId);
        esignFields.setAuthorizedAccountId(accountId);
        //根据不同的协议写死
        switch(Integer.parseInt(contractInfo.getReservedFieldTwo())){
            case 3:
            case 4:
            case 5:
                if(isCus) {
                    esignFields.setPosPage(3);
                    esignFields.setPosX(394f);
                    esignFields.setPosY(480f);
                }else {
                    esignFields.setPosPage(3);
                    esignFields.setPosX(140f);
                    esignFields.setPosY(450f);
                }
//                esignFields.setWidth(55f);
                break;
            case 6:
            case 7:
                if(isCus) {
                    esignFields.setPosPage(4);
                    esignFields.setPosX(394f);
                    esignFields.setPosY(480f);
                }else {
                    esignFields.setPosPage(4);
                    esignFields.setPosX(140f);
                    esignFields.setPosY(450f);
                }
                break;
            case 8:
                esignFields.setPosPage(11);
                esignFields.setPosX(394f);
                esignFields.setPosY(500f);
                break;
            default:
        }
        esignFieldsMapper.insertSelective(esignFields);
        PosBean posBean = new PosBean(esignFields.getPosPage().toString(),esignFields.getPosX(),esignFields.getPosY()
                ,esignFields.getWidth(),false);
        Signfield signfield = new Signfield(esignFields.getFileId(),esignFields.getAuthorizedAccountId()
                ,esignFields.getOrderNum(),posBean,null,null,esignFields.getThirdOrderNo());
        signfield.setSignDateBeanType(1);
        List<Signfield> signfields = new ArrayList<>();
        signfields.add(signfield);
        esignFlowService.setTokenToRedis();
        CommonRequest req = SignHelper.addSignerAutoSignArea(esignFlow.getFlowId(),signfields);
        if(0 != req.getCode()){
            throw new BusinessException(req.getMessage());
        }
    }
}
