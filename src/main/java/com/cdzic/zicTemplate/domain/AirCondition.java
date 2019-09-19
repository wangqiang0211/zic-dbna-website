package com.cdzic.zicTemplate.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2019/04/07 下午 03:49
 * @Author 靳东
 * @ClassName AirCondition
 * @Description 五恒空凋
 */
@Entity
public class AirCondition implements Serializable{
    private static final long serialVersionUID = -4123488799788304397L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String thumbnail;//空调缩略图
    private String headline; //空调标题
    @Column(columnDefinition = "tinyint(1)")
    private Integer airConditionType;//类型（0：仙德五恒空调 1：空调常识）
    private String abstracts;//摘要
    private String articleContent;//详细内容
    private Integer sort;//排序
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;//更新时间

    public AirCondition() {
    }

    public AirCondition(String thumbnail, String headline, Integer airConditionType, String abstracts, String articleContent, Integer sort, Date updateDate) {
        this.thumbnail = thumbnail;
        this.headline = headline;
        this.airConditionType = airConditionType;
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

    public Integer getAirConditionType() {
        return airConditionType;
    }

    public void setAirConditionType(Integer airConditionType) {
        this.airConditionType = airConditionType;
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
        return "AirCondition{" +
                "id=" + id +
                ", thumbnail='" + thumbnail + '\'' +
                ", headline='" + headline + '\'' +
                ", airConditionType=" + airConditionType +
                ", abstracts='" + abstracts + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", sort=" + sort +
                ", updateDate=" + updateDate +
                '}';
    }
}
