package com.mmtax.web.controller.business.transaction;

import com.mmtax.business.dto.SysTrxOrderDTO;
import com.mmtax.business.dto.SysTrxRecordDTO;
import com.mmtax.business.service.ISysTransactionService;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.enums.BusinessType;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统后台交易管理控制层
 * @author yuanligang
 * @date 2019/7/18
 */
@Controller
@RequestMapping("manager/transaction")
public class SysTransactionController extends BaseController {

    private String prefix = "business/trxorder";


    @Autowired
    ISysTransactionService sysTransactionService;

    /**
     * 模板语句 post请求
     * @return
     */
    @ApiOperation(value = "交易列表查询", response = SysTrxRecordDTO.class)
    @Log(title = "交易列表查询", businessType = BusinessType.SELECT)
    @PostMapping("/queryList")
    @ResponseBody
    public TableDataInfo queryMeal(SysTrxRecordDTO sys) {
        startPage();
        List<SysTrxOrderDTO> list = sysTransactionService.listSysOrders(sys);
        return getDataTable(list);
    }

    @ApiOperation(value = "交易挂起列表查询", response = SysTrxRecordDTO.class)
    @Log(title = "交易挂起列表查询", businessType = BusinessType.SELECT)
    @PostMapping("/queryHangsList")
    @ResponseBody
    public TableDataInfo queryHangsList(SysTrxRecordDTO sys) {
        startPage();
        List<SysTrxOrderDTO> list = sysTransactionService.listSysHangsOrders(sys);
        return  getDataTable(list);
    }


}




