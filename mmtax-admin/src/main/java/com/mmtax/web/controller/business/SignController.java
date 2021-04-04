package com.mmtax.web.controller.business;

import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.EsignTemplate;
import com.mmtax.business.dto.BatchOrderDTO;
import com.mmtax.business.dto.TaxSourceAccountDTO;
import com.mmtax.business.service.IEsignFlowService;
import com.mmtax.business.service.IEsignTaxSourceService;
import com.mmtax.business.service.IEsignTemplateService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "签约操作")
@Controller
@RequestMapping("/merchant/sign")
@Slf4j
public class SignController extends BaseController {

    @Autowired
    private IEsignFlowService esignFlowService;
    @Autowired
    private IEsignTemplateService esignTemplateService;
    @Autowired
    private IEsignTaxSourceService esignTaxSourceService;

    @ApiOperation(value = "获取签约文件模板")
    @GetMapping("/getSignTemplates")
    @ResponseBody
    public AjaxResult getSignTemplates(@RequestParam Integer taxSourceId) {
        List<EsignTemplate> result;
        try {
            result = esignTemplateService.getSignTemplates(taxSourceId);
        } catch (BusinessException b) {
            log.info("/merchant/sign/getSignTemplates", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.info("/merchant/sign/getSignTemplates", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    @ApiOperation(value = "批量签约")
    @PostMapping("/batchSign")
    @ResponseBody
    public AjaxResult batchPayment(@RequestBody BatchOrderDTO dto) {
        try {
            esignFlowService.batchSign(dto.getBatchNo(),dto.getMerchantId());
        } catch (BusinessException b) {
            log.info("/merchant/sign/batchSign", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.info("/merchant/sign/batchSign", e);
            return error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "税源地开通e签宝机构账号")
    @PostMapping("/createTaxSourceAccount")
    @ResponseBody
    public AjaxResult createTaxSourceAccount(@RequestBody TaxSourceAccountDTO dto) {
        try {
            esignTaxSourceService.createTaxSourceAccount(dto);
        } catch (BusinessException b) {
            log.info("/merchant/sign/createTaxSourceAccount", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.info("/merchant/sign/createTaxSourceAccount", e);
            return error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "税源地修改e签宝机构账号信息")
    @PostMapping("/updateTaxSourceAccount")
    @ResponseBody
    public AjaxResult updateTaxSourceAccount(@RequestBody TaxSourceAccountDTO dto) {
        try {
            esignTaxSourceService.updateTaxSourceAccount(dto);
        } catch (BusinessException b) {
            log.info("/merchant/sign/updateTaxSourceAccount", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.info("/merchant/sign/updateTaxSourceAccount", e);
            return error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "税源地删除e签宝机构账号")
    @PostMapping("/deleteTaxSourceAccount")
    @ResponseBody
    public AjaxResult deleteTaxSourceAccount(@RequestBody TaxSourceAccountDTO dto) {
        try {
            esignTaxSourceService.deleteTaxSourceAccount(dto);
        } catch (BusinessException b) {
            log.info("/merchant/sign/deleteTaxSourceAccount", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.info("/merchant/sign/deleteTaxSourceAccount", e);
            return error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "查询e签宝账号信息")
    @GetMapping("/queryEsignAccountInfo")
    @ResponseBody
    public AjaxResult queryEsignAccountInfo(@RequestParam(required = false) String accountId
            ,@RequestParam(required = false) String orgId) {
        JSONObject req;
        try {
            req = esignTaxSourceService.queryEsignAccountInfo(accountId,orgId);
        } catch (BusinessException b) {
            log.info("/merchant/sign/queryEsignAccountInfo", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.info("/merchant/sign/queryEsignAccountInfo", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(req);
    }

    @ApiOperation(value = "获取签约文件下载地址")
    @GetMapping("/getSignDownUrl")
    @ResponseBody
    public AjaxResult getSignDownUrl(@RequestParam Integer esignFlowId) {
        JSONObject data;
        try {
            data = esignFlowService.getSignDownUrl(esignFlowId);
        } catch (BusinessException b) {
            log.info("/merchant/sign/getSignDownUrl", b);
            return error(b.getMessage());
        } catch (Exception e) {
            log.info("/merchant/sign/getSignDownUrl", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(data);
    }
}
