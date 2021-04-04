package com.mmtax.quartz.task;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.CusLinkMerInfo;
import com.mmtax.business.domain.CustomerInfo;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.mapper.CusLinkMerInfoMapper;
import com.mmtax.business.mapper.CustomerInfoMapper;
import com.mmtax.business.mapper.TrxOrderMapper;
import com.mmtax.common.constant.PaymentConstants;
import com.mmtax.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：YH
 * @Date：2020/10/12 15:08
 */
@Component("HandleCustomerWarnJob")
@Slf4j
public class HandleCustomerWarnJob {

    @Autowired
    CustomerInfoMapper customerInfoMapper;
    @Autowired
    TrxOrderMapper trxOrderMapper;
    @Autowired
    CusLinkMerInfoMapper cusLinkMerInfoMapper;

    public void handleCustomerWarnJob() {
        log.info("开始处理灵工预警任务");
        //1.拿到所有在两家商户注册过的灵工
        List<CusLinkMerInfo> cusLinkMerInfos = cusLinkMerInfoMapper.listCustomerRegisterEqualsMultiMerchant(2);
        for (CusLinkMerInfo cusLinkMerInfo : cusLinkMerInfos) {
            //根据员工id 找到 所注册的两家商户
            List<CusLinkMerInfo> customerOfSameMerchantInfos =
                    cusLinkMerInfoMapper.listCustomerOfSameMerchantByCustomerId(cusLinkMerInfo.getCustomerId());
            //查询是否 其中一家 打款已超过10w
            for(CusLinkMerInfo customerOfSameMerchantInfo : customerOfSameMerchantInfos){
              try {
                  //查询当月 灵工在某商户下 打款总额
                  String startDate = DateUtil.format(DateUtil.beginOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
                  String endDate = DateUtil.format(DateUtil.endOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
                  BigDecimal sumMonthAmount = trxOrderMapper.sumAmountByCustomerId(customerOfSameMerchantInfo.getMerchantId(),
                          customerOfSameMerchantInfo.getCustomerId(), startDate, endDate);
                  //超过 10w 预警  否则不预警
                  if(sumMonthAmount.compareTo(PaymentConstants.WARN_LIMIT) > 0){
                      customerInfoMapper.updateCustomerWarn(1,cusLinkMerInfo.getCustomerId());
                  }else{
                      customerInfoMapper.updateCustomerWarn(0,cusLinkMerInfo.getCustomerId());
                  }
              }catch (Exception e){
                  log.info("员工：{}发生错误,错误原因:",cusLinkMerInfo.getCustomerId(),e.getMessage());
              }

            }
        }

        //2.拿到所有在两家以上商户注册过的灵工，预警
        List<CusLinkMerInfo> warnCusLinkMerInfos = cusLinkMerInfoMapper.listCustomerRegisterGreaterMultiMerchant(2);
        for(CusLinkMerInfo cusLinkMerInfo:warnCusLinkMerInfos){
          try {
              customerInfoMapper.updateCustomerWarn(1,cusLinkMerInfo.getCustomerId());
          }catch (Exception e){
              log.info("员工：{}发生错误,错误原因:",cusLinkMerInfo.getCustomerId(),e.getMessage());
          }

        }


        log.info("处理灵工预警任务结束");
    }
}
