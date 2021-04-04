package com.mmtax.business.service;

import com.mmtax.business.domain.CustomerBankcardInfo;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.BankcardAlipayInfoDTO;
import com.mmtax.business.dto.ManagerCustomerBankCardDTO;
import com.mmtax.business.dto.ManagerCustomerBankCardQueryDTO;

import java.util.List;

/**
 * 员工绑定银行卡 服务层
 * 
 * @author meimiao
 * @date 2020-07-09
 */
public interface ICustomerBankcardInfoService {

    /**
     * 查询打款银行卡
     * @param
     * @param customerId
     * @return
     */
    CustomerBankcardInfo queryBankcardInfo(String name,String mobile,String bankCard, Integer customerId);

    /**
     * 获取员工银行卡列表
     * @param queryDTO 查询条件
     * @return 列表信息
     */
    List<ManagerCustomerBankCardDTO> listManagerCustomerBankCardDTO(ManagerCustomerBankCardQueryDTO queryDTO);

    /** 员工银行卡列表 */
    List<BankcardAlipayInfoDTO> listBankcard(Integer customerId);

    /**
     * 获取银行卡所属银行名称
     * @param bankCardNo
     * @param customerId
     */
    String getBnakName(String bankCardNo,Integer customerId);

}
