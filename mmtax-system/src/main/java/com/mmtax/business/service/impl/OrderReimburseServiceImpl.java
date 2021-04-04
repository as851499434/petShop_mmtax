package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.OrderReimburse;
import com.mmtax.business.mapper.OrderReimburseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IOrderReimburseService;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * 订单服务费补偿 服务层实现
 * 
 * @author meimiao
 * @date 2020-10-14
 */
@Service
@Slf4j
public class OrderReimburseServiceImpl implements IOrderReimburseService
{
    @Autowired
    private OrderReimburseMapper orderReimburseMapper;

    @Override
    public Integer insertOrderReimburse(String orderSerilNum, BigDecimal overCharge) {
        log.info("订单{}补偿的服务费是：{}",orderSerilNum,overCharge);
        if(overCharge.compareTo(BigDecimal.ZERO) <= 0){
            log.info("订单{}补偿为0，不记录",orderSerilNum);
            return 1;
        }
        OrderReimburse orderReimburse = Optional.ofNullable(orderReimburseMapper.selectByOrderSerilNum(orderSerilNum))
                .orElse(new OrderReimburse());
        orderReimburse.setOrderSerialNum(orderSerilNum);
        orderReimburse.setAmount(overCharge);
        orderReimburse.setUpdateTime(DateUtil.date());
        if(null == orderReimburse.getId()){
            orderReimburse.setCreateTime(DateUtil.date());
            orderReimburseMapper.insertSelective(orderReimburse);
            return orderReimburse.getId();
        }
        orderReimburseMapper.updateByPrimaryKeySelective(orderReimburse);
        return orderReimburse.getId();
    }
}
