package com.mmtax.business.controller.login;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.mmtax.business.dto.LoginSuccessDTO;
import com.mmtax.business.dto.MerchantLoginDTO;
import com.mmtax.business.dto.MerchantRegisterDTO;
import com.mmtax.business.service.IMerchantInfoService;
import com.mmtax.common.core.controller.BaseController;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.redis.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: wangzhaoxu
 * @Date: 2019/7/12 9:45
 */
@Api(tags = "商户端登录")
@Controller
@RequestMapping("/merchant")
public class LoginController extends BaseController {

    @Autowired
    private IMerchantInfoService merchantInfoService;

    @Resource
    private DefaultKaptcha captchaProducerTemp;

    @ApiOperation(value = "获取图形验证码，验证码以base64的格式传输")
    @GetMapping(value = "/captcha")
    @ResponseBody
    public AjaxResult captcha() {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        Map<String, Object> map = new HashMap<>();
        try {
            //生产验证码字符串并保存到redis中
            String createText = captchaProducerTemp.createText();
            logger.info("image code is:" + createText);
            String token = UUID.randomUUID().toString().replace("-", "");
            map.put("token", token);
            //将token和验证码内容放入redis，token为key，验证码为value，默认超时时间为5分钟
            RedisUtil.put(token, createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = captchaProducerTemp.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
            //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
            captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
            // 对字节数组Base64编码
            map.put("img", new BASE64Encoder().encode(captchaChallengeAsJpeg).replaceAll("\r\n", ""));
            return AjaxResult.success(map);
        } catch (Exception e) {
            return AjaxResult.error();
        }
    }


    /**
     * 登录
     */
    @ApiOperation(value = "登录接口", response = LoginSuccessDTO.class)
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(@RequestBody MerchantLoginDTO merchantLoginDTO) {
        LoginSuccessDTO loginSuccessDTO = new LoginSuccessDTO();
        try {
            loginSuccessDTO = merchantInfoService.merchantLogin(merchantLoginDTO);
        } catch (BusinessException b) {
            logger.info("/merchant/login", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/login", e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(loginSuccessDTO);
    }

    @ApiOperation(value = "个人H5注册接口")
    @PostMapping("/register")
    @ResponseBody
    public AjaxResult register(@RequestBody MerchantRegisterDTO dto) {
        try {
            merchantInfoService.register(dto);
        } catch (BusinessException b) {
            logger.info("/merchant/register", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/register", e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "个人H5注册接口")
    @PostMapping("/getCode")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value =
                    "邮箱地址", required = true, paramType = "path")})
    public AjaxResult getCode(@ApiIgnore @RequestBody MerchantRegisterDTO dto) {
        try {
            merchantInfoService.getCode(dto);
        } catch (BusinessException b) {
            logger.info("/merchant/getCode", b);
            return AjaxResult.error(b.getMessage());
        } catch (Exception e) {
            logger.error("/merchant/getCode", e);
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }


}
