package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.*;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.constant.SmsConstant;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.EncrpytionUtil;
import com.mmtax.common.utils.PasswordUtil;
import com.mmtax.common.utils.PatternVerifyUtil;
import com.mmtax.common.utils.onlinebank.OnlineCommonResultDTO;
import com.mmtax.common.utils.redis.RedisUtil;
import com.mmtax.common.utils.sms.TiRuiYunUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 灵工提现记录 服务层实现
 * 
 * @author meimiao
 * @date 2020-09-11
 */
@Service
public class CustomerWithdrawServiceImpl extends BaseServiceImpl implements ICustomerWithdrawService
{
    @Autowired
    private CustomerWithdrawMapper customerWithdrawMapper;
    @Autowired
    IOnlineBankService onlineBankService;
    @Autowired
    CustomerInfoMapper customerInfoMapper;
    @Autowired
    CustomerBankcardInfoMapper customerBankcardInfoMapper;
    @Autowired
    CustomerAlipayInfoMapper customerAlipayInfoMapper;
    @Autowired
    ICustomerAccountService customerAccountService;
    @Autowired
    ICustomerProtocolService customerProtocolService;
    @Autowired
    ICustomerInfoService customerInfoService;
    @Autowired
    TrxOrderMapper trxOrderMapper;
    @Autowired
    CooperationMapper cooperationMapper;

    @Override
    public Boolean withDrawIsOpen(Integer customerId) {
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerId);
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(customerInfo.getMerchantId());
        cooperation = cooperationMapper.selectOne(cooperation);
        if (cooperation == null) {
            return false;
        }
        return SwitchEnum.ON.getCode().equals(cooperation.getCustomerWithdrawSwitch());
    }

    @Override
    public void handleAsyncStatus(CustomerWithdraw customerWithdraw, WithdrawStatusEnum withdrawStatus) {
        customerWithdraw.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
        customerWithdraw.setUpdateTime(DateUtil.date());
        customerWithdrawMapper.updateByPrimaryKeySelective(customerWithdraw);
        Integer customerId = customerWithdraw.getCustomerId();
        String withdrawSerialNum = customerWithdraw.getWithdrawSerialNum();
        BigDecimal withdrawAmount = customerWithdraw.getAmount();
        switch (withdrawStatus){
            case PAID:
                //员工出账解冻
                customerAccountService.updateAccountAndInsertDetail(customerId,withdrawAmount
                        ,AccountDetailTypeEnum.DEBIT_UNFREEZE,withdrawSerialNum);
                break;
            case ORDER_FAIL:
                //员工入账解冻
                customerAccountService.updateAccountAndInsertDetail(customerId,withdrawAmount
                        , AccountDetailTypeEnum.CREDIT_UNFREEZE,withdrawSerialNum);
                break;
            default:
        }
    }

    @Override
    public Page pageIncome(Integer pageSize, Integer currentPage, Integer customerId, String startDate, String endDate) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<IncomeWithdrawDTO> incomeWithdrawDTOList = trxOrderMapper.listPageByCustomerIdCreateTimeStatus(customerId
                ,TrxOrderStatusEnum.PAID.getCode(),startDate,endDate,queryPage.getStartIndex(),pageSize);
        int count = trxOrderMapper.countByCustomerIdCreateTimeStatus(customerId,TrxOrderStatusEnum.PAID.getCode()
                ,startDate,endDate);
        return new Page(count, incomeWithdrawDTOList, currentPage, pageSize);
    }

    /**
     * 查询灵工支付宝提现记录列表
     * @param queryDTO
     * @return
     */
    @Override
    public List<CustomerWithdrawInfoDTO> listCustomerWithdrawAlipayInfo(QueryCustomerWithdrawInfoDTO queryDTO) {
        queryDTO.setWithdrawChannel("ALIPAY");
        return customerWithdrawMapper.listCustomerWithdrawInfo(queryDTO);
    }

    /**
     * 查询灵工银行卡提现记录列表
     * @param queryDTO
     * @return
     */
    @Override
    public List<CustomerWithdrawInfoDTO> listCustomerWithdrawBankInfo(QueryCustomerWithdrawInfoDTO queryDTO) {
        queryDTO.setWithdrawChannel("BANK");
        return customerWithdrawMapper.listCustomerWithdrawInfo(queryDTO);
    }

    private CustomerWithdraw generateCusTomerWithdraw(CusWithdrawDTO dto,PaymentEnum withdrawChannel){
        CustomerWithdraw customerWithdraw = new CustomerWithdraw();
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(dto.getCustomerId());
        //添加提现开关的判断
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(customerInfo.getMerchantId());
        cooperation = cooperationMapper.selectOne(cooperation);
        if(SwitchEnum.OFF.getCode().equals(cooperation.getCustomerWithdrawSwitch())){
            throw new BusinessException("未开通提现功能，请联系商户");
        }
        //判断是否签约

        if(!customerProtocolService.haveSign(customerInfo.getId())){
            throw new BusinessException("您还未签约，不可操作提现");
        }
        //验证提现密码
        boolean checkPasswordResult = EncrpytionUtil.checkPassword(customerInfo.getCustomerNo(),
                dto.getWithdrawPass(), customerInfo.getWithdrawPass(), customerInfo.getSalt());
        if (!checkPasswordResult) {
            throw new BusinessException("提现密码错误");
        }
        customerWithdraw.setCustomerId(customerInfo.getId());
        customerWithdraw.setMerchantId(customerInfo.getMerchantId());
        customerWithdraw.setAmount(dto.getAmount());
        customerWithdraw.setWithdrawAccount(dto.getAccountNo());
        String bankId;
        if(PaymentEnum.BANK.equals(withdrawChannel)) {
            CustomerBankcardInfo bankcardInfo = customerBankcardInfoMapper.selectByCustomerIdAndBankAccountNo(
                    dto.getCustomerId(), dto.getAccountNo());
            bankId = bankcardInfo.getBankId();
        }else {
            CustomerAlipayInfo alipayInfo = customerAlipayInfoMapper.selectByCustomerIdAndAccountNo(dto.getCustomerId()
                    ,dto.getAccountNo());
            bankId = alipayInfo.getBankId();
        }
        customerWithdraw.setBankId(bankId);
        customerWithdraw.setWithdrawChannel(withdrawChannel.name());
        customerWithdraw.setWithdrawSerialNum(ChanPayUtil.generateOutTradeNo());
        customerWithdraw.setCustomerSerialNum(customerWithdraw.getWithdrawSerialNum());
        customerWithdraw.setWithdrawStatus(WithdrawStatusEnum.INIT.getCode());
        customerWithdraw.setAsyncStatus(AsyncStatusEnum.NOHANDLE.getStatus());
        customerWithdraw.setCreateTime(DateUtil.date());
        customerWithdraw.setUpdateTime(DateUtil.date());
        customerWithdrawMapper.insertSelective(customerWithdraw);
        return customerWithdraw;
    }

    @Override
    public void withDraw(CusWithdrawDTO dto,PaymentEnum withdrawChannel) {
        CustomerWithdraw customerWithdraw = generateCusTomerWithdraw(dto,withdrawChannel);
        Integer customerId = customerWithdraw.getCustomerId();
        BigDecimal amount = customerWithdraw.getAmount();
        String withdrawSerialNum = customerWithdraw.getWithdrawSerialNum();
        //员工出账冻结并添加变动记录
        customerAccountService.updateAccountAndInsertDetail(customerId, amount, AccountDetailTypeEnum.DEBIT_FREEZE
                , withdrawSerialNum);
        OnlineCommonResultDTO resultDTO = onlineBankService.customerWithdraw(customerWithdraw);
        customerWithdraw.setWithdrawStatus(WithdrawStatusEnum.UNPAID.getCode());
        if (!"T".equals(resultDTO.getIs_success())) {
            //失败的时候
            customerWithdraw.setWithdrawStatus(WithdrawStatusEnum.ORDER_FAIL.getCode());
            customerWithdraw.setComment(resultDTO.getError_message());
            customerWithdraw.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
            //员工入账解冻并添加记录
            customerAccountService.updateAccountAndInsertDetail(customerId, amount,AccountDetailTypeEnum.CREDIT_UNFREEZE
                    , withdrawSerialNum);
        }
        customerWithdraw.setUpdateTime(DateUtil.date());
        customerWithdrawMapper.updateByPrimaryKeySelective(customerWithdraw);
    }

    @Override
    public Boolean judgeHaveSetWithdrawPass(Integer customerId) {
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerId);
        if(StringUtils.isEmpty(customerInfo.getWithdrawPass())){
            return false;
        }
        return true;
    }

    @Override
    public void setWithdrawPass(WithdrawPassDTO dto) {
        if(StringUtils.isEmpty(dto.getNewPass())){
            throw new BusinessException("密碼不可为空");
        }
        if(!PatternVerifyUtil.validateNumber(dto.getNewPass())){
            throw new BusinessException("密碼只能为数字");
        }
        if(!dto.getNewPass().equals(dto.getConfirmPass())){
            throw new BusinessException("确认密碼不一致");
        }
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(dto.getCustomerId());
        String salt = PasswordUtil.randomSalt();
        String encryptPass = PasswordUtil.password(dto.getNewPass(),customerInfo.getCustomerNo(),salt);
        customerInfo.setSalt(salt);
        customerInfo.setWithdrawPass(encryptPass);
        customerInfo.setUpdateTime(DateUtil.date());
        customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
    }

    @Override
    public void changeWithdrawPass(WithdrawPassDTO dto) {
        if(StringUtils.isEmpty(dto.getOldPass())){
            throw new BusinessException("原密碼不可为空");
        }
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(dto.getCustomerId());
        if(!EncrpytionUtil.checkPassword(customerInfo.getCustomerNo(),dto.getOldPass(),customerInfo.getWithdrawPass()
                ,customerInfo.getSalt())){
            throw new BusinessException("原密碼不正确");
        }
        judgeAndUpdatePass(dto,customerInfo);
    }

    private void judgeAndUpdatePass(WithdrawPassDTO dto,CustomerInfo customerInfo){
        if(StringUtils.isEmpty(dto.getNewPass())){
            throw new BusinessException("新密碼不可为空");
        }
        if(!PatternVerifyUtil.validateNumber(dto.getNewPass())){
            throw new BusinessException("密碼只能为数字");
        }
        if(!dto.getNewPass().equals(dto.getConfirmPass())){
            throw new BusinessException("确认密碼不一致");
        }
        String salt = customerInfo.getSalt();
        String encryptPass = PasswordUtil.password(dto.getNewPass(),customerInfo.getCustomerNo(),salt);
        customerInfo.setWithdrawPass(encryptPass);
        customerInfo.setUpdateTime(DateUtil.date());
        customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
    }

    @Override
    public void sendSmsCode(String mobile) {
        if(!PatternVerifyUtil.isChinaPhoneLegal(mobile)){
            throw new BusinessException("不是大陆规范手机号");
        }
        customerInfoService.sendSmsCode(null,mobile,SmsConstant.RESET_WITHDRAW_PASS_KEY);
    }

    @Override
    public void resetWithdrawPass(WithdrawPassDTO dto) {
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(dto.getCustomerId());
        String cacheKey = SmsConstant.RESET_WITHDRAW_PASS_KEY + dto.getMobile();
        if(!RedisUtil.exists(cacheKey) || !RedisUtil.get(cacheKey).equals(dto.getSmsCode())){
            throw new BusinessException("验证码错误");
        }
        judgeAndUpdatePass(dto,customerInfo);
    }
}
