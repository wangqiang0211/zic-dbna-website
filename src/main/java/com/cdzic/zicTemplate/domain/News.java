package com.cdzic.zicTemplate.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2019/04/03 上午 09:43
 * @Author 靳东
 * @ClassName News
 * @Description 新闻资讯
 */
@Entity
public class News implements Serializable{
    private static final long serialVersionUID = 7392858292444841966L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String thumbnail;//新闻缩略图
    private String headline; //标题
    @Column(columnDefinition = "tinyint(1)")
    private Integer newsType;//分类  0: 公司动态   1：行业资讯
    private String abstracts;//摘要
    private String articleContent;//内容
    private Integer sort;//排序属性 倒序
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;//更新时间

    public News() {
    }

    public News(String thumbnail, String headline, Integer newsType, String abstracts, String articleContent, Integer sort, Date updateDate) {
        this.thumbnail = thumbnail;
        this.headline = headline;
        this.newsType = newsType;
        this.abstracts = abstracts;
        this.articleContent = articleContent;
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

    public Integer getNewsType() {
        return newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
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
        return "News{" +
                "id=" + id +
                ", thumbnail='" + thumbnail + '\'' +
                ", headline='" + headline + '\'' +
                ", newsType=" + newsType +
                ", abstracts='" + abstracts + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", sort=" + sort +
                ", updateDate=" + updateDate +
                '}';
    }
}
