package com.mmtax.business.mapper;

import com.mmtax.business.domain.CustomerEsignInfo;
import com.mmtax.business.dto.ManagerSignCustomerInfoAgreementDTO;
import com.mmtax.business.dto.ManagerSignCustomerInfoAgreementQueryDTO;
import com.mmtax.business.dto.ManagerSignCustomerInfoDTO;
import com.mmtax.business.dto.ManagerSignCustomerInfoQueryDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工签约 数据层
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Repository
public interface CustomerEsignInfoMapper extends MyMapper<CustomerEsignInfo> {

    CustomerEsignInfo selectByIdCardAndTaxSourceId(@Param("idCard") String idCard, @Param("taxSourceId") Integer taxSourceId);

    /**
     * 获取员工签约记录列表
     * @param queryDTO 查询条件
     * @return 列表
     */
    List<ManagerSignCustomerInfoDTO> listManagerSignCustomerInfoDTO(ManagerSignCustomerInfoQueryDTO queryDTO);

    /**
     * 获取员工签约协议列表
     * @param queryDTO 查询条件
     * @return 列表
     */
    List<ManagerSignCustomerInfoAgreementDTO> listManagerSignCustomerInfoAgreementDTO(
            ManagerSignCustomerInfoAgreementQueryDTO queryDTO);

}