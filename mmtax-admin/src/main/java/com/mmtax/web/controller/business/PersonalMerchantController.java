package com.mmtax.web.controller.business;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.TaxType;
import com.mmtax.business.domain.WorkOrder;
import com.mmtax.business.dto.*;
import com.mmtax.business.service.IWorkOrderService;
import com.mmtax.common.annotation.Log;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.core.page.TableDataInfo;
import com.mmtax.common.enums.BusinessType;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.mmtax.business.domain.PersonalMerchant;
import com.mmtax.business.service.IPersonalMerchantService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 个体工商户 信息操作处理
 * 
 * @author meimiao
 * @date 2020-11-26
 */
@Controller
@Api(tags = "个体工商户")
@RequestMapping("/business/personalMerchant")
@Slf4j
public class PersonalMerchantController extends BaseController
{
    private String prefix = "business/personalMerchant";

	@Autowired
	private IPersonalMerchantService personalMerchantService;

	@Resource
	private IWorkOrderService workOrderService;

	@RequiresPermissions("business:personalMerchant:personalMerchantListView")
    @ApiOperation("跳转入网申请页面")
	@GetMapping("/personalMerchantListView")
	public String personalMerchantList()
	{
	    return prefix + "/personalMerchantList";
	}

	@RequiresPermissions("business:personalMerchant:personalLicenseList")
	@ApiOperation("跳转个体户执照页面")
	@GetMapping("/personalLicenseList")
	public String personalLicenseList()
	{
		return prefix + "/personalLicenseList";
	}

	@RequiresPermissions("business:personalMerchant:taxTypeList")
	@ApiOperation("跳转税务类型页面")
	@GetMapping("/taxTypeList")
	public String taxTypeList()
	{
		return prefix + "/taxTypeList";
	}

	@RequiresPermissions("business:personalMerchant:applyBusinessLicenseInfo")
	@ApiOperation("跳转营业执照办理页面")
	@GetMapping("/applyBusinessLicenseInfo")
	public String applyBusinessLicenseInfo()
	{
		return prefix + "/applyBusinessLicenseInfo";
	}

	@RequiresPermissions("business:personalMerchant:workOrderPublish")
	@ApiOperation("跳转发布页面")
	@GetMapping("/workOrderPublish")
	public String workOrderPublish()
	{
		return prefix + "/add";
	}


	@RequiresPermissions("business:personalMerchant:workOrderDetail")
	@ApiOperation("跳转工单详情页面")
	@GetMapping("/workOrderDetail")
	public String workOrderDetail(@ApiParam("工单编号") String workOrderSerialNumber, ModelMap map)
	{
		map.put("workOrderDetail",workOrderService.queryWorkOrderDetail(workOrderSerialNumber));
		return prefix + "/edit";
	}

	@RequiresPermissions("business:personalMerchant:workOrderList")
	@ApiOperation("跳转工单页面")
	@GetMapping("/workOrderList")
	public String workOrderList()
	{
		return prefix + "/workOrder";
	}

	@RequiresPermissions("business:personalMerchant:uploadLicense")
	@ApiOperation("跳转上传营业执照页面")
	@GetMapping("/uploadLicense")
	public String uploadLicense(@ApiParam("applyId") Integer applyId, ModelMap map)
	{
		map.put("applyId",applyId);
		return prefix + "/uploadLicense";
	}


    @RequiresPermissions("business:personalMerchant:licenseApplyDetail")
    @ApiOperation("跳转营业执照办理详情页面")
    @GetMapping("/licenseApplyDetail")
    public String licenseApplyDetail(@ApiParam("applyId") Integer applyId, ModelMap map)
    {
		ApplyLicenseDetailInfoDTO applyLicenseDetailInfoDTO = personalMerchantService.showApplyLicenseDetailInfo(applyId);
		map.put("applyLicenseDetailInfoDTO",applyLicenseDetailInfoDTO);
		return prefix + "/licenseApplyDetail";
    }

    @RequiresPermissions("business:personalMerchant:licenseCheckDetail")
    @ApiOperation("跳转营业执照审核页面")
    @GetMapping("/licenseCheckDetail")
    public String licenseCheckDetail(@ApiParam("applyId") Integer applyId, ModelMap map)
    {
		ApplyLicenseDetailInfoDTO applyLicenseDetailInfoDTO = personalMerchantService.showApplyLicenseDetailInfo(applyId);
		map.put("applyLicenseDetailInfoDTO",applyLicenseDetailInfoDTO);
        return prefix + "/licenseCheckDetail";
    }

    @RequiresPermissions("business:personalMerchant:addTaxType")
    @ApiOperation("跳转添加税务类型页面")
    @GetMapping("/addTaxType")
    public String addTaxType()
    {
        return prefix + "/addTaxType";
    }

    @RequiresPermissions("business:personalMerchant:editTaxType")
    @ApiOperation("跳转修改税务类型页面")
    @GetMapping("/editTaxType/{id}")
    public String editTaxType(@PathVariable("id") Integer id, ModelMap mmap)
    {
		TaxTypeInfoDTO taxTypeInfoDTO = personalMerchantService.selectTaxTypeInfoById(id);
		mmap.put("taxTypeInfoDTO", taxTypeInfoDTO);
		return prefix + "/editTaxType";
    }
	/**
	 * 查询入网申请列表
	 */
	@RequiresPermissions("business:personalMerchant:personalMerchantList")
	@PostMapping("/personalMerchantList")
    @Log(title = "入网申请信息查询",businessType = BusinessType.SELECT)
	@ResponseBody
	@ApiOperation("入网申请信息查询")
	public TableDataInfo personalMerchantList(ApplyForDTO applyForDTO)
	{
		startPage();
        List<PersonalMerchantDTO> personalMerchantDTOS = personalMerchantService.selectPersonalMerchantDTOList(applyForDTO);
		return getDataTable(personalMerchantDTOS);
	}

    /**
     * 批量删除申请记录
     */
    @RequiresPermissions("business:personalMerchant:batchDeleteInfo")
    @PostMapping("/batchDeleteInfo")
    @Log(title = "批量删除入网申请信息和批量删除执照办理信息",businessType = BusinessType.UPDATE)
    @ResponseBody
    @ApiOperation("批量删除入网申请信息和批量删除执照办理信息")
    public AjaxResult batchDeleteInfo(@RequestParam("applyIds") List<Integer> applyIds){
        boolean flag;
        try {
            flag = personalMerchantService.batchDeleteInfo(applyIds);
        }catch (BusinessException e){
            logger.info("/business/personalMerchant/batchDeleteInfo, {}",e.getMessage());
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/business/personalMerchant/batchDeleteInfo, {}",e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(flag);
    }

	/**
	 * 查看办理申请详情
	 */
	@RequiresPermissions("business:personalMerchant:showPersonalMerchantInfo")
	@PostMapping("/showPersonalMerchantInfo")
	@Log(title = "查看入网申请详情",businessType = BusinessType.SELECT)
	@ResponseBody
	@ApiOperation("查看入网申请详情")
	public AjaxResult showPersonalMerchantInfo(Integer applyId){
		PersonalMerchantDetailInfoDTO personalMerchantDetailInfoDTO;
		try {
			personalMerchantDetailInfoDTO = personalMerchantService.showPersonalMerchantInfo(applyId);
		}catch (BusinessException e){
			logger.info("/business/personalMerchant/showPersonalMerchantInfo, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}catch (Exception e){
			logger.error("/business/personalMerchant/showPersonalMerchantInfo, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}
		return AjaxResult.success(personalMerchantDetailInfoDTO);
	}

	/**
	 * 修改入网申请
	 */
	@RequiresPermissions("business:personalMerchant:updatePersonalMerchantInfo")
	@PostMapping("/updatePersonalMerchantInfo")
	@Log(title = "修改入网申请",businessType = BusinessType.UPDATE)
	@ResponseBody
	@ApiOperation("修改入网申请")
	public AjaxResult updatePersonalMerchantInfo(@RequestBody PersonalMerchantDetailInfoDTO detailInfoDTO){
		boolean flag;
		try {
			flag = personalMerchantService.updatePersonalMerchantInfo(detailInfoDTO);
		}catch (BusinessException e){
			logger.info("/business/personalMerchant/updatePersonalMerchantInfo, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}catch (Exception e){
			logger.error("/business/personalMerchant/updatePersonalMerchantInfo, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}
		return AjaxResult.success(flag);
	}

	/**
	 * 查询营业执照办理信息
	 */
	@RequiresPermissions("business:personalMerchant:applyBusinessLicenseInfo")
	@PostMapping("/applyBusinessLicenseInfo")
	@Log(title = "查询营业执照办理信息",businessType = BusinessType.UPDATE)
	@ResponseBody
	@ApiOperation("查询营业执照办理信息")
	public TableDataInfo applyBusinessLicenseInfo(ApplyForLicenseQueryDTO applyForLicenseQueryDTO){
		startPage();
		List<ApplyLicenseInfoDTO> applyLicenseInfoDTOS= personalMerchantService.selectApplyBusinessLicenseInfo(applyForLicenseQueryDTO);
		return getDataTable(applyLicenseInfoDTOS);
	}


	@GetMapping("/getCTerminalSign")
	@Log(title = "获取C端签约",businessType = BusinessType.OTHER)
	@ResponseBody
	@ApiOperation("获取C端签约")
	public AjaxResult getCTerminalSign(@ApiParam("申请编号")String applyNumber){
		JSONObject jsonObject;
		try {
			jsonObject = personalMerchantService.getCTerminalSign(applyNumber);
		}catch (BusinessException e){
			logger.info("/business/personalMerchant/getCTerminalSign, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}catch (Exception e){
			logger.error("/business/personalMerchant/getCTerminalSign, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}
		return AjaxResult.success(jsonObject);
	}

	/**
	 * 审核入网申请
	 */
	@RequiresPermissions("business:personalMerchant:checkPersonalMerchantInfo")
	@PostMapping("/checkPersonalMerchantInfo")
	@Log(title = "审核入网申请",businessType = BusinessType.UPDATE)
	@ResponseBody
	@ApiOperation("审核入网申请")
	public AjaxResult checkPersonalMerchantInfo(@RequestBody CheckPersonalMerchantInfo checkPersonalMerchantInfo){
		boolean flag;
		try {
			flag = personalMerchantService.checkPersonalMerchantInfo(checkPersonalMerchantInfo);
		}catch (BusinessException e){
			logger.info("/business/personalMerchant/checkPersonalMerchantInfo, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}catch (Exception e){
			logger.error("/business/personalMerchant/checkPersonalMerchantInfo, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}
		return AjaxResult.success(flag);
	}

	/**
	 * 发布工单
	 */
//	@RequiresPermissions("business:personalMerchant:export")
	@PostMapping("/publishWorkOrder")
	@ResponseBody
	@ApiOperation("发布工单")
	public AjaxResult publishWorkOrder(@RequestBody PublishWokeOrderDTO publishWokeOrderDTO)
	{
		log.info("/business/personalMerchant/publishWorkOrder 入参:{}",JSONObject.toJSONString(publishWokeOrderDTO));
		try{
			workOrderService.publishWorkOrder(publishWokeOrderDTO);
		}catch (BusinessException e){
			logger.info("/business/personalMerchant/publishWorkOrder, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}catch (Exception e){
			logger.error("/business/personalMerchant/publishWorkOrder, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}

		return AjaxResult.success();
	}


	@GetMapping("/downloadWorOrder")
//	@ResponseBody
	@ApiOperation("下载工单相关的东西")
	public void downloadWorOrder(String url, HttpServletResponse response)
	{
		log.info("/business/personalMerchant/downloadWorOrder 入参:{}",url);
		try{
			workOrderService.downloadWorOrder(url, response);
		}catch (BusinessException e){
			logger.info("/business/personalMerchant/downloadWorOrder, {}",e.getMessage());
//			return AjaxResult.error(e.getMessage());
		}catch (Exception e){
			logger.error("/business/personalMerchant/downloadWorOrder, {}",e.getMessage());
//			return AjaxResult.error(e.getMessage());
		}

//		return AjaxResult.success();
	}


	@GetMapping("/queryNameAndIdByApplyNum")
	@ResponseBody
	@ApiOperation("根据申请编号获取姓名和身份证号")
	public AjaxResult queryNameAndIdByApplyNum(@ApiParam("申请编号") String applyNumber)
	{
		log.info("/business/personalMerchant/queryNameAndIdByApplyNum 入参:{}",applyNumber);
		PersonalMerchant personalMerchant;
		try{
			personalMerchant = workOrderService.queryNameAndIdByApplyNum(applyNumber);
		}catch (BusinessException e){
			logger.info("/business/personalMerchant/queryNameAndIdByApplyNum, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}catch (Exception e){
			logger.error("/business/personalMerchant/queryNameAndIdByApplyNum, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}

		return AjaxResult.success(personalMerchant);
	}


	@GetMapping("/closeWorkOrder")
	@ResponseBody
	@ApiOperation("关闭工单")
	public AjaxResult closeWorkOrder(@ApiParam("工单编号") String workOrderSerialNumber)
	{
		log.info("/business/personalMerchant/closeWorkOrder 入参:{}",workOrderSerialNumber);
		try{
			workOrderService.closeWorkOrder(workOrderSerialNumber);
		}catch (BusinessException e){
			logger.info("/business/personalMerchant/closeWorkOrder, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}catch (Exception e){
			logger.error("/business/personalMerchant/closeWorkOrder, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}

		return AjaxResult.success();
	}

	@GetMapping("/disPoseWorkOrder")
	@ResponseBody
	@ApiOperation("处理工单")
	public AjaxResult disPoseWorkOrder(@ApiParam("工单编号") String workOrderSerialNumber)
	{
		log.info("/business/personalMerchant/disPoseWorkOrder 入参:{}",workOrderSerialNumber);
		try{
			workOrderService.disPoseWorkOrder(workOrderSerialNumber);
		}catch (BusinessException e){
			logger.info("/business/personalMerchant/disPoseWorkOrder, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}catch (Exception e){
			logger.error("/business/personalMerchant/disPoseWorkOrder, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}

		return AjaxResult.success();
	}

	@PostMapping("/listWorkOrder")
	@ResponseBody
	@ApiOperation("查询工单列表")
	public TableDataInfo listWorkOrder(ListWorkOrderDTO listWorkOrderDTO)
	{
		log.info("/business/personalMerchant/listWorkOrder 入参:{}",JSONObject.toJSONString(listWorkOrderDTO));
		List<ListWorOrderResultDTO> listWorOrderResultDTOS = null;
		try{
			startPage();
			listWorOrderResultDTOS = workOrderService.listWorkOrder(listWorkOrderDTO);
		}catch (BusinessException e){
			logger.info("/business/personalMerchant/listWorkOrder, {}",e.getMessage());

		}catch (Exception e){
			logger.error("/business/personalMerchant/listWorkOrder, {}",e.getMessage());
		}

		return getDataTable(listWorOrderResultDTOS);
	}


	@GetMapping("/queryWorkOrderDetail")
	@ResponseBody
	@ApiOperation("查询工单详情")
	public AjaxResult queryWorkOrderDetail(@ApiParam("工单编号") String workOrderSerialNumber )
	{
		log.info("/business/personalMerchant/queryWorkOrderDetail 入参:{}",workOrderSerialNumber);
		WorkOrder workOrder;
		try{
			 workOrder = workOrderService.queryWorkOrderDetail(workOrderSerialNumber);
		}catch (BusinessException e){
			logger.info("/business/personalMerchant/queryWorkOrderDetail, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}catch (Exception e){
			logger.error("/business/personalMerchant/queryWorkOrderDetail, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}

		return AjaxResult.success(workOrder);
	}

	/**
	 * 导出营业执照办理信息
	 */
	@RequiresPermissions("business:personalMerchant:export")
    @PostMapping("/export")
    @ResponseBody
	@ApiOperation("导出营业执照办理信息")
    public AjaxResult export(ApplyForLicenseQueryDTO applyForLicenseQueryDTO)
    {
    	List<ApplyLicenseInfoDTO> applyForLicenseDTOS = personalMerchantService.exportApplyBusinessLicenseInfo(applyForLicenseQueryDTO);
        ExcelUtil<ApplyLicenseInfoDTO> util = new ExcelUtil<ApplyLicenseInfoDTO>(ApplyLicenseInfoDTO.class);
        return util.exportExcel(applyForLicenseDTOS, "applyForLicenseDTOS");
    }

	/**
	 * 查看营业执照办理详情
	 */
	@RequiresPermissions("business:personalMerchant:showApplyLicenseDetailInfo")
	@PostMapping("/showApplyLicenseDetailInfo")
	@Log(title = "查看营业执照办理详情",businessType = BusinessType.SELECT)
	@ResponseBody
	@ApiOperation("查看营业执照办理详情")
	public AjaxResult showApplyLicenseDetailInfo(Integer applyId){
		ApplyLicenseDetailInfoDTO applyLicenseDetailInfoDTO;
		try {
			applyLicenseDetailInfoDTO = personalMerchantService.showApplyLicenseDetailInfo(applyId);
		}catch (BusinessException e){
			logger.info("/business/personalMerchant/showApplyLicenseDetailInfo, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}catch (Exception e){
			logger.error("/business/personalMerchant/showApplyLicenseDetailInfo, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}
		return AjaxResult.success(applyLicenseDetailInfoDTO);
	}

	/**
	 * 上传营业执照
	 */
	@RequiresPermissions("business:personalMerchant:toUploadLicense")
	@Log(title = "上传营业执照",businessType = BusinessType.UPDATE)
	@PostMapping("/toUploadLicense")
	@ResponseBody
	@ApiOperation("上传营业执照")
	public AjaxResult toUploadLicense(Integer applyId, String url, String time){
		try {
			personalMerchantService.uploadLicense(applyId, url, time);
		} catch (Exception e) {
			logger.info("/business/personalMerchant/toUploadLicense, {}",e.getMessage());
			return AjaxResult.error(e.getMessage());
		}
		return AjaxResult.success();
	}

    /**
     * 修改办理申请
     */
    @RequiresPermissions("business:personalMerchant:updateApplyLicenseInfo")
    @PostMapping("/updateApplyLicenseInfo")
    @Log(title = "修改营业执照办理",businessType = BusinessType.UPDATE)
    @ResponseBody
    @ApiOperation("修改营业执照办理")
    public AjaxResult updateApplyLicenseInfo(ApplyLicenseDetailInfoDTO applyLicenseDetailInfoDTO){
        boolean flag;
        try {
            flag = personalMerchantService.updateApplyLicenseInfo(applyLicenseDetailInfoDTO);
        }catch (BusinessException e){
            logger.info("/business/personalMerchant/updateApplyLicenseInfo, {}",e.getMessage());
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/business/personalMerchant/updateApplyLicenseInfo, {}",e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(flag);
    }

	/**
	 * 个体户执照信息
	 */
	@RequiresPermissions("business:personalMerchant:selectPersonalLicenseInfo")
	@PostMapping("/selectPersonalLicenseInfo")
	@Log(title = "个体户执照信息",businessType = BusinessType.SELECT)
	@ResponseBody
	@ApiOperation("个体户执照信息")
	public TableDataInfo selectPersonalLicenseInfo(PersonalLicenseQueryDTO personalLicenseQueryDTO){
		startPage();
		List<PersonalLicenseDTO> personalLicenseDTOS = personalMerchantService.selectPersonalLicenseInfo(personalLicenseQueryDTO);
		return getDataTable(personalLicenseDTOS);
	}

	/**
	 * 导出个体户执照信息
	 */
	@RequiresPermissions("business:personalMerchant:exportPersonalLicense")
	@PostMapping("/exportPersonalLicense")
	@ResponseBody
	@ApiOperation("导出个体户执照信息")
	public AjaxResult exportPersonalLicense(PersonalLicenseQueryDTO personalLicenseQueryDTO)
	{
		List<PersonalLicenseDTO> personalLicenseDTOS = personalMerchantService.exportPersonalLicenseInfo(personalLicenseQueryDTO);
		ExcelUtil<PersonalLicenseDTO> util = new ExcelUtil<PersonalLicenseDTO>(PersonalLicenseDTO.class);
		return util.exportExcel(personalLicenseDTOS, "personalLicenseDTOS");
	}

	/**
	 * 税务类型信息
	 */
	@RequiresPermissions("business:personalMerchant:selectTaxTypeInfo")
	@PostMapping("/selectTaxTypeInfo")
	@Log(title = "税务类型信息",businessType = BusinessType.SELECT)
	@ResponseBody
	@ApiOperation("税务类型信息")
	public TableDataInfo selectTaxTypeInfo(TaxTypeQueryDTO taxTypeQueryDTO){
		startPage();
		List<TaxTypeInfoDTO> taxTypeInfoDTOS = personalMerchantService.selectTaxTypeInfo(taxTypeQueryDTO);
		return getDataTable(taxTypeInfoDTOS);
	}

    /**
     * 导出税务类型信息
     */
    @RequiresPermissions("business:personalMerchant:exportTaxTypeInfo")
    @PostMapping("/exportTaxTypeInfo")
    @ResponseBody
    @ApiOperation("导出税务类型信息")
    public AjaxResult exportTaxTypeInfo(TaxTypeQueryDTO taxTypeQueryDTO)
    {
        List<TaxTypeInfoDTO> taxTypeInfoDTOS = personalMerchantService.selectTaxTypeInfo(taxTypeQueryDTO);
        ExcelUtil<TaxTypeInfoDTO> util = new ExcelUtil<TaxTypeInfoDTO>(TaxTypeInfoDTO.class);
        return util.exportExcel(taxTypeInfoDTOS, "taxTypeInfoDTOS");
    }

    /**
     * 批量删除税务类型
     */
    @RequiresPermissions("business:personalMerchant:batchDeleteTaxTypeInfo")
    @PostMapping("/batchDeleteTaxTypeInfo")
    @Log(title = "批量删除税务类型",businessType = BusinessType.UPDATE)
    @ResponseBody
    @ApiOperation("批量删除税务类型")
    public AjaxResult batchDeleteTaxTypeInfo(@RequestParam("taxTypeId") List<Integer> taxTypeId){
        boolean flag;
        try {
            flag = personalMerchantService.batchDeleteTaxTypeInfo(taxTypeId);
        }catch (BusinessException e){
            logger.info("/business/personalMerchant/batchDeleteTaxTypeInfo, {}",e.getMessage());
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/business/personalMerchant/batchDeleteTaxTypeInfo, {}",e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(flag);
    }

    /**
     * 新增税务类型
     */
    @RequiresPermissions("business:personalMerchant:addTaxTypeInfo")
    @PostMapping("/addTaxTypeInfo")
    @Log(title = "新增税务类型",businessType = BusinessType.UPDATE)
    @ResponseBody
    @ApiOperation("新增税务类型")
    public AjaxResult addTaxTypeInfo(@RequestBody TaxType taxType){
        boolean flag;
        try {
            flag = personalMerchantService.addTaxTypeInfo(taxType);
        }catch (BusinessException e){
            logger.info("/business/personalMerchant/addTaxTypeInfo, {}",e.getMessage());
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/business/personalMerchant/addTaxTypeInfo, {}",e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(flag);
    }

    /**
     * 修改税务类型
     */
    @RequiresPermissions("business:personalMerchant:updateTaxTypeInfo")
    @PostMapping("/updateTaxTypeInfo")
    @Log(title = "修改税务类型",businessType = BusinessType.UPDATE)
    @ResponseBody
    @ApiOperation("修改税务类型")
    public AjaxResult updateTaxTypeInfo(@RequestBody TaxType taxType){
        boolean flag;
        try {
            flag = personalMerchantService.updateTaxTypeInfo(taxType);
        }catch (BusinessException e){
            logger.info("/business/personalMerchant/updateTaxTypeInfo, {}",e.getMessage());
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/business/personalMerchant/updateTaxTypeInfo, {}",e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(flag);
    }

	/**
	 * 修改税务类型
	 */
	@GetMapping("/getTaxTypeInfoByTaxId")
	@Log(title = "根据税源地 税务类型id查询税务类型",businessType = BusinessType.SELECT)
	@ResponseBody
	@ApiOperation("根据税源地 税务类型id查询税务类型")
	public AjaxResult getTaxTypeInfoByTaxId(Integer taxId, Integer typId){
		List<TaxType> taxTypeInfoByTaxId = personalMerchantService.getTaxTypeInfoByTaxId(taxId, typId);
		return AjaxResult.success(taxTypeInfoByTaxId);
	}

}
