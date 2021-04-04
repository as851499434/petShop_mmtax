package com.mmtax.business.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.OrderReimburse;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.OnlineCustomerInfoMapper;
import com.mmtax.business.mapper.OrderReimburseMapper;
import com.mmtax.business.mapper.OrderSheetMapper;
import com.mmtax.business.mapper.TrxOrderMapper;
import com.mmtax.business.service.ICustomerAccountService;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.business.service.ITrxOrderService;
import com.mmtax.common.annotation.DataScope;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.onlinebank.OnlineCommonResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/12 16:59
 */
@Service
@Slf4j
public class TrxOrderServiceImpl extends  CommonServiceImpl implements ITrxOrderService {

    @Resource
    private TrxOrderMapper trxOrderMapper;
    @Resource
    private OrderSheetMapper orderSheetMapper;
    @Autowired
    private ICustomerAccountService customerAccountService;
    @Autowired
    private IOnlineBankService onlineBankService;
    @Autowired
    private OnlineCustomerInfoMapper onlineCustomerInfoMapper;
    @Autowired
    private OrderReimburseMapper orderReimburseMapper;



    /**
     * 退还服务费记录
     * @param startDate
     * @param endDate
     * @param payeeName
     * @return
     */
    @Override
    public List<ManagerReturnListTrxOrderDTO> returnListTrxOrder(String startDate, String endDate, String payeeName) {
        List<ManagerReturnListTrxOrderDTO> managerReturnListTrxOrderDTOS =
                trxOrderMapper.returnListTrxOrder( startDate, endDate, payeeName);
        return managerReturnListTrxOrderDTOS;
    }

    /**
     * 补交服务费记录
     * @param startDate
     * @param endDate
     * @param payeeName
     * @return
     */
    @Override
    public List<ManagerMakeUpListTrxOrderDTO> makeUpListTrxOrder(String startDate, String endDate, String payeeName) {
        List<ManagerMakeUpListTrxOrderDTO> managerMakeUpListTrxOrderDTO =
                trxOrderMapper.makeUpListTrxOrder(startDate, endDate, payeeName);
        return managerMakeUpListTrxOrderDTO;
    }

    @Override
    @DataScope(tableAlias = "t3")
    public List<ManagerTrxOrderDTO> getListTrxOrder(ManagerTrxOrderDTO managerTrxOrderDTO) {
        return trxOrderMapper.getListTrxOrder(managerTrxOrderDTO);
    }

    @Override
    @DataScope(tableAlias = "t3")
    public ManagerIndexPaymentDTO getMonthPayment(SysUserDTO sysUser) {
        String startDate;
        String endDate;
        startDate = DateUtil.format(DateUtil.beginOfMonth(DateUtil.date()), DatePattern.NORM_DATE_PATTERN);
        endDate = DateUtil.format(DateUtil.endOfMonth(DateUtil.date()), DatePattern.NORM_DATE_PATTERN);
        int num = trxOrderMapper.orderNum(startDate, endDate);
        //累计金额
        BigDecimal sumAmount = trxOrderMapper.getSumAmount(startDate, endDate);
        //初始化扣税金额
        BigDecimal taxFeeValue = BigDecimal.ZERO;
        if (sumAmount == null) {
            sumAmount = BigDecimal.ZERO;
        }
        sysUser.setStartDate(startDate);
        sysUser.setEndDate(endDate);
        ManagerIndexPaymentDTO dto = trxOrderMapper.getChannelPaymentBySaler(sysUser);
        if (dto == null) {
            dto = new ManagerIndexPaymentDTO();
        }
        dto.setOrderNum(String.valueOf(num));
        dto.setAmount(sumAmount.toString());
        dto.setTaxFeeValue(taxFeeValue.toString());
        if (dto.getaLiPayAmount() == null) {
            dto.setaLiPayAmount(BigDecimal.ZERO.toString());
        }
        if (dto.getWeChatAmount() == null) {
            dto.setWeChatAmount(BigDecimal.ZERO.toString());
        }
        if (dto.getBankAmount() == null) {
            dto.setBankAmount(BigDecimal.ZERO.toString());
        }
        return dto;
    }

    @Override
    public List<ManagerIndexYearDataDTO> getMonthAmount() {
        return trxOrderMapper.getMonthAmount();
    }

    @Override
    @DataScope(tableAlias = "t4")
    public List<ManagerOrderSheetDTO> getListOrderSheet(ManagerOrderSheetDTO dto) {
        return orderSheetMapper.getListOrderSheet(dto);
    }

    @Override
    @DataScope(tableAlias = "t3")
    public ManagerSaleStatisticsDTO getSaleStatistics(SysUserDTO sysUser) {
        ManagerSaleStatisticsDTO dto = new ManagerSaleStatisticsDTO();
        String startDate = DateUtil.format(DateUtil.beginOfDay(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
        String endDate = DateUtil.format(DateUtil.endOfDay(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
        sysUser.setStartDate(startDate);
        sysUser.setEndDate(endDate);
        BigDecimal dayAmount = trxOrderMapper.getSumAmountBySale(sysUser);
        BigDecimal dayChargeAmount = trxOrderMapper.getSumChargeBySale(sysUser);
        Integer dayOrderCount = trxOrderMapper.getCountOrderBySale(sysUser);
        startDate = DateUtil.format(DateUtil.beginOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
        endDate = DateUtil.format(DateUtil.endOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
        sysUser.setStartDate(startDate);
        sysUser.setEndDate(endDate);
        BigDecimal monthAmount = trxOrderMapper.getSumAmountBySale(sysUser);
        BigDecimal monthChargeAmount = trxOrderMapper.getSumChargeBySale(sysUser);
        Integer monthOrderCount = trxOrderMapper.getCountOrderBySale(sysUser);
        dto.setMonthAmount((monthAmount == null ? BigDecimal.ZERO : monthAmount).toString());
        dto.setMonthServiceAmount((monthChargeAmount == null ? BigDecimal.ZERO : monthChargeAmount).toString());
        dto.setTodayAmount((dayAmount == null ? BigDecimal.ZERO : dayAmount).toString());
        dto.setTodayServiceAmount((dayChargeAmount == null ? BigDecimal.ZERO : dayChargeAmount).toString());
        dto.setTodayOrderCount(dayOrderCount);
        dto.setMonthOrderCount(monthOrderCount);
        return dto;
    }

    @Override
    @DataScope(tableAlias = "t3")
    public ManagerIndexPaymentDTO cumulativePaymentBySale(SysUserDTO sysUser) {
        ManagerIndexPaymentDTO dto;
        BigDecimal amount = trxOrderMapper.getSumAmountBySale(sysUser);
        BigDecimal serviceAmount = trxOrderMapper.getSumChargeBySale(sysUser);
        Integer count = trxOrderMapper.getCountOrderBySale(sysUser);
        dto = trxOrderMapper.getChannelPaymentBySaler(sysUser);
        dto.setTaxFeeValue((serviceAmount == null ? BigDecimal.ZERO : serviceAmount).toString());
        dto.setAmount((amount == null ? BigDecimal.ZERO : amount).toString());
        dto.setOrderNum(count.toString());
        return dto;
    }

    @Override
    @DataScope(tableAlias = "t3")
    public List<MangerMerchantStatisticsDTO> getMerchantMonthPaidStatistics(SysUserDTO sysUser) {
        String str = "-01";
        List<MangerMerchantStatisticsDTO> list = new ArrayList<>();
        String startDate = DateUtil.format(DateUtil.beginOfMonth(DateUtil.parse(sysUser.getSelectDate() + str)),
                DatePattern.NORM_DATETIME_PATTERN);
        String endDate = null;
        if (DateUtil.betweenMonth(DateUtil.beginOfMonth(DateUtil.parse(sysUser.getSelectDate() + str)), DateUtil.date(),
                false) == 0) {
            endDate = DateUtil.format(DateUtil.endOfDay(DateUtil.offsetDay(DateUtil.date(), -1)),
                    DatePattern.NORM_DATETIME_PATTERN);
        } else {
            endDate = DateUtil.format(DateUtil.endOfMonth(DateUtil.parse(sysUser.getSelectDate() + str)),
                    DatePattern.NORM_DATETIME_PATTERN);
        }
        sysUser.setStartDate(startDate);
        sysUser.setEndDate(endDate);
        list = trxOrderMapper.getMerchantMonthPaidStatistics(sysUser);
        return list;
    }

    @Override
    @DataScope(tableAlias = "t3")
    public List<MangerMerchantStatisticsDTO> getMerchantYearPaidStatistics(SysUserDTO sysUser) {
        List<MangerMerchantStatisticsDTO> list = new ArrayList<>();
        list = trxOrderMapper.getMerchantYearPaidStatistics(sysUser);
        return list;
    }

    @Override
    @DataScope(tableAlias = "t3")
    public List<ManagerSaleStatisticDTO> getSaleStatistic(ManagerSaleStatisticDTO dto) {
        List<ManagerSaleStatisticDTO> list = trxOrderMapper.getSaleStatistic(dto);
        return list;
    }

    @Override
    public void composeTrxOrderPay(TrxOrder trxOrder,BigDecimal transferAmount) {
        if(!NeedPayCardEnum.NEED.getStatus().equals(trxOrder.getNeedPayCard())){
            log.info("订单{}不需要打到卡，不执行打款",trxOrder.getOrderSerialNum());
            return;
        }
        if(!TrxOrderStatusEnum.INIT.code.equals(trxOrder.getOrderStatus())){
            log.info("订单{}状态不为初始化，不执行打款",trxOrder.getOrderSerialNum());
            return;
        }
        //员工出账冻结并添加变动记录
        customerAccountService.updateAccountAndInsertDetail(trxOrder.getCustomerId(), transferAmount
                , AccountDetailTypeEnum.DEBIT_FREEZE, trxOrder.getOrderSerialNum());
        //订单进行提现
        OnlineCommonResultDTO resultDTO = onlineBankService.tradePayToCard(trxOrder);
        trxOrder.setOrderStatus(TrxOrderStatusEnum.UNPAID.code);
        if (!"T".equals(resultDTO.getIs_success())) {
            //失败的时候
            trxOrder.setOrderStatus(TrxOrderStatusEnum.PAID_PENDING.code);
            trxOrder.setComment(resultDTO.getError_message());
            trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
            //员工入账解冻并添加记录
            customerAccountService.updateAccountAndInsertDetail(trxOrder.getCustomerId(), transferAmount
                    , AccountDetailTypeEnum.CREDIT_UNFREEZE, trxOrder.getOrderSerialNum());
        }
        trxOrder.setUpdateTime(DateUtil.date());
        trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
    }

    @Override
    public void changeTrxOrderSuccess(TrxOrder trxOrder) {
        if(!NeedPayCardEnum.NO_NEED.getStatus().equals(trxOrder.getNeedPayCard())){
            log.info("订单{}需要打到卡，不作处理",trxOrder.getOrderSerialNum());
            return;
        }
        //更新订单状态为成功
        log.info("打款单号{}提现成功",trxOrder.getOrderSerialNum());
        trxOrder.setOrderStatus(TrxOrderStatusEnum.PAID.code);
        trxOrder.setUpdateTime(DateUtil.date());
        trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
    }

    @Override
    public void changeTrxOrderFail(TrxOrder trxOrder,String errorMsg) {
        if(!NeedPayCardEnum.NO_NEED.getStatus().equals(trxOrder.getNeedPayCard())){
            log.info("订单{}需要打到卡，不作处理",trxOrder.getOrderSerialNum());
            return;
        }
        //更新订单状态为成功
        log.info("打款单号{}提现失败",trxOrder.getOrderSerialNum());
        trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
        trxOrder.setComment(errorMsg);
        trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
        trxOrder.setUpdateTime(DateUtil.date());
        trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
    }

    /**
     * 推广列表查询
     * @param queryCustomerPromotionDTO
     * @return
     */
    @Override
    public List<CustomerPromotionDTO> getPromotionList(QueryCustomerPromotionDTO queryCustomerPromotionDTO) {
        long between = 0;
        if(!StringUtils.isEmpty(queryCustomerPromotionDTO.getStartTime())
                && !StringUtils.isEmpty(queryCustomerPromotionDTO.getEndTime())){
            between = DateUtil.between(DateUtil.parse(queryCustomerPromotionDTO.getStartTime()),
                    DateUtil.parse(queryCustomerPromotionDTO.getEndTime()), DateUnit.DAY);
        }
        if(between > 31){
            throw new BusinessException("信息创建时间只能搜索31天以内的!");
        }
        if(!StringUtils.isEmpty(queryCustomerPromotionDTO.getPromotionStartTime())
                && !StringUtils.isEmpty(queryCustomerPromotionDTO.getPromotionEndTime())){
            between = DateUtil.between(DateUtil.parse(queryCustomerPromotionDTO.getPromotionStartTime()),
                    DateUtil.parse(queryCustomerPromotionDTO.getPromotionEndTime()), DateUnit.DAY);
        }
        if(between > 31){
            throw new BusinessException("推广时间只能搜索31天以内的!");
        }
        return trxOrderMapper.getPromotionList(queryCustomerPromotionDTO);
    }

    @Override
    public Page listPromotions(QueryCustomerPromotionDTO queryCustomerPromotionDTO) {
        QueryPage queryPage = convertQueryPage(queryCustomerPromotionDTO.getCurrentPage(),queryCustomerPromotionDTO.getPageSize());

        queryCustomerPromotionDTO.setStartIndex(queryPage.getStartIndex());
        queryCustomerPromotionDTO.setCurrentPage(queryPage.getPageSize());
        List<CustomerPromotionDTO> promotionList = trxOrderMapper.listPromotions(queryCustomerPromotionDTO);
        int count = trxOrderMapper.countPromotion(queryCustomerPromotionDTO);

        return new Page(count, promotionList, queryPage.getCurrentPage(), queryPage.getPageSize());
    }

    @Override
    public List<ChargeReimburseInfoDTO> getRechargeReimburseList(QueryChargeReimburseInfoDTO queryCustomerPromotionDTO) {
        return orderReimburseMapper.listChargeReimburseInfoDTOs(queryCustomerPromotionDTO);
    }


}
