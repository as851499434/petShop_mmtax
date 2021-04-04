package com.mmtax.business.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author：YH
 * @Date：2020/12/10 17:23
 */
@Data
public class ListWorOrderResultDTO {

    private String workOrderSerialNumber;

    private String name;

    private String certificateNo;

    private String mobile;

    private String taxSourceCompanyName;

    private String title;

    private Integer status;

    private Date publishTime;

    private Date feedbackTime;

}
