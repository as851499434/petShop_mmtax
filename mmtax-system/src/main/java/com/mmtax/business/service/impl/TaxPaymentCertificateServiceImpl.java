package com.mmtax.business.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.mmtax.business.domain.TaxPaymentCertificate;
import com.mmtax.business.dto.ManagerAddTaxPaymentCertificateDTO;
import com.mmtax.business.dto.ManagerTaxMerchantDTO;
import com.mmtax.business.dto.ManagerTaxPaymentCertificateDTO;
import com.mmtax.business.dto.MerchantTaxPaymentCertificateDTO;
import com.mmtax.business.mapper.MerchantInfoMapper;
import com.mmtax.business.mapper.TaxPaymentCertificateMapper;
import com.mmtax.business.service.ITaxPaymentCertificateService;
import com.mmtax.common.annotation.DataScope;
import com.mmtax.common.enums.TaxPaymentCertificateStatusEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.system.domain.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 完税证明 服务层实现
 *
 * @author meimiao
 * @date 2019-08-13
 */
@Service
public class TaxPaymentCertificateServiceImpl extends CommonServiceImpl implements ITaxPaymentCertificateService {

    @Resource
    private TaxPaymentCertificateMapper taxPaymentCertificateMapper;
    @Resource
    private MerchantInfoMapper merchantInfoMapper;

    @Override
    public Page getListMerchantTaxPaymentCertificate(Integer merchantId, Integer currentPage, Integer pageSize,
                                                     String startDate, String endDate) {
        if (StringUtils.isNotEmpty(startDate) && StringUtils.isEmpty(endDate)) {
            throw new BusinessException("所属期止不能为空");
        }

        if (StringUtils.isNotEmpty(endDate) && StringUtils.isEmpty(startDate)) {
            throw new BusinessException("所属期始不能为空");
        }
        if (StringUtils.isNotEmpty(startDate) && StringUtils.isNotEmpty(endDate)) {
            startDate = DateUtil.format(DateUtil.beginOfMonth(DateUtil.parse(startDate)),
                    DatePattern.NORM_DATETIME_PATTERN);
            endDate = DateUtil.format(DateUtil.endOfMonth(DateUtil.parse(endDate)),
                    DatePattern.NORM_DATETIME_PATTERN);
        }
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<MerchantTaxPaymentCertificateDTO> list =
                taxPaymentCertificateMapper.getListMerchantTaxPaymentCertificate(merchantId, queryPage.getStartIndex(),
                        pageSize, startDate, endDate);
        int count = taxPaymentCertificateMapper.getCountMerchantTaxPaymentCertificate(merchantId, startDate, endDate);
        return new Page(count, list, currentPage, pageSize);
    }

    @Override
    public void changeStatus(MerchantTaxPaymentCertificateDTO dto) {
        TaxPaymentCertificate taxPaymentCertificate = new TaxPaymentCertificate();
        taxPaymentCertificate.setId(dto.getId());
        taxPaymentCertificate.setStatus(dto.getStatus());
        taxPaymentCertificateMapper.updateByPrimaryKeySelective(taxPaymentCertificate);
    }

    @Override
    public String checkStatus(MerchantTaxPaymentCertificateDTO dto) {
        TaxPaymentCertificate taxPaymentCertificate = taxPaymentCertificateMapper.selectByPrimaryKey(dto.getId());
        if (taxPaymentCertificate.getStatus().equals(TaxPaymentCertificateStatusEnum.DELETE.code)) {
            throw new BusinessException("该文件已删除，无法下载");
        }
        //更新下载时间
        taxPaymentCertificate.setDownloadTime(DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
        taxPaymentCertificateMapper.updateByPrimaryKeySelective(taxPaymentCertificate);
        return taxPaymentCertificate.getFileName();
    }

    @Override
    @DataScope(tableAlias = "t3")
    public List<ManagerTaxPaymentCertificateDTO> getListManagerTaxPaymentCertificate(ManagerTaxPaymentCertificateDTO dto) {
        if (StringUtils.isNotEmpty(dto.getStartDate()) && StringUtils.isEmpty(dto.getEndDate())) {
            throw new BusinessException("结束时间不能为空");
        }

        if (StringUtils.isNotEmpty(dto.getEndDate()) && StringUtils.isEmpty(dto.getStartDate())) {
            throw new BusinessException("起始时间不能为空");
        }
        return taxPaymentCertificateMapper.getListManagerTaxPaymentCertificate(dto);
    }

    @Override
    public void addManagerTaxPaymentCertificateDTO(ManagerAddTaxPaymentCertificateDTO dto) {
        String startDate = DateUtil.format(DateUtil.beginOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);
        String endDate = DateUtil.format(DateUtil.endOfMonth(DateUtil.date()), DatePattern.NORM_DATETIME_PATTERN);

        int count = taxPaymentCertificateMapper.checkRecordByMonth(dto.getMerchantId(), startDate, endDate);
        if (count > 0) {
            throw new BusinessException("该商户本月已存在完税证明");
        }
        TaxPaymentCertificate taxPaymentCertificate = new TaxPaymentCertificate();
        taxPaymentCertificate.setStatus(TaxPaymentCertificateStatusEnum.NOT_DOWNLOAD.code);
        taxPaymentCertificate.setCreateTime(DateUtil.date());
        taxPaymentCertificate.setUpdateTime(DateUtil.date());
        taxPaymentCertificate.setMerchantId(dto.getMerchantId());
        taxPaymentCertificate.setFileName(dto.getFileName());
        taxPaymentCertificate.setName(dto.getName());
        taxPaymentCertificateMapper.insertSelective(taxPaymentCertificate);
    }

    @Override
    public Page getMerchantInfo(Integer currentPage, Integer pageSize, String merchantName, String merchantCode,
                                SysUser sysUser) {
        String saleId = null;
//        if (!sysUser.isAdmin()) {
//            saleId = sysUser.getUserId().toString();
//        }
        Integer startIndex = getStartIndex(currentPage, pageSize);
        List<ManagerTaxMerchantDTO> list = merchantInfoMapper.getListManagerTaxMerchant(startIndex, pageSize,
                merchantName, merchantCode, saleId);
        int count = merchantInfoMapper.getCountManagerTaxMerchant(merchantName, merchantCode, saleId);
        return new Page(count, list, currentPage, pageSize);
    }

    @Override
    public void update(ManagerTaxPaymentCertificateDTO dto) {
        TaxPaymentCertificate taxPaymentCertificate = new TaxPaymentCertificate();
        if (StringUtils.isNotEmpty(dto.getFileName())) {
            taxPaymentCertificate.setFileName(dto.getFileName());
        }

        if (StringUtils.isNotEmpty(dto.getFileName())) {
            taxPaymentCertificate.setFileName(dto.getFileName());
        }

        taxPaymentCertificate.setId(dto.getId());

        taxPaymentCertificateMapper.updateByPrimaryKeySelective(taxPaymentCertificate);

    }

    @Override
    public void delete(ManagerTaxPaymentCertificateDTO dto) {
        TaxPaymentCertificate taxPaymentCertificate = new TaxPaymentCertificate();
        taxPaymentCertificate.setId(dto.getId());
        taxPaymentCertificate.setStatus(Integer.valueOf(dto.getStatus()));
        taxPaymentCertificateMapper.updateByPrimaryKeySelective(taxPaymentCertificate);
    }
}
