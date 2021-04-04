package com.mmtax.business.service;

import com.mmtax.business.domain.CustomerAlipayInfo;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.BankcardAlipayInfoDTO;
import com.mmtax.business.dto.ManagerCustomerAlipayDTO;
import com.mmtax.business.dto.ManagerCustomerAlipayQueryDTO;

import java.util.List;

/**
 * 员工绑定支付宝 服务层
 * 
 * @author meimiao
 * @date 2020-07-09
 */
public interface ICustomerAlipayInfoService
{
    CustomerAlipayInfo queryAlipayInfo(String name,String mobile,String bankCard,Integer customerId);

    /**
     * 获取员工支付宝列表
     * @param queryDTO 查询条件
     * @return 列表信息
     */
    List<ManagerCustomerAlipayDTO> listManagerCustomerAlipayDTO(ManagerCustomerAlipayQueryDTO queryDTO);

    List<BankcardAlipayInfoDTO> listAlipay(Integer customerId);
}
