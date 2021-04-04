package com.mmtax.business.mapper;

import com.mmtax.business.domain.InvoiceRecord;
import com.mmtax.business.dto.InvoiceApplicationDetailDTO;
import com.mmtax.business.dto.InvoiceApplyRecordDTO;
import com.mmtax.business.dto.RefundInvoiceDTO;
import com.mmtax.common.utils.MyMapper;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 发票记录 数据层
 * 
 * @author meimiao
 * @date 2020-08-26
 */
public interface InvoiceRecordMapper extends MyMapper<InvoiceRecord>
{

    /* 根据发票流水号查询 发票id
     */
    int queryIdByInvoiceSerialum(@Param("invoiceSerialum") String invoiceSerialum);

    int updateReturnInvoice(RefundInvoiceDTO refundInvoiceDTO);

    /**
     * 更新发票状态
     * @param merchantId     商户ID
     * @param invoiceSerialNum 发票申请编号
     * @param invoiceStatus     发票状态
     * @return
     */
    int updateInvoiceStatus(@Param("merchantId") Integer merchantId,
                            @Param("invoiceSerialNum") String invoiceSerialNum,
                            @Param("invoiceStatus") Integer invoiceStatus,
                            @Param("updateTime") Date updateTime);

    /**
     * 根据发票申请编号查询 发票id
     * @param merchantId
     * @param invoiceSerialNum
     * @return
     */
    int selectIDByinvoiceSerialNum(@Param("merchantId") Integer merchantId,
                                  @Param("invoiceSerialNum") String invoiceSerialNum);

    /**
     * 根据发票申请编号 获取 发票记录
     * @param invoiceSerialNum 发票申请编号
     * @return
     */
   InvoiceApplicationDetailDTO queryInvoiceRecordByInvoiceSerialNum(@Param("merchantId") Integer merchantId,
                                                                   @Param("invoiceSerialNum") String invoiceSerialNum);


    /**
     * 查询发票记录
     * @param invoiceApplyRecordDTO
     * @return
     */
    List<InvoiceRecord> queryInvoiceApplyRecords(InvoiceApplyRecordDTO invoiceApplyRecordDTO);


    int countInvoiceApplyRecords(InvoiceApplyRecordDTO invoiceApplyRecordDTO);


}