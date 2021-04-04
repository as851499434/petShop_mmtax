package com.mmtax.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jcraft.jsch.SftpException;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.*;
import com.mmtax.business.tianjindto.*;
import com.mmtax.business.yunzbdto.YunZBSettDTO;
import com.mmtax.common.chanpay.BaseConstant;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.constant.BatchPaymentRegularExpressionConstants;
import com.mmtax.common.constant.Constants;
import com.mmtax.common.constant.PaymentConstants;
import com.mmtax.common.constant.UploadTempleteConstans;
import com.mmtax.common.constant.onlinebank.OnlineConstants;
import com.mmtax.common.constant.tianjin.TianJinConstants;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.exception.PaymentException;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.*;
import com.mmtax.common.utils.business.ConfigUtil;
import com.mmtax.common.utils.file.FileUtils;
import com.mmtax.common.utils.http.HttpUtils;
import com.mmtax.common.utils.onlinebank.OnlineCommonResultDTO;
import com.mmtax.common.utils.onlinebank.Twsign;
import com.mmtax.common.utils.redis.RedisLockConstans;
import com.mmtax.common.utils.redis.RedisLockUtil;
import com.mmtax.common.utils.redis.RedisTimeConstans;
import com.mmtax.common.utils.redis.RedisUtil;
import com.mmtax.common.utils.tianjin.TianJinUtil;
import com.mmtax.common.utils.yunzbutil.Response;
import com.mmtax.common.utils.yunzbutil.YunZBConstants;
import com.mmtax.common.utils.yunzbutil.YunZBUtil;
import com.mmtax.system.domain.SysConfig;
import com.mmtax.system.mapper.SysConfigMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.swing.BakedArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.mmtax.common.utils.RegexUtil.*;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/9 17:34
 */
@Service
public class PaymentServiceImpl extends CommonServiceImpl implements IPaymentService {
    private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    /**
     * 单人每月限额标识
     */
    private static final String SINGLE_MONTH_AMOUNT = "single_month_amount";
    /**
     * 三要素开启标志
     */
    private static final String CHECK_BANK_3C = "check_bank_3c";

    /**
     * 账户余额
     */
    private static final String PAYBALANCE = "PayBalance";
    /**
     * 回调端口
     */
    @Value("${tianjin.call.back.port}")
    private String PORT;
    /**
     * 回调ip
     */
    @Value("${tianjin.call.back.host}")
    private String HOST;
    /**
     * 回调ip
     */
    @Value("${tianjin.payment.host}")
    private String TIAN_JIN_HOST;
    /**
     * 打款的生产者
     */
    @Value("${payment.url.execute}")
    private String PAYMENT_EXECUTE_URL;
    /**
     * 打款数据校验
     */
    @Value("${payment.url.datacheck}")
    private String PAYMENT_DATACHECK_URL;

    @Resource
    private CooperationMapper cooperationMapper;
    @Resource
    private MerchantInfoMapper merchantInfoMapper;
    @Resource
    private TrxOrderMapper trxOrderMapper;
    @Resource
    private MerchantAccountMapper merchantAccountMapper;
    @Resource
    private MerchantAccountDetailMapper merchantAccountDetailMapper;
    @Resource
    private BatchPaymentRecordMapper batchPaymentRecordMapper;
    @Resource
    private MerchantKeyMapper merchantKeyMapper;
    @Resource
    private UserListMapper userListMapper;
    @Resource
    private RiskManagementConfigMapper riskManagementConfigMapper;
    @Resource
    private OrderSheetMapper orderSheetMapper;
    @Resource
    private SysConfigMapper sysConfigMapper;
    @Resource
    private TianJinPaymentInfoMapper tianJinPaymentInfoMapper;
    @Autowired
    private OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Resource
    private MerchantAccountConfigMapper merchantAccountConfigMapper;
    @Autowired
    private IOnlineBankService iOnlineBankService;
    @Resource
    private ICustomerProtocolService customerProtocolService;
    @Resource
    private OnlineAccountConfigMapper onlineAccountConfigMapper;
    @Autowired
    private TransferOrderMapper transferOrderMapper;
    @Autowired
    private IMerchantAccountService merchantAccountService;
    @Autowired
    private ICustomerInfoService customerInfoService;
    @Autowired
    private ICustomerBankcardInfoService customerBankcardInfoService;
    @Autowired
    private ICustomerAlipayInfoService customerAlipayInfoService;
    @Autowired
    private ICustomerAccountService customerAccountService;
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Autowired
    private IMakeUpChargeService makeUpChargeService;
    @Autowired
    private IBatchPaymentDetailService batchPaymentDetailService;
    @Resource
    private CustomerPromotionMapper customerPromotionMapper;
    @Autowired
    private IOrderReimburseService orderReimburseService;
    @Autowired
    IMerchantAccountDetailService merchantAccountDetailService;
    @Autowired
    CusLinkMerInfoMapper cusLinkMerInfoMapper;
    @Autowired
    CustomerTaskMapper customerTaskMapper;
    @Resource
    private BatchTaskRecordMapper batchTaskRecordMapper;
    @Autowired
    private BatchTaskDetailMapper batchTaskDetailMapper;
    @Autowired
    PayRequestDataMapper payRequestDataMapper;
    @Autowired
    private IPayRequestDataService payRequestDataService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * todo 删除注释
     *
     * @param paymentInfoDTO     代付信息
     * @param batchPaymentRecord 批量打款记录
     * @param flag               再次打款标识
     */
    @Override
    public String singlePayment(PaymentInfoDTO paymentInfoDTO, BatchPaymentRecord batchPaymentRecord, boolean flag,
                                Integer trxOrderId) throws Exception {
        logger.info("打款开始：{}", System.currentTimeMillis());
        //打款结果
        String paymentResult = null;
        JSONObject payInfo;
        //调单标识
        boolean orderSheetFlag = false;
        BigDecimal settleAmount = BigDecimal.valueOf(Double.parseDouble(paymentInfoDTO.getSettleAmount()));
        //获取商户服务费费率
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(paymentInfoDTO.getMerchantId());
        Cooperation rCooperation = cooperationMapper.selectOne(cooperation);
        //代付成功或者失败的标识
        boolean dfFlag = true;
        //查询商户信息
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(paymentInfoDTO.getMerchantId());
        //查询商户风险配置信息
        RiskManagementConfig riskManagementConfig =
                riskManagementConfigMapper.getConfigByMerchantId(merchantInfo.getId());
        //查询商户钱包信息
        MerchantAccount account = merchantAccountMapper.getMerchantAccountByMerchantId(merchantInfo.getId());
        if (account == null) {
            throw new BusinessException("您的钱包异常，请联系管理员");
        }
        double tax = settleAmount.multiply(rCooperation.getSingleServiceRate()).setScale(
                2, RoundingMode.HALF_EVEN).doubleValue();
        if (batchPaymentRecord == null) {
            if (account.getUsableAmount().compareTo(settleAmount.add(BigDecimal.valueOf(tax))) < 0) {
                if (trxOrderId != null) {
                    throw new BusinessException("您的钱包余额无法完成此次代付，请充值之后再操作");
                } else {
                    return ResultEnum.FAIL.name();
                }
            }
            if (flag) {
                checkPassword(paymentInfoDTO);
            }
        }
        //查询账户配置
        MerchantAccountConfig merchantAccountConfig =
                merchantAccountConfigMapper.getMerchantConfigByMerchantId(merchantInfo.getId());

        //获取需要代付商户的公私钥
        PaymentMerchantInfo paymentMerchantInfo =
                paymentMerchantInfoMapper.selectByMerchantIdAndChannel(paymentInfoDTO.getMerchantId(),
                        PaymentChannelEnum.YUNZB.name());

        //查询税源地公司信息
        TaxSounrceCompany taxSounrceCompany =
                taxSounrceCompanyMapper.selectByPrimaryKey(paymentMerchantInfo.getTaxSourceId());


        //畅捷平台唯一交易流水号
        String trxOrderNo = ChanPayUtil.generateOutTradeNo();
        TrxOrder trxOrder = new TrxOrder();
        //判断需要代付金额是否超出单笔限额,若超出单笔限额，订单挂起
        if (riskManagementConfig.getSingleDayQuota().equals(SwitchStatusEnum.ON)) {
            if (rCooperation.getSingleQuota().compareTo(settleAmount) < 0 && flag) {
                trxOrder = convertTrxOrder(paymentInfoDTO, merchantInfo, cooperation, tax
                        , trxOrderNo, false, settleAmount);
                trxOrder.setOrderStatus(TrxOrderStatusEnum.PAID_PENDING.code);
                trxOrder.setComment(TrxOrderCommentEnum.OUT_SINGLE_QUOTA.description);
                trxOrderMapper.insertSelective(trxOrder);
                return TrxOrderCommentEnum.OUT_SINGLE_QUOTA.description;
            }
        }

        orderSheetFlag = getOrderSheetResult(merchantInfo, paymentInfoDTO);

        if (trxOrderId == null) {
            trxOrder = convertTrxOrder(paymentInfoDTO, merchantInfo, cooperation, tax
                    , trxOrderNo, false, settleAmount);
        } else {
            trxOrder = trxOrderMapper.selectByPrimaryKey(trxOrderId);
        }
        //获取要素认证结果
        String signedResult = querySignedResult(paymentInfoDTO.getName(), paymentInfoDTO.getBankNo(),
                paymentInfoDTO.getIdCardNo(), paymentInfoDTO.getMobile(), paymentMerchantInfo, taxSounrceCompany);
        if (StringUtils.isNotEmpty(signedResult)) {
            dfFlag = false;
            trxOrder.setComment(signedResult);
        }

        if (!orderSheetFlag && dfFlag) {
//                云众包用
            YunZBSettDTO yunZBSettDTO = convertYunZBSettDTO(settleAmount.toString(), trxOrder, taxSounrceCompany,
                    paymentMerchantInfo);
            Response response = YunZBUtil.remoteInvoke(YunZBConstants.SETT, yunZBSettDTO, true,
                    taxSounrceCompany.getSecretKey());
            payInfo = JSONObject.parseObject(response.getRespData());
            if (!YunZBConstants.RESULT_CODE.equals(payInfo.get(YunZBConstants.REQUEST_CODE))) {
                //失败原因
                dfFlag = false;
                trxOrder.setComment(payInfo.getString(YunZBConstants.REQUEST_MSG));
            }

            if (!YunZBConstants.RESULT_CODE.equals(payInfo.get(YunZBConstants.RETURN_CODE))) {
                dfFlag = false;
                trxOrder.setComment(payInfo.getString(YunZBConstants.RETURN_MSG));
            }
        }
        paymentResult = doTrxOrder(dfFlag, trxOrder, settleAmount, account, tax, merchantAccountConfig, orderSheetFlag,
                batchPaymentRecord, trxOrderId, merchantInfo.getId(), merchantInfo, true);
        return paymentResult;
    }

    /**
     * 云众包打款参数
     *
     * @param money               打款金额
     * @param trxOrder            交易记录
     * @param taxSounrceCompany   税源地公司
     * @param paymentMerchantInfo 云众包商户信息
     * @return
     */
    private YunZBSettDTO convertYunZBSettDTO(String money, TrxOrder trxOrder, TaxSounrceCompany taxSounrceCompany,
                                             PaymentMerchantInfo paymentMerchantInfo) {
        YunZBSettDTO yunZBSettDTO = new YunZBSettDTO();
        yunZBSettDTO.setAmt(money);
        yunZBSettDTO.setCardName(trxOrder.getPayeeName());
        yunZBSettDTO.setCardNo(trxOrder.getPayeeBankCard());
        yunZBSettDTO.setChannelNo(queryChannels(taxSounrceCompany.getMerchantNo(),
                taxSounrceCompany.getSecretKey()));
        yunZBSettDTO.setIdCardNo(trxOrder.getPayeeIdCardNo());
        yunZBSettDTO.setNotifyUrl(YunZBConstants.SETT_NOTIFY);
        yunZBSettDTO.setMchId(taxSounrceCompany.getMerchantNo());
        yunZBSettDTO.setMobileNo(trxOrder.getPayeeMobile());
        yunZBSettDTO.setSubMchId(paymentMerchantInfo.getMerchantNo());
        yunZBSettDTO.setTradeNo(trxOrder.getMerchantSerialNum());
        return yunZBSettDTO;
    }


    /**
     * 添加调单记录
     *
     * @param merchantId 商户id
     * @param trxOrder   订单信息
     */
    private void convertOrderSheet(Integer merchantId, TrxOrder trxOrder) {
        OrderSheet orderSheet = new OrderSheet();
        orderSheet.setStatus(AuditStatusEnum.WAITING_AUDIT.code);
        orderSheet.setAuditResult(OrderSheetAuditStatusEnum.WAITING_AUDIT.name());
        orderSheet.setMerchantId(merchantId);
        orderSheet.setTrxOrderId(trxOrder.getId());
        orderSheet.setOrderNum(1);
        orderSheet.setOrderNo(ChanPayUtil.generateOutTradeNo());
        orderSheet.setCreateTime(DateUtil.date());
        orderSheet.setUpdateTime(DateUtil.date());
        orderSheet.setRiskPoint(RiskPointEnum.PAYEE_SUSPECTS_IS_SUPERVISOR_OF_ENTERPRISE.description);
        orderSheetMapper.insertSelective(orderSheet);
    }

    /**
     * todo  batch_no payment_voucher
     * 生成代付记录
     *
     * @param paymentInfoDTO 代付信息
     * @param merchantInfo   商户信息
     * @param cooperation    合作信息
     * @param tax            扣税金额
     * @param trxOrderNo     订单流水
     * @return
     */
    private TrxOrder convertTrxOrder(PaymentInfoDTO paymentInfoDTO, MerchantInfo merchantInfo, Cooperation cooperation
            , Double tax, String trxOrderNo, boolean useBigRate, BigDecimal settleAmount) {
        TrxOrder trxOrder = new TrxOrder();
        trxOrder.setMemo(paymentInfoDTO.getMemo());
        trxOrder.setMerchantName(merchantInfo.getMerchantName());
        BigDecimal mouldAmount = BigDecimal.valueOf(Double.parseDouble(paymentInfoDTO.getSettleAmount()));
        trxOrder.setAmount(mouldAmount);
        trxOrder.setTaxRate(cooperation.getSingleServiceRate());
        if (useBigRate) {
            trxOrder.setTrxRateBig(cooperation.getSingleServiceBigRate());
            trxOrder.setChargeBig(BigDecimal.valueOf(tax));
            trxOrder.setUseBigRate(UseBigRateEnum.BIGRATE.getStatus());
            trxOrder.setCharge(mouldAmount.multiply(trxOrder.getTaxRate()).setScale(2, BigDecimal.ROUND_UP));
            trxOrder.setBigInOutDeduction(cooperation.getInOrOutDeduction());
            trxOrder.setInOutDeduction(cooperation.getInOrOutDeduction());
        } else {
            trxOrder.setCharge(BigDecimal.valueOf(tax));
            trxOrder.setUseBigRate(UseBigRateEnum.NORMALRATE.getStatus());
            trxOrder.setInOutDeduction(cooperation.getInOrOutDeduction());
        }
        trxOrder.setActualAmount(settleAmount);
        if (StringUtils.isNotEmpty(paymentInfoDTO.getMerchantTrxOrderNo())) {
            trxOrder.setMerchantSerialNum(paymentInfoDTO.getMerchantTrxOrderNo());
        } else {
            trxOrder.setMerchantSerialNum(trxOrderNo);
        }
        trxOrder.setOrderSerialNum(ChanPayUtil.generateOutTradeNo());
        trxOrder.setPayeeName(paymentInfoDTO.getName());
        trxOrder.setPayeeBankCard(paymentInfoDTO.getBankNo());
        trxOrder.setPayeeIdCardNo(paymentInfoDTO.getIdCardNo());
        trxOrder.setBankName(null);
        trxOrder.setSubjectConscription(merchantInfo.getSubject());
        trxOrder.setPayeeMobile(paymentInfoDTO.getMobile());
        trxOrder.setPaymentChannel(paymentInfoDTO.getPaymentChannel());
        trxOrder.setRemark(paymentInfoDTO.getRemark());
        trxOrder.setMerchantId(merchantInfo.getId());
        trxOrder.setAsyncStatus(AsyncStatusEnum.NOHANDLE.getStatus());
        trxOrder.setNeedPayCard(NeedPayCardEnum.NEED.getStatus());
        trxOrder.setTaskRecordBatchNo(paymentInfoDTO.getTaskRecordBatchNo());
        if (SwitchEnum.ON.getCode().equals(cooperation.getSignSwitch())
                && SwitchEnum.ON.getCode().equals(cooperation.getCustomerWithdrawSwitch())) {
            trxOrder.setNeedPayCard(NeedPayCardEnum.NO_NEED.getStatus());
        }
        trxOrder.setCreateTime(DateUtil.date());
        trxOrder.setUpdateTime(DateUtil.date());
        return trxOrder;
    }

    /**
     * 更新账户余额
     *
     * @param settleAmount    金额
     * @param merchantAccount 账户信息
     * @param trxOrder        代付记录
     * @param tax             扣税金额
     */
    private void convertMerchantAccountYunzhongbao(BigDecimal settleAmount, MerchantAccount merchantAccount,
                                                   TrxOrder trxOrder, double tax, MerchantInfo merchantInfo) {
        BigDecimal amount = settleAmount.add(BigDecimal.valueOf(tax));
        //添加代付记录
        MerchantAccountDetail merchantAccountDetail = new MerchantAccountDetail();
        merchantAccountDetail.setMerchantId(merchantAccount.getMerchantId());
        merchantAccountDetail.setPaymentAmount(settleAmount);
        merchantAccountDetail.setPaymentUsableAmountBefore(merchantAccount.getUsableAmount());
        merchantAccountDetail.setPaymentUsableAmountAfter(merchantAccount.getUsableAmount().subtract(amount));
        merchantAccountDetail.setPaymentAmountBefore(merchantAccount.getAmount());
        merchantAccountDetail.setPaymentAmountAfter(merchantAccount.getAmount().subtract(amount));
        merchantAccountDetail.setStatus(AccountDetailStatusEnum.SUCCESS.code);
        merchantAccountDetail.setType(MerchantAccountDetailTypeEnum.PAID.code);
        merchantAccountDetail.setPaymentFrozenAmountBefore(merchantAccount.getFrozenAmount());
        merchantAccountDetail.setPaymentFrozenAmountAfter(merchantAccount.getFrozenAmount());
        merchantAccountDetail.setOrderSerialNum(trxOrder.getOrderSerialNum());
        merchantAccountDetail.setCreateTime(DateUtil.date());
        merchantAccountDetail.setUpdateTime(DateUtil.date());
        merchantAccountDetail.setPaymentChannel(trxOrder.getPaymentChannel());
        merchantAccountDetailMapper.insertSelective(merchantAccountDetail);
        String key = null;
        String value = UUID.randomUUID().toString();
        try {
            key = RedisLockConstans.ACCOUNT_LOCK + merchantInfo.getMerchantCode();
            RedisUtil.lock(key, value);
            //更新余额
//            merchantAccountMapper.updateMerchantAccount(merchantAccount.getMerchantId(),
//                    BigDecimal.ZERO.subtract(amount).toString());
            merchantAccountService.updateMerchantAccountByVersion(merchantAccount.getMerchantId(),
                    BigDecimal.ZERO.subtract(amount));
        } finally {
            RedisUtil.releaseDistributedLock(key, value);
            logger.info("销毁钱包redis锁");
        }
    }

    /**
     * 更新账户余额
     *
     * @param settleAmount    金额
     * @param merchantAccount 账户信息
     * @param trxOrder        代付记录
     * @param tax             扣税金额
     */
    @Override
    public MerchantAccountDetail convertMerchantAccount(BigDecimal settleAmount, MerchantAccount merchantAccount,
                                                        TrxOrder trxOrder, double tax, MerchantInfo merchantInfo) {
        //代付加服务费
        BigDecimal amount = settleAmount.add(BigDecimal.valueOf(tax));
        //添加代付记录
        MerchantAccountDetail merchantAccountDetail = new MerchantAccountDetail();
        merchantAccountDetail.setMerchantId(merchantAccount.getMerchantId());
        merchantAccountDetail.setPaymentAmount(amount);
        merchantAccountDetail.setPaymentUsableAmountBefore(merchantAccount.getUsableAmount());
        merchantAccountDetail.setPaymentUsableAmountAfter(merchantAccount.getUsableAmount().subtract(amount));
        merchantAccountDetail.setPaymentAmountBefore(merchantAccount.getAmount());
        merchantAccountDetail.setPaymentAmountAfter(merchantAccount.getAmount());
        merchantAccountDetail.setStatus(AccountDetailStatusEnum.SUCCESS.code);
        merchantAccountDetail.setType(MerchantAccountDetailTypeEnum.PAID.code);
        merchantAccountDetail.setPaymentFrozenAmountBefore(merchantAccount.getFrozenAmount());
        merchantAccountDetail.setPaymentFrozenAmountAfter(merchantAccount.getFrozenAmount().add(amount));
        merchantAccountDetail.setOrderSerialNum(trxOrder.getOrderSerialNum());
        merchantAccountDetail.setCreateTime(DateUtil.date());
        merchantAccountDetail.setUpdateTime(DateUtil.date());
        merchantAccountDetail.setPaymentChannel(trxOrder.getPaymentChannel());
        merchantAccountDetailMapper.insertSelective(merchantAccountDetail);
        String key = null;
        String value = UUID.randomUUID().toString();
        try {
            key = RedisLockConstans.ACCOUNT_LOCK + merchantInfo.getMerchantCode();
            RedisUtil.lock(key, value);
            //冻结金额
            merchantAccountService.updateMerchantAccountByVersion(merchantAccount.getMerchantId(),
                    BigDecimal.ZERO.subtract(amount), UpdateAccountTypeOfMethod.PAYORMAKEUP.getType());
        } finally {
            RedisUtil.releaseDistributedLock(key, value);
            logger.info("销毁钱包redis锁");
        }
        return merchantAccountDetail;
    }

    @Override
    public void readEncryptUploadOnline(String dateString,String filePath, String fileName, Integer taxSourceId) {
        OnlineAccountConfig onlineAccountconfig = onlineAccountConfigMapper.selectByTaxSourceId(taxSourceId);
        String host = onlineAccountconfig.getSftpIp();
        Integer port = Integer.valueOf(onlineAccountconfig.getSftpPort());
        String userName = onlineAccountconfig.getSftpUser();
        String pw = onlineAccountconfig.getSftpPass();
        String pid = onlineAccountconfig.getPid();
        String baseDirect = "/" + pid;
        String uploadDirect = baseDirect + "/" + dateString + "/";
        //加密文件
        byte[] bytes = FileUtils.fileConvertToByteArray(new File(filePath + fileName));
        String enCodeFilePath = "E:\\download\\";
        try {
            OutputStream fos = new FileOutputStream(enCodeFilePath + fileName);
            fos.write(Twsign.encode(bytes, onlineAccountconfig.getKeyStoreName()));
            fos.flush();
        }catch (Exception e){
            logger.error("加密批量打款文件出错：",e);
            throw new BusinessException("加密批量打款文件出错");
        }

        try {
            SftpUtil.loginSftp(host, port, userName, pw);
            SftpUtil.upLoadFile(uploadDirect, fileName, new FileInputStream(enCodeFilePath + fileName));
            SftpUtil.logoutSftp();
        }catch (IOException | SftpException e){
            logger.error("批量打款文件发送至网商出错：",e);
            throw new BusinessException("批量打款文件发送至网商出错");
        }
    }

    /**
     * 读取上传模板头部信息 批次号 派单批次号 总笔数 总金额，并加以校验
     *
     * @param objects     excel 数据
     * @param summaryDTO  返回数据封装
     * @param cooperation 商户合作信息
     * @return 封装数据
     */
    private BatchPaymentSummaryDTO readExcelHeadData(List<Object> objects, BatchPaymentSummaryDTO summaryDTO,
                                                     Cooperation cooperation) {
        if (objects.get(0) == null) {
            throw new BusinessException("请填写批次号");
        }
        if (SwitchEnum.ON.getCode().equals(cooperation.getSendOrderSwitch())) {
            logger.info("批量打款batchNo:{},amount:{},count:{}", objects.get(0), objects.get(3), objects.get(2));
            summaryDTO.setBatchNo(objects.get(0).toString());
            if (objects.get(1) == null) {
                throw new BusinessException("请填写派单批次号");
            } else {
                // 获取批量派单记录
                BatchTaskRecord query = new BatchTaskRecord();
                query.setMerchantId(cooperation.getMerchantId());
                query.setBatchNo(objects.get(1).toString().trim());
                int count = batchTaskRecordMapper.selectCount(query);
                if (count == 0) {
                    throw new BusinessException("派单批次号对应的派单记录不存在，请确认选择派单打款模板，并正确" +
                            "填写派单批次号");
                } else if (count > 1) {
                    throw new BusinessException("派单批次号对应的派单记录有多条，请确认选择派单打款模板，并正确" +
                            "填写派单批次号");
                }
                summaryDTO.setTaskRecordBatchNo(objects.get(1).toString());
            }
            summaryDTO.setCount(((Long) objects.get(2)).intValue());
            summaryDTO.setAmount((Double) objects.get(3));
        } else {
            logger.info("批量打款batchNo:{},amount:{},count:{}", objects.get(0), objects.get(2), objects.get(1));
            summaryDTO.setBatchNo(objects.get(0).toString());
            try {
                summaryDTO.setCount(((Long) objects.get(1)).intValue());
            } catch (ClassCastException e) {
                throw new BusinessException("总打款数字段读取失败，请检查模板和总打款数");
            }
            try {
                summaryDTO.setAmount((Double) objects.get(2));
            } catch (ClassCastException e) {
                throw new BusinessException("总打款金额字段读取失败，请检查模板和总打款金额");
            }
        }
        return summaryDTO;
    }

    /**
     * 批量代付
     *
     * @param file
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map batchPayment(MultipartFile file, CheckPasswordDTO checkPasswordDTO)
            throws Exception {
        // 打款业务开关验证
        boolean flag = getPaymentAllowFlag();
        if (!flag) {
            throw new BusinessException("服务器维护中，请稍后再试");
        }
        if (file == null) {
            throw new BusinessException("批量代付上传文件不能为空");
        }
        logger.info("参数为：{}", JSONObject.toJSON(checkPasswordDTO));

        Integer merchantId = checkPasswordDTO.getMerchantId();
        //查询商户信息
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
        Integer taxSourceId = onlinePaymentInfo.getTaxSourceCompanyId();

        //获取商户合作信息
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(checkPasswordDTO.getMerchantId());
        Cooperation rCooperation = cooperationMapper.selectOne(cooperation);

        //checkPassword(checkPasswordDTO);
        BatchPaymentSummaryDTO batchPaymentSummaryDTO = new BatchPaymentSummaryDTO();
        //获取需要代付的汇总数据
        ExcelReader excelReader = ExcelUtil.getReader(file.getInputStream());
        List<List<Object>> read = excelReader.read(2, 2);
        List<List<Object>> detailRead = excelReader.read(4, excelReader.getRowCount());
        List<PaymentInfoDTO> paymentInfoDTOList = new ArrayList<>();
        if (CollectionUtils.isEmpty(detailRead)) {
            throw new BusinessException("批量代付上传文件不能为空");
        }
        for (List<Object> objects : read) {
            batchPaymentSummaryDTO = readExcelHeadData(objects, batchPaymentSummaryDTO, rCooperation);
        }
        BatchPaymentRecord rBatchPaymentRecord = new BatchPaymentRecord();
        rBatchPaymentRecord.setBatchNo(batchPaymentSummaryDTO.getBatchNo());
        rBatchPaymentRecord.setMerchantId(checkPasswordDTO.getMerchantId());
        int count = batchPaymentRecordMapper.selectCount(rBatchPaymentRecord);
        if (count > 0) {
            throw new BusinessException("批次号已存在");
        }
        //添加批量代付记录
        BatchPaymentRecord batchPaymentRecord = new BatchPaymentRecord();
        batchPaymentRecord.setBatchNo(batchPaymentSummaryDTO.getBatchNo());
        batchPaymentRecord.setMerchantId(merchantId);
        batchPaymentRecord.setPaymentActualAmount(BigDecimal.ZERO);
        batchPaymentRecord.setPaymentAmount(BigDecimal.valueOf(batchPaymentSummaryDTO.getAmount()));
        batchPaymentRecord.setPaymentChannel(checkPasswordDTO.getPaymentChannel());
        batchPaymentRecord.setPaymentCount(batchPaymentSummaryDTO.getCount());
        batchPaymentRecord.setTrxExternalNo(ChanPayUtil.generateOutTradeNo());
        batchPaymentRecord.setStatus(BatchPaymentRecordStatusEnum.TODO.code);
        batchPaymentRecord.setOperator(merchantInfo.getMerchantName());
        batchPaymentRecord.setCreater(merchantInfo.getMerchantName());
        batchPaymentRecord.setCreateTime(DateUtil.date());
        batchPaymentRecord.setUpdateTime(DateUtil.date());


        String batchNo = batchPaymentRecord.getBatchNo();
        // idCardNoAmount 统一批次同人打款金额集合 key-证件号 value 合计金额
        Map<String, BigDecimal> idCardNoAmount = new HashMap<>();
        Set<String> hasMakeUpRecord = new HashSet<>();
        //记录序号，模板中从第5条开始
        int number = 4;
        //总笔数
        int realCount = 0;
        //发送到队列接口的集合
        List<PayRequestData> payRequestDataList = new ArrayList<>();
        //错误消息
        List<BatchErrorResultDTO> batchErrorResultDTOS = new ArrayList<>();
        boolean isFail = Boolean.FALSE;
        for (List<Object> objects : detailRead) {
            number++;
            realCount++;
            PaymentInfoDTO dto = new PaymentInfoDTO();
            dto.setTaskRecordBatchNo(batchPaymentSummaryDTO.getTaskRecordBatchNo());

            //amount = checkData(checkPasswordDTO, nummber, amount, objects);
            StringBuilder errorMessage = checkData(checkPasswordDTO, number, objects);

            if (objects.get(0) != null && StringUtils.isNotEmpty(objects.get(0).toString())) {
                dto.setMerchantTrxOrderNo(objects.get(0).toString().trim());
            }
            dto.setBankNo(objects.get(1).toString().trim());
            dto.setName(objects.get(2).toString().trim());
            dto.setIdCardNo(objects.get(3).toString().toUpperCase().trim());
            dto.setSettleAmount(objects.get(4).toString().trim());
            if (objects.size() >= 7 && null != objects.get(6) && StringUtils.isNotEmpty(objects.get(6).toString())) {
                dto.setRemark(objects.get(6).toString());
            }
            BigDecimal mouldAmount = BigDecimal.valueOf(Double.parseDouble(dto.getSettleAmount()));
            if (mouldAmount.compareTo(BigDecimal.ZERO) < 0) {
                errorMessage.append("金额不能为负,");
                //throw new BusinessException("金额不能为负");
            }
            dto.setMobile(objects.get(5).toString().trim());
            dto.setMerchantId(merchantId);
            dto.setPaymentChannel(checkPasswordDTO.getPaymentChannel());
            dto.setAccountId(checkPasswordDTO.getAccountId());
            dto.setServerId(checkPasswordDTO.getServerId());

            // 加入单笔限额
            if (PaymentConstants.SINGLE_MAX_QUOTA.compareTo(mouldAmount) < 0) {
                errorMessage.append(dto.getIdCardNo()).append("单笔打款超过限额")
                        .append(PaymentConstants.SINGLE_MAX_QUOTA.toPlainString()).append(",");
                //throw new BusinessException("该收款人" + dto.getIdCardNo() + "单笔打款超过限额" + PaymentConstants.SINGLE_MAX_QUOTA.toPlainString());
            }
            if (idCardNoAmount.containsKey(dto.getIdCardNo())) {
                idCardNoAmount.put(dto.getIdCardNo(), idCardNoAmount.get(dto.getIdCardNo()).add(mouldAmount));
            } else {
                idCardNoAmount.put(dto.getIdCardNo(), mouldAmount);
            }
//            //查询单人每月收款限制
//            SysConfig rSysConfig = getMonthlyLimitAmount(rCooperation.getBigRateSwitch());
//            BigDecimal standardAmount = BigDecimal.valueOf(Double.parseDouble(rSysConfig.getConfigValue()));
//            //查询单人当月当前税源地交易量 sumAmount
//            String startDate = DateUtil.format(DateUtil.beginOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
//            String endDate = DateUtil.format(DateUtil.endOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
//            //计算历史打款额(内扣外扣)
//            Integer[] statusList = {-1, 0, 1};
//            List<TrxOrder> historyTrxOrders = trxOrderMapper.selectByStatusInOneMonth(null, null
//                    , dto.getIdCardNo(), startDate, endDate, statusList, taxSourceId);
//            BigDecimal sumAmount = BigDecimal.ZERO;
//            if (!org.springframework.util.CollectionUtils.isEmpty(historyTrxOrders)) {
//                sumAmount = historyTrxOrders.stream().reduce(BigDecimal.ZERO
//                        , (a, b) -> a.add(calTrxOrderMouldAmount(b)), (a, b) -> BigDecimal.ZERO);
//            }
//            if (standardAmount.compareTo(sumAmount.add(idCardNoAmount.get(dto.getIdCardNo()))) < 0) {
//                throw new BusinessException("该收款人" + dto.getIdCardNo() + "当月收款超过累计限额"
//                        + rSysConfig.getConfigValue());
//            }

            //判断 任务金额是否已经打够
            nullLable:
            if (SwitchEnum.ON.getCode().equals(rCooperation.getSendOrderSwitch())) {
                BigDecimal taskDetialAllAmount = batchTaskDetailMapper.getAllAmountByBathcNoAndCertificateNo(
                        batchPaymentSummaryDTO.getTaskRecordBatchNo(), dto.getIdCardNo());
                BigDecimal trxOrderAllAmout = trxOrderMapper.getAllamoutByBatchNoAndCertificateNo(
                        batchPaymentSummaryDTO.getTaskRecordBatchNo(), dto.getIdCardNo());

                BatchTaskDetail queryBatchTaskDetail =
                        batchTaskDetailMapper.queryBatchTaskDetailByBathcNoAndCertificateNo(batchPaymentSummaryDTO.getTaskRecordBatchNo(), dto.getIdCardNo());

                if (null == queryBatchTaskDetail) {
                    errorMessage.append("该灵工没有参与本次派单任务，不予打款,");
                    //throw new BusinessException("身份证号:" + dto.getIdCardNo() + "灵工,没有参与本次派单任务，不予打款。");
                    break nullLable;
                }
                if (TaskStatusEnum.UNFINISH.getCode().equals(queryBatchTaskDetail.getTaskStatus())) {
                    errorMessage.append("该灵工任务状态为待完成不予打款,");
                    //throw new BusinessException("身份证号:" + dto.getIdCardNo() + "灵工,任务状态为待完成不予打款。");
                }
                if (TaskStatusEnum.UNCOMPLETE.getCode().equals(queryBatchTaskDetail.getTaskStatus())) {
                    errorMessage.append("该灵工任务状态为未完成不予打款,");
                    //throw new BusinessException("身份证号:" + dto.getIdCardNo() + "灵工,任务状态为未完成不予打款。");
                }
                if (taskDetialAllAmount.compareTo(idCardNoAmount.get(dto.getIdCardNo()).add(trxOrderAllAmout)) < 0) {
                    errorMessage.append("该灵工任务金额为").append(taskDetialAllAmount.toString())
                            .append("元,已打款:").append(trxOrderAllAmout.toString())
                            .append("元,本次最多可打款")
                            .append(taskDetialAllAmount.subtract(trxOrderAllAmout).toString()).append("元,");
                    //throw new BusinessException("身份证号:" + dto.getIdCardNo() + "灵工,任务金额为" + taskDetialAllAmount.toString() + "元,已打款:" + trxOrderAllAmout.toString() + "元,本次最多可打款" + taskDetialAllAmount.subtract(trxOrderAllAmout).toString() + "元。");
                }
            }
            paymentInfoDTOList.add(dto);
            //校验后数据入库
            PayRequestData payRequestData = new PayRequestData();
            payRequestData.setMerchantId(batchPaymentRecord.getMerchantId());
            payRequestData.setAmount(new BigDecimal(dto.getSettleAmount()));
            payRequestData.setInOutDeduction(rCooperation.getInOrOutDeduction());
            payRequestData.setMerchantSerialNum(dto.getMerchantTrxOrderNo());
            payRequestData.setName(dto.getName());
            payRequestData.setMobile(dto.getMobile());
            payRequestData.setBankCard(dto.getBankNo());
            payRequestData.setIdCardNo(dto.getIdCardNo());
            payRequestData.setPaymentChannel(dto.getPaymentChannel());
            payRequestData.setRemark(dto.getRemark());
            payRequestData.setComment(dto.getComment());
            payRequestData.setPromotionStatus(PromotionEnum.NOT_PROMOTION.getCode());
            payRequestData.setStatus(PromotionEnum.MIDDLE_STATUS.getCode());
            payRequestData.setUseBigRate(PromotionEnum.NOT_USE_BIG_RATE.getCode());
            payRequestData.setDelStatus(PromotionEnum.NOT_DEL_STATUS.getCode());
            payRequestData.setTaskRecordBatchNo(batchPaymentSummaryDTO.getTaskRecordBatchNo());
            BatchErrorResultDTO batchErrorResultDTO = new BatchErrorResultDTO();
            payRequestDataList.add(payRequestData);
            BeanUtils.copyProperties(payRequestData,batchErrorResultDTO);
            if(!"".equals(errorMessage.toString())){
                isFail = Boolean.TRUE;
                batchErrorResultDTO.setCheckResult("失败");
                batchErrorResultDTO.setComment(errorMessage.toString());
                batchErrorResultDTOS.add(batchErrorResultDTO);
            }

        }
        //判断总金额和总笔数
        if (realCount != batchPaymentSummaryDTO.getCount()) {
            throw new BusinessException("总条数错误，请检查后上传");
        }
        //抛出所有的错误信息
        if (isFail){
            logger.info("上传校验错误,merchantId:{},batchNo:{}",merchantId,batchNo);
            Map<String,String> map = new HashMap<>();
            map.put("batchNo",batchNo);
            map.put("batchErrorResultDTOS",
                    JSON.toJSONString(batchErrorResultDTOS, SerializerFeature.WriteMapNullValue));
            String key = merchantId + batchNo + System.currentTimeMillis();
            map.put("key",key);
            Page<Object> page = new Page<>(1, 10, batchErrorResultDTOS.size());
            map.put("totalPage",page.getTotalPage()+"");
            map.put("totalRecord",batchErrorResultDTOS.size()+"");
            RedisUtil.put(key,map,RedisUtil.ONE,TimeUnit.HOURS);

            Map<String,Object> resultMap = new HashMap<>(map);
            if(batchErrorResultDTOS.size()<10){
                resultMap.put("batchErrorResultDTOS",batchErrorResultDTOS.subList(0,batchErrorResultDTOS.size()));
            }else{
                resultMap.put("batchErrorResultDTOS",batchErrorResultDTOS.subList(0,10));
            }


            return resultMap;
        }
        //数据入库
        batchPaymentRecordMapper.insertSelective(batchPaymentRecord);

        for(PayRequestData payRequestData:payRequestDataList){
            payRequestData.setBatchPaymentRecordId(batchPaymentRecord.getId());
            payRequestDataMapper.insertSelective(payRequestData);
        }
        //所有校验完成后发送消息到队列所在接口
//        String callBackResult = HttpUtils.sendPost(PAYMENT_DATACHECK_URL, JSON.toJSONString(payRequestDataList));
//        logger.info("发送消息到生产者所在接口返回的结果：{}", callBackResult);
//        if (StringUtils.isEmpty(callBackResult) || !NotifyStatusEnum.SUCCESS.name().equals(callBackResult)) {
//            throw new BusinessException("上传失败，请联系管理员");
//        }

        //将批次号放到redis中
        RedisUtil.put(Constants.PAYMENT_FLAG + merchantId, batchPaymentRecord.getBatchNo(), RedisUtil.ONEDAY, TimeUnit.MINUTES);
        Map<String, String> map = new HashMap<>(2);
        map.put("merchantId", merchantId.toString());
        map.put("batchNo", batchPaymentRecord.getBatchNo());
//        map.put("batchNo",batchNo);
        return map;
    }

    @Override
    public Map getErrorData(Integer currentPage,Integer pageSize,String key){

        Map<String,String> resultMap = JSONObject.parseObject(RedisUtil.get(key),Map.class);
        List<BatchErrorResultDTO> batchErrorResultDTOS =
                JSONObject.parseArray(resultMap.get("batchErrorResultDTOS"), BatchErrorResultDTO.class);
        Page<Object> page = new Page<>(currentPage, pageSize, batchErrorResultDTOS.size());
        Map<String,Object> map = new HashMap<>(resultMap);
        int startRecord = page.getStartRecord();
        if(startRecord + pageSize > batchErrorResultDTOS.size()){
            resultMap.put("batchErrorResultDTOS",
                    JSON.toJSONString(batchErrorResultDTOS.subList(startRecord,batchErrorResultDTOS.size()),
                            SerializerFeature.WriteMapNullValue));
            map.put("batchErrorResultDTOS",batchErrorResultDTOS.subList(startRecord,batchErrorResultDTOS.size()));
        }else{
            resultMap.put("batchErrorResultDTOS",
                    JSON.toJSONString(batchErrorResultDTOS.subList(startRecord,startRecord + pageSize),
                            SerializerFeature.WriteMapNullValue));
            map.put("batchErrorResultDTOS",batchErrorResultDTOS.subList(startRecord,startRecord + pageSize));

        }
        return map;
    }

    @Override
    public Map getPromotionErrorData(Integer currentPage,Integer pageSize,String key){

        Map<String,String> resultMap = JSONObject.parseObject(RedisUtil.get(key),Map.class);
        List<PromotionBatchErrorResultDTO> batchErrorResultDTOS =
                JSONObject.parseArray(resultMap.get("batchErrorResultDTOS"), PromotionBatchErrorResultDTO.class);
        Page<Object> page = new Page<>(currentPage, pageSize, batchErrorResultDTOS.size());
        Map<String,Object> map = new HashMap<>(resultMap);
        int startRecord = page.getStartRecord();
        if(startRecord + pageSize > batchErrorResultDTOS.size()){
            resultMap.put("batchErrorResultDTOS",
                    JSON.toJSONString(batchErrorResultDTOS.subList(startRecord,batchErrorResultDTOS.size()),
                            SerializerFeature.WriteMapNullValue));
            map.put("batchErrorResultDTOS",batchErrorResultDTOS.subList(startRecord,batchErrorResultDTOS.size()));
        }else{
            resultMap.put("batchErrorResultDTOS",
                    JSON.toJSONString(batchErrorResultDTOS.subList(startRecord,startRecord + pageSize),
                            SerializerFeature.WriteMapNullValue));
            map.put("batchErrorResultDTOS",batchErrorResultDTOS.subList(startRecord,startRecord + pageSize));

        }
        return map;
    }

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("batchNo","111222333");
        List<BatchErrorResultDTO> batchErrorResultDTOS = new ArrayList<>();
        for(int i = 0 ;i<100;i++){
            BatchErrorResultDTO batchErrorResultDTO = new BatchErrorResultDTO();
            batchErrorResultDTO.setComment(i+"");
            batchErrorResultDTOS.add(batchErrorResultDTO);
        }
        map.put("batchErrorResultDTOS",batchErrorResultDTOS);
        String key = "321"+"123"+System.currentTimeMillis();
        RedisUtil.put(key,map,1,TimeUnit.HOURS);

        String result = RedisUtil.get(key);
        Map resultMap = JSONObject.parseObject(result,Map.class);
        System.out.println(map.get("batchNo"));
        System.out.println("==================================");
        System.out.println(map.get("batchErrorResultDTOS"));

    }

    public boolean judgeMonthlyLimitAmount(BigDecimal mouldAmount,String idcardNo,Cooperation cooperation
            ,Integer taxSourceId,Map<String,BigDecimal> idCardNoAmount){
        if (PaymentConstants.SINGLE_MAX_QUOTA.compareTo(mouldAmount) < 0) {
            throw new BusinessException("该收款人" + idcardNo + "单笔打款超过限额"
                    + PaymentConstants.SINGLE_MAX_QUOTA.toPlainString());
        }
        //查询单人每月收款限制
        SysConfig rSysConfig = getMonthlyLimitAmount(cooperation.getBigRateSwitch());
        BigDecimal standardAmount = BigDecimal.valueOf(Double.parseDouble(rSysConfig.getConfigValue()));
        //查询单人当月当前税源地交易量 sumAmount
        String startDate = DateUtil.format(DateUtil.beginOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
        String endDate = DateUtil.format(DateUtil.endOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
        //计算历史打款额(内扣外扣)
        Integer[] statusList = {-1, 0, 1};
        List<TrxOrder> historyTrxOrders = trxOrderMapper.selectByStatusInOneMonth(null, null
                , idcardNo, startDate, endDate, statusList, taxSourceId);
        BigDecimal sumAmount = BigDecimal.ZERO;
        if (!org.springframework.util.CollectionUtils.isEmpty(historyTrxOrders)) {
            sumAmount = historyTrxOrders.stream().reduce(BigDecimal.ZERO
                    , (a, b) -> a.add(calTrxOrderMouldAmount(b)), (a, b) -> BigDecimal.ZERO);
        }
        logger.info("{}的灵工本月历史打款额{}",idcardNo,sumAmount);
        if (standardAmount.compareTo(sumAmount.add(idCardNoAmount.get(idcardNo))) < 0) {
            throw new BusinessException("该收款人" + idcardNo + "当月收款超过累计限额"
                    + rSysConfig.getConfigValue());
        }
        return true;
    }

    @Override
    public void sendToProuce(Integer merchantId, String batchNo) {
        // 打款业务开关验证
        boolean flag = getPaymentAllowFlag();
        if (!flag) {
            throw new BusinessException("服务器维护中，请稍后再试");
        }
        BatchPaymentRecord batchPaymentRecord = new BatchPaymentRecord();
        batchPaymentRecord.setMerchantId(merchantId);
        batchPaymentRecord.setBatchNo(batchNo);
        batchPaymentRecord = batchPaymentRecordMapper.selectOne(batchPaymentRecord);
        List<PayRequestData> payRequestDatas = payRequestDataMapper.selectListPagination(batchPaymentRecord.getId()
                , null, null);
        //所有校验完成后发送消息到队列所在接口
        logger.info("发送消息到生产者的数据:{}", JSON.toJSONString(payRequestDatas));
        String callBackResult = HttpUtils.sendPost(PAYMENT_DATACHECK_URL, JSON.toJSONString(payRequestDatas));
        logger.info("发送消息到生产者所在接口返回的结果：{}", callBackResult);
        if (StringUtils.isEmpty(callBackResult) || !NotifyStatusEnum.SUCCESS.name().equals(callBackResult)) {
            throw new BusinessException("上传失败，请联系管理员");
        }
        batchPaymentRecord.setCheckStatus(CheckStatusEnum.YES.getCode());
        batchPaymentRecordMapper.updateByPrimaryKeySelective(batchPaymentRecord);
    }

    private StringBuilder checkData(CheckPasswordDTO checkPasswordDTO, int number, List<Object> objects) {
        StringBuilder message = new StringBuilder();
        if (objects.get(2) == null || "".equals(objects.get(2).toString().replace(" ", ""))) {
            message.append("收款人姓名不能为空,");
            //throw new BusinessException("模板序号为" + number + "收款人姓名不能为空");
        }

        String name = StringUtils.trim(objects.get(2).toString());
        boolean nameMatches = regexName(name);
        if (!nameMatches) {
            message.append("收款用户名错误,");
            //throw new BusinessException("模板序号为" + number + "收款用户名错误！");
        }
        objects.set(2, name);

        if (objects.get(3) == null || "".equals(objects.get(3).toString().replace(" ", ""))) {
            message.append("收款人身份证号不能为空,");
            //throw new BusinessException("姓名为" + objects.get(2).toString() + "的用户,收款人身份证号不能为空");
        }

        String idCardNo = StringUtils.trim(objects.get(3).toString());
        if (!IdcardUtil.isValidCard(idCardNo)) {
            logger.info("身份证号格式为17位数字+X或数字idCardNo:{}", idCardNo);
            message.append("身份证号填写错误,");
            //throw new BusinessException("姓名为" + objects.get(2).toString() + "的用户,身份证号填写错误！");
        }
        objects.set(3, idCardNo);
        if (objects.get(0) != null && StringUtils.isNotEmpty(objects.get(0).toString())) {
            Integer num = trxOrderMapper.getCountByMerchantSerialNum(objects.get(0).toString().trim(), checkPasswordDTO.getMerchantId());
            if (num > 0) {
                message.append("商户订单号不能重复,");
                //throw new BusinessException("姓名为" + objects.get(2).toString() + "的用户,商户订单号不能重复");
            }
        }

        if (objects.get(0) != null && objects.get(0).toString().length() > 40) {
            message.append("商户订单号长度不可超过40,");
            //throw new BusinessException("姓名为" + objects.get(2).toString() + "的用户,商户订单号长度不可超过40");
        }

        if (objects.get(1) == null || "".equals(objects.get(1).toString().replace(" ", ""))) {
            if (PaymentEnum.BANK.name().equals(checkPasswordDTO.getPaymentChannel())) {
                message.append("银行卡号不能为空,");
                //throw new BusinessException("姓名为" + objects.get(2).toString() + "的用户,银行卡号不能为空");
            }
            if (PaymentEnum.ALIPAY.name().equals(checkPasswordDTO.getPaymentChannel())) {
                message.append("支付宝账号不能为空,");
                //throw new BusinessException("姓名为" + objects.get(2).toString() + "的用户,支付宝账号不能为空");
            }
        }

        String bankNo = StringUtils.trim(objects.get(1).toString());
        boolean bankNoMatches = regexBankNo(bankNo);
        if (!(PaymentEnum.ALIPAY.name().equals(checkPasswordDTO.getPaymentChannel())) && !bankNoMatches) {
            message.append("收款账号填写错误,");
            //throw new BusinessException("姓名为" + objects.get(2).toString() + "的用户,收款账号填写错误！");
        }
        objects.set(1, bankNo);

        if (objects.get(4) == null || "".equals(objects.get(4).toString().replace(" ", ""))) {
            message.append("打款金额不能为空,");
            //throw new BusinessException("姓名为" + objects.get(2).toString() + "的用户,打款金额不能为空");
        }

        String settleAmountStr = StringUtils.trim(objects.get(4).toString());
        BigDecimal settleAmount = new BigDecimal(settleAmountStr);
        if (settleAmount.compareTo(new BigDecimal(BatchPaymentRegularExpressionConstants.UPPER_LIMIT)) > 0 || settleAmount.compareTo(new BigDecimal(BatchPaymentRegularExpressionConstants.LOWER_LIMIT)) < 0) {
            logger.info("身份证号：{},单个输入框范围0.01-49999:{}", idCardNo, settleAmount);
            message.append( "打款金额填写错误,单个输入框范围0.01-49999,");
            //throw new BusinessException("姓名为" + objects.get(2).toString() + "的用户,打款金额填写错误,单个输入框范围0.01-49999");
        }
        objects.set(4, settleAmount);


        if (objects.get(5) == null || "".equals(objects.get(5).toString().replace(" ", ""))) {
            message.append("预留手机号不能为空,");
            //throw new BusinessException("姓名为" + objects.get(2).toString() + "的用户,预留手机号不能为空");
        }
        String mobile = StringUtils.trim(objects.get(5).toString());
        boolean mobileMatches = RegexUtil.regexPhoneLegalNo(mobile);
        if (!mobileMatches) {
            message.append( "银行卡预留手机号错误,");
            //throw new BusinessException("姓名为" + objects.get(2).toString() + "的用户,银行卡预留手机号错误！");
        }
        objects.set(5, mobile);

        if (objects.size() >= 7 && null != objects.get(6) && StringUtils.isNotEmpty(objects.get(6).toString())) {
            if (objects.get(6) != null && objects.get(6).toString().length() > 20) {
                message.append("备注长度不可超过20个字,");
                //throw new BusinessException("姓名为" + objects.get(2).toString() + "的用户,备注长度不可超过20个字！");
            }
        }
        return message;
    }

    @Override
    public PaymentInfoDTO bigRateDealOne(PayRequestData needDealdto, Integer merchantId
            , Map<String, BigDecimal> idCardNoAmount, Set<String> hasMakeUpRecord) {
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(merchantId);
        cooperation = cooperationMapper.selectOne(cooperation);
        if (null == idCardNoAmount) {
            idCardNoAmount = new HashMap<>();
        }
        if (null == hasMakeUpRecord) {
            hasMakeUpRecord = new HashSet<>();
        }

        PaymentInfoDTO dto = new PaymentInfoDTO.Builder(needDealdto).build();
        dto.setMerchantId(merchantId);
        BatchPaymentRecord batchPaymentRecord = batchPaymentRecordMapper.selectByPrimaryKey(
                needDealdto.getBatchPaymentRecordId());
        String batchNo = batchPaymentRecord.getBatchNo();
        dto.setBatchNo(batchNo);
        BigDecimal mouldAmount = BigDecimal.valueOf(Double.parseDouble(dto.getSettleAmount()));
        if (idCardNoAmount.containsKey(dto.getIdCardNo())) {
            idCardNoAmount.put(dto.getIdCardNo(), idCardNoAmount.get(dto.getIdCardNo()).add(mouldAmount));
        } else {
            idCardNoAmount.put(dto.getIdCardNo(), mouldAmount);
        }
        //判断单人每月限额
//        try {
//            OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
//            judgeMonthlyLimitAmount(mouldAmount, dto.getIdCardNo(), cooperation, onlinePaymentInfo.getTaxSourceCompanyId()
//                    , idCardNoAmount);
//        }catch (BusinessException e){
//            logger.info("id={}的PayRequestData数据超过限额，返回",needDealdto.getId());
//            needDealdto.setStatus(PayDataCheckStatusEnum.FAIL.getStatus());
//            needDealdto.setComment(e.getMessage());
//            payRequestDataMapper.updateByPrimaryKeySelective(needDealdto);
//            return null;
//        }
        String idcardNoAmountKey = Constants.IDCARDNO_AMOUNT + merchantId + batchNo;
        RedisUtil.put(idcardNoAmountKey, JSON.toJSONString(idCardNoAmount));
        //大额费率判断
        dto.setUseBigRate(false);
        if (BigRateSwitchEnum.BIDRATEOPEN.getStatus().equals(cooperation.getBigRateSwitch())) {
            //计算补交服务费总额和详细记录
            dto = makeUpChargeService.calculateMakeUpCharge(dto, merchantId, cooperation, idCardNoAmount
                    , hasMakeUpRecord);
            if (null != dto.getMakeUpCharge()) {
                //标记该员工已补交服务费
                hasMakeUpRecord.add(dto.getIdCardNo());
                String haveMakeUpKey = Constants.HAVE_MAKEUP + merchantId + batchNo;
                RedisUtil.put(haveMakeUpKey, JSON.toJSONString(hasMakeUpRecord));
                logger.info("haveMakeUpKey对应的信息:{}",JSON.toJSONString(hasMakeUpRecord));
            }
        }
        BigDecimal tax, rate;
        rate = dto.getUseBigRate() ? cooperation.getSingleServiceBigRate() : cooperation.getSingleServiceRate();
        tax = makeUpChargeService.calculateTax(mouldAmount, rate);
        dto.setTax(tax.toString());
        dto.setProvince(needDealdto.getProvince());
        dto.setCity(needDealdto.getCity());
        dto.setArea(needDealdto.getArea());
        dto.setCommunity(needDealdto.getCommunity());
        dto.setStreet(needDealdto.getStreet());
        dto.setUnitPrice(needDealdto.getUnitPrice());
        dto.setQuantity(needDealdto.getQuantity());
        dto.setPromotionStartTime(needDealdto.getPromotionStartTime());
        dto.setPromotionEndTime(needDealdto.getPromotionEndTine());
        return dto;
    }

    //把之前模板中的记录也标为大额打款
    private List<PaymentInfoDTO> changeBeforePaymentInfoToBigRate(Integer merchantId, String idcardNo
            , List<PaymentInfoDTO> infoDTOList) {
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(merchantId);
        cooperation = cooperationMapper.selectOne(cooperation);
        for (PaymentInfoDTO one : infoDTOList) {
            if (!idcardNo.equals(one.getIdCardNo())) {
                continue;
            }
            one.setUseBigRate(true);
            BigDecimal thisOneMouldAmount = BigDecimal.valueOf(Double.parseDouble(one.getSettleAmount()));
            BigDecimal newTax = makeUpChargeService.calculateTax(thisOneMouldAmount
                    , cooperation.getSingleServiceBigRate());
            one.setTax(newTax.toString());
        }
        return infoDTOList;
    }

    @Override
    public List<PaymentInfoDTO> bigRateDeal(List<PayRequestData> dtos, Integer merchantId) {
        List<PaymentInfoDTO> resultDTOs = new ArrayList<>();
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(merchantId);
        cooperation = cooperationMapper.selectOne(cooperation);
        Map<String, BigDecimal> idCardNoAmount = new HashMap<>();
        Set<String> hasMakeUpRecord = new HashSet<>();
        for (PayRequestData needDealdto : dtos) {
            PaymentInfoDTO dto = new PaymentInfoDTO.Builder(needDealdto).build();
            dto.setMerchantId(merchantId);
            BatchPaymentRecord batchPaymentRecord = batchPaymentRecordMapper.selectByPrimaryKey(
                    needDealdto.getBatchPaymentRecordId());
            dto.setBatchNo(batchPaymentRecord.getBatchNo());
            BigDecimal mouldAmount = BigDecimal.valueOf(Double.parseDouble(dto.getSettleAmount()));
            if (idCardNoAmount.containsKey(dto.getIdCardNo())) {
                idCardNoAmount.put(dto.getIdCardNo(), idCardNoAmount.get(dto.getIdCardNo()).add(mouldAmount));
            } else {
                idCardNoAmount.put(dto.getIdCardNo(), mouldAmount);
            }
            //大额费率判断
            dto.setUseBigRate(false);
            if (BigRateSwitchEnum.BIDRATEOPEN.getStatus().equals(cooperation.getBigRateSwitch())) {
                //计算补交服务费总额和详细记录
                dto = makeUpChargeService.calculateMakeUpCharge(dto, merchantId, cooperation, idCardNoAmount
                        , hasMakeUpRecord);
                if (null != dto.getMakeUpCharge()) {
                    hasMakeUpRecord.add(dto.getIdCardNo());
                    //把之前模板中的记录也标为大额打款
                    for (PaymentInfoDTO one : resultDTOs) {
                        if (!dto.getIdCardNo().equals(one.getIdCardNo())) {
                            continue;
                        }
                        one.setUseBigRate(true);
                        BigDecimal thisOneMouldAmount = BigDecimal.valueOf(Double.parseDouble(one.getSettleAmount()));
                        BigDecimal newTax = makeUpChargeService.calculateTax(thisOneMouldAmount
                                , cooperation.getSingleServiceBigRate());
                        one.setTax(newTax.toString());
                    }
                }
            }
            BigDecimal tax, rate;
            rate = dto.getUseBigRate() ? cooperation.getSingleServiceBigRate() : cooperation.getSingleServiceRate();
            tax = makeUpChargeService.calculateTax(mouldAmount, rate);
            dto.setTax(tax.toString());
            resultDTOs.add(dto);
        }
        //将模板中同一个人的打款统计到补交服务费记录中去,并且记录服务费到数据库
        for (PaymentInfoDTO one : resultDTOs) {
            if (null != one.getMakeUpCharge() && idCardNoAmount.containsKey(one.getIdCardNo())) {
                BigDecimal thisModePayment = idCardNoAmount.get(one.getIdCardNo());
                MakeUpCharge makeUpCharge = one.getMakeUpCharge();
                makeUpCharge.setMonthPayAmount(makeUpCharge.getMonthPayAmount().add(thisModePayment));
                one.setMakeUpCharge(makeUpCharge);
            }
        }
        return resultDTOs;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public Map uploadFilePromotion(MultipartFile file, CheckPasswordDTO checkPasswordDTO) throws Exception {
        // 打款业务开关验证
        boolean flag = getPaymentAllowFlag();
        if (!flag) {
            throw new BusinessException("服务器维护中，请稍后再试");
        }
        if (file == null) {
            throw new BusinessException("批量代付上传文件不能为空");
        }
        logger.info("参数为：{}", JSONObject.toJSON(checkPasswordDTO));

        Integer merchantId = checkPasswordDTO.getMerchantId();
        //查询商户信息
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);

        //checkPassword(checkPasswordDTO);
        BatchPaymentSummaryDTO batchPaymentSummaryDTO = new BatchPaymentSummaryDTO();
        //获取需要代付的汇总数据
        ExcelReader excelReader = ExcelUtil.getReader(file.getInputStream());

        List<List<Object>> detailRead = excelReader.read(4, excelReader.getRowCount());
        List<PaymentInfoDTO> paymentInfoDTOList = new ArrayList<>();
        if (CollectionUtils.isEmpty(detailRead)) {
            throw new BusinessException("批量代付上传文件不能为空");
        }

        List<List<Object>> read = excelReader.read(3, 3);
        for (List<Object> objects : read) {

            String promotionTemplate = null;
            try {
                promotionTemplate = objects.get(14).toString();
            } catch (Exception e) {
                throw new BusinessException("请上传正确的模板");
            }

            boolean promotionFlag = UploadTempleteConstans.PROMOTION_TEMPLETE.equals(promotionTemplate);
            if (!promotionFlag) {
                throw new BusinessException("请上传正确的模板");
            }

        }

        read = excelReader.read(2, 2);
        String totalAmount = null;
        for (List<Object> objects : read) {
            if (StringUtils.isEmpty(objects.get(0).toString().replace(" ", ""))) {
                throw new BusinessException("批次号，请填写后上传");
            }
            logger.info("amount:{},count:{}", objects.get(6), objects.get(4));

            if (null == objects.get(6) || "".equals(objects.get(6).toString().replace(" ", ""))) {
                throw new BusinessException("总金额不能为空，请填写后上传");
            }
            totalAmount = new DecimalFormat("0.00").format(objects.get(6));
            if (!regexBigDecimal(totalAmount)) {
                throw new BusinessException("请填写正确总金额数");
            }
            batchPaymentSummaryDTO.setAmount(Double.valueOf(String.valueOf(objects.get(6))));
            batchPaymentSummaryDTO.setBatchNo(objects.get(0).toString());
            if (null == objects.get(4) || "".equals(objects.get(4).toString().replace(" ", ""))) {
                throw new BusinessException("总笔数不能为空，请填写后上传");
            }
            if (!regexNumber(objects.get(4).toString().replace(" ", ""))) {
                throw new BusinessException("请填写正确总笔数数");
            }
            batchPaymentSummaryDTO.setCount(((Long) objects.get(4)).intValue());
        }


        BatchPaymentRecord rBatchPaymentRecord = new BatchPaymentRecord();
        rBatchPaymentRecord.setBatchNo(batchPaymentSummaryDTO.getBatchNo());
        rBatchPaymentRecord.setMerchantId(checkPasswordDTO.getMerchantId());
        int count = batchPaymentRecordMapper.selectCount(rBatchPaymentRecord);
        if (count > 0) {
            throw new BusinessException("批次号已存在");
        }

        //获取钱包信息
        MerchantAccount merchantAccount = merchantAccountMapper.getMerchantAccountByMerchantId(
                checkPasswordDTO.getMerchantId());
        //获取商户服务费费率
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(checkPasswordDTO.getMerchantId());
        Cooperation rCooperation = cooperationMapper.selectOne(cooperation);


        //添加批量代付记录
        BatchPaymentRecord batchPaymentRecord = new BatchPaymentRecord();
        batchPaymentRecord.setBatchNo(batchPaymentSummaryDTO.getBatchNo());
        batchPaymentRecord.setMerchantId(merchantId);
        batchPaymentRecord.setPaymentActualAmount(BigDecimal.ZERO);
        batchPaymentRecord.setPaymentAmount(BigDecimal.valueOf(batchPaymentSummaryDTO.getAmount()));
        batchPaymentRecord.setPaymentChannel(checkPasswordDTO.getPaymentChannel());
        batchPaymentRecord.setPaymentCount(batchPaymentSummaryDTO.getCount());
        batchPaymentRecord.setTrxExternalNo(ChanPayUtil.generateOutTradeNo());
        batchPaymentRecord.setStatus(BatchPaymentRecordStatusEnum.TODO.code);
        batchPaymentRecord.setOperator(merchantInfo.getMerchantName());
        batchPaymentRecord.setCreater(merchantInfo.getMerchantName());
        batchPaymentRecord.setCreateTime(DateUtil.date());
        batchPaymentRecord.setUpdateTime(DateUtil.date());


        String batchNo = batchPaymentRecord.getBatchNo();
        // idCardNoAmount 统一批次同人打款金额集合 key-证件号 value 合计金额
        Map<String, BigDecimal> idCardNoAmount = new HashMap<>();
        Set<String> hasMakeUpRecord = new HashSet<>();
        BigDecimal sumTotalAmount = BigDecimal.ZERO;
        //发送到队列的集合
        List<PayRequestData> payRequestDataList = new ArrayList<>();

        //错误消息
        List<PromotionBatchErrorResultDTO> promotionBatchErrorResultDTOS = new ArrayList<>();
        boolean isFail = Boolean.FALSE;
        for (int begin = 0; begin < batchPaymentSummaryDTO.getCount(); begin++) {
            List<Object> objects = detailRead.get(begin);
            StringBuilder sb = new StringBuilder();
            PaymentInfoDTO dto = new PaymentInfoDTO();
            if (objects.get(0) != null && StringUtils.isNotEmpty(objects.get(0).toString())) {
                dto.setMerchantTrxOrderNo(objects.get(0).toString());
                Integer num = trxOrderMapper.getCountByMerchantSerialNum(dto.getMerchantTrxOrderNo(), merchantId);
                if (num > 0) {
                    sb.append("商户订单号不能重复,");
//                    throw new BusinessException("商户订单号不能重复");
                }
            }

            if (objects.get(1) == null || "".equals(objects.get(1).toString().replace(" ", ""))) {
                sb.append("姓名栏为空,");
//                throw new BusinessException("第" + begin + 1 + "条记录,姓名栏为空，请填写后再上传");
            }
            if (!regexName(objects.get(1).toString().replace(" ", ""))) {
                sb.append("姓名栏填写错误,");
//                throw new BusinessException("第" + begin + 1 + "条记录,姓名栏为空，请填写后再上传");
            }
            if (objects.get(2) == null || "".equals(objects.get(2).toString().replace(" ", ""))) {
               sb.append("身份证号栏为空,");
//                throw new BusinessException("姓名为" + objects.get(1).toString() + "的信息,身份证号栏为空，请填写后再上传");
            }

            if (!IdcardUtil.isValidCard(objects.get(2).toString().replace(" ", ""))) {
                logger.info("身份证号{}填写错误,姓名{}", objects.get(2), objects.get(1));
               sb.append("身份证号错误,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,身份证号错误,请重新填写");
            }
            if (objects.get(3) == null || "".equals(objects.get(3).toString().replace(" ", ""))) {
               sb.append("收款银行卡栏为空,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,收款银行卡栏为空,请填写后再上传");
            }
            if (!regexBankNo(objects.get(3).toString().replace(" ", ""))) {
              sb.append("收款账号错误,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息，收款账号错误，请重新填写");
            }
            if (objects.get(4) == null || "".equals(objects.get(4).toString().replace(" ", ""))) {
              sb.append("银行卡预留手机号栏为空,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,银行卡预留手机号栏为空,请填写后再上传");
            }
            if (!regexPhoneNo(objects.get(4).toString().replace(" ", ""))) {
                sb.append("银行卡预留手机号填写错误,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的用户,银行卡预留手机号填写错误");
            }

            if (objects.get(5) == null || "".equals(objects.get(5).toString().replace(" ", ""))) {
               sb.append("打款金额栏为空,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,打款金额栏为空,请填写后再上传");
            }
            String amount = new DecimalFormat("0.00").format(objects.get(5));
            if (!regexBigDecimal(amount)) {
                logger.info("姓名：{},打款金额填写错误{}", objects.get(1), objects.get(5));
              sb.append("打款金额错误,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息，打款金额错误，请重新填写");
            }
            sumTotalAmount = sumTotalAmount.add(new BigDecimal(amount));
            if (objects.get(6) == null || "".equals(objects.get(6).toString().replace(" ", ""))) {
              sb.append("省栏为空,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,省栏为空,请填写后再上传");
            }
            if (objects.get(7) == null || "".equals(objects.get(7).toString().replace(" ", ""))) {
                sb.append("市栏为空,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,市栏为空,请填写后再上传");
            }
            if (objects.get(8) == null || "".equals(objects.get(8).toString().replace(" ", ""))) {
                sb.append("区栏为空,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,区栏为空,请填写后再上传");
            }
            if (objects.get(9) == null || "".equals(objects.get(9).toString().replace(" ", ""))) {
                sb.append("街道栏为空,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,街道栏为空,请填写后再上传");
            }
            if (objects.get(10) == null || "".equals(objects.get(10).toString().replace(" ", ""))) {
                sb.append("社区栏为空,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,社区栏为空,请填写后再上传");
            }
            if (objects.get(11) == null || "".equals(objects.get(11).toString().replace(" ", ""))) {
                sb.append("数量栏为空,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,数量栏为空,请填写后再上传");
            }
            if (!regexNumber(objects.get(11).toString().replace(" ", ""))) {
                sb.append("数量填写错误,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,数量填写错误");
            }
            if (objects.get(12) == null || "".equals(objects.get(12).toString().replace(" ", ""))) {
                sb.append("单价栏为空,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,单价栏为空,请填写后再上传");
            }
            String price = new DecimalFormat("0.00").format(objects.get(12));
            if (!regexBigDecimal(price)) {
                sb.append("单价填写错误,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,单价填写错误");
            }
            if (objects.get(13) == null || "".equals(objects.get(13).toString().replace(" ", ""))) {
                sb.append("推广开始时间栏为空,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,推广开始时间栏为空,请填写后再上传");
            }
            DateTime startDateTime = null;
            if (!regexDate(objects.get(13).toString())) {
                sb.append("推广开始时间栏填写错误,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,推广开始时间栏填写错误");
            }else{
                 startDateTime = DateUtil.parse(objects.get(13).toString());
            }
            if (objects.get(14) == null || "".equals(objects.get(14).toString().replace(" ", ""))) {
                sb.append("推广结束时间栏为空,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,推广结束时间栏为空,请填写后再上传");
            }
            DateTime endDateTime = null;
            if (!regexDate(objects.get(14).toString())) {
                sb.append("推广结束时间栏填写错误,");
//                throw new BusinessException("姓名为" + objects.get(1) + "的信息,推广结束时间栏填写错误");
            }else{
                endDateTime = DateUtil.parse(objects.get(14).toString());
            }




            if (startDateTime !=null && endDateTime != null &&
                    endDateTime.before(startDateTime)) {
                sb.append("推广开始时间不能晚于开始结束时间,");
//                throw new BusinessException("推广开始时间不能晚于开始结束时间");
            }

            dto.setName(objects.get(1).toString().trim());
            dto.setIdCardNo(objects.get(2).toString().trim());
            dto.setBankNo(objects.get(3).toString().trim());
            dto.setMobile(objects.get(4).toString().trim());
            dto.setSettleAmount(objects.get(5).toString().trim());
            BigDecimal mouldAmount = BigDecimal.valueOf(Double.parseDouble(dto.getSettleAmount()));
//            if (mouldAmount.compareTo(BigDecimal.ZERO) < 0) {
//                throw new BusinessException("金额不能为负");
//            }
            dto.setProvince(objects.get(6).toString().trim());
            dto.setCity(objects.get(7).toString().trim());
            dto.setArea(objects.get(8).toString().trim());
            dto.setStreet(objects.get(9).toString().trim());
            dto.setCommunity(objects.get(10).toString().trim());
            dto.setQuantity(Integer.parseInt(objects.get(11).toString().trim()));
            BigDecimal quantity = new BigDecimal(dto.getQuantity());
            dto.setUnitPrice(new BigDecimal(objects.get(12).toString().trim()));
            BigDecimal unitPrice = dto.getUnitPrice();
//            if (unitPrice.compareTo(BigDecimal.ZERO) < 0) {
//                throw new BusinessException("单价不能为负");
//            }
//            if (!(mouldAmount.compareTo(quantity.multiply(unitPrice)) == 0)) {
//                throw new BusinessException("单价*数量=金额,请不要强制修改模板");
//            }

            // excel 模板的日期格式为 yyyy-MM-DD hh:mm:ss 修改为 yyyy-MM-DD
            dto.setPromotionStartTime(objects.get(13).toString().trim().length()>=10?objects.get(13).toString().trim().substring(0, 10):"");
            dto.setPromotionEndTime(objects.get(14).toString().trim().length() >=10?objects.get(14).toString().trim().substring(0, 10):"");

            dto.setMerchantId(checkPasswordDTO.getMerchantId());
            dto.setPaymentChannel(checkPasswordDTO.getPaymentChannel());
            dto.setAccountId(checkPasswordDTO.getAccountId());
            dto.setServerId(checkPasswordDTO.getServerId());

            // 加入单笔限额
            if (PaymentConstants.SINGLE_MAX_QUOTA.compareTo(mouldAmount) < 0) {
                sb.append("单笔打款超过限额,");
//                throw new BusinessException("该收款人" + dto.getIdCardNo() + "单笔打款超过限额"
//                        + PaymentConstants.SINGLE_MAX_QUOTA.toPlainString());
            }
            if (idCardNoAmount.containsKey(dto.getIdCardNo())) {
                idCardNoAmount.put(dto.getIdCardNo(), idCardNoAmount.get(dto.getIdCardNo()).add(mouldAmount));
            } else {
                idCardNoAmount.put(dto.getIdCardNo(), mouldAmount);
            }
            //查询单人每月收款限制
            SysConfig rSysConfig = getMonthlyLimitAmount(rCooperation.getBigRateSwitch());
            BigDecimal standardAmount = BigDecimal.valueOf(Double.parseDouble(rSysConfig.getConfigValue()));
            //查询单人当月当前税源地交易量 sumAmount
            String startDate = DateUtil.format(DateUtil.beginOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
            String endDate = DateUtil.format(DateUtil.endOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
            //计算历史打款额(内扣外扣)
//            sumAmount = trxOrderMapper.sumAmountByPayeeIdCardNo(String.valueOf(merchantInfo.getId()),
//                    dto.getIdCardNo(), startDate, endDate);
            Integer[] statusList = {-1, 0, 1};
            List<TrxOrder> historyTrxOrders = trxOrderMapper.selectByStatusInOneMonth(merchantInfo.getId(), null
                    , dto.getIdCardNo(), startDate, endDate, statusList, null);
            BigDecimal sumAmount = BigDecimal.ZERO;
            if (!org.springframework.util.CollectionUtils.isEmpty(historyTrxOrders)) {
                sumAmount = historyTrxOrders.stream().reduce(BigDecimal.ZERO
                        , (a, b) -> a.add(calTrxOrderMouldAmount(b)), (a, b) -> BigDecimal.ZERO);
            }
            if (standardAmount.compareTo(sumAmount.add(idCardNoAmount.get(dto.getIdCardNo()))) < 0) {
              sb.append("当月收款超过累计限额,");
//                throw new BusinessException("该收款人" + dto.getIdCardNo() + "当月收款超过累计限额"
//                        + rSysConfig.getConfigValue());
            }

            //大额费率判断
            dto.setUseBigRate(false);
            if (BigRateSwitchEnum.BIDRATEOPEN.getStatus().equals(rCooperation.getBigRateSwitch())) {
                //计算补交服务费总额和详细记录
                dto = makeUpChargeService.calculateMakeUpCharge(dto, merchantId, rCooperation, idCardNoAmount
                        , hasMakeUpRecord);
                if (null != dto.getMakeUpCharge()) {
                    hasMakeUpRecord.add(dto.getIdCardNo());
                    //把之前模板中的记录也标为大额打款
                    for (PaymentInfoDTO one : paymentInfoDTOList) {
                        if (!dto.getIdCardNo().equals(one.getIdCardNo())) {
                            continue;
                        }
                        one.setUseBigRate(true);
                        BigDecimal thisOneMouldAmount = BigDecimal.valueOf(Double.parseDouble(one.getSettleAmount()));
                        BigDecimal newTax = makeUpChargeService.calculateTax(thisOneMouldAmount
                                , rCooperation.getSingleServiceBigRate());
                        one.setTax(newTax.toString());
                    }
                }
            }
            BigDecimal tax, rate;
            rate = dto.getUseBigRate() ? rCooperation.getSingleServiceBigRate() : rCooperation.getSingleServiceRate();
            tax = makeUpChargeService.calculateTax(mouldAmount, rate);
            dto.setTax(tax.toString());

            paymentInfoDTOList.add(dto);
            //校验后数据入库
            PayRequestData payRequestData = new PayRequestData();

            payRequestData.setMerchantId(batchPaymentRecord.getMerchantId());
            payRequestData.setAmount(new BigDecimal(dto.getSettleAmount()));
            payRequestData.setCharge(new BigDecimal(dto.getTax()));
            payRequestData.setInOutDeduction(rCooperation.getInOrOutDeduction());
            payRequestData.setMerchantSerialNum(dto.getMerchantTrxOrderNo());
            payRequestData.setName(dto.getName());
            payRequestData.setMobile(dto.getMobile());
            payRequestData.setBankCard(dto.getBankNo());
            payRequestData.setIdCardNo(dto.getIdCardNo());
            payRequestData.setPaymentChannel(dto.getPaymentChannel());
            payRequestData.setRemark(dto.getRemark());
            payRequestData.setComment(dto.getComment());
            payRequestData.setPromotionStatus(PromotionEnum.IS_PROMOTION.getCode());
            payRequestData.setProvince(dto.getProvince());
            payRequestData.setCity(dto.getCity());
            payRequestData.setArea(dto.getArea());
            payRequestData.setStreet(dto.getStreet());
            payRequestData.setCommunity(dto.getCommunity());
            payRequestData.setQuantity(dto.getQuantity());
            payRequestData.setUnitPrice(dto.getUnitPrice());
            payRequestData.setPromotionStartTime(dto.getPromotionStartTime());
            payRequestData.setPromotionEndTine(dto.getPromotionEndTime());
            payRequestDataList.add(payRequestData);
            PromotionBatchErrorResultDTO promotionBatchErrorResultDTO = new PromotionBatchErrorResultDTO();
            BeanUtils.copyProperties(payRequestData,promotionBatchErrorResultDTO);
            if(!"".equals(sb.toString())){
                isFail = Boolean.TRUE;
                promotionBatchErrorResultDTO.setCheckResult("失败");
                promotionBatchErrorResultDTO.setComment(sb.toString());
                promotionBatchErrorResultDTOS.add(promotionBatchErrorResultDTO);
            }

        }

        if (sumTotalAmount.compareTo(new BigDecimal(totalAmount)) != 0) {
            throw new Exception("总金额/总条数错误，请检查后上传");
        }
        //抛出所有的错误信息
        if (isFail){

            Map<String,String> map = new HashMap<>();
            map.put("batchNo",batchNo);
            map.put("batchErrorResultDTOS",
                    JSON.toJSONString(promotionBatchErrorResultDTOS, SerializerFeature.WriteMapNullValue));
            String key = merchantId + batchNo + System.currentTimeMillis();
            map.put("key",key);
            Page<Object> page = new Page<>(1, 10, promotionBatchErrorResultDTOS.size());
            map.put("totalPage",page.getTotalPage()+"");
            map.put("totalRecord",promotionBatchErrorResultDTOS.size()+"");
            RedisUtil.put(key,map,RedisUtil.ONE,TimeUnit.HOURS);

            Map<String,Object> resultMap = new HashMap<>(map);
            if(promotionBatchErrorResultDTOS.size()<10){
                resultMap.put("batchErrorResultDTOS",promotionBatchErrorResultDTOS.subList(0,promotionBatchErrorResultDTOS.size()));
            }else{
                resultMap.put("batchErrorResultDTOS",promotionBatchErrorResultDTOS.subList(0,10));
            }
            return resultMap;
        }
        //数据入库
        batchPaymentRecordMapper.insertSelective(batchPaymentRecord);
        for(PayRequestData payRequestData:payRequestDataList){
            payRequestData.setBatchPaymentRecordId(batchPaymentRecord.getId());
            payRequestDataMapper.insertSelective(payRequestData);
        }

        //所有校验完成后发送消息到队列所在接口
        String info = HttpUtils.sendPost(PAYMENT_DATACHECK_URL, JSON.toJSONString(payRequestDataList));
        logger.info("推广列表-发送消息到队列所在接口返回的结果：{}", info);
        //将模板中同一个人的打款统计到补交服务费记录中去
//        for (PaymentInfoDTO one : paymentInfoDTOList) {
//            if (null != one.getMakeUpCharge() && idCardNoAmount.containsKey(one.getIdCardNo())) {
//                BigDecimal thisModePayment = idCardNoAmount.get(one.getIdCardNo());
//                MakeUpCharge makeUpCharge = one.getMakeUpCharge();
//                makeUpCharge.setMonthPayAmount(makeUpCharge.getMonthPayAmount().add(thisModePayment));
//                one.setMakeUpCharge(makeUpCharge);
//            }
//        }
        //将记录存放到redis中
//        RedisUtil.put(merchantInfo.getMerchantCode(), batchPaymentRecord, RedisUtil.TEN, TimeUnit.MINUTES);
//        RedisUtil.put(merchantInfo.getMerchantCode() + batchNo, paymentInfoDTOList,
//                RedisUtil.TEN, TimeUnit.MINUTES);
        //将批次号放到redis中
        RedisUtil.put(Constants.PAYMENT_FLAG + merchantId, batchPaymentRecord.getBatchNo(), RedisUtil.ONEDAY, TimeUnit.MINUTES);
        Map<String, String> map = new HashMap<>(2);
        map.put("merchantId", merchantId.toString());
        map.put("batchNo", batchPaymentRecord.getBatchNo());
//        map.put("batchNo",batchNo);
        return map;
    }

    /**
     * 获取单人单月累计发佣限额配置信息
     *
     * @param bigRateSwitch 大额开关值
     * @return sys_config 信息
     */
    private SysConfig getMonthlyLimitAmount(Integer bigRateSwitch) {
        String monthlyLimitAmountKey = ConfigUtil.getMonthlyLimitAmountKey(bigRateSwitch);
        SysConfig sysConfig = sysConfigMapper.checkConfigKeyUnique(monthlyLimitAmountKey);
        return sysConfig;
    }

    private PaymentResultDTO commonUse(Boolean useAlipay, CallBackDTO callBackDTO) {
        PaymentResultDTO resultDTO = new PaymentResultDTO();
        // 打款业务开关验证
        boolean flag = getPaymentAllowFlag();
        if (!flag) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("服务器维护中，请稍后再试");
            return resultDTO;
        }
        resultDTO = checkParam(callBackDTO);
        if (StringUtils.isEmpty(callBackDTO.getAmount())) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("请求金额不能为空");
            return resultDTO;
        }
        if (BigDecimal.valueOf(Double.valueOf(callBackDTO.getAmount())).compareTo(BigDecimal.ZERO) <= 0) {
            resultDTO.setCode(ResultEnum.TOTAL_FEE_LESSEQUAL_ZERO.name());
            resultDTO.setDescription(ResultEnum.TOTAL_FEE_LESSEQUAL_ZERO.description);
            return resultDTO;
        }
        if (useAlipay && StringUtils.isEmpty(callBackDTO.getAccountNo())) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("请求支付宝号不能为空");
            return resultDTO;
        }
        if (!useAlipay && StringUtils.isEmpty(callBackDTO.getBankCardNo())) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("请求银行卡号不能为空");
            return resultDTO;
        }
        if (StringUtils.isEmpty(callBackDTO.getMobileNo())) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("请求预留手机号不能为空");
            return resultDTO;
        }
        if (StringUtils.isEmpty(callBackDTO.getIdCardNo())) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("请求身份证号不能为空");
            return resultDTO;
        }
        if (StringUtils.isEmpty(callBackDTO.getName())) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("请求名字不能为空");
            return resultDTO;
        }
        if (!StringUtils.isEmpty(callBackDTO.getMemo())) {
            if (callBackDTO.getMemo().length() > 64) {
                resultDTO.setCode(ResultEnum.FAIL.name());
                resultDTO.setDescription("备注信息不可以超过64位");
            }
            return resultDTO;
        }
        return resultDTO;
    }

    private PaymentResultDTO commonVerifySign(PaymentResultDTO resultDTO, String data
            , MerchantInfo merchantInfo, CallBackDTO callBackDTO) {
        //查询商户是否存在
        if (merchantInfo == null) {
            resultDTO.setCode(ResultEnum.UNKOWN_MERCHANT.name());
            resultDTO.setDescription(ResultEnum.UNKOWN_MERCHANT.description);
            return resultDTO;
        }
        MerchantKey merchantKey = merchantKeyMapper.getMerchantKeyByMerchantId(merchantInfo.getId());
        if (!callBackDTO.getDesKey().equals(merchantKey.getDesKey())) {
            resultDTO.setCode(ResultEnum.ERROR_APP_KEY.name());
            resultDTO.setDescription(ResultEnum.ERROR_APP_KEY.description);
            return resultDTO;
        }
        Integer num = trxOrderMapper.getCountByMerchantSerialNum(callBackDTO.getOrderId(), merchantInfo.getId());
        if (num > 0) {
            resultDTO.setCode(ResultEnum.DUPLICATE_REQUEST_NO.name());
            resultDTO.setDescription(ResultEnum.DUPLICATE_REQUEST_NO.description);
            return resultDTO;
        }
        Map map = JSON.parseObject(data);
        map.remove("sign");
        map.remove("signType");
        map.remove("desKey");
        TreeMap<String, Object> treeMap = new TreeMap<>(map);
        String sign = MmtaxSign.signByMap(merchantKey.getAppKey(), treeMap, BaseConstant.CHARSET);
        //校验签名
//        if (org.springframework.util.StringUtils.isEmpty(sign) || !sign.equals(callBackDTO.getSign())) {
//            resultDTO.setCode(ResultEnum.ILLEGAL_SIGN.name());
//            resultDTO.setDescription(ResultEnum.ILLEGAL_SIGN.description);
//            return resultDTO;
//        }
        return resultDTO;
    }

    @Override
    public PaymentResultDTO getResultAlipay(HttpServletRequest request) throws Exception {
        String data = IOUtils.toString(request.getInputStream(), BaseConstant.CHARSET);
        logger.info("请求参数data:{}", JSON.toJSON(data));
        CallBackDTO callBackDTO = JSONObject.parseObject(data, CallBackDTO.class);
        PaymentResultDTO resultDTO = commonUse(true, callBackDTO);
        if (!resultDTO.getCode().equals(ResultEnum.SUCCESS.name())) {
            return resultDTO;
        }
        MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfoByCode(callBackDTO.getMerchantNo());
        resultDTO = commonVerifySign(resultDTO, data, merchantInfo, callBackDTO);
        if (!resultDTO.getCode().equals(ResultEnum.SUCCESS.name())) {
            return resultDTO;
        }
        //获取商户服务费费率
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(merchantInfo.getId());
        Cooperation rCooperation = cooperationMapper.selectOne(cooperation);
        //判断是天津渠道还是云众包渠道
        int orderChannel = getMerchantChannel(merchantInfo.getId());
        PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO();
        if (orderChannel == 1 || orderChannel == 2) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("该税源地商户不支持支付宝渠道打款");
            return resultDTO;
        }

        paymentInfoDTO.setMemo(callBackDTO.getMemo());
        paymentInfoDTO.setSettleAmount(callBackDTO.getAmount().trim());
        paymentInfoDTO.setPaymentChannel(PaymentEnum.ALIPAY.name().trim());
        paymentInfoDTO.setMerchantId(merchantInfo.getId());
        paymentInfoDTO.setBankNo(callBackDTO.getAccountNo().trim());
        paymentInfoDTO.setIdCardNo(callBackDTO.getIdCardNo().toUpperCase().trim());
        paymentInfoDTO.setMobile(callBackDTO.getMobileNo().trim());
        paymentInfoDTO.setMerchantTrxOrderNo(callBackDTO.getOrderId().trim());
        paymentInfoDTO.setName(callBackDTO.getName().trim());
        //判断使用大额还是普通
        BigDecimal mouldAmount = BigDecimal.valueOf(Double.parseDouble(paymentInfoDTO.getSettleAmount()));
        paymentInfoDTO.setUseBigRate(false);
        if (BigRateSwitchEnum.BIDRATEOPEN.getStatus().equals(rCooperation.getBigRateSwitch())) {
            Map<String, BigDecimal> idcardNoAmount = new HashMap<>(2);
            idcardNoAmount.put(paymentInfoDTO.getIdCardNo(), mouldAmount);
            paymentInfoDTO = makeUpChargeService.calculateMakeUpCharge(paymentInfoDTO, merchantInfo.getId(), rCooperation
                    , idcardNoAmount, null);
        }
        BigDecimal tax, rate;
        if (paymentInfoDTO.getUseBigRate()) {
            rate = rCooperation.getSingleServiceBigRate();
        } else {
            rate = rCooperation.getSingleServiceRate();
        }
        tax = makeUpChargeService.calculateTax(mouldAmount, rate);
        paymentInfoDTO.setTax(tax.toString());
        String result;
        if (!paymentInfoDTO.getUseBigRate()) {
            result = singlePaymentOfOnline(paymentInfoDTO, null, false, null, null);
        } else {
            result = singlePaymentOfOnlineByBigRate(paymentInfoDTO, null, false, null, null);
        }

        if (!ResultEnum.SUCCESS.name().equals(result)) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription(result);
        }
        return resultDTO;
    }

    @Override
    public PaymentResultDTO getResult(HttpServletRequest request) throws Exception {
        String data = IOUtils.toString(request.getInputStream(), BaseConstant.CHARSET);
        logger.info("请求参数data:{}", JSON.toJSON(data));
        CallBackDTO callBackDTO = JSONObject.parseObject(data, CallBackDTO.class);
        PaymentResultDTO resultDTO = commonUse(false, callBackDTO);
        if (!resultDTO.getCode().equals(ResultEnum.SUCCESS.name())) {
            return resultDTO;
        }
        MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfoByCode(callBackDTO.getMerchantNo());
        resultDTO = commonVerifySign(resultDTO, data, merchantInfo, callBackDTO);
        if (!resultDTO.getCode().equals(ResultEnum.SUCCESS.name())) {
            return resultDTO;
        }
        //判断是天津渠道还是云众包渠道
        int orderChannel = getMerchantChannel(merchantInfo.getId());
        PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO();
        //获取商户服务费费率
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(merchantInfo.getId());
        Cooperation rCooperation = cooperationMapper.selectOne(cooperation);
        if (orderChannel == 1) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("通道已关闭");
            return resultDTO;
        } else if (orderChannel == 2) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("通道已关闭");
            return resultDTO;
        }

        paymentInfoDTO.setSettleAmount(callBackDTO.getAmount().trim());
        paymentInfoDTO.setPaymentChannel(PaymentEnum.BANK.name().trim());
        paymentInfoDTO.setMerchantId(merchantInfo.getId());
        paymentInfoDTO.setBankNo(callBackDTO.getBankCardNo().trim());
        paymentInfoDTO.setIdCardNo(callBackDTO.getIdCardNo().toUpperCase().trim());
        paymentInfoDTO.setMobile(callBackDTO.getMobileNo().trim());
        paymentInfoDTO.setMerchantTrxOrderNo(callBackDTO.getOrderId().trim());
        paymentInfoDTO.setName(callBackDTO.getName().trim());
        //判断使用大额还是普通
        BigDecimal mouldAmount = BigDecimal.valueOf(Double.parseDouble(paymentInfoDTO.getSettleAmount()));
        paymentInfoDTO.setUseBigRate(false);
        if (BigRateSwitchEnum.BIDRATEOPEN.getStatus().equals(rCooperation.getBigRateSwitch())) {
            Map<String, BigDecimal> idcardNoAmount = new HashMap<>(2);
            idcardNoAmount.put(paymentInfoDTO.getIdCardNo(), mouldAmount);
            paymentInfoDTO = makeUpChargeService.calculateMakeUpCharge(paymentInfoDTO, merchantInfo.getId(), rCooperation
                    , idcardNoAmount, null);
        }
        BigDecimal tax, rate;
        if (paymentInfoDTO.getUseBigRate()) {
            rate = rCooperation.getSingleServiceBigRate();
        } else {
            rate = rCooperation.getSingleServiceRate();
        }
        tax = makeUpChargeService.calculateTax(mouldAmount, rate);
        paymentInfoDTO.setTax(tax.toString());
        String result;
        if (!paymentInfoDTO.getUseBigRate()) {
            result = singlePaymentOfOnline(paymentInfoDTO, null, false, null, null);
        } else {
            result = singlePaymentOfOnlineByBigRate(paymentInfoDTO, null, false, null, null);
        }

        if (!ResultEnum.SUCCESS.name().equals(result)) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription(result);
        }
        return resultDTO;
    }

    @Override
    public NotifyTrxOrderDTO getOrderInfo(HttpServletRequest request) throws IOException {
        NotifyTrxOrderDTO resultDTO = new NotifyTrxOrderDTO();
        PaymentResultDTO paymentResultDTO = new PaymentResultDTO();
        String data = IOUtils.toString(request.getInputStream(), BaseConstant.CHARSET);
        logger.info("请求参数data:{}", JSON.toJSON(data));
        NotifyQueryOrderInfoDTO orderInfoDTO = JSONObject.parseObject(data, NotifyQueryOrderInfoDTO.class);
        paymentResultDTO = checkParam(orderInfoDTO);
        if (!ResultEnum.SUCCESS.name().equals(paymentResultDTO.getCode())) {
            resultDTO.setCode(paymentResultDTO.getCode());
            resultDTO.setDescription(paymentResultDTO.getDescription());
            return resultDTO;
        }
        BeanUtil.copyProperties(paymentResultDTO, resultDTO);
        //查询商户是否存在
        MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfoByCode(orderInfoDTO.getMerchantNo());
        if (merchantInfo == null) {
            resultDTO.setCode(ResultEnum.UNKOWN_MERCHANT.name());
            resultDTO.setDescription(ResultEnum.UNKOWN_MERCHANT.description);
            return resultDTO;
        }

        MerchantKey merchantKey = merchantKeyMapper.getMerchantKeyByMerchantId(merchantInfo.getId());
        if (!orderInfoDTO.getDesKey().equals(merchantKey.getDesKey())) {
            resultDTO.setCode(ResultEnum.ERROR_APP_KEY.name());
            resultDTO.setDescription(ResultEnum.ERROR_APP_KEY.description);
            return resultDTO;
        }
        Map map = JSON.parseObject(data);
        map.remove("sign");
        map.remove("signType");
        map.remove("desKey");
        TreeMap<String, Object> treeMap = new TreeMap<>(map);
        String sign = MmtaxSign.signByMap(merchantKey.getAppKey(), treeMap, BaseConstant.CHARSET);
        //校验签名
//        if (StringUtils.isNotEmpty(sign) && !sign.equals(orderInfoDTO.getSign())) {
//            resultDTO.setCode(ResultEnum.ILLEGAL_SIGN.name());
//            resultDTO.setDescription(ResultEnum.ILLEGAL_SIGN.description);
//            return resultDTO;
//        }

        TrxOrder trxOrder = new TrxOrder();
        trxOrder.setMerchantSerialNum(orderInfoDTO.getQueryOrderNo());
        trxOrder.setMerchantId(merchantInfo.getId());
        TrxOrder resultTrxOrder = trxOrderMapper.selectByMerchantSerialNum(orderInfoDTO.getQueryOrderNo());
        if (resultTrxOrder == null) {
            resultDTO.setCode(ResultEnum.ORDER_NOT_EXIST.name());
            resultDTO.setDescription(ResultEnum.ORDER_NOT_EXIST.description);
            return resultDTO;
        }
        resultDTO.setAmount(resultTrxOrder.getAmount().toString());
        resultDTO.setBankCardNo(resultTrxOrder.getPayeeBankCard());
        resultDTO.setIdCardNo(resultTrxOrder.getPayeeIdCardNo());
        resultDTO.setMerchantNo(merchantInfo.getMerchantCode());
        resultDTO.setMobileNo(resultTrxOrder.getPayeeMobile());
        resultDTO.setName(resultTrxOrder.getPayeeName());
        resultDTO.setQueryOrderNo(resultTrxOrder.getMerchantSerialNum());
        resultDTO.setPaymentTime(DateUtil.format(resultTrxOrder.getUpdateTime(), DatePattern.NORM_DATETIME_PATTERN));
        resultDTO.setOrderTime(DateUtil.format(resultTrxOrder.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));
        resultDTO.setCharge(resultTrxOrder.getCharge().toString());
        resultDTO.setRemark(resultTrxOrder.getComment());
        resultDTO.setCode(ResultEnum.SUCCESS.name());
        resultDTO.setDescription(ResultEnum.SUCCESS.description);
        resultDTO.setOrderId(orderInfoDTO.getOrderId());
        resultDTO.setSign(orderInfoDTO.getSign());
        resultDTO.setTransTime(orderInfoDTO.getRequireTime());
        resultDTO.setOrderStatus(resultTrxOrder.getOrderStatus().toString());
        return resultDTO;
    }

    @Override
    public void checkPassword(CheckPasswordDTO checkPasswordDTO) {
        Integer merchantId = checkPasswordDTO.getMerchantId();
        //校验
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);
        boolean checkPasswordResult = EncrpytionUtil.checkPassword(merchantInfo.getMerchantCode(),
                checkPasswordDTO.getPassword(), merchantInfo.getPassword(), merchantInfo.getSalt());
        if (!checkPasswordResult) {
            throw new BusinessException("密码错误");
        }
    }

    @Override
    public PaymentBalanceDTO getBalance(HttpServletRequest request) throws Exception {
        PaymentBalanceDTO paymentBalanceDTO = new PaymentBalanceDTO();
        PaymentResultDTO paymentResultDTO = new PaymentBalanceDTO();
        String data = IOUtils.toString(request.getInputStream(), BaseConstant.CHARSET);
        logger.info("请求参数data:{}", JSON.toJSON(data));
        //获取返回参数
        CallBackDTO callBackDTO = JSONObject.parseObject(data, CallBackDTO.class);
        paymentResultDTO = checkParam(callBackDTO);
        BeanUtil.copyProperties(paymentResultDTO, paymentBalanceDTO);
        if (!paymentResultDTO.getCode().equals(ResultEnum.SUCCESS.name())) {
            return paymentBalanceDTO;
        }

        //查询商户是否存在
        MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfoByCode(callBackDTO.getMerchantNo());
        if (merchantInfo == null) {
            paymentBalanceDTO.setCode(ResultEnum.UNKOWN_MERCHANT.name());
            paymentBalanceDTO.setDescription(ResultEnum.UNKOWN_MERCHANT.description);
            return paymentBalanceDTO;
        }

        MerchantKey merchantKey = merchantKeyMapper.getMerchantKeyByMerchantId(merchantInfo.getId());
        if (!callBackDTO.getDesKey().equals(merchantKey.getDesKey())) {
            paymentBalanceDTO.setCode(ResultEnum.ERROR_APP_KEY.name());
            paymentBalanceDTO.setDescription(ResultEnum.ERROR_APP_KEY.description);
            return paymentBalanceDTO;
        }
        Map map = JSONObject.parseObject(data);
        map.remove("sign");
        map.remove("signType");
        map.remove("desKey");
        TreeMap<String, Object> treeMap = new TreeMap<>(map);
        String sign = MmtaxSign.signByMap(merchantKey.getAppKey(), treeMap, BaseConstant.CHARSET);
        //校验签名
//        if (!sign.equals(callBackDTO.getSign())) {
//            paymentBalanceDTO.setCode(ResultEnum.ILLEGAL_SIGN.name());
//            paymentBalanceDTO.setDescription(ResultEnum.ILLEGAL_SIGN.description);
//            return paymentBalanceDTO;
//        }
        //获取账户余额
        MerchantAccount merchantAccount = merchantAccountMapper.getMerchantAccountByMerchantId(merchantInfo.getId());
        paymentBalanceDTO.setBalance(merchantAccount.getUsableAmount().toString());
        return paymentBalanceDTO;
    }

    @Override
    public Map rechargeCalculate(BatchOrderDTO dto) {
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(dto.getMerchantId());
        cooperation = cooperationMapper.selectOne(cooperation);
        MerchantAccount merchantAccount = merchantAccountMapper.getMerchantAccountByMerchantId(dto.getMerchantId());
        //获取记录
        BatchPaymentRecord record = new BatchPaymentRecord();
        record.setMerchantId(dto.getMerchantId());
        record.setBatchNo(dto.getBatchNo());
        record.setConfirmStatus(CofirmStatusEnum.NO.getCode());
        record = batchPaymentRecordMapper.selectOne(record);
        //将代付详情计算出来
        List<PayRequestData> payRequestDatas = payRequestDataMapper.selectListPagination(record.getId()
                , null, null);
        List<PaymentInfoDTO> paymentInfoDTOS = bigRateDeal(payRequestDatas, dto.getMerchantId());
        InOutDeductionEnum type = InOutDeductionEnum.getByStatus(cooperation.getInOrOutDeduction());
        //计算模板中每个员工对应的总打款额和服务费start
        Map<String, PaymentInfoDTO> paymentInfoDTOMap = new HashMap<>();
        Map<String, BigDecimal> demoAmountMap = new HashMap<>();
        Map<String, BigDecimal> demoChargeMap = new HashMap<>();
        for (PaymentInfoDTO one : paymentInfoDTOS) {
            BigDecimal rate = one.getUseBigRate() ?
                    cooperation.getSingleServiceBigRate() : cooperation.getSingleServiceRate();
            BigDecimal mouldAmount = BigDecimal.valueOf(Double.parseDouble(one.getSettleAmount()));
            if (paymentInfoDTOMap.containsKey(one.getIdCardNo())) {
                BigDecimal monthPay = demoAmountMap.get(one.getIdCardNo()).add(mouldAmount);
                BigDecimal idcardNoCharge = demoChargeMap.get(one.getIdCardNo())
                        .add(makeUpChargeService.calculateTax(mouldAmount, rate));
                demoAmountMap.replace(one.getIdCardNo(), monthPay);
                demoChargeMap.replace(one.getIdCardNo(), idcardNoCharge);
                if (null != one.getMakeUpCharge()) {
                    paymentInfoDTOMap.replace(one.getIdCardNo(), one);
                }
            } else {
                paymentInfoDTOMap.put(one.getIdCardNo(), one);
                demoAmountMap.put(one.getIdCardNo(), mouldAmount);
                demoChargeMap.put(one.getIdCardNo(), makeUpChargeService.calculateTax(mouldAmount, rate));
            }
        }
        //计算模板中每个员工对应的总打款额和服务费end

        BigDecimal batchAmount = record.getPaymentAmount();
        BigDecimal charge = BigDecimal.ZERO;
        List<MakeUpChargeDTO> makeUpChargeDTOs = new ArrayList<>();
        for (PaymentInfoDTO one : paymentInfoDTOMap.values()) {
            charge = charge.add(demoChargeMap.get(one.getIdCardNo()));
            MakeUpChargeDTO makeUpChargeDTO = new MakeUpChargeDTO();
            makeUpChargeDTO.setCustomerName(one.getName());
            makeUpChargeDTO.setIdCardNo(one.getIdCardNo());
            if (null != one.getMakeUpCharge()) {
                List<MakeUpChargeDetail> makeUpChargeDetails = com.alibaba.fastjson.JSONArray.parseArray(
                        one.getMakeUpChargeDetails(), MakeUpChargeDetail.class);
                List<MakeUpChargeDetailDTO> detailDTOS = new ArrayList<>();
                MakeUpCharge makeUpCharge = one.getMakeUpCharge();
                makeUpChargeDetails.forEach(detail -> {
                    MakeUpChargeDetailDTO value = new MakeUpChargeDetailDTO();
                    value.setChargeMakeUp(detail.getChargeMakeUp());
                    value.setName(makeUpCharge.getCustomerName());
                    TrxOrder trxOrder = trxOrderMapper.selectByOrderSerialNum(detail.getOrderSerialNum());
                    value.setPaymentMount(calTrxOrderMouldAmount(trxOrder));
                    value.setCreateTime(DateUtil.formatDateTime(trxOrder.getCreateTime()));
                    detailDTOS.add(value);
                });
                makeUpChargeDTO.setMerchantName(makeUpCharge.getMerchantName());
                makeUpChargeDTO.setMonthPayAmount(makeUpCharge.getMonthPayAmount());
                makeUpChargeDTO.setAmount(makeUpCharge.getAmount());
                makeUpChargeDTO.setMakeUpChargeDetailDTOs(detailDTOS);
            } else {
                String startTime = DateUtil.formatDateTime(DateUtil.beginOfMonth(DateUtil.date()));
                String endTime = DateUtil.formatDateTime(DateUtil.endOfMonth(DateUtil.date()));
                Integer[] statusList = {-1, 0, 1};
                List<TrxOrder> historyTrxOrders = trxOrderMapper.selectByStatusInOneMonth(dto.getMerchantId()
                        , null, one.getIdCardNo(), startTime, endTime, statusList, null);
                BigDecimal allPayAmount = BigDecimal.ZERO;
                if (!org.springframework.util.CollectionUtils.isEmpty(historyTrxOrders)) {
                    allPayAmount = historyTrxOrders.stream().reduce(BigDecimal.ZERO
                            , (a, b) -> a.add(calTrxOrderMouldAmount(b)), (a, b) -> BigDecimal.ZERO);
                }
                makeUpChargeDTO.setMonthPayAmount(allPayAmount.add(demoAmountMap.get(one.getIdCardNo())));
                makeUpChargeDTO.setAmount(BigDecimal.ZERO);
            }
            makeUpChargeDTOs.add(makeUpChargeDTO);
        }
        BigDecimal makeUpChargeAmount = paymentInfoDTOS.stream().filter(v -> null != v.getMakeUpCharge())
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b.getMakeUpCharge().getAmount()), (a, b) -> BigDecimal.ZERO);
        BigDecimal allPaymentAmount;
        if (InOutDeductionEnum.OUTDEDUCTION.equals(type)) {
            allPaymentAmount = batchAmount.add(charge).add(makeUpChargeAmount);
        } else {
            allPaymentAmount = batchAmount.add(makeUpChargeAmount);
        }
        Map<String, Object> map = new HashMap<>(8);
        map.put("batchAmount", batchAmount);
        map.put("charge", charge);
        map.put("makeUpChargeAmount", makeUpChargeAmount);
        map.put("allPaymentAmount", allPaymentAmount);
        map.put("balance", merchantAccount.getUsableAmount());
        map.put("makeUpChargeList", makeUpChargeDTOs);
        return map;
    }

    private BigDecimal calTrxOrderMouldAmount(TrxOrder trxOrder) {
        BigDecimal rate = trxOrder.getTaxRate();
        InOutDeductionEnum inOutDeduction = InOutDeductionEnum.getByStatus(trxOrder.getInOutDeduction());
        if (UseBigRateEnum.BIGRATE.getStatus().equals(trxOrder.getUseBigRate())) {
            rate = trxOrder.getTrxRateBig();
            inOutDeduction = InOutDeductionEnum.getByStatus(trxOrder.getBigInOutDeduction());
        }
//        return makeUpChargeService.calculateMouldAmount(trxOrder.getActualAmount(),rate,inOutDeduction);
        return trxOrder.getAmount();
    }

    @Override
    public Map judgePaymentCache(Integer merchantId) {
        Map<String, Object> map = new HashMap<>(4);
        if (!RedisUtil.exists(Constants.PAYMENT_FLAG + merchantId)) {
            map.put("checkStatus",0);
            map.put("batchNo", null);
            map.put("confirmStatus", true);
            return map;
        }
        String batchNo = RedisUtil.get(Constants.PAYMENT_FLAG + merchantId);
        BatchPaymentRecord record = new BatchPaymentRecord();
        record.setMerchantId(merchantId);
        record.setBatchNo(batchNo);
        record = batchPaymentRecordMapper.selectOne(record);
        map.put("checkStatus",record.getCheckStatus());
        map.put("batchNo", batchNo);
        map.put("confirmStatus", 1 == record.getConfirmStatus());
        return map;
    }


    @Override
    public Page listPaymentDetail(Integer merchantId, String batchNo, Integer pageSize, Integer currentPage) {
        //获取商户信息
        BatchPaymentRecord record = new BatchPaymentRecord();
        record.setMerchantId(merchantId);
        record.setBatchNo(batchNo);
        record = batchPaymentRecordMapper.selectOne(record);
        List<PayRequestData> payRequestDatas = payRequestDataMapper.selectListPagination(
                record.getId(),null,null);
        List<PaymentInfoDTO> list = bigRateDeal(payRequestDatas,merchantId);
        int count = list.size();
        int allPage = count / pageSize;
        if (count % pageSize > 0) {
            allPage = allPage + 1;
        }
        int begin = (currentPage - 1) * pageSize;
        int end;
        if (currentPage != allPage) {
            end = begin + pageSize;
        } else {
            end = count;
        }
        return new Page(count, list.subList(begin, end), currentPage, pageSize);
    }

    @Override
    public Map summaryPaymentData(Integer merchantId,String batchNo) {
        BatchPaymentRecord record = new BatchPaymentRecord();
        record.setMerchantId(merchantId);
        record.setBatchNo(batchNo);
        record = batchPaymentRecordMapper.selectOne(record);
        List<PayRequestData> list = payRequestDataMapper.selectListPagination(
                record.getId(),null,null);
        BigDecimal allPaymentAmount = list.stream()
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b.getAmount()), (a, b) -> BigDecimal.ZERO);
        BigDecimal allTaxAmount = list.stream().filter(v->null != v.getCharge())
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b.getCharge()), (a, b) -> BigDecimal.ZERO);
        Map<String, Object> map = new HashMap<>(8);
        map.put("batchNo", record.getBatchNo());
        map.put("allNum", list.size());
        map.put("allPaymentAmount", allPaymentAmount);
        map.put("allTaxAmount", allTaxAmount);
        return map;
    }

    @Override
    public BatchPaymentRecord getRecord(Integer merchantId,String batchNo) {
        BatchPaymentRecord record = new BatchPaymentRecord();
        record.setMerchantId(merchantId);
        record.setBatchNo(batchNo);
        record.setConfirmStatus(CofirmStatusEnum.NO.getCode());
        record = batchPaymentRecordMapper.selectOne(record);
        PayRequestData payRequestData = new PayRequestData();
        payRequestData.setBatchPaymentRecordId(record.getId());
        payRequestData.setDelStatus(DelStatusEnum.NORMAL.code);
        record.setPaymentCount(payRequestDataMapper.selectCount(payRequestData));
        return record;
    }

    @Override
    public void cancelOrder(BatchOrderDTO dto) {
        //获取商户信息
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(dto.getMerchantId());
        //缓存中移除
        RedisUtil.remove(Constants.PAYMENT_FLAG+dto.getMerchantId());
        RedisUtil.remove(merchantInfo.getMerchantCode() + dto.getBatchNo());
        BatchPaymentRecord record = new BatchPaymentRecord();
        record.setMerchantId(dto.getMerchantId());
        record.setBatchNo(dto.getBatchNo());
        record.setConfirmStatus(CofirmStatusEnum.NO.getCode());
        record = batchPaymentRecordMapper.selectOne(record);
        if(BatchPayStatusEnum.NO.getCode() == record.getPayStatus()){
            record.setBatchNo(dto.getBatchNo()+"-"+"cancel");
            record.setConfirmStatus(CofirmStatusEnum.CANCEL.getCode());
            record.setPayStatus(BatchPayStatusEnum.CANCEL.getCode());
            record.setUpdateTime(DateUtil.date());
            record.setStatus(BatchPaymentRecordStatusEnum.CANCEL.code);
            batchPaymentRecordMapper.updateByPrimaryKeySelective(record);
        }
    }

    @Override
    public void payDataCheck(PayRequestData payRequestData) {
        int merchantId = payRequestData.getMerchantId();
        BatchPaymentRecord batchPaymentRecord = batchPaymentRecordMapper.selectByPrimaryKey(
                payRequestData.getBatchPaymentRecordId());
        String batchNo = batchPaymentRecord.getBatchNo();

        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(merchantId);
        cooperation = cooperationMapper.selectOne(cooperation);
        //校验二要素三要素
        Cooperation finalCooperation = cooperation;
        factorVerification(payRequestData, finalCooperation);

        String value = UUID.randomUUID().toString();
        String key = RedisLockConstans.MERCHANTID_BATCHNO_LOCK + merchantId + "_" + batchPaymentRecord.getBatchNo();

        Map<String, BigDecimal> idCardNoAmount = new HashMap<>(16);
        Set<String> hasMakeUpRecord = new HashSet<>();
        PaymentInfoDTO paymentInfoDTO;
        try {
            //锁住当前商户id和批次，固定锁10秒，自动释放
            logger.info("同一商户同一批次锁 key{}, start:{}", key,
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_MS_FORMAT));
            RedisLockUtil.getRedisLock(key, value, RedisTimeConstans.FIVE_MINUTE);
            logger.info("同一商户同一批次锁 key{}, end:{}", key,
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_MS_FORMAT));
            String idcardNoAmountKey = Constants.IDCARDNO_AMOUNT + merchantId + batchNo;
            if(RedisUtil.exists(idcardNoAmountKey)){
                idCardNoAmount = JSON.parseObject(RedisUtil.get(idcardNoAmountKey), Map.class);
            }
            String haveMakeUpKey = Constants.HAVE_MAKEUP + merchantId + batchNo;
            if(RedisUtil.exists(haveMakeUpKey)){
                hasMakeUpRecord = new HashSet<>(RedisUtil.getList(haveMakeUpKey,String.class));
            }
            //进行单笔限额判断和大额处理
            paymentInfoDTO = bigRateDealOne(payRequestData, merchantId, idCardNoAmount, hasMakeUpRecord);
        }finally {
            logger.info("释放锁");
            RedisUtil.releaseDistributedLock(key,value);
        }
        logger.info("计算完的paymentInfo:{}",JSON.toJSONString(paymentInfoDTO));

        //保存服务费
        PayRequestData rPayRequestData = payRequestDataMapper.selectByPrimaryKey(paymentInfoDTO.getPayRequestDataId());
        rPayRequestData.setCharge(new BigDecimal(paymentInfoDTO.getTax()));
        rPayRequestData.setUseBigRate(paymentInfoDTO.getUseBigRate() ? 1 : 0);
        rPayRequestData.setUpdateTime(DateUtil.date());
        payRequestDataMapper.updateByPrimaryKeySelective(rPayRequestData);

        //保存到缓存
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);
        if (BatchPayStatusEnum.CANCEL.getCode() == batchPaymentRecord.getPayStatus()) {
            logger.info("该批次{}已取消", payRequestData.getBatchPaymentRecordId());
            return;
        }

        value = UUID.randomUUID().toString();
        key = RedisLockConstans.MERCHANTID_BATCHNO_LOCK + batchPaymentRecord.getBatchNo() + "_" + merchantId;
        List<PaymentInfoDTO> paymentInfoDTOS;
        try {
            //锁住当前商户id和批次，固定锁10秒，自动释放
            logger.info("同一商户同一批次锁 key{}, start:{}", key,
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_MS_FORMAT));
            RedisLockUtil.getRedisLock(key, value, RedisTimeConstans.FIVE_MINUTE);
            logger.info("同一商户同一批次锁 key{}, end:{}", key,
                    DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_MS_FORMAT));
            paymentInfoDTOS = RedisUtil.getList(merchantInfo.getMerchantCode() + batchPaymentRecord.getBatchNo(),
                    PaymentInfoDTO.class);
            if (null == paymentInfoDTOS) {
                paymentInfoDTOS = new ArrayList<>();
            }
            if (null != paymentInfoDTO.getMakeUpCharge()){
                changeBeforePaymentInfoToBigRate(merchantId, paymentInfoDTO.getIdCardNo(), paymentInfoDTOS);
            }
            if(paymentInfoDTOS.contains(paymentInfoDTO)){
                paymentInfoDTOS.remove(paymentInfoDTO);
            }
            paymentInfoDTOS.add(paymentInfoDTO);
            //判断自动模式
            IPaymentService paymentService = (IPaymentService)AopContext.currentProxy();
            BatchPaymentRecord lastRecord = paymentService.getLastRecord(payRequestData.getBatchPaymentRecordId());
            if(MoneyModelEnum.AUTO.getStatus().equals(cooperation.getMoneyModel())
                    && CofirmStatusEnum.YES.getCode() == lastRecord.getConfirmStatus()
                    && payRequestDataMapper.getCountByStatus(merchantId,batchPaymentRecord.getBatchNo()) == 0){
                //发送到produce生产者
                logger.info("批次id={}开始在检验接口发送打款队列",lastRecord.getId());
                if(CollectionUtils.isEmpty(paymentInfoDTOS)){
                    logger.error("获取到的打款数据为空");
                }else {
                    try {
                        paymentInfoDTOS = calculateOvercharge(paymentInfoDTOS, batchPaymentRecord);
                    }catch (BusinessException e){
                        logger.info("自动打款计算补偿服务费时钱包金额不足，不启用补偿策略");
                    }
                }
                String content = JSON.toJSONString(paymentInfoDTOS);
                String callBackResult = HttpUtils.sendPost(PAYMENT_EXECUTE_URL, content);
                if (StringUtils.isEmpty(callBackResult) || !NotifyStatusEnum.SUCCESS.name().equals(callBackResult)) {
                    logger.error("自动打款失败，批次id{}",batchPaymentRecord.getId());
                }
                //发送完后移除缓存
                logger.info("移除批次id={}的打款数据缓存",lastRecord.getId());
                RedisUtil.remove(merchantInfo.getMerchantCode() + batchPaymentRecord.getBatchNo());
                return;
            }
            RedisUtil.put(merchantInfo.getMerchantCode() + batchPaymentRecord.getBatchNo()
                    , JSON.toJSONString(paymentInfoDTOS), RedisUtil.ONEDAY, TimeUnit.MINUTES);
        }finally {
            logger.info("释放锁");
            RedisUtil.releaseDistributedLock(key,value);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public BatchPaymentRecord getLastRecord(int id){
        return batchPaymentRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PaymentInfoDTO> calculateOvercharge(List<PaymentInfoDTO> list,BatchPaymentRecord record){
        //获取代付记录
        BigDecimal batchAmount = record.getPaymentAmount();
        Integer merchantId = record.getMerchantId();
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(merchantId);
        cooperation = cooperationMapper.selectOne(cooperation);
        BigDecimal tax = list.stream().
                reduce(BigDecimal.ZERO, (a, b) -> a.add(new BigDecimal(b.getTax())), (a, b) -> BigDecimal.ZERO);
        BigDecimal makeUpCharge = list.stream().filter(v -> null != v.getMakeUpCharge())
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b.getMakeUpCharge().getAmount()), (a, b) -> BigDecimal.ZERO);
        //获取钱包信息
        MerchantAccount merchantAccount = merchantAccountMapper.getMerchantAccountByMerchantId(merchantId);
        BigDecimal allNeedToPay;
        if (InOutDeductionEnum.OUTDEDUCTION.getStatus().equals(cooperation.getInOrOutDeduction())) {
            allNeedToPay = batchAmount.add(tax).add(makeUpCharge);
        } else {
            allNeedToPay = batchAmount.add(makeUpCharge);
        }
        //我方补点差额,写死最多1块钱
        BigDecimal acceptAmount = BigDecimal.valueOf(1);
        Map<String,BigDecimal> overCharge = new HashMap<>(4);
        BigDecimal allAmountCalCharge,overChargeAmount = BigDecimal.ZERO;
        //之后优化-todo
        if(BigRateSwitchEnum.BIDRATEOPEN.getStatus().equals(cooperation.getBigRateSwitch())
                && allNeedToPay.compareTo(merchantAccount.getUsableAmount()) > 0) {
            if(allNeedToPay.subtract(merchantAccount.getUsableAmount()).compareTo(acceptAmount) > 0){
                throw new BusinessException("钱包余额不足以支付本次批量代付！");
            }else {
                overChargeAmount = allNeedToPay.subtract(merchantAccount.getUsableAmount());
                overCharge.put(record.getBatchNo()+record.getMerchantId(),overChargeAmount);
            }
        }else if(!BigRateSwitchEnum.BIDRATEOPEN.getStatus().equals(cooperation.getBigRateSwitch())){
            allAmountCalCharge = record.getPaymentAmount().multiply(cooperation.getSingleServiceRate())
                    .setScale(2,RoundingMode.UP);
            overChargeAmount = tax.subtract(allAmountCalCharge).compareTo(acceptAmount) > 0
                    ?acceptAmount:tax.subtract(allAmountCalCharge);
            overCharge.put(record.getBatchNo()+record.getMerchantId(),overChargeAmount);
            if (allNeedToPay.subtract(overChargeAmount).compareTo(merchantAccount.getUsableAmount()) > 0) {
                throw new BusinessException("钱包余额不足以支付本次批量代付！");
            }
        }else{
            overCharge.put(record.getBatchNo()+record.getMerchantId(),BigDecimal.ZERO);
        }
        record.setOverCharge(overChargeAmount);
        batchPaymentRecordMapper.updateByPrimaryKeySelective(record);
        list.forEach(one->{
            BigDecimal allOverChargeAmount = overCharge.get(record.getBatchNo() + merchantId);
            logger.info("补偿总额{}",allOverChargeAmount);
            if(allOverChargeAmount.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal oneTax = new BigDecimal(one.getTax());
                BigDecimal oneNeedoverCharge = oneTax.compareTo(allOverChargeAmount) >= 0 ? allOverChargeAmount : oneTax;
                one.setOverChargeAmount(oneNeedoverCharge.toString());
                overCharge.put(record.getBatchNo() + merchantId,allOverChargeAmount.subtract(oneNeedoverCharge));
            }
        });
        return list;
    }

    @Override
    public void batchPayment(BatchOrderDTO dto) {
        BatchPaymentRecord record = new BatchPaymentRecord();
        record.setMerchantId(dto.getMerchantId());
        record.setBatchNo(dto.getBatchNo());
        //获取记录
        record = batchPaymentRecordMapper.selectOne(record);
        //判断重复通知
        PaymentInfoDTO paymentInfo = dto.getPaymentInfo();
//        if (BatchPayStatusEnum.YES.getCode() == record.getPayStatus()) {
//            logger.info("批次id={}已执行过打款", record.getId());
//            return;
//        }
        record.setConfirmStatus(null);
        if(BatchPayStatusEnum.NO.getCode() == record.getPayStatus()){
            record.setPayStatus(BatchPayStatusEnum.YES.getCode());
            batchPaymentRecordMapper.updateByPrimaryKeySelective(record);
        }
        logger.info("开始时间为:{}", System.currentTimeMillis());
//        for (PaymentInfoDTO paymentInfoDTO : list) {
        BatchPaymentRecord batchPaymentRecord = new BatchPaymentRecord();
        batchPaymentRecord.setId(record.getId());
        batchPaymentRecord.setStatus(BatchPaymentRecordStatusEnum.GOING.code);
        batchPaymentRecordMapper.updateByPrimaryKeySelective(batchPaymentRecord);

        if (!paymentInfo.getUseBigRate()) {
            //普通费率打款
            logger.info(DateUtil.now() + "record:{},resultBatchPaymentRecord:{}",record,record);
            singlePaymentOfOnline(paymentInfo, record, true, null,null);
        } else {
            //大额费率打款
            singlePaymentOfOnlineByBigRate(paymentInfo, record, true, null,null);
        }
//        }
        logger.info("结束时间为:{}", System.currentTimeMillis());
    }

    @Override
    public void paymentExecute(int merchantId, String batchNo) {
        // 打款业务开关验证
        boolean flag = getPaymentAllowFlag();
        if (!flag) {
            throw new BusinessException("服务器维护中，请稍后再试");
        }
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(merchantId);
        cooperation = cooperationMapper.selectOne(cooperation);
        //获取商户信息
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);
        BatchPaymentRecord batchPaymentRecord = new BatchPaymentRecord();
        batchPaymentRecord.setMerchantId(merchantId);
        batchPaymentRecord.setBatchNo(batchNo);
        batchPaymentRecord = batchPaymentRecordMapper.selectOne(batchPaymentRecord);
        if(MoneyModelEnum.NAMOL.getStatus().equals(cooperation.getMoneyModel())
                || payRequestDataMapper.getCountByStatus(merchantId,batchNo) == 0) {
            if(payRequestDataService.queryCheckOver(batchNo,merchantId)){
                throw new BusinessException("打款信息尚未校验完成，请等待");
            }
            logger.info("批次id={}的批次开始在确认锁定接口发送打款",batchPaymentRecord.getId());
            List<PaymentInfoDTO> list = getListPaymentInfoDTO(batchNo, merchantInfo.getMerchantCode());
            if(CollectionUtils.isEmpty(list)){
                logger.info("获取到的打款数据为空");
            }else {
                list = calculateOvercharge(list, batchPaymentRecord);
            }
            //发送到produce生产者
            String content = JSON.toJSONString(list);
            String callBackResult = HttpUtils.sendPost(PAYMENT_EXECUTE_URL, content);
            if (StringUtils.isEmpty(callBackResult) || !NotifyStatusEnum.SUCCESS.name().equals(callBackResult)) {
                throw new BusinessException("请等待打款信息校验完成");
            }
            logger.info("移除批次id={}的打款数据缓存",batchPaymentRecord.getId());
            RedisUtil.remove(merchantInfo.getMerchantCode() + batchNo);
        }
        //更新确认打款并移除缓存
        batchPaymentRecord.setConfirmStatus(CofirmStatusEnum.YES.getCode());
        batchPaymentRecord.setPayStatus(null);
        batchPaymentRecord.setUpdateTime(DateUtil.date());
        batchPaymentRecordMapper.updateByPrimaryKeySelective(batchPaymentRecord);
        RedisUtil.remove(Constants.PAYMENT_FLAG+merchantId);
    }

    @Override
    public MerchantBatchPaymentAmountDetailDTO getDetail(Integer merchantId,String batchNo) {
        if(StringUtils.isEmpty(batchNo)){
            throw new BusinessException("批次号为空，无法查询信息");
        }
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);
//        batchNo = RedisUtil.get(Constants.PAYMENT_FLAG+merchantId);
        logger.info("获取的数据：{}",batchNo);
        BatchPaymentRecord record = new BatchPaymentRecord();
        record.setMerchantId(merchantId);
        record.setBatchNo(batchNo);
        record = batchPaymentRecordMapper.selectOne(record);
        logger.info("打款的信息BatchPaymentRecord={}", JSON.toJSONString(record));
        MerchantBatchPaymentAmountDetailDTO detailDTO = new MerchantBatchPaymentAmountDetailDTO();
//        detailDTO.setSuccessfulOrdersNum(record.getPaymentCount());
//        detailDTO.setSuccessfulAmount(record.getPaymentAmount().toString());
        detailDTO.setProcessAndInitNum(0);
        detailDTO.setProcessAndInitAmount("0");
        //获取商户服务费费率
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(merchantId);
        Cooperation rCooperation = cooperationMapper.selectOne(cooperation);
        //扣税金额
        List<PayRequestData> list = payRequestDataMapper.selectListPagination(record.getId()
                ,null,null);
        logger.info("缓存数据list={}", JSON.toJSONString(list));
        BigDecimal taxRate = rCooperation.getSingleServiceRate();
        BigDecimal bigRate = rCooperation.getSingleServiceBigRate();
        BigDecimal tax = list.stream().filter(v->null != v.getCharge())
                .reduce(BigDecimal.ZERO, (a, b) -> a.add(b.getCharge()), (a, b) -> BigDecimal.ZERO);
        Long successCount = list.stream()
                .filter(p -> PayDataCheckStatusEnum.SUCESS.getStatus().equals(p.getStatus()))
                .filter(p -> DelStatusEnum.NORMAL.code.equals(p.getDelStatus()))
                .count();
        BigDecimal successAmounts = list.stream()
                .filter(p -> PayDataCheckStatusEnum.SUCESS.getStatus().equals(p.getStatus()))
                .filter(p -> DelStatusEnum.NORMAL.code.equals(p.getDelStatus()))
                .map(PayRequestData::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        detailDTO.setSuccessfulOrdersNum(successCount.intValue());
        detailDTO.setSuccessfulAmount(successAmounts.toString());
        detailDTO.setServiceAmount(String.valueOf(tax));
        detailDTO.setRiskOrdersNum(0);
        detailDTO.setRiskOrderAmount("0");
        detailDTO.setFailOrdersNum(0);
        detailDTO.setFailAmount("0");
        return detailDTO;
    }

    /**
     * 获取打款业务开启值 false 表示关闭 true 表示开启
     *
     * @return
     */
    private boolean getPaymentAllowFlag() {
        boolean result = true;
        SysConfig sysConfigPaymentAllow = new SysConfig();
        sysConfigPaymentAllow.setConfigKey(Constants.PAYMENT_ALLOW);
        sysConfigPaymentAllow = sysConfigMapper.selectConfig(sysConfigPaymentAllow);
        logger.info("sysConfigPaymentAllow configValue:{}", sysConfigPaymentAllow == null ? "" :
                sysConfigPaymentAllow.getConfigValue());
        if (null != sysConfigPaymentAllow && Constants.SWITCH_OFF.equals(sysConfigPaymentAllow.getConfigValue())) {
            result = false;
        }
        return result;
    }

    @Override
    public void hangingOrderPaid(ManagerTrxOrderDTO managerTrxOrderDTO) throws Exception {
        // 打款业务开关验证
        boolean flag = getPaymentAllowFlag();
        if (!flag) {
            throw new BusinessException("服务器维护中，请稍后再试");
        }
        //获取代付记录
        TrxOrder trxOrder = trxOrderMapper.selectByPrimaryKey(managerTrxOrderDTO.getId());
        PaymentInfoDTO paymentInfoDTO = new PaymentInfoDTO();
        paymentInfoDTO.setSettleAmount(trxOrder.getAmount().toString());
        paymentInfoDTO.setBankNo(trxOrder.getPayeeBankCard());
        paymentInfoDTO.setIdCardNo(trxOrder.getPayeeIdCardNo());
        paymentInfoDTO.setName(trxOrder.getPayeeName());
        paymentInfoDTO.setMerchantId(trxOrder.getMerchantId());
        paymentInfoDTO.setMerchantTrxOrderNo(ChanPayUtil.generateOutTradeNo());
        paymentInfoDTO.setPaymentChannel(trxOrder.getPaymentChannel());
        paymentInfoDTO.setMobile(trxOrder.getPayeeMobile());
        PaymentMerchantInfo paymentMerchantInfo =
                paymentMerchantInfoMapper.selectByMerchantIdAndChannel(trxOrder.getMerchantId(), PaymentChannelEnum.YUNZB.name());
        TianJinPaymentInfo tianJinPaymentInfo = tianJinPaymentInfoMapper.getTianJinPaymentInfoByMerchantId(trxOrder.getMerchantId());
        //此处判断商户是云众包还是天津，若后续还有，则继续添加判断
        if (paymentMerchantInfo != null) {
            singlePayment(paymentInfoDTO, null, false, managerTrxOrderDTO.getId());
        } else if (null != tianJinPaymentInfo) {
//            singleTianJinPayment(paymentInfoDTO, null, false, managerTrxOrderDTO.getId());
        } else {
            singlePaymentOfOnline(paymentInfoDTO, null, false, managerTrxOrderDTO.getId(),null);
        }
    }


    /**
     * 初始化返回值
     *
     * @param callBackDTO
     * @return
     */
    private PaymentResultDTO checkParam(CallBackDTO callBackDTO) {
        PaymentResultDTO resultDTO = new PaymentBalanceDTO();
        //初始化返回值
        resultDTO.setSign(callBackDTO.getSign());
        resultDTO.setTransTime(callBackDTO.getRequireTime());
        resultDTO.setOrderId(callBackDTO.getOrderId());
        resultDTO.setCode(ResultEnum.SUCCESS.name());
        resultDTO.setDescription(ResultEnum.SUCCESS.description);
        if (StringUtils.isEmpty(callBackDTO.getMerchantNo())) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("商户编号不能为空");
            return resultDTO;
        }
        if (StringUtils.isEmpty(callBackDTO.getSign())) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("签名不能为空");
            return resultDTO;
        }
        if (StringUtils.isEmpty(callBackDTO.getSignType())) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("签名类型不能为空");
            return resultDTO;
        }
        if (StringUtils.isEmpty(callBackDTO.getRequireTime())) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("请求时间不能为空");
            return resultDTO;
        }
        if (StringUtils.isEmpty(callBackDTO.getVersion())) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("版本号不能为空");
            return resultDTO;
        }
        if (StringUtils.isEmpty(callBackDTO.getOrderId())) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("订单编号不能为空");
            return resultDTO;
        }

        if (!Constants.VERSION.equals(callBackDTO.getVersion())) {
            resultDTO.setCode(ResultEnum.VERSION_ERROR.name());
            resultDTO.setDescription(ResultEnum.VERSION_ERROR.description);
            return resultDTO;
        }

        if (!callBackDTO.getSignType().equals(BaseConstant.CHARSET)) {
            resultDTO.setCode(ResultEnum.ILLEGAL_SIGN_TYPE.name());
            resultDTO.setDescription(ResultEnum.ILLEGAL_SIGN_TYPE.description);
            return resultDTO;
        }

        return resultDTO;
    }

    @Override
    @Deprecated
    public void batchTianJinPayment(BatchOrderDTO dto) throws Exception {
        //获取商户信息
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(dto.getMerchantId());
        //获取记录
        BatchPaymentRecord record = JSON.parseObject(RedisUtil.get(merchantInfo.getMerchantCode()),
                BatchPaymentRecord.class);
        batchPaymentRecordMapper.insertUseGeneratedKeys(record);
        //获取代付记录
        List<PaymentInfoDTO> list = getListPaymentInfoDTO(dto.getBatchNo(), merchantInfo.getMerchantCode());
        //批量代付
        for (PaymentInfoDTO paymentInfoDTO : list) {
//            singleTianJinPayment(paymentInfoDTO, record, true, null);
        }
    }

    @Override
    public RechargeAccountInfoDTO getAccountInfo(Integer merchantId) {
        if (merchantId == null) {
            throw new BusinessException("商户id不可为空");
        }
        RechargeAccountInfoDTO infoDTO = new RechargeAccountInfoDTO();
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
        if (onlinePaymentInfo == null) {
            return infoDTO;
        }
        infoDTO.setBankName(OnlineConstants.RECEIVING_BANK_NAME);
        Integer taxSourceId = onlinePaymentInfo.getTaxSourceCompanyId();
        OnlineAccountConfig onlineAccountConfig = new OnlineAccountConfig();
        onlineAccountConfig.setTaxSounrceId(onlinePaymentInfo.getTaxSourceCompanyId());
        onlineAccountConfig = onlineAccountConfigMapper.selectOne(onlineAccountConfig);
        infoDTO.setAccountNo(onlineAccountConfig.getAccountPrefix() + onlinePaymentInfo.getSubAccountNo());
        if (TaxSourceInfoEnum.TAI_NING_BO_RUI.getId().equals(taxSourceId)) {
            infoDTO.setAccountName(TaxSourceInfoEnum.TAI_NING_BO_RUI.getDescription());
        } else if (TaxSourceInfoEnum.HAN_NAN_JUN_JU.getId().equals(taxSourceId)) {
            infoDTO.setAccountName(TaxSourceInfoEnum.HAN_NAN_JUN_JU.getDescription());
        } else if (TaxSourceInfoEnum.JIANG_XI_QI_BO.getId().equals(taxSourceId)) {
            infoDTO.setAccountName(TaxSourceInfoEnum.JIANG_XI_QI_BO.getDescription());
        }
        return infoDTO;
    }

    @Override
    public TianJinAccountDTO getAccountDetail(String accountUuid, Integer merchantId) {
        TianJinAccountDTO tianJinAccountDTO = new TianJinAccountDTO();
        TianJinPaymentInfo tianJinPaymentInfo = tianJinPaymentInfoMapper.getTianJinPaymentInfoByMerchantId(merchantId);
        String url = TIAN_JIN_HOST + TianJinConstants.ACCOUNT_URL + accountUuid;
        String result = TianJinUtil.getAccountId(url, tianJinPaymentInfo.getXinbadaUserUuid(), tianJinPaymentInfo.getToken());
        TianJinRequestResultDTO dto = JSONObject.parseObject(result, TianJinRequestResultDTO.class);
        if (dto.getData() != null) {
            tianJinAccountDTO = JSONObject.parseObject(dto.getData().toString(), TianJinAccountDTO.class);
        }
        return tianJinAccountDTO;
    }

    @Override
    public List<TianJinServerInfoDTO> getServerInfo(Integer merchantId) {
        List<TianJinServerInfoDTO> list = new ArrayList<>();
        TianJinPaymentInfo tianJinPaymentInfo = tianJinPaymentInfoMapper.getTianJinPaymentInfoByMerchantId(merchantId);
        String url = TIAN_JIN_HOST + TianJinConstants.SALARY_SERVER;
        String result = TianJinUtil.getAccountId(url, tianJinPaymentInfo.getXinbadaUserUuid(), tianJinPaymentInfo.getToken());
        TianJinRequestResultDTO dto = JSONObject.parseObject(result, TianJinRequestResultDTO.class);
        if (dto.getData() != null) {
            list = JSONObject.parseArray(JSONObject.parseObject(dto.getData().toString()).getString("servers"), TianJinServerInfoDTO.class);
        }
        return list;
    }

    private List<PaymentInfoDTO> getListPaymentInfoDTO(String batchNo, String merchantCode) {
        List<PaymentInfoDTO> list;
        //移除批量记录
        RedisUtil.remove(merchantCode);
        //将代付详情从session中取出来
        String object = RedisUtil.get(merchantCode + batchNo);
        list = JSONArray.parseArray(object, PaymentInfoDTO.class);
        //从session中移除代付详情
//        RedisUtil.remove(merchantCode + batchNo);
        return list;
    }

    public String singlePaymentOfOnlineByBigRate(PaymentInfoDTO paymentInfoDTO, BatchPaymentRecord batchPaymentRecord,
                                                 boolean flag, Integer trxOrderId,Map<String,BigDecimal> overCharge) {
        //打款结果
        String paymentResult = ResultEnum.SUCCESS.name();
        //获取商户服务费费率
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(paymentInfoDTO.getMerchantId());
        Cooperation rCooperation = cooperationMapper.selectOne(cooperation);
        //代付成功或者失败的标识
        boolean dfFlag = true;
        //查询商户信息
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(paymentInfoDTO.getMerchantId());
        //查询商户风险配置信息
        RiskManagementConfig riskManagementConfig =
                riskManagementConfigMapper.getConfigByMerchantId(merchantInfo.getId());
        //查询商户钱包信息
        MerchantAccount account = merchantAccountMapper.getMerchantAccountByMerchantId(merchantInfo.getId());
        if (account == null) {
            throw new BusinessException("您的钱包异常，请联系管理员");
        }
        //收取的手续费,打款金额，模板金额
        BigDecimal tax, settleAmount, mouldAmount;
        mouldAmount = BigDecimal.valueOf(Double.parseDouble(paymentInfoDTO.getSettleAmount()));
        if (InOutDeductionEnum.OUTDEDUCTION.getStatus().equals(rCooperation.getInOrOutDeduction())) {
            //外扣
            settleAmount = mouldAmount;
            tax = settleAmount.multiply(rCooperation.getSingleServiceBigRate()).setScale(2, BigDecimal.ROUND_UP);
        } else {
            //内扣
            tax = mouldAmount.multiply(rCooperation.getSingleServiceBigRate()).setScale(2, BigDecimal.ROUND_UP);
            settleAmount = mouldAmount.subtract(tax);
        }

        if (mouldAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return ResultEnum.TOTAL_FEE_LESSEQUAL_ZERO.description;
        }

        if (batchPaymentRecord == null) {
            if (account.getUsableAmount().compareTo(settleAmount.add(tax)) < 0) {
                if (trxOrderId != null) {
                    throw new BusinessException(ResultEnum.INSUFFICIENT_WALLET_BALANCE.description);
                } else {
                    return ResultEnum.INSUFFICIENT_WALLET_BALANCE.description;
                }
            }
            if (flag) {
                checkPassword(paymentInfoDTO);
            }
        }
        //平台唯一交易流水号
        String trxOrderNo = ChanPayUtil.generateOutTradeNo();
        TrxOrder trxOrder = new TrxOrder();
        trxOrder.setMemo(paymentInfoDTO.getMemo());
        trxOrder.setTaskRecordBatchNo(paymentInfoDTO.getTaskRecordBatchNo());
        //派单商户判断该员工是否派单
        if(SwitchEnum.ON.getCode().equals(rCooperation.getSendOrderSwitch())
                && null != batchPaymentRecord){
            int count = customerTaskMapper.selectBymerchantIdIdCardNo(merchantInfo.getId(),paymentInfoDTO.getIdCardNo());
            if(count <= 0){
                trxOrder = convertTrxOrder(paymentInfoDTO, merchantInfo, rCooperation, tax.doubleValue(), trxOrderNo
                        , paymentInfoDTO.getUseBigRate(), settleAmount);
                trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
                trxOrder.setComment(TrxOrderCommentEnum.NO_SEND_CUSTOMER.description);
                trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                trxOrderMapper.insertSelective(trxOrder);
                return TrxOrderCommentEnum.NO_SEND_CUSTOMER.description;
            }
        }
        // 订单限额 singleMaxQuota 单笔最大金额
        BigDecimal singleMaxQuota = PaymentConstants.SINGLE_MAX_QUOTA;
        //查询单人每月收款限制
        SysConfig rSysConfig = getMonthlyLimitAmount(rCooperation.getBigRateSwitch());
        BigDecimal singleMonthMaxQuota = BigDecimal.valueOf(Double.parseDouble(rSysConfig.getConfigValue()));
        if (singleMaxQuota.compareTo(mouldAmount) < 0) {
            trxOrder = convertTrxOrder(paymentInfoDTO, merchantInfo, rCooperation, tax.doubleValue(), trxOrderNo
                    , paymentInfoDTO.getUseBigRate(), settleAmount);
            trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
            trxOrder.setComment(TrxOrderCommentEnum.OUT_SINGLE_QUOTA.description);
            trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
            trxOrderMapper.insertSelective(trxOrder);
            //若为批量代付，则添加批次号和批量代付记录id
            updateBatchAndTaxOrder(batchPaymentRecord,trxOrder,settleAmount);
            return TrxOrderCommentEnum.OUT_SINGLE_QUOTA.description;
        }
        String startDate = DateUtil.format(DateUtil.beginOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
        String endDate = DateUtil.format(DateUtil.endOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
        //计算历史打款额(内扣外扣)
//        sumAmount = trxOrderMapper.sumAmountByPayeeIdCardNo(String.valueOf(merchantInfo.getId()),
//                paymentInfoDTO.getIdCardNo(), startDate, endDate);
//        BigDecimal sumMonthAmount = trxOrderMapper.sumAmountByPayeeIdCardNo(null,
//                paymentInfoDTO.getIdCardNo(), startDate, endDate);
//        if(sumMonthAmount.compareTo(PaymentConstants.SINGLE_MONTH_LIMIT) > 0){
//            throw new BusinessException("该灵工当月收款额度已达上限");
//        }
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantInfo.getId());
        Integer taxSourceId = onlinePaymentInfo.getTaxSourceCompanyId();
        Integer[] statusList = {-1, 0, 1};
        List<TrxOrder> historyTrxOrders = trxOrderMapper.selectByStatusInOneMonth(null, null
                , paymentInfoDTO.getIdCardNo(), startDate, endDate, statusList,taxSourceId);
        BigDecimal sumAmount = BigDecimal.ZERO;
        if (!org.springframework.util.CollectionUtils.isEmpty(historyTrxOrders)) {
            sumAmount = historyTrxOrders.stream().reduce(BigDecimal.ZERO
                    , (a, b) -> a.add(calTrxOrderMouldAmount(b)), (a, b) -> BigDecimal.ZERO);
        }
        logger.info("灵工{}本月打款额为{}",paymentInfoDTO.getIdCardNo(),sumAmount);
        if (sumAmount.add(mouldAmount).compareTo(singleMonthMaxQuota) > 0) {
            logger.info(TrxOrderCommentEnum.OUT_SINGLE_MONTH_QUOTA.description + ",idCardNo:{},amount:{}",
                    paymentInfoDTO.getIdCardNo(), settleAmount.toPlainString());
            trxOrder = convertTrxOrder(paymentInfoDTO, merchantInfo, rCooperation, tax.doubleValue(), trxOrderNo
                    , paymentInfoDTO.getUseBigRate(), settleAmount);
            trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
            trxOrder.setComment(TrxOrderCommentEnum.OUT_SINGLE_MONTH_QUOTA.description
                    + singleMonthMaxQuota.toPlainString());
            trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
            trxOrderMapper.insertSelective(trxOrder);
            //若为批量代付，则添加批次号和批量代付记录id
            updateBatchAndTaxOrder(batchPaymentRecord,trxOrder,settleAmount);
            return TrxOrderCommentEnum.OUT_SINGLE_MONTH_QUOTA.description + singleMonthMaxQuota.toPlainString();
        }
        //判断需要代付金额是否超出单笔限额,若超出单笔限额，订单挂起
        if (riskManagementConfig.getSingleDayQuota().equals(SwitchStatusEnum.ON.name())) {
            boolean quotaFlag = false;
            if (PaymentEnum.BANK.name().equals(paymentInfoDTO.getPaymentChannel())
                    && ChannelOpenEnum.OPEN.getStatus().equals(rCooperation.getBankChannel())
                    && rCooperation.getBankSingleQuota().compareTo(mouldAmount) < 0) {
                quotaFlag = true;
            }
            if (PaymentEnum.ALIPAY.name().equals(paymentInfoDTO.getPaymentChannel())
                    && ChannelOpenEnum.OPEN.getStatus().equals(rCooperation.getAlipayChannel())
                    && rCooperation.getAlipaySingleQuota().compareTo(mouldAmount) < 0) {
                quotaFlag = true;
            }
            if (PaymentEnum.WECHAT.name().equals(paymentInfoDTO.getPaymentChannel())
                    && ChannelOpenEnum.OPEN.getStatus().equals(rCooperation.getWechatChannel())
                    && rCooperation.getWechatSingleQuota().compareTo(mouldAmount) < 0) {
                quotaFlag = true;
            }
            if (quotaFlag) {
                trxOrder = convertTrxOrder(paymentInfoDTO, merchantInfo, rCooperation, tax.doubleValue(), trxOrderNo
                        , paymentInfoDTO.getUseBigRate(), settleAmount);
                trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
                trxOrder.setComment(TrxOrderCommentEnum.OUT_SINGLE_QUOTA.description);
                trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                if (batchPaymentRecord != null) {
                    trxOrder.setBatchNo(batchPaymentRecord.getBatchNo());
                    trxOrder.setBatchPaymentRecordId(batchPaymentRecord.getId());
                }
                trxOrder.setCreateTime(DateUtil.date());
                trxOrder.setUpdateTime(DateUtil.date());
                trxOrderMapper.insertSelective(trxOrder);
                //若为批量代付，则添加批次号和批量代付记录id
                updateBatchAndTaxOrder(batchPaymentRecord,trxOrder,settleAmount);
                return TrxOrderCommentEnum.OUT_SINGLE_QUOTA.description;
            }
        }

        if (trxOrderId == null) {
            //生成订单信息
            trxOrder = convertTrxOrder(paymentInfoDTO, merchantInfo, rCooperation, tax.doubleValue(), trxOrderNo
                    , paymentInfoDTO.getUseBigRate(), settleAmount);
            trxOrder.setOrderStatus(TrxOrderStatusEnum.INIT.code);
            trxOrderMapper.insertSelective(trxOrder);
        }

        //若为批量代付，则添加批次号和批量代付记录id
        updateBatchAndTaxOrder(batchPaymentRecord,trxOrder,settleAmount);
//        if (batchPaymentRecord != null) {
//            batchPaymentRecord.setPaymentActualAmount(batchPaymentRecord.getPaymentActualAmount().add(settleAmount));
//            if (StringUtils.isNull(batchPaymentRecord.getPaymentCompleteCount())){
//                batchPaymentRecord.setPaymentCompleteCount(0);
//            }
//            batchPaymentRecord.setPaymentCompleteCount(batchPaymentRecord.getPaymentCompleteCount()+1);
//            batchPaymentRecord.setStatus(BatchPaymentRecordStatusEnum.GOING.code);
//            batchPaymentRecordMapper.updateByPrimaryKeySelective(batchPaymentRecord);
//            trxOrder.setBatchNo(batchPaymentRecord.getBatchNo());
//            trxOrder.setBatchPaymentRecordId(batchPaymentRecord.getId());
//            trxOrder.setUpdateTime(DateUtil.date());
//            trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
//            batchPaymentDetailService.addRecord(trxOrder, trxOrder.getTrxRateBig(), trxOrder.getChargeBig());
//        }


        //判断是否调单
        boolean orderSheetFlag = getOrderSheetResult(merchantInfo, paymentInfoDTO);
        if (orderSheetFlag) {
            trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
            trxOrder.setComment(TrxOrderCommentEnum.ORDER_SHEEL.description);
            trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
            trxOrder.setUpdateTime(DateUtil.date());
            trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
            convertOrderSheet(merchantInfo.getId(), trxOrder);
            return TrxOrderCommentEnum.ORDER_SHEEL.description;
        }

        String name = trxOrder.getPayeeName();
        String mobile = trxOrder.getPayeeMobile();
        String idCardNo = trxOrder.getPayeeIdCardNo();
        String bankNo = trxOrder.getPayeeBankCard();
        Integer merchantId = trxOrder.getMerchantId();
        int signSwitch = rCooperation.getSignSwitch();
        Integer signingType = rCooperation.getSigningType();
        CustomerInfo customerInfo = null;
        boolean haveSign = customerProtocolService.judgeSign(idCardNo, merchantId);
        if (SwitchEnum.OFF.getCode() == signSwitch || haveSign ||
                SigningTypeEnum.ONLINEPAY.getValue().equals(signingType) ||
                SigningTypeEnum.WECHAT.getValue().equals(signingType)) {
            //获取员工(未注册的新建注册后获取)
            try {
                if("51010419900307493X".equals(idCardNo)){
                    throw new BusinessException("姓名身份证校验失败");
                }
                customerInfo = customerInfoService.queryCustomerInfo(name, mobile, idCardNo, merchantId,null
                        ,null);
            } catch (BusinessException e) {
                logger.info("灵工账号注册失败，{}", e.getMessage());
                dfFlag = false;
                trxOrder.setComment(e.getMessage());
            } catch (PaymentException e) {
                logger.error(e.getMessage());
                dfFlag = false;
                trxOrder.setComment(e.getMessage());
            } catch (Exception e) {
                logger.error("灵工账号注册系统错误：" + e.getMessage());
                dfFlag = false;
                trxOrder.setComment("灵工账号注册系统错误,请联系开发人员");
            }
        } else {
            dfFlag = false;
            trxOrder.setComment("未签约，不予打款");
        }
        logger.info("customerInfo:{}", JSON.toJSONString(customerInfo));
        String bankId = null;
        if (null == customerInfo) {
            trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
            trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
            trxOrder.setUpdateTime(DateUtil.date());
            trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
            paymentResult = trxOrder.getComment();
        } else {
            //把员工id更新进trxorder
            trxOrder.setCustomerId(customerInfo.getId());
            trxOrder.setUpdateTime(DateUtil.date());
            trxOrderMapper.updateByPrimaryKeySelective(trxOrder);

            if (batchPaymentRecord != null) {
                //如果 推广开关 开(1), 插入或更新 tbl_customer_promotions记录
                Cooperation cooperationExample = new Cooperation();
                cooperationExample.setMerchantId(merchantInfo.getId());
                Cooperation cooperationResult = cooperationMapper.selectOne(cooperationExample);
                if(cooperationResult.getPromotionSwitch().equals(SwitchEnum.ON.getCode())) {
                    operateCustomerPromotion(paymentInfoDTO, merchantInfo, trxOrder, customerInfo);
                }
            }

            //获取员工信息成功，开始获取银行卡或支付宝信息
            try {
                //根据代付渠道获取信息(没有信息的绑定支付宝或银行卡)
                if (PaymentEnum.BANK.name().equals(paymentInfoDTO.getPaymentChannel())) {
                    //获取银行卡信息
                    CustomerBankcardInfo customerBankcardInfo = customerBankcardInfoService.queryBankcardInfo(
                            name, mobile, bankNo, customerInfo.getId());
                    bankId = customerBankcardInfo.getBankId();
                } else if (PaymentEnum.ALIPAY.name().equals(paymentInfoDTO.getPaymentChannel())) {
                    //获取支付宝信息
                    CustomerAlipayInfo customerAlipayInfo = customerAlipayInfoService.queryAlipayInfo(
                            name, mobile, bankNo, customerInfo.getId());
                    bankId = customerAlipayInfo.getBankId();
                }
            } catch (BusinessException | PaymentException e) {
                trxOrder.setComment(e.getMessage());
                dfFlag = false;
                trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
                trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                trxOrder.setUpdateTime(DateUtil.date());
                trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
                paymentResult = trxOrder.getComment();
            } catch (Exception e) {
                logger.error("获取支付渠道系统错误：" + e.getMessage());
                dfFlag = false;
                trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
                trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                trxOrder.setComment("获取支付渠道时系统错误");
                trxOrder.setUpdateTime(DateUtil.date());
                trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
                paymentResult = trxOrder.getComment();
            }
        }

        //如果有overCharge,则减掉
        BigDecimal overChargeAmount = BigDecimal.ZERO,allOverChargeAmount = null;
        if(null != batchPaymentRecord){
            allOverChargeAmount = batchPaymentRecord.getOverCharge();
            logger.info("补偿总额{}",allOverChargeAmount);
            if(null != paymentInfoDTO.getOverChargeAmount()) {
                overChargeAmount = BigDecimal.valueOf(Double.parseDouble(paymentInfoDTO.getOverChargeAmount()));
            }
        }

        //获取银行卡或支付宝信息成功后
        TransferOrder transferOrder = null;
        if (null != bankId) {
            //bankId更新进trxorder
            trxOrder.setBankId(bankId);
            trxOrder.setUpdateTime(DateUtil.date());
            trxOrderMapper.updateByPrimaryKeySelective(trxOrder);

            //生成转账记录信息
            transferOrder = convertTransferOrder(trxOrder, customerInfo);
            //出账冻结商户的账户并加变动记录
            BigDecimal transferAmount = settleAmount.add(tax.subtract(overChargeAmount));
            MerchantAccount beforeAccount = merchantAccountService.updateMerchantAccountByVersion(
                    trxOrder.getMerchantId(), BigDecimal.ZERO.subtract(transferAmount)
                    , UpdateAccountTypeOfMethod.PAYORMAKEUP.getType());
            MerchantAccount afterAccount = merchantAccountMapper.getMerchantAccountByMerchantId(trxOrder.getMerchantId());
            merchantAccountDetailService.insertMerchantAccountDetail(transferAmount,beforeAccount,afterAccount
                    , AccountDetailStatusEnum.SUCCESS.code,trxOrder.getOrderSerialNum()
                    ,MerchantAccountDetailTypeEnum.PAID.code,DetailTypeEnum.PAYMENT.getCode());
            //入账冻结员工的账户并加变动记录
            logger.info("settleAmount={}",settleAmount);
            BigDecimal transToCustomerAmount = settleAmount;
            customerAccountService.updateAccountAndInsertDetail(customerInfo.getId(),transToCustomerAmount
                    ,AccountDetailTypeEnum.CREDIT_FREEZE,trxOrder.getOrderSerialNum());
        }

        logger.info("dfFlag:{},bankId:{}", dfFlag, bankId);
        if (dfFlag && null != bankId) {
            //生成转账记录以及相关账户变动成功后
            OnlineCommonResultDTO resultDTO = iOnlineBankService.transferAmountFromMerToCus(transferOrder,overChargeAmount);
            transferOrder.setStatus(TransferStatusEnum.UNPAID.getCode());
            if (!"T".equals(resultDTO.getIs_success())) {
                //若未成功转账
                transferOrder.setStatus(TransferStatusEnum.PAID_PENDING.getCode());
                transferOrder.setComment(resultDTO.getError_message());
                paymentResult = "转账失败";
                //这边相关商户和员工账户不解冻，让定时任务去处理
            }
            transferOrder.setUpdateTime(DateUtil.date());
            transferOrderMapper.updateByPrimaryKeySelective(transferOrder);
            if("T".equals(resultDTO.getIs_success()) && null != batchPaymentRecord){
//                overCharge.replace(batchPaymentRecord.getBatchNo()+merchantId
//                        ,allOverChargeAmount.subtract(overChargeAmount));
                //新增入补偿表
                orderReimburseService.insertOrderReimburse(trxOrder.getOrderSerialNum(),overChargeAmount);
            }
        }

        if (ResultEnum.SUCCESS.name().equals(paymentResult)
                && TrxOrderStatusEnum.INIT.code.equals(trxOrder.getOrderStatus())) {
            //添加补交服务费和详细记录
            MakeUpCharge makeUpCharge = null;
            if (null != paymentInfoDTO.getMakeUpCharge()) {
                List<MakeUpChargeDetail> makeUpChargeDetails = com.alibaba.fastjson.JSONArray.parseArray(
                        paymentInfoDTO.getMakeUpChargeDetails(), MakeUpChargeDetail.class);
                makeUpCharge = makeUpChargeService.addRecords(paymentInfoDTO.getMakeUpCharge()
                        , makeUpChargeDetails, trxOrder);
                if (null != customerInfo) {
                    CusLinkMerInfo cusLinkMerInfo = cusLinkMerInfoMapper.selectByCusIdMerId(customerInfo.getId()
                            ,merchantId);
                    if(null != cusLinkMerInfo) {
                        cusLinkMerInfo.setMonthUseRate(UseBigRateEnum.BIGRATE.getStatus());
                        cusLinkMerInfo.setUpdateTime(DateUtil.date());
                        cusLinkMerInfoMapper.updateByPrimaryKeySelective(cusLinkMerInfo);
                    }
                }
            }
        }

        return paymentResult;
    }

    @Override
    public void factorVerification(PayRequestData payRequestData,Cooperation cooperation){
        if(PayDataCheckStatusEnum.SUCESS.getStatus().equals(payRequestData.getStatus())){
            logger.info("打款数据id={}已校验成功，不再重复校验",payRequestData.getId());
            return;
        }
        String name = payRequestData.getName();
        String mobile = payRequestData.getMobile();
        String idCardNo = payRequestData.getIdCardNo();
        String bankNo = payRequestData.getBankCard();
        Integer merchantId = payRequestData.getMerchantId();
        //获取员工(未注册的新建注册后获取)
        CustomerInfo customerInfo = null;
        Integer signingType = cooperation.getSigningType();
        boolean haveSign = customerProtocolService.judgeSign(idCardNo, merchantId);
        if (SwitchEnum.OFF.getCode().equals(cooperation.getSignSwitch()) || haveSign ||
                SigningTypeEnum.ONLINEPAY.getValue().equals(signingType) ||
                SigningTypeEnum.WECHAT.getValue().equals(signingType)) {
            try {
                if("51010419900307493X".equals(idCardNo)){
                    throw new BusinessException("姓名身份证校验失败");
                }
                customerInfo = customerInfoService.queryCustomerInfo(name, mobile, idCardNo, merchantId,null
                        ,null);
            } catch (BusinessException e) {
                logger.info("灵工账号注册失败，{}", e.getMessage());
                payRequestData.setComment(e.getMessage());
            } catch (PaymentException e) {
                logger.error(e.getMessage());
                payRequestData.setComment(e.getMessage());
            } catch (Exception e) {
                logger.error("灵工账号注册系统错误：" + e.getMessage());
                payRequestData.setComment("姓名身份证验证出错,请联系开发人员");
            }
        }else{
            payRequestData.setStatus(PayDataCheckStatusEnum.FAIL.getStatus());
            payRequestData.setComment("未签约，请先签约");
            payRequestDataMapper.updateByPrimaryKeySelective(payRequestData);
            return;
        }
        logger.info("customerInfo:{}", JSON.toJSONString(customerInfo));
        if (null == customerInfo) {
            payRequestData.setStatus(PayDataCheckStatusEnum.FAIL.getStatus());
            payRequestDataMapper.updateByPrimaryKeySelective(payRequestData);
            return;
        }
        //获取员工信息成功，开始获取银行卡或支付宝信息
        String bankId = null;
        try {
            //根据代付渠道获取信息(没有信息的绑定支付宝或银行卡)
            if (PaymentEnum.BANK.name().equals(payRequestData.getPaymentChannel())) {
                //获取银行卡信息
                CustomerBankcardInfo customerBankcardInfo = customerBankcardInfoService.queryBankcardInfo(
                        name, mobile, bankNo, customerInfo.getId());
                bankId = customerBankcardInfo.getBankId();
            } else if (PaymentEnum.ALIPAY.name().equals(payRequestData.getPaymentChannel())) {
                //获取支付宝信息
                CustomerAlipayInfo customerAlipayInfo = customerAlipayInfoService.queryAlipayInfo(
                        name, mobile, bankNo, customerInfo.getId());
                bankId = customerAlipayInfo.getBankId();
            }
        } catch (BusinessException | PaymentException e) {
            logger.error(e.getMessage());
            payRequestData.setComment(e.getMessage());
        } catch (Exception e) {
            logger.error("获取支付渠道系统错误：" + e.getMessage());
            payRequestData.setComment("姓名身份证银行卡验证出错,请联系开发人员");
        }
        if(org.springframework.util.StringUtils.isEmpty(bankId)){
            payRequestData.setStatus(PayDataCheckStatusEnum.FAIL.getStatus());
        }else {
            payRequestData.setStatus(PayDataCheckStatusEnum.SUCESS.getStatus());
        }
        payRequestDataMapper.updateByPrimaryKeySelective(payRequestData);
    }

    @Override
    public String singlePaymentOfOnline(PaymentInfoDTO paymentInfoDTO, BatchPaymentRecord batchPaymentRecord,
                                        boolean flag, Integer trxOrderId,Map<String,BigDecimal> overCharge) {
        //打款结果
        String paymentResult = ResultEnum.SUCCESS.name();
        //代付成功或者失败的标识
        boolean dfFlag = true;
        //查询商户信息
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(paymentInfoDTO.getMerchantId());
        //查询商户风险配置信息
        RiskManagementConfig riskManagementConfig =
                riskManagementConfigMapper.getConfigByMerchantId(merchantInfo.getId());
        //查询商户钱包信息
        MerchantAccount account = merchantAccountMapper.getMerchantAccountByMerchantId(merchantInfo.getId());
        if (account == null) {
            throw new BusinessException("您的钱包异常，请联系管理员");
        }
        //获取商户服务费费率
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(paymentInfoDTO.getMerchantId());
        Cooperation rCooperation = cooperationMapper.selectOne(cooperation);

        //收取的手续费,打款金额，模板金额
        BigDecimal tax, settleAmount, mouldAmount;
        mouldAmount = BigDecimal.valueOf(Double.parseDouble(paymentInfoDTO.getSettleAmount()));
        if (InOutDeductionEnum.OUTDEDUCTION.getStatus().equals(rCooperation.getInOrOutDeduction())) {
            //外扣
            settleAmount = mouldAmount;
            tax = settleAmount.multiply(rCooperation.getSingleServiceRate()).setScale(2, BigDecimal.ROUND_UP);
        } else {
            //内扣
            tax = mouldAmount.multiply(rCooperation.getSingleServiceRate()).setScale(2, BigDecimal.ROUND_UP);
            settleAmount = mouldAmount.subtract(tax);
        }
        //打款金额
        if (mouldAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return ResultEnum.TOTAL_FEE_LESSEQUAL_ZERO.description;
        }

        if (batchPaymentRecord == null) {
            if (account.getUsableAmount().compareTo(settleAmount.add(tax)) < 0) {
                if (trxOrderId != null) {
                    throw new BusinessException(ResultEnum.INSUFFICIENT_WALLET_BALANCE.description);
                } else {
                    return ResultEnum.INSUFFICIENT_WALLET_BALANCE.description;
                }
            }
            if (flag) {
                checkPassword(paymentInfoDTO);
            }
        }
        //平台唯一交易流水号
        String trxOrderNo = ChanPayUtil.generateOutTradeNo();
        TrxOrder trxOrder = new TrxOrder();
        trxOrder.setMemo(paymentInfoDTO.getMemo());
        trxOrder.setTaskRecordBatchNo(paymentInfoDTO.getTaskRecordBatchNo());
        //派单商户判断该员工是否派单
        if(SwitchEnum.ON.getCode().equals(rCooperation.getSendOrderSwitch())
                && null != batchPaymentRecord){
            int count = customerTaskMapper.selectBymerchantIdIdCardNo(merchantInfo.getId(),paymentInfoDTO.getIdCardNo());
            if(count <= 0){
                trxOrder = convertTrxOrder(paymentInfoDTO, merchantInfo, rCooperation, tax.doubleValue(), trxOrderNo
                        , paymentInfoDTO.getUseBigRate(), settleAmount);
                trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
                trxOrder.setComment(TrxOrderCommentEnum.NO_SEND_CUSTOMER.description);
                trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                trxOrderMapper.insertSelective(trxOrder);
                return TrxOrderCommentEnum.NO_SEND_CUSTOMER.description;
            }
        }
        // 订单限额 singleMaxQuota 单笔最大金额
        BigDecimal singleMaxQuota = PaymentConstants.SINGLE_MAX_QUOTA;
        //查询单人每月收款限制
        SysConfig rSysConfig = getMonthlyLimitAmount(rCooperation.getBigRateSwitch());
        BigDecimal singleMonthMaxQuota = BigDecimal.valueOf(Double.parseDouble(rSysConfig.getConfigValue()));
        if (singleMaxQuota.compareTo(mouldAmount) < 0) {
            trxOrder = convertTrxOrder(paymentInfoDTO, merchantInfo, rCooperation, tax.doubleValue(), trxOrderNo
                    , paymentInfoDTO.getUseBigRate(), settleAmount);
            trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
            trxOrder.setComment(TrxOrderCommentEnum.OUT_SINGLE_QUOTA.description);
            trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
            trxOrderMapper.insertSelective(trxOrder);
            //若为批量代付，则添加批次号和批量代付记录id
            updateBatchAndTaxOrder(batchPaymentRecord,trxOrder,settleAmount);
            return TrxOrderCommentEnum.OUT_SINGLE_QUOTA.description;
        }
        String startDate = DateUtil.format(DateUtil.beginOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
        String endDate = DateUtil.format(DateUtil.endOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
        //计算历史打款额(内扣外扣)
//        sumAmount = trxOrderMapper.sumAmountByPayeeIdCardNo(String.valueOf(merchantInfo.getId()),
//                paymentInfoDTO.getIdCardNo(), startDate, endDate);
//        BigDecimal sumMonthAmount = trxOrderMapper.sumAmountByPayeeIdCardNo(null,
//                paymentInfoDTO.getIdCardNo(), startDate, endDate);
//        if(sumMonthAmount.compareTo(PaymentConstants.SINGLE_MONTH_LIMIT) > 0){
//            throw new BusinessException("该灵工当月收款额度已达上限");
//        }
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantInfo.getId());
        Integer taxSourceId = onlinePaymentInfo.getTaxSourceCompanyId();
        Integer[] statusList = {-1, 0, 1};
        List<TrxOrder> historyTrxOrders = trxOrderMapper.selectByStatusInOneMonth(null, null
                , paymentInfoDTO.getIdCardNo(), startDate, endDate, statusList,taxSourceId);
        BigDecimal sumAmount = BigDecimal.ZERO;
        if (!org.springframework.util.CollectionUtils.isEmpty(historyTrxOrders)) {
            sumAmount = historyTrxOrders.stream().reduce(BigDecimal.ZERO
                    , (a, b) -> a.add(calTrxOrderMouldAmount(b)), (a, b) -> BigDecimal.ZERO);
        }
        logger.info("灵工{}本月打款额为{}",paymentInfoDTO.getIdCardNo(),sumAmount);
        if (sumAmount.add(mouldAmount).compareTo(singleMonthMaxQuota) > 0) {
            logger.info(TrxOrderCommentEnum.OUT_SINGLE_MONTH_QUOTA.description + ",idCardNo:{},amount:{}",
                    paymentInfoDTO.getIdCardNo(), settleAmount.toPlainString());
            trxOrder = convertTrxOrder(paymentInfoDTO, merchantInfo, rCooperation, tax.doubleValue(), trxOrderNo
                    , paymentInfoDTO.getUseBigRate(), settleAmount);
            trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
            trxOrder.setComment(TrxOrderCommentEnum.OUT_SINGLE_MONTH_QUOTA.description
                    + singleMonthMaxQuota.toPlainString());
            trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
            trxOrderMapper.insertSelective(trxOrder);
            //若为批量代付，则添加批次号和批量代付记录id
            updateBatchAndTaxOrder(batchPaymentRecord,trxOrder,settleAmount);
            return TrxOrderCommentEnum.OUT_SINGLE_MONTH_QUOTA.description + singleMonthMaxQuota.toPlainString();
        }
        //判断需要代付金额是否超出单笔限额,若超出单笔限额，订单挂起
        if (riskManagementConfig.getSingleDayQuota().equals(SwitchStatusEnum.ON.name())) {
            boolean quotaFlag = false;
            if (PaymentEnum.BANK.name().equals(paymentInfoDTO.getPaymentChannel())
                    && ChannelOpenEnum.OPEN.getStatus().equals(rCooperation.getBankChannel())
                    && rCooperation.getBankSingleQuota().compareTo(mouldAmount) < 0) {
                quotaFlag = true;
            }
            if (PaymentEnum.ALIPAY.name().equals(paymentInfoDTO.getPaymentChannel())
                    && ChannelOpenEnum.OPEN.getStatus().equals(rCooperation.getAlipayChannel())
                    && rCooperation.getAlipaySingleQuota().compareTo(mouldAmount) < 0) {
                quotaFlag = true;
            }
            if (PaymentEnum.WECHAT.name().equals(paymentInfoDTO.getPaymentChannel())
                    && ChannelOpenEnum.OPEN.getStatus().equals(rCooperation.getWechatChannel())
                    && rCooperation.getWechatSingleQuota().compareTo(mouldAmount) < 0) {
                quotaFlag = true;
            }
            if (quotaFlag) {
                trxOrder = convertTrxOrder(paymentInfoDTO, merchantInfo, rCooperation, tax.doubleValue(), trxOrderNo
                        , paymentInfoDTO.getUseBigRate(), settleAmount);
                trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
                trxOrder.setComment(TrxOrderCommentEnum.OUT_SINGLE_QUOTA.description);
                trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                if (batchPaymentRecord != null) {
                    trxOrder.setBatchNo(batchPaymentRecord.getBatchNo());
                    trxOrder.setBatchPaymentRecordId(batchPaymentRecord.getId());
                }
                trxOrder.setCreateTime(DateUtil.date());
                trxOrder.setUpdateTime(DateUtil.date());
                trxOrderMapper.insertSelective(trxOrder);
                //若为批量代付，则添加批次号和批量代付记录id
                updateBatchAndTaxOrder(batchPaymentRecord,trxOrder,settleAmount);
                return TrxOrderCommentEnum.OUT_SINGLE_QUOTA.description;
            }
        }

        if (trxOrderId == null) {
            //生成订单信息
            trxOrder = convertTrxOrder(paymentInfoDTO, merchantInfo, rCooperation, tax.doubleValue(), trxOrderNo
                    , paymentInfoDTO.getUseBigRate(), settleAmount);
            trxOrder.setOrderStatus(TrxOrderStatusEnum.INIT.code);
            trxOrderMapper.insertSelective(trxOrder);
        }

        //若为批量代付，则添加批次号和批量代付记录id
        updateBatchAndTaxOrder(batchPaymentRecord,trxOrder,settleAmount);

        //判断是否调单
        boolean orderSheetFlag = getOrderSheetResult(merchantInfo, paymentInfoDTO);
        if (orderSheetFlag) {
            trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
            trxOrder.setComment(TrxOrderCommentEnum.ORDER_SHEEL.description);
            trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
            trxOrder.setUpdateTime(DateUtil.date());
            trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
            convertOrderSheet(merchantInfo.getId(), trxOrder);
            return TrxOrderCommentEnum.ORDER_SHEEL.description;
        }

        String name = trxOrder.getPayeeName();
        String mobile = trxOrder.getPayeeMobile();
        String idCardNo = trxOrder.getPayeeIdCardNo();
        String bankNo = trxOrder.getPayeeBankCard();
        Integer merchantId = trxOrder.getMerchantId();
        int signSwitch = rCooperation.getSignSwitch();
        Integer signingType = rCooperation.getSigningType();
        CustomerInfo customerInfo = null;
        boolean haveSign = customerProtocolService.judgeSign(idCardNo, merchantId);
        if (SwitchEnum.OFF.getCode() == signSwitch || haveSign ||
                SigningTypeEnum.ONLINEPAY.getValue().equals(signingType) ||
                SigningTypeEnum.WECHAT.getValue().equals(signingType)) {
            //获取员工(未注册的新建注册后获取)
            try {
                if("51010419900307493X".equals(idCardNo)){
                    throw new BusinessException("姓名身份证校验失败");
                }
                customerInfo = customerInfoService.queryCustomerInfo(name, mobile, idCardNo, merchantId,null
                        ,null);
            } catch (BusinessException e) {
                logger.info("灵工账号注册失败，{}", e.getMessage());
                dfFlag = false;
                trxOrder.setComment(e.getMessage());
            } catch (PaymentException e) {
                logger.error(e.getMessage());
                dfFlag = false;
                trxOrder.setComment(e.getMessage());
            } catch (Exception e) {
                logger.error("灵工账号注册系统错误：" + e.getMessage());
                dfFlag = false;
                trxOrder.setComment("灵工账号注册系统错误,请联系开发人员");
            }
        } else {
            dfFlag = false;
            trxOrder.setComment("未签约，不予打款");
        }
        logger.info("customerInfo:{}", JSON.toJSONString(customerInfo));
        String bankId = null;
        if (null == customerInfo) {
            trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
            trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
            trxOrder.setUpdateTime(DateUtil.date());
            trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
            paymentResult = trxOrder.getComment();
        } else {
            //把员工id更新进trxorder
            trxOrder.setCustomerId(customerInfo.getId());
            trxOrder.setUpdateTime(DateUtil.date());
            trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
            if (batchPaymentRecord != null) {
                //如果 推广开关 开(1), 插入或更新 tbl_customer_promotions记录
                Cooperation cooperationExample = new Cooperation();
                cooperationExample.setMerchantId(merchantInfo.getId());
                Cooperation cooperationResult = cooperationMapper.selectOne(cooperationExample);
                if(cooperationResult.getPromotionSwitch().equals(SwitchEnum.ON.getCode())) {
                    operateCustomerPromotion(paymentInfoDTO, merchantInfo, trxOrder, customerInfo);
                }
            }

            //获取员工信息成功，开始获取银行卡或支付宝信息
            try {
                //根据代付渠道获取信息(没有信息的绑定支付宝或银行卡)
                if (PaymentEnum.BANK.name().equals(paymentInfoDTO.getPaymentChannel())) {
                    //获取银行卡信息
                    CustomerBankcardInfo customerBankcardInfo = customerBankcardInfoService.queryBankcardInfo(
                            name, mobile, bankNo, customerInfo.getId());
                    bankId = customerBankcardInfo.getBankId();
                } else if (PaymentEnum.ALIPAY.name().equals(paymentInfoDTO.getPaymentChannel())) {
                    //获取支付宝信息
                    CustomerAlipayInfo customerAlipayInfo = customerAlipayInfoService.queryAlipayInfo(
                            name, mobile, bankNo, customerInfo.getId());
                    bankId = customerAlipayInfo.getBankId();
                }
            } catch (BusinessException | PaymentException e) {
                trxOrder.setComment(e.getMessage());
                dfFlag = false;
                trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
                trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                trxOrder.setUpdateTime(DateUtil.date());
                trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
                paymentResult = trxOrder.getComment();
            } catch (Exception e) {
                logger.error("获取支付渠道系统错误：" + e.getMessage());
                dfFlag = false;
                trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_FAIL.code);
                trxOrder.setAsyncStatus(AsyncStatusEnum.ALREADYHANDLE.getStatus());
                trxOrder.setComment("获取支付渠道时系统错误");
                trxOrder.setUpdateTime(DateUtil.date());
                trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
                paymentResult = trxOrder.getComment();
            }
        }

        //如果有overCharge,则减掉
        BigDecimal overChargeAmount = BigDecimal.ZERO,allOverChargeAmount = null;
        if(null != batchPaymentRecord){
            allOverChargeAmount = batchPaymentRecord.getOverCharge();
            logger.info("补偿总额{}",allOverChargeAmount);
            if(null != paymentInfoDTO.getOverChargeAmount()) {
                overChargeAmount = BigDecimal.valueOf(Double.parseDouble(paymentInfoDTO.getOverChargeAmount()));
            }
        }

        //获取银行卡或支付宝信息成功后
        TransferOrder transferOrder = null;
        if (null != bankId) {
            //bankId更新进trxorder
            trxOrder.setBankId(bankId);
            trxOrder.setUpdateTime(DateUtil.date());
            trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
            //生成转账记录信息
            transferOrder = convertTransferOrder(trxOrder, customerInfo);
            //出账冻结商户的账户并加变动记录
            BigDecimal transferAmount = settleAmount.add(tax.subtract(overChargeAmount));
            MerchantAccount beforeAccount = merchantAccountService.updateMerchantAccountByVersion(
                    trxOrder.getMerchantId(), BigDecimal.ZERO.subtract(transferAmount)
                    , UpdateAccountTypeOfMethod.PAYORMAKEUP.getType());
            MerchantAccount afterAccount = merchantAccountMapper.getMerchantAccountByMerchantId(trxOrder.getMerchantId());
            merchantAccountDetailService.insertMerchantAccountDetail(transferAmount,beforeAccount,afterAccount
                    , AccountDetailStatusEnum.SUCCESS.code,trxOrder.getOrderSerialNum()
                    ,MerchantAccountDetailTypeEnum.PAID.code,DetailTypeEnum.PAYMENT.getCode());
            //入账冻结员工的账户并加变动记录
            BigDecimal transToCustomerAmount = settleAmount;
            customerAccountService.updateAccountAndInsertDetail(customerInfo.getId(),transToCustomerAmount
                    ,AccountDetailTypeEnum.CREDIT_FREEZE,trxOrder.getOrderSerialNum());
        }

        logger.info("dfFlag:{},bankId:{}", dfFlag, bankId);
        if (dfFlag && null != bankId) {
            //生成转账记录以及相关账户变动成功后
            OnlineCommonResultDTO resultDTO = iOnlineBankService.transferAmountFromMerToCus(transferOrder,overChargeAmount);
            transferOrder.setStatus(TransferStatusEnum.UNPAID.getCode());
            if (!"T".equals(resultDTO.getIs_success())) {
                //若未成功转账
                transferOrder.setStatus(TransferStatusEnum.PAID_PENDING.getCode());
                transferOrder.setComment(resultDTO.getError_message());
                //这边相关商户和员工账户不解冻，让定时任务去处理
            }
            transferOrder.setUpdateTime(DateUtil.date());
            transferOrderMapper.updateByPrimaryKeySelective(transferOrder);
            if("T".equals(resultDTO.getIs_success()) && null != batchPaymentRecord){
//                overCharge.replace(batchPaymentRecord.getBatchNo()+merchantId
//                        ,allOverChargeAmount.subtract(overChargeAmount));
                //新增入补偿表
                orderReimburseService.insertOrderReimburse(trxOrder.getOrderSerialNum(),overChargeAmount);
            }
        }
        return paymentResult;
    }

    private void updateBatchAndTaxOrder(BatchPaymentRecord batchPaymentRecord,TrxOrder trxOrder,BigDecimal settleAmount){
        if (batchPaymentRecord != null) {
            BatchPaymentRecord nowRecord = batchPaymentRecordMapper.selectByPrimaryKey(batchPaymentRecord.getId());
            nowRecord.setPaymentActualAmount(nowRecord.getPaymentActualAmount().add(settleAmount));
            if(!BatchPaymentRecordStatusEnum.GOING.code.equals(nowRecord.getStatus())) {
                nowRecord.setStatus(BatchPaymentRecordStatusEnum.GOING.code);
            }
            nowRecord.setUpdateTime(DateUtil.date());
            batchPaymentRecordMapper.updateByPrimaryKeySelective(nowRecord);
            trxOrder.setBatchNo(batchPaymentRecord.getBatchNo());
            trxOrder.setBatchPaymentRecordId(batchPaymentRecord.getId());
            trxOrder.setUpdateTime(DateUtil.date());
            trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
            batchPaymentDetailService.addRecord(trxOrder, trxOrder.getTaxRate(), trxOrder.getCharge());
        }
    }

    private void operateCustomerPromotion(PaymentInfoDTO paymentInfoDTO, MerchantInfo merchantInfo, TrxOrder trxOrder, CustomerInfo customerInfo) {

        //如果tbl_customer_promotion表中已经有该员工就更新，没有就添加
        Integer customerInfoId = customerInfo.getId();
        CustomerPromotion customerPromotion = new CustomerPromotion();
        customerPromotion.setCustomerId(customerInfoId);
        int count = customerPromotionMapper.selectCount(customerPromotion);
        CustomerPromotion customerPromotionUpdate = convertCustomerPromotion(paymentInfoDTO, merchantInfo,
                customerInfo, trxOrder);
        if (count > 0) {
            //更新
            //更新时间
            customerPromotionUpdate.setUpdateTime(DateUtil.date());
            //customer_promotion.id
            CustomerPromotion customerPromotionSelect = new CustomerPromotion();
            customerPromotionSelect.setCustomerId(customerInfo.getId());
            CustomerPromotion customerPromotionResult = customerPromotionMapper.selectOne(customerPromotionSelect);
            customerPromotionUpdate.setId(customerPromotionResult.getId());

            customerPromotionMapper.updateByPrimaryKeySelective(customerPromotionUpdate);

        } else {
            //添加
            CustomerPromotion customerPromotionInsert = convertCustomerPromotion(paymentInfoDTO, merchantInfo,
                    customerInfo, trxOrder);

            customerPromotionInsert.setCreateTime(DateUtil.date());
            customerPromotionInsert.setUpdateTime(DateUtil.date());
            customerPromotionMapper.insertSelective(customerPromotionInsert);
        }


    }

    private CustomerPromotion convertCustomerPromotion(PaymentInfoDTO paymentInfoDTO, MerchantInfo merchantInfo,
                                                       CustomerInfo customerInfo, TrxOrder trxOrder) {
        CustomerPromotion customerPromotion = new CustomerPromotion();
        //设置商户id
        customerPromotion.setMerchantId(merchantInfo.getId());
        //设置省、市、区、街道、居委会
        customerPromotion.setProvince(paymentInfoDTO.getProvince());
        customerPromotion.setCity(paymentInfoDTO.getCity());
        customerPromotion.setArea(paymentInfoDTO.getArea());
        customerPromotion.setStreet(paymentInfoDTO.getStreet());
        customerPromotion.setCommunity(paymentInfoDTO.getCommunity());
        //设置数量、单价
        customerPromotion.setQuantity(paymentInfoDTO.getQuantity());
        customerPromotion.setUnitPrice(paymentInfoDTO.getUnitPrice());
        //设置员工id
        customerPromotion.setCustomerId(customerInfo.getId());
        //推广开始时间、推广结束时间
        customerPromotion.setPromotionStartTime(paymentInfoDTO.getPromotionStartTime());
        customerPromotion.setPromotionEndTime(paymentInfoDTO.getPromotionEndTime());
        //tbl_trx_order id
        customerPromotion.setTrxOrderId(trxOrder.getId());

        return customerPromotion;
    }

    /**
     * 生成转账记录
     *
     * @param trxOrder
     * @param customerInfo
     */
    private TransferOrder convertTransferOrder(TrxOrder trxOrder, CustomerInfo customerInfo) {
        TransferOrder transferOrder = new TransferOrder();
        transferOrder.setMerchantId(trxOrder.getMerchantId());
        transferOrder.setMerchantName(trxOrder.getMerchantName());
        transferOrder.setCustomerId(customerInfo.getId());
        transferOrder.setCustomerName(customerInfo.getRealName());
        transferOrder.setAmount(trxOrder.getActualAmount());
        if (UseBigRateEnum.BIGRATE.getStatus().equals(trxOrder.getUseBigRate())) {
            transferOrder.setTaxRate(trxOrder.getTrxRateBig());
            transferOrder.setCharge(trxOrder.getChargeBig());
        } else {
            transferOrder.setTaxRate(trxOrder.getTaxRate());
            transferOrder.setCharge(trxOrder.getCharge());
        }
        transferOrder.setActualAmount(transferOrder.getAmount().add(transferOrder.getCharge()));
        transferOrder.setBatchNo(trxOrder.getBatchNo());
        transferOrder.setBatchPaymentRecordId(trxOrder.getBatchPaymentRecordId());
        transferOrder.setOrderSerialNum(trxOrder.getOrderSerialNum());
        transferOrder.setTransferSerialNum(ChanPayUtil.generateOutTradeNo());
        transferOrder.setPaymentChannel(trxOrder.getPaymentChannel());
        transferOrder.setStatus(TransferStatusEnum.INIT.getCode());
        transferOrder.setAsyncStatus(AsyncStatusEnum.NOHANDLE.getStatus());
        transferOrder.setCreateTime(DateUtil.date());
        transferOrder.setUpdateTime(DateUtil.date());
        transferOrderMapper.insertSelective(transferOrder);
        return transferOrder;
    }

    @Override
    public void unfreezeOperate(Integer merchantId, BigDecimal settleAmount, BigDecimal tax,
                                MerchantAccountDetail merchantAccountDetail) {
        //冻结金额返回可用余额
        try {
            merchantAccountService.updateMerchantAccountByVersion(merchantId, settleAmount.add(tax),
                    UpdateAccountTypeOfMethod.PAYORMAKEUP.getType());
        } catch (Exception e) {
            logger.info("商户{}冻结金额{}回退可用余额失败，请及时处理", merchantId, settleAmount.add(tax));
            logger.error(e.getMessage());
            return;
        }
        //标记账户变动记录变动失败
        merchantAccountDetail.setStatus(AccountDetailStatusEnum.FAIL.code);
        merchantAccountDetail.setUpdateTime(DateUtil.date());
        merchantAccountDetailMapper.updateByPrimaryKeySelective(merchantAccountDetail);
    }

    /**
     * 获取代付结果
     *
     * @param trxOrder           订单信息
     * @param tianJinPaymentInfo 代付渠道信息
     * @param paymentInfoDTO     代付参数
     * @return
     * @throws Exception 异常
     */
    private TianJinRequestResultDTO getTianJinPaymentResult(TrxOrder trxOrder, TianJinPaymentInfo tianJinPaymentInfo, PaymentInfoDTO paymentInfoDTO) throws Exception {
        TianJinRequestResultDTO tianJinRequestResultDTO = new TianJinRequestResultDTO();
        SalaryOrderDTO salaryOrderDTO = new SalaryOrderDTO();
        salaryOrderDTO.setCustomer_account_uuid(paymentInfoDTO.getAccountId());
        salaryOrderDTO.setServer_user_uuid(paymentInfoDTO.getServerId());
        salaryOrderDTO.setTo_launch_date(DateUtil.format(DateUtil.date(), DatePattern.PURE_DATE_PATTERN));
        salaryOrderDTO.setIs_to_confirm_order("1");
        List<TianJinPaymentDetailDTO> list = new ArrayList<>();
        TianJinPaymentDetailDTO detailDTO = new TianJinPaymentDetailDTO();
        detailDTO.setAmount(trxOrder.getAmount().toString());
        detailDTO.setCard_no(trxOrder.getPayeeBankCard());
        detailDTO.setMobile_no(trxOrder.getPayeeMobile());
        detailDTO.setSocial_no(trxOrder.getPayeeIdCardNo());
        detailDTO.setName(trxOrder.getPayeeName());
        detailDTO.setIdentity_type("身份证");
        if (PaymentEnum.ALIPAY.name().equals(trxOrder.getPaymentChannel())) {
            detailDTO.setBank_branch_name(PaymentEnum.ALIPAY.getDes());
        }
        list.add(detailDTO);
        TianJinNotifyDTO tianJinNotifyDTO = new TianJinNotifyDTO();
        tianJinNotifyDTO.setHost(HOST);
        tianJinNotifyDTO.setPort(PORT);
        tianJinNotifyDTO.setIs_https(TianJinConstants.IS_HTTPS);
        tianJinNotifyDTO.setUrl(TianJinConstants.URL);
        salaryOrderDTO.setCall_back(JSON.toJSON(tianJinNotifyDTO));
        salaryOrderDTO.setTask_list(list);
        //签名
        String sign = TianJinUtil.signatureString(JSON.toJSONString(salaryOrderDTO));
        //请求地址
        String url = TIAN_JIN_HOST + TianJinConstants.SALARY_ORDERS;
        String result = TianJinUtil.sendPost(url, JSON.toJSONString(salaryOrderDTO), trxOrder.getMerchantSerialNum(),
                tianJinPaymentInfo.getToken(), tianJinPaymentInfo.getXinbadaUserUuid(), sign);
        tianJinRequestResultDTO = JSONObject.parseObject(result, TianJinRequestResultDTO.class);
        return tianJinRequestResultDTO;
    }

    /**
     * 判断是否为法人收款
     *
     * @param merchantInfo   商户信息
     * @param paymentInfoDTO 代付信息
     * @return
     */
    private boolean getOrderSheetResult(MerchantInfo merchantInfo, PaymentInfoDTO paymentInfoDTO) {
        boolean orderSheetFlag = false;
        if (StringUtils.isNotEmpty(merchantInfo.getLegalPersonName()) &&
                paymentInfoDTO.getName().equals(merchantInfo.getLegalPersonName())) {
            List<UserList> userList = userListMapper.getListCheckUser(paymentInfoDTO.getName(),
                    paymentInfoDTO.getMerchantId());
            if (CollectionUtils.isNotEmpty(userList)) {
                for (UserList entity : userList) {
                    if (entity.getIdCardNo().equals(paymentInfoDTO.getIdCardNo())) {
                        orderSheetFlag = false;
                        break;
                    } else {
                        orderSheetFlag = true;
                    }
                }
            }
        }
        return orderSheetFlag;
    }

    /**
     * 获取支付结果
     *
     * @param dfFlag                代付结果，true -订单创建成功
     * @param trxOrder              交易信息
     * @param settleAmount          打款金额
     * @param account               账户信息
     * @param tax                   手续费
     * @param merchantAccountConfig 账户设置
     * @param orderSheetFlag        调单标识
     * @param batchPaymentRecord    批量代付信息
     * @param trxOrderId            订单id
     * @param merchantId            商户id
     * @return
     */
    private String doTrxOrder(Boolean dfFlag, TrxOrder trxOrder, BigDecimal settleAmount, MerchantAccount account,
                              Double tax, MerchantAccountConfig merchantAccountConfig, Boolean orderSheetFlag,
                              BatchPaymentRecord batchPaymentRecord, Integer trxOrderId, Integer merchantId,
                              MerchantInfo merchantInfo, boolean passCheckBank3c) {
        String paymentResult = ResultEnum.SUCCESS.name();
        if (dfFlag) {
            trxOrder.setOrderStatus(TrxOrderStatusEnum.PAID.code);
            //更新账户余额以及添加账户变动明细记录
            convertMerchantAccountYunzhongbao(settleAmount, account, trxOrder, tax, merchantInfo);
            //余额预警设置为开启并且余额低于预警设置余额时，发送邮件--todo
        } else {
            if (orderSheetFlag) {
                trxOrder.setOrderStatus(TrxOrderStatusEnum.ORDER_SHEET.code);
            } else {
                trxOrder.setOrderStatus(TrxOrderStatusEnum.PAID_PENDING.code);
                paymentResult = trxOrder.getComment();
            }
        }

        if (batchPaymentRecord != null) {
            batchPaymentRecord.setPaymentActualAmount(batchPaymentRecord.getPaymentActualAmount().
                    add(settleAmount));
            batchPaymentRecordMapper.updateByPrimaryKeySelective(batchPaymentRecord);
            //若为批量代付，则添加批次号和批量代付记录id
            trxOrder.setBatchNo(batchPaymentRecord.getBatchNo());
            trxOrder.setBatchPaymentRecordId(batchPaymentRecord.getId());
            //判断三要素验证是否通过，未通过挂起订单
            if (!passCheckBank3c) {
                trxOrder.setOrderStatus(TrxOrderStatusEnum.PAID_PENDING.code);
                trxOrder.setComment("三要素未通过，订单挂起");
                logger.info("三要素未通过，订单挂起，trxOrder={}", JSON.toJSONString(trxOrder));
                paymentResult = "有订单三要素未通过，订单被挂起";
            }
        }
        if (trxOrderId == null) {
            //添加代付记录
            trxOrderMapper.insertUseGeneratedKeys(trxOrder);
        } else {
            trxOrderMapper.updateByPrimaryKeySelective(trxOrder);
        }
        if (orderSheetFlag) {
            convertOrderSheet(merchantId, trxOrder);
        }
        return paymentResult;
    }

    /**
     * 判断商户所属渠道 1 为云众包，2 为天津 ，3 网商
     *
     * @return
     */
    private int getMerchantChannel(Integer merchantId) {
        PaymentMerchantInfo paymentMerchantInfo =
                paymentMerchantInfoMapper.selectByMerchantIdAndChannel(merchantId, PaymentChannelEnum.YUNZB.name());
        TianJinPaymentInfo tianJinPaymentInfo =
                tianJinPaymentInfoMapper.getTianJinPaymentInfoByMerchantId(merchantId);
        if (paymentMerchantInfo != null) {
            return 1;
        } else if (null != tianJinPaymentInfo) {
            return 2;
        }
        return 3;
    }

}
