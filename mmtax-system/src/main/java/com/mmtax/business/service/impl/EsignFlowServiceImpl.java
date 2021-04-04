package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.*;
import com.mmtax.common.chanpay.BaseConstant;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.exception.PaymentException;
import com.mmtax.common.page.Page;
import com.mmtax.common.utils.EncrpytionUtil;
import com.mmtax.common.utils.IpUtils;
import com.mmtax.common.utils.MmtaxSign;
import com.mmtax.common.utils.esign.comm.CacheKeyConstant;
import com.mmtax.common.utils.esign.domain.request.CommonRequest;
import com.mmtax.common.utils.esign.domain.signflow.ConfigInfo;
import com.mmtax.common.utils.esign.domain.signflow.SignFlowStart;
import com.mmtax.common.utils.esign.helper.SignHelper;
import com.mmtax.common.utils.esign.helper.TokenHelper;
import com.mmtax.common.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.mmtax.common.utils.RegexUtil.*;

/**
 * 签约流程 服务层实现
 * 
 * @author meimiao
 * @date 2020-07-29
 */
@Service
@Slf4j
public class EsignFlowServiceImpl implements IEsignFlowService
{
    @Value("${esign.noticeDeveloperUrl}")
    private String NOTICE_DEVELOPER_URL;
    @Value("${esign.redirectUrl}")
    private String REDIRECT_URL;
    @Value("${esign.app.id}")
    public String APP_ID;
    @Value("${esign.app.secret}")
    public String APP_SECRET;

    @Autowired
    private MerchantInfoMapper merchantInfoMapper;
    @Autowired
    private BatchSignRecordMapper batchSignRecordMapper;
    @Autowired
    private ICustomerEsignInfoService customerEsignInfoService;
    @Autowired
    private IContractInfoService contractInfoService;
    @Autowired
    private CustomerProtocolMapper customerProtocolMapper;
    @Autowired
    private EsignFlowMapper esignFlowMapper;
    @Autowired
    private IEsignFlowDocService esignFlowDocService;
    @Autowired
    private IEsignFieldsService esignFieldsService;
    @Autowired
    private ICustomerProtocolService customerProtocolService;
    @Autowired
    private CustomerEsignInfoMapper customerEsignInfoMapper;
    @Autowired
    private ICustomerInfoService customerInfoService;
    @Autowired
    private ICustomerBankcardInfoService customerBankcardInfoService;
    @Autowired
    private ICustomerAlipayInfoService customerAlipayInfoService;
    @Autowired
    private IEsignTaxSourceService esignTaxSourceService;
    @Autowired
    private EsignTaxSourceMapper esignTaxSourceMapper;
    @Autowired
    private OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Autowired
    private EsignTemplateMapper esignTemplateMapper;
    @Autowired
    private MerchantKeyMapper merchantKeyMapper;
    @Autowired
    private CooperationMapper cooperationMapper;
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Autowired
    private OnlineCustomerInfoMapper onlineCustomerInfoMapper;
    @Autowired
    CustomerBankcardInfoMapper customerBankcardInfoMapper;
    @Autowired
    CustomerAlipayInfoMapper customerAlipayInfoMapper;
    @Autowired
    CustomerWechatInfoMapper customerWechatInfoMapper;
    @Autowired
    CusLinkMerInfoMapper cusLinkMerInfoMapper;
    @Autowired
    IEsignFlowService esignFlowService;
    @Autowired
    MerchantTaskInfoMapper merchantTaskInfoMapper;
    @Autowired
    PersonalMerProtocolMapper personalMerProtocolMapper;

    @Override
    public void setTokenToRedis(){
        if(!RedisUtil.exists(CacheKeyConstant.TOKEN)) {
            TokenHelper.getTokenData(APP_ID,APP_SECRET);
        }
    }

    @Override
    public Map batchUploadFile(MultipartFile file, SignCheckPassDTO dto) {
        if (file == null) {
            throw new BusinessException("批量代付上传文件不能为空");
        }
        log.info("批量签约上传文件，入参：{}", JSON.toJSONString(dto));
        int merchantId = dto.getMerchantId();
        MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfoById(merchantId);
        boolean checkPasswordResult = EncrpytionUtil.checkPassword(merchantInfo.getMerchantCode(),
                dto.getPassword(), merchantInfo.getPassword(), merchantInfo.getSalt());
        if (!checkPasswordResult) {
            throw new BusinessException("密码错误");
        }
        if(StringUtils.isEmpty(dto.getPost())){
            throw new BusinessException("签约岗位未选择");
        }
        if(StringUtils.isEmpty(dto.getPaymentChannel())){
            throw new BusinessException("签约验证渠道未选择");
        }

        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(dto.getMerchantId());
        List<Cooperation> cooperations = cooperationMapper.select(cooperation);
        if(SigningTypeEnum.WECHAT.getValue().equals(cooperations.get(0).getSigningType())){
            throw new BusinessException("上传失败,你已开通微信签约");
        }
        //获取文件内容
        ExcelReader excelReader = null;
        try {
            excelReader = ExcelUtil.getReader(file.getInputStream());
        } catch (IOException ignored) {

        }
        if(null == excelReader){
            throw new BusinessException("文件解析失败");
        }
        List<List<Object>> read = excelReader.read(2, 2);
        BatchSignRecord batchSignRecord = new BatchSignRecord();
        for (List<Object> objects : read) {
            if(objects.get(0) == null
                    || StringUtils.isEmpty(objects.get(0).toString().replace(" ", ""))){
                throw new BusinessException("请填写批次号");
            }
            log.info("批次号：{}",objects.get(0).toString().trim());
            batchSignRecord.setBatchNo(objects.get(0).toString().trim());
            batchSignRecord.setSignCount(Integer.valueOf(objects.get(1).toString().trim()));
        }
        BatchSignRecord checkHas = batchSignRecordMapper.selectByBatchNo(batchSignRecord.getBatchNo(),merchantId);
        if(null != checkHas){
            throw new BusinessException("批次号已存在");
        }
        batchSignRecord.setMerchantId(merchantId);
        batchSignRecord.setTrxExternalNo(ChanPayUtil.generateOutTradeNo());
        batchSignRecord.setCreater(merchantInfo.getMerchantName());
        batchSignRecord.setOperator(merchantInfo.getMerchantName());
        batchSignRecord.setStatus(BatchPaymentRecordStatusEnum.SUCCESS.code);
        batchSignRecord.setPostId(dto.getPostId());
        batchSignRecord.setPost(dto.getPost());
        batchSignRecord.setCreateTime(DateUtil.date());
        batchSignRecord.setUpdateTime(DateUtil.date());

        List<List<Object>> detailRead = excelReader.read(4, excelReader.getRowCount());
        List<SignInfoDTO> signInfoDTOs = new ArrayList<>();
        if (CollectionUtils.isEmpty(detailRead)) {
            throw new BusinessException("批量签约上传文件不能为空");
        }
        //保存错误信息
        List<SignBatchErrorResultDTO> signBatchErrorResultDTOS = new ArrayList<>();
        boolean isFail = Boolean.FALSE;
        for (List<Object> objects : detailRead) {
            SignInfoDTO signInfoDTO = new SignInfoDTO();
            //拼接错误信息
            StringBuilder errorMessage = new StringBuilder();
            if (objects.size() > 4 && objects.get(4) != null
                    && !"".equals(objects.get(4).toString().replace(" ", ""))) {
                signInfoDTO.setRemark(objects.get(4).toString());
            }
            if (objects.get(0) == null || "".equals(objects.get(0).toString().replace(" ", ""))) {
                errorMessage.append("姓名为空,");
                //throw new BusinessException("请输入姓名");
            }
            if (objects.get(1) == null || "".equals(objects.get(1).toString().replace(" ", ""))) {
                errorMessage.append("身份证号为空,");
                //throw new BusinessException("请输入身份证号");
            }
            if (objects.get(2) == null || "".equals(objects.get(2).toString().replace(" ", ""))) {
                errorMessage.append("手机号为空,");
                //throw new BusinessException("请输入手机号");
            }
            if (objects.get(3) == null || "".equals(objects.get(3).toString().replace(" ", ""))) {
                if(PaymentEnum.BANK.name().equals(dto.getPaymentChannel())){
                    errorMessage.append("银行卡号为空,");
                    //throw new BusinessException("请输入银行卡号");
                }else{
                    errorMessage.append("支付宝账号为空,");
                    //throw new BusinessException("请输入支付宝账号");
                }
            }
            signInfoDTO.setName(objects.get(0).toString().replace(" ", ""));
            signInfoDTO.setIdCardNo(objects.get(1).toString().replace(" ", ""));
            signInfoDTO.setMobile(objects.get(2).toString().replace(" ", ""));
            signInfoDTO.setBankNo(objects.get(3).toString().replace(" ", ""));
            signInfoDTO.setPost(dto.getPost());
            signInfoDTO.setPostId(dto.getPostId());
            //批量签约，姓名、身份证号、手机号相同视为同一条记录,银行卡号为最后一条记录
            if(signInfoDTOs.contains(signInfoDTO)){
                signInfoDTOs.set(signInfoDTOs.indexOf(signInfoDTO),signInfoDTO);
                continue;
            }
            signInfoDTO.setPaymentChannel(dto.getPaymentChannel());
            if(!SwitchEnum.ON.getCode().equals(cooperations.get(0).getSendOrderSwitch())){
                if(dto.getTemplateId() >= 6){
                    errorMessage.append("非派单商户，不可选择派单模板,");
                    //throw new BusinessException("非派单商户，不可选择派单模板");
                }
            }
            signInfoDTO.setEsignTemplateId(dto.getTemplateId());
            if (signInfoDTO.getRemark() != null && signInfoDTO.getRemark().length() > 40) {
                log.info("备注{}",signInfoDTO.getRemark());
                errorMessage.append("备注长度不可超过40个字,");
                //throw new BusinessException("姓名为" +signInfoDTO.getName() + "的用户,备注长度不可超过40个字！");
            }
            if(!regexName(signInfoDTO.getName())){
                log.info("姓名{}",signInfoDTO.getName());
                errorMessage.append("收款户名填写错误,");
                //throw new BusinessException("身份证号为" +signInfoDTO.getIdCardNo() + "的用户,收款户名填写错误！");
            }
            if(!IdcardUtil.isValidCard(signInfoDTO.getIdCardNo())){
                log.info("身份证{}",signInfoDTO.getIdCardNo());
                errorMessage.append("身份证号填写错误,");
                //throw new BusinessException("姓名为" +signInfoDTO.getName() + "的用户,身份证号填写错误！");
            }
            if(!regexPhoneLegalNo(signInfoDTO.getMobile())){
                log.info("手机号{}",signInfoDTO.getMobile());
                errorMessage.append("手机号填写错误,");
                //throw new BusinessException("姓名为" +signInfoDTO.getName() + "的用户,手机号填写错误！");
            }
            if (PaymentEnum.BANK.name().equals(dto.getPaymentChannel()) && !regexBankNo(signInfoDTO.getBankNo())) {
                log.info("银行卡{}",signInfoDTO.getBankNo());
                errorMessage.append("银行卡号填写错误,");
                //throw new BusinessException("姓名为" +signInfoDTO.getName() + "的用户,银行卡号填写错误！");
            }

//            OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
//            CustomerEsignInfo customerEsignInfo = customerEsignInfoMapper.selectByIdCardAndTaxSourceId(
//                    signInfoDTO.getIdCardNo(),onlinePaymentInfo.getTaxSourceCompanyId());
//            if(null != customerEsignInfo && !dto.getMerchantId().equals(customerEsignInfo.getMerchantId())){
//                throw new BusinessException("模板中有灵工属于别家商户");
//            }
            signInfoDTOs.add(signInfoDTO);
            SignBatchErrorResultDTO signBatchErrorResultDTO = new SignBatchErrorResultDTO();
            BeanUtils.copyProperties(signInfoDTO,signBatchErrorResultDTO);
            if(!"".equals(errorMessage.toString())){
                isFail = Boolean.TRUE;
                signBatchErrorResultDTO.setCheckResult("失败");
                signBatchErrorResultDTO.setComment(errorMessage.toString());
                signBatchErrorResultDTOS.add(signBatchErrorResultDTO);
            }
        }
        //抛出所有的错误信息
        if (isFail){

            Map<String,String> map = new HashMap<>();
            map.put("batchNo",batchSignRecord.getBatchNo());
            map.put("batchErrorResultDTOS",
                    JSON.toJSONString(signBatchErrorResultDTOS, SerializerFeature.WriteMapNullValue));
            String key = merchantId + batchSignRecord.getBatchNo() + System.currentTimeMillis();
            map.put("key",key);
            Page<Object> page = new Page<>(1, 10, signBatchErrorResultDTOS.size());
            map.put("totalPage",page.getTotalPage()+"");
            map.put("totalRecord",signBatchErrorResultDTOS.size()+"");
            RedisUtil.put(key,map,RedisUtil.ONE,TimeUnit.HOURS);

            Map<String,Object> resultMap = new HashMap<>(map);
            if(signBatchErrorResultDTOS.size()<10){
                resultMap.put("batchErrorResultDTOS",signBatchErrorResultDTOS.subList(0,signBatchErrorResultDTOS.size()));
            }else{
                resultMap.put("batchErrorResultDTOS",signBatchErrorResultDTOS.subList(0,10));
            }


            return resultMap;
        }
        RedisUtil.put(batchSignRecord.getBatchNo()+merchantInfo.getMerchantCode()
                ,signInfoDTOs,RedisUtil.TEN,TimeUnit.MINUTES);

        batchSignRecord.setActualCount(signInfoDTOs.size());
        RedisUtil.put(batchSignRecord.getBatchNo()+merchantId,batchSignRecord,RedisUtil.TEN, TimeUnit.MINUTES);

        Map<String,String> map = new HashMap<>(8);
        map.put("batchNo",batchSignRecord.getBatchNo());
        map.put("num",batchSignRecord.getSignCount().toString());
        map.put("successNum",batchSignRecord.getActualCount().toString());
        map.put("failNum",String.valueOf(batchSignRecord.getSignCount() - batchSignRecord.getActualCount()));
        return map;
    }

    @Override
    public Map getSignErrorData(Integer currentPage,Integer pageSize,String key){

        Map<String,String> resultMap = JSONObject.parseObject(RedisUtil.get(key),Map.class);
        List<SignBatchErrorResultDTO> signBatchErrorResultDTOS =
                JSONObject.parseArray(resultMap.get("batchErrorResultDTOS"), SignBatchErrorResultDTO.class);
        Page<Object> page = new Page<>(currentPage, pageSize, signBatchErrorResultDTOS.size());
        Map<String,Object> map = new HashMap<>(resultMap);
        int startRecord = page.getStartRecord();
        if(startRecord + pageSize > signBatchErrorResultDTOS.size()){
            resultMap.put("batchErrorResultDTOS",
                    JSON.toJSONString(signBatchErrorResultDTOS.subList(startRecord,signBatchErrorResultDTOS.size()),
                            SerializerFeature.WriteMapNullValue));
            map.put("batchErrorResultDTOS",signBatchErrorResultDTOS.subList(startRecord,signBatchErrorResultDTOS.size()));
        }else{
            resultMap.put("batchErrorResultDTOS",
                    JSON.toJSONString(signBatchErrorResultDTOS.subList(startRecord,startRecord + pageSize),
                            SerializerFeature.WriteMapNullValue));
            map.put("batchErrorResultDTOS",signBatchErrorResultDTOS.subList(startRecord,startRecord + pageSize));

        }
        return map;
    }
    @Override
    public void batchSign(String batchNo,Integer merchantId) {
        MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfoById(merchantId);
        if(!RedisUtil.exists(batchNo+merchantId) || !RedisUtil.exists(batchNo+merchantInfo.getMerchantCode())){
            RedisUtil.remove(batchNo+merchantId);
            RedisUtil.remove(batchNo+merchantInfo.getMerchantCode());
            throw new BusinessException("缓存失效，请重新上传文件");
        }
        BatchSignRecord batchSignRecord = RedisUtil.get(batchNo+merchantId,BatchSignRecord.class);
        Object object = RedisUtil.get(batchNo+merchantInfo.getMerchantCode());
        JSONArray jsonArray = JSONArray.fromObject(object);
        List<SignInfoDTO> list = (ArrayList<SignInfoDTO>) JSONArray.toCollection(jsonArray, SignInfoDTO.class);
        RedisUtil.remove(batchNo+merchantId);
        RedisUtil.remove(batchNo+merchantInfo.getMerchantCode());
        if(null == batchSignRecord || CollectionUtils.isEmpty(list)){
            throw new BusinessException("未获取到批量记录和数据，请重新上传文件");
        }
        batchSignRecordMapper.insertSelective(batchSignRecord);
        Cooperation quertWechatSignStatusCooperation = new Cooperation();
        quertWechatSignStatusCooperation.setMerchantId(merchantId);
        List<Cooperation> cooperations = cooperationMapper.select(quertWechatSignStatusCooperation);
        if(SigningTypeEnum.WECHAT.getValue().equals(cooperations.get(0).getSigningType())){
            throw new BusinessException("上传失败,你已开通微信签约");
        }
        log.info("开始批量签约--------");
        list.forEach(one->{
            signUp(one,batchNo,one.getEsignTemplateId(),merchantId);
        });
        log.info("结束批量签约--------");
    }

    @Override
    public String signUp(SignInfoDTO dto,String batchNo,Integer esignTemplateId,Integer merchantId){
        BatchSignRecord batchSignRecord = null;
        if(null != batchNo) {
            batchSignRecord = batchSignRecordMapper.selectByBatchNo(batchNo, merchantId);
            if (null == batchSignRecord) {
                throw new BusinessException("未找到批量记录" + batchNo);
            }
        }
        //创建签约记录
        CustomerProtocol customerProtocol = new CustomerProtocol();
        customerProtocol.setBatchNo(batchNo);
        customerProtocol.setSignSerialNum(ChanPayUtil.generateOutTradeNo());
        customerProtocol.setName(dto.getName());
        customerProtocol.setRemark(dto.getRemark());
        customerProtocol.setPostId(dto.getPostId());
        //签约岗位
        customerProtocol.setPost(dto.getPost());
        if(!StringUtils.isEmpty(dto.getSignSerialNum())){
            customerProtocol.setMerchantSerialNum(dto.getSignSerialNum());
        }
        customerProtocol.setMerchantId(merchantId);
        //创建个人账户
        CustomerEsignInfo customerEsignInfo;
        try {
            customerEsignInfo = customerEsignInfoService.createPerson(dto.getIdCardNo(),dto.getName(),dto.getMobile()
                    , merchantId,null);
        }catch(BusinessException e){
            log.info(e.getMessage());
            if(null != batchSignRecord) {
                batchSignRecord.setActualCount(batchSignRecord.getActualCount() - 1);
                batchSignRecord.setUpdateTime(DateUtil.date());
                batchSignRecordMapper.updateByPrimaryKeySelective(batchSignRecord);
            }
            return e.getMessage();
        }
        customerProtocol.setCusEsignId(customerEsignInfo.getId());
        customerProtocol.setThirdPartyUserId(customerEsignInfo.getThirdPartyUserId());
        customerProtocol.setSendSignUrlStatus(SendSignUrlStatusEnum.NOSEND.getCode());
        customerProtocol.setSignStatus(SignStatusEnum.INIT.getCode());
        customerProtocol.setTaxSourceId(customerEsignInfo.getTaxSourceId());
        customerProtocol.setCreateTime(DateUtil.date());
        customerProtocol.setUpdateTime(DateUtil.date());
        customerProtocolMapper.insertSelective(customerProtocol);

        String name = dto.getName(),mobile = dto.getMobile(),idCardNo = dto.getIdCardNo();
        //签约短信一天内超过五条，不发送短信
        Integer cusEsignId = customerProtocol.getCusEsignId();
        Integer countSignSms = customerProtocolMapper.countSignSms(cusEsignId,merchantId
                ,SendSignUrlStatusEnum.SEND.getCode());
        log.info("今天已发送的短信条数数是 {}",countSignSms);
        if (countSignSms  >= SendMessageLimitNumberEnum.SENDNUMBER.getCode()) {
            if(null != batchSignRecord) {
                batchSignRecord.setActualCount(batchSignRecord.getActualCount() - 1);
                batchSignRecord.setUpdateTime(DateUtil.date());
                batchSignRecordMapper.updateByPrimaryKeySelective(batchSignRecord);
            }
            customerProtocol.setComment("该用户当日发送短信条数已达到上线，请次日再试");
            customerProtocolMapper.updateByPrimaryKeySelective(customerProtocol);
            return customerProtocol.getComment();
        }


        //二要素验证
        CustomerInfo customerInfo = null;
        try {
            customerInfo = customerInfoService.queryCustomerInfo(name,mobile,idCardNo,merchantId
                    ,customerEsignInfo.getId(),null);
        }catch(BusinessException e){
            log.info("签约时获取灵工账号失败，{}",e.getMessage());
            customerProtocol.setComment(e.getMessage());
        }catch(PaymentException e){
            log.info(e.getMessage());
            customerProtocol.setComment(e.getMessage());
        }catch (Exception e){
            log.error("签约时获取灵工账号系统错误："+e.getMessage());
            customerProtocol.setComment("系统错误,请联系管理员");
        }
        if(null == customerInfo){
            if(null != batchSignRecord) {
                batchSignRecord.setActualCount(batchSignRecord.getActualCount() - 1);
                batchSignRecord.setUpdateTime(DateUtil.date());
                batchSignRecordMapper.updateByPrimaryKeySelective(batchSignRecord);
            }
            return customerProtocol.getComment();
        }
        //三要素验证
        String paymentChannel = dto.getPaymentChannel();
        try {
            //根据代付渠道获取信息或绑定信息
            if(PaymentEnum.BANK.name().equals(paymentChannel)){
                //获取银行卡信息
                CustomerBankcardInfo customerBankcardInfo = customerBankcardInfoService.queryBankcardInfo(
                        name,mobile,dto.getBankNo(),customerInfo.getId());
            }else if(PaymentEnum.ALIPAY.name().equals(paymentChannel)){
                //获取支付宝信息
                CustomerAlipayInfo customerAlipayInfo = customerAlipayInfoService.queryAlipayInfo(
                        name,mobile,dto.getBankNo(),customerInfo.getId());
            }
        }catch (BusinessException e){
            String common;
            log.info(e.getMessage());
            common = e.getMessage();
            updateFailCustomerProtocol(customerProtocol,common,batchSignRecord);
            return common;
        }catch(PaymentException e){
            log.info(e.getMessage());
            String common = e.getMessage();
            updateFailCustomerProtocol(customerProtocol,common,batchSignRecord);
            return common;
        }catch (Exception e){
            log.error("获取打款渠道系统错误："+e.getMessage());
            String common = "";
            common = "系统错误，请联系开发人员";
            updateFailCustomerProtocol(customerProtocol,common,batchSignRecord);
            return common;
        }

        //e签宝个人账号开户
        customerEsignInfo = customerEsignInfoService.createEsignAccount(customerEsignInfo,dto.getMobile());
        if(!CustomerEsignStatusEnum.OPEN.getCode().equals(customerEsignInfo.getStatus())){
            log.info("易签宝开户失败：{}",customerEsignInfo.getComment());
            updateFailCustomerProtocol(customerProtocol,customerEsignInfo.getComment(),batchSignRecord);
            return customerEsignInfo.getComment();
        }
        //开户成功更新个人账号id
        customerProtocol.setAccountId(customerEsignInfo.getAccountId());
        customerProtocol.setUpdateTime(DateUtil.date());
        customerProtocolMapper.updateByPrimaryKeySelective(customerProtocol);

        //设置员工静默签署
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(merchantId);
        cooperation = cooperationMapper.selectOne(cooperation);
        if(AutoSignOpenEnum.OPEN.getCode().equals(cooperation.getSilentSignSwitch())){
            if(!AutoSignStatusEnum.AUTOSIGNSUCCESS.getCode().equals(customerEsignInfo.getAutoSignStatus())) {
                customerEsignInfo = customerEsignInfoService.setAutoSign(customerEsignInfo.getId(),true);
            }
            if(!AutoSignStatusEnum.AUTOSIGNSUCCESS.getCode().equals(customerEsignInfo.getAutoSignStatus())){
                String commom = "";
                log.info(commom);
                updateFailCustomerProtocol(customerProtocol,commom,batchSignRecord);
                return commom;
            }
        }


        //创建关联签约文档
        ContractInfo contractInfo;
        try {
            Integer customerId = customerInfo.getId();
            contractInfo = contractInfoService.createContractInfo(esignTemplateId, customerEsignInfo,merchantId
                    ,customerId);
        }catch(PaymentException e){
            log.info("创建签约文件失败：{}",e.getMessage());
            String common = e.getMessage();
            updateFailCustomerProtocol(customerProtocol,common,batchSignRecord);
            return common;
        }catch (Exception e){
            log.info("创建签约文件失败：{}",e.getMessage());
            String common = "系统错误，创建签约文件失败";
            updateFailCustomerProtocol(customerProtocol,common,batchSignRecord);
            return common;
        }
        if(null != contractInfo){
            customerProtocol.setFileName(contractInfo.getFileName());
            customerProtocol.setConInfoId(contractInfo.getId());
            customerProtocol.setUpdateTime(DateUtil.date());
            customerProtocolMapper.updateByPrimaryKeySelective(customerProtocol);
        }
        //创建签署流程
        EsignFlow esignFlow = createFlow(customerProtocol.getCusEsignId(),customerProtocol.getFileName()
                ,customerProtocol.getTaxSourceId(),customerProtocol,CustomerProtocol.class,null);
        if(EsignFlowStatusEnum.FAIL.getStatus().equals(esignFlow.getStatus())){
            log.info(esignFlow.getFlowDesc());
            updateFailCustomerProtocol(customerProtocol,"系统错误，创建签约流程失败",batchSignRecord);
            return "系统错误，创建签约流程失败";
        }
        customerProtocol.setEsignFlowId(esignFlow.getId());
        customerProtocol.setUpdateTime(DateUtil.date());
        customerProtocolMapper.updateByPrimaryKeySelective(customerProtocol);
        //添加流程文档
        CommonRequest addFlowDocReq = esignFlowDocService.addFlowFile(esignFlow,contractInfo);
        if(0 != addFlowDocReq.getCode()){
            log.info("签约流程添加签约文档失败");
            updateFailCustomerProtocol(customerProtocol,"系统错误，签约流程添加签约文档失败",batchSignRecord);
            return "系统错误，签约流程添加签约文档失败";
        }
        //创建签署区
        try {
            if(AutoSignOpenEnum.CLOSE.getCode().equals(cooperation.getSilentSignSwitch())) {
                esignFieldsService.createFields(esignFlow, contractInfo, customerEsignInfo,"1");
            }else{
                esignFieldsService.createFieldsAuto(esignFlow,contractInfo,true);
            }
        }catch (BusinessException e){
            log.info("签约流程添加签署人签署区失败");
            updateFailCustomerProtocol(customerProtocol,"系统错误，签约流程添加签署人签署区失败",batchSignRecord);
            return "系统错误，签约流程添加签署人签署区失败";
        }
        //先盖上税源地的章
        try{
            esignTaxSourceService.createFieldsOfSource(esignFlow,contractInfo);
        }catch (BusinessException e){
            log.info("签约流程添加税源地签署区失败");
            updateFailCustomerProtocol(customerProtocol,"系统错误，甲方签约失败",batchSignRecord);
            return "系统错误，甲方签约失败";
        }
        //开启签署流程
        setTokenToRedis();
        CommonRequest startSignRep = SignHelper.startSignFlow(esignFlow.getFlowId());
        if (0 != startSignRep.getCode()) {
            EsignatureErrorCodeEnum failReasonEnum =
                    EsignatureErrorCodeEnum.getByEnumCode(String.valueOf(startSignRep.getCode()));
                log.info(failReasonEnum.getDescription());
                updateFailCustomerProtocol(customerProtocol,failReasonEnum.getModifyDescription(),batchSignRecord);
            return failReasonEnum.getModifyDescription();
        }
        esignFlow.setStatus(EsignFlowStatusEnum.BEGIN.getStatus());
        esignFlow.setUpdateTime(DateUtil.date());
        esignFlowMapper.updateByPrimaryKeySelective(esignFlow);
        //更新协议表状态
        customerProtocol.setSendSignUrlStatus(SendSignUrlStatusEnum.SEND.getCode());
        customerProtocol.setSendSignUrlTime(DateUtil.date());
        customerProtocol.setSignStatus(SignStatusEnum.NOSIGN.getCode());
        customerProtocol.setUpdateTime(DateUtil.date());
        customerProtocolMapper.updateByPrimaryKeySelective(customerProtocol);
        return "success";
    }

    @Override
    public String signUpOfPerMer(PersonalMerchant personalMerchant,Integer esignTemplateId) {
        //创建签约记录
        PersonalMerProtocol perMerProtocol = new PersonalMerProtocol();
        perMerProtocol.setSignSerialNum(ChanPayUtil.generateOutTradeNo());
        perMerProtocol.setName(personalMerchant.getApplyName());
        perMerProtocol.setApplyNumber(personalMerchant.getApplyNumber());
        perMerProtocol.setTaxSourceId(personalMerchant.getTaxSounrceCompanyId());
        //创建个人账户
        String name = personalMerchant.getApplyName();
        String idcardNo = personalMerchant.getIdCardNumber();
        String mobile = personalMerchant.getMobileNumber();
        Integer taxSoureId = personalMerchant.getTaxSounrceCompanyId();
        CustomerEsignInfo customerEsignInfo;
        try {
            customerEsignInfo = customerEsignInfoService.createPerson(idcardNo,name,mobile, -1,taxSoureId);
        }catch(BusinessException e){
            log.info(e.getMessage());
            throw e;
        }
        perMerProtocol.setCusEsignId(customerEsignInfo.getId());
        perMerProtocol.setSignStatus(SignStatusEnum.INIT.getCode());
        perMerProtocol.setCreateTime(DateUtil.date());
        perMerProtocol.setUpdateTime(DateUtil.date());
        personalMerProtocolMapper.insertSelective(perMerProtocol);

        //e签宝个人账号开户
        customerEsignInfo = customerEsignInfoService.createEsignAccount(customerEsignInfo,mobile);
        if(!CustomerEsignStatusEnum.OPEN.getCode().equals(customerEsignInfo.getStatus())){
            log.info("易签宝开户失败：{}",customerEsignInfo.getComment());
            updateFailPerMerProtocol(perMerProtocol,customerEsignInfo.getComment());
            throw new BusinessException(customerEsignInfo.getComment());
        }

        //创建关联签约文档
        ContractInfo contractInfo;
        try {
            contractInfo = contractInfoService.createContractInfo(esignTemplateId, customerEsignInfo,null
                    ,null);
        }catch(PaymentException e){
            log.info("创建签约文件失败：{}",e.getMessage());
            String common = e.getMessage();
            updateFailPerMerProtocol(perMerProtocol,common);
            throw new BusinessException(common);
        }catch (Exception e){
            log.info("创建签约文件失败：{}",e.getMessage());
            String common = "系统错误，创建签约文件失败";
            updateFailPerMerProtocol(perMerProtocol,common);
            throw new BusinessException(common);
        }
        if(null != contractInfo){
            perMerProtocol.setFileName(contractInfo.getFileName());
            perMerProtocol.setConInfoId(contractInfo.getId());
            perMerProtocol.setUpdateTime(DateUtil.date());
            personalMerProtocolMapper.updateByPrimaryKeySelective(perMerProtocol);
        }
        //创建签署流程
        String redirectUrl = "http://www.meimiaohealth.com/individualBusinesses/index.html";
        EsignFlow esignFlow = createFlow(perMerProtocol.getCusEsignId(),perMerProtocol.getFileName()
                ,perMerProtocol.getTaxSourceId(),perMerProtocol,PersonalMerProtocol.class,redirectUrl);
        if(EsignFlowStatusEnum.FAIL.getStatus().equals(esignFlow.getStatus())){
            log.info(esignFlow.getFlowDesc());
            updateFailPerMerProtocol(perMerProtocol,"系统错误，创建签约流程失败");
            throw new BusinessException("系统错误，创建签约流程失败");
        }
        perMerProtocol.setEsignFlowId(esignFlow.getId());
        perMerProtocol.setUpdateTime(DateUtil.date());
        personalMerProtocolMapper.updateByPrimaryKeySelective(perMerProtocol);
        //添加流程文档
        CommonRequest addFlowDocReq = esignFlowDocService.addFlowFile(esignFlow,contractInfo);
        if(0 != addFlowDocReq.getCode()){
            log.info("签约流程添加签约文档失败");
            updateFailPerMerProtocol(perMerProtocol,"系统错误，签约流程添加签约文档失败");
            throw new BusinessException("系统错误，签约流程添加签约文档失败");
        }
        //创建签署区
        try {
            esignFieldsService.createFields(esignFlow, contractInfo, customerEsignInfo,"0");
        }catch (BusinessException e){
            log.info("签约流程添加签署人签署区失败");
            updateFailPerMerProtocol(perMerProtocol,"系统错误，签约流程添加签署人签署区失败");
            throw new BusinessException("系统错误，签约流程添加签署人签署区失败");
        }
        //先盖上税源地的章
        try{
            esignTaxSourceService.createFieldsOfSource(esignFlow,contractInfo);
        }catch (BusinessException e){
            log.info("签约流程添加税源地签署区失败");
            updateFailPerMerProtocol(perMerProtocol,"系统错误，乙方签约失败");
            throw new BusinessException("系统错误，乙方签约失败");
        }
        //开启签署流程
        setTokenToRedis();
        CommonRequest startSignRep = SignHelper.startSignFlow(esignFlow.getFlowId());
        if (0 != startSignRep.getCode()) {
            EsignatureErrorCodeEnum failReasonEnum =
                    EsignatureErrorCodeEnum.getByEnumCode(String.valueOf(startSignRep.getCode()));
            log.info(failReasonEnum.getDescription());
            updateFailPerMerProtocol(perMerProtocol,failReasonEnum.getModifyDescription());
            throw new BusinessException(failReasonEnum.getModifyDescription());
        }
        esignFlow.setStatus(EsignFlowStatusEnum.BEGIN.getStatus());
        esignFlow.setUpdateTime(DateUtil.date());
        esignFlowMapper.updateByPrimaryKeySelective(esignFlow);
        //更新协议表状态
        perMerProtocol.setSignStatus(SignStatusEnum.NOSIGN.getCode());
        perMerProtocol.setUpdateTime(DateUtil.date());
        personalMerProtocolMapper.updateByPrimaryKeySelective(perMerProtocol);
        //获取签署链接
        setTokenToRedis();
        CommonRequest qrySignUrlReq = SignHelper.qrySignUrl(esignFlow.getFlowId(),customerEsignInfo.getAccountId()
                ,null,"0");
        if (0 != startSignRep.getCode()) {
            log.info("获取签署链接失败");
            updateFailPerMerProtocol(perMerProtocol,"获取签署链接失败");
            throw new BusinessException("获取签署链接失败");
        }
        return qrySignUrlReq.getData().getString("shortUrl");
    }

    private void updateFailCustomerProtocol(CustomerProtocol customerProtocol, String comment, BatchSignRecord batchSignRecord){
        customerProtocol.setSignStatus(SignStatusEnum.FAIL.getCode());
        customerProtocol.setComment(comment);
        customerProtocol.setUpdateTime(DateUtil.date());
        customerProtocolMapper.updateByPrimaryKeySelective(customerProtocol);
        if(null != batchSignRecord) {
            batchSignRecord.setActualCount(batchSignRecord.getActualCount() - 1);
            batchSignRecord.setUpdateTime(DateUtil.date());
            batchSignRecordMapper.updateByPrimaryKeySelective(batchSignRecord);
        }
//        if(RedisUtil.exists(CacheKeyConstant.TOKEN)) {
//            RedisUtil.remove(CacheKeyConstant.TOKEN);
//        }
    }

    private void updateFailPerMerProtocol(PersonalMerProtocol personalMerProtocol, String comment){
        personalMerProtocol.setSignStatus(SignStatusEnum.FAIL.getCode());
        personalMerProtocol.setComment(comment);
        personalMerProtocol.setUpdateTime(DateUtil.date());
        personalMerProtocolMapper.updateByPrimaryKeySelective(personalMerProtocol);
//        if(RedisUtil.exists(CacheKeyConstant.TOKEN)) {
//            RedisUtil.remove(CacheKeyConstant.TOKEN);
//        }
    }

    @Override
    public <T> EsignFlow createFlow(Integer cusEsignId,String fileName,Integer taxSourceId,T protocol,Class<T> c
            ,String redirectUrl) {
        EsignFlow esignFlow = new EsignFlow();
        esignFlow.setCusEsignId(cusEsignId);
        esignFlow.setAutoArchive(AutoArchiveEnum.TRUE.getStatus());
        esignFlow.setBusinessScene(fileName);
        esignFlow.setNoticeDeveloperUrl(NOTICE_DEVELOPER_URL);
        esignFlow.setRedirectUrl(REDIRECT_URL);
        if(!StringUtils.isEmpty(redirectUrl)) {
            esignFlow.setRedirectUrl(redirectUrl);
        }
        esignFlow.setNoticeType(NoticeTypeEnum.SMS.getStatus());
        if(protocol instanceof PersonalMerProtocol){
            esignFlow.setNoticeType(NoticeTypeEnum.NOTNEED.getStatus());
        }
        //过期时间写死 2年
        DateTime expireTime = DateUtil.offsetMonth(DateUtil.date(),24);
        esignFlow.setContractValidity(expireTime.getTime());
        esignFlow.setStatus(EsignFlowStatusEnum.INIT.getStatus());
        esignFlow.setTaxSourceId(taxSourceId);
        esignFlow.setDelStatus(0);
        esignFlow.setCreateTime(DateUtil.date());
        esignFlow.setUpdateTime(DateUtil.date());
        esignFlowMapper.insertSelective(esignFlow);
        if(protocol instanceof CustomerProtocol) {
            CustomerProtocol customerProtocol = (CustomerProtocol)protocol;
            customerProtocol.setEspireTime(expireTime);
            customerProtocol.setUpdateTime(DateUtil.date());
            customerProtocolMapper.updateByPrimaryKeySelective(customerProtocol);
        }else if(protocol instanceof PersonalMerProtocol){
            PersonalMerProtocol personalMerProtocol = (PersonalMerProtocol)protocol;
            personalMerProtocol.setEspireTime(expireTime);
            personalMerProtocol.setUpdateTime(DateUtil.date());
            personalMerProtocolMapper.updateByPrimaryKeySelective(personalMerProtocol);
        }
        //易签宝创建流程
        boolean autoArchive = false;
        if(AutoArchiveEnum.TRUE.getStatus().equals(esignFlow.getAutoArchive())){
            autoArchive = true;
        }
        ConfigInfo configInfo = new ConfigInfo(esignFlow.getNoticeDeveloperUrl(),String.valueOf(esignFlow.getNoticeType())
                ,esignFlow.getRedirectUrl(),"1");
        Long contractValidity = null;
        Long signValidity = null;
        if(null != esignFlow.getContractValidity()){
            contractValidity = esignFlow.getContractValidity();
        }
        if(null != esignFlow.getSignValidity()){
            signValidity = esignFlow.getSignValidity().longValue();
        }
        //添加发起方主体id
        String initiatorAuthorizedAccountId = null;
        EsignTaxSource esignTaxSource = esignTaxSourceMapper.selectByTaxSourceId(0);
        if(null != esignTaxSource){
            initiatorAuthorizedAccountId = esignTaxSource.getOrgId();
        }
        SignFlowStart sfs = new SignFlowStart(autoArchive,esignFlow.getBusinessScene()
                ,contractValidity,esignFlow.getContractRemind()
                ,signValidity,null,initiatorAuthorizedAccountId,configInfo);
        setTokenToRedis();
        CommonRequest request = SignHelper.createSignFlow(sfs);
        if(0 != request.getCode()){
            esignFlow.setStatus(EsignFlowStatusEnum.FAIL.getStatus());
            esignFlow.setUpdateTime(DateUtil.date());
            esignFlow.setFlowDesc("创建签署流程失败："+request.getMessage());
            esignFlowMapper.updateByPrimaryKeySelective(esignFlow);
            return esignFlow;
        }
        JSONObject data = request.getData();
        esignFlow.setStatus(EsignFlowStatusEnum.CREATESUCCESS.getStatus());
        esignFlow.setFlowId(data.getString("flowId"));
        esignFlow.setUpdateTime(DateUtil.date());
        esignFlowMapper.updateByPrimaryKeySelective(esignFlow);
        return esignFlow;
    }

    @Override
    public void handNotify(String reqBody) {
        JSONObject reqJson = JSONObject.parseObject(reqBody);
        switch(reqJson.getString("action")){
            //签署人签署完成回调通知
            case "SIGN_FLOW_UPDATE":
                customerProtocolService.dealNotify(reqJson);
                break;
                //流程结束回调通知
            case "SIGN_FLOW_FINISH":
                dealNotify(reqJson);
                break;
                //文件过期回调
            case "SIGN_DOC_EXPIRE":
                customerProtocolService.dealDocExpire(reqJson);
                break;
            default:
        }
    }

    private void dealNotify(JSONObject reqJson){
        String flowId = reqJson.getString("flowId");
        Integer flowStatus = reqJson.getInteger("flowStatus");
        String endTime = reqJson.getString("endTime");
        EsignFlow esignFlow = esignFlowMapper.seletByFlowId(flowId);
        if(null == esignFlow){
            log.info("未找到流程id为{}的流程记录",flowId);
            return;
        }
        esignFlow.setStatus(flowStatus);
        esignFlow.setFlowEndTime(endTime);
        esignFlow.setUpdateTime(DateUtil.date());
        esignFlowMapper.updateByPrimaryKeySelective(esignFlow);
    }

    @Override
    public JSONObject getSignDownUrl(Integer esignFlowId) {
        EsignFlow esignFlow = esignFlowMapper.selectByPrimaryKey(esignFlowId);
        setTokenToRedis();
        CommonRequest request = SignHelper.downloadFlowDoc(esignFlow.getFlowId());
        if(0 != request.getCode()){
            throw new BusinessException("获取下载地址失败");
        }
        JSONObject data = request.getData();
        return data;
    }

    @Override
    public List<JSONObject> getFlowIdByCustomerId(String idCardNo) {
        List<CustomerProtocol> customerProtocols = customerProtocolMapper.selectByIdCardNoSignStatus(idCardNo
                ,SignStatusEnum.SUCCESS.getCode());
        if(CollectionUtils.isEmpty(customerProtocols)){
            throw new BusinessException("您还没有完成的签约");
        }
        List<JSONObject> results = new ArrayList<>();
        customerProtocols.forEach(one->{
            JSONObject data = esignFlowService.getSignDownUrl(one.getEsignFlowId());
            results.add(data);
        });
        return results;
    }

    @Override
    public String customerSign(Integer customerId) {
        CustomerInfo customerInfo = customerInfoMapper.selectByPrimaryKey(customerId);
        CusLinkMerInfo queryCus = new CusLinkMerInfo();
        queryCus.setCustomerId(customerId);
        List<CusLinkMerInfo> cusLinkMerInfos = cusLinkMerInfoMapper.select(queryCus);
        for(CusLinkMerInfo one:cusLinkMerInfos){
            Cooperation cooperation = new Cooperation();
            cooperation.setMerchantId(one.getMerchantId());
            cooperation = cooperationMapper.selectOne(cooperation);
            if(!SigningTypeEnum.WECHAT.getValue().equals(cooperation.getSigningType())){
                MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(one.getMerchantId());
                throw new BusinessException("签约商户"+merchantInfo.getMerchantName()+"未开启微信签约");
            }
        }
        for(CusLinkMerInfo one:cusLinkMerInfos){
            Integer merchantId = one.getMerchantId();
            boolean judgeSign = customerProtocolService.judgeSign(customerInfo.getCertificateNo(),merchantId);
            if(judgeSign){
                log.info("灵工{}在商户{}下已签过约",customerInfo.getRealName(),merchantId);
                continue;
            }
            SignInfoDTO dto = new SignInfoDTO();
            String bankNo,paymentChannel;
            CustomerBankcardInfo bankcardInfo = new CustomerBankcardInfo();
            bankcardInfo.setCustomerId(customerId);
            bankcardInfo.setVerifyStatus(VerifyStatusEnum.VERIFY.getStatus());
            bankcardInfo.setBindStatus(BankcardOrAlipayBindEnum.BIND.getStatus());
            List<CustomerBankcardInfo> bankcardInfos = customerBankcardInfoMapper.select(bankcardInfo);
            CustomerAlipayInfo alipayInfo = new CustomerAlipayInfo();
            alipayInfo.setCustomerId(customerId);
            alipayInfo.setVerifyStatus(VerifyStatusEnum.VERIFY.getStatus());
            alipayInfo.setBindStatus(BankcardOrAlipayBindEnum.BIND.getStatus());
            List<CustomerAlipayInfo> alipayInfos = customerAlipayInfoMapper.select(alipayInfo);
            if(!org.springframework.util.CollectionUtils.isEmpty(bankcardInfos)){
                bankNo = bankcardInfos.get(0).getBankAccountNo();
                paymentChannel = PaymentEnum.BANK.name();
            }else if(!org.springframework.util.CollectionUtils.isEmpty(alipayInfos)){
                bankNo = alipayInfos.get(0).getAccountNo();
                paymentChannel = PaymentEnum.ALIPAY.name();
            }else {
                throw new BusinessException("账户余额为零，不能签约");
            }
            dto.setBankNo(bankNo);
            dto.setPaymentChannel(paymentChannel);
            dto.setName(customerInfo.getRealName());
            dto.setIdCardNo(customerInfo.getCertificateNo());
            dto.setPost("市场推广");
            MerchantTaskInfo merchantTaskInfo = new MerchantTaskInfo();
            merchantTaskInfo.setMerchantId(merchantId);
            merchantTaskInfo.setDelStatus(DelStatusEnum.NORMAL.code);
            List<MerchantTaskInfo> taskInfos = merchantTaskInfoMapper.select(merchantTaskInfo);
            if (!CollectionUtils.isEmpty(taskInfos)) {
                dto.setPostId(taskInfos.get(0).getTaskId());
                dto.setPost(taskInfos.get(0).getTaskName());
            }
            CustomerWechatInfo customerWechatInfo = customerWechatInfoMapper.selectByCustomerIdHasQuit(customerId
                    ,WechatHasQuitEnum.NO_QUIT.getCode());
            dto.setMobile(customerWechatInfo.getLoginMobile());
            OnlineCustomerInfo onlineCustomerInfo = onlineCustomerInfoMapper.selectByCustomerIdCustomerNo(
                    customerId,null);
            List<EsignTemplate> esignTemplates = esignTemplateMapper.selectByTaxSourceId(onlineCustomerInfo.getTaxSourceId());
            if(esignTemplates.size() < 1){
                throw new BusinessException("内部签约文件出错，请联系管理员");
            }
            Integer esignTemplateId = esignTemplates.get(0).getId();
            Cooperation cooperation = new Cooperation();
            cooperation.setMerchantId(merchantId);
            cooperation = cooperationMapper.selectOne(cooperation);
            if(SwitchEnum.ON.getCode().equals(cooperation.getSendOrderSwitch())){
                esignTemplateId = esignTemplates.get(1).getId();
            }
            dto.setEsignTemplateId(esignTemplateId);
            dto.setSignSerialNum(ChanPayUtil.generateOutTradeNo());
            String message = signUp(dto,null,esignTemplateId,merchantId);
            if(!"success".equals(message)){
                MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);
                throw new BusinessException("签约商户"+merchantInfo.getMerchantName()+"失败："+message);
            }
        }
        return "success";
    }

    @Override
    public NotifySignDTO apiSign(HttpServletRequest request, NotifySignReqDTO dto) {
        String data = JSON.toJSONString(dto);
        log.info("请求的参数：{}",data);
        NotifySignDTO resultDTO = new NotifySignDTO();
        resultDTO.setTransTime(dto.getRequireTime());
        if(StringUtils.isEmpty(dto.getMerchantNo())){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("商户号不可为空");
        }
        MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfoByCode(dto.getMerchantNo());
        if(null == merchantInfo){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("未找到商户，不予签约");
            return resultDTO;
        }
        String requestIp = IpUtils.getIpAddr(request);
        log.info("apiSign method request ip:{};params:{}", requestIp, data);
        MerchantKey merchantKey = merchantKeyMapper.getMerchantKeyByMerchantId(merchantInfo.getId());
        if (StringUtils.isEmpty(merchantKey.getWhiteUrl())) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("请配置白名单");
            return resultDTO;
        }else {
            String [] ips = merchantKey.getWhiteUrl().split(",");
            List<String> ipList = new ArrayList<>(Arrays.asList(ips));
            if (!ipList.contains(requestIp)) {
                resultDTO.setCode(ResultEnum.FAIL.name());
                resultDTO.setDescription("请配置白名单");
                return resultDTO;
            }
        }
        if(StringUtils.isEmpty(dto.getOrderId())){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("签约流水号不可为空");
        }
        CustomerProtocol customerProtocol = customerProtocolMapper.selectByMerchantSerialNum(
                dto.getOrderId(),merchantInfo.getId());
        if(null != customerProtocol){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("签约流水号已存在");
        }
        if(StringUtils.isEmpty(dto.getName())){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("姓名不可为空");
        }
        if(StringUtils.isEmpty(dto.getBankCardNo()) && StringUtils.isEmpty(dto.getAccountNo())){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("校验账户信息不可为空");
        }
        if(StringUtils.isEmpty(dto.getIdCardNo())){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("身份证不可为空");
        }
        if(StringUtils.isEmpty(dto.getMobileNo())){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("手机号不可为空");
        }
        if(StringUtils.isEmpty(dto.getDesKey())){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("秘钥不可为空");
        }
        if(StringUtils.isEmpty(dto.getVersion())){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("版本号不可为空");
        }
        if(StringUtils.isEmpty(dto.getPaymentChannel())){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("签约验证渠道不可为空");
        }
        if(StringUtils.isEmpty(dto.getRequireTime())){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("请求时间不可为空");
        }
        //签约岗位，默认为市场推广
        if(StringUtils.isEmpty(dto.getPost())){
            dto.setPost("市场推广");
        }
        if(ResultEnum.FAIL.name().equals(resultDTO.getCode())){
            return resultDTO;
        }
        //验签
        if (!dto.getDesKey().equals(merchantKey.getDesKey())) {
            resultDTO.setCode(ResultEnum.ERROR_APP_KEY.name());
            resultDTO.setDescription(ResultEnum.ERROR_APP_KEY.description);
            return resultDTO;
        }
        Map map = JSON.parseObject(data);
        map.remove("sign");
        map.remove("signType");
        map.remove("desKey");
        map.remove("queryOrderId");
        TreeMap<String, Object> treeMap = new TreeMap<>(map);
        String sign = MmtaxSign.signByMap(merchantKey.getAppKey(), treeMap, BaseConstant.CHARSET);
        resultDTO.setSign(sign);
        //校验签名
        if (StringUtils.isEmpty(sign) || !sign.equals(dto.getSign())) {
            resultDTO.setCode(ResultEnum.ILLEGAL_SIGN.name());
            resultDTO.setDescription(ResultEnum.ILLEGAL_SIGN.description);
            return resultDTO;
        }

        SignInfoDTO signInfoDTO = new SignInfoDTO();
        signInfoDTO.setPaymentChannel(dto.getPaymentChannel());
        signInfoDTO.setName(dto.getName());
        signInfoDTO.setIdCardNo(dto.getIdCardNo());
        signInfoDTO.setMobile(dto.getMobileNo());
        //签约岗位
        signInfoDTO.setPost(dto.getPost());
        if(PaymentEnum.BANK.name().equals(dto.getPaymentChannel())) {
            signInfoDTO.setBankNo(dto.getBankCardNo());
        }
        if(PaymentEnum.ALIPAY.name().equals(dto.getPaymentChannel())){
            signInfoDTO.setBankNo(dto.getAccountNo());
        }
        signInfoDTO.setSignSerialNum(dto.getOrderId());

        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantInfo.getId());
        List<EsignTemplate> esignTemplates = esignTemplateMapper.selectByTaxSourceId(
                onlinePaymentInfo.getTaxSourceCompanyId());
        if(esignTemplates.size() < 1){
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription("内部签约文件出错，请联系管理员");
            return resultDTO;
        }
        //签约
        String common = signUp(signInfoDTO,null,esignTemplates.get(0).getId(),merchantInfo.getId());
        if(!"success".equals(common)) {
            resultDTO.setCode(ResultEnum.FAIL.name());
            resultDTO.setDescription(common);
        } else {
            resultDTO.setCode(ResultEnum.SUCCESS.name());
            resultDTO.setDescription(ResultEnum.SUCCESS.description);
            resultDTO.setOrderId(dto.getOrderId());
        }
        return resultDTO;
    }
}
