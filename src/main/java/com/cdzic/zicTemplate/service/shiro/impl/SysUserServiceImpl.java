package com.cdzic.zicTemplate.service.shiro.impl;

import com.cdzic.zicTemplate.dao.mapper.sys.SysUserMapper;
import com.cdzic.zicTemplate.dao.repo.sys.SysRoleRepo;
import com.cdzic.zicTemplate.dao.repo.sys.SysUserRepo;
import com.cdzic.zicTemplate.domain.entity.shiro.SysRole;
import com.cdzic.zicTemplate.domain.entity.shiro.SysUser;
import com.cdzic.zicTemplate.domain.vo.sys.SysUserBaseSVO;
import com.cdzic.zicTemplate.domain.vo.sys.SysUserVSVO;
import com.cdzic.zicTemplate.service.shiro.SysUserService;
import com.cdzic.zicTemplate.utils.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @creator yaotao
 * @date 2018/8/17 15:28
 * @describe:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserRepo sysUserDao;
    @Autowired
    private SysRoleRepo sysRoleDao;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUserVSVO> findByCondition(String searchKey, String searchValue) {
        String account=null,phoneNum=null,email=null;
        if (null!=searchKey&&!"".equals(searchKey)){
            if ("account".equals(searchKey)){
                account=searchValue;
            }else  if ("phone".equals(searchKey)){
                phoneNum=searchValue;
            }else  if ("email".equals(searchKey)){
                email=searchValue;
            }
        }
        return sysUserMapper.findByCondition(account,phoneNum,email);
    }

    @Override
    public SysUser findByAccount(String account) {
        SysUser user = sysUserDao.findFirstByAccount(account);
        SysRole role = user.getRole();
        if (null!=role){
            role.getPermissions().size();
        }
        return user;
    }

    @Override
    public SysUser findByPhoneOrEmail(String email) {
        return sysUserDao.findFirstByEmail(email);
    }

    @Override
    public List<SysUser> findAll() {
        return sysUserDao.findAll();
    }

    @Override
    public void save(SysUser sysUser) {
        sysUser.setPwd(CryptoUtils.encodeMD5(sysUser.getPwd()));
        sysUserDao.save(sysUser);
    }

    @Override
    public int disableUser(String account) {
        SysUser user = sysUserDao.findFirstByAccount(account);
        user.setStatus(0);
        return sysUserDao.save(user) != null ? 1 : 0;
    }

    @Override
    public int enableUser(String account) {
        SysUser user = sysUserDao.findFirstByAccount(account);
        user.setStatus(1);
        return sysUserDao.save(user) != null ? 1 : 0;
    }

    @Override
    public int verificationUser(String account, String pwd) {
        SysUser user = sysUserDao.findFirstByAccountAndPwd(account, CryptoUtils.encodeMD5(pwd));
        if (user != null) {//账号存在
            if (user.getStatus() == 1) {//账号状态为允许使用
//                sysUserDao.updateLoginDate(new Date(), account);//更新登录时间
                return 1;
            } else {//账号禁用
                return 3;
            }
        }
        if (null != sysUserDao.findFirstByAccount(account)) {//密码错误
            return 2;
        }
        //没有注册的账号
        return 0;
    }

    @Override
    public void modifyRole(String account, String[] roles) {
        SysUser user = sysUserDao.findFirstByAccount(account);
        List<SysRole> roleList = new ArrayList<>();
        for (int i = 0; i < roles.length; i++) {
            roleList.add(sysRoleDao.findFirstByRoleName(roles[i]));
        }
//        user.setRoles(roleList);
        save(user);
    }

    @Override
    public void modifyUser(String account, String role, String email, String phoneNum) {
        SysUser user = sysUserDao.findFirstByAccount(account);
        if (null!=user){
            SysRole sysRole = sysRoleDao.findFirstByRoleName(role);
            user.setEmail(email);
            user.setPhoneNum(phoneNum);
            save(user);
        }
    }

    @Override
    public int modifyPWD(String account, String oldPWD, String newPWD) {
        int resultCode = verificationUser(account, oldPWD);
        if (resultCode == 1) {
            return sysUserDao.updatePWD(CryptoUtils.encodeMD5(newPWD), account);
        }
        return 2;
    }

    @Override
    public int resetPWD(String account) {
        return sysUserDao.updatePWD(CryptoUtils.encodeMD5("12345678"),account);
    }

    @Override
    public int modifyPWD(String pwd, String phoneNumOrEmail) {
        return sysUserDao.updatePWDByEmail(pwd, phoneNumOrEmail);
    }

    @Override
    public int modifyEmailAndPhoneNumByAccount(String account, String email,String phoneNum) {
        return sysUserDao.updateEmailAndPhoneNumByAccount(email,phoneNum, account);
    }

    @Override
    public List<SysUserBaseSVO> findByRoleId(int roleId) {
        return sysUserMapper.findByRoleId(roleId);
    }
}
