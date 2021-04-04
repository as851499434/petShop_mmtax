package com.mmtax.business.service;

import com.mmtax.business.domain.PersonalMerProtocol;
import java.util.List;

/**
 * 个体工商户协议 服务层
 * 
 * @author meimiao
 * @date 2020-11-27
 */
public interface IPersonalMerProtocolService
{
    void handNotify(String flowId,String accountId,Integer signResult,String signTime,String resultDescription);

    void dealDocExpire(String flowId);
}
