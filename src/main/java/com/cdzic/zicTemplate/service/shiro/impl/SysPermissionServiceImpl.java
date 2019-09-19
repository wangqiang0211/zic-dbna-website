package com.cdzic.zicTemplate.service.shiro.impl;

import com.cdzic.zicTemplate.dao.mapper.sys.SysPermissionMapper;
import com.cdzic.zicTemplate.dao.repo.sys.SysPermissionRepo;
import com.cdzic.zicTemplate.domain.entity.shiro.SysPermission;
import com.cdzic.zicTemplate.domain.vo.sys.SysPermissionCVO;
import com.cdzic.zicTemplate.service.shiro.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @creator yaotao
 * @date 2018/8/17 16:33
 * @describe:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    private SysPermissionRepo sysPermissionDao;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> findAll() {
        return sysPermissionDao.findAll();
    }

    @Override
    public List<SysPermission> findByCondition(String permission) {
//        System.out.println("查询sysPermission 长度！");
        return sysPermissionMapper.findByCondition(permission);
    }

    @Override
    public SysPermission findByPermission(String permission) {
        return sysPermissionDao.findFirstByPermission(permission);
    }

    @Override
    public SysPermission save(SysPermission sysPermission) {
        SysPermission permission = sysPermissionDao.save(sysPermission);
        return permission;
    }

    @Override
    public void delete(Long id) {
        sysPermissionDao.deleteById(id);
    }

    @Override
    public void modify(SysPermissionCVO sysPermissionCVO) {
        sysPermissionDao.updatePermissionAndDescription(sysPermissionCVO.getPermission(),sysPermissionCVO.getDescription(),sysPermissionCVO.getId());
    }
}
