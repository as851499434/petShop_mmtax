package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.*;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.IMerchantAccountService;
import com.mmtax.business.service.IYunZBService;
import com.mmtax.business.yunzbdto.YunZBAddMerchantNotifyDTO;
import com.mmtax.business.yunzbdto.YunZBRechargeNotifyDTO;
import com.mmtax.business.yunzbdto.YunZBSettNotyfyDTO;
import com.mmtax.common.enums.*;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.redis.RedisLockConstans;
import com.mmtax.common.utils.redis.RedisTimeConstans;
import com.mmtax.common.utils.redis.RedisUtil;
import com.mmtax.common.utils.yunzbutil.YunZBConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/11/12 15:47
 */
@Service
public class YunZBServiceImpl extends CommonServiceImpl implements IYunZBService {
    /**
     * 账户余额
     */
    private static final String AMT = "amt";
    /**
     * 冻结金额
     */
    private static final String FREEZE_AMT = "freeze_amt";
    /**
     * 可用余额
     */
    private static final String USABLE_AMT = "usable_amt";


    @Resource
    private MerchantInfoMapper merchantInfoMapper;
    @Resource
    private MerchantAccountMapper merchantAccountMapper;
    @Resource
    private TrxOrderMapper trxOrderMapper;
    @Resource
    private MerchantAccountDetailMapper merchantAccountDetailMapper;
    @Resource
    private RechargeRecordMapper rechargeRecordMapper;

    @Resource
    MerchantKeyMapper merchantKeyMapper;

    @Autowired
    private IMerchantAccountService merchantAccountService;

    @Override
    public void updateMerchantInfo(YunZBAddMerchantNotifyDTO dto) throws Exception {
        logger.info("接收到开户通知结果：{}", JSONObject.toJSON(dto));
        //查询平台商户信息
        PaymentMerchantInfo paymentMerchantInfo = getPaymentMerchantInfo(dto.getSub_mch_id());
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(paymentMerchantInfo.getMerchantId());
        if (!dto.getResult_code().equals(YunZBConstants.RESULT_CODE) ||
                !dto.getMch_status().equals(YunZBConstants.MCH_STATUS)) {
            if (StringUtils.isNotEmpty(dto.getReturn_msg())) {
                merchantInfo.setRemark(dto.getReturn_msg());
            }
            if (StringUtils.isNotEmpty(dto.getReason())) {
                merchantInfo.setRemark(dto.getReason());
            }
        } else {
            merchantInfo.setStatus(MerchantStatusEnum.ACTIVE.name());
        }
        merchantInfoMapper.updateByPrimaryKeySelective(merchantInfo);
    }

    /**
     * TODO
     *
     * @param dto 参数
     */
    @Override
    public void rechargeNotify(YunZBRechargeNotifyDTO dto) throws Exception {
        logger.info("接收到充值通知结果：{}", JSONObject.toJSON(dto));
        //查询平台商户信息
        PaymentMerchantInfo paymentMerchantInfo = getPaymentMerchantInfo(dto.getSub_mch_id());
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(paymentMerchantInfo.getMerchantId());
        String key = null;
        String value = UUID.randomUUID().toString();
        try {
            key = RedisLockConstans.ACCOUNT_LOCK + merchantInfo.getMerchantCode();
            boolean result = false;
            while (!result) {
                result = RedisUtil.tryGetDistributedLock(key, value, RedisTimeConstans.ONE_MINUTE);
                if (!result) {
                    Thread.sleep(500);
                }
            }
            RechargeRecord rechargeRecord = rechargeRecordMapper.getRechargeRecordBySerialNum(dto.getSerial_no());
            if (dto.getReturn_code().equals(YunZBConstants.RESULT_CODE)) {
                if (dto.getResult_code().equals(YunZBConstants.RESULT_CODE)) {
                    MerchantAccount merchantAccount =
                            merchantAccountMapper.getMerchantAccountByMerchantId(paymentMerchantInfo.getMerchantId());
                    rechargeRecord.setStatus(RechargeStatusEnum.SUCCESS.name());
                    rechargeRecordMapper.updateByPrimaryKeySelective(rechargeRecord);
//                    merchantAccountMapper.updateMerchantAccount(paymentMerchantInfo.getMerchantId(), dto.getAmt());
                    merchantAccountService.updateMerchantAccountByVersion(paymentMerchantInfo.getMerchantId(),
                            new BigDecimal(dto.getAmt()));
                    insertAccountDetail(merchantAccount, BigDecimal.valueOf(Double.parseDouble(dto.getAmt())),
                            rechargeRecord);
                }
            }
        } finally {
            RedisUtil.releaseDistributedLock(key, value);
            logger.info("销毁钱包redis锁");
        }
    }

    @Override
    public Boolean settNotify(YunZBSettNotyfyDTO dto) {
        logger.info("接收到打款通知结果：{}", JSONObject.toJSON(dto));
        //查询平台商户信息
        PaymentMerchantInfo paymentMerchantInfo = getPaymentMerchantInfo(dto.getSub_mch_id());
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(paymentMerchantInfo.getMerchantId());
        String key = null;
        String value = UUID.randomUUID().toString();
        key = RedisLockConstans.ACCOUNT_LOCK + merchantInfo.getMerchantCode();
        try {

            TrxOrder trxOrder = new TrxOrder();
            trxOrder.setMerchantSerialNum(dto.getTrade_no());
            trxOrder.setMerchantId(paymentMerchantInfo.getMerchantId());
            TrxOrder resultTrxOrder = trxOrderMapper.selectOne(trxOrder);

            if (YunZBConstants.MCH_STATUS.equals(dto.getOrder_status())) {
                resultTrxOrder.setOrderStatus(TrxOrderStatusEnum.PAID.code);
            } else {
                //只处理扣款成功的订单
                Integer orderStatus = resultTrxOrder.getOrderStatus();
                if (orderStatus == null) {
                    return true;
                }else {
                    if (!orderStatus.equals(1)) {
                        return true;
                    }
                }
                resultTrxOrder.setOrderStatus(TrxOrderStatusEnum.PAID_PENDING.code);
                resultTrxOrder.setComment(dto.getReason());
                //返回金额
                BigDecimal amount = resultTrxOrder.getAmount().add(resultTrxOrder.getCharge());
                try {
                    RedisUtil.lock(key, value);
                    //更新钱包余额
//                    merchantAccountMapper.updateMerchantAccount(paymentMerchantInfo.getMerchantId(), amount.toString());
                    merchantAccountService.updateMerchantAccountByVersion(paymentMerchantInfo.getMerchantId(),
                            amount);
                } finally {
                    RedisUtil.releaseDistributedLock(key, value);
                    logger.info("销毁钱包redis锁");
                }
                //更新钱包支出记录状态
                MerchantAccountDetail merchantAccountDetail = new MerchantAccountDetail();
                merchantAccountDetail.setType(MerchantAccountDetailTypeEnum.PAID.code);
                merchantAccountDetail.setOrderSerialNum(resultTrxOrder.getOrderSerialNum());
                merchantAccountDetail.setMerchantId(paymentMerchantInfo.getMerchantId());
                MerchantAccountDetail resultMerchantAccountDetail =
                        merchantAccountDetailMapper.selectOne(merchantAccountDetail);
                resultMerchantAccountDetail.setStatus(AccountDetailStatusEnum.FAIL.code);
                merchantAccountDetailMapper.updateByPrimaryKeySelective(resultMerchantAccountDetail);
            }
            resultTrxOrder.setUpdateTime(DateUtil.date());
            trxOrderMapper.updateByPrimaryKeySelective(resultTrxOrder);
            //异步回调通知
            callBackNotify(resultTrxOrder, merchantInfo, merchantKeyMapper);
        } catch (Exception e) {
            logger.error("云众包通知订单号为空:{},订单信息为:{}", e, JSONObject.toJSON(dto));
            return false;
        }
        return true;
    }


    /**
     * 添加账户充值明细
     *
     * @param account        账户信息
     * @param rechargeAmount 充值金额
     * @param record         充值记录
     */
    private void insertAccountDetail(MerchantAccount account, BigDecimal rechargeAmount, RechargeRecord record) {
        //添加代付记录
        MerchantAccountDetail merchantAccountDetail = new MerchantAccountDetail();
        merchantAccountDetail.setMerchantId(account.getMerchantId());
        merchantAccountDetail.setPaymentAmount(rechargeAmount);
        merchantAccountDetail.setPaymentUsableAmountBefore(account.getUsableAmount());
        merchantAccountDetail.setPaymentUsableAmountAfter(account.getUsableAmount().add
                (rechargeAmount));
        merchantAccountDetail.setPaymentAmountBefore(account.getAmount());
        merchantAccountDetail.setPaymentAmountAfter(account.getAmount().add(rechargeAmount));
        merchantAccountDetail.setStatus(AccountDetailStatusEnum.SUCCESS.code);
        merchantAccountDetail.setType(MerchantAccountDetailTypeEnum.RECHARGE.code);
        merchantAccountDetail.setPaymentFrozenAmountBefore(account.getFrozenAmount());
        merchantAccountDetail.setPaymentFrozenAmountAfter(account.getFrozenAmount());
        merchantAccountDetail.setOrderSerialNum(record.getOrderSerialNum());
        merchantAccountDetail.setCreateTime(DateUtil.date());
        merchantAccountDetail.setUpdateTime(DateUtil.date());
        merchantAccountDetail.setPaymentChannel(record.getRechargeChannel());
        merchantAccountDetailMapper.insert(merchantAccountDetail);
    }

    private PaymentMerchantInfo getPaymentMerchantInfo(String merchantNo) {
        PaymentMerchantInfo paymentMerchantInfo = new PaymentMerchantInfo();
        paymentMerchantInfo.setChannel(PaymentChannelEnum.YUNZB.name());
        paymentMerchantInfo.setMerchantNo(merchantNo);
        PaymentMerchantInfo rPaymentMerchantInfo = paymentMerchantInfoMapper.selectOne(paymentMerchantInfo);
        return rPaymentMerchantInfo;
    }

}
