package com.cdzic.zicTemplate.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2019/04/07 下午 04:03
 * @Author 靳东
 * @ClassName VideoShow
 * @Description 视频展示
 */
@Entity
public class VideoShow implements Serializable {
    private static final long serialVersionUID = 5273794139243724895L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String thumbnail;//视频展示缩略图
    private String headline; //视频展示标题
    private String videoShowContent;//视频展示内容
    private Integer sort;//视频展示排序
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;//更新时间

    public VideoShow() {
    }

    public VideoShow(String thumbnail, String headline, String videoShowContent, Integer sort, Date updateDate) {
        this.thumbnail = thumbnail;
        this.headline = headline;
        this.videoShowContent = videoShowContent;
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

    public String getVideoShowContent() {
        return videoShowContent;
    }

    public void setVideoShowContent(String videoShowContent) {
        this.videoShowContent = videoShowContent;
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
        return "VideoShow{" +
                "id=" + id +
                ", thumbnail='" + thumbnail + '\'' +
                ", headline='" + headline + '\'' +
                ", videoShowContent='" + videoShowContent + '\'' +
                ", sort=" + sort +
                ", updateDate=" + updateDate +
                '}';
    }
}
