package com.mmtax.web.controller.business;

import com.mmtax.business.domain.MerchantKey;
import com.mmtax.business.dto.ManagerSeretKeyDTO;
import com.mmtax.business.service.IMerchantKeyService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.framework.util.ShiroUtils;
import com.mmtax.system.domain.SysUser;
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
 * @Date: 2019/7/19 17:11
 */
@Controller
@RequestMapping("manager/secret")
public class ManagerSecretController extends BaseController {

    private String prefix = "business/secret";


    @Autowired
    private IMerchantKeyService merchantKeyService;

    @RequiresPermissions("manager:secret:view")
    @GetMapping()
    public String secret() {
        return prefix + "/secret";
    }

    @PostMapping("getListKey")
    @ResponseBody
    @ApiOperation(value = "对接信息列表")
    @RequiresPermissions("manager:secret:getListKey")
    public TableDataInfo getListKey(ManagerSeretKeyDTO managerSeretKeyDTO) {
        startPage();
        List<ManagerSeretKeyDTO> list = merchantKeyService.getListManagerSeretKey(managerSeretKeyDTO);
        return getDataTable(list);
    }

    @ApiOperation(value = "获取秘钥详细信息", response = MerchantKey.class)
    @GetMapping("detail")
    @ResponseBody
    public AjaxResult getDetail(@ApiParam(value = "秘钥id", required = true) int id) {
        return AjaxResult.success(merchantKeyService.getMerchantKey(id));
    }


}
