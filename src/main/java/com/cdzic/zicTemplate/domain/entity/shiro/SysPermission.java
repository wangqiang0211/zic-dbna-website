package com.cdzic.zicTemplate.domain.entity.shiro;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class SysPermission implements Serializable {
    private static final long serialVersionUID = 3186630228946026341L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String permission;
    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")//mappedBy  ManyToOne不存在该属性  注：凡是双向关联，mapped必设，因为根本都没必要在2个表中都存在一个外键关联，在数据库中只要定义一边就可以了
    private List<SysRole> roles;
    private String description; // 权限描述

    public SysPermission() {

    }

    public SysPermission(String permission, String description) {
        this.permission = permission;
        this.description = description;
    }

    public SysPermission(String permission, List<SysRole> roles, String description) {
        this.permission = permission;
        this.roles = roles;
        this.description = description;
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
