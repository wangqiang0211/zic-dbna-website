package com.cdzic.zicTemplate.service.shiro.impl;

import com.cdzic.zicTemplate.dao.mapper.sys.SysRoleMapper;
import com.cdzic.zicTemplate.dao.repo.sys.SysPermissionRepo;
import com.cdzic.zicTemplate.dao.repo.sys.SysRoleRepo;
import com.cdzic.zicTemplate.domain.entity.shiro.SysPermission;
import com.cdzic.zicTemplate.domain.entity.shiro.SysRole;
import com.cdzic.zicTemplate.service.shiro.SysRoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @creator yaotao
 * @date 2018/8/17 16:16
 * @describe:
 */
@Service
@Transactional()
public class SysRoleServiceImpl implements SysRoleService {
    private static final Logger LOGGER = LogManager.getLogger(SysRoleServiceImpl.class);
    @Autowired
    private SysRoleRepo sysRoleDao;
    @Autowired
    private SysPermissionRepo sysPermissionDao;
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> findByCondition(String roleName) {
        return sysRoleMapper.findByCondition(roleName);
    }

    @Override
    public List<SysRole> findAll() {
        return sysRoleDao.findAll();
    }

    @Override
    public SysRole findByRoleName(String roleName) {
        SysRole role = sysRoleDao.findFirstByRoleName(roleName);
        if (null != role) {
            role.getPermissions().size();
        }
        return role;
    }

    @Override
    public SysRole save(SysRole sysRole) {
        SysRole role = sysRoleDao.save(sysRole);
        return role;
    }

    @Override
    public void delete(Long id) {
        sysRoleDao.deleteById(id);
    }

    @Override
    public void modify(Long id, SysRole sysRole) {
        SysRole role = sysRoleDao.findById(id).get();
        role.setRoleName(sysRole.getRoleName());
        role.setDescription(sysRole.getDescription());
        role.setPermissions(sysRole.getPermissions());
        sysRoleDao.save(role);
    }

    @Override
    public void modifyPermission(Long id, String[] permissions) {
        SysRole sysRole = sysRoleDao.findById(id).get();
        List<SysPermission> permissionList = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            permissionList.add(sysPermissionDao.findFirstByPermission(permissions[i]));
        }
        sysRole.setPermissions(permissionList);
        sysRoleDao.save(sysRole);
    }
}
