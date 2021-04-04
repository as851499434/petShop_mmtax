package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.CustomerProtocol;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.BatchSignRecordMapper;
import com.mmtax.business.mapper.CustomerEsignInfoMapper;
import com.mmtax.business.mapper.CustomerProtocolMapper;
import com.mmtax.business.service.IEsignFlowService;
import com.mmtax.business.service.ISignCustomerInfoService;
import com.mmtax.common.enums.SignStatusEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.esign.comm.ConfigConstant;
import com.mmtax.common.utils.esign.domain.request.CommonRequest;
import com.mmtax.common.utils.esign.helper.SignHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 签约员工 服务层实现
 * @author ljd
 * @date 2020-07-30
 */
@Service
public class SignCustomerInfoServiceImpl extends BaseServiceImpl implements ISignCustomerInfoService {

    @Resource
    private CustomerEsignInfoMapper customerEsignInfoMapper;

    @Resource
    private BatchSignRecordMapper batchSignRecordMapper;

    @Resource
    private CustomerProtocolMapper customerProtocolMapper;
    @Autowired
    private IEsignFlowService esignFlowService;

    @Override
    public List<ManagerSignCustomerInfoDTO> listManagerSignCustomerInfoDTO(ManagerSignCustomerInfoQueryDTO queryDTO) {
        DateQueryDTO dateQueryDTO = getDateQueryDTOMatchNorm(queryDTO.getStartDate(), queryDTO.getEndDate());
        queryDTO.setStartDate(dateQueryDTO.getStartDate());
        queryDTO.setEndDate(dateQueryDTO.getEndDate());
        List<ManagerSignCustomerInfoDTO> dtoList = customerEsignInfoMapper.listManagerSignCustomerInfoDTO(queryDTO);
        for (ManagerSignCustomerInfoDTO infoDTO : dtoList) {
            if (!SignStatusEnum.SUCCESS.getCode().toString().equals(infoDTO.getSignStatus())) {
                infoDTO.setSignTime(null);
            }
        }
        return dtoList;
    }

    @Override
    public List<ManagerSignCustomerInfoAgreementDTO> listManagerSignCustomerInfoAgreementDTO(
            ManagerSignCustomerInfoAgreementQueryDTO queryDTO) {
        DateQueryDTO dateQueryDTO = getDateQueryDTOMatchNorm(queryDTO.getStartDate(), queryDTO.getEndDate());
        queryDTO.setStartDate(dateQueryDTO.getStartDate());
        queryDTO.setEndDate(dateQueryDTO.getEndDate());
        List<ManagerSignCustomerInfoAgreementDTO> list = customerEsignInfoMapper
                .listManagerSignCustomerInfoAgreementDTO(queryDTO);
        return list;
    }

    @Override
    public Page pageBatchSignRecord(String startDate, String endDate, Integer status, String batchNo,
                                    Integer merchantId, Integer currentPage, Integer pageSize) {
        DateQueryDTO dateQueryDTO = getDateQueryDTOMatchNorm(startDate, endDate);
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<CustomerBatchSignRecordDTO> list = batchSignRecordMapper.listCustomerBatchSignRecordDTO(
                dateQueryDTO.getStartDate(), dateQueryDTO.getEndDate(), status, batchNo, merchantId,
                queryPage.getStartIndex(), queryPage.getPageSize());
        int count = batchSignRecordMapper.countCustomerBatchSignRecordDTO(
                dateQueryDTO.getStartDate(), dateQueryDTO.getEndDate(), status, batchNo, merchantId);
        return new Page(count, list, currentPage, pageSize);
    }

    @Override
    public Page pageBatchSignRecordDetail(Integer merchantId, Integer batchId, Integer currentPage, Integer pageSize) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<CustomerBatchSignRecordDetailDTO> list = batchSignRecordMapper.listCustomerBatchSignRecordDetailDTO(
                merchantId, batchId, queryPage.getStartIndex(), queryPage.getPageSize());
        int count = batchSignRecordMapper.countCustomerBatchSignRecordDetailDTO(merchantId, batchId);
        return new Page(count, list, currentPage, pageSize);
    }

    @Override
    public List<CustomerBatchSignRecordDetailDTO> listBatchSignRecordDetail(Integer merchantId, Integer batchId) {
        return batchSignRecordMapper.listCustomerBatchSignRecordDetailDTO(merchantId, batchId, null,
                null);
    }

    @Override
    public Page pageCustomerSignRecordDetailDTO(Integer merchantId, String name, String mobile, Integer signStatus,
                                                String endDate, String startDate, Integer currentPage,
                                                Integer pageSize) {
        DateQueryDTO dateQueryDTO = getDateQueryDTOMatchNorm(startDate, endDate);
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<CustomerSignRecordDetailDTO> list = batchSignRecordMapper.listCustomerSignRecordDetailDTO(merchantId,
                name, mobile, signStatus, dateQueryDTO.getEndDate(), dateQueryDTO.getStartDate(),
                queryPage.getStartIndex(), queryPage.getPageSize());
        int count = batchSignRecordMapper.countCustomerSignRecordDetailDTO(merchantId, name, mobile, signStatus,
                dateQueryDTO.getEndDate(), dateQueryDTO.getStartDate());
        return new Page(count, list, currentPage, pageSize);
    }

    @Override
    public List<CustomerSignRecordDetailDTO> listCustomerSignRecordDetailDTO(Integer merchantId, String name,
                                                                             String mobile, Integer signStatus,
                                                                             String endDate, String startDate) {
        DateQueryDTO dateQueryDTO = getDateQueryDTOMatchNorm(startDate, endDate);
        return batchSignRecordMapper.listCustomerSignRecordDetailDTO(merchantId, name, mobile, signStatus,
                dateQueryDTO.getEndDate(), dateQueryDTO.getStartDate(), null, null);
    }

    @Override
    public void rushSign(Integer merchantId, Integer id) {
        if (merchantId == null) {
            throw new BusinessException("商户id不可为空");
        }
        RushSignDTO rushSignDTO = batchSignRecordMapper.getRushSignDTO(id,merchantId);
        if (rushSignDTO.getRushSignAccountId() == null) {
            throw new BusinessException("用户未开户");
        }
        CustomerProtocol protocol = customerProtocolMapper.getNonFinalStatusCustomerProtocolBy(id,merchantId);
        if (protocol == null) {
            throw new BusinessException("协议不存在");
        }
        Date sendSignUrlTime = protocol.getSendSignUrlTime();
        if (sendSignUrlTime != null) {
            long hours = DateUtil.between(sendSignUrlTime, DateUtil.date(), DateUnit.HOUR);
            if (Integer.parseInt(String.valueOf(hours)) < 24) {
                throw new BusinessException("24小时内只可以发送一次");
            }
        }
        String accountId = "";
        String noticeTypes = ConfigConstant.NOTICE_TYPES_MESSAGE;
        esignFlowService.setTokenToRedis();
        CommonRequest result = SignHelper.rushSign(rushSignDTO.getFlowId(), accountId, noticeTypes,
                rushSignDTO.getRushSignAccountId());
        if (result == null || result.getCode() == null) {
            throw new BusinessException("催签失败");
        }else {
            if (!result.getCode().equals(0)) {
                throw new BusinessException(result.getMessage());
            }
            protocol.setSendSignUrlTime(DateUtil.date());
            customerProtocolMapper.updateByPrimaryKeySelective(protocol);
        }
    }
}
