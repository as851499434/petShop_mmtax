package com.mmtax.business.mapper;

import com.mmtax.business.domain.RechargeRecord;
import com.mmtax.business.domain.TaxSounrceCompany;
import com.mmtax.business.dto.*;
import com.mmtax.business.yunzbdto.YunZBInvoiceBillResultDTO;
import com.mmtax.common.utils.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 充值记录 数据层
 *
 * @author meimiao
 * @date 2019-07-09
 */
@Repository
public interface RechargeRecordMapper extends MyMapper<RechargeRecord> {


    /**
     * 发票申请详情-->充值记录详情
     * @param merchantId 商户ID
     * @param invoiceRecordId 发票记录ID
     * @return
     */
     List<RechargeDetialDTO> selectRechargeRecords(@Param("merchantId")Integer merchantId,
                                                   @Param("invoiceRecordId")Integer invoiceRecordId);

    /**
     * 根据充值流水号 查询id
     * @param orderSerialNum
     * @return
     */
    int selectIdByOrderSerialNum(@Param("orderSerialNum") String orderSerialNum);

    /**
     *  根据发票申请编号更新发票状态
     * @param invoiceStatus 发票状态
     * @param orderSerialNum 发票申请编号
     * @return
     */
    int updateInvoiceStatusByOrderSerialNum(@Param("invoiceStatus")Integer invoiceStatus,
                                            @Param("orderSerialNum") String orderSerialNum,
                                            @Param("updateTime") Date updateTime);

    /**
     *
     * @param merchantId
     * @return
     */
    TaxSounrceCompany queryTaxSourceInfo(@Param("merchantId") Integer merchantId);





    Integer countInvoiceBills(InvoiceBillDTO invoiceBillDTO);




    List<YunZBInvoiceBillResultDTO> queryInvoiceBills(InvoiceBillDTO invoiceBillDTO);

    BigDecimal queryInvoiceBillsAmount(InvoiceBillDTO invoiceBillDTO);
    /**
     * 查询充值记录表信息
     */
    List<RechargeRecord> selectTheList(@Param("merchantId") Integer merchantId,
                                       @Param("status") String status,
                                       @Param("invoiceStatus") Integer invoiceStatus);
    /**
     * 获取开票流水
     *
     * @param pageSize   每页大小
     * @param startIndex 索引开始
     * @param invoiceId  发票ID
     * @return
     */
    List<RechargeRecord> getRechargeByInvoiceId(@Param("pageSize") Integer pageSize,
                                                @Param("startIndex") Integer startIndex,
                                                @Param("invoiceId") Integer invoiceId);

    /**
     * 获取申请开票总额
     *
     * @param invoiceId
     * @return
     */
    BigDecimal getTotalRechargeAmount(@Param("invoiceId") Integer invoiceId);


    /**
     * 获取所有开票流水
     *
     * @param invoiceId 发票ID
     * @return
     */
    List<RechargeRecord> getAllRechargeByInvoiceId(@Param("invoiceId") Integer invoiceId);


    /**
     * 获取关联发票
     *
     * @param invoiceId
     * @return
     */
    List<Integer> getAllRelationInvoiceIdByInvoiceId(Integer invoiceId);


    /**
     * 计数流水
     *
     * @param invoiceId 发票ID
     * @return
     */
    int countRechargeByInvoiceId(@Param("invoiceId") Integer invoiceId);


    /**
     * 获取商户充值记录
     *
     * @param startDate       查询起始时间
     * @param endDate         查询结束时间
     * @param merchantId      商户id
     * @param rechargeChannel 充值渠道
     * @param rechargeType
     * @param status
     * @param startIndex      查询起始位置
     * @param pageSize        每页大小
     * @return
     */
    List<MerchantRechargeRecordDTO> getListMerchantRechargeRecord(@Param("startDate") String startDate,
                                                                  @Param("endDate") String endDate,
                                                                  @Param("merchantId") Integer merchantId,
                                                                  @Param("rechargeChannel") String rechargeChannel,
                                                                  @Param("rechargeType") String rechargeType,
                                                                  @Param("status") String status,
                                                                  @Param("startIndex") Integer startIndex,
                                                                  @Param("pageSize") Integer pageSize);

    /**
     * 获取商户充值记录数量
     *
     * @param startDate       查询起始时间
     * @param endDate         查询结束时间
     * @param merchantId      商户id
     * @param rechargeChannel 充值渠道
     * @param rechargeType    充值类型
     * @param status
     * @return
     */
    int getCountMerchantRechargeRecord(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                       @Param("merchantId") Integer merchantId,
                                       @Param("rechargeChannel") String rechargeChannel,
                                       @Param("rechargeType") String rechargeType,
                                       @Param("status") String status);

    /**
     * 获取商户充值记录--发票开具
     *
     * @param startDate
     * @param endDate
     * @param merchantId
     * @param startIndex
     * @param pageSize
     * @return
     */
    List<MerchantRechargeRecordInvoiceDTO> getListMerchantRechargeRecordForInvoice(@Param("startDate") String startDate,
                                                                                   @Param("endDate") String endDate,
                                                                                   @Param("merchantId") Integer merchantId,
                                                                                   @Param("startIndex") Integer startIndex,
                                                                                   @Param("pageSize") Integer pageSize);

    /**
     * 获取商户充值记录数量--发票开具
     *
     * @param startDate  查询起始时间
     * @param endDate    查询结束时间
     * @param merchantId 商户id
     * @return
     */
    int getCountMerchantRechargeRecordForInvoice(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                                 @Param("merchantId") Integer merchantId);

    /**
     * 根据订单编号查询充值记录
     *
     * @param orderNo 订单编号
     * @return
     */
    RechargeRecord getRechargeRecordBySerialNum(@Param("orderNo") String orderNo);

    /**
     * 获取充值记录
     *
     * @param managerRechargeDTO 查询参数
     * @return
     */
    List<ManagerRechargeDTO> getRechargeList(ManagerRechargeDTO managerRechargeDTO);
}