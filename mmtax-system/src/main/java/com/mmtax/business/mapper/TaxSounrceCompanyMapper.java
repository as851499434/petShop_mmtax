package com.mmtax.business.mapper;

import com.mmtax.business.domain.TaxSounrceCompany;
import com.mmtax.business.dto.ManagerTaxSourceCompanyDTO;
import com.mmtax.common.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 税源地公司 数据层
 * 
 * @author meimiao
 * @date 2019-07-10
 */
@Repository
public interface TaxSounrceCompanyMapper extends MyMapper<TaxSounrceCompany>
{
    /**
     * 获取税源地公司信息
     *
     * @return
     */
    List<ManagerTaxSourceCompanyDTO> getTaxSourceCompany();
}