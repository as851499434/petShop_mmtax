package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.OrderSheet;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.MerchantOrderSheetDTO;
import com.mmtax.business.dto.MerchantSaveOrderSheetFeedBackDTO;
import com.mmtax.business.mapper.OrderSheetMapper;
import com.mmtax.business.mapper.TrxOrderMapper;
import com.mmtax.business.service.IOrderSheetService;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 调单订单 服务层实现
 *
 * @author meimiao
 * @date 2019-08-14
 */
@Service
public class OrderSheetServiceImpl extends CommonServiceImpl implements IOrderSheetService {

    @Resource
    private OrderSheetMapper orderSheetMapper;

    @Resource
    private TrxOrderMapper trxOrderMapper;

    @Override
    public Page getPageMerchantOrderSheet(Integer merchantId, Integer currentPage, Integer pageSize, String startDate,
                                          String endDate, String orderSerialNum, String name, String orderNo,
                                          Integer status, String auditResult) {
        if (StringUtils.isNotEmpty(startDate) && StringUtils.isEmpty(endDate)) {
            throw new BusinessException("结束时间不能为空");
        }

        if (StringUtils.isNotEmpty(endDate) && StringUtils.isEmpty(startDate)) {
            throw new BusinessException("起始时间不能为空");
        }

        Integer startIndex = getStartIndex(currentPage, pageSize);
        List<MerchantOrderSheetDTO> list = orderSheetMapper.getListMerchantOrderSheet(merchantId,
                startIndex, pageSize, startDate, endDate, orderSerialNum, name, orderNo, status, auditResult);
        int count = orderSheetMapper.getCountMerchantOrderSheet(merchantId, startDate, endDate, orderSerialNum, name,
                orderNo, status, auditResult);
        return new Page(count, list, currentPage, pageSize);
    }

    @Override
    public TrxOrder getDetail(Integer trxOrderId) {
        return trxOrderMapper.selectByPrimaryKey(trxOrderId);
    }

    @Override
    public void saveFeedBackInfo(MerchantSaveOrderSheetFeedBackDTO dto) {
        OrderSheet orderSheet = new OrderSheet();
        orderSheet.setFeedBackComment(dto.getFeedBackComment());
        orderSheet.setFeedBackTime(DateUtil.date());
        orderSheet.setId(dto.getId());
        orderSheetMapper.updateByPrimaryKeySelective(orderSheet);
    }
}
