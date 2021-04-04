package com.mmtax.business.service;

import com.mmtax.business.domain.CustomerAccount;
import com.mmtax.common.enums.AccountDetailTypeEnum;
import java.math.BigDecimal;
import com.mmtax.business.dto.ManagerCustomerAccountDTO;
import com.mmtax.business.dto.ManagerCustomerAccountQueryDTO;
import java.util.List;

/**
 * 员工账户 服务层
 * 
 * @author meimiao
 * @date 2020-07-09
 */
public interface ICustomerAccountService
{
    /**
     * 乐观锁更新账户
     */
    CustomerAccount updateByVersion(Integer customerId, BigDecimal amount, AccountDetailTypeEnum type);

    /**
     * 更新账户并添加变动记录
     * @param customerId
     * @param amount
     * @param type
     * @param orderSerialNum
     */
    void updateAccountAndInsertDetail(Integer customerId,BigDecimal amount,AccountDetailTypeEnum type
            ,String orderSerialNum);

    /**
     * 获取员工账户列表
     * @param queryDTO 查询条件
     * @return 列表信息
     */
    List<ManagerCustomerAccountDTO> listManagerCustomerAccountDTO(ManagerCustomerAccountQueryDTO queryDTO);
}
