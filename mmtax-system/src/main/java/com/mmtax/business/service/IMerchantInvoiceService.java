package com.mmtax.business.service;

import com.mmtax.business.domain.InvoiceInfo;
import com.mmtax.business.domain.InvoiceRecord;
import com.mmtax.business.dto.*;
import com.mmtax.business.yunzbdto.YunZBInvoiceBillResultDTO;
import com.mmtax.business.yunzbdto.YunZBInvoiceContentResultDTO;
import com.mmtax.business.yunzbdto.YunZBInvoiceInfoDTO;
import com.mmtax.business.yunzbdto.YunZbNewInvoiceApplyDTO;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * 商户后台 发票管理
 *
 * @author yuanligang
 * @date 2019/7/10
 */
public interface IMerchantInvoiceService {

    /**
     *  更新发票默认内容
     * @param merchantId  商户
     * @param invoiceDefaultContent 发票默认内容
     */
    void updateInvoiceDefaultContent(Integer merchantId,Integer invoiceDefaultContent);

    /**
     *  开票信息
     * @param merchantId 商户ID
     * @return
     */
    InvoiceInfo billInfo(Integer merchantId);

    /**
     * 申请退票
     */
    void returnInvoice(RefundInvoiceDTO refundInvoiceDTO);

    /**
     *  发票作废
     * @param merchantId 商户ID
     * @param invoiceSerialum 发票申请编号
     */
    void invoiceInvalid(Integer merchantId,String invoiceSerialum);

    /**
     * 发票申请详情
     * @param merchantId   商户ID
     * @param invoiceSerialum 发票申请编号
     * @return
     */
    InvoiceApplicationDetailDTO invoiceApplicationDetail(Integer merchantId,String invoiceSerialum);

    /**
     * 查询 发票记录
     * @param invoiceApplyRecordDTO
     * @return
     */
    Page queryInvoiceApplyRecords(InvoiceApplyRecordDTO invoiceApplyRecordDTO );

    /**
     *  保存发票记录
     */
    void saveInvoiceRecord(InvoiceRecordDTO invoiceRecordDTO);

    /**
     * 核对发票
     * @param merchantId  商户ID
     * @param invocieType 发票类型
     * @param invoiceNote 备注
     * @param serviceName 货物或应税劳务、服务名称
     * @return
     * @throws Exception
     */
    CheckingInvoiceDTO checkingInvoice(Integer merchantId,String invocieType,String invoiceNote,Integer serviceName) throws Exception ;

    /**
     * 发票申请校验
     *
     * @param records 流水号集
     * @return 开票金额相关信息
     * @throws BusinessException
     */
    InvoiceApplyCheckDTO invoiceApplyCheck(List<Integer> records) throws BusinessException;


    /**
     * 发票申请
     *
     * @param invoiceApplyDetailDTO 流水号集
     * @return
     * @throws Exception
     */
    void invoiceApply(InvoiceApplyDetailDTO invoiceApplyDetailDTO) throws Exception;

    /**
     * 发票申请记录查询
     *
     * @param merchantId      商户id
     * @param pageSize        每页大小
     * @param currentPage     页码
     * @param invoiceSerialNum 发票序列号
     * @param invoiceCode     发票代码
     * @param startDate       查询起始时间
     * @param endDate         查询结束时间
     * @return
     * @throws BusinessException
     */
    Page listInvoiceApplications(Integer merchantId, Integer pageSize, Integer currentPage, String invoiceSerialNum, String invoiceCode,
                                 String startDate, String endDate) throws BusinessException;

    /**
     * 发票申请成功记录查询
     *
     * @param merchantId      商户id
     * @param pageSize        每页大小
     * @param currentPage     页码
     * @param invoiceSerialNum 发票序列号
     * @param invoiceCode     发票代码
     * @param startDate       查询起始时间
     * @param endDate         查询结束时间
     * @return
     * @throws BusinessException
     */
    Page listSuccessInvoiceApplications(Integer merchantId, Integer pageSize, Integer currentPage, String invoiceSerialNum, String invoiceCode,
                                        String startDate, String endDate) throws BusinessException;

    /**
     * 查询发票详情
     *
     * @param id 发票申请id
     * @return
     * @throws BusinessException
     */
    YunZBInvoiceInfoDTO getInvoiceApplyDetail(Integer id,String merchantName) throws BusinessException;

    /**
     * 发票图片
     *
     * @param invoiceDetailId 发票详情id
     * @return
     */
    String getInvoiceImage(Integer invoiceDetailId);

    /**
     * 查询开票流水
     *
     * @param pageSize    每页大小
     * @param currentPage 当前页码
     * @param invoiceId   发票ID
     * @return
     */
    Page getRechargeByInvoiceId(Integer pageSize, Integer currentPage, Integer invoiceId);

    /**
     * 获取开票总额
     *
     * @param invoiceId
     * @return
     */
    BigDecimal getInvoiceRechargeAmount(Integer invoiceId);

    /**
     * 开票流水查询
     *
     * @param merchantId
     * @param pageSize
     * @param currentPage
     * @return
     */
    Page getRechargerForInvoice(String startDate, String endDate, int merchantId, int pageSize, int currentPage);

    /**
     * 发票申请-->充值流水-->开票总额
     *
     * @param invoiceId
     * @return 开票总额
     */
    BigDecimal getAllAmountByInvoiceId(Integer invoiceId);

    /**
     * 发票重开校验 状态必需为 未作废
     *
     * @param invoiceId 待重开发票
     * @return 该票单信息
     */
    InvoiceRestartDetailDTO checkRestartInvoice(Integer invoiceId);


    /**
     * 发票重开
     *
     * @param invoiceRestartInfoDTO 重开实体类
     * @return 更新状态
     */
    int restartApplyInvoice(InvoiceRestartInfoDTO invoiceRestartInfoDTO);


    /**
     * 获取开票信息合计
     *
     * @param merchantId
     * @param invoiceSerialNum
     * @param invoiceCode
     * @param startDate
     * @param endDate
     * @return
     */
    MerchantInvoiceTotalAmountDTO getTotalAmount(Integer merchantId, String invoiceSerialNum, String invoiceCode,
                                                 String startDate, String endDate);

    /**
     * 根据开票金额计算出 单价税额等
     *
     * @param amount
     * @return
     */
    MerchantInvoiceAmountCalculationDTO getAmountDetail(BigDecimal amount);

    /**
     * 获取驳回发票信息
     *
     * @param invoiceId
     * @return
     */
    InvoiceCheckDetailDTO reviewRefuseInvoice(Integer invoiceId);

    /**
     * 驳回发票再次发起
     *
     * @param dto
     * @return
     */
    int updateAndRestartRefuseInvoice(MerchantInvoiceRefuseDTO dto);

    /**
     * 获取服务名词
     *
     * @param merchantId 商户id
     * @return
     */
    InvoiceServiceNameDTO getInvoiceServiceName(Integer merchantId) throws Exception;


    HashMap<String,Object> queryInvoiceBills(InvoiceBillDTO invoiceBillDTO) throws Exception;

    /**
     * 云众包发票申请
     *
     * @param yunZbNewInvoiceApplyDTO 申请参数
     * @throws Exception
     */
    void yunZbInvoiceApply(YunZbNewInvoiceApplyDTO yunZbNewInvoiceApplyDTO) throws Exception;
}
