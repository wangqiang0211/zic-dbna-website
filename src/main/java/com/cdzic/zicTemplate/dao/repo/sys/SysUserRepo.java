package com.cdzic.zicTemplate.dao.repo.sys;

import com.cdzic.zicTemplate.domain.entity.shiro.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;


public interface SysUserRepo extends JpaRepository<SysUser, Long> {

    /**
     * 根据邮箱验证是否有此用户  且返回整数
     * @param email
     * @return
     */
    int countByEmail(String email);

    SysUser findFirstByAccount(String account);

    SysUser findFirstByAccountAndPwd(String account, String pwd);

    SysUser findFirstByEmail(String email);

    @Modifying
    @Query("update SysUser set pwd=?1 where account=?2")
    int updatePWD(String pwd, String account);

    @Modifying
    @Query("update SysUser set lastLoginDate=?1 where account=?2")
    int updateLoginDate(Date date, String account);

    @Modifying
    @Query("update SysUser a set a.pwd=?1 where a.email=?2")
    int updatePWDByEmail(String pwd, String email);

    @Modifying
    @Query("update SysUser set email=?1,phoneNum=?2 where account=?3")
    int updateEmailAndPhoneNumByAccount(String email, String phoneNum, String account);

}
