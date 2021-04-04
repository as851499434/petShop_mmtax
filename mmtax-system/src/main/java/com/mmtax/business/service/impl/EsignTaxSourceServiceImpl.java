package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.mmtax.business.domain.ContractInfo;
import com.mmtax.business.domain.CustomerEsignInfo;
import com.mmtax.business.domain.EsignFlow;
import com.mmtax.business.domain.EsignTaxSource;
import com.mmtax.business.dto.TaxSourceAccountDTO;
import com.mmtax.business.mapper.CustomerEsignInfoMapper;
import com.mmtax.business.mapper.EsignTaxSourceMapper;
import com.mmtax.business.service.ICustomerEsignInfoService;
import com.mmtax.business.service.IEsignFieldsService;
import com.mmtax.business.service.IEsignFlowService;
import com.mmtax.common.enums.AutoSignStatusEnum;
import com.mmtax.common.enums.CustomerEsignStatusEnum;
import com.mmtax.common.enums.SealStatusEnum;
import com.mmtax.common.enums.SignPersonIdTypeEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.MerchantIdUtil;
import com.mmtax.common.utils.esign.comm.CacheKeyConstant;
import com.mmtax.common.utils.esign.domain.request.CommonRequest;
import com.mmtax.common.utils.esign.helper.AccountHelper;
import com.mmtax.common.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IEsignTaxSourceService;

/**
 * 机构(e签宝) 服务层实现
 * 
 * @author meimiao
 * @date 2020-08-05
 */
@Service
public class EsignTaxSourceServiceImpl implements IEsignTaxSourceService
{
    @Autowired
    private CustomerEsignInfoMapper customerEsignInfoMapper;
    @Autowired
    private ICustomerEsignInfoService customerEsignInfoService;
    @Autowired
    private EsignTaxSourceMapper esignTaxSourceMapper;
    @Autowired
    private IEsignFieldsService esignFieldsService;
    @Autowired
    private IEsignFlowService esignFlowService;

    @Override
    public void createTaxSourceAccount(TaxSourceAccountDTO dto) {
        CustomerEsignInfo customerEsignInfo = customerEsignInfoMapper.selectByIdCardAndTaxSourceId(
                dto.getPersonIdNumber(),dto.getTaxSourceId());
        if(null == customerEsignInfo){
            customerEsignInfo = new CustomerEsignInfo();
            customerEsignInfo.setThirdPartyUserId(MerchantIdUtil.getMerchantId());
            customerEsignInfo.setName(dto.getPersonName());
            customerEsignInfo.setIdType(SignPersonIdTypeEnum.CHIDCARD.getCode());
            customerEsignInfo.setIdNumber(dto.getPersonIdNumber());
            customerEsignInfo.setMobile(dto.getMobile());
            customerEsignInfo.setTaxSourceId(dto.getTaxSourceId());
            customerEsignInfo.setStatus(CustomerEsignStatusEnum.NOOPEN.getCode());
            customerEsignInfo.setSealStatus(SealStatusEnum.NOCREATE.getCode());
            customerEsignInfo.setMerchantId(0);
            customerEsignInfo.setCreateTime(DateUtil.date());
            customerEsignInfo.setUpdateTime(DateUtil.date());
            customerEsignInfoMapper.insertSelective(customerEsignInfo);
        }
        if(!CustomerEsignStatusEnum.OPEN.getCode().equals(customerEsignInfo.getStatus())){
            customerEsignInfo = customerEsignInfoService.createEsignAccount(customerEsignInfo,null);
        }
        //创建机构账号
        EsignTaxSource esignTaxSource = esignTaxSourceMapper.selectByTaxSourceId(dto.getTaxSourceId());
        if(null == esignTaxSource){
            esignTaxSource = new EsignTaxSource();
            esignTaxSource.setThirdPartyUserId(MerchantIdUtil.getMerchantId());
            esignTaxSource.setName(dto.getName());
            esignTaxSource.setIdType("CRED_ORG_USCC");
            esignTaxSource.setIdNumber(dto.getIdNumber());
            esignTaxSource.setCusEsignId(customerEsignInfo.getId());
            esignTaxSource.setAccountId(customerEsignInfo.getAccountId());
            esignTaxSource.setTaxSourceId(dto.getTaxSourceId());
            esignTaxSource.setStatus(CustomerEsignStatusEnum.NOOPEN.getCode());
            esignTaxSource.setSealStatus(SealStatusEnum.NOCREATE.getCode());
            esignTaxSource.setAutoSignStatus(AutoSignStatusEnum.NOAUTOSIGN.getCode());
            esignTaxSource.setCreateTime(DateUtil.date());
            esignTaxSource.setUpdateTime(DateUtil.date());
            esignTaxSourceMapper.insertSelective(esignTaxSource);
        }
        if(!CustomerEsignStatusEnum.OPEN.getCode().equals(esignTaxSource.getStatus())) {
            esignFlowService.setTokenToRedis();
            CommonRequest req = new CommonRequest();
            try {
                req = AccountHelper.createOrgAcct(esignTaxSource.getThirdPartyUserId(), esignTaxSource.getAccountId()
                        , esignTaxSource.getName(), esignTaxSource.getIdType(), esignTaxSource.getIdNumber());
            } catch (Exception e) {
                req.setCode(999);
                req.setMessage(e.getMessage());
            }
            if (0 != req.getCode()) {
                esignTaxSource.setComment("机构" + esignTaxSource.getName() + "开户失败：" + req.getMessage());
            } else {
                JSONObject data = req.getData();
                esignTaxSource.setOrgId(data.getString("orgId"));
                esignTaxSource.setStatus(CustomerEsignStatusEnum.OPEN.getCode());
            }
            esignTaxSourceMapper.updateByPrimaryKeySelective(esignTaxSource);
        }
        if(CustomerEsignStatusEnum.OPEN.getCode().equals(esignTaxSource.getStatus())
                && AutoSignStatusEnum.NOAUTOSIGN.getCode().equals(esignTaxSource.getAutoSignStatus())){
            esignFlowService.setTokenToRedis();
            //设置静默签署
            CommonRequest req = new CommonRequest();
            try {
                req = AccountHelper.setAutoSign(esignTaxSource.getOrgId(),null);
            }catch (Exception e){
                req.setCode(999);
                req.setMessage("设置静默签署失败");
            }
            esignTaxSource.setAutoSignStatus(AutoSignStatusEnum.AUTOSIGNSUCCESS.getCode());
            if(0 != req.getCode()) {
                esignTaxSource.setAutoSignStatus(AutoSignStatusEnum.NOAUTOSIGN.getCode());
                esignTaxSource.setComment(req.getMessage());
            }
            esignTaxSource.setUpdateTime(DateUtil.date());
            esignTaxSourceMapper.updateByPrimaryKeySelective(esignTaxSource);
        }
//        RedisUtil.remove(CacheKeyConstant.TOKEN);
    }

    @Override
    public void deleteTaxSourceAccount(TaxSourceAccountDTO dto) {
        //注销机构账号
        EsignTaxSource esignTaxSource = esignTaxSourceMapper.selectByTaxSourceId(dto.getTaxSourceId());
        if(null == esignTaxSource){
            throw new BusinessException("该机构账号不存在，无法注销");
        }
        if(!CustomerEsignStatusEnum.OPEN.getCode().equals(esignTaxSource.getStatus())) {
            throw new BusinessException("该机构账号未开通，无法注销");
        }
        esignFlowService.setTokenToRedis();
        CommonRequest req = new CommonRequest();
        try {
            req = AccountHelper.delOrgAcctByOrgId(esignTaxSource.getOrgId());
        } catch (Exception e) {
            req.setCode(999);
            req.setMessage(e.getMessage());
        }
        if (0 != req.getCode()) {
            throw new BusinessException("机构注销失败:" + req.getMessage());
        } else {
            esignTaxSource.setStatus(CustomerEsignStatusEnum.LOGOUT.getCode());
            esignTaxSource.setTaxSourceId(-1);
        }
        esignTaxSource.setUpdateTime(DateUtil.date());
        esignTaxSourceMapper.updateByPrimaryKeySelective(esignTaxSource);

        //注销法人账号
        CustomerEsignInfo customerEsignInfo = customerEsignInfoMapper.selectByPrimaryKey(esignTaxSource.getCusEsignId());
        if(null == customerEsignInfo){
            throw new BusinessException("该法人账号不存在，无法注销");
        }
        if(!CustomerEsignStatusEnum.OPEN.getCode().equals(customerEsignInfo.getStatus())){
            throw new BusinessException("该法人账号未开通，无法注销");
        }
        customerEsignInfoService.deleteEsignAccount(customerEsignInfo);

//        RedisUtil.remove(CacheKeyConstant.TOKEN);
    }

    @Override
    public JSONObject queryEsignAccountInfo(String accountId, String orgId) {
        esignFlowService.setTokenToRedis();
        CommonRequest req = new CommonRequest();
        try {
            if(null != accountId) {
                req = AccountHelper.qryPersonAcctByAcctId(accountId);
            }else{
                req = AccountHelper.qryOrgAcctByOrgId(orgId);
            }
        } catch (Exception e) {
            req.setCode(999);
            req.setMessage(e.getMessage());
        }
        if (0 != req.getCode()) {
            throw new BusinessException("查询失败:" + req.getMessage());
        }
        return req.getData();
    }

    @Override
    public void createFieldsOfSource(EsignFlow esignFlow, ContractInfo contractInfo) {
        EsignTaxSource esignTaxSource =  esignTaxSourceMapper.selectByTaxSourceId(esignFlow.getTaxSourceId());
        if(null == esignTaxSource){
            throw new BusinessException("对应税源地的公章还未创建");
        }
        esignFieldsService.createFieldsAuto(esignFlow,contractInfo,false);
    }

    @Override
    public void updateTaxSourceAccount(TaxSourceAccountDTO dto) {
        EsignTaxSource esignTaxSource = esignTaxSourceMapper.selectByTaxSourceId(dto.getTaxSourceId());
        if(null == esignTaxSource){
            throw new BusinessException("未查询到该税源机构账户");
        }
        esignFlowService.setTokenToRedis();
        CommonRequest req = new CommonRequest();
        try {
            req = AccountHelper.updateOrgAcctByOrgId(esignTaxSource.getOrgId(),dto.getName());
        } catch (Exception e) {
            req.setCode(999);
            req.setMessage(e.getMessage());
        }
        if (0 != req.getCode()) {
            throw new BusinessException("修改失败:" + req.getMessage());
        }
        esignTaxSource.setName(dto.getName());
        esignTaxSource.setUpdateTime(DateUtil.date());
        esignTaxSourceMapper.updateByPrimaryKeySelective(esignTaxSource);
    }
}
