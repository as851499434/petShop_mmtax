package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.*;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.IEsignFlowService;
import com.mmtax.common.constant.ComponentConstants;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.exception.PaymentException;
import com.mmtax.common.utils.esign.comm.CacheKeyConstant;
import com.mmtax.common.utils.esign.domain.request.CommonRequest;
import com.mmtax.common.utils.esign.helper.FileTemplateHelper;
import com.mmtax.common.utils.esign.helper.TokenHelper;
import com.mmtax.common.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IContractInfoService;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 签约文件 服务层实现
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Service
@Slf4j
public class ContractInfoServiceImpl implements IContractInfoService
{
    @Autowired
    private EsignTemplateMapper esignTemplateMapper;
    @Autowired
    private TemplateComponentMapper templateComponentMapper;
    @Autowired
    private TaxSounrceCompanyMapper taxSounrceCompanyMapper;
    @Autowired
    private ContractInfoMapper contractInfoMapper;
    @Autowired
    private MerchantInfoMapper merchantInfoMapper;
    @Autowired
    private IEsignFlowService esignFlowService;
    @Autowired
    BatchTaskDetailMapper batchTaskDetailMapper;
    @Autowired
    CustomerProtocolMapper customerProtocolMapper;

    @Override
    public ContractInfo createContractInfo(Integer esignTemplateId, CustomerEsignInfo customerEsignInfo
            ,Integer merchantId,Integer customerId) {
        esignFlowService.setTokenToRedis();
        EsignTemplate template = esignTemplateMapper.selectByPrimaryKey(esignTemplateId);
        if(null == template){
            log.info("未找到签约文件模板，请核对");
            throw new BusinessException("未找到签约文件模板，请核对");
        }
        //根据不同签约模板补充不同内容并创建协议
        CommonRequest request = null;
        switch (esignTemplateId){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                request = createBytemplateOne(template,customerEsignInfo,merchantId);
                break;
            case 6:
            case 7:
                request = createBytemplateTwo(template,customerEsignInfo,merchantId,customerId);
                break;
            case 8:
                request = createBytemplateThree(template,customerEsignInfo);
                break;
            default:
        }
        if(request == null){
            log.info("填充模板信息有误，请核对");
            throw new BusinessException("填充模板信息有误，请核对");
        }
        JSONObject data = request.getData();
        ContractInfo contractInfo = new ContractInfo();
        contractInfo.setFileName(data.getString("fileName"));
        contractInfo.setFileId(data.getString("fileId"));
        contractInfo.setReservedFieldOne(data.getString("downloadUrl"));
        contractInfo.setTaxSourceId(customerEsignInfo.getTaxSourceId());
        contractInfo.setReservedFieldTwo(esignTemplateId.toString());
        contractInfo.setCreateTime(DateUtil.date());
        contractInfo.setUpdateTime(DateUtil.date());
        contractInfoMapper.insertSelective(contractInfo);
        return contractInfo;
    }

    /**
     * 测试用的对应模板例子
     * @param template
     * @param customerEsignInfo
     */
    private CommonRequest createBytemplateOne(EsignTemplate template, CustomerEsignInfo customerEsignInfo
            ,Integer merchantId){
        List<TemplateComponent> components = templateComponentMapper.selectByEsignTemId(template.getId());
        Map<String,String> simpleFormFields = new HashMap<>();
        for(TemplateComponent one:components){
            String value = "";
            if(ComponentConstants.APPLYB.equals(one.getComponentName())){
                value = customerEsignInfo.getName();
            }
            if(ComponentConstants.APPLYA.equals(one.getComponentName())){
                TaxSounrceCompany taxSounrceCompany = taxSounrceCompanyMapper.selectByPrimaryKey(
                        customerEsignInfo.getTaxSourceId());
                value = taxSounrceCompany.getTaxSounrceCompanyName();
            }
            if(ComponentConstants.MERCHANTNAME.equals(one.getComponentName())){
                MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);
                value = merchantInfo.getMerchantName();
            }
            if(ComponentConstants.IDCARDNO.equals(one.getComponentName())){
                value =  customerEsignInfo.getIdNumber();
            }
            if(ComponentConstants.TASKONE.equals(one.getComponentName())
                    || ComponentConstants.TASKTWO.equals(one.getComponentName())){
                List<CustomerProtocol> customerProtocols =
                        customerProtocolMapper.selectByCusEsignId(customerEsignInfo.getId());
                if (CollectionUtils.isEmpty(customerProtocols)) {
                    throw new BusinessException("签约灵工"+customerEsignInfo.getId()+"在商户"+merchantId+"未找到签约协议");
                }
                value = customerProtocols.get(0).getPost();
            }
            simpleFormFields.put(one.getComponentId(),value);
        }
        return FileTemplateHelper.createByTemplate(template.getTemplateId(),template.getTemplateName(),simpleFormFields);
    }

    /**
     * 测试用的对应模板例子
     * @param template
     * @param customerEsignInfo
     */
    private CommonRequest createBytemplateTwo(EsignTemplate template, CustomerEsignInfo customerEsignInfo
            ,Integer merchantId,Integer customerId){
        List<TemplateComponent> components = templateComponentMapper.selectByEsignTemId(template.getId());
        Map<String,String> simpleFormFields = new HashMap<>();
        for(TemplateComponent one:components){
            String value = "";
            if(ComponentConstants.APPLYB.equals(one.getComponentName())){
                value = customerEsignInfo.getName();
            }
            if(ComponentConstants.APPLYA.equals(one.getComponentName())){
                TaxSounrceCompany taxSounrceCompany = taxSounrceCompanyMapper.selectByPrimaryKey(
                        customerEsignInfo.getTaxSourceId());
                value = taxSounrceCompany.getTaxSounrceCompanyName();
            }
            if(ComponentConstants.MERCHANTNAME.equals(one.getComponentName())){
                MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);
                value = merchantInfo.getMerchantName();
            }
            if(ComponentConstants.IDCARDNO.equals(one.getComponentName())){
                value =  customerEsignInfo.getIdNumber();
            }
            if(ComponentConstants.TASKONE.equals(one.getComponentName())
                    || ComponentConstants.TASKTWO.equals(one.getComponentName())){
                List<BatchTaskDetail> batchTaskDetails = batchTaskDetailMapper.selectByCustomerIdNewTime(customerId);
                if(CollectionUtils.isEmpty(batchTaskDetails)){
                    throw new PaymentException("灵工"+customerId+"在商户"+merchantId+"下没有接过任务，不予签约");
                }
                value = batchTaskDetails.get(0).getTaskName();
            }
            simpleFormFields.put(one.getComponentId(),value);
        }
        return FileTemplateHelper.createByTemplate(template.getTemplateId(),template.getTemplateName(),simpleFormFields);
    }

    /**
     * 对应模板例子
     * @param template
     * @param customerEsignInfo
     */
    private CommonRequest createBytemplateThree(EsignTemplate template, CustomerEsignInfo customerEsignInfo){
        List<TemplateComponent> components = templateComponentMapper.selectByEsignTemId(template.getId());
        Map<String,String> simpleFormFields = new HashMap<>();
        for(TemplateComponent one:components){
            String value = "";
            if(ComponentConstants.TAXSOURCENAME.equals(one.getComponentName())){
                TaxSounrceCompany taxSounrceCompany = taxSounrceCompanyMapper.selectByPrimaryKey(
                        customerEsignInfo.getTaxSourceId());
                value = taxSounrceCompany.getTaxSounrceCompanyName();
            }
            if(ComponentConstants.IDCARDNO.equals(one.getComponentName())){
                value =  customerEsignInfo.getIdNumber();
            }
            simpleFormFields.put(one.getComponentId(),value);
        }
        return FileTemplateHelper.createByTemplate(template.getTemplateId(),template.getTemplateName(),simpleFormFields);
    }
}
