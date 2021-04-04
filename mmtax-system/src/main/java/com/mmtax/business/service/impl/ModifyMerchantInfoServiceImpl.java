package com.mmtax.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.IModifyMerchantInfoService;
import com.mmtax.business.service.IOnlineBankService;
import com.mmtax.business.util.MerchantInfoUtil;
import com.mmtax.business.yunzbdto.YunZBNewMerchantInfoDTO;
import com.mmtax.common.constant.UserBaseConstants;
import com.mmtax.common.constant.WkycConstants;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.MmtaxSign;
import com.mmtax.common.utils.PasswordUtil;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.http.HttpUtils;
import com.mmtax.common.utils.yunzbutil.Response;
import com.mmtax.common.utils.yunzbutil.YunZBConstants;
import com.mmtax.common.utils.yunzbutil.YunZBUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 商户信息修改 实现类
 *
 * @author yuanligang
 * @date 2019/7/10
 */
@Service
public class ModifyMerchantInfoServiceImpl extends CommonServiceImpl implements IModifyMerchantInfoService {
    @Resource
    AddressMapper addressMapper;
    @Resource
    MerchantInfoMapper merchantInfoMapper;
    @Resource
    RiskManagementConfigMapper riskManagementConfigMapper;
    @Resource
    MerchantAccountConfigMapper merchantAccountConfigMapper;
    @Resource
    InvoiceInfoMapper invoiceInfoMapper;
    @Resource
    CooperationMapper cooperationMapper;
    @Resource
    CustomerSupportMapper customerSupportMapper;
    @Resource
    private SettlementInfoMapper settlementInfoMapper;
    @Autowired
    private IOnlineBankService onlineBankService;
    @Autowired
    private CustomerEsignInfoMapper customerEsignInfoMapper;
    @Autowired
    private CustomerProtocolMapper customerProtocolMapper;
    @Autowired
    private MerchantTaskInfoMapper merchantTaskInfoMapper;

    /**
     * 发票送货地址修改
     * modify 7/15 考虑到在发票生成时地址不可变 故不直接更新
     *
     * @param address 地址信息实体类
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAddressInfo(Address address) {
        int merchantId = addressMapper.selectByPrimaryKey(address.getId()).getMerchantId();
        Address oldAddress = addressMapper.getAddressByMerchantId(merchantId);
        oldAddress.setStatus(1);
        oldAddress.setId(oldAddress.getId());
        oldAddress.setUpdateTime(DateUtil.date());
        addressMapper.updateByPrimaryKeySelective(oldAddress);

        Address newAddress = new Address();
        newAddress.setAddress(address.getAddress());
        newAddress.setAddresseeName(address.getAddresseeName());
        newAddress.setMobile(address.getMobile());
        newAddress.setStatus(0);
        newAddress.setMerchantId(address.getMerchantId());
        newAddress.setCreateTime(DateUtil.date());
        newAddress.setMerchantId(merchantId);
        return addressMapper.insert(newAddress);
    }

    /**
     * 商户密码修改
     *
     * @param dto
     * @return
     */
    @Override
    public int updateMerchantPassword(UpdatePasswordDTO dto) {

        MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfoById(dto.getMerchantId());
        if (merchantInfo == null) {
            throw new BusinessException("商户信息不存在!");
        }
        if (StringUtils.isEmpty(dto.getOldPassword())) {
            throw new BusinessException("原密码不能为空!");
        }
        if (StringUtils.isEmpty(dto.getNewPassword())) {
            throw new BusinessException("新密码不能为空!");
        }

        String oldPassword = PasswordUtil.password(dto.getOldPassword(), merchantInfo.getMerchantCode(),
                merchantInfo.getSalt());
        if (!merchantInfo.getPassword().equals(oldPassword)) {
            throw new BusinessException("原始密码不一致");
        }

        merchantInfo.setSalt(PasswordUtil.randomSalt());

        merchantInfo.setPassword(PasswordUtil.password(dto.getNewPassword(), merchantInfo.getMerchantCode(),
                merchantInfo.getSalt()));
        return merchantInfoMapper.updateMerchantPassword(merchantInfo);

    }

    /**
     * 商户密码批量重置
     *
     * @param ids 商户id集
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int resetMerchantPassword(List<Integer> ids) {
        MerchantInfo merchantInfo = new MerchantInfo();
        for (Integer id : ids) {
            merchantInfo = merchantInfoMapper.getMerchantInfoById(id);
            if (merchantInfo == null) {
                throw new BusinessException("商户信息不存在!");
            }
            merchantInfo.setSalt(PasswordUtil.randomSalt());
            merchantInfo.setPassword(PasswordUtil.password(UserBaseConstants.initPassword, merchantInfo.getMerchantCode(),
                    merchantInfo.getSalt()));
            return merchantInfoMapper.updateMerchantPassword(merchantInfo);
        }
        return 0;
    }

    /**
     * 商户基本信息修改
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "merchantInfo", key = "#dto.id")
    public int updateMerchantInfo(ModifyMerchantDTO dto) throws Exception {
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(dto.getId());
        String merchantName = merchantInfo.getMerchantName();
        String businessLicenseCode = merchantInfo.getBusinessLicenseCode();
        BeanUtil.copyProperties(dto, merchantInfo);
        //将商户信息上传到云众包进行处理
        AddMerchantInfoDTO addMerchantInfoDTO = new AddMerchantInfoDTO();
        addMerchantInfoDTO.setMerchantInfo(merchantInfo);
        SettlementInfo settlementInfo = settlementInfoMapper.getSettleByMerchantId(dto.getId());
        addMerchantInfoDTO.setSettlementInfo(settlementInfo);
        InvoiceInfo invoiceInfo = invoiceInfoMapper.getInvoiceInfoByMerchantId(dto.getId());
        addMerchantInfoDTO.setInvoiceInfo(invoiceInfo);
        PaymentMerchantInfo paymentMerchantInfo = paymentMerchantInfoMapper.selectByMerchantIdAndChannel(dto.getId(),
                PaymentChannelEnum.YUNZB.name());
        if (paymentMerchantInfo != null) {
            TaxSounrceCompany taxSounrceCompany =
                    taxSounrceCompanyMapper.selectByPrimaryKey(paymentMerchantInfo.getTaxSourceId());
            YunZBNewMerchantInfoDTO yunZBMerchantInfoDTO = convertYunZBMerchantInfoDTO(addMerchantInfoDTO);
            yunZBMerchantInfoDTO.setMchId(taxSounrceCompany.getMerchantNo());
            yunZBMerchantInfoDTO.setSubMchId(paymentMerchantInfo.getMerchantNo());
            Response response = YunZBUtil.remoteInvoke(YunZBConstants.OPEN_ACC,
                    yunZBMerchantInfoDTO, taxSounrceCompany.getSecretKey());
            JSONObject tokenResult = JSONObject.parseObject(response.getRespData());
            //判断是否添加成功
            if (YunZBConstants.RESULT_CODE.equals(tokenResult.get(YunZBConstants.REQUEST_CODE))) {
                //上传图片
                tokenResult = uploadYunZBImg(addMerchantInfoDTO, paymentMerchantInfo, taxSounrceCompany);
                if (YunZBConstants.RESULT_CODE.equals(tokenResult.get(YunZBConstants.RETURN_CODE))) {
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    throw new BusinessException(tokenResult.get(YunZBConstants.RETURN_MSG).toString());
                }
                if (StringUtils.isNotEmpty(tokenResult.get(YunZBConstants.REQUEST_CODE).toString()) &&
                        YunZBConstants.RESULT_CODE.equals(tokenResult.get(YunZBConstants.REQUEST_CODE))) {
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    throw new BusinessException(tokenResult.get(YunZBConstants.REQUEST_MSG).toString());
                }
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new BusinessException(tokenResult.get(YunZBConstants.RETURN_MSG).toString());
            }
        }
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantInfo.getId());
        if(null != onlinePaymentInfo && !merchantName.equals(dto.getMerchantName())
                && !businessLicenseCode.equals(dto.getBusinessLicenseCode())){
            onlineBankService.modifyAccount(merchantInfo.getId(),merchantInfo,onlinePaymentInfo.getTaxSourceCompanyId());
            //修改之前协议为已过期
            CustomerEsignInfo customerEsignInfo = new CustomerEsignInfo();
            customerEsignInfo.setMerchantId(merchantInfo.getId());
            List<CustomerEsignInfo> customerEsignInfos = customerEsignInfoMapper.select(customerEsignInfo);
            customerEsignInfos.forEach(one->{
                CustomerProtocol customerProtocol = customerProtocolMapper.selectByAccountId(
                        one.getAccountId(),merchantInfo.getId());
                if(null != customerProtocol){
                    customerProtocol.setExpireStatus(ExpireStatusEnum.EXPIRE.getCode());
                    customerProtocol.setUpdateTime(DateUtil.date());
                    customerProtocolMapper.updateByPrimaryKeySelective(customerProtocol);
                }
            });
        }
        if (StringUtils.isNotEmpty(dto.getLoginName()) && !merchantInfo.getEmail().equals(dto.getLoginName())) {
            int i = merchantInfoMapper.checkEmail(dto.getLoginName());
            if (i > 0) {
                throw new BusinessException("该邮箱已被注册");
            }
            merchantInfo.setEmail(dto.getLoginName());
        }
        return merchantInfoMapper.updateByPrimaryKeySelective(merchantInfo);

    }

    /**
     * 修改风险配置信息
     *
     * @param updateRiskDTO
     * @return
     */
    @Override
    public int updateRiskConfig(UpdateRiskDTO updateRiskDTO) {
        RiskManagementConfig risk = new RiskManagementConfig();
        BeanUtils.copyProperties(updateRiskDTO, risk);
        return riskManagementConfigMapper.updateByPrimaryKeySelective(risk);
    }

    /**
     * 修改账户设置信息
     */
    @Override
    public int updateAccountConfig(UpdateAccountConfigDTO updateAccountConfigDTO) {
        MerchantAccountConfig config = new MerchantAccountConfig();
        List<String> balanceRemindEmailList = updateAccountConfigDTO.getBalanceRemindEmailList();
        List<String> rechargeRemindEmailList = updateAccountConfigDTO.getRechargeRemindEmailList();
        BeanUtils.copyProperties(updateAccountConfigDTO, config);
        StringBuilder balanceRemindEmail = new StringBuilder();
        StringBuilder rechargeRemindEmail = new StringBuilder();
        if (CollectionUtils.isEmpty(balanceRemindEmailList) || CollectionUtils.isEmpty(rechargeRemindEmailList)) {
            throw new BusinessException("邮箱字段不能为空");
        }

        balanceRemindEmailList.forEach(email -> balanceRemindEmail.append(email + ","));
        rechargeRemindEmailList.forEach(email -> rechargeRemindEmail.append(email + ","));
        config.setBalanceRemindEmail(balanceRemindEmail.toString());
        config.setRechargeRemindEmail(rechargeRemindEmail.toString());
        config.setUpdateTime(DateUtil.date());
        return merchantAccountConfigMapper.updateByPrimaryKeySelective(config);
    }

    /**
     * 账户信息邮箱修改
     */
    @Override
    public int updateAccountEmail(UpdateAccountEmailDTO dto) {
        MerchantAccountConfig config = new MerchantAccountConfig();
        List<String> balanceRemindEmailList = dto.getBalanceRemindEmail();
        List<String> rechargeRemindEmailList = dto.getRechargeRemindEmail();
        StringBuilder balanceRemindEmail = new StringBuilder();
        StringBuilder rechargeRemindEmailL = new StringBuilder();
        if (CollectionUtils.isEmpty(balanceRemindEmailList) || CollectionUtils.isEmpty(rechargeRemindEmailList)) {
            throw new BusinessException("邮箱字段不能为空");
        }
        balanceRemindEmailList.forEach(email -> balanceRemindEmail.append(email + ","));
        rechargeRemindEmailList.forEach(email -> rechargeRemindEmailL.append(email + ","));
        config.setBalanceRemindEmail(balanceRemindEmail.toString());
        config.setId(dto.getId());
        config.setRechargeRemindEmail(rechargeRemindEmailL.toString());
        return merchantAccountConfigMapper.updateByPrimaryKeySelective(config);
    }

    /**
     * 后台发票地址修改
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateInvoiceInfo(SysInvoiceInfoDTO sysInvoiceInfoDTO) throws Exception {
        StringBuffer invoiceContent = new StringBuffer();
        InvoiceInfoDTO invoiceInfo = sysInvoiceInfoDTO.getInvoiceInfo();
        List<String> invoiceContentList = invoiceInfo.getInvoiceContentList();
        invoiceContentList.forEach(context -> invoiceContent.append(context).append(","));
        InvoiceInfo info = new InvoiceInfo();
        BeanUtils.copyProperties(invoiceInfo, info);
        info.setInvoiceContent(invoiceContent.toString());
        invoiceInfoMapper.updateByPrimaryKeySelective(info);
        //将商户信息上传到云众包进行处理
        AddMerchantInfoDTO addMerchantInfoDTO = new AddMerchantInfoDTO();
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(sysInvoiceInfoDTO.
                getInvoiceInfo().getMerchantId());
        addMerchantInfoDTO.setMerchantInfo(merchantInfo);
        SettlementInfo settlementInfo = settlementInfoMapper.getSettleByMerchantId(merchantInfo.getId());
        addMerchantInfoDTO.setSettlementInfo(settlementInfo);
        addMerchantInfoDTO.setInvoiceInfo(info);
        PaymentMerchantInfo paymentMerchantInfo = paymentMerchantInfoMapper.selectByMerchantIdAndChannel(merchantInfo.getId(),
                PaymentChannelEnum.YUNZB.name());

        if (paymentMerchantInfo != null) {
            TaxSounrceCompany taxSounrceCompany =
                    taxSounrceCompanyMapper.selectByPrimaryKey(paymentMerchantInfo.getTaxSourceId());
            YunZBNewMerchantInfoDTO yunZBMerchantInfoDTO = convertYunZBMerchantInfoDTO(addMerchantInfoDTO);
            yunZBMerchantInfoDTO.setMchId(taxSounrceCompany.getMerchantNo());
            yunZBMerchantInfoDTO.setSubMchId(paymentMerchantInfo.getMerchantNo());
            Response response = YunZBUtil.remoteInvoke(YunZBConstants.OPEN_ACC,
                    yunZBMerchantInfoDTO, taxSounrceCompany.getSecretKey());
            JSONObject tokenResult = JSONObject.parseObject(response.getRespData());
            //判断是否添加成功
            if (YunZBConstants.RESULT_CODE.equals(tokenResult.get(YunZBConstants.REQUEST_CODE))) {

            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new BusinessException(tokenResult.get(YunZBConstants.REQUEST_MSG).toString());
            }
            if (YunZBConstants.RESULT_CODE.equals(tokenResult.get(YunZBConstants.RETURN_CODE))) {

            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new BusinessException(tokenResult.get(YunZBConstants.RETURN_MSG).toString());
            }
        }

    }

    /**
     * 合作信息详细修改
     *
     * @param managerCooperationDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCooperationDetail(ManagerCooperationDTO managerCooperationDTO) {
        Cooperation cooperation = new Cooperation();
        BeanUtils.copyProperties(managerCooperationDTO, cooperation);
        MerchantInfoUtil.checkCooperation(cooperation);
        if(BigRateSwitchEnum.BIDRATEOPEN.getStatus().equals(cooperation.getBigRateSwitch())) {
            if(null == cooperation.getSingleServiceBigRate()){
                throw new BusinessException("大额费率不可为空");
            }
            if (cooperation.getSingleServiceBigRate().compareTo(cooperation.getSingleServiceRate()) < 0) {
                throw new BusinessException("大额费率需大于普通费率");
            }
            //若本月没有超过大额打款,修改大额费率 不能低于本月的普通费率
            LocalDate start = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
            LocalDate end = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
            BigDecimal sumMonthAmount = cooperationMapper.sumMonthAmount(managerCooperationDTO.getMerchantId()
                    ,start.toString(),end.toString());
            if(sumMonthAmount.compareTo(new BigDecimal(100000))<=0){
                BigDecimal maxMonthTaxRate = cooperationMapper.maxMonthTaxRate(managerCooperationDTO.getMerchantId()
                        , start.toString(), end.toString());
                if(cooperation.getSingleServiceBigRate().compareTo(maxMonthTaxRate) < 0){
                    throw new BusinessException("大额费率需大于等于本月普通费率的历史最高值");
                }
            }
        }

        if(SigningTypeEnum.WECHAT.getValue().equals(managerCooperationDTO.getSigningType())){
            cooperation.setCustomerWithdrawSwitch(SwitchEnum.ON.getCode());
        }else {
            cooperation.setCustomerWithdrawSwitch(SwitchEnum.OFF.getCode());
        }
        cooperation.setId(managerCooperationDTO.getCooperationId());
        if (cooperation.getSendOrderSwitch() == null) {
            throw new BusinessException("请选择是否派单商户");
        }
        MerchantInfo merchantInfo = new MerchantInfo();
        //更新合同编号
        merchantInfo.setId(managerCooperationDTO.getMerchantId());
        merchantInfo.setContractNo(managerCooperationDTO.getContractNo());
        merchantInfoMapper.updateByPrimaryKeySelective(merchantInfo);
        //岗位名称修改
        if(SigningTypeEnum.PAPER.getValue().equals(managerCooperationDTO.getSigningType())){
            //获取原来的岗位名称
            MerchantTaskInfo merchantTaskInfo = new MerchantTaskInfo();
            merchantTaskInfo.setDelStatus(DelStatusEnum.NORMAL.code);
            merchantTaskInfo.setMerchantId(managerCooperationDTO.getMerchantId());
            List<MerchantTaskInfo> merchantTaskInfos = merchantTaskInfoMapper.select(merchantTaskInfo);
            if (StringUtils.isNotEmpty(merchantTaskInfos)){
                for (MerchantTaskInfo taskInfo:merchantTaskInfos) {
                    //删除所有岗位
                    taskInfo.setDelStatus(DelStatusEnum.DELETE.code);
                    merchantTaskInfoMapper.updateByPrimaryKeySelective(taskInfo);
                }
            }
        }else{
            //如果派单开关是开则跳过更新
            if (!managerCooperationDTO.getSendOrderSwitch().equals(SwitchEnum.ON.getCode())) {
                //变化后的岗位
                List<MerchantTaskInfo> newPostInfos = managerCooperationDTO.getPostInfos();
                List<MerchantTaskInfo> collect =
                        newPostInfos.stream().filter(s -> s.getTaskId() != null).collect(Collectors.toList());
                if(collect.size() != newPostInfos.size() && cooperation.getSendOrderSwitch().equals(SwitchEnum.OFF.getCode())){
                    throw new BusinessException("请选择签约岗位");
                }
                //原有岗位
                MerchantTaskInfo merchantTaskInfo = new MerchantTaskInfo();
                merchantTaskInfo.setMerchantId(managerCooperationDTO.getMerchantId());
                merchantTaskInfo.setDelStatus(DelStatusEnum.NORMAL.code);
                //查询数据库中原有的商户任务信息
                List<MerchantTaskInfo> oldPostInfos = merchantTaskInfoMapper.select(merchantTaskInfo);
                //获取变化后的岗位的Id集合
                List<Integer> newPostInfoIds = new ArrayList<>();
                for (MerchantTaskInfo newPostInfo:newPostInfos) {
                    newPostInfo.setMerchantId(managerCooperationDTO.getMerchantId());
                    newPostInfoIds.add(newPostInfo.getTaskId());
                }
                //获取原来的岗位的Id集合
                List<Integer> oldPostInfoIds = new ArrayList<>();
                for (MerchantTaskInfo oldPostInfo:oldPostInfos) {
                    oldPostInfoIds.add(oldPostInfo.getTaskId());
                }
                //岗位名不能重复
                Stream<Integer> stream = newPostInfoIds.stream().distinct();
                List<Integer> infoIds = stream.collect(Collectors.toList());
                if(infoIds.size()<newPostInfoIds.size()){
                    throw new BusinessException("岗位名不能重复");
                }
                //增加岗位
                for(MerchantTaskInfo newPostInfo:newPostInfos){
                    if(!oldPostInfoIds.contains(newPostInfo.getTaskId())){
                        merchantTaskInfoMapper.insertSelective(newPostInfo);
                    }
                }
                //删除岗位
                for(MerchantTaskInfo oldPostInfo:oldPostInfos){
                    if(!newPostInfoIds.contains(oldPostInfo.getTaskId())){
                        oldPostInfo.setDelStatus(DelStatusEnum.DELETE.code);
                        merchantTaskInfoMapper.updateByPrimaryKeySelective(oldPostInfo);
                    }
                }
            }
        }

        //派单商户,任务信息修改
        if(managerCooperationDTO.getSendOrderSwitch().equals(SwitchEnum.ON.getCode())) {
            //修改任务信息
            //获取新的商户任务信息
            List<MerchantTaskInfo> newTaskInfos = managerCooperationDTO.getMerchantTaskInfos();
            List<MerchantTaskInfo> rCollect =
                    newTaskInfos.stream().filter(s -> s.getTaskId() != null).collect(Collectors.toList());
            if(rCollect.size() != newTaskInfos.size()){
                throw new BusinessException("请选择任务信息");
            }
            //任务条数限制
            if (newTaskInfos.size() > 20) {
                throw new BusinessException("任务条数不能超过20条，请检查您的任务总数");
            }
            //删除null 的元素
            newTaskInfos.removeAll(Collections.singleton(null));
            //获取新的商户任务Id集合
            List<Integer> newTaskIds = new ArrayList<>();
            for (MerchantTaskInfo newTaskInfo : newTaskInfos) {
                if (StringUtils.isNull(newTaskInfo.getTaskId())) {
                    throw new BusinessException("商户任务名格式不对，请重新选择商户任务名");
                }
                if (newTaskInfo.getTaskInfo().trim().length() > 200) {
                    throw new BusinessException("任务简介字数不能超过200，请重新编辑任务简介");
                }
                newTaskIds.add(newTaskInfo.getTaskId());
            }
            //去除重复的任务
            Stream<Integer> distinct = newTaskIds.stream().distinct();
            List<Integer> collect = distinct.collect(Collectors.toList());
            if (collect.size() < newTaskIds.size()) {
                throw new BusinessException("商户任务名不能重复");
            }
            Integer merchantId = managerCooperationDTO.getMerchantId();
            MerchantTaskInfo merchantTaskInfo = new MerchantTaskInfo();
            merchantTaskInfo.setMerchantId(merchantId);
            merchantTaskInfo.setDelStatus(DelStatusEnum.NORMAL.code);
            //查询数据库中原有的商户任务信息
            List<MerchantTaskInfo> oldTaskInfos = merchantTaskInfoMapper.select(merchantTaskInfo);
            //获取原有商户id的所有任务id集合
            List<Integer> oldTaskIds = new ArrayList<>();
            for (MerchantTaskInfo oldTaskInfo : oldTaskInfos) {
                oldTaskIds.add(oldTaskInfo.getTaskId());
            }
            //新任务Id包含原有任务Id
            for (MerchantTaskInfo oldTaskInfo : oldTaskInfos) {
                //删除条件
                Example example = new Example(MerchantTaskInfo.class);
                example.createCriteria().andEqualTo("merchantId", oldTaskInfo.getMerchantId())
                        .andEqualTo("taskId", oldTaskInfo.getTaskId())
                        .andEqualTo("delStatus", DelStatusEnum.NORMAL.code);
                //删除任务
                if (!newTaskIds.contains(oldTaskInfo.getTaskId())) {
                    //删除原有商户任务信息
                    oldTaskInfo.setDelStatus(DelStatusEnum.DELETE.code);
                    merchantTaskInfoMapper.updateByExampleSelective(oldTaskInfo, example);
                } else {
                    for (MerchantTaskInfo newTaskinfo : newTaskInfos) {
                        if (newTaskinfo.getTaskId().equals(oldTaskInfo.getTaskId()) &&
                                !newTaskinfo.getTaskInfo().equals(oldTaskInfo.getTaskInfo())) {
                            //更新任务简介
                            merchantTaskInfoMapper.updateByExampleSelective(newTaskinfo, example);
                        }
                    }
                }
            }
            //原有任务Id包含新任务Id
            for (MerchantTaskInfo newTaskInfo : newTaskInfos) {
                //添加新商户任务记录
                if (!oldTaskIds.contains(newTaskInfo.getTaskId())) {
                    if (StringUtils.isEmpty(newTaskInfo.getTaskName())) {
                        throw new BusinessException("商户任务名称不能为空");
                    }
                    newTaskInfo.setMerchantId(merchantId);
                    merchantTaskInfoMapper.insertSelective(newTaskInfo);
                }
            }
        }

        //判断费率是否被修改
        Cooperation cooperation1 = cooperationMapper.selectByPrimaryKey(cooperation.getId());
        int singleServiceRate = cooperation.getSingleServiceRate().compareTo(cooperation1.getSingleServiceRate());
        //判断商户是否有归属代理商
        Integer merchantId = cooperation.getMerchantId();
        MerchantInfo merchantInfo1 = merchantInfoMapper.selectByPrimaryKey(merchantId);
        String agentNumber = merchantInfo1.getAgentNumber();
        if(StringUtils.isNotEmpty(agentNumber)) {
            //大额费率开关打开
            if (!StringUtils.isNull(cooperation.getSingleServiceBigRate())) {
                int singleServiceBigRate = cooperation.getSingleServiceBigRate().compareTo(cooperation1.getSingleServiceBigRate());
                //费率是否被变更
                if (!(CheckAndChangeRateEnum.NOCHANGE.getState() == singleServiceRate)
                        || !(CheckAndChangeRateEnum.NOCHANGE.getState() == singleServiceBigRate)
                ) {
                    throw new BusinessException("该商户有归属代理商，修改信息失败");
                }
            }
            //大额费率开关关闭
            //费率是否被变更
            if (!(CheckAndChangeRateEnum.NOCHANGE.getState() == singleServiceRate)) {
                throw new BusinessException("该商户有归属代理商，修改信息失败");
            }
        }

        return cooperationMapper.updateByPrimaryKeySelective(cooperation);
    }

    /**
     * 风控信息修改
     *
     * @param managerRiskConfigDTO
     * @return
     */
    @Override
    public int updateRiskDetail(ManagerRiskConfigDTO managerRiskConfigDTO) {
        RiskManagementConfig riskManagementConfig = riskManagementConfigMapper.selectByPrimaryKey(
                managerRiskConfigDTO.getRiskId());
        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setId(riskManagementConfig.getMerchantId());
        merchantInfo.setContractNo(managerRiskConfigDTO.getContractNo());
        merchantInfo.setUpdateTime(DateUtil.date());
        merchantInfoMapper.updateByPrimaryKeySelective(merchantInfo);
        RiskManagementConfig risk = new RiskManagementConfig();
        BeanUtils.copyProperties(managerRiskConfigDTO, risk);
        risk.setId(managerRiskConfigDTO.getRiskId());
        return riskManagementConfigMapper.updateByPrimaryKeySelective(risk);
    }

    @Override
    public void updateMerchantStatus(MerchantStatusDTO merchantStatusDTO) {
        List<Integer> merchantIds = merchantStatusDTO.getMerchantId();
        String status = merchantStatusDTO.getStatus();
        merchantIds.forEach(id -> {
            merchantInfoMapper.updateMerchantStatus(id, status);
        });
    }

    @Override
    public void updateCustomerSupport(ManagerCustomerSupportDTO dto) {
        CustomerSupport customerSupport = new CustomerSupport();
        BeanUtil.copyProperties(dto, customerSupport);
        customerSupport.setUpdateTime(DateUtil.date());
        customerSupportMapper.updateByPrimaryKeySelective(customerSupport);
    }

    @Override
    public void updateSettleMentInfo(ManagerSettleMentInfoDTO dto) throws Exception {
        SettlementInfo settlementInfo = new SettlementInfo();
        BeanUtil.copyProperties(dto, settlementInfo);
        settlementInfo.setUpdateTime(DateUtil.date());
        //将商户信息上传到云众包进行处理
        AddMerchantInfoDTO addMerchantInfoDTO = new AddMerchantInfoDTO();
        SettlementInfo rSettlementInfo = settlementInfoMapper.getSettleByMerchantId(dto.getId());
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(rSettlementInfo.getMerchantId());
        addMerchantInfoDTO.setMerchantInfo(merchantInfo);
        addMerchantInfoDTO.setSettlementInfo(settlementInfo);
        InvoiceInfo invoiceInfo = invoiceInfoMapper.getInvoiceInfoByMerchantId(dto.getId());
        addMerchantInfoDTO.setInvoiceInfo(invoiceInfo);
        PaymentMerchantInfo paymentMerchantInfo = paymentMerchantInfoMapper.selectByMerchantIdAndChannel(dto.getId(),
                PaymentChannelEnum.YUNZB.name());

        if (paymentMerchantInfo != null) {
            TaxSounrceCompany taxSounrceCompany =
                    taxSounrceCompanyMapper.selectByPrimaryKey(paymentMerchantInfo.getTaxSourceId());
            YunZBNewMerchantInfoDTO yunZBMerchantInfoDTO = convertYunZBMerchantInfoDTO(addMerchantInfoDTO);
            yunZBMerchantInfoDTO.setMchId(taxSounrceCompany.getMerchantNo());
            yunZBMerchantInfoDTO.setSubMchId(paymentMerchantInfo.getMerchantNo());
            Response response = YunZBUtil.remoteInvoke(YunZBConstants.OPEN_ACC,
                    yunZBMerchantInfoDTO, taxSounrceCompany.getSecretKey());
            JSONObject tokenResult = JSONObject.parseObject(response.getRespData());
            //判断是否添加成功
            if (YunZBConstants.RESULT_CODE.equals(tokenResult.get(YunZBConstants.REQUEST_CODE))) {

            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new BusinessException(tokenResult.get(YunZBConstants.REQUEST_MSG).toString());
            }
            if (YunZBConstants.RESULT_CODE.equals(tokenResult.get(YunZBConstants.RETURN_CODE))) {

            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new BusinessException(tokenResult.get(YunZBConstants.RETURN_MSG).toString());
            }
        }
        settlementInfo.setId(rSettlementInfo.getId());
        settlementInfoMapper.updateByPrimaryKeySelective(settlementInfo);
    }
}
