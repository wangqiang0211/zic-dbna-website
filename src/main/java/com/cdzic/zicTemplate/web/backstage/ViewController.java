package com.cdzic.zicTemplate.web.backstage;

import com.cdzic.zicTemplate.domain.entity.shiro.SysPermission;
import com.cdzic.zicTemplate.domain.entity.shiro.SysRole;
import com.cdzic.zicTemplate.domain.entity.shiro.SysUser;
import com.cdzic.zicTemplate.service.shiro.MyShiroRealm;
import com.cdzic.zicTemplate.service.shiro.SysPermissionService;
import com.cdzic.zicTemplate.service.shiro.SysRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ApiIgnore
@Controller
@RequestMapping("/bg")
public class ViewController {
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private MyShiroRealm myShiroRealm;

    /**
     * 登陆页面
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/login");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 主页面
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(ModelAndView modelAndView, HttpServletRequest request) {
        AuthorizationInfo authorizationInfo = myShiroRealm.getAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
        Collection<String> roles = authorizationInfo.getRoles();
        Collection<String> stringPermissions = authorizationInfo.getStringPermissions();
        modelAndView.setViewName("backstage/index");
        modelAndView.addObject("role", roles.iterator().next());
        List<String> permissions = new ArrayList<>(stringPermissions);
        modelAndView.addObject("account", SecurityUtils.getSubject().getPrincipal());
        modelAndView.addObject("path", request.getContextPath());
        modelAndView.addObject("permissions", permissions);
        return modelAndView;
    }

    /**
     * 欢迎页面
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/welcome"})
    public ModelAndView welcome(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/welcome");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }


    /**
     * 修改密码页面
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/tpl-password"})
    public ModelAndView tplPassword(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/fragment/tpl-password");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 消息
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/tpl-message"})
    public ModelAndView tplMessage(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/fragment/tpl-message");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 主题
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/tpl-theme"})
    public ModelAndView tplTheme(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/fragment/tpl-theme");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 权限
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/sys-permission"})
    public ModelAndView sysPermission(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/system/sys-permission");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 权限表单
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/sys-permission-form"})
    public ModelAndView sysPermissionAdd(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/system/sys-permission-form");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 角色
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/sys-role"})
    public ModelAndView sysRole(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/system/sys-role");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 角色表单
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/sys-role-form"})
    public ModelAndView sysRoleForm(ModelAndView modelAndView, HttpServletRequest request, String roleName) {
        modelAndView.setViewName("backstage/system/sys-role-form");
        modelAndView.addObject("path", request.getContextPath());
        modelAndView.addObject("permissions", sysPermissionService.findAll());
        if (null != roleName) {
            modelAndView.addObject("viewData", sysRoleService.findByRoleName(roleName).getPermissions());
        }
        return modelAndView;
    }

    /**
     * 管理员
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/sys-user"})
    public ModelAndView sysUser(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/system/sys-user");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 管理员表单
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = {"/sys-user-form"})
    public ModelAndView sysUserForm(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/system/sys-user-form");
        modelAndView.addObject("path", request.getContextPath());
        modelAndView.addObject("roles",sysRoleService.findAll());
//        modelAndView.addObject("seccondLevel",seccondLevelRepo.findAll());
        return modelAndView;
    }

    /**
     * 新闻列表
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/news-list")
    public ModelAndView newsList(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/news-list");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 添加/修改 新闻
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/news-from")
    public ModelAndView newsFrom(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/news-from");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }


    /**
     * 空调新闻列表
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/air-list")
    public ModelAndView airList(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/air-list");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }


    /**
     * 添加/修改 空调新闻
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/air-from")
    public ModelAndView airFrom(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/air-from");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 视频列表
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/video-list")
    public ModelAndView videoList(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/video-list");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }


    /**
     * 添加/修改 视频
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/video-from")
    public ModelAndView videoFrom(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/video-from");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 工程案例列表
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/project-list")
    public ModelAndView projectList(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/project-list");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 添加/修改 案例
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/project-from")
    public ModelAndView projectFrom(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/project-from");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 产品列表
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/product-list")
    public ModelAndView productList(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/product-list");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }


    /**
     * 添加/修改 产品
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/product-from")
    public ModelAndView productFrom(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/product-from");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }


    /**
     * 公司信息列表
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/contact-us-list")
    public ModelAndView contactUsList(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/contact-us-list");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }

    /**
     * 添加/修改 公司信息
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping("/contact-us-from")
    public ModelAndView contactUsFrom(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("backstage/business/contact-us-from");
        modelAndView.addObject("path", request.getContextPath());
        return modelAndView;
    }



}
