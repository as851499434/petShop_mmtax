package com.mmtax.business.mapper;

import com.mmtax.business.domain.MerchantKey;
import com.mmtax.business.dto.ManagerSeretKeyDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商户秘钥 数据层
 * 
 * @author meimiao
 * @date 2019-07-09
 */
@Repository
public interface MerchantKeyMapper extends MyMapper<MerchantKey>
{
    /**
     * 根据商户id获取秘钥信息
     *
     * @param merchantId
     * @return
     */
    MerchantKey getMerchantKeyByMerchantId(@Param("merchantId") Integer merchantId);


    /**
     * 获取商户密钥列表
     *
     * @param managerSeretKeyDTO 查询参数
     * @return
     */
    List<ManagerSeretKeyDTO> getListManagerSeretKey(ManagerSeretKeyDTO managerSeretKeyDTO);

}