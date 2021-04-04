package com.mmtax.business.mapper;

import com.mmtax.business.domain.CustomerWechatInfo;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工与员工微信关联 数据层
 * 
 * @author meimiao
 * @date 2020-09-11
 */
@Repository
public interface CustomerWechatInfoMapper extends MyMapper<CustomerWechatInfo>
{
    CustomerWechatInfo selectByCustomerIdHasQuit(@Param("customerId") Integer customerId
            ,@Param("hasQuit") Integer hasQuit);

    List<CustomerWechatInfo> selectByOpenId(@Param("openId") String opneId);
}