package com.cdzic.zicTemplate.domain.vo.sys;

/**
 * @creator yaotao
 * @date 2019/3/25 15:02
 * @describe:
 */
public class SysUserBaseSVO {
    private String phoneNum;
    private String account;

    public SysUserBaseSVO() {
    }

    public SysUserBaseSVO(String phoneNum, String account) {
        this.phoneNum = phoneNum;
        this.account = account;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
