package com.eziozhao.leafblog.common;

/**
 * @Author eziozhao
 * @date 2020/5/30
 */
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败"),
    PARAM_ERROR(404, "参数错误"),
    AUTHORIZATION_ERROR(401, "未登录或token过期"),
    AUTHENTICATION_ERROR(403, "无权限");


    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
