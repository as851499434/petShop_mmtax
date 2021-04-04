package com.mmtax.business.service;

import com.mmtax.business.dto.ManagerAddTaxPaymentCertificateDTO;
import com.mmtax.business.dto.ManagerTaxPaymentCertificateDTO;
import com.mmtax.business.dto.MerchantTaxPaymentCertificateDTO;
import com.mmtax.common.page.Page;
import com.mmtax.system.domain.SysUser;

import java.util.List;

/**
 * 完税证明 服务层
 *
 * @author meimiao
 * @date 2019-08-13
 */
public interface ITaxPaymentCertificateService {
    /**
     * 获取商户端完税证明列表
     *
     * @param merchantId  商户id
     * @param currentPage 页码
     * @param pageSize    每页大小
     * @param startDate   起始时间
     * @param endDate     结束时间
     * @return
     */
    Page getListMerchantTaxPaymentCertificate(Integer merchantId, Integer currentPage, Integer pageSize,
                                              String startDate, String endDate);

    /**
     * 更改下载状态
     *
     * @param dto 参数
     */
    void changeStatus(MerchantTaxPaymentCertificateDTO dto);

    /**
     * 校验下载状态
     *
     * @param dto 参数
     * @return
     */
    String checkStatus(MerchantTaxPaymentCertificateDTO dto);

    /**
     * 获取系统后台完税证明列表
     *
     * @param dto 查询参数
     * @return result
     */
    List<ManagerTaxPaymentCertificateDTO> getListManagerTaxPaymentCertificate(ManagerTaxPaymentCertificateDTO dto);

    /**
     * 添加完税证明
     *
     * @param dto 参数
     */
    void addManagerTaxPaymentCertificateDTO(ManagerAddTaxPaymentCertificateDTO dto);


    /**
     * 获取商户列表
     *
     * @param currentPage  页码
     * @param pageSize     每页大小
     * @param merchantName 商户名称
     * @param merchantCode 商户编码
     * @param sysUser 管理员
     * @return
     */
    Page getMerchantInfo(Integer currentPage, Integer pageSize, String merchantName, String merchantCode, SysUser sysUser);

    /**
     * 更新完税证明
     *
     * @param dto
     */
    void update(ManagerTaxPaymentCertificateDTO dto);

    /**
     * 删除完税证明
     *
     * @param dto
     */
    void delete(ManagerTaxPaymentCertificateDTO dto);
}
