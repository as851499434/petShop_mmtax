package com.mmtax.business.service.impl;

import com.mmtax.business.dto.SysTrxOrderDTO;
import com.mmtax.business.dto.SysTrxRecordDTO;
import com.mmtax.business.mapper.TrxOrderMapper;
import com.mmtax.business.service.ISysTransactionService;
import com.mmtax.common.enums.TrxOrderStatusEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 后台交易列表服务层实现
 * @author yuanligang
 * @date 2019/7/18
 */
@Service
public class ISysTransactionServiceImpl extends CommonServiceImpl implements ISysTransactionService {
    @Resource
    TrxOrderMapper trxOrderMapper;


    @Override
    public List<SysTrxOrderDTO> listSysOrders(SysTrxRecordDTO sysTrxRecordDTO) {
        List<SysTrxOrderDTO> list = trxOrderMapper.listSysTrxOrders(sysTrxRecordDTO);
        return  list;
    }

    @Override
    public List<SysTrxOrderDTO> listSysHangsOrders(SysTrxRecordDTO sysTrxRecordDTO) {
        sysTrxRecordDTO.setOrderStatus(TrxOrderStatusEnum.PAID_PENDING.code);
        List<SysTrxOrderDTO> list = trxOrderMapper.listSysTrxOrders(sysTrxRecordDTO);
        return  list;

    }
}
