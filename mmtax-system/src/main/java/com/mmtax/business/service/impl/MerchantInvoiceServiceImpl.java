package com.mmtax.business.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.*;
import com.mmtax.business.service.IMerchantInvoiceService;
import com.mmtax.business.yunzbdto.*;
import com.mmtax.common.chanpay.ChanPayUtil;
import com.mmtax.common.constant.RegularExpressionConstants;
import com.mmtax.common.constant.ShiroConstants;
import com.mmtax.common.constant.UserBaseConstants;
import com.mmtax.common.enums.*;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.MerchantIdUtil;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.yunzbutil.*;
import com.mmtax.system.domain.SysUser;
import net.sf.json.JSONObject;
import org.jsoup.helper.DataUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.unit.DataUnit;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商户申请发票 服务层实现
 *
 * @author yuanligang
 * @date 2019/7/10
 */
@Service

public class MerchantInvoiceServiceImpl extends CommonServiceImpl implements IMerchantInvoiceService {
    @Resource
    RechargeRecordMapper rechargeRecordMapper;
    @Resource
    InvoiceApplyMapper invoiceApplyMapper;
    @Resource
    InvoiceInfoMapper invoiceInfoMapper;
    @Resource
    AddressMapper addressMapper;
    @Resource
    InvoiceApplyDetailMapper invoiceApplyDetailMapper;
    @Resource
    MerchantInfoMapper merchantInfoMapper;
    @Resource
    InvoiceFailMapper invoiceFailMapper;
    @Resource
    InvoiceApplyAmountMapper invoiceApplyAmountMapper;
    @Resource
    private TianJinPaymentInfoMapper tianJinPaymentInfoMapper;
    @Resource
    MerchantInvoiceSubjectCorrelationMapper merchantInvoiceSubjectCorrelationMapper;
    @Resource
    InvoiceSubjectMapper invoiceSubjectMapper;
    @Resource
    InvoiceRecordMapper invoiceRecordMapper;
    @Resource
    InvoiceRechargeCorrelationMapper invoiceRechargeCorrelationMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInvoiceDefaultContent(Integer merchantId, Integer invoiceDefaultContent) {


        //更新其他发票内容为非默认发票内容
        merchantInvoiceSubjectCorrelationMapper.updateInvoiceIsDefaultAll(merchantId,DefaultStatusEnum.UN_DEFAULT.code,
                DateUtil.date());
        //更新当前发票更内容为默认发票内容
        merchantInvoiceSubjectCorrelationMapper.updateInvoiceIsDefault(merchantId,invoiceDefaultContent,DefaultStatusEnum.DEFAULT.code,
                DateUtil.date());
    }

    @Override
    public InvoiceInfo billInfo(Integer merchantId) {
        InvoiceInfo invoiceInfoByMerchantId = invoiceInfoMapper.getInvoiceInfoByMerchantId(merchantId);
        return invoiceInfoByMerchantId;
    }

    @Override
    public void returnInvoice(RefundInvoiceDTO refundInvoiceDTO) {

        if(StringUtils.isEmpty(refundInvoiceDTO.getExpressCompanyNameReturn())){
            throw new BusinessException("发票邮寄物流名称不能为空");
        }

        if(StringUtils.isEmpty(refundInvoiceDTO.getReturnRemark())){
            throw new BusinessException("备注不能为空");
        }

        if(StringUtils.isEmpty(refundInvoiceDTO.getExpressNoReturn())){
            throw new BusinessException("发票邮寄物流名称不能为空");
        }

        if(refundInvoiceDTO.getExpressCompanyNameReturn().length()>40){
           throw new BusinessException("运单号不能为空");
        }
        if(refundInvoiceDTO.getReturnRemark().length()>255){
            throw new BusinessException("备注过长，不能超过255个汉字");
        }
        if(refundInvoiceDTO.getExpressNoReturn().length()>40){
            throw new BusinessException("运单号过长，不能超过40个字符");
        }

        refundInvoiceDTO.setUpdateTime(DateUtil.date());

        //修改发票记录表的内容
        invoiceRecordMapper.updateReturnInvoice(refundInvoiceDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invoiceInvalid(Integer merchantId, String invoiceSerialum) {
        //1.发票记录表 修改发票状态
        invoiceRecordMapper.updateInvoiceStatus(merchantId,invoiceSerialum,InvoiceRecordInvoiceStatusEnum.INVALID.code,
                DateUtil.date());
        //2.查询出发票id
        Integer id = invoiceRecordMapper.selectIDByinvoiceSerialNum(merchantId, invoiceSerialum);
        //3.查询出发票id对应的充值记录
        List<RechargeDetialDTO> rechargeDetialDTOS = rechargeRecordMapper.selectRechargeRecords(merchantId, id);
        //4.将充值记录中的 发票状态修改为 0
        for(RechargeDetialDTO rechargeDetialDTO:rechargeDetialDTOS){
            rechargeRecordMapper.updateInvoiceStatusByOrderSerialNum(RechargeRecordInvoiceStatusEnum.UN_MAKE_OUT_INVOICE.code,
                    rechargeDetialDTO.getOrderSerialNum(),DateUtil.date());
        }
    }

    @Override
    public InvoiceApplicationDetailDTO invoiceApplicationDetail(Integer merchantId, String invoiceSerialum) {
        //1.根据发票申请编号查询发票记录
        InvoiceApplicationDetailDTO invoiceApplicationDetailDTO = invoiceRecordMapper.queryInvoiceRecordByInvoiceSerialNum(
                merchantId, invoiceSerialum);
        //2.获取发票id
        Integer invoiceID = invoiceApplicationDetailDTO.getId();
        //3.根据发票id查询出相应的充值记录
        List<RechargeDetialDTO> rechargeDetialDTOS = rechargeRecordMapper.selectRechargeRecords(merchantId, invoiceID);
        invoiceApplicationDetailDTO.setRechargeDetialDTOList(rechargeDetialDTOS);


        return invoiceApplicationDetailDTO;
    }

    @Override
    public Page queryInvoiceApplyRecords(InvoiceApplyRecordDTO invoiceApplyRecordDTO) {
        QueryPage queryPage = convertQueryPage(invoiceApplyRecordDTO.getCurrentPage(), invoiceApplyRecordDTO.getPageSize());
        invoiceApplyRecordDTO.setPageSize(queryPage.getPageSize());
        invoiceApplyRecordDTO.setStartIndex(queryPage.getStartIndex());
        List<InvoiceRecord> list = invoiceRecordMapper.queryInvoiceApplyRecords(invoiceApplyRecordDTO);
        Integer count = invoiceRecordMapper.countInvoiceApplyRecords(invoiceApplyRecordDTO);

        return new Page(count, list, invoiceApplyRecordDTO.getCurrentPage(), invoiceApplyRecordDTO.getPageSize());
    }

    @Override
    @Transactional
    public void saveInvoiceRecord(InvoiceRecordDTO invoiceRecordDTO){
        InvoiceRecord invoiceRecord = new InvoiceRecord();
        //商户id
        invoiceRecord.setMerchantId(invoiceRecordDTO.getMerchantId());

        TaxSounrceCompany taxSounrceCompany = rechargeRecordMapper.queryTaxSourceInfo(invoiceRecordDTO.getMerchantId());
        //税源地id
        invoiceRecord.setTaxSourceId(taxSounrceCompany.getId());
        //代征主体
        invoiceRecord.setBehalfMainBody(taxSounrceCompany.getTaxSounrceCompanyName());

        //发票金额
         invoiceRecord.setInvoiceAmount(invoiceRecordDTO.getInvoiceAmount());
        //税率
        invoiceRecord.setTaxRate(invoiceRecordDTO.getTaxRate());
        //税额
        invoiceRecord.setTaxAmount(invoiceRecordDTO.getTaxAmount());
        //单价
        invoiceRecord.setUnitPrice(invoiceRecordDTO.getUnitPrice());
        //发票性质 1
        invoiceRecord.setInvoiceNature(InvoiceNatureEnum.PAPER_INVOICE.code);
        //发票状态
        invoiceRecord.setInvoiceStatus(InvoiceRecordInvoiceStatusEnum.APPLY.code);
        //发票类型 0
        invoiceRecord.setInvoiceType(invoiceRecordDTO.getInvocieType());
        //发票申请编号（全局唯一，本地生成）
        invoiceRecord.setInvoiceSerialNum(ChanPayUtil.generateOutTradeNo());


        CheckingInvoiceDTO checkingInvoiceDTO = invoiceInfoMapper.checkingInvoice(invoiceRecordDTO.getMerchantId());
        //发票抬头
        invoiceRecord.setInvoiceTitle(checkingInvoiceDTO.getCompanyName());
        //纳税人识别号
        invoiceRecord.setTaxpayerIdentificationNumber(checkingInvoiceDTO.getTaxpayerIdentificationNumber());
        //单位注册地址
        invoiceRecord.setCompanyAddress(checkingInvoiceDTO.getCompanyAddress());
        //单位手机号
        invoiceRecord.setInvoiceMobile(checkingInvoiceDTO.getInvoiceMobile());

        String bankName = checkingInvoiceDTO.getBankName();

        String bankAccountNo = checkingInvoiceDTO.getBankNumber();
        //开户银行名称
        invoiceRecord.setBankName(bankName);
        //开户账号
        invoiceRecord.setBankAccountNo(bankAccountNo);



        //发票科目id
        invoiceRecord.setInvoiceSubjectId(invoiceRecordDTO.getInvoiceContent());
        //发票内容
        InvoiceSubject invoiceSubject = new InvoiceSubject();
        invoiceSubject.setId(invoiceRecordDTO.getInvoiceContent());
        InvoiceSubject invoiceSubject1 = invoiceSubjectMapper.selectOne(invoiceSubject);
        invoiceRecord.setInvoiceContent( invoiceSubject1.getContent());


        Address addressByMerchantId = addressMapper.getAddressByMerchantId(invoiceRecordDTO.getMerchantId());
        //收件地址id
        invoiceRecord.setAddressId(addressByMerchantId.getId());
        //收件人姓名
        invoiceRecord.setAddresseeName(addressByMerchantId.getAddresseeName());
        //收件人电话
        invoiceRecord.setAddressMobile(addressByMerchantId.getMobile());
        //收件地址
        invoiceRecord.setAddress(addressByMerchantId.getAddress());
        //备注
        invoiceRecord.setRemark(invoiceRecordDTO.getInvoiceNote());




        //创建时间
        invoiceRecord.setCreateTime(DateUtil.date());

        //insertSelective 自增id
        invoiceRecordMapper.insertSelective(invoiceRecord);
        Integer invoiceRecordID = invoiceRecordMapper.queryIdByInvoiceSerialum(invoiceRecord.getInvoiceSerialNum());

        //将未开发票 变为 已开发票 tbl_recharge_record.invoice_status
        //插入发票记录(发票id)与充值记录(充值记录id)对应关系，
        InvoiceRechargeCorrelation invoiceRechargeCorrelation;
        String[] orderSerialNums = invoiceRecordDTO.getOrderSerialNums();
        if(null != orderSerialNums && orderSerialNums.length>0){
            for (String orderSerialNum:orderSerialNums) {
                rechargeRecordMapper.updateInvoiceStatusByOrderSerialNum(RechargeRecordInvoiceStatusEnum.MAKE_OUT_INVOICE.code,
                        orderSerialNum,DateUtil.date());
                Integer rechargeRecordID = rechargeRecordMapper.selectIdByOrderSerialNum(orderSerialNum);
                invoiceRechargeCorrelation= new InvoiceRechargeCorrelation(invoiceRecordDTO.getMerchantId(),invoiceRecordID,
                        rechargeRecordID, DelStatusEnum.NORMAL.code, DateUtil.date());
                invoiceRechargeCorrelationMapper.insertSelective(invoiceRechargeCorrelation);
            }
        }
    }

    /**
     * 核对发票
     * @param merchantId  商户ID
     * @aram invocieType 发票类型
     * @param invoiceNote 备注
     * @param serviceName 货物或应税劳务、服务名称
     * @return
     * @throws Exception
     */
    @Override
    public CheckingInvoiceDTO checkingInvoice(Integer merchantId, String invocieType, String invoiceNote, Integer serviceName) throws Exception {

        CheckingInvoiceDTO checkingInvoiceDTO = invoiceInfoMapper.checkingInvoice(merchantId);


        //发票类型
        checkingInvoiceDTO.setInvoiceType(invocieType);

        //开户银行名称
        String bankName = checkingInvoiceDTO.getBankName();

        checkingInvoiceDTO.setBankName(bankName);

        //开户银行名称
        String bankNumber = checkingInvoiceDTO.getBankNumber();

        //开户卡号
        checkingInvoiceDTO.setBankNumber(bankNumber);


        //发票备注
        checkingInvoiceDTO.setInvoiceNote(invoiceNote);
        InvoiceSubject invoiceSubject = new InvoiceSubject();
        if(null == serviceName){
            throw new BusinessException("请选择货物或应税劳务、服务名称后再点击下一步");
        }
        invoiceSubject.setId(serviceName);
        InvoiceSubject invoiceSubject1 = invoiceSubjectMapper.selectOne(invoiceSubject);
        //货物或应税劳务、服务名称
        checkingInvoiceDTO.setServiceName(invoiceSubject1.getContent());

        return checkingInvoiceDTO;
    }

    /**
     * 开票校验
     */
    @Override
    public InvoiceApplyCheckDTO invoiceApplyCheck(List<Integer> records) throws BusinessException {

        // 开票总额
        BigDecimal invoiceAmount = UserBaseConstants.INIT_AMOUNT;
        if (CollectionUtils.isEmpty(records)) {
            throw new BusinessException("充值记录为空");
        }

        Integer merchantId = null;
        //充值记录
        RechargeRecord record = null;

        for (Integer recordsId : records) {
            record = rechargeRecordMapper.selectByPrimaryKey(recordsId);
            if (record == null) {
                throw new BusinessException("充值记录不存在");
            }

            // 当两条流水号所属商户不一致时
            if (merchantId != null && !merchantId.equals(record.getMerchantId())) {
                throw new BusinessException("所属商户不一致");
            }

            merchantId = record.getMerchantId();

            // 判断是否满足开票条件 记录状态--未开票 充值--成功 商户id,记录金额不为空
            boolean sign = record.getInvoiceStatus() == UserBaseConstants.UNINVOICED
                    && UserBaseConstants.SUCCESS.equals(record.getStatus()) && merchantId != null
                    && !UserBaseConstants.INIT_AMOUNT.equals(record.getAmount());
            if (!sign) {
                throw new BusinessException("不满足开票条件");
            }

            invoiceAmount = invoiceAmount.add(record.getAmount());
        }


        // 保存开票信息 todo 暂时保存金额和商户ID
        // 单价为 总金额/(1+tax) 精度4 向上取整
        BigDecimal unitPrice = invoiceAmount.divide(BigDecimal.ONE.add(UserBaseConstants.TAX_RATE),
                UserBaseConstants.SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal taxAmount = invoiceAmount.subtract(unitPrice);

        InvoiceApplyCheckDTO apply = new InvoiceApplyCheckDTO();
        apply.setInvoiceAmount(invoiceAmount);
        apply.setTaxRate(UserBaseConstants.TAX_RATE);
        apply.setTaxAmount(taxAmount);
        apply.setUnitPrice(unitPrice);
        apply.setMerchantId(merchantId);
        apply.setRecords(records);
        return apply;
    }

    /**
     * 发票申请
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invoiceApply(InvoiceApplyDetailDTO dto) throws Exception {
        // 充值流水集
        List<Integer> records = dto.getRecords();
        // 开票金额集
        List<InvoiceApplyDTO> applys = dto.getInvoices();

        //获取开票金额相关合计
        InvoiceApplyCheckDTO applyCheck = invoiceApplyCheck(records);
        BigDecimal invoiceAmount = applyCheck.getInvoiceAmount();
        Integer merchantId = applyCheck.getMerchantId();
        InvoiceInfo invoiceInfo = invoiceInfoMapper.getInvoiceInfoByMerchantId(merchantId);
        Integer invoiceInfoId = invoiceInfo.getId();

        Address address = addressMapper.getAddressByMerchantId(merchantId);
        if (address == null) {
            throw new BusinessException("商户收获地址不存在");
        }

        // 校验发票集合金额合计是否为充值记录总额-->发票集合金额合计
        BigDecimal applyAmount = BigDecimal.ZERO;

        for (InvoiceApplyDTO apply : applys) {
            BigDecimal singleInvoiceAmount = apply.getSingleInvoiceAmount();
            BigDecimal checkAmount = BigDecimal.ZERO;
            for (InvoiceAmountDTO amount : apply.getServiceAmountList()) {
                applyAmount = applyAmount.add(amount.getAmount());
                checkAmount = checkAmount.add(amount.getAmount());
            }
            if (checkAmount.compareTo(singleInvoiceAmount) != 0) {
                throw new BusinessException("发票金额有误");
            }
        }

        if (invoiceAmount.compareTo(applyAmount) != 0) {
            throw new BusinessException("开票申请金额与充值金额不一致！");
        }

        // 当开票数大于2张时每张发票的开票金额需要大于10w
        if (applys.size() > 1) {
            for (InvoiceApplyDTO invoice : applys) {
                // 单张发票服务条目合计
                BigDecimal singleAmount = BigDecimal.ZERO;
                for (InvoiceAmountDTO invoiceAmountDTO : invoice.getServiceAmountList()) {
                    singleAmount = singleAmount.add(invoiceAmountDTO.getAmount());
                }

                if (UserBaseConstants.INVOICE_MIN_AMOUNT.compareTo(singleAmount) > 0) {
                    throw new BusinessException("开多张时每张发票的开票金额不得小于10万");
                }
                invoice.setSingleInvoiceAmount(singleAmount);
            }

        }

        // 发票申请ID集合-->用于记录发票和流水间的关系

        for (InvoiceApplyDTO invoice : applys) {

            InvoiceApply invoiceApply = new InvoiceApply();
            invoiceApply.setAddressId(address.getId());
            invoiceApply.setCreateTime(DateUtil.date());
            invoiceApply.setInvoiceInfoId(invoiceInfoId);
            // 存入发票信息表
            invoiceApply.setInvoiceStatus(InvoiceStatusEnum.APPLY.name());
            invoiceApply.setBankName(invoice.getBankName());
            invoiceApply.setRemarks(invoice.getRemarks());
            invoiceApply.setInstruction(invoice.getInstruction());
            invoiceApply.setInvoiceType(invoice.getInvoiceType());
            invoiceApply.setToVoid(InvoiceEffectiveEnum.EFFECTIVE.getCode());
            invoiceApply.setInvoiceAmount(invoice.getSingleInvoiceAmount());
            invoiceApply.setMerchantId(merchantId);
            invoiceApply.setDelStatus(DelStatusEnum.NORMAL.code);
            invoiceApplyMapper.insertUseGeneratedKeys(invoiceApply);
            //云众包发票处理
//            String result = doYunZBInvoice(merchantId, dto, invoice);
//            String result = doYunZBInvoice(merchantId, dto, invoice);
//            JSONObject jsonObject = JSONObject.fromObject(result);
//
//            if (jsonObject.get(YunZBConstants.REQUEST_CODE).equals(YunZBConstants.RESULT_CODE)) {
//                invoiceApply.setInvoiceSerialNum(jsonObject.getString(YunZBConstants.BATCH_ID));
//                invoiceApplyMapper.updateByPrimaryKeySelective(invoiceApply);
//            } else {
//                throw new BusinessException(jsonObject.getString(YunZBConstants.REQUEST_MSG));
//            }

//            YunZBInvoiceInfoDTO yunZBInvoiceInfoDTO = getInvoiceApplyDetail(invoiceApply.getId());
//            //税率
//            Double rate =
//                    Double.parseDouble(yunZBInvoiceInfoDTO.getYunZBInvoice_details().get(0).getContents().get(0).getTax_rate());
//            //税额
//            Double taxAmount =
//                    Double.parseDouble(yunZBInvoiceInfoDTO.getYunZBInvoice_details().get(0).getContents().get(0).getTax_amt());
//            //含税金额
//            Double totalAmount =
//                    Double.parseDouble(yunZBInvoiceInfoDTO.getYunZBInvoice_details().get(0).getContents().get(0).getTotal_amt());
//            //发票内容代码
//            String contentCode =
//                    yunZBInvoiceInfoDTO.getYunZBInvoice_details().get(0).getContents().get(0).getContent_code();
//            //发票内容名称
//            String contentName =
//                    yunZBInvoiceInfoDTO.getYunZBInvoice_details().get(0).getContents().get(0).getContent_name();

            for (Integer recordsId : records) {
//                InvoiceApplyDetail detail = new InvoiceApplyDetail();
//                detail.setRechargeRecord(recordsId);
//                detail.setInvoiceApplyId(invoiceApply.getId());
//                detail.setDelStatus(DelStatusEnum.NORMAL.code);
//                detail.setCreateTime(DateUtil.date());
//                invoiceApplyDetailMapper.insertUseGeneratedKeys(detail);
//
//                InvoiceApplyAmount invoiceApplyAmount = new InvoiceApplyAmount();
//                invoiceApplyAmount.setInvoiceDetailId(detail.getId());
//                invoiceApplyAmount.setTaxRate(BigDecimal.valueOf(rate));
//                invoiceApplyAmount.setTaxAmount(BigDecimal.valueOf(taxAmount));
//                invoiceApplyAmount.setInvoiceSerialName(contentName);
//                invoiceApplyAmount.setInvoiceAmount(BigDecimal.valueOf(totalAmount));
//                invoiceApplyAmount.setInvoiceId(invoiceApply.getId());
//                invoiceApplyAmount.setCreateTime(DateUtil.date());
//                invoiceApplyAmountMapper.insertSelective(invoiceApplyAmount);

                // 更新开票流水
                RechargeRecord finalRecord = new RechargeRecord();
                finalRecord.setId(recordsId);
                // 更新流水号开票状态--->已开票
                finalRecord.setInvoiceStatus(UserBaseConstants.INVOICED);
                rechargeRecordMapper.updateByPrimaryKeySelective(finalRecord);
            }
        }
    }

    /**
     * 发票申请记录查询
     */
    @Override
    public Page listInvoiceApplications(Integer merchantId, Integer pageSize, Integer currentPage, String invoiceSerialNum,
                                        String invoiceCode, String startDate, String endDate) throws BusinessException {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        // 获取除重开中的发票
        List<InvoiceQueryDTO> list = invoiceApplyMapper.listInvoiceApplications(merchantId, pageSize, queryPage.getStartIndex(),
                invoiceSerialNum, invoiceCode, startDate, endDate, InvoiceEffectiveEnum.RESTARTING.getCode());
        Integer count = invoiceApplyMapper.countInvoiceApplications(merchantId, invoiceSerialNum, invoiceCode, startDate,
                endDate, InvoiceEffectiveEnum.RESTARTING.getCode());
        // todo 查询条件待定
        return new Page(count, list, currentPage, pageSize);
    }

    /**
     * 发票申请成功记录查询
     */
    @Override
    public Page listSuccessInvoiceApplications(Integer merchantId, Integer pageSize, Integer currentPage,
                                               String invoiceSerialNum, String invoiceCode, String startDate,
                                               String endDate) throws BusinessException {
        MerchantInfo info = merchantInfoMapper.getMerchantInfoById(merchantId);
        String merchantName = info.getMerchantName();
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        // 只展示审核过的发票
        List<MerchantInvoicedDetail> list = invoiceApplyMapper.listSuccessInvoiceApplications(merchantId, pageSize,
                queryPage.getStartIndex(), invoiceSerialNum, invoiceCode, startDate, endDate);

        Integer count = invoiceApplyMapper.countSuccessInvoiceApplications(merchantId, invoiceSerialNum, invoiceCode, startDate,
                endDate);
        for (MerchantInvoicedDetail detail : list) {
            detail.setBuyer(merchantName);
            Integer invoiceId = detail.getId();
            String address = addressMapper.selectByPrimaryKey(detail.getAddressId()).getAddress();
            // 发票明细列表
            List<InvoiceAmountServiceDetailDTO> amount =
                    invoiceApplyAmountMapper.getInvoiceApplyAmountByInvoiceId(invoiceId);
            MerchantInvoiceAmountCalculationDTO dto = getAmountDetail(detail.getInvoiceAmount());
            detail.setUnitPrice(dto.getUnitPrice());
            detail.setTaxRate(dto.getTaxRate());
            detail.setTaxAmount(dto.getTaxAmount());
            detail.setAddressName(address);
            // 只取第一条服务税费名
            if (!CollectionUtils.isEmpty(amount)) {
                detail.setInvoiceSerialName(amount.get(0).getInvoiceSerialName());
            }
            detail.setServiceAmounts(amount);
        }

        return new Page(count, list, currentPage, pageSize);
    }


    /**
     * 商户发票详情
     */
    @Override
    public YunZBInvoiceInfoDTO getInvoiceApplyDetail(Integer id,String merchantName) throws BusinessException {
        YunZBInvoiceInfoDTO yunZBInvoiceInfoDTO = new YunZBInvoiceInfoDTO();
        //查询本地保存的发票信息
        InvoiceApply invoiceApply = invoiceApplyMapper.selectByPrimaryKey(id);
        //查询平台商户信息
        PaymentMerchantInfo paymentMerchantInfo = paymentMerchantInfoMapper.selectByMerchantIdAndChannel(
                invoiceApply.getMerchantId(), PaymentChannelEnum.YUNZB.name());
        OnlinePaymentInfo onlinePaymentInfo = onlinePaymentInfoMapper.selectByMerchantId(invoiceApply.getMerchantId());
        TianJinPaymentInfo tianJinPaymentInfo = tianJinPaymentInfoMapper.getTianJinPaymentInfoByMerchantId(
                invoiceApply.getMerchantId());

        yunZBInvoiceInfoDTO.setApply_amt(invoiceApply.getInvoiceAmount().toString());
        yunZBInvoiceInfoDTO.setApply_num("1");
        yunZBInvoiceInfoDTO.setApply_time(invoiceApply.getCreateTime());
        yunZBInvoiceInfoDTO.setBatch_id(invoiceApply.getInvoiceSerialNum());
        yunZBInvoiceInfoDTO.setInvoice_title(merchantName);
        if(null != paymentMerchantInfo) {
            YunZBQueryInvoiceDTO yunZBQueryInvoiceDTO = new YunZBQueryInvoiceDTO();
            yunZBQueryInvoiceDTO.setBatchId(invoiceApply.getInvoiceSerialNum());
            TaxSounrceCompany taxSounrceCompany = taxSounrceCompanyMapper.selectByPrimaryKey(paymentMerchantInfo.getTaxSourceId());
            yunZBQueryInvoiceDTO.setChannelNo(queryChannels(taxSounrceCompany.getMerchantNo(),
                    taxSounrceCompany.getSecretKey()));
            yunZBQueryInvoiceDTO.setSubMchId(paymentMerchantInfo.getMerchantNo());
            yunZBQueryInvoiceDTO.setMchId(taxSounrceCompany.getMerchantNo());
            try {
                Response response = YunZBUtil.remoteInvoke(YunZBConstants.INVOICE, yunZBQueryInvoiceDTO,
                        taxSounrceCompany.getSecretKey());
                yunZBInvoiceInfoDTO = JSON.parseObject(response.getRespData(), YunZBInvoiceInfoDTO.class);
                switch (yunZBInvoiceInfoDTO.getState()) {
                    case "1":
                        invoiceApply.setInvoiceStatus(InvoiceStatusEnum.APPLY.name());
                        break;
                    case "2":
                        invoiceApply.setInvoiceStatus(InvoiceStatusEnum.AGREE.name());
                        break;
                    case "3":
                        invoiceApply.setInvoiceStatus(InvoiceStatusEnum.REFUSE.name());
                        break;
                    case "4":
                        invoiceApply.setInvoiceStatus(InvoiceStatusEnum.POSTED.name());
                        break;
                    default:
                        break;
                }
                invoiceApplyMapper.updateByPrimaryKeySelective(invoiceApply);
            } catch (Exception e) {

            }
        }else if(null != onlinePaymentInfo){
            yunZBInvoiceInfoDTO.setChannel(InvoiceChannelEnum.YUNZIJIN.getExplain());
            yunZBInvoiceInfoDTO.setIssued_by(InvoiceChannelEnum.YUNZIJIN.getExplain());

        }else{
            yunZBInvoiceInfoDTO.setChannel(InvoiceChannelEnum.TIANJIN.getExplain());
            yunZBInvoiceInfoDTO.setIssued_by(InvoiceChannelEnum.TIANJIN.getExplain());
        }

        return yunZBInvoiceInfoDTO;

    }

    /**
     * 图片
     */
    @Override
    public String getInvoiceImage(Integer invoiceDetailId) {
        InvoiceApplyDetail invoiceApplyDetail = invoiceApplyDetailMapper.selectByPrimaryKey(invoiceDetailId);
        return invoiceApplyDetail.getInvoiceImg();
    }

    /**
     * 查询开票流水
     */
    @Override
    public Page getRechargeByInvoiceId(Integer pageSize, Integer currentPage, Integer invoiceId) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        List<RechargeRecord> list = rechargeRecordMapper.getRechargeByInvoiceId(pageSize, queryPage.getStartIndex(),
                invoiceId);
        Integer count = rechargeRecordMapper.countRechargeByInvoiceId(invoiceId);
        // todo 查询条件待定
        return new Page(count, list, currentPage, pageSize);
    }

    @Override
    public BigDecimal getInvoiceRechargeAmount(Integer invoiceId) {
        BigDecimal amount = rechargeRecordMapper.getTotalRechargeAmount(invoiceId);
        if (amount == null) {
            amount = BigDecimal.ZERO;
        }
        return amount;
    }


    /**
     * 开票充值记录
     *
     * @param startDate
     * @param endDate
     * @param merchantId
     * @param pageSize
     * @param currentPage
     * @return
     */
    @Override
    public Page getRechargerForInvoice(String startDate, String endDate, int merchantId, int pageSize, int currentPage) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);

        List<MerchantRechargeRecordInvoiceDTO> list = rechargeRecordMapper.getListMerchantRechargeRecordForInvoice(startDate, endDate,
                merchantId, queryPage.getStartIndex(), pageSize);
        int count = rechargeRecordMapper.getCountMerchantRechargeRecordForInvoice(startDate, endDate, merchantId);

        return new Page(count, list, currentPage, pageSize);
    }

    /**
     * 开票总额
     */
    @Override
    public BigDecimal getAllAmountByInvoiceId(Integer invoiceId) {
        List<RechargeRecord> allList = rechargeRecordMapper.getAllRechargeByInvoiceId(invoiceId);
        BigDecimal amount = BigDecimal.ZERO;
        //获取开票总额
        for (RechargeRecord r : allList) {
            amount = amount.add(r.getAmount());
        }
        return amount;
    }

    /**
     * 发票重开校验
     */
    @Override
    public InvoiceRestartDetailDTO checkRestartInvoice(Integer invoiceId) {
        InvoiceApply invoiceApply = invoiceApplyMapper.selectByPrimaryKey(invoiceId);
        Integer merchantId = invoiceApply.getMerchantId();
        InvoiceInfo info = invoiceInfoMapper.getInvoiceInfoByMerchantId(merchantId);
        String[] serviceName = info.getInvoiceContent().split(",");
        InvoiceRestartDetailDTO invoiceDetail = new InvoiceRestartDetailDTO();
        if (invoiceApply == null || !InvoiceEffectiveEnum.EFFECTIVE.getCode().equals(invoiceApply.getToVoid())) {
            throw new BusinessException("发票不存在或已被重开");
        }
        BeanUtils.copyProperties(invoiceApply, invoiceDetail);
        invoiceDetail.setInvoiceServiceNameList(Arrays.asList(serviceName));
        List<InvoiceAmountServiceDetailDTO> detail =
                invoiceApplyAmountMapper.getInvoiceApplyAmountByInvoiceId(invoiceId);
        invoiceDetail.setServiceAmounts(detail);
        return invoiceDetail;
    }


    /**
     * 发票重开
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int restartApplyInvoice(InvoiceRestartInfoDTO invoiceRestartInfoDTO) {
        Integer invoiceId = invoiceRestartInfoDTO.getInvoiceId();
        InvoiceApply apply = invoiceApplyMapper.selectByPrimaryKey(invoiceId);
        if (!InvoiceEffectiveEnum.EFFECTIVE.getCode().equals(apply.getToVoid())) {
            throw new BusinessException("请勿重复重开");
        }
        apply.setToVoid(InvoiceEffectiveEnum.PROCESSING.getCode());
        invoiceApplyMapper.updateByPrimaryKeySelective(apply);
        Integer merchantId = apply.getMerchantId();
        BigDecimal invoiceAmount = apply.getInvoiceAmount();
        List<InvoiceApplyDTO> applyList = invoiceRestartInfoDTO.getInvoices();
        //获取开票金额相关合计
        int invoiceInfoId = invoiceInfoMapper.getInvoiceIdByMerchantId(merchantId);
        Address address = addressMapper.getAddressByMerchantId(merchantId);
        if (address == null) {
            throw new BusinessException("商户收获地址不存在");
        }

        // 校验发票集合金额合计是否为充值记录总额-->发票集合金额合计
        BigDecimal applyAmount = BigDecimal.ZERO;
        for (InvoiceApplyDTO applys : applyList) {
            BigDecimal singleInvoiceAmount = BigDecimal.ZERO;
            for (InvoiceAmountDTO amount : applys.getServiceAmountList()) {
                singleInvoiceAmount = singleInvoiceAmount.add(amount.getAmount());
            }
            applys.setSingleInvoiceAmount(singleInvoiceAmount);
            applyAmount = applyAmount.add(applys.getSingleInvoiceAmount());
        }

        if (invoiceAmount.compareTo(applyAmount) != 0) {
            throw new BusinessException("开票金额不一致！");
        }

        // 当开票数大于2张时每张发票的开票金额需要大于10w
        if (applyList.size() > 1) {
            for (InvoiceApplyDTO invoice : applyList) {
                if (UserBaseConstants.INVOICE_MIN_AMOUNT.compareTo(invoice.getSingleInvoiceAmount()) > 0) {
                    throw new BusinessException("开多张时每张发票的开票金额不得小于10万");
                }
            }

        }

        for (InvoiceApplyDTO invoice : applyList) {
            StringBuffer invoiceSerialNum = new StringBuffer();
            invoiceSerialNum.append(DateUtil.date().getTime()).append(MerchantIdUtil.getMerchantId());
            String serialNum = invoiceSerialNum.substring(8, invoiceSerialNum.length());
            InvoiceApply invoiceApply = new InvoiceApply();
            invoiceApply.setAddressId(address.getId());
            invoiceApply.setCreateTime(DateUtil.date());
            invoiceApply.setInvoiceInfoId(invoiceInfoId);
            // 存入发票信息表 待重开发票无需记录申请状态
            invoiceApply.setInvoiceStatus(InvoiceStatusEnum.APPLY.name());
            invoiceApply.setBankName(invoice.getBankName());
            invoiceApply.setRemarks(invoice.getRemarks());
            invoiceApply.setToVoid(InvoiceEffectiveEnum.RESTARTING.getCode());
            StringBuffer instruction = new StringBuffer();
            instruction.append("重开发票编号:").append(" ").append(apply.getInvoiceSerialNum());
            if (StringUtils.isNotEmpty(invoice.getInstruction())) {
                instruction.append(invoice.getInstruction());
            }
            invoiceApply.setInstruction(instruction.toString());
            invoiceApply.setInvoiceType(invoice.getInvoiceType());
            invoiceApply.setInvoiceSerialNum(serialNum);
            invoiceApply.setInvoiceAmount(invoice.getSingleInvoiceAmount());
            invoiceApply.setMerchantId(merchantId);
            // 待重开发票需要保存根据哪张作废发票重开的ID
            invoiceApply.setGenerateId(invoiceId);
            invoiceApply.setDelStatus(DelStatusEnum.NORMAL.code);
            invoiceApplyMapper.insertUseGeneratedKeys(invoiceApply);
            InvoiceApplyDetail invoiceApplyDetail = new InvoiceApplyDetail();
            invoiceApplyDetail.setDelStatus(DelStatusEnum.NORMAL.code);
            invoiceApplyDetail.setInvoiceApplyId(invoiceId);
            List<InvoiceApplyDetail> list = invoiceApplyDetailMapper.select(invoiceApplyDetail);
            for (InvoiceApplyDetail detail : list) {
                for (InvoiceAmountDTO serviceDetail : invoice.getServiceAmountList()) {
                    // 依据发票申请ID 将服务条目存入发票金额表
                    InvoiceApplyAmount invoiceApplyAmount = createServiceAmount(serviceDetail, invoiceApply.getId());
                    invoiceApplyAmount.setInvoiceDetailId(detail.getId());
                    invoiceApplyAmount.setUpdateTime(DateUtil.date());
                    invoiceApplyAmountMapper.updateByPrimaryKeySelective(invoiceApplyAmount);
                }
            }
        }

        // 重开信息更新 红冲发票 盖章证明等
        InvoiceFail invoiceFail = invoiceFailMapper.getRestartInfoByInvoiceId(invoiceRestartInfoDTO.getInvoiceId());
        if (invoiceFail != null) {
            throw new BusinessException("重开信息已存在");
        }

        InvoiceFail newInvoiceFail = new InvoiceFail();
        newInvoiceFail.setInvoiceId(invoiceRestartInfoDTO.getInvoiceId());
        newInvoiceFail.setStatus(invoiceRestartInfoDTO.getStatus());
        newInvoiceFail.setDelStatus(DelStatusEnum.NORMAL.code);
        newInvoiceFail.setRedInvoiceInfo(invoiceRestartInfoDTO.getRedInvoice());
        newInvoiceFail.setSealExplain(invoiceRestartInfoDTO.getSealExplain());
        newInvoiceFail.setCreateTime(DateUtil.date());
        newInvoiceFail.setUpdateTime(DateUtil.date());
        return invoiceFailMapper.insertSelective(newInvoiceFail);
    }

    /**
     * 获取开票合计
     */
    @Override
    public MerchantInvoiceTotalAmountDTO getTotalAmount(Integer merchantId, String invoiceSerialNum,
                                                        String invoiceCode, String startDate, String endDate) {
        MerchantInvoiceTotalAmountDTO amount = invoiceApplyMapper.getTotalAmount(merchantId, invoiceSerialNum,
                invoiceCode, startDate, endDate);
        BigDecimal totalAmount = amount.getTotalAmount();
        BigDecimal unitPrice = totalAmount.divide(BigDecimal.ONE.add(UserBaseConstants.TAX_RATE),
                UserBaseConstants.SCALE, BigDecimal.ROUND_HALF_UP);
        // 税额= 总额-单价
        BigDecimal taxAmount = totalAmount.subtract(unitPrice);
        amount.setTotalUnitprice(unitPrice);
        amount.setTotalTaxAmount(taxAmount);
        return amount;

    }

    @Override
    public MerchantInvoiceAmountCalculationDTO getAmountDetail(BigDecimal amount) {
        MerchantInvoiceAmountCalculationDTO dto = new MerchantInvoiceAmountCalculationDTO();
        //单价
        BigDecimal unitPrice = amount.divide(BigDecimal.ONE.add(UserBaseConstants.TAX_RATE),
                UserBaseConstants.SCALE, BigDecimal.ROUND_HALF_UP);
        // 税额= 总额-单价
        BigDecimal taxAmount = amount.subtract(unitPrice);
        dto.setTaxRate(UserBaseConstants.TAX_RATE);
        dto.setTaxAmount(taxAmount);
        dto.setUnitPrice(unitPrice);
        dto.setAmount(amount);
        return dto;
    }

    @Override
    public InvoiceCheckDetailDTO reviewRefuseInvoice(Integer invoiceId) {
        InvoiceCheckDetailDTO invoice = invoiceApplyMapper.getCheckDetailById(invoiceId);
        List<InvoiceAmountServiceDetailDTO> list = invoiceApplyAmountMapper.getInvoiceApplyAmountByInvoiceId(invoiceId);
        invoice.setServiceAmounts(list);
        return invoice;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateAndRestartRefuseInvoice(MerchantInvoiceRefuseDTO dto) {
        Integer invoiceId = dto.getId();
        InvoiceApply oldApply = invoiceApplyMapper.selectByPrimaryKey(invoiceId);
        if (oldApply.getInvoiceAmount().compareTo(dto.getSingleInvoiceAmount()) != 0) {
            throw new BusinessException("开票金额不一致！");
        }
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<InvoiceAmountDTO> list = dto.getServiceAmountList();
        for (InvoiceAmountDTO invoice : list) {
            totalAmount = totalAmount.add(invoice.getAmount());
        }
        if (dto.getSingleInvoiceAmount().compareTo(totalAmount) != 0) {
            throw new BusinessException("开票总额不一致");
        }

        // 移除发票金额信息表中的所有相关数据
        invoiceApplyAmountMapper.removeInvoiceAmountInfo(invoiceId);
        InvoiceApplyDTO invoice = new InvoiceApplyDTO();
        BeanUtils.copyProperties(dto, invoice);
        invoice.setServiceAmountList(dto.getServiceAmountList());
        InvoiceApplyDetail invoiceApplyDetail = new InvoiceApplyDetail();
        invoiceApplyDetail.setDelStatus(DelStatusEnum.NORMAL.code);
        invoiceApplyDetail.setInvoiceApplyId(invoiceId);
        List<InvoiceApplyDetail> detailList = invoiceApplyDetailMapper.select(invoiceApplyDetail);
        for (InvoiceApplyDetail detail : detailList) {
            // 依据发票申请ID 将服务条目存入发票金额表
            for (InvoiceAmountDTO serviceDetail : invoice.getServiceAmountList()) {
                InvoiceApplyAmount amount = createServiceAmount(serviceDetail, invoiceId);
                amount.setInvoiceDetailId(detail.getId());
                amount.setUpdateTime(DateUtil.date());
                invoiceApplyAmountMapper.updateByPrimaryKeySelective(amount);
            }

        }
        if (!InvoiceStatusEnum.REFUSE.name().equals(oldApply.getInvoiceStatus())) {
            throw new BusinessException("只能修改驳回发票");
        }

        InvoiceApply apply = new InvoiceApply();
        BeanUtils.copyProperties(dto, apply);
        apply.setInvoiceStatus(InvoiceStatusEnum.APPLY.name());
        return invoiceApplyMapper.updateByPrimaryKeySelective(apply);
    }

    /**
     * 依据发票申请ID 将服务条目存入发票金额表
     *
     * @param serviceDetail  服务详情
     * @param invoiceApplyId 服务费关联的发票申请ID
     */
    private InvoiceApplyAmount createServiceAmount(InvoiceAmountDTO serviceDetail, Integer invoiceApplyId) {

        // 单条服务总额
        BigDecimal serviceAmount = serviceDetail.getAmount();
        // 单价为 总金额/(1+tax) 精度2 向上取整
        BigDecimal unitPrice = serviceAmount.divide(BigDecimal.ONE.add(UserBaseConstants.TAX_RATE),
                UserBaseConstants.SCALE, BigDecimal.ROUND_HALF_UP);
        // 税额= 总额-单价
        BigDecimal taxAmount = serviceAmount.subtract(unitPrice);
        InvoiceApplyAmount invoiceApplyAmount = new InvoiceApplyAmount();
        invoiceApplyAmount.setTaxRate(UserBaseConstants.TAX_RATE);
        invoiceApplyAmount.setInvoiceAmount(serviceDetail.getAmount());
        invoiceApplyAmount.setUnitPrice(unitPrice);
        invoiceApplyAmount.setTaxAmount(taxAmount);
        invoiceApplyAmount.setInvoiceSerialName(serviceDetail.getInvoiceServiceName().getContent_code());
        invoiceApplyAmount.setInvoiceId(invoiceApplyId);
        invoiceApplyAmount.setCreateTime(DateUtil.date());
        invoiceApplyAmount.setStatus(DelStatusEnum.NORMAL.code);
        invoiceApplyAmountMapper.insertUseGeneratedKeys(invoiceApplyAmount);
        return invoiceApplyAmount;
    }


    @Override
    public InvoiceServiceNameDTO getInvoiceServiceName(Integer merchantId) throws Exception {
        InvoiceServiceNameDTO invoiceServiceNameDTO = new InvoiceServiceNameDTO();

        List<YunZBInvoiceContentResultDTO> list = new ArrayList<>();

        list = merchantInvoiceSubjectCorrelationMapper.getInvoiceServiceName(merchantId);

        String defaultInvoiceContent = invoiceInfoMapper.queryDefaultInvoiceContentBymerchantId(merchantId);
        invoiceServiceNameDTO.setYunZBInvoiceContentResultDTOS(list);
        invoiceServiceNameDTO.setDefaultInvoiceContent(defaultInvoiceContent);
        return invoiceServiceNameDTO;

    }

    @Override
    public HashMap<String,Object> queryInvoiceBills (InvoiceBillDTO invoiceBillDTO) throws Exception {
        HashMap<String,Object> map = new HashMap<>();
        QueryPage queryPage = convertQueryPage(invoiceBillDTO.getCurrentPage(), invoiceBillDTO.getPageSize());
        invoiceBillDTO.setPageSize(queryPage.getPageSize());
        invoiceBillDTO.setStartIndex(queryPage.getStartIndex());

        List<YunZBInvoiceBillResultDTO> yunZBInvoiceBillResultDTOs = new ArrayList<>();
        yunZBInvoiceBillResultDTOs = rechargeRecordMapper.queryInvoiceBills(invoiceBillDTO);
        Integer count = rechargeRecordMapper.countInvoiceBills(invoiceBillDTO);
        BigDecimal bigDecimal = rechargeRecordMapper.queryInvoiceBillsAmount(invoiceBillDTO);

        map.put("data",new Page(count, yunZBInvoiceBillResultDTOs, invoiceBillDTO.getCurrentPage(), invoiceBillDTO.getPageSize()));
        map.put("available_amount",bigDecimal);
        return map;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void yunZbInvoiceApply(YunZbNewInvoiceApplyDTO yunZbNewInvoiceApplyDTO) throws Exception {
        //查询邮寄地址
        Address address = addressMapper.getAddressByMerchantId(yunZbNewInvoiceApplyDTO.getMerchantId());
        //查询开票信息
        InvoiceInfo invoiceInfo = invoiceInfoMapper.getInvoiceInfoByMerchantId(yunZbNewInvoiceApplyDTO.getMerchantId());
        InvoiceApply invoiceApply = new InvoiceApply();
        invoiceApply.setAddressId(address.getId());
        invoiceApply.setCreateTime(DateUtil.date());
        invoiceApply.setInvoiceInfoId(invoiceInfo.getId());
        // 存入发票信息表
        invoiceApply.setInvoiceStatus(InvoiceStatusEnum.APPLY.name());
        invoiceApply.setRemarks(yunZbNewInvoiceApplyDTO.getInvoiceRemark());
        invoiceApply.setInstruction(yunZbNewInvoiceApplyDTO.getApplyRemark());
        invoiceApply.setInvoiceType(0);
        invoiceApply.setToVoid(InvoiceEffectiveEnum.EFFECTIVE.getCode());
        invoiceApply.setInvoiceAmount(BigDecimal.valueOf(Double.valueOf(yunZbNewInvoiceApplyDTO.getTotalAmount())));
        invoiceApply.setMerchantId(yunZbNewInvoiceApplyDTO.getMerchantId());
        invoiceApply.setDelStatus(DelStatusEnum.NORMAL.code);
        invoiceApplyMapper.insertUseGeneratedKeys(invoiceApply);

        PaymentMerchantInfo paymentMerchantInfo = paymentMerchantInfoMapper.selectByMerchantIdAndChannel(
                yunZbNewInvoiceApplyDTO.getMerchantId(),PaymentChannelEnum.YUNZB.name());
        String invoiceSerialNum;
        Double rate,taxAmount,totalAmount;
        String contentCode,contentName;
        if(null != paymentMerchantInfo) {
            String result = doYunZBInvoice(yunZbNewInvoiceApplyDTO);
            JSONObject jsonObject = JSONObject.fromObject(result);
            if (jsonObject.get(YunZBConstants.RETURN_CODE).equals(YunZBConstants.RESULT_CODE)) {
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new BusinessException(jsonObject.get(YunZBConstants.RETURN_MSG).toString());
            }
            if (StringUtils.isNotEmpty(jsonObject.get(YunZBConstants.REQUEST_CODE).toString()) &&
                    jsonObject.get(YunZBConstants.REQUEST_CODE).equals(YunZBConstants.RESULT_CODE)) {
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new BusinessException(jsonObject.get(YunZBConstants.REQUEST_MSG).toString());
            }
            invoiceSerialNum = jsonObject.getString(YunZBConstants.BATCH_ID);
            YunZBInvoiceInfoDTO yunZBInvoiceInfoDTO = getInvoiceApplyDetail(invoiceApply.getId(),null);
            //税率
            rate = Double.parseDouble(
                    yunZBInvoiceInfoDTO.getInvoice_details().get(0).getContents().get(0).getTax_rate());
            //税额
            taxAmount = Double.parseDouble(
                    yunZBInvoiceInfoDTO.getInvoice_details().get(0).getContents().get(0).getTax_amt());
            //含税金额
            totalAmount = Double.parseDouble(
                    yunZBInvoiceInfoDTO.getInvoice_details().get(0).getContents().get(0).getTotal_amt());
            //发票内容代码
            contentCode = yunZBInvoiceInfoDTO.getInvoice_details().get(0).getContents().get(0).getContent_code();
            //发票内容名称
            contentName = yunZBInvoiceInfoDTO.getInvoice_details().get(0).getContents().get(0).getContent_name();
        }else {
            invoiceSerialNum = ChanPayUtil.generateOutTradeNo();
            invoiceSerialNum = invoiceSerialNum.substring(invoiceSerialNum.length()-5);
            MerchantInvoiceAmountCalculationDTO dto = this.getAmountDetail(
                    BigDecimal.valueOf(Double.parseDouble(yunZbNewInvoiceApplyDTO.getTotalAmount())));
            rate = dto.getTaxRate().doubleValue();
            taxAmount = dto.getTaxAmount().doubleValue();
            totalAmount = dto.getAmount().doubleValue();
            contentCode = yunZbNewInvoiceApplyDTO.getContentCode();
            contentName = yunZbNewInvoiceApplyDTO.getApplyRemark();
            //更新记录表的开票状态
            yunZbNewInvoiceApplyDTO.getBillsId().forEach(one->{
                RechargeRecord rechargeRecord = rechargeRecordMapper.getRechargeRecordBySerialNum(one);
                if(null == rechargeRecord){
                    return;
                }
                rechargeRecord.setInvoiceStatus(UserBaseConstants.INVOICED);
                rechargeRecord.setUpdateTime(DateUtil.date());
                rechargeRecordMapper.updateByPrimaryKey(rechargeRecord);
            });
        }

        invoiceApply.setInvoiceSerialNum(invoiceSerialNum);
        invoiceApplyMapper.updateByPrimaryKeySelective(invoiceApply);

        InvoiceApplyDetail detail = new InvoiceApplyDetail();
//        detail.setRechargeRecord(recordsId);
        detail.setInvoiceApplyId(invoiceApply.getId());
        detail.setDelStatus(DelStatusEnum.NORMAL.code);
        detail.setCreateTime(DateUtil.date());
        invoiceApplyDetailMapper.insertUseGeneratedKeys(detail);

        InvoiceApplyAmount invoiceApplyAmount = new InvoiceApplyAmount();
        invoiceApplyAmount.setInvoiceDetailId(detail.getId());
        invoiceApplyAmount.setTaxRate(BigDecimal.valueOf(rate));
        invoiceApplyAmount.setTaxAmount(BigDecimal.valueOf(taxAmount));
        invoiceApplyAmount.setInvoiceSerialName(contentName);
        invoiceApplyAmount.setInvoiceAmount(BigDecimal.valueOf(totalAmount));
        invoiceApplyAmount.setInvoiceId(invoiceApply.getId());
        invoiceApplyAmount.setCreateTime(DateUtil.date());
        invoiceApplyAmountMapper.insertSelective(invoiceApplyAmount);

    }

    private String doYunZBInvoice(YunZbNewInvoiceApplyDTO yunZbNewInvoiceApplyDTO) throws Exception {

        YunZBInvoiceApplyDTO yunZBInvoiceApplyDTO = new YunZBInvoiceApplyDTO();
        //查询云众包平台商户信息
        PaymentMerchantInfo paymentMerchantInfo =
                paymentMerchantInfoMapper.selectByMerchantIdAndChannel(yunZbNewInvoiceApplyDTO.getMerchantId(),
                        PaymentChannelEnum.YUNZB.name());
        //查询税源地公司信息
        TaxSounrceCompany taxSounrceCompany =
                taxSounrceCompanyMapper.selectByPrimaryKey(paymentMerchantInfo.getTaxSourceId());
        yunZBInvoiceApplyDTO.setMchId(taxSounrceCompany.getMerchantNo());
        yunZBInvoiceApplyDTO.setChannelNo(queryChannels(taxSounrceCompany.getMerchantNo(),
                taxSounrceCompany.getSecretKey()));
        yunZBInvoiceApplyDTO.setContentCode(yunZbNewInvoiceApplyDTO.getContentCode());
        yunZBInvoiceApplyDTO.setInvoiceAmtType(yunZbNewInvoiceApplyDTO.getInvoiceAmtType());
        yunZBInvoiceApplyDTO.setApplyRemark(yunZbNewInvoiceApplyDTO.getApplyRemark());
        yunZBInvoiceApplyDTO.setInvoiceRemark(yunZbNewInvoiceApplyDTO.getInvoiceRemark());
        yunZBInvoiceApplyDTO.setTotalAmt(yunZbNewInvoiceApplyDTO.getTotalAmount());
        yunZBInvoiceApplyDTO.setSubMchId(paymentMerchantInfo.getMerchantNo());
        // 待签名参数
        String pairsWithKey = RequestUtil.getPairs(yunZBInvoiceApplyDTO) + "key=" + taxSounrceCompany.getSecretKey();
        // 生成参数签名
        String sign = RequestUtil.getSign(pairsWithKey);
        yunZBInvoiceApplyDTO.setBillIds(yunZbNewInvoiceApplyDTO.getBillsId());
        // 设置请求签名值
        BaseRequest baseRequest = (BaseRequest) yunZBInvoiceApplyDTO;
        baseRequest.setSign(sign);
        logger.info("请求云众包开票参数：{}", JSONObject.fromObject(yunZBInvoiceApplyDTO));
        // 请求平台接口
        String respData = RequestUtil.httpPost(YunZBConstants.serviceUrl + "/apply_invoice", yunZBInvoiceApplyDTO);
        logger.info("请求云众包开票返回参数：{}", respData);
        return respData;
    }

}
