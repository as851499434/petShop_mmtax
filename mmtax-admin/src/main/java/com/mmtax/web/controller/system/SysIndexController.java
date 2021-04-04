package com.mmtax.web.controller.system;

import cn.hutool.core.date.DateUtil;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.framework.util.ShiroUtils;
import com.mmtax.system.domain.SysMenu;
import com.mmtax.system.domain.SysRole;
import com.mmtax.system.domain.SysUser;
import com.mmtax.system.service.ISysMenuService;
import com.mmtax.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 首页 业务处理
 * 
 * @author mmtax
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;
    @Autowired
    private ISysRoleService sysRoleService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("copyrightYear", DateUtil.format(DateUtil.date(), "YYYY"));
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("user", user);
        List<SysRole> sysRole = sysRoleService.selectRolesByUserId(user.getUserId());
        mmap.put("sysRole", null==sysRole|| sysRole.size()==0?null:sysRole.get(0));
        return "main";
    }
}
