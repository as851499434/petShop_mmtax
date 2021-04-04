package com.mmtax.web.controller.business;

import com.mmtax.business.dto.QuaryTransferOrderinfoDTO;
import com.mmtax.business.dto.TransferOrderinfoDTO;
import com.mmtax.business.service.ITransferOrderService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "转账记录管理")
@Controller
@RequestMapping("/manager/transferOrder")
public class TransferOrderController extends BaseController {
    private String prefix = "business/transferOrder";

    @Autowired
    private ITransferOrderService transferOrderService;

    @ApiOperation(value = "跳转商户打款订单页面")
    @RequiresPermissions("manager:transferOrder:view")
    @GetMapping()
    public String customerInfo()
    {
        return prefix + "/transferOrder";
    }

    @ApiOperation(value = "跳转商户打款订单详情页面")
    @RequiresPermissions("manager:transferOrder:transferOrderDetail")
    @GetMapping("/transferOrderDetail")
    public String transferOrderDetail()
    {
        return prefix + "/transferOrderDetail";
    }

    /**
     * 查询商户打款订单列表
     */
    @RequiresPermissions("manager:transferOrder:list")
    @ApiOperation(value = "商户打款订单列表", response = TransferOrderinfoDTO.class)
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QuaryTransferOrderinfoDTO queryDTO) {
        startPage();
        List<TransferOrderinfoDTO> list = transferOrderService.listTranferOrderInfoDTO(queryDTO);
        return getDataTable(list);
    }
}
