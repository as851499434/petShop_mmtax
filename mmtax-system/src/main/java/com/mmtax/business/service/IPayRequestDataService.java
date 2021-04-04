package com.mmtax.business.service;

import com.mmtax.business.domain.PayRequestData;
import com.mmtax.business.dto.PayDataDTO;
import com.mmtax.business.dto.QueryCheckInfoResultDTO;
import com.mmtax.business.dto.SendToCheckDTO;
import com.mmtax.common.page.Page;
import io.swagger.annotations.ApiParam;

import java.util.List;

/**
 * 打款请求数据 服务层
 * 
 * @author meimiao
 * @date 2020-11-03
 */
public interface IPayRequestDataService
{
    Page<PayRequestData> listPayData(int merchantId, String batchNo, int pageSize, int currentPage);

    void updatePayData(PayDataDTO dto);

    QueryCheckInfoResultDTO queryCheckInfo(String batchNo, Integer merchantId);

    Boolean queryCheckOver(String batchNo, Integer merchantId);

    void deletePayData(PayDataDTO dto);

    /** 重新初步校验并发送produce */
    void sendToCheck(SendToCheckDTO dto);
}
