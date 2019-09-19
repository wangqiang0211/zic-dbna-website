package com.cdzic.zicTemplate.service.shiro;

import com.cdzic.zicTemplate.domain.resp.ResponseObj;
import net.sf.json.JSONObject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @creator yaotao
 * @date 2019/3/18 10:14
 * @describe:
 */
public class MyPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        if (isAjax(request)) {
//            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().write(JSONObject.fromObject(new ResponseObj(401,"无权限！")).toString());
            return false;
        } else {
            return super.onAccessDenied(request, response);
        }
    }

    private static boolean isAjax(ServletRequest request) {
        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
