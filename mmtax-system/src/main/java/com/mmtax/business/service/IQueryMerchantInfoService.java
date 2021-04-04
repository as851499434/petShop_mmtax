package com.mmtax.business.service;

import com.mmtax.business.domain.Address;
import com.mmtax.business.domain.SubjectInfo;
import com.mmtax.business.dto.*;
import com.mmtax.system.domain.SysUser;

import java.util.List;


/**
 * 商户信息查询相关 包含合作、风控、对接、账户设置检索 服务层接口
 * @author yuanligang
 * @date 2019/7/11
 */
public interface IQueryMerchantInfoService {
    /**
     *商户列表查询
     * @param  managerMerchantQueryDTO 查询条件
     * @param  sysUser 登录管理
     * @return
     */
    List<ManagerMerchantInfoDTO> listMerchantInfos(ManagerMerchantQueryDTO managerMerchantQueryDTO, SysUser sysUser);

    /**
     *依据商户ID获取商户基本信息
     * @param  merchantId
     * @return  商户信息详情
     *
     */
    MerchantInfoDTO getMerchantInfoById(int merchantId);


//    /**
//     * 根据商户ID获取结算信息
//     * @param merchantId
//     * @return
//     */
//    SettlementInfo getSettleInfoById(Integer merchantId);
//
//    /**
//     * 获取客户支持
//     * @param merchantId
//     * @return
//     */
//    CustomerSupport getCustomerSupport(Integer merchantId);

    /**
     * 依据商户ID获取商户基开票信息
     * @param merchantId 商户ID
     * @return
     * @throws Exception
     */
    InvoiceInfoDTO getInvoiceInfoByMerchantId(int merchantId) throws Exception;

    /**
     * 依据商户ID获取商户基开票信息--后台管理
     * @param merchantId 商户ID
     * @return
     * @throws Exception
     */
    SysInvoiceInfoDTO getSysInvoiceInfoByMerchantId(int merchantId) throws Exception;

    /**
     * 修改发票默认内容
     * @param dto 发票默认内容DTO
     * @return
     */
    int updateInvoiceDefaultContent(UpdateInvoiceInfoDTO dto);

    /**
     * 获取商户个人收获地址
     * @param merchantId 商户ID
     * @return
     */
    Address getAddressInfoByMerchantId(int merchantId);

    /**
     * 获取商户账户设置信息
     * @param merchantId 商户ID
     * @return
     */
    MerchantAccountDTO getMerchantAccountConfig(int merchantId);

    /**
     * 获取商户合作信息详情
     * @param merchantId 商户ID
     * @return
     */
    CooperationInfoDetailDTO getCooperationInfoDetail(int merchantId);

    /**
     * 获取商户合作信息
     * @param merchantId 商户ID
     * @return
     */
    CooperationInfoDTO getCooperationInfo(int merchantId);

    /**
     * 获取商户合作信息列表
     * @param managerMerchantQueryDTO 查询参数
     * @param sysUser 登录的管理员
     * @return
     */
    List<CooperationInfoDTO> getCooperationInfoList(ManagerMerchantQueryDTO managerMerchantQueryDTO, SysUser sysUser);

    /**
     * 商户姓名模糊匹配
     *
     * @param merchantName
     * @return
     */
    List<FuzzyMatchingDTO> getMerchantNameFuzzyMatching(String merchantName);

    /**
     * 校验商户名称
     *
     * @param merchantName 商户名称
     */
    void checkMerchantName(String merchantName);

    /**
     * 校验邮箱
     *
     * @param email 邮箱
     */
    void checkEmail(String email);

    /**
     * 校验联系人手机号
     *
     * @param contactsMobile 联系人手机号
     */
    void checkContactsMobile(String contactsMobile);

    /**
     * 校验企业名称
     *
     * @param companyName 企业名称
     */
    void checkCompanyName(String companyName);

    /**
     * 校验营业执照编码
     *
     * @param businessLicenseCode 营业执照编码
     */
    void checkBusinessLicenseCode(String businessLicenseCode);

    /**
     * 校验开户名称
     *
     * @param accountName 开户名称
     */
    void checkAccountName(String accountName);

    /**
     * 校验对公账户
     *
     * @param accountNo 对公账户
     */
    void checkAccountNo(String accountNo);

    /**
     * 获取客户支持列表
     *
     * @param dto 参数
     * @param sysUser 登录用户
     * @return
     */
    List<ManagerCustomerSupportDTO> customerSupportList(ManagerCustomerSupportDTO dto, SysUser sysUser);

    /**
     * 获取结算信息列表
     *
     * @param dto 参数
     * @param sysUser 登录用户
     * @return
     */
    List<ManagerSettleMentInfoDTO> settleMentInfoList(ManagerSettleMentInfoDTO dto, SysUser sysUser);

    /**
     * 获取税源地公司信息
     *
     * @return
     */
    List<ManagerTaxSourceCompanyDTO> getTaxSourceCompany();

    /**
     * 获取代征主体
     *
     * @return
     */
    List<SubjectInfo> getSubjectInfo();
}
