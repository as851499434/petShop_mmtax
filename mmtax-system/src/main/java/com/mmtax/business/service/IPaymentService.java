package com.mmtax.business.service;

import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.tianjindto.TianJinAccountDTO;
import com.mmtax.business.tianjindto.TianJinAccountInfoDTO;
import com.mmtax.business.tianjindto.TianJinServerInfoDTO;
import com.mmtax.common.page.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/9 17:33
 */
public interface IPaymentService {

    /**
     * 单个用户代付
     *
     * @param paymentInfoDTO     代付信息
     * @param batchPaymentRecord 批量打款记录
     * @param flag               打款操作平台 true 后台false 商户端
     * @param trxOrderId         订单id
     * @throws Exception
     */

    String singlePayment(PaymentInfoDTO paymentInfoDTO, BatchPaymentRecord batchPaymentRecord,
                       boolean flag, Integer trxOrderId) throws Exception;

    /**
     * 打款后冻结金额并添加冻结记录
     * @param settleAmount
     * @param merchantAccount
     * @param trxOrder
     * @param tax
     * @param merchantInfo
     */
    MerchantAccountDetail convertMerchantAccount(BigDecimal settleAmount, MerchantAccount merchantAccount,
                                                 TrxOrder trxOrder, double tax, MerchantInfo merchantInfo);

    /**
     * 打款失败后解冻金额并标记冻结记录为失败
     * @param merchantId
     * @param settleAmount
     * @param tax
     * @param merchantAccountDetail
     */
    void unfreezeOperate(Integer merchantId,BigDecimal settleAmount,BigDecimal tax,
                         MerchantAccountDetail merchantAccountDetail);

    void readEncryptUploadOnline(String dateString,String filePath,String fileName,Integer taxSourceId);

    /**
     * 批量代付
     *
     * @param file
     * @param checkPasswordDTO
     * @throws Exception
     */
    Map batchPayment(MultipartFile file, CheckPasswordDTO checkPasswordDTO) throws Exception;



    /** 发去校验生产者 */
    void sendToProuce(Integer merchantId,String batchNo);

    /**
     * 打款数据大额费率单个处理
     * @param needDealdto
     * @param merchantId
     * @param idCardNoAmount
     * @param hasMakeUpRecord
     * @return
     */
    PaymentInfoDTO bigRateDealOne(PayRequestData needDealdto, Integer merchantId
            , Map<String,BigDecimal> idCardNoAmount, Set<String> hasMakeUpRecord);

    /**
     * 打款数据大额费率处理
     * @param dtos
     * @param merchantId
     * @return
     */
    List<PaymentInfoDTO> bigRateDeal(List<PayRequestData> dtos, Integer merchantId);


    /**
     *  推广列表，批量代付
     * @param file
     * @param checkPasswordDTO
     * @return
     * @throws Exception
     */
    Map uploadFilePromotion(MultipartFile file, CheckPasswordDTO checkPasswordDTO) throws Exception;

    /**
     * 代付第三方开放接口
     *
     * @param request
     * @return
     * @throws Exception
     */
    PaymentResultDTO getResult(HttpServletRequest request) throws Exception;

    /**
     * 支付宝代付第三方开放接口
     * @param request
     * @return
     * @throws Exception
     */
    PaymentResultDTO getResultAlipay(HttpServletRequest request) throws Exception;

    /**
     * 校验登录密码
     *
     * @param checkPasswordDTO 参数
     */
    void checkPassword(CheckPasswordDTO checkPasswordDTO);

    /**
     * 获取余额
     *
     * @param request 请求
     * @return
     * @throws Exception
     */
    PaymentBalanceDTO getBalance(HttpServletRequest request) throws Exception;

    /**
     * 充值计算器
     * @param dto 参数
     */
    Map rechargeCalculate(BatchOrderDTO dto) throws Exception;

    /**
     * 判断打款缓存是否存在
     * @return
     */
    Map judgePaymentCache(Integer merchantId);

    /**
     * 打款查看详情
     * @param merchantId
     * @param pageSize
     * @param currentPage
     * @return
     */
    Page listPaymentDetail(Integer merchantId,String batchNo,Integer pageSize,Integer currentPage);

    Map summaryPaymentData(Integer merchantId,String batchNo);

    /**
     * 获取批次信息
     *
     * @param merchantId 商户编号
     * @return
     */
    BatchPaymentRecord getRecord(Integer merchantId,String batchNo);

    /**
     * 取消订单
     *
     * @param dto 请求参数
     */
    void cancelOrder(BatchOrderDTO dto);

    /** 二要素三要素验证 */
    void factorVerification(PayRequestData payRequestData,Cooperation cooperation);

    /**
     * 打款数据校验
     */
    void payDataCheck(PayRequestData payRequestData);

    /**
     * 获取校验出错的数据
     * @param currentPage
     * @param pageSize
     * @param key
     * @return
     */
    Map getErrorData(Integer currentPage,Integer pageSize,String key);


    Map getPromotionErrorData(Integer currentPage,Integer pageSize,String key);

    BatchPaymentRecord getLastRecord(int id);

    /** 补偿服务费的计算 */
    List<PaymentInfoDTO> calculateOvercharge(List<PaymentInfoDTO> list,BatchPaymentRecord record);

    /**
     * 开始批量打款
     *
     * @param dto 参数
     */
    void batchPayment(BatchOrderDTO dto);

    /**  */
    void paymentExecute(int merchantId,String batchNo);

    /**
     * 获取订单数据汇总
     *
     * @param merchantId 商户id
     * @return 结果
     * @throws Exception 异常
     */
    MerchantBatchPaymentAmountDetailDTO getDetail(Integer merchantId,String batchNo);

    /**
     * 挂起订单再次代付
     *
     * @param managerTrxOrderDTO 挂起记录
     * @throws Exception
     */
    void hangingOrderPaid(ManagerTrxOrderDTO managerTrxOrderDTO) throws Exception;


    /**
     * 天津通道打款
     * @param dto
     * @throws Exception
     */
    void batchTianJinPayment(BatchOrderDTO dto) throws Exception;

    /**
     * 获取天津渠道账户列表
     * @param merchantId 商户id
     * @return
     */
    RechargeAccountInfoDTO getAccountInfo(Integer merchantId);

    /**
     * 获取天津渠道选中账户余额
     * @param accountUuid 账户uuid
     * @return
     */
    TianJinAccountDTO getAccountDetail(String accountUuid, Integer merchantId);

    /**
     * 获取供应商信息
     *
     * @param merchantId 商户id
     * @return
     */
    List<TianJinServerInfoDTO> getServerInfo(Integer merchantId);

    /**
     * 查询订单信息
     *
     * @param request 请求
     * @return
     * @throws IOException
     */
    NotifyTrxOrderDTO getOrderInfo(HttpServletRequest request) throws IOException;

    /**
     * 单笔代付
     * @param paymentInfoDTO
     * @param batchPaymentRecord
     * @param flag
     * @param trxOrderId
     * @return
     */
    String singlePaymentOfOnline(PaymentInfoDTO paymentInfoDTO, BatchPaymentRecord batchPaymentRecord, boolean flag,
                                 Integer trxOrderId,Map<String,BigDecimal> overCharge);
}
