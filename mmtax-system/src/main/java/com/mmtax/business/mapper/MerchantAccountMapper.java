package com.mmtax.business.mapper;

import com.mmtax.business.domain.MerchantAccount;
import com.mmtax.business.dto.CooperationInfoDTO;
import com.mmtax.business.dto.ManagerAccountDTO;
import com.mmtax.business.dto.ManagerMerchantQueryDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商户账户 数据层
 * 
 * @author meimiao
 * @date 2019-07-09
 */
@Repository
public interface MerchantAccountMapper extends MyMapper<MerchantAccount>
{
    /**
     * 根据商户id查询商户钱包信息
     * @param merchantId 商户id
     * @return
     */
    MerchantAccount getMerchantAccountByMerchantId(@Param("merchantId") Integer merchantId);

    MerchantAccount getMerchantAccountByMerchantIdUpCache(@Param("merchantId") Integer merchantId);

    /**
     * 获取商户账户列表
     *
     * @param managerAccountDTO 查询参数
     * @return
     */
    List<ManagerAccountDTO> getListManagerAccount(ManagerAccountDTO managerAccountDTO);
    /**
     *
     * 获取合作信息列表
     * @param pageSize
     * @param startIndex
     * @param contactsMobile
     * @param merchantName
     * @param startDate
     * @param endDate
     * @return
     */
    List<CooperationInfoDTO> getCooperationList(@Param("pageSize") int pageSize, @Param("startIndex") int startIndex,
                                                @Param("contactsMobile") String contactsMobile,
                                                @Param("merchantName") String merchantName,
                                                @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 检索记录条目数
     * @param contactsMobile
     * @param merchantName
     * @param startDate
     * @param endDate
     * @return
     */
    int countCooperation(@Param("contactsMobile") String contactsMobile,
                         @Param("merchantName") String merchantName,
                         @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     *
     * 后台获取合作信息列表
     * @param managerMerchantQueryDTO
     * @return
     */
    List<CooperationInfoDTO> getSysCooperationList(ManagerMerchantQueryDTO managerMerchantQueryDTO);

    /**
     * 更新钱包余额
     *
     * @param merchantId 商户id
     * @param amount     金额
     * @return
     */
    int updateMerchantAccount(@Param("merchantId") Integer merchantId, @Param("amount") String amount);

    /**
     * 根据版本号更新钱包余额
     * @param id
     * @param amount
     * @param version
     * @return
     */
    int updateMerchantAccountByVersion(@Param("id") Integer id, @Param("amount") String amount,
                              @Param("version") Integer version);

}