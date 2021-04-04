package com.mmtax.business.service;

import com.mmtax.business.domain.TaxWithdrawAccount;
import com.mmtax.business.dto.ManagerTrxOrderDTO;
import com.mmtax.business.dto.TaxWithdrawAccountDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 税源地提现账户 服务层
 * 
 * @author meimiao
 * @date 2020-06-29
 */
public interface ITaxWithdrawAccountService
{
    /**
     * 添加税源地提现账户信息
     * @param taxWithdrawAccount
     */
    void withdrawAccountAddOrUpdate(TaxWithdrawAccount taxWithdrawAccount);
    /**
     * 查询税源地提现账户
     * @param id
     */
    TaxWithdrawAccount withdrawAccountView(Integer id);
    /**
     * 查询税源地提现账户
     */
    List<TaxWithdrawAccountDTO> withdrawAccountListView(TaxWithdrawAccountDTO taxWithdrawAccountDTO);
    /**
     * 删除税源地提现账户
     * @param id
     */
    void withdrawAccountDelete(Integer id);
    /**
     * 查看可提现余额
     * @return
     */
    BigDecimal viewAccountMoney(Integer sourceId);
    /**
     * 税源地账户提现
     * @param sourceId
     * @param money
     */
    String withdraw(Integer sourceId,BigDecimal money);

    /**
     * 过滤出税源地的提现记录
     * @param dto
     * @return
     */
    List<ManagerTrxOrderDTO> getListTrxOrderOfSource(ManagerTrxOrderDTO dto);
}
