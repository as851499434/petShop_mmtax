package com.mmtax.business.service.impl;

import com.mmtax.business.dto.ManagerCustomerSupportDTO;
import com.mmtax.business.mapper.CustomerSupportMapper;
import com.mmtax.business.service.ICustomerSupportService;
import com.mmtax.common.annotation.DataScope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CustomerSupportServiceImpl extends CommonServiceImpl implements ICustomerSupportService {


    @Resource
    private CustomerSupportMapper customerSupportMapper;


    @Override
    @DataScope(tableAlias = "t3")
    public List<ManagerCustomerSupportDTO> getListManagerCustomerSupport(ManagerCustomerSupportDTO dto){
        return customerSupportMapper.getListManagerCustomerSupport(dto);
    }
}

