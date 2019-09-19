package com.cdzic.zicTemplate.service.shiro;

import com.cdzic.zicTemplate.domain.entity.shiro.SysConfig;

import java.util.List;

/**
 * @creator yaotao
 * @date 2018/8/17 15:05
 * @describe:
 */
public interface SysConfigService {
    /**
     * 查询所有entity
     *
     * @return
     */
    List<SysConfig> findAll();

    SysConfig findById(Long id);

    /**
     * 保存entity
     *
     * @param sysConfig
     */
    void save(SysConfig sysConfig);

    /**
     * 根据id删除entity
     *
     * @param id
     * @return
     */
    void deleteById(Long id);

    /**
     * 根据id更新entity
     *
     * @param sysConfig
     * @return
     */
    int updateById(SysConfig sysConfig);


}
