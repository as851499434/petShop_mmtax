package com.mmtax.business.mapper;

import com.mmtax.business.domain.FactorCertification;
import com.mmtax.business.dto.MerchantFactorCertificationDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 要素认证 数据层
 *
 * @author meimiao
 * @date 2019-08-08
 */
public interface FactorCertificationMapper extends MyMapper<FactorCertification> {
    /**
     * 获取商户的要素认证列表
     *
     * @param merchantId        商户id
     * @param pageSize          每页大小
     * @param startIndex        起始位置
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
    List<MerchantFactorCertificationDTO> getPageMerchantFactorCertification(@Param("merchantId") Integer merchantId,
                                                                            @Param("pageSize") Integer pageSize,
                                                                            @Param("startIndex") Integer startIndex,
                                                                            @Param("name") String name,
                                                                            @Param("idCardNo") String idCardNo,
                                                                            @Param("bankCardNo") String bankCardNo,
                                                                            @Param("merchantSerialNum")
                                                                                    String merchantSerialNum,
                                                                            @Param("orderSerialNum")
                                                                                    String orderSerialNum,
                                                                            @Param("status") Integer status,
                                                                            @Param("startDate") String startDate,
                                                                            @Param("endDate") String endDate,
                                                                            @Param("factorOrderId")
                                                                                    Integer factorOrderId);

    /**
     * 获取商户的要素认证总记录数
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
    int getCountMerchantFactorCertification(@Param("merchantId") Integer merchantId,
                                            @Param("name") String name,
                                            @Param("idCardNo") String idCardNo,
                                            @Param("bankCardNo") String bankCardNo,
                                            @Param("merchantSerialNum")
                                                    String merchantSerialNum,
                                            @Param("orderSerialNum")
                                                    String orderSerialNum,
                                            @Param("status") Integer status,
                                            @Param("startDate") String startDate,
                                            @Param("endDate") String endDate,
                                            @Param("factorOrderId")
                                                    Integer factorOrderId);
}