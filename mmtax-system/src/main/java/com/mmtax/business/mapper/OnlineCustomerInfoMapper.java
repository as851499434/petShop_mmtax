package com.mmtax.business.mapper;

import com.mmtax.business.domain.OnlineCustomerInfo;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 网商关联员工 数据层
 * 
 * @author meimiao
 * @date 2020-07-09
 */
@Repository
public interface OnlineCustomerInfoMapper extends MyMapper<OnlineCustomerInfo>
{
    OnlineCustomerInfo selectByCustomerIdCustomerNo(@Param("customerId") Integer customerId
            , @Param("customerNo") String customerNo);

    OnlineCustomerInfo selectByIdCardNoTaxSourceId(@Param("idCardNo") String idCardNo
            , @Param("taxSourceId") Integer taxSourceId);

}