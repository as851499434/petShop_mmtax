package com.mmtax.business.service;

import com.mmtax.business.domain.CustomerWithdraw;
import com.mmtax.business.dto.CusWithdrawDTO;
import com.mmtax.business.dto.CustomerWithdrawInfoDTO;
import com.mmtax.business.dto.QueryCustomerWithdrawInfoDTO;
import com.mmtax.business.dto.WithdrawPassDTO;
import com.mmtax.common.enums.PaymentEnum;
import com.mmtax.common.enums.WithdrawStatusEnum;
import com.mmtax.common.page.Page;

import java.util.List;

/**
 * 灵工提现记录 服务层
 * 
 * @author meimiao
 * @date 2020-09-11
 */
public interface ICustomerWithdrawService
{

    Boolean withDrawIsOpen(Integer customerId);

    /**
     * 处理异步状态未处理的
     * @param customerWithdraw
     * @param withdrawStatus
     */
    void handleAsyncStatus(CustomerWithdraw customerWithdraw, WithdrawStatusEnum withdrawStatus);

    /** 获取员工收入列表 */
    Page pageIncome(Integer pageSize, Integer currentPage, Integer customerId, String startDate, String endDate);

    /**
     * 查询灵工支付宝提现记录列表
     * @param queryDTO
     * @return
     */
    List<CustomerWithdrawInfoDTO> listCustomerWithdrawAlipayInfo(QueryCustomerWithdrawInfoDTO queryDTO);
    /**
     * 查询灵工银行卡提现记录列表
     * @param queryDTO
     * @return
     */
    List<CustomerWithdrawInfoDTO> listCustomerWithdrawBankInfo(QueryCustomerWithdrawInfoDTO queryDTO);

    /**
     * 提现
     * @param dto
     */
    void withDraw(CusWithdrawDTO dto, PaymentEnum withdrawChannel);

    /**
     * 判断是否设定过支付密码
     * @param customerId
     * @return
     */
    Boolean judgeHaveSetWithdrawPass(Integer customerId);

    /**
     * 設定支付密码
     * @param dto
     */
    void setWithdrawPass(WithdrawPassDTO dto);

    /**
     * 修改支付密码
     * @param dto
     */
    void changeWithdrawPass(WithdrawPassDTO dto);

    /**
     * 忘记密码发短信
     */
    void sendSmsCode(String mobile);

    /**
     * 重置支付密码
     * @param dto
     */
    void resetWithdrawPass(WithdrawPassDTO dto);
}
