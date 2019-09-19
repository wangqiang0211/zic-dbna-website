package com.cdzic.zicTemplate.domain.entity.shiro;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class SysConfig implements Serializable{
    private static final long serialVersionUID = -5006045374007936118L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String url;
    private String permissionInit;
    private Integer sort;

    public SysConfig() {
    }

    public SysConfig(String url, String permissionInit, Integer sort) {
        this.url = url;
        this.permissionInit = permissionInit;
        this.sort = sort;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermissionInit() {
        return permissionInit;
    }

    public void setPermissionInit(String permissionInit) {
        this.permissionInit = permissionInit;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "SysConfig{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", permissionInit='" + permissionInit + '\'' +
                ", sort=" + sort +
                '}';
    }
}
