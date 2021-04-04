package com.mmtax.business.dto;

import lombok.Data;

/**
 * @Author：YH
 * @Date：2020/12/11 11:34
 */
@Data
public class WorkOrderRecordResultDTO {

    private String workOrderSerialNumber;

    private String publishTime;

    private String title;

    private Integer status;

}
