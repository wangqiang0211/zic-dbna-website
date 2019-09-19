package com.cdzic.zicTemplate.utils.sort.domain;

/**
 * @Date 2019/04/12 下午 01:58
 * @Author 靳东
 * @ClassName SortDto
 */
public class SortDto {

    private String orderType;//排序方式
    private String orderField;//排序字段

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    //默认为DESC排序
    public SortDto(String orderField) {
        this.orderField = orderField;
        this.orderType = "desc";
    }

    public SortDto(String orderType, String orderField) {
        this.orderType = orderType;
        this.orderField = orderField;
    }

}
