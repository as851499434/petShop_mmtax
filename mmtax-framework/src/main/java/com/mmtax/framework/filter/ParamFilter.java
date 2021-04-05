package com.mmtax.framework.filter;

import com.alibaba.fastjson.JSON;
import com.mmtax.common.constant.RequestContans;
import com.mmtax.common.context.ApiContext;
import com.mmtax.common.core.domain.AjaxResult;
import com.mmtax.common.utils.IpUtils;
import com.mmtax.common.utils.StringUtils;
import com.mmtax.common.utils.redis.RedisTimeConstans;
import com.mmtax.common.utils.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ParamFilter implements Filter{

    private static final Logger logger = LoggerFactory.getLogger(ParamFilter.class);
    @Autowired
    private ApiContext apiContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        MyRequestWrapper myRequest = new MyRequestWrapper(request);
        myRequest.setResponse(httpServletResponse);
        logger.info("请求ip:{},uri:{},method:{},parameters:{}", IpUtils.getIpAddr(request), request.getRequestURI(),
            request.getMethod(), JSON.toJSON(request.getParameterMap()));
        String uri = request.getRequestURI();
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,token");
        if (!("OPTIONS".equalsIgnoreCase(request.getMethod()))) {
            if (!request.getRequestURI().contains("/login")
                    && !request.getRequestURI().contains("/logout")
                    && !request.getRequestURI().contains("/js")
                    && !request.getRequestURI().contains("/css")
                    && !request.getRequestURI().contains("/system")
                    && !request.getRequestURI().contains("/tool")
                    && !request.getRequestURI().contains("/index")
                    && !request.getRequestURI().contains("/swagger")
                    && !request.getRequestURI().contains("/monitor/")
                    && !request.getRequestURI().contains("/fonts/")
                    && !request.getRequestURI().contains("/ajax")
                    && !request.getRequestURI().contains("/img")
                    && !request.getRequestURI().contains("/mmtax")
                    && !request.getRequestURI().contains("/favicon.ico")
                    && !request.getRequestURI().contains("/webjars")
                    && !request.getRequestURI().contains("captcha")
                    && !request.getRequestURI().contains("/csrf")
                    && !request.getRequestURI().contains("/docs")
                    && !request.getRequestURI().contains("/common")
                    && !request.getRequestURI().contains("/merchant/captcha")
                    && !request.getRequestURI().contains("/merchant/login")
                    && !request.getRequestURI().contains("/v2")
                    && !request.getRequestURI().contains("/captcha")
                    && !request.getRequestURI().contains("/profile")
                    && !request.getRequestURI().contains("/notify")
                    && !request.getRequestURI().contains("/mmtax/payment/notify")
                    && !request.getRequestURI().contains("/mmtax/payment/notifyAlipay")
                    && !request.getRequestURI().contains("/mmtax/payment/notifySign")
                    && !request.getRequestURI().contains("/mmtax/payment/queryEnterprise")
                    && !request.getRequestURI().contains("/mmtax/payment/querySignStatus")
                    && !request.getRequestURI().equals("/merchant/payment/uploadFile")
                    && !request.getRequestURI().equals("/merchant/payment/uploadFilePromotion")
                    && !request.getRequestURI().equals("/merchant/payment/uploadFileSign")
                    && !request.getRequestURI().equals("/")
                    && !request.getRequestURI().equals("/mmtax/payment/downOnlineFile")
                    && !request.getRequestURI().contains("merchant/replenish/test")
                    && !request.getRequestURI().contains("merchant/replenish/querySubAccountBalance")
                    && !request.getRequestURI().contains("merchant/replenish/infoQuery")
                    && !request.getRequestURI().contains("merchant/sign/getSignDownUrl")
                    && !request.getRequestURI().contains("merchant/sign/createTaxSourceAccount")
                    && !request.getRequestURI().contains("merchant/replenish/withdrawToCardHaiNan")
                    && !request.getRequestURI().contains("merchant/replenish/withdrawToCardTaiNing")
                    && !request.getRequestURI().contains("merchant/replenish/refundFail")
                    && !request.getRequestURI().contains("/merchant/replenish/initTblCusLinkMerInfo")
                    && !request.getRequestURI().contains("merchant/replenish/refundTranferOrder")
                    && !request.getRequestURI().contains("/mmtax/payment/changeMerchantTax")
            ) {
                if (uri.startsWith("/merchant")) {
                    //商户端 接口校验token是否有效
                    String token = request.getHeader(RequestContans.TOKEN_NAME);
//                    MerchantInfo merchantInfo = RedisUtil.get(token, MerchantInfo.class);
//                    apiContext.setCurrentProviderId(merchantInfo.getProviderId().longValue());
                    logger.info("ParamFilter app token:" + token);
                    if (StringUtils.isEmpty(token) || !RedisUtil.exists(token)) {
                        httpServletResponse.getWriter().write(JSON.toJSONString(AjaxResult.error("token invalid")));
                        return;
                    }
                    logger.info("登录的商户信息：{}",RedisUtil.get(token));
                    RedisUtil.put(token, RedisUtil.get(token), RedisTimeConstans.THIRTY, TimeUnit.MINUTES);
                }
            }
            filterChain.doFilter(myRequest, servletResponse);
        }else{
            filterChain.doFilter(myRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
