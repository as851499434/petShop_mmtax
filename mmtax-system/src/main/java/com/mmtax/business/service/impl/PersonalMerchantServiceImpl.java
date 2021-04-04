package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.*;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.config.Global;
import com.mmtax.common.config.ServerConfig;
import com.mmtax.common.constant.AddMerchantInfoConstants;
import com.mmtax.common.constant.ApplyScheduleConstants;
import com.mmtax.common.constant.Constants;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.enums.ApplyScheduleEnum;
import com.mmtax.common.enums.DelStatusEnum;
import com.mmtax.common.enums.PerMerApplySchEnum;
import com.mmtax.common.enums.PerMerStatusEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.exception.PaymentException;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.DealInfoUtils;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.esign.domain.request.CommonRequest;
import com.mmtax.common.utils.esign.helper.SignHelper;
import com.mmtax.common.utils.file.PictureUploadUtils;
import com.mmtax.common.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 个体工商户 服务层实现
 *
 * @author meimiao
 * @date 2020-11-26
 */
@Service
@Slf4j
public class PersonalMerchantServiceImpl extends BaseServiceImpl implements IPersonalMerchantService {
    @Autowired
    TaxSounrceCompanyMapper taxSounrceCompanyMapper;
    @Autowired
    CustomerInfoMapper customerInfoMapper;
    @Autowired
    ICustomerInfoService customerInfoService;
    @Autowired
    ICustomerBankcardInfoService customerBankcardInfoService;
    @Autowired
    IEsignFlowService esignFlowService;
    @Resource
    private PersonalMerchantMapper personalMerchantMapper;
    @Autowired
    IOrderStatusInfoService orderStatusInfoService;
    @Autowired
    OrderStatusInfoMapper orderStatusInfoMapper;
    @Autowired
    IPerMerApplyScheduleService perMerApplyScheduleService;
    @Autowired
    private ServerConfig serverConfig;

    @Resource
    private TaxTypeMapper taxTypeMapper;

    @Resource
    private PersonalMerProtocolMapper personalMerProtocolMapper;

    @Resource
    private PerMerApplyScheduleMapper perMerApplyScheduleMapper;

    @Resource
    private ContractInfoMapper contractInfoMapper;

    @Resource
    private EsignFlowMapper esignFlowMapper;



    /**
     * 图片上传路径
     */
    public static final String PICTURE_UPLOAD_PATH = "/profile/pictures/";

    @Override
    public List<BusinessLicenseReqDTO> getBusinessLicenses(Integer wechatInfoId) {
        return personalMerchantMapper.seletBusinessLicenses(wechatInfoId);
    }

    @Override
    public Page<PerMerInfoDTO> getOrderList(Integer wechatInfoId, Integer status, Integer currentPage, Integer pageSize) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<PerMerInfoDTO> merInfos = personalMerchantMapper.selectPageByStatus(wechatInfoId, status
                , queryPage.getStartIndex(), pageSize);
        int count = personalMerchantMapper.selectCountByStatus(wechatInfoId, status);
        return new Page<>(count, merInfos, currentPage, pageSize);
    }

    @Override
    public void updateOrder(PersonalMerchant dto) {
        PersonalMerchant personalMerchant = personalMerchantMapper.selectByPrimaryKey(dto.getId());
        BeanUtils.copyProperties(dto, personalMerchant);
        personalMerchant.setProviderId(null);
        personalMerchant.setApplyNumber(null);
        personalMerchant.setWechatInfoId(null);
        personalMerchant.setCreateTime(null);
        personalMerchant.setUpdateTime(DateUtil.date());
        personalMerchantMapper.updateByPrimaryKeySelective(personalMerchant);
        OrderStatusInfo orderStatusInfo = new OrderStatusInfo();
        orderStatusInfo.setApplyId(personalMerchant.getId());
        orderStatusInfo = orderStatusInfoMapper.selectOne(orderStatusInfo);
        orderStatusInfo.setOrderStatus(PerMerStatusEnum.PROCESS.getCode());
        orderStatusInfo.setUpdateTime(DateUtil.date());
        orderStatusInfoMapper.updateByPrimaryKeySelective(orderStatusInfo);
        //插入流程日志
        perMerApplyScheduleService.insertInfo(personalMerchant.getId(),PerMerApplySchEnum.SUBMIT_AGAIN,1);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public String applyAndSign(Integer wechatInfoId) {
        String key = Constants.PERSON_MER_APPLY + wechatInfoId;
        if (!RedisUtil.exists(key)) {
            throw new BusinessException("缓存已失效，请刷新");
        }
        String value = RedisUtil.get(key);
        if (StringUtils.isEmpty(value)) {
            RedisUtil.remove(key);
            throw new BusinessException("缓存出错，请刷新");
        }
        PersonalMerchant personalMerchant = JSON.parseObject(value,PersonalMerchant.class);
        //保存申请
        personalMerchant.setApplyNumber(ChanPayUtil.generateOutTradeNo());
        personalMerchant.setCreateTime(DateUtil.date());
        personalMerchant.setUpdateTime(DateUtil.date());
        personalMerchantMapper.insertSelective(personalMerchant);
        OrderStatusInfo orderStatusInfo = orderStatusInfoService.initOrderStatus(personalMerchant);
        //添加流程记录
        perMerApplyScheduleService.insertInfo(personalMerchant.getId(), PerMerApplySchEnum.SUBMIT,1);
        //签协议,数据库模板id为8
        String signUrl;
        try {
            signUrl = esignFlowService.signUpOfPerMer(personalMerchant, 8);
        } catch (Exception e) {
            orderStatusInfo.setOrderStatus(PerMerStatusEnum.FAIL.getCode());
            orderStatusInfo.setUpdateTime(DateUtil.date());
            orderStatusInfoMapper.updateByPrimaryKeySelective(orderStatusInfo);
            throw e;
        }finally {
            RedisUtil.remove(key);
            RedisUtil.remove(personalMerchant.getBusinessLicenseName());
        }
        return signUrl;
    }

    /**
     * 获取c端签约地址
     * @param applyNumber
     * @return
     */
    @Override
    public JSONObject getCTerminalSign(String applyNumber) {
        Example example = new Example(PersonalMerProtocol.class);
        example.createCriteria().andEqualTo("applyNumber",applyNumber);
        PersonalMerProtocol personalMerProtocol = personalMerProtocolMapper.selectOneByExample(example);

        Integer esignFlowId = personalMerProtocol.getEsignFlowId();
        EsignFlow esignFlow = esignFlowMapper.selectByPrimaryKey(esignFlowId);
        esignFlowService.setTokenToRedis();
        CommonRequest request = SignHelper.downloadFlowDoc(esignFlow.getFlowId());
        if(0 != request.getCode()){
            throw new BusinessException("获取下载地址失败");
        }
        JSONObject data = request.getData();
        return data;
    }

    @Override
    public PersonalMerchant judgeAndGetAddInfo(Integer wechatInfoId) {
        String key = Constants.PERSON_MER_APPLY + wechatInfoId;
        String value = RedisUtil.get(key);
        log.info("缓存：{}",value);
        if(null == value){
            return null;
        }
        return JSON.parseObject(value,PersonalMerchant.class);
    }

    @Override
    public void addVerfyInfo(VerfyInfoDTO dto) {
        if(null == dto.getTaxSourceId()){
            throw new BusinessException("请选择税源地");
        }
        if(null == dto.getTaxTypeId()){
            throw new BusinessException("请选择税务类型");
        }
        String key = Constants.PERSON_MER_APPLY + dto.getWechatInfoId();
        String value = RedisUtil.get(key);
        PersonalMerchant personalMerchant = Optional.ofNullable(JSON.parseObject(value,PersonalMerchant.class))
                .orElse(new PersonalMerchant());
        personalMerchant.setWechatInfoId(dto.getWechatInfoId());
        personalMerchant.setTaxTypeId(dto.getTaxTypeId());
        TaxSounrceCompany taxSounrceCompany = taxSounrceCompanyMapper.selectByPrimaryKey(dto.getTaxSourceId());
        personalMerchant.setTaxSounrceCompanyId(dto.getTaxSourceId());
        personalMerchant.setTaxSounrceCompanyName(taxSounrceCompany.getTaxSounrceCompanyName());
        RedisUtil.put(key, JSON.toJSONString(personalMerchant), 3 * RedisUtil.TEN, TimeUnit.MINUTES);
    }

    @Override
    public void addRegisterInfo(RegisterInfoDTO dto) {
        String key = Constants.PERSON_MER_APPLY + dto.getWechatInfoId();
        if (!RedisUtil.exists(key)) {
            throw new BusinessException("缓存已失效，请刷新");
        }
        String value = RedisUtil.get(key);
        log.info("获取的缓存：{}",value);
        if (StringUtils.isEmpty(value)) {
            RedisUtil.remove(key);
            throw new BusinessException("缓存出错，请刷新");
        }
        PersonalMerchant personalMerchant = JSON.parseObject(value,PersonalMerchant.class);
        if(StringUtils.isEmpty(dto.getIdCardPictureFront())){
            throw new BusinessException("请上传身份证正面");
        }
        if(StringUtils.isEmpty(dto.getIdCardPictureBehind())){
            throw new BusinessException("请上传身份证反面");
        }
        if(StringUtils.isEmpty(dto.getIdCardPictureFrontWithPeople())){
            throw new BusinessException("请上传手持身份证");
        }
        if(StringUtils.isEmpty(dto.getApplyName())){
            throw new BusinessException("姓名不可为空");
        }
        if(StringUtils.isEmpty(dto.getBankNo())){
            throw new BusinessException("银行卡号不可为空");
        }
        if(StringUtils.isEmpty(dto.getIdCardNumber())){
            throw new BusinessException("身份证不可为空");
        }
        if(StringUtils.isEmpty(dto.getMobileNumber())){
            throw new BusinessException("手机号不可为空");
        }
        //四要素验证-start-----
        certificationInfo(dto.getApplyName(), dto.getMobileNumber(), dto.getIdCardNumber(),
                dto.getBankNo(), personalMerchant.getTaxSounrceCompanyId());
        //四要素验证-end-----
        //更新缓存
        BeanUtils.copyProperties(dto, personalMerchant);
        RedisUtil.put(key, JSON.toJSONString(personalMerchant), 3 * RedisUtil.TEN, TimeUnit.MINUTES);
    }

    @Override
    public String getBusinessLicenseName(Integer wechatInfoId,String region) {
        String key = Constants.PERSON_MER_APPLY + wechatInfoId;
        if (!RedisUtil.exists(key)) {
            throw new BusinessException("缓存已失效，请刷新");
        }
        String value = RedisUtil.get(key);
        log.info("获取的缓存：{}",value);
        if (StringUtils.isEmpty(value)) {
            RedisUtil.remove(key);
            throw new BusinessException("缓存出错，请刷新");
        }
        PersonalMerchant personalMerchant = JSON.parseObject(value,PersonalMerchant.class);
        Integer taxTypeId = personalMerchant.getTaxTypeId();
        TaxType taxType = taxTypeMapper.selectByPrimaryKey(taxTypeId);
        //获得tbl_personal_merchant表最大的id+1作为营业执照名字的递增数字
        Integer number = personalMerchantMapper.getPermerchantMaxId() + 1;
        //行业类型去掉中文括号后的描述
        String[] split = taxType.getTaxType().split("（", 2);
        //拼接营业执照名字
        String businessLicenseName = region + AddMerchantInfoConstants.WkxxSimpleName + taxType.getBusinessType() +
                number + "号" + split[0];
        PersonalMerchant rPersonalMerchant = new PersonalMerchant();
        rPersonalMerchant.setId(number);
        //查找营业执照名字中的id是否存在
        int count = personalMerchantMapper.selectCount(rPersonalMerchant);
        if (count > 0) {
            logger.info("营业执照名字中的数字{}已存在,请重新获取营业执照名称",number);
            throw new BusinessException("请重新获取营业执照名称");
        }
        return businessLicenseName;
    }

    @Override
    public String getBusinessLicenseNameById(Integer id,String region) {
        PersonalMerchant personalMerchant = personalMerchantMapper.selectByPrimaryKey(id);
        Integer taxTypeId = personalMerchant.getTaxTypeId();
        TaxType taxType = taxTypeMapper.selectByPrimaryKey(taxTypeId);
        //获得tbl_personal_merchant表id作为营业执照名字的递增数字
        Integer number = personalMerchant.getId();
        //行业类型去掉中文括号后的描述
        String[] split = taxType.getTaxType().split("（", 2);
        //拼接营业执照名字
        String businessLicenseName = region + AddMerchantInfoConstants.WkxxSimpleName + taxType.getBusinessType() +
                number + "号" + split[0];
        return businessLicenseName;
    }

    @Override
    public void addBusinessInfo(BusinessInfoDTO dto) {
        if(StringUtils.isEmpty(dto.getBusinessLicenseName())){
            throw new BusinessException("营业执照名不可为空");
        }
        if(StringUtils.isEmpty(dto.getPremises())){
            throw new BusinessException("经营场所不可为空");
        }
        PersonalMerchant judgeRepeat = new PersonalMerchant();
        judgeRepeat.setBusinessLicenseName(dto.getBusinessLicenseName());
        int count = personalMerchantMapper.selectCount(judgeRepeat);
        if (count > 0 || RedisUtil.exists(dto.getBusinessLicenseName())) {
            throw new BusinessException("该营业执照名称已被使用，请更换");
        }
        String key = Constants.PERSON_MER_APPLY + dto.getWechatInfoId();
        if (!RedisUtil.exists(key)) {
            throw new BusinessException("缓存已失效，请刷新");
        }
        String value = RedisUtil.get(key);
        if (StringUtils.isEmpty(value)) {
            RedisUtil.remove(key);
            throw new BusinessException("缓存出错，请刷新");
        }
        PersonalMerchant personalMerchant = JSON.parseObject(value,PersonalMerchant.class);
        //更新缓存
        BeanUtils.copyProperties(dto, personalMerchant);
        RedisUtil.put(key, JSON.toJSONString(personalMerchant), 3 * RedisUtil.TEN, TimeUnit.MINUTES);
        RedisUtil.put(dto.getBusinessLicenseName(), DateUtil.date().getTime(), 4 * RedisUtil.TEN, TimeUnit.MINUTES);
    }

    @Override
    public List<PersonalMerchantDTO> selectPersonalMerchantDTOList(ApplyForDTO applyForDTO) {
        List<PersonalMerchantDTO> personalMerchantDTOS = personalMerchantMapper.selectPersonalMerchantDTOList(applyForDTO);
        for (PersonalMerchantDTO personalMerchantDTO : personalMerchantDTOS) {
            personalMerchantDTO.setApplyName(DealInfoUtils.dealWithName(personalMerchantDTO.getApplyName()));
            personalMerchantDTO.setMobileNumber(DealInfoUtils.dealWithMobileNumber(personalMerchantDTO.getMobileNumber()));
            personalMerchantDTO.setIdCardNumber(DealInfoUtils.dealWithIdCardNumber(personalMerchantDTO.getIdCardNumber()));
        }
        return personalMerchantDTOS;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean batchDeleteInfo(List<Integer> applyIds) {
        if (StringUtils.isNull(applyIds)||applyIds.size() == 0) {
            throw new BusinessException("请选择需要删除的信息");
        }
        return personalMerchantMapper.batchDeleteInfo(applyIds) > 0;
    }

    @Override
    public PersonalMerchantDetailInfoDTO showPersonalMerchantInfo(Integer applyId) {
        if (StringUtils.isNull(applyId) || applyId <= 0) {
            log.info("applyId入参格式不正确：{}", applyId);
            throw new BusinessException("applyId入参格式不正确");
        }
        return personalMerchantMapper.showPersonalMerchantInfo(applyId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean updatePersonalMerchantInfo(PersonalMerchantDetailInfoDTO detailInfoDTO) {
        if (StringUtils.isNull(detailInfoDTO)) {
            log.info("PersonalMerchantDetailInfoDTO入参不能为空：{}", detailInfoDTO);
            throw new BusinessException("PersonalMerchantDetailInfoDTO入参不能为空");
        }
        //四要素认证
        certificationInfo(detailInfoDTO.getApplyName(), detailInfoDTO.getMobileNumber(),
                detailInfoDTO.getIdCardNumber(), detailInfoDTO.getBankNo(), detailInfoDTO.getTaxSounrceCompanyId());

        TaxType taxType = new TaxType();
        taxType.setDelStatus(DelStatusEnum.NORMAL.code);
        taxType.setTaxSounrceCompanyId(detailInfoDTO.getTaxSounrceCompanyId());
        taxType.setBusinessType(detailInfoDTO.getBusinessType());
        List<TaxType> rTaxType = taxTypeMapper.select(taxType);
        if (rTaxType.size() == 0) {
            throw new BusinessException("未找到税务类型");
        }

        PersonalMerchant personalMerchant = new PersonalMerchant();
        BeanUtils.copyProperties(detailInfoDTO, personalMerchant);
        personalMerchant.setId(detailInfoDTO.getApplyId());
        personalMerchant.setTaxTypeId(rTaxType.get(0).getId());
        int updatePersonalMerchant = personalMerchantMapper.updateByPrimaryKeySelective(personalMerchant);

        return updatePersonalMerchant > 0 ;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public boolean checkPersonalMerchantInfo(CheckPersonalMerchantInfo checkPersonalMerchantInfo) {
        if (StringUtils.isNotNull(checkPersonalMerchantInfo) &&
                checkPersonalMerchantInfo.getOrderStatus().compareTo(PerMerStatusEnum.OVERRULE.getCode()) == PerMerStatusEnum.PROCESS.getCode()
                && StringUtils.isEmpty(checkPersonalMerchantInfo.getRemark())) {
            throw new BusinessException("驳回理由不能为空，请填写后再重试");
        }
        int checkPersonalMerchant = personalMerchantMapper.checkPersonalMerchantInfo(checkPersonalMerchantInfo);

        if (checkPersonalMerchant<=0){
            throw new BusinessException("审核失败，请重试或联系管理员");
        }
        PerMerApplySchedule perMerApplySchedule = new PerMerApplySchedule();
        perMerApplySchedule.setPerMerId(checkPersonalMerchantInfo.getApplyId());
        perMerApplySchedule.setApplyNumber(checkPersonalMerchantInfo.getApplyNumber());
        perMerApplySchedule.setSubmitTime(DateUtil.now());
        perMerApplySchedule.setCreateTime(DateUtil.date());
        OrderStatusInfo statusInfo = new OrderStatusInfo();
        if(StringUtils.isEmpty(checkPersonalMerchantInfo.getRemark())){
            perMerApplySchedule.setAction(ApplyScheduleConstants.ACTION_THREE);
            perMerApplySchedule.setType(ApplyScheduleEnum.LICENSE.code);
            statusInfo.setOrderStatus(PerMerStatusEnum.SUCCESS.getCode());
        }
        if (StringUtils.isNotEmpty(checkPersonalMerchantInfo.getRemark())){
            perMerApplySchedule.setAction(ApplyScheduleConstants.ACTION_TWO);
            perMerApplySchedule.setType(ApplyScheduleEnum.CKECK.code);
            statusInfo.setOrderStatus(PerMerStatusEnum.OVERRULE.getCode());
        }
        int perMerApplyScheduleUpdate = perMerApplyScheduleMapper.insertSelective(perMerApplySchedule);

        Example example = new Example(OrderStatusInfo.class);
        example.createCriteria().andEqualTo("applyId",checkPersonalMerchantInfo.getApplyId());

        int orderStatusUpdate = orderStatusInfoMapper.updateByExampleSelective(statusInfo, example);

        return perMerApplyScheduleUpdate>0 && orderStatusUpdate>0;
    }

    @Override
    public List<ApplyLicenseInfoDTO> selectApplyBusinessLicenseInfo(ApplyForLicenseQueryDTO
                                                                            applyForLicenseQueryDTO) {
        List<ApplyLicenseInfoDTO> licenseInfoDTOS = personalMerchantMapper.selectApplyBusinessLicenseInfo(applyForLicenseQueryDTO);
        for (ApplyLicenseInfoDTO licenseDTO : licenseInfoDTOS) {
            licenseDTO.setApplyName(DealInfoUtils.dealWithName(licenseDTO.getApplyName()));
            licenseDTO.setMobileNumber(DealInfoUtils.dealWithMobileNumber(licenseDTO.getMobileNumber()));
            licenseDTO.setIdCardNumber(DealInfoUtils.dealWithIdCardNumber(licenseDTO.getIdCardNumber()));
        }
        return licenseInfoDTOS;
    }
    @Override
    public List<ApplyLicenseInfoDTO> exportApplyBusinessLicenseInfo(ApplyForLicenseQueryDTO
                                                                            applyForLicenseQueryDTO) {
        List<ApplyLicenseInfoDTO> licenseInfoDTOS = personalMerchantMapper.selectApplyBusinessLicenseInfo(applyForLicenseQueryDTO);
        return licenseInfoDTOS;
    }

    @Override
    public ApplyLicenseDetailInfoDTO showApplyLicenseDetailInfo(Integer applyId) {
        if (StringUtils.isNull(applyId) || applyId <= 0) {
            log.info("applyId入参格式不正确：{}", applyId);
            throw new BusinessException("applyId入参格式不正确");
        }
        ApplyLicenseDetailInfoDTO applyLicenseDetailInfoDTO = personalMerchantMapper.showApplyLicenseDetailInfo(applyId);

        Example example = new Example(TaxType.class);
        example.createCriteria().andEqualTo("taxSounrceCompanyName",applyLicenseDetailInfoDTO.getTaxSounrceCompanyName());
        List<TaxType> taxTypes = taxTypeMapper.selectByExample(example);
        List<String> businessTypeList = taxTypes.stream().map(t ->
               t.getBusinessType()
        ).collect(Collectors.toList());
        applyLicenseDetailInfoDTO.setBusinessTypeList(businessTypeList);

        return applyLicenseDetailInfoDTO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public boolean updateApplyLicenseInfo(ApplyLicenseDetailInfoDTO detailInfoDTO) {
        if(StringUtils.isNull(detailInfoDTO)){
            log.error("ApplyLicenseDetailInfoDTO入参为空");
            throw new BusinessException("ApplyLicenseDetailInfoDTO入参为空");
        }
        certificationInfo(detailInfoDTO.getApplyName(),detailInfoDTO.getMobileNumber(),
                detailInfoDTO.getIdCardNumber(),detailInfoDTO.getBankNo(),detailInfoDTO.getTaxSounrceCompanyId());

        PersonalMerchant personalMerchant = new PersonalMerchant();
        BeanUtils.copyProperties(detailInfoDTO,personalMerchant);
        personalMerchant.setId(detailInfoDTO.getApplyId());

        int updateOne = personalMerchantMapper.updateByPrimaryKeySelective(personalMerchant);
        OrderStatusInfo orderStatusInfo = new OrderStatusInfo();
        orderStatusInfo.setOrderStatus(PerMerStatusEnum.COMPLETE.getCode());

        Example example = new Example(OrderStatusInfo.class);
        example.createCriteria().andEqualTo("applyId",detailInfoDTO.getApplyId());
        int orderStatusUpdate = orderStatusInfoMapper.updateByExampleSelective(orderStatusInfo, example);

        TaxType taxType = new TaxType();
        BeanUtils.copyProperties(detailInfoDTO,taxType);
        taxType.setId(detailInfoDTO.getTaxTypeId());
        int updateTwo = taxTypeMapper.updateByPrimaryKeySelective(taxType);

        return updateOne > 0 && updateTwo > 0 && orderStatusUpdate>0;
    }

    @Override
    public List<PersonalLicenseDTO> selectPersonalLicenseInfo(PersonalLicenseQueryDTO personalLicenseQueryDTO) {
        List<PersonalLicenseDTO> personalLicenseDTOS = personalMerchantMapper.selectPersonalLicenseInfo(personalLicenseQueryDTO);
        for (PersonalLicenseDTO personalLicenseDTO:personalLicenseDTOS) {
            personalLicenseDTO.setApplyName(DealInfoUtils.dealWithName(personalLicenseDTO.getApplyName()));
            personalLicenseDTO.setMobileNumber(DealInfoUtils.dealWithMobileNumber(personalLicenseDTO.getMobileNumber()));
            personalLicenseDTO.setIdCardNumber(DealInfoUtils.dealWithIdCardNumber(personalLicenseDTO.getIdCardNumber()));
            personalLicenseDTO.setBusinessLicenseType("个体工商户");
            personalLicenseDTO.setOrganization("个人经营");
        }
        return personalLicenseDTOS;
    }


    @Override
    public List<PersonalLicenseDTO> exportPersonalLicenseInfo(PersonalLicenseQueryDTO personalLicenseQueryDTO) {
        List<PersonalLicenseDTO> personalLicenseDTOS = personalMerchantMapper.selectPersonalLicenseInfo(personalLicenseQueryDTO);
        for (PersonalLicenseDTO personalLicenseDTO:personalLicenseDTOS) {
            personalLicenseDTO.setBusinessLicenseType("个体工商户");
            personalLicenseDTO.setOrganization("个人经营");
        }
        return personalLicenseDTOS;
    }

    @Override
    public List<TaxTypeInfoDTO> selectTaxTypeInfo(TaxTypeQueryDTO taxTypeQueryDTO) {
        return personalMerchantMapper.selectTaxTypeInfo(taxTypeQueryDTO);
    }

    @Override
    public TaxTypeInfoDTO selectTaxTypeInfoById(Integer id) {
        TaxTypeInfoDTO taxTypeInfoDTO = personalMerchantMapper.selectTaxTypeInfoById(id);
        return taxTypeInfoDTO;
    }

    @Override
    public void uploadLicense(Integer applyId, String url, String time ) {
        String undefined = "undefined";
        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(time) || undefined.equals(url)) {
            throw new BusinessException("请将信息填写完整");
        }
        PersonalMerchant personalMerchant = new PersonalMerchant();
        personalMerchant.setId(applyId);
        PersonalMerchant merchant = personalMerchant = personalMerchantMapper.selectByPrimaryKey(personalMerchant);
        //将文件路径及注册时间更新进数据库
        merchant.setBusinessLicense(url);
        merchant.setBusinessLicenseRegisterTime(time);
        personalMerchantMapper.updateByPrimaryKey(merchant);
        //更新状态
        OrderStatusInfo orderStatusInfo = new OrderStatusInfo();
        orderStatusInfo.setApplyId(applyId);
        orderStatusInfo.setOrderStatus(PerMerStatusEnum.SUCCESS.getCode());
        List<OrderStatusInfo> rOrderStatusInfo = orderStatusInfoMapper.select(orderStatusInfo);
        rOrderStatusInfo.get(0).setUpdateTime(new Date());
        rOrderStatusInfo.get(0).setOrderStatus(PerMerStatusEnum.COMPLETE.getCode());
        orderStatusInfoMapper.updateByPrimaryKeySelective(rOrderStatusInfo.get(0));
        //更新流程
        perMerApplyScheduleService.insertInfo(applyId, PerMerApplySchEnum.BUSINESS_LICENSE_SUCCESS,2);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public boolean batchDeleteTaxTypeInfo(List<Integer> taxTypeIds) {
        if(StringUtils.isNull(taxTypeIds)||taxTypeIds.size()<=0){
            log.info("请选择需要删除的税务类型信息");
            throw new BusinessException("请选择需要删除的税务类型信息");
        }
        return personalMerchantMapper.batchDeleteTaxTypeInfo(taxTypeIds)>0;
    }

    @Override
    public boolean addTaxTypeInfo(TaxType taxType) {
        taxTypeCheck(taxType);
        //查询是否有重复税务类型
        TaxType rTaxType = new TaxType();
        rTaxType.setTaxSounrceCompanyId(taxType.getTaxSounrceCompanyId());
        rTaxType.setBusinessType(taxType.getBusinessType());
        rTaxType.setDelStatus(DelStatusEnum.NORMAL.code);
        List<TaxType> rTaxTypes = taxTypeMapper.select(rTaxType);
        if (rTaxTypes.size() > 0) {
            throw new BusinessException("税务类型不可重复");
        }
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        long taxTypeNumber = snowflake.nextId();
        taxType.setTaxTypeNumber(String.valueOf(taxTypeNumber));
        return taxTypeMapper.insertSelective(taxType)>0;
    }

    private void taxTypeCheck(TaxType taxType) {
        if (StringUtils.isNull(taxType)){
            log.info("请填写数据之后再提交: {}",taxType);
            throw new BusinessException("请填写数据之后再提交");
        }
        if(StringUtils.isEmpty(taxType.getInvoiceContent())){
            log.info("请填写开票科目之后再提交: {}",taxType);
            throw new BusinessException("请填写开票科目之后再提交");
        }
        if(StringUtils.isEmpty(taxType.getBusinessScope())){
            log.info("请填写经营范围之后再提交: {}",taxType);
            throw new BusinessException("请填写经营范围之后再提交");
        }
    }

    @Override
    public boolean updateTaxTypeInfo(TaxType taxType) {
        taxTypeCheck(taxType);
        return taxTypeMapper.updateByPrimaryKeySelective(taxType)>0;
    }

    @Override
    public List<TaxType> getTaxTypeInfoByTaxId(Integer taxId, Integer typId) {
        TaxType taxType = new TaxType();
        taxType.setTaxSounrceCompanyId(taxId);
        taxType.setDelStatus(DelStatusEnum.NORMAL.code);
        taxType.setId(typId);
        List<TaxType> taxTypes = taxTypeMapper.select(taxType);
        return taxTypes;
    }

    private void certificationInfo(String applyName, String mobileNumber, String idCardNumber, String
            bankNo, Integer taxSounrceCompanyId) {
        //四要素验证-start-----
        //表示信息由工商户这边上传的
        Integer merchantId = -1;
        //获取员工(未注册的新建注册后获取)
        CustomerInfo customerInfo;
        try {
            //二要素校验
            customerInfo = customerInfoService.queryCustomerInfo(applyName, mobileNumber,
                    idCardNumber, merchantId, null, taxSounrceCompanyId);
        } catch (BusinessException | PaymentException e) {
            log.info("灵工账号注册失败，{}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("灵工账号注册系统错误：" + e.getMessage());
            throw new BusinessException("姓名身份证验证出错,请联系开发人员");
        }
        log.info("customerInfo:{}", JSON.toJSONString(customerInfo));
        if (null == customerInfo) {
            throw new BusinessException("姓名身份证验证失败");
        }
        //获取员工信息成功，开始获取银行卡或支付宝信息
        String bankId;
        try {
            //获取银行卡信息
            CustomerBankcardInfo customerBankcardInfo = customerBankcardInfoService.queryBankcardInfo(
                    applyName, mobileNumber, bankNo, customerInfo.getId());
            bankId = customerBankcardInfo.getBankId();
        } catch (BusinessException | PaymentException e) {
            log.info(e.getMessage());
            throw new BusinessException("银行卡号或预留手机号错误");
        } catch (Exception e) {
            log.error("获取支付渠道系统错误：" + e.getMessage());
            throw new BusinessException("姓名身份证银行卡手机号验证出错,请联系开发人员");
        }
        if (null == bankId) {
            throw new BusinessException("银行卡号或预留手机号错误");
        }
        //四要素验证-end-----
    }
}
