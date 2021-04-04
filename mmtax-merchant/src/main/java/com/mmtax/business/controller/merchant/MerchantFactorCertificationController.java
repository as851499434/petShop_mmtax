package com.mmtax.business.controller.merchant;

import com.mmtax.business.dto.MerchantFactorCertificationDTO;
import com.mmtax.business.service.IFactorCertificationService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/8 14:18
 */
@Api(tags = "商户端要素认证")
@Controller
@RequestMapping("/merchant/factorCertification")
public class MerchantFactorCertificationController extends BaseController {


    @Autowired
    private IFactorCertificationService factorCertificationService;

    @ApiOperation(value = "获取要素认证列表", response = MerchantFactorCertificationDTO.class)
    @GetMapping("list")
    @ResponseBody
    public AjaxResult list(@ApiParam(value = "商户id", required = true) int merchantId,
                           @ApiParam(value = "要素认证订单id", required = false) Integer factorOrderId,
                           @ApiParam(value = "每页大小", required = true) int pageSize,
                           @ApiParam(value = "页码", required = true) int currentPage,
                           @ApiParam(value = "姓名", required = false) String name,
                           @ApiParam(value = "身份证号", required = false) String idCardNo,
                           @ApiParam(value = "银行卡号", required = false) String bankCardNo,
                           @ApiParam(value = "商户订单号", required = false) String merchantSerialNum,
                           @ApiParam(value = "平台订单号", required = false) String orderSerialNum,
                           @ApiParam(value = "订单状态", required = false) Integer status,
                           @ApiParam(value = "起始时间", required = false) String startDate,
                           @ApiParam(value = "结束时间", required = false) String endDate
    ) {
        Page page = new Page();
        try {
            page = factorCertificationService.getPageMerchantFactorCertification(merchantId, pageSize, currentPage, name
                    , idCardNo, bankCardNo, merchantSerialNum, orderSerialNum, status, startDate, endDate, factorOrderId);
        } catch (BusinessException b) {
            logger.info("/merchant/factorCertification/list", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/factorCertification/list", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "导出要素认证列表")
    @GetMapping("/exportList")
    @ResponseBody
    public AjaxResult exportList(@ApiParam(value = "商户id", required = true) int merchantId,
                                 @ApiParam(value = "姓名", required = false) String name,
                                 @ApiParam(value = "身份证号", required = false) String idCardNo,
                                 @ApiParam(value = "银行卡号", required = false) String bankCardNo,
                                 @ApiParam(value = "商户订单号", required = false) String merchantSerialNum,
                                 @ApiParam(value = "平台订单号", required = false) String orderSerialNum,
                                 @ApiParam(value = "订单状态", required = false) Integer status,
                                 @ApiParam(value = "起始时间", required = false) String startDate,
                                 @ApiParam(value = "结束时间", required = false) String endDate,
                                 @ApiParam(value = "订单id，财务中心要素订单导出用", required = false) Integer factorOrderId) {
        List<MerchantFactorCertificationDTO> list = factorCertificationService.exportMerchantList(merchantId, name
                , idCardNo, bankCardNo, merchantSerialNum, orderSerialNum, status, startDate, endDate, factorOrderId);
        ExcelUtil<MerchantFactorCertificationDTO> util = new ExcelUtil<MerchantFactorCertificationDTO>
                (MerchantFactorCertificationDTO.class);
        AjaxResult result = util.exportExcel(list, "要素认证列表");
        return result;
    }


}
