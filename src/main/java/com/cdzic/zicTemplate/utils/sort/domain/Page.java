package com.cdzic.zicTemplate.utils.sort.domain;

import java.util.List;

/**
 * @Date 2019/04/15 下午 02:50
 * @Author 靳东
 * @ClassName Page
 */
public class Page {
    private Integer currentPage;//当前页
    private Integer pageSize;//每页显示记录条数
    private Integer totalPage;//总页数;
    private List<?> dataList;//每页显示的数据
    private Integer star;//开始数据

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }
}
