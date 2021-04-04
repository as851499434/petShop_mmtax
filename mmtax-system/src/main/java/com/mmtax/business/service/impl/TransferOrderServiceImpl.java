package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.MerchantAccount;
import com.mmtax.business.domain.TransferOrder;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.IncomeWithdrawDTO;
import com.mmtax.business.dto.QuaryTransferOrderinfoDTO;
import com.mmtax.business.dto.TransferOrderinfoDTO;
import com.mmtax.business.mapper.MerchantAccountMapper;
import com.mmtax.business.mapper.TransferOrderMapper;
import com.mmtax.business.mapper.TrxOrderMapper;
import com.mmtax.business.service.*;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.onlinebank.OnlineCommonResultDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商户转账员工记录 服务层实现
 * 
 * @author meimiao
 * @date 2020-07-09
 */
@Service
public class TransferOrderServiceImpl extends BaseServiceImpl implements ITransferOrderService
{
    @Autowired
    private TransferOrderMapper transferOrderMapper;
    @Autowired
    private TrxOrderMapper trxOrderMapper;
    @Autowired
    private IOnlineBankService onlineBankService;
    @Autowired
    private IMerchantAccountService merchantAccountService;
    @Autowired
    private MerchantAccountMapper merchantAccountMapper;
    @Autowired
    private ICustomerAccountService customerAccountService;
    @Autowired
    IMerchantAccountDetailService merchantAccountDetailService;

    @Override
    public List<TransferOrderinfoDTO> listTranferOrderInfoDTO(QuaryTransferOrderinfoDTO quaryDTO) {
        return transferOrderMapper.listTranferOrderInfoDTO(quaryDTO);
    }

    @Override
    public void returnTranferAmount(String orderSerialNum) {
        TrxOrder trxOrder = trxOrderMapper.selectByOrderSerialNum(orderSerialNum);
        if(null == trxOrder){
            throw new BusinessException("未找到该打款订单");
        }
        if(!NeedPayCardEnum.NEED.getStatus().equals(trxOrder.getNeedPayCard())){
            throw new BusinessException("订单属于打款到灵工账户，联系开发人员操作");
        }
        if(!TrxOrderStatusEnum.PAID_PENDING.code.equals(trxOrder.getOrderStatus())){
            throw new BusinessException("该打款订单状态不是挂起，不予处理");
        }
        TransferOrder transferOrder = transferOrderMapper.selectByOrderSerialNumAndStatus(orderSerialNum
                ,TransferStatusEnum.PAID.getCode());
        if(null == transferOrder){
            throw new BusinessException("未找到打款订单对应的成功转账记录");
        }
        if(!TransferStatusEnum.PAID.getCode().equals(transferOrder.getStatus())){
            throw new BusinessException("款订单对应的转账记录未成功，不予处理");
        }

        TransferOrder returnTransferOrder = new TransferOrder();
        BeanUtils.copyProperties(transferOrder,returnTransferOrder);
        returnTransferOrder.setId(null);
        returnTransferOrder.setTransferSerialNum(ChanPayUtil.generateOutTradeNo());
        returnTransferOrder.setCreateTime(DateUtil.date());
        returnTransferOrder.setUpdateTime(DateUtil.date());
        returnTransferOrder.setAsyncStatus(AsyncStatusEnum.NOHANDLE.getStatus());
        returnTransferOrder.setStatus(TransferStatusEnum.RETURN_PAIDING.getCode());
        returnTransferOrder.setProviderId(null);
        transferOrderMapper.insertSelective(returnTransferOrder);
        trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
        trxOrder.setUpdateTime(DateUtil.date());
        trxOrderMapper.updateByPrimaryKeySelective(trxOrder);

        BigDecimal transferAmount = returnTransferOrder.getActualAmount();
        //出账冻结员工的账户并加变动记录
        customerAccountService.updateAccountAndInsertDetail(returnTransferOrder.getCustomerId(),transferAmount
                , AccountDetailTypeEnum.DEBIT_FREEZE,returnTransferOrder.getOrderSerialNum());
        //入账冻结商户的账户并加变动记录
        MerchantAccount beforeAccount = merchantAccountService.updateMerchantAccountByVersion(
                returnTransferOrder.getMerchantId(), BigDecimal.ZERO.subtract(transferAmount)
                , UpdateAccountTypeOfMethod.PAYSUCCESS.getType());
        MerchantAccount afterAccount = merchantAccountMapper.getMerchantAccountByMerchantId(returnTransferOrder.getMerchantId());
        merchantAccountDetailService.insertMerchantAccountDetail(transferAmount,beforeAccount,afterAccount
                , AccountDetailStatusEnum.SUCCESS.code,returnTransferOrder.getOrderSerialNum()
                , MerchantAccountDetailTypeEnum.PAID.code, DetailTypeEnum.PAYMENT.getCode());

        OnlineCommonResultDTO resultDTO = onlineBankService.transferAmountFromCusToMer(returnTransferOrder);
        if(!"T".equals(resultDTO.getIs_success())){
            //若未成功转账
            returnTransferOrder.setStatus(TransferStatusEnum.RETURN_FAIL.getCode());
            returnTransferOrder.setComment("回退失败："+resultDTO.getError_message());
            //这边相关商户和员工账户不解冻，让定时任务去处理
        }
        returnTransferOrder.setUpdateTime(DateUtil.date());
        transferOrderMapper.updateByPrimaryKeySelective(transferOrder);
    }
}
