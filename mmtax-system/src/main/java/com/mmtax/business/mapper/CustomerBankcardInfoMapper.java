package com.mmtax.business.mapper;

import com.mmtax.business.domain.CustomerBankcardInfo;
import com.mmtax.business.dto.BankcardAlipayInfoDTO;
import com.mmtax.business.dto.ManagerCustomerBankCardDTO;
import com.mmtax.business.dto.ManagerCustomerBankCardQueryDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工绑定银行卡 数据层
 * 
 * @author meimiao
 * @date 2020-07-09
 */
@Repository
public interface CustomerBankcardInfoMapper extends MyMapper<CustomerBankcardInfo>
{
    CustomerBankcardInfo selectByCustomerIdAndBankAccountNo(@Param("customerId") Integer customerId
            ,@Param("bankAcountNo") String bankAcountNo);

    /**
     * 根据员工id列表获取银行卡绑定的员工id列表
     * @param customerIdList 员工id列表
     * @return 员工id列表
     */
    List<Integer> listCustomerIdForBind(@Param("customerIdList") List<Integer> customerIdList);

    /**
     * 获取员工银行卡列表源数据
     * @param queryDTO 查询条件
     * @return 列表信息
     */
    List<ManagerCustomerBankCardDTO> listManagerCustomerBankCardDTO(ManagerCustomerBankCardQueryDTO queryDTO);

    List<BankcardAlipayInfoDTO> listBankcard(@Param("customerId") Integer customerId);
}