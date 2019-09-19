package com.cdzic.zicTemplate.domain.page;

import java.io.Serializable;

/**
 * @Date 2019/04/20 下午 04:15
 * @Author 靳东
 * @ClassName PreAndNextEntity
 */
public class PreAndNextEntity implements Serializable{
    private static final long serialVersionUID = -4334563951232250565L;

    private Long id;
    private String title;
    private int thisType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThisType() {
        return thisType;
    }

    public void setThisType(int thisType) {
        this.thisType = thisType;
    }
}
