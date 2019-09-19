package com.cdzic.zicTemplate.domain.resp;

/**
 * @creator yaotao
 * @date 2018/11/17 9:55
 * @describe:
 */
public class RespObjSqlItem {
    private Long total;
    private Object items;

    public RespObjSqlItem() {
    }

    public RespObjSqlItem(Long total) {
        this.total = total;
    }

    public RespObjSqlItem(Long total, Object items) {
        this.total = total;
        this.items = items;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getItems() {
        return items;
    }

    public void setItems(Object items) {
        this.items = items;
    }
}
