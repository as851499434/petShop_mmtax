package com.mmtax.web.controller.appletspublic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mmtax.business.domain.*;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.PersonalMerchantMapper;
import com.mmtax.business.mapper.TaxSounrceCompanyMapper;
import com.mmtax.business.mapper.TaxTypeMapper;
import com.mmtax.business.service.*;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.enums.DeleteEnum;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.exception.PaymentException;
import com.mmtax.common.page.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "小程序和公众号个体工商户")
@Controller
@RequestMapping("/appletspublic/perMerchant")
@Slf4j
public class ApPersonMerchantController extends BaseController {

    @Autowired
    IWechatInfoService wechatInfoService;
    @Autowired
    IPersonalMerchantService personalMerchantService;
    @Autowired
    TaxTypeMapper taxTypeMapper;
    @Autowired
    ICommonProblemService commonProblemService;
    @Autowired
    IPerMerApplyScheduleService perMerApplyScheduleService;
    @Autowired
    TaxSounrceCompanyMapper taxSounrceCompanyMapper;
    @Autowired
    PersonalMerchantMapper personalMerchantMapper;
    @Resource
    private IWorkOrderService workOrderService;
    /**
     * 微信授权登录
     * @return
     */
    @ApiOperation(value = "微信授权登录")
    @GetMapping(value = "/wechatLogin")
    @ResponseBody
    public AjaxResult wechatLogin(@RequestParam String code){
        logger.info("/appletspublic/perMerchant/wechatLogin，入参：code={}",code);
        JSONObject result;
        try{
            result = wechatInfoService.wechatLogin(code);
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/wechatLogin",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/wechatLogin",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    /**
     * 常见问题显示
     * @return
     */
    @ApiOperation(value = "常见问题显示")
    @GetMapping(value = "/normalQuestion")
    @ResponseBody
    public AjaxResult normalQuestion(){
        logger.info("/appletspublic/perMerchant/normalQuestion");
        List<CommonProblem> result;
        try{
            result = commonProblemService.selectCommonProblemList(null,null,null);
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/normalQuestion",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/normalQuestion",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    /**
     * 获取税源地下拉表
     * @return
     */
    @ApiOperation(value = "获取税源地下拉表")
    @GetMapping(value = "/getDownDropTaxSource")
    @ResponseBody
    public AjaxResult getDownDropTaxSource(){
        logger.info("/appletspublic/perMerchant/getDownDropTaxSource");
        List<TaxSounrceCompany> result;
        try{

            TaxSounrceCompany query = new TaxSounrceCompany();
            query.setChannel("ONLINE");
            List<TaxSounrceCompany> taxSounrceCompanies = taxSounrceCompanyMapper.select(query);
            result = new ArrayList<>();
            taxSounrceCompanies.forEach(one->{
                TaxType queryTaxType = new TaxType();
                queryTaxType.setTaxSounrceCompanyId(one.getId());
                queryTaxType.setDelStatus(DeleteEnum.UN_DELETE.getCode());
                int count = taxTypeMapper.selectCount(queryTaxType);
                if(count > 0){
                    result.add(one);
                }
            });
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/getDownDropTaxSource",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/getDownDropTaxSource",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    /**
     * 获取行业类型下拉表
     * @return
     */
    @ApiOperation(value = "获取行业类型下拉表")
    @GetMapping(value = "/getDownDropIndustry")
    @ResponseBody
    public AjaxResult getDownDropIndustry(@RequestParam Integer taxSourceId){
        logger.info("/appletspublic/perMerchant/getDownDropIndustry，入参：taxSourceId={}",taxSourceId);
        List<TaxType> result;
        try{
            TaxType query = new TaxType();
            query.setTaxSounrceCompanyId(taxSourceId);
            query.setDelStatus(DeleteEnum.UN_DELETE.getCode());
            result = taxTypeMapper.select(query);
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/getDownDropIndustry",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/getDownDropIndustry",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    /**
     * 判断并获取缓存信息
     * @return
     */
    @ApiOperation(value = "判断并获取缓存信息")
    @GetMapping(value = "/judgeAndGetAddInfo")
    @ResponseBody
    public AjaxResult judgeAndGetAddInfo(@RequestParam Integer wechatInfoId){
        logger.info("/appletspublic/perMerchant/judgeAndGetAddInfo，入参：wechatInfoId={}", wechatInfoId);
        Map<String,Object> result = new HashMap<>(4);
        try{
            PersonalMerchant personalMerchant = personalMerchantService.judgeAndGetAddInfo(wechatInfoId);
            TaxType taxType = new TaxType();
            if(null != personalMerchant && null != personalMerchant.getTaxTypeId()) {
                taxType = taxTypeMapper.selectByPrimaryKey(personalMerchant.getTaxTypeId());
            }
            result.put("taxType",taxType);
            if(null == personalMerchant){
                result = null;
            }else if(null == personalMerchant.getApplyName()){
                result.put("step",1);
                result.put("personalMerchant",personalMerchant);
            }else if(null == personalMerchant.getBusinessLicenseName()){
                result.put("step",2);
                result.put("personalMerchant",personalMerchant);
            }else if(null != personalMerchant.getBusinessLicenseName()){
                result.put("step",3);
                result.put("personalMerchant",personalMerchant);
            }
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/judgeAndGetAddInfo",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/judgeAndGetAddInfo",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    /**
     * 上传校验信息
     * @return
     */
    @ApiOperation(value = "上传校验信息")
    @PostMapping(value = "/addVerfyInfo")
    @ResponseBody
    public AjaxResult addVerfyInfo(@RequestBody VerfyInfoDTO dto){
        logger.info("/appletspublic/perMerchant/addVerfyInfo，入参：{}", JSON.toJSONString(dto));
        try{
            personalMerchantService.addVerfyInfo(dto);
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/addVerfyInfo",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/addVerfyInfo",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 上传注册信息
     * @return
     */
    @ApiOperation(value = "上传注册信息")
    @PostMapping(value = "/addRegisterInfo")
    @ResponseBody
    public AjaxResult addRegisterInfo(@RequestBody RegisterInfoDTO dto){
        logger.info("/appletspublic/perMerchant/addRegisterInfo，入参：{}",JSON.toJSONString(dto));
        try{
            personalMerchantService.addRegisterInfo(dto);
        }catch (BusinessException | PaymentException e){
            logger.info("/appletspublic/perMerchant/addRegisterInfo",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/addRegisterInfo",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 生成营业执照名字
     * @return
     */
    @ApiOperation(value = "生成营业执照名字")
    @GetMapping(value = "/getBusinessLicenseName")
    @ResponseBody
    public AjaxResult getBusinessLicenseName(Integer wechatInfoId, String region){
        logger.info("/appletspublic/perMerchant/getBusinessLicenseName，入参：{}",JSON.toJSONString(wechatInfoId));
        String businessLicenseName;
        try{
            businessLicenseName = personalMerchantService.getBusinessLicenseName(wechatInfoId, region);
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/getBusinessLicenseName",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/getBusinessLicenseName",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success("success",businessLicenseName);
    }
    /**
     * 生成营业执照名字
     * @return
     */
    @ApiOperation(value = "通过主键id生成营业执照名字")
    @GetMapping(value = "/getBusinessLicenseNameById")
    @ResponseBody
    public AjaxResult getBusinessLicenseNameById(Integer id,String region){
        String businessLicenseName;
        try{
            businessLicenseName = personalMerchantService.getBusinessLicenseNameById(id, region);
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/getBusinessLicenseNameById",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/getBusinessLicenseNameById",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success("success",businessLicenseName);
    }
    /**
     * 上传营业资料
     * @return
     */
    @ApiOperation(value = "上传营业资料")
    @PostMapping(value = "/addBusinessInfo")
    @ResponseBody
    public AjaxResult addBusinessInfo(@RequestBody BusinessInfoDTO dto){
        logger.info("/appletspublic/perMerchant/addBusinessInfo，入参：{}",JSON.toJSONString(dto));
        try{
            personalMerchantService.addBusinessInfo(dto);
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/addBusinessInfo",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/addBusinessInfo",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }


    /**
     * 工单记录
     * @return
     */
    @ApiOperation(value = "工单记录")
    @GetMapping(value = "/workOrderRecord")
    @ResponseBody
    public AjaxResult workOrderRecord(@ApiParam("wechatInfoId") Integer wechatInfoId){
        logger.info("/appletspublic/perMerchant/workOrderRecord，入参：{}",JSON.toJSONString(wechatInfoId));
        List<WorkOrderRecordResultDTO> workOrderRecordResultDTOS;
        try{
            workOrderRecordResultDTOS = workOrderService.workOrderRecord(wechatInfoId);
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/workOrderRecord",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/workOrderRecord",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(workOrderRecordResultDTOS);
    }


    /**
     * 工单详情
     * @return
     */
    @ApiOperation(value = "工单详情")
    @GetMapping(value = "/workOrderDetail")
    @ResponseBody
    public AjaxResult workOrderRecord(@ApiParam("workOrderSerialNumber") String workOrderSerialNumber){
        logger.info("/appletspublic/perMerchant/workOrderDetail，入参：{}",workOrderSerialNumber);
        WorkOrder workOrder;
        try{
             workOrder = workOrderService.queryWorkOrderDetail(workOrderSerialNumber);
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/workOrderDetail",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/workOrderDetail",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(workOrder);
    }

    @PostMapping("/submitData")
    @ResponseBody
    @ApiOperation("提交资料")
    public AjaxResult submitData(@RequestBody WorkOrderFeedBackDTO workOrderFeedBackDTO)
    {
        log.info("/business/personalMerchant/submitData 入参:{}",JSONObject.toJSONString(workOrderFeedBackDTO));
        try{
			workOrderService.submitData(workOrderFeedBackDTO);
        }catch (BusinessException e){
            logger.info("/business/personalMerchant/submitData, {}",e.getMessage());
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/business/personalMerchant/submitData, {}",e.getMessage());
            return AjaxResult.error(e.getMessage());
        }

        return AjaxResult.success();
    }

    /**
     * 申请并同意签字
     * @return
     */
    @ApiOperation(value = "申请并同意签字")
    @GetMapping(value = "/applyAndSign")
    @ResponseBody
    public AjaxResult applyAndSign(@RequestParam Integer wechatInfoId){
        logger.info("/appletspublic/perMerchant/applyAndSign，入参：wechatInfoId={}",wechatInfoId);
        String result;
        try{
            result = personalMerchantService.applyAndSign(wechatInfoId);
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/applyAndSign",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/applyAndSign",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    /**
     * 我的订单
     * @return
     */
    @ApiOperation(value = "我的订单")
    @GetMapping(value = "/getOrderList")
    @ResponseBody
    public AjaxResult getOrderList(@RequestParam Integer wechatInfoId,
                                   @RequestParam(required = false) Integer status,
                                   @RequestParam(required = false,defaultValue = "1") Integer currentPage,
                                   @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        logger.info("/appletspublic/perMerchant/getOrderList，入参：wechatInfoId={}",wechatInfoId);
        Page<PerMerInfoDTO> result;
        try{
            result = personalMerchantService.getOrderList(wechatInfoId,status,currentPage,pageSize);
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/getOrderList",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/getOrderList",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    /**
     * 获取单个已申请信息
     * @return
     */
    @ApiOperation(value = "获取单个已申请信息")
    @GetMapping(value = "/getOneOrder")
    @ResponseBody
    public AjaxResult getOneOrder(@RequestParam Integer id){
        logger.info("/appletspublic/perMerchant/getOneOrder，入参：id={}",id);
        PerMerInfoDTO result;
        try{
            result = personalMerchantMapper.getOneOrder(id);
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/getOneOrder",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/getOneOrder",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    /**
     * 修改订单重新提交
     * @return
     */
    @ApiOperation(value = "修改订单重新提交")
    @PostMapping(value = "/updateOrder")
    @ResponseBody
    public AjaxResult updateOrder(@RequestBody PersonalMerchant dto){
        logger.info("/appletspublic/perMerchant/updateOrder，入参：{}",JSON.toJSONString(dto));
        try{
            personalMerchantService.updateOrder(dto);
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/updateOrder",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/updateOrder",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 查询相关流程日志
     * @return
     */
    @ApiOperation(value = "查询相关流程日志")
    @GetMapping(value = "/getPerMerApplySchedule")
    @ResponseBody
    public AjaxResult getPerMerApplySchedule(@RequestParam Integer applyId){
        logger.info("/appletspublic/perMerchant/getPerMerApplySchedule，入参：apply={}",applyId);
        List<ApplyScheduleDTO> result;
        try{
            result = perMerApplyScheduleService.getPerMerApplySchedule(applyId);
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/getPerMerApplySchedule",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/getPerMerApplySchedule",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    /**
     * 我的营业执照
     * @return
     */
    @ApiOperation(value = "我的营业执照")
    @GetMapping(value = "/getBusinessLicenses")
    @ResponseBody
    public AjaxResult getBusinessLicenses(@RequestParam Integer wechatInfoId){
        logger.info("/appletspublic/perMerchant/getBusinessLicenses，入参：wechatInfoId={}",wechatInfoId);
        List<BusinessLicenseReqDTO> result;
        try{
            result = personalMerchantService.getBusinessLicenses(wechatInfoId);
        }catch (BusinessException e){
            logger.info("/appletspublic/perMerchant/getBusinessLicenses",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/perMerchant/getBusinessLicenses",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

}
