package com.mmtax.business.service;

import com.mmtax.business.dto.MerchantFactorCertificationDTO;
import com.mmtax.common.page.Page;

import java.util.List;

/**
 * 要素认证 服务层
 *
 * @author meimiao
 * @date 2019-08-08
 */
public interface IFactorCertificationService {

    /**
     * 获取商户的要素认证列表
     *
     * @param merchantId        商户id
     * @param pageSize          每页大小
     * @param currentPage       页码
     * @param name              姓名
     * @param idCardNo          身份证号码
     * @param bankCardNo        银行卡号
     * @param merchantSerialNum 商户订单号
     * @param orderSerialNum    平台订单号
     * @param status            要素认证
     * @param startDate         开始时间
     * @param endDate           结束时间
     * @param endDate           结束时间
     * @return result
     */
    Page getPageMerchantFactorCertification(Integer merchantId, Integer pageSize, Integer currentPage, String name,
                                            String idCardNo, String bankCardNo, String merchantSerialNum,
                                            String orderSerialNum, Integer status, String startDate, String endDate,
                                            Integer factorOrderId);

    /**
     * 导出商户的要素认证列表
     *
     * @param merchantId        商户id
     * @param name              姓名
     * @param idCardNo          身份证号码
     * @param bankCardNo        银行卡号
     * @param merchantSerialNum 商户订单号
     * @param orderSerialNum    平台订单号
     * @param status            要素认证
     * @param startDate         开始时间
     * @param endDate           结束时间
     * @param factorOrderId     要素认证订单id
     * @return result
     */
    List<MerchantFactorCertificationDTO> exportMerchantList(Integer merchantId, String name, String idCardNo,
                                                            String bankCardNo, String merchantSerialNum,
                                                            String orderSerialNum, Integer status, String startDate,
                                                            String endDate, Integer factorOrderId);
}
