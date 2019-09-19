package com.cdzic.zicTemplate.domain.vo.sys;

/**
 * @creator yaotao
 * @date 2018/8/31 9:43
 * @describe:
 */
public class SysRoleCVO {
    private Long id;
    private String roleName;
    private String description; // 角色描述

    public SysRoleCVO() {
    }

    public SysRoleCVO(Long id, String roleName, String description) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "SysRoleCVO{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
