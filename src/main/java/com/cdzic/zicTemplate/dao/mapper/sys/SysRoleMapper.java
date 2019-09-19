package com.cdzic.zicTemplate.dao.mapper.sys;

import com.cdzic.zicTemplate.dao.mapper.sys.pro.SysRoleProvider;
import com.cdzic.zicTemplate.domain.entity.shiro.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @creator yaotao
 * @date 2018/11/17 15:44
 * @describe:
 */
public interface SysRoleMapper {
    @SelectProvider(type = SysRoleProvider.class,method = "findByCondition")
    List<SysRole> findByCondition(@Param("roleName") String roleName);


}
