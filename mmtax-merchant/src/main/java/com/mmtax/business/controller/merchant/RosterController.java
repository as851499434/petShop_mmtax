package com.mmtax.business.controller.merchant;

import com.mmtax.business.domain.CertificateType;
import com.mmtax.business.dto.MerchantAddUserDTO;
import com.mmtax.business.dto.MerchantListUserDTO;
import com.mmtax.business.service.IRosterService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/7 10:02
 */
@Api(tags = "要素认证")
@Controller
@RequestMapping("/merchant/roster")
public class RosterController extends BaseController {


    @Autowired
    private IRosterService rosterService;

    @ApiOperation(value = "添加免验名单")
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(@RequestBody MerchantAddUserDTO merchantAddUserDTO) {
        try {
            rosterService.add(merchantAddUserDTO);
        } catch (BusinessException b) {
            logger.info("/merchant/roster/add", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/roster/add", e);
            return error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "获取证件类型", response = CertificateType.class)
    @GetMapping("getCertificateType")
    @ResponseBody
    public AjaxResult getCertificateType() {
        List<CertificateType> list = new ArrayList<>();
        try {
            list = rosterService.getListCertificateType();
        } catch (BusinessException b) {
            logger.info("/merchant/roster/getCertificateType", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/roster/getCertificateType", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(list);
    }

    @ApiOperation(value = "获取免验名单列表", response = MerchantListUserDTO.class)
    @GetMapping("list")
    @ResponseBody
    public AjaxResult list(@ApiParam(value = "商户id", required = true) Integer merchantId,
                           @ApiParam(value = "页码", required = true) Integer currentPage,
                           @ApiParam(value = "名字", required = false) String name,
                           @ApiParam(value = "每页大小", required = true) Integer pageSize) {
        Page page = new Page();
        try {
            page = rosterService.getPageUser(merchantId, pageSize, currentPage, name);
        } catch (BusinessException b) {
            logger.info("/merchant/roster/list", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/merchant/roster/list", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }


}
