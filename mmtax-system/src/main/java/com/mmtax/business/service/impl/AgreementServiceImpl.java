package com.mmtax.business.service.impl;

import com.mmtax.business.domain.*;
import com.mmtax.business.dto.ManagerAgreementInfoDTO;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.IAgreementService;
import com.mmtax.common.enums.TaxSourceInfoEnum;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Ljd
 * @date 2020/8/16
 */
@Service
public class AgreementServiceImpl implements IAgreementService {

    @Resource
    private EsignFlowMapper esignFlowMapper;

    @Resource
    private OnlinePaymentInfoMapper onlinePaymentInfoMapper;

    @Resource
    private TrxOrderMapper trxOrderMapper;
    @Autowired
    private MerchantInfoMapper merchantInfoMapper;
    @Autowired
    SysDictDataMapper sysDictDataMapper;
    @Autowired
    MerchantTaskInfoMapper merchantTaskInfoMapper;
    @Autowired
    TaskInfoMapper taskInfoMapper;
    @Autowired
    MerCusPostMapper merCusPostMapper;

    @Override
    public ManagerAgreementInfoDTO getManagerContractInfoDTO(String idNumber, Integer merchantId) {
        OnlinePaymentInfo paymentInfo = onlinePaymentInfoMapper.selectByMerchantId(merchantId);
        ManagerAgreementInfoDTO infoDTO = trxOrderMapper.getManagerAgreementInfoDTO(idNumber, merchantId);
        infoDTO.setTaxSourceId(paymentInfo.getTaxSourceCompanyId());
        if (TaxSourceInfoEnum.HAN_NAN_JUN_JU.getId().equals(paymentInfo.getTaxSourceCompanyId())) {
            infoDTO.setTaxSourceName(TaxSourceInfoEnum.HAN_NAN_JUN_JU.getDescription());
        }else if (TaxSourceInfoEnum.JIANG_XI_QI_BO.getId().equals(paymentInfo.getTaxSourceCompanyId())) {
            infoDTO.setTaxSourceName(TaxSourceInfoEnum.JIANG_XI_QI_BO.getDescription());
        }
        MerchantInfo merchantInfo = merchantInfoMapper.selectByPrimaryKey(merchantId);
        String industry = sysDictDataMapper.selectDictLabel("industry_code",merchantInfo.getIndustry());
        infoDTO.setIndustryName(StringUtils.isEmpty(industry)?"":industry);
        List<String> postNames = Arrays.asList("装卸搬运服务", "音视频服务", "家政服务（非员工制）", "家居产品维保", "技术咨询"
                , "咨询服务", "教育信息咨询","专业设计服务", "文印晒图服务", "人才委托招聘", "市场推广", "房屋销售代理服务", "渠道搭建及使用"
                , "运输代理服务", "国内货物运输代理服务", "国际货物运输代理服务", "港澳台货物运输代理服务", "其他货物运输代理服务"
                , "代理报关服务", "婚姻介绍服务", "人力资源外包服务", "代理进口免税货物货款","软件开发服务", "软件维护服务"
                , "软件测试服务", "电路设计服务", "电路测试服务", "信息系统服务", "信息系统增值服务", "业务流程管理服务"
                , "其他企业管理服务", "其他人力资源服务", "其他经纪代理服务");
        Random random = new Random();

        MerCusPost merCusPost = new MerCusPost();
        merCusPost.setMerchantId(merchantId);
        merCusPost.setCertificateNo(idNumber);
        merCusPost = merCusPostMapper.selectOne(merCusPost);
        if(null != merCusPost){
            infoDTO.setTaskNameItemOneItemTwo(merCusPost.getPostName(),merCusPost.getItemOne(),merCusPost.getItemTwo());
            return infoDTO;
        }
        infoDTO.setTaskNameItemOneItemTwo(postNames.get(random.nextInt(postNames.size()-1)),null
                ,null);
        MerCusPost newMercusPost = new MerCusPost();
        newMercusPost.setMerchantId(merchantId);
        newMercusPost.setCertificateNo(idNumber);
        newMercusPost.setPostName(infoDTO.getTaskName());
        newMercusPost.setItemOne(infoDTO.getItemOne());
        newMercusPost.setItemTwo(infoDTO.getItemtwo());
        merCusPostMapper.insertSelective(newMercusPost);
        return infoDTO;
    }
}
