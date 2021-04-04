package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.ISysInvoiceService;
import com.mmtax.common.annotation.DataScope;
import com.mmtax.common.constant.UserBaseConstants;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 后台发票管理 服务层实现
 * @author yuanligang
 * @date 2019/7/10
 */
@Service
public class SysInvoiceServiceImpl extends CommonServiceImpl implements ISysInvoiceService {
    @Resource
    InvoiceApplyMapper invoiceApplyMapper;
    @Resource
    RechargeRecordMapper rechargeRecordMapper;
    @Resource
    InvoiceFailMapper invoiceFailMapper;
    @Resource
    InvoiceApplyAmountMapper invoiceApplyAmountMapper;
    @Resource
    InvoiceInfoMapper invoiceInfoMapper;
    @Resource
    InvoiceApplyDetailMapper invoiceApplyDetailMapper;
    @Resource
    AddressMapper addressMapper;
    @Resource
    InvoiceRecordMapper invoiceRecordMapper;
    @Resource
    InvoiceRechargeCorrelationMapper invoiceRechargeCorrelationMapper;
    /**
     * 发票申请记录查询
     * @return
     * @throws BusinessException
     */
    @Override
    @DataScope(tableAlias = "t4")
    public List<InvoiceQueryDTO> listInvoiceApplications(ManagerInvoiceApplyDTO managerInvoiceApplyDTO)
            throws BusinessException {
        managerInvoiceApplyDTO.setToVoid(InvoiceEffectiveEnum.EFFECTIVE.getCode());
        List<InvoiceQueryDTO> list = invoiceApplyMapper.listSysInvoiceApplications(managerInvoiceApplyDTO);
        return list;
    }

    /**
     * 错误重开发票列表
     */
    @Override
    public List<ManagerInvoiceDetailDTO> listRestartInvoice(ManagerInvoiceApplyDTO managerInvoiceApplyDTO)
            throws BusinessException {
        Integer status = InvoiceEffectiveEnum.PROCESSING.getCode();
        managerInvoiceApplyDTO.setStatus(status);
        List<ManagerInvoiceDetailDTO> list = invoiceApplyMapper.listRestartInvoice(managerInvoiceApplyDTO);
        for (ManagerInvoiceDetailDTO detail : list) {
            ManagerInvoiceAmountCalculationDTO dto = getAmountDetail(detail.getInvoiceAmount());
            detail.setUnitPrice(dto.getUnitPrice());
            detail.setTaxRate(dto.getTaxRate());
            detail.setTaxAmount(dto.getTaxAmount());
            List<InvoiceAmountServiceDetailDTO> amounts = invoiceApplyAmountMapper.
                    getInvoiceApplyAmountByInvoiceId(detail.getId());
            if (!CollectionUtils.isEmpty(amounts)) {
                detail.setInvoiceSerialName(amounts.get(0).getInvoiceSerialName());
            }
        }

        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int checkInvoiceStatus(CheckInvoiceDTO dto) throws BusinessException {
        String invoiceStatus = dto.getInvoiceStatus();
        Integer invoiceId = dto.getId();
        // 已寄出发票无法审核
        InvoiceApply oldApply = invoiceApplyMapper.selectByPrimaryKey(invoiceId);
        if (oldApply == null) {
            throw new BusinessException("发票记录不存在");
        }
        String status = oldApply.getInvoiceStatus();
        if (InvoiceStatusEnum.POSTED.name().equals(status) || InvoiceStatusEnum.REFUSE.name().equals(status) ||
                InvoiceStatusEnum.INVALID.name().equals(status)) {
            throw new BusinessException("请勿重复审核");
        }
        List<RechargeRecord> records = rechargeRecordMapper.getAllRechargeByInvoiceId(invoiceId);
        List<Integer> invoiceIds = rechargeRecordMapper.getAllRelationInvoiceIdByInvoiceId(invoiceId);
        Boolean sign = true;

        // 审核状态只能为已寄出和已拒绝
        if (!InvoiceStatusEnum.POSTED.name().equals(invoiceStatus) &&
                !InvoiceStatusEnum.REFUSE.name().equals(invoiceStatus)) {
            throw new  BusinessException("审核状态不合法");
        }

        RechargeRecord record = new RechargeRecord();
        if (InvoiceStatusEnum.REFUSE.name().equals(invoiceStatus)) {
            for (Integer id : invoiceIds) {
                if (!invoiceId.equals(id)) {
                    InvoiceApply apply = invoiceApplyMapper.selectByPrimaryKey(id);
                    // 若其他关联发票有未回绝状态的 充值流水金额一律不回退
                    if (!InvoiceStatusEnum.REFUSE.name().equals(apply.getInvoiceStatus())) {
                        sign = false;
                        break;
                    }
                }
            }
            if (sign) {
                records.forEach(r -> {
                    record.setId(r.getId());
                    record.setInvoiceStatus(0);
                    // 若全部为回绝 将开票金额流水全部回退为未开票状态
                    rechargeRecordMapper.updateByPrimaryKeySelective(record);
                    // 删除发票流水关联表的所有关联数据
                    invoiceApplyDetailMapper.removeInvoiceRecharge(r.getId());
                });
                // 所有发票审核标记为失效
                invoiceIds.forEach(id -> {
                    InvoiceApply newApply = new InvoiceApply();
                    newApply.setInvoiceStatus(InvoiceStatusEnum.INVALID.name());
                    newApply.setId(id);
                    invoiceApplyMapper.updateByPrimaryKeySelective(newApply);
                });
            }

        }

        InvoiceApply newApply = new InvoiceApply();
        if (!StringUtils.isEmpty(dto.getRemarks())) {
            newApply.setInstruction(dto.getRemarks());
        }
        newApply.setId(invoiceId);
        if (!sign || InvoiceStatusEnum.POSTED.name().equals(invoiceStatus)) {
            newApply.setInvoiceStatus(invoiceStatus);
        }
        return invoiceApplyMapper.updateByPrimaryKeySelective(newApply);
    }

    @Override
    public ManagerInvoiceCheckDTO getCheckDetail(Integer invoiceApplyId) {
        Integer merchantId = invoiceApplyMapper.getMerchantIdByInvoiceId(invoiceApplyId);
        String taxpayerIdentificationNumber = invoiceInfoMapper.
                getInvoiceInfoByMerchantId(merchantId).getTaxpayerIdentificationNumber();
        ManagerInvoiceCheckDTO managerInvoiceCheckDTO = new ManagerInvoiceCheckDTO();
        InvoiceCheckDetailDTO invoice = invoiceApplyMapper.getCheckDetailById(invoiceApplyId);
        List<InvoiceAmountServiceDetailDTO> list = invoiceApplyAmountMapper.getInvoiceApplyAmountByInvoiceId(invoiceApplyId);
        invoice.setServiceAmounts(list);
        List<RechargeRecord> rechargeRecords = rechargeRecordMapper.getRechargeByInvoiceId(null, null,
                invoiceApplyId);
        managerInvoiceCheckDTO.setInvoiceCheckDetailDTO(invoice);
        managerInvoiceCheckDTO.setRechargeList(rechargeRecords);
        managerInvoiceCheckDTO.setTaxpayerIdentificationNumber(taxpayerIdentificationNumber);
        return managerInvoiceCheckDTO;
    }

    @Override
    public List<QueryInvoiceDetailsDTO> listSysInvoicedApplications(ManagerInvoiceApplyDTO managerInvoiceApplyDTO)
            throws BusinessException {
        List<QueryInvoiceDetailsDTO> list = invoiceApplyMapper.listSysInvoicedApplications(managerInvoiceApplyDTO);
        return  list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateInvoiceInfo(InvoiceUploadDTO invoiceUploadDTO) {
        InvoiceApplyDetail detail = new InvoiceApplyDetail();
        detail.setId(invoiceUploadDTO.getInvoiceDetailId());
        detail.setInvoiceImg(invoiceUploadDTO.getInvoiceImg());
        detail.setInvoiceCode(invoiceUploadDTO.getInvoiceCode());
        detail.setInvoiceNo(invoiceUploadDTO.getInvoiceNum());
        if (StringUtils.isEmpty(invoiceUploadDTO.getInvoiceTime())) {
            throw new BusinessException("请填写开票时间");
        }
        detail.setInvoiceTime(invoiceUploadDTO.getInvoiceTime());
        int month = DateUtil.parse(detail.getInvoiceTime()).month() + 1;
        detail.setInvoiceMonth(String.valueOf(month));

        return invoiceApplyDetailMapper.updateByPrimaryKeySelective(detail);

    }

    @Override
    public RestartCheckDTO getRestartDetail(Integer invoiceId) {
        RestartCheckDTO check = new RestartCheckDTO();
        InvoiceCheckDetailDTO detail = invoiceApplyMapper.getCheckDetailById(invoiceId);
        List<InvoiceAmountServiceDetailDTO> list =
                invoiceApplyAmountMapper.getInvoiceApplyAmountByInvoiceId(invoiceId);
        detail.setServiceAmounts(list);
        InvoiceFail restart = invoiceFailMapper.getRestartInfoByInvoiceId(invoiceId);
        if (restart == null) {
            throw new BusinessException("重开信息不存在");
        }
        if (Integer.valueOf(1).equals(restart.getCheck())) {
            throw new BusinessException("请勿重复审核");
        }
        check.setRedInvoiceInfo(restart.getRedInvoiceInfo());
        check.setSealExplain(restart.getSealExplain());
        check.setStatus(restart.getStatus());
        check.setInvoiceDetailDTO(detail);
        return check;
    }

    @Override
    public List<InvoiceCheckDetailDTO> getRestartInvoiceListByInvoiceId(Integer invoiceId) {
        List<InvoiceCheckDetailDTO> list = invoiceApplyMapper.getRestartInvoiceList(invoiceId);
        for (InvoiceCheckDetailDTO detail : list) {
            List<InvoiceAmountServiceDetailDTO> amounts =
                    invoiceApplyAmountMapper.getInvoiceApplyAmountByInvoiceId(detail.getId());
            detail.setServiceAmounts(amounts);
        }
        return list;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void auditInvoiceStatus(AuditInvoicesDTO auditInvoicesDTO) {
        // 待作废发票ID
        Integer invoiceId = auditInvoicesDTO.getInvoiceId();
        // 审核状态
        Integer status = auditInvoicesDTO.getStatus();
        // 变更重开信息表记录
        invoiceFailMapper.updateRestartInfoByInvoiceId(invoiceId, status);
        // 获取代替发票集合
        List<Integer> newIds = invoiceApplyMapper.getRestartInvoiceIds(invoiceId);
        if (CollectionUtils.isEmpty(newIds)) {
            throw new BusinessException("代替重开发票不存在");
        }
        // 审核通过
        if (UserBaseConstants.ADOPT.equals(status)) {
            // 待重开发票状态变更 待重开-->有效 不更新因为不需要显示待重开发票
            newIds.forEach(id -> {
                InvoiceApply newApply = new InvoiceApply();
                newApply.setId(id);
                newApply.setToVoid(InvoiceEffectiveEnum.RESTARTED.getCode());
                newApply.setInvoiceStatus(InvoiceStatusEnum.POSTED.name());
                invoiceApplyMapper.updateByPrimaryKeySelective(newApply);
            });
            // 待作废发票状态变更 作废中-->已作废
            invoiceApplyMapper.updateInvoiceRestartStatus(invoiceId, InvoiceEffectiveEnum.INVALID.getCode());
        }
        // 审核驳回
        if (UserBaseConstants.REJECT.equals(status)) {
            // 删除重开发票
            newIds.forEach(id -> invoiceApplyMapper.updateDelStatus(id));
            // 待作废发票状态变更 作废中-->未作废
            invoiceApplyMapper.updateInvoiceRestartStatus(invoiceId, InvoiceEffectiveEnum.EFFECTIVE.getCode());
            invoiceFailMapper.removeInvoiceFailInfoByInvoiceId(invoiceId);
        }

    }

    @Override
    public ManagerInvoiceAmountCalculationDTO getAmountDetail(BigDecimal amount) {
        ManagerInvoiceAmountCalculationDTO dto = new ManagerInvoiceAmountCalculationDTO();
        BigDecimal unitPrice = amount.divide(BigDecimal.ONE.add(UserBaseConstants.TAX_RATE),
                UserBaseConstants.SCALE, BigDecimal.ROUND_HALF_UP);
        // 税额= 总额-单价
        BigDecimal taxAmount = amount.subtract(unitPrice);
        dto.setTaxRate(UserBaseConstants.TAX_RATE);
        dto.setTaxAmount(taxAmount);
        dto.setUnitPrice(unitPrice);
        dto.setAmount(amount);
        return dto;
    }

    @Override
    @DataScope(tableAlias = "t3")
    public List<ManagerInvoiceAddressDTO> listInvoiceAddress(ManagerInvoiceAddressDTO managerInvoiceAddressDTO) {
        List<ManagerInvoiceAddressDTO> list = addressMapper.getListInvoiceAddress(managerInvoiceAddressDTO);
        return list;
    }

    /**
     * 根据发票id查询审核信息
     * @param id
     * @return
     */
    @Override
    public InvoiceCheckDTO getInvoiceCheckByInvoiceId(String id) {
        InvoiceCheckDTO invoiceCheckInfo = invoiceApplyMapper.getInvoiceCheckByInvoiceId(id);
        if (InvoiceTypeEnum.VAT_INVOICE_NUMBER.code.equals(invoiceCheckInfo.getInvoiceType())) {
            invoiceCheckInfo.setInvoiceType(InvoiceTypeEnum.VAT_INVOICE.code);
        }else {
            invoiceCheckInfo.setInvoiceType(InvoiceTypeEnum.ORDINARY_INVOICE.code);
        }
        return invoiceCheckInfo;
    }

    /**
     * 申请审核结果
     * @param agreeAndRefuseCheckDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void agreeAndRefuseInvoiceChangeState(AgreeAndRefuseCheckDTO agreeAndRefuseCheckDTO) {
        InvoiceRecord invoiceRecord = new InvoiceRecord();
        invoiceRecord.setId(agreeAndRefuseCheckDTO.getId());
        invoiceRecord= invoiceRecordMapper.selectByPrimaryKey(invoiceRecord);
        //驳回申请 申请开票-->已驳回（申请开票驳回）
        if (AgreeAndRefuseEnum.REFUSE.code.equals(agreeAndRefuseCheckDTO.getInvoiceId())
                && InvoiceRecordInvoiceStatusEnum.APPLY.code
                .equals(Integer.parseInt(agreeAndRefuseCheckDTO.getInvoiceStatus()))) {
                invoiceRecord.setInvoiceStatus(InvoiceRecordInvoiceStatusEnum.APPLY_REJECTED.code);
            invoiceRecord.setRejectedReason(agreeAndRefuseCheckDTO.getRejectedReason());
            invoiceRecordMapper.updateByPrimaryKeySelective(invoiceRecord);
            updateInvoiceStatus(invoiceRecord.getId(),RechargeInvoiceStatusEnum.UNOPENED);
        }
        //驳回退票 退票申请中-->退票已驳回
        if (AgreeAndRefuseEnum.REFUSE.code.equals(agreeAndRefuseCheckDTO.getInvoiceId())
                && InvoiceRecordInvoiceStatusEnum.REFUND.code
                .equals(Integer.parseInt(agreeAndRefuseCheckDTO.getInvoiceStatus()))) {
                invoiceRecord.setInvoiceStatus(InvoiceRecordInvoiceStatusEnum.REFUND_REJETED.code);
            invoiceRecord.setRejectedReason(agreeAndRefuseCheckDTO.getRejectedReason());
            invoiceRecordMapper.updateByPrimaryKeySelective(invoiceRecord);
            updateInvoiceStatus(invoiceRecord.getId(),RechargeInvoiceStatusEnum.OPENED);
        }
        //同意开票申请 申请开票-->等待开票
        if (AgreeAndRefuseEnum.AGREE.code.equals(agreeAndRefuseCheckDTO.getInvoiceId())
                && InvoiceRecordInvoiceStatusEnum.APPLY.code
                .equals(Integer.parseInt(agreeAndRefuseCheckDTO.getInvoiceStatus()))) {
            invoiceRecord.setInvoiceStatus(InvoiceRecordInvoiceStatusEnum.WAITING_MAKE_OUT_INVOICE.code);
            invoiceRecordMapper.updateByPrimaryKeySelective(invoiceRecord);
        }
        //同意退票 退票申请中-->退票已完成
        if (AgreeAndRefuseEnum.AGREE.code.equals(agreeAndRefuseCheckDTO.getInvoiceId())
                && InvoiceRecordInvoiceStatusEnum.REFUND.code
                .equals(Integer.parseInt(agreeAndRefuseCheckDTO.getInvoiceStatus()))) {
            invoiceRecord.setInvoiceStatus(InvoiceRecordInvoiceStatusEnum.REFUND_COMPLETE.code);
            invoiceRecordMapper.updateByPrimaryKeySelective(invoiceRecord);
            updateInvoiceStatus(invoiceRecord.getId(),RechargeInvoiceStatusEnum.UNOPENED);
        }
    }
    /**
     * 改变开票状态方法
     */
    private void updateInvoiceStatus(Integer id, RechargeInvoiceStatusEnum rechargeInvoiceStatusEnum) {
        InvoiceRechargeCorrelation invoiceRechargeCorrelation = new InvoiceRechargeCorrelation();
        invoiceRechargeCorrelation.setInvoiceRecordId(id);
        List<InvoiceRechargeCorrelation>  invoiceRechargeCorrelationList
            = invoiceRechargeCorrelationMapper.select(invoiceRechargeCorrelation);
        for (InvoiceRechargeCorrelation invoiceRechargeCorrela:invoiceRechargeCorrelationList) {
            RechargeRecord rechargeRecord = new RechargeRecord();
            rechargeRecord.setId(invoiceRechargeCorrela.getRechargeRecordId());
            rechargeRecord.setInvoiceStatus(rechargeInvoiceStatusEnum.code);
            rechargeRecordMapper.updateByPrimaryKeySelective(rechargeRecord);
        }
    }

    /**
     * 通过申请开票
     * @param agreeAndRefuseCheckDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean toInvoiceChangeState(AgreeAndRefuseCheckDTO agreeAndRefuseCheckDTO) {
        return invoiceApplyMapper.toInvoiceChangeState(agreeAndRefuseCheckDTO);
    }

    /**
     * 发票详情
     * @param id
     * @return
     */
    @Override
    public List<InvoiceAmountDetailsDTO> invoiceAmountDetailsCheck(String id) {
        List<InvoiceAmountDetailsDTO> invoiceAmountDetailsDTOS
                = invoiceApplyMapper.invoiceAmountDetailsCheck(id);
        return invoiceAmountDetailsDTOS;
    }

}
