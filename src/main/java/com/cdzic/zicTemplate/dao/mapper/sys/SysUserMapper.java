package com.cdzic.zicTemplate.dao.mapper.sys;

import com.cdzic.zicTemplate.dao.mapper.sys.pro.SysUserProvider;
import com.cdzic.zicTemplate.domain.vo.sys.SysUserBaseSVO;
import com.cdzic.zicTemplate.domain.vo.sys.SysUserVSVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @creator yaotao
 * @date 2018/11/19 10:35
 * @describe:
 */
public interface SysUserMapper {
    @SelectProvider(type = SysUserProvider.class,method = "findByCondition")
    List<SysUserVSVO> findByCondition(@Param("account") String account, @Param("phoneNum") String phoneNum, @Param("email") String email);

    @SelectProvider(type = SysUserProvider.class,method = "findByRoleId")
    List<SysUserBaseSVO> findByRoleId(@Param("role_id")int roleId);
}
