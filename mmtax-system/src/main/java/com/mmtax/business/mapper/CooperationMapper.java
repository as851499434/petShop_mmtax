package com.mmtax.business.mapper;

import com.mmtax.business.domain.Cooperation;
import com.mmtax.business.dto.CooperationInfoDetailDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * 合作 数据层
 * 
 * @author meimiao
 * @date 2019-07-09
 */
@Repository
public interface CooperationMapper extends MyMapper<Cooperation> {

    /**
     * 获取商户后台合作信息详情包含风控设置信息管理
     * @param merchantId 商户ID
     * @return
     */
    CooperationInfoDetailDTO getCooperationInfoByMerchantId(Integer merchantId);


    BigDecimal sumMonthAmount(@Param("merchantId")Integer merchantId,@Param("startDate")String startDate
            ,@Param("endDate")String endDate);

    BigDecimal maxMonthTaxRate(@Param("merchantId")Integer merchantId,@Param("startDate")String startDate
            ,@Param("endDate")String endDate);

}