package com.mmtax.business.service;

import com.mmtax.business.domain.CusLinkMerInfo;
import java.util.List;

/**
 * 员工关联商户 服务层
 * 
 * @author meimiao
 * @date 2020-10-10
 */
public interface ICusLinkMerInfoService
{
    /**
     * 生成cusLinkMerInfo
     * @param cusId
     * @param merId
     * @return
     */
    CusLinkMerInfo coverCusLinkMerInfo(Integer cusId,Integer merId,Integer cusEsignId);

    void initTblCusLinkMerInfo();
}
