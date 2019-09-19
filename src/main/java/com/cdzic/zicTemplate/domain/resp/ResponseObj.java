package com.cdzic.zicTemplate.domain.resp;

/**
 * 自定义的响应结果工具类
 */
public class ResponseObj {
    public static final int ERROR_CODE_SUCCESS = 100;
    public static final int ERROR_CODE_FAIL = 400;
    private Integer errorCode;
    private String errorMsg;//错误描述
    private Object respObj;//响应实体

    public ResponseObj() {
    }

    public ResponseObj(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public ResponseObj(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ResponseObj(Integer errorCode, String errorMsg, Object respObj) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.respObj = respObj;
    }

    public static int getErrorCodeSuccess() {
        return ERROR_CODE_SUCCESS;
    }

    public static int getErrorCodeFail() {
        return ERROR_CODE_FAIL;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getRespObj() {
        return respObj;
    }

    public void setRespObj(Object respObj) {
        this.respObj = respObj;
    }

    @Override
    public String toString() {
        return "ResponseObj{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", respObj=" + respObj +
                '}';
    }
}
