package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.MakeUpChargeDetail;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.MakeUpChargeDetailMapper;
import com.mmtax.business.mapper.MerchantAccountDetailMapper;
import com.mmtax.business.mapper.ReturnChargeOrderMapper;
import com.mmtax.business.mapper.TrxOrderMapper;
import com.mmtax.business.service.IMerchantTransactionService;
import com.mmtax.common.enums.InOutDeductionEnum;
import com.mmtax.common.enums.TrxOrderStatusEnum;
import com.mmtax.common.enums.UseBigRateEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.CurrencyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 交易管理服务层实现
 *
 * @author yuanligang
 * @date 2019/7/12
 */
@Service
public class MerchantTransactionServiceImpl extends CommonServiceImpl implements IMerchantTransactionService {
    @Resource
    private TrxOrderMapper trxOrderMapper;
    @Resource
    private MerchantAccountDetailMapper merchantAccountDetailMapper;
    @Resource
    private ReturnChargeOrderMapper returnChargeOrderMapper;
    @Resource
    private MakeUpChargeDetailMapper makeUpChargeDetailMapper;




    /**
     * 获取退还服务费订单列表
     */
    @Override
    public Page listReturnOrders(int merchantId, int pageSize, int currentPage, String startDate, String endDate,
                                 String payeeName,String orderSerialNum, String returnSerialum,  String payeeBankCard
                                    ) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<ReturnMakeUpChargeOrderDTO> returnMakeUpChargeOrderDTOS = returnChargeOrderMapper.listReturnOrders(
                merchantId, queryPage.getPageSize(), queryPage.getStartIndex(), startDate, endDate, payeeName,
                orderSerialNum, returnSerialum, payeeBankCard);

        int count = returnChargeOrderMapper.countReturnOrders(merchantId,startDate, endDate, payeeName, orderSerialNum,
                returnSerialum, payeeBankCard);

        return new Page(count, returnMakeUpChargeOrderDTOS, currentPage, pageSize);
    }

    @Override
    public List<ReturnMakeUpChargeOrderDTO> exportReturnOrder(int merchantId,  String startDate, String endDate,
                                                              String payeeName,String orderSerialNum,
                                                              String returnSerialum,  String payeeBankCard) {

        List<ReturnMakeUpChargeOrderDTO> list = returnChargeOrderMapper.listReturnOrders(merchantId, null,
                null, startDate, endDate, payeeName, orderSerialNum, returnSerialum, payeeBankCard);
        return list;
    }

    /**
     * 获取补缴服务费订单列表
     */
    @Override
    public Page listMakeUpOrders(int merchantId, int pageSize, int currentPage, String startDate, String endDate,
                                 String payeeName,String orderSerialNum, String makeUpSerialNum,  String payeeBankCard){

        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<ReturnMakeUpChargeOrderDTO> returnMakeUpChargeOrderDTOS = makeUpChargeDetailMapper.listMakeUpOrders(
                merchantId,queryPage.getPageSize() , queryPage.getStartIndex(), startDate, endDate, payeeName,
                orderSerialNum, makeUpSerialNum, payeeBankCard);

        int count = makeUpChargeDetailMapper.countMakeUpOrders(merchantId,startDate, endDate, payeeName, orderSerialNum,
                makeUpSerialNum, payeeBankCard);

        return new Page(count, returnMakeUpChargeOrderDTOS, currentPage, pageSize);
    }


    @Override
    public List<ReturnMakeUpChargeOrderDTO> exportMakeUpOrder(int merchantId,  String startDate, String endDate,
                                                              String payeeName,String orderSerialNum,
                                                              String makeUpSerialNum,  String payeeBankCard) {

        List<ReturnMakeUpChargeOrderDTO> list = makeUpChargeDetailMapper.listMakeUpOrders(merchantId, null,
                null, startDate, endDate, payeeName, orderSerialNum, makeUpSerialNum, payeeBankCard);

        return list;
    }


    /**
     * 获取交易订单列表
     */
    @Override
    public Page listOrders(int merchantId, int pageSize, int currentPage, String startDate, String endDate,
                           String merchantName, String orderSerialNum, String merchantSerialNum, String payeeBankCard,
                           String payeeName, String payeeIdCardNo, Integer orderStatus, String paymentChannel) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<ListTrxOrdersDTO> list = trxOrderMapper.listTrxOrders(merchantId, pageSize, queryPage.getStartIndex(), startDate,
                endDate, merchantName, orderSerialNum, merchantSerialNum, payeeBankCard, payeeName, payeeIdCardNo,
                orderStatus, paymentChannel);
        int count = trxOrderMapper.countTrxOrders(merchantId, startDate, endDate, merchantName, orderSerialNum,
                merchantSerialNum, payeeBankCard, payeeName, payeeIdCardNo, orderStatus, paymentChannel);
        // todo 查询条件待定
        return new Page(count, list, currentPage, pageSize);
    }

    @Override
    public List<ListTrxOrdersDTO> exportOrder(int merchantId, String startDate, String endDate, String merchantName,
                                      String orderSerialNum, String merchantSerialNum, String payeeBankCard,
                                      String payeeName, String payeeIdCardNo, Integer orderStatus, String paymentChannel) {
        List<ListTrxOrdersDTO> list = trxOrderMapper.listTrxOrders(merchantId, null, null, startDate, endDate,
                merchantName, orderSerialNum,
                merchantSerialNum, payeeBankCard, payeeName, payeeIdCardNo, orderStatus, paymentChannel);
        return list;
    }

    /**
     * 获取挂起订单列表
     */
    @Override
    public Page listHangsOrders(int merchantId, int pageSize, int currentPage, String startDate, String endDate,
                                String merchantName, String orderSerialNum, String merchantSerialNum,
                                String payeeBankCard, String payeeName, String payeeIdCardNo, Integer orderStatus,
                                String paymentChannel) {
        if (TrxOrderStatusEnum.PAID_PENDING.code.equals(orderStatus)) {
            QueryPage queryPage = convertQueryPage(currentPage, pageSize);
            List<ListTrxOrdersDTO> list = trxOrderMapper.listTrxOrders(merchantId, pageSize, queryPage.getStartIndex(),
                    startDate, endDate, merchantName, orderSerialNum, merchantSerialNum, payeeBankCard, payeeName,
                    payeeIdCardNo, orderStatus, paymentChannel);
            int count = trxOrderMapper.countTrxOrders(merchantId, startDate, endDate, merchantName, orderSerialNum,
                    merchantSerialNum, payeeBankCard, payeeName, payeeIdCardNo, orderStatus, paymentChannel);
            // todo 查询条件待定
            return new Page(count, list, currentPage, pageSize);
        } else {
            throw new BusinessException("订单状态错误");
        }

    }

    /**
     * 获取流水详情
     */
    @Override
    public TrxOrderDTO getOrderDetail(int id) {
        TrxOrderDTO trxOrderDTO = trxOrderMapper.getTrxDetail(id);
        if (trxOrderDTO == null) {
            throw new BusinessException("交易流水不存在");
        }
        if(UseBigRateEnum.BIGRATE.getStatus().equals(trxOrderDTO.getUseBigRate())){
            trxOrderDTO.setCharge(trxOrderDTO.getChargeBig());
        }

        return trxOrderDTO;
    }

    /**
     * 电子凭证获取
     */
    @Override
    public ElectronicVoucherDTO getVoucher(int id) {
        ElectronicVoucherDTO electronicVoucher = new ElectronicVoucherDTO();
        electronicVoucher =  trxOrderMapper.getVoucher(id);
        if (electronicVoucher == null) {
            throw new BusinessException("电子凭单不存在");
        }
        try {
            // 大写金额转换
            electronicVoucher.setUpperAmount(CurrencyUtil.convert(CurrencyUtil.moneyFormat(electronicVoucher.getAmount())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        electronicVoucher.setTimestamp(String.valueOf(DateUtil.parse(electronicVoucher.getCreateTime()).getTime()));
        return  electronicVoucher;
    }

    /**
     * 交易管理--资金流水
     */
    @Override
    public Page getCapitalFlow(Integer merchantId, Integer pageSize, Integer currentPage,
                                                       String startDate, String endDate, String orderSerialNum,
                                                       Integer type) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<MerchantCapitalFlowDTO> list = merchantAccountDetailMapper.getCapitalFlow(merchantId, pageSize,
                queryPage.getStartIndex(), startDate, endDate, orderSerialNum, type);
        for (MerchantCapitalFlowDTO one:list) {
            TrxOrder order = new TrxOrder();
            order.setOrderSerialNum(one.getOrderSerialNum());
            order = trxOrderMapper.selectOne(order);
            if(null == order){
                continue;
            }
            if(TrxOrderStatusEnum.INIT.code.equals(order.getOrderStatus()) ||
                    TrxOrderStatusEnum.UNPAID.code.equals(order.getOrderStatus())){
                one.setCreateTime("进行中");
            }
            if(TrxOrderStatusEnum.PAID_PENDING.code.equals(order.getOrderStatus()) ||
                    TrxOrderStatusEnum.ORDER_FAIL.code.equals(order.getOrderStatus())){
                one.setCreateTime("订单失败/挂起,"+order.getComment());
            }
        }
        int count = merchantAccountDetailMapper.countCapitalFlow(merchantId, startDate, endDate, orderSerialNum, type);
        // todo 查询条件待定
        return new Page(count, list, currentPage, pageSize);
    }

    @Override
    public List<MerchantCapitalFlowDTO> exportCapitalFlow(Integer merchantId, String startDate, String endDate,
                                                          String orderSerialNum, Integer type) {
        List<MerchantCapitalFlowDTO> list = merchantAccountDetailMapper.getCapitalFlow(merchantId, null,
                null, startDate, endDate, orderSerialNum, type);
        List<String> orderSerialNums = list.stream().
                map(MerchantCapitalFlowDTO::getOrderSerialNum).collect(Collectors.toList());
        List<TrxOrder> orders = trxOrderMapper.selectInOrderSerialNums(orderSerialNums);
        List<String> orderSerialNumFails = orders.stream().
                filter(v-> !TrxOrderStatusEnum.PAID.code.equals(v.getOrderStatus())).map(TrxOrder::getOrderSerialNum).
                collect(Collectors.toList());
        list.forEach(one->{
            if(orderSerialNumFails.contains(one.getOrderSerialNum())){
                one.setCreateTime("订单失败/挂起，资金未入账/出账");
            }
        });
        return list;
    }

    @Override
    public MerchantStatisticalDataDTO getMerchantStatisticalData(String startDate, String endDate, String merchantName,
                                                                 String orderSerialNum, String merchantSerialNum,
                                                                 String payeeBankCard, String payeeName,
                                                                 String payeeIdCardNo, Integer orderStatus,
                                                                 Integer merchantId) {
        MerchantStatisticalDataDTO dataDTO = new MerchantStatisticalDataDTO();
        dataDTO = trxOrderMapper.getMerchantStatisticalOrderCount(startDate, endDate, merchantName, orderSerialNum,
                merchantSerialNum, payeeBankCard, payeeName, payeeIdCardNo, orderStatus, merchantId);
        if (dataDTO == null) {
            dataDTO = new MerchantStatisticalDataDTO();
        }
        MerchantStatisticalDataDTO rDataDTO = trxOrderMapper.getMerchantStatisticalOrderAmount(startDate, endDate,
                merchantName, orderSerialNum, merchantSerialNum, payeeBankCard, payeeName, payeeIdCardNo, orderStatus,
                merchantId);
        if (rDataDTO == null) {
            rDataDTO = new MerchantStatisticalDataDTO();
        }
        dataDTO.setFailOrderAmount(rDataDTO.getFailOrderAmount() == null ? BigDecimal.ZERO.toString() :
                rDataDTO.getFailOrderAmount());
        dataDTO.setSuccessOrderAmount(rDataDTO.getSuccessOrderAmount() == null ? BigDecimal.ZERO.toString() :
                rDataDTO.getSuccessOrderAmount());
        dataDTO.setSumOrderAmount(rDataDTO.getSumOrderAmount() == null ? BigDecimal.ZERO.toString() :
                rDataDTO.getSumOrderAmount());
        dataDTO.setUnFinishOrderAmount(rDataDTO.getUnFinishOrderAmount() == null ? BigDecimal.ZERO.toString() :
                rDataDTO.getUnFinishOrderAmount());
        BigDecimal charge = trxOrderMapper.getMerchantStatisticalOrderCharge(startDate, endDate,
                merchantName, orderSerialNum, merchantSerialNum, payeeBankCard, payeeName, payeeIdCardNo, orderStatus,
                merchantId);
        if (charge == null) {
            charge = BigDecimal.ZERO;
        }
        dataDTO.setMerchantServiceAmount(charge.toString());
        return dataDTO;
    }
}
