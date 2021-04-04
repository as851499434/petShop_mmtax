package com.mmtax.business.mapper;

import com.mmtax.business.domain.SinglePayment;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * 单人每月打款限制 数据层
 *
 * @author meimiao
 * @date 2019-10-18
 */
public interface SinglePaymentMapper extends MyMapper<SinglePayment> {
    /**
     * 根据身份证号查询当月已打款金额
     *
     * @param idCardNo
     * @return
     */
    SinglePayment getSinglePaymentByIdCard(@Param("idCardNo") String idCardNo);

    /**
     * 更新每月打款金额
     *
     * @param idCardNo
     * @param amount
     * @return
     */
    int updateByIdCard(@Param("idCardNo") String idCardNo, @Param("amount") BigDecimal amount);
}