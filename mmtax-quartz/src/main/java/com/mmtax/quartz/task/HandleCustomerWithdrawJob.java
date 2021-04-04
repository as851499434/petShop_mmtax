package com.mmtax.quartz.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.CustomerWithdraw;
import com.mmtax.business.mapper.CustomerWithdrawMapper;
import com.mmtax.business.service.ICustomerWithdrawService;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.common.constant.onlinebank.OnlineConstants;
import com.mmtax.common.enums.AsyncStatusEnum;
import com.mmtax.common.enums.WithdrawStatusEnum;
import com.mmtax.common.utils.onlinebank.InfoQueryResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component("HandleCustomerWithdrawJob")
@Slf4j
public class HandleCustomerWithdrawJob {

    @Autowired
    CustomerWithdrawMapper customerWithdrawMapper;
    @Autowired
    ICustomerWithdrawService customerWithdrawService;
    @Autowired
    IOnlineBankService onlineBankService;

    /**
     * 处理异步通知状态未处理的成功失败customerWithdraw记录
     */
    public void handleSuccessOrFail() {
        log.info("处理customerWithdraw记录状态为成功或失败的后续处理开始------");
        List<CustomerWithdraw> customerWithdraws = customerWithdrawMapper.selectByAsyncStatusWithdrawStatusBeforeTime(
                AsyncStatusEnum.NOHANDLE.getStatus(),null,null);
        if(CollectionUtils.isEmpty(customerWithdraws)){
            log.info("没有需要处理的customerWithdraw记录");
            return;
        }
        customerWithdraws.forEach(one->{
            try{
                if(WithdrawStatusEnum.PAID.getCode().equals(one.getWithdrawStatus())){
                    //成功
                    customerWithdrawService.handleAsyncStatus(one,WithdrawStatusEnum.PAID);
                }
                if(WithdrawStatusEnum.ORDER_FAIL.getCode().equals(one.getWithdrawStatus())){
                    //失败
                    customerWithdrawService.handleAsyncStatus(one,WithdrawStatusEnum.ORDER_FAIL);
                }
            }catch (Exception e){
                log.error("HandleCustomerWithdrawJob处理成功或失败{}出错：{}",one.getWithdrawSerialNum(),e.getMessage());
                e.printStackTrace();
            }
        });
        log.info("处理customerWithdraw记录状态为成功或失败的后续处理结束------");
    }

    /**
     * 处理2天前状态进行中的customerWithdraw记录
     */
    public void handleProcessTwoDayAgo() {
        log.info("处理customerWithdraw记录状态为进行中的后续处理开始------");
        DateTime twoDay = DateUtil.offsetDay(DateUtil.date(), -2);
        log.info("2天前的时间：{}", twoDay);
        List<CustomerWithdraw> customerWithdraws = customerWithdrawMapper.selectByAsyncStatusWithdrawStatusBeforeTime(
                null ,WithdrawStatusEnum.UNPAID.getCode(),DateUtil.formatDateTime(twoDay));
        if(CollectionUtils.isEmpty(customerWithdraws)){
            log.info("没有需要处理的进行中customerWithdraw记录");
            return;
        }
        customerWithdraws.forEach(one->{
            try{
                InfoQueryResultDTO resultDTO = onlineBankService.infoQuery(one.getMerchantId(), one.getWithdrawSerialNum());
                if (null == resultDTO) {
                    return;
                }
                log.info("处理的进行中customerWithdraw记录:{}", one.getWithdrawSerialNum());
                if ("T".equals(resultDTO.getIs_success())
                        && OnlineConstants.TRADE_FINISHED.equals(resultDTO.getTrade_status())) {
                    //更新状态为成功
                    one.setWithdrawStatus(WithdrawStatusEnum.PAID.getCode());
                    customerWithdrawMapper.updateByPrimaryKeySelective(one);
                }
                if (OnlineConstants.TRADE_FAILED.equals(resultDTO.getTrade_status()) ||
                        !"T".equals(resultDTO.getIs_success())) {
                    //更新状态为失败
                    one.setWithdrawStatus(WithdrawStatusEnum.ORDER_FAIL.getCode());
                    one.setComment(resultDTO.getError_message());
                    customerWithdrawMapper.updateByPrimaryKeySelective(one);
                }
            }catch (Exception e){
                log.error("HandleCustomerWithdrawJob处理进行中{}出错：{}",one.getWithdrawSerialNum(),e.getMessage());
                e.printStackTrace();
            }
        });
        log.info("处理customerWithdraw记录状态为进行中的后续处理结束------");
    }
}
