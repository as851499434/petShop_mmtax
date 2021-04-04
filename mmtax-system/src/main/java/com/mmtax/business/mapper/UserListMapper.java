package com.mmtax.business.mapper;

import com.mmtax.business.domain.UserList;
import com.mmtax.business.dto.ManagerUserDTO;
import com.mmtax.business.dto.MerchantListUserDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户 数据层
 *
 * @author meimiao
 * @date 2019-08-07
 */
public interface UserListMapper extends MyMapper<UserList> {

    /**
     * 获取免验名单列表
     *
     * @param pageSize   每页大小
     * @param startIndex 起始位置
     * @param merchantId 商户id
     * @param name       名字
     * @return result
     */
    List<MerchantListUserDTO> getListUser(@Param("pageSize") Integer pageSize,
                                          @Param("startIndex") Integer startIndex,
                                          @Param("merchantId") Integer merchantId, @Param("name") String name);

    /**
     * 获取免验名单数量
     *
     * @param merchantId 商户id
     * @param name       名字
     * @return
     */
    int getCountUser(@Param("merchantId") Integer merchantId, @Param("name") String name);


    /**
     * 管理后台获取验证名单列表
     *
     * @param managerUserDTO 参数
     * @return
     */
    List<ManagerUserDTO> getManagerList(ManagerUserDTO managerUserDTO);

    /**
     * 根据名字查询免验名单
     *
     * @param name
     * @param merchantId
     * @return
     */
    List<UserList> getListCheckUser(@Param("name") String name, @Param("merchantId") Integer merchantId);


}