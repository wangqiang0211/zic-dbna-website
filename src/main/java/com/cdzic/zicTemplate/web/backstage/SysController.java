package com.cdzic.zicTemplate.web.backstage;

import com.cdzic.zicTemplate.dao.repo.sys.SysUserRepo;
import com.cdzic.zicTemplate.domain.entity.shiro.SysPermission;
import com.cdzic.zicTemplate.domain.entity.shiro.SysRole;
import com.cdzic.zicTemplate.domain.entity.shiro.SysUser;
import com.cdzic.zicTemplate.domain.resp.RespObjSqlItem;
import com.cdzic.zicTemplate.domain.resp.ResponseObj;
import com.cdzic.zicTemplate.domain.vo.sys.SysPermissionCVO;
import com.cdzic.zicTemplate.domain.vo.sys.SysRoleCVO;
import com.cdzic.zicTemplate.domain.vo.sys.SysUserCVO;
import com.cdzic.zicTemplate.domain.vo.sys.SysUserVSVO;
import com.cdzic.zicTemplate.service.shiro.SysPermissionService;
import com.cdzic.zicTemplate.service.shiro.SysRoleService;
import com.cdzic.zicTemplate.service.shiro.SysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @creator yaotao
 * @date 2018/8/30 14:17
 * @describe:
 */
@ApiIgnore
@RestController
@RequestMapping("/bg")
public class SysController {
    private static final Logger LOGGER = LogManager.getLogger(SysController.class);
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserService sysUserService;
//    @Autowired
//    private SeccondLevelRepo seccondLevelRepo;
    @Autowired
    private SysUserRepo sysUserRepo;


    /**
     * 权限查询/搜索
     *
     * @param page
     * @param limit
     * @param permission
     * @return
     */
    @GetMapping("/sys-permission/query")
    public ResponseObj sysPermissionQuery(@RequestParam int page,
                                          @RequestParam int limit,
                                          @RequestParam(required = false, value = "key1") String permission) {
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<SysPermission> permissionList = sysPermissionService.findByCondition(permission);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, null, new RespObjSqlItem(startPage.getTotal(), permissionList));
    }

    /**
     * 添加权限
     *
     * @param sysPermission
     * @return
     */
    @PostMapping("/sys-permission/add")
    public ResponseObj sysPermissionAdd(SysPermissionCVO sysPermission) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "添加权限成功！");
        try {
            sysPermissionService.save(new SysPermission(sysPermission.getPermission(), sysPermission.getDescription()));
        } catch (Exception e) {
            LOGGER.error("添加SysPermission失败！原因：{}", e.toString());
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_FAIL);
            responseObj.setErrorMsg("添加权限失败！原因：权限名重复");
        }
        return responseObj;
    }

    /**
     * 根据id修改权限信息
     *
     * @param sysPermissionCVO
     * @return
     */
    @PostMapping("/sys-permission/update")
    public ResponseObj adminPermissionUpdate(SysPermissionCVO sysPermissionCVO) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "修改权限成功！");
        sysPermissionService.modify(sysPermissionCVO);
        return responseObj;
    }

    /**
     * 根据id删除权限
     *
     * @param ids
     * @return
     */
    @PostMapping("/sys-permission/delete")
    public ResponseObj adminPermissionDelete(@RequestParam(value = "ids[]") Long[] ids) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "删除权限成功！");
        for (int i = 0; i < ids.length; i++) {
            sysPermissionService.delete(ids[i]);
        }
        return responseObj;
    }

    /**
     * 角色查询/搜索
     *
     * @param page
     * @param limit
     * @param roleName
     * @return
     */
    @GetMapping("/sys-role/query")
    public ResponseObj sysRoleQuery(@RequestParam int page,
                                    @RequestParam int limit,
                                    @RequestParam(required = false, value = "key1") String roleName) {
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<SysRole> roleList = sysRoleService.findByCondition(roleName);
//        System.out.println(roleList.size()+"查看查询的角色长度!");
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, null, new RespObjSqlItem(startPage.getTotal(), roleList));
    }

    /**
     * 添加角色
     *
     * @param sysRoleCVO
     * @param permissions
     * @return
     */
    @PostMapping("/sys-role/add")
    public ResponseObj sysRoleAdd(SysRoleCVO sysRoleCVO, @RequestParam(value = "permissions[]", defaultValue = "[]") String[] permissions) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "添加角色成功！");
        List<SysPermission> list = new ArrayList<>();
        for (String permission : permissions) {
            list.add(sysPermissionService.findByPermission(permission));
        }
        SysRole sysRole = new SysRole(sysRoleCVO.getRoleName(), sysRoleCVO.getDescription());
        sysRole.setPermissions(list);
        try {
            sysRoleService.save(sysRole);
        } catch (Exception e) {
            LOGGER.error("添加SysRole失败！原因：{}", e.toString());
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_FAIL);
            responseObj.setErrorMsg("添加角色失败！原因：角色名重复");
        }
        return responseObj;
    }

    /**
     * 根据id更新角色
     *
     * @param sysRoleCVO
     * @param permissions
     * @return
     */
    @PostMapping("/sys-role/update")
    public ResponseObj adminRoleUpdate(SysRoleCVO sysRoleCVO, @RequestParam(required = false, value = "permissions[]") String[] permissions) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "修改角色成功！");
        List<SysPermission> list = new ArrayList<>();
        if (null != permissions) {
            for (String permission : permissions) {
                list.add(sysPermissionService.findByPermission(permission));
            }
        }
        SysRole sysRole = new SysRole(sysRoleCVO.getRoleName(), sysRoleCVO.getDescription());
        sysRole.setPermissions(list);
        sysRoleService.modify(sysRoleCVO.getId(), sysRole);
        return responseObj;
    }

    /**
     * 根据id删除角色
     *
     * @param id
     * @return
     */
    @PostMapping("/sys-role/delete")
    public ResponseObj adminRoleDelete(@RequestParam long id) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "删除角色成功！");
        try {
            sysRoleService.delete(id);
        } catch (Exception e) {
            LOGGER.error("删除角色失败！原因：{}" + e.toString());
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_FAIL);
            responseObj.setErrorMsg("无法删除正在使用的角色！");
        }
        return responseObj;
    }

    /**
     * 管理员查询/搜索
     *
     * @param page
     * @param limit
     * @param searchKey
     * @param searchValue
     * @return
     */
    @GetMapping("/sys-user/query")
    public ResponseObj sysUserQuery(@RequestParam int page,
                                    @RequestParam int limit,
                                    @RequestParam(required = false, value = "searchKey") String searchKey,
                                    @RequestParam(required = false, value = "searchValue") String searchValue) {
        Page<Object> startPage = PageHelper.startPage(page, limit);
        List<SysUserVSVO> list = sysUserService.findByCondition(searchKey, searchValue);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, null, new RespObjSqlItem(startPage.getTotal(), list));
    }


    /**
     * 添加管理员
     *
     * @param sysUserCVO
     * @return
     */
    @PostMapping("/sys-user/add")
    public ResponseObj sysUserAdd(SysUserCVO sysUserCVO) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "添加管理员成功！");
        if (0 != sysUserRepo.countByEmail(sysUserCVO.getEmail())) {
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_FAIL);
            responseObj.setErrorMsg("该邮箱已注册！");
            return responseObj;
        }
        SysRole role = sysRoleService.findByRoleName(sysUserCVO.getRole());
        try {
            sysUserService.save(new SysUser(sysUserCVO.getAccount(), sysUserCVO.getPwd(), sysUserCVO.getPhoneNum(),
                    sysUserCVO.getEmail(), 1, new Date(), role));
        } catch (Exception e) {
            LOGGER.error("添加SysUser失败！原因：{}", e.toString());
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_FAIL);
            responseObj.setErrorMsg("添加管理员失败！原因：管理员账号重复");
        }
        return responseObj;
    }

    /**
     * 修改管理员
     *
     * @param sysUserCVO
     * @return
     */
    @PostMapping("/sys-user/update")
    public ResponseObj adminUpdate(SysUserCVO sysUserCVO) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "修改管理员成功！");
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        SysUser sysUser = sysUserService.findByAccount(sysUserCVO.getAccount());
        sysUser.setEmail(sysUserCVO.getEmail());
        sysUser.setPhoneNum(sysUserCVO.getPhoneNum());//如果是当前账号则只能修改手机邮箱
        if (!user.getAccount().equals(sysUserCVO.getAccount())) {
            SysRole role = sysRoleService.findByRoleName(sysUserCVO.getRole());
            if (null != role) {
                sysUser.setRole(role);
            }
        }
        sysUserService.save(sysUser);
        return responseObj;
    }

    /**
     * 启用管理员
     *
     * @param account
     * @return
     */
    @PostMapping("/sys-user/enable")
    public ResponseObj sysUserEnable(@RequestParam String account) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "已启用管理员！");
        sysUserService.enableUser(account);
        return responseObj;
    }

    /**
     * 停用管理员
     *
     * @param account
     * @return
     */
    @PostMapping("/sys-user/disable")
    public ResponseObj sysUserDisable(@RequestParam String account) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_FAIL, "已停用管理员！");
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (user.getAccount().equals(account)) {
            responseObj.setErrorMsg("不能停用当前账号！");
            return responseObj;
        }
        responseObj.setErrorCode(ResponseObj.ERROR_CODE_SUCCESS);
        sysUserService.disableUser(account);
        return responseObj;
    }

    /**
     * 删除管理员
     *
     * @param account
     * @return
     */
    @PostMapping("/admin/delete")
    public ResponseObj adminDelete(@RequestParam String account) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS);
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (user.getAccount().equals(account)) {
            responseObj.setErrorMsg("不能停用当前账号！");
        }
        return responseObj;
    }

    /**
     * 修改密码
     *
     * @param newPWD
     * @param oldPWD
     * @return
     */
    @PostMapping("/sys-user/update/pwd")
    public ResponseObj sysUserUpdatePwd(@RequestParam String newPWD, @RequestParam String oldPWD) {
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        int code = sysUserService.modifyPWD(sysUser.getAccount(), oldPWD, newPWD);
        if (2 == code) {
            return new ResponseObj(ResponseObj.ERROR_CODE_FAIL, "修改密码失败！原因：账号或原始密码错误");
        }
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "修改密码成功！", null);
    }

    /**
     * 重置密码
     *
     * @param account
     * @return
     */
    @PostMapping("/sys-user/reset/pwd")
    public ResponseObj sysUserResetPwd(@RequestParam String account) {
        sysUserService.resetPWD(account);
        return new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS, "重置密码成功！", null);
    }
}
