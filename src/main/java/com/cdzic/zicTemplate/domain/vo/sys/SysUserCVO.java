package com.cdzic.zicTemplate.domain.vo.sys;

/**
 * @creator yaotao
 * @date 2018/8/31 10:47
 * @describe:
 */
public class SysUserCVO {
    private Long id;
    private String account;//用户账号
    private String pwd;//用户密码
    private String email;//邮箱
    private String phoneNum;
    private Integer status;//1允许登陆 0禁止登陆
    private String role;

    public SysUserCVO() {
    }

    public SysUserCVO(Long id, String account, String pwd, String email, String phoneNum, Integer status, String role) {
        this.id = id;
        this.account = account;
        this.pwd = pwd;
        this.email = email;
        this.phoneNum = phoneNum;
        this.status = status;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
