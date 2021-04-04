package com.mmtax.business.service;

import com.mmtax.business.dto.ManagerBatchPaymentRecordDTO;
import com.mmtax.business.dto.MerchantBatchPaymentAmountDetailDTO;
import com.mmtax.business.dto.MerchantBatchPaymentDetailDTO;
import com.mmtax.business.dto.RecordNumberDTO;
import com.mmtax.common.page.Page;
import com.mmtax.system.domain.SysUser;

import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/13 13:54
 */
public interface IBatchPaymentRecordService {

    /**
     * 获取商户批量代付记录
     *
     * @param startDate      查询起始时间
     * @param endDate        查询结束时间
     * @param status         状态
     * @param batchNo        批次号
     * @param paymentChannel 代付通道
     * @param merchantId     商户id
     * @param currentPage    页码
     * @param pageSize       每页大小
     * @return
     */
    Page getPageBatchPaymentRecord(String startDate, String endDate, Integer status, String batchNo,
                                   String paymentChannel, Integer merchantId, Integer currentPage, Integer pageSize);

    /**
     * 根据批量代付记录id获取代付列表
     *
     * @param merchantId  商户id
     * @param id          批量代付记录id
     * @param currentPage 页码
     * @param pageSize    每页大小
     * @return
     */
    Page getPageBatchPaymentDetail(Integer merchantId, Integer id, Integer currentPage, Integer pageSize);

    /**
     * 根据批量代付记录查找代付详情
     *
     * @param merchantId 商户id
     * @param id         批量代付id
     * @return
     */
    List<MerchantBatchPaymentDetailDTO> exportBatchPaymentDetail(Integer merchantId, Integer id);

    /**
     * 根据批量代付记录id获取代付金额详情
     *
     * @param merchantId 商户id
     * @param id         批量代付记录id
     * @return
     */
    MerchantBatchPaymentAmountDetailDTO getAmountDetail(Integer merchantId, Integer id);

    /**
     * 获取批量代付列表
     *
     * @param recordDTO 请求参数
     * @return
     */
    List<ManagerBatchPaymentRecordDTO> getListRecord(ManagerBatchPaymentRecordDTO recordDTO);


    /**
     * 获取代付总额记录数
     *
     * @param recordId
     * @return
     */
    RecordNumberDTO getTotalRecord(Integer recordId);


    /**
     * 批量代付详情
     */
    List<MerchantBatchPaymentDetailDTO> getBatchPaymentDetail(Integer recordId);





}
