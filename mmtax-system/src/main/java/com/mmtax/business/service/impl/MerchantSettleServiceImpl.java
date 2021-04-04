package com.mmtax.business.service.impl;

import com.mmtax.business.dto.ManagerSettleMentInfoDTO;
import com.mmtax.business.dto.MerchantBalanceDTO;
import com.mmtax.business.dto.MerchantBalanceDetailDTO;
import com.mmtax.business.mapper.BatchPaymentRecordMapper;
import com.mmtax.business.mapper.SettlementInfoMapper;
import com.mmtax.business.service.IMerchantSettleService;
import com.mmtax.common.annotation.DataScope;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 余额日账单服务层接口实现
 * @author yuanligang
 * @date 2019/7/23
 */
@Service
public class MerchantSettleServiceImpl extends CommonServiceImpl implements IMerchantSettleService {
    @Resource
    private BatchPaymentRecordMapper batchPaymentRecordMapper;
    @Resource
    private SettlementInfoMapper settlementInfoMapper;
    /**
     * 余额日账单获取
     */
    @Override
    public Page listBatchPaymentRecord(Integer merchantId, Integer pageSize, Integer currentPage,
                                       String startDate, String endDate, Integer free) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<MerchantBalanceDTO> list = batchPaymentRecordMapper.getListBalancePaymentRecord(merchantId, pageSize,
                queryPage.getStartIndex(), startDate, endDate, free);
        int count = batchPaymentRecordMapper.getCountBalancePaymentRecord(merchantId, startDate, endDate, free);
        return new Page(count, list, currentPage, pageSize);
    }

    /**
     * 余额账单详情
     */
    @Override
    public Page listTrxOrderByBatchId(Integer batchPaymentRecordId, Integer pageSize, Integer currentPage) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<MerchantBalanceDetailDTO> list = batchPaymentRecordMapper
                .getListBalancePaymentRecordDetail(batchPaymentRecordId, pageSize,queryPage.getStartIndex());
        int count = batchPaymentRecordMapper.getCountBalancePaymentRecordDetail(batchPaymentRecordId);
        return new Page(count, list, currentPage, pageSize);
    }

    @Override
    public List<MerchantBalanceDetailDTO> exportList(Integer batchPaymentRecordId) {
        List<MerchantBalanceDetailDTO> list = batchPaymentRecordMapper
                .getListBalancePaymentRecordDetail(batchPaymentRecordId, null, null);
        return list;
    }

    @Override
    @DataScope(tableAlias = "t3")
    public List<ManagerSettleMentInfoDTO> getListManagersettleMentInfo(ManagerSettleMentInfoDTO dto){
        return settlementInfoMapper.getListManagersettleMentInfo(dto);
    }
}
