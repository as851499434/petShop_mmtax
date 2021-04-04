package com.mmtax.business.service;

import com.mmtax.business.domain.Address;
import com.mmtax.business.dto.*;

import java.util.List;

/**
 * 商户信息修改 服务层接口
 * @author yuanligang
 * @date 2019/7/10
 */
public interface IModifyMerchantInfoService {
    /**
     * 发票送货地址修改
     * @param  address 地址信息实体类
     * @return
     */
    int updateAddressInfo(Address address);

    /**
     * 依据商户ID修改账户密码
     * @param  dto
     * @return
     */
    int updateMerchantPassword(UpdatePasswordDTO dto);

    /**
     * 商户密码批量重置
     * @param  ids 商户id集
     * @return
     */
    int resetMerchantPassword(List<Integer> ids);

    /**
     * 商户信息修改
     * @param modifyMerchantDTO
     * @return
     */
    int updateMerchantInfo(ModifyMerchantDTO modifyMerchantDTO) throws Exception;

    /**
     * 更新用户风控配置信息
     * @param updateRiskDTO
     * @return
     */
    int updateRiskConfig(UpdateRiskDTO updateRiskDTO);

    /**
     * 修改账户配置信息
     * @param updateAccountConfigDTO
     * @return
     */
    int updateAccountConfig(UpdateAccountConfigDTO updateAccountConfigDTO);


    /**
     * 账户设置邮箱修改
     */
    int updateAccountEmail(UpdateAccountEmailDTO dto);



    /**
     * 修改发票地址信息
     * @param sysInvoiceInfoDTO
     * @return
     */
    void updateInvoiceInfo(SysInvoiceInfoDTO sysInvoiceInfoDTO) throws Exception;

    /**
     * 修改合作信息
     * @param managerCooperationDTO
     * @return
     */
    int updateCooperationDetail(ManagerCooperationDTO managerCooperationDTO);

    /**
     * 修改风控信息
     *
     * @param managerRiskConfigDTO
     * @return
     */
    int updateRiskDetail(ManagerRiskConfigDTO managerRiskConfigDTO);

    /**
     * 商户状态更新
     *
     * @param merchantStatusDTO
     * @return
     */
    void updateMerchantStatus(MerchantStatusDTO merchantStatusDTO);

    /**
     * 更新客户支持
     *
     * @param dto 参数
     */
    void updateCustomerSupport(ManagerCustomerSupportDTO dto);

    /**
     * 更新结算信息
     *
     * @param dto 参数
     */
    void updateSettleMentInfo(ManagerSettleMentInfoDTO dto) throws Exception;
}
