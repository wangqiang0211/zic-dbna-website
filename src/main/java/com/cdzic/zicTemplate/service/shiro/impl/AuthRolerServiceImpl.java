package com.cdzic.zicTemplate.service.shiro.impl;

import com.cdzic.zicTemplate.dao.repo.sys.SysRoleRepo;
import com.cdzic.zicTemplate.domain.entity.shiro.SysRole;
import com.cdzic.zicTemplate.service.shiro.AuthRolerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthRolerServiceImpl implements AuthRolerService {
    @Autowired
    private SysRoleRepo sysRoleDao;
    @Override
    public SysRole find() {
        SysRole roleName = sysRoleDao.findFirstByRoleName("角色");
        roleName.getPermissions().size();
        return roleName;
    }
}
