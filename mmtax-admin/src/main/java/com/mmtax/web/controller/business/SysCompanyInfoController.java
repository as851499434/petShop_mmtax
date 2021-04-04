package com.mmtax.web.controller.business;


import com.mmtax.business.domain.BatchPaymentDetail;
import com.mmtax.business.domain.BatchPaymentRecord;
import com.mmtax.business.dto.*;
import com.mmtax.business.service.IAgreementService;
import com.mmtax.business.service.IMerchantInfoService;
import com.mmtax.common.constant.DataManagerConstants;
import com.mmtax.common.constant.TaxSounrceCompanyNameConstants;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.poi.ExcelUtil;
import com.mmtax.framework.util.ShiroUtils;
import com.mmtax.system.domain.SysUser;
import com.mmtax.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(tags = "企业管理")
@Controller
@RequestMapping("/manager/companyInfo")
public class SysCompanyInfoController extends BaseController {

    private String prefix = "business/company";

    @Autowired
    private IMerchantInfoService merchantInfoService;

    @Autowired
    private IAgreementService agreementService;

    @Autowired
    private ISysUserService userService;

    @ApiOperation(value = "跳转企业信息页面")
    @RequiresPermissions("manager:companyInfo:view")
    @GetMapping()
    public String companyInfo() {
        return prefix + "/companyInfo";
    }

    @ApiOperation(value = "跳转个人收款页面")
    @RequiresPermissions("manager:companyInfo:personalReceipt")
    @GetMapping("/personalReceipt")
    public String personalReceiptInfo() {
        return prefix + "/personalReceiptInfo";
    }

    @ApiOperation(value = "跳转企业账户资金流水页面")
    @RequiresPermissions("manager:companyInfo:companyReceiptInfo")
    @GetMapping("/companyReceiptInfo")
    public String companyReceiptInfo() {
        return prefix + "/companyReceiptInfo";
    }

    @ApiOperation(value = "跳转发票管理页面")
    @RequiresPermissions("manager:companyInfo:invoiceInfo")
    @GetMapping("/invoiceInfo")
    public String invoiceInfo() {
        return prefix + "/invoiceInfo";
    }

    @ApiOperation(value = "跳转合同信息页面")
    @RequiresPermissions("manager:companyInfo:contractInfo")
    @GetMapping("/contractInfo")
    public String contractInfo() {
        return prefix + "/contractInfo";
    }

    @ApiOperation(value = "跳转付款信息页面")
    @RequiresPermissions("manager:companyInfo:payInfo")
    @GetMapping("/payInfo")
    public String payInfo() {
        return prefix + "/payInfo";
    }

    @ApiOperation(value = "跳转发放信息页面")
    @RequiresPermissions("manager:companyInfo:grantInfo")
    @GetMapping("/grantInfo")
    public String grantInfo() {
        return prefix + "/grantInfo";
    }

    @ApiOperation(value = "跳转个人资金流水页面")
    @RequiresPermissions("manager:companyInfo:personalAccountFundChange")
    @GetMapping("/personalAccountFundChange")
    public String personalAccountFundChangeInfo() {
        return prefix + "/personalAccountFundChangeInfo";
    }
    /**
     * 查看企业信息
     */
    @ApiOperation(value = "查看企业信息", response = ManagerCompanyInfoDTO.class)
    @RequiresPermissions("manager:companyInfo:selectAllCompanyInfo")
    @PostMapping("/selectAllCompanyInfo")
    @ResponseBody
    public TableDataInfo selectAllCompanyInfo(ManagerCompanyInfoQueryDTO queryDTO) {
        setTaxSoureIdByUser(queryDTO);
        startPage();
        List<ManagerCompanyInfoDTO> managerCompanyInfoDTOS =
                merchantInfoService.selectAllCompanyInfo(queryDTO);
        return getDataTable(managerCompanyInfoDTOS);
    }


    /**
     * 查看公司合同信息
     */
    @ApiOperation(value = "查看公司合同信息", response = ManagerContractInfoDTO.class)
    @RequiresPermissions("manager:companyInfo:selectContractInfo")
    @PostMapping("/selectContractInfo")
    @ResponseBody
    public TableDataInfo selectContractInfo(ManagerCompanyInfoQueryDTO queryDTO) {
        setTaxSoureIdByUser(queryDTO);
        startPage();
        List<ManagerContractInfoDTO> managerContractInfoDTOS =
                merchantInfoService.selectContractInfo(queryDTO);
        return getDataTable(managerContractInfoDTOS);
    }

    /**
     * 查看公司合同信息协议详情
     */
    @ApiOperation(value = "查看公司合同信息协议详情", response = ManagerAgreementInfoDTO.class)
    @RequiresPermissions("manager:companyInfo:selectContractInfo:agreement")
    @GetMapping("/agreement")
    public String agreement(@ApiParam(value = "身份证号", required = true) String idNumber,
                            @ApiParam(value = "商户id", required = true) Integer merchantId,
                            ModelMap mmap) {
        ManagerAgreementInfoDTO dto = agreementService.getManagerContractInfoDTO(idNumber, merchantId);
        mmap.put("agreementInfo", dto);
        return prefix + "/agreementContent";
    }

    /**
     * 查看付款信息
     */
    @ApiOperation(value = "查看付款信息", response = ManagerPayInfoDTO.class)
    @RequiresPermissions("manager:companyInfo:selectPayInfo")
    @PostMapping("/selectPayInfo")
    @ResponseBody
    public TableDataInfo selectPayInfoById(ManagerCompanyInfoQueryDTO queryDTO) {
        setTaxSoureIdByUser(queryDTO);
        startPage();
        List<ManagerPayInfoDTO> managerPayInfoDTOS = merchantInfoService.selectPayInfo(queryDTO);
        return getDataTable(managerPayInfoDTOS);
    }

    /**
     * 查看发放信息
     */
    @ApiOperation(value = "查看发放信息", response = ManagerGrantInfoDTO.class)
    @RequiresPermissions("manager:companyInfo:selectGrantInfo")
    @PostMapping("/selectGrantInfo")
    @ResponseBody
    public TableDataInfo selectGrantInfo(ManagerCompanyInfoQueryDTO queryDTO) {
        setTaxSoureIdByUser(queryDTO);
        startPage();
        List<ManagerGrantInfoDTO> managerGrantInfoDTO = merchantInfoService.selectGrantInfo(queryDTO);
        return getDataTable(managerGrantInfoDTO);
    }

    @ApiOperation(value = "查看个人收款信息", response = PersonalReceiptDTO.class)
    @RequiresPermissions(value = "manager:companyInfo:personalReceipt")
    @PostMapping("/personalReceiptInfo")
    @ResponseBody
    public TableDataInfo personalReceipt(ManagerCompanyInfoQueryDTO queryDTO) {
        boolean judgeDateSuit = judgeDateSuit(queryDTO);
        if (!judgeDateSuit){
            throw new BusinessException("查询的时间间隔不能大于7天");
        }
        setTaxSoureIdByUser(queryDTO);
        setTaxSoureIdByUser(queryDTO);
        startPage();
        List<PersonalReceiptDTO> personalReceiptDTOS =
                merchantInfoService.selectPersonalReceiptInfo(queryDTO);
        return getDataTable(personalReceiptDTOS);
    }


    @ApiOperation(value = "导出个人收款信息", response = PersonalReceiptDTO.class)
    @RequiresPermissions(value = "manager:companyInfo:personalReceiptExport")
    @PostMapping("/personalReceiptExport")
    @ResponseBody
    public AjaxResult personalReceiptExport(ManagerCompanyInfoQueryDTO queryDTO) {
        boolean judgeDateSuit = judgeDateSuit(queryDTO);
        if (!judgeDateSuit){
            throw new BusinessException("查询的时间间隔不能大于7天");
        }
        setTaxSoureIdByUser(queryDTO);
        setTaxSoureIdByUser(queryDTO);
        startPage();
        List<PersonalReceiptDTO> personalReceiptDTOS =
                merchantInfoService.selectPersonalReceiptInfo(queryDTO);

        ExcelUtil<PersonalReceiptDTO> util = new ExcelUtil<>(PersonalReceiptDTO.class);

        return util.exportExcel(personalReceiptDTOS, "个人收款");

    }

    @ApiOperation(value = "查看个人资金流水信息", response = PersonalAccountFundChangeDTO.class)
    @RequiresPermissions(value = "manager:companyInfo:personalAccountFundChange")
    @PostMapping("/personalAccountFundChange")
    @ResponseBody
    public TableDataInfo personalAccountFundChange(ManagerCompanyInfoQueryDTO queryDTO) {
        boolean judgeDateSuit = judgeDateSuit(queryDTO);
        if (!judgeDateSuit){
            throw new BusinessException("查询的时间间隔不能大于7天");
        }
        setTaxSoureIdByUser(queryDTO);
        startPage();
        List<PersonalAccountFundChangeDTO> PersonalAccountFundChangeDTOS =
                merchantInfoService.selectPersonalAccountFundChangeInfo(queryDTO);
        return getDataTable(PersonalAccountFundChangeDTOS);
    }

    /**
     * 判断查询的时间段是否合适
     * @param queryDTO 参数
     * @return 结果
     */
    private boolean judgeDateSuit(ManagerCompanyInfoQueryDTO queryDTO) {
        String startDate = queryDTO.getStartDate();
        String endDate = queryDTO.getEndDate();

        if(StringUtils.isNotEmpty(startDate)&&StringUtils.isNotEmpty(endDate)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date start = null;
            Date end = null;
            try {
                start = simpleDateFormat.parse(startDate);
                end = simpleDateFormat.parse(endDate);
            } catch (ParseException e) {
                throw new BusinessException("时间格式有误");
            }
            long ms = end.getTime() - start.getTime();
            if(ms<= 7 * 24 * 60 * 60 * 1000){
                return true;
            }
        }else{
            throw new BusinessException("时间不能为空");
        }
        return false;
    }

    /**
     * 查看企业账户资金流水
     */
    @ApiOperation(value = "查看企业账户资金流水", response = CompanyReceiptInfoDTO.class)
    @RequiresPermissions("manager:companyInfo:selectCompanyReceiptInfo")
    @PostMapping("/selectCompanyReceiptInfo")
    @ResponseBody
    public TableDataInfo selectCompanyReceiptInfo(ManagerCompanyInfoQueryDTO queryDTO) {
        setTaxSoureIdByUser(queryDTO);
        startPage();
        List<CompanyReceiptInfoDTO> companyReceiptInfoDTOS = merchantInfoService.selectCompanyReceiptInfo(queryDTO);
        return getDataTable(companyReceiptInfoDTOS);
    }

    /**
     * 导出合同信息
     */
    @ApiOperation(value = "导出企业账户资金流水信息")
    @RequiresPermissions("manager:companyInfo:companyReceiptInfoExport")
    @PostMapping("/companyReceiptInfoExport")
    @ResponseBody
    public AjaxResult companyReceiptInfoExport(ManagerCompanyInfoQueryDTO queryDTO ) {
        setTaxSoureIdByUser(queryDTO);
        List<CompanyReceiptInfoDTO> companyReceiptInfoDTOS = merchantInfoService.selectCompanyReceiptInfo(queryDTO);
        ExcelUtil<CompanyReceiptInfoDTO> util = new ExcelUtil<>(CompanyReceiptInfoDTO.class);
        return util.exportExcel(companyReceiptInfoDTOS, "企业账户资金流水信息");
    }

    /**
     * 导出合同信息
     */
    @ApiOperation(value = "导出合同相关信息")
    @RequiresPermissions("manager:companyInfo:contractExport")
    @PostMapping("/contractExport")
    @ResponseBody
    public AjaxResult contractExport(ManagerCompanyInfoQueryDTO queryDTO) {
        setTaxSoureIdByUser(queryDTO);
        List<ManagerContractInfoDTO> managerContractInfoDTO =
                merchantInfoService.selectContractInfo(queryDTO);
        ExcelUtil<ManagerContractInfoDTO> util = new ExcelUtil<>(ManagerContractInfoDTO.class);
        return util.exportExcel(managerContractInfoDTO, "合同相关信息");
    }

    /**
     * 导出合同信息
     */
    @ApiOperation(value = "导出付款相关信息")
    @RequiresPermissions("manager:companyInfo:payExport")
    @PostMapping("/payExport")
    @ResponseBody
    public AjaxResult payExport(ManagerCompanyInfoQueryDTO queryDTO) {
        setTaxSoureIdByUser(queryDTO);
        List<ManagerPayInfoDTO> managerPayInfoDTOS = merchantInfoService.selectPayInfo(queryDTO);
//        managerPayInfoDTOS.stream().forEach(m -> m.setExcelAmount(m.getAmount() == null?m.getAmount().toString():"" ));

        for(ManagerPayInfoDTO managerPayInfoDTO:managerPayInfoDTOS){
            managerPayInfoDTO.setExcelAmount(String.format("%.2f",managerPayInfoDTO.getAmount()));
        }
        ExcelUtil<ManagerPayInfoDTO> util = new ExcelUtil<>(ManagerPayInfoDTO.class);
        return util.exportExcel(managerPayInfoDTOS, "付款相关信息");
    }


    /**
     * 导出合同信息
     */
    @ApiOperation(value = "导出付款相关信息")
    @RequiresPermissions("manager:companyInfo:personalAccountFundChangeExport")
    @PostMapping("/personalAccountFundChangeExport")
    @ResponseBody
    public AjaxResult personalAccountFundChangeExport(ManagerCompanyInfoQueryDTO queryDTO) {
        boolean judgeDateSuit = judgeDateSuit(queryDTO);
        if (!judgeDateSuit){
            throw new BusinessException("查询的时间间隔不能大于7天");
        }
        setTaxSoureIdByUser(queryDTO);
        List<PersonalAccountFundChangeDTO> PersonalAccountFundChangeDTOS =
                merchantInfoService.selectPersonalAccountFundChangeInfo(queryDTO);
        ExcelUtil<PersonalAccountFundChangeDTO> util = new ExcelUtil<>(PersonalAccountFundChangeDTO.class);
        return util.exportExcel(PersonalAccountFundChangeDTOS, "个人账户资金流水");
    }



    /**
     * 导出发放信息
     */
    @ApiOperation(value = "导出发放相关信息")
    @RequiresPermissions("manager:companyInfo:grantExport")
    @PostMapping("/grantExport")
    @ResponseBody
    public AjaxResult grantExport(ManagerCompanyInfoQueryDTO queryDTO) {
        setTaxSoureIdByUser(queryDTO);
        List<ManagerGrantInfoDTO> managerGrantInfoDTO = merchantInfoService.selectGrantInfo(queryDTO);
        ExcelUtil<ManagerGrantInfoDTO> util = new ExcelUtil<>(ManagerGrantInfoDTO.class);
        return util.exportExcel(managerGrantInfoDTO, "发放相关信息");
    }

    /**
     * 导出发放信息
     */
    @ApiOperation(value = "导出发票信息")
    @RequiresPermissions("manager:companyInfo:invoiceExport")
    @PostMapping("/invoiceExport")
    @ResponseBody
    public AjaxResult invoiceExport(ManagerCompanyInfoQueryDTO queryDTO) {
        setTaxSoureIdByUser(queryDTO);
        List<InvoiceInfoDetailDTO> personalReceiptDTOS =
                merchantInfoService.selectInvoiceInfoDetail(queryDTO);
        ExcelUtil<InvoiceInfoDetailDTO> util = new ExcelUtil<>(InvoiceInfoDetailDTO.class);
        return util.exportExcel(personalReceiptDTOS, "发票信息");
    }

    @ApiOperation(value = "查看发票信息", response = InvoiceInfoDetailDTO.class)
    @RequiresPermissions(value = "manager:companyInfo:invoiceInfo")
    @PostMapping("/invoiceInfoDetail")
    @ResponseBody
    public TableDataInfo selectInvoiceInfo(ManagerCompanyInfoQueryDTO queryDTO) {
        setTaxSoureIdByUser(queryDTO);
        startPage();
        List<InvoiceInfoDetailDTO> personalReceiptDTOS =
                merchantInfoService.selectInvoiceInfoDetail(queryDTO);
        return getDataTable(personalReceiptDTOS);
    }
    //根据登录用户匹配税源地
    private void  setTaxSoureIdByUser(ManagerCompanyInfoQueryDTO queryDTO){
        SysUser sysUser = userService.selectUserById(ShiroUtils.getSysUser().getUserId());
        if (DataManagerConstants.DATA_MANAGER_LOGIN_HNJJ.equals(sysUser.getLoginName()) ||
                DataManagerConstants.DATA_MANAGER_LOGIN_HNJJ_FOR_SHOW_QING_DAO.equals(sysUser.getLoginName())) {
            queryDTO.setTaxSourceId(4);
        } else if (DataManagerConstants.DATA_MANAGER_LOGIN_JXQB.equals(sysUser.getLoginName())) {
            queryDTO.setTaxSourceId(5);
        } else if (DataManagerConstants.DATA_MANAGER_LOGIN_ADMIN.equals(sysUser.getLoginName())) {
            queryDTO.setTaxSourceId(666);
        } else {
            // 此种情况表示没有对应税源地数据
            queryDTO.setTaxSourceId(0);
        }
    }

    @ApiOperation(value = "处理批量打款记录旧数据", response = BatchPaymentRecord.class)
    @PostMapping()
    @ResponseBody
    public List<BatchPaymentRecord> updateBatchPaymentRecordInfo( ) {
        return merchantInfoService.updateBatchPaymentRecordInfo();
    }

}


