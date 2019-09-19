package com.cdzic.zicTemplate;

import com.cdzic.zicTemplate.service.shiro.MyFormAuthenticationFilter;
import com.cdzic.zicTemplate.service.shiro.MyPermissionsAuthorizationFilter;
import com.cdzic.zicTemplate.service.shiro.MyShiroRealm;
import com.cdzic.zicTemplate.service.shiro.MyUserFilter;
import com.cdzic.zicTemplate.service.shiro.impl.MyAccessControlFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    private static final Logger LOGGER = LogManager.getLogger(ShiroConfig.class);
    @Value("${project.name}")
    private String projectName;
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private String redisPort;
    @Value("${shiro.kickout.after}")
    private Boolean kickoutAfter;
    @Value("${shiro.session.max}")
    private Integer kickoutSessionMax;
    @Value("${shiro.kickout.url}")
    private String kickoutUrl;


    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/bg/login");
//        shiroFilterFactoryBean.setLoginUrl("/index");//2019年5月7日16:42:31 设置访问http://yato.free.idcfengye.com 代理，访问index主页
        // 登录成功后要跳转的链接  没有作用
        shiroFilterFactoryBean.setSuccessUrl("/bg/index");
        // 未授权界面; 没有权限访问 roles
        shiroFilterFactoryBean.setUnauthorizedUrl("/error404");
//        shiroFilterFactoryBean.setUnauthorizedUrl("/bg/403");

        Map<String, Filter> filterMap = new HashMap<>();
//        filterMap.put("kickout", myAccessControlFilter());//踢人
        filterMap.put("authc", new MyFormAuthenticationFilter());//重写authc拦截器
        filterMap.put("user", new MyUserFilter());//重写user拦截器
        filterMap.put("perms", new MyPermissionsAuthorizationFilter());//
        shiroFilterFactoryBean.setFilters(filterMap);

        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/module/**", "anon");
//        filterChainDefinitionMap.put("/login-validate", "anon"); roles[]/perms[]
        filterChainDefinitionMap.put("/ueditor/**", "anon");
//        swagger-ui不拦截
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");

        filterChainDefinitionMap.put("/bg/login-validate", "anon");//登录验证接口匿名访问
        filterChainDefinitionMap.put("/bg/send-email", "anon");
        filterChainDefinitionMap.put("/bg/email-validate", "anon");
//        filterChainDefinitionMap.put("/bg/index", "user,kickout");
        filterChainDefinitionMap.put("/bg/index", "user");
//        begin 2019年3月18日09:48:28测试
//        filterChainDefinitionMap.put("/bg/test", "perms[view:wechat]");
//        end

        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/bg/logout", "logout");
//        filterChainDefinitionMap.put("/bg/**", "user,kickout");
        filterChainDefinitionMap.put("/bg/**", "user");
        filterChainDefinitionMap.put("/bg/sys-user/*","perms[view:sys]");
        filterChainDefinitionMap.put("/bg/sys-role/*","perms[view:sys]");
        filterChainDefinitionMap.put("/bg/sys-permission/*","perms[view:sys]");
        filterChainDefinitionMap.put("/bg/point-system/update","perms[integral:update]");//积分管理修改接口
        filterChainDefinitionMap.put("/bg/access-token","perms[view:wechat]");//微信公众号AccessToken界面
        filterChainDefinitionMap.put("/", "authc");


        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        //filterChainDefinitionMap.put("/zic-backstage/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    //你的主机中的软件中止了一个已建立的连接。 新加
    @Bean
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }
    @Bean
    public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }
    // 完

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myShiroRealm());//身份认证realm;
        //自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());//cacheManager 缓存
        //自定义session管理 使用redis
        securityManager.setSessionManager(SessionManager());//shiro session的管理
        //注入记住我管理器; 不配置则使用默认配置
        securityManager.setRememberMeManager(rememberMeManager());//cookie管理对象
        return securityManager;
    }


    /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     *
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher()); //设置解密规则
        return myShiroRealm;
    }

    //因为我们的密码是加过密的，所以，如果要Shiro验证用户身份的话，需要告诉它我们用的是md5加密的，并且是加密了两次。同时我们在自己的Realm中也通过SimpleAuthenticationInfo返回了加密时使用的盐。这样Shiro就能顺利的解密密码并验证用户名和密码是否正确了。
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    /**
     * cookie对象;
     *
     * @return
     */
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("zicTemplate_rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能
     *
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("pyyX1c5x2f0LZZ7VKZXjKO=="));//随机生成的 cipherKey长度为16
        return cookieRememberMeManager;
    }

    /**
     * 配置shiro redisManager
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisHost);
        redisManager.setPort(Integer.parseInt(redisPort));
        redisManager.setExpire(1800);// 配置session过期时间,三十分钟
//         redisManager.setTimeout(10);//设置超时
//         redisManager.setPassword(password);//设置redis密码
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * shiro session的管理
     */
    public DefaultWebSessionManager SessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 限制同一账号登录同时登录人数控制
     *
     * @return
     */
    public MyAccessControlFilter myAccessControlFilter() {
        MyAccessControlFilter myAccessControlFilter = new MyAccessControlFilter();
//        System.out.println("进入权限控制，人员登录");
        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
        //这里我们还是用之前shiro使用的redisManager()实现的cacheManager()缓存管理
        //也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
        myAccessControlFilter.setCacheManager(cacheManager(), projectName);
        //用于根据会话ID，获取会话进行踢出操作的；
        myAccessControlFilter.setSessionManager(SessionManager());
        //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
        myAccessControlFilter.setKickoutAfter(kickoutAfter);
        //同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
        myAccessControlFilter.setMaxSession(kickoutSessionMax);
        //被踢出后重定向到的地址；
        myAccessControlFilter.setKickoutUrl(kickoutUrl);
        return myAccessControlFilter;
    }
}
