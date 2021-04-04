package com.mmtax.business.mapper;

import com.mmtax.business.domain.CustomerAccount;
import com.mmtax.business.dto.ManagerCustomerAccountDTO;
import com.mmtax.business.dto.ManagerCustomerAccountQueryDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工账户 数据层
 * 
 * @author meimiao
 * @date 2020-07-09
 */
@Repository
public interface CustomerAccountMapper extends MyMapper<CustomerAccount>
{


    CustomerAccount selectByCustomerIdUpCache(@Param("customerId") Integer customerId);

    CustomerAccount selectByCustomerId(@Param("customerId") Integer customerId);

    /**
     * 获取员工账户列表源数据
     * @param queryDTO 查询条件
     * @return 列表信息
     */
    List<ManagerCustomerAccountDTO> listManagerCustomerAccountDTO(ManagerCustomerAccountQueryDTO queryDTO);
}