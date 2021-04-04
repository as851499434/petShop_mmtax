package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.ManagerTrxOrderDTO;
import com.mmtax.business.dto.TaxWithdrawAccountDTO;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.business.service.IPaymentService;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.constant.Constants;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.ali.AliCloudMarketUtils;
import com.mmtax.common.utils.onlinebank.OnlineCommonResultDTO;
import com.mmtax.system.domain.SysConfig;
import com.mmtax.system.mapper.SysConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.ITaxWithdrawAccountService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 税源地提现账户 服务层实现
 * 
 * @author meimiao
 * @date 2020-06-29
 */
@Service
@Slf4j
public class TaxWithdrawAccountServiceImpl implements ITaxWithdrawAccountService {
    /**
     * 三要素开启标志
     */
    private static final String CHECK_BANK_3C = "check_bank_3c";

    @Resource
    private OnlineAccountConfigMapper onlineAccountConfigMapper;
    @Resource
    private TaxSounrceCompanyMapper taxSounrceCompanyMapper;
    @Resource
    private MerchantInfoMapper merchantInfoMapper;
    @Resource
    private TaxWithdrawAccountMapper taxWithdrawAccountMapper;
    @Resource
    private MerchantAccountMapper merchantAccountMapper;
    @Autowired
    private IOnlineBankService onlineBankService;
    @Resource
    private TrxOrderMapper trxOrderMapper;
    @Resource
    private OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Autowired
    private IPaymentService paymentService;
    @Resource
    private SysConfigMapper sysConfigMapper;

    @Override
    public void withdrawAccountAddOrUpdate(TaxWithdrawAccount taxWithdrawAccount) {
        TaxWithdrawAccount judge = new TaxWithdrawAccount();
        judge.setTaxSourceCompanyId(taxWithdrawAccount.getTaxSourceCompanyId());
        judge = taxWithdrawAccountMapper.selectOne(judge);
        if(null != judge && null == taxWithdrawAccount.getId()){
            log.info("该税源地已存在提现账户");
            throw new BusinessException("该税源地已存在提现账户");
        }
        TaxSounrceCompany taxSounrceCompany = new TaxSounrceCompany();
        taxSounrceCompany.setId(taxWithdrawAccount.getTaxSourceCompanyId());
        taxSounrceCompany = taxSounrceCompanyMapper.selectOne(taxSounrceCompany);
        if(null == taxSounrceCompany){
            log.info("未找到该税源地，无法添加提现账户");
            throw new BusinessException("未找到该税源地，无法添加提现账户");
        }
        if(!"ONLINE".equals(taxSounrceCompany.getChannel())){
            log.info("不是网商的渠道，不允许添加提现账户");
            throw new BusinessException("不是网商的渠道，不允许添加提现账户");
        }
        //判断公司简称唯一
        TaxWithdrawAccount judgeCompanyName = new TaxWithdrawAccount();
        judgeCompanyName.setCompanyName(taxWithdrawAccount.getCompanyName());
        judgeCompanyName = taxWithdrawAccountMapper.selectOne(judgeCompanyName);
        if(null != taxWithdrawAccount.getId()){
            if(null != judgeCompanyName && !taxWithdrawAccount.getId().equals(judgeCompanyName.getId())){
                log.info("公司简称已存在");
                throw new BusinessException("公司简称已存在");
            }
        }else{
            if(null != judgeCompanyName){
                log.info("公司简称已存在");
                throw new BusinessException("公司简称已存在");
            }
        }

        OnlineAccountConfig onlineAccountConfig = new OnlineAccountConfig();
        onlineAccountConfig.setTaxSounrceId(taxWithdrawAccount.getTaxSourceCompanyId());
        onlineAccountConfig = onlineAccountConfigMapper.selectOne(onlineAccountConfig);
        MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfoById(onlineAccountConfig.getChargeCostMerId());
        taxWithdrawAccount.setTaxSourceName(taxSounrceCompany.getTaxSounrceCompanyName());
        taxWithdrawAccount.setChargeMerchantId(merchantInfo.getId());
        taxWithdrawAccount.setUpdateTime(DateUtil.date());
        if(null != taxWithdrawAccount.getId()){
            taxWithdrawAccountMapper.updateByPrimaryKeySelective(taxWithdrawAccount);
            return;
        }
        taxWithdrawAccount.setCreateTime(DateUtil.date());
        taxWithdrawAccountMapper.insertSelective(taxWithdrawAccount);
    }

    @Override
    public TaxWithdrawAccount withdrawAccountView(Integer id) {
        TaxWithdrawAccount taxWithdrawAccount = new TaxWithdrawAccount();
        taxWithdrawAccount.setId(id);
        taxWithdrawAccount = taxWithdrawAccountMapper.selectOne(taxWithdrawAccount);
        return taxWithdrawAccount;
    }

    @Override
    public List<TaxWithdrawAccountDTO> withdrawAccountListView(TaxWithdrawAccountDTO taxWithdrawAccountDTO) {
        List<TaxWithdrawAccount> taxWithdrawAccounts = taxWithdrawAccountMapper.selectByAttributes(taxWithdrawAccountDTO);
        List<TaxWithdrawAccountDTO> result = new ArrayList<>();
        taxWithdrawAccounts.forEach(one->{
            TaxWithdrawAccountDTO value = new TaxWithdrawAccountDTO();
            BeanUtils.copyProperties(one,value);
            value.setCreateTime(DateUtil.formatDateTime(one.getCreateTime()));
            MerchantAccount merchantAccount = merchantAccountMapper.getMerchantAccountByMerchantId(one.getChargeMerchantId());
            value.setMoney(merchantAccount.getUsableAmount());
            result.add(value);
        });
        return result;
    }

    @Override
    public void withdrawAccountDelete(Integer id) {
        TaxWithdrawAccount taxWithdrawAccount = new TaxWithdrawAccount();
        taxWithdrawAccount.setId(id);
        taxWithdrawAccount = taxWithdrawAccountMapper.selectOne(taxWithdrawAccount);
        if(null != taxWithdrawAccount) {
            taxWithdrawAccountMapper.deleteByPrimaryKey(taxWithdrawAccount);
        }
    }

    @Override
    public BigDecimal viewAccountMoney(Integer sourceId) {
        TaxWithdrawAccount taxWithdrawAccount = new TaxWithdrawAccount();
        taxWithdrawAccount.setTaxSourceCompanyId(sourceId);
        taxWithdrawAccount = taxWithdrawAccountMapper.selectOne(taxWithdrawAccount);
        MerchantAccount merchantAccount = merchantAccountMapper.getMerchantAccountByMerchantId(
                taxWithdrawAccount.getChargeMerchantId());
        return merchantAccount.getUsableAmount();
    }

    @Override
    public String withdraw(Integer sourceId, BigDecimal money) {
        TaxWithdrawAccount taxWithdrawAccount = new TaxWithdrawAccount();
        taxWithdrawAccount.setTaxSourceCompanyId(sourceId);
        taxWithdrawAccount = taxWithdrawAccountMapper.selectOne(taxWithdrawAccount);
        MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfoById(taxWithdrawAccount.getChargeMerchantId());
        MerchantAccount merchantAccount = merchantAccountMapper.getMerchantAccountByMerchantId(
                taxWithdrawAccount.getChargeMerchantId());
        //判断余额是否够提现
        if(merchantAccount.getUsableAmount().compareTo(money) < 0){
            log.info("商户{}余额不足以提现",merchantInfo.getId());
            throw new BusinessException("余额不足");
        }
        //添加打款记录
        TrxOrder trxOrder = new TrxOrder();
        trxOrder.setMerchantName(taxWithdrawAccount.getCompanyName());
        trxOrder.setAmount(money);
        trxOrder.setCharge(BigDecimal.ZERO);
        trxOrder.setActualAmount(money);
        trxOrder.setOrderStatus(TrxOrderStatusEnum.INIT.code);
        trxOrder.setOrderSerialNum(ChanPayUtil.generateOutTradeNo());
        trxOrder.setMerchantSerialNum(trxOrder.getOrderSerialNum());
        trxOrder.setSubjectConscription(merchantInfo.getSubject());
        trxOrder.setPaymentChannel("BANK");
        trxOrder.setMerchantId(taxWithdrawAccount.getChargeMerchantId());
        trxOrder.setAsyncStatus(AsyncStatusEnum.NOHANDLE.getStatus());
        trxOrder.setCreateTime(DateUtil.date());
        trxOrder.setUpdateTime(DateUtil.date());
        if(TaxWithdrawAccountPubOrPriEnum.PUBLIC.getValue().equals(taxWithdrawAccount.getPublicOrPrivate())){
            trxOrder.setPayeeName(taxWithdrawAccount.getBankAccountPublic());
            trxOrder.setPayeeBankCard(taxWithdrawAccount.getBankCardPublic());
            trxOrder.setBankName(taxWithdrawAccount.getBankPublic());
            trxOrderMapper.insertSelective(trxOrder);
        }else{
            trxOrder.setPayeeName(taxWithdrawAccount.getBankAccountPrivate());
            trxOrder.setPayeeBankCard(taxWithdrawAccount.getBankCardPrivate());
            trxOrder.setPayeeIdCardNo(taxWithdrawAccount.getIdCardPrivate());
            trxOrder.setBankName(taxWithdrawAccount.getBankPrivate());
            trxOrder.setPayeeMobile(taxWithdrawAccount.getMobilePrivate());
            trxOrderMapper.insertSelective(trxOrder);
            //三要素验证
            SysConfig sysConfig = new SysConfig();
            sysConfig.setConfigKey(CHECK_BANK_3C);
            sysConfig = sysConfigMapper.selectConfig(sysConfig);
            if (null != sysConfig && Constants.SWITCH_ON.equals(sysConfig.getConfigValue())) {
                log.info("{}进行三要素验证", trxOrder.getPayeeName());
                try {
                    AliCloudMarketUtils.checkBank3c(trxOrder.getPayeeName(), taxWithdrawAccount.getIdCardPrivate(),
                            trxOrder.getPayeeBankCard());
                }catch(BusinessException e){
                    log.info("三要输验证失败,payeeName="+trxOrder.getPayeeName()+",idcard="+
                            taxWithdrawAccount.getIdCardPrivate()+",bankcard="+trxOrder.getPayeeBankCard());
                    trxOrder.setOrderStatus(TrxOrderStatusEnum.PAID_PENDING.code);
                    trxOrder.setComment("三要输验证失败,订单挂起");
                    trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                    trxOrder.setUpdateTime(DateUtil.date());
                    trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
                    return "三要输验证失败,订单挂起";
                }
            }
        }
        //冻结账户金额并添加账户变动记录
        MerchantAccountDetail merchantAccountDetail =
                paymentService.convertMerchantAccount(money,merchantAccount,trxOrder,0,merchantInfo);
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantInfo.getId());
        //网商提现
        OnlineCommonResultDTO resultDTO = onlineBankService.payToTheCard(trxOrder,null,taxWithdrawAccount);
        if ("T".equals(resultDTO.getIs_success())){
            trxOrder.setUpdateTime(DateUtil.date());
            trxOrder.setOrderStatus(TrxOrderStatusEnum.UNPAID.code);
            trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
            return ResultEnum.SUCCESS.description;
        }
        //失败原因
        trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
        trxOrder.setComment(resultDTO.getError_code());
        trxOrder.setUpdateTime(DateUtil.date());
        //M为手动处理
        if("M".equals(resultDTO.getIs_success())){
            trxOrder.setOrderStatus(TrxOrderStatusEnum.UNPAID.code);
            trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
            return ResultEnum.FAIL.description;
        }
        trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
        //冻结金额返回可用余额，标记账户变动记录变动失败
        paymentService.unfreezeOperate(merchantInfo.getId(),money,BigDecimal.ZERO,merchantAccountDetail);
        return ResultEnum.FAIL.description;
    }

    @Override
    public List<ManagerTrxOrderDTO> getListTrxOrderOfSource(ManagerTrxOrderDTO dto) {
        return trxOrderMapper.getListTrxOrderOfSource(dto);
    }
}
