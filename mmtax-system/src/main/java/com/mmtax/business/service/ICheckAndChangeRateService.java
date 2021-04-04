package com.mmtax.business.service;

import com.mmtax.business.domain.CheckAndChangeRate;
import com.mmtax.business.dto.ChangeRateSuccessDTO;
import com.mmtax.business.dto.CheckAndChangeRateDTO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 费率变更 服务层
 * 
 * @author meimiao
 * @date 2020-11-18
 */
public interface ICheckAndChangeRateService
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
    Boolean updateRateSuccess(ChangeRateSuccessDTO changeRateSuccessDTO);

    /**
     * 费率审核驳回
     * @param merchantId 商户Id
     * @return 结果
     */
    Boolean updateRateFail(Integer merchantId);

    /**
     * 保存费率变更信息
     * @param request 悟空云创传过来的信息
     * @return 结果
     */
    String insertChangeRateInfo(HttpServletRequest request) throws IOException;
}
