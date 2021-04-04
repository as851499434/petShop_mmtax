package com.mmtax.framework.filter;

import javax.servlet.http.*;

/**
 * @author Ljd
 * @date 2020/11/1
 */
public class MyRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletResponse response = null;

    public MyRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public HttpSession getSession(){
        HttpSession session = super.getSession();
        processSessionCookie(session);
        return session;
    }

    @Override
    public HttpSession getSession(boolean create){
        HttpSession session = super.getSession(create);
        processSessionCookie(session);
        return session;
    }

    private void processSessionCookie(HttpSession session){
        if (null == response || null == session) {
            // No response or session object attached, skip the pre processing
            return;
        }
        // cookieOverWritten - 用于过滤多个Set-Cookie头的标志
        Object cookieOverWritten = getAttribute("COOKIE_OVERWRITTEN_FLAG");
        if (null == cookieOverWritten && isSecure() && isRequestedSessionIdFromCookie() && session.isNew()) {
            // 当是https协议，且新session时，创建JSESSIONID cookie以欺骗浏览器
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            // 有效时间为浏览器打开或超时
            cookie.setMaxAge(-1);
            String contextPath = getContextPath();
            if ((contextPath != null) && (contextPath.length() > 0)) {
                cookie.setPath(contextPath);
            }
            else {
                cookie.setPath("/");
            }
            // 增加一个Set-Cookie头到response
            response.addCookie(cookie);
            // 过滤多个Set-Cookie头的标志
            setAttribute("COOKIE_OVERWRITTEN_FLAG", "true");
        }
    }
}
