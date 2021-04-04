package com.mmtax.business.mapper;

import com.mmtax.business.domain.SettlementInfo;
import com.mmtax.business.dto.ManagerSettleMentInfoDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 结算 数据层
 *
 * @author meimiao
 * @date 2019-07-29
 */
public interface SettlementInfoMapper extends MyMapper<SettlementInfo> {
    /**
     * 根据商户ID更新结算信息
     *
     * @param merchantId
     * @return
     */
    int updateByMerchantId(Integer merchantId);

    /**
     * 获取结算表ID
     *
     * @param merchantId
     * @return
     */
    Integer getSettleIdByMerchantId(Integer merchantId);

    /**
     * 获取结算信息
     *
     * @param merchantId
     * @return
     */
    SettlementInfo getSettleByMerchantId(@Param("merchantId") Integer merchantId);

    /**
     * 校验开户名称
     *
     * @param accountName 开户名称
     * @return
     */
    int checkAccountName(@Param("accountName") String accountName);

    /**
     * 校验对公账户
     *
     * @param accountNo 对公账户
     * @return
     */
    int checkAccountNo(@Param("accountNo") String accountNo);

    /**
     * 获取结算信息列表
     *
     * @param dto 参数
     * @return
     */
    List<ManagerSettleMentInfoDTO> getListManagersettleMentInfo(ManagerSettleMentInfoDTO dto);

}