package com.mmtax.business.service;

import com.mmtax.business.domain.TransferOrder;
import com.mmtax.business.dto.QuaryTransferOrderinfoDTO;
import com.mmtax.business.dto.TransferOrderinfoDTO;
import com.mmtax.common.page.Page;

import java.util.List;

/**
 * 商户转账员工记录 服务层
 * 
 * @author meimiao
 * @date 2020-07-09
 */
public interface ITransferOrderService
{
    List<TransferOrderinfoDTO> listTranferOrderInfoDTO(QuaryTransferOrderinfoDTO quaryDTO);

    /**
     * 处理转账成功但订单一直失败的记录
     * @param orderSerialNum
     */
    void returnTranferAmount(String orderSerialNum);

}
