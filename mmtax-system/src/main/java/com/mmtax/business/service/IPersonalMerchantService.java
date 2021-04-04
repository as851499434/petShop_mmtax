package com.mmtax.business.service;

import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.PersonalMerchant;
import com.mmtax.business.domain.TaxType;
import com.mmtax.business.dto.*;
import com.mmtax.common.page.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 个体工商户 服务层
 * 
 * @author meimiao
 * @date 2020-11-26
 */
public interface IPersonalMerchantService
{
    List<BusinessLicenseReqDTO> getBusinessLicenses(Integer wechatInfoId);

    Page<PerMerInfoDTO> getOrderList(Integer wechatInfoId, Integer status, Integer currentPage, Integer pageSize);

    void updateOrder(PersonalMerchant dto);

    String applyAndSign(Integer wechatInfoId);

    /**
     *  获取c端签约地址
     * @param applyNumber
     * @return
     */
    JSONObject getCTerminalSign(String applyNumber);

    /** 添加校验信息 */
    void addVerfyInfo(VerfyInfoDTO dto);

    /** 判断并获取缓存信息 */
    PersonalMerchant judgeAndGetAddInfo(Integer wechatInfoId);

    /** 上传注册信息 */
    void addRegisterInfo(RegisterInfoDTO dto);

    /**
     * 生成营业执照名字
     * @param wechatInfoId
     * @param region
     * @return
     */
    String getBusinessLicenseName(Integer wechatInfoId, String region);
    /**
     * 通过id生成营业执照名字
     * @param id
     * @param region
     * @return
     */
    String getBusinessLicenseNameById(Integer id,String region);

    /** 上传营业信息 */
    void addBusinessInfo(BusinessInfoDTO dto);

    /**
     * 查询个体工商户办理申请信息
     * @param applyForDTO 查询条件
     * @return 结果
     */
    List<PersonalMerchantDTO> selectPersonalMerchantDTOList(ApplyForDTO applyForDTO);

    /**
     * 批量删除办理申请信息
     * @param applyIds 办理申请id集合
     * @return
     */
    boolean batchDeleteInfo(List<Integer> applyIds);

    /**
     * 查看办理申请
     * @param applyId 办理申请Id
     * @return
     */
    PersonalMerchantDetailInfoDTO showPersonalMerchantInfo(Integer applyId);

    /**
     * 修改办理申请
     * @param detailInfoDTO 办理申请信息
     * @return
     */
    boolean updatePersonalMerchantInfo(PersonalMerchantDetailInfoDTO detailInfoDTO);

    /**
     * 审核办理申请
     * @param checkPersonalMerchantInfo
     * @return
     */
    boolean checkPersonalMerchantInfo(CheckPersonalMerchantInfo checkPersonalMerchantInfo);

    /**
     * 查询营业执照办理信息
     * @param applyForLicenseQueryDTO
     * @return
     */
    List<ApplyLicenseInfoDTO> selectApplyBusinessLicenseInfo(ApplyForLicenseQueryDTO applyForLicenseQueryDTO);

    List<ApplyLicenseInfoDTO> exportApplyBusinessLicenseInfo(ApplyForLicenseQueryDTO applyForLicenseQueryDTO);

    /**
     * 营业执照办理详情
     * @param applyId
     * @return
     */
    ApplyLicenseDetailInfoDTO showApplyLicenseDetailInfo(Integer applyId);
    /**
     * 修改营业执照办理
     * @param applyLicenseDetailInfoDTO
     * @return
     */
    boolean updateApplyLicenseInfo(ApplyLicenseDetailInfoDTO applyLicenseDetailInfoDTO);

    /**
     * 个体户执照信息查询
     * @param personalLicenseQueryDTO
     * @return
     */
    List<PersonalLicenseDTO> selectPersonalLicenseInfo(PersonalLicenseQueryDTO personalLicenseQueryDTO);

    List<PersonalLicenseDTO> exportPersonalLicenseInfo(PersonalLicenseQueryDTO personalLicenseQueryDTO);


        /**
         * 查询税务类型信息
         * @param taxTypeQueryDTO
         * @return
         */
    List<TaxTypeInfoDTO> selectTaxTypeInfo(TaxTypeQueryDTO taxTypeQueryDTO);

    /**
     * 查询税务类型信息
     * @param id
     * @return
     */
    TaxTypeInfoDTO selectTaxTypeInfoById(Integer id);

    /**
     * 上传营业执照
     * @param applyId
     * @param url
     * @param time
     */
    void uploadLicense(Integer applyId, String url, String time );

    /**
     * 批量删除税务类型信息
     * @param taxTypeIds
     * @return
     */
    boolean batchDeleteTaxTypeInfo(List<Integer> taxTypeIds);

    /**
     * 新增税务类型
     * @param taxType
     * @return
     */
    boolean addTaxTypeInfo(TaxType taxType);

    /**
     * 修改税务类型
     * @param taxType
     */
    boolean updateTaxTypeInfo(TaxType taxType);

    /**
     * 根据税源地查询税务类型
     * @param taxId
     * @return
     */
    List<TaxType> getTaxTypeInfoByTaxId(Integer taxId, Integer typId);
}
