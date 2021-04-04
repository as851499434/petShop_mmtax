package com.mmtax.business.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 批量代付查询DTO
 *
 * @author yuanligang
 * @date 2019/8/1
 */
public class ManagerBatchPaymentDetailDTO {
    @ApiModelProperty("记录ID")
    private Integer recordId;
    @ApiModelProperty("每页大小")
    private Integer pageSize;
    @ApiModelProperty("当前页码")
    private Integer pageNum;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "ManagerBatchPaymentDetailDTO{" +
                "recordId=" + recordId +
                ", pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                '}';
    }
}
