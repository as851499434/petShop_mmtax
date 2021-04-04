package com.mmtax.quartz.task;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.MakeUpCharge;
import com.mmtax.business.domain.MerchantAccount;
import com.mmtax.business.mapper.MakeUpChargeMapper;
import com.mmtax.business.mapper.MerchantAccountMapper;
import com.mmtax.business.service.IMerchantAccountDetailService;
import com.mmtax.business.service.IMerchantAccountService;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.common.enums.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@Component("HandleMakeUpChargeJob")
@Slf4j
public class HandleMakeUpChargeJob {

    @Autowired
    private MakeUpChargeMapper makeUpChargeMapper;
    @Autowired
    private IMerchantAccountService merchantAccountService;
    @Autowired
    private MerchantAccountMapper merchantAccountMapper;
    @Autowired
    private IOnlineBankService onlineBankService;
    @Autowired
    IMerchantAccountDetailService merchantAccountDetailService;


    public void handleMakeUpCharge(){
        log.info("开始处理补交服务费记录状态为成功或失败的后续处理------");
        List<MakeUpCharge> makeUpCharges = makeUpChargeMapper.selectByStatusAndAsycSatus(null
                , AsyncStatusEnum.NOHANDLE.getStatus());
        if(CollectionUtils.isEmpty(makeUpCharges)){
            log.info("没有需要处理的记录");
            return;
        }
        makeUpCharges.forEach(one->{
            try{
                BigDecimal amount = one.getAmount();
                if(MakeUpChargeStatusEnum.SUCESS.getStatus().equals(one.getStatus())){
                    one.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                    one.setUpdateTime(DateUtil.date());
                    makeUpChargeMapper.updateByPrimaryKeySelective(one);
                    //商户出账解冻并添加变动记录
                    MerchantAccount beforeAccount = merchantAccountService.updateMerchantAccountByVersion(
                            one.getMerchantId(), amount, UpdateAccountTypeOfMethod.PAYSUCCESS.getType());
                    MerchantAccount afterAccount = merchantAccountMapper.getMerchantAccountByMerchantId(one.getMerchantId());
                    merchantAccountDetailService.insertMerchantAccountDetail(amount, beforeAccount, afterAccount
                            , AccountDetailStatusEnum.SUCCESS.code, one.getOrderSerialNum()
                            , MerchantAccountDetailTypeEnum.PAID.code, DetailTypeEnum.MAKEUPCHARGE.getCode());
                }
                if(MakeUpChargeStatusEnum.FAIL.getStatus().equals(one.getStatus())){
                    one.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                    one.setUpdateTime(DateUtil.date());
                    makeUpChargeMapper.updateByPrimaryKeySelective(one);
                    //商户入账解冻并添加记录
                    MerchantAccount beforeAccount = merchantAccountService.updateMerchantAccountByVersion(
                            one.getMerchantId(), amount, UpdateAccountTypeOfMethod.PAYORMAKEUP.getType());
                    MerchantAccount afterAccount = merchantAccountMapper.getMerchantAccountByMerchantId(one.getMerchantId());
                    merchantAccountDetailService.insertMerchantAccountDetail(amount, beforeAccount, afterAccount
                            , AccountDetailStatusEnum.SUCCESS.code, one.getOrderSerialNum()
                            , MerchantAccountDetailTypeEnum.PAID.code, DetailTypeEnum.MAKEUPCHARGE.getCode());;
                }
            }catch(Exception e){
                log.error("HandleMakeUpChargeJob定时任务出错："+e.getMessage());
            }
        });
        log.info("结束处理补交服务费记录状态为成功或失败的后续处理------");
    }
}
