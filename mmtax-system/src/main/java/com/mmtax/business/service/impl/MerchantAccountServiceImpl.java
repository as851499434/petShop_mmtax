package com.mmtax.business.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.IMerchantAccountService;
import com.mmtax.business.yunzbdto.YunZBOfflineRechargeDTO;
import com.mmtax.common.annotation.DataScope;
import com.mmtax.common.constant.MerchantAccountConstants;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.yunzbutil.Response;
import com.mmtax.common.utils.yunzbutil.YunZBConstants;
import com.mmtax.common.utils.yunzbutil.YunZBUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/15 16:16
 */
@Service
public class MerchantAccountServiceImpl extends CommonServiceImpl implements IMerchantAccountService {

    protected final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Resource
    private MerchantAccountDetailMapper merchantAccountDetailMapper;
    @Resource
    private MerchantAccountMapper merchantAccountMapper;
    @Resource
    private RechargeRecordMapper rechargeRecordMapper;
    @Resource
    private MerchantInfoMapper merchantInfoMapper;
    @Resource
    private CooperationMapper cooperationMapper;
    @Resource
    private TrxOrderMapper trxOrderMapper;
    @Resource
    private TianJinPaymentInfoMapper tianJinPaymentInfoMapper;
    @Resource
    private MakeUpChargeDetailMapper makeUpChargeDetailMapper;
    @Resource
    private ReturnChargeOrderMapper returnChargeOrderMapper;
    @Resource
    private TradeRefundOrderMapper tradeRefundOrderMapper;
    @Resource
    private TransferOrderMapper transferOrderMapper ;
    @Resource
    private MakeUpChargeMapper makeUpChargeMapper ;
    @Resource
    private ChargeFeeRecordMapper chargeFeeRecordMapper ;

    @Autowired
    private IMerchantAccountService merchantAccountService;

    @Override
    public MerchantSumPaymentDetailDTO getMerchantSumPaymentDetail(Integer merchantId) {
        MerchantSumPaymentDetailDTO detailDTO = new MerchantSumPaymentDetailDTO();
        String startDate = DateUtil.format(DateUtil.beginOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
        String endDate = DateUtil.format(DateUtil.endOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
        //获取当月代付总额
        BigDecimal amount = merchantAccountDetailMapper.getMonthSumPaymentAmount(startDate, endDate, merchantId,
                AccountTypeEnum.REPLACE_PAY.getCode());
        if (amount == null) {
            amount = BigDecimal.ZERO;
        }
        int count = merchantAccountDetailMapper.getMonthCountPaymentRecord(startDate, endDate, merchantId,
                AccountTypeEnum.REPLACE_PAY.getCode());
        List<MerchantChannelPayment> channelAmount = merchantAccountDetailMapper.getChannelPayment(startDate, endDate,
                merchantId, AccountTypeEnum.REPLACE_PAY.getCode());
        // todo后期调整
        BigDecimal bankAmount = BigDecimal.ZERO;
        BigDecimal alipayAmount = BigDecimal.ZERO;
        BigDecimal wechatAmount = BigDecimal.ZERO;

        for (MerchantChannelPayment channel : channelAmount) {
            if (PaymentEnum.BANK.name().equals(channel.getPaymentChannel())) {
                bankAmount = bankAmount.add(channel.getAmount());
            }
            if (PaymentEnum.ALIPAY.name().equals(channel.getPaymentChannel())) {
                alipayAmount = alipayAmount.add(channel.getAmount());
            }
            if (PaymentEnum.WECHAT.name().equals(channel.getPaymentChannel())) {
                wechatAmount = wechatAmount.add(channel.getAmount());
            }
        }

        detailDTO.setBankAmount(bankAmount);
        detailDTO.setAlipayAmount(alipayAmount);
        detailDTO.setWechatAmount(wechatAmount);
        detailDTO.setAmount(amount.toString());
        detailDTO.setNum(count);
        return detailDTO;
    }

    @Override
    public BigDecimal getAccountByMerchantId(Integer merchantId) {
        MerchantAccount merchantAccount = merchantAccountMapper.getMerchantAccountByMerchantId(merchantId);
        return merchantAccount.getUsableAmount();
    }

    @Override
    public Page getRechargeRecord(Integer merchantId, String rechargeChannel, String rechargeType, String status,
                                  Integer pageSize, Integer currentPage, String startDate, String endDate) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);

        List<MerchantRechargeRecordDTO> list = rechargeRecordMapper.getListMerchantRechargeRecord(startDate, endDate,
                merchantId, rechargeChannel, rechargeType, status, queryPage.getStartIndex(), pageSize);
        int count = rechargeRecordMapper.getCountMerchantRechargeRecord(startDate, endDate, merchantId,
                rechargeChannel, rechargeType, status);

        return new Page(count, list, currentPage, pageSize);
    }

    //todo 此功能待开发
    @Override
    public Page getPageTransferAccounts(Integer merchantId, Integer pageSize, Integer currentPage,
                                        String startDate, String endDate, Integer type) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);

        return null;
    }


    @Override
    public MainPagePayDTO getPayTrend(Integer merchantId) {
        MainPagePayDTO dto = new MainPagePayDTO();
        String startDate = DateUtil.format(DateUtil.beginOfYear(DateUtil.date()), DatePattern.NORM_DATE_PATTERN);
        String endDate = DateUtil.format(DateUtil.endOfYear(DateUtil.date()), DatePattern.NORM_DATE_PATTERN);
        BigDecimal amount = trxOrderMapper.getSumAmountByMerchantIdCustomerId(startDate, endDate, merchantId,null);
        BigDecimal taxAmount = trxOrderMapper.getSumTaxAmountByMerchantId(startDate, endDate, merchantId);
        if (amount == null) {
            amount = BigDecimal.ZERO;
        }
        if (taxAmount == null) {
            taxAmount = BigDecimal.ZERO;
        }
        dto.setTax(taxAmount);
        dto.setYearAmount(amount);
        return dto;
    }

    @Override
    @DataScope(tableAlias = "t3")
    public List<ManagerAccountDTO> getListManagerAccount(ManagerAccountDTO managerAccountDTO) {
        List<ManagerAccountDTO> list = merchantAccountMapper.getListManagerAccount(managerAccountDTO);
        return list;
    }

    @Override
    @DataScope(tableAlias = "t3")
    public List<ManagerCapitalFlowDTO> getListAccountRecord(ManagerAccountRecordDTO managerAccountRecordDTO) {
        List<ManagerCapitalFlowDTO> list = merchantAccountDetailMapper.getListAccountAllRecord(managerAccountRecordDTO);
        return list;
    }

    @Override
    public List<ManagerCapitalFlowDTO> getListAccountingDetails(ManagerAccountingDTO managerAccountingDTO) {
        List<ManagerCapitalFlowDTO> list = merchantAccountDetailMapper.getListAccountingRecord(managerAccountingDTO);
        return list;

    }

    @Override
    public ManagerReturnPointDTO getAccountReturnPoint(Integer merchantId) {
        ManagerReturnPointDTO managerReturnPointDTO = new ManagerReturnPointDTO();
        MerchantAccount merchantAccount = merchantAccountMapper.getMerchantAccountByMerchantId(merchantId);
        BeanUtils.copyProperties(merchantAccount, managerReturnPointDTO);
        return managerReturnPointDTO;
    }

    @Override
    public List<MerchantReturnPointListDTO> getReturnPointList(ManagerReturnPointQueryDTO managerReturnPointQueryDTO) {
        managerReturnPointQueryDTO.setType(AccountTypeEnum.RETURN_POINT.getCode());
        List<MerchantReturnPointListDTO> list = merchantAccountDetailMapper.getBackFlow(managerReturnPointQueryDTO);
        return list;
    }

    @Override
    public List<MerchantRechargeRecordDTO> exportMerchantRechargeRecord(String startDate, String endDate,
                                                                        Integer merchantId, String rechargeChannel,
                                                                        String rechargeType, String status) {
        List<MerchantRechargeRecordDTO> list = rechargeRecordMapper.getListMerchantRechargeRecord(startDate, endDate, merchantId, rechargeChannel,
                rechargeType, status, null, null);
        for (MerchantRechargeRecordDTO one: list) {
            switch(one.getStatus()){
                case "SUCCESS":
                    one.setStatus("成功");
                    break;
                case "FAIL":
                    one.setStatus("失败");
                    break;
                case "ALLPYING":
                    one.setStatus("申请中");
                    break;
                default:
            }
        }
        return list;
    }

    @Override
    public Page getMerchantReturnPointList(Integer merchantId, Integer pageSize, Integer currentPage, String startDate,
                                           String endDate, Integer status) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);

        List<MerchantReturnPointListDTO> list = merchantAccountDetailMapper.getMerchantBackFlow(merchantId, pageSize,
                queryPage.getStartIndex(), startDate, endDate, status, AccountTypeEnum.RETURN_POINT.getCode());
        int count = merchantAccountDetailMapper.countBackFlow(merchantId, startDate, endDate, status,
                AccountTypeEnum.RETURN_POINT.getCode());

        return new Page(count, list, currentPage, pageSize);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void rechargeSimulation(RechargeSimulationDTO rechargeSimulationDTO) throws Exception {
        Integer merchantId = rechargeSimulationDTO.getMerchantId();

        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);
        if (merchantInfo.getStatus().equals(MerchantStatusEnum.CANCEL.name())) {
            throw new BusinessException("该账户已经注销，无法充值！");
        }
        //充值金额
        BigDecimal rechargeAmount = rechargeSimulationDTO.getAmount();
        //查询账户信息
        MerchantAccount account = merchantAccountMapper.getMerchantAccountByMerchantId(merchantId);
        BigDecimal oldAmount = account.getAmount();
        //添加充值记录
        RechargeRecord record = new RechargeRecord();
        record.setAmount(rechargeAmount);
        record.setInvoiceStatus(0);
        record.setStatus(RechargeStatusEnum.ALLPYING.name());
        record.setRechargeType(RechargeTypeEnum.UNDERLINE.name());
        record.setRechargeAmountBefore(account.getAmount());
        record.setRechargeAmountAfter(account.getAmount().add(rechargeAmount));
        record.setRechargeUsableAmountAfter(account.getUsableAmount().add(rechargeAmount));
        record.setRechargeUsableAmountBefore(account.getUsableAmount());
        record.setRechargeChannel(PaymentChannelEnum.YUNZB.name());
        record.setMerchantId(merchantId);
        record.setCreateTime(DateUtil.date());

        //查询商户平台信息
        PaymentMerchantInfo paymentMerchantInfo = paymentMerchantInfoMapper.selectByMerchantIdAndChannel
                (merchantId, PaymentChannelEnum.YUNZB.name());
        if (paymentMerchantInfo != null) {
            //云众包充值渠道
            JSONObject jsonObject = rechargeByYunZB(merchantId, rechargeAmount.toString(), YunZBConstants.UUID, paymentMerchantInfo);
            //若申请未成功，则提示错误信息
            if (jsonObject.get(YunZBConstants.RETURN_CODE).equals(YunZBConstants.RESULT_CODE)) {
                record.setOrderSerialNum(jsonObject.getString(YunZBConstants.SERIAL_NO));
                rechargeRecordMapper.insertSelective(record);
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new BusinessException(jsonObject.get(YunZBConstants.RETURN_MSG).toString());
            }
        }else {
            rechargeRecordMapper.insertSelective(record);
        }
    }

    private JSONObject rechargeByYunZB(Integer merchantId, String amount, String orderId,PaymentMerchantInfo paymentMerchantInfo) throws Exception {
        //云众包充值渠道
        YunZBOfflineRechargeDTO yunZBOfflineRechargeDTO = new YunZBOfflineRechargeDTO();

        //查询税源地公司信息
        TaxSounrceCompany taxSounrceCompany =
                taxSounrceCompanyMapper.selectByPrimaryKey(paymentMerchantInfo.getTaxSourceId());

        yunZBOfflineRechargeDTO.setAmt(amount);
        yunZBOfflineRechargeDTO.setChannelNo(queryChannels(taxSounrceCompany.getMerchantNo(),
                taxSounrceCompany.getSecretKey()));
        yunZBOfflineRechargeDTO.setSubMchId(paymentMerchantInfo.getMerchantNo());
        yunZBOfflineRechargeDTO.setMchId(taxSounrceCompany.getMerchantNo());
        yunZBOfflineRechargeDTO.setNonceStr(orderId);
        Response response = YunZBUtil.remoteInvoke(YunZBConstants.RECHARGE, yunZBOfflineRechargeDTO,
                taxSounrceCompany.getSecretKey());
        JSONObject tokenResult = JSONObject.parseObject(response.getRespData());
        return tokenResult;
    }

    @Override
    public List<PayTrendDTO> getMerchantMonthPaymentDetail(Integer merchantId, String startDate, String endDate) {
        List<PayTrendDTO> list = trxOrderMapper.getMerchantMonthPaymentDetail(startDate, endDate, merchantId);
        return list;
    }

    @Override
    public List<DayPayTrendDTO> getMerchantDayPaymentDetail(Integer merchantId, String startDate, String endDate) {
        List<DayPayTrendDTO> list = trxOrderMapper.getMerchantDayPaymentDetail(startDate, endDate, merchantId);
        return list;
    }

    //充值记录
    @Override
    public List<ManagerRechargeDTO> getRechargeList(ManagerRechargeDTO managerRechargeDTO) {
        return rechargeRecordMapper.getRechargeList(managerRechargeDTO);
    }

    @Override
    public void auditRecharge(RechargeRecord rechargeRecord) {
        RechargeRecord resultRechargeRecord = rechargeRecordMapper.selectByPrimaryKey(rechargeRecord.getId());
        //查询是否为天津渠道的商户，只有天津渠道的商户才能手动审核
        TianJinPaymentInfo tianJinPaymentInfo =
                tianJinPaymentInfoMapper.getTianJinPaymentInfoByMerchantId(resultRechargeRecord.getMerchantId());
        if (tianJinPaymentInfo == null) {
            throw new BusinessException("该商户不支持手动审核充值");
        }
        if (RechargeStatusEnum.SUCCESS.name().equals(rechargeRecord.getStatus())) {
//            merchantAccountMapper.updateMerchantAccount(resultRechargeRecord.getMerchantId(), resultRechargeRecord.getAmount().toString());
            merchantAccountService.updateMerchantAccountByVersion(resultRechargeRecord.getMerchantId(),
                    resultRechargeRecord.getAmount());
        }
        rechargeRecord.setUpdateTime(DateUtil.date());
        rechargeRecordMapper.updateByPrimaryKeySelective(rechargeRecord);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void updateMerchantAccountByVersion(Integer merchantId, BigDecimal amount) {
        int numAttempt = 0;
        int count;
        do {
            numAttempt++;
            MerchantAccount account = merchantAccountMapper.getMerchantAccountByMerchantIdUpCache(merchantId);
            logger.info("更新前的acount：{}", JSON.toJSONString(account));
            Integer version = account.getVersion();
            account.setAmount(account.getAmount().add(amount));
            account.setUsableAmount(account.getUsableAmount().add(amount));
            account.setUpdateTime(DateUtil.date());
            count = merchantAccountMapper.updateByPrimaryKey(account);
            logger.info("updateMerchantAccountByVersion merchantId:{}, amount:{},numAttempt:{}, count:{}, " +
                    "version:{}", merchantId, amount, numAttempt, count, version);
        }
        while (count == 0 && numAttempt <= MerchantAccountConstants.MERCHANT_ACCOUNT_DEFAULT_MAX_RETRIES);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public MerchantAccount updateMerchantAccountByVersion(Integer merchantId, BigDecimal amount, Integer type) {
        int numAttempt = 0;
        int count;
        MerchantAccount accountBefore = new MerchantAccount();
        do {
            numAttempt++;
            MerchantAccount account = merchantAccountMapper.getMerchantAccountByMerchantIdUpCache(merchantId);
            logger.info("更新前的acount：{}", JSON.toJSONString(account));
            BeanUtils.copyProperties(account,accountBefore);
            Integer version = account.getVersion();
            switch(type) {
                case 1:
                    account.setFrozenAmount(account.getFrozenAmount().subtract(amount));
                    account.setUsableAmount(account.getUsableAmount().add(amount));
                    break;
                case 2:
                    account.setAmount(account.getAmount().subtract(amount));
                    account.setFrozenAmount(account.getFrozenAmount().subtract(amount));
                    break;
                default:
            }
            account.setUpdateTime(DateUtil.date());
            count = merchantAccountMapper.updateByPrimaryKey(account);
            logger.info("updateMerchantAccountByVersion merchantId:{}, amount:{},numAttempt:{}, count:{}, " +
                    "version:{}", merchantId, amount, numAttempt, count, version);
        }
        while (count == 0 && numAttempt <= MerchantAccountConstants.MERCHANT_ACCOUNT_DEFAULT_MAX_RETRIES);
        return accountBefore;
    }


    @Override
    @DataScope(tableAlias = "t3")
    public List<CooperationInfoDTO> getSysCooperationList(ManagerMerchantQueryDTO managerMerchantQueryDTO){
        return merchantAccountMapper.getSysCooperationList(managerMerchantQueryDTO);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void getIsNullMerchantAccountDetail() {
        List<MerchantAccountDetail> merchantAccountDetails = merchantAccountDetailMapper.getIsNullMerchantAccountDetail();
        for (MerchantAccountDetail one : merchantAccountDetails) {
            if (StringUtils.isNotEmpty(one.getOrderSerialNum())) {
                //查订单表
                TrxOrder trxOrder = new TrxOrder();
                trxOrder.setOrderSerialNum(one.getOrderSerialNum());
                List<TrxOrder> TrxOrderList = trxOrderMapper.select(trxOrder);
                if (TrxOrderList.size() != 0) {
                    one.setPaymentType(DetailTypeEnum.PAYMENT.getCode());
                    merchantAccountDetailMapper.updateByPrimaryKeySelective(one);
                    continue;
                }
                //查充值表
                RechargeRecord rechargeRecord = new RechargeRecord();
                rechargeRecord.setOrderSerialNum(one.getOrderSerialNum());
                List<RechargeRecord> rechargeRecordList = rechargeRecordMapper.select(rechargeRecord);
                if (rechargeRecordList.size() != 0) {
                    one.setPaymentType(DetailTypeEnum.RECHARGE.getCode());
                    merchantAccountDetailMapper.updateByPrimaryKeySelective(one);
                    continue;
                }
                //查补交表
                MakeUpCharge makeUpCharge = new MakeUpCharge();
                makeUpCharge.setMakeUpSerialNum(one.getOrderSerialNum());
                List<MakeUpCharge> makeUpChargeList = makeUpChargeMapper.select(makeUpCharge);
                if (makeUpChargeList.size() != 0) {
                    one.setPaymentType(DetailTypeEnum.MAKEUPCHARGE.getCode());
                    merchantAccountDetailMapper.updateByPrimaryKeySelective(one);
                    continue;
                }
                //查退还表
                ReturnChargeOrder returnChargeOrder = new ReturnChargeOrder();
                returnChargeOrder.setReturnSerialNum(one.getOrderSerialNum());
                List<ReturnChargeOrder> returnChargeOrderList = returnChargeOrderMapper.select(returnChargeOrder);
                if (returnChargeOrderList.size() != 0) {
                    one.setPaymentType(DetailTypeEnum.RETUANCHARGE.getCode());
                    merchantAccountDetailMapper.updateByPrimaryKeySelective(one);
                    continue;
                }
                //查退回表
                TradeRefundOrder tradeRefundOrder = new TradeRefundOrder();
                tradeRefundOrder.setRefundSerialNum(one.getOrderSerialNum());
                List<TradeRefundOrder> tradeRefundOrderList = tradeRefundOrderMapper.select(tradeRefundOrder);
                if (tradeRefundOrderList.size() != 0) {
                    one.setPaymentType(DetailTypeEnum.BACK.getCode());
                    merchantAccountDetailMapper.updateByPrimaryKeySelective(one);
                    continue;
                }
                //查转账表
                TransferOrder transferOrder = new TransferOrder();
                transferOrder.setOrderSerialNum(one.getOrderSerialNum());
                List<TransferOrder> transferOrderList = transferOrderMapper.select(transferOrder);
                if (transferOrderList.size() != 0) {
                    one.setPaymentType(DetailTypeEnum.PAYMENT.getCode());
                    merchantAccountDetailMapper.updateByPrimaryKeySelective(one);
                    continue;
                }
                //查服务费收取记录表
                ChargeFeeRecord chargeFeeRecord = chargeFeeRecordMapper.selectByChargeSerialNum(one.getOrderSerialNum());
                if (null != chargeFeeRecord) {
                    one.setPaymentType(DetailTypeEnum.PAYMENT.getCode());
                    merchantAccountDetailMapper.updateByPrimaryKeySelective(one);
                    continue;
                }
            }
        }
    }
}

