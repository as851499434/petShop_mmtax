package com.mmtax.quartz.task;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.MerchantAccount;
import com.mmtax.business.domain.ReturnChargeOrder;
import com.mmtax.business.mapper.MakeUpChargeMapper;
import com.mmtax.business.mapper.MerchantAccountMapper;
import com.mmtax.business.mapper.RechargeRecordMapper;
import com.mmtax.business.mapper.ReturnChargeOrderMapper;
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

@Component("HandleReturnChargeJob")
@Slf4j
public class HandleReturnChargeJob {
    @Autowired
    private ReturnChargeOrderMapper returnChargeOrderMapper;
    @Autowired
    private IMerchantAccountService merchantAccountService;
    @Autowired
    private MerchantAccountMapper merchantAccountMapper;
    @Autowired
    private IOnlineBankService onlineBankService;
    @Autowired
    IMerchantAccountDetailService merchantAccountDetailService;


    public void handleReturnCharge(){
        log.info("开始处理退还服务费记录状态为成功或失败的后续处理------");
        List<ReturnChargeOrder> list = returnChargeOrderMapper.selectByStatusAndAsycSatus(null
                , AsyncStatusEnum.NOHANDLE.getStatus());
        if(CollectionUtils.isEmpty(list)){
            log.info("没有需要处理的退还服务费记录，返回");
            return;
        }
        list.forEach(one->{
            try {
                BigDecimal amount = one.getReturnCharge();
                if(ReturnChargeStatusEnum.SUCESS.getCode().equals(one.getStatus())){
                    one.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                    one.setUpdateTime(DateUtil.date());
                    returnChargeOrderMapper.updateByPrimaryKeySelective(one);
                    //商户入账解冻并添加变动记录
                    MerchantAccount beforeAccount = merchantAccountService.updateMerchantAccountByVersion(
                            one.getMerchantId(), amount, UpdateAccountTypeOfMethod.PAYORMAKEUP.getType());
                    MerchantAccount afterAccount = merchantAccountMapper.getMerchantAccountByMerchantId(one.getMerchantId());
                    merchantAccountDetailService.insertMerchantAccountDetail(amount, beforeAccount, afterAccount
                            , AccountDetailStatusEnum.SUCCESS.code, one.getOrderSerialNum()
                            , MerchantAccountDetailTypeEnum.PAID.code, DetailTypeEnum.RETUANCHARGE.getCode());
                }
                if(ReturnChargeStatusEnum.FAIL.getCode().equals(one.getStatus())){
                    one.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                    one.setUpdateTime(DateUtil.date());
                    returnChargeOrderMapper.updateByPrimaryKeySelective(one);
                    //商户出账解冻并添加变动记录
                    MerchantAccount beforeAccount = merchantAccountService.updateMerchantAccountByVersion(
                            one.getMerchantId(), amount, UpdateAccountTypeOfMethod.PAYSUCCESS.getType());
                    MerchantAccount afterAccount = merchantAccountMapper.getMerchantAccountByMerchantId(one.getMerchantId());
                    merchantAccountDetailService.insertMerchantAccountDetail(amount, beforeAccount, afterAccount
                            , AccountDetailStatusEnum.SUCCESS.code, one.getOrderSerialNum()
                            , MerchantAccountDetailTypeEnum.PAID.code, DetailTypeEnum.RETUANCHARGE.getCode());
                }
            }catch (Exception e){
                log.error("HandleReturnChargeJob定时任务出错："+e.getMessage());
            }
        });
        log.info("结束处理退还服务费记录状态为成功或失败的后续处理------");
    }
}
