package com.mmtax.business.service;

import com.mmtax.business.domain.OrderReimburse;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单服务费补偿 服务层
 * 
 * @author meimiao
 * @date 2020-10-14
 */
public interface IOrderReimburseService
{
    Integer insertOrderReimburse(String orderSerilNum, BigDecimal overCharge);
}
