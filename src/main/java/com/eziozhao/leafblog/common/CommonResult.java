package com.eziozhao.leafblog.common;

import lombok.Data;

/**
 * 统一返回结果
 *
 * @author eziozhao
 * @date 2020/5/30
 */
@Data
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    protected CommonResult() {

    }

    protected CommonResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    protected CommonResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CommonResult success() {
        return new CommonResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 返回自定义消息的结果
     *
     * @param message 自定义消息
     * @param data    结果集
     */
    public static <T> CommonResult<T> success(String message, T data) {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static CommonResult fail() {
        return new CommonResult(ResultCode.FAIL.getCode(), ResultCode.FAIL.getMessage());
    }

    public static CommonResult fail(ResultCode code) {
        return new CommonResult(code.getCode(), code.getMessage());
    }

    public static CommonResult paramError() {
        return new CommonResult(ResultCode.PARAM_ERROR.getCode(), ResultCode.PARAM_ERROR.getMessage());
    }


    public static<T> CommonResult<T> authorizedError(T data) {
        return new CommonResult<T>(ResultCode.AUTHORIZATION_ERROR.getCode(), ResultCode.AUTHORIZATION_ERROR.getMessage(),data);
    }

    public static<T> CommonResult<T> authenticatedError(T data) {
        return new CommonResult<T>(ResultCode.AUTHENTICATION_ERROR.getCode(),
                ResultCode.AUTHENTICATION_ERROR.getMessage(),data);
    }

    /**
     * 自定义失败消息
     *
     * @param message 处理失败消息
     */
    public static CommonResult fail(String message) {
        return new CommonResult(ResultCode.FAIL.getCode(), message);
    }
}
