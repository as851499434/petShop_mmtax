package com.mmtax.web.controller.appletspublic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.mmtax.business.domain.CustomerAccount;
import com.mmtax.business.domain.CustomerInfo;
import com.mmtax.business.dto.*;
import com.mmtax.business.mapper.CustomerAccountMapper;
import com.mmtax.business.service.*;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.page.Page;
import com.mmtax.common.utils.redis.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 员工 信息操作处理
 *
 * @author meimiao
 * @date 2020-07-09
 */
@Api(tags = "小程序和公众号员工管理")
@Controller
@RequestMapping("/appletspublic/customerInfo")
public class ApCustomerInfoController extends BaseController {

    @Autowired
    ICustomerInfoService customerInfoService;
    @Autowired
    private ICustomerBankcardInfoService customerBankcardInfoService;
    @Autowired
    private ICustomerAlipayInfoService customerAlipayInfoService;
    @Autowired
    private IEsignFlowService esignFlowService;
    @Autowired
    private DefaultKaptcha captchaProducerTemp;
    @Autowired
    CustomerAccountMapper customerAccountMapper;

    /**
     * 微信授权登录
     * @return
     */
    @ApiOperation(value = "微信授权登录")
    @GetMapping(value = "/wechatLogin")
    @ResponseBody
    public AjaxResult wechatLogin(@RequestParam String code,@RequestParam(required = false) Integer taxSourceId){
        logger.info("/appletspublic/customerInfo/wechatLogin，入参：code={}",code);
        JSONObject result;
        try{
            result = customerInfoService.wechatLogin(code,taxSourceId);
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/wechatLogin",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/wechatLogin",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    /**
     * 小程序登录
     * @param code 参数
     * @return
     */
    @ApiOperation(value = "小程序登录")
    @GetMapping("/apLogin")
    @ResponseBody
    public AjaxResult apLogin(@RequestParam String code){
        logger.info("/appletspublic/customerInfo/apLogin,入参：code = {}",code);
        JSONObject result;
        try{
            result = customerInfoService.apLogin(code);
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/apLogin",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/apLogin",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }
    /**
     * 退出
     * @return
     */
    @ApiOperation(value = "退出")
    @GetMapping(value = "/quit")
    @ResponseBody
    public AjaxResult quit(@RequestParam Integer customerId){
        logger.info("/appletspublic/customerInfo/quit，入参：customerId={}",customerId);
        try{
            customerInfoService.quit(customerId);
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/quit",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/quit",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 实名认证发短信
     * @return
     */
    @ApiOperation(value = "实名认证发短信")
    @PostMapping(value = "/sendSmsCode")
    @ResponseBody
    public AjaxResult sendSmsCode(@RequestBody VerifyCustomerDTO dto){
        logger.info("/appletspublic/customerInfo/sendSmsCode，入参：{}", JSON.toJSONString(dto));
        try{
            customerInfoService.sendSmsCode(dto);
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/sendSmsCode",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/sendSmsCode",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 修改手机号发短信
     * @return
     */
    @ApiOperation(value = "修改手机号发短信")
    @PostMapping(value = "/sendSmsCodeCauseUpdateMobile")
    @ResponseBody
    public AjaxResult sendSmsCodeCauseUpdateMobile(@RequestBody VerifyCustomerDTO dto){
        logger.info("/appletspublic/customerInfo/sendSmsCodeCauseUpdateMobile，入参：{}", JSON.toJSONString(dto));
        try{
            customerInfoService.sendSmsCodeCauseUpdateMobile(dto);
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/sendSmsCodeCauseUpdateMobile",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/sendSmsCodeCauseUpdateMobile",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

//    @ApiOperation(value = "获取图形验证码，验证码以base64的格式传输")
//    @GetMapping(value = "/captcha")
//    @ResponseBody
//    public AjaxResult captcha() {
//        byte[] captchaChallengeAsJpeg = null;
//        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
//        Map<String, Object> map = new HashMap<>();
//        try {
//            //生产验证码字符串并保存到redis中
//            String createText = captchaProducerTemp.createText();
//            logger.info("image code is:" + createText);
//            String token = UUID.randomUUID().toString().replace("-", "");
//            map.put("token", token);
//            //将token和验证码内容放入redis，token为key，验证码为value，默认超时时间为5分钟
//            RedisUtil.put(token, createText);
//            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
//            BufferedImage challenge = captchaProducerTemp.createImage(createText);
//            ImageIO.write(challenge, "jpg", jpegOutputStream);
//            //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
//            captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//            // 对字节数组Base64编码
//            map.put("img", new BASE64Encoder().encode(captchaChallengeAsJpeg).replaceAll("\r\n", ""));
//            return AjaxResult.success(map);
//        } catch (Exception e) {
//            return AjaxResult.error();
//        }
//    }

    /**
     * 实名认证
     * @return
     */
    @ApiOperation(value = "实名认证")
    @PostMapping(value = "/verifyCustomer")
    @ResponseBody
    public AjaxResult verifyCustomer(@RequestBody VerifyCustomerDTO dto){
        logger.info("/appletspublic/customerInfo/verifyCustomer，入参：{}", JSON.toJSONString(dto));
        VerifyCustomerRespDTO resp;
        try{
            resp = customerInfoService.verifyCustomer(dto);
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/verifyCustomer",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/verifyCustomer",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(resp);
    }

    /**
     * 灵工切换税源地
     * @return
     */
    @ApiOperation(value = "灵工切换税源地")
    @GetMapping(value = "/changeTaxSource")
    @ResponseBody
    public AjaxResult changeTaxSource(@RequestParam Integer customerId){
        logger.info("/appletspublic/customerInfo/changeTaxSource，入参：customerId={}", customerId);
        List<ChangeTaxSourceDTO> result;
        try{
            result = customerInfoService.changeTaxSource(customerId);
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/changeTaxSource",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/changeTaxSource",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    /**
     * 灵工切换税源地
     * @return
     */
    @ApiOperation(value = "灵工切换税源地后的默认排序")
    @GetMapping(value = "/changeTaxSourceAfter")
    @ResponseBody
    public AjaxResult changeTaxSourceAfter(@RequestParam Integer customerId,@RequestParam Integer taxSourceId){
        logger.info("/appletspublic/customerInfo/changeTaxSourceAfter，入参：customerId={},taxSourceId={}"
                , customerId,taxSourceId);
        List<ChangeTaxSourceDTO> result;
        try{
            result = customerInfoService.changeTaxSourceAfter(customerId,taxSourceId);
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/changeTaxSourceAfter",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/changeTaxSourceAfter",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    /**
     * 主页信息
     * @return
     */
    @ApiOperation(value = "主页信息")
    @GetMapping(value = "/indexInfo")
    @ResponseBody
    public AjaxResult indexInfo(@RequestParam Integer customerId,@RequestParam Integer year
            ,@RequestParam(required = false,defaultValue = "10") Integer pageSize
            ,@RequestParam(required = false,defaultValue = "1") Integer currentPage){
        logger.info("/appletspublic/customerInfo/indexInfo，入参：customerId={},year={}," +
                "pageSize={},currentPage={},", customerId,year,pageSize,currentPage);
        IndexInfoDTO indexInfoDTO;
        try{
            indexInfoDTO = customerInfoService.indexInfo(customerId,pageSize,currentPage,year);
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/indexInfo",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/indexInfo",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(indexInfoDTO);
    }

    /**
     * 个人信息
     * @return
     */
    @ApiOperation(value = "个人信息")
    @GetMapping(value = "/getCustomerInfo")
    @ResponseBody
    public AjaxResult getCustomerInfo(@RequestParam Integer customerId){
        logger.info("/appletspublic/customerInfo/getCustomerInfo，入参：customerId={}", customerId);
        CustomerInfoDTO customerInfoDTO;
        try{
            customerInfoDTO = customerInfoService.getCustomerInfo(customerId);
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/getCustomerInfo",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/getCustomerInfo",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(customerInfoDTO);
    }

    /**
     * 修改手机号
     * @return
     */
    @ApiOperation(value = "修改手机号")
    @PostMapping(value = "/updateMobile")
    @ResponseBody
    public AjaxResult updateMobile(@RequestBody UpdateMobileDTO dto){
        logger.info("/appletspublic/customerInfo/updateMobile，入参：{}", JSON.toJSONString(dto));
        try{
            customerInfoService.updateMobile(dto);
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/updateMobile",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/updateMobile",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    /**
     * 余额详情
     * @return
     */
    @ApiOperation(value = "余额详情")
    @GetMapping(value = "/getCustomerAccount")
    @ResponseBody
    public AjaxResult getCustomerAccount(@RequestParam Integer customerId,@RequestParam String yearMonth){
        logger.info("/appletspublic/customerInfo/getCustomerAccount，入参：customerId={},yearMonth={}"
                , customerId,yearMonth);
        AccountInfoDTO accountInfoDTO;
        try{
            accountInfoDTO = customerInfoService.getCustomerAccount(customerId,yearMonth);
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/getCustomerAccount",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/getCustomerAccount",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(accountInfoDTO);
    }

    /**
     * 员工收入列表
     * @return
     */
    @ApiOperation(value = "员工收入提现列表")
    @GetMapping(value = "/getCustomerIncomeWithdrawList")
    @ResponseBody
    public AjaxResult getCustomerIncomeWithdrawList(@RequestParam Integer customerId,@RequestParam String yearMonth
            ,@RequestParam(required = false,defaultValue = "10") Integer pageSize
            ,@RequestParam(required = false,defaultValue = "1") Integer currentPage
            ,@RequestParam(required = false,defaultValue = "1") Integer type){
        logger.info("/appletspublic/customerInfo/getCustomerIncomeWithdrawList，入参：customerId={},yearMonth={}" +
                ",pageSize={},currentPage={}", customerId,yearMonth,pageSize,currentPage);
        Page page;
        try{
            if(2 == type){
                page = customerInfoService.getCustomerIncomeList(customerId,pageSize,currentPage,yearMonth);
            }else if(3 == type){
                page = customerInfoService.getCustomerWithdrawList(customerId,pageSize,currentPage,yearMonth);
            }else{
                page = customerInfoService.getCustomerIncomeWithdrawList(customerId,pageSize,currentPage,yearMonth);
            }
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/getCustomerIncomeWithdrawList",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/getCustomerIncomeWithdrawList",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(page);
    }

    /**
     * 查询可用银行卡支付宝信息
     * @return
     */
    @ApiOperation(value = "查询可用银行卡支付宝信息和可提现金额")
    @GetMapping(value = "/getBankcardAndAlipay")
    @ResponseBody
    public AjaxResult getBankcardAndAlipay(@RequestParam Integer customerId){
        logger.info("/appletspublic/customerInfo/getBankcardAndAlipay，入参：customerId={}", customerId);
        Map<String, Object> map = new HashMap<>(8);
        try{
            map.put("bankcardList",customerBankcardInfoService.listBankcard(customerId));
            map.put("alipayList",customerAlipayInfoService.listAlipay(customerId));
            CustomerAccount customerAccount = customerAccountMapper.selectByCustomerId(customerId);
            map.put("usableAmount",customerAccount.getUsableAmount());
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/getBankcardAndAlipay",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/getBankcardAndAlipay",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(map);
    }

    /**
     * 查询签约协议
     * @return
     */
    @ApiOperation(value = "查询签约协议")
    @GetMapping(value = "/getSignDocument")
    @ResponseBody
    public AjaxResult getSignDocument(@RequestParam String idCardNo){
        logger.info("/appletspublic/customerInfo/getSignDocument，入参：idCardNo={}", idCardNo);
        List<JSONObject> data;
        try{
            data = esignFlowService.getFlowIdByCustomerId(idCardNo);
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/getSignDocument",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/getSignDocument",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(data);
    }

    /**
     * 统计
     * @param yearMonth yyyy-MM
     * @return
     */
    @ApiOperation(value = "统计")
    @GetMapping(value = "/statistFlow")
    @ResponseBody
    public AjaxResult statistFlow(@RequestParam Integer customerId,
                                  @RequestParam String yearMonth){
        logger.info("/appletspublic/customerInfo/statistFlow，入参：customerId={},yearMonth={}"
                , customerId,yearMonth);
        StatistFlowRespDTO data;
        try{
            data = customerInfoService.statistFlow(customerId,yearMonth);
        }catch (BusinessException e){
            logger.info("/appletspublic/customerInfo/statistFlow",e);
            return AjaxResult.error(e.getMessage());
        }catch (Exception e){
            logger.error("/appletspublic/customerInfo/statistFlow",e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(data);
    }

}
