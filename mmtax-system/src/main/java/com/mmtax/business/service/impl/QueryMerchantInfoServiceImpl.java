package com.mmtax.business.service.impl;

import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.ICustomerSupportService;
import com.mmtax.business.service.IMerchantAccountService;
import com.mmtax.business.service.IMerchantInfoService;
import com.mmtax.business.service.IMerchantSettleService;
import com.mmtax.business.service.IQueryMerchantInfoService;
import com.mmtax.business.yunzbdto.YunZBInvoiceContentResultDTO;
import com.mmtax.common.annotation.DataScope;
import com.mmtax.common.enums.DelStatusEnum;
import com.mmtax.common.enums.InvoiceChannelEnum;
import com.mmtax.common.enums.PaymentChannelEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.yunzbutil.Response;
import com.mmtax.common.utils.yunzbutil.YunZBConstants;
import com.mmtax.system.domain.SysUser;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 商户信息查询相关 包含合作、风控、对接、账户设置 服务层实现
 *
 * @author yuanligang
 * @date 2019/7/11
 */
@Service
public class QueryMerchantInfoServiceImpl extends CommonServiceImpl implements IQueryMerchantInfoService {

    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    @Resource
    private InvoiceInfoMapper invoiceInfoMapper;

    @Resource
    private AddressMapper addressMapper;

    @Resource
    private MerchantAccountConfigMapper merchantAccountConfigMapper;

    @Resource
    private CooperationMapper cooperationMapper;

    @Resource
    private MerchantAccountMapper merchantAccountMapper;

    @Resource
    private SettlementInfoMapper settlementInfoMapper;

    @Resource
    private CustomerSupportMapper customerSupportMapper;
    @Resource
    private SubjectInfoMapper subjectInfoMapper;
    @Resource
    private TianJinPaymentInfoMapper tianJinPaymentInfoMapper;
    @Resource
    private OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Resource
    private OnlineAccountConfigMapper onlineAccountConfigMapper;

    @Autowired
    private IMerchantInfoService merchantInfoService;

    @Autowired
    private IMerchantAccountService merchantAccountService;

    @Autowired
    private IMerchantSettleService merchantSettleService;


    @Autowired
    private ICustomerSupportService customerSupportService;

    @Autowired
    private MerchantTaskInfoMapper merchantTaskInfoMapper;

    /**
     * 商户列表查询
     */
    @Override
    public List<ManagerMerchantInfoDTO> listMerchantInfos(ManagerMerchantQueryDTO managerMerchantQueryDTO,
                                                          SysUser sysUser) {
        List<ManagerMerchantInfoDTO> list = merchantInfoService.listMerchantInfos(managerMerchantQueryDTO);
        for (ManagerMerchantInfoDTO one:list) {
            PaymentMerchantInfo paymentMerchantInfo = paymentMerchantInfoMapper.selectByMerchantIdAndChannel(
                    one.getId(), PaymentChannelEnum.YUNZB.name());
            TianJinPaymentInfo tianJinPaymentInfo = tianJinPaymentInfoMapper.getTianJinPaymentInfoByMerchantId(
                    one.getId());
            OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(one.getId());
            Integer taxSourceId = null;
            if(null != paymentMerchantInfo){
                taxSourceId = paymentMerchantInfo.getTaxSourceId();
            }else if (null != tianJinPaymentInfo){
                taxSourceId = tianJinPaymentInfo.getTaxSourceCompanyId();
            }else if(null != onlinePaymentInfo){
                taxSourceId = onlinePaymentInfo.getTaxSourceCompanyId();
            }
            TaxSounrceCompany taxSounrceCompany = new TaxSounrceCompany();
            taxSounrceCompany.setId(Optional.ofNullable(taxSourceId).orElse(0));
            taxSounrceCompany = Optional.ofNullable(taxSounrceCompanyMapper.selectOne(taxSounrceCompany))
                    .orElseGet(()->{
                        TaxSounrceCompany anotherOne = new TaxSounrceCompany();
                        anotherOne.setTaxSounrceCompanyName("无对应税源地公司");
                        return anotherOne;
                    });
            one.setTaxSounrceCompanyName(taxSounrceCompany.getTaxSounrceCompanyName());
        }
        return list;

    }

    /**
     * 依据商户ID获取商户基本信息
     *
     * @param merchantId
     * @return 商户信息详情
     */
    @Override
    @Cacheable(value = "merchantInfo", key = "#merchantId")
    public MerchantInfoDTO getMerchantInfoById(int merchantId) {
        MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfoById(merchantId);
        MerchantInfoDTO merchantInfoDTO = new MerchantInfoDTO();
        BeanUtils.copyProperties(merchantInfo, merchantInfoDTO);
        SettlementInfo settlementInfo = settlementInfoMapper.getSettleByMerchantId(merchantId);
        CustomerSupport customerSupport = customerSupportMapper.getCoustomerSupportBymerchantId(merchantId);
        merchantInfoDTO.setSettlementInfo(settlementInfo);
        merchantInfoDTO.setCustomerSupport(customerSupport);
        if (StringUtils.isNotEmpty(merchantInfo.getInvoiceEmail())) {
            String[] emails = merchantInfo.getInvoiceEmail().split(",");
            merchantInfoDTO.setEmails(Arrays.asList(emails));
        }
        if (StringUtils.isNotEmpty(merchantInfo.getContractImgUrl())) {
            String[] img = merchantInfo.getContractImgUrl().split(",");
            merchantInfoDTO.setContractImgUrlList(Arrays.asList(img));
        }
        if (merchantInfo == null) {
            throw new BusinessException("商户信息不存在！");
        }
        return merchantInfoDTO;
    }


    /**
     * 依据商户ID获取商户基开票信息
     *
     * @param merchantId
     * @return
     */
    @Override
    public InvoiceInfoDTO getInvoiceInfoByMerchantId(int merchantId) throws Exception {
        InvoiceInfo invoiceInfo = invoiceInfoMapper.getInvoiceInfoByMerchantId(merchantId);
        if (invoiceInfo == null) {
            throw new BusinessException("开票信息不存在");
        }
        InvoiceInfoDTO dto = new InvoiceInfoDTO();
        BeanUtils.copyProperties(invoiceInfo, dto);
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
        if (onlinePaymentInfo != null) {
            return dto;
        }
        Response response = queryInvoiceContent(merchantId);
        JSONObject tokenResult = JSONObject.fromObject(response.getRespData());
        if (YunZBConstants.RESULT_CODE.equals(tokenResult.get(YunZBConstants.RETURN_CODE)) &&
                StringUtils.isNotEmpty(tokenResult.get(YunZBConstants.REQUEST_CODE).toString()) &&
                YunZBConstants.RESULT_CODE.equals(tokenResult.get(YunZBConstants.REQUEST_CODE))) {
            List<YunZBInvoiceContentResultDTO> list =
                    tokenResult.getJSONArray(YunZBConstants.CONTENTS);
            if (CollectionUtils.isEmpty(list)) {
                throw new BusinessException("请联系管理员设置发票内容");
            }

            invoiceInfo.setInvoiceContent(list.toString());
            if (StringUtils.isEmpty(invoiceInfo.getInvoiceContent())) {
                throw new BusinessException("服务费模式不存在");
            }
            String[] context = invoiceInfo.getInvoiceContent().split(",");
            dto.setInvoiceContentList(Arrays.asList(context));
            if (invoiceInfo == null) {
                throw new BusinessException("未定义开票信息");
            }
        }
        return dto;
    }

    @Override
    public SysInvoiceInfoDTO getSysInvoiceInfoByMerchantId(int merchantId) throws Exception {
        InvoiceInfoDTO invoiceInfoDTO = getInvoiceInfoByMerchantId(merchantId);
        SysInvoiceInfoDTO sysInvoiceInfoDTO = new SysInvoiceInfoDTO();
        sysInvoiceInfoDTO.setInvoiceInfo(invoiceInfoDTO);
        return sysInvoiceInfoDTO;
    }

    /**
     * 修改发票默认内容
     * dto 发票默认内容DTO
     *
     * @return
     */
    @Override
    public int updateInvoiceDefaultContent(UpdateInvoiceInfoDTO dto) {
        InvoiceInfo invoiceInfo = invoiceInfoMapper.selectByPrimaryKey(dto.getId());
        if (invoiceInfo == null) {
            throw new BusinessException("发票信息不存在");
        }
        invoiceInfo.setInvoiceDefaultContent(dto.getDefaultContent());
        return invoiceInfoMapper.updateByPrimaryKeySelective(invoiceInfo);
    }

    /**
     * 获取商户个人收获地址
     *
     * @param merchantId 商户ID
     * @return
     */
    @Override
    public Address getAddressInfoByMerchantId(int merchantId) {
        Address address = addressMapper.getAddressByMerchantId(merchantId);
        if (address == null) {
            throw new BusinessException("收货地址不存在");
        }
        return address;

    }

    /**
     * 商户个人账户设置信息获取
     */
    @Override
    public MerchantAccountDTO getMerchantAccountConfig(int merchantId) {
        MerchantAccountConfig merchantAccountConfig = merchantAccountConfigMapper.getMerchantConfigByMerchantId(merchantId);
        MerchantAccountDTO merchantAccountDTO = new MerchantAccountDTO();
        BeanUtils.copyProperties(merchantAccountConfig, merchantAccountDTO);
        String[] balance;
        String[] recharge;
        if (!StringUtils.isEmpty(merchantAccountConfig.getBalanceRemindEmail())) {
            balance = merchantAccountConfig.getBalanceRemindEmail().split(",");
            merchantAccountDTO.setBalanceRemindEmailList(Arrays.asList(balance));
        }
        if (!StringUtils.isEmpty(merchantAccountConfig.getRechargeRemindEmail())) {
            recharge = merchantAccountConfig.getRechargeRemindEmail().split(",");
            merchantAccountDTO.setRechargeRemindEmailList(Arrays.asList(recharge));
        }
        if (merchantAccountConfig == null) {
            throw new BusinessException("账户设置不存在");
        }

        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(merchantId);
        cooperation = cooperationMapper.selectOne(cooperation);
        merchantAccountDTO.setMoneyModel(cooperation.getMoneyModel());

        return merchantAccountDTO;
    }

    /**
     * 获取商户合作信息详情
     *
     * @param merchantId 商户ID
     * @return
     */
    @Override
    public CooperationInfoDetailDTO getCooperationInfoDetail(int merchantId) {
        MerchantInfo info = merchantInfoMapper.getMerchantInfoById(merchantId);
        if (info == null) {
            throw new BusinessException("商户信息不存在");
        }
        CooperationInfoDetailDTO cooperationInfoDTO = cooperationMapper.getCooperationInfoByMerchantId(merchantId);
        if (cooperationInfoDTO == null) {
            throw new BusinessException("商户信息不能为空");
        }
        MerchantTaskInfo merchantTaskInfo = new MerchantTaskInfo();
        merchantTaskInfo.setMerchantId(merchantId);
        merchantTaskInfo.setDelStatus(DelStatusEnum.NORMAL.code);
        List<MerchantTaskInfo> merchantTaskInfos = merchantTaskInfoMapper.select(merchantTaskInfo);
        cooperationInfoDTO.setMerchantTaskInfo(merchantTaskInfos);
        cooperationInfoDTO.setMerchantName(info.getMerchantName());
        cooperationInfoDTO.setContractNo(info.getContractNo());
        return cooperationInfoDTO;
    }

    @Override
    public CooperationInfoDTO getCooperationInfo(int merchantId) {
        CooperationInfoDTO cooperationInfoDTO = new CooperationInfoDTO();
        MerchantInfo merchantInfo = merchantInfoMapper.getMerchantInfoById(merchantId);
        if (merchantInfo == null) {
            throw new BusinessException("商户信息不存在");
        }
        String merchantName = merchantInfo.getMerchantName();
        MerchantAccount merchantAccount = merchantAccountMapper.getMerchantAccountByMerchantId(merchantId);
        BigDecimal amount = merchantAccount.getUsableAmount();
        cooperationInfoDTO.setMerchantName(merchantName);
        cooperationInfoDTO.setBalance(amount);
        cooperationInfoDTO.setFrozenAmount(merchantAccount.getFrozenAmount());
        Cooperation cooperation = new Cooperation();
        cooperation.setMerchantId(merchantId);
        cooperation = cooperationMapper.selectOne(cooperation);
        cooperationInfoDTO.setBankChannel(cooperation.getBankChannel());
        cooperationInfoDTO.setAlipayChannel(cooperation.getAlipayChannel());
        cooperationInfoDTO.setWechatChannel(cooperation.getWechatChannel());
        return cooperationInfoDTO;

    }

    @Override
    public List<CooperationInfoDTO> getCooperationInfoList(ManagerMerchantQueryDTO managerMerchantQueryDTO
            , SysUser sysUser) {
        List<CooperationInfoDTO> list = merchantAccountService.getSysCooperationList(managerMerchantQueryDTO);
        for (CooperationInfoDTO one:list) {
            PaymentMerchantInfo paymentMerchantInfo = paymentMerchantInfoMapper.selectByMerchantIdAndChannel(
                    one.getId(), PaymentChannelEnum.YUNZB.name());
            TianJinPaymentInfo tianJinPaymentInfo = tianJinPaymentInfoMapper.getTianJinPaymentInfoByMerchantId(
                    one.getId());
            OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(one.getId());
            Integer taxSourceId = null;
            if(null != paymentMerchantInfo){
                taxSourceId = paymentMerchantInfo.getTaxSourceId();
            }else if (null != tianJinPaymentInfo){
                taxSourceId = tianJinPaymentInfo.getTaxSourceCompanyId();
            }else if(null != onlinePaymentInfo){
                taxSourceId = onlinePaymentInfo.getTaxSourceCompanyId();
                OnlineAccountConfig onlineAccountConfig = new OnlineAccountConfig();
                onlineAccountConfig.setTaxSounrceId(onlinePaymentInfo.getTaxSourceCompanyId());
                onlineAccountConfig = onlineAccountConfigMapper.selectOne(onlineAccountConfig);
                one.setOnlineBankAccount(onlineAccountConfig.getAccountPrefix() + onlinePaymentInfo.getSubAccountNo());
            }
            TaxSounrceCompany taxSounrceCompany = new TaxSounrceCompany();
            taxSounrceCompany.setId(Optional.ofNullable(taxSourceId).orElse(0));
            taxSounrceCompany = Optional.ofNullable(taxSounrceCompanyMapper.selectOne(taxSounrceCompany))
                    .orElseGet(()->{
                        TaxSounrceCompany anotherOne = new TaxSounrceCompany();
                        anotherOne.setTaxSounrceCompanyName("无对应税源地公司");
                        return anotherOne;
                    });
            one.setTaxSounrceCompanyName(taxSounrceCompany.getTaxSounrceCompanyName());
        }
        return list;

    }

    @Override
    public List<FuzzyMatchingDTO> getMerchantNameFuzzyMatching(String merchantName) {
        List<FuzzyMatchingDTO> list = merchantInfoMapper.getMerchantNameFuzzyMatching(merchantName);
        return list;

    }

    @Override
    public void checkMerchantName(String merchantName) {
        int count = merchantInfoMapper.checkMerchantName(merchantName);
        if (count > 0) {
            throw new BusinessException("该商户名称已存在");
        }
    }

    @Override
    public void checkEmail(String email) {
        int count = merchantInfoMapper.checkEmail(email);
        if (count > 0) {
            throw new BusinessException("该邮箱已被使用");
        }
    }

    @Override
    public void checkContactsMobile(String contactsMobile) {
        int count = merchantInfoMapper.checkContactsMobile(contactsMobile);
        if (count > 0) {
            throw new BusinessException("该联系人手机号已被使用");
        }
    }

    @Override
    public void checkCompanyName(String companyName) {
        int count = merchantInfoMapper.checkCompanyName(companyName);
        if (count > 0) {
            throw new BusinessException("该企业已注册");
        }
    }

    @Override
    public void checkBusinessLicenseCode(String businessLicenseCode) {
        int count = merchantInfoMapper.checkBusinessLicenseCode(businessLicenseCode);
        if (count > 0) {
            throw new BusinessException("该营业执照编码已被使用");
        }
    }

    @Override
    public void checkAccountName(String accountName) {
        int count = settlementInfoMapper.checkAccountName(accountName);
        if (count > 0) {
            throw new BusinessException("该开户名称已被使用");
        }
    }

    @Override
    public void checkAccountNo(String accountNo) {
        // 应运营要求，产品同意，此处对公账号不可重复校验拿掉
//        int count = settlementInfoMapper.checkAccountNo(accountNo);
//        if (count > 0) {
//            throw new BusinessException("该对公账户已被使用");
//        }
    }

    @Override
    public List<ManagerCustomerSupportDTO> customerSupportList(ManagerCustomerSupportDTO dto, SysUser sysUser) {
        return customerSupportService.getListManagerCustomerSupport(dto);
    }

    @Override
    public List<ManagerSettleMentInfoDTO> settleMentInfoList(ManagerSettleMentInfoDTO dto, SysUser sysUser) {
        return merchantSettleService.getListManagersettleMentInfo(dto);
    }

    @Override
    public List<ManagerTaxSourceCompanyDTO> getTaxSourceCompany() {
        List<ManagerTaxSourceCompanyDTO> list = taxSounrceCompanyMapper.getTaxSourceCompany();
        return list;
    }

    @Override
    public List<SubjectInfo> getSubjectInfo() {
        return subjectInfoMapper.selectAll();
    }
}
