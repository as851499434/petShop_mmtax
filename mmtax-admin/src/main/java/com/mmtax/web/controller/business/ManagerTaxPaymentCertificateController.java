package com.mmtax.web.controller.business;

import com.mmtax.business.dto.ManagerAddTaxPaymentCertificateDTO;
import com.mmtax.business.dto.ManagerTaxMerchantDTO;
import com.mmtax.business.dto.ManagerTaxPaymentCertificateDTO;
import com.mmtax.business.service.ITaxPaymentCertificateService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.framework.util.ShiroUtils;
import com.mmtax.system.domain.SysUser;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/13 15:12
 */
@Api(tags = "后台管理个人完税证明")
@Controller
@RequestMapping("/manager/taxPaymentCertificate")
public class ManagerTaxPaymentCertificateController extends BaseController {

    @Autowired
    private ITaxPaymentCertificateService taxPaymentCertificateService;

    private String prefix = "business/taxPaymentCertificate";


    @RequiresPermissions("manager:taxPaymentCertificate:view")
    @GetMapping()
    public String taxPaymentCertificate() {
        return prefix + "/taxPaymentCertificate";
    }

    @ApiOperation(value = "后台完税证明列表", response = ManagerTaxPaymentCertificateDTO.class)
    @RequiresPermissions("manager:taxPaymentCertificate:list")
    @PostMapping("list")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "merchantName", value = "商户名称", required = false, paramType = "String"),
            @ApiImplicitParam(name = "contactsMobile", value = "联系人手机号", required = false, paramType = "String"),
            @ApiImplicitParam(name = "startDate", value = "起始时间", required = false, paramType = "String"),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = false, paramType = "String")
    })
    public TableDataInfo list(@ApiIgnore ManagerTaxPaymentCertificateDTO dto) {
        startPage();
        List<ManagerTaxPaymentCertificateDTO> list = taxPaymentCertificateService.
                getListManagerTaxPaymentCertificate(dto);
        return getDataTable(list);
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation(value = "添加完税证明")
    @RequiresPermissions("manager:taxPaymentCertificate:add")
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(@RequestBody ManagerAddTaxPaymentCertificateDTO dto) {
        try {
            taxPaymentCertificateService.addManagerTaxPaymentCertificateDTO(dto);
        } catch (BusinessException b) {
            logger.info("/manager/taxPaymentCertificate/add", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/manager/taxPaymentCertificate/add", e);
            return error(e.getMessage());
        }
        return success();
    }

    @ApiOperation(value = "获取商户信息", response = ManagerTaxMerchantDTO.class)
    @GetMapping("getMerchantInfo")
    @ResponseBody
    public AjaxResult getMerchantInfo(@ApiParam(value = "页码", required = true) Integer currentPage,
                                      @ApiParam(value = "每页大小", required = true) Integer pageSize,
                                      @ApiParam(value = "商户名称", required = false) String merchantName,
                                      @ApiParam(value = "商户编码", required = false) String merchantCode) {
        Page page = new Page();
        try {
            SysUser sysUser = ShiroUtils.getSysUser();
            page = taxPaymentCertificateService.getMerchantInfo(currentPage, pageSize, merchantName, merchantCode, sysUser);
        } catch (BusinessException b) {
            logger.info("/manager/taxPaymentCertificate/getMerchantInfo", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/manager/taxPaymentCertificate/getMerchantInfo", e);
            return error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    @ApiOperation(value = "编辑页面跳转", response = ManagerTaxPaymentCertificateDTO.class)
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        ManagerTaxPaymentCertificateDTO dto = new ManagerTaxPaymentCertificateDTO();
        dto.setId(id);
        mmap.put("info" , taxPaymentCertificateService.getListManagerTaxPaymentCertificate(dto).get(0));
        return prefix + "/edit";
    }

    @ApiOperation("更新完税证明")
    @PostMapping("edit")
    @RequiresPermissions("manager:taxPaymentCertificate:update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "记录id", required = false, paramType = "String"),
            @ApiImplicitParam(name = "fileName", value = "系统生成的文件名称", required = false, paramType = "String"),
            @ApiImplicitParam(name = "name", value = "文件名称", required = false, paramType = "String")
    })
    @ResponseBody
    public AjaxResult edit(@ApiIgnore @RequestBody ManagerTaxPaymentCertificateDTO dto) {
        try {
            taxPaymentCertificateService.update(dto);
        } catch (BusinessException b) {
            logger.info("/manager/taxPaymentCertificate/edit", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/manager/taxPaymentCertificate/edit", e);
            return error(e.getMessage());
        }
        return success();
    }

    @ApiOperation("删除完税证明")
    @PostMapping("delete")
    @RequiresPermissions("manager:taxPaymentCertificate:delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "记录id", required = false, paramType = "String"),
            @ApiImplicitParam(name = "status", value = "0-未下载1-已下载2-已删除", required = false, paramType = "String")
    })
    @ResponseBody
    public AjaxResult delete(@ApiIgnore ManagerTaxPaymentCertificateDTO dto) {
        try {
            taxPaymentCertificateService.delete(dto);
        } catch (BusinessException b) {
            logger.info("/manager/taxPaymentCertificate/delete", b);
            return error(b.getMessage());
        } catch (Exception e) {
            logger.info("/manager/taxPaymentCertificate/delete", e);
            return error(e.getMessage());
        }
        return success();
    }


}
