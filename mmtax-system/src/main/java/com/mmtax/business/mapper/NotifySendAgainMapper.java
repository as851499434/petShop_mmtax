package com.mmtax.business.mapper;

import com.mmtax.business.domain.NotifySendAgain;
import com.mmtax.common.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 异步通知重发送记录 数据层
 * 
 * @author meimiao
 * @date 2020-06-01
 */
@Repository
public interface NotifySendAgainMapper extends MyMapper<NotifySendAgain>
{
//    List<NotifySendAgain>
}