package com.cdzic.zicTemplate.service.shiro;

import com.cdzic.zicTemplate.domain.entity.shiro.SysPermission;
import com.cdzic.zicTemplate.domain.entity.shiro.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyShiroRealm extends AuthorizingRealm {
    @Value("${project.name}")
    private String projectName;
    @Value("${redis.login.space}")
    private String redisLoginCountSpace;
    @Value("${shiro.login.count}")
    private Integer redisLoginCount;
    @Value("${redis.login.lock.space}")
    private String redisLoginLockSpace;
    @Value("${shiro.login.lock.hours}")
    private Integer redisLoginLockHours;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SysUserService sysUserService;


    /**
     * 角色权限验证
     *
     * @param principalCollection PrincipalCollection  是一个包装对象，它表示"用户认证凭证信息"。包装的是谁呢？
     *                            没错，就是认证doGetAuthenticationInfo（）方法的返回值的第一个参数yourInputUsername。
     *                            你可以通过这个包装对象的getPrimaryPrincipal（）方法拿到此值。
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String userName = (String) principalCollection.getPrimaryPrincipal();
        SysUser user = sysUserService.findByAccount(userName);
        if (null != user.getRole()) {//查询数据库，得到你的权限信息与角色信息。并存放到权限凭证中
            List<SysPermission> permissions = user.getRole().getPermissions();
            info.addRole(user.getRole().getRoleName());
            for (SysPermission permission : permissions) {
                info.addStringPermission(permission.getPermission());
            }
        }
        return info;
    }

    /**
     * 身份验证  认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.increment(redisLoginCountSpace + username, 1);//次数++
        //计数大于redisLoginCount时，设置用户被锁定一小时

        if (Integer.parseInt(opsForValue.get(redisLoginCountSpace + username)) > redisLoginCount) {
            opsForValue.set(redisLoginLockSpace + username, "LOCK");
            stringRedisTemplate.expire(redisLoginLockSpace + username, redisLoginLockHours, TimeUnit.HOURS);
        }
        if ("LOCK".equals(opsForValue.get(redisLoginLockSpace + username))) {
            opsForValue.set(redisLoginCountSpace + username, "0");
            throw new AccountException("密码输入错误次数大于" + redisLoginCount + "次，帐号已经禁止登录" + redisLoginLockHours + "小时！");
        }
        int verificationCode = sysUserService.verificationUser(username, password);
        if (verificationCode == 0) {
            throw new AccountException("账户不存在");
        } else if (verificationCode == 2) {
            throw new AccountException("密码不正确");
        } else if (verificationCode == 3) {
            throw new AccountException("此帐号已经设置为禁止登录");
        }
        SysUser user = sysUserService.findByAccount(username);
        opsForValue.set(redisLoginCountSpace + username, "0");//用户名、密码、验证码都输入正确  重置次数
        return new SimpleAuthenticationInfo(username, user.getPwd(), projectName+"_"+username);
    }


    @Override
    public AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
        return super.getAuthorizationInfo(principals);
    }
}
