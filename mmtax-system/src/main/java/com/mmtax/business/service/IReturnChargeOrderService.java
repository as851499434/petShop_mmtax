package com.mmtax.business.service;

import com.mmtax.business.domain.ReturnChargeOrder;
import java.util.List;

/**
 * 退还补交服务费记录 服务层
 * 
 * @author meimiao
 * @date 2020-08-19
 */
public interface IReturnChargeOrderService
{
    void returnHistoryFail(Integer makeUpChargeId);

    void returnBigRateSuccess(Integer customerId,Integer merchantId);
}
