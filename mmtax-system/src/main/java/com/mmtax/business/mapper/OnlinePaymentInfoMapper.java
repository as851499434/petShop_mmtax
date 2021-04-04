package com.mmtax.business.mapper;

import com.mmtax.business.domain.OnlinePaymentInfo;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 云资金渠道(网银)商户 数据层
 * 
 * @author meimiao
 * @date 2020-04-26
 */
@Repository
public interface OnlinePaymentInfoMapper extends MyMapper<OnlinePaymentInfo>
{

    OnlinePaymentInfo selectByMerchantId(@Param("merchantId") Integer merchantId);

    OnlinePaymentInfo selectByUidOrSubAccount(@Param("uid") String uid, @Param("subAccount") String subAccount);

    OnlinePaymentInfo selectByUidOrSubAccountOrTaxSourceId(@Param("uid") String uid,
                                                           @Param("subAccount") String subAccount,
                                                           @Param("taxSourceId") Integer taxSourceId);

    List<OnlinePaymentInfo> listByUidOrSubAccount();
}