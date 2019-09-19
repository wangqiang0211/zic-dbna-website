package com.cdzic.zicTemplate.service.shiro;

import com.cdzic.zicTemplate.domain.entity.shiro.SysRole;

import java.util.List;

/**
 * @creator yaotao
 * @date 2018/8/17 16:16
 * @describe:
 */
public interface SysRoleService {

    /**
     * 查询/条件搜索角色
     * @param roleName
     * @return
     */
    List<SysRole> findByCondition(String roleName);

    /**
     * 查询所有的角色
     * @return
     */
    List<SysRole> findAll();

    /**
     * 根据roleName查询角色
     * @param roleName
     * @return
     */
    SysRole findByRoleName(String roleName);

    /**
     * 添加角色
     * @param sysRole
     */
    SysRole save(SysRole sysRole);

    /**
     * 根据id删除角色
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id修改角色信息
     * @param id
     * @param sysRole
     */
    void modify(Long id,SysRole sysRole);

    void modifyPermission(Long id,String [] permissions);
}
