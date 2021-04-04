package com.mmtax.business.service;

import com.mmtax.business.domain.CustomerInfo;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.*;
import com.mmtax.common.page.Page;

import java.util.List;

/**
 * 签约员工 服务层
 * 
 * @author ljd
 * @date 2020-07-09
 */
public interface ISignCustomerInfoService {

    /**
     * 获取员工签约记录列表
     * @param queryDTO 查询条件
     * @return 列表
     */
    List<ManagerSignCustomerInfoDTO> listManagerSignCustomerInfoDTO(ManagerSignCustomerInfoQueryDTO queryDTO);

    /**
     * 获取员工签约协议列表
     * @param queryDTO 查询条件
     * @return 列表
     */
    List<ManagerSignCustomerInfoAgreementDTO> listManagerSignCustomerInfoAgreementDTO(
            ManagerSignCustomerInfoAgreementQueryDTO queryDTO);

    /**
     * 获取员工批量签约记录
     * @param startDate      查询起始时间
     * @param endDate        查询结束时间
     * @param status         状态
     * @param batchNo        批次号
     * @param merchantId     商户id
     * @param currentPage    页码
     * @param pageSize       每页大小
     * @return
     */
    Page pageBatchSignRecord(String startDate, String endDate, Integer status, String batchNo, Integer merchantId,
                             Integer currentPage, Integer pageSize);

    /**
     * 获取员工批量签约记录明细
     * @param merchantId 商户id
     * @param batchId 批量签约记录id
     * @param currentPage 页码
     * @param pageSize 页容
     * @return 明细分页列表
     */
    Page pageBatchSignRecordDetail(Integer merchantId, Integer batchId, Integer currentPage, Integer pageSize);

    /**
     * 获取员工批量签约记录明细列表
     * @param merchantId 商户id
     * @param batchId 批量记录id
     * @return 明细列表
     */
    List<CustomerBatchSignRecordDetailDTO> listBatchSignRecordDetail(Integer merchantId, Integer batchId);

    /**
     * 获取员工签约分页列表
     * @param merchantId 商户id
     * @param name 员工名称
     * @param mobile 手机号
     * @param signStatus 签约状态
     * @param endDate 起始时间
     * @param startDate 终止解释
     * @param currentPage 页码
     * @param pageSize 页容
     * @return 分页列表
     */
    Page pageCustomerSignRecordDetailDTO(Integer merchantId, String name, String mobile, Integer signStatus,
                                         String endDate, String startDate, Integer currentPage, Integer pageSize);

    /**
     * 获取员工签约列表
     * @param merchantId 商户id
     * @param name 员工名称
     * @param mobile 手机号
     * @param signStatus 签约状态
     * @param endDate 起始时间
     * @param startDate 终止解释
     * @return 列表
     */
    List<CustomerSignRecordDetailDTO> listCustomerSignRecordDetailDTO(Integer merchantId, String name, String mobile,
                                                                      Integer signStatus, String endDate,
                                                                      String startDate);

    /**
     * 催签
     * @param merchantId 商户id
     * @param id 被催员工id
     */
    void rushSign(Integer merchantId, Integer id);

}
