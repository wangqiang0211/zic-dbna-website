package com.cdzic.zicTemplate.domain.vo.sys;

/**
 * @creator yaotao
 * @date 2018/8/30 15:19
 * @describe:
 */
public class SysPermissionCVO {
    private Long id;
    private String permission;
    private String description; // 权限描述

    public SysPermissionCVO() {
    }

    public SysPermissionCVO(String permission, String description) {
        this.permission = permission;
        this.description = description;
    }

    public SysPermissionCVO(Long id, String permission, String description) {
        this.id = id;
        this.permission = permission;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
