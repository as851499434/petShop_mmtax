package com.mmtax.business.mapper;

import com.mmtax.business.domain.PayRequestData;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 打款请求数据 数据层
 * 
 * @author meimiao
 * @date 2020-11-03
 */
@Repository
public interface PayRequestDataMapper extends MyMapper<PayRequestData>
{
    List<PayRequestData> selectListPagination(@Param("batchRecordId") Integer batchRecordId,
                                              @Param("pageSize") Integer pageSize,
                                              @Param("startIndex") Integer startIndex);

    /**
     * 查询打款请求数据不是待校验的条数
     * @param merchanId
     * @param batchNo
     * @return
     */
    Integer getCountByStatus(@Param("merchantId") Integer merchanId,@Param("batchNo") String batchNo);
}