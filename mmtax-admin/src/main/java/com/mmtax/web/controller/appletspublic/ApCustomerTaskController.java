package com.mmtax.web.controller.appletspublic;

import com.mmtax.business.dto.CustomerTaskDTO;
import com.mmtax.business.dto.CustomerTaskDetailDTO;
import com.mmtax.business.dto.CustomerTaskResultDTO;
import com.mmtax.business.service.ICustomerTaskService;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mmtax.common.core.domain.AjaxResult.error;

/**
 * @Author：YH
 * @Date：2020/10/16 9:38
 */

@Api(tags = "小程序和公众号派单任务")
@Controller
@RequestMapping("/appletspublic/customerTask")
@Slf4j
public class ApCustomerTaskController {

    @Autowired
    ICustomerTaskService customerTaskService;

    /**
     *  模糊搜索派单任务
     * @param dto
     * @return
     */
    @ApiOperation(value = "查询任务")
    @GetMapping("/listTask")
    @ResponseBody
    public AjaxResult listTask(CustomerTaskDTO dto){
        log.info("/appletspublic/customerTask/listTask，入参：dto={}", dto);
        List<CustomerTaskResultDTO> customerTaskResultDTOS;
        try {
            customerTaskResultDTOS =  customerTaskService.listTask(dto);
        } catch (BusinessException b) {
            log.info("/appletspublic/customerTask/listTask", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.error("/appletspublic/customerTask/listTask", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(customerTaskResultDTOS);
    }

    /**
     *  查询 派单任务 细节
     * @param customerTaskId tbl_batch_task_detail 表中id
     * @return
     */
    @ApiOperation(value = "查询任务详情")
    @GetMapping("/queryTaskDetail")
    @ResponseBody
    public AjaxResult queryTaskDetail(Integer customerTaskId){
        log.info("/appletspublic/customerTask/queryTaskDetail，入参：customerTaskId={}", customerTaskId);
        CustomerTaskDetailDTO customerTaskDetailDTO;
        try {
            customerTaskDetailDTO =  customerTaskService.queryTaskDetail(customerTaskId);
        } catch (BusinessException b) {
            log.info("/appletspublic/customerTask/queryTaskDetail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.error("/appletspublic/customerTask/queryTaskDetail", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(customerTaskDetailDTO);
    }

    /**
     *   确定完成任务
     * @param customerTaskId tbl_batch_task_detail 表中id
     * @return
     */
    @ApiOperation(value = "确定完成任务")
    @PostMapping("/updateTaskStatusComplete")
    @ResponseBody
    public AjaxResult updateTaskStatusComplete(Integer customerTaskId){
        log.info("/appletspublic/customerTask/updateTaskStatusComplete，入参：customerTaskId={}", customerTaskId);

        try {
            customerTaskService.updateTaskStatusComplete(customerTaskId);
        } catch (BusinessException b) {
            log.info("/appletspublic/customerTask/updateTaskStatusComplete", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.error("/appletspublic/customerTask/updateTaskStatusComplete", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }



    /**
     *   判断该身份证号是否下载过税税通
     * @param certificateNo
     * @return
     */
    @ApiOperation(value = "判断该身份证号是否下载过税税通，true:没下载过,false:已经下载过")
    @GetMapping("/isDownLoad")
    @ResponseBody
    public AjaxResult isDownLoad(String certificateNo){
        log.info("/appletspublic/customerTask/isDownLoad，入参：certificateNo={}", certificateNo);
        Boolean bool;
        try {
            bool = customerTaskService.isDownLoad(certificateNo);
        } catch (BusinessException b) {
            log.info("/appletspublic/customerTask/isDownLoad", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.error("/appletspublic/customerTask/isDownLoad", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(bool);
    }


}
