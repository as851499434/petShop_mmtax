package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.BatchWithDrawToCardDTO;
import com.mmtax.business.dto.NotifyDTO;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.*;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.constant.Constants;
import com.mmtax.common.constant.onlinebank.OnlineConstants;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.exception.PaymentException;
import com.mmtax.common.utils.DateUtils;
import com.mmtax.common.utils.http.HttpUtils;
import com.mmtax.common.utils.onlinebank.*;
import com.mmtax.common.utils.redis.RedisLockConstans;
import com.mmtax.common.utils.redis.RedisUtil;
import com.mmtax.system.domain.SysConfig;
import com.mmtax.system.mapper.SysConfigMapper;
import com.mmtax.system.service.ISysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
public class OnlineBankServiceImpl extends CommonServiceImpl implements IOnlineBankService {

    @Value("${online.payment.host}")
    private String HOST;
    @Value("${online.version}")
    private String VERSION;
    @Value("${online.charset}")
    private String CHARSET;
    @Value("${online.signType}")
    private String SIGNTYPE;
    @Value("${online.asynchronous.notify.url}")
    private String ASY_NOTIFY_URL;
    @Value("${qiyingyun.url}")
    private String QIYINGYUN_URL;
    @Autowired
    private OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Resource
    private TrxOrderMapper trxOrderMapper;
    @Resource
    private MerchantAccountMapper merchantAccountMapper;
    @Resource
    private MerchantAccountDetailMapper merchantAccountDetailMapper;
    @Autowired
    IMerchantAccountDetailService merchantAccountDetailService;
    @Autowired
    IRechargeRecordService rechargeRecordService;
    @Autowired
    private OnlineCustomerInfoMapper onlineCustomerInfoMapper;
    @Resource
    private MerchantInfoMapper merchantInfoMapper;
    @Resource
    private MerchantKeyMapper merchantKeyMapper;
    @Resource
    private OnlineAccountConfigMapper onlineAccountConfigMapper;
    @Autowired
    private IMerchantAccountService merchantAccountService;
    @Resource
    private SysConfigMapper sysConfigMapper;
    @Autowired
    private ISysConfigService sysConfigService;
    @Autowired
    private ICustomerAccountService customerAccountService;
    @Autowired
    private TransferOrderMapper transferOrderMapper;
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Resource
    private TaxWithdrawAccountMapper taxWithdrawAccountMapper;
    @Autowired
    private MakeUpChargeMapper makeUpChargeMapper;
    @Autowired
    private ReturnChargeOrderMapper returnChargeOrderMapper;
    @Autowired
    private CustomerBankcardInfoMapper customerBankcardInfoMapper;
    @Autowired
    private CustomerAlipayInfoMapper customerAlipayInfoMapper;
    @Autowired
    CustomerWithdrawMapper customerWithdrawMapper;
    @Autowired
    TradeRefundOrderMapper tradeRefundOrderMapper;
    @Resource
    NotifySendAgainMapper notifySendAgainMapper;
    @Autowired
    INotifyWkycService notifyWkycService;
    @Autowired
    private IOnlineBankService onlineBankService;

    private OnlineBankCommonDTO getCommonDTO(OnlineBankCommonDTO commonDTO,Integer taxSourceId){
        OnlineAccountConfig onlineAccountConfig = new OnlineAccountConfig();
        onlineAccountConfig.setTaxSounrceId(taxSourceId);
        onlineAccountConfig = onlineAccountConfigMapper.selectOne(onlineAccountConfig);
        commonDTO.setCharset(CHARSET);
        commonDTO.setSignType(SIGNTYPE);
        commonDTO.setVersion(VERSION);
        commonDTO.setPartnerId(onlineAccountConfig.getPartnerId());
        commonDTO.setNotifyUrl(ASY_NOTIFY_URL);
        commonDTO.setHost(HOST);
        commonDTO.setKeyStoreName(onlineAccountConfig.getKeyStoreName());
        return commonDTO;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public OnlinePaymentInfo registerAccount(Integer merchantId, MerchantInfo merchantInfo,Integer taxSourceId) {
        OnlinePaymentInfo onlinePaymentInfo = new OnlinePaymentInfo();
        onlinePaymentInfo.setMerchantId(merchantId);
        onlinePaymentInfo.setTaxSourceCompanyId(taxSourceId);
        onlinePaymentInfo.setCreateTime(DateUtil.date());
        onlinePaymentInfo.setUpdateTime(DateUtil.date());

        EnterpriseRegisterDTO dto = new EnterpriseRegisterDTO();
        dto = (EnterpriseRegisterDTO) getCommonDTO(dto,taxSourceId);
        dto.setUid(merchantInfo.getMerchantCode());
        dto.setEnterpriseName(merchantInfo.getMerchantName());
        dto.setUnifiedSocialCreditCode(merchantInfo.getBusinessLicenseCode());
        EnterPriseRegisterResultDTO resultDTO = IOnlineBankUtils.enterpriseRegister(dto);

        onlinePaymentInfo.setMerchantNo(merchantInfo.getMerchantCode());
        onlinePaymentInfo.setSubAccountNo(resultDTO.getSub_account_no());
        onlinePaymentInfoMapper.insertSelective(onlinePaymentInfo);
        return onlinePaymentInfo;
    }

    @Override
    public OnlineCommonResultDTO modifyAccount(Integer merchantId, MerchantInfo merchantInfo,Integer taxSourceId) {
        EnterpriseRegisterDTO dto = new EnterpriseRegisterDTO();
        dto = (EnterpriseRegisterDTO) getCommonDTO(dto,taxSourceId);
        dto.setUid(merchantInfo.getMerchantCode());
        dto.setEnterpriseName(merchantInfo.getMerchantName());
        dto.setUnifiedSocialCreditCode(merchantInfo.getBusinessLicenseCode());
        return IOnlineBankUtils.enterpriseModify(dto);
    }

    @Override
    public OnlineCustomerInfo personalRegister(Integer taxSourceId, CustomerInfo customerInfo) {
        OnlineCustomerInfo onlineCustomerInfo = new OnlineCustomerInfo();
        onlineCustomerInfo.setCustomerId(customerInfo.getId());
        onlineCustomerInfo.setCustomerNo(customerInfo.getCustomerNo());
        onlineCustomerInfo.setTaxSourceId(taxSourceId);

        PersonalRegisterDTO dto = new PersonalRegisterDTO();
        dto = (PersonalRegisterDTO) getCommonDTO(dto,onlineCustomerInfo.getTaxSourceId());
        dto.setUid(customerInfo.getCustomerNo());
        dto.setMemberName(customerInfo.getMemberName());
        dto.setRealName(customerInfo.getRealName());
        dto.setCertificateType(customerInfo.getCertificateType());
        dto.setCertificateNo(customerInfo.getCertificateNo());
        dto.setMobile(customerInfo.getMobile());
        SysConfig sysConfigVerifiedRealName = new SysConfig();
        sysConfigVerifiedRealName.setConfigKey(Constants.VERIFY_REALNAME);
        sysConfigVerifiedRealName = sysConfigMapper.selectConfig(sysConfigVerifiedRealName);
        dto.setIsVerify(sysConfigVerifiedRealName.getConfigValue());

        PersonalRegisterResultDTO resultDTO = IOnlineBankUtils.personalRegister(dto);
        if(!"T".equals(resultDTO.getIs_success())){
            InternetBusinessFailReasonEnum failReasonEnum =
                    InternetBusinessFailReasonEnum.getByEnumName(resultDTO.getError_code());
            log.info("灵工{}注册失败：{}",customerInfo.getRealName(),failReasonEnum.getDescription());
            throw new PaymentException(failReasonEnum);
        }
        onlineCustomerInfo.setSubAccountNo(resultDTO.getSub_account_no());
        onlineCustomerInfo.setCreateTime(DateUtil.date());
        onlineCustomerInfo.setUpdateTime(DateUtil.date());
        onlineCustomerInfoMapper.insertSelective(onlineCustomerInfo);
        customerInfo.setVerifyStatus(VerifyStatusEnum.VERIFY.getStatus());
        customerInfo.setEffective(EffectiveEnum.EFFECTIVE.getStatus());
        customerInfo.setUpdateTime(DateUtil.date());
        customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
        return onlineCustomerInfo;
    }

    @Override
    public CardbinQueryResultDTO.CardBinInfo cardbinQuery(String bankAccountNo,Integer customerId) {
        OnlineCustomerInfo onlineCustomerInfo = onlineCustomerInfoMapper.selectByCustomerIdCustomerNo(
                customerId,null);
        CardbinQueryDTO dto = new CardbinQueryDTO();
        dto = (CardbinQueryDTO) getCommonDTO(dto,onlineCustomerInfo.getTaxSourceId());
        dto.setBankAccountNo(bankAccountNo);
        CardbinQueryResultDTO resultDTO = IOnlineBankUtils.cardbinQuery(dto);
        if(!"T".equals(resultDTO.getIs_success())){
            log.info("银行卡{}查询卡bin失败：{}",bankAccountNo,resultDTO.getError_message()+","+resultDTO.getMemo());
            throw new PaymentException(InternetBusinessFailReasonEnum.getByEnumName(resultDTO.getError_code()));
        }
        return resultDTO.getCard_bin_info();
    }

    @Override
    public String bankcardBind(CustomerBankcardInfo customerBankcardInfo) {
        OnlineCustomerInfo onlineCustomerInfo = onlineCustomerInfoMapper.selectByCustomerIdCustomerNo(
                customerBankcardInfo.getCustomerId(),null);
        BankcardBindDTO dto = new BankcardBindDTO();
        dto = (BankcardBindDTO) getCommonDTO(dto,onlineCustomerInfo.getTaxSourceId());
        dto.setUid(customerBankcardInfo.getCustomerNo());
        dto.setBankName(customerBankcardInfo.getBankName());
        dto.setBankAccountNo(customerBankcardInfo.getBankAccountNo());
        dto.setAccountName(customerBankcardInfo.getAccountName());
        dto.setCardType(customerBankcardInfo.getCardType());
        dto.setCardAttribute(OnlineConstants.CARD_ATTRIBUTE);
        dto.setVerifyType(String.valueOf(customerBankcardInfo.getVerifyType()));
        dto.setCertificateType(customerBankcardInfo.getCertificateType());
        dto.setCertificateNo(customerBankcardInfo.getCertificateNo());
        dto.setReservedMobile(customerBankcardInfo.getReservedMobile());
        BankcardBindResultDTO resultDTO = IOnlineBankUtils.bankcardBind(dto);

        if(!"T".equals(resultDTO.getIs_success())){
            log.info("银行卡{}绑定失败：{}",customerBankcardInfo.getBankAccountNo()
                    ,resultDTO.getError_message()+","+resultDTO.getMemo());
            InternetBusinessFailReasonEnum failReasonEnum =
                    InternetBusinessFailReasonEnum.getByEnumName(resultDTO.getError_code());
            String message = failReasonEnum.getDescription();
            if(!StringUtils.isEmpty(resultDTO.getMemo())
                    && InternetBusinessFailReasonEnum.SYSTEM_ERROR.equals(failReasonEnum)){
                message = resultDTO.getMemo();
            }
            throw new PaymentException(InternetBusinessFailReasonEnum.getByEnumName(message));
        }
        return resultDTO.getBank_id();
    }

    @Override
    public String alipayBind(CustomerAlipayInfo customerAlipayInfo) {
        OnlineCustomerInfo onlineCustomerInfo = onlineCustomerInfoMapper.selectByCustomerIdCustomerNo(
                customerAlipayInfo.getCustomerId(),null);
        AlipayBindDTO dto = new AlipayBindDTO();
        dto = (AlipayBindDTO) getCommonDTO(dto,onlineCustomerInfo.getTaxSourceId());
        dto.setUid(customerAlipayInfo.getCustomerNo());
        dto.setAccountNo(customerAlipayInfo.getAccountNo());
        dto.setAccountName(customerAlipayInfo.getAccountName());
        dto.setCertificateNo(customerAlipayInfo.getCertificateNo());
        dto.setMobileNo(customerAlipayInfo.getMobileNo());
        String isVerify = sysConfigService.selectConfigByKey(Constants.IS_VERIFY);
        dto.setIsVerify(isVerify);
        AlipayBindResultDTO resultDTO = IOnlineBankUtils.alipayBind(dto);
        if(!"T".equals(resultDTO.getIs_success())){
            log.info("支付宝{}绑定失败：{}",customerAlipayInfo.getAccountNo()
                    ,resultDTO.getError_message()+","+resultDTO.getMemo());
            throw new PaymentException(InternetBusinessFailReasonEnum.getByEnumName(resultDTO.getError_code()));
        }
        return resultDTO.getBank_id();
    }

    @Override
    public boolean bankcardOrAlipayUnbind(Integer bankInfoId, Integer alipayInfoId) {
        Integer customerId;
        String bankId;
        if(null != bankInfoId){
            CustomerBankcardInfo bankcardInfo = customerBankcardInfoMapper.selectByPrimaryKey(bankInfoId);
            if(null == bankcardInfo){
                throw new BusinessException("该绑卡信息不存在");
            }
            customerId = bankcardInfo.getCustomerId();
            bankId = bankcardInfo.getBankId();
        }else{
            CustomerAlipayInfo alipayInfo = customerAlipayInfoMapper.selectByPrimaryKey(alipayInfoId);
            if(null == alipayInfo){
                throw new BusinessException("该绑定支付宝信息不存在");
            }
            customerId = alipayInfo.getCustomerId();
            bankId = alipayInfo.getBankId();
        }
        OnlineCustomerInfo onlineCustomerInfo = onlineCustomerInfoMapper.selectByCustomerIdCustomerNo(
                customerId,null);
        BankcardUnbindDTO dto = new BankcardUnbindDTO();
        dto = (BankcardUnbindDTO)getCommonDTO(dto,onlineCustomerInfo.getTaxSourceId());
        dto.setUid(onlineCustomerInfo.getCustomerNo());
        dto.setBankId(bankId);
        IOnlineBankUtils.bankcardOrAlipayUnbind(dto);
        return true;
    }

    @Override
    public OnlineCommonResultDTO transferAmountFromMerToCus(TransferOrder transferOrder,BigDecimal overCharge) {
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(
                transferOrder.getMerchantId());
        OnlineCustomerInfo onlineCustomerInfo = onlineCustomerInfoMapper.selectByCustomerIdCustomerNo(
                transferOrder.getCustomerId(),null);
        PayInstantDTO dto = new PayInstantDTO();
        dto = (PayInstantDTO) getCommonDTO(dto,onlinePaymentInfo.getTaxSourceCompanyId());
        dto.setBuyerId(onlinePaymentInfo.getMerchantNo());
        dto.setSellerId(onlineCustomerInfo.getCustomerNo());
        dto.setOuterTradeNo(transferOrder.getTransferSerialNum());
        dto.setSubject("商户"+transferOrder.getMerchantId()+"向灵工"+transferOrder.getCustomerId()+"转账");
        //服务费扣去我们补偿的overcharge
        dto.setPrice(transferOrder.getAmount());
        BigDecimal charge = transferOrder.getCharge().subtract(overCharge);
        if(charge.compareTo(BigDecimal.ZERO) > 0) {
            Map<String, String> feeInfo = new HashMap<>();
            feeInfo.put("buyerFee", charge.toString());
            dto.setFeeInfo(JSON.toJSONString(feeInfo));
        }
        dto.setQuantity(BigDecimal.valueOf(1));
        dto.setAccountType(OnlineConstants.ACCOUNT_TYPE);
        return IOnlineBankUtils.payInstant(dto,null);
    }

    @Override
    public OnlineCommonResultDTO transferAmountFromCusToMer(TransferOrder transferOrder) {
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(
                transferOrder.getMerchantId());
        OnlineCustomerInfo onlineCustomerInfo = onlineCustomerInfoMapper.selectByCustomerIdCustomerNo(
                transferOrder.getCustomerId(),null);
        PayInstantDTO dto = new PayInstantDTO();
        dto = (PayInstantDTO) getCommonDTO(dto,onlinePaymentInfo.getTaxSourceCompanyId());
        dto.setBuyerId(onlineCustomerInfo.getCustomerNo());
        dto.setSellerId(onlinePaymentInfo.getMerchantNo());
        dto.setOuterTradeNo(transferOrder.getTransferSerialNum());
        dto.setSubject("员工"+transferOrder.getCustomerId()+"向商户"+transferOrder.getMerchantId()+"转账");
        dto.setPrice(transferOrder.getActualAmount());
        dto.setQuantity(BigDecimal.valueOf(1));
        dto.setAccountType(OnlineConstants.ACCOUNT_TYPE);
        return IOnlineBankUtils.payInstant(dto,null);
    }

    @Override
    public OnlineCommonResultDTO refundTransferOrder(TradeRefundOrder tradeRefundOrder) {
        LinkRecordTypeEnum linkRecordType = LinkRecordTypeEnum.getEnumByType(tradeRefundOrder.getLinkRecordType());
        if(null == linkRecordType){
            throw new BusinessException("系统错误");
        }
        Integer taxSourceId = null;
        switch(linkRecordType){
            case TRANFER_ORDER:
                TransferOrder transferOrder = transferOrderMapper.selectByTransferSerialNum(
                        tradeRefundOrder.getLinkSerialNum());
                OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(
                        transferOrder.getMerchantId());
                taxSourceId = onlinePaymentInfo.getTaxSourceCompanyId();
                break;
            default:
        }
        TradeRefundDTO dto = new TradeRefundDTO();
        dto = (TradeRefundDTO) getCommonDTO(dto,taxSourceId);
        dto.setOuterTradeNo(tradeRefundOrder.getRefundSerialNum());
        dto.setOrigOuterTradeNo(tradeRefundOrder.getLinkSerialNum());
        dto.setRefundAmount(tradeRefundOrder.getAmount().toString());
        if(tradeRefundOrder.getCharge().compareTo(BigDecimal.ZERO) > 0) {
            Map<String, String> refundFeeInfo = new HashMap<>(2);
            refundFeeInfo.put("buyerRefundFee", tradeRefundOrder.getCharge().toString());
            dto.setRefundFeeInfo(JSON.toJSONString(refundFeeInfo));
        }
        return IOnlineBankUtils.tradeRefund(dto);
    }

    @Override
    public OnlineCommonResultDTO tradePayToCard(TrxOrder trxOrder) {
        OnlineCustomerInfo onlineCustomerInfo = onlineCustomerInfoMapper.selectByCustomerIdCustomerNo(
                trxOrder.getCustomerId(),null);
        OnlineAccountConfig onlineAccountConfig = new OnlineAccountConfig();
        onlineAccountConfig.setTaxSounrceId(onlineCustomerInfo.getTaxSourceId());
        onlineAccountConfig = onlineAccountConfigMapper.selectOne(onlineAccountConfig);

        TradePaytocardDTO dto = new TradePaytocardDTO();
        dto = (TradePaytocardDTO) getCommonDTO(dto,onlineAccountConfig.getTaxSounrceId());
        dto.setOuterInstOrderNo(trxOrder.getOrderSerialNum());
        dto.setUid(onlineCustomerInfo.getCustomerNo());
        dto.setWhiteChannelCode(onlineAccountConfig.getWhiteChannelCode());
        dto.setOuterTradeNo(trxOrder.getOrderSerialNum());
        dto.setAccountType(OnlineConstants.ACCOUNT_TYPE);
        dto.setBankId(trxOrder.getBankId());
        dto.setAmount(trxOrder.getActualAmount().toString());
        if (!StringUtils.isEmpty(trxOrder.getMemo())) {
            dto.setMemo(trxOrder.getMemo());
        }
        //手续费信息
//        dto.setFeeInfo(null);
//        if(UseBigRateEnum.NORMALRATE.getStatus().equals(trxOrder.getUseBigRate())) {
//            if (trxOrder.getCharge().compareTo(BigDecimal.ZERO) > 0) {
//                Map<String, String> feeInfo = new HashMap<>(16);
//                feeInfo.put("buyerFee", trxOrder.getCharge().toString());
//                dto.setFeeInfo(JSON.toJSONString(feeInfo));
//            }
//        }else{
//            BigDecimal bigCharge = trxOrder.getChargeBig();
//            if (bigCharge.compareTo(BigDecimal.ZERO) > 0) {
//                Map<String, String> feeInfo = new HashMap<>(16);
//                feeInfo.put("buyerFee", bigCharge.toString());
//                dto.setFeeInfo(JSON.toJSONString(feeInfo));
//            }
//        }
        return IOnlineBankUtils.tradePaytocard(dto);
    }

    @Override
    public OnlineCommonResultDTO customerWithdraw(CustomerWithdraw customerWithdraw) {
        OnlineCustomerInfo onlineCustomerInfo = onlineCustomerInfoMapper.selectByCustomerIdCustomerNo(
                customerWithdraw.getCustomerId(),null);
        OnlineAccountConfig onlineAccountConfig = new OnlineAccountConfig();
        onlineAccountConfig.setTaxSounrceId(onlineCustomerInfo.getTaxSourceId());
        onlineAccountConfig = onlineAccountConfigMapper.selectOne(onlineAccountConfig);

        TradePaytocardDTO dto = new TradePaytocardDTO();
        dto = (TradePaytocardDTO) getCommonDTO(dto,onlineAccountConfig.getTaxSounrceId());
        dto.setOuterInstOrderNo(customerWithdraw.getWithdrawSerialNum());
        dto.setUid(onlineCustomerInfo.getCustomerNo());
        dto.setWhiteChannelCode(onlineAccountConfig.getWhiteChannelCode());
        dto.setOuterTradeNo(customerWithdraw.getWithdrawSerialNum());
        dto.setAccountType(OnlineConstants.ACCOUNT_TYPE);
        dto.setBankId(customerWithdraw.getBankId());
        dto.setAmount(customerWithdraw.getAmount().toString());
        //不收手续费信息
        return IOnlineBankUtils.tradePaytocard(dto);
    }

    @Override
    public OnlineCommonResultDTO payToTheCard(TrxOrder trxOrder, OnlineCustomerInfo onlineCustomerInfo,
                                              TaxWithdrawAccount taxWithdrawAccount) {
        WithdrawToCardDTO dto = new WithdrawToCardDTO();
        dto = (WithdrawToCardDTO) getCommonDTO(dto,onlineCustomerInfo.getTaxSourceId());
        dto.setOuterTradeNo(trxOrder.getOrderSerialNum());
        dto.setUid(onlineCustomerInfo.getCustomerNo());
        dto.setOuterInstOrderNo(trxOrder.getOrderSerialNum());
        OnlineAccountConfig onlineAccountConfig = new OnlineAccountConfig();
        onlineAccountConfig.setTaxSounrceId(onlineCustomerInfo.getTaxSourceId());
        onlineAccountConfig = onlineAccountConfigMapper.selectOne(onlineAccountConfig);
        dto.setWhiteChannelCode(onlineAccountConfig.getWhiteChannelCode());
        dto.setAccountType(OnlineConstants.ACCOUNT_TYPE);
        dto.setBankAccountNo(trxOrder.getPayeeBankCard());
        dto.setAccountName(trxOrder.getPayeeName());
        dto.setBankName(trxOrder.getBankName());
        dto.setCardType(OnlineConstants.CARD_TYPE);
        dto.setCardAttribute(OnlineConstants.CARD_ATTRIBUTE);
        if (null != taxWithdrawAccount &&
                TaxWithdrawAccountPubOrPriEnum.PUBLIC.getValue().equals(taxWithdrawAccount.getPublicOrPrivate())) {
            dto.setCardAttribute(OnlineConstants.CARD_ATTRIBUTE_PUBLIC);
            dto.setBankLineNo(taxWithdrawAccount.getReservedFieldOne());
            dto.setBankBranch(taxWithdrawAccount.getBankNamePublic());
        }
        dto.setAmount(trxOrder.getActualAmount().toString());
        //手续费信息
        Map<String,String> feeInfo = new HashMap<>(16);
        feeInfo.put("buyerFee",trxOrder.getCharge().toString());
        dto.setFeeInfo(JSON.toJSONString(feeInfo));
        return IOnlineBankUtils.withdrawtocard(dto);
    }

    @Override
    public OnlineCommonResultDTO batchwithdrawtocard(Integer taxSourceId, String batchPaymentSerialNum
            , String merchantCode, BatchWithDrawToCardDTO batchWithDrawToCard) {
        OnlineAccountConfig onlineAccountConfig = new OnlineAccountConfig();
        onlineAccountConfig.setTaxSounrceId(taxSourceId);
        onlineAccountConfig = onlineAccountConfigMapper.selectOne(onlineAccountConfig);
        BatchPaymentDTO dto = new BatchPaymentDTO();
        dto = (BatchPaymentDTO) getCommonDTO(dto,taxSourceId);
        dto.setOuterTradeNo(batchPaymentSerialNum);
        StringBuilder filePath = new StringBuilder(onlineAccountConfig.getFileDirectory());
        filePath = filePath.append("/").append(batchWithDrawToCard.getBatchDate()).append("/");
        dto.setFilePath(filePath.toString());
        dto.setFileName(batchWithDrawToCard.getFileName());
        dto.setUid(merchantCode);
        dto.setWhiteChannelCode(onlineAccountConfig.getWhiteChannelCode());
        dto.setTotalAmount(batchWithDrawToCard.getTotalAmount().toString());
        dto.setTotalCount(batchWithDrawToCard.getTotalCount().toString());
        return IOnlineBankUtils.onlineBatchPayment(dto);
    }

    @Override
    public OnlineCommonResultDTO payToTheCardForTaxSource(TrxOrder trxOrder, Integer taxSourceId, String uid) {
        TaxWithdrawAccount taxWithdrawAccount = new TaxWithdrawAccount();
        taxWithdrawAccount.setTaxSourceCompanyId(taxSourceId);
        taxWithdrawAccount = taxWithdrawAccountMapper.selectOne(taxWithdrawAccount);

        WithdrawToCardDTO dto = new WithdrawToCardDTO();
        dto = (WithdrawToCardDTO) getCommonDTO(dto, taxSourceId);
        dto.setOuterTradeNo(trxOrder.getOrderSerialNum());
        dto.setUid(uid);
        dto.setOuterInstOrderNo(trxOrder.getOrderSerialNum());

        OnlineAccountConfig onlineAccountConfig = new OnlineAccountConfig();
        onlineAccountConfig.setTaxSounrceId(taxSourceId);
        onlineAccountConfig = onlineAccountConfigMapper.selectOne(onlineAccountConfig);

        dto.setWhiteChannelCode(onlineAccountConfig.getWhiteChannelCode());
        dto.setAccountType(OnlineConstants.ACCOUNT_TYPE);
        dto.setBankAccountNo(trxOrder.getPayeeBankCard());
        dto.setAccountName(trxOrder.getPayeeName());
        dto.setBankName(trxOrder.getBankName());
        dto.setCardType(OnlineConstants.CARD_TYPE);
        dto.setCardAttribute(OnlineConstants.CARD_ATTRIBUTE);
        dto.setCardAttribute(OnlineConstants.CARD_ATTRIBUTE_PUBLIC);
        dto.setBankLineNo(taxWithdrawAccount.getReservedFieldOne());
        dto.setBankBranch(taxWithdrawAccount.getBankNamePublic());
        dto.setAmount(trxOrder.getActualAmount().toString());
        return IOnlineBankUtils.withdrawtocardForTaxSource(dto);
    }

    @Override
    public OnlineCommonResultDTO payToTheCardForMerchant(TrxOrder trxOrder, Integer taxSourceId, String uid,
                                                         String bankLineNo, String bankBranch) {

        WithdrawToCardDTO dto = new WithdrawToCardDTO();
        dto = (WithdrawToCardDTO) getCommonDTO(dto, taxSourceId);
        dto.setOuterTradeNo(trxOrder.getOrderSerialNum());
        dto.setUid(uid);
        dto.setOuterInstOrderNo(trxOrder.getOrderSerialNum());

        OnlineAccountConfig onlineAccountConfig = new OnlineAccountConfig();
        onlineAccountConfig.setTaxSounrceId(taxSourceId);
        onlineAccountConfig = onlineAccountConfigMapper.selectOne(onlineAccountConfig);

        dto.setWhiteChannelCode(onlineAccountConfig.getWhiteChannelCode());
        dto.setAccountType(OnlineConstants.ACCOUNT_TYPE);
        dto.setBankAccountNo(trxOrder.getPayeeBankCard());
        dto.setAccountName(trxOrder.getPayeeName());
        dto.setBankName(trxOrder.getBankName());
        dto.setCardType(OnlineConstants.CARD_TYPE);
        dto.setCardAttribute(OnlineConstants.CARD_ATTRIBUTE);
        dto.setCardAttribute(OnlineConstants.CARD_ATTRIBUTE_PUBLIC);
        dto.setBankLineNo(bankLineNo);
        dto.setBankBranch(bankBranch);
        dto.setAmount(trxOrder.getActualAmount().toString());
        return IOnlineBankUtils.withdrawtocardForTaxSource(dto);
    }

    @Override
    public void payToTheCardForMerchantAddInfo(String uid, String amount, TrxOrder trxOrder, String taxSourceId) {
        Integer merchantId = merchantInfoMapper.selectMerchantIdByMerchantCode(uid);
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);
        TaxSounrceCompany taxSounrceCompany = taxSounrceCompanyMapper.selectByPrimaryKey(taxSourceId);
        //插入订单记录
        trxOrder.setSubjectConscription(taxSounrceCompany.getTaxSounrceCompanyName());
        trxOrder.setAmount(new BigDecimal(amount));
        trxOrder.setMerchantId(merchantId);
        trxOrder.setMerchantName(merchantInfo.getMerchantName());
        //将订单的异步处理设置为已处理
        trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
        trxOrderMapper.insertSelective(trxOrder);
        //交易之前的商户账户信息
        MerchantAccount accountBefore = merchantAccountMapper.getMerchantAccountByMerchantId(merchantId);
        //交易之后的商户账号信息
        BigDecimal afterUsableAmount = accountBefore.getUsableAmount().subtract(new BigDecimal(amount));
        if (afterUsableAmount.compareTo(BigDecimal.ZERO) < 0) {
            AccountBalanceResultDTO data =
                    onlineBankService.accountBalance(merchantId,null,null,null);
            List<AccountBalanceResultDTO.AccountInfo> balance = data.getAccount_list();
            accountBefore.setUsableAmount(new BigDecimal(balance.get(0).getAvailable_balance()));
            accountBefore.setUpdateTime(new Date());
            merchantAccountMapper.updateByPrimaryKeySelective(accountBefore);
            throw new BusinessException("可用余额不足，正在刷新余额");
        }
        accountBefore.setUsableAmount(afterUsableAmount);
        accountBefore.setUpdateTime(new Date());
        merchantAccountMapper.updateByPrimaryKeySelective(accountBefore);
        MerchantAccount accountAfter = merchantAccountMapper.getMerchantAccountByMerchantId(merchantId);
        //记录交易资金流水
        merchantAccountDetailService.insertMerchantAccountDetail(new BigDecimal(amount), accountBefore, accountAfter
                , AccountDetailStatusEnum.SUCCESS.code, trxOrder.getOrderSerialNum()
                , MerchantAccountDetailTypeEnum.TRANSFER.code, DetailTypeEnum.GET.getCode());
    }

    @Override
    public AccountBalanceResultDTO accountBalance(Integer merchantId,Integer customerId,Integer taxSourceId
            ,String accountType) {
        String uid = null;
        Integer taxSourId = null;
        if(null != merchantId) {
            OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
            uid = onlinePaymentInfo.getMerchantNo();
            taxSourId = onlinePaymentInfo.getTaxSourceCompanyId();
        }
        if(null != customerId){
            OnlineCustomerInfo onlineCustomerInfo = onlineCustomerInfoMapper.selectByCustomerIdCustomerNo(customerId,null);
            uid = onlineCustomerInfo.getCustomerNo();
            taxSourId = onlineCustomerInfo.getTaxSourceId();
        }
        AccountBalanceDTO dto = new AccountBalanceDTO();
        if(!StringUtils.isEmpty(uid) && null != taxSourId) {
            dto = (AccountBalanceDTO) getCommonDTO(dto, taxSourId);
            dto.setUid(uid);
            dto.setAccountType(OnlineConstants.ACCOUNT_TYPE);
        } else{
            //查询平台收款户余额
            dto = (AccountBalanceDTO) getCommonDTO(dto, taxSourceId);
            OnlineAccountConfig onlineAccountConfig = onlineAccountConfigMapper.selectByTaxSourceId(taxSourceId);
            dto.setUid(onlineAccountConfig.getRegisterEmail());
            if(!StringUtils.isEmpty(accountType)){
                dto.setAccountType(accountType);
            }
        }
        return IOnlineBankUtils.accountBalance(dto);
    }

    @Override
    public OnlineCommonResultDTO transferAmountFromPbcsicToMer(Integer taxSourceId, Integer merId, BigDecimal money) {
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merId);
        if(null == onlinePaymentInfo){
            throw new BusinessException("未查询到该商户子账号");
        }
        OnlineAccountConfig onlineAccountConfig = onlineAccountConfigMapper.selectByTaxSourceId(taxSourceId);
        PayInstantDTO dto = new PayInstantDTO();
        dto = (PayInstantDTO) getCommonDTO(dto,taxSourceId);
        dto.setBuyerId(onlineAccountConfig.getRegisterEmail());
        dto.setSellerId(onlinePaymentInfo.getMerchantNo());
        dto.setOuterTradeNo(ChanPayUtil.generateOutTradeNo());
        dto.setSubject("平台基本户"+onlineAccountConfig.getRegisterEmail()+"向商户"+merId+"转账");
        dto.setPrice(money);
        dto.setQuantity(BigDecimal.valueOf(1));
        dto.setAccountType(OnlineConstants.ACCOUNT_TYPE);
        return IOnlineBankUtils.payInstant(dto,OnlineConstants.PBSIC_ACCOUNT);
    }

    @Override
    public OnlineCommonResultDTO transferAmountFromMerToPincome(Integer merId, BigDecimal money, String tradeNo) {
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merId);
        if(null == onlinePaymentInfo){
            throw new BusinessException("未查询到该商户子账号");
        }
        Integer taxSourceId = onlinePaymentInfo.getTaxSourceCompanyId();
        OnlineAccountConfig onlineAccountConfig = onlineAccountConfigMapper.selectByTaxSourceId(taxSourceId);
        PayInstantDTO dto = new PayInstantDTO();
        dto = (PayInstantDTO) getCommonDTO(dto,taxSourceId);
        dto.setBuyerId(onlinePaymentInfo.getMerchantNo());
        dto.setSellerId(onlineAccountConfig.getRegisterEmail());
        dto.setOuterTradeNo(tradeNo);
        dto.setSubject("商户"+merId+"向收入户"+onlineAccountConfig.getRegisterEmail()+"补交服务费");
        dto.setPrice(money);
        dto.setQuantity(BigDecimal.valueOf(1));
        dto.setAccountType(OnlineConstants.PINCOME_ACCOUNT);
        return IOnlineBankUtils.payInstant(dto,null);
    }

    @Override
    public OnlineCommonResultDTO transferAmountFromPincomeToMer(Integer merId, BigDecimal money, String tradeNo) {
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merId);
        if(null == onlinePaymentInfo){
            throw new BusinessException("未查询到该商户子账号");
        }
        Integer taxSourceId = onlinePaymentInfo.getTaxSourceCompanyId();
        OnlineAccountConfig onlineAccountConfig = onlineAccountConfigMapper.selectByTaxSourceId(taxSourceId);
        PayInstantDTO dto = new PayInstantDTO();
        dto = (PayInstantDTO) getCommonDTO(dto,taxSourceId);
        dto.setBuyerId(onlineAccountConfig.getRegisterEmail());
        dto.setSellerId(onlinePaymentInfo.getMerchantNo());
        dto.setOuterTradeNo(tradeNo);
        dto.setSubject("收入户"+onlineAccountConfig.getRegisterEmail()+"向商户"+merId+"退还服务费");
        dto.setPrice(money);
        dto.setQuantity(BigDecimal.valueOf(1));
        dto.setAccountType(OnlineConstants.ACCOUNT_TYPE);
        return IOnlineBankUtils.payInstant(dto,OnlineConstants.PINCOME_ACCOUNT);
    }

    @Override
    public InfoQueryResultDTO infoQuery(Integer merchantId,String orderSerialNum) {
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
        log.info("onlinePaymentInfo:{}",onlinePaymentInfo);
        InfoQueryDTO dto = new InfoQueryDTO();
        dto = (InfoQueryDTO) getCommonDTO(dto,onlinePaymentInfo.getTaxSourceCompanyId());
        dto.setOuterTradeNo(orderSerialNum);
        return IOnlineBankUtils.infoQuery(dto);
    }

    @Override
    public AccountQueryResultDTO accountQuery(Integer merchantId,String startTime,String endTime,String page) {
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
        AccountQueryDTO dto = new AccountQueryDTO();
        dto = (AccountQueryDTO) getCommonDTO(dto,onlinePaymentInfo.getTaxSourceCompanyId());
        dto.setUid(onlinePaymentInfo.getMerchantNo());
        dto.setAccountType(OnlineConstants.ACCOUNT_TYPE);
        dto.setCurrentPage(page);
        dto.setStartTime(startTime);
        dto.setEndTime(endTime);
        return IOnlineBankUtils.accountQuery(dto);
    }

    private String sendToQiyingyun(Map<String,String> param,String surilNum){
        try{
            //发送到启盈云
            log.info("充值单号{}属于启盈云，转发到启盈云",surilNum);
            return HttpUtils.sendPostByForm(QIYINGYUN_URL,param);
        }catch (Exception ignored){
            return "fail";
        }
    }

    @Override
    public String receiveTranctionNotify(Map<String, String> paramConvert,Map<String,String> map){
        SysConfig sysConfigPaymentAllow = new SysConfig();
        sysConfigPaymentAllow.setConfigKey(Constants.PAYMENT_ALLOW);
        sysConfigPaymentAllow = sysConfigMapper.selectConfig(sysConfigPaymentAllow);
        logger.info("sysConfigPaymentAllow configValue:{}", sysConfigPaymentAllow == null ? "" :
                sysConfigPaymentAllow.getConfigValue());
        if(null != sysConfigPaymentAllow && Constants.SWITCH_OFF.equals(sysConfigPaymentAllow.getConfigValue())) {
            return "fail";
        }
        OnlinePaymentInfo onlinePaymentInfo = null;
        Integer taxSourceId = null;
        switch(paramConvert.get("notify_type")){
            case OnlineConstants.REFUND_STATUS_SYNC:
                String refundSerilNum = paramConvert.get("outer_trade_no");
                TradeRefundOrder tradeRefundOrder = tradeRefundOrderMapper.selectByRefundSerialNum(refundSerilNum);
                if(null != tradeRefundOrder){
                    taxSourceId = tradeRefundOrder.getTaxSourceId();
                }
                break;
            case OnlineConstants.WITHDRAWAL_STATUS_SYNC:
                OnlineCustomerInfo onlineCustomerInfo = onlineCustomerInfoMapper.selectByCustomerIdCustomerNo(
                        null,paramConvert.get("uid"));
                log.info("onlineCustomerInfo={}",onlineCustomerInfo);
                if(null != onlineCustomerInfo){
                    taxSourceId = onlineCustomerInfo.getTaxSourceId();
                }
                break;
            case OnlineConstants.REMIT_SYNC:
                String subAccountNo = paramConvert.get("subAccount");
                String acountPrefix = subAccountNo.substring(0,11);
                log.info("充值账户前缀：{}",acountPrefix);
                OnlineAccountConfig onlineAccountConfig = new OnlineAccountConfig();
                onlineAccountConfig.setAccountPrefix(acountPrefix);
                List<OnlineAccountConfig> onlineAccountConfigs = onlineAccountConfigMapper.select(onlineAccountConfig);
                subAccountNo =  subAccountNo.substring(subAccountNo.length()-8);
                if(CollectionUtils.isEmpty(onlineAccountConfigs)){
                    //发到启盈云
                    return sendToQiyingyun(map,paramConvert.get("outer_trade_no"));
                }
                boolean isQiyingyun = true;
                for(OnlineAccountConfig one:onlineAccountConfigs){
                    onlinePaymentInfo = onlinePaymentInfoMapper.selectByUidOrSubAccountOrTaxSourceId(
                            null,subAccountNo,one.getTaxSounrceId());
                    log.info("onlinePaymentInfo：{}",JSON.toJSONString(onlinePaymentInfo));
                    if(null != onlinePaymentInfo){
                        isQiyingyun = false;
                        taxSourceId = onlinePaymentInfo.getTaxSourceCompanyId();
                        break;
                    }
                }
                log.info("isQiyingyun：{}",isQiyingyun);
                if(isQiyingyun){
                    //发到启盈云
                    return sendToQiyingyun(map,paramConvert.get("outer_trade_no"));
                }
                break;
            case OnlineConstants.TRADE_STATUS_SYNC:
                String outerTradeNo = paramConvert.get("outer_trade_no");
                Integer merchantId;
                TransferOrder transferOrder = transferOrderMapper.selectByTransferSerialNum(outerTradeNo);
                MakeUpCharge makeUpCharge = makeUpChargeMapper.selectByOrderSerialSumOrMakeUpSerialNum(
                        null,outerTradeNo);
                ReturnChargeOrder returnChargeOrder = returnChargeOrderMapper.selectByOrderSerialNumOrReturnSerialNum(
                        null,outerTradeNo);
                if(null != transferOrder) {
                    merchantId = transferOrder.getMerchantId();
                }else if(null != makeUpCharge){
                    merchantId = makeUpCharge.getMerchantId();
                }else{
                    merchantId = returnChargeOrder.getMerchantId();
                }
                onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
                log.info("onlinePaymentInfo：{}",JSON.toJSONString(onlinePaymentInfo));
                taxSourceId = onlinePaymentInfo.getTaxSourceCompanyId();
                break;
            case OnlineConstants.TRANSFER_STATUS_SYNC:
            case OnlineConstants.PAY_STATUS_SYNC:
                log.info("不需要的处理通知,返回success");
                return "success";
            default:
        }
        log.info("taxSourceId:{}", taxSourceId);
        if(taxSourceId == null){
            log.info("未找到对应税源地，无法验签");
            return "fail";
        }
        OnlineAccountConfig onlineAccountConfig = new OnlineAccountConfig();
        onlineAccountConfig.setTaxSounrceId(taxSourceId);
        onlineAccountConfig = onlineAccountConfigMapper.selectOne(onlineAccountConfig);
        // 验证签名
        boolean verify = SecurityVerifyUtils.verify(paramConvert, "UTF-8",
                paramConvert.get("sign"), paramConvert.get("sign_type"), onlineAccountConfig.getWalletPublicKey());
        String succInfo = "success";
        if(!verify) {
            log.info("异步通知验签失败");
            succInfo = "fail";
            return succInfo;
        }

        //获取代理，触发事务
        IOnlineBankService onlineBankServiceProxy = (IOnlineBankService) AopContext.currentProxy();
        switch(paramConvert.get("notify_type")){
            case OnlineConstants.REFUND_STATUS_SYNC:
                onlineBankServiceProxy.dealRefundNotify(paramConvert);
                break;
            case OnlineConstants.WITHDRAWAL_STATUS_SYNC:
                onlineBankServiceProxy.dealWithdrawNotify(paramConvert);
                break;
            case OnlineConstants.REMIT_SYNC:
                onlineBankServiceProxy.dealRemitNotify(paramConvert,onlinePaymentInfo);
                break;
            case OnlineConstants.TRADE_STATUS_SYNC:
                onlineBankServiceProxy.dealTransferNotify(paramConvert);
                break;
            default:
        }

        log.info("处理完成后返回：{}",succInfo);
        return succInfo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void dealRefundNotify(Map<String, String> paramsMap) {
        String refundSerilNum = paramsMap.get("outer_trade_no");
        String tradeStatus = paramsMap.get("trade_status");
        TradeRefundOrder tradeRefundOrder = tradeRefundOrderMapper.selectByRefundSerialNum(refundSerilNum);
        if(null == tradeRefundOrder) {
            log.info("未找到该退款订单{}", refundSerilNum);
            return;
        }
        if(OnlineConstants.REFUND_FINISH.equals(tradeStatus)){
            log.info("tradeRefundOrder单号{}成功",refundSerilNum);
            tradeRefundOrder.setStatus(RefundStatusEnum.SUCESS.getCode());
            tradeRefundOrder.setUpdateTime(DateUtil.date());
            tradeRefundOrderMapper.updateByPrimaryKeySelective(tradeRefundOrder);
            //存入发送悟空云创的表
            TransferOrder transferOrder = transferOrderMapper.selectByTransferSerialNum(
                    tradeRefundOrder.getLinkSerialNum());
            notifyWkycService.insertNotifyWkyc(refundSerilNum,OrderTypeEnum.REFUND,transferOrder.getMerchantId());
        }
        if(OnlineConstants.REFUND_FAILED.equals(tradeStatus)){
            log.info("tradeRefundOrder单号{}失败",refundSerilNum);
            tradeRefundOrder.setStatus(RefundStatusEnum.FAIL.getCode());
            tradeRefundOrder.setComment(paramsMap.get("error_msg"));
            tradeRefundOrder.setUpdateTime(DateUtil.date());
            tradeRefundOrderMapper.updateByPrimaryKeySelective(tradeRefundOrder);
        }
    }

    /**
     * 处理商户转账员工异步通知
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void dealTransferNotify(Map<String,String> paramsMap){
        String outerTradeNo = paramsMap.get("outer_trade_no");
        String tradeStatus = paramsMap.get("trade_status");
        MakeUpCharge makeUpCharge = makeUpChargeMapper.selectByOrderSerialSumOrMakeUpSerialNum(null
                ,outerTradeNo);
        if(null != makeUpCharge){
            if(OnlineConstants.TRADE_FINISHED.equals(tradeStatus)){
                log.info("补交手续费订单{}成功", outerTradeNo);
                makeUpCharge.setStatus(MakeUpChargeStatusEnum.SUCESS.getStatus());
                makeUpCharge.setUpdateTime(DateUtil.date());
                makeUpChargeMapper.updateByPrimaryKeySelective(makeUpCharge);
            }
            if(OnlineConstants.TRADE_FAILED.equals(tradeStatus)){
                log.info("补交手续费订单{}失败", outerTradeNo);
                makeUpCharge.setStatus(MakeUpChargeStatusEnum.FAIL.getStatus());
                makeUpCharge.setComment(paramsMap.get("error_msg"));
                makeUpCharge.setUpdateTime(DateUtil.date());
                makeUpChargeMapper.updateByPrimaryKeySelective(makeUpCharge);
            }
            return;
        }
        ReturnChargeOrder returnChargeOrder =
                returnChargeOrderMapper.selectByOrderSerialNumOrReturnSerialNum(null,outerTradeNo);
        if(null != returnChargeOrder){
            if(OnlineConstants.TRADE_FINISHED.equals(tradeStatus)){
                log.info("回退手续费订单{}成功", outerTradeNo);
                returnChargeOrder.setStatus(ReturnChargeStatusEnum.SUCESS.getCode());
                returnChargeOrder.setUpdateTime(DateUtil.date());
                returnChargeOrderMapper.updateByPrimaryKeySelective(returnChargeOrder);
            }
            if(OnlineConstants.TRADE_FAILED.equals(tradeStatus)){
                log.info("回退手续费订单{}失败", outerTradeNo);
                returnChargeOrder.setStatus(ReturnChargeStatusEnum.FAIL.getCode());
                returnChargeOrder.setComment(paramsMap.get("error_msg"));
                returnChargeOrder.setUpdateTime(DateUtil.date());
                returnChargeOrderMapper.updateByPrimaryKeySelective(returnChargeOrder);
            }
            return;
        }
        TransferOrder transferOrder = transferOrderMapper.selectByTransferSerialNum(outerTradeNo);
        if(null == transferOrder){
            log.info("未找到转账订单{}", outerTradeNo);
            return;
        }
        log.info("转账单号{}异步状态{}", outerTradeNo, tradeStatus);
        if(TransferStatusEnum.UNPAID.getCode().equals(transferOrder.getStatus())){
            if(OnlineConstants.TRADE_FAILED.equals(tradeStatus)){
                log.info("转账单号{}转账失败",outerTradeNo);
                transferOrder.setStatus(TransferStatusEnum.PAID_PENDING.getCode());
                transferOrder.setComment(paramsMap.get("error_msg"));
                transferOrder.setUpdateTime(DateUtil.date());
                transferOrderMapper.updateByPrimaryKeySelective(transferOrder);
            }
            if(OnlineConstants.TRADE_FINISHED.equals(tradeStatus)){
                log.info("转账单号{}转账成功",outerTradeNo);
                transferOrder.setStatus(TransferStatusEnum.PAID.getCode());
                transferOrder.setUpdateTime(DateUtil.date());
                transferOrderMapper.updateByPrimaryKeySelective(transferOrder);
            }
        }
    }

    /**
     * 处理来账通知
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void dealRemitNotify(Map<String,String> paramsMap,OnlinePaymentInfo onlinePaymentInfo){
        String subAccountNo = paramsMap.get("subAccount");
        String orderTradeNo = paramsMap.get("outer_trade_no");
        subAccountNo =  subAccountNo.substring(subAccountNo.length()-8);
        if(null == onlinePaymentInfo){
            log.error("未找到该充值单号对应subAccount的商户信息，outer_trade_no={},subAccount={}",orderTradeNo,subAccountNo);
            return;
        }
        //查询账户信息(变动前)
        MerchantAccount accountBefore = merchantAccountMapper.getMerchantAccountByMerchantId(
                onlinePaymentInfo.getMerchantId());
        if(null == accountBefore){
            log.error("不存在客户账户，出错，merchantid={}",onlinePaymentInfo.getMerchantId());
            return;
        }
        //处理通知结果
        String value = UUID.randomUUID().toString();
        String key = RedisLockConstans.ACCOUNT_LOCK + onlinePaymentInfo.getMerchantNo();
        BigDecimal addAmount = new BigDecimal(paramsMap.get("remitAmount"));
        if(!OnlineConstants.SUCCESS.equals(paramsMap.get("status"))){
            return;
        }
        //添加钱包余额变动明细记录
        MerchantAccountDetail merchantAccountDetail = new MerchantAccountDetail();
        merchantAccountDetail.setOrderSerialNum(orderTradeNo);
        merchantAccountDetail = merchantAccountDetailMapper.selectOne(merchantAccountDetail);
        //判断是否处理过该条成功的充值通知
        if(null != merchantAccountDetail){
            log.info("该条成功通知已处理，不再重复处理");
            return;
        }
        try{
            RedisUtil.lock(key,value);
            merchantAccountService.updateMerchantAccountByVersion(onlinePaymentInfo.getMerchantId(),addAmount);
        }finally{
            RedisUtil.releaseDistributedLock(key, value);
            log.info("销毁钱包redis锁");
        }
        //添加钱包余额变动明细和充值记录(成功)
        rechargeRecordService.addAccountDetailAndRechargeRecord(accountBefore,addAmount,orderTradeNo,1);
        // 来账通知存储异步通知内容
        NotifySendAgain notifySendAgain = new NotifySendAgain();
        notifySendAgain.setFailNum(0);
        notifySendAgain.setNotifyStatus(2);
        notifySendAgain.setMerchantId(onlinePaymentInfo.getMerchantId());
        notifySendAgain.setNotifyType(2);
        notifySendAgain.setSendMethod(1);
        NotifyDTO notifyDTO = new NotifyDTO();
        notifyDTO.setNotifyType("2");
        notifyDTO.setCode(NotifyStatusEnum.SUCCESS.name());
        notifyDTO.setMessage(NotifyStatusEnum.SUCCESS.name());
        // 订单号
        notifyDTO.setTradeNo(orderTradeNo);
        // 商户编码
        notifyDTO.setMerchantNo(onlinePaymentInfo.getMerchantNo());
        notifyDTO.setAmount(paramsMap.get("remitAmount"));
        notifySendAgain.setNotifyContent(JSON.toJSONString(notifyDTO));
        notifySendAgain.setCreateTime(DateUtil.date());
        notifySendAgain.setLastSendTime(DateUtils.getNowDate());
        notifySendAgainMapper.insertSelective(notifySendAgain);
    }

    /**
     * 处理个人提现异步通知
     * @param paramsMap
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void dealWithdrawNotify(Map<String,String> paramsMap) {
        //查询员工信息
        OnlineCustomerInfo onlineCustomerInfo = onlineCustomerInfoMapper.selectByCustomerIdCustomerNo(
                null, paramsMap.get("uid"));
        if (null == onlineCustomerInfo) {
            log.error("未找到该提现订单对应uid的员工信息，outer_trade_no,uid->{}"
                    , paramsMap.get("outer_trade_no") + "," + paramsMap.get("uid"));
            return;
        }
        //查询转账订单信息
        String orderSerialNum = paramsMap.get("outer_trade_no");
        String withdrawalStatus = paramsMap.get("withdrawal_status");
        log.info("提现订单号={},异步状态={}", orderSerialNum, withdrawalStatus);
        TrxOrder trxOrder = trxOrderMapper.selectByOrderSerialNum(orderSerialNum);
        CustomerWithdraw customerWithdraw = customerWithdrawMapper.selectByWithdrawSerialNum(orderSerialNum);
        if (null != trxOrder && TrxOrderStatusEnum.UNPAID.getCode().equals(trxOrder.getOrderStatus())) {
            //判断并根据通知结果修改信息
            if (OnlineConstants.TRADE_FINISHED.equals(withdrawalStatus)) {
                //更新订单状态为成功
                log.info("提现单号{}提现成功", orderSerialNum);
                trxOrder.setOrderStatus(TrxOrderStatusEnum.PAID.code);
                trxOrder.setUpdateTime(DateUtil.date());
                trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
                //存入发送悟空云创的表
                notifyWkycService.insertNotifyWkyc(orderSerialNum,OrderTypeEnum.NOMAL,trxOrder.getMerchantId());
            }
            if (OnlineConstants.TRADE_FAILED.equals(withdrawalStatus)) {
                //更新订单状态为失败
                log.info("提现单号{}提现失败", orderSerialNum);
                trxOrder.setOrderStatus(TrxOrderStatusEnum.PAID_PENDING.code);
                trxOrder.setComment(InternetBusinessFailReasonEnum.getByEnumName(
                        paramsMap.get("error_msg")).getModifyDescription());
                trxOrder.setUpdateTime(DateUtil.date());
                trxOrder.setUpdateTime(DateUtil.date());
                trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
            }
        }
        if (null != customerWithdraw && WithdrawStatusEnum.UNPAID.getCode().equals(customerWithdraw.getWithdrawStatus())) {
            //判断并根据通知结果修改信息
            if (OnlineConstants.TRADE_FINISHED.equals(withdrawalStatus)) {
                //更新订单状态为成功
                log.info("提现单号{}提现成功", orderSerialNum);
                customerWithdraw.setWithdrawStatus(WithdrawStatusEnum.PAID.getCode());
                customerWithdraw.setUpdateTime(DateUtil.date());
                customerWithdrawMapper.updateByPrimaryKeySelective(customerWithdraw);
            }
            if (OnlineConstants.TRADE_FAILED.equals(withdrawalStatus)) {
                //更新订单状态为失败
                log.info("提现单号{}提现失败", orderSerialNum);
                customerWithdraw.setWithdrawStatus(TrxOrderStatusEnum.PAID_PENDING.code);
                customerWithdraw.setComment(InternetBusinessFailReasonEnum.getByEnumName(
                        paramsMap.get("error_msg")).getModifyDescription());
                customerWithdraw.setUpdateTime(DateUtil.date());
                customerWithdrawMapper.updateByPrimaryKeySelective(customerWithdraw);
            }
        }
    }

    @Override
    public void orderSuccessDeal(TrxOrder resultTrxOrder){
        BigDecimal addAmount = resultTrxOrder.getActualAmount();
        if(UseBigRateEnum.BIGRATE.getStatus().equals(resultTrxOrder.getUseBigRate())){
            addAmount = resultTrxOrder.getActualAmount();
        }
        //员工出账解冻
        customerAccountService.updateAccountAndInsertDetail(resultTrxOrder.getCustomerId(),addAmount
                ,AccountDetailTypeEnum.DEBIT_UNFREEZE,resultTrxOrder.getOrderSerialNum());
        //我方发送异步通知
        callBackNotifyOfOnline(resultTrxOrder);
    }

    @Override
    public void orderFailDeal(TrxOrder resultTrxOrder) {
        BigDecimal addAmount = resultTrxOrder.getActualAmount();
        if(UseBigRateEnum.BIGRATE.getStatus().equals(resultTrxOrder.getUseBigRate())){
            addAmount = resultTrxOrder.getActualAmount();
        }
        //员工入账解冻
        customerAccountService.updateAccountAndInsertDetail(resultTrxOrder.getCustomerId(),addAmount
                ,AccountDetailTypeEnum.CREDIT_UNFREEZE,resultTrxOrder.getOrderSerialNum());
        //我方发送异步通知
        callBackNotifyOfOnline(resultTrxOrder);
    }

    /**
     * 我方发送异步通知处理
     * @param trxOrder
     */
    @Override
    public void callBackNotifyOfOnline(TrxOrder trxOrder){
        MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfoById(trxOrder.getMerchantId());
        callBackNotify(trxOrder,merchantInfo,merchantKeyMapper);
    }

    @Override
    public AccountCheckOnlineDTO accountCheckOnline(Integer merchantId) {
        AccountCheckOnlineDTO checkOnlineDTO = new AccountCheckOnlineDTO();
        List<AccountCheckResultOnLineDTO> resultOnLineDTOList = new ArrayList<>();
        // infoList 待核对网商数据
        List<OnlinePaymentInfo> infoList = new ArrayList<>();
        if (merchantId == null) {
            infoList = onlinePaymentInfoMapper.listByUidOrSubAccount();
        }else {
            OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
            infoList.add(onlinePaymentInfo);
        }
        for (OnlinePaymentInfo info : infoList) {
            AccountCheckResultOnLineDTO checkResultOnLineDTO = new AccountCheckResultOnLineDTO();
            checkResultOnLineDTO.setMerchantId(info.getMerchantId());
            // 封装网商请求参数 dto
            AccountBalanceDTO dto = new AccountBalanceDTO();
            dto = (AccountBalanceDTO) getCommonDTO(dto, info.getTaxSourceCompanyId());
            dto.setUid(info.getMerchantNo());
            dto.setAccountType(OnlineConstants.ACCOUNT_TYPE);
            try {
                AccountBalanceResultDTO resultDTO = IOnlineBankUtils.accountBalance(dto);
                if (resultDTO != null) {
                    checkResultOnLineDTO.setCreateTime(DateUtil.date());
                    List<AccountBalanceResultDTO.AccountInfo> accountInfoList = resultDTO.getAccount_list();
                    if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(accountInfoList)) {
                        AccountBalanceResultDTO.AccountInfo accountInfo = accountInfoList.get(0);
                        checkResultOnLineDTO.setOnlineBalance(new BigDecimal(accountInfo.getBalance()));
                        checkResultOnLineDTO.setOnlineAvailableBalance(new BigDecimal(
                                accountInfo.getAvailable_balance()));
                        checkResultOnLineDTO.setSubAccountNo(accountInfo.getSub_account_no());
                    }
                }
                MerchantAccount merchantAccount = merchantAccountMapper.getMerchantAccountByMerchantId(info.
                        getMerchantId());
                if (merchantAccount != null) {
                    checkResultOnLineDTO.setLocalBalance(merchantAccount.getUsableAmount());
                    if (checkResultOnLineDTO.getOnlineAvailableBalance()
                            .compareTo(checkResultOnLineDTO.getLocalBalance()) == 0) {
                        checkResultOnLineDTO.setBalance(BigDecimal.ZERO);
                        checkResultOnLineDTO.setResultFlag(true);
                    }else {
                        checkResultOnLineDTO.setBalance(checkResultOnLineDTO.getLocalBalance()
                                .subtract(checkResultOnLineDTO.getOnlineAvailableBalance()));
                        checkResultOnLineDTO.setResultFlag(false);
                    }
                }
            }catch (Exception e) {
                logger.info("accountCheckOnline accountBalance{},{}",info.getMerchantId(), e);
            }
            resultOnLineDTOList.add(checkResultOnLineDTO);
        }
        checkOnlineDTO.setCheckDate(DateUtil.date());
        checkOnlineDTO.setResultOnLineDTOList(resultOnLineDTOList);
        return checkOnlineDTO;
    }

    @Override
    public String bankQueryOnline(String parentBranchNO, String areaCode, String keyWords, String sourceCompanyId) {
        BankQueryDTO dto = new BankQueryDTO();
        dto = (BankQueryDTO) getCommonDTO(dto, Integer.valueOf(sourceCompanyId));
        dto.setParentBranchNo(parentBranchNO);
        dto.setAreaCode(areaCode);
        dto.setKeyWords(keyWords);
        String result = IOnlineBankUtils.bankQueryOnline(dto);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void changeOrderInfo(String orderSerialNum) {
        //修改订单表
        TrxOrder trxOrder = new TrxOrder();
        trxOrder.setOrderSerialNum(orderSerialNum);
        List<TrxOrder> trxOrderInfo = trxOrderMapper.select(trxOrder);
        if (trxOrderInfo.size() == 0) {
            logger.info("无交易订单记录，订单流水号为{}",orderSerialNum);
            throw new BusinessException("无交易订单记录");
        }
        trxOrderInfo.get(0).setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.getCode());
        trxOrderInfo.get(0).setUpdateTime(new Date());
        trxOrderMapper.updateByPrimaryKeySelective(trxOrderInfo.get(0));
        //修改资金流水表
        MerchantAccountDetail merchantAccountDetail = new MerchantAccountDetail();
        merchantAccountDetail.setOrderSerialNum(orderSerialNum);
        List<MerchantAccountDetail> merchantAccountDetails = merchantAccountDetailMapper.select(merchantAccountDetail);
        if (merchantAccountDetails.size() == 0) {
            logger.info("无资金流水记录，订单流水号为{}",orderSerialNum);
            throw new BusinessException("无资金流水记录");
        }
        merchantAccountDetails.get(0).setStatus(0);
        merchantAccountDetails.get(0).setUpdateTime(new Date());
        merchantAccountDetailMapper.updateByPrimaryKeySelective(merchantAccountDetails.get(0));
        //更新账户余额
        MerchantAccount accountBefore =
                merchantAccountMapper.getMerchantAccountByMerchantId(trxOrderInfo.get(0).getMerchantId());
        AccountBalanceResultDTO data =
                onlineBankService.accountBalance(trxOrderInfo.get(0).getMerchantId(),null,null,null);
        List<AccountBalanceResultDTO.AccountInfo> balance = data.getAccount_list();
        accountBefore.setAmount(new BigDecimal(balance.get(0).getBalance()));
        accountBefore.setUsableAmount(new BigDecimal(balance.get(0).getAvailable_balance()));
        accountBefore.setUpdateTime(new Date());
        logger.info("更新账户余额,可用余额为{},总余额为{}",balance.get(0).getAvailable_balance(),balance.get(0).getBalance());
        merchantAccountMapper.updateByPrimaryKeySelective(accountBefore);
    }

    @Override
    public BigDecimal getUsableAmount(Integer merchantId) {
        MerchantAccount info = merchantAccountMapper.getMerchantAccountByMerchantId(merchantId);
        return info.getUsableAmount();
    }
}
