package com.cdzic.zicTemplate.service.shiro;

import com.cdzic.zicTemplate.domain.resp.ResponseObj;
import net.sf.json.JSONObject;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @creator yaotao
 * @date 2018/10/8 10:26
 * @describe:
 */
public class MyUserFilter extends UserFilter {


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isAjax(request)) {
            response.getWriter().write(JSONObject.fromObject(new ResponseObj(403)).toString());
        } else {
            this.saveRequestAndRedirectToLogin(request, response);
        }
        return false;
    }

    private static boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
