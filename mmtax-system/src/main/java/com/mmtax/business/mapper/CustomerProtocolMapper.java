package com.mmtax.business.mapper;

import com.mmtax.business.domain.CustomerProtocol;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工协议 数据层
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Repository
public interface CustomerProtocolMapper extends MyMapper<CustomerProtocol>
{
    CustomerProtocol selectByAccountId(@Param("accountId") String accountId,@Param("merchantId") Integer merchantId);

    CustomerProtocol selectByMerchantSerialNum(@Param("signSerialNum") String signSerialNum
            , @Param("merchantId") Integer merchantId);

    CustomerProtocol selectByAccountIdAndEsignFlowId(@Param("accountId") String accountId
            ,@Param("esignFlowId") Integer esignFlowId);

    CustomerProtocol selectByConInfoIdAndAccountId(@Param("conInfoId") Integer conInfoId, @Param("accountId") String accountId);

    /**
     * 获取非终态签署协议
     * @param cusEsignId 员工id
     * @return 协议
     */
    CustomerProtocol getNonFinalStatusCustomerProtocolBy(@Param("cusEsignId") Integer cusEsignId
            ,@Param("merchantId") Integer merchantId);

    /**
     * 获取未过期非失败和拒签的协议
     * @param cusEsignId
     * @return
     */
    List<CustomerProtocol> selectByCusEsignId(@Param("cusEsignId") Integer cusEsignId);

    List<CustomerProtocol> selectByIdCardNoSignStatus(@Param("idCardNo") String idCardNo
            ,@Param("signStatus") Integer signStatus);

    /**
     * 获取一天的签约短信发送次数
     * @param cusEsignId
     * @param sendSignUrlStatus
     * @return
     */
    Integer countSignSms(@Param("cusEsignId") Integer cusEsignId,@Param("merchantId") Integer merchantId,
                         @Param("sendSignUrlStatus") Integer sendSignUrlStatus);
}