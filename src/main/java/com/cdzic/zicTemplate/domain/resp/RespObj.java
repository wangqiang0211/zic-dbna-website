package com.cdzic.zicTemplate.domain.resp;

/**
 * @creator yaotao
 * @date 2018/9/10 13:27
 * @describe:
 */
public class RespObj {
    private int type;
    private Object obj;

    public RespObj() {
    }

    public RespObj(int type, Object obj) {
        this.type = type;
        this.obj = obj;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
