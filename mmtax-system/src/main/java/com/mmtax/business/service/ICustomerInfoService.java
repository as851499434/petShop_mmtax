package com.mmtax.business.service;

import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.CustomerInfo;
import com.mmtax.business.dto.*;
import com.mmtax.common.page.Page;

import java.util.List;
import java.util.Map;

/**
 * 员工 服务层
 * 
 * @author meimiao
 * @date 2020-07-09
 */
public interface ICustomerInfoService {

    CustomerInfo queryCustomerInfo(String name,String mobile,String idCardNo,Integer merchantId,Integer cusEsignId
            ,Integer taxSourceId);

    CustomerInfo convertCustomerInfo(String name,String mobile,String idCardNo,Integer merchantId,Integer cusEsignId
            ,Integer taxSourceId);

    /**
     * 获取员工列表
     * @param queryDTO 查询条件
     * @return 列表信息
     */
    List<ManagerCustomerInfoDTO> listManagerCustomerInfoDTO(ManagerCustomerInfoQueryDTO queryDTO);

    /**
     * 微信授权登录
     * @param code
     * @return
     */
    JSONObject wechatLogin(String code,Integer taxSourceId);

    /**
     * 小程序登录
     * @param code
     * @return
     */
    JSONObject apLogin(String code);

    /**
     * 退出
     * @param customerId
     */
    void quit(Integer customerId);


    /**
     * 本地实名认证
     * @param dto
     */
    VerifyCustomerRespDTO verifyCustomer(VerifyCustomerDTO dto);

    /**
     * 实名认证发短信
     * @param dto
     * @return
     */
    void sendSmsCode(VerifyCustomerDTO dto);

    /**
     * 修改手机号发短信
     * @param dto
     */
    void sendSmsCodeCauseUpdateMobile(VerifyCustomerDTO dto);

    /**
     * 修改手机号
     * @param dto
     */
    void updateMobile(UpdateMobileDTO dto);

    /**
     * 发短信
     * @param idCardNo
     * @param mobile
     * @param type
     */
    void sendSmsCode(String idCardNo, String mobile,String type);

    /**
     * 获取员工信息
     * @return
     */
    CustomerInfoDTO getCustomerInfo(Integer customerId);

    /** 主页信息 */
    IndexInfoDTO indexInfo(Integer customerId,Integer pageSize,Integer currentPage,Integer year);

    AccountInfoDTO getCustomerAccount(Integer customerId,String yearMonth);

    /** 员工收入列表 */
    Page getCustomerIncomeList(Integer customerId,Integer pageSize,Integer currentPage
            ,String yearMonth);

    /** 员工提现列表 */
    Page getCustomerWithdrawList(Integer customerId,Integer pageSize,Integer currentPage
            ,String yearMonth);

    /** 员工收入提现列表 */
    Page getCustomerIncomeWithdrawList(Integer customerId,Integer pageSize,Integer currentPage
            ,String yearMonth);

    /**
     * 统计
     * @param customerId
     * @param yearMonth
     * @return
     */
    StatistFlowRespDTO statistFlow(Integer customerId,String yearMonth);

    /**
     * 切换税源地
     * @param customerId
     * @return
     */
    List<ChangeTaxSourceDTO> changeTaxSource(Integer customerId);

    /**
     * 切换税源地后的展示
     * @param customerId
     * @param taxSourceId
     * @return
     */
    List<ChangeTaxSourceDTO> changeTaxSourceAfter(Integer customerId,Integer taxSourceId);
}
