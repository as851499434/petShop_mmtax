package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.ApiSignStatusDTO;
import com.mmtax.business.dto.NotifySignReqDTO;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.INotifySendAgainService;
import com.mmtax.business.service.IPersonalMerProtocolService;
import com.mmtax.common.chanpay.BaseConstant;
import com.mmtax.common.enums.ExpireStatusEnum;
import com.mmtax.common.enums.ResultEnum;
import com.mmtax.common.enums.SignStatusEnum;
import com.mmtax.common.utils.MmtaxSign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.ICustomerProtocolService;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 员工协议 服务层实现
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Service
@Slf4j
public class CustomerProtocolServiceImpl implements ICustomerProtocolService
{
    @Autowired
    private CustomerProtocolMapper customerProtocolMapper;
    @Autowired
    private CustomerEsignInfoMapper customerEsignInfoMapper;
    @Autowired
    private EsignFlowDocMapper esignFlowDocMapper;
    @Autowired
    private EsignTaxSourceMapper esignTaxSourceMapper;
    @Autowired
    private OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Autowired
    private MerchantKeyMapper merchantKeyMapper;
    @Autowired
    private MerchantInfoMapper merchantInfoMapper;
    @Autowired
    private INotifySendAgainService notifySendAgainService;
    @Autowired
    CustomerInfoMapper customerInfoMapper;
    @Autowired
    CusLinkMerInfoMapper cusLinkMerInfoMapper;
    @Autowired
    IPersonalMerProtocolService personalMerProtocolService;

    @Override
    public void dealNotify(JSONObject reqJson) {
        String flowId = reqJson.getString("flowId");
        String accountId = reqJson.getString("accountId");
        EsignTaxSource esignTaxSource = esignTaxSourceMapper.selectByOrgId(accountId);
        if(null != esignTaxSource){
            log.info("属于税源地自动签署的异步通知，不予理会");
            return;
        }
        Integer signResult = reqJson.getInteger("signResult");
        String signTime = reqJson.getString("signTime");
        String resultDescription = reqJson.getString("resultDescription");
        EsignFlowDoc esignFlowDoc = esignFlowDocMapper.selectByFileIdAndFlowId(flowId,null);
        //处理个体工商户的签约
        personalMerProtocolService.handNotify(flowId,accountId,signResult,signTime,resultDescription);

        CustomerProtocol customerProtocol = customerProtocolMapper.selectByConInfoIdAndAccountId(
                esignFlowDoc.getContractInfoId(),accountId);
        if (null == customerProtocol) {
            log.info("未找到对应accountId={}的签署协议记录",accountId);
            return;
        }
        customerProtocol.setSignStatus(signResult);
        customerProtocol.setSignTime(DateUtil.parseDateTime(signTime));
//        if(!SignStatusEnum.REFUSE.getCode().equals(signResult)) {
            customerProtocol.setComment(resultDescription);
//        }
        customerProtocol.setUpdateTime(DateUtil.date());
        customerProtocolMapper.updateByPrimaryKeySelective(customerProtocol);
        //发送异步通知
        try {
            notifySendAgainService.sendSignNotify(customerProtocol);
        }catch (Exception ignored){

        }
    }

    @Override
    public void dealDocExpire(JSONObject reqJson) {
        String flowId = reqJson.getString("flowId");
        String[] fileIds = reqJson.getString("fileId").split(",");
        for(String fileId:fileIds){
            EsignFlowDoc esignFlowDoc = esignFlowDocMapper.selectByFileIdAndFlowId(flowId,fileId);
            personalMerProtocolService.dealDocExpire(flowId);
            CustomerProtocol customerProtocol = customerProtocolMapper.selectByConInfoIdAndAccountId(
                    esignFlowDoc.getContractInfoId(),null);
            if(null != customerProtocol){
                customerProtocol.setExpireStatus(ExpireStatusEnum.EXPIRE.getCode());
                customerProtocol.setUpdateTime(DateUtil.date());
                customerProtocolMapper.updateByPrimaryKeySelective(customerProtocol);
            }
        }
    }

    @Override
    public boolean judgeSign(String idCardNo,Integer merchantId) {
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
        Integer taxSourceId = onlinePaymentInfo.getTaxSourceCompanyId();
        CustomerEsignInfo customerEsignInfo = customerEsignInfoMapper.selectByIdCardAndTaxSourceId(idCardNo,taxSourceId);
        if(null == customerEsignInfo){
            return false;
        }
        CustomerProtocol customerProtocol = customerProtocolMapper.selectByAccountId(
                customerEsignInfo.getAccountId(),merchantId);
        if(null == customerProtocol){
            return false;
        }
        if(!SignStatusEnum.SUCCESS.getCode().equals(customerProtocol.getSignStatus())){
            return false;
        }
        if(!ExpireStatusEnum.NOEXPIRE.getCode().equals(customerProtocol.getExpireStatus())){
            return false;
        }
        return true;
    }

    @Override
    public boolean haveSign(Integer customerId) {
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerId);
        CusLinkMerInfo queryCus = new CusLinkMerInfo();
        queryCus.setCustomerId(customerId);
        List<CusLinkMerInfo> cusLinkMerInfos = cusLinkMerInfoMapper.select(queryCus);
        boolean haveSign = true;
        for(CusLinkMerInfo one:cusLinkMerInfos){
            boolean sign = judgeSign(customerInfo.getCertificateNo(),one.getMerchantId());
            if (!sign) {
                return sign;
            }
        }
        return haveSign;
    }

    @Override
    public ApiSignStatusDTO queryNotifySignStatus(NotifySignReqDTO dto) {
        String data = JSON.toJSONString(dto);
        log.info("入参：{}",data);
        ApiSignStatusDTO resultDTO = new ApiSignStatusDTO();
        MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfoByCode(dto.getMerchantNo());
        if(null == merchantInfo){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("商户不存在");
            return resultDTO;
        }
        if(StringUtils.isEmpty(dto.getQueryOrderId())){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("签约流水号不可为空");
            return resultDTO;
        }
        if(StringUtils.isEmpty(dto.getVersion())){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("版本号不可为空");
            return resultDTO;
        }
        if(StringUtils.isEmpty(dto.getSignType())){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("签名类型不可为空");
            return resultDTO;
        }
        if(StringUtils.isEmpty(dto.getDesKey())){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("秘钥不可为空");
            return resultDTO;
        }
        if(StringUtils.isEmpty(dto.getRequireTime())){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("请求时间不可为空");
            return resultDTO;
        }
        //验签
        MerchantKey merchantKey = merchantKeyMapper.getMerchantKeyByMerchantId(merchantInfo.getId());
        if (!dto.getDesKey().equals(merchantKey.getDesKey())) {
            resultDTO.setCode(ResultEnum.ERROR_APP_KEY.name());
            resultDTO.setDescription(ResultEnum.ERROR_APP_KEY.description);
            return resultDTO;
        }
        Map map = JSON.parseObject(data);
        map.remove("sign");
        map.remove("signType");
        map.remove("desKey");
        map.remove("paymentChannel");
        map.remove("bankCardNo");
        map.remove("name");
        map.remove("accountNo");
        map.remove("idCardNo");
        map.remove("mobileNo");
        map.remove("orderId");
        TreeMap<String, Object> treeMap = new TreeMap<>(map);
        String sign = MmtaxSign.signByMap(merchantKey.getAppKey(), treeMap, BaseConstant.CHARSET);
        resultDTO.setSign(sign);
        //校验签名
//        if (StringUtils.isEmpty(sign) || !sign.equals(dto.getSign())) {
//            resultDTO.setCode(ResultEnum.ILLEGAL_SIGN.name());
//            resultDTO.setDescription(ResultEnum.ILLEGAL_SIGN.description);
//            return resultDTO;
//        }
        CustomerProtocol customerProtocol = customerProtocolMapper.selectByMerchantSerialNum(
                dto.getQueryOrderId(),merchantInfo.getId());
        if(null == customerProtocol){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("未查到该签约记录");
            return resultDTO;
        }
        CustomerEsignInfo customerEsignInfo = customerEsignInfoMapper.selectByPrimaryKey(customerProtocol.getCusEsignId());
        resultDTO.setCode(ResultEnum.SUCCESS.name());
        resultDTO.setDescription(ResultEnum.SUCCESS.description);
        resultDTO.setName(customerEsignInfo.getName());
        resultDTO.setIdCardNo(customerEsignInfo.getIdNumber());
        resultDTO.setMobileNo(customerEsignInfo.getMobile());
        resultDTO.setOrderId(customerProtocol.getMerchantSerialNum());
        Integer signStatus = customerProtocol.getSignStatus();
        if (!StringUtils.isEmpty(customerProtocol.getComment())) {
            resultDTO.setDescription(customerProtocol.getComment());
        }
        if(SignStatusEnum.INIT.getCode().equals(signStatus) || SignStatusEnum.NOSIGN.getCode().equals(signStatus)){
            resultDTO.setStatus("1");
        }else{
            resultDTO.setStatus(customerProtocol.getSignStatus().toString());
        }
        return resultDTO;
    }
}
