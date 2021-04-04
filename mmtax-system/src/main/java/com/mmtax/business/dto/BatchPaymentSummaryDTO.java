package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;

import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/10 16:54
 */
public class BatchPaymentSummaryDTO {

    @Excel(name = "总笔数")
    private Integer count;
    @Excel(name = "总金额/元(以显示金额汇总)")
    private Double amount;
    @Excel(name = "批次号(与文件名称一致)")
    private String batchNo;
    @Excel(name = "派单批次号")
    private String taskRecordBatchNo;

    private List<PaymentInfoDTO> list;

    public List<PaymentInfoDTO> getList() {
        return list;
    }

    public void setList(List<PaymentInfoDTO> list) {
        this.list = list;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getTaskRecordBatchNo() {
        return taskRecordBatchNo;
    }

    public void setTaskRecordBatchNo(String taskRecordBatchNo) {
        this.taskRecordBatchNo = taskRecordBatchNo;
    }
}
