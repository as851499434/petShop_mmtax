package com.mmtax.business.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.AddMerchantInfoDTO;
import com.mmtax.business.dto.NotifyDTO;
import com.mmtax.business.mapper.MerchantKeyMapper;
import com.mmtax.business.mapper.OnlinePaymentInfoMapper;
import com.mmtax.business.mapper.PaymentMerchantInfoMapper;
import com.mmtax.business.mapper.TaxSounrceCompanyMapper;
import com.mmtax.business.service.INotifySendAgainService;
import com.mmtax.business.yunzbdto.*;
import com.mmtax.common.enums.NotifyStatusEnum;
import com.mmtax.common.enums.PaymentChannelEnum;
import com.mmtax.common.enums.TrxOrderStatusEnum;
import com.mmtax.common.page.QueryPage;
import com.mmtax.common.utils.Base64Util;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.http.HttpUtils;
import com.mmtax.common.utils.yunzbutil.Response;
import com.mmtax.common.utils.yunzbutil.YunZBConstants;
import com.mmtax.common.utils.yunzbutil.YunZBUtil;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/12 15:10
 */
public class CommonServiceImpl {
    /**
     * logger 子类可以直接调用，注意，不要再在子类中再定义logger，否则会引起变量混乱
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Resource
    PaymentMerchantInfoMapper paymentMerchantInfoMapper;

    @Resource
    TaxSounrceCompanyMapper taxSounrceCompanyMapper;

    @Resource
    OnlinePaymentInfoMapper onlinePaymentInfoMapper;
    @Autowired
    private INotifySendAgainService notifySendAgainService;

    /**
     * 生成分页查询基础参数类
     *
     * @param currentPage
     * @param pageSize
     * @return
     */
    protected QueryPage convertQueryPage(Integer currentPage, Integer pageSize) {
        QueryPage queryPage = new QueryPage();
        if (currentPage != null) {
            queryPage.setCurrentPage(currentPage);
        }
        if (pageSize != null) {
            queryPage.setPageSize(pageSize);
        }
        return queryPage;
    }

    protected Integer getStartIndex(Integer currentPage, Integer pageSize) {
        QueryPage queryPage = convertQueryPage(currentPage, pageSize);
        return queryPage.getStartIndex();
    }

    /**
     * 查询渠道号
     *
     * @param merchantNo 渠道商户编号
     * @return
     * @throws Exception
     */
    protected String queryChannels(String merchantNo, String secretKey) {
        String result = "10000017";
        try {
            // todo ljd 此前方法待优化
//            YunZBQueryChannelDTO queryChannel = new YunZBQueryChannelDTO();
//            queryChannel.setMchId(merchantNo);
//            Response response = YunZBUtil.remoteInvoke(YunZBConstants.CHANNEL, queryChannel, secretKey);
//            JSONObject jsonObject = JSONObject.parseObject(response.getRespData());
//            result = jsonObject.getJSONArray("channels").getJSONObject(0).getString("channel_no");
        } catch (Exception e) {
            logger.error("查询渠道号异常", e);
        }
        return result;
    }

    /**
     * 添加云众包渠道商户信息
     *
     * @param addMerchantInfoDTO 添加商户信息
     * @return
     */
    protected YunZBNewMerchantInfoDTO convertYunZBMerchantInfoDTO(AddMerchantInfoDTO addMerchantInfoDTO) {
        YunZBNewMerchantInfoDTO yunZBMerchantInfoDTO = new YunZBNewMerchantInfoDTO();
        yunZBMerchantInfoDTO.setAddress(addMerchantInfoDTO.getMerchantInfo().getMerchantAddress());
        yunZBMerchantInfoDTO.setAddressAndTel(addMerchantInfoDTO.getInvoiceInfo().getCompanyAddress()
                + " " + addMerchantInfoDTO.getInvoiceInfo().getInvoiceMobile());
        yunZBMerchantInfoDTO.setBasicBankCardNo(addMerchantInfoDTO.getSettlementInfo().getAccountNo());
        yunZBMerchantInfoDTO.setBasicBankCode(addMerchantInfoDTO.getSettlementInfo().getBankCode());
        yunZBMerchantInfoDTO.setBasicBankNo(addMerchantInfoDTO.getSettlementInfo().getSettBankcardNo());
        yunZBMerchantInfoDTO.setContactEmail(addMerchantInfoDTO.getMerchantInfo().getEmail());
        yunZBMerchantInfoDTO.setContactMobile(addMerchantInfoDTO.getMerchantInfo().getContactsMobile());
        yunZBMerchantInfoDTO.setContactName(addMerchantInfoDTO.getMerchantInfo().getContacts());
        yunZBMerchantInfoDTO.setCorpName(addMerchantInfoDTO.getMerchantInfo().getLegalPersonName());
        yunZBMerchantInfoDTO.setCorpIdCardExp(addMerchantInfoDTO.getMerchantInfo().getLegalPersonIdCardExp().replace(
                "-", ""));
        yunZBMerchantInfoDTO.setCorpIdCardNo(addMerchantInfoDTO.getMerchantInfo().getLegalPersonIdCardNo());
        yunZBMerchantInfoDTO.setCorpMobile(addMerchantInfoDTO.getMerchantInfo().getLegalPersonMobile());
        yunZBMerchantInfoDTO.setIndustry(addMerchantInfoDTO.getMerchantInfo().getIndustry());
        yunZBMerchantInfoDTO.setLicenceExp(addMerchantInfoDTO.getMerchantInfo().getBusinessLicenseExp().replace(
                "-", ""));
        yunZBMerchantInfoDTO.setLicenceNumber(addMerchantInfoDTO.getMerchantInfo().getBusinessLicenseCode());
        yunZBMerchantInfoDTO.setMerchantName(addMerchantInfoDTO.getMerchantInfo().getCompanyName());
        yunZBMerchantInfoDTO.setMerchantShortName(addMerchantInfoDTO.getMerchantInfo().getMerchantName());
        yunZBMerchantInfoDTO.setNonceStr(YunZBConstants.UUID);
        yunZBMerchantInfoDTO.setSettBankcardNo(addMerchantInfoDTO.getSettlementInfo().getAccountNo());
        yunZBMerchantInfoDTO.setReqTime(DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_PATTERN));
        yunZBMerchantInfoDTO.setSettBankCode(addMerchantInfoDTO.getSettlementInfo().getBankCode());
        yunZBMerchantInfoDTO.setSettBankName(addMerchantInfoDTO.getSettlementInfo().getBankName());
        yunZBMerchantInfoDTO.setSettBankNameAndAccount(addMerchantInfoDTO.getInvoiceInfo().getBankNumber());
        yunZBMerchantInfoDTO.setTaxpayerType(String.valueOf(addMerchantInfoDTO.getMerchantInfo().getTaxpayerType() + 1));
        yunZBMerchantInfoDTO.setSettBankcardName(addMerchantInfoDTO.getSettlementInfo().getAccountName());
        yunZBMerchantInfoDTO.setSettBankcardAccount(addMerchantInfoDTO.getSettlementInfo().getSettBankcardNo());
        return yunZBMerchantInfoDTO;
    }

    /**
     * 上传照片到云众包
     *
     * @param addMerchantInfoDTO  商户信息
     * @param paymentMerchantInfo 平台商户信息
     * @param taxSounrceCompany   税源地公司信息
     * @return
     * @throws Exception
     */
    protected com.alibaba.fastjson.JSONObject uploadYunZBImg(AddMerchantInfoDTO addMerchantInfoDTO,
                                                             PaymentMerchantInfo paymentMerchantInfo,
                                                             TaxSounrceCompany taxSounrceCompany) throws Exception {
        //上传图片信息到云众包渠道
        YunZBPicInfoDTO yunZBPicInfoDTO = new YunZBPicInfoDTO();
        yunZBPicInfoDTO.setIdCardBack(Base64Util.getImgStrToBase64(addMerchantInfoDTO.getMerchantInfo().
                getContactsIdCardBack()));
        yunZBPicInfoDTO.setIdCardFront(Base64Util.getImgStrToBase64(addMerchantInfoDTO.getMerchantInfo().
                getContactsIdCardFront()));
        yunZBPicInfoDTO.setLicence(Base64Util.getImgStrToBase64(addMerchantInfoDTO.getMerchantInfo().
                getBusinessLicenseImg()));
        yunZBPicInfoDTO.setSubMchId(paymentMerchantInfo.getMerchantNo());
        yunZBPicInfoDTO.setMchId(taxSounrceCompany.getMerchantNo());
        yunZBPicInfoDTO.setReqTime(DateUtil.format(DateUtil.date(), DatePattern.PURE_DATETIME_PATTERN));
        yunZBPicInfoDTO.setNonceStr(YunZBConstants.UUID);
        Response response = YunZBUtil.remoteInvoke(YunZBConstants.UPLOAD_PIC, yunZBPicInfoDTO,
                taxSounrceCompany.getSecretKey());
        com.alibaba.fastjson.JSONObject tokenResult = com.alibaba.fastjson.JSONObject.parseObject(response.getRespData());
        return tokenResult;
    }

    /**
     * 获取发票内容
     *
     * @param merchantId 商户id
     * @return
     * @throws Exception
     */
    protected Response queryInvoiceContent(Integer merchantId) throws Exception {
        PaymentMerchantInfo paymentMerchantInfo = paymentMerchantInfoMapper.selectByMerchantIdAndChannel(merchantId,
                PaymentChannelEnum.YUNZB.name());
        if(null == paymentMerchantInfo){
            return null;
        }
        TaxSounrceCompany taxSounrceCompany =
                taxSounrceCompanyMapper.selectByPrimaryKey(paymentMerchantInfo.getTaxSourceId());
        YunZBInvoiceContentDTO yunZBInvoiceContentDTO = new YunZBInvoiceContentDTO();
        yunZBInvoiceContentDTO.setSubMchId(paymentMerchantInfo.getMerchantNo());
        yunZBInvoiceContentDTO.setMchId(taxSounrceCompany.getMerchantNo());
        Response response = YunZBUtil.remoteInvoke(YunZBConstants.INVOICE_CONTENT, yunZBInvoiceContentDTO,
                taxSounrceCompany.getSecretKey());
        return response;
    }

    /**
     * 个人用户签约
     *
     * @param cardName            名字
     * @param cardNo              卡号
     * @param idCardNo            身份证号
     * @param mobileNo            手机号
     * @param paymentMerchantInfo 商户信息
     * @param taxSounrceCompany   税源地公司信息
     * @return
     * @throws Exception
     */
    protected String querySignedResult(String cardName, String cardNo, String idCardNo, String mobileNo,
                                       PaymentMerchantInfo paymentMerchantInfo, TaxSounrceCompany taxSounrceCompany) throws Exception {
        String result = null;
        YunZBSignDTO yunZBSignDTO = new YunZBSignDTO();
        yunZBSignDTO.setCardName(cardName);
        yunZBSignDTO.setCardNo(cardNo);
        yunZBSignDTO.setIdCardNo(idCardNo);
        yunZBSignDTO.setMobileNo(mobileNo);
        yunZBSignDTO.setMchId(taxSounrceCompany.getMerchantNo());
        yunZBSignDTO.setSubMchId(paymentMerchantInfo.getMerchantNo());
        Response response = YunZBUtil.remoteInvoke(YunZBConstants.SIGNED, yunZBSignDTO,
                taxSounrceCompany.getSecretKey());
        JSONObject payInfo = JSONObject.parseObject(response.getRespData());
        if (!YunZBConstants.RESULT_CODE.equals(payInfo.get(YunZBConstants.REQUEST_CODE))) {
            result = payInfo.getString(YunZBConstants.REQUEST_MSG);
        }
        if (!YunZBConstants.RESULT_CODE.equals(payInfo.get(YunZBConstants.RETURN_CODE))) {
            result = payInfo.getString(YunZBConstants.RETURN_MSG);
        }
        return result;
    }

    /**
     * 异步回调参数赋值
     *
     * @param trxOrder     订单信息
     * @param merchantInfo 商户信息
     * @return
     */
    protected NotifyDTO convertNotifyDTO(TrxOrder trxOrder, MerchantInfo merchantInfo) {
        NotifyDTO notifyDTO = new NotifyDTO();
        notifyDTO.setNotifyType("1");
        notifyDTO.setAmount(trxOrder.getAmount().toString());
        notifyDTO.setTradeNo(trxOrder.getMerchantSerialNum());
        notifyDTO.setSettleTime(DateUtil.now());
        notifyDTO.setMerchantNo(merchantInfo.getMerchantCode());
        //订单成功赋值
        Integer orderStatus = trxOrder.getOrderStatus();
        if (TrxOrderStatusEnum.PAID.code.equals(orderStatus)) {
            notifyDTO.setCode(NotifyStatusEnum.SUCCESS.name());
            notifyDTO.setMessage(NotifyStatusEnum.SUCCESS.name());
        } else if(TrxOrderStatusEnum.ORDER_FAIL.code.equals(orderStatus)
                || TrxOrderStatusEnum.PAID_PENDING.code.equals(orderStatus)){
            notifyDTO.setCode(NotifyStatusEnum.FAIL.name());
            notifyDTO.setMessage(trxOrder.getComment());
        }
        return notifyDTO;
    }

    /**
     * 判断系统是否有存异步回调通知地址，若有，则发送异步回调通知
     *
     * @param trxOrder          订单信息
     * @param merchantInfo      商户信息
     * @param merchantKeyMapper 地址信息
     */
    protected void callBackNotify(TrxOrder trxOrder, MerchantInfo merchantInfo, MerchantKeyMapper merchantKeyMapper) {
        logger.info("我方系统开始发送异步通知");
        MerchantKey merchantKey = new MerchantKey();
        merchantKey.setMerchantId(merchantInfo.getId());
        MerchantKey rMerchantKey = merchantKeyMapper.selectOne(merchantKey);
        if (StringUtils.isNotEmpty(rMerchantKey.getCallBackAddress())) {
            if (!TrxOrderStatusEnum.PAID.code.equals(trxOrder.getOrderStatus())
                    && !TrxOrderStatusEnum.ORDER_FAIL.code.equals(trxOrder.getOrderStatus())
                    && !TrxOrderStatusEnum.PAID_PENDING.code.equals(trxOrder.getOrderStatus())) {
                logger.info("订单{}不是终态，不推送异步通知，返回",trxOrder.getOrderSerialNum());
                return;
            }
            //异步回调通知参数组装
            NotifyDTO notifyDTO = convertNotifyDTO(trxOrder, merchantInfo);
            logger.info("组装完成的回调参数：{}", JSON.toJSONString(notifyDTO));
            String callBackResult = HttpUtils.sendPost(rMerchantKey.getCallBackAddress(), JSONObject.toJSONString(notifyDTO));
            NotifySendAgain notifySendAgain = new NotifySendAgain();
            notifySendAgain.setFailNum(0);
            notifySendAgain.setNotifyStatus(1);
            if (StringUtils.isEmpty(callBackResult) || !NotifyStatusEnum.SUCCESS.name().equals(callBackResult)) {
                logger.info("发送失败,记录数据库，callBackResult={}",callBackResult);
                //发送失败记录数据库
                notifySendAgain.setNotifyStatus(2);
                notifySendAgain.setFailNum(1);
//                retryNotify(rMerchantKey.getCallBackAddress(), notifyDTO, 0);
            }
            notifySendAgain.setMerchantId(merchantInfo.getId());
            notifySendAgain.setNotifyType(1);
            notifySendAgain.setSendMethod(1);
            notifySendAgain.setNotifyContent(JSON.toJSONString(notifyDTO));
            notifySendAgainService.insertNotifyLog(notifySendAgain);
        }
    }

    /**
     * 发送异步回调通知
     *
     * @param url       回调地址
     * @param notifyDTO 参数
     * @param i         失败重试的次数
     * @throws InterruptedException
     */
    protected void retryNotify(String url, NotifyDTO notifyDTO, int i) throws InterruptedException {
        String callBackResult = HttpUtils.sendPost(url, JSONObject.toJSONString(notifyDTO));
        if (StringUtils.isEmpty(callBackResult) || !NotifyStatusEnum.SUCCESS.name().equals(callBackResult)) {
            //重试时间默认为5分钟
            long time = 300000L;
            int count = (int) Math.pow(2, i);
            Thread.sleep(time * count);
            if (5 > i) {
                retryNotify(url, notifyDTO, ++i);
            }
        }
    }

}
