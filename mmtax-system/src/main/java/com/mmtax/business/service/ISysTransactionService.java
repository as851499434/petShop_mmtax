package com.mmtax.business.service;

import com.mmtax.business.dto.SysTrxOrderDTO;
import com.mmtax.business.dto.SysTrxRecordDTO;

import java.util.List;

/**
 * 后台交易管理服务层接口
 * @author yuanligang
 * @date 2019/7/18
 */
public interface ISysTransactionService {

    /**
     * 获取交易订单列表
     * @param  sysTrxRecordDTO
     * @return
     */
    List<SysTrxOrderDTO> listSysOrders (SysTrxRecordDTO sysTrxRecordDTO);


    /**
     * 获取挂起订单列表
     * @param sysTrxRecordDTO
     * @return
     */
    List<SysTrxOrderDTO> listSysHangsOrders(SysTrxRecordDTO sysTrxRecordDTO);


}
