package com.mmtax.business.mapper;

import com.mmtax.business.domain.NotifyWkyc;
import com.mmtax.common.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 悟空云创需求代付发送 数据层
 * 
 * @author meimiao
 * @date 2020-11-17
 */
@Repository
public interface NotifyWkycMapper extends MyMapper<NotifyWkyc>
{
    List<NotifyWkyc> selectNeedSend();
}