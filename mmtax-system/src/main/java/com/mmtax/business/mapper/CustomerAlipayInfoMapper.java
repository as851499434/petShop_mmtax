package com.mmtax.business.mapper;

import com.mmtax.business.domain.CustomerAlipayInfo;
import com.mmtax.business.dto.BankcardAlipayInfoDTO;
import com.mmtax.business.dto.ManagerCustomerAlipayDTO;
import com.mmtax.business.dto.ManagerCustomerAlipayQueryDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工绑定支付宝 数据层
 * 
 * @author meimiao
 * @date 2020-07-09
 */
@Repository
public interface CustomerAlipayInfoMapper extends MyMapper<CustomerAlipayInfo>
{
    CustomerAlipayInfo selectByCustomerIdAndAccountNo(@Param("customerId") Integer customerId
            ,@Param("accountNo") String accountNo);

    /**
     * 根据员工id列表获取支付宝绑定的员工id列表
     * @param customerIdList 员工id列表
     * @return 员工id列表
     */
    List<Integer> listCustomerIdForBind(@Param("customerIdList") List<Integer> customerIdList);

    /**
     * 获取员工支付宝列表源数据
     * @param queryDTO 查询条件
     * @return 列表信息
     */
    List<ManagerCustomerAlipayDTO> listManagerCustomerAlipayDTO(ManagerCustomerAlipayQueryDTO queryDTO);

    List<BankcardAlipayInfoDTO> listAlipay(@Param("customerId") Integer customerId);
}