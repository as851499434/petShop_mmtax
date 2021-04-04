package com.mmtax.business.mapper;

import com.mmtax.business.domain.PersonalMerchant;
import com.mmtax.business.dto.*;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 个体工商户 数据层
 * 
 * @author meimiao
 * @date 2020-11-26
 */
@Mapper
@Repository
public interface PersonalMerchantMapper extends MyMapper<PersonalMerchant>
{
    PerMerInfoDTO getOneOrder(@Param("id") Integer id);

    List<BusinessLicenseReqDTO> seletBusinessLicenses(@Param("wechatInfoId") Integer wechatInfoId);

    /** 查询订单列表 */
    List<PerMerInfoDTO> selectPageByStatus(@Param("wechatInfoId") Integer wechatInfoId,
                                           @Param("status") Integer status,
                                           @Param("startIndex") Integer startIndex,
                                           @Param("pageSize") Integer pageSize);

    /** 查询订单总数 */
    int selectCountByStatus(@Param("wechatInfoId") Integer wechatInfoId, @Param("status") Integer status);

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
    int batchDeleteInfo(@Param("applyIds") List<Integer> applyIds);

    /**
     * 修改/查看入网申请记录
     * @param applyId 办理申请Id
     * @return
     */
    PersonalMerchantDetailInfoDTO showPersonalMerchantInfo(Integer applyId);

    /**
     * 审核办理申请
     * @param checkPersonalMerchantInfo
     * @return
     */
    int checkPersonalMerchantInfo(CheckPersonalMerchantInfo checkPersonalMerchantInfo);

    /**
     * 查询营业执照办理信息
     * @param applyForLicenseQueryDTO
     * @return
     */
    List<ApplyLicenseInfoDTO> selectApplyBusinessLicenseInfo(ApplyForLicenseQueryDTO applyForLicenseQueryDTO);

    /**
     * 营业执照办理详情
     * @param applyId
     * @return
     */
    ApplyLicenseDetailInfoDTO showApplyLicenseDetailInfo(Integer applyId);

    /**
     * 个体户执照信息查询
     * @param personalLicenseQueryDTO
     * @return
     */
    List<PersonalLicenseDTO> selectPersonalLicenseInfo(PersonalLicenseQueryDTO personalLicenseQueryDTO);


    /**
     * 查询税务类型信息
     * @param taxTypeQueryDTO
     * @return
     */
    List<TaxTypeInfoDTO> selectTaxTypeInfo(TaxTypeQueryDTO taxTypeQueryDTO);

    /**
     * 根据申请id查询税务类型信息
     * @param id
     * @return
     */
    TaxTypeInfoDTO selectTaxTypeInfoById(@Param("id") Integer id);

    /**
     * 批量删除税务类型信息
     * @param taxTypeIds
     * @return
     */
    int batchDeleteTaxTypeInfo(@Param("taxTypeIds") List<Integer> taxTypeIds);

    /**
     * 获得最大的id数
     * @return
     */
    Integer getPermerchantMaxId();
}