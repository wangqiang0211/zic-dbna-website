package com.cdzic.zicTemplate.dao.repo.sys;

import com.cdzic.zicTemplate.domain.entity.shiro.SysConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @creator yaotao
 * @date 2018/8/17 15:03
 * @describe: 链接权限操作
 */
public interface SysConfigRepo extends JpaRepository<SysConfig,Long>{

    SysConfig findFirstById(Long id);

    @Modifying
    @Query("update SysConfig set url=?1,permissionInit=?2,sort=?3 where id=?4")
    int updateById(String url, String permissionInit, Integer sort, Long id);
}
