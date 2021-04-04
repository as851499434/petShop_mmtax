package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import com.alibaba.fastjson.JSON;
import com.mmtax.business.domain.BatchPaymentRecord;
import com.mmtax.business.domain.MerchantInfo;
import com.mmtax.business.domain.PayRequestData;
import com.mmtax.business.dto.PayDataDTO;
import com.mmtax.business.dto.QueryCheckInfoResultDTO;
import com.mmtax.business.dto.SendToCheckDTO;
import com.mmtax.business.mapper.BatchPaymentRecordMapper;
import com.mmtax.business.mapper.MerchantInfoMapper;
import com.mmtax.business.mapper.PayRequestDataMapper;
import com.mmtax.common.constant.Constants;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.RegexUtil;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.http.HttpUtils;
import com.mmtax.common.utils.redis.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IPayRequestDataService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.mmtax.common.utils.RegexUtil.regexBankNo;
import static com.mmtax.common.utils.RegexUtil.regexName;

/**
 * 打款请求数据 服务层实现
 * 
 * @author meimiao
 * @date 2020-11-03
 */
@Service
public class PayRequestDataServiceImpl extends CommonServiceImpl implements IPayRequestDataService
{

    @Value("${payment.url.datacheck}")
    private String VERIFY_URL;
    @Autowired
    PayRequestDataMapper payRequestDataMapper;
    @Autowired
    BatchPaymentRecordMapper batchPaymentRecordMapper;
    @Autowired
    MerchantInfoMapper merchantInfoMapper;

    @Override
    public Page<PayRequestData> listPayData(int merchantId, String batchNo,int pageSize,int currentPage) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        BatchPaymentRecord batchPaymentRecord = new BatchPaymentRecord();
        batchPaymentRecord.setMerchantId(merchantId);
        batchPaymentRecord.setBatchNo(batchNo);
        batchPaymentRecord = batchPaymentRecordMapper.selectOne(batchPaymentRecord);
        PayRequestData selectObject = new PayRequestData();
        selectObject.setBatchPaymentRecordId(batchPaymentRecord.getId());
        selectObject.setDelStatus(DeleteEnum.UN_DELETE.getCode());
        int count = payRequestDataMapper.selectCount(selectObject);
        List<PayRequestData> payRequestDatas = payRequestDataMapper.selectListPagination(batchPaymentRecord.getId()
                ,queryPage.getPageSize(),queryPage.getStartIndex());
        return new Page<>(count, payRequestDatas, currentPage, pageSize);
    }

    @Override
    public void updatePayData(PayDataDTO dto) {
        dto.getCheckDatas().forEach(one->{
            PayRequestData payRequestData = payRequestDataMapper.selectByPrimaryKey(one.getId());
            if(PayDataCheckStatusEnum.SUCESS.getStatus().equals(payRequestData.getStatus())){
                logger.info("成功记录不予修改");
                return;
            }
            BeanUtils.copyProperties(one,payRequestData);
            payRequestData.setStatus(PayDataCheckStatusEnum.NEEDCHECK.getStatus());
            payRequestData.setUpdateTime(DateUtil.date());
            payRequestDataMapper.updateByPrimaryKeySelective(payRequestData);
        });
    }

    @Override
    public Boolean queryCheckOver(String batchNo, Integer merchantId) {
        BatchPaymentRecord batchPaymentRecord = new BatchPaymentRecord();
        batchPaymentRecord.setMerchantId(merchantId);
        batchPaymentRecord.setBatchNo(batchNo);
        batchPaymentRecord = batchPaymentRecordMapper.selectOne(batchPaymentRecord);
        PayRequestData payRequestData = new PayRequestData();
        payRequestData.setBatchPaymentRecordId(batchPaymentRecord.getId());
        payRequestData.setStatus(PayDataCheckStatusEnum.PROCESS.getStatus());
        payRequestData.setDelStatus(DeleteEnum.UN_DELETE.getCode());
        int count = payRequestDataMapper.selectCount(payRequestData);
        return count > 0?true:false;
    }

    @Override
    public QueryCheckInfoResultDTO queryCheckInfo(String batchNo,Integer merchantId) {
        QueryCheckInfoResultDTO queryCheckInfoResultDTO = new QueryCheckInfoResultDTO();
        BatchPaymentRecord batchPaymentRecord = new BatchPaymentRecord();
        batchPaymentRecord.setBatchNo(batchNo);
        batchPaymentRecord.setMerchantId(merchantId);
        batchPaymentRecord = batchPaymentRecordMapper.selectOne(batchPaymentRecord);

        PayRequestData payRequestData = new PayRequestData();
        payRequestData.setBatchPaymentRecordId(batchPaymentRecord.getId());
        List<PayRequestData> payRequestDatas = payRequestDataMapper.select(payRequestData);
        //通过验证订单数、总金额和服务费
        long successCount = payRequestDatas.stream()
                .filter(p -> PayDataCheckStatusEnum.SUCESS.getStatus().equals(p.getStatus()))
                .filter(p -> DelStatusEnum.NORMAL.code.equals(p.getDelStatus()))
                .count();
        BigDecimal successAmounts = payRequestDatas.stream()
                .filter(p -> PayDataCheckStatusEnum.SUCESS.getStatus().equals(p.getStatus()))
                .filter(p -> DelStatusEnum.NORMAL.code.equals(p.getDelStatus()))
                .map(PayRequestData::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal successCharges = payRequestDatas.stream()
                .filter(p -> PayDataCheckStatusEnum.SUCESS.getStatus().equals(p.getStatus()))
                .filter(p -> DelStatusEnum.NORMAL.code.equals(p.getDelStatus()))
                .map(PayRequestData::getCharge).reduce(BigDecimal.ZERO, BigDecimal::add);

        //失败订单数 和 总金额
        long failCount = payRequestDatas.stream()
                .filter(p -> PayDataCheckStatusEnum.FAIL.getStatus().equals(p.getStatus()))
                .filter(p -> DelStatusEnum.NORMAL.code.equals(p.getDelStatus()))
                .count();
        BigDecimal failAmounts = payRequestDatas.stream()
                .filter(p -> PayDataCheckStatusEnum.FAIL.getStatus().equals(p.getStatus()))
                .filter(p -> DelStatusEnum.NORMAL.code.equals(p.getDelStatus()))
                .map(PayRequestData::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        //待处理订单数 和 总金额
        long processCount = payRequestDatas.stream()
                .filter(p -> PayDataCheckStatusEnum.PROCESS.getStatus().equals(p.getStatus()))
                .filter(p -> DelStatusEnum.NORMAL.code.equals(p.getDelStatus()))
                .count();
        BigDecimal processAmounts = payRequestDatas.stream()
                .filter(p -> PayDataCheckStatusEnum.PROCESS.getStatus().equals(p.getStatus()))
                .filter(p -> DelStatusEnum.NORMAL.code.equals(p.getDelStatus()))
                .map(PayRequestData::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

        queryCheckInfoResultDTO.setSuccessCount(successCount);
        queryCheckInfoResultDTO.setSuccessAmounts(successAmounts);
        queryCheckInfoResultDTO.setSuccessCharges(successCharges);
        queryCheckInfoResultDTO.setFailCount(failCount);
        queryCheckInfoResultDTO.setFailAmounts(failAmounts);
        queryCheckInfoResultDTO.setProcessCount(processCount);
        queryCheckInfoResultDTO.setProcessAmounts(processAmounts);
        return queryCheckInfoResultDTO;
    }

    @Override
    public void deletePayData(PayDataDTO dto) {
        dto.getDeleteIds().forEach(one->{
            PayRequestData payRequestData = payRequestDataMapper.selectByPrimaryKey(one);
            payRequestData.setDelStatus(DeleteEnum.DELETE.getCode());
            payRequestData.setUpdateTime(DateUtil.date());
            payRequestDataMapper.updateByPrimaryKeySelective(payRequestData);
        });
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public void sendToCheck(SendToCheckDTO dto) {
        BatchPaymentRecord batchPaymentRecord = new BatchPaymentRecord();
        batchPaymentRecord.setMerchantId(dto.getMerchantId());
        batchPaymentRecord.setBatchNo(dto.getBatchNo());
        batchPaymentRecord = batchPaymentRecordMapper.selectOne(batchPaymentRecord);
        List<PayRequestData> checkDatas = payRequestDataMapper.selectListPagination(batchPaymentRecord.getId()
                ,null,null);
        //判断数据正确性
        checkDatas.forEach(one->{
            if(PayDataCheckStatusEnum.SUCESS.getStatus().equals(one.getStatus())) {
               logger.info("成功数据不校验");
               return;
            }
            checkData(one);
            if(PayDataCheckStatusEnum.NEEDCHECK.getStatus().equals(one.getStatus())) {
                one.setStatus(PayDataCheckStatusEnum.PROCESS.getStatus());
            }
            one.setUpdateTime(DateUtil.date());
            payRequestDataMapper.updateByPrimaryKeySelective(one);
        });
        //更新批次信息
        BigDecimal paymentAmount = checkDatas.stream()
                .reduce(BigDecimal.ZERO,(a,b)->a.add(b.getAmount()),(a,b)->BigDecimal.ZERO);
        batchPaymentRecord.setPaymentCount(checkDatas.size());
        batchPaymentRecord.setPaymentAmount(paymentAmount);
        batchPaymentRecord.setUpdateTime(DateUtil.date());
        batchPaymentRecordMapper.updateByPrimaryKeySelective(batchPaymentRecord);
        //移除之前的缓存
        String idcardNoAmountKey = Constants.IDCARDNO_AMOUNT + dto.getMerchantId() + dto.getBatchNo();
        String haveMakeUpKey = Constants.HAVE_MAKEUP + dto.getMerchantId() + dto.getBatchNo();
        RedisUtil.remove(idcardNoAmountKey);
        RedisUtil.remove(haveMakeUpKey);
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(dto.getMerchantId());
        RedisUtil.remove(merchantInfo.getMerchantCode()+dto.getBatchNo());
        //所有校验完成后发送消息到队列所在接口
        String info = HttpUtils.sendPost(VERIFY_URL, JSON.toJSONString(checkDatas));
        logger.info("发送消息到队列所在接口{}返回的结果：{}",VERIFY_URL,info);
        if(StringUtils.isEmpty(info) || !NotifyStatusEnum.SUCCESS.name().equals(info)){
            throw new BusinessException("校验失败，请联系管理员");
        }
    }

    /** 校验数据 */
    private void checkData(PayRequestData one){
        if(StringUtils.isEmpty(one.getName())){
            throw new BusinessException("有数据姓名为空，请补充");
        }
        String name = StringUtils.trim(one.getName());
        if (!regexName(name)) {
            throw new BusinessException("姓名"+name+"不符合规范");
        }
        one.setName(name);

        if (StringUtils.isEmpty(one.getIdCardNo())) {
            throw new BusinessException("有数据身份证为空，请补充");
        }
        String idCardNo = StringUtils.trim(one.getIdCardNo());
        if (!IdcardUtil.isValidCard(idCardNo)) {
            logger.info("身份证号格式为17位数字+X或数字idCardNo:{}", idCardNo);
            throw new BusinessException("身份证"+idCardNo+"不符合规范");
        }
        one.setIdCardNo(idCardNo);

        if (StringUtils.isEmpty(one.getBankCard())) {
            if (PaymentEnum.BANK.name().equals(one.getPaymentChannel())) {
                throw new BusinessException("有数据银行卡号为空，请补充");
            }
            if (PaymentEnum.ALIPAY.name().equals(one.getPaymentChannel())) {
                throw new BusinessException("有数据支付宝号为空，请补充");
            }
        }
        String bankNo = StringUtils.trim(one.getBankCard());
        if (!(PaymentEnum.ALIPAY.name().equals(one.getPaymentChannel())) && !regexBankNo(bankNo)) {
            throw new BusinessException("收款账户"+bankNo+"不符合规范");
        }
        one.setBankCard(bankNo);

        if (StringUtils.isEmpty(one.getMobile())) {
            throw new BusinessException("有数据预留手机号为空，请补充");
        }
        String mobile = StringUtils.trim(one.getMobile());
        if (!RegexUtil.regexPhoneLegalNo(mobile)) {
            throw new BusinessException("预留手机号"+mobile+"不符合规范");
        }
        one.setMobile(mobile);

        if (StringUtils.isNotEmpty(one.getRemark()) && one.getRemark().length() > 20) {
            throw new BusinessException("备注长度\'"+one.getRemark()+"\'不可超过20个字");
        }
    }
}
