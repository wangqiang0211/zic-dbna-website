package com.cdzic.zicTemplate.service.shiro;

import com.cdzic.zicTemplate.domain.entity.shiro.SysPermission;
import com.cdzic.zicTemplate.domain.vo.sys.SysPermissionCVO;

import java.util.List;

/**
 * @creator yaotao
 * @date 2018/8/17 16:33
 * @describe:
 */
public interface SysPermissionService {
    /**
     * 查询所有权限
     * @return
     */
    List<SysPermission> findAll();


    /**
     * 条件查询
     * @param permission
     * @return
     */
    List<SysPermission> findByCondition(String permission);

    /**
     * 根据permission查询权限
     * @param permission
     * @return
     */
    SysPermission findByPermission(String permission);

    /**
     * 添加权限
     * @param sysPermission
     */
    SysPermission save(SysPermission sysPermission);

    /**
     * 根据id删除权限
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id修改权限
     * @param sysPermissionCVO
     */
    void modify(SysPermissionCVO sysPermissionCVO);
}
