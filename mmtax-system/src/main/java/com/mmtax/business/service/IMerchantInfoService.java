package com.mmtax.business.service;

import com.mmtax.business.domain.BatchPaymentRecord;
import com.mmtax.business.domain.MealInfo;
import com.mmtax.business.dto.*;
import com.mmtax.common.exception.BusinessException;

import java.util.List;

/**
 * 商户添加 服务层接口
 * @author yuanligang
 * @date 2019/7/9
 */
public interface IMerchantInfoService {
    /**
     * 商户添加
     * @param addMerchantInfoDTO 商户添加包装类
     * @return 保存结果
     * @throws BusinessException
     */
    void addMerchantInfo(AddMerchantInfoDTO addMerchantInfoDTO) throws Exception;

    /**
     * 切换商户税源地
     * @param merchantId
     * @param taxSourceId
     */
    Integer changeTaxSource(Integer merchantId,Integer taxSourceId);


    /**
     * 校验商户编号称是否唯一
     *
     * @param merchantCode 商户编号
     * @return 结果
     */
    String checkMerchantCodeUnique(String merchantCode);

    /**
     * 通过ID获取套餐信息
     * @param merchantId
     * @return
     */
    MealInfo getMealInfoByMerchantId(Integer merchantId);

    /**
     * 商户登录
     * @param merchantLoginDTO
     */
    LoginSuccessDTO merchantLogin(MerchantLoginDTO merchantLoginDTO);

    /**
     * 商户信息补全
     *
     * @param merchantSupplementDTO
     * @return
     */
    int completionMerchantInfo(MerchantSupplementDTO merchantSupplementDTO);

    /**
     * 个人H5注册接口
     * @param dto 参数
     */
    void register(MerchantRegisterDTO dto);

    /**
     * 获取邮箱验证码
     * @param dto 参数
     */
    void getCode(MerchantRegisterDTO dto);


    List<ManagerMerchantInfoDTO> listMerchantInfos(ManagerMerchantQueryDTO managerMerchantQueryDTO);




    /**
     * 查询所有企业信息
     * @return
     */
    List<ManagerCompanyInfoDTO> selectAllCompanyInfo(ManagerCompanyInfoQueryDTO queryDTO);

    /**
     * 查询合同信息
     * @param queryDTO
     * @return
     */
    List<ManagerContractInfoDTO> selectContractInfo(ManagerCompanyInfoQueryDTO queryDTO);

    /**
     * 个人收款
     * @param queryDTO
     * @return
     */
    List<PersonalReceiptDTO> selectPersonalReceiptInfo(ManagerCompanyInfoQueryDTO queryDTO);

    /**
     * 个人资金流水
     * @param queryDTO
     * @return
     */
    List<PersonalAccountFundChangeDTO> selectPersonalAccountFundChangeInfo(ManagerCompanyInfoQueryDTO queryDTO);


    /**
     * 查看企业账户资金流水
     * @param queryDTO
     * @return
     */
    List<CompanyReceiptInfoDTO> selectCompanyReceiptInfo(ManagerCompanyInfoQueryDTO queryDTO);

    /**
     * 查询付款相关信息
     * @param queryDTO
     * @return
     */
    List<ManagerPayInfoDTO> selectPayInfo(ManagerCompanyInfoQueryDTO queryDTO);


    /**
     * 查询发放相关信息
     * @param queryDTO
     * @return
     */

    List<ManagerGrantInfoDTO> selectGrantInfo(ManagerCompanyInfoQueryDTO queryDTO);

    /**
     * 根据税源地id查询科目内容
     * @param taxSounrceCompanyId
     * @return
     */
    List<InvoiceSubjectWithTaxCompanyCorrelationDTO> selectContent(Integer taxSounrceCompanyId);
    /**
     * 根据商户id查询科目内容
     * @param merchantId
     * @return
     */
    List<InvoiceSubjectWithTaxCompanyCorrelationDTO> selectInvoiceContentInfoByMerchanId(Integer merchantId);
    /**
     * 根据商户id查询税源地对应的科目内容
     * @param merchantId
     * @return
     */
    List<InvoiceSubjectWithTaxCompanyCorrelationDTO> selectInvoiceContent(Integer merchantId);
    /**
     * 修改发票信息
     */
    void updateInvoiceContentInfo(UpdateBaseInvoiceInfoDTO updateBaseInvoiceInfoDTO,
                                  List<Integer> invoiceSubjectIds,Integer merchantId);

    /**
     * 发票信息
     * @param queryDTO 参数
     * @return 结果
     */
    List<InvoiceInfoDetailDTO> selectInvoiceInfoDetail(ManagerCompanyInfoQueryDTO queryDTO);

    /**
     * 处理批量打款记录旧数据
     * @return
     */
    List<BatchPaymentRecord> updateBatchPaymentRecordInfo();

    /**
     * 根据商户id查询合作信息中的开关
     * @param merchantId
     * @return
     */
    MerchantSwitchDTO getMerchantSwitch(Integer merchantId);
}
