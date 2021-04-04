package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.MerchantAccountDetail;
import com.mmtax.business.domain.MerchantInfo;
import com.mmtax.business.domain.TianJinPaymentInfo;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.IMerchantAccountService;
import com.mmtax.business.service.ITianJinService;
import com.mmtax.business.tianjindto.TianJinRequestResultDTO;
import com.mmtax.common.constant.tianjin.TianJinConstants;
import com.mmtax.common.enums.AccountDetailStatusEnum;
import com.mmtax.common.enums.MerchantAccountDetailTypeEnum;
import com.mmtax.common.enums.TrxOrderStatusEnum;
import com.mmtax.common.utils.redis.RedisLockConstans;
import com.mmtax.common.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Author: WangZhaoXu
 * @Date: 2020/3/22 2:18 下午
 */
@Service
public class TianJinServiceImpl extends CommonServiceImpl implements ITianJinService {

    @Resource
    private MerchantInfoMapper merchantInfoMapper;
    @Resource
    private MerchantAccountMapper merchantAccountMapper;
    @Resource
    private TrxOrderMapper trxOrderMapper;
    @Resource
    private MerchantAccountDetailMapper merchantAccountDetailMapper;
    @Resource
    TianJinPaymentInfoMapper tianJinPaymentInfoMapper;
    @Resource
    MerchantKeyMapper merchantKeyMapper;

    @Autowired
    private IMerchantAccountService merchantAccountService;

    @Override
    public TianJinRequestResultDTO settNotify(JSONObject jsonObject) {
        TianJinRequestResultDTO dto = new TianJinRequestResultDTO();
        dto.setResp_code(TianJinConstants.RESP_CODE);
        dto.setResp_message("SUCCESS");
        dto.setData("true");
        logger.info("打款通知参数为：{}", jsonObject);

        String value = UUID.randomUUID().toString();
        TianJinPaymentInfo tianJinPaymentInfo = new TianJinPaymentInfo();
        tianJinPaymentInfo.setServerUserUuid(jsonObject.getString(TianJinConstants.SERVER_USER_UUID));
        tianJinPaymentInfo.setCustomerAccountUuid(jsonObject.getString(TianJinConstants.CUSTOMER_ACCOUNT_UUID));
        TianJinPaymentInfo resultTianJinPaymentInfo= tianJinPaymentInfoMapper.selectOne(tianJinPaymentInfo);
        //查询商户信息
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(resultTianJinPaymentInfo.getMerchantId());
        String key = RedisLockConstans.ACCOUNT_LOCK + merchantInfo.getMerchantCode();
        try {
            //查询原始订单信息
            TrxOrder trxOrder = new TrxOrder();
            trxOrder.setOrderSerialNum(jsonObject.getString(TianJinConstants.ORDER_NO));
            trxOrder.setMerchantId(resultTianJinPaymentInfo.getMerchantId());
            TrxOrder resultTrxOrder = trxOrderMapper.selectOne(trxOrder);

            //若订单状态为成功状态
            if (TianJinConstants.SUCCESS_ORDER_STATUS_CODE.equals(jsonObject.getString(TianJinConstants.ORDER_STATUS))){
                resultTrxOrder.setOrderStatus(TrxOrderStatusEnum.PAID.code);
            }else {
                resultTrxOrder.setOrderStatus(TrxOrderStatusEnum.PAID_PENDING.code);
                //返回金额
                BigDecimal amount = resultTrxOrder.getAmount().add(resultTrxOrder.getCharge());
                try {
                    RedisUtil.lock(key, value);
                    //更新钱包余额
//                    merchantAccountMapper.updateMerchantAccount(resultTrxOrder.getMerchantId(), amount.toString());
                    merchantAccountService.updateMerchantAccountByVersion(resultTrxOrder.getMerchantId(),
                            amount);
                } finally {
                    RedisUtil.releaseDistributedLock(key, value);
                    logger.info("销毁钱包redis锁");
                }
                //更新钱包支出记录状态
                MerchantAccountDetail merchantAccountDetail = new MerchantAccountDetail();
                merchantAccountDetail.setType(MerchantAccountDetailTypeEnum.PAID.code);
                merchantAccountDetail.setOrderSerialNum(resultTrxOrder.getOrderSerialNum());
                merchantAccountDetail.setMerchantId(resultTrxOrder.getMerchantId());
                MerchantAccountDetail resultMerchantAccountDetail =
                        merchantAccountDetailMapper.selectOne(merchantAccountDetail);
                resultMerchantAccountDetail.setStatus(AccountDetailStatusEnum.FAIL.code);
                merchantAccountDetailMapper.updateByPrimaryKeySelective(resultMerchantAccountDetail);
            }
            resultTrxOrder.setUpdateTime(DateUtil.date());
            trxOrderMapper.updateByPrimaryKeySelective(resultTrxOrder);
            //异步回调通知
            callBackNotify(resultTrxOrder, merchantInfo, merchantKeyMapper);
        }catch (Exception e){
            logger.error("订单异常：{},{}", e, jsonObject);
        }
        return dto;
    }
}
