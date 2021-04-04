package com.mmtax.business.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.dto.MerchantFactorListDTO;
import com.mmtax.business.mapper.FactorCertificationMapper;
import com.mmtax.business.mapper.FactorOrderMapper;
import com.mmtax.business.service.IFactorOrderService;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 要素认证订单 服务层实现
 *
 * @author meimiao
 * @date 2019-08-12
 */
@Service
public class FactorOrderServiceImpl extends CommonServiceImpl implements IFactorOrderService {

    @Resource
    private FactorOrderMapper factorOrderMapper;
    @Resource
    private FactorCertificationMapper factorCertificationMapper;

    @Override
    public Page getPageFactorOrder(Integer merchantId, Integer pageSize, Integer currentPage, String startDate,
                                   String endDate, String orderNo) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        if (StringUtils.isEmpty(startDate) && StringUtils.isEmpty(endDate)) {
            startDate = DateUtil.format(DateUtil.date(), DatePattern.NORM_DATE_PATTERN);
            endDate = DateUtil.format(DateUtil.date(), DatePattern.NORM_DATE_PATTERN);
        }
        List<MerchantFactorListDTO> list = factorOrderMapper.getListFactorOrder(merchantId, pageSize,
                queryPage.getStartIndex(), startDate, endDate, orderNo);
        int count = factorOrderMapper.getCountFactorOrder(merchantId, startDate, endDate, orderNo);
        return new Page(count, list, currentPage, pageSize);
    }
}
