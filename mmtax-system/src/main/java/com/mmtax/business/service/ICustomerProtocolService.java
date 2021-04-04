package com.mmtax.business.service;

import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.CustomerProtocol;
import com.mmtax.business.dto.ApiSignStatusDTO;
import com.mmtax.business.dto.NotifySignReqDTO;

import java.util.List;

/**
 * 员工协议 服务层
 * 
 * @author meimiao
 * @date 2020-07-29
 */
public interface ICustomerProtocolService
{
    void dealNotify(JSONObject reqJson);

    boolean judgeSign(String idCardNo,Integer merchantId);

    void dealDocExpire(JSONObject reqJson);

    ApiSignStatusDTO queryNotifySignStatus(NotifySignReqDTO dto);

    boolean haveSign(Integer customerId);
}
