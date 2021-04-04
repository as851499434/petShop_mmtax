package com.mmtax.business.mapper;

import com.mmtax.business.domain.CustomerTask;
import com.mmtax.business.dto.CustomerTaskDTO;
import com.mmtax.business.dto.CustomerTaskResultDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 任务接单记录 数据层
 * 
 * @author meimiao
 * @date 2020-10-15
 */
@Repository
public interface CustomerTaskMapper extends MyMapper<CustomerTask>
{
    List<CustomerTaskResultDTO> lsitTask(CustomerTaskDTO dto);

    int selectBymerchantIdIdCardNo(@Param("merchantId") Integer merchantId,@Param("idCardNo") String idcardNo);
}