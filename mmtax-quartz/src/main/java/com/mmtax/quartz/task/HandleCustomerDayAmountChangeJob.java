package com.mmtax.quartz.task;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.CustomerAccount;
import com.mmtax.business.domain.CustomerDayAmountChange;
import com.mmtax.business.domain.CustomerInfo;
import com.mmtax.business.mapper.CustomerAccountMapper;
import com.mmtax.business.mapper.CustomerDayAmountChangeMapper;
import com.mmtax.business.mapper.CustomerInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author：YH
 * @Date：2020/9/21 16:53
 */
@Component("HandleCustomerDayAmountChangeJob")
@Slf4j
public class HandleCustomerDayAmountChangeJob {

    @Resource
    CustomerDayAmountChangeMapper customerDayAmountChangeMapper;
    @Resource
    CustomerAccountMapper customerAccountMapper;
    @Resource
    CustomerInfoMapper customerInfoMapper;

    public void handleCustomerDayAmountChangeJob(){
        log.info("开始处理handleCustomerDayAmountChangeJob");
        //1.拿到tbl_customer_account表中所有的员工记录
        List<CustomerAccount> customerAccounts = customerAccountMapper.selectAll();
        //2.拿到昨天tbl_customer_day_amount_change的记录
        String yesterday = DateUtil.offsetDay(new DateTime(),-2).toString().substring(0, 10);
        CustomerDayAmountChange customerDayAmountChange = new CustomerDayAmountChange();
        customerDayAmountChange.setPeriod(yesterday);
        List<CustomerDayAmountChange> customerDayAmountChanges =
                customerDayAmountChangeMapper.select(customerDayAmountChange);

        for(CustomerAccount customerAccount:customerAccounts){
            try{
                //查询该员工id在tbl_customer_day_amount_change是否有昨天的记录
                CustomerDayAmountChange yesterdayCustomerDayAmountChange =
                        judgeCustomerDayAmountChangeExits(customerDayAmountChanges, customerAccount);

                CustomerDayAmountChange insertCustomerDayAmountChange = new CustomerDayAmountChange();
                //填充插入字段
                insertCustomerDayAmountChange.setCustomerId(customerAccount.getCustomerId());
                insertCustomerDayAmountChange.setPeriod(DateUtil.yesterday().toString().substring(0, 10));
                BigDecimal endleAmount = customerAccount.getUsableAmount();
                insertCustomerDayAmountChange.setEndAmoutnt(endleAmount);
                insertCustomerDayAmountChange.setCreateTime(DateUtil.date());
                insertCustomerDayAmountChange.setUpdateTime(DateUtil.date());
                //找到员工所属商户id
                CustomerInfo customerInfo = new CustomerInfo();
                customerInfo.setId(customerAccount.getCustomerId());
                customerInfo = customerInfoMapper.selectOne(customerInfo);
                insertCustomerDayAmountChange.setMerchantId(customerInfo.getMerchantId());

                BigDecimal startAmount = BigDecimal.ZERO;
                if(null != yesterdayCustomerDayAmountChange){
                    startAmount = yesterdayCustomerDayAmountChange.getEndAmoutnt();
                }
                insertCustomerDayAmountChange.setStartAmount(startAmount);
                insertCustomerDayAmountChange.setDiffAmount(endleAmount.subtract(startAmount));

                CustomerDayAmountChange customerDayAmountChangeCountQuery = new CustomerDayAmountChange();
                customerDayAmountChangeCountQuery.setCustomerId(insertCustomerDayAmountChange.getCustomerId());
                customerDayAmountChangeCountQuery.setCustomerId(insertCustomerDayAmountChange.getMerchantId());
                customerDayAmountChangeCountQuery.setPeriod(insertCustomerDayAmountChange.getPeriod());
                int count = customerDayAmountChangeMapper.selectCount(customerDayAmountChangeCountQuery);

                if(count > 0){
                    log.info("员工{}:日期{},已经插入过了",customerDayAmountChangeCountQuery.getCustomerId(),
                            customerDayAmountChangeCountQuery.getPeriod());
                    continue;
                }else{
                    customerDayAmountChangeMapper.insertSelective(insertCustomerDayAmountChange);
                }

            }catch (Exception e){
                log.error("灵工{},错误原因",customerAccount.getCustomerId(),e.getMessage());
            }

        }

        log.info("处理handleCustomerDayAmountChangeJob结束");

    }

    public CustomerDayAmountChange judgeCustomerDayAmountChangeExits(List<CustomerDayAmountChange> customerDayAmountChanges,
                                                     CustomerAccount customerAccount){

        for(CustomerDayAmountChange customerDayAmountChange:customerDayAmountChanges){
            if(customerDayAmountChange.getCustomerId().equals(customerAccount.getCustomerId())){
                return customerDayAmountChange;
            }
        }

        return null;
    }


}
