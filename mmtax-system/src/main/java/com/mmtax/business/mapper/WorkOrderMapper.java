package com.mmtax.business.mapper;

import com.mmtax.business.domain.WorkOrder;
import com.mmtax.business.dto.ListWorOrderResultDTO;
import com.mmtax.business.dto.ListWorkOrderDTO;
import com.mmtax.business.dto.WorkOrderRecordResultDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 工单 数据层
 * 
 * @author meimiao
 * @date 2020-12-10
 */
@Repository
public interface WorkOrderMapper extends MyMapper<WorkOrder>
{
    List<ListWorOrderResultDTO> listWorkOrder(ListWorkOrderDTO listWorkOrderDTO);

    List<WorkOrderRecordResultDTO> listWorkOrderByApplyNumbers(@Param("applyNumbers") List<String> applyNumbers);
}