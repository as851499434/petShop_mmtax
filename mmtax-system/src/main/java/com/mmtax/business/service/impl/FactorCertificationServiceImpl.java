package com.mmtax.business.service.impl;

import com.mmtax.business.dto.MerchantFactorCertificationDTO;
import com.mmtax.business.mapper.FactorCertificationMapper;
import com.mmtax.business.service.IFactorCertificationService;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 要素认证 服务层实现
 *
 * @author meimiao
 * @date 2019-08-08
 */
@Service
public class FactorCertificationServiceImpl extends CommonServiceImpl implements IFactorCertificationService {


    @Resource
    private FactorCertificationMapper factorCertificationMapper;


    @Override
    public Page getPageMerchantFactorCertification(Integer merchantId, Integer pageSize, Integer currentPage,
                                                   String name, String idCardNo, String bankCardNo,
                                                   String merchantSerialNum, String orderSerialNum, Integer status,
                                                   String startDate, String endDate, Integer factorOrderId) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<MerchantFactorCertificationDTO> list = factorCertificationMapper.getPageMerchantFactorCertification(
                merchantId, pageSize, queryPage.getStartIndex(), name, idCardNo, bankCardNo, merchantSerialNum,
                orderSerialNum, status, startDate, endDate, factorOrderId);
        int count = factorCertificationMapper.getCountMerchantFactorCertification(merchantId, name, idCardNo,
                bankCardNo, merchantSerialNum, orderSerialNum, status, startDate, endDate, factorOrderId);
        return new Page(count, list, currentPage, pageSize);
    }

    @Override
    public List<MerchantFactorCertificationDTO> exportMerchantList(Integer merchantId, String name, String idCardNo,
                                                                   String bankCardNo, String merchantSerialNum,
                                                                   String orderSerialNum, Integer status,
                                                                   String startDate, String endDate, Integer factorOrderId) {
        return factorCertificationMapper.getPageMerchantFactorCertification(
                merchantId, null, null, name, idCardNo, bankCardNo, merchantSerialNum,
                orderSerialNum, status, startDate, endDate, factorOrderId);
    }
}
