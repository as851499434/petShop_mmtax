package com.mmtax.web.controller.business;

import com.mmtax.business.dto.ManagerBatchPaymentDetailDTO;
import com.mmtax.business.dto.ManagerBatchPaymentRecordDTO;
import com.mmtax.business.dto.MerchantBatchPaymentAmountDetailDTO;
import com.mmtax.business.dto.MerchantBatchPaymentDetailDTO;
import com.mmtax.business.service.IBatchPaymentRecordService;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.enums.BusinessType;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.framework.util.ShiroUtils;
import com.mmtax.system.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/13 13:32
 */
@Api(tags = "系统后台--批量代付")
@Controller
@RequestMapping("/manager/batchPaymentRecord")
public class ManagerBatchPaymentRecordController extends BaseController {


    private String prefix = "business/batchPaymentRecord";

    @RequiresPermissions("manager:batchPayment:view")
    @GetMapping()
    public String user() {
        return prefix + "/batchPaymentRecord";
    }

    @ApiOperation("批量打款详情跳转")
    @RequiresPermissions("manager:batchPayment:detail")
    @GetMapping("detail")
    public String detail(@ApiParam("记录ID") Integer recordId) {
        return prefix + "/detail";
    }


    @Autowired
    private IBatchPaymentRecordService batchPaymentRecordService;

    @ApiOperation(value = "批量代付代付列表查询", response = ManagerBatchPaymentRecordDTO.class)
    @RequiresPermissions("manager:batchPayment:view")
    @Log(title = "代付记录查询", businessType = BusinessType.SELECT)
    @PostMapping("/getBatchRecord")
    @ResponseBody
    public TableDataInfo getBatchRecord(ManagerBatchPaymentRecordDTO dto) {
        startPage();
        List<ManagerBatchPaymentRecordDTO> list = batchPaymentRecordService.getListRecord(dto);
        return getDataTable(list);
    }

    @ApiOperation(value = "批量代付列表详情", response = MerchantBatchPaymentDetailDTO.class)
    @RequiresPermissions("manager:batchPayment:detail")
    @Log(title = "批量代付列表详情", businessType = BusinessType.SELECT)
    @PostMapping("/getBatchRecordDetail")
    @ResponseBody
    public TableDataInfo getBatchRecordDetail(ManagerBatchPaymentDetailDTO managerBatchPaymentDetailDTO) {
        startPage();
        List<MerchantBatchPaymentDetailDTO> list =
                batchPaymentRecordService.getBatchPaymentDetail(managerBatchPaymentDetailDTO.getRecordId());
        return getDataTable(list);
    }

    @ApiOperation(value = "商户端批量代付记录金额详情", response = MerchantBatchPaymentAmountDetailDTO.class)
    @GetMapping("amountDetail")
    @ResponseBody
    public AjaxResult amountDetail(@ApiParam(value = "商户id", required = true) Integer merchantId,
                                   @ApiParam(value = "记录id", required = true) Integer id) {
        MerchantBatchPaymentAmountDetailDTO detailDTO;
        try {
            detailDTO = batchPaymentRecordService.getAmountDetail(merchantId, id);
        } catch (BusinessException b) {
            logger.info("/manager/batchPaymentRecord/amountDetail", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/manager/batchPaymentRecord/amountDetail", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(detailDTO);
    }
}
