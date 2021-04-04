package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.db.sql.Order;
import com.mmtax.business.domain.OrderStatusInfo;
import com.mmtax.business.domain.PersonalMerchant;
import com.mmtax.business.mapper.OrderStatusInfoMapper;
import com.mmtax.common.enums.PerMerStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmtax.business.service.IOrderStatusInfoService;

/**
 * 订单状态 服务层实现
 * 
 * @author meimiao
 * @date 2020-11-26
 */
@Service
public class OrderStatusInfoServiceImpl implements IOrderStatusInfoService
{
    @Autowired
    OrderStatusInfoMapper orderStatusInfoMapper;

    @Override
    public OrderStatusInfo initOrderStatus(PersonalMerchant personalMerchant) {
        OrderStatusInfo orderStatusInfo = new OrderStatusInfo();
        orderStatusInfo.setApplyId(personalMerchant.getId());
        orderStatusInfo.setApplyTime(DateUtil.formatDateTime(DateUtil.date()));
        orderStatusInfo.setOrderStatus(PerMerStatusEnum.INIT.getCode());
        orderStatusInfo.setCreateTime(DateUtil.date());
        orderStatusInfo.setUpdateTime(DateUtil.date());
        orderStatusInfoMapper.insertSelective(orderStatusInfo);
        return orderStatusInfo;
    }
}
