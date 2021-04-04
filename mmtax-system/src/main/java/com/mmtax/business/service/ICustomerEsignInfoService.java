package com.mmtax.business.service;

import com.mmtax.business.domain.CustomerEsignInfo;
import com.mmtax.business.dto.SignInfoDTO;
import com.mmtax.business.dto.TaxSourceAccountDTO;

import java.util.List;

/**
 * 员工签约 服务层
 * 
 * @author meimiao
 * @date 2020-07-29
 */
public interface ICustomerEsignInfoService
{

    CustomerEsignInfo createPerson(String idcardNo,String name,String mobile, Integer merchantId,Integer taxSourceId);

    CustomerEsignInfo createEsignAccount(CustomerEsignInfo info,String mobile);

    boolean deleteEsignAccount(CustomerEsignInfo info);

    CustomerEsignInfo setAutoSign(Integer esignCusId,boolean openOrClose);
}
