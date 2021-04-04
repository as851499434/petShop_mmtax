package com.mmtax.business.controller.merchant;

import com.mmtax.business.dto.MerchantTaxPaymentCertificateDTO;
import com.mmtax.business.service.ITaxPaymentCertificateService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/13 11:26
 */
@Api(tags = "商户端完税证明")
@Controller
@RequestMapping("/merchant/taxPaymentCertificate")
public class MerchantTaxPaymentCertificateController extends BaseController {


    @Autowired
    private ITaxPaymentCertificateService taxPaymentCertificateService;


    @ApiOperation(value = "获取完税证明列表", response = MerchantTaxPaymentCertificateDTO.class)
    @GetMapping("list")
    @ResponseBody
    public AjaxResult list(@ApiParam(value = "商户id", required = true) int merchantId,
                           @ApiParam(value = "起始期", required = false) String startDate,
                           @ApiParam(value = "结束期", required = false) String endDate,
                           @ApiParam(value = "页码", required = true) Integer currentPage,
                           @ApiParam(value = "每页大小", required = true) Integer pageSize) {
        Page page = new Page();
        try {
            page = taxPaymentCertificateService.getListMerchantTaxPaymentCertificate(merchantId, currentPage, pageSize,
                    startDate, endDate);
        } catch (BusinessException b) {
            logger.info("/merchant/taxPaymentCertificate/list", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/taxPaymentCertificate/list", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "更改下载状态")
    @PostMapping("changeStatus")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "记录id", required = true, paramType = "int"),
            @ApiImplicitParam(name = "status", value = "下载状态0-未下载1-已下载", required = true, paramType = "int")
    })
    public AjaxResult changeDownloadStatus(@ApiIgnore @RequestBody MerchantTaxPaymentCertificateDTO dto) {
        try {
            taxPaymentCertificateService.changeStatus(dto);
        } catch (BusinessException b) {
            logger.info("/merchant/taxPaymentCertificate/changeStatus", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/taxPaymentCertificate/changeStatus", e);
            return error(e.getMessage());
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "获取下载文件名")
    @PostMapping("checkStatus")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "记录id", required = true, paramType = "int")
    })
    public AjaxResult checkStatus(@ApiIgnore @RequestBody MerchantTaxPaymentCertificateDTO dto) {
        Object object = new Object();
        try {
            object = taxPaymentCertificateService.checkStatus(dto);
        } catch (BusinessException b) {
            logger.info("/merchant/taxPaymentCertificate/checkStatus", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/taxPaymentCertificate/checkStatus", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(object);
    }


}
