package com.mmtax.business.mapper;

import com.mmtax.business.domain.InvoiceApply;
import com.mmtax.business.dto.*;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 开票申请 数据层
 *
 * @author meimiao
 * @date 2019-07-09
 */
public interface InvoiceApplyMapper extends MyMapper<InvoiceApply> {

    /**
     * 发票申请记录查询--后台管理
     * @param applyDTO
     * @return
     */
    List<InvoiceQueryDTO> listSysInvoiceApplications(ManagerInvoiceApplyDTO applyDTO);

    /**
     * 发票申请记录数量查询--后台管理
     *
     * @param invoiceSerialNum 发票序列号
     * @param startDate       查询起始时间
     * @param endDate         查询结束时间
     * @return
     */
    int countSysInvoiceApplications(@Param("invoiceSerialNum") String invoiceSerialNum,
                                 @Param("startDate") String startDate, @Param("endDate") String endDate);


    /**
     * 获取错误重开发票集-- 后台管理
     * @param managerInvoiceApplyDTO
     * @return
     */

    List<ManagerInvoiceDetailDTO> listRestartInvoice(ManagerInvoiceApplyDTO managerInvoiceApplyDTO);





    /**
     * 发票申请记录查询--商户后台
     * @param merchantId 商户id
     * @param pageSize 每页大小
     * @param startIndex 查询位置
     * @param invoiceSerialNum 发票序列号
     * @param invoiceCode 发票代码
     * @param startDate 查询起始时间
     * @param endDate 查询结束时间
     * @param type  作废标识 0-未作废 1-已作废 2-作废中 3-待重开
     * @return
     */
    List<InvoiceQueryDTO> listInvoiceApplications(@Param("merchantId") Integer merchantId,
                                                  @Param("pageSize") Integer pageSize,
                                                  @Param("startIndex") Integer startIndex,
                                                  @Param("invoiceSerialNum") String invoiceSerialNum,
                                                  @Param("invoiceCode") String invoiceCode,
                                                  @Param("startDate") String startDate,
                                                  @Param("endDate") String endDate,
                                                  @Param("type") Integer type);


    /**
     * 发票申请记录查询--商户后台
     * @param merchantId 商户id
     * @param pageSize 每页大小
     * @param startIndex 查询位置
     * @param invoiceSerialNum 发票序列号
     * @param invoiceCode 发票代码
     * @param startDate 查询起始时间
     * @param endDate 查询结束时间
     * @return
     */
    List<MerchantInvoicedDetail> listSuccessInvoiceApplications(@Param("merchantId") Integer merchantId,
                                                                @Param("pageSize") Integer pageSize,
                                                                @Param("startIndex") Integer startIndex,
                                                                @Param("invoiceSerialNum") String invoiceSerialNum,
                                                                @Param("invoiceCode") String invoiceCode,
                                                                @Param("startDate") String startDate,
                                                                @Param("endDate") String endDate);


    /**
     * 查询已开具的发票--系统后台
     * @param managerInvoiceApplyDTO
     * @return
     */
    List<QueryInvoiceDetailsDTO> listSysInvoicedApplications(ManagerInvoiceApplyDTO managerInvoiceApplyDTO);





    /**
     * 发票申请记录数量查询--商户管理
     *
     * @param merchantId      商户id
     * @param invoiceSerialNum 发票序列号
     * @param invoiceCode     发票代码
     * @param startDate       查询起始时间
     * @param endDate         查询结束时间
     * @param type 作废标识 0-未作废 1-已作废 2-作废中 3-待重开
     * @return
     */
    int countInvoiceApplications(@Param("merchantId") Integer merchantId,
                                 @Param("invoiceSerialNum") String invoiceSerialNum,
                                 @Param("invoiceCode") String invoiceCode,
                                 @Param("startDate") String startDate,
                                 @Param("endDate") String endDate,
                                 @Param("type") Integer type);

    /**
     * 发票申请成功记录数量查询--商户管理
     *
     * @param merchantId      商户id
     * @param invoiceSerialNum 发票序列号
     * @param invoiceCode     发票代码
     * @param startDate       查询起始时间
     * @param endDate         查询结束时间
     * @return
     */
    int countSuccessInvoiceApplications(@Param("merchantId") Integer merchantId,
                                        @Param("invoiceSerialNum") String invoiceSerialNum,
                                        @Param("invoiceCode") String invoiceCode,
                                        @Param("startDate") String startDate,
                                        @Param("endDate") String endDate);

    /**
     * 发查看已开具发票--后台管理
     * @param invoiceSerialNum 发票序列号
     * @param invoiceCode     发票代码
     * @param startDate       查询起始时间
     * @param endDate         查询结束时间
     * @param type 作废标识 0-未作废 1-已作废 2-作废中 3-待重开
     * @return
     */
    int countInvoicedApplications(@Param("invoiceSerialNum") String invoiceSerialNum,
                                  @Param("invoiceCode") String invoiceCode,
                                  @Param("startDate") String startDate,
                                  @Param("endDate") String endDate,
                                  @Param("type") Integer type);

    /**
     * 发票详细信息获取
     * @param  id 发票申请id
     * @return
     */
    InvoiceDetailDTO getInvoiceDetailById(@Param("id") Integer id);


    /**
     * 发票审核详情查看
     * @param invoiceApplyId
     * @return
     */
    InvoiceCheckDetailDTO getCheckDetailById(@Param("invoiceApplyId") Integer invoiceApplyId);

    /**
     * 获取发票重开列表--代替发票列表
     *
     * @param invoiceId
     * @return
     */
    List<InvoiceCheckDetailDTO> getRestartInvoiceList(@Param("invoiceId") Integer invoiceId);

    /**
     * 获取代替发票ID集
     *
     * @param invoiceId
     * @return
     */
    List<Integer> getRestartInvoiceIds(Integer invoiceId);

    /**
     * 更新发票重开状态
     *
     * @param invoiceId 发票ID
     * @param status    作废标识 0-未作废 1-已作废 2-作废中 3-待重开
     * @return
     */
    int updateInvoiceRestartStatus(@Param("invoiceId") Integer invoiceId,
                                   @Param("status") Integer status);

    /**
     * 获取发票详情获取开票记录数
     *
     * @param merchantId
     * @param invoiceSerialNum
     * @param invoiceCode
     * @param startDate
     * @param endDate
     * @return
     */
    MerchantInvoiceTotalAmountDTO getTotalAmount(@Param("merchantId") Integer merchantId,
                                                 @Param("invoiceSerialNum") String invoiceSerialNum,
                                                 @Param("invoiceCode") String invoiceCode,
                                                 @Param("startDate") String startDate,
                                                 @Param("endDate") String endDate);

    /**
     * 根据发票ID获取商户ID
     *
     * @param invoiceId
     * @return
     */
    int getMerchantIdByInvoiceId(@Param("invoiceId") Integer invoiceId);

    /**
     * 将该条记录置为逻辑删除
     *
     * @param id 记录id
     * @return
     */
    int updateDelStatus(@Param("id") Integer id);

    /**
     * 根据invoiceId查询审核信息
     * @param id
     * @return
     */
    InvoiceCheckDTO getInvoiceCheckByInvoiceId(@Param("id") String  id);
    /**
     * 通过申请开票
     */
    Boolean toInvoiceChangeState(AgreeAndRefuseCheckDTO agreeAndRefuseCheckDTO);
    /**
     * 发票详情
     */
    List<InvoiceAmountDetailsDTO> invoiceAmountDetailsCheck(@Param("id") String id);
}