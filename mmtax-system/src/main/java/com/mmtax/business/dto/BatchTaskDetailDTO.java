package com.mmtax.business.dto;

import com.mmtax.common.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class BatchTaskDetailDTO {
    /** 任务编号 */
    @Excel(name = "任务编号")
    @ApiModelProperty("任务编号")
    private String taskSerialNum;
    /** 任务名称 */
    @Excel(name = "任务名称")
    @ApiModelProperty("任务名称")
    private String taskName;
    /** 任务数量 */
    @Excel(name = "任务数量")
    @ApiModelProperty("任务数量")
    private Integer taskNum;
    /** 接任务人的姓名 */
    @Excel(name = "完成人")
    @ApiModelProperty("完成人")
    private String name;
    /** 接任务人的联系电话 */
    @Excel(name = "联系电话")
    @ApiModelProperty("联系电话")
    private String mobileNo;
    /** 税源地(承接方) */
    @Excel(name = "承接方")
    @ApiModelProperty("承接方")
    private String taxSourceName;
    /** 派单时间 */
    @Excel(name = "派单时间")
    @ApiModelProperty("派单时间")
    private Date createTime;
    /** 任务完成时间 */
    @Excel(name = "任务完成时间")
    @ApiModelProperty("任务完成时间")
    private String taskCompleteTime;
}
