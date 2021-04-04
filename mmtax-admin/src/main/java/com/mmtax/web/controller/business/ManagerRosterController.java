package com.mmtax.web.controller.business;

import com.mmtax.business.dto.ManagerUserDTO;
import com.mmtax.business.service.IRosterService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.framework.util.ShiroUtils;
import com.mmtax.system.domain.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/8/7 13:55
 */
@Api(tags = "系统后台免验名单")
@Controller
@RequestMapping("/manager/roster")
public class ManagerRosterController extends BaseController {

    private String prefix = "business/userList";

    @Autowired
    private IRosterService rosterService;


    @RequiresPermissions("manager:roster:view")
    @GetMapping()
    public String secret() {
        return prefix + "/userList";
    }

    @ApiOperation(value = "免验名单查询", response = ManagerUserDTO.class)
    @RequiresPermissions("manager:roster:list")
    @PostMapping("list")
    @ResponseBody
    public TableDataInfo getListUser(ManagerUserDTO managerUserDTO) {
        startPage();
        List<ManagerUserDTO> list = rosterService.getListUser(managerUserDTO);
        return getDataTable(list);
    }

    @ApiOperation(value = "免验名单状态审核")
    @RequiresPermissions("manager:roster:status")
    @PostMapping("checkUserStatus")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "名单id", required = true, paramType = "int"),
            @ApiImplicitParam(name = "status", value = "免验状态0-未加入1-已加入2-已拒绝", required = true, paramType = "int")
    })
    public AjaxResult checkUserStatus(@ApiIgnore ManagerUserDTO managerUserDTO) {
        rosterService.checkUserStatus(managerUserDTO);
        return success();
    }


}
