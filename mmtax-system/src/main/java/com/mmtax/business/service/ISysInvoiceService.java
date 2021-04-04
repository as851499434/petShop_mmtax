package com.mmtax.business.service;

import com.mmtax.business.dto.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.system.domain.SysUser;

import java.math.BigDecimal;
import java.util.List;


/**
 * 后台 发票管理
 *
 * @author yuanligang
 * @date 2019/7/10
 */
public interface ISysInvoiceService {
    /**
     * 发票申请记录查询
     *
     * @param managerInvoiceApplyDTO 参数
     * @return
     * @throws BusinessException
     */
    List<InvoiceQueryDTO> listInvoiceApplications(ManagerInvoiceApplyDTO managerInvoiceApplyDTO)
            throws BusinessException;

    /**
     * 后台管理员审核发票
     *
     * @param checkInvoiceDTO
     * @return
     * @throws BusinessException
     */
    int checkInvoiceStatus(CheckInvoiceDTO checkInvoiceDTO) throws BusinessException;

    /**
     * 审核查看发票详情
     *
     * @param invoiceApplyId
     * @return
     */
    ManagerInvoiceCheckDTO getCheckDetail(Integer invoiceApplyId);

    /**
     * 发票申请成功记录查询
     *
     * @param managerInvoiceApplyDTO
     * @return
     * @throws BusinessException
     */
    List<QueryInvoiceDetailsDTO> listSysInvoicedApplications(ManagerInvoiceApplyDTO managerInvoiceApplyDTO)
            throws BusinessException;


    /**
     * 获取重开发票
     *
     * @param managerInvoiceApplyDTO
     * @return
     * @throws BusinessException
     */
    List<ManagerInvoiceDetailDTO> listRestartInvoice(ManagerInvoiceApplyDTO managerInvoiceApplyDTO)
            throws BusinessException;

    /**
     * 更新发票信息
     *
     * @param invoiceUploadDTO
     * @return
     */
    int updateInvoiceInfo(InvoiceUploadDTO invoiceUploadDTO);


    /**
     * 获取重开发票信息
     *
     * @param invoiceId
     * @return
     */
    RestartCheckDTO getRestartDetail(Integer invoiceId);


    /**
     * 获取重开发票信息列表
     *
     * @param invoiceId
     * @return
     */
    List<InvoiceCheckDetailDTO> getRestartInvoiceListByInvoiceId(Integer invoiceId);

    /**
     * 发票审核校验
     *
     * @param auditInvoicesDTO
     * @return
     */
    void auditInvoiceStatus(AuditInvoicesDTO auditInvoicesDTO);

    /**
     * 根据开票金额计算出 单价税额等
     *
     * @param amount
     * @return
     */
    ManagerInvoiceAmountCalculationDTO getAmountDetail(BigDecimal amount);

    /**
     * 获取系统后台邮寄地址列表
     *
     * @param managerInvoiceAddressDTO 查询条件
     * @return
     */
    List<ManagerInvoiceAddressDTO> listInvoiceAddress(ManagerInvoiceAddressDTO managerInvoiceAddressDTO);

    /**
     * 根据发票id查询审核信息
     * @param id
     * @return
     */
    InvoiceCheckDTO getInvoiceCheckByInvoiceId(String id);

    /**
     * 申请审核结果
     * @param agreeAndRefuseCheckDTO
     * @return
     */
    void agreeAndRefuseInvoiceChangeState(AgreeAndRefuseCheckDTO agreeAndRefuseCheckDTO);
    /**
     * 开票按钮
     * @param agreeAndRefuseCheckDTO
     * @return
     */
    Boolean toInvoiceChangeState(AgreeAndRefuseCheckDTO agreeAndRefuseCheckDTO);
    /**
     * 发票详情
     * @param id
     * @return
     */
    List<InvoiceAmountDetailsDTO> invoiceAmountDetailsCheck(String id);

}
