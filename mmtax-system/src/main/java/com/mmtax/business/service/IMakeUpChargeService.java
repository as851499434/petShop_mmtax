package com.mmtax.business.service;

import cn.hutool.core.date.DateTime;
import com.mmtax.business.domain.Cooperation;
import com.mmtax.business.domain.MakeUpCharge;
import com.mmtax.business.domain.MakeUpChargeDetail;
import com.mmtax.business.domain.TrxOrder;
import com.mmtax.business.dto.PaymentInfoDTO;
import com.mmtax.common.enums.InOutDeductionEnum;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 补交服务费总记录 服务层
 * 
 * @author meimiao
 * @date 2020-08-19
 */
public interface IMakeUpChargeService
{
    /**
     * 计算获取补交服务费记录和详细
     */
    PaymentInfoDTO calculateMakeUpCharge(PaymentInfoDTO dto, Integer merchantId, Cooperation cooperation
            , Map<String, BigDecimal> idCardNoAmount, Set<String> hasMakeUpRecord);

    /**
     * 计算服务费(内扣外扣)
     * @param mouldAmount 上传模板中的金额
     * @param rate
     * @param type
     * @return
     */
    BigDecimal calculateTax(BigDecimal mouldAmount, BigDecimal rate);

    /**
     * 计算上传模板金额
     * @param settleAmount 打款金额
     * @param rate 服务费率
     * @param type
     * @return
     */
    BigDecimal calculateMouldAmount(BigDecimal settleAmount, BigDecimal rate, InOutDeductionEnum type);

    /**
     * 计算打款金额
     * @param mouldAmount 上传模板金额
     * @param rate
     * @param type
     * @return
     */
    BigDecimal calculateSettleAmount(BigDecimal mouldAmount, BigDecimal rate, InOutDeductionEnum type);

    /**
     * 补交服务费记录和详细入库
     * @param makeUpCharge
     * @param makeUpChargeDetails
     * @param trxOrder
     * @return
     */
    MakeUpCharge addRecords(MakeUpCharge makeUpCharge, List<MakeUpChargeDetail> makeUpChargeDetails
            ,TrxOrder trxOrder);

    /**
     * 收取补交服务费
     * @param orderSerialSum
     */
    void payMakeUpCharge(String orderSerialSum,BigDecimal bigRate);

    /**
     * 使补交服务费记录失效
     * @param orderSerialSum
     */
    void invalidMakeUpCharge(String orderSerialSum);

    /**
     * 判断时间段内成功打款的总额是否超过某额度
     * @param customerId
     * @param startTime
     * @param endTime
     * @return
     */
    boolean calculateAllPaymentInTime(Integer customerId,Integer merchantId, String startTime
            , String endTime,BigDecimal judgeAmount);
}
