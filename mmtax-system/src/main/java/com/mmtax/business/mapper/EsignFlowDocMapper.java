package com.mmtax.business.mapper;

import com.mmtax.business.domain.EsignFlowDoc;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 签约流程文档关联 数据层
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Repository
public interface EsignFlowDocMapper extends MyMapper<EsignFlowDoc>
{
    EsignFlowDoc selectByFileIdAndFlowId(@Param("flowId") String flowId,@Param("fileId") String fileId);
}