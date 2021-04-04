package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.mmtax.business.domain.CustomerAccount;
import com.mmtax.business.domain.CustomerAccountDetail;
import com.mmtax.business.dto.DateQueryDTO;
import com.mmtax.business.dto.ManagerCustomerAccountDTO;
import com.mmtax.business.dto.ManagerCustomerAccountQueryDTO;
import com.mmtax.business.mapper.CustomerAccountDetailMapper;
import com.mmtax.business.mapper.CustomerAccountMapper;
import com.mmtax.business.service.ICustomerAccountService;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.common.constant.MerchantAccountConstants;
import com.mmtax.common.constant.PaymentTypeConstants;
import com.mmtax.common.enums.AccountDetailStatusEnum;
import com.mmtax.common.enums.AccountDetailTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 员工账户 服务层实现
 *
 * @author meimiao
 * @date 2020-07-09
 */
@Slf4j
@Service
public class CustomerAccountServiceImpl extends BaseServiceImpl implements ICustomerAccountService {
    @Autowired
    private CustomerAccountMapper customerAccountMapper;
    @Autowired
    private CustomerAccountDetailMapper customerAccountDetailMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public CustomerAccount updateByVersion(Integer customerId, BigDecimal amount, AccountDetailTypeEnum type) {
        int numAttempt = 0;
        int count;
        CustomerAccount accountBefore = new CustomerAccount();
        do {
            numAttempt++;
            CustomerAccount account = customerAccountMapper.selectByCustomerIdUpCache(customerId);
            log.info("更新前的customerAcount：{}", JSON.toJSONString(account));
            BeanUtils.copyProperties(account, accountBefore);
            Integer version = account.getVersion();
            switch (type) {
                case DEBIT_FREEZE:
                    account.setFrozenAmount(account.getFrozenAmount().add(amount));
                    account.setUsableAmount(account.getUsableAmount().subtract(amount));
                    break;
                case DEBIT_UNFREEZE:
                    account.setAmount(account.getAmount().subtract(amount));
                    account.setFrozenAmount(account.getFrozenAmount().subtract(amount));
                    break;
                case CREDIT_FREEZE:
                    account.setAmount(account.getAmount().add(amount));
                    account.setFrozenAmount(account.getFrozenAmount().add(amount));
                    break;
                case CREDIT_UNFREEZE:
                    account.setUsableAmount(account.getUsableAmount().add(amount));
                    account.setFrozenAmount(account.getFrozenAmount().subtract(amount));
                    break;
                default:
            }
            account.setUpdateTime(DateUtil.date());
            count = customerAccountMapper.updateByPrimaryKey(account);
            log.info("updateByVersion customerId:{}, amount:{},numAttempt:{}, count:{}, version:{}",
                    customerId, amount, numAttempt, count, version);
        }
        while (count == 0 && numAttempt <= MerchantAccountConstants.MERCHANT_ACCOUNT_DEFAULT_MAX_RETRIES);
        return accountBefore;
    }

    @Override
    public void updateAccountAndInsertDetail(Integer customerId, BigDecimal amount, AccountDetailTypeEnum type
            , String orderSerialNum) {
        //变动账户
        ICustomerAccountService customerAccountServiceProxy = (ICustomerAccountService) AopContext.currentProxy();
        CustomerAccount beforeAccount = customerAccountServiceProxy.updateByVersion(customerId, amount, type);
        //添加变动记录
        CustomerAccount afterAccount = customerAccountMapper.selectByCustomerId(customerId);
        CustomerAccountDetail customerAccountDetail = new CustomerAccountDetail();
        customerAccountDetail.setType(type.getType());
        customerAccountDetail.setPaymentAmountBefore(beforeAccount.getAmount());
        customerAccountDetail.setPaymentFrozenAmountBefore(beforeAccount.getFrozenAmount());
        customerAccountDetail.setPaymentUsableAmountBefore(beforeAccount.getUsableAmount());
        customerAccountDetail.setPaymentAmount(amount);
        customerAccountDetail.setPaymentAmountAfter(afterAccount.getAmount());
        customerAccountDetail.setPaymentUsableAmountAfter(afterAccount.getUsableAmount());
        customerAccountDetail.setPaymentFrozenAmountAfter(afterAccount.getFrozenAmount());
        customerAccountDetail.setOrderSerialNum(orderSerialNum);
        customerAccountDetail.setCustomerId(customerId);
        customerAccountDetail.setStatus(AccountDetailStatusEnum.SUCCESS.code);
        customerAccountDetail.setCreateTime(DateUtil.date());
        customerAccountDetail.setUpdateTime(DateUtil.date());
        if (customerAccountDetail.getType().compareTo(AccountDetailTypeEnum.CREDIT_FREEZE.getType()) == 0 ||
                customerAccountDetail.getType().compareTo(AccountDetailTypeEnum.CREDIT_UNFREEZE.getType()) == 0) {
            customerAccountDetail.setPaymentType(PaymentTypeConstants.PAYMENT_TYPE_RECEIPT);
        } else if (customerAccountDetail.getType().compareTo(AccountDetailTypeEnum.DEBIT_FREEZE.getType()) == 0
                || customerAccountDetail.getType().compareTo(AccountDetailTypeEnum.DEBIT_UNFREEZE.getType()) == 0) {
            customerAccountDetail.setPaymentType(PaymentTypeConstants.PAYMENT_TYPE_WITHDRAW);
        }
        customerAccountDetailMapper.insertSelective(customerAccountDetail);
    }

    @Override
    public List<ManagerCustomerAccountDTO> listManagerCustomerAccountDTO(ManagerCustomerAccountQueryDTO queryDTO) {
        DateQueryDTO dateQueryDTO = getDateQueryDTOMatchNorm(queryDTO.getStartDate(), queryDTO.getEndDate());
        queryDTO.setStartDate(dateQueryDTO.getStartDate());
        queryDTO.setEndDate(dateQueryDTO.getEndDate());
        List<ManagerCustomerAccountDTO> dtoList = customerAccountMapper.listManagerCustomerAccountDTO(queryDTO);
        return dtoList;
    }
}
