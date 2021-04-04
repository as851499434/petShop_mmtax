package com.mmtax.business.service;

import com.mmtax.business.dto.ManagerCustomerSupportDTO;
import com.mmtax.common.page.Page;

import java.util.List;

/**
 *客户支持
 */
public interface ICustomerSupportService {

    /**
     * 获取客户支持列表
     *
     * @param dto 参数
     * @return
     */
    List<ManagerCustomerSupportDTO> getListManagerCustomerSupport(ManagerCustomerSupportDTO dto);

}
