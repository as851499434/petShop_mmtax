package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.BatchPaymentRecord;
import com.mmtax.business.domain.PayRequestData;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.BatchPaymentRecordMapper;
import com.mmtax.business.mapper.PayRequestDataMapper;
import com.mmtax.business.mapper.TrxOrderMapper;
import com.mmtax.business.service.IBatchPaymentRecordService;
import com.mmtax.common.annotation.DataScope;
import com.mmtax.common.enums.BatchPaymentRecordStatusEnum;
import com.mmtax.common.enums.DeleteEnum;
import com.mmtax.common.enums.TrxOrderStatusEnum;
import com.mmtax.common.enums.UseBigRateEnum;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/13 13:54
 */
@Service
public class BatchPaymentRecordServiceImpl extends CommonServiceImpl implements IBatchPaymentRecordService {

    @Resource
    private BatchPaymentRecordMapper batchPaymentRecordMapper;

    @Resource
    private TrxOrderMapper trxOrderMapper;

    @Autowired
    private PayRequestDataMapper payRequestDataMapper;

    @Override
    @DataScope(tableAlias = "t3")
    public List<ManagerBatchPaymentRecordDTO> getListRecord(ManagerBatchPaymentRecordDTO recordDTO) {
        List<ManagerBatchPaymentRecordDTO> result = batchPaymentRecordMapper.getListManagerPaymentRecord(recordDTO);
        for(ManagerBatchPaymentRecordDTO one:result){
            TrxOrder trxOrder = new TrxOrder();
            trxOrder.setBatchNo(one.getBatchNo());
            trxOrder.setOrderStatus(TrxOrderStatusEnum.PAID.code);
            trxOrder.setMerchantId(one.getMerchantId());
            List<TrxOrder> trxOrders = trxOrderMapper.select(trxOrder);
            one.setActualAmount("0");
            if(!CollectionUtils.isEmpty(trxOrders)) {
                one.setActualAmount(trxOrders.stream().map(TrxOrder::getActualAmount).
                        reduce(BigDecimal.ZERO, BigDecimal::add).toString());
            }
//            Integer count = trxOrderMapper.countTrxOrderFinish(one.getId());
//            if(count.equals(one.getPaymentCount())
//                    && !BatchPaymentRecordStatusEnum.COMPLETE.code.equals(one.getStatus())){
//                one.setStatus(BatchPaymentRecordStatusEnum.COMPLETE.code.toString());
//                BatchPaymentRecord record = new BatchPaymentRecord();
//                record.setId(one.getId());
//                record.setStatus(BatchPaymentRecordStatusEnum.COMPLETE.code);
//                record.setUpdateTime(DateUtil.date());
//                batchPaymentRecordMapper.updateByPrimaryKeySelective(record);
//                one.setStatus(BatchPaymentRecordStatusEnum.COMPLETE.code.toString());
//            }
        }

        return result;

    }

    @Override
    public RecordNumberDTO getTotalRecord(Integer recordId) {
        RecordNumberDTO totalRecord = batchPaymentRecordMapper.getTotalRecord(recordId);

        PayRequestData payRequestData = new PayRequestData();
        payRequestData.setBatchPaymentRecordId(recordId);
        payRequestData.setDelStatus(DeleteEnum.UN_DELETE.getCode());
        totalRecord.setMerchantNum(payRequestDataMapper.selectCount(payRequestData));

        Example example = new Example(TrxOrder.class);
        List<Integer> exampleIns = new ArrayList<>();
        exampleIns.add(TrxOrderStatusEnum.PAID.getCode());
        exampleIns.add(TrxOrderStatusEnum.ORDER_FAIL.getCode());
        example.createCriteria().andEqualTo("merchantId",totalRecord.getMerchantId())
                .andEqualTo("batchNo",totalRecord.getBatchNo())
                .andIn("orderStatus",exampleIns);
        int count = trxOrderMapper.selectCountByExample(example);
        totalRecord.setSuccessCount(count);
        //合计 笔| 元
        MerchantBatchPaymentAmountDetailDTO detailDTO =
                getMerchantBatchPaymentAmountDetailDTO(recordId);
        totalRecord.setMerchantNum(detailDTO.getSuccessfulOrdersNum()+ detailDTO.getFailOrdersNum()+
                detailDTO.getProcessAndInitNum());
        totalRecord.setSystemAmount(new BigDecimal(detailDTO.getSuccessfulAmount())
                .add(new BigDecimal(detailDTO.getFailAmount()))
                .add(new BigDecimal(detailDTO.getProcessAndInitAmount())));

        return totalRecord;
    }

    @Override
    public List<MerchantBatchPaymentDetailDTO> getBatchPaymentDetail(Integer recordId) {
        return batchPaymentRecordMapper.getBatchPaymentDetailDTO(recordId);
    }

    @Override
    public Page getPageBatchPaymentRecord(String startDate, String endDate, Integer status, String batchNo,
                                          String paymentChannel, Integer merchantId, Integer currentPage, Integer pageSize) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        //查询列表
        List<MerchantBatchPaymentRecordDTO> list = batchPaymentRecordMapper.getListMerchantPaymentRecord(startDate,
                endDate, status, batchNo, paymentChannel, merchantId, queryPage.getStartIndex(), pageSize);
        list.forEach(o ->{
            Example example = new Example(TrxOrder.class);
            List<Integer> exampleIns = new ArrayList<>();
            exampleIns.add(TrxOrderStatusEnum.PAID.getCode());
            exampleIns.add(TrxOrderStatusEnum.ORDER_FAIL.getCode());
            example.createCriteria().andEqualTo("merchantId",merchantId)
                    .andEqualTo("batchNo",o.getBatchNo())
                    .andIn("orderStatus",exampleIns);
            int count = trxOrderMapper.selectCountByExample(example);
            o.setSuccessCount(count);

            PayRequestData payRequestData = new PayRequestData();
            payRequestData.setBatchPaymentRecordId(o.getId());
            payRequestData.setDelStatus(DeleteEnum.UN_DELETE.getCode());
            o.setCount(payRequestDataMapper.selectCount(payRequestData));
            //更新批次订单的状态
//            Integer completeCount = trxOrderMapper.countTrxOrderFinish(o.getId());
//            if(completeCount.equals(o.getCount()) && !BatchPaymentRecordStatusEnum.COMPLETE.code.equals(o.getStatus())){
//                BatchPaymentRecord record = new BatchPaymentRecord();
//                record.setId(o.getId());
//                record.setStatus(BatchPaymentRecordStatusEnum.COMPLETE.code);
//                record.setUpdateTime(DateUtil.date());
//                batchPaymentRecordMapper.updateByPrimaryKeySelective(record);
//                o.setStatus(BatchPaymentRecordStatusEnum.COMPLETE.code);
//            }
        });
        int count = batchPaymentRecordMapper.getCountMerchantPaymentRecord(startDate, endDate, status, batchNo,
                paymentChannel, merchantId);
        return new Page(count, list, currentPage, pageSize);
    }

    @Override
    public Page getPageBatchPaymentDetail(Integer merchantId, Integer id, Integer currentPage, Integer pageSize) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<MerchantBatchPaymentDetailDTO> list = trxOrderMapper.getListDetail(id,
                queryPage.getStartIndex(), pageSize);
        for (MerchantBatchPaymentDetailDTO dto:list) {
            if(UseBigRateEnum.BIGRATE.getStatus().equals(dto.getUseBigRate())){
                dto.setCharge(dto.getChargeBig());
            }
        }
        int count = trxOrderMapper.getCountDetail(id);
        return new Page(count, list, currentPage, pageSize);
    }

    @Override
    public List<MerchantBatchPaymentDetailDTO> exportBatchPaymentDetail(Integer merchantId, Integer id) {
        List<MerchantBatchPaymentDetailDTO> list = trxOrderMapper.getListDetail(id, null, null);
        for (MerchantBatchPaymentDetailDTO dto:list) {
            if(UseBigRateEnum.BIGRATE.getStatus().equals(dto.getUseBigRate())){
                dto.setCharge(dto.getChargeBig());
            }
        }
        return list;
    }


    @Override
    public MerchantBatchPaymentAmountDetailDTO getAmountDetail(Integer merchantId, Integer id) {

        return getMerchantBatchPaymentAmountDetailDTO(id);
    }

    private MerchantBatchPaymentAmountDetailDTO getMerchantBatchPaymentAmountDetailDTO(Integer id) {
        MerchantBatchPaymentAmountDetailDTO detailDTO = new MerchantBatchPaymentAmountDetailDTO();
        //获取进行中的订单数量和交易额  0
        BigDecimal processAmount = trxOrderMapper.getSumAmountByRecordId(id, TrxOrderStatusEnum.UNPAID.code);
        BigDecimal initAmount = trxOrderMapper.getSumAmountByRecordId(id,TrxOrderStatusEnum.INIT.code);
        BigDecimal transferAmount = trxOrderMapper.getSumAmountByRecordId(id,TrxOrderStatusEnum.PAID_PENDING.code);


        int processNum = trxOrderMapper.getCountByRecordId(id,TrxOrderStatusEnum.UNPAID.code);
        int initNum = trxOrderMapper.getCountByRecordId(id,TrxOrderStatusEnum.INIT.code);
        processNum = processNum + initNum;
        processNum = processNum + trxOrderMapper.getCountByRecordId(id,TrxOrderStatusEnum.PAID_PENDING.code);

        //获取代付成功交易额
        BigDecimal sumAmount = trxOrderMapper.getSumAmountByRecordId(id, TrxOrderStatusEnum.PAID.code);
        if (sumAmount == null) {
            sumAmount = BigDecimal.ZERO;
        }
        //获取代付成功交易数量  1
        int successNum = trxOrderMapper.getCountByRecordId(id, TrxOrderStatusEnum.PAID.code);
        //获取代付失败交易额
        BigDecimal failAmount = trxOrderMapper.getSumAmountByRecordId(id,
                TrxOrderStatusEnum.ORDER_FAIL.code);
        //获取代付失败交易数量   9
        int failNum = trxOrderMapper.getCountByRecordId(id, TrxOrderStatusEnum.ORDER_FAIL.code);
        //获取风险订单总额
        BigDecimal riskAmount = trxOrderMapper.getSumAmountByRecordId(id, TrxOrderStatusEnum.ORDER_SHEET.code);
        //获取风险订单数量
        int riskNum = trxOrderMapper.getCountByRecordId(id, TrxOrderStatusEnum.ORDER_SHEET.code);
        //获取手续费
        BigDecimal charge = trxOrderMapper.getSumCharge(id);
        if (charge == null) {
            charge = BigDecimal.ZERO;
        }
        if (failAmount == null) {
            failAmount = BigDecimal.ZERO;
        }
        if (riskAmount == null) {
            riskAmount = BigDecimal.ZERO;
        }
        if(processAmount == null){
            processAmount = BigDecimal.ZERO;
        }
        if(initAmount == null){
            initAmount = BigDecimal.ZERO;
        }
        if(transferAmount == null){
            transferAmount = BigDecimal.ZERO;
        }

        detailDTO.setFailAmount(failAmount.toString());
        detailDTO.setFailOrdersNum(failNum);
        detailDTO.setRiskOrderAmount(riskAmount.toString());
        detailDTO.setRiskOrdersNum(riskNum);
        detailDTO.setSuccessfulAmount(sumAmount.toString());
        detailDTO.setSuccessfulOrdersNum(successNum);
        detailDTO.setServiceAmount(charge.toString());
        detailDTO.setProcessAndInitAmount(processAmount.add(initAmount).add(transferAmount).toString());
        detailDTO.setProcessAndInitNum(processNum);
        return detailDTO;
    }


}
