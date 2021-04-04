package com.mmtax.business.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.ICusLinkMerInfoService;
import com.mmtax.business.service.ICustomerInfoService;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.business.service.*;
import com.mmtax.common.constant.Constants;
import com.mmtax.common.constant.SmsConstant;
import com.mmtax.common.constant.WechatLoginConstants;
import com.mmtax.common.constant.onlinebank.OnlineConstants;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.DateUtils;
import com.mmtax.common.utils.MerchantIdUtil;
import com.mmtax.common.utils.PatternVerifyUtil;
import com.mmtax.common.utils.http.HttpUtils;
import com.mmtax.common.utils.redis.RedisLockConstans;
import com.mmtax.common.utils.redis.RedisLockUtil;
import com.mmtax.common.utils.redis.RedisTimeConstans;
import com.mmtax.common.utils.redis.RedisUtil;
import com.mmtax.common.utils.sms.TiRuiYunUtil;
import com.mmtax.system.service.ISysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 员工 服务层实现
 * @author meimiao
 * @date 2020-07-09
 */
@Slf4j
@Service
public class CustomerInfoServiceImpl extends BaseServiceImpl implements ICustomerInfoService {
    @Autowired
    private MerchantInfoMapper merchantInfoMapper;
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Autowired
    private IOnlineBankService onlineBankService;
    @Autowired
    private OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Autowired
    private CustomerAlipayInfoMapper customerAlipayInfoMapper;
    @Autowired
    private CustomerBankcardInfoMapper customerBankcardInfoMapper;
    @Autowired
    ICustomerBankcardInfoService customerBankcardInfoService;
    @Autowired
    private CustomerAccountMapper customerAccountMapper;
    @Autowired
    private CusLinkMerInfoMapper cusLinkMerInfoMapper;
    @Autowired
    private ICusLinkMerInfoService cusLinkMerInfoService;
    @Autowired
    private ICustomerWithdrawService customerWithdrawService;
    @Autowired
    private TrxOrderMapper trxOrderMapper;
    @Autowired
    private CustomerWithdrawMapper customerWithdrawMapper;
    @Autowired
    ICustomerProtocolService customerProtocolService;
    @Autowired
    CustomerWechatInfoMapper customerWechatInfoMapper;
    @Autowired
    CustomerDayAmountChangeMapper customerDayAmountChangeMapper;
    @Autowired
    TaxSounrceCompanyMapper taxSounrceCompanyMapper;
    @Autowired
    OnlineCustomerInfoMapper onlineCustomerInfoMapper;
    @Autowired
    ISysConfigService sysConfigService;

    @Override
    public CustomerInfo queryCustomerInfo(String name,String mobile,String idCardNo,Integer merchantId
            ,Integer cusEsignId,Integer taxSourceId) {
        if(null == taxSourceId && -1 != merchantId) {
            OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
            taxSourceId = onlinePaymentInfo.getTaxSourceCompanyId();
        }
        //同一员工同一税源地加锁
        String value = UUID.randomUUID().toString();
        String key = RedisLockConstans.CUSTOMER_INFO_LOCK + idCardNo + "_" + taxSourceId;
        logger.info("customer_info_lock key{}, start:{}", key,
                DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_MS_FORMAT));
        RedisLockUtil.getRedisLock(key, value, RedisTimeConstans.ONLINE_LOCK_TIME);
        logger.info("customer_info_lock key{}, end:{}", key,
                DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_MS_FORMAT));
        CustomerInfo customerInfo = customerInfoMapper.selectByIdCardNoAndTaxSourceId(idCardNo,taxSourceId);
        //若存在员工且不属于该订单的商户，返回null
        if(null != customerInfo) {
            if (!name.equals(customerInfo.getRealName()) && -1 == merchantId) {
                throw new BusinessException("姓名或身份证号错误");
            }
            if (!name.equals(customerInfo.getRealName())) {
                log.info("灵工身份证和真名不匹配");
                if(-1 == merchantId){
                    throw new BusinessException("身份证或姓名错误");
                }
                throw new BusinessException("灵工身份证和姓名不匹配");
            }
            if(-1 != merchantId) {
                CusLinkMerInfo cusLinkMerInfo = cusLinkMerInfoMapper.selectByCusIdMerId(customerInfo.getId(), merchantId);
                if (null == cusLinkMerInfo) {
                    //不存在链接信息，创建
                    cusLinkMerInfoService.coverCusLinkMerInfo(customerInfo.getId(), merchantId, cusEsignId);
                } else {
                    cusLinkMerInfo.setCusEsignId(cusEsignId);
                    cusLinkMerInfo.setUpdateTime(DateUtil.date());
                    //根据cusEsignId是否为null判断是否更新
                    int isUpdate = null != cusEsignId ? cusLinkMerInfoMapper.updateByPrimaryKeySelective(cusLinkMerInfo) : 0;
                }
            }
            //存在且属于该商户，返回
            return customerInfo;
        }

        //不存在员工，生成并注册
        ICustomerInfoService customerInfoService = (ICustomerInfoService) AopContext.currentProxy();
        return customerInfoService.convertCustomerInfo(name,mobile,idCardNo,merchantId,cusEsignId,taxSourceId);
    }

    /**
     * 设置事务读其他事务未提交的新增customer信息，保证编码唯一性
     * @param name
     * @param mobile
     * @param idCardNo
     * @param merchantId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED,rollbackFor = Exception.class)
    public CustomerInfo convertCustomerInfo(String name,String mobile,String idCardNo,Integer merchantId
            ,Integer cusEsignId,Integer taxSourceId) {
        CustomerInfo customerInfo = new CustomerInfo();
        String code = MerchantIdUtil.getMerchantId();
        // 防止重复编码
        boolean isUnique = checkCustomerCodeUnique(code);
        while (isUnique) {
            code = MerchantIdUtil.getMerchantId();
            isUnique = checkCustomerCodeUnique(code);
        }
        customerInfo.setCustomerNo(code);
        customerInfo.setMemberName(name);
        customerInfo.setRealName(name);
        customerInfo.setMobile(mobile);
        customerInfo.setCertificateType(OnlineConstants.ID_CARD);
        customerInfo.setCertificateNo(idCardNo);
        customerInfo.setMerchantId(merchantId);
        customerInfo.setVerifyStatus(VerifyStatusEnum.UNVERIFY.getStatus());
        customerInfo.setEffective(EffectiveEnum.UNEFFECTIVE.getStatus());
        customerInfo.setCreateTime(DateUtil.date());
        customerInfo.setUpdateTime(DateUtil.date());
        customerInfoMapper.insertSelective(customerInfo);
        //同步生成员工账户
        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setCustomerId(customerInfo.getId());
        customerAccount.setAmount(BigDecimal.ZERO);
        customerAccount.setFrozenAmount(BigDecimal.ZERO);
        customerAccount.setUsableAmount(BigDecimal.ZERO);
        customerAccount.setStatus(MerOrCusAccountStatusEnum.ACTIVE.name());
        customerAccount.setCreateTime(DateUtil.date());
        customerAccount.setUpdateTime(DateUtil.date());
        customerAccountMapper.insertSelective(customerAccount);
        //-1表示是个体工商户上传
        if(-1 != merchantId) {
            //生成关联信息
            cusLinkMerInfoService.coverCusLinkMerInfo(customerInfo.getId(), merchantId, cusEsignId);
        }
        //第三方注册员工账户
        onlineBankService.personalRegister(taxSourceId,customerInfo);
        return customerInfo;
    }

    /**
     * 判断员工编码是否在所有商户和员工中唯一
     * @param code
     * @return
     */
    private boolean checkCustomerCodeUnique(String code) {
        MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfoByCode(code);
        if (null != merchantInfo) {
            return true;
        }
        CustomerInfo customerInfo = customerInfoMapper.selectByCustomerNo(code);
        if (null != customerInfo) {
            return true;
        }
        return false;
    }

    @Override
    public List<ManagerCustomerInfoDTO> listManagerCustomerInfoDTO(ManagerCustomerInfoQueryDTO queryDTO) {
        DateQueryDTO dateQueryDTO = getDateQueryDTOMatchNorm(queryDTO.getStartDate(), queryDTO.getEndDate());
        queryDTO.setStartDate(dateQueryDTO.getStartDate());
        queryDTO.setEndDate(dateQueryDTO.getEndDate());
        List<ManagerCustomerInfoDTO> dtoList = customerInfoMapper.listManagerCustomerInfoDTO(queryDTO);
        // 获取员工银行卡，支付宝绑定状态
        if (CollectionUtils.isNotEmpty(dtoList)) {
            dtoList = giveBindStatus(dtoList);
        }
        return dtoList;
    }

    /**
     * 赋予员工信息支付宝，银行卡绑定状态信息
     * @param dtoList 员工信息列表
     * @return 赋值后员工信息列表
     */
    private List<ManagerCustomerInfoDTO> giveBindStatus(List<ManagerCustomerInfoDTO> dtoList) {
        List<Integer> customerIdList = new ArrayList<>();
        dtoList.forEach(o -> customerIdList.add(o.getId()));
        if (CollectionUtils.isEmpty(customerIdList)) {
            customerIdList.add(-1);
        }
        List<Integer> alipayCustomerIdList = customerAlipayInfoMapper.listCustomerIdForBind(customerIdList);
        List<Integer> bankCustomerIdList = customerBankcardInfoMapper.listCustomerIdForBind(customerIdList);
        for (ManagerCustomerInfoDTO dto : dtoList) {
            if (alipayCustomerIdList.contains(dto.getId())) {
                dto.setBindStatusAlipay(BindStatusEnum.BIND.code);
            }else {
                dto.setBindStatusAlipay(BindStatusEnum.NON_BIND.code);
            }
            if (bankCustomerIdList.contains(dto.getId())) {
                dto.setBindStatusBank(BindStatusEnum.BIND.code);
            }else {
                dto.setBindStatusBank(BindStatusEnum.NON_BIND.code);
            }
        }
        return dtoList;
    }

    @Override
    public JSONObject wechatLogin(String code,Integer taxSourceId) {
        Map<String,String> paramMap = new HashMap<>(4);
        paramMap.put("appid", WechatLoginConstants.APPID);
        paramMap.put("secret", WechatLoginConstants.APPSECRET);
        paramMap.put("grant_type", WechatLoginConstants.GRANT_TYPE);
        paramMap.put("code", code);
        String openIdAccessToken = HttpUtils.sendGet(WechatLoginConstants.GET_ACESSTOKEN_OPENID_HOST
                ,HttpUtils.buildUpGetParams(paramMap));
        JSONObject jsonObject = JSON.parseObject(openIdAccessToken);
        if(StringUtils.isEmpty(jsonObject.getString("openid"))){
            throw new BusinessException("请求失败");
        }
        String accessToken = jsonObject.getString("access_token");
        String openId = jsonObject.getString("openid");
        paramMap.clear();
        paramMap.put("access_token",accessToken);
        paramMap.put("openid",openId);
        paramMap.put("lang",WechatLoginConstants.LANG);
        String result = HttpUtils.sendGet(WechatLoginConstants.GET_WECHAT_INFO_HOST,HttpUtils.buildUpGetParams(paramMap));
        JSONObject resultObject = JSON.parseObject(result);
        if(StringUtils.isEmpty(resultObject.getString("openid"))){
            throw new BusinessException("请求失败");
        }
        List<CustomerWechatInfo> customerWechatInfos = customerWechatInfoMapper.selectByOpenId(
                resultObject.getString("openid"));
        if(org.springframework.util.CollectionUtils.isEmpty(customerWechatInfos)){
            resultObject.put("isVerifyFlag",false);
        }else {
            CustomerWechatInfo customerWechatInfo = customerWechatInfos.get(0);
            resultObject.put("isVerifyFlag",true);
            Integer resultCustomerId;
            if(null != taxSourceId) {
                Integer customerId = customerWechatInfos.get(0).getCustomerId();
                CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerId);
                OnlineCustomerInfo onlineCustomerInfo = onlineCustomerInfoMapper.selectByIdCardNoTaxSourceId(
                        customerInfo.getCertificateNo(), taxSourceId);
                resultCustomerId = onlineCustomerInfo.getCustomerId();
            }else{
                resultCustomerId = customerWechatInfo.getCustomerId();
            }
            resultObject.put("customerId",resultCustomerId);
            CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(resultCustomerId);
            resultObject.put("idCardNo",customerInfo.getCertificateNo());
            resultObject.put("mobile",customerWechatInfo.getLoginMobile());
        }
        return resultObject;
    }

    @Override
    public JSONObject apLogin(String code) {
        Map<String,String> paramMap = new HashMap<>(4);
        paramMap.put("appid",WechatLoginConstants.APP_ID);
        paramMap.put("secret",WechatLoginConstants.SECRET);
        paramMap.put("grant_type", WechatLoginConstants.GRANT_TYPE);
        paramMap.put("js_code",code);
        String resultObject = HttpUtils.sendGet(WechatLoginConstants.APP_LOGIN_HOST,
                HttpUtils.buildUpGetParams(paramMap));
        JSONObject jsonObject = JSON.parseObject(resultObject);
        if (StringUtils.isEmpty(jsonObject.getString("openid"))){
            throw new BusinessException("请求失败");
        }
        List<CustomerWechatInfo> customerWechatInfos = customerWechatInfoMapper.selectByOpenId(
                jsonObject.getString("openid"));
        if(org.springframework.util.CollectionUtils.isEmpty(customerWechatInfos)){
            jsonObject.put("isVerifyFlag",false);
        }else {
            CustomerWechatInfo customerWechatInfo = customerWechatInfos.get(0);
            jsonObject.put("isVerifyFlag",true);
            Integer resultCustomerId = customerWechatInfo.getCustomerId();
            jsonObject.put("customerId",resultCustomerId);
            CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(resultCustomerId);
            jsonObject.put("idCardNo",customerInfo.getCertificateNo());
            jsonObject.put("hasQuit",customerWechatInfo.getHasQuit());
            jsonObject.put("mobile",customerWechatInfo.getLoginMobile());
        }
        jsonObject.remove("session_key");
        return jsonObject;
    }

    @Override
    public void quit(Integer customerId) {
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerId);
        List<CustomerInfo> customerInfos = customerInfoMapper.selectListByTimeAsc(customerInfo.getCertificateNo());
        customerInfos.forEach(one->{
            CustomerWechatInfo customerWechatInfo = customerWechatInfoMapper.selectByCustomerIdHasQuit(one.getId()
                    ,WechatHasQuitEnum.NO_QUIT.getCode());
            if(null != customerWechatInfo) {
                customerWechatInfo.setHasQuit(WechatHasQuitEnum.QUIT.getCode());
                customerWechatInfo.setUpdateTime(DateUtil.date());
                customerWechatInfoMapper.updateByPrimaryKeySelective(customerWechatInfo);
            }
        });
    }

    @Override
    public void updateMobile(UpdateMobileDTO dto) {
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(dto.getCustomerId());
        String newMobileKey = SmsConstant.CHANGE_MOBILE + customerInfo.getCertificateNo() + "-" + dto.getNewMobile();
        if(!RedisUtil.exists(newMobileKey)){
            throw new BusinessException("短信验证码失效，请重试");
        }
        String newMobileCode = RedisUtil.get(newMobileKey);
        if(!newMobileCode.equals(dto.getNewMobileSms())){
            throw new BusinessException("短信验证码不正确，请重试");
        }
        CustomerWechatInfo customerWechatInfo = customerWechatInfoMapper.selectByCustomerIdHasQuit(dto.getCustomerId()
                ,WechatHasQuitEnum.NO_QUIT.getCode());
        customerWechatInfo.setLoginMobile(dto.getNewMobile());
        customerWechatInfo.setUpdateTime(DateUtil.date());
        customerWechatInfoMapper.updateByPrimaryKeySelective(customerWechatInfo);
    }

    @Override
    public void sendSmsCode(VerifyCustomerDTO dto) {
        if(!RedisUtil.exists(dto.getToken()) || !RedisUtil.get(dto.getToken()).equals(dto.getPictureCode())){
            throw new BusinessException("图片验证码不正确");
        }
        if(StringUtils.isEmpty(dto.getIdcardNo())){
            throw new BusinessException("身份证不可为空");
        }
        if(!PatternVerifyUtil.isChinaPhoneLegal(dto.getMobile())){
            throw new BusinessException("不是大陆规范手机号");
        }
        sendSmsCode(dto.getIdcardNo(),dto.getMobile(),SmsConstant.CUSTOMER_VERIFY);
    }

    @Override
    public void sendSmsCodeCauseUpdateMobile(VerifyCustomerDTO dto) {
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(dto.getCustomerId());
        CustomerWechatInfo customerWechatInfo = customerWechatInfoMapper.selectByCustomerIdHasQuit(dto.getCustomerId()
                ,WechatHasQuitEnum.NO_QUIT.getCode());
        if(!StringUtils.isEmpty(dto.getVerfyCode())){
            String oldMobileKey = SmsConstant.CHANGE_MOBILE
                    + customerInfo.getCertificateNo() + "-" + customerWechatInfo.getLoginMobile();
            if(!RedisUtil.exists(oldMobileKey)){
                throw new BusinessException("短信验证码失效，请重试");
            }
            String oldMobileCode = RedisUtil.get(oldMobileKey);
            if(!oldMobileCode.equals(dto.getVerfyCode())){
                throw new BusinessException("短信验证码不正确，请重试");
            }
        }
        if(!PatternVerifyUtil.isChinaPhoneLegal(dto.getMobile())){
            throw new BusinessException("不是大陆规范手机号");
        }
        sendSmsCode(customerInfo.getCertificateNo(),dto.getMobile(), SmsConstant.CHANGE_MOBILE);
    }

    @Override
    public void sendSmsCode(String idCardNo, String mobile,String type){
        String code = "6666";
        String isTest = sysConfigService.selectConfigByKey(Constants.IS_SMS_TEST);
        if("N".equals(isTest)) {
            code = String.valueOf(new Random().nextInt(9999 - 1000 + 1) + 1000);
        }
        String cacheKey;
        if(null != idCardNo) {
            cacheKey = type + idCardNo + "-" + mobile;
        }else {
            cacheKey = type + mobile;
        }
        //默认5分钟
        RedisUtil.put(cacheKey,code);
        String smsContent = null;
        switch (type){
            case SmsConstant.CUSTOMER_VERIFY:
                smsContent = TiRuiYunUtil.SIGN_MMTAX+"###实名认证，验证码："+code;
                break;
            case SmsConstant.RESET_WITHDRAW_PASS_KEY:
                smsContent = TiRuiYunUtil.SIGN_MMTAX+"###重置支付密码，验证码："+code;
                break;
            case SmsConstant.CHANGE_MOBILE:
                smsContent = TiRuiYunUtil.SIGN_MMTAX+"###修改手机号，验证码："+code;
                break;
            default:
        }
        if("N".equals(isTest)) {
            JSONObject resultMap = TiRuiYunUtil.sendSmsTRYNew(mobile,TiRuiYunUtil.SIGN_MMTAX
                    ,TiRuiYunUtil.TEMPLATE_ID,smsContent)
                    .getJSONObject("response");
            String bizCode = resultMap.getString("code");
            if ("0".equals(bizCode)){
                //是否存数据库？
            }else {
                throw new BusinessException("验证码发送失败,"+resultMap.getString("msg"));
            }
        }
    }

    @Override
    public VerifyCustomerRespDTO verifyCustomer(VerifyCustomerDTO dto) {
        String cacheKey = SmsConstant.CUSTOMER_VERIFY + dto.getIdcardNo()+"-"+dto.getMobile();
        if(!RedisUtil.exists(cacheKey)){
            throw new BusinessException("短信验证码失效，请重试");
        }
        String verifyCode = RedisUtil.get(cacheKey);
        if(!verifyCode.equals(dto.getVerfyCode())){
            throw new BusinessException("短信验证码不正确，请重试");
        }
        List<CustomerInfo> customerInfos = customerInfoMapper.selectListByTimeAsc(dto.getIdcardNo());
        if(org.springframework.util.CollectionUtils.isEmpty(customerInfos)){
            throw new BusinessException("认证失败，账号不存在");
        }
        CustomerInfo customerInfo = customerInfos.get(0);
        if(!customerInfo.getRealName().equals(dto.getRealName())){
            throw new BusinessException("认证失败，请确认姓名");
        }
        customerInfos.forEach(one->{
            CustomerWechatInfo customerWechatInfo = customerWechatInfoMapper.selectByCustomerIdHasQuit(one.getId()
                    ,WechatHasQuitEnum.NO_QUIT.getCode());
            if(null != customerWechatInfo && !dto.getOpenId().equals(customerWechatInfo.getOpenId())){
                throw new BusinessException("该灵工已实名认证并登陆微信"+customerWechatInfo.getWechatName());
            }
        });
        CustomerWechatInfo customerWechatInfo = customerWechatInfoMapper.selectByCustomerIdHasQuit(customerInfo.getId()
                        ,null);
        if(null == customerWechatInfo || WechatHasQuitEnum.NO_QUIT.getCode().equals(customerWechatInfo.getHasQuit())){
            CustomerWechatInfo resultCustomerWechatInfo = Optional
                    .ofNullable(customerWechatInfo).orElse(new CustomerWechatInfo());
            resultCustomerWechatInfo.setCustomerId(customerInfo.getId());
            resultCustomerWechatInfo.setWechatName(dto.getWechatName());
            resultCustomerWechatInfo.setWechatNumber(dto.getWechatNumber());
            resultCustomerWechatInfo.setOpenId(dto.getOpenId());
            resultCustomerWechatInfo.setLoginMobile(dto.getMobile());
            resultCustomerWechatInfo.setUpdateTime(DateUtil.date());
            if(null == resultCustomerWechatInfo.getId()){
                resultCustomerWechatInfo.setCreateTime(DateUtil.date());
                customerWechatInfoMapper.insertSelective(resultCustomerWechatInfo);
            }else {
                customerWechatInfoMapper.updateByPrimaryKeySelective(resultCustomerWechatInfo);
            }
        }else{
            if(!customerWechatInfo.getLoginMobile().equals(dto.getMobile())){
                throw new BusinessException("与该灵工已绑定的验证手机号不一致");
            }
            customerInfos.forEach(one->{
                CustomerWechatInfo wechatInfo = customerWechatInfoMapper.selectByCustomerIdHasQuit(one.getId()
                        ,WechatHasQuitEnum.QUIT.getCode());
                wechatInfo.setWechatName(dto.getWechatName());
                wechatInfo.setWechatNumber(dto.getWechatNumber());
                wechatInfo.setOpenId(dto.getOpenId());
                wechatInfo.setLoginMobile(dto.getMobile());
                wechatInfo.setHasQuit(WechatHasQuitEnum.NO_QUIT.getCode());
                wechatInfo.setUpdateTime(DateUtil.date());
                customerWechatInfoMapper.updateByPrimaryKeySelective(wechatInfo);
            });
        }
        VerifyCustomerRespDTO resp = new VerifyCustomerRespDTO();
        resp.setCustomerId(customerInfo.getId());
        resp.setIsVerifyFlag(true);
        CusLinkMerInfo queryCus = new CusLinkMerInfo();
        queryCus.setCustomerId(customerInfo.getId());
        List<CusLinkMerInfo> cusLinkMerInfos = cusLinkMerInfoMapper.select(queryCus);
        boolean haveSign = true;
        for (CusLinkMerInfo one:cusLinkMerInfos) {
            haveSign = haveSign && customerProtocolService.judgeSign(customerInfo.getCertificateNo()
                    ,one.getMerchantId());
        }
        resp.setIsSignFlag(haveSign);
        return resp;
    }

    @Override
    public CustomerInfoDTO getCustomerInfo(Integer customerId) {
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerId);
        CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO();
        BeanUtils.copyProperties(customerInfo,customerInfoDTO);
        CustomerWechatInfo customerWechatInfo = customerWechatInfoMapper.selectByCustomerIdHasQuit(customerId
                ,WechatHasQuitEnum.NO_QUIT.getCode());
        customerInfoDTO.setMobile(customerWechatInfo.getLoginMobile());
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(customerInfo.getMerchantId());
        customerInfoDTO.setMerchantName(merchantInfo.getMerchantName());
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantInfo.getId());
        customerInfoDTO.setTaxSourceId(onlinePaymentInfo.getTaxSourceCompanyId());
        TaxSounrceCompany taxSounrceCompany = taxSounrceCompanyMapper.selectByPrimaryKey(
                onlinePaymentInfo.getTaxSourceCompanyId());
        customerInfoDTO.setTaxSourceName(taxSounrceCompany.getTaxSounrceCompanyName());
        return customerInfoDTO;
    }

    @Override
    public IndexInfoDTO indexInfo(Integer customerId,Integer pageSize,Integer currentPage,Integer year) {
        IndexInfoDTO indexInfoDTO = new IndexInfoDTO();
        CustomerAccount customerAccount = customerAccountMapper.selectByCustomerId(customerId);
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerId);
        indexInfoDTO.setCustomerName(customerInfo.getRealName());
        indexInfoDTO.setUsableAmount(String.valueOf(customerAccount.getUsableAmount()));
        Date nowDate = DateUtil.date();
        String startTime = DateUtil.formatDate(DateUtil.beginOfYear(nowDate));
        String endTime = DateUtil.formatDate(DateUtil.endOfYear(nowDate));
        BigDecimal yearIncomeSucess = trxOrderMapper.getSumAmountByMerchantIdCustomerId(startTime,endTime,null
                ,customerId);
        if(null == yearIncomeSucess){
            yearIncomeSucess = BigDecimal.ZERO;
        }
        indexInfoDTO.setYearIncome(yearIncomeSucess);
        startTime = DateUtil.formatDate(DateUtil.beginOfMonth(nowDate));
        endTime = DateUtil.formatDate(DateUtil.endOfMonth(nowDate));
        BigDecimal monthIncomeSucess = trxOrderMapper.getSumAmountByMerchantIdCustomerId(startTime,endTime,null
                ,customerId);
        if(null == monthIncomeSucess){
            monthIncomeSucess = BigDecimal.ZERO;
        }
        indexInfoDTO.setMonthIncome(monthIncomeSucess);
        DateTime yearDateTime = DateUtil.parse(String.valueOf(year), "yyyy");
        String startDate = DateUtil.formatDate(DateUtil.beginOfYear(yearDateTime));
        String endDate = DateUtil.formatDate(DateUtil.endOfYear(yearDateTime));
        indexInfoDTO.setPage(customerWithdrawService.pageIncome(pageSize,currentPage,customerId,startDate,endDate));
        return indexInfoDTO;
    }

    @Override
    public AccountInfoDTO getCustomerAccount(Integer customerId,String yearMonth) {
        AccountInfoDTO accountInfoDTO = new AccountInfoDTO();
        CustomerAccount customerAccount = customerAccountMapper.selectByCustomerId(customerId);
        accountInfoDTO.setAllAmount(customerAccount.getAmount());
        accountInfoDTO.setUsableAmount(customerAccount.getUsableAmount());
        accountInfoDTO.setFrozenAmount(customerAccount.getFrozenAmount());
        String startDate = DateUtil.format(
                DateUtil.beginOfMonth(DateUtil.parse(yearMonth,DateUtils.YYYY_MM)),DateUtils.YYYY_MM_DD);
        String endDate = DateUtil.format(
                DateUtil.endOfMonth(DateUtil.parse(yearMonth,DateUtils.YYYY_MM)),DateUtils.YYYY_MM_DD);
        BigDecimal incomeSuccess = trxOrderMapper.getSumAmountByMerchantIdCustomerId(startDate,endDate,null
                ,customerId);
        if(null == incomeSuccess){
            incomeSuccess = BigDecimal.ZERO;
        }
        accountInfoDTO.setIncomeAmount(incomeSuccess);
        BigDecimal withdrawSuccess = customerWithdrawMapper.sumAmountByCustomerId(startDate,endDate,customerId
                ,WithdrawStatusEnum.PAID.getCode(),null);
        if(null == withdrawSuccess){
            withdrawSuccess = BigDecimal.ZERO;
        }
        accountInfoDTO.setWithdrawAmount(withdrawSuccess);
        return accountInfoDTO;
    }

    @Override
    public Page getCustomerIncomeList(Integer customerId, Integer pageSize, Integer currentPage
            , String yearMonth) {
        QueryPage queryPage = convertQueryPage(currentPage,pageSize);
        String startDate = DateUtil.format(
                DateUtil.beginOfMonth(DateUtil.parse(yearMonth,DateUtils.YYYY_MM)),DateUtils.YYYY_MM_DD);
        String endDate = DateUtil.format(
                DateUtil.endOfMonth(DateUtil.parse(yearMonth,DateUtils.YYYY_MM)),DateUtils.YYYY_MM_DD);
        List<IncomeWithdrawDTO> incomeList = trxOrderMapper.listPageByCustomerIdCreateTimeStatus(customerId
                ,TrxOrderStatusEnum.PAID.getCode(),startDate,endDate,queryPage.getStartIndex(),pageSize);
        int count = trxOrderMapper.countByCustomerIdCreateTimeStatus(customerId,TrxOrderStatusEnum.PAID.getCode()
                ,startDate,endDate);
        return new Page(count,incomeList,currentPage,pageSize);
    }

    @Override
    public Page getCustomerWithdrawList(Integer customerId, Integer pageSize, Integer currentPage, String yearMonth) {
        QueryPage queryPage = convertQueryPage(currentPage,pageSize);
        String startDate = DateUtil.format(
                DateUtil.beginOfMonth(DateUtil.parse(yearMonth,DateUtils.YYYY_MM)),DateUtils.YYYY_MM_DD);
        String endDate = DateUtil.format(
                DateUtil.endOfMonth(DateUtil.parse(yearMonth,DateUtils.YYYY_MM)),DateUtils.YYYY_MM_DD);
        List<IncomeWithdrawDTO> withdrawList = customerWithdrawMapper.listPageByCustomerIdCreateTimeStatus(customerId
                ,null,startDate,endDate,queryPage.getStartIndex(),pageSize);
        int count = customerWithdrawMapper.countByCustomerIdCreateTimeStatus(customerId,null,startDate,endDate);
        return new Page(count,withdrawList,currentPage,pageSize);
    }

    @Override
    public Page getCustomerIncomeWithdrawList(Integer customerId, Integer pageSize, Integer currentPage, String yearMonth) {
        String startDate = DateUtil.format(
                DateUtil.beginOfMonth(DateUtil.parse(yearMonth,DateUtils.YYYY_MM)),DateUtils.YYYY_MM_DD);
        String endDate = DateUtil.format(
                DateUtil.endOfMonth(DateUtil.parse(yearMonth,DateUtils.YYYY_MM)),DateUtils.YYYY_MM_DD);
        List<IncomeWithdrawDTO> incomeList = trxOrderMapper.listPageByCustomerIdCreateTimeStatus(customerId
                ,TransferStatusEnum.PAID.getCode(),startDate,endDate,null,null);
        List<IncomeWithdrawDTO> withdrawList = customerWithdrawMapper.listPageByCustomerIdCreateTimeStatus(customerId
                ,null,startDate,endDate,null,null);
        List<IncomeWithdrawDTO> incomeWithdrawDTOList = new ArrayList<>();
        incomeWithdrawDTOList.addAll(incomeList);
        incomeWithdrawDTOList.addAll(withdrawList);
        incomeWithdrawDTOList.sort(
                Comparator.comparing(IncomeWithdrawDTO::getIncomeWithdrawTime,Comparator.reverseOrder()));
        int count = incomeWithdrawDTOList.size();
        int allPage = count / pageSize;
        if(count % pageSize > 0){
            allPage = allPage + 1;
        }
        int begin = (currentPage - 1) * pageSize;
        int end;
        if(currentPage != allPage) {
            end = begin + pageSize;
        }else {
            end = count ;
        }
        if (0 == count) {
            end = 0;
        }
        return new Page(count,incomeWithdrawDTOList.subList(begin,end),currentPage,pageSize);
    }

    @Override
    public StatistFlowRespDTO statistFlow(Integer customerId, String yearMonth) {
        String startDate = DateUtil.format(
                DateUtil.beginOfMonth(DateUtil.parse(yearMonth,DateUtils.YYYY_MM)), DateUtils.YYYY_MM_DD);
        String endDate = DateUtil.format(
                DateUtil.endOfMonth(DateUtil.parse(yearMonth,DateUtils.YYYY_MM)), DateUtils.YYYY_MM_DD);
        StatistFlowRespDTO statistFlowRespDTO = new StatistFlowRespDTO();
        //税源地信息
        OnlineCustomerInfo onlineCustomerInfo = onlineCustomerInfoMapper.selectByCustomerIdCustomerNo(customerId
                ,null);
        TaxSounrceCompany taxSounrceCompany = taxSounrceCompanyMapper.selectByPrimaryKey(
                onlineCustomerInfo.getTaxSourceId());
        statistFlowRespDTO.setTaxSourceId(taxSounrceCompany.getId());
        statistFlowRespDTO.setTaxSourceName(taxSounrceCompany.getTaxSounrceCompanyName());
        //收入和提现
        BigDecimal incomeSuccess = trxOrderMapper.getSumAmountByMerchantIdCustomerId(startDate,endDate,null
                ,customerId);
        if(null == incomeSuccess){
            incomeSuccess = BigDecimal.ZERO;
        }
        statistFlowRespDTO.setIncomeAmount(incomeSuccess);
        BigDecimal withdrawSuccess = customerWithdrawMapper.sumAmountByCustomerId(startDate,endDate,customerId
                ,WithdrawStatusEnum.PAID.getCode(),null);
        if(null == withdrawSuccess){
            withdrawSuccess = BigDecimal.ZERO;
        }
        statistFlowRespDTO.setWithdrawAmount(withdrawSuccess);
        //余额变化
        List<IncomeWithdrawDTO> balanceFlow = customerDayAmountChangeMapper.selectListByTime(customerId,startDate,endDate);
        String thisYearMonth = DateUtil.format(DateUtil.date(),DateUtils.YYYY_MM);
        if(thisYearMonth.equals(yearMonth)) {
            IncomeWithdrawDTO thisDay = new IncomeWithdrawDTO();
            CustomerAccount customerAccount = customerAccountMapper.selectByCustomerId(customerId);
            thisDay.setBalance(customerAccount.getUsableAmount());
            thisDay.setIncomeWithdrawTime(DateUtil.formatDate(DateUtil.date()));
            balanceFlow.add(thisDay);
        }
        statistFlowRespDTO.setBalanceFlow(balanceFlow);
        List<BankcardAlipayInfoDTO> bankcards = customerBankcardInfoMapper.listBankcard(customerId);
        List<BankcardAlipayInfoDTO> alipays = customerAlipayInfoMapper.listAlipay(customerId);
        bankcards.forEach(one->{
            BigDecimal withdrawAmount = customerWithdrawMapper.sumAmountByCustomerId(startDate,endDate,customerId
                    ,WithdrawStatusEnum.PAID.getCode(),one.getAccountNo());
            one.setWithdrawAmount(null == withdrawAmount?BigDecimal.ZERO:withdrawAmount);
            one.setBankName(customerBankcardInfoService.getBnakName(one.getAccountNo(),customerId));
        });
        statistFlowRespDTO.setBankIncomeList(bankcards);
        alipays.forEach(one->{
            BigDecimal withdrawAmount = customerWithdrawMapper.sumAmountByCustomerId(startDate,endDate,customerId
                    ,WithdrawStatusEnum.PAID.getCode(),one.getAccountNo());
            one.setWithdrawAmount(null == withdrawAmount?BigDecimal.ZERO:withdrawAmount);
        });
        statistFlowRespDTO.setAlipayIncomeList(alipays);
        return statistFlowRespDTO;
    }

    @Override
    public List<ChangeTaxSourceDTO> changeTaxSource(Integer customerId) {
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerId);
        String idCardNo = customerInfo.getCertificateNo();
        CustomerWechatInfo customerWechatInfo = customerWechatInfoMapper.selectByCustomerIdHasQuit(customerId
                ,WechatHasQuitEnum.NO_QUIT.getCode());
        List<CustomerInfo> customerInfos = customerInfoMapper.selectListByTimeAsc(idCardNo);
        List<ChangeTaxSourceDTO> result = new ArrayList<>(5);
        customerInfos.forEach(one->{
            ChangeTaxSourceDTO value = new ChangeTaxSourceDTO();
            Optional.ofNullable(customerWechatInfoMapper.selectByCustomerIdHasQuit(one.getId()
                    ,WechatHasQuitEnum.NO_QUIT.getCode()))
                    .orElseGet(()->{
                        CustomerWechatInfo wechatInfo = new CustomerWechatInfo();
                        BeanUtils.copyProperties(customerWechatInfo,wechatInfo);
                        wechatInfo.setId(null);
                        wechatInfo.setCustomerId(one.getId());
                        wechatInfo.setProviderId(null);
                        wechatInfo.setCreateTime(DateUtil.date());
                        wechatInfo.setUpdateTime(DateUtil.date());
                        customerWechatInfoMapper.insertSelective(wechatInfo);
                        return wechatInfo;
                    });
            value.setCustomerId(one.getId());
            value.setCustomerNo(one.getCustomerNo());
            value.setMerchantId(one.getMerchantId());
            MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(one.getMerchantId());
            value.setMerchantName(merchantInfo.getMerchantName());
            OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(one.getMerchantId());
            TaxSounrceCompany taxSounrceCompany = taxSounrceCompanyMapper.selectByPrimaryKey(
                    onlinePaymentInfo.getTaxSourceCompanyId());
            value.setTaxSourceId(taxSounrceCompany.getId());
            value.setTaxSourceName(taxSounrceCompany.getTaxSounrceCompanyName());
            result.add(value);
        });
        return result;
    }

    @Override
    public List<ChangeTaxSourceDTO> changeTaxSourceAfter(Integer customerId, Integer taxSourceId) {
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerId);
        String idCardNo = customerInfo.getCertificateNo();
        CustomerInfo changeCustomerInfo = customerInfoMapper.selectByIdCardNoAndTaxSourceId(idCardNo,taxSourceId);
        List<ChangeTaxSourceDTO> result = new ArrayList<>(5);
        List<CustomerInfo> customerInfos = customerInfoMapper.selectListByTimeAsc(idCardNo);
        ChangeTaxSourceDTO nowInfo = new ChangeTaxSourceDTO();
        nowInfo.setCustomerId(changeCustomerInfo.getId());
        nowInfo.setMerchantId(changeCustomerInfo.getMerchantId());
        nowInfo.setTaxSourceId(taxSourceId);
        TaxSounrceCompany taxSounrceCompany = taxSounrceCompanyMapper.selectByPrimaryKey(taxSourceId);
        nowInfo.setTaxSourceName(taxSounrceCompany.getTaxSounrceCompanyName());
        CustomerAccount customerAccount = customerAccountMapper.selectByCustomerId(changeCustomerInfo.getId());
        nowInfo.setBalance(customerAccount.getUsableAmount());
        result.add(nowInfo);
        customerInfos.forEach(one->{
            OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(one.getMerchantId());
            if(taxSourceId.equals(onlinePaymentInfo.getTaxSourceCompanyId())){
                return;
            }
            ChangeTaxSourceDTO value = new ChangeTaxSourceDTO();
            value.setCustomerId(one.getId());
            value.setCustomerNo(one.getCustomerNo());
            value.setMerchantId(one.getMerchantId());
            MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(one.getMerchantId());
            value.setMerchantName(merchantInfo.getMerchantName());
            TaxSounrceCompany sounrceCompany = taxSounrceCompanyMapper.selectByPrimaryKey(
                    onlinePaymentInfo.getTaxSourceCompanyId());
            value.setTaxSourceId(sounrceCompany.getId());
            value.setTaxSourceName(sounrceCompany.getTaxSounrceCompanyName());
            CustomerAccount account = customerAccountMapper.selectByCustomerId(one.getId());
            value.setBalance(account.getUsableAmount());
            result.add(value);
        });
        return result;
    }
}
