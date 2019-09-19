package com.cdzic.zicTemplate.domain.entity.shiro;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1198498760291444838L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String roleName;
//    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<SysUser> users;
    @ManyToMany()
    @JoinTable(name = "sysrole_syspermission",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")}
    )
    private List<SysPermission> permissions;
    private String description; // 角色描述

    public SysRole() {
    }

    public SysRole(String roleName, String description) {
        this.roleName = roleName;
        this.description = description;
    }

    public SysRole(String roleName, List<SysPermission> permissions, String description) {
        this.roleName = roleName;
        this.permissions = permissions;
        this.description = description;
    }

    public SysRole(String roleName, List<SysUser> users, List<SysPermission> permissions, String description) {
        this.roleName = roleName;
        this.users = users;
        this.permissions = permissions;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<SysUser> getUsers() {
        return users;
    }

    public void setUsers(List<SysUser> users) {
        this.users = users;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", permissions=" + permissions +
                ", description='" + description + '\'' +
                '}';
    }

}
