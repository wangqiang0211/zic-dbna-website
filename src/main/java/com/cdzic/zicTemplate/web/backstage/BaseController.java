package com.cdzic.zicTemplate.web.backstage;

import com.cdzic.zicTemplate.cache.impl.RedisStringService;
import com.cdzic.zicTemplate.cache.impl.RedisTokenService;
import com.cdzic.zicTemplate.domain.entity.shiro.SysUser;
import com.cdzic.zicTemplate.domain.resp.ResponseObj;
import com.cdzic.zicTemplate.service.email.MailService;
import com.cdzic.zicTemplate.service.shiro.SysUserService;
import com.cdzic.zicTemplate.utils.CryptoUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@ApiIgnore
@RestController
@RequestMapping("/bg")
public class BaseController {
    private static final Logger LOGGER = LogManager.getLogger(BaseController.class);
    @Value("${project.name}")
    private String projectName;
    @Autowired
    private MailService mailService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisStringService redisStringService;

    @GetMapping("/test")
    public ResponseObj test(){
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_SUCCESS);
        responseObj.setRespObj("访问成功");
        return responseObj;
    }

    @GetMapping("/403")
    public String perms403(){
        ResponseObj responseObj = new ResponseObj(403);
        return responseObj.toString();
    }

    /**
     * 登录验证接口
     *
     * @param account
     * @param pwd
     * @param isRemember
     * @return
     */
    @PostMapping("/login-validate")
    public ResponseObj loginValidate(@RequestParam("account") String account, @RequestParam("pwd") String pwd,
                                     @RequestParam("remember") boolean isRemember, HttpServletRequest request) {
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_FAIL);
        UsernamePasswordToken token = new UsernamePasswordToken(account, pwd, isRemember);
        try {
            SecurityUtils.getSubject().login(token);
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_SUCCESS);
        } catch (AuthenticationException e) {
            responseObj.setErrorMsg(e.getMessage());
        }
        return responseObj;
    }

    /**
     * 验证邮箱并发送验证码
     *
     * @param email
     * @return
     */
    @GetMapping("/send-email")
    public ResponseObj sendEmail(@RequestParam String email) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_FAIL);//初始化错误返回数值
        SysUser user = sysUserService.findByPhoneOrEmail(email);//通过邮箱验证用户是否存在
        if (null == user) {//用户不存在或 email输入错误
            responseObj.setErrorMsg("该邮箱未绑定!");
            return responseObj;
        }
        String validateCode = (Math.random() + "").substring(2, 6);//四位随机验证码
        redisStringService.put(projectName + email, email, validateCode, 300);
        try {
            //发送HTML格式邮件
            mailService.sendHtmlTemplate("仙德管理系统", email, "仙德后台验证码", "成都仙德",
                    "仙德登录密码修改", "仙德管理员修改密码验证码：" + validateCode + "(5分钟内有效)，如非本人操作，请忽略此邮箱。",
                    "成都仙德");
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_SUCCESS);
        } catch (Exception e) {
            LOGGER.error("发送邮箱失败，" + e);
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_FAIL);
            responseObj.setErrorMsg("发送邮箱失败！请稍后重试");
        }
        //发送普通文本模式邮件
        //mailService.sendSimple(email,"子程服务","尊敬的"+user.getAccount()+":\n您的验证码为 "+validateCode+" ，请在五分钟内进行验证。");
        return responseObj;
    }

    /**
     * 验证验证码并修改密码
     *
     * @param email
     * @param code
     * @param pwd
     * @return
     */
    @PostMapping("/email-validate")
    public ResponseObj emailValidate(@RequestParam String email, @RequestParam String code, @RequestParam String pwd) {
        ResponseObj responseObj = new ResponseObj(ResponseObj.ERROR_CODE_FAIL);
        String codeTemp = redisStringService.get(projectName + email, email);//发送的code
        if (null == codeTemp || !codeTemp.equals(code)) {//验证码验证失败
            responseObj.setErrorMsg("验证失败！");
            return responseObj;
        } else {
            sysUserService.modifyPWD(CryptoUtils.encodeMD5(pwd), email);
            responseObj.setErrorCode(ResponseObj.ERROR_CODE_SUCCESS);
            responseObj.setErrorMsg("修改密码成功！");
        }
        return responseObj;
    }


}
