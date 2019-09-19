package com.cdzic.zicTemplate.dao.repo.sys;

import com.cdzic.zicTemplate.domain.entity.shiro.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SysRoleRepo extends JpaRepository<SysRole,Long> {
    SysRole findFirstByRoleName(String roleName);
}
