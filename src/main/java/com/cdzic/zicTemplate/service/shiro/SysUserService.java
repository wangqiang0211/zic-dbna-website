package com.cdzic.zicTemplate.service.shiro;

import com.cdzic.zicTemplate.domain.entity.shiro.SysUser;
import com.cdzic.zicTemplate.domain.vo.sys.SysUserBaseSVO;
import com.cdzic.zicTemplate.domain.vo.sys.SysUserVSVO;

import java.util.List;

/**
 * @creator yaotao
 * @date 2018/8/17 15:28
 * @describe:
 */
public interface SysUserService {


    /**
     * 后台 查询/条件搜索用户
     * @param searchKey
     * @param searchValue
     * @return
     */
    List<SysUserVSVO> findByCondition(String searchKey, String searchValue);

    /**
     * 根据account获取账号
     * @param account
     * @return
     */
    SysUser findByAccount(String account);

    /**
     * 根据手机号或email获取用户
     * @param phoneOrEmail
     * @return
     */
    SysUser findByPhoneOrEmail(String phoneOrEmail);

    /**
     * 获取所有用户
     * @return
     */
    List<SysUser> findAll();

    /**
     * 添加用户
     * @param sysUser
     */
    void save(SysUser sysUser);

    /**
     * 禁用账号
     * @param account
     * @return 0：操作失败，1：操作成功
     */
    int disableUser(String account);

    /**
     * 允许使用账号
     * @param account
     * @return 0：操作失败，1：操作成功
     */
    int enableUser(String account);

    /**
     * 验证用户
     * @return 0：账号不存在，1：验证成功，2：密码错误，3：账号被禁用
     */
    int verificationUser(String account,String pwd);

    /**
     * 修改指定用户的角色
     * @param account
     * @param roles
     */
    void modifyRole(String account,String [] roles);


    void modifyUser(String account,String role,String email,String phoneNum);

    /**
     * 修改账号密码
     * @param account
     * @param oldPWD
     * @param newPWD
     * @return 0：服务器内部错误，1：修改成功，2：旧密码错误
     */
    int modifyPWD(String account,String oldPWD,String newPWD);

    int resetPWD(String account);

    /**
     *邮箱验证修改密码
     * @param pwd
     * @param email
     * @return
     */
    int modifyPWD(String pwd,String email);

    /**
     * 通过account修改手机和邮箱
     * @param account
     * @param email
     * @return
     */
    int modifyEmailAndPhoneNumByAccount(String account,String email,String phoneNum);

    List<SysUserBaseSVO> findByRoleId(int roleId);


}
