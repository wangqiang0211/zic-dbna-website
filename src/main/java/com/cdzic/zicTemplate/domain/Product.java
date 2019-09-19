package com.cdzic.zicTemplate.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2019/04/07 下午 03:54
 * @Author 靳东
 * @ClassName Product
 * @Description 产品展示
 */
@Entity
public class Product implements Serializable{
    private static final long serialVersionUID = 3276805995241722860L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String thumbnail;//产品缩略图
    private String headline; //产品标题
    private Integer productType;//产品类型（0：空气处理 1：冷热源  2：配套组件 3：智能控制）
    private String productContent;//产品内容
    private Integer sort;//排序
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;//更新时间

    public Product() {
    }

    public Product(String thumbnail, String headline, Integer productType, String productContent, Integer sort, Date updateDate) {
        this.thumbnail = thumbnail;
        this.headline = headline;
        this.productType = productType;
        this.productContent = productContent;
        this.sort = sort;
        this.updateDate = updateDate;
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

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProductContent() {
        return productContent;
    }

    public void setProductContent(String productContent) {
        this.productContent = productContent;
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
        return "Product{" +
                "id=" + id +
                ", thumbnail='" + thumbnail + '\'' +
                ", headline='" + headline + '\'' +
                ", productType=" + productType +
                ", productContent='" + productContent + '\'' +
                ", sort=" + sort +
                ", updateDate=" + updateDate +
                '}';
    }
}
