package com.mmtax.business.mapper;

import com.mmtax.business.domain.BatchSignRecord;
import com.mmtax.business.dto.CustomerBatchSignRecordDTO;
import com.mmtax.business.dto.CustomerBatchSignRecordDetailDTO;
import com.mmtax.business.dto.CustomerSignRecordDetailDTO;
import com.mmtax.business.dto.RushSignDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 批量签约记录 数据层
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Repository
public interface BatchSignRecordMapper extends MyMapper<BatchSignRecord> {
    BatchSignRecord selectByBatchNo(@Param("batchNo") String batchNo,@Param("merchantId") Integer merchantId);

    /**
     * 获取员工批量签约记录列表
     * @param startDate 创建时间开始
     * @param endDate 创建时间结束
     * @param status 状态
     * @param batchNo 批次号
     * @param merchantId 商户id
     * @param currentPage 当前页
     * @param pageSize 页容
     * @return 列表
     */
    List<CustomerBatchSignRecordDTO> listCustomerBatchSignRecordDTO(@Param("startDate")String startDate,
                                                                    @Param("endDate")String endDate,
                                                                    @Param("status")Integer status,
                                                                    @Param("batchNo")String batchNo,
                                                                    @Param("merchantId")Integer merchantId,
                                                                    @Param("startIndex")Integer startIndex,
                                                                    @Param("pageSize")Integer pageSize);

    /**
     * 获取员工批量签约记录总数
     * @param startDate 创建时间开始
     * @param endDate 创建时间结束
     * @param status 状态
     * @param batchNo 批次号
     * @param merchantId 商户id
     * @return 列表
     */
    int countCustomerBatchSignRecordDTO(@Param("startDate")String startDate, @Param("endDate")String endDate,
                                        @Param("status")Integer status, @Param("batchNo")String batchNo,
                                        @Param("merchantId")Integer merchantId);

    /**
     * 获取员工批量签约详情记录
     * @param merchantId 商户id
     * @param batchId 批量记录id
     * @param startIndex 页码
     * @param pageSize 页容
     * @return 列表
     */
    List<CustomerBatchSignRecordDetailDTO> listCustomerBatchSignRecordDetailDTO(@Param("merchantId")Integer merchantId,
                                                                                @Param("batchId")Integer batchId,
                                                                                @Param("startIndex")Integer startIndex,
                                                                                @Param("pageSize")Integer pageSize);

    /**
     * 获取员工批量签约详情记录数
     * @param merchantId 商户id
     * @param batchId 批量记录id
     * @return 数量
     */
    int countCustomerBatchSignRecordDetailDTO(@Param("merchantId")Integer merchantId,
                                              @Param("batchId")Integer batchId);

    /**
     * 获取员工签约详情列表
     * @param merchantId
     * @param name
     * @param mobile
     * @param signStatus
     * @param endDate
     * @param startDate
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<CustomerSignRecordDetailDTO> listCustomerSignRecordDetailDTO(@Param("merchantId")Integer merchantId,
                                                                      @Param("name")String name,
                                                                      @Param("mobile")String mobile,
                                                                      @Param("signStatus")Integer signStatus,
                                                                      @Param("endDate")String endDate,
                                                                      @Param("startDate")String startDate,
                                                                      @Param("startIndex")Integer startIndex,
                                                                      @Param("pageSize")Integer pageSize);

    /**
     * 获取员工签约详情列表
     * @param merchantId
     * @param name
     * @param mobile
     * @param signStatus
     * @param endDate
     * @param startDate
     * @return
     */
    int countCustomerSignRecordDetailDTO(@Param("merchantId")Integer merchantId,
                                         @Param("name")String name, @Param("mobile")String mobile,
                                         @Param("signStatus")Integer signStatus,
                                         @Param("endDate")String endDate,
                                         @Param("startDate")String startDate);

    /**
     * 获取催签信息
     * @param cusEsignId 签约员工id
     * @return 参数
     */
    RushSignDTO getRushSignDTO(@Param("cusEsignId") Integer cusEsignId,@Param("merchantId") Integer merchantId);
}