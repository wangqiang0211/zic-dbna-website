package com.cdzic.zicTemplate.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2019/04/07 下午 04:01
 * @Author 靳东
 * @ClassName ProjectCase
 * @Description 工程案例
 */
@Entity
public class ProjectCase implements Serializable {
    private static final long serialVersionUID = 1575292240385341624L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String thumbnail;//工程案例缩略图
    private String headline; //工程案例标题
    private String projectCaseContent;//工程案例内容
    private Integer sort;//工程案例排序
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;//更新时间

    public ProjectCase() {
    }

    public ProjectCase(String thumbnail, String headline, String projectCaseContent, Integer sort, Date updateDate) {
        this.thumbnail = thumbnail;
        this.headline = headline;
        this.projectCaseContent = projectCaseContent;
        this.sort = sort;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getProjectCaseContent() {
        return projectCaseContent;
    }

    public void setProjectCaseContent(String projectCaseContent) {
        this.projectCaseContent = projectCaseContent;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "ProjectCase{" +
                "id=" + id +
                ", thumbnail='" + thumbnail + '\'' +
                ", headline='" + headline + '\'' +
                ", projectCaseContent='" + projectCaseContent + '\'' +
                ", sort=" + sort +
                ", updateDate=" + updateDate +
                '}';
    }
}
