package com.cdzic.zicTemplate.domain.entity.shiro;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class SysUser implements Serializable {
    private static final long serialVersionUID = -8260979323845216918L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String account;//用户账号
    private String pwd;//用户密码
    private String phoneNum;
    private String email;//手机号或邮箱
    private Integer status;//1允许登陆 0禁止登陆
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;//创建时间
    //    @ManyToMany()
//    @JoinTable(
//            name = "sysuser_sysrole",
//            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
//    )
    //    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="role_id")
    private SysRole role;


    public SysUser() {
    }

    public SysUser(String account, String pwd, String phoneNum, String email, Integer status, Date createDate, SysRole role) {
        this.account = account;
        this.pwd = pwd;
        this.phoneNum = phoneNum;
        this.email = email;
        this.status = status;
        this.createDate = createDate;
        this.role = role;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

}
