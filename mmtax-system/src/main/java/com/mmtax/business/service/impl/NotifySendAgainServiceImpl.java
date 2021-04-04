package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.ApiSignStatusDTO;
import com.mmtax.business.mapper.*;
import com.mmtax.common.chanpay.BaseConstant;
import com.mmtax.common.enums.NotifyStatusEnum;
import com.mmtax.common.enums.SignStatusEnum;
import com.mmtax.common.enums.SwitchEnum;
import com.mmtax.common.utils.DateUtils;
import com.mmtax.common.utils.MmtaxSign;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.INotifySendAgainService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 异步通知重发送记录 服务层实现
 * 
 * @author meimiao
 * @date 2020-06-01
 */
@Service
@Slf4j
public class NotifySendAgainServiceImpl implements INotifySendAgainService
{
    @Resource
    private NotifySendAgainMapper notifySendAgainMapper;
    @Resource
    private MerchantKeyMapper merchantKeyMapper;
    @Autowired
    private CustomerEsignInfoMapper customerEsignInfoMapper;
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Autowired
    private CusLinkMerInfoMapper cusLinkMerInfoMapper;
    @Resource
    private CooperationMapper cooperationMapper;

    @Override
    public int insertNotifyLog(NotifySendAgain notifySendAgain) {
        notifySendAgain.setLastSendTime(DateUtils.getNowDate());
        return notifySendAgainMapper.insertSelective(notifySendAgain);
    }

    @Override
    public void sendNotify(NotifySendAgain notifySendAgain,Integer minutes) {
        Date sendTime = DateUtils.addMinutes(notifySendAgain.getLastSendTime(),minutes);
        if(DateUtils.getNowDate().compareTo(sendTime) < 0){
            log.info("id={}的记录未到下次发送的时间点之后，return",notifySendAgain.getId());
            return;
        }
        MerchantKey merchantKey = new MerchantKey();
        merchantKey.setMerchantId(notifySendAgain.getMerchantId());
        MerchantKey rMerchantKey = merchantKeyMapper.selectOne(merchantKey);
        if(StringUtils.isEmpty(rMerchantKey.getCallBackAddress())){
            log.info("商户{}通知地址为空，不发送",notifySendAgain.getMerchantId());
            return;
        }
        Cooperation queryCoop = new Cooperation();
        queryCoop.setMerchantId(notifySendAgain.getMerchantId());
        Cooperation cooperation = cooperationMapper.selectOne(queryCoop);
        // 商户异步通知开关关闭，不再定时发送通知
        if (cooperation != null && SwitchEnum.OFF.getCode().equals(cooperation.getAsynSwitch())) {
            return;
        }
        String content = notifySendAgain.getNotifyContent();
        // 商户异步通知签名开关开启，通知数据添加签名
        if (cooperation != null && SwitchEnum.ON.getCode().equals(cooperation.getAsynSignSwitch())) {
            content = MmtaxSign.addSignByJsonStr(content, merchantKey.getAppKey());
        }
        String callBackResult = HttpUtils.sendPost(rMerchantKey.getCallBackAddress(),content);
        log.info("返回的结果：{}",callBackResult);
        NotifySendAgain updateNotify = new NotifySendAgain();
        updateNotify.setId(notifySendAgain.getId());
        updateNotify.setLastSendTime(DateUtils.getNowDate());
        updateNotify.setFailNum(notifySendAgain.getFailNum()+1);
        if(StringUtils.isNotEmpty(callBackResult) && NotifyStatusEnum.SUCCESS.name().equals(callBackResult)){
            log.info("id={}的记录发送成功，更改记录状态",notifySendAgain.getId());
            updateNotify.setNotifyStatus(1);
            updateNotify.setFailNum(notifySendAgain.getFailNum());
        }
        try {
            notifySendAgainMapper.updateByPrimaryKeySelective(updateNotify);
        }catch(Exception e){
            log.info("id={}的记录更新失败",notifySendAgain.getId());
        }
    }

    @Override
    public void sendSignNotify(CustomerProtocol customerProtocol) {
        CustomerEsignInfo customerEsignInfo = customerEsignInfoMapper.selectByPrimaryKey(customerProtocol.getCusEsignId());
        Integer merchantId = customerProtocol.getMerchantId();
        MerchantKey merchantKey = new MerchantKey();
        merchantKey.setMerchantId(merchantId);
        MerchantKey rMerchantKey = merchantKeyMapper.selectOne(merchantKey);
        if(StringUtils.isEmpty(rMerchantKey.getCallBackAddress())){
            log.info("通知地址为空，不发送");
            return;
        }
        ApiSignStatusDTO apiSignStatusDTO = new ApiSignStatusDTO();
        apiSignStatusDTO.setNotifyType("3");
        apiSignStatusDTO.setIdCardNo(customerEsignInfo.getIdNumber());
        apiSignStatusDTO.setMobileNo(customerEsignInfo.getMobile());
        apiSignStatusDTO.setName(customerEsignInfo.getName());
        apiSignStatusDTO.setOrderId(customerProtocol.getMerchantSerialNum());
        Integer signStatus = customerProtocol.getSignStatus();
        apiSignStatusDTO.setCode(NotifyStatusEnum.SUCCESS.name());
        if(SignStatusEnum.INIT.getCode().equals(signStatus) || SignStatusEnum.NOSIGN.getCode().equals(signStatus)) {
            apiSignStatusDTO.setStatus("1");
            apiSignStatusDTO.setMessage("进行中");
        }else{
            apiSignStatusDTO.setStatus(signStatus.toString());
            apiSignStatusDTO.setMessage(Objects.requireNonNull(SignStatusEnum.getByCode(signStatus)).getDescription());
        }
        apiSignStatusDTO.setSign(null);
        String content = JSON.toJSONString(apiSignStatusDTO);
        JSONObject jsonObject = JSONObject.parseObject(content);
        jsonObject.remove("sign");
        jsonObject.remove("description");
        content = jsonObject.toJSONString();
        String callBackResult = HttpUtils.sendPost(rMerchantKey.getCallBackAddress(),content);
        log.info("返回的结果：{}",callBackResult);
        if(StringUtils.isNotEmpty(callBackResult) && NotifyStatusEnum.SUCCESS.name().equals(callBackResult)){
            log.info("通知发送成功");
            return;
        }
        log.info("通知发送失败，记录数据库");
        NotifySendAgain notifySendAgain = new NotifySendAgain();
        notifySendAgain.setMerchantId(merchantId);
        notifySendAgain.setNotifyContent(content);
        notifySendAgain.setNotifyType(3);
        notifySendAgain.setNotifyStatus(2);
        notifySendAgain.setSendMethod(1);
        notifySendAgain.setLastSendTime(DateUtils.getNowDate());
        notifySendAgain.setFailNum(1);
        notifySendAgain.setCreateTime(DateUtil.date());
        notifySendAgain.setUpdateTime(DateUtil.date());
        notifySendAgainMapper.insertSelective(notifySendAgain);
    }
}
