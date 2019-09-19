package com.cdzic.zicTemplate.service.shiro.impl;

import com.cdzic.zicTemplate.dao.repo.sys.SysConfigRepo;
import com.cdzic.zicTemplate.domain.entity.shiro.SysConfig;
import com.cdzic.zicTemplate.service.shiro.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @creator yaotao
 * @date 2018/8/17 15:05
 * @describe:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysConfigServiceImpl implements SysConfigService {
    @Autowired
    private SysConfigRepo sysConfigDao;

    @Override
    public List<SysConfig> findAll() {
        return sysConfigDao.findAll();
    }

    @Override
    public SysConfig findById(Long id) {
        return sysConfigDao.findFirstById(id);
    }

    @Override
    public void save(SysConfig sysConfig) {
        sysConfigDao.save(sysConfig);
    }

    @Override
    public void deleteById(Long id) {
        sysConfigDao.deleteById(id);
    }

    @Override
    public int updateById(SysConfig sysConfig) {
        return sysConfigDao.updateById(sysConfig.getUrl(),sysConfig.getPermissionInit(),sysConfig.getSort(),sysConfig.getId());
    }
}
