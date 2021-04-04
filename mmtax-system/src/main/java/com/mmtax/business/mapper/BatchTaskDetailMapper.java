package com.mmtax.business.mapper;

import com.mmtax.business.domain.BatchTaskDetail;
import com.mmtax.business.dto.BatchTaskDetailDTO;
import com.mmtax.business.dto.BatchTaskRecordQueryDTO;
import com.mmtax.business.dto.CustomerTaskDetailDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * 任务详细记录 数据层
 * 
 * @author meimiao
 * @date 2020-10-15
 */
@Repository
public interface BatchTaskDetailMapper extends MyMapper<BatchTaskDetail>
{

    List<BatchTaskDetail> selectByCustomerIdNewTime(@Param("customerId") Integer customerId);

    /**
     * 查询任务完成记录
     * @param queryDTO
     * @return
     */
    List<BatchTaskDetailDTO> listBatchTaskRecordDetailCompleted(BatchTaskRecordQueryDTO queryDTO);
    /**
     * 查询任务完成记录条数
     * @param queryDTO
     * @return
     */
    Integer countBatchTaskRecordDetailCompleted(BatchTaskRecordQueryDTO queryDTO);
    /**
     * 任务完成记录查询
     * @param queryDTO
     * @return
     */
    List<BatchTaskDetailDTO> batchTaskRecordDetailCompletedList(BatchTaskRecordQueryDTO queryDTO);

    /**
     *  查询派单任务详情
     * @param customerTaskId
     * @return
     */
    CustomerTaskDetailDTO queryTaskDetail(@Param("customerTaskId") Integer customerTaskId);

    /**
     * 更新 任务状态根据 任务详情id
     * @param taskStatus
     * @param taskDetailId
     * @return
     */
    int updateTaskDetailStatus(@Param("taskStatus")Integer taskStatus,@Param("taskDetailId")Integer taskDetailId,
                               @Param("currentTime")String currentTime);

    /**
     * 查询完成的派单数
     * @param batchNo
     * @param taskStatus
     * @return
     */
    Integer countTaskDetailCompleted(@Param("batchNo")String batchNo, @Param("taskStatus")Integer taskStatus,
                                     @Param("takeTaskStatus")Integer takeTaskStatus);

    /**
     * 获得完成的派单金额
     * @param batchNo
     * @param taskStatus
     * @return
     */
    BigDecimal getSumAmountTaskDetail(@Param("batchNo")String batchNo, @Param("taskStatus")Integer taskStatus,
                                      @Param("takeTaskStatus")Integer takeTaskStatus);

    /**
     * 获得打款成功的任务数
     * @param batchNo
     * @param certificateNo
     * @param orderStatus
     * @return
     */
    Integer countOrderCompleted(@Param("batchNo")String batchNo,
                                @Param("certificateNo")String certificateNo,
                                @Param("orderStatus")Integer orderStatus,
                                @Param("customerId")Integer customerId);

    /**
     * 获得派单任务完成的打款金额
     * @param batchNo
     * @param certificateNo
     * @param orderStatus
     * @return
     */
    BigDecimal getSumAmountCompletedOrder(@Param("batchNo")String batchNo,
                                          @Param("certificateNo")String certificateNo,
                                          @Param("orderStatus")Integer orderStatus,
                                          @Param("customerId")Integer customerId);


    BigDecimal getAllAmountByBathcNoAndCertificateNo(@Param("batchNo")String batchNo,
                                                     @Param("certificateNo")String certificateNo);


    BatchTaskDetail queryBatchTaskDetailByBathcNoAndCertificateNo(@Param("batchNo")String batchNo,
                                                                  @Param("certificateNo")String certificateNo);
}