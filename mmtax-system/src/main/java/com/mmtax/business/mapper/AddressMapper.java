package com.mmtax.business.mapper;

import com.mmtax.business.domain.Address;
import com.mmtax.business.dto.ManagerInvoiceAddressDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 邮寄地址 数据层
 * 
 * @author meimiao
 * @date 2019-07-09
 */
public interface AddressMapper extends MyMapper<Address> {

    /**
     * 根据商户id获取有效地址信息
     * @param merchantId 商户id
     * @return
     */
    Address getAddressByMerchantId(@Param("merchantId") int merchantId);

    /**
     * 获取商户邮寄地址列表
     *
     * @param managerInvoiceAddressDTO
     * @return
     */
    List<ManagerInvoiceAddressDTO> getListInvoiceAddress(ManagerInvoiceAddressDTO managerInvoiceAddressDTO);
}