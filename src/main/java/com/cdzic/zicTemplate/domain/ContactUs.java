package com.cdzic.zicTemplate.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2019/04/07 下午 04:07
 * @Author 靳东
 * @ClassName ContactUs
 * @Description 联系我们
 */
@Entity
public class ContactUs implements Serializable {
    private static final long serialVersionUID = -7748473780442868559L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//主键自增
    private String usKey;//key 值
    private String usValue;//value 值
    private Integer usType;//类型 0:公司信息 1：地图信息
    private Integer sort;//排序
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createDate;//创建时间

    public ContactUs() {
    }

    public ContactUs(String usKey, String usValue, Integer usType, Integer sort, Date createDate) {
        this.usKey = usKey;
        this.usValue = usValue;
        this.usType = usType;
        this.sort = sort;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsKey() {
        return usKey;
    }

    public void setUsKey(String usKey) {
        this.usKey = usKey;
    }

    public String getUsValue() {
        return usValue;
    }

    public void setUsValue(String usValue) {
        this.usValue = usValue;
    }

    public Integer getUsType() {
        return usType;
    }

    public void setUsType(Integer usType) {
        this.usType = usType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "ContactUs{" +
                "id=" + id +
                ", usKey='" + usKey + '\'' +
                ", usValue='" + usValue + '\'' +
                ", usType=" + usType +
                ", sort=" + sort +
                ", createDate=" + createDate +
                '}';
    }
}
