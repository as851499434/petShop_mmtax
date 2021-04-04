package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.IMerchantInfoService;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.business.util.MerchantInfoUtil;
import com.mmtax.business.yunzbdto.YunZBNewMerchantInfoDTO;
import com.mmtax.common.annotation.DataScope;
import com.mmtax.common.constant.AddMerchantInfoConstants;
import com.mmtax.common.constant.PaymentTypeConstants;
import com.mmtax.common.constant.TaxSounrceCompanyNameConstants;
import com.mmtax.common.constant.WkycConstants;
import com.mmtax.common.context.ApiContext;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.*;
import com.mmtax.common.utils.http.HttpUtils;
import com.mmtax.common.utils.redis.RedisTimeConstans;
import com.mmtax.common.utils.redis.RedisUtil;
import com.mmtax.common.utils.yunzbutil.Response;
import com.mmtax.common.utils.yunzbutil.YunZBConstants;
import com.mmtax.common.utils.yunzbutil.YunZBUtil;
import com.mmtax.system.domain.InstitutionInfo;
import com.mmtax.system.mapper.InstitutionInfoMapper;
import com.mmtax.system.mapper.SysUserMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 商户添加 服务层接口实现
 *
 * @author yuanligang
 * @date 2019/7/9
 */
@Service
public class MerchantInfoServiceImpl extends CommonServiceImpl implements IMerchantInfoService {
    /**
     * 云众包返回成功字段
     */
    private static final String RESULT_CODE = "result_code";
    @Resource
    MerchantInfoMapper merchantInfoMapper;
    @Resource
    MerchantAccountMapper merchantAccountMapper;
    @Resource
    CooperationMapper cooperationMapper;
    @Resource
    RiskManagementConfigMapper riskManagementConfigMapper;
    @Resource
    InvoiceInfoMapper invoiceInfoMapper;
    @Resource
    AddressMapper addressMapper;
    @Resource
    MealInfoMapper mealInfoMapper;
    @Resource
    TrxOrderMapper trxOrderMapper;
    @Resource
    private InstitutionInfoMapper institutionInfoMapper;
    @Resource
    MerchantAccountDetailMapper merchantAccountDetailMapper;
    @Resource
    BatchPaymentDetailMapper batchPaymentDetailMapper;
    @Resource
    RechargeRecordMapper rechargeRecordMapper;
    @Autowired
    private ApiContext apiContext;
    @Resource
    private MerchantAccountConfigMapper merchantAccountConfigMapper;
    @Resource
    private SettlementInfoMapper settlementInfoMapper;
    @Resource
    private CustomerSupportMapper customerSupportMapper;
    @Resource
    private MerchantKeyMapper merchantKeyMapper;
    @Resource
    private TianJinPaymentInfoMapper tianJinPaymentInfoMapper;
    @Autowired
    private IOnlineBankService iOnlineBankService;
    @Resource
    private MerchantInvoiceSubjectCorrelationMapper merchantInvoiceSubjectCorrelationMapper;
    @Resource
    BatchPaymentRecordMapper batchPaymentRecordMapper;
    @Resource
    private SysUserMapper userMapper;
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Autowired
    private CustomerAccountDetailMapper customerAccountDetailMapper;
    @Resource
    private MerchantTaskInfoMapper merchantTaskInfoMapper;
    @Resource
    BatchTaskDetailMapper batchTaskDetailMapper;
    @Resource
    CustomerTaskMapper customerTaskMapper;

    @Value("${wkyc.agent.name.send.url}")
    private String getAgentNameURL;

    @Value("${wkyc.dev.key}")
    private String appKey;

    @Value("${wkyc.push.merchant.info.url}")
    private String pushMerchantInfoURL;

    /**
     * 商户添加
     *
     * @param addMerchantInfoDTO 商户添加包装类
     * @return 保存结果
     * @throws BusinessException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMerchantInfo(AddMerchantInfoDTO addMerchantInfoDTO) throws Exception {
        if (addMerchantInfoDTO == null) {
            throw new BusinessException("请求参数列表为空");
        }
        // 商户信息
        MerchantInfo merchantInfo = addMerchantInfoDTO.getMerchantInfo();
        // 发票信息
        InvoiceInfo invoiceInfo = addMerchantInfoDTO.getInvoiceInfo();
        // 合作信息
        Cooperation cooperation = addMerchantInfoDTO.getCooperation();
        // 派单信息
        List<MerchantTaskInfo> merchantTaskInfoList = addMerchantInfoDTO.getMerchantTaskInfoList();
        // 风险信息
        RiskManagementConfig riskManagementConfig = addMerchantInfoDTO.getRiskManagementConfig();
        // 地址
        Address address = addMerchantInfoDTO.getAddress();
        // 账户设置
        MerchantAccountConfig merchantAccountConfig = addMerchantInfoDTO.getMerchantAccountConfig();
        //结算信息
        SettlementInfo settlementInfo = addMerchantInfoDTO.getSettlementInfo();
        //客户支持
        CustomerSupport customerSupport = addMerchantInfoDTO.getCustomerSupport();
        //签约岗位集合
        List<TaskInfo> taskInfos = addMerchantInfoDTO.getTaskInfos();
        /*   商户信息处理开始    */
        if (merchantInfo == null) {
            throw new BusinessException("商户信息为空");
        }

        String merchantName = merchantInfo.getMerchantName();
        String password = merchantInfo.getPassword();
        String businessLicenseCode = merchantInfo.getBusinessLicenseCode();
        String businessLicenseImg = merchantInfo.getBusinessLicenseImg();
        String contractNo = merchantInfo.getContractNo();
        String legalPersonName = merchantInfo.getLegalPersonName();
        String agentNumber = merchantInfo.getAgentNumber();
        //关键参数校验标识
        boolean sign = StringUtils.isEmpty(merchantName) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(businessLicenseCode) || StringUtils.isEmpty(businessLicenseImg)
                || StringUtils.isEmpty(contractNo) || StringUtils.isEmpty(legalPersonName);
        if (sign) {
            throw new BusinessException("商户关键信息缺失");
        }
        if (merchantInfoMapper.checkMerchantNameUnique(merchantName) > 0) {
            throw new BusinessException("商户名称已存在");
        }

        merchantInfo.setCreateTime(DateUtil.date());
        String merchantCode = MerchantIdUtil.getMerchantId();
        // 防止重复商编
        boolean isUnique = AddMerchantInfoConstants.MERCHANT_CODE_NOT_UNIQUE.
                equals(checkMerchantCodeUnique(merchantCode));
        while (isUnique) {
            merchantCode = MerchantIdUtil.getMerchantId();
            isUnique = AddMerchantInfoConstants.MERCHANT_CODE_NOT_UNIQUE.equals(checkMerchantCodeUnique(merchantCode));
        }

        if(StringUtils.isNotEmpty(agentNumber)){
            //获取悟空云创的AgentName
            logger.info("获取代理商姓名，开始发送请求");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("agentNumber",agentNumber);
            String callbackResult = HttpUtils.sendPost(getAgentNameURL, jsonObject.toJSONString());
            logger.info("获取代理商姓名成功");
            //返回结果
            GetAgentNameResultDTO getAgentNameResultDTO = JSONObject.parseObject(callbackResult, GetAgentNameResultDTO.class);
            logger.info("调用此接口：{}，返回的信息：{}",getAgentNameURL,getAgentNameResultDTO);
            if(StringUtils.isNull(getAgentNameResultDTO)||!WkycConstants.AGENT_NUMBER_SUCCESS.equals(getAgentNameResultDTO.getCode())){
                //logger.info("获取代理商名称失败：{}",getAgentNameResultDTO.getDescription());
                throw new BusinessException("获取代理商姓名失败");
            }
            //返回的商户名称
            merchantInfo.setAgentName(getAgentNameResultDTO.getAgentName());
            merchantInfo.setAgentNumber(agentNumber);
        }
        merchantInfo.setSalt(PasswordUtil.randomSalt());
        merchantInfo.setPassword(PasswordUtil.password(password, merchantCode, merchantInfo.getSalt()));
        merchantInfo.setMerchantCode(merchantCode);
        merchantInfo.setType(MerchantTypeEnum.OFFICIAL_MERCHANTS.code);
        merchantInfo.setStatus(MerchantStatusEnum.LOCKED.name());
        merchantInfoMapper.insertUseGeneratedKeys(merchantInfo);
        // 外键 涉及六张表
        Integer merchantId = merchantInfo.getId();
        if (merchantId == null) {
            throw new BusinessException("商户信息保存失败");
        }


        /*   商户信息处理结束    */

        /*  商户信息初始化开始 */
        MerchantAccount merchantAccount = new MerchantAccount();
        merchantAccount.setMerchantId(merchantId);
        merchantAccount.setAmount(AddMerchantInfoConstants.INIT_AMOUNT);
        merchantAccount.setFrozenAmount(AddMerchantInfoConstants.INIT_AMOUNT);
        merchantAccount.setUsableAmount(AddMerchantInfoConstants.INIT_AMOUNT);
        merchantAccount.setUsableAccumulatedReturnAmount(AddMerchantInfoConstants.INIT_AMOUNT);
        merchantAccount.setAccumulatedReturnAmount(AddMerchantInfoConstants.INIT_AMOUNT);
        merchantAccount.setStatus(MerOrCusAccountStatusEnum.ACTIVE.name());
        merchantAccount.setCreateTime(DateUtil.date());
        merchantAccount.setVersion(1);
        merchantAccountMapper.insert(merchantAccount);
        /*  商户信息初始化结束 */

        /* 合作信息处理开始  */
        addCooperation(cooperation, merchantId,taskInfos);
        /*  合作信息处理结束 */

        /* 派单信息处理开始  */
        if (SwitchEnum.ON.getCode().equals(cooperation.getSendOrderSwitch())) {
            addMerchantTaskInfoList(merchantTaskInfoList, merchantId);
        }
        /*  派单信息处理结束 */

        /* 风控信息处理开始 */
        if (riskManagementConfig == null) {
            throw new BusinessException("风控信息不存在");
        }

        BigDecimal singleQuotaConfig = riskManagementConfig.getSingleQuotaConfig();
        if (singleQuotaConfig == null || singleQuotaConfig.equals(AddMerchantInfoConstants.INIT_AMOUNT)) {
            throw new BusinessException("全网单人月累计金额限制未设置");
        }

        BigDecimal temporaryQuota = riskManagementConfig.getSingleQuotaConfig();
        if (temporaryQuota == null || temporaryQuota.equals(AddMerchantInfoConstants.INIT_AMOUNT)) {
            throw new BusinessException("临时额度未设置");
        }

        riskManagementConfig.setCreateTime(DateUtil.date());
        riskManagementConfig.setMerchantId(merchantId);
        riskManagementConfigMapper.insert(riskManagementConfig);

        /* 发票信息处理开始 */
        if (invoiceInfo == null) {
            throw new BusinessException("发票信息为空");
        }

        String companyName = invoiceInfo.getCompanyName();
        if (StringUtils.isEmpty(companyName)) {
            throw new BusinessException("企业名称不能为空");
        }

        String taxpayerIdentificationNumber = invoiceInfo.getTaxpayerIdentificationNumber();
        if (StringUtils.isEmpty(taxpayerIdentificationNumber)) {
            throw new BusinessException("纳税人识别号为空");
        }

        String bankName = invoiceInfo.getBankName();
        if (StringUtils.isEmpty(bankName)) {
            throw new BusinessException("开户行不能为空");
        }

        String bankNumber = invoiceInfo.getBankNumber();
        if (StringUtils.isEmpty(bankNumber)) {
            throw new BusinessException("账号不能为空");
        }

        invoiceInfo.setMerchantId(merchantId);
        invoiceInfo.setCreateTime(DateUtil.date());
        invoiceInfoMapper.insert(invoiceInfo);

        /* 发票信息处理结束 */


        /*发票科目信息处理开始*/
        //前端传入的科目id集合
        List<Integer> invoiceSubjectIdList = addMerchantInfoDTO.getInvoiceSubjectIdList();
        if (merchantInfoMapper.selectContent(addMerchantInfoDTO.getTaxSourceId()).size() != 0) {
            for (Integer invoiceSubjectId : invoiceSubjectIdList) {
                if (invoiceSubjectId == null) {
                    throw new BusinessException("请选择发票科目");
                }
            }
            for (int i = 0; i < invoiceSubjectIdList.size(); i++) {
                MerchantInvoiceSubjectCorrelation merchantInvoiceSubjectCorrelation = new MerchantInvoiceSubjectCorrelation();
                if (i == 0) {
                    merchantInvoiceSubjectCorrelation.setIsDefault(1);
                }
                //发票科目信息
                merchantInvoiceSubjectCorrelation.setCreateTime(DateUtil.date());
                merchantInvoiceSubjectCorrelation.setMerchantInfoId(invoiceInfo.getMerchantId());
                merchantInvoiceSubjectCorrelation.setInvoiceSubjectId(invoiceSubjectIdList.get(i));
                merchantInvoiceSubjectCorrelationMapper.insertSelective(merchantInvoiceSubjectCorrelation);
            }
        }
        /*发票科目信息处理结束*/

        /*  邮寄地址信息处理开始 */
        if (address == null) {
            throw new BusinessException("地址信息为空");
        }

        String addressDetail = address.getAddress();
        String mobile = address.getMobile();
        if (StringUtils.isEmpty(addressDetail) || StringUtils.isEmpty(mobile)) {
            throw new BusinessException("地址关键信息缺失");
        }

        address.setCreateTime(DateUtil.date());
        address.setMerchantId(merchantId);
        address.setStatus(0);
        addressMapper.insert(address);
        /*  邮寄地址信息处理结束 */

        /* 账户配置信息处理开始 */
        if (merchantAccountConfig == null) {
            throw new BusinessException("账户配置信息为空");
        }
        StringBuffer balanceRemindEmail = new StringBuffer();
        StringBuffer rechargeRemindEmail = new StringBuffer();
        merchantAccountConfig.getBalanceRemindEmails()
                .forEach(email -> balanceRemindEmail.append(email).append(","));
        merchantAccountConfig.getRechargeRemindEmails()
                .forEach(email -> rechargeRemindEmail.append(email).append(","));
        merchantAccountConfig.setBalanceRemindEmail(balanceRemindEmail.toString());
        merchantAccountConfig.setRechargeRemindEmail(balanceRemindEmail.toString());
        merchantAccountConfig.setMerchantId(merchantId);
        merchantAccountConfig.setCreateTime(DateUtil.date());
        merchantAccountConfigMapper.insert(merchantAccountConfig);

        /* 账户配置信息处理结束 */

        /*结算信息*/
        settlementInfo.setCreateTime(DateUtil.date());
        settlementInfo.setUpdateTime(DateUtil.date());
        settlementInfo.setMerchantId(merchantId);
        settlementInfoMapper.insert(settlementInfo);
        //客户支持
        customerSupport.setMerchantId(merchantId);
        customerSupport.setCreateTime(DateUtil.date());
        customerSupport.setUpdateTime(DateUtil.date());
        customerSupportMapper.insert(customerSupport);

        //生成秘钥信息
        MerchantKey merchantKey = new MerchantKey();
        merchantKey.setDesKey(UUID.randomUUID().toString().replace("-", ""));
        merchantKey.setAppKey(UUID.randomUUID().toString().replace("-", ""));
        merchantKey.setMerchantId(merchantId);
        merchantKey.setCreateTime(DateUtil.date());
        merchantKey.setUpdateTime(DateUtil.date());
        merchantKeyMapper.insertSelective(merchantKey);

        //查询税源地信息
        TaxSounrceCompany taxSounrceCompany =
                taxSounrceCompanyMapper.selectByPrimaryKey(addMerchantInfoDTO.getTaxSourceId());
        if (PaymentChannelEnum.YUNZB.name().equals(taxSounrceCompany.getChannel())) {
            //将商户信息上传到云众包渠道
            YunZBNewMerchantInfoDTO yunZBMerchantInfoDTO = convertYunZBMerchantInfoDTO(addMerchantInfoDTO);
            logger.info("上传云众包商户信息为：{}", net.sf.json.JSONObject.fromObject(yunZBMerchantInfoDTO));
            yunZBMerchantInfoDTO.setMchId(taxSounrceCompany.getMerchantNo());
            Response response = YunZBUtil.remoteInvoke(YunZBConstants.OPEN_ACC,
                    yunZBMerchantInfoDTO, taxSounrceCompany.getSecretKey());
            JSONObject tokenResult = JSONObject.parseObject(response.getRespData());
            //判断是否添加成功
            if (YunZBConstants.RESULT_CODE.equals(tokenResult.get(RESULT_CODE)) &&
                    YunZBConstants.RESULT_CODE.equals(tokenResult.get(YunZBConstants.RETURN_CODE))) {
                PaymentMerchantInfo paymentMerchantInfo = new PaymentMerchantInfo();
                paymentMerchantInfo.setMerchantId(merchantId);
                paymentMerchantInfo.setChannel(PaymentChannelEnum.YUNZB.name());
                paymentMerchantInfo.setCreateTime(DateUtil.date());
                paymentMerchantInfo.setMerchantNo(tokenResult.get(YunZBConstants.SUB_MCH_ID).toString());
                paymentMerchantInfo.setTaxSourceId(addMerchantInfoDTO.getTaxSourceId());
                paymentMerchantInfoMapper.insertSelective(paymentMerchantInfo);
                //上传图片
                tokenResult = uploadYunZBImg(addMerchantInfoDTO, paymentMerchantInfo, taxSounrceCompany);
                if (YunZBConstants.RESULT_CODE.equals(tokenResult.getString(YunZBConstants.RETURN_CODE))) {
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    throw new BusinessException(tokenResult.getString(YunZBConstants.RETURN_MSG).toString());
                }
                if (StringUtils.isNotEmpty(tokenResult.get(YunZBConstants.REQUEST_CODE).toString()) &&
                        YunZBConstants.RESULT_CODE.equals(tokenResult.getString(YunZBConstants.REQUEST_CODE))) {
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    throw new BusinessException(tokenResult.getString(YunZBConstants.REQUEST_MSG).toString());
                }
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                if (StringUtils.isNotEmpty(tokenResult.getString(YunZBConstants.RETURN_CODE))) {
                    throw new BusinessException(tokenResult.getString(YunZBConstants.RETURN_MSG));
                }
                if (StringUtils.isNotEmpty(tokenResult.getString(YunZBConstants.REQUEST_CODE))) {
                    throw new BusinessException(tokenResult.getString(YunZBConstants.REQUEST_MSG));
                }
            }
        } else if (PaymentChannelEnum.ONLINE.name().equals(taxSounrceCompany.getChannel())) {
            OnlinePaymentInfo onlinePaymentInfo = iOnlineBankService.registerAccount(merchantId, merchantInfo,
                    addMerchantInfoDTO.getTaxSourceId());
            merchantInfo.setStatus(MerchantStatusEnum.ACTIVE.name());
            merchantInfoMapper.updateByPrimaryKeySelective(merchantInfo);
        } else {
            //添加天津渠道对接信息
            TianJinPaymentInfo tianJinPaymentInfo = addMerchantInfoDTO.getTianJinPaymentInfo();
            tianJinPaymentInfo.setMerchantId(merchantId);
            tianJinPaymentInfo.setCreateTime(DateUtil.date());
            tianJinPaymentInfo.setUpdateTime(DateUtil.date());
            tianJinPaymentInfoMapper.insertSelective(tianJinPaymentInfo);
            merchantInfo.setStatus(MerchantStatusEnum.ACTIVE.name());
            merchantInfoMapper.updateByPrimaryKeySelective(merchantInfo);
        }
        if(StringUtils.isNotEmpty(agentNumber)){
            //悟空云创所需信息
            MerchantInfoPushedWkycDTO infoPushedWkycDTO = new MerchantInfoPushedWkycDTO();
            infoPushedWkycDTO.setAgentNumber(merchantInfo.getAgentNumber());
            infoPushedWkycDTO.setMerchantCode(merchantInfo.getMerchantCode());
            infoPushedWkycDTO.setMerchantName(merchantInfo.getMerchantName());
            infoPushedWkycDTO.setOrdinaryRate(cooperation.getSingleServiceRate());
            infoPushedWkycDTO.setBigRate(cooperation.getSingleServiceBigRate());
            infoPushedWkycDTO.setEmail(merchantInfo.getEmail());
            infoPushedWkycDTO.setMobileNo(merchantInfo.getContactsMobile());
            //发送商户信息到悟空云创
            String content = JSON.toJSONString(infoPushedWkycDTO);
            content = MmtaxSign.addSignByJsonStr(content,appKey);
            infoPushedWkycDTO.setSign(content);
            String callbackString = HttpUtils.sendPost(pushMerchantInfoURL, JSON.toJSONString(infoPushedWkycDTO));
            ChangeTaxRateResultDTO changeTaxRateResultDTO1 = JSONObject.parseObject(callbackString, ChangeTaxRateResultDTO.class);
            if (!WkycConstants.PUSH_MERCHANT_INFO_SUCCESS.equals(changeTaxRateResultDTO1.getCode())){
                logger.info("商户信息推送失败：{}",changeTaxRateResultDTO1.getDescription());
                throw new BusinessException("商户信息推送失败，请联系管理员"+changeTaxRateResultDTO1.getCode());
            }
        }
    }

    /**
     * 添加派单信息
     * @param taskInfoList 任务列表
     * @param merchantId 商户id
     */
    private void addMerchantTaskInfoList(List<MerchantTaskInfo> taskInfoList, Integer merchantId) {
        if (CollectionUtils.isNotEmpty(taskInfoList)) {
            // 校验数据
            checkMerchantInfoTask(taskInfoList);
            // 添加数据
            for (MerchantTaskInfo info : taskInfoList) {
                info.setMerchantId(merchantId);
                info.setCreateTime(DateUtil.date());
                info.setUpdateTime(DateUtil.date());
                merchantTaskInfoMapper.insertSelective(info);
            }
        }
    }

    /**
     * 校验派单信息
     * @param taskInfoList 派单信息
     */
    private void checkMerchantInfoTask(List<MerchantTaskInfo> taskInfoList) {
        List<String> taskIdStrList = new ArrayList<>();
        for (MerchantTaskInfo info : taskInfoList) {
            if (info.getTaskId() == null) {
                throw new BusinessException("请选择任务名称");
            }
            if (StringUtils.isEmpty(info.getTaskName())) {
                throw new BusinessException("任务名称不可为空");
            }
            if (taskIdStrList.contains(info.getTaskId().toString())) {
                throw new BusinessException("任务名称：" + info.getTaskName() + "选择重复");
            }
            if (StringUtils.isEmpty(info.getTaskInfo())) {
                throw new BusinessException("请填写任务简介");
            }
            if (info.getTaskInfo().trim().length() > 200) {
                throw new BusinessException("任务名称：" + info.getTaskName() +
                        "的任务简介字数不能超过200，请重新编辑任务简介");
            }
            taskIdStrList.add(info.getTaskId().toString());
        }
    }

    /**
     * 添加合作信息
     * @param cooperation 合作信息参数
     * @param merchantId 商户id
     */
    private void addCooperation(Cooperation cooperation, Integer merchantId, List<TaskInfo> taskInfos) {
        MerchantInfoUtil.checkCooperation(cooperation);
        BigDecimal singleServiceRate = cooperation.getSingleServiceRate();
        if (singleServiceRate == null || singleServiceRate.equals(AddMerchantInfoConstants.INIT_AMOUNT)) {
            throw new BusinessException("未设置相关服务费率");
        }
        Integer mealInfoId = cooperation.getMealInfoId();
        // TODO 后期需要与套餐表关联查询对应ID是否存在
        if (mealInfoId == null) {
            throw new BusinessException("套餐ID不存在");
        }
        Integer rateSwitch = cooperation.getRateSwitch();
        if (rateSwitch == null) {
            throw new BusinessException("未设置服务费开关");
        }
        if (BigRateSwitchEnum.BIDRATEOPEN.getStatus().equals(cooperation.getBigRateSwitch())) {
            if (null == cooperation.getSingleServiceBigRate()) {
                throw new BusinessException("大额费率不可为空");
            }
            if (cooperation.getSingleServiceBigRate().compareTo(singleServiceRate) < 0) {
                throw new BusinessException("大额费率需大于普通费率");
            }
        }
        if (ChannelOpenEnum.OPEN.getStatus().equals(cooperation.getBankChannel())) {
            BigDecimal bankSingleQuota = cooperation.getBankSingleQuota();
            if (bankSingleQuota == null || bankSingleQuota.equals(AddMerchantInfoConstants.INIT_AMOUNT)) {
                throw new BusinessException("未设置单笔限额");
            }
        }
        if (ChannelOpenEnum.OPEN.getStatus().equals(cooperation.getAlipayChannel())) {
            BigDecimal alipaySingleQuota = cooperation.getAlipaySingleQuota();
            if (alipaySingleQuota == null || alipaySingleQuota.equals(AddMerchantInfoConstants.INIT_AMOUNT)) {
                throw new BusinessException("未设置单笔限额");
            }
        }
        if (ChannelOpenEnum.OPEN.getStatus().equals(cooperation.getWechatChannel())) {
            BigDecimal wechatSingleQuota = cooperation.getWechatSingleQuota();
            if (wechatSingleQuota == null || wechatSingleQuota.equals(AddMerchantInfoConstants.INIT_AMOUNT)) {
                throw new BusinessException("未设置单笔限额");
            }
        }
        if(SigningTypeEnum.WECHAT.getValue().equals(cooperation.getSigningType())){
            cooperation.setCustomerWithdrawSwitch(SwitchEnum.ON.getCode());
        }else {
            cooperation.setCustomerWithdrawSwitch(SwitchEnum.OFF.getCode());
        }
        if (cooperation.getSendOrderSwitch() == null) {
            throw new BusinessException("未设置是否为派单商户");
        }
        // 派单模式处理
        if (SwitchEnum.ON.getCode().equals(cooperation.getSendOrderSwitch())) {
            if (cooperation.getSendOrderMode() == null) {
                cooperation.setSendOrderMode(SendOrderModeEnum.MANUAL.getCode());
            }
        }
        // 打款渠道校验
        boolean offAllFlag = (cooperation.getAlipayChannel() == null ||
                cooperation.getAlipayChannel().equals(SwitchEnum.OFF.getCode()))
                && (cooperation.getBankChannel() == null ||
                cooperation.getBankChannel().equals(SwitchEnum.OFF.getCode()))
                && (cooperation.getWechatChannel() == null ||
                cooperation.getWechatChannel().equals(SwitchEnum.OFF.getCode()));
        if (offAllFlag) {
            throw new BusinessException("请至少选择一种打款渠道");
        }
        //非纸质签约
        if(!SigningTypeEnum.PAPER.getValue().equals(cooperation.getSigningType())){
            //签约岗位个数校验
            if(StringUtils.isEmpty(taskInfos)){
                throw new BusinessException("请至少选择一个签约岗位");
            }
            //获取任务Id集合
            List<Integer> taskInfoIds = new ArrayList<>();
            for (TaskInfo taskInfo:taskInfos){
                taskInfoIds.add(taskInfo.getId());
            }
            //重复判断
            Stream<Integer> distinct = taskInfoIds.stream().distinct();
            List<Integer> collect = distinct.collect(Collectors.toList());
            if(collect.size()<taskInfoIds.size()){
                throw new BusinessException("岗位名不能重复");
            }
            for (TaskInfo taskInfo:taskInfos){
                if(StringUtils.isNull(taskInfo.getId())){
                    throw new BusinessException("签约岗位名不正确，请重新选择");
                }
                //商户任务信息
                MerchantTaskInfo merchantTaskInfo = new MerchantTaskInfo();
                merchantTaskInfo.setMerchantId(merchantId);
                merchantTaskInfo.setTaskId(taskInfo.getId());
                merchantTaskInfo.setTaskName(taskInfo.getTaskName());
                merchantTaskInfoMapper.insertSelective(merchantTaskInfo);
            }
        }
        cooperation.setMerchantId(merchantId);
        cooperation.setCreateTime(DateUtil.date());
        cooperationMapper.insert(cooperation);
    }

    @Override
    public Integer changeTaxSource(Integer merchantId, Integer taxSourceId) {
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);
        if (null == merchantInfo) {
            throw new BusinessException("商户不存在");
        }
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
        if (null == onlinePaymentInfo) {
            throw new BusinessException("未查询到改商户注册网商的信息");
        }
        if (onlinePaymentInfo.getTaxSourceCompanyId().equals(taxSourceId)) {
            throw new BusinessException("该商户已注册在该税源地");
        }
        //秘钥信息
        MerchantKey merchantKey = new MerchantKey();
        merchantKey.setMerchantId(merchantId);
        merchantKey = merchantKeyMapper.selectOne(merchantKey);
        if (null == merchantKey) {
            throw new BusinessException("未找到该商户秘钥信息");
        }
        // 发票信息
        InvoiceInfo invoiceInfo = invoiceInfoMapper.getInvoiceInfoByMerchantId(merchantId);
        // 发票关联
        MerchantInvoiceSubjectCorrelation correlationQuery = new MerchantInvoiceSubjectCorrelation();
        correlationQuery.setMerchantInfoId(merchantId);
        List<MerchantInvoiceSubjectCorrelation> correlationList =
                merchantInvoiceSubjectCorrelationMapper.select(correlationQuery);

        // 商户任务信息
        MerchantTaskInfo merchantTaskInfo = new MerchantTaskInfo();
        merchantTaskInfo.setMerchantId(merchantId);
        List<MerchantTaskInfo> taskInfoList = merchantTaskInfoMapper.select(merchantTaskInfo);

        // 合作信息
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(merchantId);
        cooperation = cooperationMapper.selectOne(cooperation);
        // 风险信息
        RiskManagementConfig riskManagementConfig = new RiskManagementConfig();
        riskManagementConfig.setMerchantId(merchantId);
        riskManagementConfig = riskManagementConfigMapper.selectOne(riskManagementConfig);
        // 地址
        Address address = addressMapper.getAddressByMerchantId(merchantId);
        // 账户设置
        MerchantAccountConfig merchantAccountConfig = merchantAccountConfigMapper.getMerchantConfigByMerchantId(merchantId);
        //结算信息
        SettlementInfo settlementInfo = settlementInfoMapper.getSettleByMerchantId(merchantId);
        //客户支持
        CustomerSupport customerSupport = customerSupportMapper.getCoustomerSupportBymerchantId(merchantId);

        //新增商户
        merchantInfo.setId(null);
        merchantInfo.setProviderId(null);
        merchantInfo.setCreateTime(DateUtil.date());
        merchantInfo.setUpdateTime(DateUtil.date());
        merchantInfoMapper.insertUseGeneratedKeys(merchantInfo);
        Integer newMerchantId = merchantInfo.getId();

        /*  商户账户信息初始化开始 */
        MerchantAccount merchantAccount = new MerchantAccount();
        merchantAccount.setMerchantId(newMerchantId);
        merchantAccount.setAmount(AddMerchantInfoConstants.INIT_AMOUNT);
        merchantAccount.setFrozenAmount(AddMerchantInfoConstants.INIT_AMOUNT);
        merchantAccount.setUsableAmount(AddMerchantInfoConstants.INIT_AMOUNT);
        merchantAccount.setUsableAccumulatedReturnAmount(AddMerchantInfoConstants.INIT_AMOUNT);
        merchantAccount.setAccumulatedReturnAmount(AddMerchantInfoConstants.INIT_AMOUNT);
        merchantAccount.setStatus(MerOrCusAccountStatusEnum.ACTIVE.name());
        merchantAccount.setCreateTime(DateUtil.date());
        merchantAccount.setVersion(1);
        merchantAccountMapper.insert(merchantAccount);
        /*  商户账户信息初始化结束 */

        if (null != invoiceInfo) {
            invoiceInfo.setId(null);
            invoiceInfo.setMerchantId(newMerchantId);
            invoiceInfo.setProviderId(null);
            invoiceInfo.setCreateTime(DateUtil.date());
            invoiceInfo.setUpdateTime(DateUtil.date());
            invoiceInfoMapper.insertSelective(invoiceInfo);
        }

        // 添加商户发票科目关联信息
        if (CollectionUtils.isNotEmpty(correlationList)) {
            for (MerchantInvoiceSubjectCorrelation correlation : correlationList) {
                correlation.setId(null);
                correlation.setMerchantInfoId(newMerchantId);
                correlation.setCreateTime(DateUtil.date());
                correlation.setUpdateTime(DateUtil.date());
                merchantInvoiceSubjectCorrelationMapper.insertSelective(correlation);
            }
        }

        // 添加商户任务信息记录
        if(CollectionUtils.isNotEmpty(taskInfoList)) {
            for (MerchantTaskInfo taskInfo : taskInfoList) {
                taskInfo.setId(null);
                taskInfo.setMerchantId(newMerchantId);
                taskInfo.setProviderId(null);
                taskInfo.setCreateTime(DateUtil.date());
                taskInfo.setUpdateTime(DateUtil.date());
                merchantTaskInfoMapper.insertSelective(taskInfo);
            }
        }

        if (null != cooperation) {
            cooperation.setId(null);
            cooperation.setMerchantId(newMerchantId);
            cooperation.setProviderId(null);
            cooperation.setCreateTime(DateUtil.date());
            cooperation.setUpdateTime(DateUtil.date());
            cooperationMapper.insertSelective(cooperation);
        }

        if (null != cooperation) {
            riskManagementConfig.setId(null);
            riskManagementConfig.setMerchantId(newMerchantId);
            riskManagementConfig.setProviderId(null);
            riskManagementConfig.setCreateTime(DateUtil.date());
            riskManagementConfig.setUpdateTime(DateUtil.date());
            riskManagementConfigMapper.insertSelective(riskManagementConfig);
        }

        if (null != address) {
            address.setId(null);
            address.setMerchantId(newMerchantId);
            address.setProviderId(null);
            address.setCreateTime(DateUtil.date());
            address.setUpdateTime(DateUtil.date());
            addressMapper.insertSelective(address);
        }

        if (null != merchantAccountConfig) {
            merchantAccountConfig.setId(null);
            merchantAccountConfig.setMerchantId(newMerchantId);
            merchantAccountConfig.setProviderId(null);
            merchantAccountConfig.setCreateTime(DateUtil.date());
            merchantAccountConfig.setUpdateTime(DateUtil.date());
            merchantAccountConfigMapper.insertSelective(merchantAccountConfig);
        }

        if (null != settlementInfo) {
            settlementInfo.setId(null);
            settlementInfo.setMerchantId(newMerchantId);
            settlementInfo.setProviderId(null);
            settlementInfo.setCreateTime(DateUtil.date());
            settlementInfo.setUpdateTime(DateUtil.date());
            settlementInfoMapper.insertSelective(settlementInfo);
        }

        if (null != customerSupport) {
            customerSupport.setId(null);
            customerSupport.setMerchantId(newMerchantId);
            customerSupport.setProviderId(null);
            customerSupport.setCreateTime(DateUtil.date());
            customerSupport.setUpdateTime(DateUtil.date());
            customerSupportMapper.insertSelective(customerSupport);
        }

        // 生成秘钥信息
        merchantKey.setId(null);
        merchantKey.setMerchantId(newMerchantId);
        merchantKey.setProviderId(null);
        merchantKey.setCreateTime(DateUtil.date());
        merchantKey.setUpdateTime(DateUtil.date());
        merchantKeyMapper.insertSelective(merchantKey);

        //查询税源地信息
        TaxSounrceCompany taxSounrceCompany = taxSounrceCompanyMapper.selectByPrimaryKey(taxSourceId);
        if (null == taxSounrceCompany) {
            throw new BusinessException("未找到需要切换的税源地");
        }

        if (PaymentChannelEnum.ONLINE.name().equals(taxSounrceCompany.getChannel())) {
            OnlinePaymentInfo newOnlinePaymentInfo = iOnlineBankService.registerAccount(newMerchantId, merchantInfo,
                    taxSourceId);

            merchantInfo.setStatus(MerchantStatusEnum.ACTIVE.name());
            merchantInfoMapper.updateByPrimaryKeySelective(merchantInfo);
            MerchantInfo oldMerchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);
            oldMerchantInfo.setMerchantCode(oldMerchantInfo.getMerchantCode() + "-888888");
            oldMerchantInfo.setMerchantName(oldMerchantInfo.getMerchantName() + "-888888");
            oldMerchantInfo.setEmail(oldMerchantInfo.getEmail() + "-888888");
            oldMerchantInfo.setUpdateTime(DateUtil.date());
            merchantInfoMapper.updateByPrimaryKeySelective(oldMerchantInfo);
            onlinePaymentInfo.setMerchantNo(onlinePaymentInfo.getMerchantNo() + "-888888");
            onlinePaymentInfoMapper.updateByPrimaryKeySelective(onlinePaymentInfo);
        }

        return newMerchantId;
    }

    /**
     * 校验商户编号称是否唯一
     *
     * @param merchantCode 商户编号
     * @return 结果
     */
    @Override
    public String checkMerchantCodeUnique(String merchantCode) {
        int count = merchantInfoMapper.checkMerchantCodeUnique(merchantCode);
        if (count > 0) {
            return AddMerchantInfoConstants.MERCHANT_CODE_NOT_UNIQUE;
        }
        return AddMerchantInfoConstants.MERCHANT_CODE_UNIQUE;
    }

    /**
     * 根据套餐id获取套餐内容
     *
     * @param merchantId
     * @return
     */

    @Override
    public MealInfo getMealInfoByMerchantId(Integer merchantId) {
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(merchantId);
        Cooperation rCooperation = cooperationMapper.selectOne(cooperation);
        return mealInfoMapper.selectByPrimaryKey(rCooperation.getMealInfoId());
    }

    @Override
    public LoginSuccessDTO merchantLogin(MerchantLoginDTO merchantLoginDTO) {
        if (StringUtils.isEmpty(merchantLoginDTO.getUserName())) {
            throw new BusinessException("用户名不能为空");
        }
        if (StringUtils.isEmpty(merchantLoginDTO.getPassword())) {
            throw new BusinessException("密码不能为空");
        }
        if (StringUtils.isEmpty(merchantLoginDTO.getSafeCode())) {
            throw new BusinessException("验证码不能为空");
        }
        if (!RedisUtil.exists(merchantLoginDTO.getToken())) {
            throw new BusinessException("验证码已失效，请刷新重试");
        }
        if (!RedisUtil.get(merchantLoginDTO.getToken()).equals(merchantLoginDTO.getSafeCode())) {
            RedisUtil.remove(merchantLoginDTO.getToken());
            throw new BusinessException("验证码错误，请刷新重试");
        }
        //查询商户信息
        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setEmail(merchantLoginDTO.getUserName());
        merchantInfo.setStatus(MerchantStatusEnum.ACTIVE.name());
        MerchantInfo rMerchantInfo = new MerchantInfo();

        //查询所有租户信息
        List<InstitutionInfo> institutionInfoList = institutionInfoMapper.selectAll();
        if (CollectionUtils.isNotEmpty(institutionInfoList)) {
            for (InstitutionInfo entity : institutionInfoList) {
                apiContext.setCurrentProviderId(entity.getProviderId().longValue());
                rMerchantInfo = merchantInfoMapper.selectOne(merchantInfo);
                if (rMerchantInfo != null) {
                    break;
                }
            }
        }
        if (rMerchantInfo == null) {
            throw new BusinessException("用户不存在");
        }
        boolean checkPasswordResult = EncrpytionUtil.checkPassword(rMerchantInfo.getMerchantCode(),
                merchantLoginDTO.getPassword(), rMerchantInfo.getPassword(), rMerchantInfo.getSalt());
        if (!checkPasswordResult) {
            throw new BusinessException("账号或密码错误");
        }

        String status = rMerchantInfo.getStatus();
        if (MerchantStatusEnum.CANCEL.name().equals(status)) {
            throw new BusinessException("账户已注销");
        }
        //TODO 增加登录记录
        String token = UUID.randomUUID().toString().replace("-", "");
        LoginSuccessDTO loginSuccessDTO = new LoginSuccessDTO();
        loginSuccessDTO.setMerchantCode(rMerchantInfo.getMerchantCode());
        loginSuccessDTO.setMerchantId(rMerchantInfo.getId().toString());
        loginSuccessDTO.setToken(token);
        loginSuccessDTO.setWelCome(rMerchantInfo.getCompanyName() + ",您好！");
        PaymentMerchantInfo paymentMerchantInfo =
                paymentMerchantInfoMapper.selectByMerchantIdAndChannel(rMerchantInfo.getId(), PaymentChannelEnum.YUNZB.name());
        if (paymentMerchantInfo != null) {
            loginSuccessDTO.setChannel(PaymentChannelEnum.YUNZB.name());
        } else {
            OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(rMerchantInfo.getId());
            if (onlinePaymentInfo != null) {
                loginSuccessDTO.setChannel(PaymentChannelEnum.ONLINE.name());
                TaxSounrceCompany taxSounrceCompany = taxSounrceCompanyMapper.selectByPrimaryKey(
                        onlinePaymentInfo.getTaxSourceCompanyId());
                loginSuccessDTO.setTaxSourceId(taxSounrceCompany.getId());
                loginSuccessDTO.setTaxSourceName(taxSounrceCompany.getTaxSounrceCompanyName());
            } else {
                loginSuccessDTO.setChannel(PaymentChannelEnum.TIANJIN.name());
            }
        }
        RedisUtil.remove(merchantLoginDTO.getToken());
        //将token和商户信息存放到redis中
        RedisUtil.put(token, rMerchantInfo, RedisTimeConstans.THIRTY, TimeUnit.MINUTES);
        //查询打款渠道支持
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(rMerchantInfo.getId());
        cooperation = cooperationMapper.selectOne(cooperation);
        loginSuccessDTO.setBankChannel(cooperation.getBankChannel());
        loginSuccessDTO.setAlipayChannel(cooperation.getAlipayChannel());
        loginSuccessDTO.setWechatChannel(cooperation.getWechatChannel());
        loginSuccessDTO.setPromotion(cooperation.getPromotionSwitch());
        loginSuccessDTO.setSignSwitch(cooperation.getSignSwitch());
        return loginSuccessDTO;
    }

    /**
     * 商户信息补全
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int completionMerchantInfo(MerchantSupplementDTO merchantSupplementDTO) {
        // 商户表信息更新
        MerchantInfo info = new MerchantInfo();
        BeanUtils.copyProperties(merchantSupplementDTO, info);
        info.setId(merchantSupplementDTO.getMerchantId());
        // 设置为个体商户
        info.setType(0);
        info.setStatus(MerchantStatusEnum.ACTIVE.name());
        // 初始化审核状态
        info.setAuditStatus(0);
        info.setUpdateTime(DateUtil.date());
        merchantInfoMapper.updateByPrimaryKeySelective(info);

        // 商户结算表信息新增
        SettlementInfo settlementInfo = new SettlementInfo();
        settlementInfo.setMerchantId(merchantSupplementDTO.getMerchantId());
        settlementInfo.setBankName(merchantSupplementDTO.getBankName());
        settlementInfo.setAccountNo(merchantSupplementDTO.getAccountNo());
        Integer settleId = settlementInfoMapper.getSettleIdByMerchantId(merchantSupplementDTO.getMerchantId());
        if (settleId == null) {
            settlementInfo.setCreateTime(DateUtil.date());
            return settlementInfoMapper.insertUseGeneratedKeys(settlementInfo);
        }

        settlementInfo.setId(settleId);
        return settlementInfoMapper.updateByPrimaryKeySelective(settlementInfo);
    }

    @Override
    public void register(MerchantRegisterDTO dto) {
        if (StringUtils.isEmpty(dto.getEmail())) {
            throw new BusinessException("注册邮箱不能为空");
        }
        if (StringUtils.isEmpty(dto.getInvitationCode())) {
            throw new BusinessException("邀请码不能为空");
        }
        if (StringUtils.isEmpty(dto.getLoginPassword())) {
            throw new BusinessException("登录密码不能为空");
        }
        if (StringUtils.isEmpty(dto.getVerificationCode())) {
            throw new BusinessException("验证码不能为空");
        }
        //校验邮箱格式
        String reg = "[A-z]+[A-z0-9_-]*\\@[A-z0-9]+\\.[A-z]+";
        if (!dto.getEmail().matches(reg)) {
            throw new BusinessException("邮箱格式不正确");
        }

        //校验邮箱是否存在
        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setEmail(dto.getEmail());
        int count = merchantInfoMapper.selectCount(merchantInfo);
        if (count > 0) {
            throw new BusinessException("该邮箱已被使用，请更换邮箱再试");
        }

        if (RedisUtil.exists(dto.getEmail())) {
            throw new BusinessException("验证码已失效，请重新获取");
        }
        //校验邀请码
        merchantInfo = new MerchantInfo();
        merchantInfo.setMerchantCode(dto.getInvitationCode());
        MerchantInfo invitationInfo = merchantInfoMapper.selectOne(merchantInfo);
        if (invitationInfo == null) {
            throw new BusinessException("邀请码不存在");
        }

        if (!RedisUtil.get(dto.getEmail()).equals(dto.getVerificationCode())) {
            throw new BusinessException("验证码错误");
        }
        String code = MerchantIdUtil.getMerchantId();
        // 防止重复商编
        boolean isUnique = AddMerchantInfoConstants.MERCHANT_CODE_NOT_UNIQUE.
                equals(checkMerchantCodeUnique(code));
        while (isUnique) {
            code = MerchantIdUtil.getMerchantId();
            isUnique = AddMerchantInfoConstants.MERCHANT_CODE_NOT_UNIQUE.equals(checkMerchantCodeUnique(code));
        }
        MerchantInfo rMerchantInfo = new MerchantInfo();
        rMerchantInfo.setEmail(dto.getEmail());
        rMerchantInfo.setInviter(invitationInfo.getId());
        rMerchantInfo.setMerchantCode(code);
        rMerchantInfo.setSalt(PasswordUtil.randomSalt());
        rMerchantInfo.setPassword(PasswordUtil.password(dto.getLoginPassword(), code, merchantInfo.getSalt()));
        rMerchantInfo.setType(MerchantTypeEnum.INDIVIDUAL_BUSINESSMEN.code);
        rMerchantInfo.setAuditStatus(MerchantAuditStatusEnum.SUBMISSION_INFORMATION.code);
        merchantInfoMapper.insertSelective(rMerchantInfo);
    }

    @Override
    public void getCode(MerchantRegisterDTO dto) {
        if (StringUtils.isEmpty(dto.getEmail())) {
            throw new BusinessException("注册邮箱不能为空");
        }

        //校验邮箱格式
        String reg = "[A-z]+[A-z0-9_-]*\\@[A-z0-9]+\\.[A-z]+";
        if (!dto.getEmail().matches(reg)) {
            throw new BusinessException("邮箱格式不正确");
        }

        //校验邮箱是否存在
        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setEmail(dto.getEmail());
        int count = merchantInfoMapper.selectCount(merchantInfo);
        if (count > 0) {
            throw new BusinessException("该邮箱已被使用，请更换邮箱再试");
        }


    }


    @Override
    @DataScope(tableAlias = "t2")
    public List<ManagerMerchantInfoDTO> listMerchantInfos(ManagerMerchantQueryDTO managerMerchantQueryDTO) {
        return merchantInfoMapper.listMerchantInfos(managerMerchantQueryDTO);
    }


    //查询企业所有信息
    @Override
    public List<ManagerCompanyInfoDTO> selectAllCompanyInfo(ManagerCompanyInfoQueryDTO queryDTO) {
        List<ManagerCompanyInfoDTO> managerCompanyInfoDTOS = merchantInfoMapper.selectAllCompanyInfo(queryDTO);
        //设置企业全名
        for(ManagerCompanyInfoDTO managerCompanyInfoDTO:managerCompanyInfoDTOS){
            if(managerCompanyInfoDTO.getCompanyName() == null){
                continue;
            }
            if(managerCompanyInfoDTO.getTaxSounrceCompanyName().indexOf(TaxSounrceCompanyNameConstants.HNJJ_COMPANY_NAME)
                    != TaxSounrceCompanyNameConstants.NO_CONTAINS ){
                managerCompanyInfoDTO.setTaxSounrceCompanyName(TaxSounrceCompanyNameConstants.HNJJ_COMPANY_NAME);
                managerCompanyInfoDTO.setTaxSounrceCompanyFullName(TaxSounrceCompanyNameConstants.HNJJ_COMPANY_FULL_NAME);
            }else if(managerCompanyInfoDTO.getTaxSounrceCompanyName().indexOf(TaxSounrceCompanyNameConstants.JXQB_COMPANY_NAME)
                    != TaxSounrceCompanyNameConstants.NO_CONTAINS ){
                managerCompanyInfoDTO.setTaxSounrceCompanyName(TaxSounrceCompanyNameConstants.JXQB_COMPANY_NAME);
                managerCompanyInfoDTO.setTaxSounrceCompanyFullName(TaxSounrceCompanyNameConstants.JXQB_COMPANY_FULL_NAME);
            }else if(managerCompanyInfoDTO.getTaxSounrceCompanyName().indexOf(TaxSounrceCompanyNameConstants.TNBR_COMPANY_NAME)
                    != TaxSounrceCompanyNameConstants.NO_CONTAINS){
                managerCompanyInfoDTO.setTaxSounrceCompanyName(TaxSounrceCompanyNameConstants.TNBR_COMPANY_NAME);
                managerCompanyInfoDTO.setTaxSounrceCompanyFullName(TaxSounrceCompanyNameConstants.TNBR_COMPANY_FULL_NAME);
            }else {
                managerCompanyInfoDTO.setTaxSounrceCompanyFullName(TaxSounrceCompanyNameConstants.NO_COMPANY_FULL_NAME);
            }
        }
//        for (ManagerCompanyInfoDTO managerCompanyInfoDTO : managerCompanyInfoDTOS) {
//            if (TaxSounrceCompanyNameConstants.HNJJ_COMPANY_NAME.equals(managerCompanyInfoDTO.getTaxSounrceCompanyName())) {
//                managerCompanyInfoDTO.setTaxSounrceCompanyFullName(TaxSounrceCompanyNameConstants.HNJJ_COMPANY_FULL_NAME);
//            } else if (TaxSounrceCompanyNameConstants.JXQB_COMPANY_NAME.equals(managerCompanyInfoDTO.getTaxSounrceCompanyName())) {
//                managerCompanyInfoDTO.setTaxSounrceCompanyFullName(TaxSounrceCompanyNameConstants.JXQB_COMPANY_FULL_NAME);
//            } else if (TaxSounrceCompanyNameConstants.TNBR_COMPANY_NAME.equals(managerCompanyInfoDTO.getTaxSounrceCompanyName())) {
//                managerCompanyInfoDTO.setTaxSounrceCompanyFullName(TaxSounrceCompanyNameConstants.TNBR_COMPANY_FULL_NAME);
//            } else {
//                managerCompanyInfoDTO.setTaxSounrceCompanyFullName(TaxSounrceCompanyNameConstants.NO_COMPANY_FULL_NAME);
//            }
//        }
        return managerCompanyInfoDTOS;
    }

    //查询合同相关信息
    @Override
    public List<ManagerContractInfoDTO> selectContractInfo(ManagerCompanyInfoQueryDTO queryDTO) {
        if (queryDTO.getExpireStatus() != null && queryDTO.getExpireStatus().equals(1)) {
            return new ArrayList<ManagerContractInfoDTO>();
        }
        List<ManagerContractInfoDTO> managerContractInfos = merchantInfoMapper.selectContractInfo(queryDTO);
        //隐藏姓名
//        for (ManagerContractInfoDTO managerContractInfo : managerContractInfos) {
//            String payeeName = managerContractInfo.getName();
//            managerContractInfo.setName(dealName(payeeName));
//        }
        return managerContractInfos;
    }

    /**
     * 个人收款信息
     *
     * @param queryDTO 税原地信息
     * @return 结果
     */
    @Override
    public List<PersonalReceiptDTO> selectPersonalReceiptInfo(ManagerCompanyInfoQueryDTO queryDTO) {
        List<PersonalReceiptDTO> personalReceiptDTOS = merchantInfoMapper.selectPersonalReceiptInfo(queryDTO);
        String[] taskNames = {"市场推广", "软件开发服务", "电路设计服务", "信息系统服务", "业务流程管理服务", "专业设计服务", "人才委托招聘"};
        for (PersonalReceiptDTO personalReceiptDTO : personalReceiptDTOS) {
            //获得员工id
            Integer id = personalReceiptDTO.getId();
            //任务名称为空时，插入任务名称假数据
            CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(id);
            Random random = new Random();
            if (personalReceiptDTO.getTaskName() == null) {
                //商户id
                Integer merchantId = personalReceiptDTO.getMerchantId();
                //员工身份证号码
                String payIdCardNo = personalReceiptDTO.getPayIdCardNo();

                //员工任务名与第一次批量打款的任务名相同
                Example example = new Example(TrxOrder.class);

                //根据商户id和员工身份证号码(payIdCardNo)查询交易订单信息(TrxOrder)
                example.createCriteria().andEqualTo("merchantId", merchantId).andEqualTo("payeeIdCardNo", payIdCardNo).andIsNotNull("batchNo");

                example.setOrderByClause("update_time asc limit 1");

                List<TrxOrder> trxOrders = trxOrderMapper.selectByExample(example);
                TrxOrder trxOrder = new TrxOrder();
                //取第一次打款的信息
                if (StringUtils.isNotEmpty(trxOrders)) {
                    trxOrder = trxOrders.get(0);
                }
                //任务名
                String taskName = "";
                //查询批量打款记录中的任务名称
                if (StringUtils.isNotEmpty(trxOrder.getBatchNo())) {
                    //根据批次号查询批量打款中的任务名
                    BatchPaymentRecord batchPaymentRecord = new BatchPaymentRecord();
                    batchPaymentRecord.setBatchNo(trxOrder.getBatchNo());
                    List<BatchPaymentRecord> batchPaymentRecords = batchPaymentRecordMapper.select(batchPaymentRecord);
                    if (StringUtils.isNotEmpty(batchPaymentRecords)) {
                        taskName = batchPaymentRecords.get(0).getTaskName();
                    }
                }
                //任务名不为空，取批量打款中的任务名，否则随机生成任务名
                if (StringUtils.isNotEmpty(taskName)) {
                    customerInfo.setTaskName(taskName);
                } else {
                    customerInfo.setTaskName(taskNames[random.nextInt(taskNames.length)]);
                }
                customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
                personalReceiptDTO.setTaskName(customerInfo.getTaskName());
            }
            //设置名字显示格式
//            String payeeName = personalReceiptDTO.getPayeeName();
//            payeeName = dealName(payeeName);
//            personalReceiptDTO.setPayeeName(payeeName);
//
            //设置手机号显示格式
//            String payeeMobile = personalReceiptDTO.getPayeeMobile();
//            if (payeeMobile.length() == 11) {
//                payeeMobile = payeeMobile.substring(0, 3) + "****" + payeeMobile.substring(payeeMobile.length() - 4);
//            }
//            personalReceiptDTO.setPayeeMobile(payeeMobile);

            //设置身份证显示格式
//            String payIdCardNo = personalReceiptDTO.getPayIdCardNo();
//            if (payIdCardNo.length() >= 8) {
//                payIdCardNo = payIdCardNo.substring(0, 4) + "******" + payIdCardNo.substring(payIdCardNo.length() - 4);
//            }
//            personalReceiptDTO.setPayIdCardNo(payIdCardNo);

            //设置交易账号显示格式
//            String payeeBankcard = personalReceiptDTO.getPayeeBankcard();
//            if (payeeBankcard.length() >= 8) {
//                //支付宝、银行卡
//                if (payeeBankcard.length() >= 11 && payeeBankcard.contains("@")) {
//                    payeeBankcard = payeeBankcard.substring(0, 4) + "******" + payeeBankcard.substring(payeeBankcard.length() - 8);
//                } else {
//                    payeeBankcard = payeeBankcard.substring(0, 4) + "******" + payeeBankcard.substring(payeeBankcard.length() - 4);
//                }
//            }
//            personalReceiptDTO.setPayeeBankcard(payeeBankcard);
        }
        return personalReceiptDTOS;
    }

    /**
     * 设置名字显示格式
     *
     * @param payeeName 要处理的名字
     * @return 结果
     */
    private String dealName(String payeeName) {
        if (payeeName == null) {
            return "";
        }
        //名字长度为两位数时、和名字长度大于两位时
        if (payeeName.length() == NameLengthEnum.NAME_LENGTH.getLength()) {
            payeeName = payeeName.substring(0, 1) + "*";
        } else {
            int length = payeeName.length();
            String str = "*";
            payeeName = payeeName.substring(0, 1) + str + payeeName.substring(length - 1);
        }
        return payeeName;
    }

    //查询企业资金流水
    @Override
    public List<CompanyReceiptInfoDTO> selectCompanyReceiptInfo(ManagerCompanyInfoQueryDTO queryDTO) {
        List<CompanyReceiptInfoDTO> companyReceiptInfos = merchantInfoMapper.selectCompanyReceiptInfo(queryDTO);
        for (CompanyReceiptInfoDTO companyReceiptInfo : companyReceiptInfos) {
            //3-入账解冻
            if (AccountDetailTypeEnum.CREDIT_UNFREEZE.getType().equals(companyReceiptInfo.getType())) {
                companyReceiptInfo.setIncreaseAmount(companyReceiptInfo.getPaymentAmount());
                if (companyReceiptInfo.getPaymentType() == null) {
                    updateMerchantAccountDetail(companyReceiptInfo.getId(), DetailTypeEnum.RECHARGE);
                    companyReceiptInfo.setPaymentType(DetailTypeEnum.RECHARGE.getCode());
                }
            }
            //0-出账冻结
            if (AccountDetailTypeEnum.DEBIT_FREEZE.getType().equals(companyReceiptInfo.getType())) {
                companyReceiptInfo.setDecreaseAmount(companyReceiptInfo.getPaymentAmount());
                if (companyReceiptInfo.getPaymentType() == null) {
                    updateMerchantAccountDetail(companyReceiptInfo.getId(), DetailTypeEnum.PAYMENT);
                    companyReceiptInfo.setPaymentType(DetailTypeEnum.PAYMENT.getCode());
                }
            }
            //设置付款的明细类型 (充值)
            if (companyReceiptInfo.getOrderSerialNum() != null) {
                RechargeRecord rechargeRecord = new RechargeRecord();
                rechargeRecord.setOrderSerialNum(companyReceiptInfo.getOrderSerialNum());
                rechargeRecord.setStatus(RechargeStatusEnum.SUCCESS.name());
                RechargeRecord rRechargeRecord = rechargeRecordMapper.selectOne(rechargeRecord);
                if (rRechargeRecord != null && companyReceiptInfo.getPaymentType() == null) {
                    updateMerchantAccountDetail(companyReceiptInfo.getId(), DetailTypeEnum.RECHARGE);
                    companyReceiptInfo.setPaymentType(DetailTypeEnum.RECHARGE.getCode());
                }
            }
            //设置变动金额（增加和减少）
            if (DetailTypeEnum.RECHARGE.getCode().equals(companyReceiptInfo.getPaymentType())) {
                companyReceiptInfo.setIncreaseAmount(companyReceiptInfo.getPaymentAmount());
                companyReceiptInfo.setDecreaseAmount(BigDecimal.ZERO);
            }
            if (DetailTypeEnum.PAYMENT.getCode().equals(companyReceiptInfo.getPaymentType())) {
                companyReceiptInfo.setDecreaseAmount(companyReceiptInfo.getPaymentAmount());
                companyReceiptInfo.setIncreaseAmount(BigDecimal.ZERO);
            }
        }
        return companyReceiptInfos;
    }

    //更新明细记录类型 方法
    private void updateMerchantAccountDetail(Integer id, DetailTypeEnum detailTypeEnum) {
        MerchantAccountDetail merchantAccountDetail = merchantAccountDetailMapper.selectByPrimaryKey(id);
        merchantAccountDetail.setPaymentType(detailTypeEnum.getCode());
        merchantAccountDetailMapper.updateByPrimaryKeySelective(merchantAccountDetail);
    }

    //查询付款相关信息
    @Override
    public List<ManagerPayInfoDTO> selectPayInfo(ManagerCompanyInfoQueryDTO queryDTO) {
        return merchantInfoMapper.selectPayInfo(queryDTO);
    }


    //查询发放相关信息
    @Override
    public List<ManagerGrantInfoDTO> selectGrantInfo(ManagerCompanyInfoQueryDTO queryDTO) {
        List<ManagerGrantInfoDTO> managerGrantInfos = merchantInfoMapper.selectGrantInfo(queryDTO);
        for (ManagerGrantInfoDTO managerGrantInfo : managerGrantInfos) {
            //获得接单的派单数
            Integer successNum =
                    batchTaskDetailMapper.
                            countTaskDetailCompleted(managerGrantInfo.getBatchNo(), TaskStatusEnum.FINISH.getCode(),
                                    TakeTaskStatusEnum.TAKE.getCode());
            //获得全部订单数
            Integer num = batchTaskDetailMapper.
                    countTaskDetailCompleted(managerGrantInfo.getBatchNo(), null, null);
            //获得未接单的派单数
            Integer failNum = num - successNum;
            //获得完成的派单金额
            BigDecimal successAmount =
                    batchTaskDetailMapper.
                            getSumAmountTaskDetail(managerGrantInfo.getBatchNo(), TaskStatusEnum.FINISH.getCode(),
                                    TakeTaskStatusEnum.TAKE.getCode());
            //获得未接单的派单金额
            BigDecimal sumAmount =
                    batchTaskDetailMapper.getSumAmountTaskDetail(managerGrantInfo.getBatchNo(), null, null);
            BigDecimal failAmount = sumAmount.subtract(successAmount);
            //获得打款成功的任务信息
            BatchTaskDetail batchTaskDetail = new BatchTaskDetail();
            batchTaskDetail.setBatchNo(managerGrantInfo.getBatchNo());
            batchTaskDetail.setTakeTaskStatus(TakeTaskStatusEnum.TAKE.getCode());
            List<BatchTaskDetail> batchTaskDetails = batchTaskDetailMapper.select(batchTaskDetail);
            Integer paymentNum = 0;
            BigDecimal paymentAmount =BigDecimal.ZERO;
            for (BatchTaskDetail taskDetail : batchTaskDetails) {
                CustomerTask customerTask = new CustomerTask();
                customerTask.setTaskDetailId(taskDetail.getId());
                CustomerTask rCustomerTask = customerTaskMapper.selectOne(customerTask);
                //获得打款成功数
                Integer rPaymentNum = batchTaskDetailMapper.countOrderCompleted(managerGrantInfo.getBatchNo(),
                        rCustomerTask.getCertificateNo(), TrxOrderStatusEnum.PAID.code, rCustomerTask.getCustomerId());
                if (rPaymentNum != 0) {
                    paymentNum++;
                }
                //获得打款成功的金额
                BigDecimal rPaymentAmount = batchTaskDetailMapper.getSumAmountCompletedOrder(managerGrantInfo.getBatchNo(),
                        rCustomerTask.getCertificateNo(), TrxOrderStatusEnum.PAID.code, rCustomerTask.getCustomerId());
                paymentAmount = paymentAmount.add(rPaymentAmount);
            }
            managerGrantInfo.setSuccessNum(successNum);
            managerGrantInfo.setFailNum(failNum);
            managerGrantInfo.setSuccessAmount(successAmount);
            managerGrantInfo.setFailAmount(failAmount);
            managerGrantInfo.setTaskTotalNum(num);
            managerGrantInfo.setPaymentNum(paymentNum);
            managerGrantInfo.setPaymentAmount(paymentAmount);
        }
        return managerGrantInfos;
    }

    //根据税源地id查询科目内容
    @Override
    public List<InvoiceSubjectWithTaxCompanyCorrelationDTO> selectContent(Integer taxSounrceCompanyId) {
        return merchantInfoMapper.selectContent(taxSounrceCompanyId);
    }

    //根据商户id查询所包含的科目内容
    @Override
    public List<InvoiceSubjectWithTaxCompanyCorrelationDTO> selectInvoiceContentInfoByMerchanId(Integer merchantId) {
        List<InvoiceSubjectWithTaxCompanyCorrelationDTO> invoiceSubjects
                = merchantInfoMapper.selectInvoiceContentInfoByMerchanId(merchantId);
        //查询默认的开票科目
        if (invoiceSubjects.size() != 0) {
            InvoiceSubjectWithTaxCompanyCorrelationDTO invoiceSubjectWithTaxCompanyCorrelationDTO = null;
            for (InvoiceSubjectWithTaxCompanyCorrelationDTO invocieSubject : invoiceSubjects) {
                if (DefaultStatusEnum.DEFAULT.code.equals(invocieSubject.getIsDefault())) {
                    invoiceSubjectWithTaxCompanyCorrelationDTO = invocieSubject;
                }
            }
            //将默认的科目先挪出来
            invoiceSubjects.remove(invoiceSubjectWithTaxCompanyCorrelationDTO);
            //将默认的发票科目放在第一位
            invoiceSubjects.add(0, invoiceSubjectWithTaxCompanyCorrelationDTO);
            return invoiceSubjects;
        } else {
            return invoiceSubjects;
        }
    }

    //根据商户id查询税源地对应的科目内容
    @Override
    public List<InvoiceSubjectWithTaxCompanyCorrelationDTO> selectInvoiceContent(Integer merchantId) {
        return merchantInfoMapper.selectInvoiceContent(merchantId);
    }

    //修改发票信息
    @Override
    public void updateInvoiceContentInfo(UpdateBaseInvoiceInfoDTO updateBaseInvoiceInfoDTO,
                                         List<Integer> invoiceSubjectIds, Integer merchantId) {
        //删除null id
        invoiceSubjectIds.removeAll(Collections.singleton(null));
        //修改发票基础信息
        merchantInfoMapper.updateInvoiceInfo(updateBaseInvoiceInfoDTO);
        //获得原有发票科目id集合
        List<Integer> invoiceSubjectIdList = new ArrayList<>();
        List<InvoiceSubjectWithTaxCompanyCorrelationDTO> invoiceSubjectWithTaxCompanyCorrelationDTOS
                = merchantInfoMapper.selectInvoiceContentInfoByMerchanId(merchantId);
        for (InvoiceSubjectWithTaxCompanyCorrelationDTO invoiceSubjectWithTaxCompanyCorrelationDTO
                : invoiceSubjectWithTaxCompanyCorrelationDTOS) {
            invoiceSubjectIdList.add(invoiceSubjectWithTaxCompanyCorrelationDTO.getInvoiceSubjectId());
        }
        // 删除减少的科目
        for (Integer oldInvoiceSubjectId : invoiceSubjectIdList) {
            if (!invoiceSubjectIds.contains(oldInvoiceSubjectId)) {
                merchantInfoMapper.updateInvoiceContentInfo(DelStatusEnum.DELETE.code, merchantId, oldInvoiceSubjectId);
            }
        }
        // 添加新增科目
        for (Integer newInvoiceSubjectId : invoiceSubjectIds) {
            if (!invoiceSubjectIdList.contains(newInvoiceSubjectId)) {
                MerchantInvoiceSubjectCorrelation merchantInvoiceSubjectCorrelation
                        = new MerchantInvoiceSubjectCorrelation();
                merchantInvoiceSubjectCorrelation.setCreateTime(DateUtil.date());
                merchantInvoiceSubjectCorrelation.setUpdateTime(DateUtil.date());
                merchantInvoiceSubjectCorrelation.setInvoiceSubjectId(newInvoiceSubjectId);
                merchantInvoiceSubjectCorrelation.setMerchantInfoId(merchantId);
                merchantInvoiceSubjectCorrelationMapper.insertSelective(merchantInvoiceSubjectCorrelation);
            }
        }
        // 设置传入的第一个科目为默认科目
        for (int i = 0; i < invoiceSubjectIds.size(); i++) {
            // 找到对应的关联表对象
            MerchantInvoiceSubjectCorrelation merchantInvoiceSubjectCorrelation
                    = new MerchantInvoiceSubjectCorrelation();
            merchantInvoiceSubjectCorrelation.setInvoiceSubjectId(invoiceSubjectIds.get(i));
            merchantInvoiceSubjectCorrelation.setMerchantInfoId(merchantId);
            merchantInvoiceSubjectCorrelation.setDelStatus(DelStatusEnum.NORMAL.code);
            MerchantInvoiceSubjectCorrelation merchantInvoiceInfo
                    = merchantInvoiceSubjectCorrelationMapper.selectOne(merchantInvoiceSubjectCorrelation);
            // 设置第一个为默认，其它的为非默认
            if (i == 0) {
                merchantInvoiceInfo.setIsDefault(DefaultStatusEnum.DEFAULT.code);
            } else {
                merchantInvoiceInfo.setIsDefault(DefaultStatusEnum.UN_DEFAULT.code);
            }
            merchantInvoiceSubjectCorrelationMapper.updateByPrimaryKeySelective(merchantInvoiceInfo);
        }
    }

    /**
     * 查询发票信息
     *
     * @param queryDTO 参数
     * @return
     */
    @Override
    public List<InvoiceInfoDetailDTO> selectInvoiceInfoDetail(ManagerCompanyInfoQueryDTO queryDTO) {
        List<InvoiceInfoDetailDTO> invoiceInfoDetailDTOS = merchantInfoMapper.selectInvoiceInfoDetail(queryDTO);
        for (InvoiceInfoDetailDTO detailDTO : invoiceInfoDetailDTOS) {
            //设置
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            detailDTO.setCreateTimeStr(sdf.format(detailDTO.getCreateTime()));
            //设置名字显示格式
//            String addresseeName = detailDTO.getAddresseeName();
//            addresseeName = dealName(addresseeName);
//            detailDTO.setAddresseeName(addresseeName);
            //设置手机号显示格式
//            String addressMobile = detailDTO.getAddressMobile();
//            if (addressMobile.length() == 11) {
//                addressMobile = addressMobile.substring(0, 3) + "****" + addressMobile.substring(addressMobile.length() - 4);
//            }
//            detailDTO.setAddressMobile(addressMobile);
            //设置地址显示格式
//            String address = detailDTO.getAddress();
//            if (address.length() > 8) {
//                address = address.substring(0, 4) + "****" + address.substring(address.length() - 4);
//            }
//            detailDTO.setAddress(address);
            //收件人信息显示格式
            detailDTO.setAddressInfo(detailDTO.getAddresseeName() + " " + detailDTO.getAddressMobile() + " " + detailDTO.getAddress());

            if (detailDTO.getInvoiceStatus() == 1 || detailDTO.getInvoiceStatus() == 3 ||
                    detailDTO.getInvoiceStatus() == 7 || detailDTO.getInvoiceStatus() == 8) {
                detailDTO.setInvoiceNo("-------");
                detailDTO.setExpressNo("-------");
                detailDTO.setExpressCompanyName("-------");
                detailDTO.setCreateTimeStr("-------");
            }
        }
        return invoiceInfoDetailDTOS;
    }

    @Override
    public List<BatchPaymentRecord> updateBatchPaymentRecordInfo() {
        List<BatchPaymentRecord> batchPaymentRecords = batchPaymentRecordMapper.selectAll();
        String[] taskNames = {"市场推广", "软件开发服务", "电路设计服务", "信息系统服务", "业务流程管理服务", "专业设计服务", "人才委托招聘"};
        for (BatchPaymentRecord batchPaymentRecord : batchPaymentRecords) {
            if (batchPaymentRecord.getBatchNo() != null) {
                //任务名称为空时，插入任务名称假数据
                Random random = new Random();
                if (batchPaymentRecord.getTaskName() == null) {
                    int randomNo = random.nextInt(taskNames.length);
                    batchPaymentRecord.setTaskName(taskNames[randomNo]);
                    batchPaymentRecordMapper.updateByPrimaryKeySelective(batchPaymentRecord);
                }
            }
        }

        return null;
    }

    /**
     * 个人资金流水信息
     *
     * @param queryDTO 税原地信息
     * @return 结果
     */
    @Override
    public List<PersonalAccountFundChangeDTO> selectPersonalAccountFundChangeInfo(ManagerCompanyInfoQueryDTO queryDTO) {
        List<PersonalAccountFundChangeDTO> dtos = merchantInfoMapper.selectPersonalAccountFundChangeInfo(queryDTO);
        for (PersonalAccountFundChangeDTO dto : dtos) {
            //根据资金变动情况来设置交易类型
            CustomerAccountDetail accountDetail = customerAccountDetailMapper.selectByPrimaryKey(dto.getId());
            //设置资金变动情况
            if (dto.getType().compareTo(AccountDetailTypeEnum.CREDIT_FREEZE.getType()) == 0 ||
                    dto.getType().compareTo(AccountDetailTypeEnum.CREDIT_UNFREEZE.getType()) == 0) {
                if (dto.getPaymentType() == null) {
                    dto.setPaymentType(PaymentTypeConstants.PAYMENT_TYPE_RECEIPT);
                    accountDetail.setPaymentType(PaymentTypeConstants.PAYMENT_TYPE_RECEIPT);
                    customerAccountDetailMapper.updateByPrimaryKeySelective(accountDetail);
                }
                dto.setAddPaymentAmount(dto.getChangePaymentAmount());
                dto.setReducePaymentAmount(BigDecimal.ZERO);
            } else if (dto.getType().compareTo(AccountDetailTypeEnum.DEBIT_FREEZE.getType()) == 0
                    || dto.getType().compareTo(AccountDetailTypeEnum.DEBIT_UNFREEZE.getType()) == 0) {
                if (dto.getPaymentType() == null) {
                    dto.setPaymentType(PaymentTypeConstants.PAYMENT_TYPE_WITHDRAW);
                    accountDetail.setPaymentType(PaymentTypeConstants.PAYMENT_TYPE_WITHDRAW);
                    customerAccountDetailMapper.updateByPrimaryKeySelective(accountDetail);
                }
                //根据员工id查询员工姓名
                CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(dto.getCustomerId());
                if(StringUtils.isNotNull(customerInfo)){
                    dto.setCustomerName(customerInfo.getRealName());
                }
                dto.setAddPaymentAmount(BigDecimal.ZERO);
                dto.setReducePaymentAmount(dto.getChangePaymentAmount());
            }
            //设置名字显示格式
//            String customerName = dto.getCustomerName();
//            customerName = dealName(customerName);
//            dto.setCustomerName(customerName);
        }
        return dtos;
    }

    @Override
    public MerchantSwitchDTO getMerchantSwitch(Integer merchantId) {
        CooperationInfoDetailDTO cooperationInfo = cooperationMapper.getCooperationInfoByMerchantId(merchantId);
        MerchantSwitchDTO merchantSwitch = new MerchantSwitchDTO();
        merchantSwitch.setSignSwitch(cooperationInfo.getSignSwitch());
        merchantSwitch.setPromotionSwitch(cooperationInfo.getPromotionSwitch());
        merchantSwitch.setSendOrderSwitch(cooperationInfo.getSendOrderSwitch());
        merchantSwitch.setSendOrderMode(cooperationInfo.getSendOrderMode());
        merchantSwitch.setMoneyModel(cooperationInfo.getMoneyModel());
        return merchantSwitch;
    }
}
