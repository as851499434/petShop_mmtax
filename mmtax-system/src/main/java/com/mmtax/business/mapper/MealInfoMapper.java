package com.mmtax.business.mapper;

import com.mmtax.business.domain.MealInfo;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 套餐 数据层
 * 
 * @author meimiao
 * @date 2019-07-09
 */
public interface MealInfoMapper extends MyMapper<MealInfo>
{
    /**
     * 获取商户名下所有套餐
     *
     * @param merchantId
     * @return
     */
    List<MealInfo> getMealsByMerchantId(@Param("merchantId") Integer merchantId);

}