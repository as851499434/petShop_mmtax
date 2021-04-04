package com.mmtax.business.mapper;

import com.mmtax.business.domain.InvoiceInfo;
import com.mmtax.business.domain.MerchantInfo;
import com.mmtax.business.dto.*;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 商户 数据层
 *
 * @author meimiao
 * @date 2019-07-09
 */
@Repository
public interface MerchantInfoMapper extends MyMapper<MerchantInfo> {


    /**
     * 校验商户编号是否唯一
     *
     * @param merchantCode 商户编号
     * @return 结果
     */
    int checkMerchantCodeUnique(String merchantCode);

    /**
     * 检验企业名称是否重复
     *
     * @param merchantName
     * @return
     */
    int checkMerchantNameUnique(String merchantName);

    /**
     * 商户列表查询
     *
     * @param managerMerchantQueryDTO
     * @return
     */
    List<ManagerMerchantInfoDTO> listMerchantInfos(ManagerMerchantQueryDTO managerMerchantQueryDTO);

    /**
     * 商户列表查询
     *
     * @param contactsMobile 联系人手机号
     * @param merchantName   商户名称
     * @param startDate      起始时间
     * @param endDate        结束时间
     * @return
     */
    int countMerchantInfos(@Param("contactsMobile") String contactsMobile, @Param("merchantName") String merchantName,
                           @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 依据商户ID获取商户基本信息
     *
     * @param merchantId
     * @return 商户信息详情
     */
    MerchantInfo getMerchantInfoById(int merchantId);

    /**
     * 依据商户ID修改账户密码
     *
     * @param merchantInfo
     * @return
     */
    int updateMerchantPassword(MerchantInfo merchantInfo);

    /**
     * 依据商户code获取商户基本信息
     *
     * @param code 商户编码
     * @return 商户信息详情
     */
    MerchantInfo getMerchantInfoByCode(@Param("code") String code);

    /**
     * 商户姓名模糊匹配
     *
     * @param merchantName
     * @return
     */
    List<FuzzyMatchingDTO> getMerchantNameFuzzyMatching(String merchantName);

    /**
     * 更新商户状态
     *
     * @param merchantId
     * @param status
     * @return
     */
    int updateMerchantStatus(@Param("merchantId") Integer merchantId, @Param("status") String status);

    /**
     * 获取商户列表
     *
     * @param startIndex   页码
     * @param pageSize     每页大小
     * @param merchantName 商户名称
     * @param merchantCode 商户编码
     * @param saleId 销售id
     * @return
     */
    List<ManagerTaxMerchantDTO> getListManagerTaxMerchant(@Param("startIndex") Integer startIndex,
                                                          @Param("pageSize") Integer pageSize,
                                                          @Param("merchantName") String merchantName,
                                                          @Param("merchantCode") String merchantCode,
                                                          @Param("saleId") String saleId);

    /**
     * 获取商户数量
     *
     * @param merchantName 商户名称
     * @param merchantCode 商户编码
     * @param saleId 销售id
     * @return
     */
    int getCountManagerTaxMerchant(@Param("merchantName") String merchantName,
                                   @Param("merchantCode") String merchantCode,
                                   @Param("saleId") String saleId);

    /**
     * 校验商户名称
     *
     * @param merchantName 商户名称
     * @return
     */
    int checkMerchantName(@Param("merchantName") String merchantName);

    /**
     * 校验邮箱
     *
     * @param email 邮箱
     * @return
     */
    int checkEmail(@Param("email") String email);

    /**
     * 校验联系人手机号
     *
     * @param contactsMobile 联系人手机号
     * @return
     */
    int checkContactsMobile(@Param("contactsMobile") String contactsMobile);

    /**
     * 校验企业名称
     *
     * @param companyName 企业名称
     * @return
     */
    int checkCompanyName(@Param("companyName") String companyName);

    /**
     * 校验营业执照编码
     *
     * @param businessLicenseCode 营业执照编码
     * @return
     */
    int checkBusinessLicenseCode(@Param("businessLicenseCode") String businessLicenseCode);

    /**
     * 查询全部的企业信息
     * @return
     */
    List<ManagerCompanyInfoDTO> selectAllCompanyInfo(ManagerCompanyInfoQueryDTO queryDTO);

    /**
     * 根据商户id查询合同信息
     * @param queryDTO
     * @return
     */
    List<ManagerContractInfoDTO> selectContractInfo(ManagerCompanyInfoQueryDTO queryDTO);

    /**
     * 查看个人收款信息
     * @param queryDTO
     * @return
     */
    List<PersonalReceiptDTO> selectPersonalReceiptInfo(ManagerCompanyInfoQueryDTO queryDTO);

    /**
     * 查看发票信息
     * @param queryDTO
     * @return
     */
    List<InvoiceInfoDetailDTO> selectInvoiceInfoDetail(ManagerCompanyInfoQueryDTO queryDTO);
    /**
     * 查看企业资金流水
     * @param queryDTO
     * @return
     */
    List<CompanyReceiptInfoDTO> selectCompanyReceiptInfo(ManagerCompanyInfoQueryDTO queryDTO);

    /**
     * 查看个人资金流水
     * @param queryDTO
     * @return
     */
    List<PersonalAccountFundChangeDTO> selectPersonalAccountFundChangeInfo (ManagerCompanyInfoQueryDTO queryDTO);
    /**
     * 根据商户id查询付款信息
     * @param queryDTO
     * @return
     */
    List<ManagerPayInfoDTO> selectPayInfo(ManagerCompanyInfoQueryDTO queryDTO);

    /**
     * 根据商户id查询发放信息
     * @param queryDTO
     * @return
     */
    List<ManagerGrantInfoDTO> selectGrantInfo(ManagerCompanyInfoQueryDTO queryDTO);

    /**
     * 根据税源地id查询科目内容
     */
    List<InvoiceSubjectWithTaxCompanyCorrelationDTO> selectContent(Integer taxSounrceCompanyId);
    /**
     * 根据商户id查询所包含的科目内容
     */
    List<InvoiceSubjectWithTaxCompanyCorrelationDTO> selectInvoiceContentInfoByMerchanId(Integer merchantId);
    /**
     * 根据商户id查询税源地对应的科目内容
     */
    List<InvoiceSubjectWithTaxCompanyCorrelationDTO> selectInvoiceContent(Integer merchantId);
    /**
     * 根据商户id修改发票信息
     */
    Boolean updateInvoiceInfo(UpdateBaseInvoiceInfoDTO updateBaseInvoiceInfoDTO);

    /**
     * 根据商户id修改发票科目信息
     */
    Boolean updateInvoiceContentInfo(@Param("delSatus") Integer delSatus,
                                     @Param("merchantId")Integer merchantId,
                                     @Param("invoiceSubjectId")Integer invoiceSubjectId);

    /**
     * 根据商户编号查询商户Id
     * @param merchantCode 商户编号
     * @return 结果
     */
    Integer selectMerchantIdByMerchantCode(String merchantCode);
    /**
     * 根据商户编号查询商户Id
     * @param merchantId 商户Id
     * @return 结果
     */
    String selectMerchantCodeByMerchantId(Integer merchantId);
}