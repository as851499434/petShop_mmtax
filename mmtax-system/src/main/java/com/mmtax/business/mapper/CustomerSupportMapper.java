package com.mmtax.business.mapper;

import com.mmtax.business.domain.CustomerSupport;
import com.mmtax.business.dto.ManagerCustomerSupportDTO;
import com.mmtax.common.utils.MyMapper;

import java.util.List;

/**
 * 客户支持 数据层
 *
 * @author meimiao
 * @date 2019-07-29
 */
public interface CustomerSupportMapper extends MyMapper<CustomerSupport> {

    /**
     * 获取账户支持
     *
     * @param merchantId
     * @return
     */
    CustomerSupport getCoustomerSupportBymerchantId(Integer merchantId);

    /**
     * 获取客户支持列表
     *
     * @param dto 参数
     * @return
     */
    List<ManagerCustomerSupportDTO> getListManagerCustomerSupport(ManagerCustomerSupportDTO dto);
}