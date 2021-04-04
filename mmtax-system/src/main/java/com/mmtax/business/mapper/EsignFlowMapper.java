package com.mmtax.business.mapper;

import com.mmtax.business.domain.EsignFlow;
import com.mmtax.business.dto.ManagerAgreementInfoDTO;
import com.mmtax.business.dto.ManagerContractInfoDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 签约流程 数据层
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Repository
public interface EsignFlowMapper extends MyMapper<EsignFlow>
{
    EsignFlow seletByFlowId(@Param("flowId") String flowId);

    ManagerAgreementInfoDTO getManagerAgreementInfoDTO(@Param("idNumber") String idNumber,
                                                       @Param("taxSourceId") Integer taxSourceId);

}