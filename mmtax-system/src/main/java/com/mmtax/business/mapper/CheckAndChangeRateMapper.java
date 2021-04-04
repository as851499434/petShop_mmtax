package com.mmtax.business.mapper;

import com.mmtax.business.domain.CheckAndChangeRate;
import com.mmtax.business.dto.ChangeRateSuccessDTO;
import com.mmtax.business.dto.CheckAndChangeRateDTO;
import com.mmtax.common.utils.MyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 费率变更 数据层
 * 
 * @author meimiao
 * @date 2020-11-18
 */
@Repository
public interface CheckAndChangeRateMapper extends MyMapper<CheckAndChangeRate>
{
    /**
     * 查询所有费率审核信息
     * @return 结果
     */
    List<CheckAndChangeRate> selectAllChangeRateInfo(CheckAndChangeRateDTO changeRateDTO);

    /**
     * 费率审核通过
     * @param changeRateSuccessDTO 费率审核通过所需信息
     * @return 结果
     */
    Integer updateRateSuccess(ChangeRateSuccessDTO changeRateSuccessDTO);

    /**
     * 费率审核驳回
     * @param merchantId 商户Id
     * @return 结果
     */
    Integer updateRateFail(Integer merchantId);
}