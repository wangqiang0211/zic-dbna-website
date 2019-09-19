package com.cdzic.zicTemplate.domain.vo.sys;

/**
 * @creator yaotao
 * @date 2018/11/19 10:43
 * @describe:VS=ViewSelect
 */
public class SysUserVSVO {
    private String account;
    private String role;
    private String phoneNum;
    private String email;
    private int status;
    private String seccondLevelCode;
    private String seccondLevelName;
    private String createDate;

    public SysUserVSVO() {

    }

    public SysUserVSVO(String account, String role, String phoneNum, String email, int status, String seccondLevelCode, String seccondLevelName, String createDate) {
        this.account = account;
        this.role = role;
        this.phoneNum = phoneNum;
        this.email = email;
        this.status = status;
        this.seccondLevelCode = seccondLevelCode;
        this.seccondLevelName = seccondLevelName;
        this.createDate = createDate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSeccondLevelCode() {
        return seccondLevelCode;
    }

    public void setSeccondLevelCode(String seccondLevelCode) {
        this.seccondLevelCode = seccondLevelCode;
    }

    public String getSeccondLevelName() {
        return seccondLevelName;
    }

    public void setSeccondLevelName(String seccondLevelName) {
        this.seccondLevelName = seccondLevelName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
