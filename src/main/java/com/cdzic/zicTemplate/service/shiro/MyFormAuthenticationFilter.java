package com.cdzic.zicTemplate.service.shiro;

import com.cdzic.zicTemplate.domain.resp.ResponseObj;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(FormAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {//登录页面form表单直接提交
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }//allow them to see the login page
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {//是否启用跟踪
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " + "Authentication url [" + getLoginUrl() + "]");
            }
            if (isAjax(request)) {//是ajax请求
//                response.getWriter().write(JSON.toJSONString(ResultBuilder.genExpResult(new AppBizException(AppExcCodesEnum.SESSION_TIMEOUT))));
                response.getWriter().write(JSONObject.fromObject(new ResponseObj(403)).toString());
            } else {
                this.saveRequestAndRedirectToLogin(request, response);
            }
            return false;
        }
    }

    private static boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


//    @Override
//    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
//        WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
//    }
}
