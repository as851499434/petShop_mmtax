package com.mmtax.business.service;

import com.mmtax.business.domain.CertificateType;
import com.mmtax.business.dto.ManagerUserDTO;
import com.mmtax.business.dto.MerchantAddUserDTO;
import com.mmtax.common.page.Page;
import com.mmtax.system.domain.SysUser;

import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/7 10:16
 */
public interface IRosterService {

    /**
     * 添加免验名单
     *
     * @param merchantAddUserDTO 名单信息
     */
    void add(MerchantAddUserDTO merchantAddUserDTO);

    /**
     * 获取证件类型
     *
     * @return 证件类型集合
     */
    List<CertificateType> getListCertificateType();

    /**
     * 获取免验名单列表
     *
     * @param merchantId  商户id
     * @param pageSize    每页大小
     * @param currentPage 页码
     * @param name        名字
     * @return result
     */
    Page getPageUser(Integer merchantId, Integer pageSize, Integer currentPage, String name);

    /**
     * 管理后台获取验证名单列表
     *
     * @param managerUserDTO 参数
     * @return
     */
    List<ManagerUserDTO> getListUser(ManagerUserDTO managerUserDTO);

    /**
     * 审核免验名单
     *
     * @param managerUserDTO 参数
     */
    void checkUserStatus(ManagerUserDTO managerUserDTO);
}
