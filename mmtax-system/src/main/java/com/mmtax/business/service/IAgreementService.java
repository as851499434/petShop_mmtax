package com.mmtax.business.service;

import com.mmtax.business.dto.ManagerAgreementInfoDTO;
import com.mmtax.business.dto.ManagerContractInfoDTO;

/**
 * @author Ljd
 * @date 2020/8/16
 */
public interface IAgreementService {

    /**
     * 获取协议信息
     * @param idNumber 身份证号
     * @param merchantId 商户id
     * @return 协议信息
     */
    ManagerAgreementInfoDTO getManagerContractInfoDTO(String idNumber, Integer merchantId);
}
