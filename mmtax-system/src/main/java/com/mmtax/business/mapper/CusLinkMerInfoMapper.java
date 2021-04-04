package com.mmtax.business.mapper;

import com.mmtax.business.domain.CusLinkMerInfo;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工关联商户 数据层
 * 
 * @author meimiao
 * @date 2020-10-10
 */
@Repository
public interface CusLinkMerInfoMapper extends MyMapper<CusLinkMerInfo>
{
    CusLinkMerInfo selectByCusIdMerId(@Param("customerId") Integer customerId, @Param("merchantId") Integer merchantId);
    /**
     * 获取 同一员工 等于 多少家 商户注册的
     * @param countNum
     * @return
     */
    List<CusLinkMerInfo> listCustomerRegisterEqualsMultiMerchant(@Param("countNum")Integer countNum);

    /**
     * 获取 同一员工 大于 多少家 商户注册的
     * @param countNum
     * @return
     */
    List<CusLinkMerInfo> listCustomerRegisterGreaterMultiMerchant(@Param("countNum")Integer countNum);

    List<CusLinkMerInfo> listCustomerOfSameMerchantByCustomerId(@Param("customerId")Integer customerId);
}