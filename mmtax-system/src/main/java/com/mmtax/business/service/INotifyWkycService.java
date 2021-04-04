package com.mmtax.business.service;

import com.mmtax.business.domain.NotifyWkyc;
import com.mmtax.common.enums.NeedPayCardEnum;
import com.mmtax.common.enums.OrderTypeEnum;

import java.util.List;

/**
 * 悟空云创需求代付发送 服务层
 * 
 * @author meimiao
 * @date 2020-11-17
 */
public interface INotifyWkycService
{

    void insertNotifyWkyc(String orderSerilNum, OrderTypeEnum orderType,Integer merchantId);

    void sendNotifyWkyc(NotifyWkyc notifyWkyc, Integer minutes);
}
