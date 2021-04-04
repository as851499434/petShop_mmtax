package com.mmtax.business.service;

import com.mmtax.business.domain.OrderStatusInfo;
import com.mmtax.business.domain.PersonalMerchant;

import java.util.List;

/**
 * 订单状态 服务层
 * 
 * @author meimiao
 * @date 2020-11-26
 */
public interface IOrderStatusInfoService
{
    OrderStatusInfo initOrderStatus(PersonalMerchant personalMerchant);
}
